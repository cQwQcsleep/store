package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ\u001d\u0010\u000e\u001a\u00020\u000f*\u00020\u0002H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0010J\u001d\u0010\u0011\u001a\u00020\u000f*\u00020\u0002H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0010¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirThrowableSubclassChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirClassChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "hasThrowableSupertype", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)Z", "hasGenericOuterDeclaration", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirThrowableSubclassChecker extends FirDeclarationChecker<FirClass> {
    public static final FirThrowableSubclassChecker INSTANCE = new FirThrowableSubclassChecker();

    private FirThrowableSubclassChecker() {
        super(MppCheckerKind.Common);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean hasGenericOuterDeclaration(CheckerContext checkerContext, FirClass firClass) {
        if (!firClass.getIsLocal()) {
            return false;
        }
        for (FirBasedSymbol firBasedSymbol : CollectionsKt.asReversed(checkerContext.getContainingDeclarations())) {
            if (firBasedSymbol instanceof FirCallableSymbol) {
                if (!((FirCallableSymbol) firBasedSymbol).getTypeParameterSymbols().isEmpty()) {
                    return true;
                }
            } else if ((firBasedSymbol instanceof FirClassLikeSymbol) && !((FirClassLikeSymbol) firBasedSymbol).getTypeParameterSymbols().isEmpty()) {
                return true;
            }
            if (firBasedSymbol instanceof FirRegularClassSymbol) {
                FirClassLikeSymbol firClassLikeSymbol = (FirClassLikeSymbol) firBasedSymbol;
                if (!((FirClassLikeDeclaration) firClassLikeSymbol.getFir()).getIsLocal() && !firClassLikeSymbol.getRawStatus().isInner()) {
                    break;
                }
            }
        }
        return false;
    }

    private final boolean hasThrowableSupertype(CheckerContext checkerContext, FirClass firClass) {
        List<ConeClassLikeType> superConeTypes = FirDeclarationUtilKt.getSuperConeTypes(firClass);
        if ((superConeTypes instanceof Collection) && superConeTypes.isEmpty()) {
            return false;
        }
        for (ConeClassLikeType coneClassLikeType : superConeTypes) {
            if (!(coneClassLikeType instanceof ConeErrorType) && FirHelpersKt.isSubtypeOfThrowable(coneClassLikeType, checkerContext.getSession())) {
                return true;
            }
        }
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass) {
        KtSourceElement source;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firClass.getClass();
        if (hasThrowableSupertype(checkerContext, firClass)) {
            if (firClass.getTypeParameters().isEmpty()) {
                if (hasGenericOuterDeclaration(checkerContext, firClass)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firClass.getSource(), FirErrors.INSTANCE.getINNER_CLASS_OF_GENERIC_THROWABLE_SUBCLASS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    return;
                }
                return;
            }
            FirTypeParameterRef firTypeParameterRef = (FirTypeParameterRef) CollectionsKt.firstOrNull(firClass.getTypeParameters());
            if (firTypeParameterRef != null && (source = firTypeParameterRef.getSource()) != null) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirErrors.INSTANCE.getGENERIC_THROWABLE_SUBCLASS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
            if (firClass instanceof FirRegularClass) {
                if (!firClass.getStatus().isInner() && !((FirRegularClass) firClass).getIsLocal()) {
                    return;
                }
            } else if (!(firClass instanceof FirAnonymousObject)) {
                bu8.a();
                return;
            }
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firClass.getSource(), FirErrors.INSTANCE.getINNER_CLASS_OF_GENERIC_THROWABLE_SUBCLASS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
    }
}
