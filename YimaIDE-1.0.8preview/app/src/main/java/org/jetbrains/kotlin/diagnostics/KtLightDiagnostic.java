package org.jetbrains.kotlin.diagnostics;

import com.intellij.psi.PsiElement;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import org.jetbrains.kotlin.KtLightSourceElement;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R4\u0010\u0006\u001a\u00020\u00078VX\u0097\u0004r\u0018\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\n\b\u000f\u0012\u0006\b\n0\u00108\u0011¢\u0006\f\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0012À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/KtLightDiagnostic;", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticMarker;", "element", "Lorg/jetbrains/kotlin/KtLightSourceElement;", "getElement", "()Lorg/jetbrains/kotlin/KtLightSourceElement;", "psiElement", "Lcom/intellij/psi/PsiElement;", "getPsiElement$annotations", "()V", "getPsiElement", "()Lcom/intellij/psi/PsiElement;", "Lkotlin/Deprecated;", "message", "Should not be called", "level", "Lkotlin/DeprecationLevel;", "HIDDEN", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface KtLightDiagnostic extends DiagnosticMarker {
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Should not be called")
    static /* synthetic */ void getPsiElement$annotations() {
    }

    /* JADX INFO: renamed from: getElement */
    KtLightSourceElement mo191getElement();

    @Override // org.jetbrains.kotlin.diagnostics.DiagnosticMarker
    /* synthetic */ default PsiElement getPsiElement() {
        throw new IllegalStateException("psiElement should not be called on KtLightDiagnostic");
    }
}
