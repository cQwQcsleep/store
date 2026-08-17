package org.jetbrains.kotlin.asJava.elements;

import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiAnnotationOwner;
import com.intellij.psi.PsiAnnotationParameterList;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiJavaCodeReferenceElement;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.psi.KtCallElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\b&\u0018\u00002\u00020\u00012\u00020\u00022\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00020\u0003B\u000f\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\n\u0010\t\u001a\u0004\u0018\u00010\nH&J\n\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\b\u0010\r\u001a\u00020\u000eH&J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/asJava/elements/KtLightAbstractAnnotation;", "Lorg/jetbrains/kotlin/asJava/elements/KtLightElementBase;", "Lcom/intellij/psi/PsiAnnotation;", "Lorg/jetbrains/kotlin/asJava/elements/KtLightElement;", "Lorg/jetbrains/kotlin/psi/KtCallElement;", "parent", "Lcom/intellij/psi/PsiElement;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lcom/intellij/psi/PsiElement;)V", "getNameReferenceElement", "Lcom/intellij/psi/PsiJavaCodeReferenceElement;", "getOwner", "Lcom/intellij/psi/PsiAnnotationOwner;", "getParameterList", "Lcom/intellij/psi/PsiAnnotationParameterList;", "fqNameMatches", "", "fqName", "", "org.jetbrains.kotlin:light-classes-base"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class KtLightAbstractAnnotation extends KtLightElementBase implements PsiAnnotation, KtLightElement<KtCallElement, PsiAnnotation> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KtLightAbstractAnnotation(PsiElement psiElement) {
        super(psiElement);
        psiElement.getClass();
    }

    public boolean fqNameMatches(String fqName) {
        fqName.getClass();
        return Intrinsics.areEqual(getQualifiedName(), fqName);
    }

    public abstract PsiJavaCodeReferenceElement getNameReferenceElement();

    public PsiAnnotationOwner getOwner() {
        PsiAnnotationOwner lightParent = getLightParent();
        if (lightParent instanceof PsiAnnotationOwner) {
            return lightParent;
        }
        return null;
    }

    public abstract PsiAnnotationParameterList getParameterList();
}
