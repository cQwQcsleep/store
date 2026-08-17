package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.diagnostics.SourceElementPositioningStrategies;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirImplicitInvokeCall;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvable;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirAbstractImportingScopeKt;
import org.jetbrains.kotlin.fir.scopes.impl.ImportedFromObjectOrStaticData;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ\u000f\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\u000fH\u0082\u0010¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirTypeArgumentsNotAllowedExpressionChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirQualifiedAccessExpressionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;)V", "lastQualifierPartWithTypeArguments", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirTypeArgumentsNotAllowedExpressionChecker extends FirExpressionChecker<FirQualifiedAccessExpression> {
    public static final FirTypeArgumentsNotAllowedExpressionChecker INSTANCE = new FirTypeArgumentsNotAllowedExpressionChecker();

    private FirTypeArgumentsNotAllowedExpressionChecker() {
        super(MppCheckerKind.Common);
    }

    private final FirResolvedQualifier lastQualifierPartWithTypeArguments(FirResolvedQualifier firResolvedQualifier) {
        while (!firResolvedQualifier.getTypeArguments().isEmpty()) {
            if (!UtilsKt.getOwnTypeArguments(firResolvedQualifier).isEmpty()) {
                return firResolvedQualifier;
            }
            firResolvedQualifier = firResolvedQualifier.getExplicitParent();
            if (firResolvedQualifier == null) {
                return null;
            }
        }
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:45:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:47:0x00af A[PHI: r15
      0x00af: PHI (r15v3 org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext) = 
      (r15v1 org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext)
      (r15v4 org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext)
     binds: [B:46:0x00ad, B:44:0x00a2] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:49:0x00b8  */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirQualifiedAccessExpression firQualifiedAccessExpression) throws KotlinIllegalArgumentExceptionWithAttachments {
        List<FirTypeParameterSymbol> typeParameterSymbols;
        FirCallableSymbol<?> resolvedCallableSymbol;
        List<FirTypeParameterSymbol> typeParameterSymbols2;
        CheckerContext checkerContext2;
        KtDiagnosticFactory1<String> type_arguments_not_allowed_warning;
        ImportedFromObjectOrStaticData importedFromObjectOrStaticData;
        FirCallableDeclaration original;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firQualifiedAccessExpression.getClass();
        FirExpression explicitReceiver = firQualifiedAccessExpression.getExplicitReceiver();
        FirAnnotationContainer firAnnotationContainerUnwrapSmartcastExpression = explicitReceiver != null ? FirExpressionUtilKt.unwrapSmartcastExpression(explicitReceiver) : null;
        if (firAnnotationContainerUnwrapSmartcastExpression instanceof FirResolvedQualifier) {
            FirResolvedQualifier firResolvedQualifier = (FirResolvedQualifier) firAnnotationContainerUnwrapSmartcastExpression;
            FirResolvedQualifier firResolvedQualifierLastQualifierPartWithTypeArguments = lastQualifierPartWithTypeArguments(firResolvedQualifier);
            if (firResolvedQualifierLastQualifierPartWithTypeArguments == null) {
                return;
            }
            if (firResolvedQualifier.getSymbol() == null) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firResolvedQualifier.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getTYPE_ARGUMENTS_NOT_ALLOWED(), (Object) "for packages", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
            FirCallableSymbol<?> resolvedCallableSymbol2 = org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt.toResolvedCallableSymbol(firQualifiedAccessExpression);
            if (firResolvedQualifier.getSymbol() != null && resolvedCallableSymbol2 != null && resolvedCallableSymbol2.getRawStatus().isStatic() && !(firQualifiedAccessExpression instanceof FirCallableReferenceAccess)) {
                FirDeclarationOrigin origin = resolvedCallableSymbol2.getOrigin();
                if ((origin instanceof FirDeclarationOrigin.Java) || Intrinsics.areEqual(origin, FirDeclarationOrigin.Enhancement.INSTANCE)) {
                    checkerContext2 = checkerContext;
                    if (!LanguageVersionUtilsKt.isEnabled(checkerContext2, LanguageFeature.ForbidUselessTypeArgumentsIn25)) {
                        type_arguments_not_allowed_warning = FirErrors.INSTANCE.getTYPE_ARGUMENTS_NOT_ALLOWED();
                    } else {
                        type_arguments_not_allowed_warning = FirErrors.INSTANCE.getTYPE_ARGUMENTS_NOT_ALLOWED_WARNING();
                    }
                } else {
                    Object fir = resolvedCallableSymbol2.getFir();
                    FirCallableDeclaration firCallableDeclaration = fir instanceof FirCallableDeclaration ? (FirCallableDeclaration) fir : null;
                    if (firCallableDeclaration == null || (importedFromObjectOrStaticData = FirAbstractImportingScopeKt.getImportedFromObjectOrStaticData(firCallableDeclaration)) == null || (original = importedFromObjectOrStaticData.getOriginal()) == null || !DeclarationUtilsKt.isJavaOrEnhancement(original)) {
                        FirClassLikeSymbol<?> containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol(resolvedCallableSymbol2);
                        if ((containingClassSymbol != null ? FirHelpersKt.getClassKind(containingClassSymbol) : null) == ClassKind.ENUM_CLASS) {
                            checkerContext2 = checkerContext;
                            if (!LanguageVersionUtilsKt.isEnabled(checkerContext2, LanguageFeature.ForbidUselessTypeArgumentsIn25)) {
                                type_arguments_not_allowed_warning = FirErrors.INSTANCE.getTYPE_ARGUMENTS_NOT_ALLOWED_WARNING();
                            }
                        } else {
                            checkerContext2 = checkerContext;
                        }
                        type_arguments_not_allowed_warning = FirErrors.INSTANCE.getTYPE_ARGUMENTS_NOT_ALLOWED();
                    } else {
                        checkerContext2 = checkerContext;
                        if (!LanguageVersionUtilsKt.isEnabled(checkerContext2, LanguageFeature.ForbidUselessTypeArgumentsIn25)) {
                            type_arguments_not_allowed_warning = FirErrors.INSTANCE.getTYPE_ARGUMENTS_NOT_ALLOWED();
                        } else {
                            type_arguments_not_allowed_warning = FirErrors.INSTANCE.getTYPE_ARGUMENTS_NOT_ALLOWED_WARNING();
                        }
                    }
                }
                KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext2, diagnosticReporter, (AbstractKtSourceElement) firResolvedQualifierLastQualifierPartWithTypeArguments.getSource(), type_arguments_not_allowed_warning, "when static member is accessed", (AbstractSourceElementPositioningStrategy) SourceElementPositioningStrategies.INSTANCE.getTYPE_ARGUMENT_LIST_OR_WITHOUT_RECEIVER());
            }
        }
        if ((firQualifiedAccessExpression instanceof FirImplicitInvokeCall) && (firAnnotationContainerUnwrapSmartcastExpression instanceof FirPropertyAccessExpression)) {
            FirImplicitInvokeCall firImplicitInvokeCall = (FirImplicitInvokeCall) firQualifiedAccessExpression;
            List<FirTypeProjection> typeArguments = firImplicitInvokeCall.getTypeArguments();
            if ((typeArguments instanceof Collection) && typeArguments.isEmpty()) {
                return;
            }
            Iterator<T> it = typeArguments.iterator();
            while (it.hasNext()) {
                if (FirHelpersKt.isExplicit((FirTypeProjection) it.next())) {
                    FirCallableSymbol<?> resolvedCallableSymbol3 = org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt.toResolvedCallableSymbol(firQualifiedAccessExpression);
                    if (resolvedCallableSymbol3 == null || (typeParameterSymbols = resolvedCallableSymbol3.getTypeParameterSymbols()) == null || !(!typeParameterSymbols.isEmpty()) || (resolvedCallableSymbol = org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt.toResolvedCallableSymbol((FirResolvable) firAnnotationContainerUnwrapSmartcastExpression)) == null || (typeParameterSymbols2 = resolvedCallableSymbol.getTypeParameterSymbols()) == null || !(!typeParameterSymbols2.isEmpty())) {
                        return;
                    }
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firImplicitInvokeCall.getCalleeReference().getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getTYPE_ARGUMENTS_NOT_ALLOWED(), (Object) "on implicit invoke call", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                    return;
                }
            }
        }
    }
}
