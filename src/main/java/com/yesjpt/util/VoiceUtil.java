/*
 * https://www.cnblogs.com/weihuang6620/p/8818476.html
 */
package com.yesjpt.util;

import java.io.IOException;

import com.alibaba.fastjson.JSONObject;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class VoiceUtil {
	public static void main(String[] args) throws IOException {
		say("hello!语音引擎测试成功！");
	}
	public static JSONObject say(Object message) {
		JSONObject o = new JSONObject();
		o.put("success", true);
		o.put("message", message);
		if(message == null) {
			o.put("success", false);
			o.put("message", "没有提供要朗读的语音文字消息");
			return o;
		}
		// 这个Sapi.SpVoice是需要安装什么东西吗，感觉平白无故就来了
		ActiveXComponent sap = new ActiveXComponent("Sapi.SpVoice");
		// Dispatch是做什么的？
		Dispatch sapo = sap.getObject();
		try {
			// 音量 0-100
			sap.setProperty("Volume", new Variant(100));
			// 语音朗读速度 -10 到 +10
			sap.setProperty("Rate", new Variant(0));
			// 执行朗读
			Dispatch.call(sapo, "Speak", new Variant(message));
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			o.put("success", false);
			o.put("message", "语音引擎调用异常：" + e);
			return o;
		} finally {
			sapo.safeRelease();
			sap.safeRelease();
		}
	}
	
}
