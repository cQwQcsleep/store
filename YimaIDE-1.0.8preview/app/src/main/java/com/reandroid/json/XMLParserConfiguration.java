package com.reandroid.json;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLParserConfiguration {
    private String cDataTagName;
    private boolean convertNilAttributeToNull;
    private boolean keepStrings;
    private Map<String, XMLXsiTypeConverter<?>> xsiTypeMap;
    public static final XMLParserConfiguration ORIGINAL = new XMLParserConfiguration();
    public static final XMLParserConfiguration KEEP_STRINGS = new XMLParserConfiguration().withKeepStrings(true);

    public XMLParserConfiguration() {
        this.keepStrings = false;
        this.cDataTagName = "content";
        this.convertNilAttributeToNull = false;
        this.xsiTypeMap = Collections.EMPTY_MAP;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public XMLParserConfiguration m70clone() {
        return new XMLParserConfiguration(this.keepStrings, this.cDataTagName, this.convertNilAttributeToNull, this.xsiTypeMap);
    }

    public Map<String, XMLXsiTypeConverter<?>> getXsiTypeMap() {
        return this.xsiTypeMap;
    }

    public String getcDataTagName() {
        return this.cDataTagName;
    }

    public boolean isConvertNilAttributeToNull() {
        return this.convertNilAttributeToNull;
    }

    public boolean isKeepStrings() {
        return this.keepStrings;
    }

    public XMLParserConfiguration withConvertNilAttributeToNull(boolean z) {
        XMLParserConfiguration xMLParserConfigurationM70clone = m70clone();
        xMLParserConfigurationM70clone.convertNilAttributeToNull = z;
        return xMLParserConfigurationM70clone;
    }

    public XMLParserConfiguration withKeepStrings(boolean z) {
        XMLParserConfiguration xMLParserConfigurationM70clone = m70clone();
        xMLParserConfigurationM70clone.keepStrings = z;
        return xMLParserConfigurationM70clone;
    }

    public XMLParserConfiguration withXsiTypeMap(Map<String, XMLXsiTypeConverter<?>> map) {
        XMLParserConfiguration xMLParserConfigurationM70clone = m70clone();
        xMLParserConfigurationM70clone.xsiTypeMap = Collections.unmodifiableMap(new HashMap(map));
        return xMLParserConfigurationM70clone;
    }

    public XMLParserConfiguration withcDataTagName(String str) {
        XMLParserConfiguration xMLParserConfigurationM70clone = m70clone();
        xMLParserConfigurationM70clone.cDataTagName = str;
        return xMLParserConfigurationM70clone;
    }

    @Deprecated
    public XMLParserConfiguration(boolean z) {
        this(z, "content", false);
    }

    @Deprecated
    public XMLParserConfiguration(String str) {
        this(false, str, false);
    }

    @Deprecated
    public XMLParserConfiguration(boolean z, String str) {
        this.keepStrings = z;
        this.cDataTagName = str;
        this.convertNilAttributeToNull = false;
    }

    @Deprecated
    public XMLParserConfiguration(boolean z, String str, boolean z2) {
        this.keepStrings = z;
        this.cDataTagName = str;
        this.convertNilAttributeToNull = z2;
    }

    private XMLParserConfiguration(boolean z, String str, boolean z2, Map<String, XMLXsiTypeConverter<?>> map) {
        this.keepStrings = z;
        this.cDataTagName = str;
        this.convertNilAttributeToNull = z2;
        this.xsiTypeMap = Collections.unmodifiableMap(map);
    }
}
