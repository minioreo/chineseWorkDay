package com.nut.chineseworkday.pojo;

public class DayJudgeResult {
    private boolean isHoliday;
    private String relatedHolidayName;

    public boolean isHoliday() {
        return isHoliday;
    }

    public String getRelatedHolidayName() {
        return relatedHolidayName;
    }

    public DayJudgeResult(boolean isHoliday, String relatedHolidayName) {
        this.isHoliday = isHoliday;
        this.relatedHolidayName = relatedHolidayName;
    }
}
