package com.tarena.util;

public class ResponseInfo {
	private boolean accept;
	private String value;
	
	public ResponseInfo() {
		super();
	}
	public ResponseInfo(boolean accept, String value) {
		this.accept = accept;
		this.value = value;
	}
	public boolean isAccept() {
		return accept;
	}
	public void setAccept(boolean accept) {
		this.accept = accept;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
