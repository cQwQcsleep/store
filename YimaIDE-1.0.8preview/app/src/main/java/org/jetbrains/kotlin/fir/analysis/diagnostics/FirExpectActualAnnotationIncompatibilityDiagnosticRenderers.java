package org.jetbrains.kotlin.fir.analysis.diagnostics;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.rendering.ContextIndependentParameterRenderer;
import org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRendererKt;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirExpectActualAnnotationIncompatibilityDiagnosticRenderers;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.renderer.ConeIdShortRenderer;
import org.jetbrains.kotlin.fir.renderer.ConeTypeRenderer;
import org.jetbrains.kotlin.fir.renderer.ConeTypeRendererForReadability;
import org.jetbrains.kotlin.fir.renderer.FirCallableSignatureRendererForReadability;
import org.jetbrains.kotlin.fir.renderer.FirGetClassCallRendererForReadability;
import org.jetbrains.kotlin.fir.renderer.FirIdRendererBasedSymbolRenderer;
import org.jetbrains.kotlin.fir.renderer.FirRenderer;
import org.jetbrains.kotlin.fir.renderer.FirResolvedNamedReferenceRenderer;
import org.jetbrains.kotlin.fir.renderer.FirResolvedQualifierRenderer;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.resolve.multiplatform.ExpectActualAnnotationsIncompatibilityType;
import org.jetbrains.kotlin.utils.Printer;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\fH\u0002R!\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¢\u0006\u000e\n\u0000\u0012\u0004\b\u0007\u0010\u0003\u001a\u0004\b\b\u0010\tR\u001d\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\t¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/diagnostics/FirExpectActualAnnotationIncompatibilityDiagnosticRenderers;", Argument.Delimiters.none, "<init>", "()V", "SYMBOL_RENDERER", "Lorg/jetbrains/kotlin/diagnostics/rendering/ContextIndependentParameterRenderer;", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "getSYMBOL_RENDERER$annotations", "getSYMBOL_RENDERER", "()Lorg/jetbrains/kotlin/diagnostics/rendering/ContextIndependentParameterRenderer;", "INCOMPATIBILITY", "Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualAnnotationsIncompatibilityType;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getINCOMPATIBILITY", "renderAnnotation", Argument.Delimiters.none, "ann", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirExpectActualAnnotationIncompatibilityDiagnosticRenderers {
    public static final FirExpectActualAnnotationIncompatibilityDiagnosticRenderers INSTANCE = new FirExpectActualAnnotationIncompatibilityDiagnosticRenderers();
    private static final ContextIndependentParameterRenderer<FirBasedSymbol<?>> SYMBOL_RENDERER = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: b65
        public final Object invoke(Object obj) {
            return FirExpectActualAnnotationIncompatibilityDiagnosticRenderers.b((FirBasedSymbol) obj);
        }
    });
    private static final ContextIndependentParameterRenderer<ExpectActualAnnotationsIncompatibilityType<? extends FirAnnotation>> INCOMPATIBILITY = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: c65
        public final Object invoke(Object obj) {
            return FirExpectActualAnnotationIncompatibilityDiagnosticRenderers.c((ExpectActualAnnotationsIncompatibilityType) obj);
        }
    });

    private FirExpectActualAnnotationIncompatibilityDiagnosticRenderers() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ConeIdShortRenderer SYMBOL_RENDERER$lambda$0$0() {
        return new ConeIdShortRenderer();
    }

    public static String b(FirBasedSymbol firBasedSymbol) {
        firBasedSymbol.getClass();
        Function0 function0 = new Function0() { // from class: d65
            public final Object invoke() {
                return FirExpectActualAnnotationIncompatibilityDiagnosticRenderers.SYMBOL_RENDERER$lambda$0$0();
            }
        };
        return StringsKt.replace$default(new FirRenderer(null, null, null, null, null, null, null, null, (ConeIdShortRenderer) function0.invoke(), null, null, null, null, new ConeTypeRendererForReadability(null, function0), null, new FirCallableSignatureRendererForReadability(), null, null, null, null, null, false, false, false, 16735385, null).renderElementAsString(firBasedSymbol.getFir(), true), Printer.LINE_SEPARATOR, Argument.Delimiters.none, false, 4, (Object) null);
    }

    public static String c(ExpectActualAnnotationsIncompatibilityType expectActualAnnotationsIncompatibilityType) {
        expectActualAnnotationsIncompatibilityType.getClass();
        StringBuilder sb = new StringBuilder("Annotation `");
        FirExpectActualAnnotationIncompatibilityDiagnosticRenderers firExpectActualAnnotationIncompatibilityDiagnosticRenderers = INSTANCE;
        sb.append(firExpectActualAnnotationIncompatibilityDiagnosticRenderers.renderAnnotation((FirAnnotation) expectActualAnnotationsIncompatibilityType.getExpectAnnotation()));
        sb.append("` ");
        if (expectActualAnnotationsIncompatibilityType instanceof ExpectActualAnnotationsIncompatibilityType.MissingOnActual) {
            sb.append("is missing on actual declaration");
        } else {
            if (!(expectActualAnnotationsIncompatibilityType instanceof ExpectActualAnnotationsIncompatibilityType.DifferentOnActual)) {
                bu8.a();
                return null;
            }
            sb.append("has different arguments on actual declaration: `");
            sb.append(firExpectActualAnnotationIncompatibilityDiagnosticRenderers.renderAnnotation((FirAnnotation) ((ExpectActualAnnotationsIncompatibilityType.DifferentOnActual) expectActualAnnotationsIncompatibilityType).getActualAnnotation()));
            sb.append("`");
        }
        return sb.toString();
    }

    public static /* synthetic */ void getSYMBOL_RENDERER$annotations() {
    }

    private final String renderAnnotation(FirAnnotation ann) {
        return new FirRenderer(null, null, null, null, null, null, null, null, new ConeIdShortRenderer(), null, null, null, null, new ConeTypeRenderer(null, false, 3, null), new FirIdRendererBasedSymbolRenderer(), null, null, new FirResolvedNamedReferenceRenderer(), new FirResolvedQualifierRenderer(), new FirGetClassCallRendererForReadability(), null, false, false, false, 15834879, null).renderElementAsString(ann, true);
    }

    public final ContextIndependentParameterRenderer<ExpectActualAnnotationsIncompatibilityType<? extends FirAnnotation>> getINCOMPATIBILITY() {
        return INCOMPATIBILITY;
    }

    public final ContextIndependentParameterRenderer<FirBasedSymbol<?>> getSYMBOL_RENDERER() {
        return SYMBOL_RENDERER;
    }
}
