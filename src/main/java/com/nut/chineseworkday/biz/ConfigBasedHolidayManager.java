package com.nut.chineseworkday.biz;

import com.nut.chineseworkday.pojo.DayJudgeResult;
import com.nut.chineseworkday.pojo.HolidayCache;

import java.util.Calendar;

import static java.util.Calendar.SUNDAY;

public class ConfigBasedHolidayManager implements HolidayManager {

    private HolidayConfig holidayConfig = new HolidayConfig();

    @Override
    public DayJudgeResult dayJudge(Calendar calendar) {
        HolidayCache.DayInfo dayInfo = holidayConfig.getHolidayCache().judgeDay(calendar);
        if(dayInfo == null){
            return new DayJudgeResult(isHolidayByWeekDay(calendar),null);
        }
        if(dayInfo.isHoliday()){
            return new DayJudgeResult(true,dayInfo.getRelatedHolidayName());
        }else{
            return new DayJudgeResult(false,dayInfo.getRelatedHolidayName());
        }
    }

    private boolean isHolidayByWeekDay(Calendar calendar){
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return (dayOfWeek == Calendar.SATURDAY || dayOfWeek == SUNDAY);
    }
}
