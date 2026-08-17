package com.sun.org.apache.xerces.internal.parsers;

import com.sun.org.apache.xerces.internal.xni.Augmentations;
import com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xni.XMLAttributes;
import com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler;
import com.sun.org.apache.xerces.internal.xni.XMLDTDHandler;
import com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler;
import com.sun.org.apache.xerces.internal.xni.XMLLocator;
import com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier;
import com.sun.org.apache.xerces.internal.xni.XMLString;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLDTDContentModelSource;
import com.sun.org.apache.xerces.internal.xni.parser.XMLDTDSource;
import com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentSource;
import com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class AbstractXMLDocumentParser extends XMLParser implements XMLDocumentHandler, XMLDTDHandler, XMLDTDContentModelHandler {
    protected XMLDTDContentModelSource fDTDContentModelSource;
    protected XMLDTDSource fDTDSource;
    protected XMLDocumentSource fDocumentSource;
    protected boolean fInDTD;

    public AbstractXMLDocumentParser(XMLParserConfiguration xMLParserConfiguration) {
        super(xMLParserConfiguration);
        xMLParserConfiguration.setDocumentHandler(this);
        xMLParserConfiguration.setDTDHandler(this);
        xMLParserConfiguration.setDTDContentModelHandler(this);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
    public void any(Augmentations augmentations) throws XNIException {
    }

    public void attributeDecl(String str, String str2, String str3, String[] strArr, String str4, XMLString xMLString, XMLString xMLString2, Augmentations augmentations) throws XNIException {
    }

    public void characters(XMLString xMLString, Augmentations augmentations) throws XNIException {
    }

    public void comment(XMLString xMLString, Augmentations augmentations) throws XNIException {
    }

    public void doctypeDecl(String str, String str2, String str3, Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
    public void element(String str, Augmentations augmentations) throws XNIException {
    }

    public void elementDecl(String str, String str2, Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
    public void empty(Augmentations augmentations) throws XNIException {
    }

    public void emptyElement(QName qName, XMLAttributes xMLAttributes, Augmentations augmentations) throws XNIException {
        startElement(qName, xMLAttributes, augmentations);
        endElement(qName, augmentations);
    }

    public void endAttlist(Augmentations augmentations) throws XNIException {
    }

    public void endCDATA(Augmentations augmentations) throws XNIException {
    }

    public void endConditional(Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
    public void endContentModel(Augmentations augmentations) throws XNIException {
    }

    public void endDTD(Augmentations augmentations) throws XNIException {
        this.fInDTD = false;
    }

    public void endDocument(Augmentations augmentations) throws XNIException {
    }

    public void endElement(QName qName, Augmentations augmentations) throws XNIException {
    }

    public void endExternalSubset(Augmentations augmentations) throws XNIException {
    }

    public void endGeneralEntity(String str, Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
    public void endGroup(Augmentations augmentations) throws XNIException {
    }

    public void endParameterEntity(String str, Augmentations augmentations) throws XNIException {
    }

    public void externalEntityDecl(String str, XMLResourceIdentifier xMLResourceIdentifier, Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
    public XMLDTDContentModelSource getDTDContentModelSource() {
        return this.fDTDContentModelSource;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public XMLDTDSource getDTDSource() {
        return this.fDTDSource;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public XMLDocumentSource getDocumentSource() {
        return this.fDocumentSource;
    }

    public void ignorableWhitespace(XMLString xMLString, Augmentations augmentations) throws XNIException {
    }

    public void ignoredCharacters(XMLString xMLString, Augmentations augmentations) throws XNIException {
    }

    public void internalEntityDecl(String str, XMLString xMLString, XMLString xMLString2, Augmentations augmentations) throws XNIException {
    }

    public void notationDecl(String str, XMLResourceIdentifier xMLResourceIdentifier, Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
    public void occurrence(short s, Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
    public void pcdata(Augmentations augmentations) throws XNIException {
    }

    public void processingInstruction(String str, XMLString xMLString, Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.XMLParser
    public void reset() throws XNIException {
        super.reset();
        this.fInDTD = false;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
    public void separator(short s, Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
    public void setDTDContentModelSource(XMLDTDContentModelSource xMLDTDContentModelSource) {
        this.fDTDContentModelSource = xMLDTDContentModelSource;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void setDTDSource(XMLDTDSource xMLDTDSource) {
        this.fDTDSource = xMLDTDSource;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void setDocumentSource(XMLDocumentSource xMLDocumentSource) {
        this.fDocumentSource = xMLDocumentSource;
    }

    public void startAttlist(String str, Augmentations augmentations) throws XNIException {
    }

    public void startCDATA(Augmentations augmentations) throws XNIException {
    }

    public void startConditional(short s, Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
    public void startContentModel(String str, Augmentations augmentations) throws XNIException {
    }

    public void startDTD(XMLLocator xMLLocator, Augmentations augmentations) throws XNIException {
        this.fInDTD = true;
    }

    public void startDocument(XMLLocator xMLLocator, String str, NamespaceContext namespaceContext, Augmentations augmentations) throws XNIException {
    }

    public void startElement(QName qName, XMLAttributes xMLAttributes, Augmentations augmentations) throws XNIException {
    }

    public void startExternalSubset(XMLResourceIdentifier xMLResourceIdentifier, Augmentations augmentations) throws XNIException {
    }

    public void startGeneralEntity(String str, XMLResourceIdentifier xMLResourceIdentifier, String str2, Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
    public void startGroup(Augmentations augmentations) throws XNIException {
    }

    public void startParameterEntity(String str, XMLResourceIdentifier xMLResourceIdentifier, String str2, Augmentations augmentations) throws XNIException {
    }

    public void textDecl(String str, String str2, Augmentations augmentations) throws XNIException {
    }

    public void unparsedEntityDecl(String str, XMLResourceIdentifier xMLResourceIdentifier, String str2, Augmentations augmentations) throws XNIException {
    }

    public void xmlDecl(String str, String str2, String str3, Augmentations augmentations) throws XNIException {
    }
}
