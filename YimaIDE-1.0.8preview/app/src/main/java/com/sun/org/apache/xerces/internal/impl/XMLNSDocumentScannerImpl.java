package com.sun.org.apache.xerces.internal.impl;

import com.sun.org.apache.xerces.internal.impl.dtd.XMLDTDValidatorFilter;
import com.sun.org.apache.xerces.internal.util.NamespaceSupport;
import com.sun.org.apache.xerces.internal.util.XMLAttributesImpl;
import com.sun.org.apache.xerces.internal.util.XMLSymbols;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityManager;
import com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler;
import com.sun.org.apache.xerces.internal.xni.XMLString;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager;
import com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentSource;
import com.sun.xml.internal.stream.dtd.DTDGrammarUtil;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLNSDocumentScannerImpl extends XMLDocumentScannerImpl {
    protected boolean fBindNamespaces;
    private XMLDTDValidatorFilter fDTDValidator;
    protected boolean fPerformValidation;
    protected boolean fNotAddNSDeclAsAttribute = false;
    private boolean fXmlnsDeclared = false;

    public final class NSContentDriver extends XMLDocumentScannerImpl.ContentDriver {
        public NSContentDriver() {
            super();
        }

        private void reconfigurePipeline() {
            XMLNSDocumentScannerImpl xMLNSDocumentScannerImpl = XMLNSDocumentScannerImpl.this;
            if (xMLNSDocumentScannerImpl.fNamespaces && xMLNSDocumentScannerImpl.fDTDValidator == null) {
                XMLNSDocumentScannerImpl.this.fBindNamespaces = true;
                return;
            }
            XMLNSDocumentScannerImpl xMLNSDocumentScannerImpl2 = XMLNSDocumentScannerImpl.this;
            if (!xMLNSDocumentScannerImpl2.fNamespaces || xMLNSDocumentScannerImpl2.fDTDValidator.hasGrammar()) {
                return;
            }
            XMLNSDocumentScannerImpl xMLNSDocumentScannerImpl3 = XMLNSDocumentScannerImpl.this;
            xMLNSDocumentScannerImpl3.fBindNamespaces = true;
            xMLNSDocumentScannerImpl3.fPerformValidation = xMLNSDocumentScannerImpl3.fDTDValidator.validate();
            XMLDocumentSource documentSource = XMLNSDocumentScannerImpl.this.fDTDValidator.getDocumentSource();
            XMLDocumentHandler documentHandler = XMLNSDocumentScannerImpl.this.fDTDValidator.getDocumentHandler();
            documentSource.setDocumentHandler(documentHandler);
            if (documentHandler != null) {
                documentHandler.setDocumentSource(documentSource);
            }
            XMLNSDocumentScannerImpl.this.fDTDValidator.setDocumentSource(null);
            XMLNSDocumentScannerImpl.this.fDTDValidator.setDocumentHandler(null);
        }

        @Override // com.sun.org.apache.xerces.internal.impl.XMLDocumentScannerImpl.ContentDriver, com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl.FragmentContentDriver
        public boolean scanRootElementHook() throws IOException, XNIException {
            reconfigurePipeline();
            if (!XMLNSDocumentScannerImpl.this.scanStartElement()) {
                return false;
            }
            XMLNSDocumentScannerImpl.this.setScannerState(44);
            XMLNSDocumentScannerImpl xMLNSDocumentScannerImpl = XMLNSDocumentScannerImpl.this;
            xMLNSDocumentScannerImpl.setDriver(xMLNSDocumentScannerImpl.fTrailingMiscDriver);
            return true;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLDocumentScannerImpl, com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl
    public XMLDocumentFragmentScannerImpl.Driver createContentDriver() {
        return new NSContentDriver();
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLDocumentScannerImpl, com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl, com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentScanner
    public int next() throws IOException, XNIException {
        if (this.fScannerLastState == 2 && this.fBindNamespaces) {
            this.fScannerLastState = -1;
            this.fNamespaceContext.popContext();
        }
        int next = super.next();
        this.fScannerLastState = next;
        return next;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLDocumentScannerImpl, com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl, com.sun.org.apache.xerces.internal.impl.XMLScanner
    public void reset(PropertyManager propertyManager) {
        setPropertyManager(propertyManager);
        super.reset(propertyManager);
        this.fBindNamespaces = false;
        this.fNotAddNSDeclAsAttribute = !((Boolean) propertyManager.getProperty(Constants.ADD_NAMESPACE_DECL_AS_ATTRIBUTE)).booleanValue();
    }

    public void scanAttribute(XMLAttributesImpl xMLAttributesImpl) throws IOException, XNIException {
        String strAddSymbol;
        int length;
        this.fEntityScanner.scanQName(this.fAttributeQName, XMLScanner.NameType.ATTRIBUTENAME);
        this.fEntityScanner.skipSpaces();
        if (!this.fEntityScanner.skipChar(61, XMLScanner.NameType.ATTRIBUTE)) {
            reportFatalError("EqRequiredInAttribute", new Object[]{this.fCurrentElement.rawname, this.fAttributeQName.rawname});
        }
        this.fEntityScanner.skipSpaces();
        boolean z = false;
        boolean z2 = this.fHasExternalDTD && !this.fStandalone;
        XMLString string = getString();
        QName qName = this.fAttributeQName;
        String str = qName.localpart;
        String str2 = qName.prefix;
        if (str2 == null) {
            str2 = XMLSymbols.EMPTY_STRING;
        }
        String str3 = str2;
        boolean z3 = this.fBindNamespaces;
        String str4 = XMLSymbols.PREFIX_XMLNS;
        if (str3 == str4 || (str3 == XMLSymbols.EMPTY_STRING && str == str4)) {
            z = true;
        }
        boolean z4 = z3 & z;
        scanAttributeValue(string, this.fTempString2, qName.rawname, xMLAttributesImpl, 0, z2, this.fCurrentElement.rawname, z4);
        if (this.fBindNamespaces && z4) {
            int i = string.length;
            if (i > this.fXMLNameLimit) {
                this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MaxXMLNameLimit", new Object[]{new String(string.ch, string.offset, i), Integer.valueOf(string.length), Integer.valueOf(this.fXMLNameLimit), this.fSecurityManager.getStateLiteral(XMLSecurityManager.Limit.MAX_NAME_LIMIT)}, (short) 2);
            }
            strAddSymbol = this.fSymbolTable.addSymbol(string.ch, string.offset, string.length);
            if (str3 == str4 && str == str4) {
                this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "CantBindXMLNS", new Object[]{this.fAttributeQName}, (short) 2);
            }
            if (strAddSymbol == NamespaceContext.XMLNS_URI) {
                this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "CantBindXMLNS", new Object[]{this.fAttributeQName}, (short) 2);
            }
            if (str == XMLSymbols.PREFIX_XML) {
                if (strAddSymbol != NamespaceContext.XML_URI) {
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "CantBindXML", new Object[]{this.fAttributeQName}, (short) 2);
                }
            } else if (strAddSymbol == NamespaceContext.XML_URI) {
                this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "CantBindXML", new Object[]{this.fAttributeQName}, (short) 2);
            }
            String str5 = str != str4 ? str : XMLSymbols.EMPTY_STRING;
            String str6 = XMLSymbols.EMPTY_STRING;
            if (str5 == str6 && str == str4) {
                this.fAttributeQName.prefix = str4;
            }
            if (strAddSymbol == str6 && str != str4) {
                this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "EmptyPrefixedAttName", new Object[]{this.fAttributeQName}, (short) 2);
            }
            if (((NamespaceSupport) this.fNamespaceContext).containsPrefixInCurrentContext(str5)) {
                reportFatalError("AttributeNotUnique", new Object[]{this.fCurrentElement.rawname, this.fAttributeQName.rawname});
            }
            if (!this.fNamespaceContext.declarePrefix(str5, strAddSymbol.length() != 0 ? strAddSymbol : null)) {
                if (this.fXmlnsDeclared) {
                    reportFatalError("AttributeNotUnique", new Object[]{this.fCurrentElement.rawname, this.fAttributeQName.rawname});
                }
                this.fXmlnsDeclared = true;
            }
            if (this.fNotAddNSDeclAsAttribute) {
                return;
            }
        } else {
            strAddSymbol = null;
        }
        if (this.fBindNamespaces) {
            length = xMLAttributesImpl.getLength();
            xMLAttributesImpl.addAttributeNS(this.fAttributeQName, XMLSymbols.fCDATASymbol, null);
        } else {
            int length2 = xMLAttributesImpl.getLength();
            int iAddAttribute = xMLAttributesImpl.addAttribute(this.fAttributeQName, XMLSymbols.fCDATASymbol, null);
            if (length2 == xMLAttributesImpl.getLength()) {
                reportFatalError("AttributeNotUnique", new Object[]{this.fCurrentElement.rawname, this.fAttributeQName.rawname});
            }
            length = iAddAttribute;
        }
        xMLAttributesImpl.setValue(length, strAddSymbol, string);
        xMLAttributesImpl.setSpecified(length, true);
        String str7 = this.fAttributeQName.prefix;
        if (str7 != null) {
            xMLAttributesImpl.setURI(length, this.fNamespaceContext.getURI(str7));
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl
    public boolean scanStartElement() throws IOException, XNIException {
        QName qNameCheckDuplicatesNS;
        if (this.fSkip && !this.fAdd) {
            QName next = this.fElementStack.getNext();
            boolean zSkipString = this.fEntityScanner.skipString(next.rawname);
            this.fSkip = zSkipString;
            XMLDocumentFragmentScannerImpl.ElementStack elementStack = this.fElementStack;
            if (zSkipString) {
                elementStack.push();
                this.fElementQName = next;
            } else {
                elementStack.reposition();
            }
        }
        if (!this.fSkip || this.fAdd) {
            QName qNameNextElement = this.fElementStack.nextElement();
            this.fElementQName = qNameNextElement;
            boolean z = this.fNamespaces;
            XMLEntityScanner xMLEntityScanner = this.fEntityScanner;
            if (z) {
                xMLEntityScanner.scanQName(qNameNextElement, XMLScanner.NameType.ELEMENTSTART);
            } else {
                String strScanName = xMLEntityScanner.scanName(XMLScanner.NameType.ELEMENTSTART);
                this.fElementQName.setValues(null, strScanName, strScanName, null);
            }
        }
        if (this.fAdd) {
            this.fElementStack.matchElement(this.fElementQName);
        }
        QName qName = this.fElementQName;
        this.fCurrentElement = qName;
        String str = qName.rawname;
        checkDepth(str);
        if (this.fBindNamespaces) {
            this.fNamespaceContext.pushContext();
            if (this.fScannerState == 26 && this.fPerformValidation) {
                this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_GRAMMAR_NOT_FOUND", new Object[]{str}, (short) 1);
                String str2 = this.fDoctypeName;
                if (str2 == null || !str2.equals(str)) {
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "RootElementTypeMustMatchDoctypedecl", new Object[]{this.fDoctypeName, str}, (short) 1);
                }
            }
        }
        this.fEmptyElement = false;
        this.fAttributes.removeAllAttributes();
        if (!seekCloseOfStartTag()) {
            this.fReadingAttributes = true;
            this.fAttributeCacheUsedCount = 0;
            this.fStringBufferIndex = 0;
            this.fAddDefaultAttr = true;
            this.fXmlnsDeclared = false;
            do {
                scanAttribute((XMLAttributesImpl) this.fAttributes);
                XMLSecurityManager xMLSecurityManager = this.fSecurityManager;
                if (xMLSecurityManager != null && !xMLSecurityManager.isNoLimit(this.fElementAttributeLimit)) {
                    int length = this.fAttributes.getLength();
                    int i = this.fElementAttributeLimit;
                    if (length > i) {
                        this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "ElementAttributeLimit", new Object[]{str, Integer.valueOf(i)}, (short) 2);
                    }
                }
            } while (!seekCloseOfStartTag());
            this.fReadingAttributes = false;
        }
        if (this.fBindNamespaces) {
            QName qName2 = this.fElementQName;
            if (qName2.prefix == XMLSymbols.PREFIX_XMLNS) {
                this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "ElementXMLNSPrefix", new Object[]{qName2.rawname}, (short) 2);
            }
            QName qName3 = this.fElementQName;
            String str3 = qName3.prefix;
            if (str3 == null) {
                str3 = XMLSymbols.EMPTY_STRING;
            }
            qName3.uri = this.fNamespaceContext.getURI(str3);
            QName qName4 = this.fCurrentElement;
            QName qName5 = this.fElementQName;
            qName4.uri = qName5.uri;
            if (qName5.prefix == null && qName5.uri != null) {
                qName5.prefix = XMLSymbols.EMPTY_STRING;
            }
            String str4 = qName5.prefix;
            if (str4 != null && qName5.uri == null) {
                this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "ElementPrefixUnbound", new Object[]{str4, qName5.rawname}, (short) 2);
            }
            int length2 = this.fAttributes.getLength();
            for (int i2 = 0; i2 < length2; i2++) {
                this.fAttributes.getName(i2, this.fAttributeQName);
                String str5 = this.fAttributeQName.prefix;
                if (str5 == null) {
                    str5 = XMLSymbols.EMPTY_STRING;
                }
                String uri = this.fNamespaceContext.getURI(str5);
                QName qName6 = this.fAttributeQName;
                String str6 = qName6.uri;
                if ((str6 == null || str6 != uri) && str5 != XMLSymbols.EMPTY_STRING) {
                    qName6.uri = uri;
                    if (uri == null) {
                        this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "AttributePrefixUnbound", new Object[]{this.fElementQName.rawname, qName6.rawname, str5}, (short) 2);
                    }
                    this.fAttributes.setURI(i2, uri);
                }
            }
            if (length2 > 1 && (qNameCheckDuplicatesNS = this.fAttributes.checkDuplicatesNS()) != null) {
                String str7 = qNameCheckDuplicatesNS.uri;
                XMLErrorReporter xMLErrorReporter = this.fErrorReporter;
                if (str7 != null) {
                    xMLErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "AttributeNSNotUnique", new Object[]{this.fElementQName.rawname, qNameCheckDuplicatesNS.localpart, str7}, (short) 2);
                } else {
                    xMLErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "AttributeNotUnique", new Object[]{this.fElementQName.rawname, qNameCheckDuplicatesNS.rawname}, (short) 2);
                }
            }
        }
        if (this.fEmptyElement) {
            int i3 = this.fMarkupDepth - 1;
            this.fMarkupDepth = i3;
            if (i3 < this.fEntityStack[this.fEntityDepth - 1]) {
                reportFatalError("ElementEntityMismatch", new Object[]{this.fCurrentElement.rawname});
            }
            XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
            if (xMLDocumentHandler != null) {
                xMLDocumentHandler.emptyElement(this.fElementQName, this.fAttributes, null);
            }
            this.fScanEndElement = true;
            this.fElementStack.popElement();
        } else {
            DTDGrammarUtil dTDGrammarUtil = this.dtdGrammarUtil;
            if (dTDGrammarUtil != null) {
                dTDGrammarUtil.startElement(this.fElementQName, this.fAttributes);
            }
            XMLDocumentHandler xMLDocumentHandler2 = this.fDocumentHandler;
            if (xMLDocumentHandler2 != null) {
                xMLDocumentHandler2.startElement(this.fElementQName, this.fAttributes, null);
            }
        }
        return this.fEmptyElement;
    }

    public void setDTDValidator(XMLDTDValidatorFilter xMLDTDValidatorFilter) {
        this.fDTDValidator = xMLDTDValidatorFilter;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLDocumentScannerImpl, com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl, com.sun.org.apache.xerces.internal.impl.XMLScanner, com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public void reset(XMLComponentManager xMLComponentManager) throws XMLConfigurationException {
        super.reset(xMLComponentManager);
        this.fNotAddNSDeclAsAttribute = false;
        this.fPerformValidation = false;
        this.fBindNamespaces = false;
    }
}
