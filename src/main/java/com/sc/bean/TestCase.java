package com.sc.bean;

import com.github.crab2died.annotation.ExcelField;


public class TestCase {
	
	@ExcelField(title = "类型")
	private String type;
	
	@ExcelField(title = "地址")
	private String url;
	
	@ExcelField(title = "参数")
	private String params;
	
	@ExcelField(title = "头部",order=2)
	private String header;
	
	@ExcelField(title = "测试结果", order=1)
	private String result;
	
	@ExcelField(title = "检查点")
	private String checkP;
	
	@ExcelField(title = "关联")
	private String correlation;


	public String getCheckP() {
		return checkP;
	}

	public void setCheckP(String checkP) {
		this.checkP = checkP;
	}

	public String getCorrelation() {
		return correlation;
	}

	public void setCorrelation(String correlation) {
		this.correlation = correlation;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	@Override
	public String toString() {
		return "TestCase{" +
				"type='" + type + '\'' +
				", url='" + url + '\'' +
				", params='" + params + '\'' +
				", header='" + header + '\'' +
				", result='" + result + '\'' +
				", checkP='" + checkP + '\'' +
				", correlation='" + correlation + '\'' +
				'}';
	}
}
