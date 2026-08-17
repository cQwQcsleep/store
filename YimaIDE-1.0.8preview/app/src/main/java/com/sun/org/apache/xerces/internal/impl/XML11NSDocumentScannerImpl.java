package com.sun.org.apache.xerces.internal.impl;

import com.sun.org.apache.xerces.internal.impl.dtd.XMLDTDValidatorFilter;
import com.sun.org.apache.xerces.internal.util.XMLAttributesImpl;
import com.sun.org.apache.xerces.internal.util.XMLSymbols;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityManager;
import com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager;
import com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentSource;
import com.sun.xml.internal.stream.dtd.DTDGrammarUtil;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XML11NSDocumentScannerImpl extends XML11DocumentScannerImpl {
    protected boolean fBindNamespaces;
    private XMLDTDValidatorFilter fDTDValidator;
    protected boolean fPerformValidation;
    private boolean fSawSpace;

    public final class NS11ContentDriver extends XMLDocumentScannerImpl.ContentDriver {
        public NS11ContentDriver() {
            super();
        }

        private void reconfigurePipeline() {
            XMLDTDValidatorFilter xMLDTDValidatorFilter = XML11NSDocumentScannerImpl.this.fDTDValidator;
            XML11NSDocumentScannerImpl xML11NSDocumentScannerImpl = XML11NSDocumentScannerImpl.this;
            if (xMLDTDValidatorFilter == null) {
                xML11NSDocumentScannerImpl.fBindNamespaces = true;
                return;
            }
            if (xML11NSDocumentScannerImpl.fDTDValidator.hasGrammar()) {
                return;
            }
            XML11NSDocumentScannerImpl xML11NSDocumentScannerImpl2 = XML11NSDocumentScannerImpl.this;
            xML11NSDocumentScannerImpl2.fBindNamespaces = true;
            xML11NSDocumentScannerImpl2.fPerformValidation = xML11NSDocumentScannerImpl2.fDTDValidator.validate();
            XMLDocumentSource documentSource = XML11NSDocumentScannerImpl.this.fDTDValidator.getDocumentSource();
            XMLDocumentHandler documentHandler = XML11NSDocumentScannerImpl.this.fDTDValidator.getDocumentHandler();
            documentSource.setDocumentHandler(documentHandler);
            if (documentHandler != null) {
                documentHandler.setDocumentSource(documentSource);
            }
            XML11NSDocumentScannerImpl.this.fDTDValidator.setDocumentSource(null);
            XML11NSDocumentScannerImpl.this.fDTDValidator.setDocumentHandler(null);
        }

        @Override // com.sun.org.apache.xerces.internal.impl.XMLDocumentScannerImpl.ContentDriver, com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl.FragmentContentDriver
        public boolean scanRootElementHook() throws IOException, XNIException {
            XML11NSDocumentScannerImpl xML11NSDocumentScannerImpl = XML11NSDocumentScannerImpl.this;
            if (xML11NSDocumentScannerImpl.fExternalSubsetResolver == null || xML11NSDocumentScannerImpl.fSeenDoctypeDecl || xML11NSDocumentScannerImpl.fDisallowDoctype || !(xML11NSDocumentScannerImpl.fValidation || xML11NSDocumentScannerImpl.fLoadExternalDTD)) {
                reconfigurePipeline();
                if (!XML11NSDocumentScannerImpl.this.scanStartElement()) {
                    return false;
                }
                XML11NSDocumentScannerImpl.this.setScannerState(44);
                XML11NSDocumentScannerImpl xML11NSDocumentScannerImpl2 = XML11NSDocumentScannerImpl.this;
                xML11NSDocumentScannerImpl2.setDriver(xML11NSDocumentScannerImpl2.fTrailingMiscDriver);
                return true;
            }
            xML11NSDocumentScannerImpl.scanStartElementName();
            resolveExternalSubsetAndRead();
            reconfigurePipeline();
            if (!XML11NSDocumentScannerImpl.this.scanStartElementAfterName()) {
                return false;
            }
            XML11NSDocumentScannerImpl.this.setScannerState(44);
            XML11NSDocumentScannerImpl xML11NSDocumentScannerImpl3 = XML11NSDocumentScannerImpl.this;
            xML11NSDocumentScannerImpl3.setDriver(xML11NSDocumentScannerImpl3.fTrailingMiscDriver);
            return true;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLDocumentScannerImpl, com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl
    public XMLDocumentFragmentScannerImpl.Driver createContentDriver() {
        return new NS11ContentDriver();
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

    @Override // com.sun.org.apache.xerces.internal.impl.XMLDocumentScannerImpl, com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl, com.sun.org.apache.xerces.internal.impl.XMLScanner, com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public void reset(XMLComponentManager xMLComponentManager) throws XMLConfigurationException {
        super.reset(xMLComponentManager);
        this.fPerformValidation = false;
        this.fBindNamespaces = false;
    }

    public void scanAttribute(XMLAttributesImpl xMLAttributesImpl) throws IOException, XNIException {
        int i;
        this.fEntityScanner.scanQName(this.fAttributeQName, XMLScanner.NameType.ATTRIBUTENAME);
        this.fEntityScanner.skipSpaces();
        if (!this.fEntityScanner.skipChar(61, XMLScanner.NameType.ATTRIBUTE)) {
            reportFatalError("EqRequiredInAttribute", new Object[]{this.fCurrentElement.rawname, this.fAttributeQName.rawname});
        }
        this.fEntityScanner.skipSpaces();
        if (this.fBindNamespaces) {
            int length = xMLAttributesImpl.getLength();
            xMLAttributesImpl.addAttributeNS(this.fAttributeQName, XMLSymbols.fCDATASymbol, null);
            i = length;
        } else {
            int length2 = xMLAttributesImpl.getLength();
            int iAddAttribute = xMLAttributesImpl.addAttribute(this.fAttributeQName, XMLSymbols.fCDATASymbol, null);
            if (length2 == xMLAttributesImpl.getLength()) {
                reportFatalError("AttributeNotUnique", new Object[]{this.fCurrentElement.rawname, this.fAttributeQName.rawname});
            }
            i = iAddAttribute;
        }
        boolean z = false;
        boolean z2 = this.fHasExternalDTD && !this.fStandalone;
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
        scanAttributeValue(this.fTempString, this.fTempString2, qName.rawname, z2, this.fCurrentElement.rawname, z4);
        String string = this.fTempString.toString();
        xMLAttributesImpl.setValue(i, string);
        xMLAttributesImpl.setNonNormalizedValue(i, this.fTempString2.toString());
        xMLAttributesImpl.setSpecified(i, true);
        if (this.fBindNamespaces) {
            if (!z4) {
                String str5 = this.fAttributeQName.prefix;
                if (str5 != null) {
                    xMLAttributesImpl.setURI(i, this.fNamespaceContext.getURI(str5));
                    return;
                }
                return;
            }
            if (string.length() > this.fXMLNameLimit) {
                this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MaxXMLNameLimit", new Object[]{string, Integer.valueOf(string.length()), Integer.valueOf(this.fXMLNameLimit), this.fSecurityManager.getStateLiteral(XMLSecurityManager.Limit.MAX_NAME_LIMIT)}, (short) 2);
            }
            String strAddSymbol = this.fSymbolTable.addSymbol(string);
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
            if (str == str4) {
                str = XMLSymbols.EMPTY_STRING;
            }
            this.fNamespaceContext.declarePrefix(str, strAddSymbol.length() != 0 ? strAddSymbol : null);
            xMLAttributesImpl.setURI(i, this.fNamespaceContext.getURI(str4));
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl
    public int scanEndElement() throws IOException, XNIException {
        QName qNamePopElement = this.fElementStack.popElement();
        if (!this.fEntityScanner.skipString(qNamePopElement.rawname)) {
            reportFatalError("ETagRequired", new Object[]{qNamePopElement.rawname});
        }
        this.fEntityScanner.skipSpaces();
        if (!this.fEntityScanner.skipChar(62, XMLScanner.NameType.ELEMENTEND)) {
            reportFatalError("ETagUnterminated", new Object[]{qNamePopElement.rawname});
        }
        int i = this.fMarkupDepth - 2;
        this.fMarkupDepth = i;
        if (i < this.fEntityStack[this.fEntityDepth - 1]) {
            reportFatalError("ElementEntityMismatch", new Object[]{qNamePopElement.rawname});
        }
        XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
        if (xMLDocumentHandler != null) {
            xMLDocumentHandler.endElement(qNamePopElement, null);
        }
        DTDGrammarUtil dTDGrammarUtil = this.dtdGrammarUtil;
        if (dTDGrammarUtil != null) {
            dTDGrammarUtil.endElement(qNamePopElement);
        }
        return this.fMarkupDepth;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl
    public boolean scanStartElement() throws IOException, XNIException {
        int i;
        boolean z;
        QName qNameCheckDuplicatesNS;
        this.fEntityScanner.scanQName(this.fElementQName, XMLScanner.NameType.ELEMENTSTART);
        String str = this.fElementQName.rawname;
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
        this.fCurrentElement = this.fElementStack.pushElement(this.fElementQName);
        this.fAttributes.removeAllAttributes();
        while (true) {
            boolean zSkipSpaces = this.fEntityScanner.skipSpaces();
            int iPeekChar = this.fEntityScanner.peekChar();
            if (iPeekChar == 62) {
                this.fEntityScanner.scanChar(null);
                z = false;
                break;
            }
            if (iPeekChar == 47) {
                this.fEntityScanner.scanChar(null);
                if (!this.fEntityScanner.skipChar(62, null)) {
                    reportFatalError("ElementUnterminated", new Object[]{str});
                }
                z = true;
                break;
            }
            if ((!isValidNameStartChar(iPeekChar) || !zSkipSpaces) && (!isValidNameStartHighSurrogate(iPeekChar) || !zSkipSpaces)) {
                reportFatalError("ElementUnterminated", new Object[]{str});
            }
            scanAttribute((XMLAttributesImpl) this.fAttributes);
            XMLSecurityManager xMLSecurityManager = this.fSecurityManager;
            if (xMLSecurityManager != null && !xMLSecurityManager.isNoLimit(this.fElementAttributeLimit)) {
                int length = this.fAttributes.getLength();
                int i2 = this.fElementAttributeLimit;
                if (length > i2) {
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "ElementAttributeLimit", new Object[]{str, Integer.valueOf(i2)}, (short) 2);
                }
            }
        }
        if (this.fBindNamespaces) {
            QName qName = this.fElementQName;
            if (qName.prefix == XMLSymbols.PREFIX_XMLNS) {
                this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "ElementXMLNSPrefix", new Object[]{qName.rawname}, (short) 2);
            }
            QName qName2 = this.fElementQName;
            String str3 = qName2.prefix;
            if (str3 == null) {
                str3 = XMLSymbols.EMPTY_STRING;
            }
            qName2.uri = this.fNamespaceContext.getURI(str3);
            QName qName3 = this.fCurrentElement;
            QName qName4 = this.fElementQName;
            qName3.uri = qName4.uri;
            if (qName4.prefix == null && qName4.uri != null) {
                String str4 = XMLSymbols.EMPTY_STRING;
                qName4.prefix = str4;
                qName3.prefix = str4;
            }
            String str5 = qName4.prefix;
            if (str5 != null && qName4.uri == null) {
                this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "ElementPrefixUnbound", new Object[]{str5, qName4.rawname}, (short) 2);
            }
            int length2 = this.fAttributes.getLength();
            for (i = 0; i < length2; i++) {
                this.fAttributes.getName(i, this.fAttributeQName);
                String str6 = this.fAttributeQName.prefix;
                if (str6 == null) {
                    str6 = XMLSymbols.EMPTY_STRING;
                }
                String uri = this.fNamespaceContext.getURI(str6);
                QName qName5 = this.fAttributeQName;
                String str7 = qName5.uri;
                if ((str7 == null || str7 != uri) && str6 != XMLSymbols.EMPTY_STRING) {
                    qName5.uri = uri;
                    if (uri == null) {
                        this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "AttributePrefixUnbound", new Object[]{this.fElementQName.rawname, qName5.rawname, str6}, (short) 2);
                    }
                    this.fAttributes.setURI(i, uri);
                }
            }
            if (length2 > 1 && (qNameCheckDuplicatesNS = this.fAttributes.checkDuplicatesNS()) != null) {
                String str8 = qNameCheckDuplicatesNS.uri;
                XMLErrorReporter xMLErrorReporter = this.fErrorReporter;
                if (str8 != null) {
                    xMLErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "AttributeNSNotUnique", new Object[]{this.fElementQName.rawname, qNameCheckDuplicatesNS.localpart, str8}, (short) 2);
                } else {
                    xMLErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "AttributeNotUnique", new Object[]{this.fElementQName.rawname, qNameCheckDuplicatesNS.rawname}, (short) 2);
                }
            }
        }
        if (!z) {
            DTDGrammarUtil dTDGrammarUtil = this.dtdGrammarUtil;
            if (dTDGrammarUtil != null) {
                dTDGrammarUtil.startElement(this.fElementQName, this.fAttributes);
            }
            XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
            if (xMLDocumentHandler != null) {
                xMLDocumentHandler.startElement(this.fElementQName, this.fAttributes, null);
            }
            return z;
        }
        int i3 = this.fMarkupDepth - 1;
        this.fMarkupDepth = i3;
        if (i3 < this.fEntityStack[this.fEntityDepth - 1]) {
            reportFatalError("ElementEntityMismatch", new Object[]{this.fCurrentElement.rawname});
        }
        XMLDocumentHandler xMLDocumentHandler2 = this.fDocumentHandler;
        if (xMLDocumentHandler2 != null) {
            xMLDocumentHandler2.emptyElement(this.fElementQName, this.fAttributes, null);
        }
        this.fScanEndElement = true;
        this.fElementStack.popElement();
        return z;
    }

    public boolean scanStartElementAfterName() throws IOException, XNIException {
        int i;
        boolean z;
        QName qNameCheckDuplicatesNS;
        String str = this.fElementQName.rawname;
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
        this.fCurrentElement = this.fElementStack.pushElement(this.fElementQName);
        this.fAttributes.removeAllAttributes();
        while (true) {
            int iPeekChar = this.fEntityScanner.peekChar();
            if (iPeekChar == 62) {
                this.fEntityScanner.scanChar(null);
                z = false;
                break;
            }
            if (iPeekChar == 47) {
                this.fEntityScanner.scanChar(null);
                if (!this.fEntityScanner.skipChar(62, null)) {
                    reportFatalError("ElementUnterminated", new Object[]{str});
                }
                z = true;
                break;
            }
            if ((!isValidNameStartChar(iPeekChar) || !this.fSawSpace) && (!isValidNameStartHighSurrogate(iPeekChar) || !this.fSawSpace)) {
                reportFatalError("ElementUnterminated", new Object[]{str});
            }
            scanAttribute((XMLAttributesImpl) this.fAttributes);
            this.fSawSpace = this.fEntityScanner.skipSpaces();
        }
        if (this.fBindNamespaces) {
            QName qName = this.fElementQName;
            if (qName.prefix == XMLSymbols.PREFIX_XMLNS) {
                this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "ElementXMLNSPrefix", new Object[]{qName.rawname}, (short) 2);
            }
            QName qName2 = this.fElementQName;
            String str3 = qName2.prefix;
            if (str3 == null) {
                str3 = XMLSymbols.EMPTY_STRING;
            }
            qName2.uri = this.fNamespaceContext.getURI(str3);
            QName qName3 = this.fCurrentElement;
            QName qName4 = this.fElementQName;
            qName3.uri = qName4.uri;
            if (qName4.prefix == null && qName4.uri != null) {
                String str4 = XMLSymbols.EMPTY_STRING;
                qName4.prefix = str4;
                qName3.prefix = str4;
            }
            String str5 = qName4.prefix;
            if (str5 != null && qName4.uri == null) {
                this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "ElementPrefixUnbound", new Object[]{str5, qName4.rawname}, (short) 2);
            }
            int length = this.fAttributes.getLength();
            for (i = 0; i < length; i++) {
                this.fAttributes.getName(i, this.fAttributeQName);
                String str6 = this.fAttributeQName.prefix;
                if (str6 == null) {
                    str6 = XMLSymbols.EMPTY_STRING;
                }
                String uri = this.fNamespaceContext.getURI(str6);
                QName qName5 = this.fAttributeQName;
                String str7 = qName5.uri;
                if ((str7 == null || str7 != uri) && str6 != XMLSymbols.EMPTY_STRING) {
                    qName5.uri = uri;
                    if (uri == null) {
                        this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "AttributePrefixUnbound", new Object[]{this.fElementQName.rawname, qName5.rawname, str6}, (short) 2);
                    }
                    this.fAttributes.setURI(i, uri);
                }
            }
            if (length > 1 && (qNameCheckDuplicatesNS = this.fAttributes.checkDuplicatesNS()) != null) {
                String str8 = qNameCheckDuplicatesNS.uri;
                XMLErrorReporter xMLErrorReporter = this.fErrorReporter;
                if (str8 != null) {
                    xMLErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "AttributeNSNotUnique", new Object[]{this.fElementQName.rawname, qNameCheckDuplicatesNS.localpart, str8}, (short) 2);
                } else {
                    xMLErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "AttributeNotUnique", new Object[]{this.fElementQName.rawname, qNameCheckDuplicatesNS.rawname}, (short) 2);
                }
            }
        }
        XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
        if (xMLDocumentHandler != null) {
            if (z) {
                int i2 = this.fMarkupDepth - 1;
                this.fMarkupDepth = i2;
                if (i2 < this.fEntityStack[this.fEntityDepth - 1]) {
                    reportFatalError("ElementEntityMismatch", new Object[]{this.fCurrentElement.rawname});
                }
                this.fDocumentHandler.emptyElement(this.fElementQName, this.fAttributes, null);
                if (this.fBindNamespaces) {
                    this.fNamespaceContext.popContext();
                }
                this.fElementStack.popElement();
                return z;
            }
            xMLDocumentHandler.startElement(this.fElementQName, this.fAttributes, null);
        }
        return z;
    }

    public void scanStartElementName() throws IOException, XNIException {
        this.fEntityScanner.scanQName(this.fElementQName, XMLScanner.NameType.ELEMENTSTART);
        this.fSawSpace = this.fEntityScanner.skipSpaces();
    }

    public void setDTDValidator(XMLDTDValidatorFilter xMLDTDValidatorFilter) {
        this.fDTDValidator = xMLDTDValidatorFilter;
    }
}
