package com.sun.org.apache.xerces.internal.impl.xpath;

import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xerces.internal.impl.xpath.XPath;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.util.XMLChar;
import com.sun.org.apache.xerces.internal.util.XMLSymbols;
import com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import com.sun.org.apache.xerces.internal.xni.QName;
import defpackage.uj0;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XPath {
    private static final boolean DEBUG_ALL = false;
    private static final boolean DEBUG_XPATH_PARSE = false;
    protected final String fExpression;
    protected final LocationPath[] fLocationPaths;
    protected final SymbolTable fSymbolTable;

    public static class Scanner {
        private static final byte CHARTYPE_ATSIGN = 19;
        private static final byte CHARTYPE_CLOSE_BRACKET = 22;
        private static final byte CHARTYPE_CLOSE_PAREN = 7;
        private static final byte CHARTYPE_COLON = 15;
        private static final byte CHARTYPE_COMMA = 10;
        private static final byte CHARTYPE_DIGIT = 14;
        private static final byte CHARTYPE_DOLLAR = 5;
        private static final byte CHARTYPE_EQUAL = 17;
        private static final byte CHARTYPE_EXCLAMATION = 3;
        private static final byte CHARTYPE_GREATER = 18;
        private static final byte CHARTYPE_INVALID = 0;
        private static final byte CHARTYPE_LESS = 16;
        private static final byte CHARTYPE_LETTER = 20;
        private static final byte CHARTYPE_MINUS = 11;
        private static final byte CHARTYPE_NONASCII = 25;
        private static final byte CHARTYPE_OPEN_BRACKET = 21;
        private static final byte CHARTYPE_OPEN_PAREN = 6;
        private static final byte CHARTYPE_OTHER = 1;
        private static final byte CHARTYPE_PERIOD = 12;
        private static final byte CHARTYPE_PLUS = 9;
        private static final byte CHARTYPE_QUOTE = 4;
        private static final byte CHARTYPE_SLASH = 13;
        private static final byte CHARTYPE_STAR = 8;
        private static final byte CHARTYPE_UNDERSCORE = 23;
        private static final byte CHARTYPE_UNION = 24;
        private static final byte CHARTYPE_WHITESPACE = 2;
        private SymbolTable fSymbolTable;
        private static final byte[] fASCIICharMap = {0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 3, 4, 1, 5, 1, 1, 4, 6, 7, 8, 9, 10, 11, 12, 13, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 15, 1, 16, 17, 18, 1, 19, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 21, 1, 22, 1, 23, 1, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 1, 24, 1, 1, 1};
        private static final String fAndSymbol = "and".intern();
        private static final String fOrSymbol = "or".intern();
        private static final String fModSymbol = "mod".intern();
        private static final String fDivSymbol = "div".intern();
        private static final String fCommentSymbol = Constants.ELEMNAME_COMMENT_STRING.intern();
        private static final String fTextSymbol = "text".intern();
        private static final String fPISymbol = Constants.ELEMNAME_PI_STRING.intern();
        private static final String fNodeSymbol = "node".intern();
        private static final String fAncestorSymbol = "ancestor".intern();
        private static final String fAncestorOrSelfSymbol = "ancestor-or-self".intern();
        private static final String fAttributeSymbol = "attribute".intern();
        private static final String fChildSymbol = "child".intern();
        private static final String fDescendantSymbol = "descendant".intern();
        private static final String fDescendantOrSelfSymbol = "descendant-or-self".intern();
        private static final String fFollowingSymbol = "following".intern();
        private static final String fFollowingSiblingSymbol = "following-sibling".intern();
        private static final String fNamespaceSymbol = Constants.ATTRNAME_NAMESPACE.intern();
        private static final String fParentSymbol = "parent".intern();
        private static final String fPrecedingSymbol = "preceding".intern();
        private static final String fPrecedingSiblingSymbol = "preceding-sibling".intern();
        private static final String fSelfSymbol = "self".intern();

        public Scanner(SymbolTable symbolTable) {
            this.fSymbolTable = symbolTable;
        }

        private int scanNumber(Tokens tokens, String str, int i, int i2) {
            char cCharAt = str.charAt(i2);
            int i3 = 0;
            int i4 = 0;
            while (cCharAt >= '0' && cCharAt <= '9') {
                i4 = (i4 * 10) + (cCharAt - '0');
                i2++;
                if (i2 == i) {
                    break;
                }
                cCharAt = str.charAt(i2);
            }
            if (cCharAt == '.' && (i2 = i2 + 1) < i) {
                char cCharAt2 = str.charAt(i2);
                int i5 = 0;
                while (cCharAt2 >= '0' && cCharAt2 <= '9') {
                    i5 = (i5 * 10) + (cCharAt2 - '0');
                    i2++;
                    if (i2 == i) {
                        break;
                    }
                    cCharAt2 = str.charAt(i2);
                }
                if (i5 != 0) {
                    f63.a("find a solution!");
                    return 0;
                }
                i3 = i5;
            }
            tokens.addToken(i4);
            tokens.addToken(i3);
            return i2;
        }

        public void addToken(Tokens tokens, int i) throws XPathException {
            tokens.addToken(i);
        }

        /* JADX WARN: Code duplicated, block: B:119:0x0182  */
        /* JADX WARN: Code duplicated, block: B:120:0x0189  */
        /* JADX WARN: Code duplicated, block: B:122:0x018d  */
        /* JADX WARN: Code duplicated, block: B:123:0x0194  */
        /* JADX WARN: Code duplicated, block: B:125:0x0198  */
        /* JADX WARN: Code duplicated, block: B:126:0x019e  */
        /* JADX WARN: Code duplicated, block: B:128:0x01a2  */
        /* JADX WARN: Code duplicated, block: B:129:0x01a8  */
        /* JADX WARN: Code duplicated, block: B:131:0x01ac  */
        /* JADX WARN: Code duplicated, block: B:132:0x01b2  */
        /* JADX WARN: Code duplicated, block: B:134:0x01b6  */
        /* JADX WARN: Code duplicated, block: B:135:0x01bc  */
        /* JADX WARN: Code duplicated, block: B:137:0x01c0  */
        /* JADX WARN: Code duplicated, block: B:138:0x01c6  */
        /* JADX WARN: Code duplicated, block: B:140:0x01ca  */
        /* JADX WARN: Code duplicated, block: B:141:0x01ce  */
        /* JADX WARN: Code duplicated, block: B:143:0x01d2  */
        /* JADX WARN: Code duplicated, block: B:144:0x01d8  */
        /* JADX WARN: Code duplicated, block: B:146:0x01dc  */
        /* JADX WARN: Code duplicated, block: B:147:0x01e0  */
        /* JADX WARN: Code duplicated, block: B:149:0x01e4  */
        /* JADX WARN: Code duplicated, block: B:150:0x01ea  */
        /* JADX WARN: Code duplicated, block: B:152:0x01ee  */
        /* JADX WARN: Code duplicated, block: B:153:0x01f4  */
        /* JADX WARN: Code duplicated, block: B:155:0x01f8  */
        /* JADX WARN: Code duplicated, block: B:159:0x0202  */
        /* JADX WARN: Code duplicated, block: B:161:0x020b  */
        /* JADX WARN: Code duplicated, block: B:164:0x0212 A[LOOP:2: B:59:0x00d1->B:164:0x0212, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:303:0x0111 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:306:0x020f A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:307:0x01ff A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:327:0x00dd A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:63:0x00d9  */
        /* JADX WARN: Code duplicated, block: B:66:0x00df  */
        /* JADX WARN: Code duplicated, block: B:68:0x00e3  */
        /* JADX WARN: Code duplicated, block: B:69:0x00e9  */
        /* JADX WARN: Code duplicated, block: B:71:0x00ed  */
        /* JADX WARN: Code duplicated, block: B:72:0x00f3  */
        /* JADX WARN: Code duplicated, block: B:74:0x00f7  */
        /* JADX WARN: Code duplicated, block: B:75:0x00fd  */
        /* JADX WARN: Code duplicated, block: B:77:0x0101  */
        /* JADX WARN: Code duplicated, block: B:85:0x0112  */
        public boolean scanExpr(SymbolTable symbolTable, Tokens tokens, String str, int i, int i2) throws XPathException {
            boolean z;
            int iScanNCName;
            int iScanNCName2;
            String strAddSymbol;
            boolean z2;
            int iScanNumber;
            int i3;
            String str2;
            boolean z3;
            boolean z4;
            int i4;
            boolean z5 = false;
            int i5 = i;
            boolean z6 = false;
            while (i5 != i2) {
                char cCharAt = str.charAt(i5);
                while (true) {
                    if ((cCharAt == ' ' || cCharAt == '\n' || cCharAt == '\t' || cCharAt == '\r') && (i5 = i5 + 1) != i2) {
                        cCharAt = str.charAt(i5);
                        z5 = z5 ? 1 : 0;
                    }
                }
                if (i5 == i2) {
                    return true;
                }
                switch (cCharAt >= 128 ? (byte) 25 : fASCIICharMap[cCharAt]) {
                    case 3:
                        int i6 = i5 + 1;
                        if (i6 == i2) {
                            return false;
                        }
                        z = false;
                        if (str.charAt(i6) != '=') {
                            return false;
                        }
                        addToken(tokens, 27);
                        i5 += 2;
                        z6 = z ? 1 : 0;
                        z5 = z;
                        break;
                    case 4:
                        int i7 = i5 + 1;
                        if (i7 == i2) {
                            return false;
                        }
                        boolean z7 = false;
                        char cCharAt2 = str.charAt(i7);
                        int i8 = i7;
                        while (cCharAt2 != cCharAt) {
                            i8++;
                            if (i8 == i2) {
                                return z7;
                            }
                            cCharAt2 = str.charAt(i8);
                            z7 = false;
                        }
                        addToken(tokens, 46);
                        tokens.addToken(symbolTable.addSymbol(str.substring(i7, (i8 - i7) + i7)));
                        z6 = true;
                        i5 = i8 + 1;
                        z = false;
                        z5 = z;
                        break;
                    case 5:
                        boolean z8 = z5;
                        int i9 = i5 + 1;
                        if (i9 == i2 || (iScanNCName = scanNCName(str, i2, i9)) == i9) {
                            return z8;
                        }
                        char cCharAt3 = iScanNCName < i2 ? str.charAt(iScanNCName) : (char) 65535;
                        String strAddSymbol2 = symbolTable.addSymbol(str.substring(i9, iScanNCName));
                        if (cCharAt3 != ':') {
                            strAddSymbol = strAddSymbol2;
                            strAddSymbol2 = XMLSymbols.EMPTY_STRING;
                            iScanNCName2 = iScanNCName;
                        } else {
                            int i10 = iScanNCName + 1;
                            if (i10 == i2 || (iScanNCName2 = scanNCName(str, i2, i10)) == i10) {
                                return false;
                            }
                            if (iScanNCName2 < i2) {
                                str.charAt(iScanNCName2);
                            }
                            strAddSymbol = symbolTable.addSymbol(str.substring(i10, iScanNCName2));
                        }
                        addToken(tokens, 48);
                        tokens.addToken(strAddSymbol2);
                        tokens.addToken(strAddSymbol);
                        i5 = iScanNCName2;
                        z = false;
                        z6 = true;
                        z5 = z;
                        break;
                    case 6:
                        z6 = z5;
                        addToken(tokens, z6 ? 1 : 0);
                        i5++;
                        z = z6 ? 1 : 0;
                        z5 = z;
                        break;
                    case 7:
                        addToken(tokens, 1);
                        i5++;
                        z6 = true;
                        z = false;
                        z5 = z;
                        break;
                    case 8:
                        if (z6) {
                            addToken(tokens, 20);
                            z2 = false;
                        } else {
                            addToken(tokens, 9);
                            z2 = true;
                        }
                        i5++;
                        z6 = z2;
                        z = false;
                        z5 = z;
                        break;
                    case 9:
                        addToken(tokens, 24);
                        i5++;
                        z6 = false;
                        z = false;
                        z5 = z;
                        break;
                    case 10:
                        addToken(tokens, 7);
                        i5++;
                        z6 = false;
                        z = false;
                        z5 = z;
                        break;
                    case 11:
                        addToken(tokens, 25);
                        i5++;
                        z6 = false;
                        z = false;
                        z5 = z;
                        break;
                    case 12:
                        iScanNumber = i5 + 1;
                        if (iScanNumber == i2) {
                            addToken(tokens, 4);
                        } else {
                            char cCharAt4 = str.charAt(iScanNumber);
                            if (cCharAt4 == '.') {
                                addToken(tokens, 5);
                                iScanNumber = i5 + 2;
                            } else if (cCharAt4 < '0' || cCharAt4 > '9') {
                                if (cCharAt4 == '/' || cCharAt4 == '|') {
                                    addToken(tokens, 4);
                                } else {
                                    if (cCharAt4 != ' ' && cCharAt4 != '\n' && cCharAt4 != '\t' && cCharAt4 != '\r') {
                                        throw new XPathException("c-general-xpath");
                                    }
                                    while (true) {
                                        i5++;
                                        if (i5 != i2 && ((cCharAt4 = str.charAt(i5)) == ' ' || cCharAt4 == '\n' || cCharAt4 == '\t' || cCharAt4 == '\r')) {
                                        }
                                    }
                                    if (i5 != i2 && cCharAt4 != '|' && cCharAt4 != '/') {
                                        throw new XPathException("c-general-xpath");
                                    }
                                    addToken(tokens, 4);
                                    z6 = true;
                                }
                                z = false;
                                z5 = z;
                            } else {
                                addToken(tokens, 47);
                                iScanNumber = scanNumber(tokens, str, i2, i5);
                            }
                        }
                        z6 = true;
                        i5 = iScanNumber;
                        z = false;
                        z5 = z;
                        break;
                    case 13:
                        int i11 = i5 + 1;
                        if (i11 == i2 || str.charAt(i11) != '/') {
                            addToken(tokens, 21);
                            i5 = i11;
                            z6 = false;
                            z = false;
                            z5 = z;
                        } else {
                            addToken(tokens, 22);
                            i5 += 2;
                            z6 = false;
                            z = false;
                            z5 = z;
                        }
                        break;
                    case 14:
                        addToken(tokens, 47);
                        iScanNumber = scanNumber(tokens, str, i2, i5);
                        z6 = true;
                        i5 = iScanNumber;
                        z = false;
                        z5 = z;
                        break;
                    case 15:
                        int i12 = i5 + 1;
                        if (i12 == i2 || str.charAt(i12) != ':') {
                            return false;
                        }
                        addToken(tokens, 8);
                        i5 += 2;
                        z6 = false;
                        z = false;
                        z5 = z;
                        break;
                        break;
                    case 16:
                        i3 = i5 + 1;
                        if (i3 == i2 || str.charAt(i3) != '=') {
                            addToken(tokens, 28);
                            i5 = i3;
                            z6 = false;
                            z = false;
                            z5 = z;
                        } else {
                            addToken(tokens, 29);
                            i5 += 2;
                            z6 = false;
                            z = false;
                            z5 = z;
                        }
                        break;
                    case 17:
                        addToken(tokens, 26);
                        i5++;
                        z6 = false;
                        z = false;
                        z5 = z;
                        break;
                    case 18:
                        i3 = i5 + 1;
                        if (i3 == i2 || str.charAt(i3) != '=') {
                            addToken(tokens, 30);
                            i5 = i3;
                            z6 = false;
                            z = false;
                            z5 = z;
                        } else {
                            addToken(tokens, 31);
                            i5 += 2;
                            z6 = false;
                            z = false;
                            z5 = z;
                        }
                        break;
                    case 19:
                        addToken(tokens, 6);
                        i5++;
                        z6 = false;
                        z = false;
                        z5 = z;
                        break;
                    case 20:
                    case 23:
                    case 25:
                        int iScanNCName3 = scanNCName(str, i2, i5);
                        if (iScanNCName3 == i5) {
                            return z5;
                        }
                        char cCharAt5 = iScanNCName3 < i2 ? str.charAt(iScanNCName3) : (char) 65535;
                        String strAddSymbol3 = symbolTable.addSymbol(str.substring(i5, iScanNCName3));
                        String str3 = XMLSymbols.EMPTY_STRING;
                        if (cCharAt5 == ':') {
                            int i13 = iScanNCName3 + 1;
                            if (i13 == i2) {
                                return z5;
                            }
                            z = z5;
                            char cCharAt6 = str.charAt(i13);
                            if (cCharAt6 == '*') {
                                iScanNCName3 += 2;
                                if (iScanNCName3 < i2) {
                                    cCharAt6 = str.charAt(iScanNCName3);
                                }
                                cCharAt5 = cCharAt6;
                                str2 = str3;
                                z3 = z ? 1 : 0;
                                z4 = true;
                            } else if (cCharAt6 == ':') {
                                iScanNCName3 += 2;
                                if (iScanNCName3 < i2) {
                                    cCharAt6 = str.charAt(iScanNCName3);
                                }
                                cCharAt5 = cCharAt6;
                                z3 = true;
                                str2 = str3;
                                z4 = z ? 1 : 0;
                            } else {
                                iScanNCName3 = scanNCName(str, i2, i13);
                                if (iScanNCName3 == i13) {
                                    return z;
                                }
                                char cCharAt7 = iScanNCName3 < i2 ? str.charAt(iScanNCName3) : (char) 65535;
                                strAddSymbol3 = symbolTable.addSymbol(str.substring(i13, iScanNCName3));
                                str2 = strAddSymbol3;
                                cCharAt5 = cCharAt7;
                            }
                            while (true) {
                                if (cCharAt5 != ' ' || cCharAt5 == '\n' || cCharAt5 == '\t' || cCharAt5 == '\r') {
                                    iScanNCName3++;
                                    if (iScanNCName3 != i2) {
                                        cCharAt5 = str.charAt(iScanNCName3);
                                        z = false;
                                    }
                                }
                            }
                            if (!z6) {
                                if (cCharAt5 != '(' && !z4 && !z3) {
                                    if (strAddSymbol3 == fCommentSymbol) {
                                        addToken(tokens, 12);
                                    } else if (strAddSymbol3 == fTextSymbol) {
                                        addToken(tokens, 13);
                                    } else if (strAddSymbol3 == fPISymbol) {
                                        addToken(tokens, 14);
                                    } else if (strAddSymbol3 == fNodeSymbol) {
                                        addToken(tokens, 15);
                                    } else {
                                        addToken(tokens, 32);
                                        tokens.addToken(str2);
                                        tokens.addToken(strAddSymbol3);
                                    }
                                    addToken(tokens, z ? 1 : 0 ? 1 : 0);
                                    i5 = iScanNCName3 + 1;
                                    z6 = false;
                                } else if (!z3 || (cCharAt5 == ':' && (i4 = iScanNCName3 + 1) < i2 && str.charAt(i4) == ':')) {
                                    if (strAddSymbol3 == fAncestorSymbol) {
                                        addToken(tokens, 33);
                                    } else if (strAddSymbol3 == fAncestorOrSelfSymbol) {
                                        addToken(tokens, 34);
                                    } else if (strAddSymbol3 == fAttributeSymbol) {
                                        addToken(tokens, 35);
                                    } else if (strAddSymbol3 == fChildSymbol) {
                                        addToken(tokens, 36);
                                    } else if (strAddSymbol3 == fDescendantSymbol) {
                                        addToken(tokens, 37);
                                    } else if (strAddSymbol3 == fDescendantOrSelfSymbol) {
                                        addToken(tokens, 38);
                                    } else if (strAddSymbol3 == fFollowingSymbol) {
                                        addToken(tokens, 39);
                                    } else if (strAddSymbol3 == fFollowingSiblingSymbol) {
                                        addToken(tokens, 40);
                                    } else if (strAddSymbol3 == fNamespaceSymbol) {
                                        addToken(tokens, 41);
                                    } else if (strAddSymbol3 == fParentSymbol) {
                                        addToken(tokens, 42);
                                    } else if (strAddSymbol3 == fPrecedingSymbol) {
                                        addToken(tokens, 43);
                                    } else if (strAddSymbol3 == fPrecedingSiblingSymbol) {
                                        addToken(tokens, 44);
                                    } else {
                                        if (strAddSymbol3 == fSelfSymbol) {
                                            return false;
                                        }
                                        addToken(tokens, 45);
                                    }
                                    if (z4) {
                                        return false;
                                    }
                                    z = false;
                                    z = false;
                                    addToken(tokens, 8);
                                    if (!z3) {
                                        iScanNCName3 += 2;
                                    }
                                } else {
                                    if (z4) {
                                        addToken(tokens, 10);
                                        tokens.addToken(strAddSymbol3);
                                    } else {
                                        addToken(tokens, 11);
                                        tokens.addToken(str2);
                                        tokens.addToken(strAddSymbol3);
                                    }
                                    z6 = true;
                                    i5 = iScanNCName3;
                                }
                                z = false;
                                z5 = z;
                                break;
                            } else {
                                if (strAddSymbol3 == fAndSymbol) {
                                    addToken(tokens, 16);
                                } else if (strAddSymbol3 == fOrSymbol) {
                                    addToken(tokens, 17);
                                } else if (strAddSymbol3 == fModSymbol) {
                                    addToken(tokens, 18);
                                } else {
                                    if (strAddSymbol3 == fDivSymbol) {
                                        return z;
                                    }
                                    addToken(tokens, 19);
                                }
                                if (!z4 || z3) {
                                }
                            }
                            i5 = iScanNCName3;
                            z6 = z ? 1 : 0;
                            z5 = z;
                        } else {
                            z = z5;
                            str2 = str3;
                        }
                        boolean z9 = z ? 1 : 0;
                        z3 = z9 ? 1 : 0;
                        z4 = z9;
                        while (true) {
                            if (cCharAt5 != ' ') {
                                iScanNCName3++;
                                if (iScanNCName3 != i2) {
                                    cCharAt5 = str.charAt(iScanNCName3);
                                    z = false;
                                }
                            } else {
                                iScanNCName3++;
                                if (iScanNCName3 != i2) {
                                    cCharAt5 = str.charAt(iScanNCName3);
                                    z = false;
                                }
                            }
                        }
                        if (!z6) {
                            if (strAddSymbol3 == fAndSymbol) {
                                addToken(tokens, 16);
                            } else if (strAddSymbol3 == fOrSymbol) {
                                addToken(tokens, 17);
                            } else if (strAddSymbol3 == fModSymbol) {
                                addToken(tokens, 18);
                            } else {
                                if (strAddSymbol3 == fDivSymbol) {
                                    return z;
                                }
                                addToken(tokens, 19);
                            }
                            return !z4 ? z : z;
                        }
                        if (cCharAt5 != '(') {
                        }
                        if (z3) {
                        }
                        if (strAddSymbol3 == fAncestorSymbol) {
                            addToken(tokens, 33);
                        } else if (strAddSymbol3 == fAncestorOrSelfSymbol) {
                            addToken(tokens, 34);
                        } else if (strAddSymbol3 == fAttributeSymbol) {
                            addToken(tokens, 35);
                        } else if (strAddSymbol3 == fChildSymbol) {
                            addToken(tokens, 36);
                        } else if (strAddSymbol3 == fDescendantSymbol) {
                            addToken(tokens, 37);
                        } else if (strAddSymbol3 == fDescendantOrSelfSymbol) {
                            addToken(tokens, 38);
                        } else if (strAddSymbol3 == fFollowingSymbol) {
                            addToken(tokens, 39);
                        } else if (strAddSymbol3 == fFollowingSiblingSymbol) {
                            addToken(tokens, 40);
                        } else if (strAddSymbol3 == fNamespaceSymbol) {
                            addToken(tokens, 41);
                        } else if (strAddSymbol3 == fParentSymbol) {
                            addToken(tokens, 42);
                        } else if (strAddSymbol3 == fPrecedingSymbol) {
                            addToken(tokens, 43);
                        } else if (strAddSymbol3 == fPrecedingSiblingSymbol) {
                            addToken(tokens, 44);
                        } else {
                            if (strAddSymbol3 == fSelfSymbol) {
                                return false;
                            }
                            addToken(tokens, 45);
                        }
                        if (z4) {
                            return false;
                        }
                        z = false;
                        z = false;
                        addToken(tokens, 8);
                        if (!z3) {
                            iScanNCName3 += 2;
                        }
                        i5 = iScanNCName3;
                        z6 = z ? 1 : 0;
                        z5 = z;
                        break;
                    case 21:
                        addToken(tokens, 2);
                        i5++;
                        z6 = z5;
                        z = z6 ? 1 : 0;
                        z5 = z;
                        break;
                    case 22:
                        addToken(tokens, 3);
                        i5++;
                        z6 = true;
                        z = z5;
                        z5 = z;
                        break;
                    case 24:
                        addToken(tokens, 23);
                        i5++;
                        z6 = z5;
                        z = z6 ? 1 : 0;
                        z5 = z;
                        break;
                    default:
                        return z5;
                }
            }
            return true;
        }

        public int scanNCName(String str, int i, int i2) {
            byte b;
            char cCharAt = str.charAt(i2);
            if (cCharAt < 128 ? !((b = fASCIICharMap[cCharAt]) == 20 || b == 23) : !XMLChar.isNameStart(cCharAt)) {
                return i2;
            }
            while (true) {
                i2++;
                if (i2 < i) {
                    char cCharAt2 = str.charAt(i2);
                    if (cCharAt2 < 128) {
                        byte b2 = fASCIICharMap[cCharAt2];
                        if (b2 != 20 && b2 != 14 && b2 != 12 && b2 != 11 && b2 != 23) {
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
    }

    public XPath(String str, SymbolTable symbolTable, NamespaceContext namespaceContext) throws XPathException {
        this.fExpression = str;
        this.fSymbolTable = symbolTable;
        this.fLocationPaths = parseExpression(namespaceContext);
    }

    private LocationPath buildLocationPath(ArrayList<Step> arrayList) throws XPathException {
        int size = arrayList.size();
        check(size != 0);
        Step[] stepArr = (Step[]) arrayList.toArray(new Step[size]);
        arrayList.clear();
        return new LocationPath(stepArr);
    }

    private static void check(boolean z) throws XPathException {
        if (!z) {
            throw new XPathException("c-general-xpath");
        }
    }

    public static void main(String[] strArr) throws Exception {
        for (String str : strArr) {
            System.out.println("# XPath expression: \"" + str + '\"');
            try {
                System.out.println("expanded xpath: \"" + new XPath(str, new SymbolTable(), null).toString() + '\"');
            } catch (XPathException e) {
                System.out.println("error: " + e.getMessage());
            }
        }
    }

    private LocationPath[] parseExpression(NamespaceContext namespaceContext) throws XPathException {
        int iNextToken;
        Tokens tokens = new Tokens(this.fSymbolTable);
        if (!new Scanner(this.fSymbolTable) { // from class: com.sun.org.apache.xerces.internal.impl.xpath.XPath.1
            @Override // com.sun.org.apache.xerces.internal.impl.xpath.XPath.Scanner
            public void addToken(Tokens tokens2, int i) throws XPathException {
                if (i != 6 && i != 35 && i != 11 && i != 21 && i != 4 && i != 9 && i != 10 && i != 22 && i != 23 && i != 36 && i != 8) {
                    throw new XPathException("c-general-xpath");
                }
                super.addToken(tokens2, i);
            }
        }.scanExpr(this.fSymbolTable, tokens, this.fExpression, 0, this.fExpression.length())) {
            throw new XPathException("c-general-xpath");
        }
        ArrayList<Step> arrayList = new ArrayList<>();
        ArrayList arrayList2 = new ArrayList();
        while (true) {
            boolean z = true;
            while (true) {
                if (!tokens.hasMore()) {
                    check(!z);
                    arrayList2.add(buildLocationPath(arrayList));
                    return (LocationPath[]) arrayList2.toArray(new LocationPath[arrayList2.size()]);
                }
                iNextToken = tokens.nextToken();
                boolean z2 = false;
                if (iNextToken == 4) {
                    check(z);
                    if (arrayList.isEmpty()) {
                        arrayList.add(new Step(new Axis((short) 3), new NodeTest((short) 3)));
                        if (tokens.hasMore() && tokens.peekToken() == 22) {
                            tokens.nextToken();
                            arrayList.add(new Step(new Axis((short) 4), new NodeTest((short) 3)));
                            z2 = true;
                        }
                    }
                } else if (iNextToken == 6) {
                    check(z);
                    arrayList.add(new Step(new Axis((short) 2), parseNodeTest(tokens.nextToken(), tokens, namespaceContext)));
                } else if (iNextToken == 35) {
                    check(z);
                    if (tokens.nextToken() != 8) {
                        throw new XPathException("c-general-xpath");
                    }
                    arrayList.add(new Step(new Axis((short) 2), parseNodeTest(tokens.nextToken(), tokens, namespaceContext)));
                } else if (iNextToken != 36) {
                    switch (iNextToken) {
                        case 8:
                            throw new XPathException("c-general-xpath");
                        case 9:
                        case 10:
                        case 11:
                            check(z);
                            arrayList.add(new Step(new Axis((short) 1), parseNodeTest(iNextToken, tokens, namespaceContext)));
                            break;
                    }
                } else {
                    check(z);
                    if (tokens.nextToken() != 8) {
                        throw new XPathException("c-general-xpath");
                    }
                    arrayList.add(new Step(new Axis((short) 1), parseNodeTest(tokens.nextToken(), tokens, namespaceContext)));
                }
                z = z2;
            }
            switch (iNextToken) {
                case 21:
                    check(!z);
                    break;
                case 22:
                    throw new XPathException("c-general-xpath");
                case 23:
                    check(!z);
                    arrayList2.add(buildLocationPath(arrayList));
                    break;
                default:
                    uj0.a();
                    return null;
            }
        }
    }

    private NodeTest parseNodeTest(int i, Tokens tokens, NamespaceContext namespaceContext) throws XPathException {
        String strAddSymbol;
        switch (i) {
            case 9:
                return new NodeTest((short) 2);
            case 10:
            case 11:
                String strNextTokenAsString = tokens.nextTokenAsString();
                String uri = (namespaceContext == null || strNextTokenAsString == XMLSymbols.EMPTY_STRING) ? null : namespaceContext.getURI(strNextTokenAsString);
                String str = XMLSymbols.EMPTY_STRING;
                if (strNextTokenAsString != str && namespaceContext != null && uri == null) {
                    throw new XPathException("c-general-xpath-ns");
                }
                if (i == 10) {
                    return new NodeTest(strNextTokenAsString, uri);
                }
                String strNextTokenAsString2 = tokens.nextTokenAsString();
                if (strNextTokenAsString != str) {
                    strAddSymbol = this.fSymbolTable.addSymbol(strNextTokenAsString + ':' + strNextTokenAsString2);
                } else {
                    strAddSymbol = strNextTokenAsString2;
                }
                return new NodeTest(new QName(strNextTokenAsString, strNextTokenAsString2, strAddSymbol, uri));
            default:
                throw new XPathException("c-general-xpath");
        }
    }

    public LocationPath getLocationPath() {
        return (LocationPath) this.fLocationPaths[0].clone();
    }

    public LocationPath[] getLocationPaths() {
        LocationPath[] locationPathArr = new LocationPath[this.fLocationPaths.length];
        int i = 0;
        while (true) {
            LocationPath[] locationPathArr2 = this.fLocationPaths;
            if (i >= locationPathArr2.length) {
                return locationPathArr;
            }
            locationPathArr[i] = (LocationPath) locationPathArr2[i].clone();
            i++;
        }
    }

    public String toString() {
        return (String) Arrays.asList(this.fLocationPaths).stream().map(new Function() { // from class: dyf
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((XPath.LocationPath) obj).toString();
            }
        }).collect(Collectors.joining("|"));
    }

    public static class Axis implements Cloneable {
        public static final short ATTRIBUTE = 2;
        public static final short CHILD = 1;
        public static final short DESCENDANT = 4;
        public static final short SELF = 3;
        public final short type;

        public Axis(Axis axis) {
            this.type = axis.type;
        }

        public Object clone() {
            return new Axis(this);
        }

        public String toString() {
            short s = this.type;
            if (s == 1) {
                return "child";
            }
            if (s == 2) {
                return "attribute";
            }
            if (s != 3) {
                return s != 4 ? "???" : "descendant";
            }
            return "self";
        }

        public Axis(short s) {
            this.type = s;
        }
    }

    public static class NodeTest implements Cloneable {
        public static final short NAMESPACE = 4;
        public static final short NODE = 3;
        public static final short QNAME = 1;
        public static final short WILDCARD = 2;
        public final QName name;
        public final short type;

        public NodeTest(NodeTest nodeTest) {
            QName qName = new QName();
            this.name = qName;
            this.type = nodeTest.type;
            qName.setValues(nodeTest.name);
        }

        public Object clone() {
            return new NodeTest(this);
        }

        public String toString() {
            short s = this.type;
            if (s == 1) {
                int length = this.name.prefix.length();
                QName qName = this.name;
                if (length == 0) {
                    return qName.localpart;
                }
                String str = qName.uri;
                QName qName2 = this.name;
                if (str != null) {
                    return qName2.prefix + ':' + this.name.localpart;
                }
                return "{" + qName2.uri + '}' + this.name.prefix + ':' + this.name.localpart;
            }
            if (s == 2) {
                return "*";
            }
            if (s == 3) {
                return "node()";
            }
            if (s != 4) {
                return "???";
            }
            if (this.name.prefix.length() == 0) {
                return "???:*";
            }
            String str2 = this.name.uri;
            QName qName3 = this.name;
            if (str2 != null) {
                return qName3.prefix + ":*";
            }
            return "{" + qName3.uri + '}' + this.name.prefix + ":*";
        }

        public NodeTest(QName qName) {
            QName qName2 = new QName();
            this.name = qName2;
            this.type = (short) 1;
            qName2.setValues(qName);
        }

        public NodeTest(String str, String str2) {
            QName qName = new QName();
            this.name = qName;
            this.type = (short) 4;
            qName.setValues(str, null, null, str2);
        }

        public NodeTest(short s) {
            this.name = new QName();
            this.type = s;
        }
    }

    public static class Step implements Cloneable {
        public final Axis axis;
        public final NodeTest nodeTest;

        public Step(Step step) {
            this.axis = (Axis) step.axis.clone();
            this.nodeTest = (NodeTest) step.nodeTest.clone();
        }

        public Object clone() {
            return new Step(this);
        }

        public String toString() {
            short s = this.axis.type;
            if (s == 3) {
                return Constants.ATTRVAL_THIS;
            }
            if (s == 2) {
                return "@" + this.nodeTest.toString();
            }
            if (s == 1) {
                return this.nodeTest.toString();
            }
            if (s == 4) {
                return "//";
            }
            return "??? (" + ((int) this.axis.type) + ')';
        }

        public Step(Axis axis, NodeTest nodeTest) {
            this.axis = axis;
            this.nodeTest = nodeTest;
        }
    }

    public static class LocationPath implements Cloneable {
        public final Step[] steps;

        public LocationPath(LocationPath locationPath) {
            this.steps = new Step[locationPath.steps.length];
            int i = 0;
            while (true) {
                Step[] stepArr = this.steps;
                if (i >= stepArr.length) {
                    return;
                }
                stepArr[i] = (Step) locationPath.steps[i].clone();
                i++;
            }
        }

        public Object clone() {
            return new LocationPath(this);
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            int i = 0;
            while (true) {
                Step[] stepArr = this.steps;
                if (i >= stepArr.length) {
                    return stringBuffer.toString();
                }
                if (i > 0 && stepArr[i - 1].axis.type != 4 && stepArr[i].axis.type != 4) {
                    stringBuffer.append('/');
                }
                stringBuffer.append(this.steps[i].toString());
                i++;
            }
        }

        public LocationPath(Step[] stepArr) {
            this.steps = stepArr;
        }
    }

    public static final class Tokens {
        static final boolean DUMP_TOKENS = false;
        public static final int EXPRTOKEN_ATSIGN = 6;
        public static final int EXPRTOKEN_AXISNAME_ANCESTOR = 33;
        public static final int EXPRTOKEN_AXISNAME_ANCESTOR_OR_SELF = 34;
        public static final int EXPRTOKEN_AXISNAME_ATTRIBUTE = 35;
        public static final int EXPRTOKEN_AXISNAME_CHILD = 36;
        public static final int EXPRTOKEN_AXISNAME_DESCENDANT = 37;
        public static final int EXPRTOKEN_AXISNAME_DESCENDANT_OR_SELF = 38;
        public static final int EXPRTOKEN_AXISNAME_FOLLOWING = 39;
        public static final int EXPRTOKEN_AXISNAME_FOLLOWING_SIBLING = 40;
        public static final int EXPRTOKEN_AXISNAME_NAMESPACE = 41;
        public static final int EXPRTOKEN_AXISNAME_PARENT = 42;
        public static final int EXPRTOKEN_AXISNAME_PRECEDING = 43;
        public static final int EXPRTOKEN_AXISNAME_PRECEDING_SIBLING = 44;
        public static final int EXPRTOKEN_AXISNAME_SELF = 45;
        public static final int EXPRTOKEN_CLOSE_BRACKET = 3;
        public static final int EXPRTOKEN_CLOSE_PAREN = 1;
        public static final int EXPRTOKEN_COMMA = 7;
        public static final int EXPRTOKEN_DOUBLE_COLON = 8;
        public static final int EXPRTOKEN_DOUBLE_PERIOD = 5;
        public static final int EXPRTOKEN_FUNCTION_NAME = 32;
        public static final int EXPRTOKEN_LITERAL = 46;
        public static final int EXPRTOKEN_NAMETEST_ANY = 9;
        public static final int EXPRTOKEN_NAMETEST_NAMESPACE = 10;
        public static final int EXPRTOKEN_NAMETEST_QNAME = 11;
        public static final int EXPRTOKEN_NODETYPE_COMMENT = 12;
        public static final int EXPRTOKEN_NODETYPE_NODE = 15;
        public static final int EXPRTOKEN_NODETYPE_PI = 14;
        public static final int EXPRTOKEN_NODETYPE_TEXT = 13;
        public static final int EXPRTOKEN_NUMBER = 47;
        public static final int EXPRTOKEN_OPEN_BRACKET = 2;
        public static final int EXPRTOKEN_OPEN_PAREN = 0;
        public static final int EXPRTOKEN_OPERATOR_AND = 16;
        public static final int EXPRTOKEN_OPERATOR_DIV = 19;
        public static final int EXPRTOKEN_OPERATOR_DOUBLE_SLASH = 22;
        public static final int EXPRTOKEN_OPERATOR_EQUAL = 26;
        public static final int EXPRTOKEN_OPERATOR_GREATER = 30;
        public static final int EXPRTOKEN_OPERATOR_GREATER_EQUAL = 31;
        public static final int EXPRTOKEN_OPERATOR_LESS = 28;
        public static final int EXPRTOKEN_OPERATOR_LESS_EQUAL = 29;
        public static final int EXPRTOKEN_OPERATOR_MINUS = 25;
        public static final int EXPRTOKEN_OPERATOR_MOD = 18;
        public static final int EXPRTOKEN_OPERATOR_MULT = 20;
        public static final int EXPRTOKEN_OPERATOR_NOT_EQUAL = 27;
        public static final int EXPRTOKEN_OPERATOR_OR = 17;
        public static final int EXPRTOKEN_OPERATOR_PLUS = 24;
        public static final int EXPRTOKEN_OPERATOR_SLASH = 21;
        public static final int EXPRTOKEN_OPERATOR_UNION = 23;
        public static final int EXPRTOKEN_PERIOD = 4;
        public static final int EXPRTOKEN_VARIABLE_REFERENCE = 48;
        private static final int INITIAL_TOKEN_COUNT = 256;
        private static final String[] fgTokenNames = {"EXPRTOKEN_OPEN_PAREN", "EXPRTOKEN_CLOSE_PAREN", "EXPRTOKEN_OPEN_BRACKET", "EXPRTOKEN_CLOSE_BRACKET", "EXPRTOKEN_PERIOD", "EXPRTOKEN_DOUBLE_PERIOD", "EXPRTOKEN_ATSIGN", "EXPRTOKEN_COMMA", "EXPRTOKEN_DOUBLE_COLON", "EXPRTOKEN_NAMETEST_ANY", "EXPRTOKEN_NAMETEST_NAMESPACE", "EXPRTOKEN_NAMETEST_QNAME", "EXPRTOKEN_NODETYPE_COMMENT", "EXPRTOKEN_NODETYPE_TEXT", "EXPRTOKEN_NODETYPE_PI", "EXPRTOKEN_NODETYPE_NODE", "EXPRTOKEN_OPERATOR_AND", "EXPRTOKEN_OPERATOR_OR", "EXPRTOKEN_OPERATOR_MOD", "EXPRTOKEN_OPERATOR_DIV", "EXPRTOKEN_OPERATOR_MULT", "EXPRTOKEN_OPERATOR_SLASH", "EXPRTOKEN_OPERATOR_DOUBLE_SLASH", "EXPRTOKEN_OPERATOR_UNION", "EXPRTOKEN_OPERATOR_PLUS", "EXPRTOKEN_OPERATOR_MINUS", "EXPRTOKEN_OPERATOR_EQUAL", "EXPRTOKEN_OPERATOR_NOT_EQUAL", "EXPRTOKEN_OPERATOR_LESS", "EXPRTOKEN_OPERATOR_LESS_EQUAL", "EXPRTOKEN_OPERATOR_GREATER", "EXPRTOKEN_OPERATOR_GREATER_EQUAL", "EXPRTOKEN_FUNCTION_NAME", "EXPRTOKEN_AXISNAME_ANCESTOR", "EXPRTOKEN_AXISNAME_ANCESTOR_OR_SELF", "EXPRTOKEN_AXISNAME_ATTRIBUTE", "EXPRTOKEN_AXISNAME_CHILD", "EXPRTOKEN_AXISNAME_DESCENDANT", "EXPRTOKEN_AXISNAME_DESCENDANT_OR_SELF", "EXPRTOKEN_AXISNAME_FOLLOWING", "EXPRTOKEN_AXISNAME_FOLLOWING_SIBLING", "EXPRTOKEN_AXISNAME_NAMESPACE", "EXPRTOKEN_AXISNAME_PARENT", "EXPRTOKEN_AXISNAME_PRECEDING", "EXPRTOKEN_AXISNAME_PRECEDING_SIBLING", "EXPRTOKEN_AXISNAME_SELF", "EXPRTOKEN_LITERAL", "EXPRTOKEN_NUMBER", "EXPRTOKEN_VARIABLE_REFERENCE"};
        private int fCurrentTokenIndex;
        private final SymbolTable fSymbolTable;
        private int[] fTokens = new int[256];
        private int fTokenCount = 0;
        private final Map<String, Integer> fSymbolMapping = new HashMap();
        private final Map<Integer, String> fTokenNames = new HashMap();

        public Tokens(SymbolTable symbolTable) {
            this.fSymbolTable = symbolTable;
            String[] strArr = {"ancestor", "ancestor-or-self", "attribute", "child", "descendant", "descendant-or-self", "following", "following-sibling", Constants.ATTRNAME_NAMESPACE, "parent", "preceding", "preceding-sibling", "self"};
            for (int i = 0; i < 13; i++) {
                this.fSymbolMapping.put(this.fSymbolTable.addSymbol(strArr[i]), Integer.valueOf(i));
            }
            this.fTokenNames.put(0, "EXPRTOKEN_OPEN_PAREN");
            this.fTokenNames.put(1, "EXPRTOKEN_CLOSE_PAREN");
            this.fTokenNames.put(2, "EXPRTOKEN_OPEN_BRACKET");
            this.fTokenNames.put(3, "EXPRTOKEN_CLOSE_BRACKET");
            this.fTokenNames.put(4, "EXPRTOKEN_PERIOD");
            this.fTokenNames.put(5, "EXPRTOKEN_DOUBLE_PERIOD");
            this.fTokenNames.put(6, "EXPRTOKEN_ATSIGN");
            this.fTokenNames.put(7, "EXPRTOKEN_COMMA");
            this.fTokenNames.put(8, "EXPRTOKEN_DOUBLE_COLON");
            this.fTokenNames.put(9, "EXPRTOKEN_NAMETEST_ANY");
            this.fTokenNames.put(10, "EXPRTOKEN_NAMETEST_NAMESPACE");
            this.fTokenNames.put(11, "EXPRTOKEN_NAMETEST_QNAME");
            this.fTokenNames.put(12, "EXPRTOKEN_NODETYPE_COMMENT");
            this.fTokenNames.put(13, "EXPRTOKEN_NODETYPE_TEXT");
            this.fTokenNames.put(14, "EXPRTOKEN_NODETYPE_PI");
            this.fTokenNames.put(15, "EXPRTOKEN_NODETYPE_NODE");
            this.fTokenNames.put(16, "EXPRTOKEN_OPERATOR_AND");
            this.fTokenNames.put(17, "EXPRTOKEN_OPERATOR_OR");
            this.fTokenNames.put(18, "EXPRTOKEN_OPERATOR_MOD");
            this.fTokenNames.put(19, "EXPRTOKEN_OPERATOR_DIV");
            this.fTokenNames.put(20, "EXPRTOKEN_OPERATOR_MULT");
            this.fTokenNames.put(21, "EXPRTOKEN_OPERATOR_SLASH");
            this.fTokenNames.put(22, "EXPRTOKEN_OPERATOR_DOUBLE_SLASH");
            this.fTokenNames.put(23, "EXPRTOKEN_OPERATOR_UNION");
            this.fTokenNames.put(24, "EXPRTOKEN_OPERATOR_PLUS");
            this.fTokenNames.put(25, "EXPRTOKEN_OPERATOR_MINUS");
            this.fTokenNames.put(26, "EXPRTOKEN_OPERATOR_EQUAL");
            this.fTokenNames.put(27, "EXPRTOKEN_OPERATOR_NOT_EQUAL");
            this.fTokenNames.put(28, "EXPRTOKEN_OPERATOR_LESS");
            this.fTokenNames.put(29, "EXPRTOKEN_OPERATOR_LESS_EQUAL");
            this.fTokenNames.put(30, "EXPRTOKEN_OPERATOR_GREATER");
            this.fTokenNames.put(31, "EXPRTOKEN_OPERATOR_GREATER_EQUAL");
            this.fTokenNames.put(32, "EXPRTOKEN_FUNCTION_NAME");
            this.fTokenNames.put(33, "EXPRTOKEN_AXISNAME_ANCESTOR");
            this.fTokenNames.put(34, "EXPRTOKEN_AXISNAME_ANCESTOR_OR_SELF");
            this.fTokenNames.put(35, "EXPRTOKEN_AXISNAME_ATTRIBUTE");
            this.fTokenNames.put(36, "EXPRTOKEN_AXISNAME_CHILD");
            this.fTokenNames.put(37, "EXPRTOKEN_AXISNAME_DESCENDANT");
            this.fTokenNames.put(38, "EXPRTOKEN_AXISNAME_DESCENDANT_OR_SELF");
            this.fTokenNames.put(39, "EXPRTOKEN_AXISNAME_FOLLOWING");
            this.fTokenNames.put(40, "EXPRTOKEN_AXISNAME_FOLLOWING_SIBLING");
            this.fTokenNames.put(41, "EXPRTOKEN_AXISNAME_NAMESPACE");
            this.fTokenNames.put(42, "EXPRTOKEN_AXISNAME_PARENT");
            this.fTokenNames.put(43, "EXPRTOKEN_AXISNAME_PRECEDING");
            this.fTokenNames.put(44, "EXPRTOKEN_AXISNAME_PRECEDING_SIBLING");
            this.fTokenNames.put(45, "EXPRTOKEN_AXISNAME_SELF");
            this.fTokenNames.put(46, "EXPRTOKEN_LITERAL");
            this.fTokenNames.put(47, "EXPRTOKEN_NUMBER");
            this.fTokenNames.put(48, "EXPRTOKEN_VARIABLE_REFERENCE");
        }

        public void addToken(String str) {
            Integer numValueOf = null;
            for (Map.Entry<Integer, String> entry : this.fTokenNames.entrySet()) {
                if (entry.getValue().equals(str)) {
                    numValueOf = entry.getKey();
                }
            }
            if (numValueOf == null) {
                numValueOf = Integer.valueOf(this.fTokenNames.size());
                this.fTokenNames.put(numValueOf, str);
            }
            addToken(numValueOf.intValue());
        }

        public void dumpTokens() {
            int i = 0;
            while (i < this.fTokenCount) {
                switch (this.fTokens[i]) {
                    case 0:
                        System.out.print("<OPEN_PAREN/>");
                        break;
                    case 1:
                        System.out.print("<CLOSE_PAREN/>");
                        break;
                    case 2:
                        System.out.print("<OPEN_BRACKET/>");
                        break;
                    case 3:
                        System.out.print("<CLOSE_BRACKET/>");
                        break;
                    case 4:
                        System.out.print("<PERIOD/>");
                        break;
                    case 5:
                        System.out.print("<DOUBLE_PERIOD/>");
                        break;
                    case 6:
                        System.out.print("<ATSIGN/>");
                        break;
                    case 7:
                        System.out.print("<COMMA/>");
                        break;
                    case 8:
                        System.out.print("<DOUBLE_COLON/>");
                        break;
                    case 9:
                        System.out.print("<NAMETEST_ANY/>");
                        break;
                    case 10:
                        System.out.print("<NAMETEST_NAMESPACE");
                        PrintStream printStream = System.out;
                        StringBuilder sb = new StringBuilder(" prefix=\"");
                        i++;
                        sb.append(getTokenString(this.fTokens[i]));
                        sb.append("\"");
                        printStream.print(sb.toString());
                        System.out.print("/>");
                        break;
                    case 11:
                        System.out.print("<NAMETEST_QNAME");
                        int i2 = i + 1;
                        if (this.fTokens[i2] != -1) {
                            System.out.print(" prefix=\"" + getTokenString(this.fTokens[i2]) + "\"");
                        }
                        PrintStream printStream2 = System.out;
                        StringBuilder sb2 = new StringBuilder(" localpart=\"");
                        i += 2;
                        sb2.append(getTokenString(this.fTokens[i]));
                        sb2.append("\"");
                        printStream2.print(sb2.toString());
                        System.out.print("/>");
                        break;
                    case 12:
                        System.out.print("<NODETYPE_COMMENT/>");
                        break;
                    case 13:
                        System.out.print("<NODETYPE_TEXT/>");
                        break;
                    case 14:
                        System.out.print("<NODETYPE_PI/>");
                        break;
                    case 15:
                        System.out.print("<NODETYPE_NODE/>");
                        break;
                    case 16:
                        System.out.print("<OPERATOR_AND/>");
                        break;
                    case 17:
                        System.out.print("<OPERATOR_OR/>");
                        break;
                    case 18:
                        System.out.print("<OPERATOR_MOD/>");
                        break;
                    case 19:
                        System.out.print("<OPERATOR_DIV/>");
                        break;
                    case 20:
                        System.out.print("<OPERATOR_MULT/>");
                        break;
                    case 21:
                        System.out.print("<OPERATOR_SLASH/>");
                        if (i + 1 < this.fTokenCount) {
                            System.out.println();
                            System.out.print("  ");
                        }
                        break;
                    case 22:
                        System.out.print("<OPERATOR_DOUBLE_SLASH/>");
                        break;
                    case 23:
                        System.out.print("<OPERATOR_UNION/>");
                        break;
                    case 24:
                        System.out.print("<OPERATOR_PLUS/>");
                        break;
                    case 25:
                        System.out.print("<OPERATOR_MINUS/>");
                        break;
                    case 26:
                        System.out.print("<OPERATOR_EQUAL/>");
                        break;
                    case 27:
                        System.out.print("<OPERATOR_NOT_EQUAL/>");
                        break;
                    case 28:
                        System.out.print("<OPERATOR_LESS/>");
                        break;
                    case 29:
                        System.out.print("<OPERATOR_LESS_EQUAL/>");
                        break;
                    case 30:
                        System.out.print("<OPERATOR_GREATER/>");
                        break;
                    case 31:
                        System.out.print("<OPERATOR_GREATER_EQUAL/>");
                        break;
                    case 32:
                        System.out.print("<FUNCTION_NAME");
                        int i3 = i + 1;
                        if (this.fTokens[i3] != -1) {
                            System.out.print(" prefix=\"" + getTokenString(this.fTokens[i3]) + "\"");
                        }
                        PrintStream printStream3 = System.out;
                        StringBuilder sb3 = new StringBuilder(" localpart=\"");
                        i += 2;
                        sb3.append(getTokenString(this.fTokens[i]));
                        sb3.append("\"");
                        printStream3.print(sb3.toString());
                        System.out.print("/>");
                        break;
                    case 33:
                        System.out.print("<AXISNAME_ANCESTOR/>");
                        break;
                    case 34:
                        System.out.print("<AXISNAME_ANCESTOR_OR_SELF/>");
                        break;
                    case 35:
                        System.out.print("<AXISNAME_ATTRIBUTE/>");
                        break;
                    case 36:
                        System.out.print("<AXISNAME_CHILD/>");
                        break;
                    case 37:
                        System.out.print("<AXISNAME_DESCENDANT/>");
                        break;
                    case 38:
                        System.out.print("<AXISNAME_DESCENDANT_OR_SELF/>");
                        break;
                    case 39:
                        System.out.print("<AXISNAME_FOLLOWING/>");
                        break;
                    case 40:
                        System.out.print("<AXISNAME_FOLLOWING_SIBLING/>");
                        break;
                    case 41:
                        System.out.print("<AXISNAME_NAMESPACE/>");
                        break;
                    case 42:
                        System.out.print("<AXISNAME_PARENT/>");
                        break;
                    case 43:
                        System.out.print("<AXISNAME_PRECEDING/>");
                        break;
                    case 44:
                        System.out.print("<AXISNAME_PRECEDING_SIBLING/>");
                        break;
                    case 45:
                        System.out.print("<AXISNAME_SELF/>");
                        break;
                    case 46:
                        System.out.print("<LITERAL");
                        PrintStream printStream4 = System.out;
                        StringBuilder sb4 = new StringBuilder(" value=\"");
                        i++;
                        sb4.append(getTokenString(this.fTokens[i]));
                        sb4.append("\"");
                        printStream4.print(sb4.toString());
                        System.out.print("/>");
                        break;
                    case 47:
                        System.out.print("<NUMBER");
                        System.out.print(" whole=\"" + getTokenString(this.fTokens[i + 1]) + "\"");
                        PrintStream printStream5 = System.out;
                        StringBuilder sb5 = new StringBuilder(" part=\"");
                        i += 2;
                        sb5.append(getTokenString(this.fTokens[i]));
                        sb5.append("\"");
                        printStream5.print(sb5.toString());
                        System.out.print("/>");
                        break;
                    case 48:
                        System.out.print("<VARIABLE_REFERENCE");
                        int i4 = i + 1;
                        if (this.fTokens[i4] != -1) {
                            System.out.print(" prefix=\"" + getTokenString(this.fTokens[i4]) + "\"");
                        }
                        PrintStream printStream6 = System.out;
                        StringBuilder sb6 = new StringBuilder(" localpart=\"");
                        i += 2;
                        sb6.append(getTokenString(this.fTokens[i]));
                        sb6.append("\"");
                        printStream6.print(sb6.toString());
                        System.out.print("/>");
                        break;
                    default:
                        System.out.println("<???/>");
                        break;
                }
                i++;
            }
            System.out.println();
        }

        public String getTokenString(int i) {
            return this.fTokenNames.get(Integer.valueOf(i));
        }

        public boolean hasMore() {
            return this.fCurrentTokenIndex < this.fTokenCount;
        }

        public int nextToken() throws XPathException {
            int i = this.fCurrentTokenIndex;
            if (i == this.fTokenCount) {
                throw new XPathException("c-general-xpath");
            }
            int[] iArr = this.fTokens;
            this.fCurrentTokenIndex = i + 1;
            return iArr[i];
        }

        public String nextTokenAsString() throws XPathException {
            String tokenString = getTokenString(nextToken());
            if (tokenString != null) {
                return tokenString;
            }
            throw new XPathException("c-general-xpath");
        }

        public int peekToken() throws XPathException {
            int i = this.fCurrentTokenIndex;
            if (i != this.fTokenCount) {
                return this.fTokens[i];
            }
            throw new XPathException("c-general-xpath");
        }

        public void rewind() {
            this.fCurrentTokenIndex = 0;
        }

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
