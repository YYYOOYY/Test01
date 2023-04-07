<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="/commons/top.jsp"%>
<div class="root">
	<div class="post-list" style="margin-top: 20px">
		<c:forEach items="${boards }" var="board">
	 		<div class="post">
			<div onclick="location.href='/board/detail?code=${board.code}'">
					<h2 class="post-title">
					<c:choose>
						<c:when test="${fn:length(board.title) gt 10}">
							${fn:substring(board.title, 0, 10) }...
						</c:when>
						<c:otherwise>
							${board.title }
						</c:otherwise>
					</c:choose>
					</h2>
					<p class="post-info">
						<span class="post-author"><b>작성자 : ${board.writer }</b></span>
						<br>
						<span class="post-date">${board.writed }</span>
					</p>
			    <div class="post-content">
			    	<c:choose>
						<c:when test="${fn:length(board.body) gt 25}">
							${fn:substring(board.body, 0, 25) }...
						</c:when>
						<c:otherwise>
							${board.body }
						</c:otherwise>
					</c:choose>
			    </div>
      			<div class="post-stats">
					<span class="post-likes">${board.likeCount }</span>
					<span class="post-views">${board.viewCount }</span>
				</div>
			</div>
		</div>
		</c:forEach>
	</div>
</div>
	<%-- 페이지 링크 뷰 --%>
			<c:set var="currentPage" value="${empty param.page ? 1 : param.page }"/>
			<div class="pagination">
				<div class="page">
					<c:if test="${existPrev }">
						<c:url value="/" var="target">
							<c:param name="page" value="${start-1 }" />
						</c:url>
						<a href="${target}">&lt;</a>
					</c:if>
					
					<c:forEach var="p" begin="${start }" end="${last }">
						<c:url value="/" var="target">
							<c:param name="page" value="${p }" />
						</c:url>
						<c:choose>
							<c:when test="${p eq currentPage }">
								<b>${p }</b>
							</c:when>
							<c:otherwise>
								<a href="${target }">${p }</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					
					<c:url value="/" var="target">
						<c:param name="page" value="${last + 1 }" />
					</c:url>
					<c:if test="${existNext }">
						<a href="${target }" style="margin-left: 7px;">&gt;</a>
					</c:if>
				</div>
			</div>
</body>
</html>