package org.jetbrains.kotlin.fir.analysis.diagnostics;

import java.util.Collection;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.rendering.ContextIndependentParameterRenderer;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.resolve.multiplatform.ExpectActualMatchingCompatibility;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 \r2 \u0012\u001c\u0012\u001a\u0012\u0006\b\u0001\u0012\u00020\u0003\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u00040\u00020\u0001:\u0001\rB\u000f\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ(\u0010\n\u001a\u00020\u000b2\u001e\u0010\f\u001a\u001a\u0012\u0006\b\u0001\u0012\u00020\u0003\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u00040\u0002H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/diagnostics/FirPlatformIncompatibilityDiagnosticRenderer;", "Lorg/jetbrains/kotlin/diagnostics/rendering/ContextIndependentParameterRenderer;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "mode", "Lorg/jetbrains/kotlin/fir/analysis/diagnostics/MultiplatformDiagnosticRenderingMode;", "<init>", "(Lorg/jetbrains/kotlin/fir/analysis/diagnostics/MultiplatformDiagnosticRenderingMode;)V", "render", Argument.Delimiters.none, "obj", "Companion", "org.jetbrains.kotlin:diagnostic-renderers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirPlatformIncompatibilityDiagnosticRenderer implements ContextIndependentParameterRenderer<Map<? extends ExpectActualMatchingCompatibility, ? extends Collection<? extends FirBasedSymbol<?>>>> {
    public static final FirPlatformIncompatibilityDiagnosticRenderer TEXT = new FirPlatformIncompatibilityDiagnosticRenderer(new MultiplatformDiagnosticRenderingMode());
    private final MultiplatformDiagnosticRenderingMode mode;

    public FirPlatformIncompatibilityDiagnosticRenderer(MultiplatformDiagnosticRenderingMode multiplatformDiagnosticRenderingMode) {
        multiplatformDiagnosticRenderingMode.getClass();
        this.mode = multiplatformDiagnosticRenderingMode;
    }

    @Override // org.jetbrains.kotlin.diagnostics.rendering.ContextIndependentParameterRenderer
    public String render(Map<? extends ExpectActualMatchingCompatibility, ? extends Collection<? extends FirBasedSymbol<?>>> obj) {
        obj.getClass();
        if (obj.isEmpty()) {
            return Argument.Delimiters.none;
        }
        StringBuilder sb = new StringBuilder();
        this.mode.newLine(sb);
        FirPlatformIncompatibilityDiagnosticRendererKt.renderIncompatibilityInformation(sb, obj, Argument.Delimiters.none, this.mode);
        return sb.toString();
    }
}
