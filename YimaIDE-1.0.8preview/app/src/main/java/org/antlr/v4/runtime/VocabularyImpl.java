package org.antlr.v4.runtime;

import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class VocabularyImpl implements Vocabulary {
    private static final String[] EMPTY_NAMES;
    public static final VocabularyImpl EMPTY_VOCABULARY;
    private final String[] displayNames;
    private final String[] literalNames;
    private final int maxTokenType;
    private final String[] symbolicNames;

    static {
        String[] strArr = new String[0];
        EMPTY_NAMES = strArr;
        EMPTY_VOCABULARY = new VocabularyImpl(strArr, strArr, strArr);
    }

    public VocabularyImpl(String[] strArr, String[] strArr2, String[] strArr3) {
        strArr = strArr == null ? EMPTY_NAMES : strArr;
        this.literalNames = strArr;
        strArr2 = strArr2 == null ? EMPTY_NAMES : strArr2;
        this.symbolicNames = strArr2;
        strArr3 = strArr3 == null ? EMPTY_NAMES : strArr3;
        this.displayNames = strArr3;
        this.maxTokenType = Math.max(strArr3.length, Math.max(strArr.length, strArr2.length)) - 1;
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0039  */
    public static Vocabulary fromTokenNames(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return EMPTY_VOCABULARY;
        }
        String[] strArr2 = (String[]) Arrays.copyOf(strArr, strArr.length);
        String[] strArr3 = (String[]) Arrays.copyOf(strArr, strArr.length);
        for (int i = 0; i < strArr.length; i++) {
            String str = strArr[i];
            if (str != null) {
                if (str.isEmpty()) {
                    strArr2[i] = null;
                    strArr3[i] = null;
                } else {
                    char cCharAt = str.charAt(0);
                    if (cCharAt == '\'') {
                        strArr3[i] = null;
                    } else if (Character.isUpperCase(cCharAt)) {
                        strArr2[i] = null;
                    } else {
                        strArr2[i] = null;
                        strArr3[i] = null;
                    }
                }
            }
        }
        return new VocabularyImpl(strArr2, strArr3, strArr);
    }

    @Override // org.antlr.v4.runtime.Vocabulary
    public String getDisplayName(int i) {
        String str;
        if (i >= 0) {
            String[] strArr = this.displayNames;
            if (i < strArr.length && (str = strArr[i]) != null) {
                return str;
            }
        }
        String literalName = getLiteralName(i);
        if (literalName != null) {
            return literalName;
        }
        String symbolicName = getSymbolicName(i);
        return symbolicName != null ? symbolicName : Integer.toString(i);
    }

    public String[] getDisplayNames() {
        return this.displayNames;
    }

    @Override // org.antlr.v4.runtime.Vocabulary
    public String getLiteralName(int i) {
        if (i < 0) {
            return null;
        }
        String[] strArr = this.literalNames;
        if (i < strArr.length) {
            return strArr[i];
        }
        return null;
    }

    public String[] getLiteralNames() {
        return this.literalNames;
    }

    @Override // org.antlr.v4.runtime.Vocabulary
    public int getMaxTokenType() {
        return this.maxTokenType;
    }

    @Override // org.antlr.v4.runtime.Vocabulary
    public String getSymbolicName(int i) {
        if (i >= 0) {
            String[] strArr = this.symbolicNames;
            if (i < strArr.length) {
                return strArr[i];
            }
        }
        if (i == -1) {
            return "EOF";
        }
        return null;
    }

    public String[] getSymbolicNames() {
        return this.symbolicNames;
    }

    public VocabularyImpl(String[] strArr, String[] strArr2) {
        this(strArr, strArr2, null);
    }
}
