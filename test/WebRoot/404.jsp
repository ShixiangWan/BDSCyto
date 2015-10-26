<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/npm.js"></script>
    
    <style type="text/css">
      body {
              min-height: 2000px;
              padding-top: 70px;
            }
    </style>
	<script type="text/javascript">
	function formReset()
	{
		document.getElementById("myForm").reset();
	}
	/* function algorithm()
	{
		
		initial=document.forms[0].initial;
		times=document.forms[0].times;
		asdas=document.forms[0].asdas;
		optimal=document.forms[0].optimal;
		
		txt = "";
		txt = txt + asdas.checked?(asdas.value+","):"";
		txt = txt + optimal.checked?(optimal.value+","):"";
		txt = txt + initial.checked?(initial.value+","):"";
		txt = txt + times.checked?(times.value+","):"";
		
		document.getElementById("order").value= optimal.checked?(optimal.value+","):"";
	} */
	
	
	</script>

  </head>

  <body>
    <jsp:include page="header.jsp" />
    <div class="container">
    
    
<!---------------------Second raw ---------------------->
			  <div class="col-md-4">
				<h4><strong>Step 2.</strong> Algorithm options (optional)</h4>
				<hr/>
				<form id="myForm" action="algorithm.do">    <!-- form[1] -->
				<ul class="list-group">
				
				  <li class="list-group-item">
				    <div class="form-inline">
					  <div class="form-group col-md-8">
					    <p class="form-control-static">Initial dimension</p>
					  </div>
					  <div class="form-group">
					  	<select class="form-control" name="initial">
						  <option>5</option>
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
						  <option>1000</option>
						</select>
					  </div>
					</div>
				  </li>
				  
				  <li class="list-group-item">
				    <div class="form-inline">
					    <p>Extract feature</p>
					    <label class="checkbox-inline">
						  <input name="optimal" type="checkbox" id="inlineCheckbox1" value="optimal" checked>Optimal
						</label>
						<label class="checkbox-inline">
						  <input name="pcaac" type="checkbox" id="inlineCheckbox2" value="pcaac" checked>PC-AAC
						</label>
						<label class="checkbox-inline">
						  <input name="scaac" type="checkbox" id="inlineCheckbox3" value="scaac" checked>SC-AAC
						</label>
						<label class="checkbox-inline">
						  <input name="d" type="checkbox" id="inlineCheckbox3" value="d" checked>188D
						</label>
					</div>
				  </li>
				  
				  <li class="list-group-item">
				    <div class="form-inline">
					    <p>Clssifier optional</p>
					    <label class="checkbox-inline">
						  <input name="libd3c" type="checkbox" id="inlineCheckbox1" value="libd3c" checked> LibD3C
						</label>
						<label class="checkbox-inline">
						  <input name="randomforest" type="checkbox" id="inlineCheckbox3" value="randomforest" checked> RandomForest
						</label>
						<label class="checkbox-inline">
						  <input name="libsvm" type="checkbox" id="inlineCheckbox2" value="libsvm" checked> LibSVM
						</label></br>
						<label class="checkbox-inline">
						  <input name="bagging" type="checkbox" id="inlineCheckbox3" value="option3" checked> Bagging
						</label>
						<label class="checkbox-inline">
						  <input name="ibk" type="checkbox" id="inlineCheckbox3" value="option4" checked> IBK
						</label>
					</div>
				  </li>
				</ul>
				  <button type="button" onclick="formReset()" class="btn btn-default btn-block">
						Reset all options
				  </button>
				  <button type="submit" class="btn btn-success btn-block">
						<span class="glyphicon glyphicon-hand-down" aria-hidden="true"></span>
						Submit all and wait for results
				  </button>
				</form>
			  </div><!-- Second raw -->
			  
			  
			  
		<form action="user.do">
		  <div class="form-group">
		    <label for="exampleInputEmail1">User name</label>
		    <input type="text" class="form-control" id="exampleInputEmail1" name="uname">
		  </div>
		  <div class="form-group">
		    <label for="exampleInputPassword1">Password</label>
		    <input type="password" class="form-control" id="exampleInputPassword1" name="psword">
		  </div>
		  <button type="submit" class="btn btn-default" name="method" value="reg2">Submit</button>
		</form>
		<p>requestScope:${requestScope.a}</p>
		<p>a: ${a}</p>
		<p>param: ${param.a}</p>



    </div> <!-- /container -->
  </body>
</html>