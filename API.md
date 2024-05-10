```shell
curl --location --request POST 'https://api.coze.com/open_api/v2/chat' \
--header 'Authorization: Bearer pat_7XBJJBTZ5RSwd98QVw4VPJ68jG1hZYHIIuj6xEUxPX2t6XNlfygc4JLNhER4myCk' \
--header 'Content-Type: application/json' \
--header 'Accept: */*' \
--header 'Host: api.coze.com' \
--header 'Connection: keep-alive' \
--data-raw '{
   "conversation_id": "123",
   "bot_id": "7366455788757778437",
   "user": "milk",
   "query": "风吹了会头痛，水果吃多了也头痛，偶尔运动完也头痛",
   "stream": true,
   "chat_history": [
       {
           "role": "user",
           "content": "你好",
           "content_type": "text"
       },
       {
           "role": "assistant",
           "type": "answer",
           "content": "你好,请问有什么可以帮助你的吗？",
           "content_type": "text"
       },
       {
           "role": "user",
           "content": "你好，我最近经常头痛",
           "content_type": "text"
       },
       {
           "role": "assistant",
           "type": "answer",
           "content": "很抱歉听说您经常头痛，如果您提供更多关于您的症状的信息，我可以帮您判断您生了什么病",
           "content_type": "text"
       }
   ]
}'
```

```shell
curl --location --request POST 'https://api.coze.com/open_api/v2/chat' \
--header 'Authorization: Bearer pat_7XBJJBTZ5RSwd98QVw4VPJ68jG1hZYHIIuj6xEUxPX2t6XNlfygc4JLNhER4myCk' \
--header 'Content-Type: application/json' \
--header 'Accept: */*' \
--header 'Host: api.coze.com' \
--header 'Connection: keep-alive' \
--data-raw '{
     "conversation_id": "123",
     "bot_id": "7366455788757778437",
     "user": "milk",
     "query": "我刚才咨询了什么",
     "stream": true,
     "chat_history": [
         {
             "role":"user",
             "content":"你好",
             "content_type":"text"
         },
         {
             "role":"assistant",
             "type":"answer",
             "content":"你好,请问有什么可以帮助你的吗？",
             "content_type":"text"
         },
         {
             "role":"user",
             "content":"帮我查一下今天的科技新闻",
             "content_type":"text"
         },
         {
             "role":"assistant",
             "type":"answer",
             "content":"以下是今天的一些科技新闻：\n\n1. 数字经济产业有哪些最新科技？来这里沉浸式体验→：2023世界数字经济大会目前正在浙江宁波举行，“上天入海”的高科技新产品让众多观众驻足体验。会上，我国的6G和5.5G等前沿技术以及相关产品纷纷亮相。来源：央视新闻。\n\n2. 中国这4大科技，领先世界，就连欧美国家都只能甘拜下风：中国这些年，科技在飞速发展当中，如今已经掌握了不少领先世界的科技。来源：科学知识点。\n\n3. 中国4大领先全球顶尖科技，日美也不得不服，形成垄断趋势：中国科技这些年可以说取得了不少的成就，在逐渐的突破下，中国在科技领域上有着越来越多的改变，更甚至从落后他国，到领先世界。来源：科学知识点。\n\n4. 中国20大顶尖科技→：经过努力，目前，中国已在不少领域建立起技术优势。本文梳理了中国在世界上处于领先地位的顶尖技术。来源：中国工业报。\n\n5. 中国领先全球的5大顶尖科技，日美也不得不服，你知道多少呢？：虽然中国在不少领域还比不上西方国家，但是经过自己的努力,也已经有了不少属于自己的拿手戏，就有不少科技领先世界，成为顶尖技术，就是日美也不得不服。来源：科学知识点。",
             "content_type":"text"
         }
      ]
}'

```

pat_0wos8gCEuKpPPu2jtdHmBoDosjfzwtHWZRNxUUEPogoJV0N4CjuCtxC2WSGkecdM
