package com.sun.org.apache.xerces.internal.impl;

import com.sun.org.apache.xerces.internal.util.Status;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.util.XMLChar;
import com.sun.org.apache.xerces.internal.util.XMLResourceIdentifierImpl;
import com.sun.org.apache.xerces.internal.util.XMLStringBuffer;
import com.sun.org.apache.xerces.internal.utils.XMLLimitAnalyzer;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityManager;
import com.sun.org.apache.xerces.internal.xni.Augmentations;
import com.sun.org.apache.xerces.internal.xni.XMLAttributes;
import com.sun.org.apache.xerces.internal.xni.XMLLocator;
import com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier;
import com.sun.org.apache.xerces.internal.xni.XMLString;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponent;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager;
import com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import com.sun.org.apache.xml.internal.serializer.SerializerConstants;
import com.sun.xml.internal.stream.Entity;
import com.sun.xml.internal.stream.XMLEntityStorage;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.stream.events.XMLEvent;
import jdk.xml.internal.JdkConstants;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class XMLScanner implements XMLComponent {
    protected static final boolean DEBUG_ATTR_NORMALIZATION = false;
    protected static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String NAMESPACES = "http://xml.org/sax/features/namespaces";
    protected static final String NOTIFY_CHAR_REFS = "http://apache.org/xml/features/scanner/notify-char-refs";
    protected static final String PARSER_SETTINGS = "http://apache.org/xml/features/internal/parser-settings";
    private static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String VALIDATION = "http://xml.org/sax/features/validation";
    protected int fEntityDepth;
    protected XMLErrorReporter fErrorReporter;
    protected XMLEvent fEvent;
    protected boolean fNamespaces;
    protected boolean fReportEntity;
    protected boolean fScanningAttribute;
    protected SymbolTable fSymbolTable;
    protected static final String fVersionSymbol = "version".intern();
    protected static final String fEncodingSymbol = "encoding".intern();
    protected static final String fStandaloneSymbol = com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_OUTPUT_STANDALONE.intern();
    protected static final String fAmpSymbol = "amp".intern();
    protected static final String fLtSymbol = "lt".intern();
    protected static final String fGtSymbol = "gt".intern();
    protected static final String fQuotSymbol = "quot".intern();
    protected static final String fAposSymbol = "apos".intern();
    private boolean fNeedNonNormalizedValue = false;
    protected ArrayList<XMLString> attributeValueCache = new ArrayList<>();
    protected ArrayList<XMLStringBuffer> stringBufferCache = new ArrayList<>();
    protected int fStringBufferIndex = 0;
    protected boolean fAttributeCacheInitDone = false;
    protected int fAttributeCacheUsedCount = 0;
    protected boolean fValidation = false;
    protected boolean fNotifyCharRefs = false;
    protected boolean fParserSettings = true;
    protected PropertyManager fPropertyManager = null;
    protected XMLEntityManager fEntityManager = null;
    protected XMLEntityStorage fEntityStore = null;
    protected XMLSecurityManager fSecurityManager = null;
    protected XMLLimitAnalyzer fLimitAnalyzer = null;
    protected XMLEntityScanner fEntityScanner = null;
    protected String fCharRefLiteral = null;
    private XMLString fString = new XMLString();
    private XMLStringBuffer fStringBuffer = new XMLStringBuffer();
    private XMLStringBuffer fStringBuffer2 = new XMLStringBuffer();
    private XMLStringBuffer fStringBuffer3 = new XMLStringBuffer();
    protected XMLResourceIdentifierImpl fResourceIdentifier = new XMLResourceIdentifierImpl();
    int initialCacheCount = 6;

    public enum NameType {
        ATTRIBUTE("attribute"),
        ATTRIBUTENAME("attribute name"),
        COMMENT(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_COMMENT_STRING),
        DOCTYPE("doctype"),
        ELEMENTSTART("startelement"),
        ELEMENTEND("endelement"),
        ENTITY("entity"),
        NOTATION("notation"),
        PI(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_PI_OLD_STRING),
        REFERENCE("reference");

        final String literal;

        NameType(String str) {
            this.literal = str;
        }

        public String literal() {
            return this.literal;
        }
    }

    private void init() {
        this.fEntityScanner = null;
        this.fEntityDepth = 0;
        this.fReportEntity = true;
        this.fResourceIdentifier.clear();
        if (!this.fAttributeCacheInitDone) {
            for (int i = 0; i < this.initialCacheCount; i++) {
                this.attributeValueCache.add(new XMLString());
                this.stringBufferCache.add(new XMLStringBuffer());
            }
            this.fAttributeCacheInitDone = true;
        }
        this.fStringBufferIndex = 0;
        this.fAttributeCacheUsedCount = 0;
    }

    private String scanPseudoAttributeName() throws IOException, XNIException {
        int iPeekChar = this.fEntityScanner.peekChar();
        if (iPeekChar == 101) {
            XMLEntityScanner xMLEntityScanner = this.fEntityScanner;
            String str = fEncodingSymbol;
            if (xMLEntityScanner.skipString(str)) {
                return str;
            }
            return null;
        }
        if (iPeekChar == 115) {
            XMLEntityScanner xMLEntityScanner2 = this.fEntityScanner;
            String str2 = fStandaloneSymbol;
            if (xMLEntityScanner2.skipString(str2)) {
                return str2;
            }
            return null;
        }
        if (iPeekChar != 118) {
            return null;
        }
        XMLEntityScanner xMLEntityScanner3 = this.fEntityScanner;
        String str3 = fVersionSymbol;
        if (xMLEntityScanner3.skipString(str3)) {
            return str3;
        }
        return null;
    }

    public void checkEntityLimit(boolean z, String str, int i) {
        if (this.fLimitAnalyzer == null) {
            this.fLimitAnalyzer = this.fEntityManager.fLimitAnalyzer;
        }
        XMLLimitAnalyzer xMLLimitAnalyzer = this.fLimitAnalyzer;
        if (z) {
            XMLSecurityManager.Limit limit = XMLSecurityManager.Limit.PARAMETER_ENTITY_SIZE_LIMIT;
            xMLLimitAnalyzer.addValue(limit, "%" + str, i);
            if (this.fSecurityManager.isOverLimit(limit, this.fLimitAnalyzer)) {
                this.fSecurityManager.debugPrint(this.fLimitAnalyzer);
                reportFatalError("MaxEntitySizeLimit", new Object[]{"%" + str, Integer.valueOf(this.fLimitAnalyzer.getValue(limit)), Integer.valueOf(this.fSecurityManager.getLimit(limit)), this.fSecurityManager.getStateLiteral(limit)});
            }
        } else {
            XMLSecurityManager.Limit limit2 = XMLSecurityManager.Limit.GENERAL_ENTITY_SIZE_LIMIT;
            xMLLimitAnalyzer.addValue(limit2, str, i);
            if (this.fSecurityManager.isOverLimit(limit2, this.fLimitAnalyzer)) {
                this.fSecurityManager.debugPrint(this.fLimitAnalyzer);
                reportFatalError("MaxEntitySizeLimit", new Object[]{str, Integer.valueOf(this.fLimitAnalyzer.getValue(limit2)), Integer.valueOf(this.fSecurityManager.getLimit(limit2)), this.fSecurityManager.getStateLiteral(limit2)});
            }
        }
        XMLSecurityManager xMLSecurityManager = this.fSecurityManager;
        XMLSecurityManager.Limit limit3 = XMLSecurityManager.Limit.TOTAL_ENTITY_SIZE_LIMIT;
        if (xMLSecurityManager.isOverLimit(limit3, this.fLimitAnalyzer)) {
            this.fSecurityManager.debugPrint(this.fLimitAnalyzer);
            reportFatalError("TotalEntitySizeLimit", new Object[]{Integer.valueOf(this.fLimitAnalyzer.getTotalValue(limit3)), Integer.valueOf(this.fSecurityManager.getLimit(limit3)), this.fSecurityManager.getStateLiteral(limit3)});
        }
    }

    public void endEntity(String str, Augmentations augmentations) throws IOException, XNIException {
        int i = this.fEntityDepth;
        if (i > 0) {
            this.fEntityDepth = i - 1;
        }
    }

    public boolean getFeature(String str) throws XMLConfigurationException {
        if (VALIDATION.equals(str)) {
            return this.fValidation;
        }
        if (NOTIFY_CHAR_REFS.equals(str)) {
            return this.fNotifyCharRefs;
        }
        throw new XMLConfigurationException(Status.NOT_RECOGNIZED, str);
    }

    public XMLStringBuffer getStringBuffer() {
        int i = this.fStringBufferIndex;
        if (i < this.initialCacheCount || i < this.stringBufferCache.size()) {
            ArrayList<XMLStringBuffer> arrayList = this.stringBufferCache;
            int i2 = this.fStringBufferIndex;
            this.fStringBufferIndex = i2 + 1;
            return arrayList.get(i2);
        }
        XMLStringBuffer xMLStringBuffer = new XMLStringBuffer();
        this.fStringBufferIndex++;
        this.stringBufferCache.add(xMLStringBuffer);
        return xMLStringBuffer;
    }

    public boolean isInvalid(int i) {
        return XMLChar.isInvalid(i);
    }

    public boolean isInvalidLiteral(int i) {
        return XMLChar.isInvalid(i);
    }

    public boolean isValidNCName(int i) {
        return XMLChar.isNCName(i);
    }

    public boolean isValidNameChar(int i) {
        return XMLChar.isName(i);
    }

    public boolean isValidNameStartChar(int i) {
        return XMLChar.isNameStart(i);
    }

    public boolean isValidNameStartHighSurrogate(int i) {
        return false;
    }

    public void normalizeWhitespace(XMLString xMLString) {
        XMLEntityScanner xMLEntityScanner = this.fEntityScanner;
        int[] iArr = xMLEntityScanner.whiteSpaceLookup;
        int i = xMLEntityScanner.whiteSpaceLen;
        int i2 = xMLString.offset + xMLString.length;
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = iArr[i3];
            if (i4 < i2) {
                xMLString.ch[i4] = ' ';
            }
        }
    }

    public void reportFatalError(String str, Object[] objArr) throws XNIException {
        this.fErrorReporter.reportError((XMLLocator) this.fEntityScanner, "http://www.w3.org/TR/1998/REC-xml-19980210", str, objArr, (short) 2);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public void reset(XMLComponentManager xMLComponentManager) throws XMLConfigurationException {
        boolean feature = xMLComponentManager.getFeature(PARSER_SETTINGS, true);
        this.fParserSettings = feature;
        if (!feature) {
            init();
            return;
        }
        this.fSymbolTable = (SymbolTable) xMLComponentManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
        this.fErrorReporter = (XMLErrorReporter) xMLComponentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
        this.fEntityManager = (XMLEntityManager) xMLComponentManager.getProperty(ENTITY_MANAGER);
        this.fSecurityManager = (XMLSecurityManager) xMLComponentManager.getProperty("http://apache.org/xml/properties/security-manager");
        this.fEntityStore = this.fEntityManager.getEntityStore();
        this.fValidation = xMLComponentManager.getFeature(VALIDATION, false);
        this.fNamespaces = xMLComponentManager.getFeature("http://xml.org/sax/features/namespaces", true);
        this.fNotifyCharRefs = xMLComponentManager.getFeature(NOTIFY_CHAR_REFS, false);
        init();
    }

    public boolean resolveCharacter(String str, XMLStringBuffer xMLStringBuffer) {
        if (str == fAmpSymbol) {
            xMLStringBuffer.append('&');
            return true;
        }
        if (str == fAposSymbol) {
            xMLStringBuffer.append('\'');
            return true;
        }
        if (str == fLtSymbol) {
            xMLStringBuffer.append('<');
            return true;
        }
        if (str == fGtSymbol) {
            checkEntityLimit(false, this.fEntityScanner.fCurrentEntity.name, 1);
            xMLStringBuffer.append('>');
            return true;
        }
        if (str != fQuotSymbol) {
            return false;
        }
        checkEntityLimit(false, this.fEntityScanner.fCurrentEntity.name, 1);
        xMLStringBuffer.append('\"');
        return true;
    }

    public void scanAttributeValue(XMLString xMLString, XMLString xMLString2, String str, XMLAttributes xMLAttributes, int i, boolean z, String str2, boolean z2) throws IOException, XNIException {
        int iPeekChar = this.fEntityScanner.peekChar();
        if (iPeekChar != 39 && iPeekChar != 34) {
            reportFatalError("OpenQuoteExpected", new Object[]{str2, str});
        }
        this.fEntityScanner.scanChar(NameType.ATTRIBUTE);
        int i2 = this.fEntityDepth;
        int iScanLiteral = this.fEntityScanner.scanLiteral(iPeekChar, xMLString, z2);
        if (this.fNeedNonNormalizedValue) {
            this.fStringBuffer2.clear();
            this.fStringBuffer2.append(xMLString);
        }
        if (this.fEntityScanner.whiteSpaceLen > 0) {
            normalizeWhitespace(xMLString);
        }
        if (iScanLiteral != iPeekChar) {
            this.fScanningAttribute = true;
            XMLStringBuffer stringBuffer = getStringBuffer();
            stringBuffer.clear();
            while (true) {
                stringBuffer.append(xMLString);
                if (iScanLiteral == 38) {
                    XMLEntityScanner xMLEntityScanner = this.fEntityScanner;
                    NameType nameType = NameType.REFERENCE;
                    xMLEntityScanner.skipChar(38, nameType);
                    if (i2 == this.fEntityDepth && this.fNeedNonNormalizedValue) {
                        this.fStringBuffer2.append('&');
                    }
                    if (this.fEntityScanner.skipChar(35, nameType)) {
                        if (i2 == this.fEntityDepth && this.fNeedNonNormalizedValue) {
                            this.fStringBuffer2.append('#');
                        }
                        if (this.fNeedNonNormalizedValue) {
                            scanCharReferenceValue(stringBuffer, this.fStringBuffer2);
                        } else {
                            scanCharReferenceValue(stringBuffer, null);
                        }
                    } else {
                        String strScanName = this.fEntityScanner.scanName(NameType.ENTITY);
                        if (strScanName == null) {
                            reportFatalError("NameRequiredInReference", null);
                        } else if (i2 == this.fEntityDepth && this.fNeedNonNormalizedValue) {
                            this.fStringBuffer2.append(strScanName);
                        }
                        if (!this.fEntityScanner.skipChar(59, nameType)) {
                            reportFatalError("SemicolonRequiredInReference", new Object[]{strScanName});
                        } else if (i2 == this.fEntityDepth && this.fNeedNonNormalizedValue) {
                            this.fStringBuffer2.append(';');
                        }
                        if (resolveCharacter(strScanName, stringBuffer)) {
                            checkEntityLimit(false, this.fEntityScanner.fCurrentEntity.name, 1);
                        } else if (this.fEntityStore.isExternalEntity(strScanName)) {
                            reportFatalError("ReferenceToExternalEntity", new Object[]{strScanName});
                        } else {
                            if (!this.fEntityStore.isDeclaredEntity(strScanName)) {
                                if (!z) {
                                    reportFatalError("EntityNotDeclared", new Object[]{strScanName});
                                } else if (this.fValidation) {
                                    this.fErrorReporter.reportError((XMLLocator) this.fEntityScanner, "http://www.w3.org/TR/1998/REC-xml-19980210", "EntityNotDeclared", new Object[]{strScanName}, (short) 1);
                                }
                            }
                            this.fEntityManager.startEntity(true, strScanName, true);
                        }
                    }
                } else if (iScanLiteral == 60) {
                    reportFatalError("LessthanInAttValue", new Object[]{str2, str});
                    this.fEntityScanner.scanChar(null);
                    if (i2 == this.fEntityDepth && this.fNeedNonNormalizedValue) {
                        this.fStringBuffer2.append((char) iScanLiteral);
                    }
                } else if (iScanLiteral == 37 || iScanLiteral == 93) {
                    this.fEntityScanner.scanChar(null);
                    char c = (char) iScanLiteral;
                    stringBuffer.append(c);
                    if (i2 == this.fEntityDepth && this.fNeedNonNormalizedValue) {
                        this.fStringBuffer2.append(c);
                    }
                } else if (iScanLiteral != -1 && XMLChar.isHighSurrogate(iScanLiteral)) {
                    this.fStringBuffer3.clear();
                    if (scanSurrogates(this.fStringBuffer3)) {
                        stringBuffer.append(this.fStringBuffer3);
                        if (i2 == this.fEntityDepth && this.fNeedNonNormalizedValue) {
                            this.fStringBuffer2.append(this.fStringBuffer3);
                        }
                    }
                } else if (iScanLiteral != -1 && isInvalidLiteral(iScanLiteral)) {
                    reportFatalError("InvalidCharInAttValue", new Object[]{str2, str, Integer.toString(iScanLiteral, 16)});
                    this.fEntityScanner.scanChar(null);
                    if (i2 == this.fEntityDepth && this.fNeedNonNormalizedValue) {
                        this.fStringBuffer2.append((char) iScanLiteral);
                    }
                }
                iScanLiteral = this.fEntityScanner.scanLiteral(iPeekChar, xMLString, z2);
                if (i2 == this.fEntityDepth && this.fNeedNonNormalizedValue) {
                    this.fStringBuffer2.append(xMLString);
                }
                if (this.fEntityScanner.whiteSpaceLen > 0) {
                    normalizeWhitespace(xMLString);
                }
                if (iScanLiteral == iPeekChar && i2 == this.fEntityDepth) {
                    break;
                }
            }
            stringBuffer.append(xMLString);
            xMLString.setValues(stringBuffer);
            this.fScanningAttribute = false;
        }
        if (this.fNeedNonNormalizedValue) {
            xMLString2.setValues(this.fStringBuffer2);
        }
        if (this.fEntityScanner.scanChar(NameType.ATTRIBUTE) != iPeekChar) {
            reportFatalError("CloseQuoteExpected", new Object[]{str2, str});
        }
    }

    public int scanCharReferenceValue(XMLStringBuffer xMLStringBuffer, XMLStringBuffer xMLStringBuffer2) throws IOException, XNIException {
        boolean z;
        boolean z2;
        int i;
        int i2 = xMLStringBuffer.length;
        XMLEntityScanner xMLEntityScanner = this.fEntityScanner;
        NameType nameType = NameType.REFERENCE;
        if (xMLEntityScanner.skipChar(120, nameType)) {
            if (xMLStringBuffer2 != null) {
                xMLStringBuffer2.append('x');
            }
            this.fStringBuffer3.clear();
            int iPeekChar = this.fEntityScanner.peekChar();
            int i3 = 97;
            if ((iPeekChar < 48 || iPeekChar > 57) && ((iPeekChar < 97 || iPeekChar > 102) && (iPeekChar < 65 || iPeekChar > 70))) {
                reportFatalError("HexdigitRequiredInCharRef", null);
            } else {
                if (xMLStringBuffer2 != null) {
                    xMLStringBuffer2.append((char) iPeekChar);
                }
                this.fEntityScanner.scanChar(nameType);
                this.fStringBuffer3.append((char) iPeekChar);
                while (true) {
                    int iPeekChar2 = this.fEntityScanner.peekChar();
                    boolean z3 = (iPeekChar2 >= 48 && iPeekChar2 <= 57) || (iPeekChar2 >= i3 && iPeekChar2 <= 102) || (iPeekChar2 >= 65 && iPeekChar2 <= 70);
                    if (z3) {
                        if (xMLStringBuffer2 != null) {
                            xMLStringBuffer2.append((char) iPeekChar2);
                        }
                        this.fEntityScanner.scanChar(NameType.REFERENCE);
                        this.fStringBuffer3.append((char) iPeekChar2);
                    }
                    if (!z3) {
                        break;
                    }
                    i3 = 97;
                }
            }
            z = true;
        } else {
            this.fStringBuffer3.clear();
            int iPeekChar3 = this.fEntityScanner.peekChar();
            if (iPeekChar3 < 48 || iPeekChar3 > 57) {
                reportFatalError("DigitRequiredInCharRef", null);
            } else {
                if (xMLStringBuffer2 != null) {
                    xMLStringBuffer2.append((char) iPeekChar3);
                }
                this.fEntityScanner.scanChar(nameType);
                this.fStringBuffer3.append((char) iPeekChar3);
                do {
                    int iPeekChar4 = this.fEntityScanner.peekChar();
                    z2 = iPeekChar4 >= 48 && iPeekChar4 <= 57;
                    if (z2) {
                        if (xMLStringBuffer2 != null) {
                            xMLStringBuffer2.append((char) iPeekChar4);
                        }
                        this.fEntityScanner.scanChar(NameType.REFERENCE);
                        this.fStringBuffer3.append((char) iPeekChar4);
                    }
                } while (z2);
            }
            z = false;
        }
        if (!this.fEntityScanner.skipChar(59, NameType.REFERENCE)) {
            reportFatalError("SemicolonRequiredInCharRef", null);
        }
        if (xMLStringBuffer2 != null) {
            xMLStringBuffer2.append(';');
        }
        try {
            i = Integer.parseInt(this.fStringBuffer3.toString(), z ? 16 : 10);
            try {
                if (isInvalid(i)) {
                    StringBuffer stringBuffer = new StringBuffer(this.fStringBuffer3.length + 1);
                    if (z) {
                        stringBuffer.append('x');
                    }
                    XMLStringBuffer xMLStringBuffer3 = this.fStringBuffer3;
                    stringBuffer.append(xMLStringBuffer3.ch, xMLStringBuffer3.offset, xMLStringBuffer3.length);
                    reportFatalError("InvalidCharRef", new Object[]{stringBuffer.toString()});
                }
            } catch (NumberFormatException unused) {
                StringBuffer stringBuffer2 = new StringBuffer(this.fStringBuffer3.length + 1);
                if (z) {
                    stringBuffer2.append('x');
                }
                XMLStringBuffer xMLStringBuffer4 = this.fStringBuffer3;
                stringBuffer2.append(xMLStringBuffer4.ch, xMLStringBuffer4.offset, xMLStringBuffer4.length);
                reportFatalError("InvalidCharRef", new Object[]{stringBuffer2.toString()});
            }
        } catch (NumberFormatException unused2) {
            i = -1;
        }
        if (XMLChar.isSupplemental(i)) {
            xMLStringBuffer.append(XMLChar.highSurrogate(i));
            xMLStringBuffer.append(XMLChar.lowSurrogate(i));
        } else {
            xMLStringBuffer.append((char) i);
        }
        if (this.fNotifyCharRefs && i != -1) {
            StringBuilder sb = new StringBuilder("#");
            sb.append(z ? "x" : "");
            sb.append(this.fStringBuffer3.toString());
            String string = sb.toString();
            if (!this.fScanningAttribute) {
                this.fCharRefLiteral = string;
            }
        }
        Entity.ScannedEntity scannedEntity = this.fEntityScanner.fCurrentEntity;
        if (scannedEntity.isGE) {
            checkEntityLimit(false, scannedEntity.name, xMLStringBuffer.length - i2);
        }
        return i;
    }

    public void scanComment(XMLStringBuffer xMLStringBuffer) throws IOException, XNIException {
        XMLEntityScanner xMLEntityScanner;
        xMLStringBuffer.clear();
        while (true) {
            boolean zScanData = this.fEntityScanner.scanData("--", xMLStringBuffer, 0);
            xMLEntityScanner = this.fEntityScanner;
            if (!zScanData) {
                break;
            }
            int iPeekChar = xMLEntityScanner.peekChar();
            if (iPeekChar != -1) {
                if (XMLChar.isHighSurrogate(iPeekChar)) {
                    scanSurrogates(xMLStringBuffer);
                } else if (isInvalidLiteral(iPeekChar)) {
                    reportFatalError("InvalidCharInComment", new Object[]{Integer.toHexString(iPeekChar)});
                    this.fEntityScanner.scanChar(NameType.COMMENT);
                }
            }
        }
        if (xMLEntityScanner.skipChar(62, NameType.COMMENT)) {
            return;
        }
        reportFatalError("DashDashInComment", null);
    }

    public void scanExternalID(String[] strArr, boolean z) throws IOException, XNIException {
        String string;
        String str = null;
        if (this.fEntityScanner.skipString("PUBLIC")) {
            if (!this.fEntityScanner.skipSpaces()) {
                reportFatalError("SpaceRequiredAfterPUBLIC", null);
            }
            scanPubidLiteral(this.fString);
            string = this.fString.toString();
            if (!this.fEntityScanner.skipSpaces() && !z) {
                reportFatalError("SpaceRequiredBetweenPublicAndSystem", null);
            }
        } else {
            string = null;
        }
        if (string != null || this.fEntityScanner.skipString("SYSTEM")) {
            if (string == null && !this.fEntityScanner.skipSpaces()) {
                reportFatalError("SpaceRequiredAfterSYSTEM", null);
            }
            int iPeekChar = this.fEntityScanner.peekChar();
            if (iPeekChar != 39 && iPeekChar != 34) {
                if (string != null && z) {
                    strArr[0] = null;
                    strArr[1] = string;
                    return;
                }
                reportFatalError("QuoteRequiredInSystemID", null);
            }
            this.fEntityScanner.scanChar(null);
            XMLString xMLString = this.fString;
            if (this.fEntityScanner.scanLiteral(iPeekChar, xMLString, false) != iPeekChar) {
                this.fStringBuffer.clear();
                do {
                    this.fStringBuffer.append(xMLString);
                    int iPeekChar2 = this.fEntityScanner.peekChar();
                    if (XMLChar.isMarkup(iPeekChar2) || iPeekChar2 == 93) {
                        this.fStringBuffer.append((char) this.fEntityScanner.scanChar(null));
                    } else if (iPeekChar2 != -1 && isInvalidLiteral(iPeekChar2)) {
                        reportFatalError("InvalidCharInSystemID", new Object[]{Integer.toString(iPeekChar2, 16)});
                    }
                } while (this.fEntityScanner.scanLiteral(iPeekChar, xMLString, false) != iPeekChar);
                this.fStringBuffer.append(xMLString);
                xMLString = this.fStringBuffer;
            }
            String string2 = xMLString.toString();
            if (!this.fEntityScanner.skipChar(iPeekChar, null)) {
                reportFatalError("SystemIDUnterminated", null);
            }
            str = string2;
        }
        strArr[0] = str;
        strArr[1] = string;
    }

    public void scanPI(XMLStringBuffer xMLStringBuffer) throws IOException, XNIException {
        this.fReportEntity = false;
        String strScanName = this.fEntityScanner.scanName(NameType.PI);
        if (strScanName == null) {
            reportFatalError("PITargetRequired", null);
        }
        scanPIData(strScanName, xMLStringBuffer);
        this.fReportEntity = true;
    }

    public void scanPIData(String str, XMLStringBuffer xMLStringBuffer) throws IOException, XNIException {
        if (str.length() == 3) {
            char lowerCase = Character.toLowerCase(str.charAt(0));
            char lowerCase2 = Character.toLowerCase(str.charAt(1));
            char lowerCase3 = Character.toLowerCase(str.charAt(2));
            if (lowerCase == 'x' && lowerCase2 == 'm' && lowerCase3 == 'l') {
                reportFatalError("ReservedPITarget", null);
            }
        }
        if (!this.fEntityScanner.skipSpaces()) {
            if (this.fEntityScanner.skipString("?>")) {
                return;
            } else {
                reportFatalError("SpaceRequiredInPI", null);
            }
        }
        if (this.fEntityScanner.scanData("?>", xMLStringBuffer, 0)) {
            do {
                int iPeekChar = this.fEntityScanner.peekChar();
                if (iPeekChar != -1) {
                    if (XMLChar.isHighSurrogate(iPeekChar)) {
                        scanSurrogates(xMLStringBuffer);
                    } else if (isInvalidLiteral(iPeekChar)) {
                        reportFatalError("InvalidCharInPI", new Object[]{Integer.toHexString(iPeekChar)});
                        this.fEntityScanner.scanChar(null);
                    }
                }
            } while (this.fEntityScanner.scanData("?>", xMLStringBuffer, 0));
        }
    }

    public String scanPseudoAttribute(boolean z, XMLString xMLString) throws IOException, XNIException {
        String strScanPseudoAttributeName = scanPseudoAttributeName();
        if (strScanPseudoAttributeName == null) {
            reportFatalError("PseudoAttrNameExpected", null);
        }
        this.fEntityScanner.skipSpaces();
        if (!this.fEntityScanner.skipChar(61, null)) {
            reportFatalError(z ? "EqRequiredInTextDecl" : "EqRequiredInXMLDecl", new Object[]{strScanPseudoAttributeName});
        }
        this.fEntityScanner.skipSpaces();
        int iPeekChar = this.fEntityScanner.peekChar();
        if (iPeekChar != 39 && iPeekChar != 34) {
            reportFatalError(z ? "QuoteRequiredInTextDecl" : "QuoteRequiredInXMLDecl", new Object[]{strScanPseudoAttributeName});
        }
        this.fEntityScanner.scanChar(NameType.ATTRIBUTE);
        int iScanLiteral = this.fEntityScanner.scanLiteral(iPeekChar, xMLString, false);
        if (iScanLiteral != iPeekChar) {
            this.fStringBuffer2.clear();
            do {
                this.fStringBuffer2.append(xMLString);
                if (iScanLiteral != -1) {
                    if (iScanLiteral == 38 || iScanLiteral == 37 || iScanLiteral == 60 || iScanLiteral == 93) {
                        this.fStringBuffer2.append((char) this.fEntityScanner.scanChar(NameType.ATTRIBUTE));
                    } else if (XMLChar.isHighSurrogate(iScanLiteral)) {
                        scanSurrogates(this.fStringBuffer2);
                    } else if (isInvalidLiteral(iScanLiteral)) {
                        reportFatalError(z ? "InvalidCharInTextDecl" : "InvalidCharInXMLDecl", new Object[]{Integer.toString(iScanLiteral, 16)});
                        this.fEntityScanner.scanChar(null);
                    }
                }
                iScanLiteral = this.fEntityScanner.scanLiteral(iPeekChar, xMLString, false);
            } while (iScanLiteral != iPeekChar);
            this.fStringBuffer2.append(xMLString);
            xMLString.setValues(this.fStringBuffer2);
        }
        if (!this.fEntityScanner.skipChar(iPeekChar, null)) {
            reportFatalError(z ? "CloseQuoteMissingInTextDecl" : "CloseQuoteMissingInXMLDecl", new Object[]{strScanPseudoAttributeName});
        }
        return strScanPseudoAttributeName;
    }

    public boolean scanPubidLiteral(XMLString xMLString) throws IOException, XNIException {
        int iScanChar = this.fEntityScanner.scanChar(null);
        if (iScanChar != 39 && iScanChar != 34) {
            reportFatalError("QuoteRequiredInPublicID", null);
            return false;
        }
        this.fStringBuffer.clear();
        boolean z = true;
        boolean z2 = true;
        while (true) {
            int iScanChar2 = this.fEntityScanner.scanChar(null);
            if (iScanChar2 == 32 || iScanChar2 == 10 || iScanChar2 == 13) {
                if (!z) {
                    this.fStringBuffer.append(' ');
                    z = true;
                }
            } else {
                if (iScanChar2 == iScanChar) {
                    if (z) {
                        this.fStringBuffer.length--;
                    }
                    xMLString.setValues(this.fStringBuffer);
                    return z2;
                }
                if (XMLChar.isPubid(iScanChar2)) {
                    this.fStringBuffer.append((char) iScanChar2);
                    z = false;
                } else {
                    if (iScanChar2 == -1) {
                        reportFatalError("PublicIDUnterminated", null);
                        return false;
                    }
                    reportFatalError("InvalidCharInPublicID", new Object[]{Integer.toHexString(iScanChar2)});
                    z2 = false;
                }
            }
        }
    }

    public boolean scanSurrogates(XMLStringBuffer xMLStringBuffer) throws IOException, XNIException {
        int iScanChar = this.fEntityScanner.scanChar(null);
        int iPeekChar = this.fEntityScanner.peekChar();
        if (!XMLChar.isLowSurrogate(iPeekChar)) {
            reportFatalError("InvalidCharInContent", new Object[]{Integer.toString(iScanChar, 16)});
            return false;
        }
        this.fEntityScanner.scanChar(null);
        char c = (char) iScanChar;
        char c2 = (char) iPeekChar;
        int iSupplemental = XMLChar.supplemental(c, c2);
        if (isInvalid(iSupplemental)) {
            reportFatalError("InvalidCharInContent", new Object[]{Integer.toString(iSupplemental, 16)});
            return false;
        }
        xMLStringBuffer.append(c);
        xMLStringBuffer.append(c2);
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:32:0x009b A[PHI: r10
      0x009b: PHI (r10v3 java.lang.String) = (r10v2 java.lang.String), (r10v6 java.lang.String) binds: [B:75:0x0140, B:30:0x0098] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.sun.org.apache.xerces.internal.impl.XMLScanner$NameType, java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void scanXMLDeclOrTextDecl(boolean z, String[] strArr) throws IOException, XNIException {
        char c;
        ?? r6;
        String str;
        boolean zSkipSpaces = this.fEntityScanner.skipSpaces();
        Entity.ScannedEntity currentEntity = this.fEntityManager.getCurrentEntity();
        boolean z2 = currentEntity.literal;
        char c2 = 0;
        currentEntity.literal = false;
        char c3 = 0;
        boolean z3 = false;
        String string = null;
        String string2 = null;
        String string3 = null;
        while (true) {
            c = c2;
            if (this.fEntityScanner.peekChar() == 63) {
                break;
            }
            String strScanPseudoAttribute = scanPseudoAttribute(z, this.fString);
            if (c3 != 0) {
                boolean z4 = zSkipSpaces;
                if (c3 == 1) {
                    if (strScanPseudoAttribute.equals(fEncodingSymbol)) {
                        if (!z4) {
                            reportFatalError(z ? "SpaceRequiredBeforeEncodingInTextDecl" : "SpaceRequiredBeforeEncodingInXMLDecl", null);
                        }
                        string2 = this.fString.toString();
                        if (!z) {
                            c3 = 2;
                        }
                    } else if (z || !strScanPseudoAttribute.equals(fStandaloneSymbol)) {
                        reportFatalError("EncodingDeclRequired", null);
                    } else {
                        if (!z4) {
                            reportFatalError("SpaceRequiredBeforeStandalone", null);
                        }
                        string3 = this.fString.toString();
                        if (!string3.equals(JdkConstants.JDK_YES) && !string3.equals("no")) {
                            reportFatalError("SDDeclInvalid", new Object[]{string3});
                        }
                    }
                    c3 = 3;
                } else if (c3 != 2) {
                    reportFatalError("NoMorePseudoAttributes", null);
                } else if (strScanPseudoAttribute.equals(fStandaloneSymbol)) {
                    if (!z4) {
                        reportFatalError("SpaceRequiredBeforeStandalone", null);
                    }
                    string3 = this.fString.toString();
                    if (!string3.equals(JdkConstants.JDK_YES) && !string3.equals("no")) {
                        reportFatalError("SDDeclInvalid", new Object[]{string3});
                    }
                    c3 = 3;
                } else {
                    reportFatalError("SDDeclNameInvalid", null);
                }
            } else {
                boolean z5 = zSkipSpaces;
                if (strScanPseudoAttribute.equals(fVersionSymbol)) {
                    if (!z5) {
                        reportFatalError(z ? "SpaceRequiredBeforeVersionInTextDecl" : "SpaceRequiredBeforeVersionInXMLDecl", null);
                    }
                    string = this.fString.toString();
                    if (!versionSupported(string)) {
                        reportFatalError("VersionNotSupported", new Object[]{string});
                    }
                    if (string.equals(SerializerConstants.XMLVERSION11)) {
                        Entity.ScannedEntity topLevelEntity = this.fEntityManager.getTopLevelEntity();
                        if (topLevelEntity != null && ((str = topLevelEntity.version) == null || str.equals("1.0"))) {
                            reportFatalError("VersionMismatch", null);
                        }
                        this.fEntityManager.setScannerVersion((short) 2);
                    }
                    c3 = 1;
                } else if (strScanPseudoAttribute.equals(fEncodingSymbol)) {
                    if (!z) {
                        reportFatalError("VersionInfoRequired", null);
                    }
                    if (!z5) {
                        reportFatalError(z ? "SpaceRequiredBeforeEncodingInTextDecl" : "SpaceRequiredBeforeEncodingInXMLDecl", null);
                    }
                    string2 = this.fString.toString();
                    if (z) {
                        c3 = 3;
                    } else {
                        c3 = 2;
                    }
                } else if (z) {
                    reportFatalError("EncodingDeclRequired", null);
                } else {
                    reportFatalError("VersionInfoRequired", null);
                }
            }
            zSkipSpaces = this.fEntityScanner.skipSpaces();
            c2 = c;
            z3 = true;
        }
        if (z2) {
            currentEntity.literal = true;
        }
        if (!z || c3 == 3) {
            r6 = 0;
        } else {
            r6 = 0;
            reportFatalError("MorePseudoAttributes", null);
        }
        if (z) {
            if (!z3 && string2 == null) {
                reportFatalError("EncodingDeclRequired", r6);
            }
        } else if (!z3 && string == null) {
            reportFatalError("VersionInfoRequired", r6);
        }
        if (!this.fEntityScanner.skipChar(63, r6)) {
            reportFatalError("XMLDeclUnterminated", r6);
        }
        if (!this.fEntityScanner.skipChar(62, r6)) {
            reportFatalError("XMLDeclUnterminated", r6);
        }
        strArr[c] = string;
        strArr[1] = string2;
        strArr[2] = string3;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public void setFeature(String str, boolean z) throws XMLConfigurationException {
        if (VALIDATION.equals(str)) {
            this.fValidation = z;
        } else if (NOTIFY_CHAR_REFS.equals(str)) {
            this.fNotifyCharRefs = z;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public void setProperty(String str, Object obj) throws XMLConfigurationException {
        if (str.startsWith(Constants.XERCES_PROPERTY_PREFIX)) {
            String strSubstring = str.substring(33);
            if (strSubstring.equals(Constants.SYMBOL_TABLE_PROPERTY)) {
                this.fSymbolTable = (SymbolTable) obj;
            } else if (strSubstring.equals(Constants.ERROR_REPORTER_PROPERTY)) {
                this.fErrorReporter = (XMLErrorReporter) obj;
            } else if (strSubstring.equals(Constants.ENTITY_MANAGER_PROPERTY)) {
                this.fEntityManager = (XMLEntityManager) obj;
            }
        }
        if (str.equals("http://apache.org/xml/properties/security-manager")) {
            this.fSecurityManager = (XMLSecurityManager) obj;
        }
    }

    public void setPropertyManager(PropertyManager propertyManager) {
        this.fPropertyManager = propertyManager;
    }

    public void startEntity(String str, XMLResourceIdentifier xMLResourceIdentifier, String str2, Augmentations augmentations) throws XNIException {
        this.fEntityDepth++;
        this.fEntityScanner = this.fEntityManager.getEntityScanner();
        this.fEntityStore = this.fEntityManager.getEntityStore();
    }

    public boolean versionSupported(String str) {
        return str.equals("1.0") || str.equals(SerializerConstants.XMLVERSION11);
    }

    public void reset() {
        init();
        this.fValidation = true;
        this.fNotifyCharRefs = false;
    }

    public void reset(PropertyManager propertyManager) {
        init();
        this.fSymbolTable = (SymbolTable) propertyManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
        this.fErrorReporter = (XMLErrorReporter) propertyManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
        XMLEntityManager xMLEntityManager = (XMLEntityManager) propertyManager.getProperty(ENTITY_MANAGER);
        this.fEntityManager = xMLEntityManager;
        this.fEntityStore = xMLEntityManager.getEntityStore();
        this.fEntityScanner = this.fEntityManager.getEntityScanner();
        this.fSecurityManager = (XMLSecurityManager) propertyManager.getProperty("http://apache.org/xml/properties/security-manager");
        this.fValidation = false;
        this.fNotifyCharRefs = false;
    }

    public void checkEntityLimit(boolean z, String str, XMLString xMLString) {
        checkEntityLimit(z, str, xMLString.length);
    }
}
