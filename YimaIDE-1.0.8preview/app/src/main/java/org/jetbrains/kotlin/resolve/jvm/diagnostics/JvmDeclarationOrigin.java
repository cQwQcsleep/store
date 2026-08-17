package org.jetbrains.kotlin.resolve.jvm.diagnostics;

import com.intellij.psi.PsiElement;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\n\u0010\u0017\u001a\u00020\u0018H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0010\u001a\u0004\u0018\u00010\u00018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/resolve/jvm/diagnostics/JvmDeclarationOrigin;", "", "originKind", "Lorg/jetbrains/kotlin/resolve/jvm/diagnostics/JvmDeclarationOriginKind;", CapturedVarsOptimizationMethodTransformerKt.REF_ELEMENT_FIELD, "Lcom/intellij/psi/PsiElement;", "descriptor", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/resolve/jvm/diagnostics/JvmDeclarationOriginKind;Lcom/intellij/psi/PsiElement;Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;)V", "getOriginKind", "()Lorg/jetbrains/kotlin/resolve/jvm/diagnostics/JvmDeclarationOriginKind;", "getElement", "()Lcom/intellij/psi/PsiElement;", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "originalSourceElement", "getOriginalSourceElement", "()Ljava/lang/Object;", "generatedForCompilerPlugin", "", "getGeneratedForCompilerPlugin", "()Z", "toString", "", "Companion", "org.jetbrains.kotlin:backend"}, k = 1, mv = {2, 4, 0}, xi = 48)
public class JvmDeclarationOrigin {
    public static final JvmDeclarationOrigin NO_ORIGIN = new JvmDeclarationOrigin(JvmDeclarationOriginKind.OTHER, null, null);
    public static final JvmDeclarationOrigin NO_ORIGIN_SUSPEND_FOR_INLINE = new JvmDeclarationOrigin(JvmDeclarationOriginKind.INLINE_VERSION_OF_SUSPEND_FUN, null, null);
    private final DeclarationDescriptor descriptor;
    private final PsiElement element;
    private final JvmDeclarationOriginKind originKind;

    public JvmDeclarationOrigin(JvmDeclarationOriginKind jvmDeclarationOriginKind, PsiElement psiElement, DeclarationDescriptor declarationDescriptor) {
        jvmDeclarationOriginKind.getClass();
        this.originKind = jvmDeclarationOriginKind;
        this.element = psiElement;
        this.descriptor = declarationDescriptor;
    }

    public final DeclarationDescriptor getDescriptor() {
        return this.descriptor;
    }

    public final PsiElement getElement() {
        return this.element;
    }

    public boolean getGeneratedForCompilerPlugin() {
        return false;
    }

    public final JvmDeclarationOriginKind getOriginKind() {
        return this.originKind;
    }

    public Object getOriginalSourceElement() {
        return this.element;
    }

    public String toString() {
        if (Intrinsics.areEqual(this, NO_ORIGIN)) {
            return "NO_ORIGIN";
        }
        StringBuilder sb = new StringBuilder("origin=");
        sb.append(this.originKind);
        sb.append(" element=");
        PsiElement psiElement = this.element;
        sb.append(psiElement != null ? psiElement.getClass().getSimpleName() : null);
        sb.append(" descriptor=");
        sb.append(this.descriptor);
        return sb.toString();
    }
}
