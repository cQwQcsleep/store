package jdk.xml.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class JdkProperty<T> {
    private final ImplPropMap pName;
    private State pState;
    private final Class<T> pType;
    private T pValue;

    public enum ImplPropMap {
        ISSTANDALONE(JdkConstants.S_IS_STANDALONE, JdkConstants.FQ_IS_STANDALONE, JdkConstants.SP_IS_STANDALONE, true, null, null),
        XSLTCISSTANDALONE("xsltcIsStandalone", JdkConstants.JDK_IS_STANDALONE, JdkConstants.SP_XSLTC_IS_STANDALONE, true, JdkConstants.ORACLE_IS_STANDALONE, null),
        CDATACHUNKSIZE("cdataChunkSize", JdkConstants.CDATA_CHUNK_SIZE, JdkConstants.CDATA_CHUNK_SIZE, false, null, null),
        EXTCLSLOADER("extensionClassLoader", JdkConstants.JDK_EXT_CLASSLOADER, null, true, JdkConstants.JDK_EXTENSION_CLASSLOADER, null),
        ENABLEEXTFUNC("enableExtensionFunctions", JdkConstants.ORACLE_ENABLE_EXTENSION_FUNCTION, JdkConstants.SP_ENABLE_EXTENSION_FUNCTION_SPEC, true, null, JdkConstants.SP_ENABLE_EXTENSION_FUNCTION),
        OVERRIDEPARSER("overrideDefaultParser", JdkConstants.OVERRIDE_PARSER, JdkConstants.OVERRIDE_PARSER, false, JdkConstants.ORACLE_FEATURE_SERVICE_MECHANISM, JdkConstants.ORACLE_FEATURE_SERVICE_MECHANISM),
        RESETSYMBOLTABLE("resetSymbolTable", JdkConstants.RESET_SYMBOL_TABLE, JdkConstants.RESET_SYMBOL_TABLE, false, null, null),
        ENTITYCOUNT("getEntityCountInfo", JdkConstants.JDK_DEBUG_LIMIT, null, true, JdkConstants.JDK_ENTITY_COUNT_INFO, null);

        private final boolean differ;
        private final String name;
        private final String oldQName;
        private final String oldSPName;
        private final String qName;
        private final String spName;

        ImplPropMap(String str, String str2, String str3, boolean z, String str4, String str5) {
            this.name = str;
            this.qName = str2;
            this.spName = str3;
            this.differ = z;
            this.oldQName = str4;
            this.oldSPName = str5;
        }

        public State getState(String str) {
            String str2;
            String str3 = this.spName;
            if ((str3 != null && str3.equals(str)) || (this.spName == null && this.qName.equals(str))) {
                return State.APIPROPERTY;
            }
            if (!(this.differ && this.qName.equals(str)) && ((str2 = this.oldQName) == null || !str2.equals(str))) {
                return null;
            }
            return State.LEGACY_APIPROPERTY;
        }

        public boolean is(String str) {
            String str2 = this.spName;
            if (str2 != null && str2.equals(str)) {
                return true;
            }
            if (this.differ && this.qName.equals(str)) {
                return true;
            }
            String str3 = this.oldQName;
            return str3 != null && str3.equals(str);
        }

        public boolean isNameDiffer() {
            return this.differ;
        }

        public String qName() {
            return this.qName;
        }

        public String qNameOld() {
            return this.oldQName;
        }

        public String systemProperty() {
            return this.spName;
        }

        public String systemPropertyOld() {
            return this.oldSPName;
        }
    }

    public enum State {
        DEFAULT("default"),
        FSP("FEATURE_SECURE_PROCESSING"),
        JAXPDOTPROPERTIES("jaxp.properties"),
        LEGACY_SYSTEMPROPERTY("legacy system property"),
        SYSTEMPROPERTY("system property"),
        LEGACY_APIPROPERTY("legacy property"),
        APIPROPERTY("property");

        final String literal;

        State(String str) {
            this.literal = str;
        }

        public String literal() {
            return this.literal;
        }
    }

    public JdkProperty(ImplPropMap implPropMap, Class<T> cls, T t, State state) {
        State state2 = State.DEFAULT;
        this.pName = implPropMap;
        this.pType = cls;
        this.pValue = t;
        this.pState = state;
        readSystemProperty();
    }

    private void readSystemProperty() {
        if (this.pState == State.DEFAULT) {
            T t = this.pName.systemProperty() != null ? (T) SecuritySupport.getJAXPSystemProperty(this.pType, this.pName.systemProperty(), (String) null) : null;
            if (t == null && this.pName.systemPropertyOld() != null) {
                t = (T) SecuritySupport.getJAXPSystemProperty(this.pType, this.pName.systemPropertyOld(), (String) null);
            }
            if (t != null) {
                this.pValue = t;
                this.pState = State.SYSTEMPROPERTY;
            }
        }
    }

    public T getValue() {
        return this.pValue;
    }

    public boolean setValue(String str, T t, State state) {
        State state2 = this.pName.getState(str);
        if (state2 == null || state2.compareTo(this.pState) < 0) {
            return false;
        }
        this.pState = state2;
        this.pValue = t;
        return true;
    }
}
