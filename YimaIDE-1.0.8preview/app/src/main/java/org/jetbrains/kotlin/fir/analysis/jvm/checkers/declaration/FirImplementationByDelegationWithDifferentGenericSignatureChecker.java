package org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryForDeprecation2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.DelegatedWrapperData;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.jvm.FirJvmErrors;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration.FirImplementationByDelegationWithDifferentGenericSignatureChecker;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.scopes.CallableCopyTypeCalculator;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScopeKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScopeKt;
import org.jetbrains.kotlin.fir.scopes.ProcessorAction;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/declaration/FirImplementationByDelegationWithDifferentGenericSignatureChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirClassChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirImplementationByDelegationWithDifferentGenericSignatureChecker extends FirDeclarationChecker<FirClass> {
    public static final FirImplementationByDelegationWithDifferentGenericSignatureChecker INSTANCE = new FirImplementationByDelegationWithDifferentGenericSignatureChecker();

    private FirImplementationByDelegationWithDifferentGenericSignatureChecker() {
        super(MppCheckerKind.Platform);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit check$lambda$0$0(final Ref.BooleanRef booleanRef, FirTypeScope firTypeScope, final FirNamedFunctionSymbol firNamedFunctionSymbol, final CheckerContext checkerContext, final DiagnosticReporter diagnosticReporter, final DelegatedWrapperData delegatedWrapperData, final FirNamedFunction firNamedFunction, final FirNamedFunctionSymbol firNamedFunctionSymbol2) {
        firNamedFunctionSymbol2.getClass();
        if (booleanRef.element || !firNamedFunctionSymbol2.getTypeParameterSymbols().isEmpty()) {
            return Unit.INSTANCE;
        }
        FirTypeScopeKt.processOverriddenFunctions(firTypeScope, firNamedFunctionSymbol2, (Function1<? super FirNamedFunctionSymbol, ? extends ProcessorAction>) new Function1() { // from class: i85
            public final Object invoke(Object obj) {
                return FirImplementationByDelegationWithDifferentGenericSignatureChecker.check$lambda$0$0$0(firNamedFunctionSymbol, checkerContext, diagnosticReporter, delegatedWrapperData, firNamedFunction, firNamedFunctionSymbol2, booleanRef, (FirNamedFunctionSymbol) obj);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final ProcessorAction check$lambda$0$0$0(FirNamedFunctionSymbol firNamedFunctionSymbol, CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, DelegatedWrapperData delegatedWrapperData, FirNamedFunction firNamedFunction, FirNamedFunctionSymbol firNamedFunctionSymbol2, Ref.BooleanRef booleanRef, FirNamedFunctionSymbol firNamedFunctionSymbol3) {
        firNamedFunctionSymbol3.getClass();
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firNamedFunctionSymbol3.getFir();
        while (true) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
            }
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            }
            firCallableDeclaration = originalForSubstitutionOverrideAttr;
        }
        FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
        if (symbol == null) {
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol");
            return null;
        }
        if (((FirNamedFunctionSymbol) symbol) != firNamedFunctionSymbol) {
            return ProcessorAction.NEXT;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) delegatedWrapperData.getDelegateFieldSymbol().getResolvedReturnTypeRef().getSource(), (KtDiagnosticFactoryForDeprecation2) FirJvmErrors.INSTANCE.getIMPLEMENTATION_BY_DELEGATION_WITH_DIFFERENT_GENERIC_SIGNATURE(), (Object) firNamedFunction.getSymbol(), (Object) firNamedFunctionSymbol2, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
        booleanRef.element = true;
        return ProcessorAction.STOP;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Unit d(final CheckerContext checkerContext, final DiagnosticReporter diagnosticReporter, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        ConeKotlinType resolvedType;
        final FirTypeScope firTypeScopeScope;
        firNamedFunctionSymbol.getClass();
        final DelegatedWrapperData delegatedWrapperData = ClassMembersKt.getDelegatedWrapperData(firNamedFunctionSymbol);
        if (delegatedWrapperData == null) {
            return Unit.INSTANCE;
        }
        final FirNamedFunction firNamedFunction = (FirNamedFunction) delegatedWrapperData.getWrapped();
        if (firNamedFunction.getTypeParameters().isEmpty()) {
            return Unit.INSTANCE;
        }
        FirExpression resolvedInitializer = delegatedWrapperData.getDelegateFieldSymbol().getResolvedInitializer();
        if (resolvedInitializer == null || (resolvedType = FirTypeUtilsKt.getResolvedType(resolvedInitializer)) == null || (firTypeScopeScope = ScopeUtilsKt.scope(checkerContext, resolvedType, CallableCopyTypeCalculator.DoNothing.INSTANCE, (FirResolvePhase) null)) == null) {
            return Unit.INSTANCE;
        }
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firNamedFunction.getSymbol().getFir();
        while (true) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
            }
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            }
            firCallableDeclaration = originalForSubstitutionOverrideAttr;
        }
        FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
        if (symbol == null) {
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol");
            return null;
        }
        final FirNamedFunctionSymbol firNamedFunctionSymbol2 = (FirNamedFunctionSymbol) symbol;
        firTypeScopeScope.processFunctionsByName(firNamedFunctionSymbol.getName(), new Function1() { // from class: h85
            public final Object invoke(Object obj) {
                return FirImplementationByDelegationWithDifferentGenericSignatureChecker.check$lambda$0$0(booleanRef, firTypeScopeScope, firNamedFunctionSymbol2, checkerContext, diagnosticReporter, delegatedWrapperData, firNamedFunction, (FirNamedFunctionSymbol) obj);
            }
        });
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(final CheckerContext checkerContext, final DiagnosticReporter diagnosticReporter, FirClass firClass) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firClass.getClass();
        FirContainingNamesAwareScopeKt.processAllFunctions(FirHelpersKt.unsubstitutedScope(checkerContext, firClass), new Function1() { // from class: g85
            public final Object invoke(Object obj) {
                return FirImplementationByDelegationWithDifferentGenericSignatureChecker.d(checkerContext, diagnosticReporter, (FirNamedFunctionSymbol) obj);
            }
        });
    }
}
