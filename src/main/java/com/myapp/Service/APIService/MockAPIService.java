package com.myapp.Service.APIService;


import com.myapp.Model.APIResponse;
import com.myapp.util.JsonParser;

import org.json.JSONArray;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * 模拟API服务实现类，用于在测试环境中提供假数据响应。
 * 当application.yml中设置了active: test时，会使用此实现类。
 * @author milk
 */
@Profile("test")
@Service
public class MockAPIService implements IAPIService {
    /**
     * 模拟访问API并处理用户输入，返回一个包含SseEmitter和假数据消息的APIResponse对象。
     *
     * @param userInput       用户输入的字符串。
     * @param chatHistory     当前会话的聊天历史JSONArray。
     * @param username        当前用户的用户名。
     * @param conversation_id 当前会话的ID。
     * @return APIResponse    包含SseEmitter和假数据消息的API响应对象。
     */
    @Override
    public APIResponse accessAPI(String userInput, JSONArray chatHistory, String username, String conversation_id) {
        SseEmitter emitter = new SseEmitter(300000L);
        String answer = "";

        new Thread(() -> {
            try {
                final String text = """
                        In the world of technology, coding is an essential skill that drives industries ranging from entertainment to healthcare. Regardless of the field you're in, knowledge of coding can open doors to exciting opportunities and innovative solutions.

                        In fact, the demand for coding skills is increasing at a rapid pace. According to the U.S. Bureau of Labor Statistics, employment in computer and information technology occupations is projected to grow 12 percent from 2018 to 2028, much faster than the average for all occupations.

                        Coding, at its core, is a problem-solving activity. It involves using a programming language to write instructions for a computer to execute. Each language has its own syntax and unique set of characteristics, but they all share the common goal of enabling computers to solve problems and perform tasks.

                        There are hundreds of programming languages currently in use, and they can be broadly classified into two categories: low-level languages and high-level languages. Low-level languages include Assembly and C, which are closer to the hardware and provide the programmer with a high level of control over the computer's operation. High-level languages, on the other hand, such as Python and JavaScript, are more user-friendly and easier to learn, but they offer less control over computer hardware.

                        When choosing a programming language to learn, it's important to consider your goals and the specific project you want to work on. If you're interested in building websites, for example, you would likely focus on learning HTML, CSS, and JavaScript. If you're interested in data analysis or machine learning, Python would be a great choice, as it's known for its simplicity and ability to handle large datasets.

                        Regardless of the language you choose, the process of learning to code involves understanding the basic concepts, such as variables, control flow, functions, and data structures, and then applying these concepts to solve real-world problems.

                        While coding can be challenging and even frustrating at times, it's also incredibly rewarding. With practice and persistence, you'll eventually be able to build your own programs, automate tasks, or even contribute to open-source projects. So, if you're a beginner, don't be discouraged by the initial learning curve. Every programmer was once a beginner, and the most important thing is to keep learning and experimenting.

                        In the world of coding, the possibilities are endless. So, whether you're interested in building the next viral app, revolutionizing an industry, or simply automating tedious tasks, learning to code can provide you with the toolset needed to turn your ideas into reality.
                """;

                final String[] words = text.split(" ");
                for (String word : words) {
                    emitter.send("\u200B " + word);
                    Thread.sleep(50);
                }
                emitter.send(SseEmitter.event().name("DONE").data(""));
                emitter.complete();
            } catch (Exception exception) {
                emitter.completeWithError(exception);
            }
        }).start();

        return new APIResponse(emitter, JsonParser.createJsonObject("assistant", "answer", answer, "text"));
    }
}
