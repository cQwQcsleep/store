package org.eclipse.jdt.internal.compiler.parser;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface TerminalTokens {
    public static final int TokenNameAND = 21;
    public static final int TokenNameAND_AND = 30;
    public static final int TokenNameAND_EQUAL = 98;
    public static final int TokenNameARROW = 105;
    public static final int TokenNameAT = 35;
    public static final int TokenNameAT308 = 28;
    public static final int TokenNameAT308DOTDOTDOT = 132;
    public static final int TokenNameBeginCaseElement = 133;
    public static final int TokenNameBeginCaseExpr = 73;
    public static final int TokenNameBeginIntersectionCast = 70;
    public static final int TokenNameBeginLambda = 63;
    public static final int TokenNameBeginTypeArguments = 90;
    public static final int TokenNameCOLON = 66;
    public static final int TokenNameCOLON_COLON = 7;
    public static final int TokenNameCOMMA = 32;
    public static final int TokenNameCOMMENT_BLOCK = 1002;
    public static final int TokenNameCOMMENT_JAVADOC = 1003;
    public static final int TokenNameCOMMENT_LINE = 1001;
    public static final int TokenNameCharacterLiteral = 62;
    public static final int TokenNameDIVIDE = 10;
    public static final int TokenNameDIVIDE_EQUAL = 97;
    public static final int TokenNameDOT = 1;
    public static final int TokenNameDoubleLiteral = 61;
    public static final int TokenNameELLIPSIS = 123;
    public static final int TokenNameEOF = 64;
    public static final int TokenNameEQUAL = 78;
    public static final int TokenNameEQUAL_EQUAL = 19;
    public static final int TokenNameERROR = 140;
    public static final int TokenNameElidedSemicolonAndRightBrace = 72;
    public static final int TokenNameFloatingPointLiteral = 60;
    public static final int TokenNameGREATER = 15;
    public static final int TokenNameGREATER_EQUAL = 13;
    public static final int TokenNameIdentifier = 22;
    public static final int TokenNameIntegerLiteral = 58;
    public static final int TokenNameLBRACE = 52;
    public static final int TokenNameLBRACKET = 6;
    public static final int TokenNameLEFT_SHIFT = 18;
    public static final int TokenNameLEFT_SHIFT_EQUAL = 102;
    public static final int TokenNameLESS = 11;
    public static final int TokenNameLESS_EQUAL = 12;
    public static final int TokenNameLPAREN = 24;
    public static final int TokenNameLongLiteral = 59;
    public static final int TokenNameMINUS = 5;
    public static final int TokenNameMINUS_EQUAL = 95;
    public static final int TokenNameMINUS_MINUS = 3;
    public static final int TokenNameMULTIPLY = 8;
    public static final int TokenNameMULTIPLY_EQUAL = 96;
    public static final int TokenNameNOT = 67;
    public static final int TokenNameNOT_EQUAL = 20;
    public static final int TokenNameNotAToken = 0;
    public static final int TokenNameOR = 27;
    public static final int TokenNameOR_EQUAL = 99;
    public static final int TokenNameOR_OR = 31;
    public static final int TokenNamePLUS = 4;
    public static final int TokenNamePLUS_EQUAL = 94;
    public static final int TokenNamePLUS_PLUS = 2;
    public static final int TokenNameQUESTION = 29;
    public static final int TokenNameRBRACE = 33;
    public static final int TokenNameRBRACKET = 69;
    public static final int TokenNameREMAINDER = 9;
    public static final int TokenNameREMAINDER_EQUAL = 101;
    public static final int TokenNameRIGHT_SHIFT = 14;
    public static final int TokenNameRIGHT_SHIFT_EQUAL = 103;
    public static final int TokenNameRPAREN = 25;
    public static final int TokenNameRestrictedIdentifierWhen = 134;
    public static final int TokenNameRestrictedIdentifierYield = 81;
    public static final int TokenNameRestrictedIdentifierpermits = 130;
    public static final int TokenNameRestrictedIdentifierrecord = 76;
    public static final int TokenNameRestrictedIdentifiersealed = 41;
    public static final int TokenNameSEMICOLON = 26;
    public static final int TokenNameSingleQuoteStringLiteral = 1004;
    public static final int TokenNameStringLiteral = 53;
    public static final int TokenNameStringTemplate = 121;
    public static final int TokenNameTWIDDLE = 68;
    public static final int TokenNameTextBlock = 54;
    public static final int TokenNameTextBlockTemplate = 122;
    public static final int TokenNameUNDERSCORE = 34;
    public static final int TokenNameUNSIGNED_RIGHT_SHIFT = 17;
    public static final int TokenNameUNSIGNED_RIGHT_SHIFT_EQUAL = 104;
    public static final int TokenNameWHITESPACE = 1000;
    public static final int TokenNameXOR = 23;
    public static final int TokenNameXOR_EQUAL = 100;
    public static final int TokenNameabstract = 42;
    public static final int TokenNameassert = 82;
    public static final int TokenNameboolean = 106;
    public static final int TokenNamebreak = 83;
    public static final int TokenNamebyte = 107;
    public static final int TokenNamecase = 92;
    public static final int TokenNamecatch = 108;
    public static final int TokenNamechar = 109;
    public static final int TokenNameclass = 71;
    public static final int TokenNameconst = 138;
    public static final int TokenNamecontinue = 84;
    public static final int TokenNamedefault = 77;
    public static final int TokenNamedo = 85;
    public static final int TokenNamedouble = 110;
    public static final int TokenNameelse = 124;
    public static final int TokenNameenum = 75;
    public static final int TokenNameexports = 126;
    public static final int TokenNameextends = 93;
    public static final int TokenNamefalse = 55;
    public static final int TokenNamefinal = 43;
    public static final int TokenNamefinally = 117;
    public static final int TokenNamefloat = 111;
    public static final int TokenNamefor = 86;
    public static final int TokenNamegoto = 139;
    public static final int TokenNameif = 87;
    public static final int TokenNameimplements = 135;
    public static final int TokenNameimport = 112;
    public static final int TokenNameinstanceof = 16;
    public static final int TokenNameint = 113;
    public static final int TokenNameinterface = 74;
    public static final int TokenNamelong = 114;
    public static final int TokenNamemodule = 119;
    public static final int TokenNamenative = 44;
    public static final int TokenNamenew = 40;
    public static final int TokenNamenon_sealed = 45;
    public static final int TokenNamenull = 56;
    public static final int TokenNameopen = 120;
    public static final int TokenNameopens = 127;
    public static final int TokenNamepackage = 91;
    public static final int TokenNameprivate = 46;
    public static final int TokenNameprotected = 47;
    public static final int TokenNameprovides = 129;
    public static final int TokenNamepublic = 48;
    public static final int TokenNamerequires = 125;
    public static final int TokenNamereturn = 88;
    public static final int TokenNameshort = 115;
    public static final int TokenNamestatic = 36;
    public static final int TokenNamestrictfp = 49;
    public static final int TokenNamesuper = 37;
    public static final int TokenNameswitch = 65;
    public static final int TokenNamesynchronized = 39;
    public static final int TokenNamethis = 38;
    public static final int TokenNamethrow = 79;
    public static final int TokenNamethrows = 118;
    public static final int TokenNameto = 136;
    public static final int TokenNametransient = 50;
    public static final int TokenNametransitive = 131;
    public static final int TokenNametrue = 57;
    public static final int TokenNametry = 89;
    public static final int TokenNameuses = 128;
    public static final int TokenNamevoid = 116;
    public static final int TokenNamevolatile = 51;
    public static final int TokenNamewhile = 80;
    public static final int TokenNamewith = 137;

    static int getRestrictedKeyword(String str) {
        switch (str.hashCode()) {
            case -934908847:
                return !str.equals("record") ? 0 : 76;
            case -906342564:
                return !str.equals("sealed") ? 0 : 41;
            case -678625352:
                return !str.equals("permits") ? 0 : 130;
            case 3648314:
                if (str.equals("when")) {
                    return TokenNameRestrictedIdentifierWhen;
                }
                return 0;
            case 114974605:
                return !str.equals("yield") ? 0 : 81;
            default:
                return 0;
        }
    }

    static boolean isRestrictedKeyword(int i) {
        return i == 41 || i == 76 || i == 81 || i == 130 || i == 134;
    }

    static int getRestrictedKeyword(char[] cArr) {
        int length;
        char c;
        if (cArr == null || !(((length = cArr.length) == 4 && cArr[0] == 'w') || ((length == 5 && cArr[0] == 'y') || ((length == 6 && ((c = cArr[0]) == 'r' || c == 's')) || (length == 7 && cArr[0] == 'p'))))) {
            return 0;
        }
        return getRestrictedKeyword(new String(cArr));
    }
}
