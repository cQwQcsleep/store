package org.jetbrains.kotlin.asJava.elements;

import com.intellij.psi.PsiAnnotationParameterList;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameValuePair;
import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.psi.KtElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0013\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0016¢\u0006\u0002\u0010\u000eR\u0016\u0010\u0007\u001a\u0004\u0018\u00010\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/asJava/elements/KtLightEmptyAnnotationParameterList;", "Lorg/jetbrains/kotlin/asJava/elements/KtLightElementBase;", "Lcom/intellij/psi/PsiAnnotationParameterList;", "parent", "Lcom/intellij/psi/PsiElement;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lcom/intellij/psi/PsiElement;)V", "kotlinOrigin", "Lorg/jetbrains/kotlin/psi/KtElement;", "getKotlinOrigin", "()Lorg/jetbrains/kotlin/psi/KtElement;", "getAttributes", "", "Lcom/intellij/psi/PsiNameValuePair;", "()[Lcom/intellij/psi/PsiNameValuePair;", "org.jetbrains.kotlin:light-classes"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class KtLightEmptyAnnotationParameterList extends KtLightElementBase implements PsiAnnotationParameterList {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KtLightEmptyAnnotationParameterList(PsiElement psiElement) {
        super(psiElement);
        psiElement.getClass();
    }

    public PsiNameValuePair[] getAttributes() {
        return new PsiNameValuePair[0];
    }

    @Override // org.jetbrains.kotlin.asJava.elements.KtLightElementBase, org.jetbrains.kotlin.asJava.elements.KtLightElement
    /* JADX INFO: renamed from: getKotlinOrigin */
    public KtElement mo25getKotlinOrigin() {
        return null;
    }
}
