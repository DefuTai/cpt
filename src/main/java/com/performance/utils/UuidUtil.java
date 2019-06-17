package com.performance.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * @author lianyu on 2018/10/15 16:54
 **/
public class UuidUtil {

    private static final int ID_LENGTH = 32;
    private static final int INT_BIT = 8;
    private static final int STR_BIT = 8;
    private static final int SHORT_BIT = 4;
    private static final int JVM_BIT = 8;
    private static final int HI = 32;
    private static short counter = 0;
    private static final int JVM = (int) (System.currentTimeMillis() >>> 8);

    public UuidUtil() {
    }

    protected static int getJVM() {
        return JVM;
    }

    protected static short getCount() {
        Class var0 = UuidUtil.class;
        synchronized (UuidUtil.class) {
            if (counter < 0) {
                counter = 0;
            }

            return counter++;
        }
    }

    protected static short getHiTime() {
        return (short) ((int) (System.currentTimeMillis() >>> 32));
    }

    protected static int getLoTime() {
        return (int) System.currentTimeMillis();
    }

    protected static String format(int intval) {
        String formatted = Integer.toHexString(intval);
        StringBuilder buf = new StringBuilder("00000000");
        buf.replace(8 - formatted.length(), 8, formatted);
        return buf.toString();
    }

    protected static String format(short shortval) {
        String formatted = Integer.toHexString(shortval);
        StringBuilder buf = new StringBuilder("0000");
        buf.replace(4 - formatted.length(), 4, formatted);
        return buf.toString();
    }

    public static String generate(String entityId) {
        return StringUtils.isNotBlank(entityId) ? (new StringBuilder(32)).append(format(entityId)).append(format(getJVM())).append(format(getHiTime())).append(format(getLoTime())).append(format(getCount())).toString() : getUUID();
    }

    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        return s.replaceAll("-", "");
    }

    protected static String format(String stringval) {
        if (stringval == null) {
            stringval = "";
        }

        stringval = stringval.length() > 8 ? stringval.substring(stringval.length() - 8, stringval.length()) : stringval;
        StringBuilder buf = new StringBuilder("00000000");
        buf.replace(8 - stringval.length(), 8, stringval);
        return buf.toString();
    }

    public static void main(String[] args) {
        System.out.println(generate("12345678"));
    }

}
