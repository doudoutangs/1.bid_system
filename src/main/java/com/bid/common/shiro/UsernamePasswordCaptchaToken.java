package com.bid.common.shiro;


import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 扩展添加验证码-继承用户验证类
 * @author sugar 2016-06-26 12:30:59
 */
public class UsernamePasswordCaptchaToken extends UsernamePasswordToken {

	private static final long serialVersionUID = 1L;

	private String captcha;

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public UsernamePasswordCaptchaToken() {
		super();
	}

	public UsernamePasswordCaptchaToken(String username, char[] password, String captcha, boolean rememberMe, String host) {
		super(username, password, rememberMe, host);
		this.captcha = captcha;
	}

	@Override
	public String toString() {
		return "UsernamePasswordCaptchaToken [captcha=" + captcha
				+ ", toString()=" + super.toString() + "]";
	}
	
	

}