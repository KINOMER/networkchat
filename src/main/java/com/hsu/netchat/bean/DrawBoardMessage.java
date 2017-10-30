package com.hsu.netchat.bean;

import java.io.Serializable;

public class DrawBoardMessage extends ChatMessage implements Serializable{

	private static final long serialVersionUID = 1L;
	private String lineColor;
	private String lineWidth;
	
	public String getLineColor() {
		return lineColor;
	}
	public void setLineColor(String lineColor) {
		this.lineColor = lineColor;
	}
	public String getLineWidth() {
		return lineWidth;
	}
	public void setLineWidth(String lineWidth) {
		this.lineWidth = lineWidth;
	}
}
