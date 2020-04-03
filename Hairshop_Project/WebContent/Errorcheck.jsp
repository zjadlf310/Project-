<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  	String errorcheck = (String)request.getAttribute("errorcheck");
	
	if(errorcheck.equals("review00")){
		out.print("<script>alert('작성되셨습니다.')</script>");
		out.print("<script>window.location.href = 'reviewoutput.do'</script>");
	
	}else if(errorcheck.equals("review01")){
		out.print("<script>alert('리뷰작성 기간이 아닙니다.')</script>");
		out.print("<script>window.location.href = 'reviewoutput.do'</script>");
		
	}else if(errorcheck.equals("review02")){
		out.print("<script>alert('최신예약내역이 존재하지 않습니다.')</script>");
		out.print("<script>window.location.href = 'reviewoutput.do'</script>");
		
	}else if(errorcheck.equals("review03")){
		out.print("<script>alert('리뷰작성에 실패했습니다.\\n오류가 지속된다면 고객센터에 문의해주세요.')</script>");
		out.print("<script>window.location.href = 'reviewoutput.do'</script>");
		
	}else if(errorcheck.equals("review04")){
		out.print("<script>alert('리뷰내용을 입력해주세요.')</script>");
		out.print("<script>window.location.href = 'reviewoutput.do'</script>");
		
	}else if(errorcheck.equals("login01")){
		out.print("<script>alert('로그인 후 이용해주세요.')</script>");
		out.print("<script>window.location.href = 'Login.jsp'</script>");
		
	}else if(errorcheck.equals("login02")){
		out.print("<script>alert('로그인에 실패했습니다\\n다시 시도해주세요.')</script>");
		out.print("<script>window.location.href = 'Login.jsp'</script>");
		
	}else if(errorcheck.equals("idcheck01")){
		out.print("<script>alert('사용가능한 아이디입니다.')</script>");
		out.print("<script>self.opener = self</script>");
		out.print("<script>self.close()</script>");
	}else if(errorcheck.equals("idcheck02")){
		out.print("<script>alert('중복아이디 입니다.')</script>");
		out.print("<script>window.location.href = 'IdCheck.jsp'</script>");
		
	}else if(errorcheck.equals("signup01")){
		out.print("<script>alert('가입되셨습니다.로그인 화면으로 이동합니다.')</script>");
		out.print("<script>window.location.href = 'Login.jsp'</script>");
		
	}else if(errorcheck.equals("signup02")){
		out.print("<script>alert('가입에 실패했습니다.\\n다시 시도해주세요.')</script>");
		out.print("<script>window.location.href = 'Signup.jsp'</script>");
		
	}else if(errorcheck.equals("searchid01")){
		out.print("<script>alert('회원님의 아이디는 "+request.getAttribute("sname")+" 입니다.')</script>");
		out.print("<script>window.location.href = 'Login.jsp'</script>");
		
	}else if(errorcheck.equals("searchid02")){
		out.print("<script>alert('아이디가 존재하지 않습니다. 다시 시도해주세요.')</script>");
		out.print("<script>window.location.href = 'Signup.jsp'</script>");
		
	}else if(errorcheck.equals("checkid01")){
		out.print("<script>alert('확인되셨습니다.')</script>");
		out.print("<script>window.location.href = 'Password.jsp'</script>");
		
	}else if(errorcheck.equals("checkid02")){
		out.print("<script>alert('정보가 일치하지 않습니다.\\n다시 시도해주세요.')</script>");
		out.print("<script>window.location.href = 'Search.jsp'</script>");
		
	}else if(errorcheck.equals("password01")){
		out.print("<script>alert('변경되셨습니다.\\n로그인 페이지로 이동합니다.')</script>");
		out.print("<script>window.location.href = 'Login.jsp'</script>");
		
	}else if(errorcheck.equals("password02")){
		out.print("<script>alert('비밀번호 변경에 실패했습니다.\\n다시 시도해주세요.')</script>");
		out.print("<script>window.location.href = 'Search.jsp'</script>");
		
	}
%>