let days = ['Sun', 'Mon', 'Tue', 'Wed', 'Thr', 'Fri', 'Sat'];

  function makeCalendar(month = 1) {


    return tbl;
  }

  // table 생성
  let tbl = document.createElement('table');
  tbl.border = '1';


  // thead 생성
  let thd = document.createElement('thead');
  let tr = document.createElement('tr');
  // th 생성
  for (let day of days) {
    let th = document.createElement('th');
    th.innerText = day;
    tr.append(th);
  }
  thd.append(tr);
  tbl.append(thd);


  // tbody 생성
  let tbd = document.createElement('tbody');
  tr = document.createElement('tr');


  // 5월 1일의 위치지정
  // 첫쨋날의 위치지정하기 위한 반복
  for (let i = 0; i < getFirstDay(6); i++) {
    tr.append(document.createElement('td'));
  }
  for (let i = 1; i <= getLastDate(6); i++) {
    // Dom을 활용해서 달력 생성
    let th = document.createElement('td');
    th.innerText = i;
    tr.append(th);
    if ((i + 6) % 7 == 0) { // 7일마다 새로운 tr 생성
      tbd.append(tr);
      tr = document.createElement('tr');
    }
  }
  // 색깔지정
  //   if ((i + getFirstDay(mon)) % 7 == 0) {
  //     th.style.backgroundColor = 'red';
  //     th.style.color = 'yellow';
  //   } else if ((i + getFirstDay(mon)) % 7 == 1) {
  //     th.style.backgroundColor = 'blue';
  //     th.style.color = 'yellow';
  //   }


  tbd.append(tr); // 마지막 생성된 tr을 출력
  tbl.append(tbd);


  // 생성된 tbl을 #show 하위 요소로 등록
  document.getElementById('show').append(makeCalendar(5));


  //
  // 월정보를 받고 첫번째 날짜의 값을 반환해주는 함수
  function getFirstDay(month = 5) {
    if (month == 5) {
      return 1;
    } else if (month == 6) {
      return 4;
    }
  }
  // 월정보를 받고 마지막날짜 반환해주는 함수
  function getLastDate(month = 5) {
    if (month == 5) {
      return 31;
    } else if (month == 6) {
      return 30;
    }
  }