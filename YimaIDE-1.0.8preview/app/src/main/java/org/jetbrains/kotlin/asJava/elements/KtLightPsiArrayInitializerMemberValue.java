package org.jetbrains.kotlin.asJava.elements;

import com.intellij.psi.PsiAnnotationMemberValue;
import com.intellij.psi.PsiArrayInitializerMemberValue;
import com.intellij.psi.PsiElement;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.psi.KtElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B1\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0018\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b¢\u0006\u0004\b\u000b\u0010\fJ\u0013\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\u0010H\u0016¢\u0006\u0002\u0010\u0011J\b\u0010\u0012\u001a\u00020\u0006H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/asJava/elements/KtLightPsiArrayInitializerMemberValue;", "Lorg/jetbrains/kotlin/asJava/elements/KtLightElementBase;", "Lcom/intellij/psi/PsiArrayInitializerMemberValue;", "kotlinOrigin", "Lorg/jetbrains/kotlin/psi/KtElement;", "lightParent", "Lcom/intellij/psi/PsiElement;", "arguments", "Lkotlin/Function1;", "", "Lcom/intellij/psi/PsiAnnotationMemberValue;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/psi/KtElement;Lcom/intellij/psi/PsiElement;Lkotlin/jvm/functions/Function1;)V", "getKotlinOrigin", "()Lorg/jetbrains/kotlin/psi/KtElement;", "getInitializers", "", "()[Lcom/intellij/psi/PsiAnnotationMemberValue;", "getParent", "isPhysical", "", "org.jetbrains.kotlin:light-classes"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class KtLightPsiArrayInitializerMemberValue extends KtLightElementBase implements PsiArrayInitializerMemberValue {
    private final Function1<KtLightPsiArrayInitializerMemberValue, List<PsiAnnotationMemberValue>> arguments;
    private final KtElement kotlinOrigin;
    private final PsiElement lightParent;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public KtLightPsiArrayInitializerMemberValue(KtElement ktElement, PsiElement psiElement, Function1<? super KtLightPsiArrayInitializerMemberValue, ? extends List<? extends PsiAnnotationMemberValue>> function1) {
        super(psiElement);
        ktElement.getClass();
        psiElement.getClass();
        function1.getClass();
        this.kotlinOrigin = ktElement;
        this.lightParent = psiElement;
        this.arguments = function1;
    }

    public PsiAnnotationMemberValue[] getInitializers() {
        return (PsiAnnotationMemberValue[]) ((Collection) this.arguments.invoke(this)).toArray(new PsiAnnotationMemberValue[0]);
    }

    @Override // org.jetbrains.kotlin.asJava.elements.KtLightElementBase, org.jetbrains.kotlin.asJava.elements.KtLightElement
    public KtElement getKotlinOrigin() {
        return this.kotlinOrigin;
    }

    @Override // org.jetbrains.kotlin.asJava.elements.KtLightElementBase
    /* JADX INFO: renamed from: getParent, reason: from getter */
    public PsiElement getLightParent() {
        return this.lightParent;
    }

    public boolean isPhysical() {
        return false;
    }
}
