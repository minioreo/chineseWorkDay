package com.nut.chineseworkday.pojo;

public class DateIsHolidayResponseData {
    private boolean isHoliday;
    private String holidayName;

    public boolean isHoliday() {
        return isHoliday;
    }

    public String getHolidayName() {
        return holidayName;
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
