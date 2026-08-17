package com.reandroid.arsc;

import java.io.InputStream;
import java.util.Properties;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ARSCLib {
    public static final String NAME_arsc_lib_version = "arsc_lib_version";
    private static Properties sProperties;

    public static String getDescription() {
        return getProperties().getProperty("lib.description", "Failed to load properties");
    }

    public static String getName() {
        return getProperties().getProperty("lib.name", "ARSCLib");
    }

    private static Properties getProperties() {
        if (sProperties == null) {
            sProperties = loadProperties();
        }
        return sProperties;
    }

    public static String getRepo() {
        return getProperties().getProperty("lib.repo", "https://github.com/REAndroid");
    }

    public static String getVersion() {
        return getProperties().getProperty("lib.version", "");
    }

    private static Properties loadProperties() {
        InputStream resourceAsStream = ARSCLib.class.getResourceAsStream("/arsclib.properties");
        Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);
        } catch (Exception unused) {
        }
        return properties;
    }
}
