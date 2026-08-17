package com.sun.org.apache.xerces.internal.xpointer;

import com.sun.org.apache.xerces.internal.impl.XMLErrorReporter;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.util.XMLChar;
import com.sun.org.apache.xerces.internal.xni.Augmentations;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xni.XMLAttributes;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLErrorHandler;
import java.util.HashMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class ElementSchemePointer implements XPointerPart {
    private int[] fChildSequence;
    private int[] fCurrentChildSequence;
    protected XMLErrorHandler fErrorHandler;
    protected XMLErrorReporter fErrorReporter;
    private String fSchemeData;
    private String fSchemeName;
    private ShortHandPointer fShortHandPointer;
    private String fShortHandPointerName;
    private SymbolTable fSymbolTable;
    private boolean fIsResolveElement = false;
    private boolean fIsElementFound = false;
    private boolean fWasOnlyEmptyElementFound = false;
    boolean fIsShortHand = false;
    int fFoundDepth = 0;
    private int fCurrentChildPosition = 1;
    private int fCurrentChildDepth = 0;
    private boolean fIsFragmentResolved = false;

    public class Scanner {
        private static final byte CHARTYPE_DIGIT = 5;
        private static final byte CHARTYPE_INVALID = 0;
        private static final byte CHARTYPE_LETTER = 6;
        private static final byte CHARTYPE_MINUS = 2;
        private static final byte CHARTYPE_NONASCII = 8;
        private static final byte CHARTYPE_OTHER = 1;
        private static final byte CHARTYPE_PERIOD = 3;
        private static final byte CHARTYPE_SLASH = 4;
        private static final byte CHARTYPE_UNDERSCORE = 7;
        private final byte[] fASCIICharMap;
        private SymbolTable fSymbolTable;

        private Scanner(SymbolTable symbolTable) {
            this.fASCIICharMap = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 1, 1, 1, 1, 1, 1, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 1, 1, 1, 1, 7, 1, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 1, 1, 1, 1, 1};
            this.fSymbolTable = symbolTable;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Code duplicated, block: B:27:0x004f  */
        /* JADX WARN: Code duplicated, block: B:39:0x003e A[SYNTHETIC] */
        public boolean scanExpr(SymbolTable symbolTable, Tokens tokens, String str, int i, int i2) throws XNIException {
            while (i != i2) {
                char cCharAt = str.charAt(i);
                switch (cCharAt >= 128 ? (byte) 8 : this.fASCIICharMap[cCharAt]) {
                    case 1:
                    case 2:
                    case 3:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                        int iScanNCName = scanNCName(str, i2, i);
                        if (iScanNCName == i) {
                            ElementSchemePointer.this.reportError("InvalidNCNameInElementSchemeData", new Object[]{str});
                            return false;
                        }
                        if (iScanNCName < i2) {
                            str.charAt(iScanNCName);
                        }
                        String strAddSymbol = symbolTable.addSymbol(str.substring(i, iScanNCName));
                        addToken(tokens, 0);
                        tokens.addToken(strAddSymbol);
                        i = iScanNCName;
                        break;
                    case 4:
                        i++;
                        if (i == i2) {
                            return false;
                        }
                        addToken(tokens, 1);
                        char cCharAt2 = str.charAt(i);
                        int i3 = 0;
                        while (cCharAt2 >= '0' && cCharAt2 <= '9') {
                            i3 = (i3 * 10) + (cCharAt2 - '0');
                            i++;
                            if (i != i2) {
                                cCharAt2 = str.charAt(i);
                            } else {
                                if (i3 == 0) {
                                    ElementSchemePointer.this.reportError("InvalidChildSequenceCharacter", new Object[]{Character.valueOf(cCharAt2)});
                                    return false;
                                }
                                tokens.addToken(i3);
                            }
                            break;
                        }
                        if (i3 == 0) {
                            ElementSchemePointer.this.reportError("InvalidChildSequenceCharacter", new Object[]{Character.valueOf(cCharAt2)});
                            return false;
                        }
                        tokens.addToken(i3);
                        break;
                }
            }
            return true;
        }

        private int scanNCName(String str, int i, int i2) {
            byte b;
            char cCharAt = str.charAt(i2);
            if (cCharAt < 128 ? !((b = this.fASCIICharMap[cCharAt]) == 6 || b == 7) : !XMLChar.isNameStart(cCharAt)) {
                return i2;
            }
            while (true) {
                i2++;
                if (i2 < i) {
                    char cCharAt2 = str.charAt(i2);
                    if (cCharAt2 < 128) {
                        byte b2 = this.fASCIICharMap[cCharAt2];
                        if (b2 != 6 && b2 != 5 && b2 != 3 && b2 != 2 && b2 != 7) {
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

    public ElementSchemePointer(SymbolTable symbolTable, XMLErrorReporter xMLErrorReporter) {
        this.fSymbolTable = symbolTable;
        this.fErrorReporter = xMLErrorReporter;
    }

    public boolean checkMatch() {
        int[] iArr;
        int i;
        boolean z = this.fIsShortHand;
        int[] iArr2 = this.fChildSequence;
        if (z) {
            if (iArr2.length > this.fCurrentChildDepth + 1) {
                return false;
            }
            int i2 = 0;
            do {
                int[] iArr3 = this.fChildSequence;
                if (i2 < iArr3.length) {
                    iArr = this.fCurrentChildSequence;
                    if (iArr.length < i2 + 2) {
                        return false;
                    }
                    i = iArr3[i2];
                    i2++;
                }
            } while (i == iArr[i2]);
            return false;
        }
        if (iArr2.length > this.fCurrentChildDepth + 1) {
            return false;
        }
        int i3 = 0;
        while (true) {
            int[] iArr4 = this.fChildSequence;
            if (i3 >= iArr4.length) {
                break;
            }
            if (iArr4[i3] != this.fCurrentChildSequence[i3]) {
                return false;
            }
            i3++;
        }
        return true;
    }

    @Override // com.sun.org.apache.xerces.internal.xpointer.XPointerPart
    public String getSchemeData() {
        return this.fSchemeData;
    }

    @Override // com.sun.org.apache.xerces.internal.xpointer.XPointerPart
    public String getSchemeName() {
        return this.fSchemeName;
    }

    public void init() {
        this.fSchemeName = null;
        this.fSchemeData = null;
        this.fShortHandPointerName = null;
        this.fIsResolveElement = false;
        this.fIsElementFound = false;
        this.fWasOnlyEmptyElementFound = false;
        this.fFoundDepth = 0;
        this.fCurrentChildPosition = 1;
        this.fCurrentChildDepth = 0;
        this.fIsFragmentResolved = false;
        this.fShortHandPointer = null;
        initErrorReporter();
    }

    public void initErrorReporter() {
        if (this.fErrorReporter == null) {
            this.fErrorReporter = new XMLErrorReporter();
        }
        if (this.fErrorHandler == null) {
            this.fErrorHandler = new XPointerErrorHandler();
        }
        this.fErrorReporter.putMessageFormatter(XPointerMessageFormatter.XPOINTER_DOMAIN, new XPointerMessageFormatter());
    }

    @Override // com.sun.org.apache.xerces.internal.xpointer.XPointerPart
    public boolean isChildFragmentResolved() {
        ShortHandPointer shortHandPointer;
        if (this.fIsShortHand && (shortHandPointer = this.fShortHandPointer) != null && this.fChildSequence.length <= 0) {
            return shortHandPointer.isChildFragmentResolved();
        }
        boolean z = this.fWasOnlyEmptyElementFound;
        if (z) {
            return !z;
        }
        return this.fIsFragmentResolved && this.fCurrentChildDepth >= this.fFoundDepth;
    }

    @Override // com.sun.org.apache.xerces.internal.xpointer.XPointerPart
    public boolean isFragmentResolved() throws XNIException {
        return this.fIsFragmentResolved;
    }

    public boolean matchChildSequence(QName qName, int i) throws XNIException {
        int i2 = this.fCurrentChildDepth;
        int[] iArr = this.fCurrentChildSequence;
        if (i2 >= iArr.length) {
            int length = iArr.length;
            int[] iArr2 = new int[length];
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
            int[] iArr3 = new int[this.fCurrentChildDepth * 2];
            this.fCurrentChildSequence = iArr3;
            System.arraycopy(iArr2, 0, iArr3, 0, length);
        }
        if (this.fIsResolveElement) {
            this.fWasOnlyEmptyElementFound = false;
            if (i == 0) {
                int[] iArr4 = this.fCurrentChildSequence;
                int i3 = this.fCurrentChildDepth;
                iArr4[i3] = this.fCurrentChildPosition;
                int i4 = i3 + 1;
                this.fCurrentChildDepth = i4;
                this.fCurrentChildPosition = 1;
                int i5 = this.fFoundDepth;
                if (i4 <= i5 || i5 == 0) {
                    if (checkMatch()) {
                        this.fIsElementFound = true;
                        this.fFoundDepth = this.fCurrentChildDepth;
                    } else {
                        this.fIsElementFound = false;
                        this.fFoundDepth = 0;
                    }
                }
            } else if (i == 1) {
                int i6 = this.fCurrentChildDepth;
                int i7 = this.fFoundDepth;
                if (i6 == i7) {
                    this.fIsElementFound = true;
                } else if ((i6 < i7 && i7 != 0) || (i6 > i7 && i7 == 0)) {
                    this.fIsElementFound = false;
                }
                int[] iArr5 = this.fCurrentChildSequence;
                iArr5[i6] = 0;
                int i8 = i6 - 1;
                this.fCurrentChildDepth = i8;
                this.fCurrentChildPosition = iArr5[i8] + 1;
            } else if (i == 2) {
                int[] iArr6 = this.fCurrentChildSequence;
                int i9 = this.fCurrentChildDepth;
                int i10 = this.fCurrentChildPosition;
                iArr6[i9] = i10;
                this.fCurrentChildPosition = i10 + 1;
                if (checkMatch()) {
                    if (this.fIsElementFound) {
                        this.fWasOnlyEmptyElementFound = false;
                    } else {
                        this.fWasOnlyEmptyElementFound = true;
                    }
                    this.fIsElementFound = true;
                } else {
                    this.fIsElementFound = false;
                    this.fWasOnlyEmptyElementFound = false;
                }
            }
        }
        return this.fIsElementFound;
    }

    @Override // com.sun.org.apache.xerces.internal.xpointer.XPointerPart
    public void parseXPointer(String str) throws XNIException {
        init();
        Tokens tokens = new Tokens(this.fSymbolTable);
        if (!new Scanner(this.fSymbolTable) { // from class: com.sun.org.apache.xerces.internal.xpointer.ElementSchemePointer.1
            @Override // com.sun.org.apache.xerces.internal.xpointer.ElementSchemePointer.Scanner
            public void addToken(Tokens tokens2, int i) throws XNIException {
                if (i == 1 || i == 0) {
                    super.addToken(tokens2, i);
                } else {
                    ElementSchemePointer.this.reportError("InvalidElementSchemeToken", new Object[]{tokens2.getTokenString(i)});
                }
            }
        }.scanExpr(this.fSymbolTable, tokens, str, 0, str.length())) {
            reportError("InvalidElementSchemeXPointer", new Object[]{str});
        }
        int[] iArr = new int[(tokens.getTokenCount() / 2) + 1];
        int i = 0;
        while (tokens.hasMore()) {
            int iNextToken = tokens.nextToken();
            if (iNextToken == 0) {
                this.fShortHandPointerName = tokens.getTokenString(tokens.nextToken());
                ShortHandPointer shortHandPointer = new ShortHandPointer(this.fSymbolTable);
                this.fShortHandPointer = shortHandPointer;
                shortHandPointer.setSchemeName(this.fShortHandPointerName);
            } else if (iNextToken != 1) {
                reportError("InvalidElementSchemeXPointer", new Object[]{str});
            } else {
                iArr[i] = tokens.nextToken();
                i++;
            }
        }
        int[] iArr2 = new int[i];
        this.fChildSequence = iArr2;
        this.fCurrentChildSequence = new int[i];
        System.arraycopy(iArr, 0, iArr2, 0, i);
    }

    public void reportError(String str, Object[] objArr) throws XNIException {
        throw new XNIException(this.fErrorReporter.getMessageFormatter(XPointerMessageFormatter.XPOINTER_DOMAIN).formatMessage(this.fErrorReporter.getLocale(), str, objArr));
    }

    @Override // com.sun.org.apache.xerces.internal.xpointer.XPointerPart
    public boolean resolveXPointer(QName qName, XMLAttributes xMLAttributes, Augmentations augmentations, int i) throws XNIException {
        boolean zResolveXPointer;
        if (this.fShortHandPointerName != null) {
            zResolveXPointer = this.fShortHandPointer.resolveXPointer(qName, xMLAttributes, augmentations, i);
            if (zResolveXPointer) {
                this.fIsResolveElement = true;
                this.fIsShortHand = true;
            } else {
                this.fIsResolveElement = false;
            }
        } else {
            this.fIsResolveElement = true;
            zResolveXPointer = false;
        }
        int[] iArr = this.fChildSequence;
        if (iArr.length > 0) {
            this.fIsFragmentResolved = matchChildSequence(qName, i);
        } else if (!zResolveXPointer || iArr.length > 0) {
            this.fIsFragmentResolved = false;
        } else {
            this.fIsFragmentResolved = zResolveXPointer;
        }
        return this.fIsFragmentResolved;
    }

    @Override // com.sun.org.apache.xerces.internal.xpointer.XPointerPart
    public void setSchemeData(String str) {
        this.fSchemeData = str;
    }

    @Override // com.sun.org.apache.xerces.internal.xpointer.XPointerPart
    public void setSchemeName(String str) {
        this.fSchemeName = str;
    }

    public ElementSchemePointer(SymbolTable symbolTable) {
        this.fSymbolTable = symbolTable;
    }

    public ElementSchemePointer() {
    }

    public final class Tokens {
        private static final int INITIAL_TOKEN_COUNT = 256;
        private static final int XPTRTOKEN_ELEM_CHILD = 1;
        private static final int XPTRTOKEN_ELEM_NCNAME = 0;
        private int fCurrentTokenIndex;
        private SymbolTable fSymbolTable;
        private int fTokenCount;
        private HashMap<Integer, String> fTokenNames;
        private int[] fTokens;
        private final String[] fgTokenNames;

        private Tokens(SymbolTable symbolTable) {
            this.fgTokenNames = new String[]{"XPTRTOKEN_ELEM_NCNAME", "XPTRTOKEN_ELEM_CHILD"};
            this.fTokens = new int[256];
            this.fTokenCount = 0;
            HashMap<Integer, String> map = new HashMap<>();
            this.fTokenNames = map;
            this.fSymbolTable = symbolTable;
            map.put(0, "XPTRTOKEN_ELEM_NCNAME");
            this.fTokenNames.put(1, "XPTRTOKEN_ELEM_CHILD");
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
        public int getTokenCount() {
            return this.fTokenCount;
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
                ElementSchemePointer.this.reportError("XPointerElementSchemeProcessingError", null);
            }
            int[] iArr = this.fTokens;
            int i = this.fCurrentTokenIndex;
            this.fCurrentTokenIndex = i + 1;
            return iArr[i];
        }

        private String nextTokenAsString() throws XNIException {
            String tokenString = getTokenString(nextToken());
            if (tokenString == null) {
                ElementSchemePointer.this.reportError("XPointerElementSchemeProcessingError", null);
            }
            return tokenString;
        }

        private int peekToken() throws XNIException {
            if (this.fCurrentTokenIndex == this.fTokenCount) {
                ElementSchemePointer.this.reportError("XPointerElementSchemeProcessingError", null);
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
