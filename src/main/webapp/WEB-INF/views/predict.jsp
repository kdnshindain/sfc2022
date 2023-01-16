<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<html>
<%
List<HashMap> mList = (List<HashMap>)request.getAttribute("mList");
List<HashMap> pList = (List<HashMap>)request.getAttribute("pList");
String category = (String)request.getAttribute("category");
//현재날짜
String asOfDate = (String)request.getAttribute("asOfDate");

//예측날짜
//String predDate = (String)request.getAttribute("predDate");

//날짜 파싱
StringBuffer buffer1 = new StringBuffer();
buffer1.append(asOfDate.toString().substring(0, 4));
buffer1.append("-");
buffer1.append(asOfDate.toString().substring(4, 6));
buffer1.append("-");
buffer1.append(asOfDate.toString().substring(6, 8));
String as_of_date = buffer1.toString();

//예측날짜 null 방지
/*
if(predDate != null){
	StringBuffer buffer2 = new StringBuffer();
	buffer2.append(predDate.toString().substring(0, 4));
	buffer2.append("-");
	buffer2.append(predDate.toString().substring(4, 6));
	buffer2.append("-");
	buffer2.append(predDate.toString().substring(6, 8));
	String pred_date = buffer2.toString();		
}
*/
%>


	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Smart Fuel System</title>
		<link rel="stylesheet" href="./assets/home/css/style.css">
		<link rel="stylesheet" href="./assets/node_modules/bootstrap/dist/css/bootstrap.css" />
		<script src="./assets/node_modules/bootstrap/dist/js/bootstrap.js" ></script>
		<style type="text/css">
		
.highcharts-figure,
.highcharts-data-table table {
    min-width: 310px;
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

		</style>
		
	</head>
	<body>
	
	<p> &nbsp; </p>
	
	<div class = "box">
		<div class = "nav">
			<div class="nav_btn">
	           <input type="button" class="btn btn-primary" style="width: 100%" value="Home" onclick="location.href='<%=request.getContextPath() %>/';">		
			</div>			
			<div class="nav_btn">
	           <input type="button" class="btn btn-primary" style="width: 100%" value="예측 서비스" onclick="location.href='<%=request.getContextPath() %>/predict';">		
			</div>
			<div class="nav_btn">
	           <input type="button" class="btn btn-primary" style="width: 100%" value="분석 서비스" onclick="location.href='<%=request.getContextPath() %>/factor';">		
			</div>	
		</div><!-- nav -->
		<div class="content">		
			<div class="search">
				<form class="search_form" action="<%=request.getContextPath()%>/predict">
					<div class="search_date">
						<label for="asOfDate">현재날짜</label>
						<input type="text" class="form-control" name="asOfDate" value="<%=asOfDate %>" id="asOfDate">
					</div>
					<div class="search_category">
						<label for="category">예측유형</label>
						<select name="category" class="form-select" id="category">
						  <option value="gci" <% if ("gci".equals(category)) out.println("selected"); %>>GCI :호주 글로벌 유연탄 인덱스</option>
						  <option value="ici1" <% if ("ici1".equals(category)) out.println("selected"); %>>ICI1 : 인도네시아 고열량탄</option>
						  <option value="ici3" <% if ("ici3".equals(category)) out.println("selected"); %>>ICI3 : 인도네시아 중열량탄</option>
						  <option value="ici4" <% if ("ici4".equals(category)) out.println("selected"); %>>ICI4 : 인도네시아 저열량탄</option>
						</select>
					</div>
					<div class="search_button">
						<input type="submit" class="btn btn-secondary" value="예측 및 분석">
					</div>
				</form>
			
			</div>
	<p> &nbsp; </p>
	
<script src="./assets/predict/code/highcharts.js"></script>
<script src="./assets/predict/code/modules/series-label.js"></script>
<script src="./assets/predict/code/modules/exporting.js"></script>
<script src="./assets/predict/code/modules/export-data.js"></script>
<script src="./assets/js/code/modules/accessibility.js"></script>
    <div class="show">
		<figure class="highcharts-figure" style="width:50%;border:3px solid red">
		    <div id="pred" class="prediction">
			    <p class="highcharts-description">
			        Smart Fuel Center 클라우드 서비스를 위한 예측 시스템
			    </p>
		    </div>
		</figure>
		<figure class="highcharts-figure" style="width:50%;border:3px solid yellow">
		   	<div class="inspection">
		   		<p>에너지 연료 단가 변동요인 분석</p>
		   	</div>
		</figure>
    </div>

	<script type="text/javascript">
// Data retrieved from https://www.vikjavev.no/ver/snjomengd

Highcharts.chart('pred', {
    chart: {
        type: 'spline'
    },
    title: {
        text: '에너지 연료 단가예측 <%=category.toUpperCase() %> 지표'
    },
    subtitle: {
        text: '예측 기준 일자 : <%=as_of_date%>'
    },
    xAxis: {
        type: 'datetime',
        dateTimeLabelFormats: { // don't display the year
            month: '%e. %b',
            year: '%b'
        },
        title: {
            text: '날짜'
        }
    },
    yAxis: {
        title: {
            text: '국제가격 (Dollar)'
        },
        min: 0
    },
    tooltip: {
        headerFormat: '<b>{series.name}</b><br>',
        pointFormat: '{point.x:%e. %b}: {point.y:.2f} m'
    },

    plotOptions: {
        series: {
            marker: {
                enabled: true,
                radius: 2.5
            }
        }
    },

    colors: ['#6CF', '#80C', '#06C', '#036', '#000'],

    // Define the data points. All series have a year of 1970/71 in order
    // to be compared on the same x axis. Note that in JavaScript, months start
    // at 0 for January, 1 for February etc.
    series: [
        {
            name: "관측 값",
            data: [
            	<%
            	for(int i=0 ; i<mList.size() ; i++){
            		Map map = mList.get(i);
            		
            		String predDate = (String)map.get("pred_date");
            		Object index = map.get(category);
            		
            		StringBuffer buffer = new StringBuffer();
            		buffer.append(predDate.toString().substring(0, 4));
            		buffer.append(",");
            		buffer.append(predDate.toString().substring(4, 6));
            		buffer.append(",");
            		buffer.append(predDate.toString().substring(6, 8));
            		predDate = buffer.toString();
            		
            		%>[Date.UTC(<%=predDate%>),  <%=index%>],<%
            	}
            	%>
            ]
        }, {
            name: "예측 값",
            data: [
            	<%
            	for(int i=0 ; i<pList.size() ; i++){
            		Map map = pList.get(i);
            		
            		String predDate = (String)map.get("pred_date");
            		Object index = map.get(category);
            		
            		StringBuffer buffer = new StringBuffer();
            		buffer.append(predDate.toString().substring(0, 4));
            		buffer.append(",");
            		buffer.append(predDate.toString().substring(4, 6));
            		buffer.append(",");
            		buffer.append(predDate.toString().substring(6, 8));
            		predDate = buffer.toString();
            		
            		%>[Date.UTC(<%=predDate%>),  <%=index%>],<%
            	}
            	%>
            ]
        }
    ]
});
	
	
		</script>
            <p>&nbsp;</p>

                    		

    <script src="./assets/home/js/main.min.js"></script>	
		</div><!-- content -->		
	</div><!-- box -->
    <footer class="site-footer">
        <div class="container">
            <div class="site-footer-inner has-top-divider">
                <div class="footer-copyright">&copy; Smart Energy Business Team, KEPCO-KDN</div>
            </div>
        </div>
    </footer>
	</body>
</html>

