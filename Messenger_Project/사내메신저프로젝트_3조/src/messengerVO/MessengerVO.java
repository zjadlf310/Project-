package messengerVO;

import java.util.Date;
//messenger
public class MessengerVO { // vo-end
private int employee_list ;	//사원번호
private String employee_name;	//사원이름
private String employee_tel;	//사원연락처
private	String employee_rank;	//사원직급
private String employee_dep;	//사원부서
private String employee_pw;		//사원비밀번호
private int employee_logincheck;		//login체크 넘버

//notice
private int num;
private String title;
private String content;
private String writer;
private Date regdate;

private int letter_id;
private String letter_name;
private String letter_dep;
private String letter_send_name;
private String letter_title;
private String letter_text;
private String letter_filename;
private Date letter_date;

public MessengerVO() {}

//list,name,tel,rank,dep,pw
//Messenger
public MessengerVO (int employee_list, String employee_name, String employee_tel, String employee_dep,
		String employee_rank, String employee_pw, int employee_logincheck) {
	this.employee_list =employee_list ;	//
	this.employee_name=employee_name;
	this.employee_tel=employee_tel;
	this.employee_dep=employee_dep;
	this.employee_rank=employee_rank;
	this.employee_pw=employee_pw;
	this.employee_logincheck=employee_logincheck;//로그인 값
	
}//인자 받은 vo

//notice
public MessengerVO(int num, Date regdate , String title, String writer) {
	this.num=num;
	this.title=title;
	this.writer=writer;
	this.regdate=regdate;
}	

public MessengerVO(int num,String title, String content, String writer, Date regdate, int rewrite) {
	//rewrite 구분짓기용
	
	this.num=num;
	this.title=title;
	this.content=content;
	this.writer=writer;
	this.regdate=regdate;	
}


//letter
public MessengerVO(int id, String name, String dep,  String rank) {
	this.employee_list = id;
	this.employee_name = name;
	this.employee_dep = dep;
	this.employee_rank = rank;
	
}

public MessengerVO (int letter_id, String letter_name, String letter_dep, String letter_send_name, String letter_title,
					String letter_text, String letter_filename, Date letter_date) {
	this.letter_id = letter_id;
	this.letter_name = letter_name;
	this.letter_dep = letter_dep;
	this.letter_send_name = letter_send_name;
	this.letter_title = letter_title;
	this.letter_text = letter_text;
	this.letter_filename = letter_filename;
	this.letter_date = letter_date;
	
}

public MessengerVO (String letter_send_name, String letter_title, String letter_text, String letter_filename, Date letter_date) {
	
	this.letter_send_name = letter_send_name;
	this.letter_title = letter_title;
	this.letter_text = letter_text;
	this.letter_filename = letter_filename;
	this.letter_date = letter_date;

}

public int getEmployee_list() {
	return employee_list;
}

public void setEmployee_list(int employee_list) {
	this.employee_list = employee_list;
}

public String getEmployee_name() {
	return employee_name;
}

public void setEmployee_name(String employee_name) {
	this.employee_name = employee_name;
}

public String getEmployee_tel() {
	return employee_tel;
}

public void setEmployee_tel(String employee_tel) {
	this.employee_tel = employee_tel;
}

public String getEmployee_rank() {
	return employee_rank;
}

public void setEmployee_rank(String employee_rank) {
	this.employee_rank = employee_rank;
}

public String getEmployee_dep() {
	return employee_dep;
}

public int getEmployee_logincheck() {
	return employee_logincheck;
}

public void setEmployee_logincheck(int employee_logincheck) {
	this.employee_logincheck = employee_logincheck;
}

public void setEmployee_dep(String employee_dep) {
	this.employee_dep = employee_dep;
}

public String getEmployee_pw() {
	return employee_pw;
}

public void setEmployee_pw(String employee_pw) {
	this.employee_pw = employee_pw;
}

public int getNum() {
	return num;
}

public void setNum(int num) {
	this.num = num;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getContent() {
	return content;
}

public void setContent(String content) {
	this.content = content;
}

public String getWriter() {
	return writer;
}

public void setWriter(String writer) {
	this.writer = writer;
}

public Date getRegdate() {
	return regdate;
}

public void setRegdate(Date regdate) {
	this.regdate = regdate;
}

public int getLetter_id() {
	return letter_id;
}

public void setLetter_id(int letter_id) {
	this.letter_id = letter_id;
}

public String getLetter_name() {
	return letter_name;
}

public void setLetter_name(String letter_name) {
	this.letter_name = letter_name;
}

public String getLetter_dep() {
	return letter_dep;
}

public void setLetter_dep(String letter_dep) {
	this.letter_dep = letter_dep;
}

public String getLetter_send_name() {
	return letter_send_name;
}

public void setLetter_send_name(String letter_send_name) {
	this.letter_send_name = letter_send_name;
}

public String getLetter_title() {
	return letter_title;
}

public void setLetter_title(String letter_title) {
	this.letter_title = letter_title;
}

public String getLetter_text() {
	return letter_text;
}

public void setLetter_text(String letter_text) {
	this.letter_text = letter_text;
}

public String getLetter_filename() {
	return letter_filename;
}

public void setLetter_filename(String letter_filename) {
	this.letter_filename = letter_filename;
}

public Date getLetter_date() {
	return letter_date;
}

public void setLetter_date(Date letter_date) {
	this.letter_date = letter_date;
}


}//vo-end
