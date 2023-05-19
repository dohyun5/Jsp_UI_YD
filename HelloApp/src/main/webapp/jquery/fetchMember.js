//fetchMember.js
$(function () {

  $('#list').remove();
  $('#show').empty();
  $('#show2').remove();

  $('#show').before('<h3>회원목록</h3>');

  //json 데이터 출력.
  fetch('MOCK_DATA.json')
    .then(function (resolve) {
      return resolve.json(); //stream -> object로 변경 (json문자열을 jsvascript포멧으로 변경)
    })
    .then(makeList)
    .catch(function (err) {
      //예외처리
      console.error(err);
    }) //end of fetch







  //----------------------------------- 함수  ----------------------------------------------
  function makeHead() {
    //title 등록
    $('table:eq(1)').prepend(
      $('<thead />').append($('<th />').text('ID'),
        $('<th />').text('Fname'),
        $('<th />').text('Lname'),
        $('<th />').text('Gender'),
        $('<th />').text('Delete'))
    )

  }
  //한 라인 삭제 func
  function delMember(e) {
    console.log(e.target);
    $(e.target).parentsUntil('tbody').remove(); //jQuery객체로 바꿔주고 지우자
  }

  function makeList(result) {
    console.log(result);
    let tbl = $('<table border="1" />');
    let tbd = $('<tbody />').attr('id', 'memberList');
    result.forEach(function (member, idx) {
      if (idx < 5) {
        let tr = $('<tr />').append($('<td />').text(member.id),
          $('<td />').text(member.first_name),
          $('<td />').text(member.last_name),
          $('<td />').text(member.gender),
          $('<td />').append($('<button />').text('Delete').on('click', delMember)),
          $('<td />').append($('<input />').attr('type','checkbox').on('click')));
        tbd.append(tr);
      }
    });
    tbl.append(tbd);
    $('#show').append(tbl); //<div id="show"><table border="1">...</table></div>
    makeHead();
  }
  

  $('#addBtn').on('click', AddMember)
  function AddMember(e){
    let tr = $('<tr />').append($('<td />').text($('#id').val()),
          $('<td />').text($('#fname').val()),
          $('<td />').text($('#lname').val()),
          $('<td />').text($('#gender').val()),
          $('<td />').append($('<button />').text('Delete').on('click', delMember)),
          $('<td />').append($('<input />').attr('type','checkbox').on('click')));
        $('#memberList').append(tr);
        $('input[type=text]').val('');//input 비워주기
  }
//
  $('#saveBtn').on('click',update)

  //마우스 오버시 input 채워주기

//더블 클릭시 바꿔주기
//라이브이벤트
  $('body').on('dblclick', '#memberList tr', function(){
    console.log('tr click.');
    //새로운 tr생성.
    let oldTr = $(this);
    let oldId = $(this).children().eq(0).text();
    let oldFname = $(this).children().eq(1).text();
    let oldLname = $(this).children().eq(2).text();
    let oldGender = $(this).children().eq(3).text();


    let newTr = $('<tr />').append(
      $('<td />').text(oldId),
      $('<td />').append($('<input>').val(oldFname)),
      $('<td />').html('<input type="text" value="'+oldLname+'">'),
      $('<td />').append('<select id="gender"><option value="'+oldGender+'">'+oldGender+'</option><option value="Male">Male</option><option value="Female">Female</option></select>'),
      $('<td />').append($('<button />').text('save').on('click', updateTr))
    );

      console.log(oldTr);
      oldTr.replaceWith(newTr);

  })
  function update(){
    let id = $('#id').val();
    let fname = $('#fname').val();
    let lname = $('#lname').val();
    let gender = $('#gender').val();

    $('td:contains('+id+')').parent().children().eq(1).text(fname);
    $('td:contains('+id+')').parent().children().eq(2).text(lname);
    $('td:contains('+id+')').parent().children().eq(3).text(gender);
  }
  
  $('button:eq(2)').on('click',function(){
    let targetTr = $('input[type="checkbox"]:checked').closest('tr');
    targetTr.next().after(targetTr);

  })


  function updateTr() {
    let oldTr = $(this).parentsUntil('tbody');
    let id = oldTr.find('td:eq(0)').text();
    let fname = oldTr.find('td:eq(1)>input').val();
    let lname = oldTr.find('td:eq(2)>input').val();
    let gender = oldTr.find('td:eq(3)>select').val();
    console.log(id);
    console.log(fname);
    console.log(lname);
    
    let newTr = $('.template').clone();
    newTr.find('td:eq(0)').text(id);
    newTr.find('td:eq(1)').text(fname);
    newTr.find('td:eq(2)').text(lname);
    newTr.find('td:eq(3)').text(gender);
    newTr.toggleClass('template');
    newTr.removeClass('template');

    console.log(newTr);
    oldTr.replaceWith(newTr);
  }

































})