package org.jetbrains.kotlin.diagnostics;

import com.intellij.psi.PsiElement;
import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.rendering.BaseDiagnosticRendererFactory;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0016R\u001b\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/BackendErrors;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticsContainer;", "<init>", "()V", "NON_LOCAL_RETURN_IN_DISABLED_INLINE", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", "getNON_LOCAL_RETURN_IN_DISABLED_INLINE", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", "NON_LOCAL_RETURN_IN_DISABLED_INLINE$delegate", "Lkotlin/properties/ReadOnlyProperty;", "getRendererFactory", "Lorg/jetbrains/kotlin/diagnostics/rendering/BaseDiagnosticRendererFactory;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class BackendErrors extends KtDiagnosticsContainer {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    public static final BackendErrors INSTANCE;

    /* JADX INFO: renamed from: NON_LOCAL_RETURN_IN_DISABLED_INLINE$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty NON_LOCAL_RETURN_IN_DISABLED_INLINE;

    static {
        KProperty<?>[] kPropertyArr = {new PropertyReference1Impl<>(BackendErrors.class, "NON_LOCAL_RETURN_IN_DISABLED_INLINE", "getNON_LOCAL_RETURN_IN_DISABLED_INLINE()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", 0)};
        $$delegatedProperties = kPropertyArr;
        BackendErrors backendErrors = new BackendErrors();
        INSTANCE = backendErrors;
        NON_LOCAL_RETURN_IN_DISABLED_INLINE = new DiagnosticFactory0DelegateProvider(Severity.ERROR, SourceElementPositioningStrategies.INSTANCE.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), backendErrors).provideDelegate(backendErrors, kPropertyArr[0]);
    }

    private BackendErrors() {
    }

    public final KtDiagnosticFactory0 getNON_LOCAL_RETURN_IN_DISABLED_INLINE() {
        return (KtDiagnosticFactory0) NON_LOCAL_RETURN_IN_DISABLED_INLINE.getValue(this, $$delegatedProperties[0]);
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnosticsContainer
    public BaseDiagnosticRendererFactory getRendererFactory() {
        return BackendErrorMessages.INSTANCE;
    }
}
