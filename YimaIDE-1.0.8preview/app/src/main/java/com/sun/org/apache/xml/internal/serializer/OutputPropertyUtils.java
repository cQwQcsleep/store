package com.sun.org.apache.xml.internal.serializer;

import java.util.Properties;
import jdk.xml.internal.JdkConstants;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class OutputPropertyUtils {
    public static boolean getBooleanProperty(String str, Properties properties) {
        String property = properties.getProperty(str);
        return property != null && property.equals(JdkConstants.JDK_YES);
    }

    public static int getIntProperty(String str, Properties properties) {
        String property = properties.getProperty(str);
        if (property == null) {
            return 0;
        }
        return Integer.parseInt(property);
    }
}
