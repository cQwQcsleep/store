package androidx.compose.compiler.plugins.kotlin.k2;

import androidx.compose.compiler.plugins.kotlin.ComposeCallableIds;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory3;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousInitializer;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.InlineStatus;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirCatch;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirTryExpression;
import org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentList;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000h\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u001a,\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002\u001a*\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002\u001a \u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002\u001a\u007f\u0010\u0013\u001a\u00020\u0003*\u00020\t2\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00030\u00152\u0014\b\u0002\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00030\u00152\u0014\b\u0002\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00030\u00152\u001a\b\u0002\u0010\u001a\u001a\u0014\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00030\u001b2\u0014\b\u0002\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00030\u0015H\u0082\b\u001a\u0016\u0010 \u001a\u0004\u0018\u00010\u0016*\u00020\t2\u0006\u0010!\u001a\u00020\"H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"runCatchingCallableId", "Lorg/jetbrains/kotlin/name/CallableId;", "checkComposableCall", Argument.Delimiters.none, "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "calleeFunction", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "context", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "reporter", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "checkComposableFunction", "Landroidx/compose/compiler/plugins/kotlin/k2/ComposableCheckForScopeStatus;", "function", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "nonReadOnlyCallInsideFunction", "Lorg/jetbrains/kotlin/KtSourceElement;", "checkInvoke", "visitCurrentScope", "visitInlineLambdaParameter", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "visitAnonymousFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "visitFunction", "visitTryExpression", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/fir/expressions/FirTryExpression;", "Lorg/jetbrains/kotlin/fir/FirElement;", "visitFunctionCall", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "findValueParameterForLambdaAtIndex", "elementIndex", Argument.Delimiters.none, "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ComposableCallCheckerKt {
    private static final CallableId runCatchingCallableId;

    static {
        FqName fqName = new FqName("kotlin");
        Name nameIdentifier = Name.identifier("runCatching");
        nameIdentifier.getClass();
        runCatchingCallableId = new CallableId(fqName, nameIdentifier);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:24:0x00c0  */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void checkComposableCall(FirQualifiedAccessExpression firQualifiedAccessExpression, FirCallableSymbol<?> firCallableSymbol, CheckerContext checkerContext, DiagnosticReporter diagnosticReporter) {
        CheckerContext checkerContext2;
        DiagnosticReporter diagnosticReporter2;
        FirValueParameter firValueParameterFindValueParameterForLambdaAtIndex;
        boolean z;
        if (Intrinsics.areEqual(firCallableSymbol.getCallableId(), ComposeCallableIds.INSTANCE.getKey()) && (firQualifiedAccessExpression instanceof FirFunctionCall) && ((FirCall) firQualifiedAccessExpression).getArgumentList().getArguments().size() == 1) {
            checkerContext2 = checkerContext;
            KtDiagnosticReportHelpersKt.reportOn$default(diagnosticReporter, (AbstractKtSourceElement) ((FirFunctionCall) firQualifiedAccessExpression).getCalleeReference().getSource(), ComposeErrors.INSTANCE.getKEY_CALL_WITH_NO_ARGUMENTS(), (DiagnosticContext) checkerContext2, (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        } else {
            checkerContext2 = checkerContext;
        }
        for (IndexedValue indexedValue : CollectionsKt.reversed(CollectionsKt.withIndex(checkerContext2.getContainingElements()))) {
            int index = indexedValue.getIndex();
            FirElement firElement = (FirElement) indexedValue.component2();
            if (firElement instanceof FirAnonymousFunction) {
                FirAnonymousFunction firAnonymousFunction = (FirAnonymousFunction) firElement;
                InlineStatus inlineStatus = firAnonymousFunction.getInlineStatus();
                InlineStatus inlineStatus2 = InlineStatus.Inline;
                if (inlineStatus != inlineStatus2 || (firValueParameterFindValueParameterForLambdaAtIndex = findValueParameterForLambdaAtIndex(checkerContext2, index)) == null) {
                    diagnosticReporter2 = diagnosticReporter;
                } else {
                    FirBasedSymbol<?> containingDeclarationSymbol = firValueParameterFindValueParameterForLambdaAtIndex.getContainingDeclarationSymbol();
                    if (FirUtilsKt.hasDisallowComposableCallsAnnotation(firValueParameterFindValueParameterForLambdaAtIndex.getReturnTypeRef(), checkerContext2.getSession()) && (containingDeclarationSymbol instanceof FirCallableSymbol)) {
                        CheckerContext checkerContext3 = checkerContext2;
                        KtDiagnosticReportHelpersKt.reportOn$default(diagnosticReporter, (AbstractKtSourceElement) firQualifiedAccessExpression.getCalleeReference().getSource(), ComposeErrors.INSTANCE.getCAPTURED_COMPOSABLE_INVOCATION(), (Object) firValueParameterFindValueParameterForLambdaAtIndex.getSymbol(), (Object) containingDeclarationSymbol, (DiagnosticContext) checkerContext3, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                        diagnosticReporter2 = diagnosticReporter;
                        checkerContext2 = checkerContext3;
                    } else {
                        diagnosticReporter2 = diagnosticReporter;
                    }
                }
                if (FunctionalTypeUtilsKt.functionTypeKind$default(FirTypeUtilsKt.getConeType(firAnonymousFunction.getTypeRef()), checkerContext2.getSession(), false, 2, (Object) null) == ComposableFunction.INSTANCE) {
                    return;
                }
                if (!firAnonymousFunction.getIsLambda()) {
                    if (checkComposableFunction(firAnonymousFunction, FirUtilsKt.isReadOnlyComposable(firCallableSymbol, checkerContext2.getSession()) ? null : firQualifiedAccessExpression.getCalleeReference().getSource(), checkerContext2, diagnosticReporter2) == ComposableCheckForScopeStatus.STOP) {
                        return;
                    }
                }
                if (firAnonymousFunction.getInlineStatus() != inlineStatus2) {
                    break;
                }
            } else {
                if (firElement instanceof FirFunction) {
                    if (checkComposableFunction((FirFunction) firElement, FirUtilsKt.isReadOnlyComposable(firCallableSymbol, checkerContext2.getSession()) ? null : firQualifiedAccessExpression.getCalleeReference().getSource(), checkerContext2, diagnosticReporter) != ComposableCheckForScopeStatus.STOP) {
                        break;
                    } else {
                        return;
                    }
                }
                if (firElement instanceof FirTryExpression) {
                    FirElement firElement2 = (FirElement) CollectionsKt.getOrNull(checkerContext2.getContainingElements(), index + 1);
                    if (firElement2 != null) {
                        FirTryExpression firTryExpression = (FirTryExpression) firElement;
                        if (!(firElement2 instanceof FirCatch) && !Intrinsics.areEqual(firTryExpression.getFinallyBlock(), firElement2)) {
                            KtDiagnosticReportHelpersKt.reportOn$default(diagnosticReporter, (AbstractKtSourceElement) firTryExpression.getSource(), ComposeErrors.INSTANCE.getILLEGAL_TRY_CATCH_AROUND_COMPOSABLE(), (DiagnosticContext) checkerContext2, (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                        }
                        checkerContext2 = checkerContext;
                    }
                } else {
                    if (!(firElement instanceof FirFunctionCall)) {
                        z = true;
                        if (!(firElement instanceof FirProperty) && !(firElement instanceof FirValueParameter) && !(firElement instanceof FirAnonymousObject) && !(firElement instanceof FirAnonymousInitializer)) {
                            if (firElement instanceof FirField) {
                                if (!Intrinsics.areEqual(((FirField) firElement).getOrigin(), FirDeclarationOrigin.Synthetic.DelegateField.INSTANCE)) {
                                    break;
                                }
                            } else if (firElement instanceof FirDeclaration) {
                                break;
                            }
                        }
                    } else {
                        FirFunctionCall firFunctionCall = (FirFunctionCall) firElement;
                        z = true;
                        FirCallableSymbol resolvedCallableSymbol$default = FirReferenceUtilsKt.toResolvedCallableSymbol$default(firFunctionCall.getCalleeReference(), false, 1, null);
                        if (Intrinsics.areEqual(resolvedCallableSymbol$default != null ? resolvedCallableSymbol$default.getCallableId() : null, runCatchingCallableId)) {
                            KtDiagnosticReportHelpersKt.reportOn$default(diagnosticReporter, (AbstractKtSourceElement) firFunctionCall.getSource(), ComposeErrors.INSTANCE.getILLEGAL_RUN_CATCHING_AROUND_COMPOSABLE(), (DiagnosticContext) checkerContext, (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                        }
                    }
                    checkerContext2 = checkerContext;
                }
            }
        }
        KtDiagnosticReportHelpersKt.reportOn$default(diagnosticReporter, (AbstractKtSourceElement) firQualifiedAccessExpression.getCalleeReference().getSource(), ComposeErrors.INSTANCE.getCOMPOSABLE_INVOCATION(), (DiagnosticContext) checkerContext, (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
    }

    private static final ComposableCheckForScopeStatus checkComposableFunction(FirFunction firFunction, KtSourceElement ktSourceElement, CheckerContext checkerContext, DiagnosticReporter diagnosticReporter) {
        if (FirUtilsKt.hasComposableAnnotation(firFunction, checkerContext.getSession())) {
            if (FirUtilsKt.hasReadOnlyComposableAnnotation(firFunction, checkerContext.getSession()) && ktSourceElement != null) {
                KtDiagnosticReportHelpersKt.reportOn$default(diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, ComposeErrors.INSTANCE.getNONREADONLY_CALL_IN_READONLY_COMPOSABLE(), (DiagnosticContext) checkerContext, (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
            return ComposableCheckForScopeStatus.STOP;
        }
        boolean z = firFunction instanceof FirPropertyAccessor;
        if (z) {
            FirPropertyAccessor firPropertyAccessor = (FirPropertyAccessor) firFunction;
            if (firPropertyAccessor.getPropertySymbol().getHasDelegate()) {
                if (firPropertyAccessor.getPropertySymbol().isVar()) {
                    KtDiagnosticReportHelpersKt.reportOn$default(diagnosticReporter, (AbstractKtSourceElement) firPropertyAccessor.getSource(), ComposeErrors.INSTANCE.getCOMPOSE_INVALID_DELEGATE(), (DiagnosticContext) checkerContext, (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
                if (firPropertyAccessor.getPropertySymbol() instanceof FirRegularPropertySymbol) {
                    KtDiagnosticReportHelpersKt.reportOn$default(diagnosticReporter, (AbstractKtSourceElement) firPropertyAccessor.getPropertySymbol().getSource(), ComposeErrors.INSTANCE.getCOMPOSABLE_EXPECTED(), (DiagnosticContext) checkerContext, (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
                return ComposableCheckForScopeStatus.STOP;
            }
        }
        KtDiagnosticReportHelpersKt.reportOn$default(diagnosticReporter, (AbstractKtSourceElement) (z ? ((FirPropertyAccessor) firFunction).getPropertySymbol().getSource() : firFunction.getSource()), ComposeErrors.INSTANCE.getCOMPOSABLE_EXPECTED(), (DiagnosticContext) checkerContext, (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        return ComposableCheckForScopeStatus.CONTINUE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkInvoke(FirQualifiedAccessExpression firQualifiedAccessExpression, CheckerContext checkerContext, DiagnosticReporter diagnosticReporter) {
        FirNamedReference calleeReference;
        FirValueParameterSymbol resolvedValueParameterSymbol$default;
        FirValueParameter firValueParameterFindValueParameterForLambdaAtIndex;
        FirExpression dispatchReceiver = firQualifiedAccessExpression.getDispatchReceiver();
        FirPropertyAccessExpression firPropertyAccessExpression = dispatchReceiver instanceof FirPropertyAccessExpression ? (FirPropertyAccessExpression) dispatchReceiver : null;
        if (firPropertyAccessExpression == null || (calleeReference = firPropertyAccessExpression.getCalleeReference()) == null || (resolvedValueParameterSymbol$default = FirReferenceUtilsKt.toResolvedValueParameterSymbol$default(calleeReference, false, 1, null)) == null || FirUtilsKt.hasDisallowComposableCallsAnnotation(resolvedValueParameterSymbol$default.getResolvedReturnTypeRef(), checkerContext.getSession())) {
            return;
        }
        FirBasedSymbol<?> containingDeclarationSymbol = resolvedValueParameterSymbol$default.getContainingDeclarationSymbol();
        if ((containingDeclarationSymbol instanceof FirCallableSymbol) && ((FirCallableSymbol) containingDeclarationSymbol).getRawStatus().isInline()) {
            for (IndexedValue indexedValue : CollectionsKt.reversed(CollectionsKt.withIndex(checkerContext.getContainingElements()))) {
                int index = indexedValue.getIndex();
                FirElement firElement = (FirElement) indexedValue.component2();
                if (firElement instanceof FirAnonymousFunction) {
                    FirAnonymousFunction firAnonymousFunction = (FirAnonymousFunction) firElement;
                    InlineStatus inlineStatus = firAnonymousFunction.getInlineStatus();
                    InlineStatus inlineStatus2 = InlineStatus.Inline;
                    if (inlineStatus == inlineStatus2 && (firValueParameterFindValueParameterForLambdaAtIndex = findValueParameterForLambdaAtIndex(checkerContext, index)) != null && FirUtilsKt.hasDisallowComposableCallsAnnotation(firValueParameterFindValueParameterForLambdaAtIndex.getReturnTypeRef(), checkerContext.getSession())) {
                        KtSourceElement source = resolvedValueParameterSymbol$default.getSource();
                        KtDiagnosticFactory3 missing_disallow_composable_calls_annotation = ComposeErrors.INSTANCE.getMISSING_DISALLOW_COMPOSABLE_CALLS_ANNOTATION();
                        FirValueParameterSymbol symbol = firValueParameterFindValueParameterForLambdaAtIndex.getSymbol();
                        FirBasedSymbol<?> containingDeclarationSymbol2 = firValueParameterFindValueParameterForLambdaAtIndex.getContainingDeclarationSymbol();
                        containingDeclarationSymbol2.getClass();
                        KtDiagnosticReportHelpersKt.reportOn(diagnosticReporter, (AbstractKtSourceElement) source, (KtDiagnosticFactory3<FirValueParameterSymbol, FirValueParameterSymbol, FirCallableSymbol>) ((KtDiagnosticFactory3<Object, Object, Object>) missing_disallow_composable_calls_annotation), resolvedValueParameterSymbol$default, symbol, (FirCallableSymbol) containingDeclarationSymbol2, (DiagnosticContext) checkerContext, (64 & 64) != 0 ? null : null);
                    }
                    if (firAnonymousFunction.getInlineStatus() != inlineStatus2) {
                        return;
                    }
                } else {
                    if (firElement instanceof FirFunction) {
                        return;
                    }
                    if (firElement instanceof FirTryExpression) {
                    } else if (!(firElement instanceof FirFunctionCall) && !(firElement instanceof FirProperty) && !(firElement instanceof FirValueParameter) && !(firElement instanceof FirAnonymousObject) && !(firElement instanceof FirAnonymousInitializer)) {
                        if (firElement instanceof FirField) {
                            if (!Intrinsics.areEqual(((FirField) firElement).getOrigin(), FirDeclarationOrigin.Synthetic.DelegateField.INSTANCE)) {
                                return;
                            }
                        } else if (firElement instanceof FirDeclaration) {
                            return;
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FirValueParameter findValueParameterForLambdaAtIndex(CheckerContext checkerContext, int i) {
        Object next;
        Object orNull = CollectionsKt.getOrNull(checkerContext.getContainingElements(), i);
        FirAnonymousFunction firAnonymousFunction = orNull instanceof FirAnonymousFunction ? (FirAnonymousFunction) orNull : null;
        if (firAnonymousFunction == null) {
            return null;
        }
        Object orNull2 = CollectionsKt.getOrNull(checkerContext.getContainingElements(), i - 1);
        FirResolvedArgumentList firResolvedArgumentList = orNull2 instanceof FirResolvedArgumentList ? (FirResolvedArgumentList) orNull2 : null;
        if (firResolvedArgumentList == null) {
            return null;
        }
        Iterator<T> it = firResolvedArgumentList.getArguments().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            FirExpression firExpression = (FirExpression) next;
            if ((firExpression instanceof FirAnonymousFunctionExpression) && Intrinsics.areEqual(((FirAnonymousFunctionExpression) firExpression).getAnonymousFunction(), firAnonymousFunction)) {
                break;
            }
        }
        FirExpression firExpression2 = (FirExpression) next;
        if (firExpression2 == null) {
            return null;
        }
        return firResolvedArgumentList.getMapping().get(firExpression2);
    }

    private static final void visitCurrentScope(CheckerContext checkerContext, Function1<? super FirValueParameter, Unit> function1, Function1<? super FirAnonymousFunction, Unit> function2, Function1<? super FirFunction, Unit> function3, Function2<? super FirTryExpression, ? super FirElement, Unit> function4, Function1<? super FirFunctionCall, Unit> function5) {
        FirValueParameter firValueParameterFindValueParameterForLambdaAtIndex;
        for (IndexedValue indexedValue : CollectionsKt.reversed(CollectionsKt.withIndex(checkerContext.getContainingElements()))) {
            int index = indexedValue.getIndex();
            FirElement firElement = (FirElement) indexedValue.component2();
            if (firElement instanceof FirAnonymousFunction) {
                FirAnonymousFunction firAnonymousFunction = (FirAnonymousFunction) firElement;
                InlineStatus inlineStatus = firAnonymousFunction.getInlineStatus();
                InlineStatus inlineStatus2 = InlineStatus.Inline;
                if (inlineStatus == inlineStatus2 && (firValueParameterFindValueParameterForLambdaAtIndex = findValueParameterForLambdaAtIndex(checkerContext, index)) != null) {
                    function1.invoke(firValueParameterFindValueParameterForLambdaAtIndex);
                }
                function2.invoke(firElement);
                if (firAnonymousFunction.getInlineStatus() != inlineStatus2) {
                    return;
                }
            } else {
                if (firElement instanceof FirFunction) {
                    function3.invoke(firElement);
                    return;
                }
                if (firElement instanceof FirTryExpression) {
                    FirElement firElement2 = (FirElement) CollectionsKt.getOrNull(checkerContext.getContainingElements(), index + 1);
                    if (firElement2 != null) {
                        function4.invoke(firElement, firElement2);
                    }
                } else if (firElement instanceof FirFunctionCall) {
                    function5.invoke(firElement);
                } else if (!(firElement instanceof FirProperty) && !(firElement instanceof FirValueParameter) && !(firElement instanceof FirAnonymousObject) && !(firElement instanceof FirAnonymousInitializer)) {
                    if (firElement instanceof FirField) {
                        if (!Intrinsics.areEqual(((FirField) firElement).getOrigin(), FirDeclarationOrigin.Synthetic.DelegateField.INSTANCE)) {
                            return;
                        }
                    } else if (firElement instanceof FirDeclaration) {
                        return;
                    }
                }
            }
        }
    }

    public static /* synthetic */ void visitCurrentScope$default(CheckerContext checkerContext, Function1 function1, Function1 function2, Function1 function3, Function2 function4, Function1 function5, int i, Object obj) {
        FirValueParameter firValueParameterFindValueParameterForLambdaAtIndex;
        if ((i & 2) != 0) {
            function2 = visitCurrentScope.1.INSTANCE;
        }
        if ((i & 4) != 0) {
            function3 = visitCurrentScope.2.INSTANCE;
        }
        if ((i & 8) != 0) {
            function4 = visitCurrentScope.3.INSTANCE;
        }
        if ((i & 16) != 0) {
            function5 = visitCurrentScope.4.INSTANCE;
        }
        for (IndexedValue indexedValue : CollectionsKt.reversed(CollectionsKt.withIndex(checkerContext.getContainingElements()))) {
            int index = indexedValue.getIndex();
            FirElement firElement = (FirElement) indexedValue.component2();
            if (firElement instanceof FirAnonymousFunction) {
                FirAnonymousFunction firAnonymousFunction = (FirAnonymousFunction) firElement;
                InlineStatus inlineStatus = firAnonymousFunction.getInlineStatus();
                InlineStatus inlineStatus2 = InlineStatus.Inline;
                if (inlineStatus == inlineStatus2 && (firValueParameterFindValueParameterForLambdaAtIndex = findValueParameterForLambdaAtIndex(checkerContext, index)) != null) {
                    function1.invoke(firValueParameterFindValueParameterForLambdaAtIndex);
                }
                function2.invoke(firElement);
                if (firAnonymousFunction.getInlineStatus() != inlineStatus2) {
                    return;
                }
            } else {
                if (firElement instanceof FirFunction) {
                    function3.invoke(firElement);
                    return;
                }
                if (firElement instanceof FirTryExpression) {
                    FirElement firElement2 = (FirElement) CollectionsKt.getOrNull(checkerContext.getContainingElements(), index + 1);
                    if (firElement2 != null) {
                        function4.invoke(firElement, firElement2);
                    }
                } else if (firElement instanceof FirFunctionCall) {
                    function5.invoke(firElement);
                } else if (!(firElement instanceof FirProperty) && !(firElement instanceof FirValueParameter) && !(firElement instanceof FirAnonymousObject) && !(firElement instanceof FirAnonymousInitializer)) {
                    if (firElement instanceof FirField) {
                        if (!Intrinsics.areEqual(((FirField) firElement).getOrigin(), FirDeclarationOrigin.Synthetic.DelegateField.INSTANCE)) {
                            return;
                        }
                    } else if (firElement instanceof FirDeclaration) {
                        return;
                    }
                }
            }
        }
    }
}
