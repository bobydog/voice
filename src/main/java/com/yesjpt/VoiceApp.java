package com.yesjpt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.yesjpt.util.VoiceUtil;

/**
 * @author junmingyang
 */
@SpringBootApplication
public class VoiceApp {
    public static void main(String[] args) {
        SpringApplication.run(VoiceApp.class, args);
        VoiceUtil.say("语音引擎启动成功！");
    }
}
