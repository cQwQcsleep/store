package org.jetbrains.kotlin.lexer;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public final class KtModifierKeywordToken extends KtKeywordToken {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i == 1 || i == 3) {
            objArr[0] = "value";
        } else {
            objArr[0] = "debugName";
        }
        objArr[1] = "org/jetbrains/kotlin/lexer/KtModifierKeywordToken";
        objArr[2] = "<init>";
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @Deprecated
    private KtModifierKeywordToken(String str, String str2, boolean z) {
        super(str, str2, z);
        if (str == null) {
            $$$reportNull$$$0(0);
        }
        if (str2 == null) {
            $$$reportNull$$$0(1);
        }
    }

    @Deprecated
    public static KtModifierKeywordToken keywordModifier(String str) {
        return new KtModifierKeywordToken(str, str, false);
    }

    @Deprecated
    public static KtModifierKeywordToken softKeywordModifier(String str) {
        return new KtModifierKeywordToken(str, str, true);
    }

    public static KtModifierKeywordToken keywordModifier(String str, int i) {
        return new KtModifierKeywordToken(str, str, false, i);
    }

    public static KtModifierKeywordToken softKeywordModifier(String str, int i) {
        return new KtModifierKeywordToken(str, str, true, i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    private KtModifierKeywordToken(String str, String str2, boolean z, int i) {
        super(str, str2, z, i);
        if (str == null) {
            $$$reportNull$$$0(2);
        }
        if (str2 == null) {
            $$$reportNull$$$0(3);
        }
    }
}
