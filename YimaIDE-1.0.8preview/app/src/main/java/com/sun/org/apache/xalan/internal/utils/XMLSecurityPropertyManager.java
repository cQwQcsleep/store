package com.sun.org.apache.xalan.internal.utils;

import javax.xml.XMLConstants;
import jdk.xml.internal.JdkConstants;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class XMLSecurityPropertyManager extends FeaturePropertyBase {

    public enum Property {
        ACCESS_EXTERNAL_DTD("http://javax.xml.XMLConstants/property/accessExternalDTD", "all"),
        ACCESS_EXTERNAL_STYLESHEET(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "all");

        final String defaultValue;
        final String name;

        Property(String str, String str2) {
            this.name = str;
            this.defaultValue = str2;
        }

        public String defaultValue() {
            return this.defaultValue;
        }

        public boolean equalsName(String str) {
            if (str == null) {
                return false;
            }
            return this.name.equals(str);
        }
    }

    public XMLSecurityPropertyManager() {
        this.values = new String[Property.values().length];
        for (Property property : Property.values()) {
            this.values[property.ordinal()] = property.defaultValue();
        }
        readSystemProperties();
    }

    private void readSystemProperties() {
        getSystemProperty(Property.ACCESS_EXTERNAL_DTD, JdkConstants.SP_ACCESS_EXTERNAL_DTD);
        getSystemProperty(Property.ACCESS_EXTERNAL_STYLESHEET, JdkConstants.SP_ACCESS_EXTERNAL_STYLESHEET);
    }

    @Override // com.sun.org.apache.xalan.internal.utils.FeaturePropertyBase
    public int getIndex(String str) {
        for (Property property : Property.values()) {
            if (property.equalsName(str)) {
                return property.ordinal();
            }
        }
        return -1;
    }
}
