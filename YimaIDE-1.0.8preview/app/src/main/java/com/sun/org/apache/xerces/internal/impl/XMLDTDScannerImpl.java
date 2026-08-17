package com.sun.org.apache.xerces.internal.impl;

import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.util.XMLAttributesImpl;
import com.sun.org.apache.xerces.internal.util.XMLChar;
import com.sun.org.apache.xerces.internal.util.XMLStringBuffer;
import com.sun.org.apache.xerces.internal.utils.XMLLimitAnalyzer;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityManager;
import com.sun.org.apache.xerces.internal.xni.Augmentations;
import com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler;
import com.sun.org.apache.xerces.internal.xni.XMLDTDHandler;
import com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier;
import com.sun.org.apache.xerces.internal.xni.XMLString;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponent;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager;
import com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLDTDScanner;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import com.sun.xml.internal.stream.XMLEntityStorage;
import com.sun.xml.internal.stream.dtd.nonvalidating.DTDGrammar;
import defpackage.u01;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLDTDScannerImpl extends XMLScanner implements XMLDTDScanner, XMLComponent, XMLEntityHandler {
    private static final boolean DEBUG_SCANNER_STATE = false;
    protected static final int SCANNER_STATE_END_OF_INPUT = 0;
    protected static final int SCANNER_STATE_MARKUP_DECL = 2;
    protected static final int SCANNER_STATE_TEXT_DECL = 1;
    private XMLAttributesImpl fAttributes;
    private int fContentDepth;
    private int[] fContentStack;
    protected XMLDTDContentModelHandler fDTDContentModelHandler;
    public XMLDTDHandler fDTDHandler;
    private String[] fEnumeration;
    private int fEnumerationCount;
    private int fExtEntityDepth;
    private XMLStringBuffer fIgnoreConditionalBuffer;
    private int fIncludeSectDepth;
    private XMLString fLiteral;
    private XMLString fLiteral2;
    private int fMarkUpDepth;
    private int fPEDepth;
    private boolean[] fPEReport;
    private int[] fPEStack;
    protected int fScannerState;
    protected boolean fSeenExternalDTD;
    protected boolean fSeenExternalPE;
    protected boolean fStandalone;
    private boolean fStartDTDCalled;
    private XMLString fString;
    private XMLStringBuffer fStringBuffer;
    private XMLStringBuffer fStringBuffer2;
    private String[] fStrings;
    boolean nonValidatingMode;
    DTDGrammar nvGrammarInfo;
    private static final String[] RECOGNIZED_FEATURES = {"http://xml.org/sax/features/validation", "http://apache.org/xml/features/scanner/notify-char-refs"};
    private static final Boolean[] FEATURE_DEFAULTS = {null, Boolean.FALSE};
    private static final String[] RECOGNIZED_PROPERTIES = {"http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/entity-manager"};
    private static final Object[] PROPERTY_DEFAULTS = {null, null, null};

    public XMLDTDScannerImpl(SymbolTable symbolTable, XMLErrorReporter xMLErrorReporter, XMLEntityManager xMLEntityManager) {
        this.fDTDHandler = null;
        this.fAttributes = new XMLAttributesImpl();
        this.fContentStack = new int[5];
        this.fPEStack = new int[5];
        this.fPEReport = new boolean[5];
        this.fStrings = new String[3];
        this.fString = new XMLString();
        this.fStringBuffer = new XMLStringBuffer();
        this.fStringBuffer2 = new XMLStringBuffer();
        this.fLiteral = new XMLString();
        this.fLiteral2 = new XMLString();
        this.fEnumeration = new String[5];
        this.fIgnoreConditionalBuffer = new XMLStringBuffer(128);
        this.nvGrammarInfo = null;
        this.nonValidatingMode = false;
        this.fSymbolTable = symbolTable;
        this.fErrorReporter = xMLErrorReporter;
        this.fEntityManager = xMLEntityManager;
        xMLEntityManager.setProperty("http://apache.org/xml/properties/internal/symbol-table", symbolTable);
    }

    private final void ensureEnumerationSize(int i) {
        String[] strArr = this.fEnumeration;
        if (strArr.length == i) {
            String[] strArr2 = new String[i * 2];
            System.arraycopy(strArr, 0, strArr2, 0, i);
            this.fEnumeration = strArr2;
        }
    }

    private static String getScannerStateName(int i) {
        return "??? (" + i + ')';
    }

    private void init() {
        this.fStartDTDCalled = false;
        this.fExtEntityDepth = 0;
        this.fIncludeSectDepth = 0;
        this.fMarkUpDepth = 0;
        this.fPEDepth = 0;
        this.fStandalone = false;
        this.fSeenExternalDTD = false;
        this.fSeenExternalPE = false;
        setScannerState(1);
        XMLEntityManager xMLEntityManager = this.fEntityManager;
        this.fLimitAnalyzer = xMLEntityManager.fLimitAnalyzer;
        this.fSecurityManager = xMLEntityManager.fSecurityManager;
    }

    private final boolean peekReportEntity() {
        return this.fPEReport[this.fPEDepth - 1];
    }

    private final int popContentStack() {
        int[] iArr = this.fContentStack;
        int i = this.fContentDepth - 1;
        this.fContentDepth = i;
        return iArr[i];
    }

    private final int popPEStack() {
        int[] iArr = this.fPEStack;
        int i = this.fPEDepth - 1;
        this.fPEDepth = i;
        return iArr[i];
    }

    private final void pushContentStack(int i) {
        int[] iArr = this.fContentStack;
        int length = iArr.length;
        int i2 = this.fContentDepth;
        if (length == i2) {
            int[] iArr2 = new int[i2 * 2];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            this.fContentStack = iArr2;
        }
        int[] iArr3 = this.fContentStack;
        int i3 = this.fContentDepth;
        this.fContentDepth = i3 + 1;
        iArr3[i3] = i;
    }

    private final void pushPEStack(int i, boolean z) {
        int[] iArr = this.fPEStack;
        int length = iArr.length;
        int i2 = this.fPEDepth;
        if (length == i2) {
            int[] iArr2 = new int[i2 * 2];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            this.fPEStack = iArr2;
            int i3 = this.fPEDepth;
            boolean[] zArr = new boolean[i3 * 2];
            System.arraycopy(this.fPEReport, 0, zArr, 0, i3);
            this.fPEReport = zArr;
        }
        boolean[] zArr2 = this.fPEReport;
        int i4 = this.fPEDepth;
        zArr2[i4] = z;
        int[] iArr3 = this.fPEStack;
        this.fPEDepth = i4 + 1;
        iArr3[i4] = i;
    }

    private final String scanAttType(String str, String str2) throws IOException, XNIException {
        int iScanChar;
        int iScanChar2;
        this.fEnumerationCount = 0;
        if (this.fEntityScanner.skipString("CDATA")) {
            return "CDATA";
        }
        if (this.fEntityScanner.skipString(SchemaSymbols.ATTVAL_IDREFS)) {
            return SchemaSymbols.ATTVAL_IDREFS;
        }
        if (this.fEntityScanner.skipString(SchemaSymbols.ATTVAL_IDREF)) {
            return SchemaSymbols.ATTVAL_IDREF;
        }
        if (this.fEntityScanner.skipString(SchemaSymbols.ATTVAL_ID)) {
            return SchemaSymbols.ATTVAL_ID;
        }
        if (this.fEntityScanner.skipString(SchemaSymbols.ATTVAL_ENTITY)) {
            return SchemaSymbols.ATTVAL_ENTITY;
        }
        if (this.fEntityScanner.skipString(SchemaSymbols.ATTVAL_ENTITIES)) {
            return SchemaSymbols.ATTVAL_ENTITIES;
        }
        if (this.fEntityScanner.skipString(SchemaSymbols.ATTVAL_NMTOKENS)) {
            return SchemaSymbols.ATTVAL_NMTOKENS;
        }
        if (this.fEntityScanner.skipString(SchemaSymbols.ATTVAL_NMTOKEN)) {
            return SchemaSymbols.ATTVAL_NMTOKEN;
        }
        if (!this.fEntityScanner.skipString(SchemaSymbols.ATTVAL_NOTATION)) {
            if (this.fEntityScanner.scanChar(null) != 40) {
                reportFatalError("AttTypeRequiredInAttDef", new Object[]{str, str2});
            }
            this.fMarkUpDepth++;
            do {
                skipSeparator(false, !scanningInternalSubset());
                String strScanNmtoken = this.fEntityScanner.scanNmtoken();
                if (strScanNmtoken == null) {
                    reportFatalError("MSG_NMTOKEN_REQUIRED_IN_ENUMERATION", new Object[]{str, str2});
                }
                ensureEnumerationSize(this.fEnumerationCount + 1);
                String[] strArr = this.fEnumeration;
                int i = this.fEnumerationCount;
                this.fEnumerationCount = i + 1;
                strArr[i] = strScanNmtoken;
                skipSeparator(false, !scanningInternalSubset());
                iScanChar = this.fEntityScanner.scanChar(null);
            } while (iScanChar == 124);
            if (iScanChar != 41) {
                reportFatalError("EnumerationUnterminated", new Object[]{str, str2});
            }
            this.fMarkUpDepth--;
            return "ENUMERATION";
        }
        if (!skipSeparator(true, !scanningInternalSubset())) {
            reportFatalError("MSG_SPACE_REQUIRED_AFTER_NOTATION_IN_NOTATIONTYPE", new Object[]{str, str2});
        }
        if (this.fEntityScanner.scanChar(null) != 40) {
            reportFatalError("MSG_OPEN_PAREN_REQUIRED_IN_NOTATIONTYPE", new Object[]{str, str2});
        }
        this.fMarkUpDepth++;
        do {
            skipSeparator(false, !scanningInternalSubset());
            String strScanName = this.fEntityScanner.scanName(XMLScanner.NameType.ATTRIBUTENAME);
            if (strScanName == null) {
                reportFatalError("MSG_NAME_REQUIRED_IN_NOTATIONTYPE", new Object[]{str, str2});
            }
            ensureEnumerationSize(this.fEnumerationCount + 1);
            String[] strArr2 = this.fEnumeration;
            int i2 = this.fEnumerationCount;
            this.fEnumerationCount = i2 + 1;
            strArr2[i2] = strScanName;
            skipSeparator(false, !scanningInternalSubset());
            iScanChar2 = this.fEntityScanner.scanChar(null);
        } while (iScanChar2 == 124);
        if (iScanChar2 != 41) {
            reportFatalError("NotationTypeUnterminated", new Object[]{str, str2});
        }
        this.fMarkUpDepth--;
        return SchemaSymbols.ATTVAL_NOTATION;
    }

    private final void scanChildren(String str) throws IOException, XNIException {
        short s;
        int iPeekChar;
        this.fContentDepth = 0;
        pushContentStack(0);
        while (true) {
            int i = 0;
            while (!this.fEntityScanner.skipChar(40, null)) {
                skipSeparator(false, !scanningInternalSubset());
                String strScanName = this.fEntityScanner.scanName(XMLScanner.NameType.ELEMENTSTART);
                if (strScanName == null) {
                    reportFatalError("MSG_OPEN_PAREN_OR_ELEMENT_TYPE_REQUIRED_IN_CHILDREN", new Object[]{str});
                    return;
                }
                XMLDTDContentModelHandler xMLDTDContentModelHandler = this.fDTDContentModelHandler;
                if (xMLDTDContentModelHandler != null) {
                    xMLDTDContentModelHandler.element(strScanName, null);
                }
                this.fStringBuffer.append(strScanName);
                int iPeekChar2 = this.fEntityScanner.peekChar();
                if (iPeekChar2 == 63 || iPeekChar2 == 42 || iPeekChar2 == 43) {
                    XMLDTDContentModelHandler xMLDTDContentModelHandler2 = this.fDTDContentModelHandler;
                    if (xMLDTDContentModelHandler2 != null) {
                        if (iPeekChar2 == 63) {
                            s = 2;
                        } else {
                            s = iPeekChar2 == 42 ? (short) 3 : (short) 4;
                        }
                        xMLDTDContentModelHandler2.occurrence(s, null);
                    }
                    this.fEntityScanner.scanChar(null);
                    this.fStringBuffer.append((char) iPeekChar2);
                }
                while (true) {
                    skipSeparator(false, !scanningInternalSubset());
                    iPeekChar = this.fEntityScanner.peekChar();
                    if (iPeekChar == 44 && i != 124) {
                        XMLDTDContentModelHandler xMLDTDContentModelHandler3 = this.fDTDContentModelHandler;
                        if (xMLDTDContentModelHandler3 != null) {
                            xMLDTDContentModelHandler3.separator((short) 1, null);
                        }
                        this.fEntityScanner.scanChar(null);
                        this.fStringBuffer.append(',');
                        break;
                    }
                    if (iPeekChar == 124 && i != 44) {
                        XMLDTDContentModelHandler xMLDTDContentModelHandler4 = this.fDTDContentModelHandler;
                        if (xMLDTDContentModelHandler4 != null) {
                            xMLDTDContentModelHandler4.separator((short) 0, null);
                        }
                        this.fEntityScanner.scanChar(null);
                        this.fStringBuffer.append('|');
                        break;
                    }
                    if (iPeekChar != 41) {
                        reportFatalError("MSG_CLOSE_PAREN_REQUIRED_IN_CHILDREN", new Object[]{str});
                    }
                    XMLDTDContentModelHandler xMLDTDContentModelHandler5 = this.fDTDContentModelHandler;
                    if (xMLDTDContentModelHandler5 != null) {
                        xMLDTDContentModelHandler5.endGroup(null);
                    }
                    int iPopContentStack = popContentStack();
                    if (this.fEntityScanner.skipString(")?")) {
                        this.fStringBuffer.append(")?");
                        XMLDTDContentModelHandler xMLDTDContentModelHandler6 = this.fDTDContentModelHandler;
                        if (xMLDTDContentModelHandler6 != null) {
                            xMLDTDContentModelHandler6.occurrence((short) 2, null);
                        }
                    } else if (this.fEntityScanner.skipString(")+")) {
                        this.fStringBuffer.append(")+");
                        XMLDTDContentModelHandler xMLDTDContentModelHandler7 = this.fDTDContentModelHandler;
                        if (xMLDTDContentModelHandler7 != null) {
                            xMLDTDContentModelHandler7.occurrence((short) 4, null);
                        }
                    } else if (this.fEntityScanner.skipString(")*")) {
                        this.fStringBuffer.append(")*");
                        XMLDTDContentModelHandler xMLDTDContentModelHandler8 = this.fDTDContentModelHandler;
                        if (xMLDTDContentModelHandler8 != null) {
                            xMLDTDContentModelHandler8.occurrence((short) 3, null);
                        }
                    } else {
                        this.fEntityScanner.scanChar(null);
                        this.fStringBuffer.append(')');
                    }
                    this.fMarkUpDepth--;
                    if (this.fContentDepth == 0) {
                        return;
                    } else {
                        i = iPopContentStack;
                    }
                }
                skipSeparator(false, !scanningInternalSubset());
                i = iPeekChar;
            }
            this.fMarkUpDepth++;
            this.fStringBuffer.append('(');
            XMLDTDContentModelHandler xMLDTDContentModelHandler9 = this.fDTDContentModelHandler;
            if (xMLDTDContentModelHandler9 != null) {
                xMLDTDContentModelHandler9.startGroup(null);
            }
            pushContentStack(i);
            skipSeparator(false, !scanningInternalSubset());
        }
    }

    private final void scanConditionalSect(int i) throws IOException, XNIException {
        this.fReportEntity = false;
        skipSeparator(false, !scanningInternalSubset());
        if (this.fEntityScanner.skipString("INCLUDE")) {
            skipSeparator(false, !scanningInternalSubset());
            if (i != this.fPEDepth && this.fValidation) {
                this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "INVALID_PE_IN_CONDITIONAL", new Object[]{this.fEntityManager.fCurrentEntity.name}, (short) 1);
            }
            if (!this.fEntityScanner.skipChar(91, null)) {
                reportFatalError("MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", null);
            }
            XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
            if (xMLDTDHandler != null) {
                xMLDTDHandler.startConditional((short) 0, null);
            }
            this.fIncludeSectDepth++;
            this.fReportEntity = true;
            return;
        }
        if (!this.fEntityScanner.skipString("IGNORE")) {
            reportFatalError("MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", null);
            return;
        }
        skipSeparator(false, !scanningInternalSubset());
        if (i != this.fPEDepth && this.fValidation) {
            this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "INVALID_PE_IN_CONDITIONAL", new Object[]{this.fEntityManager.fCurrentEntity.name}, (short) 1);
        }
        XMLDTDHandler xMLDTDHandler2 = this.fDTDHandler;
        if (xMLDTDHandler2 != null) {
            xMLDTDHandler2.startConditional((short) 1, null);
        }
        if (!this.fEntityScanner.skipChar(91, null)) {
            reportFatalError("MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", null);
        }
        this.fReportEntity = true;
        int i2 = this.fIncludeSectDepth + 1;
        this.fIncludeSectDepth = i2;
        if (this.fDTDHandler != null) {
            this.fIgnoreConditionalBuffer.clear();
        }
        while (true) {
            if (this.fEntityScanner.skipChar(60, null)) {
                if (this.fDTDHandler != null) {
                    this.fIgnoreConditionalBuffer.append('<');
                }
                if (this.fEntityScanner.skipChar(33, null)) {
                    boolean zSkipChar = this.fEntityScanner.skipChar(91, null);
                    XMLDTDHandler xMLDTDHandler3 = this.fDTDHandler;
                    if (zSkipChar) {
                        if (xMLDTDHandler3 != null) {
                            this.fIgnoreConditionalBuffer.append("![");
                        }
                        this.fIncludeSectDepth++;
                    } else if (xMLDTDHandler3 != null) {
                        this.fIgnoreConditionalBuffer.append("!");
                    }
                }
            } else if (this.fEntityScanner.skipChar(93, null)) {
                if (this.fDTDHandler != null) {
                    this.fIgnoreConditionalBuffer.append(']');
                }
                if (this.fEntityScanner.skipChar(93, null)) {
                    if (this.fDTDHandler != null) {
                        this.fIgnoreConditionalBuffer.append(']');
                    }
                    while (this.fEntityScanner.skipChar(93, null)) {
                        if (this.fDTDHandler != null) {
                            this.fIgnoreConditionalBuffer.append(']');
                        }
                    }
                    if (this.fEntityScanner.skipChar(62, null)) {
                        int i3 = this.fIncludeSectDepth;
                        this.fIncludeSectDepth = i3 - 1;
                        if (i3 == i2) {
                            this.fMarkUpDepth--;
                            if (this.fDTDHandler != null) {
                                XMLString xMLString = this.fLiteral;
                                XMLStringBuffer xMLStringBuffer = this.fIgnoreConditionalBuffer;
                                xMLString.setValues(xMLStringBuffer.ch, 0, xMLStringBuffer.length - 2);
                                this.fDTDHandler.ignoredCharacters(this.fLiteral, null);
                                this.fDTDHandler.endConditional(null);
                                return;
                            }
                            return;
                        }
                        if (this.fDTDHandler != null) {
                            this.fIgnoreConditionalBuffer.append('>');
                        }
                    } else {
                        continue;
                    }
                } else {
                    continue;
                }
            } else {
                int iScanChar = this.fEntityScanner.scanChar(null);
                if (this.fScannerState == 0) {
                    reportFatalError("IgnoreSectUnterminated", null);
                    return;
                } else if (this.fDTDHandler != null) {
                    this.fIgnoreConditionalBuffer.append((char) iScanChar);
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0047  */
    private final void scanEntityDecl() throws IOException, XNIException {
        boolean zSkipChar;
        boolean z;
        String str;
        this.fReportEntity = false;
        if (!this.fEntityScanner.skipSpaces()) {
            if (scanningInternalSubset() || !this.fEntityScanner.skipChar(37, XMLScanner.NameType.REFERENCE)) {
                reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ENTITY_NAME_IN_ENTITYDECL", null);
            } else if (this.fEntityScanner.skipSpaces()) {
                reportFatalError("MSG_SPACE_REQUIRED_BEFORE_PERCENT_IN_PEDECL", null);
            } else {
                zSkipChar = false;
                z = true;
            }
            z = false;
            zSkipChar = false;
        } else if (this.fEntityScanner.skipChar(37, XMLScanner.NameType.REFERENCE)) {
            if (!skipSeparator(true, !scanningInternalSubset())) {
                if (scanningInternalSubset()) {
                    reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ENTITY_NAME_IN_ENTITYDECL", null);
                } else if (this.fEntityScanner.peekChar() == 37) {
                    skipSeparator(false, !scanningInternalSubset());
                } else {
                    zSkipChar = false;
                    z = true;
                }
            }
            z = false;
            zSkipChar = true;
        } else {
            z = false;
            zSkipChar = false;
        }
        if (z) {
            while (true) {
                XMLEntityScanner xMLEntityScanner = this.fEntityScanner;
                XMLScanner.NameType nameType = XMLScanner.NameType.REFERENCE;
                String strScanName = xMLEntityScanner.scanName(nameType);
                if (strScanName == null) {
                    reportFatalError("NameRequiredInPEReference", null);
                } else if (this.fEntityScanner.skipChar(59, nameType)) {
                    startPE(strScanName, false);
                } else {
                    reportFatalError("SemicolonRequiredInPEReference", new Object[]{strScanName});
                }
                this.fEntityScanner.skipSpaces();
                if (!this.fEntityScanner.skipChar(37, nameType)) {
                    break;
                }
                if (!zSkipChar) {
                    if (skipSeparator(true, !scanningInternalSubset())) {
                        zSkipChar = true;
                        break;
                    }
                    zSkipChar = this.fEntityScanner.skipChar(37, nameType);
                }
            }
        }
        String strScanName2 = this.fEntityScanner.scanName(XMLScanner.NameType.ENTITY);
        if (strScanName2 == null) {
            reportFatalError("MSG_ENTITY_NAME_REQUIRED_IN_ENTITYDECL", null);
        }
        if (!skipSeparator(true, !scanningInternalSubset())) {
            reportFatalError("MSG_SPACE_REQUIRED_AFTER_ENTITY_NAME_IN_ENTITYDECL", new Object[]{strScanName2});
        }
        scanExternalID(this.fStrings, false);
        String[] strArr = this.fStrings;
        String str2 = strArr[0];
        String str3 = strArr[1];
        if (zSkipChar && str2 != null) {
            this.fSeenExternalPE = true;
        }
        boolean zSkipSeparator = skipSeparator(true, !scanningInternalSubset());
        if (zSkipChar || !this.fEntityScanner.skipString("NDATA")) {
            str = null;
        } else {
            if (!zSkipSeparator) {
                reportFatalError("MSG_SPACE_REQUIRED_BEFORE_NDATA_IN_UNPARSED_ENTITYDECL", new Object[]{strScanName2});
            }
            if (!skipSeparator(true, !scanningInternalSubset())) {
                reportFatalError("MSG_SPACE_REQUIRED_BEFORE_NOTATION_NAME_IN_UNPARSED_ENTITYDECL", new Object[]{strScanName2});
            }
            String strScanName3 = this.fEntityScanner.scanName(XMLScanner.NameType.NOTATION);
            if (strScanName3 == null) {
                reportFatalError("MSG_NOTATION_NAME_REQUIRED_FOR_UNPARSED_ENTITYDECL", new Object[]{strScanName2});
            }
            str = strScanName3;
        }
        if (str2 == null) {
            scanEntityValue(strScanName2, zSkipChar, this.fLiteral, this.fLiteral2);
            this.fStringBuffer.clear();
            this.fStringBuffer2.clear();
            XMLStringBuffer xMLStringBuffer = this.fStringBuffer;
            XMLString xMLString = this.fLiteral;
            xMLStringBuffer.append(xMLString.ch, xMLString.offset, xMLString.length);
            XMLStringBuffer xMLStringBuffer2 = this.fStringBuffer2;
            XMLString xMLString2 = this.fLiteral2;
            xMLStringBuffer2.append(xMLString2.ch, xMLString2.offset, xMLString2.length);
        }
        skipSeparator(false, !scanningInternalSubset());
        if (!this.fEntityScanner.skipChar(62, null)) {
            reportFatalError("EntityDeclUnterminated", new Object[]{strScanName2});
        }
        this.fMarkUpDepth--;
        if (zSkipChar) {
            strScanName2 = "%" + strScanName2;
        }
        String str4 = strScanName2;
        if (str2 != null) {
            String baseSystemId = this.fEntityScanner.getBaseSystemId();
            XMLEntityStorage xMLEntityStorage = this.fEntityStore;
            if (str != null) {
                xMLEntityStorage.addUnparsedEntity(str4, str3, str2, baseSystemId, str);
            } else {
                xMLEntityStorage.addExternalEntity(str4, str3, str2, baseSystemId);
            }
            if (this.fDTDHandler != null) {
                this.fResourceIdentifier.setValues(str3, str2, baseSystemId, XMLEntityManager.expandSystemId(str2, baseSystemId));
                XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
                if (str != null) {
                    xMLDTDHandler.unparsedEntityDecl(str4, this.fResourceIdentifier, str, null);
                } else {
                    xMLDTDHandler.externalEntityDecl(str4, this.fResourceIdentifier, null);
                }
            }
        } else {
            this.fEntityStore.addInternalEntity(str4, this.fStringBuffer.toString());
            XMLDTDHandler xMLDTDHandler2 = this.fDTDHandler;
            if (xMLDTDHandler2 != null) {
                xMLDTDHandler2.internalEntityDecl(str4, this.fStringBuffer, this.fStringBuffer2, null);
            }
        }
        this.fReportEntity = true;
    }

    private final void scanMixed(String str) throws IOException, XNIException {
        this.fStringBuffer.append("#PCDATA");
        XMLDTDContentModelHandler xMLDTDContentModelHandler = this.fDTDContentModelHandler;
        if (xMLDTDContentModelHandler != null) {
            xMLDTDContentModelHandler.pcdata(null);
        }
        skipSeparator(false, !scanningInternalSubset());
        String strScanName = null;
        while (this.fEntityScanner.skipChar(124, null)) {
            this.fStringBuffer.append('|');
            XMLDTDContentModelHandler xMLDTDContentModelHandler2 = this.fDTDContentModelHandler;
            if (xMLDTDContentModelHandler2 != null) {
                xMLDTDContentModelHandler2.separator((short) 0, null);
            }
            skipSeparator(false, !scanningInternalSubset());
            strScanName = this.fEntityScanner.scanName(XMLScanner.NameType.ENTITY);
            if (strScanName == null) {
                reportFatalError("MSG_ELEMENT_TYPE_REQUIRED_IN_MIXED_CONTENT", new Object[]{str});
            }
            this.fStringBuffer.append(strScanName);
            XMLDTDContentModelHandler xMLDTDContentModelHandler3 = this.fDTDContentModelHandler;
            if (xMLDTDContentModelHandler3 != null) {
                xMLDTDContentModelHandler3.element(strScanName, null);
            }
            skipSeparator(false, !scanningInternalSubset());
        }
        if (this.fEntityScanner.skipString(")*")) {
            this.fStringBuffer.append(")*");
            XMLDTDContentModelHandler xMLDTDContentModelHandler4 = this.fDTDContentModelHandler;
            if (xMLDTDContentModelHandler4 != null) {
                xMLDTDContentModelHandler4.endGroup(null);
                this.fDTDContentModelHandler.occurrence((short) 3, null);
            }
        } else if (strScanName != null) {
            reportFatalError("MixedContentUnterminated", new Object[]{str});
        } else if (this.fEntityScanner.skipChar(41, null)) {
            this.fStringBuffer.append(')');
            XMLDTDContentModelHandler xMLDTDContentModelHandler5 = this.fDTDContentModelHandler;
            if (xMLDTDContentModelHandler5 != null) {
                xMLDTDContentModelHandler5.endGroup(null);
            }
        } else {
            reportFatalError("MSG_CLOSE_PAREN_REQUIRED_IN_CHILDREN", new Object[]{str});
        }
        this.fMarkUpDepth--;
    }

    private final void scanNotationDecl() throws IOException, XNIException {
        this.fReportEntity = false;
        if (!skipSeparator(true, !scanningInternalSubset())) {
            reportFatalError("MSG_SPACE_REQUIRED_BEFORE_NOTATION_NAME_IN_NOTATIONDECL", null);
        }
        String strScanName = this.fEntityScanner.scanName(XMLScanner.NameType.NOTATION);
        if (strScanName == null) {
            reportFatalError("MSG_NOTATION_NAME_REQUIRED_IN_NOTATIONDECL", null);
        }
        if (!skipSeparator(true, !scanningInternalSubset())) {
            reportFatalError("MSG_SPACE_REQUIRED_AFTER_NOTATION_NAME_IN_NOTATIONDECL", new Object[]{strScanName});
        }
        scanExternalID(this.fStrings, true);
        String[] strArr = this.fStrings;
        String str = strArr[0];
        String str2 = strArr[1];
        String baseSystemId = this.fEntityScanner.getBaseSystemId();
        if (str == null && str2 == null) {
            reportFatalError("ExternalIDorPublicIDRequired", new Object[]{strScanName});
        }
        skipSeparator(false, !scanningInternalSubset());
        if (!this.fEntityScanner.skipChar(62, null)) {
            reportFatalError("NotationDeclUnterminated", new Object[]{strScanName});
        }
        this.fMarkUpDepth--;
        this.fResourceIdentifier.setValues(str2, str, baseSystemId, XMLEntityManager.expandSystemId(str, baseSystemId));
        if (this.nonValidatingMode) {
            this.nvGrammarInfo.notationDecl(strScanName, this.fResourceIdentifier, null);
        }
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.notationDecl(strScanName, this.fResourceIdentifier, null);
        }
        this.fReportEntity = true;
    }

    private boolean skipSeparator(boolean z, boolean z2) throws IOException, XNIException {
        int i = this.fPEDepth;
        boolean zSkipSpaces = this.fEntityScanner.skipSpaces();
        if (!z2 || !this.fEntityScanner.skipChar(37, XMLScanner.NameType.REFERENCE)) {
            return (z && !zSkipSpaces && i == this.fPEDepth) ? false : true;
        }
        do {
            String strScanName = this.fEntityScanner.scanName(XMLScanner.NameType.ENTITY);
            if (strScanName == null) {
                reportFatalError("NameRequiredInPEReference", null);
            } else if (!this.fEntityScanner.skipChar(59, XMLScanner.NameType.REFERENCE)) {
                reportFatalError("SemicolonRequiredInPEReference", new Object[]{strScanName});
            }
            startPE(strScanName, false);
            this.fEntityScanner.skipSpaces();
        } while (this.fEntityScanner.skipChar(37, XMLScanner.NameType.REFERENCE));
        return true;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLScanner, com.sun.org.apache.xerces.internal.impl.XMLEntityHandler
    public void endEntity(String str, Augmentations augmentations) throws IOException, XNIException {
        super.endEntity(str, augmentations);
        if (this.fScannerState == 0) {
            return;
        }
        boolean zEquals = str.equals("[dtd]");
        if (str.startsWith("%")) {
            boolean zPeekReportEntity = peekReportEntity();
            int iPopPEStack = popPEStack();
            if (iPopPEStack == 0 && iPopPEStack < this.fMarkUpDepth) {
                this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "ILL_FORMED_PARAMETER_ENTITY_WHEN_USED_IN_DECL", new Object[]{this.fEntityManager.fCurrentEntity.name}, (short) 2);
            }
            if (iPopPEStack != this.fMarkUpDepth) {
                if (this.fValidation) {
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "ImproperDeclarationNesting", new Object[]{str}, (short) 1);
                }
                zPeekReportEntity = false;
            }
            if (this.fEntityScanner.isExternal()) {
                this.fExtEntityDepth--;
            }
            XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
            if (xMLDTDHandler != null && zPeekReportEntity) {
                xMLDTDHandler.endParameterEntity(str, null);
            }
        }
        if (zEquals) {
            if (this.fIncludeSectDepth != 0) {
                reportFatalError("IncludeSectUnterminated", null);
            }
            this.fScannerState = 0;
            this.fEntityManager.endExternalSubset();
            this.fEntityStore.endExternalSubset();
            XMLDTDHandler xMLDTDHandler2 = this.fDTDHandler;
            if (xMLDTDHandler2 != null) {
                xMLDTDHandler2.endExternalSubset(null);
                this.fDTDHandler.endDTD(null);
            }
            this.fExtEntityDepth--;
        }
        if (augmentations == null || !Boolean.TRUE.equals(augmentations.getItem(Constants.LAST_ENTITY))) {
            return;
        }
        if (this.fMarkUpDepth == 0 && this.fExtEntityDepth == 0 && this.fIncludeSectDepth == 0) {
            return;
        }
        u01.a();
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLDTDContentModelSource
    public XMLDTDContentModelHandler getDTDContentModelHandler() {
        return this.fDTDContentModelHandler;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLDTDSource
    public XMLDTDHandler getDTDHandler() {
        return this.fDTDHandler;
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

    public DTDGrammar getGrammar() {
        return this.nvGrammarInfo;
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

    @Override // com.sun.org.apache.xerces.internal.impl.XMLScanner
    public void reset(PropertyManager propertyManager) {
        setPropertyManager(propertyManager);
        super.reset(propertyManager);
        init();
        this.nonValidatingMode = true;
        this.nvGrammarInfo = new DTDGrammar(this.fSymbolTable);
    }

    public final String scanAttDefaultDecl(String str, String str2, String str3, XMLString xMLString, XMLString xMLString2) throws IOException, XNIException {
        this.fString.clear();
        xMLString.clear();
        if (this.fEntityScanner.skipString("#REQUIRED")) {
            return "#REQUIRED";
        }
        if (this.fEntityScanner.skipString("#IMPLIED")) {
            return "#IMPLIED";
        }
        String str4 = "#FIXED";
        boolean z = true;
        if (!this.fEntityScanner.skipString("#FIXED")) {
            str4 = null;
        } else if (!skipSeparator(true, !scanningInternalSubset())) {
            reportFatalError("MSG_SPACE_REQUIRED_AFTER_FIXED_IN_DEFAULTDECL", new Object[]{str, str2});
        }
        String str5 = str4;
        if (this.fStandalone || (!this.fSeenExternalDTD && !this.fSeenExternalPE)) {
            z = false;
        }
        scanAttributeValue(xMLString, xMLString2, str2, this.fAttributes, 0, z, str, false);
        return str5;
    }

    public final void scanAttlistDecl() throws IOException, XNIException {
        int i;
        String[] strArr;
        this.fReportEntity = false;
        if (!skipSeparator(true, !scanningInternalSubset())) {
            reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ELEMENT_TYPE_IN_ATTLISTDECL", null);
        }
        String strScanName = this.fEntityScanner.scanName(XMLScanner.NameType.ELEMENTSTART);
        if (strScanName == null) {
            reportFatalError("MSG_ELEMENT_TYPE_REQUIRED_IN_ATTLISTDECL", null);
        }
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.startAttlist(strScanName, null);
        }
        if (!skipSeparator(true, !scanningInternalSubset())) {
            if (this.fEntityScanner.skipChar(62, null)) {
                XMLDTDHandler xMLDTDHandler2 = this.fDTDHandler;
                if (xMLDTDHandler2 != null) {
                    xMLDTDHandler2.endAttlist(null);
                }
                this.fMarkUpDepth--;
                return;
            }
            reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ATTRIBUTE_NAME_IN_ATTDEF", new Object[]{strScanName});
        }
        while (!this.fEntityScanner.skipChar(62, null)) {
            String strScanName2 = this.fEntityScanner.scanName(XMLScanner.NameType.ATTRIBUTENAME);
            if (strScanName2 == null) {
                reportFatalError("AttNameRequiredInAttDef", new Object[]{strScanName});
            }
            if (!skipSeparator(true, !scanningInternalSubset())) {
                reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ATTTYPE_IN_ATTDEF", new Object[]{strScanName, strScanName2});
            }
            String strScanAttType = scanAttType(strScanName, strScanName2);
            if (!skipSeparator(true, !scanningInternalSubset())) {
                reportFatalError("MSG_SPACE_REQUIRED_BEFORE_DEFAULTDECL_IN_ATTDEF", new Object[]{strScanName, strScanName2});
            }
            String strScanAttDefaultDecl = scanAttDefaultDecl(strScanName, strScanName2, strScanAttType, this.fLiteral, this.fLiteral2);
            if ((this.fDTDHandler != null || this.nonValidatingMode) && (i = this.fEnumerationCount) != 0) {
                String[] strArr2 = new String[i];
                System.arraycopy(this.fEnumeration, 0, strArr2, 0, i);
                strArr = strArr2;
            } else {
                strArr = null;
            }
            if (strScanAttDefaultDecl == null || !(strScanAttDefaultDecl.equals("#REQUIRED") || strScanAttDefaultDecl.equals("#IMPLIED"))) {
                XMLDTDHandler xMLDTDHandler3 = this.fDTDHandler;
                if (xMLDTDHandler3 != null) {
                    xMLDTDHandler3.attributeDecl(strScanName, strScanName2, strScanAttType, strArr, strScanAttDefaultDecl, this.fLiteral, this.fLiteral2, null);
                }
                if (this.nonValidatingMode) {
                    this.nvGrammarInfo.attributeDecl(strScanName, strScanName2, strScanAttType, strArr, strScanAttDefaultDecl, this.fLiteral, this.fLiteral2, null);
                }
            } else {
                XMLDTDHandler xMLDTDHandler4 = this.fDTDHandler;
                if (xMLDTDHandler4 != null) {
                    xMLDTDHandler4.attributeDecl(strScanName, strScanName2, strScanAttType, strArr, strScanAttDefaultDecl, null, null, null);
                }
                if (this.nonValidatingMode) {
                    this.nvGrammarInfo.attributeDecl(strScanName, strScanName2, strScanAttType, strArr, strScanAttDefaultDecl, null, null, null);
                }
            }
            skipSeparator(false, !scanningInternalSubset());
        }
        XMLDTDHandler xMLDTDHandler5 = this.fDTDHandler;
        if (xMLDTDHandler5 != null) {
            xMLDTDHandler5.endAttlist(null);
        }
        this.fMarkUpDepth--;
        this.fReportEntity = true;
    }

    public final void scanComment() throws IOException, XNIException {
        this.fReportEntity = false;
        scanComment(this.fStringBuffer);
        this.fMarkUpDepth--;
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.comment(this.fStringBuffer, null);
        }
        this.fReportEntity = true;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLDTDScanner
    public boolean scanDTDExternalSubset(boolean z) throws IOException, XNIException {
        this.fEntityManager.setEntityHandler(this);
        if (this.fScannerState == 1) {
            this.fSeenExternalDTD = true;
            boolean zScanTextDecl = scanTextDecl();
            if (this.fScannerState == 0) {
                return false;
            }
            setScannerState(2);
            if (zScanTextDecl && !z) {
                return true;
            }
        }
        while (scanDecls(z)) {
            if (!z) {
                return true;
            }
        }
        return false;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLDTDScanner
    public boolean scanDTDInternalSubset(boolean z, boolean z2, boolean z3) throws IOException, XNIException {
        this.fEntityScanner = this.fEntityManager.getEntityScanner();
        this.fEntityManager.setEntityHandler(this);
        this.fStandalone = z2;
        if (this.fScannerState == 1) {
            XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
            if (xMLDTDHandler != null) {
                xMLDTDHandler.startDTD(this.fEntityScanner, null);
                this.fStartDTDCalled = true;
            }
            if (this.nonValidatingMode) {
                this.fStartDTDCalled = true;
                this.nvGrammarInfo.startDTD(this.fEntityScanner, null);
            }
            setScannerState(2);
        }
        while (scanDecls(z)) {
            if (!z) {
                return true;
            }
        }
        XMLDTDHandler xMLDTDHandler2 = this.fDTDHandler;
        if (xMLDTDHandler2 != null && !z3) {
            xMLDTDHandler2.endDTD(null);
        }
        if (this.nonValidatingMode && !z3) {
            this.nvGrammarInfo.endDTD(null);
        }
        setScannerState(1);
        this.fLimitAnalyzer.reset(XMLSecurityManager.Limit.GENERAL_ENTITY_SIZE_LIMIT);
        this.fLimitAnalyzer.reset(XMLSecurityManager.Limit.TOTAL_ENTITY_SIZE_LIMIT);
        return false;
    }

    public final boolean scanDecls(boolean z) throws IOException, XNIException {
        skipSeparator(false, true);
        boolean z2 = true;
        while (z2 && this.fScannerState == 2) {
            if (this.fEntityScanner.skipChar(60, null)) {
                this.fMarkUpDepth++;
                if (this.fEntityScanner.skipChar(63, null)) {
                    this.fStringBuffer.clear();
                    scanPI(this.fStringBuffer);
                    this.fMarkUpDepth--;
                } else if (this.fEntityScanner.skipChar(33, null)) {
                    boolean zSkipChar = this.fEntityScanner.skipChar(45, null);
                    XMLEntityScanner xMLEntityScanner = this.fEntityScanner;
                    if (zSkipChar) {
                        if (xMLEntityScanner.skipChar(45, null)) {
                            scanComment();
                        } else {
                            reportFatalError("MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", null);
                        }
                    } else if (xMLEntityScanner.skipString("ELEMENT")) {
                        scanElementDecl();
                    } else if (this.fEntityScanner.skipString("ATTLIST")) {
                        scanAttlistDecl();
                    } else if (this.fEntityScanner.skipString(SchemaSymbols.ATTVAL_ENTITY)) {
                        scanEntityDecl();
                    } else if (this.fEntityScanner.skipString(SchemaSymbols.ATTVAL_NOTATION)) {
                        scanNotationDecl();
                    } else if (!this.fEntityScanner.skipChar(91, null) || scanningInternalSubset()) {
                        this.fMarkUpDepth--;
                        reportFatalError("MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", null);
                    } else {
                        scanConditionalSect(this.fPEDepth);
                    }
                } else {
                    this.fMarkUpDepth--;
                    reportFatalError("MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", null);
                }
            } else if (this.fIncludeSectDepth > 0 && this.fEntityScanner.skipChar(93, null)) {
                if (!this.fEntityScanner.skipChar(93, null) || !this.fEntityScanner.skipChar(62, null)) {
                    reportFatalError("IncludeSectUnterminated", null);
                }
                XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
                if (xMLDTDHandler != null) {
                    xMLDTDHandler.endConditional(null);
                }
                this.fIncludeSectDepth--;
                this.fMarkUpDepth--;
            } else {
                if (scanningInternalSubset() && this.fEntityScanner.peekChar() == 93) {
                    return false;
                }
                if (!this.fEntityScanner.skipSpaces()) {
                    reportFatalError("MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", null);
                }
            }
            skipSeparator(false, true);
            z2 = z;
        }
        return this.fScannerState != 0;
    }

    public final void scanElementDecl() throws IOException, XNIException {
        this.fReportEntity = false;
        if (!skipSeparator(true, !scanningInternalSubset())) {
            reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ELEMENT_TYPE_IN_ELEMENTDECL", null);
        }
        String strScanName = this.fEntityScanner.scanName(XMLScanner.NameType.ELEMENTSTART);
        if (strScanName == null) {
            reportFatalError("MSG_ELEMENT_TYPE_REQUIRED_IN_ELEMENTDECL", null);
        }
        if (!skipSeparator(true, !scanningInternalSubset())) {
            reportFatalError("MSG_SPACE_REQUIRED_BEFORE_CONTENTSPEC_IN_ELEMENTDECL", new Object[]{strScanName});
        }
        XMLDTDContentModelHandler xMLDTDContentModelHandler = this.fDTDContentModelHandler;
        if (xMLDTDContentModelHandler != null) {
            xMLDTDContentModelHandler.startContentModel(strScanName, null);
        }
        this.fReportEntity = true;
        String string = "EMPTY";
        if (this.fEntityScanner.skipString("EMPTY")) {
            XMLDTDContentModelHandler xMLDTDContentModelHandler2 = this.fDTDContentModelHandler;
            if (xMLDTDContentModelHandler2 != null) {
                xMLDTDContentModelHandler2.empty(null);
            }
        } else {
            string = "ANY";
            if (this.fEntityScanner.skipString("ANY")) {
                XMLDTDContentModelHandler xMLDTDContentModelHandler3 = this.fDTDContentModelHandler;
                if (xMLDTDContentModelHandler3 != null) {
                    xMLDTDContentModelHandler3.any(null);
                }
            } else {
                if (!this.fEntityScanner.skipChar(40, null)) {
                    reportFatalError("MSG_OPEN_PAREN_OR_ELEMENT_TYPE_REQUIRED_IN_CHILDREN", new Object[]{strScanName});
                }
                XMLDTDContentModelHandler xMLDTDContentModelHandler4 = this.fDTDContentModelHandler;
                if (xMLDTDContentModelHandler4 != null) {
                    xMLDTDContentModelHandler4.startGroup(null);
                }
                this.fStringBuffer.clear();
                this.fStringBuffer.append('(');
                this.fMarkUpDepth++;
                skipSeparator(false, !scanningInternalSubset());
                if (this.fEntityScanner.skipString("#PCDATA")) {
                    scanMixed(strScanName);
                } else {
                    scanChildren(strScanName);
                }
                string = this.fStringBuffer.toString();
            }
        }
        XMLDTDContentModelHandler xMLDTDContentModelHandler5 = this.fDTDContentModelHandler;
        if (xMLDTDContentModelHandler5 != null) {
            xMLDTDContentModelHandler5.endContentModel(null);
        }
        this.fReportEntity = false;
        skipSeparator(false, !scanningInternalSubset());
        if (!this.fEntityScanner.skipChar(62, null)) {
            reportFatalError("ElementDeclUnterminated", new Object[]{strScanName});
        }
        this.fReportEntity = true;
        this.fMarkUpDepth--;
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.elementDecl(strScanName, string, null);
        }
        if (this.nonValidatingMode) {
            this.nvGrammarInfo.elementDecl(strScanName, string, null);
        }
    }

    public final void scanEntityValue(String str, boolean z, XMLString xMLString, XMLString xMLString2) throws IOException, XNIException {
        XMLString xMLString3;
        int i;
        XMLScanner.NameType nameType;
        int iScanChar = this.fEntityScanner.scanChar(null);
        if (iScanChar != 39 && iScanChar != 34) {
            reportFatalError("OpenQuoteMissingInDecl", null);
        }
        int i2 = this.fEntityDepth;
        XMLString xMLString4 = this.fString;
        if (this.fLimitAnalyzer == null) {
            this.fLimitAnalyzer = this.fEntityManager.fLimitAnalyzer;
        }
        this.fLimitAnalyzer.startEntity(str);
        if (this.fEntityScanner.scanLiteral(iScanChar, this.fString, false) != iScanChar) {
            this.fStringBuffer.clear();
            this.fStringBuffer2.clear();
            do {
                XMLStringBuffer xMLStringBuffer = this.fStringBuffer;
                int i3 = xMLStringBuffer.length;
                xMLStringBuffer.append(this.fString);
                this.fStringBuffer2.append(this.fString);
                XMLEntityScanner xMLEntityScanner = this.fEntityScanner;
                XMLScanner.NameType nameType2 = XMLScanner.NameType.REFERENCE;
                boolean zSkipChar = xMLEntityScanner.skipChar(38, nameType2);
                XMLEntityScanner xMLEntityScanner2 = this.fEntityScanner;
                if (!zSkipChar) {
                    i = 1;
                    if (xMLEntityScanner2.skipChar(37, nameType2)) {
                        do {
                            this.fStringBuffer2.append('%');
                            XMLEntityScanner xMLEntityScanner3 = this.fEntityScanner;
                            nameType = XMLScanner.NameType.REFERENCE;
                            String strScanName = xMLEntityScanner3.scanName(nameType);
                            if (strScanName == null) {
                                reportFatalError("NameRequiredInPEReference", null);
                            } else if (this.fEntityScanner.skipChar(59, nameType)) {
                                if (scanningInternalSubset()) {
                                    reportFatalError("PEReferenceWithinMarkup", new Object[]{strScanName});
                                }
                                this.fStringBuffer2.append(strScanName);
                                this.fStringBuffer2.append(';');
                            } else {
                                reportFatalError("SemicolonRequiredInPEReference", new Object[]{strScanName});
                            }
                            startPE(strScanName, true);
                            this.fEntityScanner.skipSpaces();
                        } while (this.fEntityScanner.skipChar(37, nameType));
                    } else {
                        int iPeekChar = this.fEntityScanner.peekChar();
                        if (XMLChar.isHighSurrogate(iPeekChar)) {
                            scanSurrogates(this.fStringBuffer2);
                        } else if (isInvalidLiteral(iPeekChar)) {
                            reportFatalError("InvalidCharInLiteral", new Object[]{Integer.toHexString(iPeekChar)});
                            this.fEntityScanner.scanChar(null);
                        } else if (iPeekChar != iScanChar || i2 != this.fEntityDepth) {
                            char c = (char) iPeekChar;
                            this.fStringBuffer.append(c);
                            this.fStringBuffer2.append(c);
                            this.fEntityScanner.scanChar(null);
                        }
                        checkEntityLimit(z, str, (this.fStringBuffer.length - i3) + i);
                    }
                } else if (xMLEntityScanner2.skipChar(35, nameType2)) {
                    this.fStringBuffer2.append("&#");
                    scanCharReferenceValue(this.fStringBuffer, this.fStringBuffer2);
                } else {
                    this.fStringBuffer.append('&');
                    this.fStringBuffer2.append('&');
                    String strScanName2 = this.fEntityScanner.scanName(nameType2);
                    if (strScanName2 == null) {
                        reportFatalError("NameRequiredInReference", null);
                    } else {
                        this.fStringBuffer.append(strScanName2);
                        this.fStringBuffer2.append(strScanName2);
                    }
                    if (this.fEntityScanner.skipChar(59, nameType2)) {
                        this.fStringBuffer.append(';');
                        this.fStringBuffer2.append(';');
                    } else {
                        reportFatalError("SemicolonRequiredInReference", new Object[]{strScanName2});
                    }
                }
                i = 0;
                checkEntityLimit(z, str, (this.fStringBuffer.length - i3) + i);
            } while (this.fEntityScanner.scanLiteral(iScanChar, this.fString, false) != iScanChar);
            checkEntityLimit(z, str, this.fString.length);
            this.fStringBuffer.append(this.fString);
            this.fStringBuffer2.append(this.fString);
            xMLString4 = this.fStringBuffer;
            xMLString3 = this.fStringBuffer2;
        } else {
            checkEntityLimit(z, str, xMLString4);
            xMLString3 = xMLString4;
        }
        xMLString.setValues(xMLString4);
        xMLString2.setValues(xMLString3);
        XMLLimitAnalyzer xMLLimitAnalyzer = this.fLimitAnalyzer;
        if (xMLLimitAnalyzer != null) {
            if (z) {
                xMLLimitAnalyzer.endEntity(XMLSecurityManager.Limit.PARAMETER_ENTITY_SIZE_LIMIT, str);
            } else {
                xMLLimitAnalyzer.endEntity(XMLSecurityManager.Limit.GENERAL_ENTITY_SIZE_LIMIT, str);
            }
        }
        if (this.fEntityScanner.skipChar(iScanChar, null)) {
            return;
        }
        reportFatalError("CloseQuoteMissingInDecl", null);
    }

    public final void scanPIData(String str, XMLString xMLString) throws IOException, XNIException {
        this.fMarkUpDepth--;
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.processingInstruction(str, xMLString, null);
        }
    }

    public final boolean scanTextDecl() throws IOException, XNIException {
        boolean z = false;
        if (this.fEntityScanner.skipString("<?xml")) {
            this.fMarkUpDepth++;
            if (isValidNameChar(this.fEntityScanner.peekChar())) {
                this.fStringBuffer.clear();
                this.fStringBuffer.append("xml");
                while (isValidNameChar(this.fEntityScanner.peekChar())) {
                    this.fStringBuffer.append((char) this.fEntityScanner.scanChar(null));
                }
                SymbolTable symbolTable = this.fSymbolTable;
                XMLStringBuffer xMLStringBuffer = this.fStringBuffer;
                scanPIData(symbolTable.addSymbol(xMLStringBuffer.ch, xMLStringBuffer.offset, xMLStringBuffer.length), this.fString);
            } else {
                scanXMLDeclOrTextDecl(true, this.fStrings);
                this.fMarkUpDepth--;
                String[] strArr = this.fStrings;
                String str = strArr[0];
                String str2 = strArr[1];
                this.fEntityScanner.setEncoding(str2);
                XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
                if (xMLDTDHandler != null) {
                    xMLDTDHandler.textDecl(str, str2, null);
                }
                z = true;
            }
        }
        this.fEntityManager.fCurrentEntity.mayReadChunks = true;
        return z;
    }

    public final boolean scanningInternalSubset() {
        return this.fExtEntityDepth == 0;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLDTDContentModelSource
    public void setDTDContentModelHandler(XMLDTDContentModelHandler xMLDTDContentModelHandler) {
        this.fDTDContentModelHandler = xMLDTDContentModelHandler;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLDTDSource
    public void setDTDHandler(XMLDTDHandler xMLDTDHandler) {
        this.fDTDHandler = xMLDTDHandler;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLDTDScanner
    public void setInputSource(XMLInputSource xMLInputSource) throws IOException {
        if (xMLInputSource != null) {
            this.fEntityManager.setEntityHandler(this);
            this.fEntityManager.startDTDEntity(xMLInputSource);
            return;
        }
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.startDTD(null, null);
            this.fDTDHandler.endDTD(null);
        }
        if (this.nonValidatingMode) {
            this.nvGrammarInfo.startDTD(null, null);
            this.nvGrammarInfo.endDTD(null);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLDTDScanner
    public void setLimitAnalyzer(XMLLimitAnalyzer xMLLimitAnalyzer) {
        this.fLimitAnalyzer = xMLLimitAnalyzer;
    }

    public final void setScannerState(int i) {
        this.fScannerState = i;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLDTDScanner
    public boolean skipDTD(boolean z) throws IOException {
        if (z) {
            return false;
        }
        this.fStringBuffer.clear();
        while (true) {
            boolean zScanData = this.fEntityScanner.scanData("]", this.fStringBuffer, 0);
            XMLEntityScanner xMLEntityScanner = this.fEntityScanner;
            if (!zScanData) {
                xMLEntityScanner.fCurrentEntity.position--;
                return true;
            }
            int iPeekChar = xMLEntityScanner.peekChar();
            if (iPeekChar != -1) {
                if (XMLChar.isHighSurrogate(iPeekChar)) {
                    scanSurrogates(this.fStringBuffer);
                }
                if (isInvalidLiteral(iPeekChar)) {
                    reportFatalError("InvalidCharInDTD", new Object[]{Integer.toHexString(iPeekChar)});
                    this.fEntityScanner.scanChar(null);
                }
            }
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLScanner, com.sun.org.apache.xerces.internal.impl.XMLEntityHandler
    public void startEntity(String str, XMLResourceIdentifier xMLResourceIdentifier, String str2, Augmentations augmentations) throws XNIException {
        super.startEntity(str, xMLResourceIdentifier, str2, augmentations);
        boolean zEquals = str.equals("[dtd]");
        if (zEquals) {
            XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
            if (xMLDTDHandler != null && !this.fStartDTDCalled) {
                xMLDTDHandler.startDTD(this.fEntityScanner, null);
            }
            XMLDTDHandler xMLDTDHandler2 = this.fDTDHandler;
            if (xMLDTDHandler2 != null) {
                xMLDTDHandler2.startExternalSubset(xMLResourceIdentifier, null);
            }
            this.fEntityManager.startExternalSubset();
            this.fEntityStore.startExternalSubset();
            this.fExtEntityDepth++;
        } else if (str.charAt(0) == '%') {
            pushPEStack(this.fMarkUpDepth, this.fReportEntity);
            if (this.fEntityScanner.isExternal()) {
                this.fExtEntityDepth++;
            }
        }
        XMLDTDHandler xMLDTDHandler3 = this.fDTDHandler;
        if (xMLDTDHandler3 == null || zEquals || !this.fReportEntity) {
            return;
        }
        xMLDTDHandler3.startParameterEntity(str, xMLResourceIdentifier, str2, null);
    }

    public void startPE(String str, boolean z) throws IOException, XNIException {
        int i = this.fPEDepth;
        String str2 = "%" + str;
        if (this.fValidation && !this.fEntityStore.isDeclaredEntity(str2)) {
            this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "EntityNotDeclared", new Object[]{str}, (short) 1);
        }
        this.fEntityManager.startEntity(false, this.fSymbolTable.addSymbol(str2), z);
        if (i == this.fPEDepth || !this.fEntityScanner.isExternal()) {
            return;
        }
        scanTextDecl();
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLScanner
    public void reset() {
        super.reset();
        init();
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLScanner, com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public void reset(XMLComponentManager xMLComponentManager) throws XMLConfigurationException {
        super.reset(xMLComponentManager);
        init();
    }

    public XMLDTDScannerImpl() {
        this.fDTDHandler = null;
        this.fAttributes = new XMLAttributesImpl();
        this.fContentStack = new int[5];
        this.fPEStack = new int[5];
        this.fPEReport = new boolean[5];
        this.fStrings = new String[3];
        this.fString = new XMLString();
        this.fStringBuffer = new XMLStringBuffer();
        this.fStringBuffer2 = new XMLStringBuffer();
        this.fLiteral = new XMLString();
        this.fLiteral2 = new XMLString();
        this.fEnumeration = new String[5];
        this.fIgnoreConditionalBuffer = new XMLStringBuffer(128);
        this.nvGrammarInfo = null;
        this.nonValidatingMode = false;
    }
}
