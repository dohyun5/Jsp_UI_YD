<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h3>공지사항 상세 페이지(noticeGet.jsp)</h3>
<style>
	#content {
		padding: 15px auto;
	}
</style>
<form action="modifyNotice.do" method="get">
	<!-- 첨부파일이 있기 때문에 method는 post로 -->
	<table class="table">
		<tr>
			<th>글번호</th>
			<td><input type="text" name="nNo" value="${noticeInfo.noticeNo }" readonly></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title" value="${noticeInfo.noticeTitle }" readonly></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="3" cols="20" name="content" readonly>${noticeInfo.noticeContent }</textarea></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><input type="text" name="writer" value="${noticeInfo.noticeWriter }" readonly></td>
		</tr>
		<tr style="display:none;">
			<th>첨부파일:${fileType }</th>
			<td>
				<c:if test="${noticeInfo.attachFile != null}">
					<c:choose>
						<c:when test="${fileType == 'image' }">
							<img width="200px" src="images/${noticeInfo.attachFile }">
							<!-- 파일이 이미지면img 아니면 다운로드 받는 태그?사용 -->
						</c:when>
						<c:otherwise>
							<a href="images/${noticeInfo.attachFile} ">${noticeInfo.attachFile }</a>
						</c:otherwise>
					</c:choose>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<c:choose>
					<c:when test="${noticeInfo.noticeWriter == id }">
						<button type="submit">수정</button>
					</c:when>
					<c:otherwise>
						<button disabled="disabled">수정</button>
					</c:otherwise>
				</c:choose>
				<button type="button" onClick="location.href='noticeList.do?page=${pageNum}'">목록으로</button>
		</tr>
	</table>
</form>
<!-- 댓글 등록 -->
<div id="content">
	<input type="text" id="reply">
	<span>${id }</span>
	<button type="button" id="addBtn">등록</button>
</div>
<!--  댓글 정보  -->
<table class="table">
	<thead>
		<tr>
			<th>댓글번호</th>
			<th>내용</th>
			<th>작성자</th>
			<th>삭제</th>
		</tr>
	</thead>
	<tbody id="tlist">
	</tbody>

</table>
<!-- 
<form action="" method="post">
	<input name="id">
	<input name="pass">
</form>
 -->
<script>
	let showFields = ['replyNo', 'reply', 'replyWriter']

	let xhtp = new XMLHttpRequest();
	xhtp.open('get', 'replyList.do?nNo=${noticeInfo.noticeNo}')
	xhtp.send();
	xhtp.onload = function () {
		console.log(xhtp.response);

		let tilst = document.querySelector('#tlist'); //querySelector('#tlist')
		let data = JSON.parse(xhtp.response);
		for (let reply of data) {
			console.log(reply);
			let tr = makeTrFunc(reply);
			tlist.append(tr);
		}
	}
	//tr생성해주는 함수
	function makeTrFunc(reply = {}) { //변수의 타입은 object타입이라는 의미.
		let tr = document.createElement('tr');
		for (let prop of showFields) {
			let td = document.createElement('td');
			td.innerText = reply[prop] //prop - 필드값
			tr.append(td);
		}
		//삭제버튼.
		let btn = document.createElement('button');
		btn.addEventListener('click', function (e) {
			let rNo = btn.parentElement.parentElement.children[0].innerText;
			console.log(btn);
			//db에서 삭제 후 화면에서 삭제.
			let xhtp = new XMLHttpRequest();
			xhtp.open('post', 'removeReply.do');
			xhtp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
			xhtp.send('rno=' + rNo);
			
			xhtp.onload = function(){
				let result = xhtp.response;
				if(result.retCode == 'Success'){
					//화면에서 지우기.

				}else if(result.retCode == 'Fail'){
					alert('처리중 에러발생.')

				}else {
					alert('알 수 없는 결과값입니다.')
				}
			}

		})
		btn.innerText = '삭제'
		let td = document.createElement('td');
		td.append(btn);
		tr.append(td);


		return tr; //생성한 tr을 반환.
	}



	//등록이벤트
	document.querySelector("#addBtn").addEventListener('click', addReplyFnc)

	function addReplyFnc(e) {
		//로그인 여부를 체크해서 로그인 정보가 없으면 로그인 화면으로 이동하기/.
		let id = document.querySelector('#content span').innerText;
		if (id == null || id == '') {
			alert("로그인 후에 처리하세요.");
			location.href = 'loginForm.do';
			return;
		}
		console.log('click', e.target);
		console.log('reply', document.querySelector("#reply").value)
		console.log('id', "${id}")
		let reply = document.querySelector("#reply").value

		//Ajax호출.
		let xhtp = new XMLHttpRequest();
		xhtp.open('post', 'addReply.do'); //get방식은 uri에 들어가지만 post는 send에 key와 value값으로 넘겨줘야한다.
		xhtp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xhtp.send('id=${id}&reply=' + reply + "&notice_no=${noticeInfo.noticeNo}");
		xhtp.onload = function () {
			console.log(xhtp.response);
			let result = JSON.parse(xhtp.response);
			if (result.retCode == 'Success') {
				//값을 활용해서 tr생성
				let tr = makeTrFunc(result.data);
				tlist.append(tr);
				//입력값 초기화하기.

				document.getElementById("reply").value = '';
				document.getElementById("reply").focus();


			} else if (result.retCode == 'Fail') {


				//alert('처리중 에러가 발생했습니다.')

			}



		}
	}
</script>


<!-- 
<script>
function back() {
    history.back();
} 
</script> onClick ="back()"-->