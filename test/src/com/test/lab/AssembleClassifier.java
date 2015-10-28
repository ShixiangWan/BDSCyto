package com.test.lab;

import java.util.List;

import weka.classifiers.Classifier;
import weka.core.Capabilities;
import weka.core.Instance;
import weka.core.Instances;

/**
 * 
 * @author hsc (hushichang88@163.com)
 * @version 1.0
 * 
 */
public class AssembleClassifier implements Classifier {

	private Classifier[] classifiers;//分类器集合
	private int classifierNum = 100;//多少个分类器
	private int classNum;//多少个类别	
	private int classifierIndex = 0;//分类器指针
	public int getClassNum() {
		return classNum;
	}
	public void setClassNum(int classNum) {
		this.classNum = classNum;
	}
	public int getClassifierIndex() {
		return classifierIndex;
	}
	public void setClassifierIndex(int classifierIndex) {
		this.classifierIndex = classifierIndex;
	}
	/**
	 * 构造函数，顺便初始化分类器集合
	 * @param classifierNum 分类器个数
	 * @throws Exception
	 */
	public AssembleClassifier() throws Exception {
		classifiers = new Classifier[classifierNum];
	}
	/**
	 * 添加需要集成的分类器
	 * @param classifier 分类器名字
	 * @throws Exception
	 */
	public void addClassifier(Classifier classifier) throws Exception {
		if (classifierIndex + 1 >= classifierNum) {
			throw new Exception("超出分类器集成数目上限");
		}
		classifiers[classifierIndex++] = classifier;
	}
	
	/**
	 * 添加需要集成的分类器集合
	 * @param classifierList 分类器集合
	 * @throws Exception
	 */
	public void addClassifiers(List<Classifier> classifierList) throws Exception {
		for (int i = 0; i < classifierList.size(); i++) {
			addClassifier(classifierList.get(i));
		}
	}
	
	/**
	   * 注意这个方法是必须的！！！！
	   * @return String
	   */
	public String getRevision()
	{
		return("");
	}
	
	@Override
	public void buildClassifier(Instances instances) throws Exception {
		// TODO Auto-generated method stub
		if (classifierIndex <= 0) {
			throw new Exception("请至少选择一个分类器");
		}
		for (int i = 0; i < classifierIndex; i++) {
			classifiers[i].buildClassifier(instances);
		}
	}
	
	/**
	 * 获得分类结果以及概率，如果集成的话，返回的是平均概率
	 * @param instance	分类的数据样例
	 * @throws Exception
	 */
	public double[] distributionForInstance(Instance instance)throws Exception {
		double[] sum = new double[classNum];
		double[][] radio = new double[classifierIndex][classNum];
		for (int i = 0; i < classifierIndex; i++) {
			radio[i] = classifiers[i].distributionForInstance(instance);
		}
		
		for (int i = 0; i < classNum; i++) {
			for (int j = 0; j < classifierIndex; j++) {
				sum[i] += radio[j][i];
			}
			sum[i] /= classifierIndex; 
		}
		
		return sum;
	}
	
	/**
	 * 获取分类结果
	 * @param instance	分类的数据样例
	 * @return
	 * @throws Exception
	 */
	@Override
	public double classifyInstance(Instance instance) throws Exception {
		double[] sum = new double[classNum];
		
		for (int i = 0; i < classifierIndex; i++) {
			sum[(int)classifiers[i].classifyInstance(instance)] ++;
		}
		double k = 0, max = -1;
		for (int i = 0; i < sum.length; i++) {
			if (max < sum[i]) {
				max = sum[i];
				k = i;
			}
		}
		return k;
	}
	
	@Override
	public Capabilities getCapabilities() {
		// TODO Auto-generated method stub
		return null;
	}
}
