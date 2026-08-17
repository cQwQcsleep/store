package com.sun.org.apache.xerces.internal.impl.dtd;

import com.sun.org.apache.xerces.internal.util.XMLSymbols;
import com.sun.org.apache.xerces.internal.xni.Augmentations;
import com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xni.XMLAttributes;
import com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler;
import com.sun.org.apache.xerces.internal.xni.XNIException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XML11NSDTDValidator extends XML11DTDValidator {
    private QName fAttributeQName = new QName();

    @Override // com.sun.org.apache.xerces.internal.impl.dtd.XMLDTDValidator
    public void endNamespaceScope(QName qName, Augmentations augmentations, boolean z) throws XNIException {
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
        if (xMLDocumentHandler != null && !z) {
            xMLDocumentHandler.endElement(qName, augmentations);
        }
        this.fNamespaceContext.popContext();
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dtd.XMLDTDValidator
    public final void startNamespaceScope(QName qName, XMLAttributes xMLAttributes, Augmentations augmentations) throws XNIException {
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
                if (localName == str) {
                    localName = XMLSymbols.EMPTY_STRING;
                }
                NamespaceContext namespaceContext = this.fNamespaceContext;
                if (strAddSymbol.length() == 0) {
                    strAddSymbol = null;
                }
                namespaceContext.declarePrefix(localName, strAddSymbol);
            }
        }
        String str2 = qName.prefix;
        if (str2 == null) {
            str2 = XMLSymbols.EMPTY_STRING;
        }
        String uri = this.fNamespaceContext.getURI(str2);
        qName.uri = uri;
        if (qName.prefix == null && uri != null) {
            qName.prefix = XMLSymbols.EMPTY_STRING;
        }
        String str3 = qName.prefix;
        if (str3 != null && uri == null) {
            this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "ElementPrefixUnbound", new Object[]{str3, qName.rawname}, (short) 2);
        }
        for (int i2 = 0; i2 < length; i2++) {
            xMLAttributes.getName(i2, this.fAttributeQName);
            QName qName2 = this.fAttributeQName;
            String str4 = qName2.prefix;
            if (str4 == null) {
                str4 = XMLSymbols.EMPTY_STRING;
            }
            String str5 = qName2.rawname;
            String str6 = XMLSymbols.PREFIX_XMLNS;
            if (str5 == str6) {
                qName2.uri = this.fNamespaceContext.getURI(str6);
                xMLAttributes.setName(i2, this.fAttributeQName);
            } else if (str4 != XMLSymbols.EMPTY_STRING) {
                qName2.uri = this.fNamespaceContext.getURI(str4);
                if (this.fAttributeQName.uri == null) {
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "AttributePrefixUnbound", new Object[]{qName.rawname, str5, str4}, (short) 2);
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
    }
}
