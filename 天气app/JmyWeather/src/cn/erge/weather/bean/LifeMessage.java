package cn.erge.weather.bean;

public class LifeMessage {
	private int pictureId;
	private String prompAdvice;
	private String prompMessage;
	public int getPictureId() {
		return pictureId;
	}
	public void setPictureId(int pictureId) {
		this.pictureId = pictureId;
	}
	public String getPrompMessage() {
		return prompMessage;
	}
	public void setPrompMessage(String prompMessage) {
		this.prompMessage = prompMessage;
	}
	public String getPrompAdvice() {
		return prompAdvice;
	}
	public void setPrompAdvice(String prompAdvice) {
		this.prompAdvice = prompAdvice;
	}
	public LifeMessage(int pictureId, String prompAdvice,String prompMessage ) {
		super();
		this.pictureId = pictureId;
		this.prompAdvice = prompAdvice;
		this.prompMessage = prompMessage;
	}
	public LifeMessage() {
		super();
	}
	
}
