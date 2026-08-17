package org.jetbrains.kotlin.psi;

import com.intellij.psi.impl.source.tree.LeafPsiElement;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u0004\u0018\u00010\u0003H'b\u0002\b\u0004J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\n\u0010\u000b\u001a\u0004\u0018\u00010\fH&J\b\u0010\u000f\u001a\u00020\bH&R\u0014\u0010\r\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/psi/ValueArgument;", "", "getArgumentExpression", "Lorg/jetbrains/kotlin/psi/KtExpression;", "Lorg/jetbrains/kotlin/psi/IfNotParsed;", "getArgumentName", "Lorg/jetbrains/kotlin/psi/ValueArgumentName;", "isNamed", "", "asElement", "Lorg/jetbrains/kotlin/psi/KtElement;", "getSpreadElement", "Lcom/intellij/psi/impl/source/tree/LeafPsiElement;", "isSpread", "()Z", "isExternal", "org.jetbrains.kotlin:psi-api"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface ValueArgument {
    KtElement asElement();

    @IfNotParsed
    KtExpression getArgumentExpression();

    ValueArgumentName getArgumentName();

    LeafPsiElement getSpreadElement();

    boolean isExternal();

    boolean isNamed();

    default boolean isSpread() {
        return getSpreadElement() != null;
    }
}
