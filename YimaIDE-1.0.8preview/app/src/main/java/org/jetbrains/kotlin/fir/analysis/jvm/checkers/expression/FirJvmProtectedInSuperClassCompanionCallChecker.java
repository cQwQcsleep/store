package org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.jvm.FirJvmErrors;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.FirVariableAssignment;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirBackingFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertyAccessorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.load.java.JvmAbi;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;
import org.jetbrains.kotlin.types.AbstractTypeChecker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/expression/FirJvmProtectedInSuperClassCompanionCallChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirBasicExpressionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirStatement;)V", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmProtectedInSuperClassCompanionCallChecker extends FirExpressionChecker<FirStatement> {
    public static final FirJvmProtectedInSuperClassCompanionCallChecker INSTANCE = new FirJvmProtectedInSuperClassCompanionCallChecker();

    private FirJvmProtectedInSuperClassCompanionCallChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirStatement firStatement) {
        FirRegularClassSymbol regularClassSymbol;
        FirReference reference;
        FirCallableSymbol resolvedCallableSymbol$default;
        Visibility visibility;
        FirClassSymbol firClassSymbol;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firStatement.getClass();
        FirClassSymbol firClassSymbol2 = null;
        FirExpression dispatchReceiver = firStatement instanceof FirQualifiedAccessExpression ? ((FirQualifiedAccessExpression) firStatement).getDispatchReceiver() : firStatement instanceof FirVariableAssignment ? FirExpressionUtilKt.getDispatchReceiver((FirVariableAssignment) firStatement) : null;
        if (dispatchReceiver == null || (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(checkerContext, FirTypeUtilsKt.getResolvedType(dispatchReceiver))) == null || (reference = ReferenceUtilsKt.toReference(firStatement, checkerContext.getSession())) == null || (resolvedCallableSymbol$default = FirReferenceUtilsKt.toResolvedCallableSymbol$default(reference, false, 1, null)) == null) {
            return;
        }
        boolean z = resolvedCallableSymbol$default instanceof FirPropertySymbol;
        if (z && LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.AllowAccessToProtectedFieldFromSuperCompanion)) {
            if (resolvedCallableSymbol$default.getRawStatus().isConst()) {
                return;
            }
            FirBackingFieldSymbol backingFieldSymbol = ((FirPropertySymbol) resolvedCallableSymbol$default).getBackingFieldSymbol();
            if (backingFieldSymbol != null && FirAnnotationUtilsKt.hasAnnotation(backingFieldSymbol, JvmAbi.JVM_FIELD_ANNOTATION_CLASS_ID, checkerContext.getSession())) {
                return;
            }
        }
        if (!z) {
            visibility = resolvedCallableSymbol$default.getResolvedStatus().getVisibility();
        } else if (firStatement instanceof FirVariableAssignment) {
            FirPropertyAccessorSymbol setterSymbol = ((FirPropertySymbol) resolvedCallableSymbol$default).getSetterSymbol();
            if (setterSymbol == null || (visibility = setterSymbol.getResolvedStatus().getVisibility()) == null) {
                visibility = resolvedCallableSymbol$default.getResolvedStatus().getVisibility();
            }
        } else {
            FirPropertyAccessorSymbol getterSymbol = ((FirPropertySymbol) resolvedCallableSymbol$default).getGetterSymbol();
            if (getterSymbol == null || (visibility = getterSymbol.getResolvedStatus().getVisibility()) == null) {
                visibility = resolvedCallableSymbol$default.getResolvedStatus().getVisibility();
            }
        }
        if (Intrinsics.areEqual(visibility, Visibilities.Protected.INSTANCE) && !FirAnnotationUtilsKt.hasAnnotation(resolvedCallableSymbol$default, StandardClassIds$Annotations.INSTANCE.getJvmStatic(), checkerContext.getSession()) && regularClassSymbol.getRawStatus().isCompanion()) {
            FirClassLikeSymbol<FirClassLikeDeclaration> containingDeclaration = DeclarationUtilsKt.getContainingDeclaration(regularClassSymbol, checkerContext.getSession());
            FirRegularClassSymbol firRegularClassSymbol = containingDeclaration instanceof FirRegularClassSymbol ? (FirRegularClassSymbol) containingDeclaration : null;
            if (firRegularClassSymbol == null) {
                return;
            }
            ConeClassLikeType coneClassLikeTypeDefaultType = ScopeUtilsKt.defaultType(firRegularClassSymbol);
            Iterator it = CollectionsKt.asReversed(checkerContext.getContainingDeclarations()).iterator();
            while (true) {
                if (!it.hasNext()) {
                    firClassSymbol = null;
                    break;
                }
                FirBasedSymbol firBasedSymbol = (FirBasedSymbol) it.next();
                if (!(firBasedSymbol instanceof FirClassSymbol)) {
                    firBasedSymbol = null;
                }
                firClassSymbol = (FirClassSymbol) firBasedSymbol;
                if (firClassSymbol != null) {
                    if (!AbstractTypeChecker.isSubtypeOf$default(AbstractTypeChecker.INSTANCE, TypeComponentsKt.getTypeContext(checkerContext.getSession()), ScopeUtilsKt.defaultType(firClassSymbol), coneClassLikeTypeDefaultType, false, 8, (Object) null)) {
                        firClassSymbol = null;
                    }
                    if (firClassSymbol != null) {
                        break;
                    }
                }
            }
            if (firClassSymbol == null) {
                return;
            }
            for (FirBasedSymbol firBasedSymbol2 : CollectionsKt.asReversed(checkerContext.getContainingDeclarations())) {
                if (!(firBasedSymbol2 instanceof FirClassSymbol)) {
                    firBasedSymbol2 = null;
                }
                FirClassSymbol firClassSymbol3 = (FirClassSymbol) firBasedSymbol2;
                if (firClassSymbol3 != null) {
                    if (!Intrinsics.areEqual(firClassSymbol3, regularClassSymbol) && !Intrinsics.areEqual(firClassSymbol3, firRegularClassSymbol)) {
                        firClassSymbol3 = null;
                    }
                    if (firClassSymbol3 != null) {
                        firClassSymbol2 = firClassSymbol3;
                        break;
                    }
                }
            }
            if (firClassSymbol2 == null) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) reference.getSource(), FirJvmErrors.INSTANCE.getSUBCLASS_CANT_CALL_COMPANION_PROTECTED_NON_STATIC(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
    }
}
