package com.sun.org.apache.xerces.internal.utils;

import com.sun.jna.platform.win32.WinError;
import com.sun.org.apache.xerces.internal.util.SecurityManager;
import java.util.concurrent.CopyOnWriteArrayList;
import jdk.xml.internal.JdkConstants;
import jdk.xml.internal.JdkProperty;
import jdk.xml.internal.SecuritySupport;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class XMLSecurityManager {
    private static final int NO_LIMIT = 0;
    private static final CopyOnWriteArrayList<String> printedWarnings = new CopyOnWriteArrayList<>();
    private final int indexEntityCountInfo;
    private boolean[] isSet;
    private String printEntityCountInfo;
    boolean secureProcessing;
    private JdkProperty.State[] states;
    private final int[] values;

    public enum Limit {
        ENTITY_EXPANSION_LIMIT("EntityExpansionLimit", JdkConstants.JDK_ENTITY_EXPANSION_LIMIT, JdkConstants.SP_ENTITY_EXPANSION_LIMIT, 0, 64000),
        MAX_OCCUR_NODE_LIMIT("MaxOccurLimit", JdkConstants.JDK_MAX_OCCUR_LIMIT, JdkConstants.SP_MAX_OCCUR_LIMIT, 0, 5000),
        ELEMENT_ATTRIBUTE_LIMIT("ElementAttributeLimit", JdkConstants.JDK_ELEMENT_ATTRIBUTE_LIMIT, JdkConstants.SP_ELEMENT_ATTRIBUTE_LIMIT, 0, WinError.WSABASEERR),
        TOTAL_ENTITY_SIZE_LIMIT("TotalEntitySizeLimit", JdkConstants.JDK_TOTAL_ENTITY_SIZE_LIMIT, JdkConstants.SP_TOTAL_ENTITY_SIZE_LIMIT, 0, 50000000),
        GENERAL_ENTITY_SIZE_LIMIT("MaxEntitySizeLimit", JdkConstants.JDK_GENERAL_ENTITY_SIZE_LIMIT, JdkConstants.SP_GENERAL_ENTITY_SIZE_LIMIT, 0, 0),
        PARAMETER_ENTITY_SIZE_LIMIT("MaxEntitySizeLimit", JdkConstants.JDK_PARAMETER_ENTITY_SIZE_LIMIT, JdkConstants.SP_PARAMETER_ENTITY_SIZE_LIMIT, 0, 1000000),
        MAX_ELEMENT_DEPTH_LIMIT("MaxElementDepthLimit", JdkConstants.JDK_MAX_ELEMENT_DEPTH, JdkConstants.SP_MAX_ELEMENT_DEPTH, 0, 0),
        MAX_NAME_LIMIT("MaxXMLNameLimit", JdkConstants.JDK_XML_NAME_LIMIT, JdkConstants.SP_XML_NAME_LIMIT, 1000, 1000),
        ENTITY_REPLACEMENT_LIMIT("EntityReplacementLimit", JdkConstants.JDK_ENTITY_REPLACEMENT_LIMIT, JdkConstants.SP_ENTITY_REPLACEMENT_LIMIT, 0, 3000000);

        final String apiProperty;
        final int defaultValue;
        final String key;
        final int secureValue;
        final String systemProperty;

        Limit(String str, String str2, String str3, int i, int i2) {
            this.key = str;
            this.apiProperty = str2;
            this.systemProperty = str3;
            this.defaultValue = i;
            this.secureValue = i2;
        }

        public String apiProperty() {
            return this.apiProperty;
        }

        public int defaultValue() {
            return this.defaultValue;
        }

        public JdkProperty.State getState(String str) {
            String str2 = this.systemProperty;
            if (str2 != null && str2.equals(str)) {
                return JdkProperty.State.APIPROPERTY;
            }
            if (this.apiProperty.equals(str)) {
                return JdkProperty.State.LEGACY_APIPROPERTY;
            }
            return null;
        }

        public boolean is(String str) {
            String str2 = this.systemProperty;
            return (str2 != null && str2.equals(str)) || this.apiProperty.equals(str);
        }

        public String key() {
            return this.key;
        }

        public int secureValue() {
            return this.secureValue;
        }

        public String systemProperty() {
            return this.systemProperty;
        }
    }

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

    public XMLSecurityManager(boolean z) {
        this.indexEntityCountInfo = WinError.WSABASEERR;
        this.printEntityCountInfo = "";
        this.values = new int[Limit.values().length];
        this.states = new JdkProperty.State[Limit.values().length];
        this.isSet = new boolean[Limit.values().length];
        this.secureProcessing = z;
        for (Limit limit : Limit.values()) {
            int[] iArr = this.values;
            if (z) {
                iArr[limit.ordinal()] = limit.secureValue;
                this.states[limit.ordinal()] = JdkProperty.State.FSP;
            } else {
                iArr[limit.ordinal()] = limit.defaultValue();
                this.states[limit.ordinal()] = JdkProperty.State.DEFAULT;
            }
        }
        readSystemProperties();
    }

    public static XMLSecurityManager convert(Object obj, XMLSecurityManager xMLSecurityManager) {
        if (obj == null) {
            return xMLSecurityManager == null ? new XMLSecurityManager(true) : xMLSecurityManager;
        }
        if (obj instanceof XMLSecurityManager) {
            return (XMLSecurityManager) obj;
        }
        if (xMLSecurityManager == null) {
            xMLSecurityManager = new XMLSecurityManager(true);
        }
        if (obj instanceof SecurityManager) {
            SecurityManager securityManager = (SecurityManager) obj;
            Limit limit = Limit.MAX_OCCUR_NODE_LIMIT;
            JdkProperty.State state = JdkProperty.State.APIPROPERTY;
            xMLSecurityManager.setLimit(limit, state, securityManager.getMaxOccurNodeLimit());
            xMLSecurityManager.setLimit(Limit.ENTITY_EXPANSION_LIMIT, state, securityManager.getEntityExpansionLimit());
            xMLSecurityManager.setLimit(Limit.ELEMENT_ATTRIBUTE_LIMIT, state, securityManager.getElementAttrLimit());
        }
        return xMLSecurityManager;
    }

    private boolean getSystemProperty(Limit limit, String str) {
        try {
            String systemProperty = SecuritySupport.getSystemProperty(str);
            if (systemProperty != null && !systemProperty.equals("")) {
                this.values[limit.ordinal()] = Integer.parseInt(systemProperty);
                this.states[limit.ordinal()] = JdkProperty.State.SYSTEMPROPERTY;
                return true;
            }
            String jAXPProperty = SecuritySupport.readJAXPProperty(str);
            if (jAXPProperty == null || jAXPProperty.equals("")) {
                return false;
            }
            this.values[limit.ordinal()] = Integer.parseInt(jAXPProperty);
            this.states[limit.ordinal()] = JdkProperty.State.JAXPDOTPROPERTIES;
            return true;
        } catch (NumberFormatException unused) {
            s01.a("Invalid setting for system property: ", limit.systemProperty());
            return false;
        }
    }

    public static void printWarning(String str, String str2, SAXException sAXException) {
        if (printedWarnings.addIfAbsent(str + ":" + str2)) {
            System.err.println("Warning: " + str + ": " + sAXException.getMessage());
        }
    }

    private void readSystemProperties() {
        for (Limit limit : Limit.values()) {
            if (!getSystemProperty(limit, limit.systemProperty())) {
                for (NameMap nameMap : NameMap.values()) {
                    String oldName = nameMap.getOldName(limit.systemProperty());
                    if (oldName != null) {
                        getSystemProperty(limit, oldName);
                    }
                }
            }
        }
    }

    public void debugPrint(XMLLimitAnalyzer xMLLimitAnalyzer) {
        if (this.printEntityCountInfo.equals(JdkConstants.JDK_YES)) {
            xMLLimitAnalyzer.debugPrint(this);
        }
    }

    public String find(String str) {
        for (Limit limit : Limit.values()) {
            if (limit.is(str)) {
                return limit.systemProperty();
            }
        }
        JdkProperty.ImplPropMap implPropMap = JdkProperty.ImplPropMap.ENTITYCOUNT;
        if (implPropMap.is(str)) {
            return implPropMap.qName();
        }
        return null;
    }

    public int getIndex(String str) {
        for (Limit limit : Limit.values()) {
            if (limit.is(str)) {
                return limit.ordinal();
            }
        }
        if (JdkProperty.ImplPropMap.ENTITYCOUNT.is(str)) {
            return WinError.WSABASEERR;
        }
        return -1;
    }

    public int getLimit(Limit limit) {
        return this.values[limit.ordinal()];
    }

    public String getLimitAsString(String str) {
        int index = getIndex(str);
        if (index > -1) {
            return getLimitValueByIndex(index);
        }
        return null;
    }

    public String getLimitValueAsString(Limit limit) {
        return Integer.toString(this.values[limit.ordinal()]);
    }

    public String getLimitValueByIndex(int i) {
        return i == 10000 ? this.printEntityCountInfo : Integer.toString(this.values[i]);
    }

    public JdkProperty.State getState(Limit limit) {
        return this.states[limit.ordinal()];
    }

    public String getStateLiteral(Limit limit) {
        return this.states[limit.ordinal()].literal();
    }

    public boolean isNoLimit(int i) {
        return i == 0;
    }

    public boolean isOverLimit(int i, XMLLimitAnalyzer xMLLimitAnalyzer) {
        if (this.values[i] == 0) {
            return false;
        }
        if (i == Limit.ELEMENT_ATTRIBUTE_LIMIT.ordinal() || i == Limit.ENTITY_EXPANSION_LIMIT.ordinal() || i == Limit.TOTAL_ENTITY_SIZE_LIMIT.ordinal() || i == Limit.ENTITY_REPLACEMENT_LIMIT.ordinal() || i == Limit.MAX_ELEMENT_DEPTH_LIMIT.ordinal() || i == Limit.MAX_NAME_LIMIT.ordinal()) {
            return xMLLimitAnalyzer.getTotalValue(i) > this.values[i];
        }
        return xMLLimitAnalyzer.getValue(i) > this.values[i];
    }

    public boolean isSecureProcessing() {
        return this.secureProcessing;
    }

    public boolean isSet(int i) {
        return this.isSet[i];
    }

    public boolean printEntityCountInfo() {
        return this.printEntityCountInfo.equals(JdkConstants.JDK_YES);
    }

    public void setLimit(int i, JdkProperty.State state, int i2) {
        if (i == 10000) {
            this.printEntityCountInfo = JdkConstants.JDK_YES;
        } else if (state.compareTo(this.states[i]) >= 0) {
            this.values[i] = i2;
            this.states[i] = state;
            this.isSet[i] = true;
        }
    }

    public void setSecureProcessing(boolean z) {
        this.secureProcessing = z;
        for (Limit limit : Limit.values()) {
            if (z) {
                setLimit(limit.ordinal(), JdkProperty.State.FSP, limit.secureValue());
            } else {
                setLimit(limit.ordinal(), JdkProperty.State.FSP, limit.defaultValue());
            }
        }
    }

    public void setLimit(Limit limit, JdkProperty.State state, int i) {
        setLimit(limit.ordinal(), state, i);
    }

    public void setLimit(int i, JdkProperty.State state, Object obj) {
        int iIntValue;
        if (i == 10000) {
            this.printEntityCountInfo = (String) obj;
            return;
        }
        if (obj instanceof Integer) {
            iIntValue = ((Integer) obj).intValue();
        } else {
            iIntValue = Integer.parseInt((String) obj);
            if (iIntValue < 0) {
                iIntValue = 0;
            }
        }
        setLimit(i, state, iIntValue);
    }

    public boolean setLimit(String str, JdkProperty.State state, Object obj) {
        int index = getIndex(str);
        if (index <= -1) {
            return false;
        }
        if (index != 10000 && state == JdkProperty.State.APIPROPERTY) {
            state = Limit.values()[index].getState(str);
        }
        setLimit(index, state, obj);
        return true;
    }

    public boolean isOverLimit(int i, String str, int i2, XMLLimitAnalyzer xMLLimitAnalyzer) {
        int i3 = this.values[i];
        if (i3 == 0 || i2 <= i3) {
            return false;
        }
        xMLLimitAnalyzer.addValue(i, str, i2);
        return true;
    }

    public boolean isOverLimit(Limit limit, XMLLimitAnalyzer xMLLimitAnalyzer) {
        return isOverLimit(limit.ordinal(), xMLLimitAnalyzer);
    }

    public boolean isOverLimit(Limit limit, String str, int i, XMLLimitAnalyzer xMLLimitAnalyzer) {
        return isOverLimit(limit.ordinal(), str, i, xMLLimitAnalyzer);
    }

    public XMLSecurityManager() {
        this(false);
    }
}
