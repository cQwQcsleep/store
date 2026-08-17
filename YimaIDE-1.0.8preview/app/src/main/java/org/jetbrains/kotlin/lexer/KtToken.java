package org.jetbrains.kotlin.lexer;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.kotlin.idea.KotlinLanguage;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public class KtToken extends IElementType {
    public final int tokenId;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "debugName", "org/jetbrains/kotlin/lexer/KtToken", "<init>"));
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KtToken(String str, int i) {
        super(str, KotlinLanguage.INSTANCE);
        if (str == null) {
            $$$reportNull$$$0(1);
        }
        this.tokenId = i;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Deprecated
    public KtToken(String str) {
        this(str, -1);
        if (str == null) {
            $$$reportNull$$$0(0);
        }
    }
}
