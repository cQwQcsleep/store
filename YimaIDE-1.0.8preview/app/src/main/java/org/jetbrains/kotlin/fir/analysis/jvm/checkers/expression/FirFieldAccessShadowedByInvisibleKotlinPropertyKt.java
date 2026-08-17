package org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirVisibilityCheckerKt;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.jvm.FirJvmErrors;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.scopes.CallableCopyTypeCalculator;
import org.jetbrains.kotlin.fir.scopes.FirScopeKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirAbstractImportingScopeKt;
import org.jetbrains.kotlin.fir.scopes.impl.ImportedFromObjectOrStaticData;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFileSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a-\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0007H\u0002R\u00020\u0002R\u00020\u0004j\u0006\u0010\u0003\u001a\u00020\u0002j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\b\u001aE\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002R\u00020\u0002R\u00020\u0004j\u0006\u0010\u0003\u001a\u00020\u0002j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0010\u001a5\u0010\u0011\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002R\u00020\u0002R\u00020\u0004j\u0006\u0010\u0003\u001a\u00020\u0002j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0012¨\u0006\u0013"}, d2 = {"checkFieldAccess", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;)V", "checkClashWithInvisibleProperty", "fieldSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFieldSymbol;", "containingFileSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFileSymbol;", "dispatchReceiver", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/symbols/impl/FirFieldSymbol;Lorg/jetbrains/kotlin/fir/symbols/impl/FirFileSymbol;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;)V", "checkClashWithCompanionProperty", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/symbols/impl/FirFieldSymbol;Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;)V", "org.jetbrains.kotlin:checkers.jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirFieldAccessShadowedByInvisibleKotlinPropertyKt {
    private static final void checkClashWithCompanionProperty(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFieldSymbol firFieldSymbol, FirQualifiedAccessExpression firQualifiedAccessExpression) {
        ConeKotlinType resolvedType;
        FirRegularClassSymbol regularClassSymbol;
        FirRegularClassSymbol resolvedCompanionObjectSymbol;
        FirExpression dispatchReceiver = firQualifiedAccessExpression.getDispatchReceiver();
        if (dispatchReceiver == null || (resolvedType = FirTypeUtilsKt.getResolvedType(dispatchReceiver)) == null || (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(checkerContext, resolvedType)) == null || (resolvedCompanionObjectSymbol = regularClassSymbol.getResolvedCompanionObjectSymbol()) == null) {
            return;
        }
        for (FirVariableSymbol<?> firVariableSymbol : FirScopeKt.getProperties(FirHelpersKt.unsubstitutedScope(checkerContext, resolvedCompanionObjectSymbol), firFieldSymbol.getName())) {
            if ((firVariableSymbol instanceof FirPropertySymbol) && DeclarationAttributesKt.getHasBackingField((FirPropertySymbol) firVariableSymbol)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firQualifiedAccessExpression.getSource(), (KtDiagnosticFactory1) FirJvmErrors.INSTANCE.getJAVA_FIELD_SHADOWED_BY_KOTLIN_PROPERTY(), (Object) firVariableSymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                return;
            }
        }
    }

    private static final void checkClashWithInvisibleProperty(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFieldSymbol firFieldSymbol, FirFileSymbol firFileSymbol, FirExpression firExpression, FirQualifiedAccessExpression firQualifiedAccessExpression) {
        FirTypeScope firTypeScopeScope = ScopeUtilsKt.scope(checkerContext, FirTypeUtilsKt.getResolvedType(firExpression), CallableCopyTypeCalculator.CalculateDeferredForceLazyResolution.INSTANCE, (FirResolvePhase) null);
        if (firTypeScopeScope == null) {
            return;
        }
        for (FirVariableSymbol<?> firVariableSymbol : FirScopeKt.getProperties(firTypeScopeScope, firFieldSymbol.getName())) {
            if ((firVariableSymbol instanceof FirPropertySymbol) && DeclarationAttributesKt.getHasBackingField((FirPropertySymbol) firVariableSymbol)) {
                FirFileSymbol firFileSymbol2 = firFileSymbol;
                FirExpression firExpression2 = firExpression;
                if (!FirVisibilityCheckerKt.isVisible$default(FirVisibilityCheckerKt.getVisibilityChecker(checkerContext.getSession()), firVariableSymbol, checkerContext.getSession(), firFileSymbol2, checkerContext.getContainingDeclarations(), firExpression2, false, 32, null)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firQualifiedAccessExpression.getSource(), (KtDiagnosticFactory1) FirJvmErrors.INSTANCE.getJAVA_FIELD_SHADOWED_BY_KOTLIN_PROPERTY(), (Object) firVariableSymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                    return;
                } else {
                    firFileSymbol = firFileSymbol2;
                    firExpression = firExpression2;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void checkFieldAccess(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirQualifiedAccessExpression firQualifiedAccessExpression) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirExpression dispatchReceiver;
        ImportedFromObjectOrStaticData importedFromObjectOrStaticData;
        FirCallableDeclaration original;
        if (LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.ProperFieldAccessGenerationForFieldAccessShadowedByKotlinProperty)) {
            return;
        }
        FirCallableSymbol<?> resolvedCallableSymbol = ReferenceUtilsKt.toResolvedCallableSymbol(firQualifiedAccessExpression);
        FirFieldSymbol firFieldSymbol = resolvedCallableSymbol instanceof FirFieldSymbol ? (FirFieldSymbol) resolvedCallableSymbol : null;
        if (firFieldSymbol == null) {
            return;
        }
        FirDeclarationOrigin origin = firFieldSymbol.getOrigin();
        if (!(origin instanceof FirDeclarationOrigin.Java) && !Intrinsics.areEqual(origin, FirDeclarationOrigin.Enhancement.INSTANCE)) {
            D fir = firFieldSymbol.getFir();
            FirCallableDeclaration firCallableDeclaration = fir instanceof FirCallableDeclaration ? (FirCallableDeclaration) fir : null;
            if (firCallableDeclaration == null || (importedFromObjectOrStaticData = FirAbstractImportingScopeKt.getImportedFromObjectOrStaticData(firCallableDeclaration)) == null || (original = importedFromObjectOrStaticData.getOriginal()) == null || !DeclarationUtilsKt.isJavaOrEnhancement(original)) {
                return;
            }
        }
        FirFileSymbol containingFileSymbol = checkerContext.getContainingFileSymbol();
        if (containingFileSymbol == null || (dispatchReceiver = firQualifiedAccessExpression.getDispatchReceiver()) == null) {
            return;
        }
        checkClashWithInvisibleProperty(checkerContext, diagnosticReporter, firFieldSymbol, containingFileSymbol, dispatchReceiver, firQualifiedAccessExpression);
        checkClashWithCompanionProperty(checkerContext, diagnosticReporter, firFieldSymbol, firQualifiedAccessExpression);
    }
}
