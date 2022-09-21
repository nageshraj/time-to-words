package com.example.speakingclock.util;

import org.apache.commons.lang3.StringUtils;

import com.example.speakingclock.vo.SpeakingClockVO;

public class ClockTimeUtil {

	public static SpeakingClockVO convertGivenTimeToHoursAndMinutes(String timeString) {
		SpeakingClockVO speakingClockVO = new SpeakingClockVO();

		if (timeString != null && timeString.length() > 0 && StringUtils.isNotBlank(timeString)) {
			String[] splitStringArray = timeString.split(":");
			if (splitStringArray[0] != null && splitStringArray[1] != null) {
				try {
					speakingClockVO.setHourValue(Integer.valueOf(splitStringArray[0]));
					speakingClockVO.setMinuteValue(Integer.valueOf(splitStringArray[1]));
				} catch (NumberFormatException nme) {
					System.out.println("Number format exception");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

		return speakingClockVO;

	}

}
