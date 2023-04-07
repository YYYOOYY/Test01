<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/top.jsp"%>
<div class="root">
	<div class="login-form">
		<h3>수정할 내용을 적어주세요</h3>
		<form action="/action/modify-task" method="post">
			<input type="hidden" name="code" value="${code }" />
			<textarea name="body" placeholder="내용(내용칸을 늘릴 수 있습니다.)"></textarea>
			<div style="margin: 0.4em">
				<button type="submit" class="join-btn">제출</button>
			</div>
		</form>
	</div>
</div>


</body>
</html>