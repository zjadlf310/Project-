package com.hairshop.vo;

import java.util.Date;

public class HairVO {
	private String id;
	private String name;
	private String pw;
	private String tel;
	private String mail;
	
	private String review_id;
	private Date review_date;
	private String review_hair;
	private String review_style;
	private String review_text;
	
	private String reservation_id;
	private Date reservation_date;
	private String reservation_hair;
	private String reservation_style;
	private String reservation_tel;
	private String reservation_name;
	private String reservation_photo;
	
	private String S_HAIR;
	private String S_STYLE;
	private Date S_DATE;
	private int S_TIME;
	
	
	public HairVO() {
		// TODO Auto-generated constructor stub
	}
	
	public HairVO (String review_id, Date review_date, String review_hair, String review_style,
			String review_text) {
		// TODO Auto-generated constructor stub
		this.review_id = review_id;
		this.review_date =review_date;
		this.review_hair = review_hair;
		this.review_style = review_style;
		this.review_text = review_text;
	}

	public HairVO(String reservation_name, String reservation_id, String reservation_tel, Date reservation_date,
			String reservation_hair, String reservation_style) {
		// TODO Auto-generated constructor stub
		this.reservation_name = reservation_name;
		this.reservation_id = reservation_id;
		this.reservation_tel = reservation_tel;
		this.reservation_date = reservation_date;
		this.reservation_hair = reservation_hair;
		this.reservation_style = reservation_style;
	}
	
	public HairVO(String reservation_name, String reservation_id, String reservation_tel, Date reservation_date,
			String reservation_hair, String reservation_style, String reservation_photo) {
		// TODO Auto-generated constructor stub
		this.reservation_name = reservation_name;
		this.reservation_id = reservation_id;
		this.reservation_tel = reservation_tel;
		this.reservation_date = reservation_date;
		this.reservation_hair = reservation_hair;
		this.reservation_style = reservation_style;
		this.reservation_photo = reservation_photo;
	}

	public HairVO(String name, String id, String pw, String tel, String mail) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.id = id;
		this.pw = pw;
		this.tel = tel;
		this.mail = mail;
	}

	public HairVO(String name, String id, String tel) {
		// TODO Auto-generated constructor stub
		this.name=name;
		this.id =id;
		this.tel = tel;
	}
	
	public HairVO(String s_HAIR2, String s_STYLE2, Date s_DATE, int s_TIME2) {
		// TODO Auto-generated constructor stub
		this.S_HAIR=s_HAIR2;
		this.S_STYLE=s_STYLE2;
		this.S_DATE=s_DATE;
		this.S_TIME=s_TIME2;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getReview_id() {
		return review_id;
	}
	public void setReview_id(String review_id) {
		this.review_id = review_id;
	}
	public String getReview_hair() {
		return review_hair;
	}
	public void setReview_hair(String review_hair) {
		this.review_hair = review_hair;
	}
	public String getReview_style() {
		return review_style;
	}
	public void setReview_style(String review_style) {
		this.review_style = review_style;
	}
	public String getReview_text() {
		return review_text;
	}
	public void setReview_text(String review_text) {
		this.review_text = review_text;
	}

	public Date getReview_date() {
		return review_date;
	}

	public void setReview_date(Date review_date) {
		this.review_date = review_date;
	}

	public String getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(String reservation_id) {
		this.reservation_id = reservation_id;
	}

	public Date getReservation_date() {
		return reservation_date;
	}

	public void setReservation_date(Date reservation_date) {
		this.reservation_date = reservation_date;
	}

	public String getReservation_hair() {
		return reservation_hair;
	}

	public void setReservation_hair(String reservation_hair) {
		this.reservation_hair = reservation_hair;
	}

	public String getReservation_style() {
		return reservation_style;
	}

	public void setReservation_style(String reservation_style) {
		this.reservation_style = reservation_style;
	}

	public String getReservation_tel() {
		return reservation_tel;
	}

	public void setReservation_tel(String reservation_tel) {
		this.reservation_tel = reservation_tel;
	}

	public String getReservation_name() {
		return reservation_name;
	}

	public void setReservation_name(String reservation_name) {
		this.reservation_name = reservation_name;
	}

	public String getReservation_photo() {
		return reservation_photo;
	}

	public void setReservation_photo(String reservation_photo) {
		this.reservation_photo = reservation_photo;
	}

	public String getS_HAIR() {
		return S_HAIR;
	}

	public void setS_HAIR(String s_HAIR) {
		S_HAIR = s_HAIR;
	}

	public String getS_STYLE() {
		return S_STYLE;
	}

	public void setS_STYLE(String s_STYLE) {
		S_STYLE = s_STYLE;
	}

	public Date getS_DATE() {
		return S_DATE;
	}

	public void setS_DATE(Date s_DATE) {
		S_DATE = s_DATE;
	}

	public int getS_TIME() {
		return S_TIME;
	}

	public void setS_TIME(int s_TIME) {
		S_TIME = s_TIME;
	}
	
}
