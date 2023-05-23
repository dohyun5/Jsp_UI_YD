<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


</script>
<h3>공지사항 등록 페이지</h3>
<form action="addNotice.do" method="post" enctype="multipart/form-data">
	<!-- 첨부파일이 있기 때문에 method는 post로 -->
	<table class="table">
		<tr>
			<th>제목</th>
			<td><input type="text" name="title"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="3" cols="20" name="content"></textarea></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><input type="text" name="writer" readonly value=${id }></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td><input type="file" name="attach"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button type="submit">등록</button>
				<button type="reset">취소</button>
			</td>
		</tr>
	</table>
</form>

