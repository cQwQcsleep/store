package com.sun.org.apache.xerces.internal.util;

import jdk.xml.internal.JdkConstants;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class SecurityManager {
    private static final int DEFAULT_ELEMENT_ATTRIBUTE_LIMIT = 10000;
    private static final int DEFAULT_ENTITY_EXPANSION_LIMIT = 64000;
    private static final int DEFAULT_MAX_OCCUR_NODE_LIMIT = 5000;
    private int entityExpansionLimit = DEFAULT_ENTITY_EXPANSION_LIMIT;
    private int maxOccurLimit = DEFAULT_MAX_OCCUR_NODE_LIMIT;
    private int fElementAttributeLimit = 10000;

    public SecurityManager() {
        readSystemProperties();
    }

    private void readSystemProperties() {
        try {
            String property = System.getProperty(JdkConstants.ENTITY_EXPANSION_LIMIT);
            if (property == null || property.equals("")) {
                this.entityExpansionLimit = DEFAULT_ENTITY_EXPANSION_LIMIT;
            } else {
                int i = Integer.parseInt(property);
                this.entityExpansionLimit = i;
                if (i < 0) {
                    this.entityExpansionLimit = DEFAULT_ENTITY_EXPANSION_LIMIT;
                }
            }
        } catch (Exception unused) {
        }
        try {
            String property2 = System.getProperty(JdkConstants.MAX_OCCUR_LIMIT);
            if (property2 == null || property2.equals("")) {
                this.maxOccurLimit = DEFAULT_MAX_OCCUR_NODE_LIMIT;
            } else {
                int i2 = Integer.parseInt(property2);
                this.maxOccurLimit = i2;
                if (i2 < 0) {
                    this.maxOccurLimit = DEFAULT_MAX_OCCUR_NODE_LIMIT;
                }
            }
        } catch (Exception unused2) {
        }
        try {
            String property3 = System.getProperty(JdkConstants.ELEMENT_ATTRIBUTE_LIMIT);
            if (property3 == null || property3.equals("")) {
                this.fElementAttributeLimit = 10000;
                return;
            }
            int i3 = Integer.parseInt(property3);
            this.fElementAttributeLimit = i3;
            if (i3 < 0) {
                this.fElementAttributeLimit = 10000;
            }
        } catch (Exception unused3) {
        }
    }

    public int getElementAttrLimit() {
        return this.fElementAttributeLimit;
    }

    public int getEntityExpansionLimit() {
        return this.entityExpansionLimit;
    }

    public int getMaxOccurNodeLimit() {
        return this.maxOccurLimit;
    }

    public void setElementAttrLimit(int i) {
        this.fElementAttributeLimit = i;
    }

    public void setEntityExpansionLimit(int i) {
        this.entityExpansionLimit = i;
    }

    public void setMaxOccurNodeLimit(int i) {
        this.maxOccurLimit = i;
    }
}
