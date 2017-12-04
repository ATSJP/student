package com.atsjp.webDemo.entity;

public class Customer {
	private String id;
	private String cname;
	private String cpassword;
	private String cgender;
	private String cbirth;
	private String cmajority;
	private String cinterest;
	private String cemail;
	private String cphone;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String id, String cname, String cpassword, String cgender,
			String cbirth, String cmajority, String cinterest, String cemail,
			String cphone) {
		super();
		this.id = id;
		this.cname = cname;
		this.cpassword = cpassword;
		this.cgender = cgender;
		this.cbirth = cbirth;
		this.cmajority = cmajority;
		this.cinterest = cinterest;
		this.cemail = cemail;
		this.cphone = cphone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCpassword() {
		return cpassword;
	}

	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}

	public String getCgender() {
		return cgender;
	}

	public void setCgender(String cgender) {
		this.cgender = cgender;
	}

	public String getCbirth() {
		return cbirth;
	}

	public void setCbirth(String cbirth) {
		this.cbirth = cbirth;
	}

	public String getCmajority() {
		return cmajority;
	}

	public void setCmajority(String cmajority) {
		this.cmajority = cmajority;
	}

	public String getCinterest() {
		return cinterest;
	}

	public void setCinterest(String cinterest) {
		this.cinterest = cinterest;
	}

	public String getCemail() {
		return cemail;
	}

	public void setCemail(String cemail) {
		this.cemail = cemail;
	}

	public String getCphone() {
		return cphone;
	}

	public void setCphone(String cphone) {
		this.cphone = cphone;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", cname=" + cname + ", cpassword="
				+ cpassword + ", cgender=" + cgender + ", cbirth=" + cbirth
				+ ", cmajority=" + cmajority + ", cinterest=" + cinterest
				+ ", cemail=" + cemail + ", cphone=" + cphone + "]";
	}

}
