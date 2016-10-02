package cn.erge.jmyweather.entity;

public class Result {
private String reason;
private String result;
private String error_code;

public Result(String reason,  String error_code) {
	this.reason = reason;
	this.error_code = error_code;
}
public String getReason() {
	return reason;
}
public void setReason(String reason) {
	this.reason = reason;
}
public String getResult() {
	return result;
}
public void setResult(String result) {
	this.result = result;
}
public String getError_code() {
	return error_code;
}
public void setError_code(String error_code) {
	this.error_code = error_code;
}
}
