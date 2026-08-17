package com.sun.org.apache.xerces.internal.xpointer;

import com.sun.org.apache.xerces.internal.impl.XMLErrorReporter;
import com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaLoader;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.util.XMLChar;
import com.sun.org.apache.xerces.internal.util.XMLSymbols;
import com.sun.org.apache.xerces.internal.xinclude.XIncludeHandler;
import com.sun.org.apache.xerces.internal.xinclude.XIncludeNamespaceSupport;
import com.sun.org.apache.xerces.internal.xni.Augmentations;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xni.XMLAttributes;
import com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler;
import com.sun.org.apache.xerces.internal.xni.XMLString;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLErrorHandler;
import defpackage.knd;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class XPointerHandler extends XIncludeHandler implements XPointerProcessor {
    protected XMLErrorHandler fErrorHandler;
    protected SymbolTable fSymbolTable;
    protected XMLErrorReporter fXPointerErrorReporter;
    protected ArrayList<XPointerPart> fXPointerParts;
    protected XPointerPart fXPointerPart = null;
    protected boolean fFoundMatchingPtrPart = false;
    private final String ELEMENT_SCHEME_NAME = "element";
    protected boolean fIsXPointerResolved = false;
    protected boolean fFixupBase = false;
    protected boolean fFixupLang = false;

    public class Scanner {
        private static final byte CHARTYPE_CARRET = 3;
        private static final byte CHARTYPE_CLOSE_PAREN = 5;
        private static final byte CHARTYPE_COLON = 10;
        private static final byte CHARTYPE_DIGIT = 9;
        private static final byte CHARTYPE_EQUAL = 11;
        private static final byte CHARTYPE_INVALID = 0;
        private static final byte CHARTYPE_LETTER = 12;
        private static final byte CHARTYPE_MINUS = 6;
        private static final byte CHARTYPE_NONASCII = 14;
        private static final byte CHARTYPE_OPEN_PAREN = 4;
        private static final byte CHARTYPE_OTHER = 1;
        private static final byte CHARTYPE_PERIOD = 7;
        private static final byte CHARTYPE_SLASH = 8;
        private static final byte CHARTYPE_UNDERSCORE = 13;
        private static final byte CHARTYPE_WHITESPACE = 2;
        private final byte[] fASCIICharMap;
        private SymbolTable fSymbolTable;

        private Scanner(SymbolTable symbolTable) {
            this.fASCIICharMap = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 1, 1, 1, 4, 5, 1, 1, 1, 6, 7, 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 10, 1, 1, 11, 1, 1, 1, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 1, 1, 1, 3, 13, 1, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 1, 1, 1, 1, 1};
            this.fSymbolTable = symbolTable;
        }

        private int scanData(String str, StringBuffer stringBuffer, int i, int i2) {
            while (i2 != i) {
                char cCharAt = str.charAt(i2);
                byte b = cCharAt >= 128 ? (byte) 14 : this.fASCIICharMap[cCharAt];
                if (b == 4) {
                    stringBuffer.append((int) cCharAt);
                    i2 = scanData(str, stringBuffer, i, i2 + 1);
                    if (i2 == i) {
                        return i2;
                    }
                    char cCharAt2 = str.charAt(i2);
                    if ((cCharAt2 < 128 ? this.fASCIICharMap[cCharAt2] : (byte) 14) != 5) {
                        return i;
                    }
                    stringBuffer.append(cCharAt2);
                } else {
                    if (b == 5) {
                        break;
                    }
                    if (b == 3) {
                        int i3 = i2 + 1;
                        char cCharAt3 = str.charAt(i3);
                        byte b2 = cCharAt3 < 128 ? this.fASCIICharMap[cCharAt3] : (byte) 14;
                        if (b2 != 3 && b2 != 4 && b2 != 5) {
                            return i3;
                        }
                        stringBuffer.append(cCharAt3);
                        i2 += 2;
                    } else {
                        stringBuffer.append(cCharAt);
                    }
                }
                i2++;
            }
            return i2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean scanExpr(SymbolTable symbolTable, Tokens tokens, String str, int i, int i2) throws XNIException {
            int iScanNCName;
            StringBuffer stringBuffer = new StringBuffer();
            String strAddSymbol = null;
            int i3 = 0;
            int i4 = 0;
            while (i != i2) {
                char cCharAt = str.charAt(i);
                while (true) {
                    if ((cCharAt == ' ' || cCharAt == '\n' || cCharAt == '\t' || cCharAt == '\r') && (i = i + 1) != i2) {
                        cCharAt = str.charAt(i);
                    }
                }
                if (i == i2) {
                    return true;
                }
                switch (cCharAt >= 128 ? (byte) 14 : this.fASCIICharMap[cCharAt]) {
                    case 1:
                    case 2:
                    case 3:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                        if (i3 == 0) {
                            int iScanNCName2 = scanNCName(str, i2, i);
                            if (iScanNCName2 == i) {
                                XPointerHandler.this.reportError("InvalidShortHandPointer", new Object[]{str});
                                return false;
                            }
                            byte bCharAt = iScanNCName2 < i2 ? str.charAt(iScanNCName2) : (byte) -1;
                            String strAddSymbol2 = symbolTable.addSymbol(str.substring(i, iScanNCName2));
                            String str2 = XMLSymbols.EMPTY_STRING;
                            if (bCharAt == 58) {
                                int i5 = iScanNCName2 + 1;
                                if (i5 == i2) {
                                    return false;
                                }
                                str.charAt(i5);
                                iScanNCName = scanNCName(str, i2, i5);
                                if (iScanNCName == i5) {
                                    return false;
                                }
                                if (iScanNCName < i2) {
                                    str.charAt(iScanNCName);
                                }
                                strAddSymbol = symbolTable.addSymbol(str.substring(i5, iScanNCName));
                            } else {
                                iScanNCName = iScanNCName2;
                                strAddSymbol = strAddSymbol2;
                                strAddSymbol2 = str2;
                            }
                            if (iScanNCName != i2) {
                                addToken(tokens, 3);
                                tokens.addToken(strAddSymbol2);
                                tokens.addToken(strAddSymbol);
                            } else if (iScanNCName == i2) {
                                addToken(tokens, 2);
                                tokens.addToken(strAddSymbol);
                            }
                            i = iScanNCName;
                            i4 = 0;
                        } else {
                            if (i3 <= 0 || i4 != 0 || strAddSymbol == null) {
                                return false;
                            }
                            int iScanData = scanData(str, stringBuffer, i2, i);
                            if (iScanData == i) {
                                XPointerHandler.this.reportError("InvalidSchemeDataInXPointer", new Object[]{str});
                                return false;
                            }
                            if (iScanData < i2) {
                                str.charAt(iScanData);
                            }
                            String strAddSymbol3 = symbolTable.addSymbol(stringBuffer.toString());
                            addToken(tokens, 4);
                            tokens.addToken(strAddSymbol3);
                            stringBuffer.delete(0, stringBuffer.length());
                            i = iScanData;
                            i3 = 0;
                            continue;
                        }
                        break;
                    case 4:
                        addToken(tokens, 0);
                        i3++;
                        break;
                    case 5:
                        addToken(tokens, 1);
                        i4++;
                        break;
                    default:
                        continue;
                }
                i++;
            }
            return true;
        }

        private int scanNCName(String str, int i, int i2) {
            byte b;
            char cCharAt = str.charAt(i2);
            if (cCharAt < 128 ? !((b = this.fASCIICharMap[cCharAt]) == 12 || b == 13) : !XMLChar.isNameStart(cCharAt)) {
                return i2;
            }
            while (true) {
                i2++;
                if (i2 < i) {
                    char cCharAt2 = str.charAt(i2);
                    if (cCharAt2 < 128) {
                        byte b2 = this.fASCIICharMap[cCharAt2];
                        if (b2 != 12 && b2 != 9 && b2 != 7 && b2 != 6 && b2 != 13) {
                            break;
                        }
                    } else if (!XMLChar.isName(cCharAt2)) {
                        break;
                    }
                } else {
                    break;
                }
            }
            return i2;
        }

        public void addToken(Tokens tokens, int i) throws XNIException {
            tokens.addToken(i);
        }
    }

    public XPointerHandler() {
        this.fXPointerParts = null;
        this.fSymbolTable = null;
        this.fXPointerParts = new ArrayList<>();
        this.fSymbolTable = new SymbolTable();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportError(String str, Object[] objArr) throws XNIException {
        throw new XNIException(this.fErrorReporter.getMessageFormatter(XPointerMessageFormatter.XPOINTER_DOMAIN).formatMessage(this.fErrorReporter.getLocale(), str, objArr));
    }

    private void reportWarning(String str, Object[] objArr) throws XNIException {
        this.fXPointerErrorReporter.reportError(XPointerMessageFormatter.XPOINTER_DOMAIN, str, objArr, (short) 0);
    }

    @Override // com.sun.org.apache.xerces.internal.xinclude.XIncludeHandler, com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void characters(XMLString xMLString, Augmentations augmentations) throws XNIException {
        if (isChildFragmentResolved()) {
            super.characters(xMLString, augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xinclude.XIncludeHandler, com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void comment(XMLString xMLString, Augmentations augmentations) throws XNIException {
        if (isChildFragmentResolved()) {
            super.comment(xMLString, augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xinclude.XIncludeHandler, com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void emptyElement(QName qName, XMLAttributes xMLAttributes, Augmentations augmentations) throws XNIException {
        if (resolveXPointer(qName, xMLAttributes, augmentations, 2)) {
            super.emptyElement(qName, xMLAttributes, augmentations);
            return;
        }
        if (this.fFixupBase) {
            processXMLBaseAttributes(xMLAttributes);
        }
        if (this.fFixupLang) {
            processXMLLangAttributes(xMLAttributes);
        }
        this.fNamespaceContext.setContextInvalid();
    }

    @Override // com.sun.org.apache.xerces.internal.xinclude.XIncludeHandler, com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void endCDATA(Augmentations augmentations) throws XNIException {
        if (isChildFragmentResolved()) {
            super.endCDATA(augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xinclude.XIncludeHandler, com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void endElement(QName qName, Augmentations augmentations) throws XNIException {
        if (resolveXPointer(qName, null, augmentations, 1)) {
            super.endElement(qName, augmentations);
        }
    }

    public ArrayList<XPointerPart> getPointerParts() {
        return this.fXPointerParts;
    }

    public XPointerPart getXPointerPart() {
        return this.fXPointerPart;
    }

    @Override // com.sun.org.apache.xerces.internal.xinclude.XIncludeHandler, com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void ignorableWhitespace(XMLString xMLString, Augmentations augmentations) throws XNIException {
        if (isChildFragmentResolved()) {
            super.ignorableWhitespace(xMLString, augmentations);
        }
    }

    public void init() {
        this.fXPointerParts.clear();
        this.fXPointerPart = null;
        this.fFoundMatchingPtrPart = false;
        this.fIsXPointerResolved = false;
        initErrorReporter();
    }

    public void initErrorReporter() {
        if (this.fXPointerErrorReporter == null) {
            this.fXPointerErrorReporter = new XMLErrorReporter();
        }
        if (this.fErrorHandler == null) {
            this.fErrorHandler = new XPointerErrorHandler();
        }
        this.fXPointerErrorReporter.putMessageFormatter(XPointerMessageFormatter.XPOINTER_DOMAIN, new XPointerMessageFormatter());
    }

    public boolean isChildFragmentResolved() throws XNIException {
        XPointerPart xPointerPart = this.fXPointerPart;
        if (xPointerPart != null) {
            return xPointerPart.isChildFragmentResolved();
        }
        return false;
    }

    @Override // com.sun.org.apache.xerces.internal.xpointer.XPointerProcessor
    public boolean isFragmentResolved() throws XNIException {
        XPointerPart xPointerPart = this.fXPointerPart;
        boolean zIsFragmentResolved = xPointerPart != null ? xPointerPart.isFragmentResolved() : false;
        if (!this.fIsXPointerResolved) {
            this.fIsXPointerResolved = zIsFragmentResolved;
        }
        return zIsFragmentResolved;
    }

    @Override // com.sun.org.apache.xerces.internal.xpointer.XPointerProcessor
    public boolean isXPointerResolved() throws XNIException {
        return this.fIsXPointerResolved;
    }

    @Override // com.sun.org.apache.xerces.internal.xpointer.XPointerProcessor
    public void parseXPointer(String str) throws XNIException {
        init();
        Tokens tokens = new Tokens(this.fSymbolTable);
        if (!new Scanner(this.fSymbolTable) { // from class: com.sun.org.apache.xerces.internal.xpointer.XPointerHandler.1
            @Override // com.sun.org.apache.xerces.internal.xpointer.XPointerHandler.Scanner
            public void addToken(Tokens tokens2, int i) throws XNIException {
                if (i == 0 || i == 1 || i == 3 || i == 4 || i == 2) {
                    super.addToken(tokens2, i);
                } else {
                    XPointerHandler.this.reportError("InvalidXPointerToken", new Object[]{tokens2.getTokenString(i)});
                }
            }
        }.scanExpr(this.fSymbolTable, tokens, str, 0, str.length())) {
            reportError("InvalidXPointerExpression", new Object[]{str});
        }
        while (tokens.hasMore()) {
            int iNextToken = tokens.nextToken();
            if (iNextToken == 2) {
                String tokenString = tokens.getTokenString(tokens.nextToken());
                if (tokenString == null) {
                    reportError("InvalidXPointerExpression", new Object[]{str});
                }
                ShortHandPointer shortHandPointer = new ShortHandPointer(this.fSymbolTable);
                shortHandPointer.setSchemeName(tokenString);
                this.fXPointerParts.add(shortHandPointer);
            } else if (iNextToken != 3) {
                reportError("InvalidXPointerExpression", new Object[]{str});
            } else {
                String str2 = tokens.getTokenString(tokens.nextToken()) + tokens.getTokenString(tokens.nextToken());
                int iNextToken2 = tokens.nextToken();
                if (tokens.getTokenString(iNextToken2) != "XPTRTOKEN_OPEN_PAREN") {
                    if (iNextToken2 == 2) {
                        reportError("MultipleShortHandPointers", new Object[]{str});
                    } else {
                        reportError("InvalidXPointerExpression", new Object[]{str});
                    }
                }
                int i = 1;
                int i2 = 1;
                while (tokens.hasMore() && tokens.getTokenString(tokens.nextToken()) == "XPTRTOKEN_OPEN_PAREN") {
                    i2++;
                }
                String tokenString2 = tokens.getTokenString(tokens.nextToken());
                if (tokens.getTokenString(tokens.nextToken()) != "XPTRTOKEN_CLOSE_PAREN") {
                    reportError("SchemeDataNotFollowedByCloseParenthesis", new Object[]{str});
                }
                while (tokens.hasMore() && tokens.getTokenString(tokens.peekToken()) == "XPTRTOKEN_OPEN_PAREN") {
                    i++;
                }
                if (i2 != i) {
                    reportError("UnbalancedParenthesisInXPointerExpression", new Object[]{str, Integer.valueOf(i2), Integer.valueOf(i)});
                }
                if (str2.equals("element")) {
                    ElementSchemePointer elementSchemePointer = new ElementSchemePointer(this.fSymbolTable, this.fErrorReporter);
                    elementSchemePointer.setSchemeName(str2);
                    elementSchemePointer.setSchemeData(tokenString2);
                    try {
                        elementSchemePointer.parseXPointer(tokenString2);
                        this.fXPointerParts.add(elementSchemePointer);
                    } catch (XNIException e) {
                        knd.a(e);
                        return;
                    }
                } else {
                    reportWarning("SchemeUnsupported", new Object[]{str2});
                }
            }
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xinclude.XIncludeHandler, com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void processingInstruction(String str, XMLString xMLString, Augmentations augmentations) throws XNIException {
        if (isChildFragmentResolved()) {
            super.processingInstruction(str, xMLString, augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xpointer.XPointerProcessor
    public boolean resolveXPointer(QName qName, XMLAttributes xMLAttributes, Augmentations augmentations, int i) throws XNIException {
        boolean z = true;
        if (!this.fFoundMatchingPtrPart) {
            boolean z2 = false;
            for (int i2 = 0; i2 < this.fXPointerParts.size(); i2++) {
                XPointerPart xPointerPart = this.fXPointerParts.get(i2);
                this.fXPointerPart = xPointerPart;
                if (xPointerPart.resolveXPointer(qName, xMLAttributes, augmentations, i)) {
                    this.fFoundMatchingPtrPart = true;
                    z2 = true;
                }
            }
            z = z2;
        } else if (!this.fXPointerPart.resolveXPointer(qName, xMLAttributes, augmentations, i)) {
            z = false;
        }
        if (!this.fIsXPointerResolved) {
            this.fIsXPointerResolved = z;
        }
        return z;
    }

    @Override // com.sun.org.apache.xerces.internal.xinclude.XIncludeHandler, com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentSource
    public void setDocumentHandler(XMLDocumentHandler xMLDocumentHandler) {
        this.fDocumentHandler = xMLDocumentHandler;
    }

    @Override // com.sun.org.apache.xerces.internal.xinclude.XIncludeHandler, com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public void setProperty(String str, Object obj) throws XMLConfigurationException {
        if (str == "http://apache.org/xml/properties/internal/error-reporter") {
            if (obj != null) {
                this.fXPointerErrorReporter = (XMLErrorReporter) obj;
            } else {
                this.fXPointerErrorReporter = null;
            }
        }
        if (str == XMLSchemaLoader.ERROR_HANDLER) {
            if (obj != null) {
                this.fErrorHandler = (XMLErrorHandler) obj;
            } else {
                this.fErrorHandler = null;
            }
        }
        if (str == "http://apache.org/xml/features/xinclude/fixup-language") {
            if (obj != null) {
                this.fFixupLang = ((Boolean) obj).booleanValue();
            } else {
                this.fFixupLang = false;
            }
        }
        if (str == "http://apache.org/xml/features/xinclude/fixup-base-uris") {
            if (obj != null) {
                this.fFixupBase = ((Boolean) obj).booleanValue();
            } else {
                this.fFixupBase = false;
            }
        }
        if (str == "http://apache.org/xml/properties/internal/namespace-context") {
            this.fNamespaceContext = (XIncludeNamespaceSupport) obj;
        }
        super.setProperty(str, obj);
    }

    @Override // com.sun.org.apache.xerces.internal.xinclude.XIncludeHandler, com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void startCDATA(Augmentations augmentations) throws XNIException {
        if (isChildFragmentResolved()) {
            super.startCDATA(augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xinclude.XIncludeHandler, com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void startElement(QName qName, XMLAttributes xMLAttributes, Augmentations augmentations) throws XNIException {
        if (resolveXPointer(qName, xMLAttributes, augmentations, 0)) {
            super.startElement(qName, xMLAttributes, augmentations);
            return;
        }
        if (this.fFixupBase) {
            processXMLBaseAttributes(xMLAttributes);
        }
        if (this.fFixupLang) {
            processXMLLangAttributes(xMLAttributes);
        }
        this.fNamespaceContext.setContextInvalid();
    }

    public XPointerHandler(SymbolTable symbolTable, XMLErrorHandler xMLErrorHandler, XMLErrorReporter xMLErrorReporter) {
        this.fXPointerParts = null;
        this.fSymbolTable = null;
        this.fXPointerParts = new ArrayList<>();
        this.fSymbolTable = symbolTable;
        this.fErrorHandler = xMLErrorHandler;
        this.fXPointerErrorReporter = xMLErrorReporter;
    }

    public final class Tokens {
        private static final int INITIAL_TOKEN_COUNT = 256;
        private static final int XPTRTOKEN_CLOSE_PAREN = 1;
        private static final int XPTRTOKEN_OPEN_PAREN = 0;
        private static final int XPTRTOKEN_SCHEMEDATA = 4;
        private static final int XPTRTOKEN_SCHEMENAME = 3;
        private static final int XPTRTOKEN_SHORTHAND = 2;
        private int fCurrentTokenIndex;
        private SymbolTable fSymbolTable;
        private int fTokenCount;
        private HashMap<Integer, String> fTokenNames;
        private int[] fTokens;
        private final String[] fgTokenNames;

        private Tokens(SymbolTable symbolTable) {
            this.fgTokenNames = new String[]{"XPTRTOKEN_OPEN_PAREN", "XPTRTOKEN_CLOSE_PAREN", "XPTRTOKEN_SHORTHAND", "XPTRTOKEN_SCHEMENAME", "XPTRTOKEN_SCHEMEDATA"};
            this.fTokens = new int[256];
            this.fTokenCount = 0;
            HashMap<Integer, String> map = new HashMap<>();
            this.fTokenNames = map;
            this.fSymbolTable = symbolTable;
            map.put(0, "XPTRTOKEN_OPEN_PAREN");
            this.fTokenNames.put(1, "XPTRTOKEN_CLOSE_PAREN");
            this.fTokenNames.put(2, "XPTRTOKEN_SHORTHAND");
            this.fTokenNames.put(3, "XPTRTOKEN_SCHEMENAME");
            this.fTokenNames.put(4, "XPTRTOKEN_SCHEMEDATA");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addToken(String str) {
            String str2 = this.fTokenNames.get(str);
            Integer numValueOf = str2 == null ? null : Integer.valueOf(Integer.parseInt(str2));
            if (numValueOf == null) {
                numValueOf = Integer.valueOf(this.fTokenNames.size());
                this.fTokenNames.put(numValueOf, str);
            }
            addToken(numValueOf.intValue());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String getTokenString(int i) {
            return this.fTokenNames.get(Integer.valueOf(i));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean hasMore() {
            return this.fCurrentTokenIndex < this.fTokenCount;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int nextToken() throws XNIException {
            if (this.fCurrentTokenIndex == this.fTokenCount) {
                XPointerHandler.this.reportError("XPointerProcessingError", null);
            }
            int[] iArr = this.fTokens;
            int i = this.fCurrentTokenIndex;
            this.fCurrentTokenIndex = i + 1;
            return iArr[i];
        }

        private String nextTokenAsString() throws XNIException {
            String tokenString = getTokenString(nextToken());
            if (tokenString == null) {
                XPointerHandler.this.reportError("XPointerProcessingError", null);
            }
            return tokenString;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int peekToken() throws XNIException {
            if (this.fCurrentTokenIndex == this.fTokenCount) {
                XPointerHandler.this.reportError("XPointerProcessingError", null);
            }
            return this.fTokens[this.fCurrentTokenIndex];
        }

        private void rewind() {
            this.fCurrentTokenIndex = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addToken(int i) {
            try {
                this.fTokens[this.fTokenCount] = i;
            } catch (ArrayIndexOutOfBoundsException unused) {
                int[] iArr = this.fTokens;
                int i2 = this.fTokenCount;
                int[] iArr2 = new int[i2 << 1];
                this.fTokens = iArr2;
                System.arraycopy(iArr, 0, iArr2, 0, i2);
                this.fTokens[this.fTokenCount] = i;
            }
            this.fTokenCount++;
        }
    }
}
