package org.jetbrains.kotlin.fir.analysis.checkers;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.diagnostics.SourceElementPositioningStrategies;
import org.jetbrains.kotlin.fir.analysis.checkers.FirUnderscoreHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef;
import org.jetbrains.kotlin.fir.types.FirQualifierPart;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeProjectionWithVariance;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirUserTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a7\u0010\u0000\u001a\u00020\u00012\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH\u0000R\u00020\u0002R\u00020\u0004j\u0006\u0010\u0003\u001a\u00020\u0002j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\n\u001a/\u0010\u000e\u001a\u00020\u00012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0000R\u00020\u0002R\u00020\u0004j\u0006\u0010\u0003\u001a\u00020\u0002j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0011\u001a \u0010\u0012\u001a\u00020\u0001*\u00020\u00102\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00010\u0014H\u0002\"\u0015\u0010\u000b\u001a\u00020\t*\u00020\f8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\r¨\u0006\u0016"}, d2 = {"checkUnderscoreDiagnostics", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "isExpression", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/KtSourceElement;Z)V", "isUnderscore", Argument.Delimiters.none, "(Ljava/lang/CharSequence;)Z", "checkTypeRefForUnderscore", "typeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/types/FirTypeRef;)V", "forEachQualifierPart", "block", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/types/FirQualifierPart;", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirUnderscoreHelpersKt {
    public static Unit a(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirQualifierPart firQualifierPart) {
        firQualifierPart.getClass();
        checkUnderscoreDiagnostics(checkerContext, diagnosticReporter, firQualifierPart.getSource(), true);
        for (FirTypeProjection firTypeProjection : firQualifierPart.getTypeArgumentList().getTypeArguments()) {
            if (firTypeProjection instanceof FirTypeProjectionWithVariance) {
                checkTypeRefForUnderscore(checkerContext, diagnosticReporter, ((FirTypeProjectionWithVariance) firTypeProjection).getTypeRef());
            } else {
                checkUnderscoreDiagnostics(checkerContext, diagnosticReporter, firTypeProjection.getSource(), true);
            }
        }
        return Unit.INSTANCE;
    }

    public static final void checkTypeRefForUnderscore(final CheckerContext checkerContext, final DiagnosticReporter diagnosticReporter, FirTypeRef firTypeRef) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        if ((firTypeRef instanceof FirErrorTypeRef) || firTypeRef == null) {
            return;
        }
        forEachQualifierPart(firTypeRef, new Function1() { // from class: gg5
            public final Object invoke(Object obj) {
                return FirUnderscoreHelpersKt.a(checkerContext, diagnosticReporter, (FirQualifierPart) obj);
            }
        });
    }

    public static final void checkUnderscoreDiagnostics(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, KtSourceElement ktSourceElement, boolean z) {
        KtSourceElementKind kind;
        CharSequence rawIdentifier;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        if (ktSourceElement == null || (kind = ktSourceElement.getKind()) == null) {
            return;
        }
        if (((kind instanceof KtRealSourceElementKind) || (kind instanceof KtFakeSourceElementKind.ReferenceInAtomicQualifiedAccess)) && (rawIdentifier = SourceNavigator.INSTANCE.forSource(ktSourceElement).getRawIdentifier(ktSourceElement)) != null && isUnderscore(rawIdentifier)) {
            KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, z ? FirErrors.INSTANCE.getUNDERSCORE_USAGE_WITHOUT_BACKTICKS() : FirErrors.INSTANCE.getUNDERSCORE_IS_RESERVED(), (AbstractSourceElementPositioningStrategy) SourceElementPositioningStrategies.INSTANCE.getREFERENCED_NAME_BY_QUALIFIED());
        }
    }

    private static final void forEachQualifierPart(FirTypeRef firTypeRef, Function1<? super FirQualifierPart, Unit> function1) {
        List qualifier;
        FirResolvedTypeRef firResolvedTypeRef = firTypeRef instanceof FirResolvedTypeRef ? (FirResolvedTypeRef) firTypeRef : null;
        FirTypeRef delegatedTypeRef = firResolvedTypeRef != null ? firResolvedTypeRef.getDelegatedTypeRef() : null;
        FirUserTypeRef firUserTypeRef = delegatedTypeRef instanceof FirUserTypeRef ? (FirUserTypeRef) delegatedTypeRef : null;
        if (firUserTypeRef == null || (qualifier = firUserTypeRef.getQualifier()) == null) {
            return;
        }
        Iterator it = qualifier.iterator();
        while (it.hasNext()) {
            function1.invoke(it.next());
        }
    }

    public static final boolean isUnderscore(CharSequence charSequence) {
        charSequence.getClass();
        for (int i = 0; i < charSequence.length(); i++) {
            if (charSequence.charAt(i) != '_') {
                return false;
            }
        }
        return true;
    }
}
