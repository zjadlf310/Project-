package com.hairshop.contorller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hairshop.dao.HairDAO;
import com.hairshop.vo.HairVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class controller
 */
@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String uri;    
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String c = request.getRequestURI().substring(request.getContextPath().length());
		
		switch(c) {
		case "/reviewoutput.do":
			
			ArrayList<HairVO> ar1 = null;
			try {
				ar1 = new HairDAO().ReviewOutput();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				request.setAttribute("error", e.getMessage());
				uri ="Error.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(uri);
				rd.forward(request, response);
			}
			
			uri ="Review.jsp";
			request.setAttribute("reviewoutput", ar1);
			break;
			
		case "/reviewinput.do":
			
			HairVO in = (HairVO) request.getAttribute("hvo");
			Date date = new Date();
			String text = (String)request.getAttribute("text");
			Boolean input = false;
			try {
				input = new HairDAO().ReviewInput(in.getReservation_id(), date, in.getReservation_hair(), in.getReservation_style(), text);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				request.setAttribute("error", e1.getMessage());
				uri ="Error.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(uri);
				rd.forward(request, response);
			}
			if(input) {
				request.setAttribute("errorcheck", "review00");
				uri ="Errorcheck.jsp";
			}else {
				request.setAttribute("errorcheck", "review03");
				uri ="Errorcheck.jsp";
			}
			break;
			
		
		case "/login.do":
			
			String id2 = request.getParameter("id");
			String pw2 = request.getParameter("pw");
			boolean b1 = false;
			try {
				b1 = new HairDAO().login(id2, pw2);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				request.setAttribute("error", e.getMessage());
				uri ="Error.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(uri);
				rd.forward(request, response);
			}
			
			if(b1) {
				HttpSession ses = request.getSession();
				HairVO session = null;
				try {
					session = new HairDAO().session(id2);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					request.setAttribute("error", e.getMessage());
					uri ="Error.jsp";
					RequestDispatcher rd = request.getRequestDispatcher(uri);
					rd.forward(request, response);
				}
				ses.setAttribute("session1", session);
				uri = "Main.jsp";
			}else {
				request.setAttribute("errorcheck", "login02");
				uri = "Errorcheck.jsp";
			}
			break;
			
		case "/logincheck.do":
			HttpSession ses = request.getSession(false);
			if(ses == null || ses.getAttribute("session1") == null) {
				request.setAttribute("errorcheck", "login01");
				uri = "Errorcheck.jsp";
			}else {
				uri = "Reservation.jsp";
			}
			
		
			break;
			
		case "/doubleIDcheck.do":
			String join_id = request.getParameter("idcheck");
			
			boolean b2 = false;
			try {
				b2 = new HairDAO().doubleIDcheck(join_id);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				request.setAttribute("error", e.getMessage());
				uri ="Error.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(uri);
				rd.forward(request, response);
			}
			
			if(b2) {
				
				request.setAttribute("errorcheck", "idcheck01");
				uri = "Errorcheck.jsp";
			}else {
				request.setAttribute("errorcheck", "idcheck02");
				uri = "Errorcheck.jsp";
			}

			
			break;
			
		case "/newID.do":
			
			
			String name3 = request.getParameter("join_name");
			String id3 = request.getParameter("join_id");
			String pw3 = request.getParameter("join_pw");
			String tel3 = request.getParameter("join_tel");
			String mail3 = request.getParameter("join_mail");
			HairVO newid = new HairVO(name3,id3,pw3,tel3,mail3);
			boolean b3 = false;
			try {
				b3 = new HairDAO().newID(newid);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				request.setAttribute("error", e.getMessage());
				uri ="Error.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(uri);
				rd.forward(request, response);
			}
			
			if(b3) {
				request.setAttribute("errorcheck", "signup01");
				uri = "Errorcheck.jsp";
			}else {
				request.setAttribute("errorcheck", "signup02");
				uri = "Errorcheck.jsp";
			}
			break;
			
		case "/searchID.do":

			String name = request.getParameter("S_name");
			String tel = request.getParameter("S_pnum");
			String sname = null;
			try {
				sname = new HairDAO().SearchID(name, tel);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				request.setAttribute("error", e.getMessage());
				uri ="Error.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(uri);
				rd.forward(request, response);
			}
			
			if(sname!=null) {
				request.setAttribute("sname", sname);
				request.setAttribute("errorcheck", "searchid01");
				uri = "Errorcheck.jsp";
			}else {
				
				request.setAttribute("errorcheck", "searchid02");
				uri = "Errorcheck.jsp";
			}
			
			break;
			
		case "/checkID.do":
			
			String name2 = request.getParameter("S_name");
			String ID = request.getParameter("S_id");
			String tel2 = request.getParameter("S_pnum");
			
			boolean b4 = false;
			try {
				b4 = new HairDAO().checkID(ID, name2, tel2);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				request.setAttribute("error", e.getMessage());
				uri ="Error.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(uri);
				rd.forward(request, response);
			}
			if(b4) {
				HttpSession ses2 = request.getSession();
				ses2.setAttribute("id", ID);
				request.setAttribute("errorcheck", "checkid01");
				uri = "Errorcheck.jsp";
			}else {
				request.setAttribute("errorcheck", "checkid02");
				uri = "Errorcheck.jsp";
			}
			break;
			
		case "/updatePW.do":
			
			String id = (String) request.getSession().getAttribute("id");
			String pw = request.getParameter("pw");
			
			boolean b5 = false;
			try {
				b5 = new HairDAO().updatePW(pw,id);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				request.setAttribute("error", e.getMessage());
				uri ="Error.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(uri);
				rd.forward(request, response);
			}
			
			if(b5) {
				request.setAttribute("errorcheck", "password01");
				uri = "Errorcheck.jsp";
				
			}else {
				request.setAttribute("errorcheck", "password02");
				uri = "Errorcheck.jsp";
			}
			break;
			
		case"/Reservation.do"://�����Ե� ȭ�鿡�� ���� ���������� �ѱ� �� 
			String S_HAIR=request.getParameter("S_HAIR");
			String image1=null;
			String  tea1=null;
			String error=null;
			String teatext1 = null;
			String teatext11 = null;
		
			if (S_HAIR.equals("Cat")) {
				image1="img/cat.png";
				tea1="����� ������";
				teatext1="- �÷�����Ʈ �ڰ��� ����";
				teatext11=" - ����, Ż�� ����";
			}
			else if(S_HAIR.equals("Dog")) {
				image1="img/dog.png";
				tea1="������ ������";
				teatext1="- ������ ������  ";
				teatext11 = "- ���� ���� ����";
			}
			else if(S_HAIR.equals("Elephant")) {
				image1="img/elephant.png";
				tea1="�ڳ��� ������";
				teatext1="- ĿƮ�� ������ ȣ�� ������";
				teatext11="- ���� �� ����";
			}
			else if(S_HAIR.equals("Frog")) {
				image1="img/frog.png";
				tea1="������ ������";
				teatext1="- ����,������,���׻� ȣ�� �̹߻� ���";
				teatext11="- ���� �� ����";
			}
			else if(S_HAIR.equals("Giraffe")) {
				image1="img/giraffe.png";
				tea1="�⸰ ������";
				teatext1="- ����ƿ������б� �̿��к� �ܷ�����";
				teatext11="- ��Ÿ�ϸ� ���� ��ȯ��";
			}
			else if(S_HAIR.equals("Hippo")) {
				image1="img/hippo.png";
				tea1="�ϸ� ������";
				teatext1="- ����� ��� ��ī���� ��������";
				teatext11="- ���� ����";
			}
			else if(S_HAIR.equals("Sheep")) {
				image1="img/sheep.png";
				tea1="�� ������";
				teatext1="- ������׼���! ù �湮 40% ���!";
				teatext11="- �Ѹ����� ����";
			}
			else if(S_HAIR.equals("Eagle")) {
				image1="img/eagle.png";
				tea1="������ ������";
				teatext1="- ���Ⱓ�� ���Ͽ츦 ���� ���� ������!";
				teatext11="- Ż�� ������ ���� �̼�";
			}
			else if(S_HAIR.equals("Tiger")){
				image1="img/tiger.png";
				tea1="ȣ���� ������";
				teatext1="- ������ ������ ��Ʈ�� �ϼ��ص帳�ϴ�";
				teatext11="- �ոӸ��� ���� ȯ��";
			}
			
			int [] soo1 = null;
			
			request.setAttribute("sw", 0);	
			request.setAttribute("soo", soo1);	
			request.setAttribute("image1", image1);
			request.setAttribute("tea1", tea1);
			request.setAttribute("error", error);
			request.setAttribute("S_HAIR", S_HAIR );
			request.setAttribute("teatext1", teatext1);
			request.setAttribute("teatext11", teatext11 );
			uri="Reservation2.jsp";
			break;
			
		case"/Reservation1_1.do"://�޷� ���� ��
			String S_HAIR1_1=request.getParameter("S_HAIR");
			String image1_1=null;
			String tea1_1=null;
			String error1_1=null;
			String teatext1_1 = null;
			String teatext11_1 = null;
			String year1_1=request.getParameter("year");
			String month1_1=request.getParameter("month");
			
			if (S_HAIR1_1.equals("Cat")) {
				image1_1="img/cat.png";
				tea1_1="����� ������";
				teatext1_1="- �÷�����Ʈ �ڰ��� ����";
				teatext11_1=" - ����, Ż�� ����";
			}
			else if(S_HAIR1_1.equals("Dog")) {
				image1_1="img/dog.png";
				tea1_1="������ ������";
				teatext1_1="- ������ ������  ";
				teatext11_1= "- ���� ���� ����";
			}
			else if(S_HAIR1_1.equals("Elephant")) {
				image1_1="img/elephant.png";
				tea1_1="�ڳ��� ������";
				teatext1_1="- ĿƮ�� ������ ȣ�� ������";
				teatext11_1="- ���� �� ����";
			}
			else if(S_HAIR1_1.equals("Frog")) {
				image1_1="img/frog.png";
				tea1_1="������ ������";
				teatext1_1="- ����,������,���׻� ȣ�� �̹߻� ���";
				teatext11_1="- ���� �� ����";
			}
			else if(S_HAIR1_1.equals("Giraffe")) {
				image1_1="img/giraffe.png";
				tea1_1="�⸰ ������";
				teatext1_1="- ����ƿ������б� �̿��к� �ܷ�����";
				teatext11_1="- ��Ÿ�ϸ� ���� ��ȯ��";
			}
			else if(S_HAIR1_1.equals("Hippo")) {
				image1_1="img/hippo.png";
				tea1_1="�ϸ� ������";
				teatext1_1="- ����� ��� ��ī���� ��������";
				teatext11_1="- ���� ����";
			}
			else if(S_HAIR1_1.equals("Sheep")) {
				image1_1="img/sheep.png";
				tea1_1="�� ������";
				teatext1_1="- ������׼���! ù �湮 40% ���!";
				teatext11_1="- �Ѹ����� ����";		
			}
			else if(S_HAIR1_1.equals("Eagle")) {
				image1_1="img/eagle.png";
				tea1_1="������ ������";
				teatext1_1="- ���Ⱓ�� ���Ͽ츦 ���� ���� ������!";
				teatext11_1="- Ż�� ������ ���� �̼�";		
			}
			else {
				image1_1="img/tiger.png";
				tea1_1="ȣ���� ������";
				teatext1_1="- ������ ������ ��Ʈ�� �ϼ��ص帳�ϴ�";
				teatext11_1="- �ոӸ��� ���� ȯ��";
			}	
			
			int [] soo1_1 = null;
			
			request.setAttribute("sw", 0);	
			request.setAttribute("soo", soo1_1);	
			request.setAttribute("image1", image1_1);
			request.setAttribute("tea1", tea1_1);
			request.setAttribute("error", error1_1);
			request.setAttribute("S_HAIR", S_HAIR1_1 );
			request.setAttribute("year", year1_1 );
			request.setAttribute("month", month1_1 );
			request.setAttribute("teatext1", teatext1_1);
			request.setAttribute("teatext11", teatext11_1 );
			uri="Reservation2.jsp";
			break;
			

		case"/reservation_only.do": //��¥ �Ѿ ��
			
			String error1=null;
			HairDAO Hdao1 = null;
			Hdao1 = new HairDAO();
				
			int year= Integer.parseInt(request.getParameter("year"));
			int month=Integer.parseInt(request.getParameter("month"));			
			int day=Integer.parseInt(request.getParameter("day"));				
			String S_HAIR1=request.getParameter("S_HAIR");
			String re_day=null;
			String teatext1_2 = null;
			String teatext11_2 = null;
			if(month<10) {
				re_day=year+"-"+"0"+month;
			}else {
				re_day=year+"-"+month;
			}			
			if(day<10) {
				re_day += "-0"+day;
			}else {
				re_day += "-"+day;
			}
			
					
			ArrayList<HairVO> alist1 = null;
			try {
				alist1=Hdao1.Schedule_info(S_HAIR1,re_day);
			} catch (SQLException | ClassNotFoundException e) {
				request.setAttribute("error", e.getMessage());
				uri ="Error.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(uri);
				rd.forward(request, response);
			} 
			
			//s_time ����
			int [] soo = new int[alist1.size()];
			if (alist1 != null) {
				for(int i=0; i<alist1.size();i++) {
					soo[i]=alist1.get(i).getS_TIME();
				}
			}
			
			String image11=null;
			String  tea11=null;
			if (S_HAIR1.equals("Cat")) {
				image11="img/cat.png";
				tea11="����� ������";
				teatext1_2 = "- �÷�����Ʈ �ڰ��� ����";
				teatext11_2 = " - ����, Ż�� ����";
			}
			else if(S_HAIR1.equals("Dog")) {
				image11="img/dog.png";
				tea11="������ ������";
				teatext1_2 = "- ������ ������  ";
				teatext11_2 = "- ���� ���� ����";
			}
			else if(S_HAIR1.equals("Elephant")) {
				image11="img/elephant.png";
				tea11="�ڳ��� ������";
				teatext1_2 = "- ĿƮ�� ������ ȣ�� ������";
				teatext11_2 = "- ���� �� ����";
			}
			else if(S_HAIR1.equals("Frog")) {
				image11="img/frog.png";
				tea11="������ ������";
				teatext1_2 = "- ����,������,���׻� ȣ�� �̹߻� ���";
				teatext11_2 = "- ���� �� ����";
			}
			else if(S_HAIR1.equals("Giraffe")) {
				image11="img/giraffe.png";
				tea11="�⸰ ������";
				teatext1_2 = "- ����ƿ������б� �̿��к� �ܷ�����";
				teatext11_2 = "- ��Ÿ�ϸ� ���� ��ȯ��";
			}
			else if(S_HAIR1.equals("Hippo")) {
				image11="img/hippo.png";
				tea11="�ϸ� ������";
				teatext1_2 = "- ����� ��� ��ī���� ��������";
				teatext11_2 = "- ���� ����";
			}
			else if(S_HAIR1.equals("Sheep")) {
				image11="img/sheep.png";
				tea11="�� ������";
				teatext1_2 = "- ������׼���! ù �湮 40% ���!";
				teatext11_2 = "- �Ѹ����� ����";	
			}
			else if(S_HAIR1.equals("Eagle")) {
				image11="img/eagle.png";
				tea11="������ ������";
				teatext1_2 = "- ���Ⱓ�� ���Ͽ츦 ���� ���� ������!";
				teatext11_2 = "- Ż�� ������ ���� �̼�";	
			}
			else {
				image11="img/tiger.png";
				tea11="ȣ���� ������";
				teatext1_2 = "- ������ ������ ��Ʈ�� �ϼ��ص帳�ϴ�";
				teatext11_2 = "- �ոӸ��� ���� ȯ��";
			}	
			Calendar cal=Calendar.getInstance();
			int toyear=cal.get(Calendar.YEAR);
			int tomonth=cal.get(Calendar.MONTH)+1;
			int today=cal.get(Calendar.DAY_OF_MONTH);
			int tohour=cal.get(Calendar.HOUR_OF_DAY);
			
			//������ ���������� ����ص� ���̴� ����.
			int todayo=toyear+tomonth+today;//����
			int reday=year+month+day;//���� ���� 
			
			String [] str={"true","true","true","true","true","true","true","true","true","true","true"};
		
		if(todayo == reday) {//������ ��¥�� ���÷� ���� �Ǿ��ٸ�
			//�ѹ� ������
			
			
			if(tohour>=20) {
				str[10]="disabled";
			}if(tohour>=19) {
				str[9]="disabled";
			}if(tohour>=18) {
				str[8]="disabled";
			}if(tohour>=17) {
				str[7]="disabled";
			}if(tohour>=16) {
				str[6]="disabled";
			}if(tohour>=15) {
				str[5]="disabled";
			}if(tohour>=14) {
				str[4]="disabled";
			}if(tohour>=13) {
				str[3]="disabled";
			}if(tohour>=12) {
				str[2]="disabled";
			}if(tohour>=11) {
				str[1]="disabled";
			}if(tohour>=10)
				str[0]="disabled";
			
			
			//���� ����� ������ ������ �� �ѹ� �ִ°� �����س��� �ƿ�
			for(int i=0;i<soo.length;i++){	
				if (soo[i]==10 ) {
					str[0]="disabled";
				}else if(soo[i]==11){
					str[1]="disabled";
				}else if(soo[i]==12){
					str[2]="disabled";
				}else if(soo[i]==13){
					str[3]="disabled";
				}else if(soo[i]==14){
					str[4]="disabled";
				}else if(soo[i]==15){
					str[5]="disabled";
				}else if(soo[i]==16){
					str[6]="disabled";
				}else if(soo[i]==17){
					str[7]="disabled";
				}else if(soo[i]==18){
					str[8]="disabled";
				}else if(soo[i]==19){
					str[9]="disabled";
				}else if(soo[i]==20){
					str[10]="disabled";
				}				
			}
			
		}else {//���� ���� �ȉ��� �� ���� ������ �±� �� �� ������ ���� ���鸸 ����.
			for(int i=0;i<soo.length;i++){	
				if (soo[i]==10 ) {
					str[0]="disabled";
				}else if(soo[i]==11){
					str[1]="disabled";
				}else if(soo[i]==12){
					str[2]="disabled";
				}else if(soo[i]==13){
					str[3]="disabled";
				}else if(soo[i]==14){
					str[4]="disabled";
				}else if(soo[i]==15){
					str[5]="disabled";
				}else if(soo[i]==16){
					str[6]="disabled";
				}else if(soo[i]==17){
					str[7]="disabled";
				}else if(soo[i]==18){
					str[8]="disabled";
				}else if(soo[i]==19){
					str[9]="disabled";
				}else if(soo[i]==20){
					str[10]="disabled";
				}				
			}
		}
			request.setAttribute("sw", 1);
			request.setAttribute("S_HAIR", S_HAIR1);
			request.setAttribute("image1", image11);
			request.setAttribute("tea1", tea11);
			request.setAttribute("date", re_day);//string ��¥
			request.setAttribute("error",error1);//s_time �Ҳ���
			request.setAttribute("str",str);//s_time �Ҳ���
			request.setAttribute("teatext1", teatext1_2);
			request.setAttribute("teatext11", teatext11_2 );
			uri="Reservation2.jsp";
					
			break;			
			
		case"/reservation2.do": //����2 Ȯ�ι�ư Ŭ�� ��
		
			//������ ������ �̸�/�ü��� ����/�ü� ��¥ /�ü� �ð�/�ü� ����
	//���� �ޱ�
			String username = System.getProperty("user.name");
			String bang1="c:/Users/"+username+"/Downloads/";
		    String encType1 = "UTF-8";
		    int maxFilesize1 = 100 * 1024 * 1024;
 
			// MultipartRequest(request, ������[, �ִ����ũ��, ���ڵ��ɸ��ͼ�, ������ ���ϸ� ��ȣ ����])
		    MultipartRequest mr1 = new MultipartRequest(request, bang1, maxFilesize1,
		            encType1, new DefaultFileRenamePolicy());
	
		    File file11 = mr1.getFile("file1");
		    
			
		//��������
		String S_HAIR2=mr1.getParameter("S_HAIR3");
		String S_STYLE=mr1.getParameter("S_style");
		String S_date=mr1.getParameter("S_DATE3"); //�ü� ��¥ only.do���� �̾�޾ƿ� ��
		String S_TIME=mr1.getParameter("time");//�ü� �ð�
		String price=mr1.getParameter("price3");// �ű⿡ �´� ����
		System.out.println(S_TIME);
	    request.setAttribute("S_HAIR",S_HAIR2);
		request.setAttribute("S_STYLE",S_STYLE);
		request.setAttribute("S_DATE", S_date);
		request.setAttribute("S_TIME",S_TIME);
		request.setAttribute("photo", price);
		request.setAttribute("price", price);
		
		//check
		uri="Reservation3.jsp";
		
		break;
			
	case"/reservation3.do":	//���� 3������ �����ϱ� ������ ��
		//�� 10��
		String name1=request.getParameter("W_name");//���� �� �̸�
		//session�ϰ� �ߺ�
		String tel1=request.getParameter("W_tel");//���� �� ����ó <<�̰� �ٲ� �� ���� ����!
	    //�̰� �ٲ�� �־�� ���� �ʳ�? �ù踦 �ٸ� ��ȣ�� ���̵���
		String mail=request.getParameter("W_mail"); //���� �� ���� �������
	    //session�ϰ� �ߺ�
		String text1=request.getParameter("W_text");//�뷮���� �ɷ������� ����.
	    
		/*String text2=request.getParameter("W_text2");//���� ���� ���� �� ���� Ȯ�ζ� �̰͵� ���� �������
	    //���ó����� ���� �ȹ޾Ƶ� ��.*/
		
	    //hidden
		//�̰͵鵵 ��� ����� ���� ���� �� ������.
	    String S_HAIR21=request.getParameter("S_HAIR");
		String S_STYLE1=request.getParameter("S_STYLE");
		String sday11=request.getParameter("S_DATE");
		String S_TIME1=request.getParameter("S_TIME");
		String photo1=request.getParameter("photo");
		String price1=request.getParameter("price");
			

		request.setAttribute("W_name",name1);//���� �� �̸�
		request.setAttribute("W_tel",tel1);//���� �� ����ó <<�̰� �ٲ� �� ���� ����!
	    request.setAttribute("W_mail",mail); //���� �� ���� �������
	    request.setAttribute("W_text",text1);//�뷮���� �ɷ������� ����.
	    /*request.setAttribute("W_text2",text2);//���� ���� ���� �� ���� Ȯ�ζ� �̰͵� ���� �������
		*/
	    request.setAttribute("S_HAIR",S_HAIR21);
		request.setAttribute("S_STYLE",S_STYLE1);
		request.setAttribute("S_DATE",sday11);
		request.setAttribute("S_TIME",S_TIME1);
		request.setAttribute("photo",photo1);
		request.setAttribute("price",price1);
		
		
		uri="Payment.jsp";
		break;
			
	case"/Payment.do":  //����â���� ���� �Ϸ� ������ ��

		
		HairVO	login11=(HairVO) request.getSession().getAttribute("session1");
		String rename=login11.getName();
		String reid=login11.getId();
		String mail1=login11.getMail();
		
		//���� �� �̸� �ߺ� String name1=request.getParameter("W_name");
		String tel11=request.getParameter("W_tel");//���� �� ����ó <<�̰� �ٲ� �� ���� ����!
	    String text11=request.getParameter("W_text");//�뷮���� �ɷ������� ����.
	     
	    //�̰͵鵵 ��� ����� ���� ���� �� ������.
	    String S_HAIR211=request.getParameter("S_HAIR");
		String S_STYLE11=request.getParameter("S_STYLE");
		String sday111=request.getParameter("S_DATE");
		int S_TIME11=Integer.parseInt(request.getParameter("S_TIME"));
		String S_photo11=request.getParameter("photo");
		String price11=request.getParameter("price");
		
			
		HairDAO Hdao11 = null;
			Hdao11 = new HairDAO();
		//�����Ե� ������ǥ�� insert�ϱ�
		try {
			Hdao11.S_insert(S_HAIR211, S_STYLE11, sday111, S_TIME11);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			request.setAttribute("error", e.getMessage());
			uri ="Error.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(uri);
			rd.forward(request, response);
		}
			
		//���� ���̺� ���� �ڷ� �ֱ�
		try {
			Hdao11.RE_insert(rename,reid,tel11,sday111,S_HAIR211,S_STYLE11,S_photo11);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			request.setAttribute("error", e.getMessage());
			uri ="Error.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(uri);
			rd.forward(request, response);
		}
		
		//���� ������ �ʿ�
		/*request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");*/
        try {
/*            String mail_from =  m_name + "<" + m_email + ">";
            String mail_to =    "admin<admin@83rpm.com>";
            String title =      "hosting.83rpm.com ��û���� :: " + m_title;
            String contents =   "���� ��� :: " + m_name + "&lt;" + m_email + "&gt;<br><br>" + m_title + "<br><br>" + m_text;
 */
            String mail_from 	= "������<ep2596@gmail.com>";
            String mail_to 		= mail1;
            String title		= rename+" ��, ����� ��� ��տ�"+sday111+" �� ������ ����Ǿ����ϴ�.";
            String contents =   "���� ��� :: < kazzza hair salon > &lt;<br><br>" + S_STYLE11 +"-"+ price11 + "&gt;<br><br>" +sday111+ S_TIME11 + "�� <br><br> ��û���� ::" + 
            					text11+"<br><br> ���� 'kazzza hair salon'�� �̿����ּż� �����մϴ�.";

            
            mail_from = new String(mail_from.getBytes("UTF-8"), "UTF-8");
            mail_to = new String(mail_to.getBytes("UTF-8"), "UTF-8");
 
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.put("mail.smtp.auth", "true");
 
            Authenticator auth = new SMTPAuthenticator();
 
            Session sess = Session.getDefaultInstance(props, auth);
 
            MimeMessage msg = new MimeMessage(sess);
 
            msg.setFrom(new InternetAddress(mail_from));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(mail_to));
            msg.setSubject(title, "UTF-8");
            msg.setContent(contents, "text/html; charset=UTF-8");
            msg.setHeader("Content-type", "text/html; charset=UTF-8");
 
            Transport.send(msg);
            
            uri="Main.jsp";
        } catch (Exception e) {
        	request.setAttribute("error", e.getMessage());
			uri ="Error.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(uri);
			rd.forward(request, response);
        } 
        	break;
        	
			case"/Logout.do":
				HttpSession sess = request.getSession();
				sess.invalidate();//���ǵ����� �ʱ�ȭ
				uri="Main.jsp";
				break;
		
		}
		RequestDispatcher rd = request.getRequestDispatcher(uri);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
