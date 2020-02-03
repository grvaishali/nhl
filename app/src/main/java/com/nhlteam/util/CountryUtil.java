package com.nhlteam.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Gets the proper ISO code for country
 */
public class CountryUtil {

    private CountryUtil(){}
    private static Map<String, Locale> staticLocaleMap = initCountryCodeMapping();

    private static Map<String, Locale> initCountryCodeMapping() {
        String[] countries = Locale.getISOCountries();
        Map<String, Locale> localeMap = new HashMap<>(countries.length);
        for (String country : countries) {
            Locale locale = new Locale("", country);
            localeMap.put(locale.getISO3Country().toUpperCase(), locale);
        }
        return localeMap;
    }

    /**
     * Gets the 2 digit ISO code for 3 digit ISO code
     *
     * @param iso3CountryCode
     * @return 2 digit ISO code
     */
    public static String iso3CountryCodeToIso2CountryCode(String iso3CountryCode) {
        return staticLocaleMap.get(iso3CountryCode).getCountry();
    }

    /**
     * Gets the 3 digit ISO code for 2 digit ISO code
     *
     * @param iso2CountryCode
     * @return 3 digit ISO code
     */
    public static String iso2CountryCodeToIso3CountryCode(String iso2CountryCode) {
        Locale locale = new Locale("", iso2CountryCode);
        return locale.getISO3Country();
    }
}
