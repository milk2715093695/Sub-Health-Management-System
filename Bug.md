# Bug修复文档


## Bug描述

1. 在测试前端网页时，发现`chat.html`在回复六条消息之后就会卡住（期间其他网页的加载也会卡住），直到五分钟后到达SseEmitter设定的超时时间，再次回复六条消息（会包括已经回复过的），继续卡住。
2. 在修复完第一个bug后，发现前端会没有间隔地对同一条消息反复回复，经过检查发现前端`chat.js`中的`sendMessage`函数只被调用了一次，但是后端`ChatController.java`却在反复调用`accessAPI`函数

## 原因分析
1. 关于第一个bug，发现原因是SseEmitter没有结束，因此，一直到超时位置，SseEmitter都不会自动关闭，从而导致了资源耗尽。
2. 关于第二个问题，发现其原因是由于前端的`EventSource`在后端的`SseEmitter`结束连接后并未关闭，因此它会自动再次发起连接请求。这样，后端的`accessAPI`函数就会被重新执行，导致函数反复被调用。

## 解决尝试-bug1
第一个问题解决的关键在于正确地结束SseEmitter，因此，需要在合适的位置添加`emitter.compelete()`语句。

## 代码修复-bug1
SseEmitter结束的时机应该是在调用的API的应答结束后，因此，需要想办法获取API应答是否完成。

事实上，该API确实提供了响应的状态信息，具体可以查看[coze的API文档](https://www.coze.com/docs/developer_guides/coze_api_overview?_lang=zh)

因此，在`JsonParser.java`中，我们用于处理API响应的`parseJson`函数需要加入一个标志响应是否完成的布尔变量`isParseCompelete`，也就是说返回值将从String变成一个由String和boolean组成的类

可以创建一个记录类`JsonParserData`记录返回值
```JsonParserData.java
package com.myapp.Model;

public record JsonParserData(String parsedContent, boolean isParseComplete) {}
```

并在`JsonParser.java`的`parseJson`函数中添加响应结束的处理
```JsonParser.java
if (event.equals("done")) return new JsonParserData("", true);
if (event.equals("error")) return new JsonParserData(data.getJSONObject("error_information").getString("err_msg") + "\n", true);
```

为`JsonParser.java`加入响应完成时的逻辑
```JsonParser.java
if (parsedData != null && !parsedData.isParseComplete()) {
    // ...
    // 一些代码
} else if (parsedData != null) {
    emitter.complete();
}
```

## 解决尝试-bug2
为了解决这个问题，我们考虑在后端向前端发送一个特殊的事件（例如"DONE"），以此告诉前端所有数据都已经发送完毕，此时应当关闭`EventSource`。

## 代码修复-bug2
为实施这个解决方案，我们在后端的`parseJson`函数中加入了以下代码：
```JsonParser.java
if (parsedData != null && !parsedData.isParseComplete()) {
    // ...
    // 一些代码
} else if (parsedData != null) {
    emitter.send(SseEmitter.event().name("DONE").data(""));
    emitter.complete();
}
```
这段代码的作用是：如果得到的解析数据是完成的（也就是说所有数据都已经读取完毕），那么就向前端发送一个"DONE"事件，然后完成数据的发送。

在前端，我们在`sendMessage`函数中使用`eventSource`监听"DONE"事件。如果监听到这个事件，就关闭`EventSource`。
```chat.js
eventSource.addEventListener('DONE', function(event) {
    console.log("DONE");
    eventSource.close();
})
```