package com.sun.org.apache.xerces.internal.impl;

import com.sun.org.apache.xerces.internal.utils.XMLSecurityManager;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityPropertyManager;
import com.sun.xml.internal.stream.StaxEntityResolverWrapper;
import java.util.HashMap;
import javax.xml.catalog.CatalogFeatures;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLResolver;
import jdk.xml.internal.JdkConstants;
import jdk.xml.internal.JdkProperty;
import jdk.xml.internal.JdkXmlUtils;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class PropertyManager {
    public static final int CONTEXT_READER = 1;
    public static final int CONTEXT_WRITER = 2;
    private static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
    public static final String STAX_ENTITIES = "javax.xml.stream.entities";
    public static final String STAX_NOTATIONS = "javax.xml.stream.notations";
    private static final String STRING_INTERNING = "http://xml.org/sax/features/string-interning";
    private static final String XML_SECURITY_PROPERTY_MANAGER = "jdk.xml.xmlSecurityPropertyManager";
    private XMLSecurityManager fSecurityManager;
    private XMLSecurityPropertyManager fSecurityPropertyMgr;
    HashMap<String, Object> supportedProps = new HashMap<>();

    public PropertyManager(PropertyManager propertyManager) {
        this.supportedProps.putAll(propertyManager.getProperties());
        this.fSecurityManager = (XMLSecurityManager) getProperty("http://apache.org/xml/properties/security-manager");
        this.fSecurityPropertyMgr = (XMLSecurityPropertyManager) getProperty("jdk.xml.xmlSecurityPropertyManager");
    }

    private HashMap<String, Object> getProperties() {
        return this.supportedProps;
    }

    private void initConfigurableReaderProperties() {
        HashMap<String, Object> map = this.supportedProps;
        Boolean bool = Boolean.TRUE;
        map.put(XMLInputFactory.IS_NAMESPACE_AWARE, bool);
        HashMap<String, Object> map2 = this.supportedProps;
        Boolean bool2 = Boolean.FALSE;
        map2.put(XMLInputFactory.IS_VALIDATING, bool2);
        this.supportedProps.put(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, bool);
        this.supportedProps.put(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, bool);
        this.supportedProps.put(XMLInputFactory.IS_COALESCING, bool2);
        this.supportedProps.put(XMLInputFactory.SUPPORT_DTD, bool);
        this.supportedProps.put(XMLInputFactory.REPORTER, null);
        this.supportedProps.put(XMLInputFactory.RESOLVER, null);
        this.supportedProps.put(XMLInputFactory.ALLOCATOR, null);
        this.supportedProps.put(STAX_NOTATIONS, null);
        this.supportedProps.put(STRING_INTERNING, bool);
        this.supportedProps.put("http://apache.org/xml/features/allow-java-encodings", bool);
        this.supportedProps.put(Constants.ADD_NAMESPACE_DECL_AS_ATTRIBUTE, bool2);
        this.supportedProps.put(Constants.READER_IN_DEFINED_STATE, bool);
        this.supportedProps.put(Constants.REUSE_INSTANCE, bool);
        this.supportedProps.put("http://java.sun.com/xml/stream/properties/report-cdata-event", bool2);
        this.supportedProps.put("http://java.sun.com/xml/stream/properties/ignore-external-dtd", bool2);
        this.supportedProps.put("http://apache.org/xml/features/validation/warn-on-duplicate-attdef", bool2);
        this.supportedProps.put("http://apache.org/xml/features/warn-on-duplicate-entitydef", bool2);
        this.supportedProps.put("http://apache.org/xml/features/validation/warn-on-undeclared-elemdef", bool2);
        XMLSecurityManager xMLSecurityManager = new XMLSecurityManager(true);
        this.fSecurityManager = xMLSecurityManager;
        this.supportedProps.put("http://apache.org/xml/properties/security-manager", xMLSecurityManager);
        XMLSecurityPropertyManager xMLSecurityPropertyManager = new XMLSecurityPropertyManager();
        this.fSecurityPropertyMgr = xMLSecurityPropertyManager;
        this.supportedProps.put("jdk.xml.xmlSecurityPropertyManager", xMLSecurityPropertyManager);
        this.supportedProps.put("http://javax.xml.XMLConstants/feature/useCatalog", Boolean.valueOf(JdkXmlUtils.USE_CATALOG_DEFAULT));
        for (CatalogFeatures.Feature feature : CatalogFeatures.Feature.values()) {
            this.supportedProps.put(feature.getPropertyName(), null);
        }
        this.supportedProps.put(JdkConstants.CDATA_CHUNK_SIZE, Integer.valueOf(JdkConstants.CDATA_CHUNK_SIZE_DEFAULT));
    }

    private void initWriterProps() {
        this.supportedProps.put(XMLOutputFactory.IS_REPAIRING_NAMESPACES, Boolean.FALSE);
        HashMap<String, Object> map = this.supportedProps;
        Boolean bool = Boolean.TRUE;
        map.put(Constants.ESCAPE_CHARACTERS, bool);
        this.supportedProps.put(Constants.REUSE_INSTANCE, bool);
    }

    public boolean containsProperty(String str) {
        if (this.supportedProps.containsKey(str)) {
            return true;
        }
        XMLSecurityManager xMLSecurityManager = this.fSecurityManager;
        if (xMLSecurityManager != null && xMLSecurityManager.getIndex(str) > -1) {
            return true;
        }
        XMLSecurityPropertyManager xMLSecurityPropertyManager = this.fSecurityPropertyMgr;
        return xMLSecurityPropertyManager != null && xMLSecurityPropertyManager.getIndex(str) > -1;
    }

    public Object getProperty(String str) {
        XMLSecurityManager xMLSecurityManager = this.fSecurityManager;
        String limitAsString = xMLSecurityManager != null ? xMLSecurityManager.getLimitAsString(str) : null;
        return limitAsString != null ? limitAsString : this.supportedProps.get(str);
    }

    public void setProperty(String str, Object obj) {
        String str2;
        XMLSecurityPropertyManager xMLSecurityPropertyManager;
        if (str.equals(XMLInputFactory.IS_NAMESPACE_AWARE)) {
            str2 = "http://apache.org/xml/features/namespaces";
        } else {
            if (str.equals(XMLInputFactory.IS_VALIDATING)) {
                if ((obj instanceof Boolean) && ((Boolean) obj).booleanValue()) {
                    w01.a("true value of isValidating not supported");
                    return;
                }
            } else if (str.equals(STRING_INTERNING)) {
                if ((obj instanceof Boolean) && !((Boolean) obj).booleanValue()) {
                    w01.a("false value of http://xml.org/sax/features/string-interningfeature is not supported");
                    return;
                }
            } else if (str.equals(XMLInputFactory.RESOLVER)) {
                this.supportedProps.put("http://apache.org/xml/properties/internal/stax-entity-resolver", new StaxEntityResolverWrapper((XMLResolver) obj));
            }
            str2 = null;
        }
        if (str.equals("http://apache.org/xml/properties/security-manager")) {
            XMLSecurityManager xMLSecurityManagerConvert = XMLSecurityManager.convert(obj, this.fSecurityManager);
            this.fSecurityManager = xMLSecurityManagerConvert;
            this.supportedProps.put("http://apache.org/xml/properties/security-manager", xMLSecurityManagerConvert);
            return;
        }
        if (str.equals("jdk.xml.xmlSecurityPropertyManager")) {
            if (obj == null) {
                this.fSecurityPropertyMgr = new XMLSecurityPropertyManager();
            } else {
                this.fSecurityPropertyMgr = (XMLSecurityPropertyManager) obj;
            }
            this.supportedProps.put("jdk.xml.xmlSecurityPropertyManager", this.fSecurityPropertyMgr);
            return;
        }
        XMLSecurityManager xMLSecurityManager = this.fSecurityManager;
        if ((xMLSecurityManager == null || !xMLSecurityManager.setLimit(str, JdkProperty.State.APIPROPERTY, obj)) && ((xMLSecurityPropertyManager = this.fSecurityPropertyMgr) == null || !xMLSecurityPropertyManager.setValue(str, XMLSecurityPropertyManager.State.APIPROPERTY, obj))) {
            this.supportedProps.put(str, obj);
        }
        if (str2 != null) {
            this.supportedProps.put(str2, obj);
        }
    }

    public String toString() {
        return this.supportedProps.toString();
    }

    public PropertyManager(int i) {
        if (i == 1) {
            initConfigurableReaderProperties();
        } else {
            if (i != 2) {
                return;
            }
            initWriterProps();
        }
    }
}
