package org.jetbrains.kotlin.asJava.builder;

import com.intellij.psi.PsiElement;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.psi.KtDeclaration;
import org.jetbrains.kotlin.psi.KtParameter;
import org.jetbrains.kotlin.resolve.jvm.diagnostics.JvmDeclarationOriginKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\b\u0002\u0010\u0006\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\n\u0010\u000bJ\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0012\u0010\u0015\u001a\u00020\u00142\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u0016J\u0012\u0010\u0015\u001a\u00020\u00142\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0001H\u0016J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\u0013\u0010\u001b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J=\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0012\b\u0002\u0010\u0006\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u001d\u001a\u00020\u00142\b\u0010\u0016\u001a\u0004\u0018\u00010\u001eHÖ\u0083\u0004J\n\u0010\u001f\u001a\u00020 HÖ\u0081\u0004J\n\u0010!\u001a\u00020\"HÖ\u0081\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0006\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\t\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\r¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/asJava/builder/LightMemberOriginForDeclaration;", "Lorg/jetbrains/kotlin/asJava/builder/LightMemberOrigin;", "originalElement", "Lorg/jetbrains/kotlin/psi/KtDeclaration;", "originKind", "Lorg/jetbrains/kotlin/resolve/jvm/diagnostics/JvmDeclarationOriginKind;", "parametersForJvmOverloads", "", "Lorg/jetbrains/kotlin/psi/KtParameter;", "auxiliaryOriginalElement", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/psi/KtDeclaration;Lorg/jetbrains/kotlin/resolve/jvm/diagnostics/JvmDeclarationOriginKind;Ljava/util/List;Lorg/jetbrains/kotlin/psi/KtDeclaration;)V", "getOriginalElement", "()Lorg/jetbrains/kotlin/psi/KtDeclaration;", "getOriginKind", "()Lorg/jetbrains/kotlin/resolve/jvm/diagnostics/JvmDeclarationOriginKind;", "getParametersForJvmOverloads", "()Ljava/util/List;", "getAuxiliaryOriginalElement", "isValid", "", "isEquivalentTo", "other", "Lcom/intellij/psi/PsiElement;", "copy", "component1", "component2", "component3", "component4", "equals", "", "hashCode", "", "toString", "", "org.jetbrains.kotlin:light-classes-base"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class LightMemberOriginForDeclaration implements LightMemberOrigin {
    private final KtDeclaration auxiliaryOriginalElement;
    private final JvmDeclarationOriginKind originKind;
    private final KtDeclaration originalElement;
    private final List<KtParameter> parametersForJvmOverloads;

    public LightMemberOriginForDeclaration(KtDeclaration ktDeclaration, JvmDeclarationOriginKind jvmDeclarationOriginKind, List<? extends KtParameter> list, KtDeclaration ktDeclaration2) {
        ktDeclaration.getClass();
        jvmDeclarationOriginKind.getClass();
        this.originalElement = ktDeclaration;
        this.originKind = jvmDeclarationOriginKind;
        this.parametersForJvmOverloads = list;
        this.auxiliaryOriginalElement = ktDeclaration2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ LightMemberOriginForDeclaration copy$default(LightMemberOriginForDeclaration lightMemberOriginForDeclaration, KtDeclaration ktDeclaration, JvmDeclarationOriginKind jvmDeclarationOriginKind, List list, KtDeclaration ktDeclaration2, int i, Object obj) {
        if ((i & 1) != 0) {
            ktDeclaration = lightMemberOriginForDeclaration.originalElement;
        }
        if ((i & 2) != 0) {
            jvmDeclarationOriginKind = lightMemberOriginForDeclaration.originKind;
        }
        if ((i & 4) != 0) {
            list = lightMemberOriginForDeclaration.parametersForJvmOverloads;
        }
        if ((i & 8) != 0) {
            ktDeclaration2 = lightMemberOriginForDeclaration.auxiliaryOriginalElement;
        }
        return lightMemberOriginForDeclaration.copy(ktDeclaration, jvmDeclarationOriginKind, list, ktDeclaration2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final KtDeclaration getOriginalElement() {
        return this.originalElement;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final JvmDeclarationOriginKind getOriginKind() {
        return this.originKind;
    }

    public final List<KtParameter> component3() {
        return this.parametersForJvmOverloads;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final KtDeclaration getAuxiliaryOriginalElement() {
        return this.auxiliaryOriginalElement;
    }

    @Override // org.jetbrains.kotlin.asJava.builder.LightMemberOrigin
    public LightMemberOrigin copy() {
        KtDeclaration ktDeclarationCopy = getOriginalElement().copy();
        ktDeclarationCopy.getClass();
        return new LightMemberOriginForDeclaration(ktDeclarationCopy, getOriginKind(), getParametersForJvmOverloads(), null, 8, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LightMemberOriginForDeclaration)) {
            return false;
        }
        LightMemberOriginForDeclaration lightMemberOriginForDeclaration = (LightMemberOriginForDeclaration) other;
        return Intrinsics.areEqual(this.originalElement, lightMemberOriginForDeclaration.originalElement) && this.originKind == lightMemberOriginForDeclaration.originKind && Intrinsics.areEqual(this.parametersForJvmOverloads, lightMemberOriginForDeclaration.parametersForJvmOverloads) && Intrinsics.areEqual(this.auxiliaryOriginalElement, lightMemberOriginForDeclaration.auxiliaryOriginalElement);
    }

    @Override // org.jetbrains.kotlin.asJava.builder.LightMemberOrigin
    public KtDeclaration getAuxiliaryOriginalElement() {
        return this.auxiliaryOriginalElement;
    }

    @Override // org.jetbrains.kotlin.asJava.builder.LightMemberOrigin
    public JvmDeclarationOriginKind getOriginKind() {
        return this.originKind;
    }

    @Override // org.jetbrains.kotlin.asJava.builder.LightMemberOrigin
    public KtDeclaration getOriginalElement() {
        return this.originalElement;
    }

    @Override // org.jetbrains.kotlin.asJava.builder.LightMemberOrigin
    public List<KtParameter> getParametersForJvmOverloads() {
        return this.parametersForJvmOverloads;
    }

    public int hashCode() {
        int iHashCode = ((this.originalElement.hashCode() * 31) + this.originKind.hashCode()) * 31;
        List<KtParameter> list = this.parametersForJvmOverloads;
        int iHashCode2 = (iHashCode + (list == null ? 0 : list.hashCode())) * 31;
        KtDeclaration ktDeclaration = this.auxiliaryOriginalElement;
        return iHashCode2 + (ktDeclaration != null ? ktDeclaration.hashCode() : 0);
    }

    @Override // org.jetbrains.kotlin.asJava.builder.LightMemberOrigin
    public boolean isEquivalentTo(LightMemberOrigin other) {
        if (other instanceof LightMemberOriginForDeclaration) {
            return isEquivalentTo((PsiElement) ((LightMemberOriginForDeclaration) other).getOriginalElement());
        }
        return false;
    }

    @Override // org.jetbrains.kotlin.asJava.builder.LightMemberOrigin
    public boolean isValid() {
        return getOriginalElement().isValid();
    }

    public String toString() {
        return "LightMemberOriginForDeclaration(originalElement=" + this.originalElement + ", originKind=" + this.originKind + ", parametersForJvmOverloads=" + this.parametersForJvmOverloads + ", auxiliaryOriginalElement=" + this.auxiliaryOriginalElement + Util.C_PARAM_END;
    }

    @Override // org.jetbrains.kotlin.asJava.builder.LightMemberOrigin
    public boolean isEquivalentTo(PsiElement other) {
        return getOriginalElement().isEquivalentTo(other);
    }

    public /* synthetic */ LightMemberOriginForDeclaration(KtDeclaration ktDeclaration, JvmDeclarationOriginKind jvmDeclarationOriginKind, List list, KtDeclaration ktDeclaration2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(ktDeclaration, jvmDeclarationOriginKind, (i & 4) != 0 ? null : list, (i & 8) != 0 ? null : ktDeclaration2);
    }

    public final LightMemberOriginForDeclaration copy(KtDeclaration originalElement, JvmDeclarationOriginKind originKind, List<? extends KtParameter> parametersForJvmOverloads, KtDeclaration auxiliaryOriginalElement) {
        originalElement.getClass();
        originKind.getClass();
        return new LightMemberOriginForDeclaration(originalElement, originKind, parametersForJvmOverloads, auxiliaryOriginalElement);
    }
}
