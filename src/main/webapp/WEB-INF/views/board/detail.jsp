<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/commons/top.jsp"%>
<div class="root">
	<div class="container" style="margin-top: 30px">
	  <div class="header">
	    <div class="header-left">
	      <div class="title">${board.title }</div>
	      <div class="meta">
	        <div class="author">${board.writer }</div>
	        <div class="date">${board.writed }</div>
	      </div>
	    </div>
	    <div class="header-right">
	      <div class="like-btn">
	      	<c:choose>
				<c:when test="${sessionScope.logon eq true }">
					<a href="/action/like?code=${board.code }"><b style="font-size: 15px;">추천</b></a>
				</c:when>
				<c:otherwise>
					<a href="/user/login">추천</a>
				</c:otherwise>
			</c:choose>
	      </div>
	    </div>
	  </div>
	  <div class="content">
	    <p>${board.body }</p>
	  </div>
	  <div class="footer">
	    <div class="footer-left">
	      <div class="likes">👍 : ${board.likeCount }</div> 
	      <div class="views">👀 : ${board.viewCount }</div>
	    </div>
	    <div class="footer-right">
	    	<c:choose>
				<c:when test="${userBoard eq true && sessionScope.logon eq true}">
					<a href="/action/modify?code=${board.code }&body=${board.body}" class="edit-btn"><b style="font-size: 15px;">수정</b></a>
					<a href="/action/delete?code=${board.code }" class="delete-btn"><b style="font-size: 15px;">삭제</b></a>
				</c:when>
				<c:when test="${nonUserBoard eq true && sessionScope.logon eq false }">
					<a href="/action/modify?code=${board.code }&body=${board.body}" class="edit-btn"><b style="font-size: 15px;">수정</b></a>
					<a href="/action/delete?code=${board.code }" class="delete-btn"><b style="font-size: 15px;">삭제</b></a>
				</c:when>
			</c:choose>
	    </div>
	  </div>
	</div>
	<c:choose>
		<c:when test="${param.error eq 'r' }">
			<span style="color: red">시스템 오류 발생</span>
		</c:when>
		<c:when test="${param.error eq 'e' }">
			<b style="color: red">이미 추천을 누르셨습니다</b>
		</c:when>
		
		<c:otherwise>
		
		</c:otherwise>
	</c:choose>
		
	
</div>
</body>
</html>