package messengerDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import db.DBCon;
import login.Login;
import messengerVO.MessengerVO;

public class MessengerDAO {//class DAO
	//DAO는 DB에서 값 불러올 객체같은 존재임
	//connection class에서 연결값 다 불러와야함.
		
	private Connection con;	//계속 쓸것들 선언
	PreparedStatement st=null;//준비된 문장
	

	//Statement st2=con.createStatement();
	ResultSet rs=null;//이건 근데 출력할때 빼고는 안쓸꺼 같긴함.

	public MessengerDAO(){
		
	}
	
	//쪽지
	public ArrayList<MessengerVO> LetterSendSearch() throws SQLException, ClassNotFoundException{
		con=new DBCon().DBConnection();
		String sql = "select * from"
				+ " kajaemployee order by employee_list, employee_name";
		st = con.prepareStatement(sql);
		
		rs = st.executeQuery();
		
		ArrayList<MessengerVO> arVO = new ArrayList<MessengerVO>();
		while(rs.next()) {
			int Eid = rs.getInt("employee_list");
			String Ename = rs.getString("employee_name");
			String Edep = rs.getString("employee_dep");
			String Erank = rs.getString("employee_rank");
			arVO.add(new MessengerVO(Eid,Ename,Edep,Erank));	
		
		}
		con.close();
		return arVO;
	}

	public ArrayList<MessengerVO> LetterSendSearch(String name) throws SQLException, ClassNotFoundException{
		con=new DBCon().DBConnection();
		
		String sql = "select * from"
				+ " kajaemployee where employee_name = ? order by employee_list, employee_name";
		st = con.prepareStatement(sql);
		
		st.setString(1, name);
		
		rs = st.executeQuery();
		
		ArrayList<MessengerVO> arVO = new ArrayList<MessengerVO>();
		while(rs.next()) {
			int Eid = rs.getInt("employee_list");
			String Ename = rs.getString("employee_name");
			String Edep = rs.getString("employee_dep");
			String Erank = rs.getString("employee_rank");
			arVO.add(new MessengerVO(Eid,Ename,Edep,Erank));	
		
		}
		con.close();
		return arVO;
	}
	
	public ArrayList<MessengerVO> LetterList(int id,String name) throws SQLException, ClassNotFoundException {
		con=new DBCon().DBConnection();
		String sql = "select * from letter where letter_id = ? and letter_name = ?";
		st = con.prepareStatement(sql);
		st.setInt(1, id);
		st.setString(2, name);
		rs =st.executeQuery();
		
		ArrayList<MessengerVO> arVO = new ArrayList<MessengerVO>();
		while(rs.next()) {
			String sendname = rs.getString("letter_send_name");
			String title = rs.getString("letter_title");
			String text = rs.getString("letter_text");
			String filename = rs.getString("letter_filename");
			Date letterdate = rs.getDate("letter_date"); 
			
			arVO.add(new MessengerVO(sendname, title, text, filename, letterdate));
		}
		con.close();
		return arVO;
	}
	
	public void LetterSend(int id, String name, String dep,String send_name, String title, String text, String filename) throws SQLException, ClassNotFoundException {
		con=new DBCon().DBConnection();
		String sql = "insert into letter values(?, ?, ?, ?, ?, ? ,?, sysdate)";
		
		st = con.prepareStatement(sql);
		st.setInt(1, id);
		st.setString(2, name);
		st.setString(3, dep);
		st.setString(4, send_name);
		st.setString(5, title);
		st.setString(6, text);
		st.setString(7, filename);
		st.executeUpdate();
		con.close();
	}
	
	public boolean LetterDelete(int id, String name, String sendname, String title) throws SQLException, ClassNotFoundException {
		con=new DBCon().DBConnection();
		String sql = "delete from letter where letter_id = ? and letter_name = ? and letter_send_name = ? and letter_title = ?";
		
		st = con.prepareStatement(sql);
		st.setInt(1, id);
		st.setString(2, name);
		st.setString(3, sendname);
		st.setString(4, title);
		st.executeUpdate();
		
		con.close();
		return true;
	}
	
	
//notice 화면
	public ArrayList<MessengerVO> NoticeSendList() throws SQLException, ClassNotFoundException{
		con=new DBCon().DBConnection();
		String sql = "select num, regdate, title, writer from notice order by num";
		st = con.prepareStatement(sql);
		
		rs = st.executeQuery();
		
		ArrayList<MessengerVO> noVO = new ArrayList<MessengerVO>();
		while(rs.next()) {
			int Enum = rs.getInt("num");
			Date Eregdate = rs.getDate("regdate");
			String Etitle = rs.getString("title");
			String Ewriter = rs.getString("writer");
			noVO.add(new MessengerVO(Enum, Eregdate, Etitle, Ewriter));	
		
		}
		con.close();
		return noVO;
	}
	
 //공지넣기
	public boolean NoticeInsert(String title, String write, String name) throws SQLException, ClassNotFoundException{
		con=new DBCon().DBConnection();
		String sql = "insert into notice VALUES ((select NVL(MAX(NUM),0)+1 from notice) , ? , ? , ? , SYSDATE)";
		try {
			st = con.prepareStatement(sql);
			st.setString(1,title);
			st.setString(2,write);
			st.setString(3,name);
			st.executeUpdate();
			
		} catch (Exception e) {
			con.close();
			return false;
		}
		con.close();
		return true;
	}

//공지수정 창 
	public MessengerVO NoticeRewrite(int rowNum) throws SQLException, ClassNotFoundException {
		con=new DBCon().DBConnection();
		String sql= "select *from notice where NUM=?";
		st = con.prepareStatement(sql);
		st.setInt(1, rowNum);
		rs = st.executeQuery();
		MessengerVO vos = null;
		int rewrite=1;
		while(rs.next()) {
			int num = rs.getInt("num");
			String title = rs.getString("title");
			String content =rs.getString("content");
			String writer = rs.getString("writer");
			Date regdate = rs.getDate("regdate");
			
			vos = new MessengerVO(num, title, content, writer, regdate, rewrite);	
		}
		con.close();
		return vos;
	}
//공지 수정넣기
	public boolean NoticeUpdate(int num, String title, String content, String writer) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		con=new DBCon().DBConnection();
		String sql = "update notice set title = ?, content = ?, writer = ?, regdate = SYSDATE "
				+ " where NUM = ?";
		
		st=con.prepareStatement(sql);
		st.setString(1, title);
		st.setString(2, content);
		st.setString(3, writer);
		st.setInt(4, num );
		st.executeUpdate();
		
		con.close();	
		return true;
	}
	
	public boolean NoticeDelete(int num) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		con=new DBCon().DBConnection();
		String sql = "delete from notice where NUM = ?";
		
		st=con.prepareStatement(sql);
		st.setInt(1, num);
		st.executeUpdate();
		
		con.close();
		return true;
	}

	
	
//아이디 중복체크
	public boolean id_doubleCheck(String lb_join_id_getText) throws SQLException, ClassNotFoundException {//id중복체크 회원가입시 필요
		con=new DBCon().DBConnection();
		String str1="select employee_list from kajaemployee";
		st=con.prepareStatement(str1);
		rs=st.executeQuery();
		// id는 이미 있는 사람들 id정보가 출력됨.
		int sw=0;//switch를 사용.
		while(rs.next()) {
			String sid=rs.getString(1);//"id"필드
			if(lb_join_id_getText.equals (sid)) { //내용비교 
				sw=1;
			}
		}
		if(sw==1) {
			con.close();
			return false;//중복이야
		}else {
			con.close();
			return true;//중복아니야~
		}
		
	}
	
	
//아이디 가입하기 절차
	public boolean kaip(String id, String name, String tel1, String rank, String dep) throws ClassNotFoundException, SQLException {
		con=new DBCon().DBConnection();
		String tel=phone(tel1);
		String str="insert into kajaemployee values(?,?,?,?,?,?,?)";

		try {
			
			st=con.prepareStatement(str);
			st.setInt(1, Integer.parseInt(id));
			st.setString(2,name);
			st.setString(3, tel);//전화번호
			st.setString(4, rank);
			st.setString(5,dep);
			st.setString(6, tel1);//비밀번호
			st.setInt(7, 0);//가입 초기치 로그인값 0
			st.executeUpdate();
			//}
		}catch(Exception e) {
			con.close();
			return false;
		}//catch-end
		con.close();
		return true;
	}

	public boolean update(int id, String pw, String name, String tel1, String rank, String dep) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		con=new DBCon().DBConnection();
		String tel=phone(tel1);
		String str="update kajaemployee set  employee_name=?,  employee_tel=?, "
				+ "employee_rank=?, employee_dep=?, employee_pw=? , employee_logincheck=?  where employee_list=?";
		int ch1=0;
		try {					
			st=con.prepareStatement(str);
			st.setString(1,name);
			st.setString(2, tel);//전화번호
			st.setString(3, rank);
			st.setString(4,dep);
			st.setString(5, pw);//비밀번호
			st.setInt(6, ch1);//수정해서 로그아웃 되니 0으로
			st.setInt(7, id);//
			
			st.executeUpdate();
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "에러1");
			con.close();
			return false;
		}//catch-end	
		con.close();
		return true;
	}
		
	private String phone(String tel) {
		// TODO Auto-generated method stub
		if (tel == null) {
			return "";
		}
	    if (tel.length() == 8) {
	    	return tel.replaceFirst("^([0-9]{4})([0-9]{4})$", "$1-$2");
	    } else if (tel.length() == 12) {
	    	return tel.replaceFirst("(^[0-9]{4})([0-9]{4})([0-9]{4})$", "$1-$2-$3");
	    }
	    return tel.replaceFirst("(^02|[0-9]{3})([0-9]{3,4})([0-9]{4})$", "$1-$2-$3");
	}





//로그인 정보 대조하기

	public boolean login(String id, String pw) throws ClassNotFoundException, SQLException {
		con=new DBCon().DBConnection();
		String str="select employee_list, employee_pw, employee_logincheck from kajaemployee where employee_list=? and employee_pw=?";
		String str1="select employee_logincheck from kajaemployee where employee_list=? and employee_pw=?";
	
		int sw=0;
		int id1 = 0;
		String pw1 = "";
		int ch1=0;
		
		st=con.prepareStatement(str1);
		st.setInt(1, Integer.parseInt(id));//id number		
		st.setString(2, pw);
		rs=st.executeQuery();
	
		while (rs.next()) {
			ch1=rs.getInt("employee_logincheck");
		}
		
		if(ch1==0) {
			st=con.prepareStatement(str);
			st.setInt(1, Integer.parseInt(id));//id number		
			st.setString(2, pw);
			rs=st.executeQuery();
		
			while (rs.next()) {
				id1=rs.getInt("employee_list");
				pw1=rs.getString("employee_pw");	
				ch1=rs.getInt("employee_logincheck");
			}
		
			if(id1==Integer.parseInt(id) && pw.equals(pw1)) {
				sw=1;
			
		
			}//if-end
			if(sw==1) {
				con.close();
				return true;
			}else {
				JOptionPane.showMessageDialog(Login.bt_login, "잘못 입력하셨습니다.");
				con.close();
				return false;
			}
		}else {
			JOptionPane.showMessageDialog(Login.bt_login, "이미 로그인 중입니다.");
			con.close();
			return false;
		}
		
	
	}//login-end
	
	//로그인시 update 로그인체크	
		public boolean inup(int id) throws ClassNotFoundException, SQLException {
			// TODO Auto-generated method stub
			con=new DBCon().DBConnection();
			String str="update kajaemployee set employee_logincheck=?  where employee_list=?";
			try {					
				st=con.prepareStatement(str);
				st.setInt(1,1); 	//로그인 시 1
				st.setInt(2, id); //회원정보
				st.executeUpdate();
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, "에러1");
				con.close();
				return false;
			}//catch-end	
			con.close();
			return true;
		}
		//로그인시 update 로그인체크	
		public boolean outup(int id) throws ClassNotFoundException, SQLException {
			// TODO Auto-generated method stub
			con=new DBCon().DBConnection();
			String str="update kajaemployee set employee_logincheck=?  where employee_list=?";
			try {					
				st=con.prepareStatement(str);
				st.setInt(1,0); 	//로그아웃 시 1
				st.setInt(2, id); //회원정보
				st.executeUpdate();
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, "중복로그아웃에러");
				con.close();
				return false;
			}//catch-end
			con.close();
			return true;
		}
	
//로그인 정보받기 >>메신저 창으로 쏴주는 문구
			public ArrayList<MessengerVO> getInfo(String tf_login_id, String tf_login_pw) throws SQLException, ClassNotFoundException{//메소드 정의문 1형식 				READ
				con=new DBCon().DBConnection();
				ArrayList<MessengerVO> arVO = new ArrayList<MessengerVO>();
				/*MessengerVO tv1 = new MessengerVO();*///메인에 것과 다름.
				String sql = "SELECT * FROM kajaemployee where employee_list=? and employee_pw=? ";
				try {
					st = con.prepareStatement(sql);
					st.setInt(1, Integer.parseInt(tf_login_id));
					st.setString(2, tf_login_pw);
					rs = st.executeQuery();
					while(rs.next()){
						int list = rs.getInt("employee_list");
						String name = rs.getString("employee_name");
						String tel = rs.getString("employee_tel");
						String rank = rs.getString("employee_rank");
						String dep = rs.getString("employee_dep");
						String pw	=rs.getString("employee_pw");
						int ck =rs.getInt("employee_logincheck");
						//VO객체에 넣기 
						arVO.add(new MessengerVO(list,name,tel,rank,dep,pw,ck));
				    }
				} catch (Exception e) {
					// TODO: handle exception
					con.close();
				}	
				con.close();
				return arVO;//잡힌 collection 넣어진거 다 보내.	
			}


	
		
	}//DAO-end
