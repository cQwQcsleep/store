package org.jetbrains.kotlin.fir.analysis.checkers;

import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeAttributes;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.FirMissingDependencyStorageKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a7\u0010\u0000\u001a\u00020\u00012\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tR\u00020\u0002R\u00020\u0004j\u0006\u0010\u0003\u001a\u00020\u0002j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\n\u001aC\u0010\u0000\u001a\u00020\u00012\f\u0010\u000b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\f2\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\r\u001a\u00020\u0001R\u00020\u0002R\u00020\u0004j\u0006\u0010\u0003\u001a\u00020\u0002j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u000e¨\u0006\u000f"}, d2 = {"checkMissingDependencySuperTypes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "classifierType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/KtSourceElement;)Z", "declaration", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "isEagerCheck", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/KtSourceElement;Z)Z", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirMissingDependencySupertypeUtilsKt {
    public static final boolean checkMissingDependencySuperTypes(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirBasedSymbol<?> firBasedSymbol, KtSourceElement ktSourceElement, boolean z) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        if (!(firBasedSymbol instanceof FirClassSymbol)) {
            return false;
        }
        Set<ConeKotlinType> missingSuperTypes = FirMissingDependencyStorageKt.getMissingDependencyStorage(checkerContext.getSession()).getMissingSuperTypes((FirClassSymbol) firBasedSymbol);
        LanguageVersionSettings languageVersionSettings = checkerContext.get$languageVersionSettings();
        Iterator<ConeKotlinType> it = missingSuperTypes.iterator();
        while (it.hasNext()) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory2) ((!z || languageVersionSettings.supportsFeature(LanguageFeature.AllowEagerSupertypeAccessibilityChecks)) ? FirErrors.INSTANCE.getMISSING_DEPENDENCY_SUPERCLASS() : FirErrors.INSTANCE.getMISSING_DEPENDENCY_SUPERCLASS_WARNING()), (Object) TypeUtilsKt.withNullability$default(TypeUtilsKt.withArguments(it.next(), new ConeTypeProjection[0]), false, TypeComponentsKt.getTypeContext(checkerContext.getSession()), null, false, 12, null), (Object) TypeConstructionUtilsKt.constructType$default((FirClassLikeSymbol) firBasedSymbol, (ConeTypeProjection[]) null, false, (ConeAttributes) null, 7, (Object) null), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
        }
        return !missingSuperTypes.isEmpty();
    }

    public static final boolean checkMissingDependencySuperTypes(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, ConeKotlinType coneKotlinType, KtSourceElement ktSourceElement) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        return checkMissingDependencySuperTypes(checkerContext, diagnosticReporter, coneKotlinType != null ? ToSymbolUtilsKt.toSymbol(checkerContext, coneKotlinType) : null, ktSourceElement, false);
    }
}
