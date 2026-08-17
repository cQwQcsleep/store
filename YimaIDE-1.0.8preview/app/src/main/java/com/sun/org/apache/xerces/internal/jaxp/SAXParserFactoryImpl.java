package com.sun.org.apache.xerces.internal.jaxp;

import com.sun.org.apache.xerces.internal.util.SAXMessageFormatter;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SAXParserFactoryImpl extends SAXParserFactory {
    private static final String NAMESPACES_FEATURE = "http://xml.org/sax/features/namespaces";
    private static final String VALIDATION_FEATURE = "http://xml.org/sax/features/validation";
    private static final String XINCLUDE_FEATURE = "http://apache.org/xml/features/xinclude";
    private boolean fSecureProcess = true;
    private Map<String, Boolean> features;
    private Schema grammar;
    private boolean isXIncludeAware;

    private boolean getFromFeatures(String str) {
        Boolean bool;
        Map<String, Boolean> map = this.features;
        if (map == null || (bool = map.get(str)) == null) {
            return false;
        }
        return bool.booleanValue();
    }

    private SAXParserImpl newSAXParserImpl() throws SAXNotRecognizedException, SAXNotSupportedException, ParserConfigurationException {
        try {
            return new SAXParserImpl(this, this.features);
        } catch (SAXNotRecognizedException e) {
            throw e;
        } catch (SAXNotSupportedException e2) {
            throw e2;
        } catch (SAXException e3) {
            throw new ParserConfigurationException(e3.getMessage());
        }
    }

    private void putInFeatures(String str, boolean z) {
        if (this.features == null) {
            this.features = new HashMap();
        }
        this.features.put(str, z ? Boolean.TRUE : Boolean.FALSE);
    }

    @Override // javax.xml.parsers.SAXParserFactory
    public boolean getFeature(String str) throws SAXNotRecognizedException, SAXNotSupportedException, ParserConfigurationException {
        str.getClass();
        return str.equals("http://javax.xml.XMLConstants/feature/secure-processing") ? this.fSecureProcess : newSAXParserImpl().getXMLReader().getFeature(str);
    }

    @Override // javax.xml.parsers.SAXParserFactory
    public Schema getSchema() {
        return this.grammar;
    }

    @Override // javax.xml.parsers.SAXParserFactory
    public boolean isNamespaceAware() {
        return getFromFeatures("http://xml.org/sax/features/namespaces");
    }

    @Override // javax.xml.parsers.SAXParserFactory
    public boolean isValidating() {
        return getFromFeatures(VALIDATION_FEATURE);
    }

    @Override // javax.xml.parsers.SAXParserFactory
    public boolean isXIncludeAware() {
        return getFromFeatures(XINCLUDE_FEATURE);
    }

    @Override // javax.xml.parsers.SAXParserFactory
    public SAXParser newSAXParser() throws ParserConfigurationException {
        try {
            return new SAXParserImpl(this, this.features, this.fSecureProcess);
        } catch (SAXException e) {
            throw new ParserConfigurationException(e.getMessage());
        }
    }

    @Override // javax.xml.parsers.SAXParserFactory
    public void setFeature(String str, boolean z) throws SAXNotRecognizedException, SAXNotSupportedException, ParserConfigurationException {
        str.getClass();
        if (str.equals("http://javax.xml.XMLConstants/feature/secure-processing")) {
            if (System.getSecurityManager() != null && !z) {
                throw new ParserConfigurationException(SAXMessageFormatter.formatMessage(null, "jaxp-secureprocessing-feature", null));
            }
            this.fSecureProcess = z;
            putInFeatures(str, z);
            return;
        }
        putInFeatures(str, z);
        try {
            newSAXParserImpl();
        } catch (SAXNotRecognizedException e) {
            this.features.remove(str);
            throw e;
        } catch (SAXNotSupportedException e2) {
            this.features.remove(str);
            throw e2;
        }
    }

    @Override // javax.xml.parsers.SAXParserFactory
    public void setNamespaceAware(boolean z) {
        putInFeatures("http://xml.org/sax/features/namespaces", z);
    }

    @Override // javax.xml.parsers.SAXParserFactory
    public void setSchema(Schema schema) {
        this.grammar = schema;
    }

    @Override // javax.xml.parsers.SAXParserFactory
    public void setValidating(boolean z) {
        putInFeatures(VALIDATION_FEATURE, z);
    }

    @Override // javax.xml.parsers.SAXParserFactory
    public void setXIncludeAware(boolean z) {
        putInFeatures(XINCLUDE_FEATURE, z);
    }
}
