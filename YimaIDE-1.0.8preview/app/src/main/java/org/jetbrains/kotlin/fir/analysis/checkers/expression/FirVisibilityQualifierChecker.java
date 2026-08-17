package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.fir.FirVisibilityCheckerKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.ConeDiagnosticToFirDiagnosticKt;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCodeFragment;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeVisibilityError;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFileSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJA\u0010\u000e\u001a\u00020\u00072\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u00102\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0013J\u001f\u0010\u0014\u001a\u0004\u0018\u00010\u0015*\u00020\u0015H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0016¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirVisibilityQualifierChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirResolvedQualifierChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;)V", "checkClassLikeSymbol", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "isStandalone", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;Z)V", "toInvisibleCompanion", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirVisibilityQualifierChecker extends FirExpressionChecker<FirResolvedQualifier> {
    public static final FirVisibilityQualifierChecker INSTANCE = new FirVisibilityQualifierChecker();

    private FirVisibilityQualifierChecker() {
        super(MppCheckerKind.Common);
    }

    private final void checkClassLikeSymbol(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClassLikeSymbol<?> firClassLikeSymbol, FirResolvedQualifier firResolvedQualifier, boolean z) {
        FirClassLikeSymbol<?> symbol;
        FirClassLikeSymbol<?> classLikeSymbol;
        FirFile fir;
        List<FirDeclaration> declarations;
        FirRegularClassSymbol firRegularClassSymbolFullyExpandedClass;
        FirFile fir2;
        List<FirDeclaration> declarations2;
        FirFileSymbol containingFileSymbol = checkerContext.getContainingFileSymbol();
        if (containingFileSymbol == null) {
            return;
        }
        KtSourceElement source = firResolvedQualifier.getSource();
        FirDeclaration firDeclaration = null;
        if (!Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitReceiver.INSTANCE) && !FirVisibilityCheckerKt.isClassLikeVisible(FirVisibilityCheckerKt.getVisibilityChecker(checkerContext.getSession()), firClassLikeSymbol, checkerContext.getSession(), containingFileSymbol, checkerContext.getContainingDeclarations())) {
            if ((firResolvedQualifier instanceof FirErrorResolvedQualifier) && (((FirErrorResolvedQualifier) firResolvedQualifier).getDiagnostic() instanceof ConeVisibilityError)) {
                return;
            }
            FirFileSymbol containingFileSymbol2 = checkerContext.getContainingFileSymbol();
            if (containingFileSymbol2 != null && (fir2 = containingFileSymbol2.getFir()) != null && (declarations2 = fir2.getDeclarations()) != null) {
                firDeclaration = (FirDeclaration) CollectionsKt.singleOrNull(declarations2);
            }
            if (firDeclaration instanceof FirCodeFragment) {
                return;
            }
            diagnosticReporter.report(ConeDiagnosticToFirDiagnosticKt.toInvisibleReferenceDiagnostic(firClassLikeSymbol, firResolvedQualifier.getSource(), checkerContext.getSession()), checkerContext);
            return;
        }
        if (z) {
            FirClassLikeSymbol<?> symbol2 = firResolvedQualifier.getSymbol();
            FirRegularClassSymbol invisibleCompanion = (symbol2 == null || (firRegularClassSymbolFullyExpandedClass = DeclarationUtilsKt.fullyExpandedClass(checkerContext, symbol2)) == null) ? null : toInvisibleCompanion(checkerContext, firRegularClassSymbolFullyExpandedClass);
            if (invisibleCompanion != null) {
                if ((firResolvedQualifier instanceof FirErrorResolvedQualifier) && (((FirErrorResolvedQualifier) firResolvedQualifier).getDiagnostic() instanceof ConeVisibilityError)) {
                    return;
                }
                FirFileSymbol containingFileSymbol3 = checkerContext.getContainingFileSymbol();
                if (containingFileSymbol3 != null && (fir = containingFileSymbol3.getFir()) != null && (declarations = fir.getDeclarations()) != null) {
                    firDeclaration = (FirDeclaration) CollectionsKt.singleOrNull(declarations);
                }
                if (firDeclaration instanceof FirCodeFragment) {
                    return;
                }
                diagnosticReporter.report(ConeDiagnosticToFirDiagnosticKt.toInvisibleReferenceDiagnostic(invisibleCompanion, firResolvedQualifier.getSource(), checkerContext.getSession()), checkerContext);
                return;
            }
        }
        if ((firClassLikeSymbol instanceof FirTypeAliasSymbol) && (classLikeSymbol = ToSymbolUtilsKt.toClassLikeSymbol(checkerContext, ((FirTypeAliasSymbol) firClassLikeSymbol).getResolvedExpandedTypeRef().getConeType())) != null) {
            INSTANCE.checkClassLikeSymbol(checkerContext, diagnosticReporter, classLikeSymbol, firResolvedQualifier, z);
        }
        ConeClassLikeLookupTag ownerLookupTag = FirVisibilityCheckerKt.getOwnerLookupTag(firClassLikeSymbol);
        if (ownerLookupTag == null || (symbol = ToSymbolUtilsKt.toSymbol((SessionHolder) checkerContext, ownerLookupTag)) == null) {
            return;
        }
        INSTANCE.checkClassLikeSymbol(checkerContext, diagnosticReporter, symbol, firResolvedQualifier, false);
    }

    private final FirRegularClassSymbol toInvisibleCompanion(CheckerContext checkerContext, FirRegularClassSymbol firRegularClassSymbol) {
        FirRegularClassSymbol resolvedCompanionObjectSymbol;
        FirFileSymbol containingFileSymbol = checkerContext.getContainingFileSymbol();
        if (containingFileSymbol == null || (resolvedCompanionObjectSymbol = firRegularClassSymbol.getResolvedCompanionObjectSymbol()) == null || FirVisibilityCheckerKt.isClassLikeVisible(FirVisibilityCheckerKt.getVisibilityChecker(checkerContext.getSession()), resolvedCompanionObjectSymbol, checkerContext.getSession(), containingFileSymbol, checkerContext.getContainingDeclarations())) {
            return null;
        }
        return resolvedCompanionObjectSymbol;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirResolvedQualifier firResolvedQualifier) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firResolvedQualifier.getClass();
        FirClassLikeSymbol<?> symbol = firResolvedQualifier.getSymbol();
        if (symbol == null) {
            return;
        }
        checkClassLikeSymbol(checkerContext, diagnosticReporter, symbol, firResolvedQualifier, FirHelpersKt.isStandalone(checkerContext, firResolvedQualifier));
    }
}
