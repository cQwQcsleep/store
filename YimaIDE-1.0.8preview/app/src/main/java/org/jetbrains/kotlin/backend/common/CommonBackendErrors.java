package org.jetbrains.kotlin.backend.common;

import com.intellij.psi.PsiElement;
import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.diagnostics.DiagnosticFactory1DelegateProvider;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticsContainer;
import org.jetbrains.kotlin.diagnostics.Severity;
import org.jetbrains.kotlin.diagnostics.SourceElementPositioningStrategies;
import org.jetbrains.kotlin.diagnostics.SourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.rendering.BaseDiagnosticRendererFactory;
import org.jetbrains.kotlin.ir.declarations.IrFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u000f\u001a\u00020\u0010H\u0016R!\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR!\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\n\u001a\u0004\b\r\u0010\b¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/CommonBackendErrors;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticsContainer;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "EVALUATION_ERROR", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", "", "getEVALUATION_ERROR", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", "EVALUATION_ERROR$delegate", "Lkotlin/properties/ReadOnlyProperty;", "INLINE_CALL_CYCLE", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "getINLINE_CALL_CYCLE", "INLINE_CALL_CYCLE$delegate", "getRendererFactory", "Lorg/jetbrains/kotlin/diagnostics/rendering/BaseDiagnosticRendererFactory;", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class CommonBackendErrors extends KtDiagnosticsContainer {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;

    /* JADX INFO: renamed from: EVALUATION_ERROR$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty EVALUATION_ERROR;

    /* JADX INFO: renamed from: INLINE_CALL_CYCLE$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty INLINE_CALL_CYCLE;
    public static final CommonBackendErrors INSTANCE;

    static {
        KProperty<Object>[] kPropertyArr = {new PropertyReference1Impl<>(CommonBackendErrors.class, "EVALUATION_ERROR", "getEVALUATION_ERROR()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", 0), new PropertyReference1Impl<>(CommonBackendErrors.class, "INLINE_CALL_CYCLE", "getINLINE_CALL_CYCLE()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", 0)};
        $$delegatedProperties = kPropertyArr;
        CommonBackendErrors commonBackendErrors = new CommonBackendErrors();
        INSTANCE = commonBackendErrors;
        SourceElementPositioningStrategies sourceElementPositioningStrategies = SourceElementPositioningStrategies.INSTANCE;
        SourceElementPositioningStrategy sourceElementPositioningStrategy = sourceElementPositioningStrategies.getDEFAULT();
        Severity severity = Severity.ERROR;
        EVALUATION_ERROR = new DiagnosticFactory1DelegateProvider(severity, sourceElementPositioningStrategy, Reflection.getOrCreateKotlinClass(PsiElement.class), commonBackendErrors).provideDelegate(commonBackendErrors, kPropertyArr[0]);
        INLINE_CALL_CYCLE = new DiagnosticFactory1DelegateProvider(severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), commonBackendErrors).provideDelegate(commonBackendErrors, kPropertyArr[1]);
    }

    private CommonBackendErrors() {
    }

    public final KtDiagnosticFactory1<String> getEVALUATION_ERROR() {
        return (KtDiagnosticFactory1) EVALUATION_ERROR.getValue(this, $$delegatedProperties[0]);
    }

    public final KtDiagnosticFactory1<IrFunction> getINLINE_CALL_CYCLE() {
        return (KtDiagnosticFactory1) INLINE_CALL_CYCLE.getValue(this, $$delegatedProperties[1]);
    }

    public BaseDiagnosticRendererFactory getRendererFactory() {
        return KtDefaultCommonBackendErrorMessages.INSTANCE;
    }
}
