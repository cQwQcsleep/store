package org.jetbrains.kotlin.fir.analysis.diagnostics;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.rendering.ContextIndependentParameterRenderer;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirDiagnosticRenderers$CALLABLES_FQ_NAMES$1;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u00020\u0001J\u001a\u0010\u0004\u001a\u00020\u00052\u0010\u0010\u0006\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u0002H\u0016¨\u0006\u0007"}, d2 = {"org/jetbrains/kotlin/fir/analysis/diagnostics/FirDiagnosticRenderers$CALLABLES_FQ_NAMES$1", "Lorg/jetbrains/kotlin/diagnostics/rendering/ContextIndependentParameterRenderer;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "render", Argument.Delimiters.none, "obj", "org.jetbrains.kotlin:diagnostic-renderers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDiagnosticRenderers$CALLABLES_FQ_NAMES$1 implements ContextIndependentParameterRenderer<Collection<? extends FirCallableSymbol<?>>> {
    public static CharSequence a(FirCallableSymbol firCallableSymbol) {
        firCallableSymbol.getClass();
        return FirPlatformIncompatibilityDiagnosticRendererKt.INDENTATION_UNIT + FirDiagnosticRenderers.INSTANCE.getCALLABLE_FQ_NAME().render(firCallableSymbol);
    }

    @Override // org.jetbrains.kotlin.diagnostics.rendering.ContextIndependentParameterRenderer
    public String render(Collection<? extends FirCallableSymbol<?>> obj) {
        obj.getClass();
        return "\n" + CollectionsKt.joinToString$default(obj, "\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: p45
            public final Object invoke(Object obj2) {
                return FirDiagnosticRenderers$CALLABLES_FQ_NAMES$1.a((FirCallableSymbol) obj2);
            }
        }, 30, (Object) null) + '\n';
    }
}
