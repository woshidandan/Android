package cn.erge.weather.bean;

public class JBaseEntity {
	private String code;
	private String cmd;
	private String msg;
	
	public JBaseEntity(String code, String cmd, String msg) {
		this.code = code;
		this.cmd = cmd;
		this.msg = msg;
	}
	
	public JBaseEntity() {
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "JBaseEntity [code=" + code + ", cmd=" + cmd + ", msg=" + msg
				+ "]";
	}
	

}
