package com.sun.org.apache.xerces.internal.utils;

import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import jdk.xml.internal.JdkConstants;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class XMLLimitAnalyzer {
    private String entityEnd;
    private String entityStart;
    private final int[] values = new int[XMLSecurityManager.Limit.values().length];
    private final int[] totalValue = new int[XMLSecurityManager.Limit.values().length];
    private final String[] names = new String[XMLSecurityManager.Limit.values().length];
    private final Map<String, Integer>[] caches = new Map[XMLSecurityManager.Limit.values().length];

    public enum NameMap {
        ENTITY_EXPANSION_LIMIT(JdkConstants.SP_ENTITY_EXPANSION_LIMIT, JdkConstants.ENTITY_EXPANSION_LIMIT),
        MAX_OCCUR_NODE_LIMIT(JdkConstants.SP_MAX_OCCUR_LIMIT, JdkConstants.MAX_OCCUR_LIMIT),
        ELEMENT_ATTRIBUTE_LIMIT(JdkConstants.SP_ELEMENT_ATTRIBUTE_LIMIT, JdkConstants.ELEMENT_ATTRIBUTE_LIMIT);

        final String newName;
        final String oldName;

        NameMap(String str, String str2) {
            this.newName = str;
            this.oldName = str2;
        }

        public String getOldName(String str) {
            if (str.equals(this.newName)) {
                return this.oldName;
            }
            return null;
        }
    }

    public void addValue(int i, String str, int i2) {
        int iIntValue;
        if (i != XMLSecurityManager.Limit.ENTITY_EXPANSION_LIMIT.ordinal() && i != XMLSecurityManager.Limit.MAX_OCCUR_NODE_LIMIT.ordinal() && i != XMLSecurityManager.Limit.ELEMENT_ATTRIBUTE_LIMIT.ordinal()) {
            XMLSecurityManager.Limit limit = XMLSecurityManager.Limit.TOTAL_ENTITY_SIZE_LIMIT;
            if (i != limit.ordinal() && i != XMLSecurityManager.Limit.ENTITY_REPLACEMENT_LIMIT.ordinal()) {
                if (i == XMLSecurityManager.Limit.MAX_ELEMENT_DEPTH_LIMIT.ordinal() || i == XMLSecurityManager.Limit.MAX_NAME_LIMIT.ordinal()) {
                    this.values[i] = i2;
                    this.totalValue[i] = i2;
                    return;
                }
                Map<String, Integer> map = this.caches[i];
                if (map == null) {
                    map = new HashMap<>(10);
                    this.caches[i] = map;
                }
                if (map.containsKey(str)) {
                    iIntValue = map.get(str).intValue() + i2;
                    map.put(str, Integer.valueOf(iIntValue));
                } else {
                    map.put(str, Integer.valueOf(i2));
                    iIntValue = i2;
                }
                int[] iArr = this.values;
                if (iIntValue > iArr[i]) {
                    iArr[i] = iIntValue;
                    this.names[i] = str;
                }
                if (i == XMLSecurityManager.Limit.GENERAL_ENTITY_SIZE_LIMIT.ordinal() || i == XMLSecurityManager.Limit.PARAMETER_ENTITY_SIZE_LIMIT.ordinal()) {
                    int[] iArr2 = this.totalValue;
                    int iOrdinal = limit.ordinal();
                    iArr2[iOrdinal] = iArr2[iOrdinal] + i2;
                    return;
                }
                return;
            }
        }
        int[] iArr3 = this.totalValue;
        iArr3[i] = iArr3[i] + i2;
    }

    public void debugPrint(XMLSecurityManager xMLSecurityManager) {
        System.out.println(new Formatter().format("%30s %15s %15s %15s %30s", "Property", "Limit", "Total size", "Size", "Entity Name"));
        for (XMLSecurityManager.Limit limit : XMLSecurityManager.Limit.values()) {
            System.out.println(new Formatter().format("%30s %15d %15d %15d %30s", limit.name(), Integer.valueOf(xMLSecurityManager.getLimit(limit)), Integer.valueOf(this.totalValue[limit.ordinal()]), Integer.valueOf(this.values[limit.ordinal()]), this.names[limit.ordinal()]));
        }
    }

    public void endEntity(XMLSecurityManager.Limit limit, String str) {
        this.entityStart = "";
        Map<String, Integer> map = this.caches[limit.ordinal()];
        if (map != null) {
            map.remove(str);
        }
    }

    public int getTotalValue(XMLSecurityManager.Limit limit) {
        return this.totalValue[limit.ordinal()];
    }

    public int getValue(int i) {
        return i == XMLSecurityManager.Limit.ENTITY_REPLACEMENT_LIMIT.ordinal() ? this.totalValue[i] : this.values[i];
    }

    public int getValueByIndex(int i) {
        return this.values[i];
    }

    public boolean isTracking(String str) {
        String str2 = this.entityStart;
        if (str2 == null) {
            return false;
        }
        return str2.equals(str);
    }

    public void reset(XMLSecurityManager.Limit limit) {
        if (limit.ordinal() == XMLSecurityManager.Limit.TOTAL_ENTITY_SIZE_LIMIT.ordinal()) {
            this.totalValue[limit.ordinal()] = 0;
        } else if (limit.ordinal() == XMLSecurityManager.Limit.GENERAL_ENTITY_SIZE_LIMIT.ordinal()) {
            this.names[limit.ordinal()] = null;
            this.values[limit.ordinal()] = 0;
            this.caches[limit.ordinal()] = null;
            this.totalValue[limit.ordinal()] = 0;
        }
    }

    public void startEntity(String str) {
        this.entityStart = str;
    }

    public int getTotalValue(int i) {
        return this.totalValue[i];
    }

    public int getValue(XMLSecurityManager.Limit limit) {
        return getValue(limit.ordinal());
    }

    public void addValue(XMLSecurityManager.Limit limit, String str, int i) {
        addValue(limit.ordinal(), str, i);
    }
}
