package com.example.speakingclock.vo;

public class SpeakingClockVO {

	Integer hourValue;
	Integer minuteValue;

	public Integer getHourValue() {
		return hourValue;
	}

	public void setHourValue(Integer hourValue) {
		this.hourValue = hourValue;
	}

	public Integer getMinuteValue() {
		return minuteValue;
	}

	public void setMinuteValue(Integer minuteValue) {
		this.minuteValue = minuteValue;
	}

	@Override
	public String toString() {
		return "SpeakingClockVO [hourValue=" + hourValue + ", minuteValue=" + minuteValue + "]";
	}

}
