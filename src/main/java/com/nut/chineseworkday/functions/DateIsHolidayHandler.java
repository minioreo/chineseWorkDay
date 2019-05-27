package com.nut.chineseworkday.functions;

import com.aliyun.fc.runtime.Context;
import com.aliyun.fc.runtime.PojoRequestHandler;
import com.nut.chineseworkday.biz.ConfigBasedHolidayManager;
import com.nut.chineseworkday.biz.HolidayManager;
import com.nut.chineseworkday.pojo.DateIsHolidayRequest;
import com.nut.chineseworkday.pojo.DayJudgeResult;
import com.nut.chineseworkday.pojo.Response;
import com.nut.chineseworkday.pojo.DateIsHolidayResponseData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateIsHolidayHandler implements PojoRequestHandler<DateIsHolidayRequest, Response<DateIsHolidayResponseData>> {
    private HolidayManager holidayManager = new ConfigBasedHolidayManager();
    public Response<DateIsHolidayResponseData> handleRequest(DateIsHolidayRequest request, Context context) {
        String dateString = request.getDateString();
        if(dateString == null || dateString.trim().isEmpty()){
            return Response.createFailResponse("param invalid","param dateString required.");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            context.getLogger().error("fail to parse date:" + dateString);
            return Response.createFailResponse("DateFormat Error","correct dateFormat is 'yyyy/MM/dd'.");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        DayJudgeResult result = holidayManager.dayJudge(calendar);
        return Response.createSuccessResponse(DateIsHolidayResponseData.create(result.isHoliday(),result.getRelatedHolidayName()));
    }


}
