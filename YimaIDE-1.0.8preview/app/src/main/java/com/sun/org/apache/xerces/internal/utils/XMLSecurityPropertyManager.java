package com.sun.org.apache.xerces.internal.utils;

import jdk.xml.internal.JdkConstants;
import jdk.xml.internal.SecuritySupport;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class XMLSecurityPropertyManager {
    private State[] states;
    private final String[] values;

    public enum Property {
        ACCESS_EXTERNAL_DTD("http://javax.xml.XMLConstants/property/accessExternalDTD", "all"),
        ACCESS_EXTERNAL_SCHEMA("http://javax.xml.XMLConstants/property/accessExternalSchema", "all");

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

        public String propertyName() {
            return this.name;
        }
    }

    public enum State {
        DEFAULT,
        FSP,
        JAXPDOTPROPERTIES,
        SYSTEMPROPERTY,
        APIPROPERTY
    }

    public XMLSecurityPropertyManager() {
        State state = State.DEFAULT;
        this.states = new State[]{state, state};
        this.values = new String[Property.values().length];
        for (Property property : Property.values()) {
            this.values[property.ordinal()] = property.defaultValue();
        }
        readSystemProperties();
    }

    private void getSystemProperty(Property property, String str) {
        try {
            String systemProperty = SecuritySupport.getSystemProperty(str);
            if (systemProperty != null) {
                this.values[property.ordinal()] = systemProperty;
                this.states[property.ordinal()] = State.SYSTEMPROPERTY;
                return;
            }
            String jAXPProperty = SecuritySupport.readJAXPProperty(str);
            if (jAXPProperty != null) {
                this.values[property.ordinal()] = jAXPProperty;
                this.states[property.ordinal()] = State.JAXPDOTPROPERTIES;
            }
        } catch (NumberFormatException unused) {
        }
    }

    private void readSystemProperties() {
        getSystemProperty(Property.ACCESS_EXTERNAL_DTD, JdkConstants.SP_ACCESS_EXTERNAL_DTD);
        getSystemProperty(Property.ACCESS_EXTERNAL_SCHEMA, JdkConstants.SP_ACCESS_EXTERNAL_SCHEMA);
    }

    public String find(String str) {
        for (Property property : Property.values()) {
            if (property.equalsName(str)) {
                return property.propertyName();
            }
        }
        return null;
    }

    public int getIndex(String str) {
        for (Property property : Property.values()) {
            if (property.equalsName(str)) {
                return property.ordinal();
            }
        }
        return -1;
    }

    public String getValue(String str) {
        int index = getIndex(str);
        if (index > -1) {
            return getValueByIndex(index);
        }
        return null;
    }

    public String getValueByIndex(int i) {
        return this.values[i];
    }

    public void setValue(Property property, State state, String str) {
        if (state.compareTo(this.states[property.ordinal()]) >= 0) {
            this.values[property.ordinal()] = str;
            this.states[property.ordinal()] = state;
        }
    }

    public String getValue(Property property) {
        return this.values[property.ordinal()];
    }

    public boolean setValue(String str, State state, Object obj) {
        int index = getIndex(str);
        if (index <= -1) {
            return false;
        }
        setValue(index, state, (String) obj);
        return true;
    }

    public void setValue(int i, State state, String str) {
        if (state.compareTo(this.states[i]) >= 0) {
            this.values[i] = str;
            this.states[i] = state;
        }
    }
}
