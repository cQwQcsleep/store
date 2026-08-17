package org.jetbrains.kotlin.asJava.builder;

import com.intellij.psi.PsiElement;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.psi.KtDeclaration;
import org.jetbrains.kotlin.psi.KtParameter;
import org.jetbrains.kotlin.resolve.jvm.diagnostics.JvmDeclarationOriginKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0011\u001a\u00020\u0012H&J\u0012\u0010\u0013\u001a\u00020\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\u0000H&J\u0012\u0010\u0013\u001a\u00020\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H&J\b\u0010\u0016\u001a\u00020\u0000H&R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u001e\u0010\n\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\f\u0018\u00010\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u000f\u001a\u0004\u0018\u00010\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0017À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/asJava/builder/LightMemberOrigin;", "", "originalElement", "Lorg/jetbrains/kotlin/psi/KtDeclaration;", "getOriginalElement", "()Lorg/jetbrains/kotlin/psi/KtDeclaration;", "originKind", "Lorg/jetbrains/kotlin/resolve/jvm/diagnostics/JvmDeclarationOriginKind;", "getOriginKind", "()Lorg/jetbrains/kotlin/resolve/jvm/diagnostics/JvmDeclarationOriginKind;", "parametersForJvmOverloads", "", "Lorg/jetbrains/kotlin/psi/KtParameter;", "getParametersForJvmOverloads", "()Ljava/util/List;", "auxiliaryOriginalElement", "getAuxiliaryOriginalElement", "isValid", "", "isEquivalentTo", "other", "Lcom/intellij/psi/PsiElement;", "copy", "org.jetbrains.kotlin:light-classes-base"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface LightMemberOrigin {
    LightMemberOrigin copy();

    default KtDeclaration getAuxiliaryOriginalElement() {
        return null;
    }

    JvmDeclarationOriginKind getOriginKind();

    KtDeclaration getOriginalElement();

    default List<KtParameter> getParametersForJvmOverloads() {
        return null;
    }

    boolean isEquivalentTo(PsiElement other);

    boolean isEquivalentTo(LightMemberOrigin other);

    boolean isValid();
}
