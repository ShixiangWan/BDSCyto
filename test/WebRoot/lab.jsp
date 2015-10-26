<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
	</script>
  </head>

  <body>

    <jsp:include page="header.jsp" />

    <div class="container">

		<div class="panel panel-primary">
		
		  <div class="panel-heading">
		    <h2 class="panel-title">
		    	<span class="glyphicon glyphicon-hourglass" aria-hidden="true"></span>
		    	BDSCyto Experiment
		    </h2>
		  </div>
		  
		  <div class="panel-body">
		  	<div class="row">
		  	  <!---------------------First raw ---------------------->
			  <div class="col-md-4">
				<h4><strong>Step 1.</strong> Upload or paste FASTA (required)</h4>
				<hr/>
				<form id="form" action="upload.do"  method="post" enctype="multipart/form-data">    <!-- form[0] -->
			  	<div class="radio">
				  <label>
				    <input type="radio" name="name" id="optionsRadios1" value="option1" checked>
				    Upload file
				    <input type="file" id="exampleInputFile" name="file">
				  </label>
				</div>
				<div class="radio">
				  <label>
				    <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
				    Paste file content
				  </label>
				  <textarea class="form-control" rows="13"></textarea>
				</div>
				<!-- </form> -->
				<p>*<strong>Note:</strong> the size of your data must be less than 20MB!</p>
			  </div><!-- First raw -->
			  
			  <!---------------------Second raw ---------------------->
			  <div class="col-md-4">
				<h4><strong>Step 2.</strong> Algorithm options (optional)</h4>
				<hr/>
				<!-- <form id="form2">    form[1] -->
				<ul class="list-group">
				
				  <li class="list-group-item">
				    <div class="form-inline">
					  <div class="form-group col-md-8">
					    <p class="form-control-static">Initial dimension</p>
					  </div>
					  <div class="form-group">
					  	<select class="form-control" name="initial">
						  <option>2</option>
						  <option>10</option>
						  <option>20</option>
						  <option>30</option>
						  <option>40</option>
						  <option>50</option>
						</select>
					  </div>
					</div>
				  </li>
				 
				  <li class="list-group-item">
				    <div class="form-inline">
					  <div class="form-group col-md-8">
					    <p class="form-control-static">Times Iterator</p>
					  </div>
					  <div class="form-group">
					  	<select class="form-control" name="times">
						  <option>2</option>
						  <option>5</option>
						  <option>10</option>
						</select>
					  </div>
					</div>
				  </li>
				  
				  <li class="list-group-item">
				    <div class="form-inline">
					  <div class="form-group col-md-8">
					    <p class="form-control-static">Subtractor Iterator</p>
					  </div>
					  <div class="form-group">
					  	<select class="form-control" name="subtractor">
						  <option>2</option>
						  <option>5</option>
						  <option>10</option>
						</select>
					  </div>
					</div>
				  </li>
				  
				  <li class="list-group-item">
				    <div class="form-inline">
					  <div class="form-group col-md-8">
					    <p class="form-control-static">Maximum dimension</p>
					  </div>
					  <div class="form-group">
					  	<select class="form-control" name="maximum">
						  <option>10</option>
						  <option>50</option>
						  <option>100</option>
						  <option>200</option>
						  <option>500</option>
						  <option>default</option>
						</select>
					  </div>
					</div>
				  </li>
				  
				  <li class="list-group-item">
				    <div class="form-inline">
					    <p>Extract feature</p>
					    <label class="checkbox-inline">
						  <input type="checkbox" id="inlineCheckbox1" value="option1" checked>Optimal
						</label>
						<label class="checkbox-inline">
						  <input type="checkbox" id="inlineCheckbox2" value="option2" checked>PC-AAC
						</label>
						<label class="checkbox-inline">
						  <input type="checkbox" id="inlineCheckbox3" value="option3" checked>SC-AAC
						</label>
						<label class="checkbox-inline">
						  <input type="checkbox" id="inlineCheckbox3" value="option4" checked>188D
						</label>
					</div>
				  </li>
				  
				  <li class="list-group-item">
				    <div class="form-inline">
					    <p>Classifier optional</p>
					    <label class="checkbox-inline">
						  <input type="checkbox" id="inlineCheckbox1" value="option1" checked> LibD3C
						</label>
						<label class="checkbox-inline">
						  <input type="checkbox" id="inlineCheckbox3" value="option5" checked> RandomForest
						</label>
						<label class="checkbox-inline">
						  <input type="checkbox" id="inlineCheckbox2" value="option2" checked> LibSVM
						</label></br>
						<label class="checkbox-inline">
						  <input type="checkbox" id="inlineCheckbox3" value="option3" checked> Bagging
						</label>
						<label class="checkbox-inline">
						  <input type="checkbox" id="inlineCheckbox3" value="option4" checked> IBK
						</label>
					</div>
				  </li>
				</ul>
				<!-- </form> -->
			  </div><!-- Second raw -->
			  
			  <div class="col-md-4">
				<h4><strong>Step 3.</strong> Output options (optional)</h4>
				<hr/>
				<!-- <form id="form3">    form[2] -->
			  	<div class="radio">
				  <label>
				    <input type="checkbox" name="optionsRadios" id="optionsRadios1" value="option31" checked>
				    Accuracy (ACC) chart
				  </label>
				</div>
				<div class="radio">
				  <label>
				    <input type="checkbox" name="optionsRadios" id="optionsRadios2" value="option32" checked>
				    Sensitivity (SN) chart
				  </label>
				</div>
				<div class="radio">
				  <label>
				    <input type="checkbox" name="optionsRadios" id="optionsRadios3" value="option33" checked>
				    Specificity (SP) chart
				  </label>
				</div>
				<div class="radio">
				  <label>
				    <input type="checkbox" name="optionsRadios" id="optionsRadios4" value="option34" checked>
				    Receiver operating characteristic curve (ROC)
				  </label>
				</div>
				
				<hr/>
				
				<div class="radio">
				  <label>
				    <input type="checkbox" name="optionsRadios" id="optionsRadios4" value="option35" checked>
				    Extracted features (.csv)
				  </label>
				</div>
				
				<br/>
				<br/>
				<br/>
				<button type="button" onclick="formReset()" class="btn btn-warning btn-block">
					Reset
				</button>
				<button type="submit" class="btn btn-success btn-block">
					<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
					Submit experiment
				</button>
				
				</form>
			  </div><!-- Third raw -->
			  
			</div>
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
		  	<table style="table-layout:fixed;" class="table table-condensed table-bordered">
				<tr>
				  <td class="active">Overview</td>
				  <td class="info">Algorithm</td>
				  <td class="info">Classifier</td>
				  <td class="info">Optimal Dimension</td>
				  <td class="info">ACC</td>
				  <td class="info">SN</td>
				  <td class="info">SP</td>
				</tr>
				<tr>
				  <td class="">
				  	Dimension: <span class="result">${requestScope.feaNum}</span></br>
				  	Positive: <span class="result">${requestScope.pos}</span></br>
				  	Negative: <span class="result">${requestScope.neg}</span></br>
				  	Error: <span class="result">${requestScope.error}</span>
				  </td>
				  <td class="result">${requestScope.algorithm}</td>
				  <td class="result">${requestScope.classifier}</td>
				  <td class="result">${requestScope.bestFeaNum}</td>
				  <td class="result">${requestScope.bestAccuracy}%</td>
				  <td class="result">SN</td>
				  <td class="result">SP</td>
				</tr>
			</table>
		  </div>
		  
		<jsp:include page="charts.jsp" />
		<jsp:include page="charts2.jsp" />
		  
		</div>
		  
		
		
        
        

    </div> <!-- /container -->

    
  </body>
</html>