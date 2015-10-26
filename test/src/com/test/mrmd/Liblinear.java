package com.test.mrmd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.LibLINEAR;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

public class Liblinear {
	
	public double getLiblinearResults (String path, String train, String test) {
		//本次精确度
		double accuracy = 0.0;
		
		try {
			LibLINEAR c1 = new LibLINEAR();
			
			//* String[] options=weka.core.Utils.splitOptions(
			//* "-S 1 -C 1.0 -E 0.001 -B 0"); c1.setOptions(options);

			ArffLoader atf = new ArffLoader();
			File TraininputFile = new File(train);
			atf.setFile(TraininputFile);// 训练语料文件
			Instances instancesTrain = atf.getDataSet(); // 读入训练文件
			instancesTrain.setClassIndex(instancesTrain.numAttributes() - 1);

			File TestinputFile = new File(test);
			atf.setFile(TestinputFile);// 测试语料文件
			Instances instancesTest = atf.getDataSet(); // 读入测试文件
			// 设置分类属性所在行号（第一行为0号），instancesTest.numAttributes()可以取得属性总数
			instancesTest.setClassIndex(instancesTest.numAttributes() - 1); 

			c1.buildClassifier(instancesTrain); // 训练

			Evaluation eval = new Evaluation(instancesTrain);
			eval.evaluateModel(c1, instancesTest);
			// eval.crossValidateModel(c1, instancesTrain, 10, new
			// Random(1));
			File newfile = new File(path + "OutLiblinear_temp"+ ".txt");

			BufferedWriter bufferedWriter = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(newfile), "utf-8"));

			bufferedWriter.write(eval.toSummaryString() + "\r\n");
			bufferedWriter.write(eval.toClassDetailsString() + "\r\n");
			bufferedWriter.write(eval.toMatrixString() + "\r\n");

			bufferedWriter.flush();
			bufferedWriter.close();

			BufferedReader bufferedReader = new BufferedReader(
					new FileReader(newfile));
			String[] splitLineString = new String[5];
			while (bufferedReader.ready()) {
				bufferedReader.readLine();
				String lineString = bufferedReader.readLine();
				splitLineString = lineString.split(" ");
				System.out.println(splitLineString[4]);
				break;
			}
			bufferedReader.close();
			
			//求分类准确度
			String tempLine;
			BufferedReader tempBF = new BufferedReader(new FileReader(newfile));
			while (tempBF.ready()) {
				tempLine = tempBF.readLine();
				if (tempLine.contains("Correctly Classified Instances")) {
					tempLine = tempLine.substring(tempLine.lastIndexOf(".")-2, tempLine.lastIndexOf(" "));
					accuracy = Double.parseDouble(tempLine);
					break;
				}
			}
			
			tempBF.close();
			
		} catch (Exception e) {
			System.out.println("Can't run linlinear of weka.");
		}
		
		return accuracy;
	}
}
