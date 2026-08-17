package org.jetbrains.kotlin.backend.common.linkage.partial;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticsContainer;
import org.jetbrains.kotlin.diagnostics.KtSourcelessDiagnosticFactory;
import org.jetbrains.kotlin.diagnostics.Severity;
import org.jetbrains.kotlin.diagnostics.rendering.BaseDiagnosticRendererFactory;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/linkage/partial/PartialLinkageDiagnostics;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticsContainer;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "MINOR_PARTIAL_LINKAGE_ISSUE", "Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", "getMINOR_PARTIAL_LINKAGE_ISSUE", "()Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", "MAJOR_PARTIAL_LINKAGE_ISSUE", "getMAJOR_PARTIAL_LINKAGE_ISSUE", "getRendererFactory", "Lorg/jetbrains/kotlin/diagnostics/rendering/BaseDiagnosticRendererFactory;", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PartialLinkageDiagnostics extends KtDiagnosticsContainer {
    public static final PartialLinkageDiagnostics INSTANCE;
    private static final KtSourcelessDiagnosticFactory MAJOR_PARTIAL_LINKAGE_ISSUE;
    private static final KtSourcelessDiagnosticFactory MINOR_PARTIAL_LINKAGE_ISSUE;

    static {
        PartialLinkageDiagnostics partialLinkageDiagnostics = new PartialLinkageDiagnostics();
        INSTANCE = partialLinkageDiagnostics;
        MINOR_PARTIAL_LINKAGE_ISSUE = new KtSourcelessDiagnosticFactory("MINOR_PARTIAL_LINKAGE_ISSUE", Severity.INFO, partialLinkageDiagnostics.getRendererFactory());
        MAJOR_PARTIAL_LINKAGE_ISSUE = new KtSourcelessDiagnosticFactory("MAJOR_PARTIAL_LINKAGE_ISSUE", Severity.WARNING, partialLinkageDiagnostics.getRendererFactory());
    }

    private PartialLinkageDiagnostics() {
    }

    public final KtSourcelessDiagnosticFactory getMAJOR_PARTIAL_LINKAGE_ISSUE() {
        return MAJOR_PARTIAL_LINKAGE_ISSUE;
    }

    public final KtSourcelessDiagnosticFactory getMINOR_PARTIAL_LINKAGE_ISSUE() {
        return MINOR_PARTIAL_LINKAGE_ISSUE;
    }

    public BaseDiagnosticRendererFactory getRendererFactory() {
        return KtDefaultPartialLinkageErrorMessages.INSTANCE;
    }
}
