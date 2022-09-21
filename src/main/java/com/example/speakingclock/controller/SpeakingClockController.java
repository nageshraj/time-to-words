package com.example.speakingclock.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.speakingclock.service.SpeakingClockService;
import com.example.speakingclock.util.ClockTimeUtil;
import com.example.speakingclock.vo.SpeakingClockVO;

/**
 * @author Nagesh
 * @Date 22-09-2022
 * @Description Converts 24 Hour format time to words
 */

@RestController
@RequestMapping("/speaking_clock")
public class SpeakingClockController {

	@Autowired
	SpeakingClockService speakingClockService;

	@GetMapping("/time_in_words")
	public ResponseEntity<String> getTimeInWords(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String timeValue) throws Exception {
		System.out.println("Inside SpeakingClockController getTimeInWords");
		SpeakingClockVO speakingClockVO = new SpeakingClockVO();

		if (timeValue != null && timeValue.length() > 0 && StringUtils.isNotBlank(timeValue)) {
			try {
				speakingClockVO = ClockTimeUtil.convertGivenTimeToHoursAndMinutes(timeValue);

				if (speakingClockVO.getHourValue() != null && speakingClockVO.getMinuteValue() != null
						&& (speakingClockVO.getHourValue() < 0 || speakingClockVO.getHourValue() > 24)
						&& (speakingClockVO.getMinuteValue() < 0 || speakingClockVO.getMinuteValue() > 59))
					throw new Exception();
				else {
					String finalTimeString = speakingClockService.convertTimeToWords(speakingClockVO);
					return new ResponseEntity<String>(finalTimeString, HttpStatus.OK);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return new ResponseEntity<String>("Some Error has occured", HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
