<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <script src="${pageContext.request.contextPath}/static/js/jquery-2.2.3.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/loader.js"></script>
    <script type="text/javascript">
        google.charts.load('current', {packages:['wordtree']});
        google.charts.setOnLoadCallback(drawChart);
        function  getData() {
            var dataForChart;
            $.ajax({
                type: "GET",
                url: '${pageContext.request.contextPath}/applicant/${applicantId}',
                async: false,
                success: function (data) {
                    dataForChart=data;
                },
                error: function (e) {
                    console.log(e.responseText);
                }
            });
            return dataForChart;
        }
        function drawChart() {
          var data=getData();
            var dataForChart = google.visualization.arrayToDataTable(data);
            var options = {
                wordtree: {
                    format: 'implicit',
                    type: 'suffix',
                    height:750,
                    width:800,
                    word: 'applicant'
                }
            };

            var chart = new google.visualization.WordTree(document.getElementById('wordtree_basic'));
            chart.draw(dataForChart, options);
        }
    </script>
</head>
<body>
<div id="wordtree_basic" style="width:800px; height: 750px;"></div>
</body>
</html>
