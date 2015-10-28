package com.test.lab;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;

import weka.core.Instances;

public class MainClassifier {
	private String modlePath;
	private Object classifier2;
	private AssembleClassifier classifier;
	private boolean isHaveModel = false;
	private boolean generateMode = false;
	public Object getClassifier2() {
		return classifier2;
	}
	public void setClassifier2(Object classifier2) {
		this.classifier2 = classifier2;
	}
	public boolean isGenerateMode() {
		return generateMode;
	}
	public void setGenerateMode(boolean generateMode) {
		this.generateMode = generateMode;
	}
	public String getModlePath() {
		return modlePath;
	}
	public void setModlePath(String modlePath) {
		this.modlePath = modlePath;
		this.isHaveModel = true;
	}

	/**
	 * 构造函数
	 * @throws Exception
	 */
	public MainClassifier() throws Exception {
		this.classifier = new AssembleClassifier();
	}
	
	/**
	 * 构造函数
	 * @param classifier 集成分类器
	 * @throws Exception
	 */
	public MainClassifier(AssembleClassifier classifier) throws Exception {
		if (classifier == null) {
			this.classifier = new AssembleClassifier();
		} else {
			this.classifier = classifier;
		}
	}
	
	/**
	 * 用来查询预测值和概率
	 * @param preres 分类后的各个类别的准确率
	 * @param classNum 类别个数
	 * @return
	 */
	private double[] queryResult(double[] preres, int classNum) {
		double[] res=new double[2];
		double max = 0.0,pot = 0;
		for(int i = 0; i < classNum; ++i) {
			if(max<preres[i]) {
				max=preres[i];
				pot=i;
			}
		}
		res[0]=pot;
		res[1]=max;
		return res;
	}
	
	/**
	 * 交叉验证，获得训练数据的模型
	 * @param trainName 训练集路径
	 * @throws Exception
	 */
	private void getTrainModel(String trainName) throws Exception {
		if (isHaveModel) {
			File file = new File(getModlePath());
			if (!file.exists()) {
				throw new Exception("Model文件不存在！");
			}
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(getModlePath()));
			System.out.println(getModlePath());
			classifier2 = ois.readObject();
			ois.close();
		} else {
			
		}
		
	}
	
	/**
	 * 获取测试集的分类结果，分类平均概率集成
	 * @param testName 测试集路径
	 * @param outputName 输出文件的路径
	 * @throws Exception
	 */
	private void getTestResultByProbability(String testName, String outputName) throws Exception {
		File f = new File(testName);
		if (!f.exists()) {
			throw new Exception("测试集文件不存在！");
		}
		//读取测试集
		BufferedReader bReaderTest = new BufferedReader(new FileReader(f));
		Instances insTest = new Instances(bReaderTest);
		insTest.setClassIndex(insTest.numAttributes()-1);//最后一个属性是类别，必须得有这句
		
		BufferedWriter bWriterResult = new BufferedWriter(new FileWriter(outputName));
		
		double sum = insTest.numInstances();//测试语料实例数
		for (int i = 0; i < sum; i++) {
			//查询各个基分类器的分类概率，选取最高的为最终分类结果
			double[] res = queryResult(classifier.distributionForInstance(insTest.instance(i)), classifier.getClassNum());
			bWriterResult.write((i+1)+","+(int)res[0]);
			bWriterResult.newLine();
		}
		bWriterResult.flush();
		bWriterResult.close();
	}
	
	/**
	 * 集成分类器的入口，获取集成分类器的结果
	 * @param trainName 训练集路径
	 * @param testName 测试集路径
	 * @param outputName 输出结果路径
	 * @param byProbability 是否按照平均概率集成
	 * @throws Exception
	 */
	public void getAssembleResult(String trainName, String testName, String outputName, boolean byProbability) throws Exception {
		if (classifier.getClassifierIndex() <= 0 && !isHaveModel) {
			throw new Exception("初始化集成分类器失败，请添加至少一个基分类器");
		}
		//获取训练模型
		getTrainModel(trainName);
		//获取测试集分类结果
		getTestResultByProbability(testName, outputName);
	}
	
	/*public static void main(String[] args) throws Exception {
		MainClassifier m = new MainClassifier();
		m.setModlePath("cyto.model");
		m.getAssembleResult("train.arff", "_test.arff", "task.csv",true);
	}*/

}
