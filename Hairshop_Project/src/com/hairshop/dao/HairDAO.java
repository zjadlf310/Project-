package com.hairshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.hairshop.dbcon.DBcon;
import com.hairshop.vo.HairVO;

public class HairDAO {
	Connection con;
	PreparedStatement st;
	ResultSet rs;
	public HairDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<HairVO> ReviewOutput() throws ClassNotFoundException, SQLException {
		con = new DBcon().DBConnection();
		String sql = "select * from Review";
		st = con.prepareStatement(sql);
		rs = st.executeQuery();
		ArrayList<HairVO> ar1 = new ArrayList<HairVO>();
		while(rs.next()) {
			String review_id = rs.getString("ID");
			Date review_date = rs.getDate("R_DATE");
			String review_hair = rs.getString("R_HAIR");
			String review_style = rs.getString("R_STYLE");
			String review_text = rs.getString("R_TEXT");
			ar1.add(new HairVO(review_id, review_date, review_hair, review_style, review_text));
		}
		con.close();
		return ar1;
	}
	public boolean ReviewInput(String id,Date date, String hair, String style, String text) throws ClassNotFoundException, SQLException {
		con = new DBcon().DBConnection();
		String sql = "insert into Review values(?,?,?,?,?)";
		st = con.prepareStatement(sql);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");
		String date2 = sdf.format(date);
		st.setString(1, id);
		st.setString(2, date2);
		st.setString(3, hair);
		st.setString(4, style);
		st.setString(5, text);
		
		int aa=st.executeUpdate();
		con.close();
		if(aa==1) {
			return true;
		}else {
			return false;
		}
	}
	
	public HairVO Reviewcheck(String id) throws ClassNotFoundException, SQLException {
		con = new DBcon().DBConnection();
		String sql = "select * from Reservation where 'DATE' = ("
				+ "select max('DATE') from Reservation where ID = ?)";
		st = con.prepareStatement(sql);
		st.setString(1, id);
		rs = st.executeQuery();
		HairVO hvo = null;
		while(rs.next()) {
			String Reservation_name = rs.getString("NAME");
		    String Reservation_id = rs.getString("ID");
		    String Reservation_tel = rs.getString("TEL");
		    Date Reservation_date = rs.getDate("DATE");
		    String Reservation_hair = rs.getString("HAIR");
		    String Reservation_style = rs.getString("STYLE");
		    hvo = new HairVO(Reservation_name, Reservation_id, Reservation_tel, Reservation_date, Reservation_hair, Reservation_style);
		}
		con.close();
		return hvo;
		
	}
	
	public boolean login(String id, String pw) throws ClassNotFoundException, SQLException {
		con = new DBcon().DBConnection();
		String sql = "Select * from People where ID = ? and PW = ?";
		st = con.prepareStatement(sql);
		st.setString(1, id);
		st.setString(2, pw);
		rs = st.executeQuery();
		String id2 = null,pw2 = null;
		while(rs.next()) {
			id2 = rs.getString("ID");
			pw2 = rs.getString("PW");
		}
		con.close();
		if(id.equals(id2)||pw.equals(pw2)) {
			return true;
		}else {
			return false;
		}
		
	}
	public boolean newID(HairVO newid) throws ClassNotFoundException, SQLException {
		con = new DBcon().DBConnection();
		String sql = "insert into People values(?, ?, ?, ?, ?)";
		
		st = con.prepareStatement(sql);
		st.setString(1, newid.getName());
		st.setString(2, newid.getId());
		st.setString(3, newid.getPw());
		st.setString(4, newid.getTel());
		st.setString(5, newid.getMail());
		
		int aa=st.executeUpdate();
		con.close();
		if(aa==1) {
			return true;
		}else {
			return false;
		}
		
		
	}
	public boolean doubleIDcheck(String id) throws ClassNotFoundException, SQLException {
		con = new DBcon().DBConnection();
		String sql = "select ID from People where ID = ?";
		st = con.prepareStatement(sql);
		st.setString(1, id);
		rs = st.executeQuery();
		String id2 = null;
		while(rs.next()) {
			id2=rs.getString("ID");
		}
		con.close();
		if(!id.equals(id2)) {
			return true;
		}else {
			return false;
		}
	}
	
	public String SearchID(String name, String tel) throws ClassNotFoundException, SQLException {
		con = new DBcon().DBConnection();
		String sql = "select ID from People where NAME=? and TEL = ? ";
		st = con.prepareStatement(sql);
		st.setString(1, name);
		st.setString(2, tel);
		rs = st.executeQuery();
		String error = "해당하는 아이디가 없습니다.";
		String id = null;
		while(rs.next()) {
			id = rs.getString("ID");
		}
		con.close();
		if(id==null) {
			return error;
		}else {
			return id;
		}
	}
	public boolean updatePW(String pw, String id) throws ClassNotFoundException, SQLException {
		con = new DBcon().DBConnection();
		String sql = "update People set PW = ? where ID = ?";
		st = con.prepareStatement(sql);
		st.setString(1, pw);
		st.setString(2, id);
		
		int aa = st.executeUpdate();
		con.close();
		
		if(aa>0) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean checkID(String id, String name, String tel) throws SQLException, ClassNotFoundException {
		con = new DBcon().DBConnection();
		String sql = "select * from People where ID = ? and NAME = ? and TEL = ? ";
		st = con.prepareStatement(sql);
		st.setString(1, id);
		st.setString(2, name);
		st.setString(3, tel);
		rs = st.executeQuery();
		String id2 = null;
		while(rs.next()) {
			id2 = rs.getString("ID");
		}
		con.close();
		if(id2!=null) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public HairVO session(String id) throws ClassNotFoundException, SQLException {
		con = new DBcon().DBConnection();
		String sql = "select * from People where ID = ?";
		st = con.prepareStatement(sql);
		st.setString(1, id);
		rs = st.executeQuery();
		HairVO hvo = null;
		while(rs.next()) {
			String vname = rs.getString("NAME");
			String vid = rs.getString("ID");
			String vpw = rs.getString("PW");
			String vtel = rs.getString("TEL");
			String vmail = rs.getString("MAIL");
			hvo = new HairVO(vname,vid,vpw,vtel,vmail);
		}
		con.close();
		return hvo;
	}
	
	public ArrayList<HairVO> Schedule_info(String Hair, String sqlD )  //선생님 자료 출력
			throws SQLException, ClassNotFoundException{
		con = new DBcon().DBConnection();
		ArrayList<HairVO> tiarray = new ArrayList<HairVO>();
		String sql = "SELECT * FROM Schedule where S_HAIR=? and S_DATE=?";
		st = con.prepareStatement(sql);
		st.setString(1, Hair);
		st.setString(2, sqlD);

		rs = st.executeQuery();
		while(rs.next()){//이게 없는 경우를 잡아야 함.
			String S_HAIR = rs.getString("S_HAIR");
			String S_STYLE = rs.getString("S_STYLE");
			Date S_DATE = rs.getDate("S_DATE");
			int S_TIME = rs.getInt("S_TIME"); //이거 꼭 시간으로 안해도 됨. 보류
			HairVO tv = new HairVO(S_HAIR,S_STYLE,S_DATE,S_TIME);
			tiarray.add(tv);
		}
		con.close();
		return tiarray;
	}//////////////////////
	
	
	//결제 완료 누른 후 DB에 헤어  예약 정보 넣기
	public boolean S_insert( String S_HAIR, String S_STYLE, String S_DATE, int S_TIME ) throws SQLException, ClassNotFoundException{
		con = new DBcon().DBConnection();
		String sql = "insert into Schedule values(?,?,?,?)";		
	
			st=con.prepareStatement(sql);
			st.setString(1, S_HAIR);
			st.setString(2, S_STYLE);
			st.setString(3, S_DATE);
			st.setInt(4, S_TIME);
			st.executeUpdate();/////////
			con.close();
			return true;
	}
	
	//결제 완료 누른 후 예약 정보 DB에 넣기
	public boolean RE_insert(String rename, String reid, String tel, 
			String sday, String s_HAIR, String s_STYLE, String s_photo) throws SQLException, ClassNotFoundException {
		con = new DBcon().DBConnection();
		String sql = "insert into Reservation values(?,?,?,?,?,?,?)";		
		
		st=con.prepareStatement(sql);
		st.setString(1, rename);
		st.setString(2, reid);
		st.setString(3, tel);
		st.setString(4, sday);
		st.setString(5, s_HAIR);
		st.setString(6, s_STYLE);
		st.setString(7, s_photo);
		
		
		st.executeUpdate();/////////
		
		con.close();
		return true;
	}
	
	

}
