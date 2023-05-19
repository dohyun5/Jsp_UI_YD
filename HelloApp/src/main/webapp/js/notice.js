//notice.js
function deleteRow() {
  //ajax호출 id를 기준으로 삭제후 화면에서 제거 
  let tr = $(this).closest('tr');
  let no = tr.children().eq(0).text();
  console.log(no);
  $.ajax({
      url: 'delNoticeJson.do?nNo=' + no,
      dataType: 'html',
      error: function (xhr) {
        console.log(xhr)
      },
      success: function (result) {
        if (result == 'Success') {
          tr.remove();
        } else if (result == 'Fail') {
          alert('처리 에러')
        } else {
          alert('알 수 없는 반환')
        }

      }
    })
    .always(function () {
      console.log('final');
    })


}


//multipart request.
$(document).ready(function () {
  
//modal 처리 라이브 이벤트처리.
$('#noticeList').on('dblclick','tr', function(){
  let no = $(this).children().eq(0).text();
  console.log(no);
  $.ajax({
    url:'getNoticeJson.do?nNo='+no,
    dataType:'json',
    error: function(xhr){
      console.log(xhr);
    },
    success: function(data) {
      console.log(data);
      $('.nNo').text(data.noticeNo),
      $('.nTitle').text(data.noticeTitle),
      $('.nContent').val(data.noticeContent),
      $('.nWriter').text(data.noticeWriter),
      $('.nFile').css('width','100px').attr('src','images/'+data.attachFile)
    }

  })


  $('#myModal').css('display','block');
})
$('span.close').on('click',function(){
  $('#myModal').css('display','none');
})
$(window).on('click',function(e){
  console.log(e.target,$('#myModal'))
  if(e.target == $('#myModal')[0]){
    $('#myModal').css('display','none');
  }
})

//modal 창에 있는 이미지 클릭.
$('img.nFile').on('click',function(){
  $('#attachFile').click();//trigger event.
})

//파일 선택하면 change 이벤트.
$('#attachFile').on('change',function(e){
  //게시글 번호, 파일=> 서버전송 : DB수정
  console.log(e.target.files[0])

  let data = new FormData();
  data.append('nNo',$('.nNo').text());
  data.append('nFile',e.target.files[0])
  console.log(data);

  $.ajax({
    url: 'modifyNoticeFile.do',
    method: 'post',
    data: data,
    //multipart 요청 아래 두가지를 false로 지정해야함 
    contentType: false,
    processData: false,
    error: function(err){
      console.error(err)
    },
    success:function(result){
      console.log(result)
      //이미지 변경.
      $('img.nFile').attr('src','images/'+ result.attachFile);

    }
  });


})

//모달창의 수정버튼 클릭.
$('div.modal-body button').on('click',function(e){
  let no = $('div.modal-body td.nNo').text();
  let title = $('div.modal-body td.nTitle').text();
  let content = $('div.modal-body textarea.nContent').val();
  $.ajax({
    url:'modifyNoticeJson.do',
    method:'post',
    data: {no:no, title:title, content:content},
    error:function(){

    },
    success:function(result){
      if(result.retCode == 'Success'){ 
        console.log(result.retVal); //no,title,content,file...
        $('#tr_'+result.retVal.noticeNo).find('img').attr('src','images/'+result.retVal.attachFile);
        $('#tr_'+result.retVal.noticeNo).find('td:nth-of-type[2]').text(result.retVal.noticeContent);
        console.log();
        $('#myModal').hide();
      }else if(result.retCode == 'Fail'){
        alert('error 발생')
      }
    }




  })


})





  //등록버튼
  $('form').on('submit', function (e) {
    e.preventDefault(); //form.submit기능 차단
    var frm = $('form')[0];
    //$(frm).find('input[name:"job"]').value('multi');
    var data = new FormData(frm); //multipart/ form-data 처리하는 객체
    for (let val of data.entries(frm)) {
      console.log(val);
    }
    $.ajax({
        url: 'addNotice.do',
        method: 'post',
        data: data,
        //multipart 요청 아래 두가지를 false로 지정해야함 
        contentType: false,
        processData: false,
        error: function (jqxhr) {
          console.log(jqxhr.responseText)
        },
        success: function (data, status, xhr) {
          let val = data.retVal;
          if (data.retCode == 'Success') {
            let tr = $('<tr />').append(
              $('<td />').css('vertical-align', 'middle').text(val.noticeNo),
              $('<td />').css('vertical-align', 'middle').text(val.noticeTitle),
              $('<td />').css('vertical-align', 'middle').text(val.noticeContent),
              $('<td />').css('vertical-align', 'middle').text(val.noticeWriter),
              $('<td />').css('vertical-align', 'middle').append($('<img>').css('width', '100px').attr('src', 'images/' + val.attachFile)),
              $('<td />').css('vertical-align', 'middle').append($('<button />').text('Delete').on('click', deleteRow))
            );
            tr.attr('id','tr_'+notice.NoticeNo);
            $('#noticeList').prepend(tr);
            $('form')[0].reset();//form reset 이벤트 호출 
          } else if (data.retCode == 'Fail') {
            alert('처리중 에러')
          } else {
            alert('알 수 없는 에러')
          }
          // $('form').after($('<p />').text("작성자 :" + data.retVal.noticeWriter +
          //   "제목 :" + data.retVal.noticeTitle +
          //   "내용 :" + data.retVal.noticeContent +
          //   "첨부파일 :" + data.retVal.attachFile));
        }
      })
      .always(function () {
        console.log('final');
      })
  }); //end of 등록버튼

  //공지목록 출력
  $.ajax({
    url: 'noticeListJson.do',
    method: 'GET',
    DataType: 'json',
    error: function (xhr) {
      console.log(xhr);
    },
    success: function (data) {
      console.log(data);
      data.forEach(function (notice) {
        let tr = $('<tr />').append($('<td />').css('vertical-align', 'middle').text(notice.noticeNo),
          $('<td />').css('vertical-align', 'middle').text(notice.noticeTitle),
          $('<td />').css('vertical-align', 'middle').text(notice.noticeContent),
          $('<td />').css('vertical-align', 'middle').text(notice.noticeWriter),
          $('<td />').css('vertical-align', 'middle').append($('<img>').css('width', '100px').attr('src', 'images/' + notice.attachFile)),
          $('<td />').css('vertical-align', 'middle').append($('<button />').text('Delete').on('click', deleteRow)))

          tr.attr('id','tr_'+notice.noticeNo);
        $('#noticeList').append(tr);
      })
    }
  })

})