package org.jetbrains.kotlin.asJava.classes;

import com.intellij.psi.PsiModifierListOwner;
import com.intellij.psi.PsiType;
import kotlin.Metadata;
import org.jetbrains.kotlin.asJava.elements.KtLightDeclaration;
import org.jetbrains.kotlin.psi.KtDeclaration;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u0002*\n\b\u0001\u0010\u0003 \u0001*\u00020\u00042\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u00052\u00020\u0004R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u0004\u0018\u00010\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000eÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/asJava/classes/KtUltraLightElementWithNullabilityAnnotation;", "T", "Lorg/jetbrains/kotlin/psi/KtDeclaration;", "D", "Lcom/intellij/psi/PsiModifierListOwner;", "Lorg/jetbrains/kotlin/asJava/elements/KtLightDeclaration;", "qualifiedNameForNullabilityAnnotation", "", "getQualifiedNameForNullabilityAnnotation", "()Ljava/lang/String;", "psiTypeForNullabilityAnnotation", "Lcom/intellij/psi/PsiType;", "getPsiTypeForNullabilityAnnotation", "()Lcom/intellij/psi/PsiType;", "org.jetbrains.kotlin:light-classes"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface KtUltraLightElementWithNullabilityAnnotation<T extends KtDeclaration, D extends PsiModifierListOwner> extends PsiModifierListOwner, KtLightDeclaration<T, D> {
    PsiType getPsiTypeForNullabilityAnnotation();

    String getQualifiedNameForNullabilityAnnotation();
}
