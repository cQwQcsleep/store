package org.jetbrains.kotlin.psi;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public interface KtPureClassOrObject extends KtDeclarationContainer, KtPureElement {
    KtClassBody getBody();

    List<KtObjectDeclaration> getCompanionObjects();

    List<KtContextReceiver> getContextReceivers();

    String getName();

    KtPrimaryConstructor getPrimaryConstructor();

    KtModifierList getPrimaryConstructorModifierList();

    List<KtParameter> getPrimaryConstructorParameters();

    List<KtSecondaryConstructor> getSecondaryConstructors();

    List<KtSuperTypeListEntry> getSuperTypeListEntries();

    boolean hasExplicitPrimaryConstructor();

    boolean hasPrimaryConstructor();

    boolean isLocal();
}
