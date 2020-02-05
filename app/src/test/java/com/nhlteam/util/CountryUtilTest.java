package com.nhlteam.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CountryUtilTest {

    @Test
    public void testISO2toISO3CountryCode() {

        //when
        String iso2CountryCode = CountryUtil.iso3CountryCodeToIso2CountryCode("CAN");

        //then
        Assert.assertEquals("CA", iso2CountryCode);
    }

    @Test
    public void testISO3toISO2CountryCode() {

        //when
        String iso3CountryCode = CountryUtil.iso2CountryCodeToIso3CountryCode("CA");

        //then
        Assert.assertEquals("CAN", iso3CountryCode);
    }

}

