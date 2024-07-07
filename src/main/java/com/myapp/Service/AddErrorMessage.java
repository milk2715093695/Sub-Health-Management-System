package com.myapp.Service;


import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于向后端反馈给前端的数据中添加中文与英文的版本
 * @author milk
 */
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
