/**
 * 
 * 管理员实体类
 */
package com.example.demo.entity;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class AdminBean {
	private  int uid;
	/** @Valid 开始数据检验 放在要检验的参数旁边
	 * @NotBlank 不为空限制
	 * @Length 长度限制
	 *  @Email 邮箱限制
	 */
	@NotBlank
	private String uname;
	@NotBlank
	@Length(min = 6,message = "密码需要6位数以上")
	private String upass;
	@NotBlank
	private String usex;
	@Email
	private String uemil;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpass() {
		return upass;
	}
	public void setUpass(String upass) {
		this.upass = upass;
	}
	public String getUsex() {
		return usex;
	}
	public void setUsex(String usex) {
		this.usex = usex;
	}
	public String getUemil() {
		return uemil;
	}
	public void setUemil(String uemil) {
		this.uemil = uemil;
	}
	public AdminBean() {
		super();
	}
	@Override
	public String toString() {
		return "AdminBean [uid=" + uid + ", uname=" + uname + ", upass=" + upass + ", usex=" + usex + ", uemil=" + uemil
				+ "]";
	}
	
}
