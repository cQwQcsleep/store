package com.sun.org.apache.xerces.internal.xni.parser;

import com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler;
import com.sun.org.apache.xerces.internal.xni.XMLDTDHandler;
import com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import java.io.IOException;
import java.util.Locale;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface XMLParserConfiguration extends XMLComponentManager {
    void addRecognizedFeatures(String[] strArr);

    void addRecognizedProperties(String[] strArr);

    XMLDTDContentModelHandler getDTDContentModelHandler();

    XMLDTDHandler getDTDHandler();

    XMLDocumentHandler getDocumentHandler();

    XMLEntityResolver getEntityResolver();

    XMLErrorHandler getErrorHandler();

    boolean getFeature(String str) throws XMLConfigurationException;

    Locale getLocale();

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager
    Object getProperty(String str) throws XMLConfigurationException;

    void parse(XMLInputSource xMLInputSource) throws IOException, XNIException;

    void setDTDContentModelHandler(XMLDTDContentModelHandler xMLDTDContentModelHandler);

    void setDTDHandler(XMLDTDHandler xMLDTDHandler);

    void setDocumentHandler(XMLDocumentHandler xMLDocumentHandler);

    void setEntityResolver(XMLEntityResolver xMLEntityResolver);

    void setErrorHandler(XMLErrorHandler xMLErrorHandler);

    void setFeature(String str, boolean z) throws XMLConfigurationException;

    void setLocale(Locale locale) throws XNIException;

    void setProperty(String str, Object obj) throws XMLConfigurationException;
}
