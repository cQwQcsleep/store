package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirInlineDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.expressions.FirArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirFunctionTypeConversionExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvable;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentList;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ArrayUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.util.OperatorNameConventions;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003:\u0001\u000eB\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirInlineBodyResolvableExpressionChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirBasicExpressionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirStatement;)V", "InlinableParameterContext", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirInlineBodyResolvableExpressionChecker extends FirExpressionChecker<FirStatement> {
    public static final FirInlineBodyResolvableExpressionChecker INSTANCE = new FirInlineBodyResolvableExpressionChecker();

    @Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ7\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00122\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0014R\u00020\rR\u00020\u000fj\u0006\u0010\u000e\u001a\u00020\rj\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u0015JC\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u001bH\u0002R\u00020\rR\u00020\u000fj\u0006\u0010\u000e\u001a\u00020\rj\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u001cJ\u0019\u0010\u001d\u001a\u00020\u001eH\u0002R\u00020\rj\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010\u001fJ/\u0010 \u001a\u0010\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020#\u0018\u00010!2\u0006\u0010$\u001a\u00020%H\u0002R\u00020\rj\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010&J\u0010\u0010'\u001a\u00020\u001e*\u0006\u0012\u0002\b\u00030(H\u0002J9\u0010)\u001a\u00020\f2\u0006\u0010*\u001a\u00020+2\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u001bH\u0002R\u00020\rR\u00020\u000fj\u0006\u0010\u000e\u001a\u00020\rj\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010,J\u0010\u0010-\u001a\u00020\u001e*\u0006\u0012\u0002\b\u00030\u001bH\u0002J\u001d\u0010.\u001a\u00020\u001e*\u00020\u0012H\u0002R\u00020\rj\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010/J\f\u00100\u001a\u00020\u001a*\u00020\u001aH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirInlineBodyResolvableExpressionChecker$InlinableParameterContext;", Argument.Delimiters.none, "inlineFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "inlinableParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirFunction;Ljava/util/List;Lorg/jetbrains/kotlin/fir/FirSession;)V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "statement", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "targetSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirStatement;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)V", "checkReceiver", "qualifiedAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "receiverExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)V", "isNonLocalReturnAllowed", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;)Z", "extractCallAndParameter", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "anonymousFunction", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirAnonymousFunctionSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirAnonymousFunctionSymbol;)Lkotlin/Pair;", "isArrayLambdaConstructor", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "checkArgumentsOfCall", "functionCall", "Lorg/jetbrains/kotlin/fir/expressions/FirCall;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirCall;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)V", "isInvokeOfSomeFunctionType", "partOfCall", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/expressions/FirStatement;)Z", "unwrapToPotentialParameterUsage", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class InlinableParameterContext {
        private final List<FirValueParameterSymbol> inlinableParameters;
        private final FirFunction inlineFunction;
        private final FirSession session;

        public InlinableParameterContext(FirFunction firFunction, List<FirValueParameterSymbol> list, FirSession firSession) {
            firFunction.getClass();
            list.getClass();
            firSession.getClass();
            this.inlineFunction = firFunction;
            this.inlinableParameters = list;
            this.session = firSession;
        }

        private final void checkArgumentsOfCall(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCall firCall, FirBasedSymbol<?> firBasedSymbol) {
            Object next;
            KtDiagnosticFactory1<FirBasedSymbol<?>> usage_is_not_inlinable;
            if (checkerContext.getIsContractBody()) {
                return;
            }
            FirFunctionSymbol firFunctionSymbol = firBasedSymbol instanceof FirFunctionSymbol ? (FirFunctionSymbol) firBasedSymbol : null;
            if (firFunctionSymbol == null) {
                return;
            }
            FirArgumentList argumentList = firCall.getArgumentList();
            LinkedHashMap<FirExpression, FirValueParameter> mapping = argumentList instanceof FirResolvedArgumentList ? ((FirResolvedArgumentList) argumentList).getMapping() : null;
            if (mapping == null) {
                return;
            }
            for (Map.Entry<FirExpression, FirValueParameter> entry : mapping.entrySet()) {
                FirExpression key = entry.getKey();
                FirValueParameter value = entry.getValue();
                FirExpression firExpressionUnwrapToPotentialParameterUsage = unwrapToPotentialParameterUsage(key);
                FirCallableSymbol<?> resolvedCallableSymbol = org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt.toResolvedCallableSymbol(firExpressionUnwrapToPotentialParameterUsage, this.session);
                FirVariableSymbol firVariableSymbol = resolvedCallableSymbol instanceof FirVariableSymbol ? (FirVariableSymbol) resolvedCallableSymbol : null;
                if (firVariableSymbol != null) {
                    Iterator<T> it = this.inlinableParameters.iterator();
                    do {
                        if (!it.hasNext()) {
                            next = null;
                            break;
                        }
                        next = it.next();
                    } while (!Intrinsics.areEqual((FirValueParameterSymbol) next, firVariableSymbol));
                    FirValueParameterSymbol firValueParameterSymbol = (FirValueParameterSymbol) next;
                    if (firValueParameterSymbol != null) {
                        if (!firFunctionSymbol.getRawStatus().isInline()) {
                            usage_is_not_inlinable = FirErrors.INSTANCE.getUSAGE_IS_NOT_INLINABLE();
                        } else if (!DeclarationUtilsKt.isInlinable(value, this.session)) {
                            usage_is_not_inlinable = FirErrors.INSTANCE.getUSAGE_IS_NOT_INLINABLE();
                        } else if (!firValueParameterSymbol.isCrossinline() && (value.getIsCrossinline() || !isNonLocalReturnAllowed(checkerContext))) {
                            usage_is_not_inlinable = FirErrors.INSTANCE.getNON_LOCAL_RETURN_NOT_ALLOWED();
                        }
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firExpressionUnwrapToPotentialParameterUsage.getSource(), (KtDiagnosticFactory1) usage_is_not_inlinable, (Object) firValueParameterSymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                    }
                }
            }
        }

        private final void checkReceiver(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirQualifiedAccessExpression firQualifiedAccessExpression, FirExpression firExpression, FirBasedSymbol<?> firBasedSymbol) {
            if (firExpression == null) {
                return;
            }
            FirCallableSymbol<?> resolvedCallableSymbol = org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt.toResolvedCallableSymbol(FirExpressionUtilKt.unwrapErrorExpression(firExpression), this.session);
            FirValueParameterSymbol firValueParameterSymbol = resolvedCallableSymbol instanceof FirValueParameterSymbol ? (FirValueParameterSymbol) resolvedCallableSymbol : null;
            if (firValueParameterSymbol != null && this.inlinableParameters.contains(firValueParameterSymbol)) {
                if (!isInvokeOfSomeFunctionType(firBasedSymbol) || (firQualifiedAccessExpression instanceof FirCallableReferenceAccess)) {
                    KtSourceElement source = firExpression.getSource();
                    if (source == null) {
                        source = firQualifiedAccessExpression.getSource();
                    }
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, (KtDiagnosticFactory1) FirErrors.INSTANCE.getUSAGE_IS_NOT_INLINABLE(), (Object) firValueParameterSymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                    return;
                }
                if (firValueParameterSymbol.isCrossinline() || isNonLocalReturnAllowed(checkerContext)) {
                    return;
                }
                KtSourceElement source2 = firExpression.getSource();
                if (source2 == null) {
                    source2 = firQualifiedAccessExpression.getSource();
                }
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source2, (KtDiagnosticFactory1) FirErrors.INSTANCE.getNON_LOCAL_RETURN_NOT_ALLOWED(), (Object) firValueParameterSymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
        }

        private final Pair<FirFunctionCall, FirValueParameter> extractCallAndParameter(CheckerContext checkerContext, FirAnonymousFunctionSymbol firAnonymousFunctionSymbol) {
            FirAnonymousFunction anonymousFunction;
            for (FirStatement firStatement : checkerContext.getCallsOrAssignments()) {
                if (firStatement instanceof FirFunctionCall) {
                    FirArgumentList argumentList = ((FirCall) firStatement).getArgumentList();
                    LinkedHashMap<FirExpression, FirValueParameter> mapping = argumentList instanceof FirResolvedArgumentList ? ((FirResolvedArgumentList) argumentList).getMapping() : null;
                    if (mapping == null) {
                        continue;
                    } else {
                        for (Map.Entry<FirExpression, FirValueParameter> entry : mapping.entrySet()) {
                            FirExpression key = entry.getKey();
                            FirValueParameter value = entry.getValue();
                            FirExpression firExpressionUnwrapArgument = FirExpressionUtilKt.unwrapArgument(key);
                            FirAnonymousFunctionExpression firAnonymousFunctionExpression = firExpressionUnwrapArgument instanceof FirAnonymousFunctionExpression ? (FirAnonymousFunctionExpression) firExpressionUnwrapArgument : null;
                            if (((firAnonymousFunctionExpression == null || (anonymousFunction = firAnonymousFunctionExpression.getAnonymousFunction()) == null) ? null : anonymousFunction.getSymbol()) == firAnonymousFunctionSymbol) {
                                return TuplesKt.to(firStatement, value);
                            }
                        }
                    }
                }
            }
            return null;
        }

        private final boolean isArrayLambdaConstructor(FirFunctionSymbol<?> firFunctionSymbol) {
            if (!(firFunctionSymbol instanceof FirConstructorSymbol)) {
                return false;
            }
            FirConstructorSymbol firConstructorSymbol = (FirConstructorSymbol) firFunctionSymbol;
            return firConstructorSymbol.getValueParameterSymbols().size() == 2 && ArrayUtilsKt.isArrayOrPrimitiveArray(firConstructorSymbol.getResolvedReturnType());
        }

        private final boolean isInvokeOfSomeFunctionType(FirBasedSymbol<?> firBasedSymbol) {
            ConeSimpleKotlinType dispatchReceiverType;
            if (!(firBasedSymbol instanceof FirNamedFunctionSymbol)) {
                return false;
            }
            FirNamedFunctionSymbol firNamedFunctionSymbol = (FirNamedFunctionSymbol) firBasedSymbol;
            return Intrinsics.areEqual(firNamedFunctionSymbol.getName(), OperatorNameConventions.INVOKE) && (dispatchReceiverType = firNamedFunctionSymbol.getDispatchReceiverType()) != null && FunctionalTypeUtilsKt.isSomeFunctionType(dispatchReceiverType, this.session);
        }

        private final boolean isNonLocalReturnAllowed(CheckerContext checkerContext) {
            Pair<FirFunctionCall, FirValueParameter> pairExtractCallAndParameter;
            List<FirBasedSymbol<?>> containingDeclarations = checkerContext.getContainingDeclarations();
            int iIndexOf = containingDeclarations.indexOf(this.inlineFunction.getSymbol());
            if (iIndexOf == -1) {
                return true;
            }
            int size = containingDeclarations.size();
            for (int i = iIndexOf + 1; i < size; i++) {
                FirBasedSymbol<?> firBasedSymbol = containingDeclarations.get(i);
                if ((firBasedSymbol instanceof FirFunctionSymbol) || (firBasedSymbol instanceof FirClassSymbol)) {
                    FirAnonymousFunctionSymbol firAnonymousFunctionSymbol = firBasedSymbol instanceof FirAnonymousFunctionSymbol ? (FirAnonymousFunctionSymbol) firBasedSymbol : null;
                    if (firAnonymousFunctionSymbol == null || (pairExtractCallAndParameter = extractCallAndParameter(checkerContext, firAnonymousFunctionSymbol)) == null) {
                        return false;
                    }
                    FirFunctionCall firFunctionCall = (FirFunctionCall) pairExtractCallAndParameter.component1();
                    FirValueParameter firValueParameter = (FirValueParameter) pairExtractCallAndParameter.component2();
                    FirCallableSymbol<?> resolvedCallableSymbol = org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt.toResolvedCallableSymbol(firFunctionCall);
                    FirFunctionSymbol<?> firFunctionSymbol = resolvedCallableSymbol instanceof FirFunctionSymbol ? (FirFunctionSymbol) resolvedCallableSymbol : null;
                    if (firFunctionSymbol == null) {
                        return false;
                    }
                    if ((!firFunctionSymbol.getRawStatus().isInline() && !isArrayLambdaConstructor(firFunctionSymbol)) || firValueParameter.getIsNoinline() || firValueParameter.getIsCrossinline()) {
                        return false;
                    }
                }
            }
            return true;
        }

        private final boolean partOfCall(CheckerContext checkerContext, FirStatement firStatement) {
            FirStatement firStatement2;
            FirExpression explicitReceiver;
            if (!(firStatement instanceof FirExpression) || (firStatement2 = (FirStatement) CollectionsKt.getOrNull(checkerContext.getCallsOrAssignments(), checkerContext.getCallsOrAssignments().size() - 2)) == null) {
                return false;
            }
            FirQualifiedAccessExpression firQualifiedAccessExpression = firStatement2 instanceof FirQualifiedAccessExpression ? (FirQualifiedAccessExpression) firStatement2 : null;
            if (Intrinsics.areEqual(firStatement, (firQualifiedAccessExpression == null || (explicitReceiver = firQualifiedAccessExpression.getExplicitReceiver()) == null) ? null : FirExpressionUtilKt.unwrapErrorExpression(explicitReceiver))) {
                return true;
            }
            FirCall firCall = firStatement2 instanceof FirCall ? (FirCall) firStatement2 : null;
            if (firCall == null) {
                return false;
            }
            List<FirExpression> arguments = firCall.getArgumentList().getArguments();
            if ((arguments instanceof Collection) && arguments.isEmpty()) {
                return false;
            }
            Iterator<T> it = arguments.iterator();
            while (it.hasNext()) {
                if (Intrinsics.areEqual(unwrapToPotentialParameterUsage((FirExpression) it.next()), firStatement)) {
                    return true;
                }
            }
            return false;
        }

        private final FirExpression unwrapToPotentialParameterUsage(FirExpression firExpression) {
            FirExpression expression;
            FirExpression firExpressionUnwrapToPotentialParameterUsage;
            FirExpression firExpressionUnwrapArgument = FirExpressionUtilKt.unwrapArgument(FirExpressionUtilKt.unwrapErrorExpression(firExpression));
            if (firExpressionUnwrapArgument == firExpression) {
                firExpressionUnwrapArgument = null;
            }
            if (firExpressionUnwrapArgument != null) {
                return unwrapToPotentialParameterUsage(firExpressionUnwrapArgument);
            }
            FirFunctionTypeConversionExpression firFunctionTypeConversionExpression = firExpression instanceof FirFunctionTypeConversionExpression ? (FirFunctionTypeConversionExpression) firExpression : null;
            return (firFunctionTypeConversionExpression == null || (expression = firFunctionTypeConversionExpression.getExpression()) == null || (firExpressionUnwrapToPotentialParameterUsage = unwrapToPotentialParameterUsage(expression)) == null) ? firExpression : firExpressionUnwrapToPotentialParameterUsage;
        }

        public final void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirStatement firStatement, FirCallableSymbol<?> firCallableSymbol) {
            checkerContext.getClass();
            diagnosticReporter.getClass();
            firStatement.getClass();
            firCallableSymbol.getClass();
            KtSourceElement source = firStatement.getSource();
            if (source == null) {
                return;
            }
            if (CollectionsKt.contains(this.inlinableParameters, firCallableSymbol)) {
                if (!partOfCall(checkerContext, firStatement)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, (KtDiagnosticFactory1) FirErrors.INSTANCE.getUSAGE_IS_NOT_INLINABLE(), (Object) firCallableSymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                }
                List<FirBasedSymbol<?>> containingDeclarations = checkerContext.getContainingDeclarations();
                if (!(containingDeclarations instanceof Collection) || !containingDeclarations.isEmpty()) {
                    Iterator<T> it = containingDeclarations.iterator();
                    while (it.hasNext()) {
                        if (CollectionsKt.contains(this.inlinableParameters, (FirBasedSymbol) it.next())) {
                            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, (KtDiagnosticFactory1) FirErrors.INSTANCE.getNOT_SUPPORTED_INLINE_PARAMETER_IN_INLINE_PARAMETER_DEFAULT_VALUE(), firCallableSymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                            break;
                        }
                    }
                }
            }
            if (firStatement instanceof FirQualifiedAccessExpression) {
                FirQualifiedAccessExpression firQualifiedAccessExpression = (FirQualifiedAccessExpression) firStatement;
                checkReceiver(checkerContext, diagnosticReporter, firQualifiedAccessExpression, firQualifiedAccessExpression.getDispatchReceiver(), firCallableSymbol);
                checkReceiver(checkerContext, diagnosticReporter, firQualifiedAccessExpression, firQualifiedAccessExpression.getExtensionReceiver(), firCallableSymbol);
            }
            if (firStatement instanceof FirCall) {
                checkArgumentsOfCall(checkerContext, diagnosticReporter, (FirCall) firStatement, firCallableSymbol);
            }
        }
    }

    private FirInlineBodyResolvableExpressionChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirStatement firStatement) {
        FirCallableSymbol<?> resolvedCallableSymbol;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firStatement.getClass();
        InlinableParameterContext inlinableParameterContext = checkerContext.getInlinableParameterContext();
        if (inlinableParameterContext == null) {
            return;
        }
        FirInlineDeclarationChecker.InlineFunctionBodyContext inlineFunctionBodyContext = checkerContext.getInlineFunctionBodyContext();
        if (((firStatement instanceof FirQualifiedAccessExpression) || (firStatement instanceof FirDelegatedConstructorCall)) && (resolvedCallableSymbol = org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt.toResolvedCallableSymbol((FirResolvable) firStatement)) != null) {
            if (inlineFunctionBodyContext != null) {
                inlineFunctionBodyContext.check(checkerContext, diagnosticReporter, firStatement, resolvedCallableSymbol);
            }
            inlinableParameterContext.check(checkerContext, diagnosticReporter, firStatement, resolvedCallableSymbol);
        }
    }
}
