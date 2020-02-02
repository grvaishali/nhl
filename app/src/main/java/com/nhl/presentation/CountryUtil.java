package com.nhl.presentation;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CountryUtil {
    private static Map<String, Locale> staticLocaleMap = initCountryCodeMapping();

    private static Map<String, Locale> initCountryCodeMapping() {
        String[] countries = Locale.getISOCountries();
        Map<String, Locale>  localeMap = new HashMap<>(countries.length);
        for (String country : countries) {
            Locale locale = new Locale("", country);
            localeMap.put(locale.getISO3Country().toUpperCase(), locale);
        }
        return localeMap;
    }
    public static String iso3CountryCodeToIso2CountryCode(String iso3CountryCode) {
        return staticLocaleMap.get(iso3CountryCode).getCountry();
    }

    public static String iso2CountryCodeToIso3CountryCode(String iso2CountryCode){
        Locale locale = new Locale("", iso2CountryCode);
        return locale.getISO3Country();
    }
}
