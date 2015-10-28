package com.test.lab;
/*
 * ExtractFeature.java
 * 这是读取fasta源文件，调用sequence类提取特征并整理成arff文件的类，但是只能整理成某种类别的arff文件，要做进一步处理
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExtractFeature {

	/**
	 * 读取fasta序列文件
	 * @param filePath 系统运行根目录+用户目录
	 * @param fastaFile fasta文件
	 * @throws Exception
	 */
	public ArrayList<String> readFastaFile(String fastaFile, String filePath, String classValue) throws Exception {
		File file = new File(fastaFile);
		if (!file.exists()) {
			throw new Exception("fasta文件不存在！");
		}
		BufferedReader bReaderFasta = new BufferedReader(new FileReader(file));
		String line, name="";
		//用来保存序列信息
		StringBuffer sBuffer = new StringBuffer();
		//表明第几个序列
		int count = 0;
		//保存名字
		Map<String, Integer> seqName = new HashMap<String, Integer>();
		//用来保存提取特征数据
		ArrayList<String> featureList = new ArrayList<String>();
		while((line = bReaderFasta.readLine())!=null) {
			//跳过空行
			if (line.trim().length() == 0) {
				continue;
			}
			//如果碰到>，说明是名字
			if (line.startsWith(">")) {
				//说明是第一条
				if (name.equals("")) {
					name = line;
					continue;
				} else {//说明不是第一条，需要处理上一条内容
					System.out.println(name);
					seqName.put(name, count);
					Sequence sequence = new Sequence(sBuffer.toString());
					featureList.add(sequence.run()+classValue);
					//恢复环境
					name = line;
					sBuffer.delete(0, sBuffer.length());
					count++;
				}
			} else {//把不同行的序列添加进来
				sBuffer.append(line);
			}
		}
		seqName.put(name, count);
		Sequence sequence = new Sequence(sBuffer.toString());
		featureList.add(sequence.run()+classValue);
		bReaderFasta.close();
		//需要把名字文件先保存下
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath+"name.txt"));
		oos.writeObject(seqName);
		oos.flush();
		oos.close();
		return featureList;
	}
	
	/**
	 * 得到一个测试集的arff文件
	 * @param fastaFile fasta文件路径
	 * @param outputPath 输出文件路径
	 * @param outputFile 输出文件名
	 * @param classValue 类标
	 * @param featureNum 特征个数
	 * @param classNum 类别个数
	 * @throws Exception
	 */
	public boolean getTestArffFile(String fastaFile, String outputPath, String outputFile, String classValue, int featureNum, int classNum) throws Exception {
		try {
			ArrayList<String> featureList = readFastaFile(fastaFile, outputPath, classValue);
			BufferedWriter bWriterArff = new BufferedWriter(new FileWriter(outputPath+outputFile));
			/*写入文件头*/
			bWriterArff.write("@relation " + outputFile + "\n");
			/*写入特征名*/
			for (int i = 1; i <= featureNum; i++) {
				bWriterArff.write("@attribute feature" + i + " real");
				bWriterArff.newLine();
			}
			/*写入类标*/
			bWriterArff.write("@attribute class {0");
			for (int i = 1; i < classNum; i++) {
				bWriterArff.write(","+i);
			}
			bWriterArff.write("}");
			bWriterArff.newLine();
			bWriterArff.write("@data");
			bWriterArff.newLine();
			/*写入数据*/
			for (String seqdata : featureList) {
				bWriterArff.write(seqdata);
				bWriterArff.newLine();
			}
			bWriterArff.flush();
			bWriterArff.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	/**
	 * @param args
	 */
	/*public static void main(String[] args) throws Exception {
		ExtractFeature e = new ExtractFeature();
		e.getTestArffFile("pos.fasta", "C:\\ShixiangWan\\workspace\\MRMD\\upload\\", "_pos", "1", 188, 2);
		e.getTestArffFile("neg.fasta", "C:\\ShixiangWan\\workspace\\MRMD\\upload\\", "_neg", "0", 188, 2);
	}*/

}
