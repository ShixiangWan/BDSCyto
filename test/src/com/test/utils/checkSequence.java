package com.test.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class checkSequence {
	/*public static void main(String[] args) {
		try {
			int a = new checkSequence().checkIsRight("C:\\ShixiangWan\\workspace\\MRMD\\pos.fasta");
			System.out.println(a);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	/**
	 * 检查输入的数据是否合法，错误的信息有两种，一种是没有数据的，一种是数据里边包含 有错误的序列
	 * -1代表没有数据，0是正常，其他的代表错误的是第几条蛋白质
	 * @param filePath 保存的蛋白质序列文件
	 * @return
	 * @throws Exception
	 */
	public int checkIsRight(String filePath) throws Exception {
		//标记是第几条蛋白质序列
		int countNumber = 1;
		//先检查是否有数据
		File file = new File(filePath);
		if (file.length() == 0 || !file.exists()) {
			return -1;
		}
		//检查是不是蛋白质序列包含不合法序列
		BufferedReader bReader = new BufferedReader(new FileReader(file));
		String line = bReader.readLine();
		String seqLine = "";
		//判断第一条序列是否有名字
		if (!line.trim().startsWith(">")) {
			bReader.close();
			return countNumber;
		}
		while((line = bReader.readLine())!=null) {
			if (line == null || line.trim().length() == 0) {
				continue;
			}
			if (line.trim().startsWith(">")) {//处理上一条信息
				//判断是否超长或者超短
				if (seqLine.length() < 10 || seqLine.length() > 10000) {
					bReader.close();
					return countNumber;
				}
				//查看是否包含非法序列
				if (containBad(seqLine)) {
					bReader.close();
					return countNumber;
				}
				//开始下一条
				countNumber++;
				seqLine = "";
			} else {
				seqLine += line.trim();
			}
		}
		//处理最后一条
		if (seqLine.length() < 10 || seqLine.length() > 10000) {
			bReader.close();
			return countNumber;
		}
		//查看是否包含非法序列
		if (containBad(seqLine)) {
			bReader.close();
			return countNumber;
		}
		bReader.close();
		return 0;
	}
	
	//保存的蛋白质的核苷酸名字
	private ArrayList<String> baseList;
	public checkSequence() throws Exception {
		baseList = new ArrayList<String>();
		baseList.add("A");baseList.add("C");baseList.add("D");
		baseList.add("E");baseList.add("F");baseList.add("G");
		baseList.add("H");baseList.add("I");baseList.add("K");
		baseList.add("L");baseList.add("M");baseList.add("N");
		baseList.add("P");baseList.add("Q");baseList.add("R");
		baseList.add("S");baseList.add("T");baseList.add("V");
		baseList.add("W");baseList.add("Y");baseList.add(" ");
	}
	/**
	 * 检查是否有非法序列
	 * @param seq
	 * @return
	 * @throws Exception
	 */
	private boolean containBad(String seq) throws Exception {
		for (int i = 0; i < seq.length(); i++) {
			if (!baseList.contains(String.valueOf(seq.charAt(i)))) {
				return true;
			}
		}
		return false;
	}
	
	
	//专门用来清除临时文件的方法
    protected static void deleteTemp(File file)
    {
    	if(file.isDirectory())
    	{
    		File[] f=file.listFiles();
    		for(int i=0;i<f.length;++i)
    		{
    			deleteTemp(f[i]);
    		}
    	}
    	file.delete();
    }

}
