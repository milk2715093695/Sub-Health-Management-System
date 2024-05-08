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
   "query": "我最近经常头痛",
   "stream": false
}'
```
