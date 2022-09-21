package com.example.speakingclock.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.speakingclock.constants.ClockConstants;
import com.example.speakingclock.service.SpeakingClockService;
import com.example.speakingclock.vo.SpeakingClockVO;

@Service
public class SpeakingClockServiceImpl implements SpeakingClockService {

	@SuppressWarnings("serial")
	private Map<Integer, String> tensValues = new HashMap<Integer, String>() {
		{
			put(1, "ten");
			put(2, "twenty");
			put(3, "thirty");
			put(4, "forty");
			put(5, "fifty");
		}
	};

	@SuppressWarnings("serial")
	private Map<Integer, String> onesValues = new HashMap<Integer, String>() {
		{
			put(1, "one");
			put(2, "two");
			put(3, "three");
			put(4, "four");
			put(5, "five");
			put(6, "six");
			put(7, "seven");
			put(8, "eight");
			put(9, "nine");
			put(00, "");
			put(0, "");
		}
	};

	@SuppressWarnings("serial")
	private Map<Integer, String> teensValues = new HashMap<Integer, String>() {
		{
			put(0, "ten");
			put(1, "eleven");
			put(2, "twelve");
			put(3, "tirtheen");
			put(4, "forteen");
			put(5, "fiftheen");
			put(6, "sixteen");
			put(7, "seventeen");
			put(8, "eighteen");
			put(9, "nineteen");
		}
	};

	@SuppressWarnings("serial")
	private Map<Integer, String> hoursWords = new HashMap<Integer, String>() {
		{
			put(0, "twelve");
			put(1, "one");
			put(2, "two");
			put(3, "three");
			put(4, "four");
			put(5, "five");
			put(6, "six");
			put(7, "seven");
			put(8, "eight");
			put(9, "nine");
			put(10, "ten");
			put(11, "eleven");

		}
	};

	@Override
	public String convertTimeToWords(SpeakingClockVO speakingClockVO) {

		StringBuilder stringBuilder = new StringBuilder();
		try {

			if (speakingClockVO.getHourValue() != null && speakingClockVO.getMinuteValue() != null) {

				// Midnight Condition
				if (speakingClockVO.getHourValue().intValue() == 0
						&& speakingClockVO.getMinuteValue().intValue() == 0) {
					stringBuilder.append("Its " + ClockConstants.midNight);
					System.out.println(stringBuilder.toString());
					return stringBuilder.toString();
				}

				// Midday Condition
				if (speakingClockVO.getHourValue().intValue() == 12
						&& speakingClockVO.getMinuteValue().intValue() == 0) {
					stringBuilder.append("Its " + ClockConstants.midDay);
					System.out.println(stringBuilder.toString());
					return stringBuilder.toString();

				}

				else {
					// Here the input needs to be divided further to fetch their hour and minute
					// values from map
					stringBuilder.append("Its ");
					fetchHourValueWord(speakingClockVO.getHourValue(), stringBuilder);
					fetchMinuteValueWord(speakingClockVO.getMinuteValue(), stringBuilder);
					fetchAMPMValues(speakingClockVO.getHourValue(), stringBuilder);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(stringBuilder.toString());

		return stringBuilder.toString();
	}

	public StringBuilder fetchHourValueWord(Integer hourValue, StringBuilder stringBuilder) {
		if (hourValue > 0 && hourValue < 24) {
			Integer hourValueInStandardClock = hourValue % 12;
			return stringBuilder.append(hoursWords.get(hourValueInStandardClock)).append(" ");

		}
		// Input is not valid
		return stringBuilder;
	}

	public StringBuilder fetchMinuteValueWord(Integer minuteValue, StringBuilder stringBuilder) {
		if (minuteValue > 0 && minuteValue < 10) {
			return stringBuilder.append(onesValues.get(minuteValue));
		}
		if (minuteValue >= 10 && minuteValue < 20) {
			Integer minuteValueInStandardClock = minuteValue % 10;
			return stringBuilder.append(teensValues.get(minuteValueInStandardClock));
		} else {
			Integer minuteValueInStandardClock = minuteValue / 10;
			stringBuilder.append(tensValues.get(minuteValueInStandardClock)).append(" ");
			stringBuilder.append(onesValues.get(minuteValue % 10));
		}

		// Input is not valid
		return stringBuilder;
	}

	public StringBuilder fetchAMPMValues(Integer hourValue, StringBuilder stringBuilder) {
		if (hourValue > 12)
			stringBuilder.append(" PM");
		else
			stringBuilder.append(" AM");

		return stringBuilder;
	}

}
