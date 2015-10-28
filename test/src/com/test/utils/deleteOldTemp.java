package com.test.utils;

import java.io.File;

public class deleteOldTemp {
	
	public boolean clear(String clearPath, String protectFile) {
		/*删除实验旧文件*/
		File dirFile = new File(clearPath);
		boolean flag;
	    File[] files = dirFile.listFiles();  
	    for (int i = 0; i < files.length; i++) {  
	        if (files[i].isFile()) {
	        	String tempString1 = files[i].getAbsolutePath();
	        	String tempString2 = tempString1.substring(tempString1.lastIndexOf("\\")+1);
	        	if (!tempString2.equals(protectFile) && !tempString2.equals("MRMD.jar")) {
	        		flag = deleteFile(files[i].getAbsolutePath());  
	        		if (!flag) {
	        			break;
	        		}
	        	}
	        }  
	    }
	    return true;
	}
	
	/** 
	 * 删除单个文件 
	 * @param   sPath    被删除文件的文件名 
	 * @return 单个文件删除成功返回true，否则返回false 
	 */  
	public boolean deleteFile(String sPath) {  
	    boolean flag = false;  
	    File file = new File(sPath);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true;  
	    }  
	    return flag;  
	}  
}
