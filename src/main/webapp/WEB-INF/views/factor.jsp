<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<html>
<%
List<HashMap> fList = (List<HashMap>)request.getAttribute("fList");
String category = (String)request.getAttribute("category");
String asOfDate = (String)request.getAttribute("asOfDate");
String predDate = (String)request.getAttribute("predDate");

StringBuffer buffer1 = new StringBuffer();
buffer1.append(asOfDate.toString().substring(0, 4));
buffer1.append("-");
buffer1.append(asOfDate.toString().substring(4, 6));
buffer1.append("-");
buffer1.append(asOfDate.toString().substring(6, 8));
String as_of_date = buffer1.toString();

if(predDate != null){
	StringBuffer buffer2 = new StringBuffer();
	buffer2.append(predDate.toString().substring(0, 4));
	buffer2.append("-");
	buffer2.append(predDate.toString().substring(4, 6));
	buffer2.append("-");
	buffer2.append(predDate.toString().substring(6, 8));
	String pred_date = buffer2.toString();	
}
%>


	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Smart Fuel System</title>
		    <link rel="stylesheet" href="assets/home/css/style.css">
		<style type="text/css">
.highcharts-figure,
.highcharts-data-table table {
    min-width: 320px;
    max-width: 800px;
    margin: 1em auto;
}

.highcharts-data-table table {
    font-family: Verdana, sans-serif;
    border-collapse: collapse;
    border: 1px solid #ebebeb;
    margin: 10px auto;
    text-align: center;
    width: 100%;
    max-width: 500px;
}

.highcharts-data-table caption {
    padding: 1em 0;
    font-size: 1.2em;
    color: #555;
}

.highcharts-data-table th {
    font-weight: 600;
    padding: 0.5em;
}

.highcharts-data-table td,
.highcharts-data-table th,
.highcharts-data-table caption {
    padding: 0.5em;
}

.highcharts-data-table thead tr,
.highcharts-data-table tr:nth-child(even) {
    background: #f8f8f8;
}

.highcharts-data-table tr:hover {
    background: #f1f7ff;
}

.ld-label {
    width: 200px;
    display: inline-block;
}

.ld-url-input {
    width: 500px;
}

.ld-time-input {
    width: 40px;
}

		</style>
	</head>
	<body>
	
	<p> &nbsp; </p>
	<form action="<%=request.getContextPath()%>/factor">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		???????????? :
		<input type-"text" name="asOfDate" value="<%=asOfDate %>">
		<br&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		???????????? :
		<select name="category" id="cars">
		  <option value="gci" <% if ("gci".equals(category)) out.println("selected"); %>>GCI :?????? ????????? ????????? ?????????</option>
		  <option value="ici1" <% if ("ici1".equals(category)) out.println("selected"); %>>ICI1 : ??????????????? ????????????</option>
		  <option value="ici3" <% if ("ici3".equals(category)) out.println("selected"); %>>ICI3 : ??????????????? ????????????</option>
		  <option value="ici4" <% if ("ici4".equals(category)) out.println("selected"); %>>ICI4 : ??????????????? ????????????</option>
		</select>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="submit" value="????????????">
	</form>
	<p> &nbsp; </p>
	
<script src="./assets/predict/code/highcharts.js"></script>
<script src="./assets/predict/code/highcharts-more.js"></script>
<script src="./assets/predict/code/modules/dumbbell.js"></script>
<script src="./assets/predict/code/modules/lollipop.js"></script>
<script src="./assets/predict/code/modules/exporting.js"></script>
<script src="./assets/predict/code/modules/accessibility.js"></script>
<figure class="highcharts-figure">
    <div id="container"></div>
    <p class="highcharts-description">
        Smart Fuel Center ???????????? ???????????? ?????? ?????? ?????????
    </p>
</figure>

		<script type="text/javascript">
// Data retrieved from https://worldpopulationreview.com/countries
Highcharts.chart('container', {

    chart: {
        type: 'lollipop'
    },

    accessibility: {
        point: {
            valueDescriptionFormat: '{index}. {xDescription}, {point.y}.'
        }
    },

    legend: {
        enabled: false
    },

    subtitle: {
        text: '?????? ?????? ?????? : <%=as_of_date%>'
    },

    title: {
        text: '????????? ?????? ?????? ???????????? ?????? <%=category.toUpperCase() %> ??????'
    },

    tooltip: {
        shared: true
    },

    xAxis: {
        type: 'category'
    },

    yAxis: {
        title: {
            text: 'Population'
        }
    },

    series: [{
        name: 'Population',
        data: [
        	<%
        	for(int i=0 ; i<fList.size() ; i++){
        		Map map = fList.get(i);
        		
        		String metaNm = (String)map.get("meta_nm");
        		Object index = map.get("fi_score");
        		
        		out.println("{");
        		out.println("name:'"+metaNm+"',");
        		out.println("low:"+index);
        		out.println("},");
        	}
        	%>
        ]
    }]

});

		</script>
		
            <p>&nbsp;</p>
            <input type="button" value="Home" onclick="location.href='<%=request.getContextPath() %>/';">
            &nbsp;
            <input type="button" value="?????? ?????????" onclick="location.href='<%=request.getContextPath() %>/predict';">
            &nbsp;
            <input type="button" value="?????? ?????????" onclick="location.href='<%=request.getContextPath() %>/factor';">
            <p>&nbsp;</p>
		
        <footer class="site-footer">
            <div class="container">
                <div class="site-footer-inner has-top-divider">
                    <div class="footer-copyright">&copy; Smart Energy Business Team, KEPCO-KDN</div>
                </div>
            </div>
        </footer>

    <script src="./assets/home/js/main.min.js"></script>	
	</body>
</html>

