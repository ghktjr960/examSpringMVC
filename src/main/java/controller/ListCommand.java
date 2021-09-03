package controller;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ListCommand {
	
	@DateTimeFormat(pattern="yyyyMMdd")
	private Date from;
	@DateTimeFormat(pattern="yyyyMMdd")
	private Date to;

	public Date getFrom() {
		return from;
	}
	public void setFrom(Date from) {
		this.from = from;
	}
	public Date getTo() {
		return to;
	}
	public void setTo(Date to) {
		this.to = to;
	}
	
}
