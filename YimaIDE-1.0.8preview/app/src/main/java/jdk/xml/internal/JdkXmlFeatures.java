package jdk.xml.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class JdkXmlFeatures {
    public static final String CATALOG_FEATURES = "javax.xml.catalog.catalogFeatures";
    public static final String ORACLE_JAXP_PROPERTY_PREFIX = "http://www.oracle.com/xml/jaxp/properties/";
    public static final String PROPERTY_USE_CATALOG = "http://javax.xml.XMLConstants/feature/useCatalog";
    public static final String XML_FEATURE_MANAGER = "http://www.oracle.com/xml/jaxp/properties/XmlFeatureManager";
    boolean secureProcessing;
    private final boolean[] featureValues = new boolean[XmlFeature.values().length];
    private final JdkProperty.State[] states = new JdkProperty.State[XmlFeature.values().length];

    public enum XmlFeature {
        ENABLE_EXTENSION_FUNCTION(JdkProperty.ImplPropMap.ENABLEEXTFUNC, null, null, true, null, null, true, false, true, true),
        USE_CATALOG(null, "http://javax.xml.XMLConstants/feature/useCatalog", JdkXmlUtils.SP_USE_CATALOG, false, null, null, true, false, true, false),
        RESET_SYMBOL_TABLE_FEATURE(JdkProperty.ImplPropMap.RESETSYMBOLTABLE, null, null, false, null, null, false, false, true, false),
        JDK_OVERRIDE_PARSER(JdkProperty.ImplPropMap.OVERRIDEPARSER, null, null, false, null, null, false, false, true, false);

        private final boolean differ;
        private final boolean enforced;
        private final boolean hasSystem;
        private final JdkProperty.ImplPropMap implMap;
        private final String name;
        private final String nameOld;
        private final String nameOldSP;
        private final String nameSP;
        private final boolean valueDefault;
        private final boolean valueEnforced;

        XmlFeature(JdkProperty.ImplPropMap implPropMap, String str, String str2, boolean z, String str3, String str4, boolean z2, boolean z3, boolean z4, boolean z5) {
            this.implMap = implPropMap;
            if (implPropMap != null) {
                this.name = implPropMap.qName();
                this.nameSP = implPropMap.systemProperty();
                this.nameOld = implPropMap.qNameOld();
                this.nameOldSP = implPropMap.systemPropertyOld();
            } else {
                this.name = str;
                this.nameSP = str2;
                this.nameOld = str3;
                this.nameOldSP = str4;
            }
            this.differ = z;
            this.valueDefault = z2;
            this.valueEnforced = z3;
            this.hasSystem = z4;
            this.enforced = z5;
        }

        public String apiProperty() {
            return this.name;
        }

        public boolean defaultValue() {
            return this.valueDefault;
        }

        public boolean enforced() {
            return this.enforced;
        }

        public boolean enforcedValue() {
            return this.valueEnforced;
        }

        public boolean equalsPropertyName(String str) {
            JdkProperty.ImplPropMap implPropMap = this.implMap;
            if (implPropMap != null) {
                return implPropMap.is(str);
            }
            if (this.name.equals(str)) {
                return true;
            }
            String str2 = this.nameOld;
            return str2 != null && str2.equals(str);
        }

        public JdkProperty.State getState(String str) {
            JdkProperty.ImplPropMap implPropMap = this.implMap;
            if (implPropMap != null) {
                return implPropMap.getState(str);
            }
            if (this.name.equals(str)) {
                return JdkProperty.State.APIPROPERTY;
            }
            return null;
        }

        public boolean hasSystemProperty() {
            return this.hasSystem;
        }

        public String systemProperty() {
            return this.nameSP;
        }

        public String systemPropertyOld() {
            return this.nameOldSP;
        }
    }

    public JdkXmlFeatures(boolean z) {
        this.secureProcessing = z;
        for (XmlFeature xmlFeature : XmlFeature.values()) {
            if (z && xmlFeature.enforced()) {
                this.featureValues[xmlFeature.ordinal()] = xmlFeature.enforcedValue();
                this.states[xmlFeature.ordinal()] = JdkProperty.State.FSP;
            } else {
                this.featureValues[xmlFeature.ordinal()] = xmlFeature.defaultValue();
                this.states[xmlFeature.ordinal()] = JdkProperty.State.DEFAULT;
            }
        }
        readSystemProperties();
    }

    private boolean getSystemProperty(XmlFeature xmlFeature, String str) {
        try {
            String systemProperty = SecuritySupport.getSystemProperty(str);
            if (systemProperty != null && !systemProperty.isEmpty()) {
                setFeature(xmlFeature, JdkProperty.State.SYSTEMPROPERTY, Boolean.parseBoolean(systemProperty));
                return true;
            }
            String jAXPProperty = SecuritySupport.readJAXPProperty(str);
            if (jAXPProperty == null || jAXPProperty.isEmpty()) {
                return false;
            }
            setFeature(xmlFeature, JdkProperty.State.JAXPDOTPROPERTIES, Boolean.parseBoolean(jAXPProperty));
            return true;
        } catch (NumberFormatException unused) {
            s01.a("Invalid setting for system property: ", xmlFeature.systemProperty());
            return false;
        }
    }

    private void readSystemProperties() {
        String strSystemPropertyOld;
        for (XmlFeature xmlFeature : XmlFeature.values()) {
            if (!getSystemProperty(xmlFeature, xmlFeature.systemProperty()) && (strSystemPropertyOld = xmlFeature.systemPropertyOld()) != null) {
                getSystemProperty(xmlFeature, strSystemPropertyOld);
            }
        }
    }

    public XmlFeature findByName(String str) {
        for (XmlFeature xmlFeature : XmlFeature.values()) {
            if (xmlFeature.equalsPropertyName(str)) {
                return xmlFeature;
            }
        }
        return null;
    }

    public boolean getFeature(XmlFeature xmlFeature) {
        return this.featureValues[xmlFeature.ordinal()];
    }

    public int getIndex(String str) {
        for (XmlFeature xmlFeature : XmlFeature.values()) {
            if (xmlFeature.equalsPropertyName(str)) {
                return xmlFeature.ordinal();
            }
        }
        return -1;
    }

    public void setFeature(int i, JdkProperty.State state, Object obj) {
        setFeature(i, state, Boolean.class.isAssignableFrom(obj.getClass()) ? ((Boolean) obj).booleanValue() : Boolean.parseBoolean((String) obj));
    }

    public void update() {
        readSystemProperties();
    }

    public boolean getFeature(int i) {
        return this.featureValues[i];
    }

    public void setFeature(XmlFeature xmlFeature, JdkProperty.State state, boolean z) {
        setFeature(xmlFeature.ordinal(), state, z);
    }

    public boolean setFeature(String str, JdkProperty.State state, Object obj) {
        XmlFeature xmlFeatureFindByName = findByName(str);
        if (xmlFeatureFindByName == null) {
            return false;
        }
        if (state == JdkProperty.State.APIPROPERTY) {
            state = xmlFeatureFindByName.getState(str);
        }
        if (state == null) {
            return false;
        }
        setFeature(xmlFeatureFindByName.ordinal(), state, obj);
        return true;
    }

    public void setFeature(int i, JdkProperty.State state, boolean z) {
        if (state.compareTo(this.states[i]) >= 0) {
            this.featureValues[i] = z;
            this.states[i] = state;
        }
    }
}
