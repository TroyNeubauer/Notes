package com.fish.core.packet;

public class RegisterReply {
	
	private RegisterReplyEnum reply;
	
	public RegisterReply(RegisterReplyEnum reply) {
		this.reply = reply;
	}

	public enum RegisterReplyEnum {
		REGISTER_SUCEED, REGISTER_FAIL_EMAIL_IN_USE, REGISTER_FAIL_USERNAME_IN_USE
	}

	public RegisterReplyEnum getReply() {
		return reply;
	}
}
