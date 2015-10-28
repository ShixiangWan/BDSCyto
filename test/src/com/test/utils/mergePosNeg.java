package com.test.utils;
/*输出文件严格保持无空行*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class mergePosNeg {
	public static void main(String[] args) {
		new mergePosNeg().merge("C:\\ShixiangWan\\workspace\\MRMD\\upload\\", "_pos.arff", "_neg.arff", "_all.arff");
	}
	
	public boolean merge(String path, String posFilename, String negFilename, String outFilename) {
		try {
			BufferedReader br1 = new BufferedReader(new FileReader(path+posFilename));
			BufferedWriter bw = new BufferedWriter(new FileWriter(path+outFilename, true));
			
			String br1_line;
			br1_line = br1.readLine();
			if (br1_line != null) bw.write(br1_line);
			while (br1.ready()) {
				try {
					br1_line = br1.readLine();
					if (br1_line != null) {
						bw.write("\n"+br1_line);
					}
				} catch (Exception e) {
					
				}
			}
			
			BufferedReader br2 = new BufferedReader(new FileReader(path+negFilename));
			String br2_line;
			while (br2.ready()) {
				try {
					br2_line = br2.readLine();
					if (br2_line != null && br2_line.substring(0, 1).matches("[0-9]")) {
						bw.write("\n"+br2_line);
					}
				} catch (Exception e) {
					
				}
			}
			br1.close();
			br2.close();
			bw.close();
			return true;
		} catch (Exception e) {
			
		}
		return false;
	}
}
