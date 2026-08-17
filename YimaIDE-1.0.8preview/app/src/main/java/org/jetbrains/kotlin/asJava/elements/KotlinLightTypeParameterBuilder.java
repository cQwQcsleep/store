package org.jetbrains.kotlin.asJava.elements;

import com.intellij.psi.PsiTypeParameterListOwner;
import com.intellij.psi.impl.light.LightTypeParameterBuilder;
import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.psi.KtTypeParameter;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B'\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0004\b\u000b\u0010\fR\u0014\u0010\n\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/asJava/elements/KotlinLightTypeParameterBuilder;", "Lcom/intellij/psi/impl/light/LightTypeParameterBuilder;", "Lorg/jetbrains/kotlin/asJava/elements/PsiElementWithOrigin;", "Lorg/jetbrains/kotlin/psi/KtTypeParameter;", "name", "", "owner", "Lcom/intellij/psi/PsiTypeParameterListOwner;", "index", "", "origin", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;Lcom/intellij/psi/PsiTypeParameterListOwner;ILorg/jetbrains/kotlin/psi/KtTypeParameter;)V", "getOrigin", "()Lorg/jetbrains/kotlin/psi/KtTypeParameter;", "org.jetbrains.kotlin:light-classes"}, k = 1, mv = {2, 4, 0}, xi = 48)
public class KotlinLightTypeParameterBuilder extends LightTypeParameterBuilder implements PsiElementWithOrigin<KtTypeParameter> {
    private final KtTypeParameter origin;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KotlinLightTypeParameterBuilder(String str, PsiTypeParameterListOwner psiTypeParameterListOwner, int i, KtTypeParameter ktTypeParameter) {
        super(str, psiTypeParameterListOwner, i);
        str.getClass();
        psiTypeParameterListOwner.getClass();
        ktTypeParameter.getClass();
        this.origin = ktTypeParameter;
    }

    @Override // org.jetbrains.kotlin.asJava.elements.PsiElementWithOrigin
    public KtTypeParameter getOrigin() {
        return this.origin;
    }
}
