package com.nhlteam.util;

import android.content.Context;
import android.content.res.AssetManager;

import com.nhlteam.constant.NHLConstants;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import timber.log.Timber;

/**
 * Reads the property from configuration file
 */
public class ConfigUtil {
    private ConfigUtil(){}

    /**
     * Gets the property for key
     *
     * @param key
     * @param context
     * @return property value
     */
    public static String getProperty(String key, Context context) {
        Properties properties = new Properties();
        AssetManager assetManager = context.getAssets();
        try {
            InputStream inputStream = assetManager.open(NHLConstants.CONFIG_PROPERTIES);
            properties.load(inputStream);
            return properties.getProperty(key);
        } catch (IOException ioe) {
            Timber.e("Config properties", "getProperty: ", ioe);
            return null;
        }

    }
}
