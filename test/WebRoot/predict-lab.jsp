<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>BDSCyto</title>
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/echarts-all.js"></script>
    <script src="js/npm.js"></script>
    
    <style type="text/css">
      body {
              background: url(img/background2.jpg) repeat;
              background-attachment: fixed;
              min-height: 2000px;
              padding-top: 70px;
            }
      .result {
      	color: blue;
      }
    </style>
	<script type="text/javascript">
		/* 重置所有表单 */
		function formReset()
		{
			document.getElementById("form").reset();
		}
		/* 检查上传的文件格式 */
	  	function check(thiz){
	  		var value = $(thiz).val();
	  		value = value.substring(value.lastIndexOf(".")+1);
	  		if (value.indexOf("fasta") == -1) {
	  			document.getElementById("form").reset();
	  			alert("Please insure file format (.fasta)!");
	  		}
	  	}
    	function getMap() {
	  	var map = new Map();
	  	map.put("key", "value");
	  	var val = map.get("key");
    		alert(val);
    	}
	</script>
  </head>

  <body>

    <jsp:include page="header.jsp" />

    <div class="container">

		<div class="panel panel-primary">
		
		  <div class="panel-heading">
		    <h2 class="panel-title">
		    	<span class="glyphicon glyphicon-hourglass" aria-hidden="true"></span>
		    	<strong>Predict unknown sequence(s): </strong> Upload or paste FASTA (required)
		    </h2>
		  </div>
		  
		  <div class="panel-body">
			
			<form id="form" action="map.do"  method="post" enctype="multipart/form-data">
			  	<div class="radio">
				  <label>
				    <input type="radio" name="name" id="optionsRadios1" value="option1" checked>
				    Upload file
				    <input type="file" id="predictInputFile" name="file" onchange="check(this)">
				  </label>
				</div>
				<div class="radio">
				  <label>
				    <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
				    Paste file content
				  </label>
				  <textarea class="form-control" rows="5"></textarea>
				</div>
				<p>*<strong>Note:</strong> the size of your data must be less than 20MB!</p>
				<button type="button" onclick="formReset()" class="btn btn-warning btn-center" style="width:200px;">
					Reset
				</button>
				<button type="submit" class="btn btn-success btn-center"  style="width:200px;">
					<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
					Submit experiment
				</button>
				<a href="javascript:void(0)" type="button" class="btn btn-success btn-center"  style="width:200px;" onclick="getMap()">
					Test
				</a>
			</form>
			
			
		  </div>
		</div>
		<!-- ================================================== -->
		<div class="panel panel-primary">
		
		  <div class="panel-heading">
		    <h2 class="panel-title">
		    	<span class="glyphicon glyphicon-th" aria-hidden="true"></span>
		    	Records of your experiment
	    	</h2>
		  </div>
		  
		  <div class="panel-body">
		  <c:if test="${empty requestScope.mymap}">
			  <hr/>
			  <p>You have no data here. </p>
			  <p>Now, uploading your data or paste your sequence and getting rusults. </p>
		  </c:if>
		  <c:if test="${not empty requestScope.mymap}">
			  	<table class="table table-condensed table-bordered">
					<tr>
					  <td class="active col-md-1">ID</td>
					  <td class="info col-md-10">Sequence Name</td>
					  <td class="info col-md-1">Predict class</td>
					</tr>
					<c:set var="index" value="0" />
			        <c:forEach items="${requestScope.mymap}" var="m">
			        <c:set var="index" value="${index+1}" />
					<tr>
					  <td class="result">${index}</td>
					  <td class="result">${m.key}</td>
					  <td class="result">${m.value}</td>
					</tr>
					</c:forEach>
				</table>
			  
			  
			  <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	    	  <div id="main" style="height:400px"></div>
		  </c:if>
		  </div>
		</div>

    </div> <!-- /container -->
    
	<script type="text/javascript">
        // 基于准备好的dom，初始化echarts图表
        var myChart = echarts.init(document.getElementById('main')); 
        
        
        
        
        var option = {
        	    title : {
        	        text: '南丁格尔玫瑰图',
        	        subtext: '纯属虚构',
        	        x:'center'
        	    },
        	    tooltip : {
        	        trigger: 'item',
        	        formatter: "{a} <br/>{b} : {c} ({d}%)"
        	    },
        	    legend: {
        	        x : 'center',
        	        y : 'bottom',
        	        data:['rose1','rose2','rose3','rose4','rose5','rose6','rose7','rose8']
        	    },
        	    toolbox: {
        	        feature : {
        	            mark : {show: true},
        	            dataView : {show: true, readOnly: false},
        	            magicType : {
        	                show: true, 
        	                type: ['pie', 'funnel']
        	            },
        	            restore : {show: true},
        	            saveAsImage : {show: true}
        	        }
        	    },
        	    calculable : true,
        	    series : [
        	        {
        	            name:'半径模式',
        	            type:'pie',
        	            radius : [20, 110],
        	            center : ['25%', 200],
        	            roseType : 'radius',
        	            width: '40%',       // for funnel
        	            max: 40,            // for funnel
        	            itemStyle : {
        	                normal : {
        	                    label : {
        	                        show : false
        	                    },
        	                    labelLine : {
        	                        show : false
        	                    }
        	                },
        	                emphasis : {
        	                    label : {
        	                        show : true
        	                    },
        	                    labelLine : {
        	                        show : true
        	                    }
        	                }
        	            },
        	            data:[
        	                {value:10, name:'rose1'},
        	                {value:5, name:'rose2'},
        	                {value:15, name:'rose3'},
        	                {value:25, name:'rose4'},
        	                {value:20, name:'rose5'},
        	                {value:35, name:'rose6'},
        	                {value:30, name:'rose7'},
        	                {value:40, name:'rose8'}
        	            ]
        	        },
        	        {
        	            name:'面积模式',
        	            type:'pie',
        	            radius : [30, 110],
        	            center : ['75%', 200],
        	            roseType : 'area',
        	            x: '50%',               // for funnel
        	            max: 40,                // for funnel
        	            sort : 'ascending',     // for funnel
        	            data:[
        	                {value:10, name:'rose1'},
        	                {value:5, name:'rose2'},
        	                {value:15, name:'rose3'},
        	                {value:25, name:'rose4'},
        	                {value:20, name:'rose5'},
        	                {value:35, name:'rose6'},
        	                {value:30, name:'rose7'},
        	                {value:40, name:'rose8'}
        	            ]
        	        }
        	    ]
        	};
        myChart.setOption(option); 
    </script>
    
  </body>
</html>