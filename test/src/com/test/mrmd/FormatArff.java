/*
 * 说明：由于直接处理得到的正反例文件过大，程序处理故障，本程序实现将大文件整合成正例和反例数目相等的文件，
 * 	        同时保留剩余的实例，并删除错误分类的实例。适合生成训练集和测试集。
 * 作者：万世想
 * 时间：2015-10-05
 * */
package com.test.mrmd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Random;

public class FormatArff {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashMap createTrainTest(String path, String uploadFile) {
		System.out.println(uploadFile);//输入文件名
		String m_pos = "0"; //正例标记
		String m_neg = "1"; //反例标记
		
		
		System.out.println(path+"\\"+uploadFile);
		String input_file_name = path+"\\"+uploadFile;
		
		/*删除实验旧文件*/
		File dirFile = new File(path+"\\");
		boolean flag = true;  
	    File[] files = dirFile.listFiles();  
	    for (int i = 0; i < files.length; i++) {  
	        if (files[i].isFile()) {
	        	String tempString1 = files[i].getAbsolutePath();
	        	String tempString2 = tempString1.substring(tempString1.lastIndexOf("\\")+1);
	        	if (!tempString2.equals(uploadFile) && !tempString2.equals("MRMD.jar")) {
	        		flag = deleteFile(files[i].getAbsolutePath());  
	        		if (!flag) break;
	        	}
	        }  
	    }
		
		String br_line1, br_line2, end;
		int pos = 0, neg = 0, error = 0;
		try {
			//Please input the fasta file.
			BufferedReader br = new BufferedReader(new FileReader(input_file_name));
			BufferedReader br_main = new BufferedReader(new FileReader(input_file_name));
			BufferedWriter bw_main = new BufferedWriter(new FileWriter(path+"\\train.arff", true));
			BufferedWriter bw_re = new BufferedWriter(new FileWriter(path+"\\test.arff", true));
			
			//1. Count the number of pos, neg and error.
			while (br.ready()) {
				br_line1 = br.readLine();
				if (br_line1 != null && br_line1.substring(0, 1).matches("[0-9]")) {
					end = br_line1.substring(br_line1.lastIndexOf(",") + 1, br_line1.length());
					if (end.equals(m_pos))
						pos++;
					else if (end.equals(m_neg))
						neg++;
					else
						error++;
				}
			}
			System.out.println("POS: " + pos);
			System.out.println("NEG: " + neg);
			System.out.println("ERROR: " + error);
			HashMap hashMap = new HashMap<>();
			hashMap.put("pos", pos);
			hashMap.put("neg", neg);
			hashMap.put("error", error);
			
			
			
			//2. Get the minus number of instances.
			//待选取的正例和反例的数目，如果此值大于文件中的正例或反例，将按照最小的值进行选取
			int num = Math.round((float)(Math.max(pos, neg)*0.66));
			int minNum = Math.min(Math.min(pos, neg), num);
			
			//3. Choose random instance of pos and neg.
			Random random = new Random();
			
			boolean r_pos[] = new boolean[pos];
			for (int i = 0; i < minNum;) {
				int temp = random.nextInt(pos);
				if (!r_pos[temp]) {
					r_pos[temp] = true;
					i++;
				}
			}
			
			boolean r_neg[] = new boolean[neg];
			for (int i = 0; i < minNum;) {
				int temp = random.nextInt(neg);
				if (!r_neg[temp]) {
					r_neg[temp] = true;
					i++;
				}
			}
			//4. Integrate the pos and neg.
			int i = 0, j = 0;
			while (br_main.ready()) {
				br_line2 = br_main.readLine();

				if (br_line2 != null) {
					if (!br_line2.substring(0, 1).matches("[0-9]")) {
						bw_main.write(br_line2 + "\n");
						bw_re.write(br_line2 + "\n");
					} else {
						end = br_line2.substring(br_line2.lastIndexOf(",") + 1, br_line2.length());
						if (end.equals(m_pos)) {
							if (r_pos[i] && i < pos) {
								bw_main.write(br_line2 + "\n");
							} else {
								bw_re.write(br_line2 + "\n");
							}
							i++;
						}
						if (end.equals(m_neg)) {
							if (r_neg[j] && j < neg) {
								bw_main.write(br_line2 + "\n");
							} else {
								bw_re.write(br_line2 + "\n");
							}
							j++;
						}
					}
				}
			}
			br.close();
			br_main.close();
			bw_main.close();
			bw_re.close();

			System.out.println("FormatArff OK!");
			return hashMap;
			
		} catch (Exception e) {
			System.out.println("FormatArff执行错误！");
			return null;
		}
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
