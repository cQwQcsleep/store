package org.jetbrains.kotlin.psi;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface KtTypeParameterListOwner extends KtNamedDeclaration {
    KtTypeConstraintList getTypeConstraintList();

    List<KtTypeConstraint> getTypeConstraints();

    KtTypeParameterList getTypeParameterList();

    List<KtTypeParameter> getTypeParameters();
}
