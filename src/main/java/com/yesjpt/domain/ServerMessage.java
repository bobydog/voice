package com.yesjpt.domain;

/**
 * @author junmingyang
 */
public class ServerMessage {

    private String content;

    public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

    @Override
    public String toString() {
        return content;
    }

}
