//basic2.js

$(document).ready(function(){
  console.log($('ul>li:nth-of-type(1)'));
  $('ul>li:nth-of-type(1)').css('background','yellow');
  $('ul>li:nth-of-type(2)').css('color','red');
  
  //$('#show button')[0]
  //$('#show>p:nth-of-type(1)>button').css('background','yellow')
  $('#show>p>button:eq(0)').css('background','yellow');
  //$('span:eq(1)').text('월드');
  $('span:eq(1)').html('<b>월드</b>');


  $('div span:eq(3),div span:eq(0)').css('color','red');
  //$('div p:nth-of-type(1)>span').css('color','red'); 같은 태그만 계산 nth-of-type
  //gt  - 보다 큰것 
  $('div#show>p:gt(0)>span').css('background','blue').css('color','white');
  //not - 외에 모두
  $('#show2>p:not(:eq(1))>span').css('background','yellow');
  //contains - 지정한 텍스트
  $('span:contains(Good)').css('fontSize','30px')
  //has - 가지고 있는 태그
  $('p:has(b)').css('background','lightgreen');
  //first, last, not,parent,empty는 위와 같이 알아두는게 좋을듯?





});


















