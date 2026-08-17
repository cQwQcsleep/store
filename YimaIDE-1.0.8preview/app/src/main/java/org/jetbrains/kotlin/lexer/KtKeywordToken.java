package org.jetbrains.kotlin.lexer;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public class KtKeywordToken extends KtSingleValueToken {
    private final boolean myIsSoft;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i == 1 || i == 3) {
            objArr[0] = "value";
        } else {
            objArr[0] = "debugName";
        }
        objArr[1] = "org/jetbrains/kotlin/lexer/KtKeywordToken";
        objArr[2] = "<init>";
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @Deprecated
    public KtKeywordToken(String str, String str2, boolean z) {
        super(str, str2);
        if (str == null) {
            $$$reportNull$$$0(0);
        }
        if (str2 == null) {
            $$$reportNull$$$0(1);
        }
        this.myIsSoft = z;
    }

    @Deprecated
    public static KtKeywordToken keyword(String str, String str2) {
        return new KtKeywordToken(str, str2, false);
    }

    @Deprecated
    public static KtKeywordToken softKeyword(String str) {
        return new KtKeywordToken(str, str, true);
    }

    public boolean isSoft() {
        return this.myIsSoft;
    }

    public static KtKeywordToken keyword(String str, int i) {
        return keyword(str, str, i);
    }

    public static KtKeywordToken softKeyword(String str, int i) {
        return new KtKeywordToken(str, str, true, i);
    }

    @Deprecated
    public static KtKeywordToken keyword(String str) {
        return keyword(str, str);
    }

    public static KtKeywordToken keyword(String str, String str2, int i) {
        return new KtKeywordToken(str, str2, false, i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KtKeywordToken(String str, String str2, boolean z, int i) {
        super(str, str2, i);
        if (str == null) {
            $$$reportNull$$$0(2);
        }
        if (str2 == null) {
            $$$reportNull$$$0(3);
        }
        this.myIsSoft = z;
    }
}
