package com.test.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="tt_lab")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Lab {
	private Integer id;
	private String filename;
	private String filesize;
	private String fea_pcaac;
	private String fea_scaac;
	private String fea_optimal;
	private String fea_188d;
	private String cla_libd3c;
	private String cla_randomforest;
	private String cla_libsvm;
	private String cla_bagging;
	private String cla_ibk;
	private String ip;
	private String time;
	private String re_acc;
	private String re_sn;
	private String re_sp;
	private Integer rank;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilesize() {
		return filesize;
	}
	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}
	public String getFea_pcaac() {
		return fea_pcaac;
	}
	public void setFea_pcaac(String fea_pcaac) {
		this.fea_pcaac = fea_pcaac;
	}
	public String getFea_scaac() {
		return fea_scaac;
	}
	public void setFea_scaac(String fea_scaac) {
		this.fea_scaac = fea_scaac;
	}
	public String getFea_optimal() {
		return fea_optimal;
	}
	public void setFea_optimal(String fea_optimal) {
		this.fea_optimal = fea_optimal;
	}
	public String getFea_188d() {
		return fea_188d;
	}
	public void setFea_188d(String fea_188d) {
		this.fea_188d = fea_188d;
	}
	public String getCla_libd3c() {
		return cla_libd3c;
	}
	public void setCla_libd3c(String cla_libd3c) {
		this.cla_libd3c = cla_libd3c;
	}
	public String getCla_randomforest() {
		return cla_randomforest;
	}
	public void setCla_randomforest(String cla_randomforest) {
		this.cla_randomforest = cla_randomforest;
	}
	public String getCla_libsvm() {
		return cla_libsvm;
	}
	public void setCla_libsvm(String cla_libsvm) {
		this.cla_libsvm = cla_libsvm;
	}
	public String getCla_bagging() {
		return cla_bagging;
	}
	public void setCla_bagging(String cla_bagging) {
		this.cla_bagging = cla_bagging;
	}
	public String getCla_ibk() {
		return cla_ibk;
	}
	public void setCla_ibk(String cla_ibk) {
		this.cla_ibk = cla_ibk;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getRe_acc() {
		return re_acc;
	}
	public void setRe_acc(String re_acc) {
		this.re_acc = re_acc;
	}
	public String getRe_sn() {
		return re_sn;
	}
	public void setRe_sn(String re_sn) {
		this.re_sn = re_sn;
	}
	public String getRe_sp() {
		return re_sp;
	}
	public void setRe_sp(String re_sp) {
		this.re_sp = re_sp;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}

}