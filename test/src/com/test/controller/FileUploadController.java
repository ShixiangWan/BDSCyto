package com.test.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.test.dao.ILabDAO;
import com.test.lab.testRF;
import com.test.mrmd.CaculateNum;
import com.test.mrmd.FormatArff;
import com.test.mrmd.MRMD;

@Controller
public class FileUploadController implements ServletContextAware{
		private ServletContext servletContext;
		@Autowired
		public void setServletContext(ServletContext context) {
			this.servletContext  = context;
		}
		
		@Resource
		private ILabDAO iLabDAO;
		
		@SuppressWarnings({ "rawtypes" })
		@RequestMapping(value="predict", method = RequestMethod.POST)
		public ModelAndView handleUploadData(String name,@RequestParam("file") CommonsMultipartFile file,
				Integer initial,Integer times,Integer subtractor,Integer maximum){
			try {
				if (!file.isEmpty()) {
					   String path = this.servletContext.getRealPath("/upload/");  //获取upload路径,末尾没有分隔符！！！
					   String pathModel = this.servletContext.getRealPath("/model/");  //获取model路径,末尾没有分隔符！！！
					   String fileName = file.getOriginalFilename();
					   String fileType = fileName.substring(fileName.lastIndexOf(".")); //获取文件格式
					   String tempName = new Date().getTime() + fileType; //按时间上传的文件命名
					   File file2 = new File(path, tempName); //新建一个文件
					   try {
						    file.getFileItem().write(file2); //将上传的文件写入新建的文件中
					   } catch (Exception e) {
						    e.printStackTrace();
					   }
					   //将新文件名存入数据库,检查是否有相同名字的文件，若没有则存入，返回存入成功
					   System.out.println(path + "\\" + tempName);
					   boolean flag = iLabDAO.checkByFilename(path + "\\" + tempName);
					   if (!flag) {
						   boolean flag2 = iLabDAO.saveFilename(path + "\\" + tempName);
						   if (flag2) {
							   System.out.println("存入成功");
						   }
					   }
					   
					   Map mymap = new testRF().run(path+"\\",tempName,"_test.arff",pathModel+"\\train.arff",pathModel+"\\cytoRF.model",2);
					   
					   Iterator it = mymap.entrySet().iterator();
					   while (it.hasNext())
					   {    
						   Map.Entry pairs = (Map.Entry)it.next();    
						   System.out.println(pairs.getKey() + " = " + pairs.getValue());    
					   }
					   if (mymap!=null) System.out.println("OK");

					   return new ModelAndView("predict-lab", "mymap", mymap);
				}else{
					return new ModelAndView("404");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			return new ModelAndView("predict-lab");
		}
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@RequestMapping(value="mrmd")
		public HashMap handleMRMD(String path, String labFile, Integer initial, Integer times,
				Integer subtractor,Integer maximum) {
			//String path = this.servletContext.getRealPath("/upload/");  //获取本地存储路径
			HashMap hashInfo = new FormatArff().createTrainTest(path, labFile);
			HashMap hashMap = new MRMD().getOptimalResults(path, initial, times, subtractor, maximum);
			HashMap hashMap2 = new HashMap<>();
			hashMap2.put("pos", hashInfo.get("pos"));
			hashMap2.put("neg", hashInfo.get("neg"));
			hashMap2.put("error", hashInfo.get("error"));
			hashMap2.put("feaNum", hashMap.get("feaNum"));
			hashMap2.put("bestAccuracy", hashMap.get("bestAccuracy"));
			hashMap2.put("bestFeaNum", hashMap.get("bestFeaNum"));
			return hashMap2;
			
		}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@RequestMapping(value="map")
		public ModelAndView handleHashMap() {
			Map map2 = new HashMap<>();
			map2.put("shixiang1", "1");
			map2.put("shixiang2", "2");
			map2.put("shixiang3", "3");
			map2.put("shixiang4", "4");
			map2.put("shixiang5", "5");
			map2.put("shixiang6", "6");
			map2.put("shixiang7", "7");
			map2.put("shixiang8", "8");
			map2.put("shixiang9", "9");
			return new ModelAndView("predict-lab", "mymap", map2);
		}
		
		
		/*@SuppressWarnings("rawtypes")
		public static void main(String[] args) {
			设置路径和蛋白质文件
			String path = "C:\\ShixiangWan\\workspace\\test\\WebRoot\\upload";
			String pathModel = "C:\\ShixiangWan\\workspace\\test\\WebRoot\\model";
			String tempName = "test.fasta";
			Map f = new testRF().run(path+"\\",tempName,"_test.arff",pathModel+"\\train.arff",pathModel+"\\cytoRF.model",2);
			Iterator it = f.entrySet().iterator();
			while (it.hasNext())
			{    
			        Map.Entry pairs = (Map.Entry)it.next();    
			        System.out.println(pairs.getKey() + " = " + pairs.getValue());    
			}
			if (f!=null) System.out.println("OK");
			
		}*/
		
		
}
