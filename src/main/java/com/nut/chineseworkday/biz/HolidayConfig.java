package com.nut.chineseworkday.biz;

import com.alibaba.edas.acm.ConfigService;
import com.alibaba.edas.acm.exception.ConfigException;
import com.alibaba.edas.acm.listener.ConfigChangeListener;
import com.nut.chineseworkday.configuration.Config;
import com.nut.chineseworkday.configuration.PropertyConfig;
import com.nut.chineseworkday.pojo.HolidayCache;

import java.util.Properties;

class HolidayConfig {
    private HolidayCache holidayCache;

    HolidayCache getHolidayCache() {
        return holidayCache;
    }

    HolidayConfig() {
        Config acmConfig = new PropertyConfig("acm.properties");
        try {
            // 从控制台命名空间管理中拷贝对应值
            Properties properties = new Properties();
            properties.put("endpoint", acmConfig.getConfigStringValue("endpoint"));
            properties.put("namespace", acmConfig.getConfigStringValue("namespace"));
            properties.put("accessKey", acmConfig.getConfigStringValue("accessKey"));
            properties.put("secretKey", acmConfig.getConfigStringValue("secretKey"));
            ConfigService.init(properties);
            String dataId = acmConfig.getConfigStringValue("dataId");
            String group = acmConfig.getConfigStringValue("group");
            // 主动获取配置
            String config = ConfigService.getConfig(dataId, group, 6000);
            System.out.println("config receive from acm:" + config);

            // 初始化的时候，给配置添加监听，配置变更会回调通知
            ConfigService.addListener(dataId, group, new ConfigChangeListener() {
                public void receiveConfigInfo(String configInfo) {
                    // 当配置更新后，通过该回调函数将最新值返回给用户。
                    // 注意回调函数中不要做阻塞操作，否则阻塞通知线程。
                    System.out.println("config change event:" + configInfo);
                    holidayCache = HolidayCache.load(configInfo);
                }
            });
            holidayCache = HolidayCache.load(config);
        } catch (ConfigException e) {
            e.printStackTrace();
        }
    }

}
