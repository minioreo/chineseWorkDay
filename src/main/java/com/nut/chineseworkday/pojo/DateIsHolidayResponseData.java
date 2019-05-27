package com.nut.chineseworkday.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class DateIsHolidayResponseData {
    @JSONField(name = "isHoliday")
    private boolean isHoliday;
    @JSONField(name = "relatedHolidayName")
    private String relatedHolidayName;

    public boolean getIsHoliday() {
        return isHoliday;
    }

    public String getRelatedHolidayName() {
        return relatedHolidayName;
    }

    public boolean isHoliday() {
        return isHoliday;
    }

    public void setHoliday(boolean holiday) {
        isHoliday = holiday;
    }

    public void setRelatedHolidayName(String relatedHolidayName) {
        this.relatedHolidayName = relatedHolidayName;
    }

    public static DateIsHolidayResponseData create(boolean isHoliday, String relatedHolidayName){
        DateIsHolidayResponseData response = new DateIsHolidayResponseData();
        response.isHoliday = isHoliday;
        response.relatedHolidayName = relatedHolidayName;
        return response;
    }

}
