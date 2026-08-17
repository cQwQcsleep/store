package org.jetbrains.kotlin.resolve.lazy.data;

import com.intellij.psi.PsiElement;
import java.util.List;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.psi.KtAnnotationEntry;
import org.jetbrains.kotlin.psi.KtClassOrObject;
import org.jetbrains.kotlin.psi.KtDeclarationContainer;
import org.jetbrains.kotlin.psi.KtModifierList;
import org.jetbrains.kotlin.psi.KtObjectDeclaration;
import org.jetbrains.kotlin.psi.KtParameter;
import org.jetbrains.kotlin.psi.KtTypeParameterList;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public interface KtClassLikeInfo extends KtDeclarationContainer {
    ClassKind getClassKind();

    List<KtObjectDeclaration> getCompanionObjects();

    FqName getContainingPackageFqName();

    /* JADX INFO: renamed from: getCorrespondingClassOrObject */
    KtClassOrObject mo153getCorrespondingClassOrObject();

    List<KtAnnotationEntry> getDanglingAnnotations();

    /* JADX INFO: renamed from: getModifierList */
    KtModifierList mo154getModifierList();

    List<? extends KtParameter> getPrimaryConstructorParameters();

    PsiElement getScopeAnchor();

    /* JADX INFO: renamed from: getTypeParameterList */
    KtTypeParameterList mo156getTypeParameterList();
}
