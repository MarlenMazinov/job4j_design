package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        boolean booleanArg = true;
        char charArg = 'A';
        byte byteArg = 1;
        short shortArg = 100;
        int intArg = 32000;
        long longArg = 35000;
        float floatArg = 68000.00f;
        double doubleArg = 100000.05;
        LOG.debug("booleanArg : {}, charArg : {}, byteArg : {}, shortArg : {}, intArg : {}," +
                        " longArg : {}, floatArg : {}, doubleArg : {}",
                booleanArg, charArg, byteArg, shortArg, intArg, longArg, floatArg, doubleArg);
    }
}