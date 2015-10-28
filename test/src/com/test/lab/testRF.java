package com.test.lab;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.test.lab.ExtractFeature;
import com.test.utils.checkSequence;
import com.test.utils.deleteOldTemp;



public class testRF {
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		/*设置路径和蛋白质文件*/
		String path = "C:\\ShixiangWan\\workspace\\test\\WebRoot\\upload\\";
		String file = "C:\\ShixiangWan\\workspace\\test\\WebRoot\\model\\test.fasta";
		String posOut = "_test.arff";
		String train = "C:\\ShixiangWan\\workspace\\test\\WebRoot\\model\\train.arff";
		String model = "C:\\ShixiangWan\\workspace\\test\\WebRoot\\model\\cytoRF.model";
		int dimen = 2;
		Map f = new testRF().run(path,file,posOut,train,model,dimen);
		Iterator it = f.entrySet().iterator();    
		while (it.hasNext())
		{    
		        Map.Entry pairs = (Map.Entry)it.next();    
		        System.out.println(pairs.getKey() + " = " + pairs.getValue());    
		}
		if (f!=null) System.out.println("OK");
	}
	
	/*
	 * path是实验文件路径，file是用户上传的实验序列文件，
	posOut是提取特征序列后的arff文件名称，train是我们准备好的train.arff文件，
	model是我们准备好的训练模型，dimen是用户的类标数目
	*/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map run(String path,String file, String posOut,String train,String model,int dimen) {
		try {
			//删除旧文件,保护用户实验文件
			new deleteOldTemp().clear(path, file);
			/*检查序列的正确性*/
			String errorMessage = "";  //记录错误信息
			boolean isAllRight = true;  //标记整个过程是否会有错误
			file = path+file;
			int testError = new checkSequence().checkIsRight(file);
			if (testError != 0) {
				isAllRight = false;
				if (testError == -1) {
					errorMessage = "The data is Empty, please input the test data.";
				} else {
					errorMessage = "The "+testError+"th protein sequence contains incorrect sequences or the " +
							"format of this sequence is wrong, please check it.";
				}
				Map error = new HashMap();
				error.put("error", errorMessage);
				return error;
			}
			System.out.println("序列正确，开始提取特征");
			/*提取特征，预测分类*/
			if (isAllRight) {
				/*提取特征*/
				/**********************需要修改的地方*****************************************/
				ExtractFeature feature = new ExtractFeature();
				boolean e1 = feature.getTestArffFile(file, path, posOut, "1", 188, dimen);
				if (!e1) return null;
				System.out.println("特征提取成功，构建预测");
				MainClassifier m = new MainClassifier();
				System.out.println("载入随机森林模型");
				m.setModlePath(model);
				System.out.println("作出预测");
				m.getAssembleResult(train, path+posOut, path+"results.csv", true);
				/*************************************************************************/
				//读取蛋白质名字,预测分类
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path+"name.txt"));
				Map<Integer, String> seqName = (Map<Integer, String>)ois.readObject();
				ois.close();
				return seqName;
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
