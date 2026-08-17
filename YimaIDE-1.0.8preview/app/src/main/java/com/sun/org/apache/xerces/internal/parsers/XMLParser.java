package com.sun.org.apache.xerces.internal.parsers;

import com.sun.org.apache.xerces.internal.utils.XMLSecurityManager;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityPropertyManager;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration;
import java.io.IOException;
import jdk.xml.internal.JdkConstants;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class XMLParser {
    protected static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    protected static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
    private static final String[] RECOGNIZED_PROPERTIES = {"http://apache.org/xml/properties/internal/entity-resolver", "http://apache.org/xml/properties/internal/error-handler"};
    protected XMLParserConfiguration fConfiguration;
    XMLSecurityManager securityManager;
    XMLSecurityPropertyManager securityPropertyManager;

    public XMLParser(XMLParserConfiguration xMLParserConfiguration) {
        this.fConfiguration = xMLParserConfiguration;
        xMLParserConfiguration.addRecognizedProperties(RECOGNIZED_PROPERTIES);
    }

    public boolean getFeature(String str) throws SAXNotRecognizedException, SAXNotSupportedException {
        return this.fConfiguration.getFeature(str);
    }

    public void parse(XMLInputSource xMLInputSource) throws IOException, XNIException {
        if (this.securityManager == null) {
            XMLSecurityManager xMLSecurityManager = new XMLSecurityManager(true);
            this.securityManager = xMLSecurityManager;
            this.fConfiguration.setProperty("http://apache.org/xml/properties/security-manager", xMLSecurityManager);
        }
        if (this.securityPropertyManager == null) {
            XMLSecurityPropertyManager xMLSecurityPropertyManager = new XMLSecurityPropertyManager();
            this.securityPropertyManager = xMLSecurityPropertyManager;
            this.fConfiguration.setProperty(JdkConstants.XML_SECURITY_PROPERTY_MANAGER, xMLSecurityPropertyManager);
        }
        reset();
        this.fConfiguration.parse(xMLInputSource);
    }

    public void reset() throws XNIException {
    }
}
