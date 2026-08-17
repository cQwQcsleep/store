package org.jetbrains.kotlin.lexer;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public class KtSingleValueToken extends KtToken {
    private final String myValue;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 4 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 4 ? 3 : 2];
        if (i == 1 || i == 3) {
            objArr[0] = "value";
        } else if (i != 4) {
            objArr[0] = "debugName";
        } else {
            objArr[0] = "org/jetbrains/kotlin/lexer/KtSingleValueToken";
        }
        if (i != 4) {
            objArr[1] = "org/jetbrains/kotlin/lexer/KtSingleValueToken";
        } else {
            objArr[1] = "getValue";
        }
        if (i != 4) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i == 4) {
            throw new IllegalStateException(str2);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @Deprecated
    public KtSingleValueToken(String str, String str2) {
        super(str);
        if (str == null) {
            $$$reportNull$$$0(0);
        }
        if (str2 == null) {
            $$$reportNull$$$0(1);
        }
        this.myValue = str2;
    }

    public String getValue() {
        String str = this.myValue;
        if (str == null) {
            $$$reportNull$$$0(4);
        }
        return str;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KtSingleValueToken(String str, String str2, int i) {
        super(str, i);
        if (str == null) {
            $$$reportNull$$$0(2);
        }
        if (str2 == null) {
            $$$reportNull$$$0(3);
        }
        this.myValue = str2;
    }
}
