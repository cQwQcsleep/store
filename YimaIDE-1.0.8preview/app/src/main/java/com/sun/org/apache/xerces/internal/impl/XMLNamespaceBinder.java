package com.sun.org.apache.xerces.internal.impl;

import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.util.XMLSymbols;
import com.sun.org.apache.xerces.internal.xni.Augmentations;
import com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xni.XMLAttributes;
import com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler;
import com.sun.org.apache.xerces.internal.xni.XMLLocator;
import com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier;
import com.sun.org.apache.xerces.internal.xni.XMLString;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponent;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager;
import com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentFilter;
import com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLNamespaceBinder implements XMLComponent, XMLDocumentFilter {
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String NAMESPACES = "http://xml.org/sax/features/namespaces";
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    private QName fAttributeQName = new QName();
    protected XMLDocumentHandler fDocumentHandler;
    protected XMLDocumentSource fDocumentSource;
    protected XMLErrorReporter fErrorReporter;
    private NamespaceContext fNamespaceContext;
    protected boolean fNamespaces;
    protected boolean fOnlyPassPrefixMappingEvents;
    protected SymbolTable fSymbolTable;
    private static final String[] RECOGNIZED_FEATURES = {"http://xml.org/sax/features/namespaces"};
    private static final Boolean[] FEATURE_DEFAULTS = {null};
    private static final String[] RECOGNIZED_PROPERTIES = {"http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-reporter"};
    private static final Object[] PROPERTY_DEFAULTS = {null, null};

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void characters(XMLString xMLString, Augmentations augmentations) throws XNIException {
        XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
        if (xMLDocumentHandler == null || this.fOnlyPassPrefixMappingEvents) {
            return;
        }
        xMLDocumentHandler.characters(xMLString, augmentations);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void comment(XMLString xMLString, Augmentations augmentations) throws XNIException {
        XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
        if (xMLDocumentHandler == null || this.fOnlyPassPrefixMappingEvents) {
            return;
        }
        xMLDocumentHandler.comment(xMLString, augmentations);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void doctypeDecl(String str, String str2, String str3, Augmentations augmentations) throws XNIException {
        XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
        if (xMLDocumentHandler == null || this.fOnlyPassPrefixMappingEvents) {
            return;
        }
        xMLDocumentHandler.doctypeDecl(str, str2, str3, augmentations);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void emptyElement(QName qName, XMLAttributes xMLAttributes, Augmentations augmentations) throws XNIException {
        if (this.fNamespaces) {
            handleStartElement(qName, xMLAttributes, augmentations, true);
            handleEndElement(qName, augmentations, true);
        } else {
            XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
            if (xMLDocumentHandler != null) {
                xMLDocumentHandler.emptyElement(qName, xMLAttributes, augmentations);
            }
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void endCDATA(Augmentations augmentations) throws XNIException {
        XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
        if (xMLDocumentHandler == null || this.fOnlyPassPrefixMappingEvents) {
            return;
        }
        xMLDocumentHandler.endCDATA(augmentations);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void endDocument(Augmentations augmentations) throws XNIException {
        XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
        if (xMLDocumentHandler == null || this.fOnlyPassPrefixMappingEvents) {
            return;
        }
        xMLDocumentHandler.endDocument(augmentations);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void endElement(QName qName, Augmentations augmentations) throws XNIException {
        if (this.fNamespaces) {
            handleEndElement(qName, augmentations, false);
            return;
        }
        XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
        if (xMLDocumentHandler != null) {
            xMLDocumentHandler.endElement(qName, augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void endGeneralEntity(String str, Augmentations augmentations) throws XNIException {
        XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
        if (xMLDocumentHandler == null || this.fOnlyPassPrefixMappingEvents) {
            return;
        }
        xMLDocumentHandler.endGeneralEntity(str, augmentations);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentSource
    public XMLDocumentHandler getDocumentHandler() {
        return this.fDocumentHandler;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public XMLDocumentSource getDocumentSource() {
        return this.fDocumentSource;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public Boolean getFeatureDefault(String str) {
        int i = 0;
        while (true) {
            String[] strArr = RECOGNIZED_FEATURES;
            if (i >= strArr.length) {
                return null;
            }
            if (strArr[i].equals(str)) {
                return FEATURE_DEFAULTS[i];
            }
            i++;
        }
    }

    public boolean getOnlyPassPrefixMappingEvents() {
        return this.fOnlyPassPrefixMappingEvents;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public Object getPropertyDefault(String str) {
        int i = 0;
        while (true) {
            String[] strArr = RECOGNIZED_PROPERTIES;
            if (i >= strArr.length) {
                return null;
            }
            if (strArr[i].equals(str)) {
                return PROPERTY_DEFAULTS[i];
            }
            i++;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public String[] getRecognizedFeatures() {
        return (String[]) RECOGNIZED_FEATURES.clone();
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public String[] getRecognizedProperties() {
        return (String[]) RECOGNIZED_PROPERTIES.clone();
    }

    public void handleEndElement(QName qName, Augmentations augmentations, boolean z) throws XNIException {
        String str = qName.prefix;
        if (str == null) {
            str = XMLSymbols.EMPTY_STRING;
        }
        String uri = this.fNamespaceContext.getURI(str);
        qName.uri = uri;
        if (uri != null) {
            qName.prefix = str;
        }
        XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
        if (xMLDocumentHandler != null && !this.fOnlyPassPrefixMappingEvents && !z) {
            xMLDocumentHandler.endElement(qName, augmentations);
        }
        this.fNamespaceContext.popContext();
    }

    public void handleStartElement(QName qName, XMLAttributes xMLAttributes, Augmentations augmentations, boolean z) throws XNIException {
        this.fNamespaceContext.pushContext();
        if (qName.prefix == XMLSymbols.PREFIX_XMLNS) {
            this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "ElementXMLNSPrefix", new Object[]{qName.rawname}, (short) 2);
        }
        int length = xMLAttributes.getLength();
        for (int i = 0; i < length; i++) {
            String localName = xMLAttributes.getLocalName(i);
            String prefix = xMLAttributes.getPrefix(i);
            String str = XMLSymbols.PREFIX_XMLNS;
            if (prefix == str || (prefix == XMLSymbols.EMPTY_STRING && localName == str)) {
                String strAddSymbol = this.fSymbolTable.addSymbol(xMLAttributes.getValue(i));
                if (prefix == str && localName == str) {
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "CantBindXMLNS", new Object[]{xMLAttributes.getQName(i)}, (short) 2);
                }
                if (strAddSymbol == NamespaceContext.XMLNS_URI) {
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "CantBindXMLNS", new Object[]{xMLAttributes.getQName(i)}, (short) 2);
                }
                if (localName == XMLSymbols.PREFIX_XML) {
                    if (strAddSymbol != NamespaceContext.XML_URI) {
                        this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "CantBindXML", new Object[]{xMLAttributes.getQName(i)}, (short) 2);
                    }
                } else if (strAddSymbol == NamespaceContext.XML_URI) {
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "CantBindXML", new Object[]{xMLAttributes.getQName(i)}, (short) 2);
                }
                String str2 = localName != str ? localName : XMLSymbols.EMPTY_STRING;
                if (prefixBoundToNullURI(strAddSymbol, localName)) {
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "EmptyPrefixedAttName", new Object[]{xMLAttributes.getQName(i)}, (short) 2);
                } else {
                    NamespaceContext namespaceContext = this.fNamespaceContext;
                    if (strAddSymbol.length() == 0) {
                        strAddSymbol = null;
                    }
                    namespaceContext.declarePrefix(str2, strAddSymbol);
                }
            }
        }
        String str3 = qName.prefix;
        if (str3 == null) {
            str3 = XMLSymbols.EMPTY_STRING;
        }
        String uri = this.fNamespaceContext.getURI(str3);
        qName.uri = uri;
        if (qName.prefix == null && uri != null) {
            qName.prefix = XMLSymbols.EMPTY_STRING;
        }
        String str4 = qName.prefix;
        if (str4 != null && uri == null) {
            this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "ElementPrefixUnbound", new Object[]{str4, qName.rawname}, (short) 2);
        }
        for (int i2 = 0; i2 < length; i2++) {
            xMLAttributes.getName(i2, this.fAttributeQName);
            QName qName2 = this.fAttributeQName;
            String str5 = qName2.prefix;
            if (str5 == null) {
                str5 = XMLSymbols.EMPTY_STRING;
            }
            String str6 = qName2.rawname;
            String str7 = XMLSymbols.PREFIX_XMLNS;
            if (str6 == str7) {
                qName2.uri = this.fNamespaceContext.getURI(str7);
                xMLAttributes.setName(i2, this.fAttributeQName);
            } else if (str5 != XMLSymbols.EMPTY_STRING) {
                qName2.uri = this.fNamespaceContext.getURI(str5);
                if (this.fAttributeQName.uri == null) {
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "AttributePrefixUnbound", new Object[]{qName.rawname, str6, str5}, (short) 2);
                }
                xMLAttributes.setName(i2, this.fAttributeQName);
            }
        }
        int length2 = xMLAttributes.getLength();
        for (int i3 = 0; i3 < length2 - 1; i3++) {
            String uri2 = xMLAttributes.getURI(i3);
            if (uri2 != null && uri2 != NamespaceContext.XMLNS_URI) {
                String localName2 = xMLAttributes.getLocalName(i3);
                for (int i4 = i3 + 1; i4 < length2; i4++) {
                    String localName3 = xMLAttributes.getLocalName(i4);
                    String uri3 = xMLAttributes.getURI(i4);
                    if (localName2 == localName3 && uri2 == uri3) {
                        this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "AttributeNSNotUnique", new Object[]{qName.rawname, localName2, uri2}, (short) 2);
                    }
                }
            }
        }
        XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
        if (xMLDocumentHandler == null || this.fOnlyPassPrefixMappingEvents) {
            return;
        }
        if (z) {
            xMLDocumentHandler.emptyElement(qName, xMLAttributes, augmentations);
        } else {
            xMLDocumentHandler.startElement(qName, xMLAttributes, augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void ignorableWhitespace(XMLString xMLString, Augmentations augmentations) throws XNIException {
        XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
        if (xMLDocumentHandler == null || this.fOnlyPassPrefixMappingEvents) {
            return;
        }
        xMLDocumentHandler.ignorableWhitespace(xMLString, augmentations);
    }

    public boolean prefixBoundToNullURI(String str, String str2) {
        return str == XMLSymbols.EMPTY_STRING && str2 != XMLSymbols.PREFIX_XMLNS;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void processingInstruction(String str, XMLString xMLString, Augmentations augmentations) throws XNIException {
        XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
        if (xMLDocumentHandler == null || this.fOnlyPassPrefixMappingEvents) {
            return;
        }
        xMLDocumentHandler.processingInstruction(str, xMLString, augmentations);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public void reset(XMLComponentManager xMLComponentManager) throws XNIException {
        this.fNamespaces = xMLComponentManager.getFeature("http://xml.org/sax/features/namespaces", true);
        this.fSymbolTable = (SymbolTable) xMLComponentManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
        this.fErrorReporter = (XMLErrorReporter) xMLComponentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentSource
    public void setDocumentHandler(XMLDocumentHandler xMLDocumentHandler) {
        this.fDocumentHandler = xMLDocumentHandler;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void setDocumentSource(XMLDocumentSource xMLDocumentSource) {
        this.fDocumentSource = xMLDocumentSource;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public void setFeature(String str, boolean z) throws XMLConfigurationException {
    }

    public void setOnlyPassPrefixMappingEvents(boolean z) {
        this.fOnlyPassPrefixMappingEvents = z;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public void setProperty(String str, Object obj) throws XMLConfigurationException {
        if (str.startsWith(Constants.XERCES_PROPERTY_PREFIX)) {
            int length = str.length() - 33;
            if (length == 21 && str.endsWith(Constants.SYMBOL_TABLE_PROPERTY)) {
                this.fSymbolTable = (SymbolTable) obj;
            } else if (length == 23 && str.endsWith(Constants.ERROR_REPORTER_PROPERTY)) {
                this.fErrorReporter = (XMLErrorReporter) obj;
            }
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void startCDATA(Augmentations augmentations) throws XNIException {
        XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
        if (xMLDocumentHandler == null || this.fOnlyPassPrefixMappingEvents) {
            return;
        }
        xMLDocumentHandler.startCDATA(augmentations);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void startDocument(XMLLocator xMLLocator, String str, NamespaceContext namespaceContext, Augmentations augmentations) throws XNIException {
        this.fNamespaceContext = namespaceContext;
        XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
        if (xMLDocumentHandler == null || this.fOnlyPassPrefixMappingEvents) {
            return;
        }
        xMLDocumentHandler.startDocument(xMLLocator, str, namespaceContext, augmentations);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void startElement(QName qName, XMLAttributes xMLAttributes, Augmentations augmentations) throws XNIException {
        if (this.fNamespaces) {
            handleStartElement(qName, xMLAttributes, augmentations, false);
            return;
        }
        XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
        if (xMLDocumentHandler != null) {
            xMLDocumentHandler.startElement(qName, xMLAttributes, augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void startGeneralEntity(String str, XMLResourceIdentifier xMLResourceIdentifier, String str2, Augmentations augmentations) throws XNIException {
        XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
        if (xMLDocumentHandler == null || this.fOnlyPassPrefixMappingEvents) {
            return;
        }
        xMLDocumentHandler.startGeneralEntity(str, xMLResourceIdentifier, str2, augmentations);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void textDecl(String str, String str2, Augmentations augmentations) throws XNIException {
        XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
        if (xMLDocumentHandler == null || this.fOnlyPassPrefixMappingEvents) {
            return;
        }
        xMLDocumentHandler.textDecl(str, str2, augmentations);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void xmlDecl(String str, String str2, String str3, Augmentations augmentations) throws XNIException {
        XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
        if (xMLDocumentHandler == null || this.fOnlyPassPrefixMappingEvents) {
            return;
        }
        xMLDocumentHandler.xmlDecl(str, str2, str3, augmentations);
    }
}
