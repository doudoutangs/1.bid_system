package com.bid.common.utils;


import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author Administrator
 * @version 1.0
 * @Description
 */
public class RandomUtils {
    /**
     * UUID 生成id的分割线
     */
    private static final String RANDOM_SPLIT = "-";

    /**
     * 获得10位随机数
     *
     * @return
     */
    public static String randomString10() {
        String result = RandomStringUtils.randomNumeric(10);
        return result;
    }


    public static String randomString32() {
        return randomString36().replaceAll(RANDOM_SPLIT, "");
    }

    public static String randomString36() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获得时间戳长度的流水号，长度为17位
     *
     * @return
     */
    public static String getSerialNumber(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String serialNumber = sdf.format(new Date());
        return serialNumber;
    }


    /**
     * 获得时间戳长度的流水号，默认长度为26位
     *
     * @return
     */
    public static String getSerialNumber26(String name) {
        String result = RandomStringUtils.randomNumeric(9);
        return name + getSerialNumber("yyyyMMddHHmmssSSS") + result;
    }

    /**
     * 获得时间戳长度的流水号，默认长度为17位
     *
     * @return
     */
    public static String getSerialNumber18() {
        String result = RandomStringUtils.randomNumeric(10);
        return getSerialNumber("yyyyMMdd") + result;
    }

    public static String getSerialNumber10() {
        String result = RandomStringUtils.randomNumeric(10);
        return result;
    }

}
