package com.yesjpt.web;

import com.alibaba.fastjson.JSONObject;
import com.yesjpt.util.ContextUtil;
import com.yesjpt.util.VoiceUtil;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoiceController {
	/*
	 * 这个东西就是前端直接传参数过来，调用的格式为：http://localhost:8082/say?message=太好了
	 *
	 */
	@GetMapping("/say")
    public Object say(String message) {
    	System.out.println(new Throwable().getStackTrace()[0] + ", message = " + message);
    	return VoiceUtil.say(message);
    }
	/*
	 * 用这个前端可以灵活地加很多东西过来，调用的格式为：http://localhost:8082/sayRest?message=真是太好了
	 */
	@GetMapping("/sayRest")
    public Object sayRest() {
		//这个方法很帅
    	Map<String, Object> params = ContextUtil.getParams();
    	System.out.println(new Throwable().getStackTrace()[0] + "，前端传递过来的参数为：" + params);
    	JSONObject o = VoiceUtil.say(params.get("message"));
    	o.putAll(params);
    	System.out.println("前后端处理后的JSON = " + o);
    	return o;
    }
}
