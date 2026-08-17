package org.jetbrains.kotlin.asJava.elements;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiTypeParameterListOwner;
import com.intellij.psi.ResolveState;
import com.intellij.psi.impl.light.LightTypeParameterListBuilder;
import com.intellij.psi.scope.PsiScopeProcessor;
import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.idea.KotlinLanguage;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J*\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016J\b\u0010\u000f\u001a\u00020\rH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\n\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/asJava/elements/KotlinLightTypeParameterListBuilder;", "Lcom/intellij/psi/impl/light/LightTypeParameterListBuilder;", "owner", "Lcom/intellij/psi/PsiTypeParameterListOwner;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lcom/intellij/psi/PsiTypeParameterListOwner;)V", "processDeclarations", "", "processor", "Lcom/intellij/psi/scope/PsiScopeProcessor;", "state", "Lcom/intellij/psi/ResolveState;", "lastParent", "Lcom/intellij/psi/PsiElement;", "place", "getParent", "getContainingFile", "Lcom/intellij/psi/PsiFile;", "getText", "", "org.jetbrains.kotlin:light-classes"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class KotlinLightTypeParameterListBuilder extends LightTypeParameterListBuilder {
    private final PsiTypeParameterListOwner owner;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KotlinLightTypeParameterListBuilder(PsiTypeParameterListOwner psiTypeParameterListOwner) {
        super(psiTypeParameterListOwner.getManager(), KotlinLanguage.INSTANCE);
        psiTypeParameterListOwner.getClass();
        this.owner = psiTypeParameterListOwner;
    }

    public PsiFile getContainingFile() {
        PsiFile containingFile = this.owner.getContainingFile();
        containingFile.getClass();
        return containingFile;
    }

    public PsiElement getParent() {
        return this.owner;
    }

    public String getText() {
        return "";
    }

    public boolean processDeclarations(PsiScopeProcessor processor, ResolveState state, PsiElement lastParent, PsiElement place) {
        processor.getClass();
        state.getClass();
        place.getClass();
        PsiElement[] typeParameters = getTypeParameters();
        typeParameters.getClass();
        for (PsiElement psiElement : typeParameters) {
            if (!processor.execute(psiElement, state)) {
                return false;
            }
        }
        return true;
    }
}
