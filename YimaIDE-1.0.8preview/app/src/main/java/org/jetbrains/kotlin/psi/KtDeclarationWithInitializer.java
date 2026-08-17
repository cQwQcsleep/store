package org.jetbrains.kotlin.psi;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public interface KtDeclarationWithInitializer extends KtDeclaration {
    KtExpression getInitializer();

    boolean hasInitializer();
}
