package org.jetbrains.kotlin.asJava.classes;

import com.intellij.psi.PsiAnnotationMemberValue;
import com.intellij.psi.PsiArrayInitializerMemberValue;
import com.intellij.psi.PsiElement;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.asJava.elements.KtLightElementBase;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.psi.KtElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B)\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006¢\u0006\u0004\b\t\u0010\nJ\u0013\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u0012H\u0016¢\u0006\u0002\u0010\u0013J\b\u0010\u0014\u001a\u00020\u0004H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR \u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\u0004\u0018\u00010\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/asJava/classes/KtUltraLightPsiArrayInitializerMemberValue;", "Lorg/jetbrains/kotlin/asJava/elements/KtLightElementBase;", "Lcom/intellij/psi/PsiArrayInitializerMemberValue;", "lightParent", "Lcom/intellij/psi/PsiElement;", "arguments", "Lkotlin/Function1;", "", "Lcom/intellij/psi/PsiAnnotationMemberValue;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lcom/intellij/psi/PsiElement;Lkotlin/jvm/functions/Function1;)V", "getLightParent", "()Lcom/intellij/psi/PsiElement;", "kotlinOrigin", "Lorg/jetbrains/kotlin/psi/KtElement;", "getKotlinOrigin", "()Lorg/jetbrains/kotlin/psi/KtElement;", "getInitializers", "", "()[Lcom/intellij/psi/PsiAnnotationMemberValue;", "getParent", "isPhysical", "", "getText", "", "org.jetbrains.kotlin:light-classes"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class KtUltraLightPsiArrayInitializerMemberValue extends KtLightElementBase implements PsiArrayInitializerMemberValue {
    private final Function1<KtUltraLightPsiArrayInitializerMemberValue, List<PsiAnnotationMemberValue>> arguments;
    private final PsiElement lightParent;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public KtUltraLightPsiArrayInitializerMemberValue(PsiElement psiElement, Function1<? super KtUltraLightPsiArrayInitializerMemberValue, ? extends List<? extends PsiAnnotationMemberValue>> function1) {
        super(psiElement);
        psiElement.getClass();
        function1.getClass();
        this.lightParent = psiElement;
        this.arguments = function1;
    }

    public static CharSequence Cd(PsiAnnotationMemberValue psiAnnotationMemberValue) {
        psiAnnotationMemberValue.getClass();
        String text = psiAnnotationMemberValue.getText();
        text.getClass();
        return text;
    }

    public PsiAnnotationMemberValue[] getInitializers() {
        return (PsiAnnotationMemberValue[]) ((Collection) this.arguments.invoke(this)).toArray(new PsiAnnotationMemberValue[0]);
    }

    @Override // org.jetbrains.kotlin.asJava.elements.KtLightElementBase, org.jetbrains.kotlin.asJava.elements.KtLightElement
    /* JADX INFO: renamed from: getKotlinOrigin */
    public KtElement mo25getKotlinOrigin() {
        return null;
    }

    public final PsiElement getLightParent() {
        return this.lightParent;
    }

    @Override // org.jetbrains.kotlin.asJava.elements.KtLightElementBase
    public PsiElement getParent() {
        return this.lightParent;
    }

    @Override // org.jetbrains.kotlin.asJava.elements.KtLightElementBase
    public String getText() {
        return "{" + ArraysKt.joinToString$default(getInitializers(), (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: org.jetbrains.kotlin.asJava.classes.c
            public final Object invoke(Object obj) {
                return KtUltraLightPsiArrayInitializerMemberValue.Cd((PsiAnnotationMemberValue) obj);
            }
        }, 31, (Object) null) + '}';
    }

    public boolean isPhysical() {
        return false;
    }
}
