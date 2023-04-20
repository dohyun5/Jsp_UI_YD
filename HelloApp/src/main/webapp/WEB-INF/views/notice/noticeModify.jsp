<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h3>공지사항 수정 페이지(noticeModify.jsp)</h3>

<form action="modifyNotice.do" method="post">
	<!-- 첨부파일이 있기 때문에 method는 post로 -->
	<table class="table">
		<tr>
			<th>글번호</th>
			<td><input type="text" name="nNo" value="${noticeInfo.noticeNo }" readonly></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title" value="${noticeInfo.noticeTitle }"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="3" cols="20" name="content" >${noticeInfo.noticeContent }</textarea></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><input type="text" name="writer" value="${noticeInfo.noticeWriter }" ></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<c:if test="${noticeInfo.attachFile != null && noticeInfo.attachFile != ''}">
					<img width="200px" src="images/${noticeInfo.attachFile }"><!-- 파일이 이미지면img 아니면 다운로드 받는 태그?사용 -->
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button type="submit">저장(수정)</button>
				<button type="button">삭제</button>
			</td>
		</tr>
	</table>
</form>



