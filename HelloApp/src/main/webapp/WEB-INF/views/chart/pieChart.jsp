<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <script type="text/javascript">
    google.charts.load('current', {
      'packages': ['corechart']
    });
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {
      //console.log('1')
      var result = [
        ['dept', 'cnt']
      ];
      //console.log('2')
      let xhtp = new XMLHttpRequest(); //비동기방식 처리(처리하는 순서가 매우 중요하다.)(Ajax호출)  동기방식 - 순차적으로 처리하는것. 
      xhtp.open('get', 'chartData.do');
      xhtp.send();
      xhtp.onload = function () {
        //console.log('3')
        let data = JSON.parse(xhtp.response); //{'admin':3,'sales':5,...'shipping':3}
        //data = {Admin : 3, Sales:6,... Shipping : 9}
        for (let dept in data) {
          //console.log(dept, data[dept]);
          result.push([dept, data[dept]]);
        }
        //console.log(result);
        data = google.visualization.arrayToDataTable(result);

        var options = {
          title: '부서별 인원 현황'
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options); //차트 그려줌


      }

      //console.log('4')

    }
  </script>
</head>

<body>
  <div id="piechart" style="width: 900px; height: 500px;"></div>
  <p>views/chart/pieChart.jsp</p>
</body>

</html>