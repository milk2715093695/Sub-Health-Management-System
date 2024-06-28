package com.myapp.Service;


import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AddErrorMessage {
    public void addErrorMessage(Map<String, Object> result, String zhMsg, String enMsg) {
        Map<String, String> errMessage = new HashMap<>();
        errMessage.put("zh-CN", zhMsg);
        errMessage.put("en", enMsg);
        result.put("errMessage", errMessage);
        result.put("success", false);
    }
}
