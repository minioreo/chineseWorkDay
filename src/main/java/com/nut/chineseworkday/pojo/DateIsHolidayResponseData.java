package com.nut.chineseworkday.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class DateIsHolidayResponseData {
    @JSONField(name = "isHoliday")
    private boolean isHoliday;
    @JSONField(name = "holidayName")
    private String holidayName;

    public boolean getIsHoliday() {
        return isHoliday;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public boolean isHoliday() {
        return isHoliday;
    }

    public void setHoliday(boolean holiday) {
        isHoliday = holiday;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

    public static DateIsHolidayResponseData createHolidayResponse(String holidayName){
        DateIsHolidayResponseData response = new DateIsHolidayResponseData();
        response.isHoliday = true;
        response.holidayName = holidayName;
        return response;
    }

    public static DateIsHolidayResponseData createWorkdayResponse(){
        DateIsHolidayResponseData response = new DateIsHolidayResponseData();
        response.isHoliday = false;
        return response;
    }
}
