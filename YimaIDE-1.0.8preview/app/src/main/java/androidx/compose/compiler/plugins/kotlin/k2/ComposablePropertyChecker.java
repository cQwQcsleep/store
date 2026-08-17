package androidx.compose.compiler.plugins.kotlin.k2;

import kotlin.Metadata;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/k2/ComposablePropertyChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirPropertyChecker;", "<init>", "()V", "check", "", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)V", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ComposablePropertyChecker extends FirDeclarationChecker<FirProperty> {
    public static final ComposablePropertyChecker INSTANCE = new ComposablePropertyChecker();

    private ComposablePropertyChecker() {
        super(MppCheckerKind.Common);
    }

    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirProperty firProperty) {
        FirPropertyAccessor setter;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firProperty.getClass();
        FirPropertyAccessor getter = firProperty.getGetter();
        if ((getter == null || !FirUtilsKt.hasComposableAnnotation(getter, checkerContext.getSession())) && ((setter = firProperty.getSetter()) == null || !FirUtilsKt.hasComposableAnnotation(setter, checkerContext.getSession()))) {
            return;
        }
        if (firProperty.isVar()) {
            KtDiagnosticReportHelpersKt.reportOn$default(checkerContext, diagnosticReporter, firProperty.getSource(), ComposeErrors.INSTANCE.getCOMPOSABLE_VAR(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
        if (DeclarationAttributesKt.getHasBackingField(firProperty)) {
            KtDiagnosticReportHelpersKt.reportOn$default(checkerContext, diagnosticReporter, firProperty.getSource(), ComposeErrors.INSTANCE.getCOMPOSABLE_PROPERTY_BACKING_FIELD(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
    }
}
