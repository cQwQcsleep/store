package com.sun.org.apache.xml.internal.serializer;

import java.io.IOException;
import java.util.List;
import javax.xml.transform.Transformer;
import org.w3c.dom.Node;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DeclHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface SerializationHandler extends ExtendedContentHandler, ExtendedLexicalHandler, XSLOutputAttributes, DeclHandler, DTDHandler, ErrorHandler, DOMSerializer, Serializer {
    @Override // com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    /* synthetic */ void addAttribute(String str, String str2);

    @Override // com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    /* synthetic */ void addAttribute(String str, String str2, String str3, String str4, String str5);

    @Override // com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    /* synthetic */ void characters(String str);

    void close();

    /* synthetic */ void comment(String str);

    @Override // com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    /* synthetic */ void endElement(String str);

    void flushPending() throws SAXException;

    /* synthetic */ boolean getIndent();

    @Override // com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    /* synthetic */ NamespaceMappings getNamespaceMappings();

    Transformer getTransformer();

    @Override // com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    /* synthetic */ void namespaceAfterStartElement(String str, String str2);

    void serialize(Node node) throws IOException;

    /* synthetic */ void setCdataSectionElements(List list);

    void setContentHandler(ContentHandler contentHandler);

    void setDTDEntityExpansion(boolean z);

    /* synthetic */ void setDoctype(String str, String str2);

    /* synthetic */ void setEncoding(String str);

    boolean setEscaping(boolean z) throws SAXException;

    /* synthetic */ void setIndent(boolean z);

    void setIndentAmount(int i);

    void setIsStandalone(boolean z);

    /* synthetic */ void setMediaType(String str);

    void setNamespaceMappings(NamespaceMappings namespaceMappings);

    /* synthetic */ void setOmitXMLDeclaration(boolean z);

    /* synthetic */ void setOutputProperty(String str, String str2);

    /* synthetic */ void setStandalone(String str);

    void setTransformer(Transformer transformer);

    /* synthetic */ void setVersion(String str);

    @Override // com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    /* synthetic */ void startElement(String str);

    @Override // com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    /* synthetic */ void startElement(String str, String str2, String str3);
}
