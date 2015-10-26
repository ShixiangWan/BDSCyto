
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main1" style="height:400px"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts图表
        var myChart1 = echarts.init(document.getElementById('main1')); 
        
        var option1 = {
       	     title: {
       	         text: 'Accuracy (ACC) chart',
       	         subtext: 'Data from BDSCyto',
       	         x:'center'
       	     },
       	     legend: {                                   // 图例配置
       	    	 x: 'right',
       	         padding: 15,                             // 图例内边距，单位px，默认上下左右内边距为5
       	         itemGap: 10,                            // Legend各个item之间的间隔，横向布局时为水平间隔，纵向布局时为纵向间隔
       	         data: ['Optimal','PC-AAC','SC-AAC','188D']
       	     },
       	     tooltip: {                                  // 气泡提示配置
       	         trigger: 'axis',                        // 触发类型，默认数据触发，可选为：'axis'
       	     },
       	     grid: {
       	    	 y: '60'
       	     },
       	     xAxis: [                                    // 直角坐标系中横轴数组
       	         {
       	    	 	 name: 'Algorithm',
       	             type: 'category',                   // 坐标轴类型，横轴默认为类目轴，数值轴则参考yAxis说明
       	             data: ['LibD3C', 'RandomForest', 'LibSVM', 'Bagging', 'IBK']
       	         }
       	     ],
       	     yAxis: [                                    // 直角坐标系中纵轴数组
       	         {
       	        	 name: 'Rate (%)',
       	             type: 'value',                      // 坐标轴类型，纵轴默认为数值轴，类目轴则参考xAxis说明
       	             boundaryGap: [0, 0],            // 坐标轴两端空白策略，数组内数值代表百分比
       	             splitNumber: 0.1                      // 数值轴用，分割段数，默认为5
       	         }
       	     ],
       	     series: [
       	         {
       	             name: 'Optimal',                        // 系列名称
       	             type: 'bar',                       // 图表类型，折线图line、散点图scatter、柱状图bar、饼图pie、雷达图radar
       	             data: [90.7283, 23.67, 45.67, 56.54, 78.53],
       	             markPoint : {
       	            	effect: {show: true,},
       	                data : [
       	                    // 纵轴，默认
       	                    {type : 'max', name: 'Max rate',symbol: 'emptyCircle', itemStyle:{normal:{color:'#dc143c',label:{position:'top'}}}},
       	                ]
       	             },
       	         },
       	         {
       	             name: 'PC-AAC',                    // 系列名称
       	             type: 'bar',                       // 图表类型，折线图line、散点图scatter、柱状图bar、饼图pie、雷达图radar
       	             data: [90.7283, 23.67, 45.67, 56.54, 78.53],
       	             markPoint : {
       	            	effect: {show: true,},
       	                data : [
       	                    // 纵轴，默认
       	                    {type : 'max', name: 'Max rate',symbol: 'emptyCircle', itemStyle:{normal:{color:'#dc143c',label:{position:'top'}}}},
       	                ]
       	             },
       	         },
       	         {
       	             name: 'SC-AAC',                    // 系列名称
       	             type: 'bar',                       // 图表类型，折线图line、散点图scatter、柱状图bar、饼图pie、雷达图radar
       	             data: [90.7283, 23.67, 45.67, 56.54, 78.53],
       	             markPoint : {
       	            	effect: {show: true,},
       	                data : [
       	                    // 纵轴，默认
       	                    {type : 'max', name: 'Max rate',symbol: 'emptyCircle', itemStyle:{normal:{color:'#dc143c',label:{position:'top'}}}},
       	                ]
       	             },
       	         },
       	         {
       	             name: '188D',                    // 系列名称
       	             type: 'bar',                       // 图表类型，折线图line、散点图scatter、柱状图bar、饼图pie、雷达图radar
       	             data: [${requestScope.bestAccuracy}, 23.67, 45.67, 56.54, 78.53],
       	             markPoint : {
       	            	effect: {show: true,},
       	                data : [
       	                    // 纵轴，默认
       	                    {type : 'max', name: 'Max rate',symbol: 'emptyCircle', itemStyle:{normal:{color:'#dc143c',label:{position:'top'}}}},
       	                ]
       	             },
       	         },
       	     ]
       	 };
        myChart1.setOption(option1); 
    </script>
                    