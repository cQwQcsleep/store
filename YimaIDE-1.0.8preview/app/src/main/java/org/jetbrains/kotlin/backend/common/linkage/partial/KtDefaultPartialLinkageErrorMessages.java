package org.jetbrains.kotlin.backend.common.linkage.partial;

import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryToRendererMap;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryToRendererMapKt;
import org.jetbrains.kotlin.diagnostics.rendering.BaseDiagnosticRendererFactory;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/linkage/partial/KtDefaultPartialLinkageErrorMessages;", "Lorg/jetbrains/kotlin/diagnostics/rendering/BaseDiagnosticRendererFactory;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "MAP", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryToRendererMap;", "getMAP", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryToRendererMap;", "MAP$delegate", "Lkotlin/Lazy;", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
final class KtDefaultPartialLinkageErrorMessages extends BaseDiagnosticRendererFactory {
    public static final KtDefaultPartialLinkageErrorMessages INSTANCE = new KtDefaultPartialLinkageErrorMessages();

    /* JADX INFO: renamed from: MAP$delegate, reason: from kotlin metadata */
    private static final Lazy MAP = KtDiagnosticFactoryToRendererMapKt.KtDiagnosticFactoryToRendererMap("KT", new Function1() { // from class: org.jetbrains.kotlin.backend.common.linkage.partial.a
        public final Object invoke(Object obj) {
            return KtDefaultPartialLinkageErrorMessages.a((KtDiagnosticFactoryToRendererMap) obj);
        }
    });

    private KtDefaultPartialLinkageErrorMessages() {
    }

    public static Unit a(KtDiagnosticFactoryToRendererMap ktDiagnosticFactoryToRendererMap) {
        ktDiagnosticFactoryToRendererMap.getClass();
        PartialLinkageDiagnostics partialLinkageDiagnostics = PartialLinkageDiagnostics.INSTANCE;
        ktDiagnosticFactoryToRendererMap.put(partialLinkageDiagnostics.getMINOR_PARTIAL_LINKAGE_ISSUE(), "{0}");
        ktDiagnosticFactoryToRendererMap.put(partialLinkageDiagnostics.getMAJOR_PARTIAL_LINKAGE_ISSUE(), "{0}");
        return Unit.INSTANCE;
    }

    public KtDiagnosticFactoryToRendererMap getMAP() {
        return (KtDiagnosticFactoryToRendererMap) MAP.getValue();
    }
}
