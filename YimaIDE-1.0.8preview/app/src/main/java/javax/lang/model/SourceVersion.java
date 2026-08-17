package javax.lang.model;

import com.intellij.psi.PsiKeyword;
import com.sun.jna.platform.win32.WinNT;
import com.sun.org.apache.bcel.internal.Const;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public enum SourceVersion {
    RELEASE_0,
    RELEASE_1,
    RELEASE_2,
    RELEASE_3,
    RELEASE_4,
    RELEASE_5,
    RELEASE_6,
    RELEASE_7,
    RELEASE_8,
    RELEASE_9,
    RELEASE_10,
    RELEASE_11,
    RELEASE_12,
    RELEASE_13,
    RELEASE_14,
    RELEASE_15,
    RELEASE_16,
    RELEASE_17,
    RELEASE_18,
    RELEASE_19,
    RELEASE_20,
    RELEASE_21,
    RELEASE_22,
    RELEASE_23,
    RELEASE_24,
    RELEASE_25,
    RELEASE_26;

    private static final SourceVersion latestSupported = getLatestSupported();

    private static SourceVersion getLatestSupported() {
        return latest();
    }

    public static boolean isIdentifier(CharSequence charSequence) {
        String string = charSequence.toString();
        if (string.length() == 0) {
            return false;
        }
        int iCodePointAt = string.codePointAt(0);
        if (!Character.isJavaIdentifierStart(iCodePointAt)) {
            return false;
        }
        int iCharCount = Character.charCount(iCodePointAt);
        while (iCharCount < string.length()) {
            int iCodePointAt2 = string.codePointAt(iCharCount);
            if (!Character.isJavaIdentifierPart(iCodePointAt2)) {
                return false;
            }
            iCharCount += Character.charCount(iCodePointAt2);
        }
        return true;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static boolean isKeyword(CharSequence charSequence, SourceVersion sourceVersion) {
        String string = charSequence.toString();
        string.getClass();
        byte b = -1;
        switch (string.hashCode()) {
            case -1888027236:
                if (string.equals(PsiKeyword.VOLATILE)) {
                    b = 0;
                }
                break;
            case -1466596076:
                if (string.equals(PsiKeyword.SYNCHRONIZED)) {
                    b = 1;
                }
                break;
            case -1408208058:
                if (string.equals(PsiKeyword.ASSERT)) {
                    b = 2;
                }
                break;
            case -1325958191:
                if (string.equals("double")) {
                    b = 3;
                }
                break;
            case -1305664359:
                if (string.equals(PsiKeyword.EXTENDS)) {
                    b = 4;
                }
                break;
            case -1184795739:
                if (string.equals("import")) {
                    b = 5;
                }
                break;
            case -1052618729:
                if (string.equals(PsiKeyword.NATIVE)) {
                    b = 6;
                }
                break;
            case -977423767:
                if (string.equals(PsiKeyword.PUBLIC)) {
                    b = 7;
                }
                break;
            case -934396624:
                if (string.equals(PsiKeyword.RETURN)) {
                    b = 8;
                }
                break;
            case -915384400:
                if (string.equals(PsiKeyword.IMPLEMENTS)) {
                    b = 9;
                }
                break;
            case -892481938:
                if (string.equals(PsiKeyword.STATIC)) {
                    b = 10;
                }
                break;
            case -889473228:
                if (string.equals(PsiKeyword.SWITCH)) {
                    b = 11;
                }
                break;
            case -874432947:
                if (string.equals(PsiKeyword.THROWS)) {
                    b = 12;
                }
                break;
            case -853259901:
                if (string.equals(PsiKeyword.FINALLY)) {
                    b = 13;
                }
                break;
            case -807062458:
                if (string.equals(PsiKeyword.PACKAGE)) {
                    b = 14;
                }
                break;
            case -608539730:
                if (string.equals(PsiKeyword.PROTECTED)) {
                    b = 15;
                }
                break;
            case -567202649:
                if (string.equals(PsiKeyword.CONTINUE)) {
                    b = 16;
                }
                break;
            case -314497661:
                if (string.equals(PsiKeyword.PRIVATE)) {
                    b = 17;
                }
                break;
            case 95:
                if (string.equals("_")) {
                    b = 18;
                }
                break;
            case 3211:
                if (string.equals(PsiKeyword.DO)) {
                    b = 19;
                }
                break;
            case 3357:
                if (string.equals("if")) {
                    b = 20;
                }
                break;
            case 101577:
                if (string.equals(PsiKeyword.FOR)) {
                    b = Const.ATTR_METHOD_PARAMETERS;
                }
                break;
            case 104431:
                if (string.equals("int")) {
                    b = Const.ATTR_MODULE;
                }
                break;
            case 108960:
                if (string.equals(PsiKeyword.NEW)) {
                    b = Const.ATTR_MODULE_PACKAGES;
                }
                break;
            case 115131:
                if (string.equals(PsiKeyword.TRY)) {
                    b = Const.ATTR_MODULE_MAIN_CLASS;
                }
                break;
            case 3039496:
                if (string.equals("byte")) {
                    b = Const.ATTR_NEST_HOST;
                }
                break;
            case 3046192:
                if (string.equals(PsiKeyword.CASE)) {
                    b = 26;
                }
                break;
            case 3052374:
                if (string.equals(PsiKeyword.CHAR)) {
                    b = 27;
                }
                break;
            case 3116345:
                if (string.equals(PsiKeyword.ELSE)) {
                    b = 28;
                }
                break;
            case 3118337:
                if (string.equals(PsiKeyword.ENUM)) {
                    b = 29;
                }
                break;
            case 3178851:
                if (string.equals(PsiKeyword.GOTO)) {
                    b = 30;
                }
                break;
            case 3327612:
                if (string.equals("long")) {
                    b = WinNT.VALID_INHERIT_FLAGS;
                }
                break;
            case 3392903:
                if (string.equals(PsiKeyword.NULL)) {
                    b = 32;
                }
                break;
            case 3559070:
                if (string.equals(PsiKeyword.THIS)) {
                    b = 33;
                }
                break;
            case 3569038:
                if (string.equals("true")) {
                    b = 34;
                }
                break;
            case 3625364:
                if (string.equals(PsiKeyword.VOID)) {
                    b = 35;
                }
                break;
            case 64711720:
                if (string.equals("boolean")) {
                    b = 36;
                }
                break;
            case 94001407:
                if (string.equals(PsiKeyword.BREAK)) {
                    b = 37;
                }
                break;
            case 94432955:
                if (string.equals(PsiKeyword.CATCH)) {
                    b = 38;
                }
                break;
            case 94742904:
                if (string.equals("class")) {
                    b = 39;
                }
                break;
            case 94844771:
                if (string.equals(PsiKeyword.CONST)) {
                    b = 40;
                }
                break;
            case 97196323:
                if (string.equals("false")) {
                    b = 41;
                }
                break;
            case 97436022:
                if (string.equals(PsiKeyword.FINAL)) {
                    b = 42;
                }
                break;
            case 97526364:
                if (string.equals("float")) {
                    b = 43;
                }
                break;
            case 109413500:
                if (string.equals("short")) {
                    b = 44;
                }
                break;
            case 109801339:
                if (string.equals(PsiKeyword.SUPER)) {
                    b = 45;
                }
                break;
            case 110339814:
                if (string.equals(PsiKeyword.THROW)) {
                    b = 46;
                }
                break;
            case 113101617:
                if (string.equals(PsiKeyword.WHILE)) {
                    b = 47;
                }
                break;
            case 502623545:
                if (string.equals(PsiKeyword.INTERFACE)) {
                    b = 48;
                }
                break;
            case 902025516:
                if (string.equals(PsiKeyword.INSTANCEOF)) {
                    b = 49;
                }
                break;
            case 1052746378:
                if (string.equals(PsiKeyword.TRANSIENT)) {
                    b = 50;
                }
                break;
            case 1544803905:
                if (string.equals("default")) {
                    b = 51;
                }
                break;
            case 1732898850:
                if (string.equals(PsiKeyword.ABSTRACT)) {
                    b = 52;
                }
                break;
            case 1794694483:
                if (string.equals(PsiKeyword.STRICTFP)) {
                    b = 53;
                }
                break;
        }
        switch (b) {
            case 0:
            case 1:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
                return true;
            case 2:
                return sourceVersion.compareTo(RELEASE_4) >= 0;
            case 18:
                return sourceVersion.compareTo(RELEASE_9) >= 0;
            case 29:
                return sourceVersion.compareTo(RELEASE_5) >= 0;
            case 53:
                return sourceVersion.compareTo(RELEASE_2) >= 0;
            default:
                return false;
        }
    }

    public static boolean isName(CharSequence charSequence, SourceVersion sourceVersion) {
        for (String str : charSequence.toString().split("\\.", -1)) {
            if (!isIdentifier(str) || isKeyword(str, sourceVersion)) {
                return false;
            }
        }
        return true;
    }

    public static SourceVersion latest() {
        return RELEASE_26;
    }

    public static SourceVersion latestSupported() {
        return latestSupported;
    }

    public static boolean isName(CharSequence charSequence) {
        return isName(charSequence, latest());
    }

    public static boolean isKeyword(CharSequence charSequence) {
        return isKeyword(charSequence, latest());
    }
}
