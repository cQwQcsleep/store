package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2JsArgumentConstants;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory0;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirKeywordUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.FirDiagnosticHolder;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.FirWhileLoop;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.resolve.calls.AbstractCandidate;
import org.jetbrains.kotlin.fir.resolve.calls.InapplicableNullableReceiver;
import org.jetbrains.kotlin.fir.resolve.calls.OperatorCallOfNonOperatorFunction;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionDiagnostic;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeAmbiguityError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeConstraintSystemHasContradiction;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeInapplicableCandidateError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeInapplicableWrongReceiver;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeUnresolvedNameError;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.lexer.KtKeywordToken;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicabilityKt;
import org.jetbrains.kotlin.util.OperatorNameConventions;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ\u007f\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0016\u0010\u0014\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00170\u00160\u00152\u0006\u0010\u0018\u001a\u00020\u00192\u001a\b\u0002\u0010\u001a\u001a\u0014\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00170\u0016\u0018\u00010\u00152\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0019H\u0002R\u00020\nR\u00020\bj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u001c¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirForLoopChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirBlockChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirBlock;)V", "checkSpecialFunctionCall", Argument.Delimiters.none, K2JsArgumentConstants.CALL, "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "reportSource", "Lorg/jetbrains/kotlin/KtSourceElement;", "ambiguityFactory", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "missingFactory", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", "noneApplicableFactory", "nullableReceiverFactory", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;)Z", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirForLoopChecker extends FirExpressionChecker<FirBlock> {
    public static final FirForLoopChecker INSTANCE = new FirForLoopChecker();

    private FirForLoopChecker() {
        super(MppCheckerKind.Common);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean checkSpecialFunctionCall(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirFunctionCall firFunctionCall, KtSourceElement ktSourceElement, KtDiagnosticFactory1<Collection<FirBasedSymbol<?>>> ktDiagnosticFactory1, KtDiagnosticFactory0 ktDiagnosticFactory0, KtDiagnosticFactory1<Collection<FirBasedSymbol<?>>> ktDiagnosticFactory2, KtDiagnosticFactory0 ktDiagnosticFactory3) {
        FirNamedReference calleeReference = firFunctionCall.getCalleeReference();
        if (!FirReferenceUtilsKt.isError(calleeReference)) {
            if (calleeReference instanceof FirResolvedNamedReference) {
                FirBasedSymbol<?> resolvedSymbol = ((FirResolvedNamedReference) calleeReference).getResolvedSymbol();
                if ((resolvedSymbol instanceof FirNamedFunctionSymbol) && !((FirCallableSymbol) resolvedSymbol).getResolvedStatus().isOperator()) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory1) FirErrors.INSTANCE.getOPERATOR_MODIFIER_REQUIRED(), (Object) resolvedSymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                }
            }
            return false;
        }
        ConeDiagnostic diagnostic = ((FirDiagnosticHolder) calleeReference).getDiagnostic();
        if (diagnostic instanceof ConeAmbiguityError) {
            ConeAmbiguityError coneAmbiguityError = (ConeAmbiguityError) diagnostic;
            KtDiagnosticFactory1<Collection<FirBasedSymbol<?>>> ktDiagnosticFactory4 = (CandidateApplicabilityKt.isSuccess(coneAmbiguityError.getApplicability()) || ktDiagnosticFactory2 == null) ? ktDiagnosticFactory1 : ktDiagnosticFactory2;
            Collection<AbstractCandidate> candidates = coneAmbiguityError.getCandidates();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(candidates, 10));
            Iterator<T> it = candidates.iterator();
            while (it.hasNext()) {
                arrayList.add(((AbstractCandidate) it.next()).getSymbol());
            }
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory1) ktDiagnosticFactory4, (Object) arrayList, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        } else if (diagnostic instanceof ConeUnresolvedNameError) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, ktDiagnosticFactory0, (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        } else if (diagnostic instanceof ConeInapplicableWrongReceiver) {
            if (ktDiagnosticFactory2 != null) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory1) ktDiagnosticFactory2, (Object) ((ConeInapplicableWrongReceiver) diagnostic).getCandidateSymbols(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            } else {
                if (!Intrinsics.areEqual(calleeReference.getName(), OperatorNameConventions.ITERATOR)) {
                    k2d.a("ConeInapplicableWrongReceiver, but no diagnostic reported");
                    return false;
                }
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, ktDiagnosticFactory0, (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        } else if (diagnostic instanceof ConeConstraintSystemHasContradiction) {
            if (Intrinsics.areEqual(calleeReference.getName(), OperatorNameConventions.ITERATOR)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, ktDiagnosticFactory0, (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        } else if ((diagnostic instanceof ConeInapplicableCandidateError) && (ktDiagnosticFactory3 != null || ktDiagnosticFactory2 != null)) {
            ConeInapplicableCandidateError coneInapplicableCandidateError = (ConeInapplicableCandidateError) diagnostic;
            List<ResolutionDiagnostic> diagnostics = coneInapplicableCandidateError.getCandidate().getDiagnostics();
            ArrayList<ResolutionDiagnostic> arrayList2 = new ArrayList();
            for (Object obj : diagnostics) {
                if (((ResolutionDiagnostic) obj).getApplicability() == coneInapplicableCandidateError.getApplicability()) {
                    arrayList2.add(obj);
                }
            }
            for (ResolutionDiagnostic resolutionDiagnostic : arrayList2) {
                if (resolutionDiagnostic instanceof InapplicableNullableReceiver) {
                    if (ktDiagnosticFactory3 != null) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, ktDiagnosticFactory3, (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    } else {
                        ktDiagnosticFactory2.getClass();
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory1) ktDiagnosticFactory2, (Object) CollectionsKt.listOf(coneInapplicableCandidateError.getCandidate().getSymbol()), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                    }
                    return true;
                }
                if (resolutionDiagnostic instanceof OperatorCallOfNonOperatorFunction) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory1) FirErrors.INSTANCE.getOPERATOR_MODIFIER_REQUIRED(), (Object) ((OperatorCallOfNonOperatorFunction) resolutionDiagnostic).getFunction(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                }
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ boolean checkSpecialFunctionCall$default(FirForLoopChecker firForLoopChecker, DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirFunctionCall firFunctionCall, KtSourceElement ktSourceElement, KtDiagnosticFactory1 ktDiagnosticFactory1, KtDiagnosticFactory0 ktDiagnosticFactory0, KtDiagnosticFactory1 ktDiagnosticFactory2, KtDiagnosticFactory0 ktDiagnosticFactory3, int i, Object obj) {
        if ((i & 64) != 0) {
            ktDiagnosticFactory2 = null;
        }
        if ((i & 128) != 0) {
            ktDiagnosticFactory3 = null;
        }
        return firForLoopChecker.checkSpecialFunctionCall(diagnosticReporter, checkerContext, firFunctionCall, ktSourceElement, ktDiagnosticFactory1, ktDiagnosticFactory0, ktDiagnosticFactory2, ktDiagnosticFactory3);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirBlock firBlock) {
        KtSourceElement source;
        KtSourceElement source2;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firBlock.getClass();
        KtSourceElement source3 = firBlock.getSource();
        KtSourceElementKind kind = null;
        KtSourceElementKind kind2 = source3 != null ? source3.getKind() : null;
        KtFakeSourceElementKind.DesugaredForLoop desugaredForLoop = KtFakeSourceElementKind.DesugaredForLoop.INSTANCE;
        if (Intrinsics.areEqual(kind2, desugaredForLoop)) {
            List<FirStatement> statements = firBlock.getStatements();
            FirStatement firStatement = statements.get(0);
            FirProperty firProperty = firStatement instanceof FirProperty ? (FirProperty) firStatement : null;
            if (firProperty == null) {
                return;
            }
            FirStatement firStatement2 = statements.get(1);
            FirWhileLoop firWhileLoop = firStatement2 instanceof FirWhileLoop ? (FirWhileLoop) firStatement2 : null;
            if (firWhileLoop == null) {
                return;
            }
            KtSourceElement source4 = firProperty.getSource();
            if (Intrinsics.areEqual(source4 != null ? source4.getKind() : null, desugaredForLoop)) {
                FirExpression initializer = firProperty.getInitializer();
                initializer.getClass();
                FirFunctionCall firFunctionCall = (FirFunctionCall) initializer;
                FirExpression explicitReceiver = firFunctionCall.getExplicitReceiver();
                if (explicitReceiver == null || (source = explicitReceiver.getSource()) == null) {
                    source = firFunctionCall.getSource();
                }
                KtSourceElement ktSourceElement = source;
                FirErrors firErrors = FirErrors.INSTANCE;
                if (checkSpecialFunctionCall$default(this, diagnosticReporter, checkerContext, firFunctionCall, ktSourceElement, firErrors.getITERATOR_AMBIGUITY(), firErrors.getITERATOR_MISSING(), null, firErrors.getITERATOR_ON_NULLABLE(), 64, null)) {
                    return;
                }
                FirExpression condition = firWhileLoop.getCondition();
                condition.getClass();
                checkSpecialFunctionCall$default(this, diagnosticReporter, checkerContext, (FirFunctionCall) condition, ktSourceElement, firErrors.getHAS_NEXT_FUNCTION_AMBIGUITY(), firErrors.getHAS_NEXT_MISSING(), firErrors.getHAS_NEXT_FUNCTION_NONE_APPLICABLE(), null, 128, null);
                Object objFirstOrNull = CollectionsKt.firstOrNull(firWhileLoop.getBlock().getStatements());
                FirProperty firProperty2 = objFirstOrNull instanceof FirProperty ? (FirProperty) objFirstOrNull : null;
                if (firProperty2 == null) {
                    return;
                }
                FirExpression initializer2 = firProperty2.getInitializer();
                if (initializer2 != null && (source2 = initializer2.getSource()) != null) {
                    kind = source2.getKind();
                }
                if (Intrinsics.areEqual(kind, desugaredForLoop)) {
                    FirExpression initializer3 = firProperty2.getInitializer();
                    initializer3.getClass();
                    checkSpecialFunctionCall$default(this, diagnosticReporter, checkerContext, (FirFunctionCall) initializer3, ktSourceElement, firErrors.getNEXT_AMBIGUITY(), firErrors.getNEXT_MISSING(), firErrors.getNEXT_NONE_APPLICABLE(), null, 128, null);
                    KtSourceElement source5 = firProperty2.getSource();
                    KtKeywordToken valOrVarKeyword = FirKeywordUtilsKt.getValOrVarKeyword(source5);
                    if (valOrVarKeyword != null) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source5, (KtDiagnosticFactory1) firErrors.getVAL_OR_VAR_ON_LOOP_PARAMETER(), (Object) valOrVarKeyword, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                    }
                }
            }
        }
    }
}
