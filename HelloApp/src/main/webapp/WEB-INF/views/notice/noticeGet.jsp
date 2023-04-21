<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h3>공지사항 상세 페이지(noticeGet.jsp)</h3>

<form action="modifyNotice.do" method="get">
	<!-- 첨부파일이 있기 때문에 method는 post로 -->
	<table class="table">
		<tr>
			<th>글번호</th>
			<td><input type="text" name="nNo"
				value="${noticeInfo.noticeNo }" readonly></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title"
				value="${noticeInfo.noticeTitle }" readonly></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="3" cols="20" name="content" readonly>${noticeInfo.noticeContent }</textarea></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><input type="text" name="writer"
				value="${noticeInfo.noticeWriter }" readonly></td>
		</tr>
		<tr>
			<th>첨부파일:${fileType }</th>
			<td><c:if test="${noticeInfo.attachFile != null}">
					<c:choose>
						<c:when test="${fileType == 'image' }">
							<img width="200px" src="images/${noticeInfo.attachFile }">
							<!-- 파일이 이미지면img 아니면 다운로드 받는 태그?사용 -->
						</c:when>
						<c:otherwise>
							<a href="images/${noticeInfo.attachFile} ">${noticeInfo.attachFile }</a>
						</c:otherwise>
					</c:choose>
				</c:if></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><c:choose>
					<c:when test="${noticeInfo.noticeWriter == id }">
						<button type="submit">수정</button>
					</c:when>
					<c:otherwise>
						<button disabled="disabled">수정</button>
					</c:otherwise>
				</c:choose>
				<button type="button"
					onClick="location.href='noticeList.do?page=${pageNum}'">목록으로</button>
		</tr>
	</table>
</form>
<!-- 
<script>
function back() {
    history.back();
} 
</script> onClick ="back()"-->