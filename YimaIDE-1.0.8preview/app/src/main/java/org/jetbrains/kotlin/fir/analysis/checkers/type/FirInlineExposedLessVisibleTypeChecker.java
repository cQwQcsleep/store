package org.jetbrains.kotlin.fir.analysis.checkers.type;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryForDeprecation3;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirInlineDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJA\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0000R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirInlineExposedLessVisibleTypeChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirTypeChecker;", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirResolvedTypeRefChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "typeRef", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;)V", "coneType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "inlineFunctionBodyContext", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirInlineDeclarationChecker$InlineFunctionBodyContext;", "check$org_jetbrains_kotlin_checkers", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirInlineDeclarationChecker$InlineFunctionBodyContext;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirInlineExposedLessVisibleTypeChecker extends FirTypeChecker<FirResolvedTypeRef> {
    public static final FirInlineExposedLessVisibleTypeChecker INSTANCE = new FirInlineExposedLessVisibleTypeChecker();

    private FirInlineExposedLessVisibleTypeChecker() {
        super(MppCheckerKind.Platform);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.type.FirTypeChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirResolvedTypeRef firResolvedTypeRef) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firResolvedTypeRef.getClass();
        FirInlineDeclarationChecker.InlineFunctionBodyContext inlineFunctionBodyContext = checkerContext.getInlineFunctionBodyContext();
        if (inlineFunctionBodyContext == null) {
            return;
        }
        check$org_jetbrains_kotlin_checkers(checkerContext, diagnosticReporter, firResolvedTypeRef.getConeType(), firResolvedTypeRef.getSource(), inlineFunctionBodyContext);
    }

    public final void check$org_jetbrains_kotlin_checkers(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, ConeKotlinType coneKotlinType, KtSourceElement ktSourceElement, FirInlineDeclarationChecker.InlineFunctionBodyContext inlineFunctionBodyContext) {
        EffectiveVisibility effectiveVisibilityLessVisibleVisibilityOrNull;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        coneKotlinType.getClass();
        inlineFunctionBodyContext.getClass();
        List<FirStatement> callsOrAssignments = checkerContext.getCallsOrAssignments();
        if (!(callsOrAssignments instanceof Collection) || !callsOrAssignments.isEmpty()) {
            Iterator<T> it = callsOrAssignments.iterator();
            while (it.hasNext()) {
                if (((FirStatement) it.next()) instanceof FirAnnotation) {
                    return;
                }
            }
        }
        ConeKotlinType coneKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType(checkerContext, coneKotlinType);
        FirClassLikeSymbol<?> classLikeSymbol = ToSymbolUtilsKt.toClassLikeSymbol(checkerContext, coneKotlinTypeFullyExpandedType);
        if (classLikeSymbol == null || (effectiveVisibilityLessVisibleVisibilityOrNull = inlineFunctionBodyContext.lessVisibleVisibilityOrNull(classLikeSymbol, true)) == null) {
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactoryForDeprecation3<EffectiveVisibility, ConeKotlinType, EffectiveVisibility>) ((KtDiagnosticFactoryForDeprecation3<Object, Object, Object>) FirErrors.INSTANCE.getLESS_VISIBLE_TYPE_ACCESS_IN_INLINE()), effectiveVisibilityLessVisibleVisibilityOrNull, coneKotlinTypeFullyExpandedType, inlineFunctionBodyContext.getInlineFunEffectiveVisibility(), (64 & 64) != 0 ? null : null);
    }
}
