package com.nut.chineseworkday.biz;

import com.nut.chineseworkday.pojo.DayJudgeResult;

import java.util.Calendar;

public interface HolidayManager {
    DayJudgeResult dayJudge(Calendar calendar);
}
