package com.test.utils;

import java.util.HashMap;
/*
 * 从weka产生的结果集文件中匹配找出特异性，敏感性和准确度值
 */
public class getDetailResults {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashMap getThreeParas(String resultStrings) {
		int k = 0;
        String sn = null, sp = null, acc = null;
        for (int i=0; i<resultStrings.length(); i++) {
        	if (resultStrings.substring(i, i+1).equals(".")) {
        		k++;
        		if (k==1) {
        			sn = resultStrings.substring(i-1, i+4);
        		} 
        		if (k==9) {
        			sp = resultStrings.substring(i-1, i+4);
        		}
        		if (k==18) {
        			acc = resultStrings.substring(i-1, i+4);
        		}
        	}
        }
		HashMap hashMap = new HashMap();
		hashMap.put("sn", sn);
		hashMap.put("sp", sp);
		hashMap.put("acc", acc);
		return hashMap;
	}
}
