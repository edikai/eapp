package com.ek.eapp.util;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class CodeUtilTest {

    @Test
    public void uuid() {
        String uuid = UUID.randomUUID().toString();
        System.out.println(StringUtils.replace(uuid, "-", ""));
    }

    @Test
    public void generateShort() {
        CodeUtil codeUtil = new CodeUtil();
        System.out.println(codeUtil.generateShort());
    }

    @Test
    public void generateLong() {
        CodeUtil codeUtil = new CodeUtil();
        System.out.println(codeUtil.generateLong());
    }
}