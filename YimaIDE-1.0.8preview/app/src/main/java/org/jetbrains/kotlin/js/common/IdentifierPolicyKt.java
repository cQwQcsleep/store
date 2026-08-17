package org.jetbrains.kotlin.js.common;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.library.KlibConstants;
import org.jetbrains.kotlin.library.KotlinLibraryKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\f\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\f\u0010\u0003\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\f\u0010\u0004\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\n\u0010\u0005\u001a\u00020\u0001*\u00020\u0002\u001a\f\u0010\u0006\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\n\u0010\u0007\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\b\u001a\u00020\u0001*\u00020\t\u001a\u0018\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\t2\b\b\u0002\u0010\u0012\u001a\u00020\u0001\u001a&\u0010\u0013\u001a\u00020\u0002*\u00020\u00022\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u0016H\u0082\b\"\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\"\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r\"\u0015\u0010\u0017\u001a\u00020\t*\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"isAllowedLatinLetterOrSpecial", "", "", "isAllowedSimpleDigit", "isNotAllowedSimpleCharacter", "isES5IdentifierStart", "isES5IdentifierStartFull", "isES5IdentifierPart", "isValidES5Identifier", "", "SPECIAL_KEYWORDS", "", "getSPECIAL_KEYWORDS", "()Ljava/util/Set;", "RESERVED_KEYWORDS", "getRESERVED_KEYWORDS", "makeValidES5Identifier", "name", "withHash", "mangleIfNot", "predicate", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "safeModuleName", "getSafeModuleName", "(Ljava/lang/String;)Ljava/lang/String;", "org.jetbrains.kotlin:js.ast"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class IdentifierPolicyKt {
    private static final Set<String> RESERVED_KEYWORDS;
    private static final Set<String> SPECIAL_KEYWORDS;

    static {
        Set<String> of = SetsKt.setOf(KlibConstants.KLIB_DEFAULT_COMPONENT_NAME);
        SPECIAL_KEYWORDS = of;
        RESERVED_KEYWORDS = SetsKt.plus(of, SetsKt.setOf(new String[]{"await", "break", "case", "catch", "continue", "debugger", "delete", "do", "else", "finally", "for", "function", "if", "in", "instanceof", "new", "return", "switch", "this", "throw", "try", "typeof", "var", "void", "while", "with", "class", "const", "enum", "export", "extends", "import", "super", "implements", "interface", "let", KotlinLibraryKt.KLIB_PROPERTY_PACKAGE, "private", "protected", "public", "static", "yield", "null", "true", "false", "eval", "arguments"}));
    }

    public static final Set<String> getRESERVED_KEYWORDS() {
        return RESERVED_KEYWORDS;
    }

    public static final Set<String> getSPECIAL_KEYWORDS() {
        return SPECIAL_KEYWORDS;
    }

    public static final String getSafeModuleName(String str) {
        str.getClass();
        if (StringsKt.startsWith$default(str, '<', false, 2, (Object) null)) {
            str = str.substring(1);
        }
        if (StringsKt.endsWith$default(str, '>', false, 2, (Object) null)) {
            str = str.substring(0, str.length() - 1);
        }
        return makeValidES5Identifier("kotlin_" + str, false);
    }

    private static final boolean isAllowedLatinLetterOrSpecial(char c) {
        if ('a' > c || c >= '{') {
            return ('A' <= c && c < '[') || c == '_' || c == '$';
        }
        return true;
    }

    private static final boolean isAllowedSimpleDigit(char c) {
        return '0' <= c && c < ':';
    }

    public static final boolean isES5IdentifierPart(char c) {
        byte type;
        if (isAllowedLatinLetterOrSpecial(c) || isAllowedSimpleDigit(c)) {
            return true;
        }
        if (isNotAllowedSimpleCharacter(c)) {
            return false;
        }
        return isES5IdentifierStartFull(c) || (type = (byte) Character.getType(c)) == 6 || type == 8 || type == 9 || type == 23 || c == 8204 || c == 8205;
    }

    public static final boolean isES5IdentifierStart(char c) {
        if (isAllowedLatinLetterOrSpecial(c)) {
            return true;
        }
        if (isNotAllowedSimpleCharacter(c)) {
            return false;
        }
        return isES5IdentifierStartFull(c);
    }

    private static final boolean isES5IdentifierStartFull(char c) {
        return Character.isLetter(c) || ((byte) Character.getType(c)) == 10;
    }

    private static final boolean isNotAllowedSimpleCharacter(char c) {
        return c == ' ' || c == '-' || c == '<' || c == '>' || c == '?';
    }

    public static final boolean isValidES5Identifier(String str) {
        str.getClass();
        if (str.length() == 0 || !isES5IdentifierStart(str.charAt(0))) {
            return false;
        }
        int length = str.length();
        for (int i = 1; i < length; i++) {
            if (!isES5IdentifierPart(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static final String makeValidES5Identifier(String str, boolean z) {
        str.getClass();
        if (isValidES5Identifier(str)) {
            return str;
        }
        if (str.length() == 0) {
            return "_";
        }
        StringBuilder sb = new StringBuilder(str.length() + (z ? 7 : 0));
        char cFirst = StringsKt.first(str);
        if (!isES5IdentifierStart(cFirst)) {
            cFirst = '_';
        }
        sb.append(cFirst);
        int lastIndex = StringsKt.getLastIndex(str);
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                char cCharAt = str.charAt(i);
                if (!isES5IdentifierPart(cCharAt)) {
                    cCharAt = '_';
                }
                sb.append(cCharAt);
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        if (!z) {
            return sb.toString();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append((Object) sb);
        sb2.append('_');
        String string = Integer.toString(Math.abs(str.hashCode()), CharsKt.checkRadix(36));
        string.getClass();
        sb2.append(string);
        return sb2.toString();
    }

    public static /* synthetic */ String makeValidES5Identifier$default(String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return makeValidES5Identifier(str, z);
    }
}
