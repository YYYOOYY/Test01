<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/commons/top.jsp"%>
<div class="root">
	<div class="login-form">
	<form action="/board/create-task" method="post" class="sign-form">
		<h2>게시글작성</h2>
		<c:choose>
			<c:when test="${sessionScope.logon eq true }">
				<div style="margin: 0.4em">
					<input type="text" placeholder="제목" name="title" required autocomplete="off" />
				</div>
				<div style="margin: 0.4em">
					<textarea name="body" placeholder="내용(내용칸을 늘릴 수 있습니다.)"></textarea>
				</div>
				<div style="margin: 0.4em">
					<button type="submit" class="join-btn">다음</button>
				</div>
			</c:when>
			<c:otherwise>
				<div style="margin: 0.4em">
					<input type="text" placeholder="제목" name="title" required autocomplete="off" />
				</div>
				<div style="margin: 0.4em">
					<textarea name="body" placeholder="내용"></textarea>
				</div>
					<div style="margin: 0.4em">
					<input type="text" placeholder="아이디" name="writer" required autocomplete="off" />
				</div>
					<div style="margin: 0.4em">
					<input type="password" placeholder="비밀번호" name="boardPass" />
				</div>
				<div style="margin: 0.4em">
					<button type="submit" class="join-btn">다음</button>
				</div>
			</c:otherwise>
		</c:choose>
	</form>
	</div>
</div>
</body>
</html>