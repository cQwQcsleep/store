package com.sun.org.apache.xerces.internal.jaxp;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import com.sun.org.apache.xerces.internal.util.SAXMessageFormatter;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityManager;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityPropertyManager;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import jdk.xml.internal.JdkProperty;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DocumentBuilderFactoryImpl extends DocumentBuilderFactory {
    private Map<String, Object> attributes;
    private boolean fSecureProcess = true;
    XMLSecurityManager fSecurityManager = new XMLSecurityManager(true);
    XMLSecurityPropertyManager fSecurityPropertyMgr = new XMLSecurityPropertyManager();
    private Map<String, Boolean> features;
    private Schema grammar;
    private boolean isXIncludeAware;

    @Override // javax.xml.parsers.DocumentBuilderFactory
    public Object getAttribute(String str) throws IllegalArgumentException {
        DOMParser dOMParser;
        Object obj;
        String strFind = this.fSecurityManager.find(str);
        if (strFind != null) {
            return this.attributes.get(strFind);
        }
        String strFind2 = this.fSecurityPropertyMgr.find(str);
        Map<String, Object> map = this.attributes;
        if (strFind2 != null) {
            return map.get(strFind2);
        }
        if (map != null && (obj = map.get(str)) != null) {
            return obj;
        }
        try {
            dOMParser = new DocumentBuilderImpl(this, this.attributes, this.features).getDOMParser();
            try {
                return dOMParser.getProperty(str);
            } catch (SAXException e) {
                e = e;
                try {
                    return dOMParser.getFeature(str) ? Boolean.TRUE : Boolean.FALSE;
                } catch (SAXException unused) {
                    w01.a(e.getMessage());
                    return null;
                }
            }
        } catch (SAXException e2) {
            e = e2;
            dOMParser = null;
        }
    }

    @Override // javax.xml.parsers.DocumentBuilderFactory
    public boolean getFeature(String str) throws ParserConfigurationException {
        Boolean bool;
        if (str.equals("http://javax.xml.XMLConstants/feature/secure-processing")) {
            return this.fSecureProcess;
        }
        Map<String, Boolean> map = this.features;
        if (map != null && (bool = map.get(str)) != null) {
            return bool.booleanValue();
        }
        try {
            return new DocumentBuilderImpl(this, this.attributes, this.features).getDOMParser().getFeature(str);
        } catch (SAXException e) {
            throw new ParserConfigurationException(e.getMessage());
        }
    }

    @Override // javax.xml.parsers.DocumentBuilderFactory
    public Schema getSchema() {
        return this.grammar;
    }

    @Override // javax.xml.parsers.DocumentBuilderFactory
    public boolean isXIncludeAware() {
        return this.isXIncludeAware;
    }

    @Override // javax.xml.parsers.DocumentBuilderFactory
    public DocumentBuilder newDocumentBuilder() throws ParserConfigurationException {
        Map<String, Object> map;
        if (this.grammar != null && (map = this.attributes) != null) {
            if (map.containsKey(JAXPConstants.JAXP_SCHEMA_LANGUAGE)) {
                throw new ParserConfigurationException(SAXMessageFormatter.formatMessage(null, "schema-already-specified", new Object[]{JAXPConstants.JAXP_SCHEMA_LANGUAGE}));
            }
            if (this.attributes.containsKey(JAXPConstants.JAXP_SCHEMA_SOURCE)) {
                throw new ParserConfigurationException(SAXMessageFormatter.formatMessage(null, "schema-already-specified", new Object[]{JAXPConstants.JAXP_SCHEMA_SOURCE}));
            }
        }
        try {
            return new DocumentBuilderImpl(this, this.attributes, this.features, this.fSecureProcess);
        } catch (SAXException e) {
            throw new ParserConfigurationException(e.getMessage());
        }
    }

    @Override // javax.xml.parsers.DocumentBuilderFactory
    public void setAttribute(String str, Object obj) throws IllegalArgumentException {
        Map<String, Object> map = this.attributes;
        if (obj == null) {
            if (map != null) {
                map.remove(str);
                return;
            }
            return;
        }
        if (map == null) {
            this.attributes = new HashMap();
        }
        String strFind = this.fSecurityManager.find(str);
        if (strFind != null) {
            this.fSecurityManager.setLimit(str, JdkProperty.State.APIPROPERTY, obj);
            this.attributes.put(strFind, this.fSecurityManager.getLimitAsString(strFind));
            return;
        }
        String strFind2 = this.fSecurityPropertyMgr.find(str);
        Map<String, Object> map2 = this.attributes;
        if (strFind2 != null) {
            map2.put(strFind2, obj);
            return;
        }
        map2.put(str, obj);
        try {
            new DocumentBuilderImpl(this, this.attributes, this.features);
        } catch (Exception e) {
            this.attributes.remove(str);
            w01.a(e.getMessage());
        }
    }

    @Override // javax.xml.parsers.DocumentBuilderFactory
    public void setFeature(String str, boolean z) throws ParserConfigurationException {
        if (this.features == null) {
            this.features = new HashMap();
        }
        if (str.equals("http://javax.xml.XMLConstants/feature/secure-processing")) {
            if (System.getSecurityManager() != null && !z) {
                throw new ParserConfigurationException(SAXMessageFormatter.formatMessage(null, "jaxp-secureprocessing-feature", null));
            }
            this.fSecureProcess = z;
            this.features.put(str, z ? Boolean.TRUE : Boolean.FALSE);
            return;
        }
        this.features.put(str, z ? Boolean.TRUE : Boolean.FALSE);
        try {
            new DocumentBuilderImpl(this, this.attributes, this.features);
        } catch (SAXNotRecognizedException e) {
            this.features.remove(str);
            throw new ParserConfigurationException(e.getMessage());
        } catch (SAXNotSupportedException e2) {
            this.features.remove(str);
            throw new ParserConfigurationException(e2.getMessage());
        }
    }

    @Override // javax.xml.parsers.DocumentBuilderFactory
    public void setSchema(Schema schema) {
        this.grammar = schema;
    }

    @Override // javax.xml.parsers.DocumentBuilderFactory
    public void setXIncludeAware(boolean z) {
        this.isXIncludeAware = z;
    }
}
