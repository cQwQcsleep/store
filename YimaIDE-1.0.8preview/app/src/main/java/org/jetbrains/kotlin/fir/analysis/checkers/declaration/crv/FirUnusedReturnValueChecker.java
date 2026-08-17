package org.jetbrains.kotlin.fir.analysis.checkers.declaration.crv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.config.ReturnValueCheckerMode;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.analysis.cfa.util.CfgTraverserKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirUnusedCheckerBase;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirUnusedCheckerBaseKt;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.crv.FirUnusedReturnValueChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirMustUseReturnValueStatusComponentKt;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirBooleanOperatorExpression;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirCheckNotNullCall;
import org.jetbrains.kotlin.fir.expressions.FirElvisExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCallOrigin;
import org.jetbrains.kotlin.fir.expressions.FirReturnExpression;
import org.jetbrains.kotlin.fir.expressions.FirSafeCallExpression;
import org.jetbrains.kotlin.fir.expressions.FirTypeOperatorCall;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirControlFlowGraphReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.dfa.FirControlFlowGraphReferenceImpl;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraph;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirEnumEntrySymbol;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.ReturnValueStatus;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001%B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0004\u001a\u00020\u0005H\u0016R\u00020\u0006j\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJA\u0010\t\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u00020\u0006R\u00020\u000bR\u00020\rj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\f\u001a\u00020\u000bj\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010\u0013J1\u0010\u0014\u001a\u00020\u0005*\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002R\u00020\u0006R\u00020\rj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010\u0018JA\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u00172\n\u0010\u001b\u001a\u0006\u0012\u0002\b\u00030\u001c2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u00020\u0006R\u00020\rj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010\u001dJ!\u0010\u001e\u001a\u00020\u0005*\u0006\u0012\u0002\b\u00030\u001cH\u0002R\u00020\u0006j\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\u001fJO\u0010 \u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u001b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001c2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u00020\u0006R\u00020\u000bR\u00020\rj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\f\u001a\u00020\u000bj\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010!J)\u0010\"\u001a\u00060#R\u00020\u0001H\u0014R\u00020\u0006R\u00020\u000bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010$¨\u0006&"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/crv/FirUnusedReturnValueChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirUnusedCheckerBase;", "<init>", "()V", "isEnabled", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;)Z", "checkIfExpressionUnused", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/crv/FirUnusedReturnValueChecker$UsageVisitor;", "visitor", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "data", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirUnusedCheckerBase$UsageState;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/crv/FirUnusedReturnValueChecker$UsageVisitor;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirUnusedCheckerBase$UsageState;)V", "canMarkAllReturnPoints", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "originalFunctionCall", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/crv/FirUnusedReturnValueChecker$UsageVisitor;Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;)Z", "hasContractAndCanBeIgnored", "functionCall", "resolvedSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/crv/FirUnusedReturnValueChecker$UsageVisitor;Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirUnusedCheckerBase$UsageState;)Z", "isSubjectToCheck", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)Z", "reportForSymbol", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/crv/FirUnusedReturnValueChecker$UsageVisitor;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirUnusedCheckerBase$UsageState;)V", "createVisitor", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirUnusedCheckerBase$UsageVisitorBase;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;)Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirUnusedCheckerBase$UsageVisitorBase;", "UsageVisitor", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirUnusedReturnValueChecker extends FirUnusedCheckerBase {
    public static final FirUnusedReturnValueChecker INSTANCE = new FirUnusedReturnValueChecker();

    private FirUnusedReturnValueChecker() {
    }

    public static boolean b(FirFunctionCall firFunctionCall, FirFunctionCall firFunctionCall2) {
        firFunctionCall2.getClass();
        return Intrinsics.areEqual(firFunctionCall2, firFunctionCall);
    }

    /* JADX WARN: Code duplicated, block: B:29:0x0070  */
    private final boolean canMarkAllReturnPoints(CheckerContext checkerContext, UsageVisitor usageVisitor, FirAnonymousFunction firAnonymousFunction, FirFunctionCall firFunctionCall) {
        ControlFlowGraph controlFlowGraph;
        FirReturnExpression firReturnExpression;
        if (firAnonymousFunction.getBody() == null) {
            return false;
        }
        FirControlFlowGraphReference controlFlowGraphReference = firAnonymousFunction.getControlFlowGraphReference();
        FirControlFlowGraphReferenceImpl firControlFlowGraphReferenceImpl = controlFlowGraphReference instanceof FirControlFlowGraphReferenceImpl ? (FirControlFlowGraphReferenceImpl) controlFlowGraphReference : null;
        if (firControlFlowGraphReferenceImpl == null || (controlFlowGraph = firControlFlowGraphReferenceImpl.getControlFlowGraph()) == null) {
            return false;
        }
        List<CFGNode<?>> previousCfgNodes = CfgTraverserKt.getPreviousCfgNodes(controlFlowGraph.getExitNode());
        ArrayList<FirReturnExpression> arrayList = new ArrayList();
        Iterator<T> it = previousCfgNodes.iterator();
        while (it.hasNext()) {
            FirElement fir = ((CFGNode) it.next()).getFir();
            if (fir instanceof FirReturnExpression) {
                firReturnExpression = (FirReturnExpression) fir;
            } else if (fir instanceof FirBlock) {
                Object objLastOrNull = CollectionsKt.lastOrNull(((FirBlock) fir).getStatements());
                firReturnExpression = objLastOrNull instanceof FirReturnExpression ? (FirReturnExpression) objLastOrNull : null;
                if (firReturnExpression == null || !Intrinsics.areEqual(firReturnExpression.getTarget().getLabeledElement(), firAnonymousFunction)) {
                    firReturnExpression = null;
                }
            } else {
                firReturnExpression = null;
            }
            if (firReturnExpression != null) {
                arrayList.add(firReturnExpression);
            }
        }
        for (FirReturnExpression firReturnExpression2 : arrayList) {
            if (!HelpersKt.isIgnorable(checkerContext, FirTypeUtilsKt.getResolvedType(firReturnExpression2.getResult()))) {
                usageVisitor.getReturnsToCheck().put(firReturnExpression2, firFunctionCall);
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:46:0x008b  */
    public final void checkIfExpressionUnused(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, UsageVisitor usageVisitor, FirExpression firExpression, FirUnusedCheckerBase.UsageState usageState) {
        FirCallableSymbol<FirCallableDeclaration> firCallableSymbol;
        FirUnusedReturnValueChecker firUnusedReturnValueChecker;
        CheckerContext checkerContext2;
        UsageVisitor usageVisitor2;
        FirUnusedCheckerBase.UsageState usageState2;
        Name name;
        if (HelpersKt.isIgnorable(checkerContext, FirTypeUtilsKt.getResolvedType(firExpression))) {
            return;
        }
        FirCallableSymbol<?> resolvedCallableSymbol = ReferenceUtilsKt.toResolvedCallableSymbol(firExpression, checkerContext.getSession());
        String strAsString = null;
        if (resolvedCallableSymbol != null) {
            FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) resolvedCallableSymbol.getFir();
            while (ClassMembersKt.isSubstitutionOrIntersectionOverride(firCallableDeclaration)) {
                FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
                if (originalForSubstitutionOverrideAttr == null) {
                    originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
                }
                if (originalForSubstitutionOverrideAttr == null) {
                    break;
                } else {
                    firCallableDeclaration = originalForSubstitutionOverrideAttr;
                }
            }
            FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
            if (symbol == null) {
                x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol<*>");
                return;
            }
            firCallableSymbol = symbol;
        } else {
            firCallableSymbol = null;
        }
        if (firExpression instanceof FirFunctionCall) {
            FirFunctionCall firFunctionCall = (FirFunctionCall) firExpression;
            if (firFunctionCall.getOrigin() == FirFunctionCallOrigin.Operator) {
                if (firCallableSymbol != null && (name = firCallableSymbol.getName()) != null) {
                    strAsString = name.asString();
                }
                if (Intrinsics.areEqual(strAsString, "set")) {
                    return;
                }
            }
            if (firCallableSymbol != null) {
                firUnusedReturnValueChecker = this;
                checkerContext2 = checkerContext;
                usageVisitor2 = usageVisitor;
                usageState2 = usageState;
                if (firUnusedReturnValueChecker.hasContractAndCanBeIgnored(checkerContext2, usageVisitor2, firFunctionCall, firCallableSymbol, usageState2)) {
                    return;
                }
            } else {
                firUnusedReturnValueChecker = this;
                checkerContext2 = checkerContext;
                usageVisitor2 = usageVisitor;
                usageState2 = usageState;
            }
        } else {
            firUnusedReturnValueChecker = this;
            checkerContext2 = checkerContext;
            usageVisitor2 = usageVisitor;
            usageState2 = usageState;
        }
        if ((firExpression instanceof FirBooleanOperatorExpression) && HelpersKt.isIgnorable(checkerContext2, FirTypeUtilsKt.getResolvedType(((FirBooleanOperatorExpression) firExpression).getRightOperand()))) {
            return;
        }
        firUnusedReturnValueChecker.reportForSymbol(checkerContext2, diagnosticReporter, usageVisitor2, firExpression, firCallableSymbol, usageState2);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final boolean hasContractAndCanBeIgnored(CheckerContext checkerContext, UsageVisitor usageVisitor, FirFunctionCall firFunctionCall, FirCallableSymbol<?> firCallableSymbol, FirUnusedCheckerBase.UsageState usageState) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirCallableSymbol<?> resolvedCallableSymbol;
        List<Integer> listIndicesOfPropagatingFunctionalParameters = HelpersKt.indicesOfPropagatingFunctionalParameters(firCallableSymbol);
        ArrayList<FirExpression> arrayList = new ArrayList();
        Iterator<T> it = listIndicesOfPropagatingFunctionalParameters.iterator();
        while (it.hasNext()) {
            FirExpression firExpression = (FirExpression) CollectionsKt.getOrNull(firFunctionCall.getArgumentList().getArguments(), ((Number) it.next()).intValue());
            if (firExpression != null) {
                arrayList.add(firExpression);
            }
        }
        if (arrayList.isEmpty()) {
            return false;
        }
        if ((usageState instanceof FirUnusedCheckerBase.UsageState.UsedInReturn) && (firFunctionCall = usageVisitor.getReturnsToCheck().get(((FirUnusedCheckerBase.UsageState.UsedInReturn) usageState).getReturnExpression())) == null) {
            return true;
        }
        for (FirExpression firExpression2 : arrayList) {
            if (!(firExpression2 instanceof FirAnonymousFunctionExpression ? INSTANCE.canMarkAllReturnPoints(checkerContext, usageVisitor, ((FirAnonymousFunctionExpression) firExpression2).getAnonymousFunction(), firFunctionCall) : (firExpression2 instanceof FirCallableReferenceAccess) && (resolvedCallableSymbol = FirReferenceUtilsKt.toResolvedCallableSymbol(((FirCallableReferenceAccess) firExpression2).getCalleeReference(), true)) != null && (HelpersKt.isIgnorable(checkerContext, resolvedCallableSymbol.getResolvedReturnType()) || !INSTANCE.isSubjectToCheck(checkerContext, resolvedCallableSymbol)))) {
                return false;
            }
        }
        return true;
    }

    private final boolean isSubjectToCheck(CheckerContext checkerContext, FirCallableSymbol<?> firCallableSymbol) {
        if (firCallableSymbol instanceof FirEnumEntrySymbol) {
            return true;
        }
        return firCallableSymbol.getResolvedStatus().getReturnValueStatus() == ReturnValueStatus.MustUse && !FirMustUseReturnValueStatusComponentKt.getMustUseReturnValueStatusComponent(checkerContext.getSession()).hasIgnorableLikeAnnotation(firCallableSymbol.getResolvedAnnotationClassIds());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reportForSymbol(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, UsageVisitor usageVisitor, FirExpression firExpression, FirCallableSymbol<?> firCallableSymbol, FirUnusedCheckerBase.UsageState usageState) {
        if (firCallableSymbol == null || isSubjectToCheck(checkerContext, firCallableSymbol)) {
            Name name = firCallableSymbol != null ? firCallableSymbol.getName() : null;
            FirElement firElement = firExpression;
            if (usageState instanceof FirUnusedCheckerBase.UsageState.UsedInReturn) {
                final FirFunctionCall firFunctionCall = usageVisitor.getReturnsToCheck().get(((FirUnusedCheckerBase.UsageState.UsedInReturn) usageState).getReturnExpression());
                if (firFunctionCall == null) {
                    return;
                }
                CollectionsKt.removeAll(usageVisitor.getReturnsToCheck().values(), new Function1() { // from class: kg5
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(FirUnusedReturnValueChecker.b(firFunctionCall, (FirFunctionCall) obj));
                    }
                });
                firElement = firFunctionCall;
            }
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firElement.getSource(), (KtDiagnosticFactory1) (!Intrinsics.areEqual(usageState, FirUnusedCheckerBase.UsageState.UnusedFromCoercion.INSTANCE) ? FirErrors.INSTANCE.getRETURN_VALUE_NOT_USED() : FirErrors.INSTANCE.getRETURN_VALUE_NOT_USED_COERCION()), (Object) name, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirUnusedCheckerBase
    public FirUnusedCheckerBase.UsageVisitorBase createVisitor(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        return new UsageVisitor(checkerContext, diagnosticReporter);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirUnusedCheckerBase
    public boolean isEnabled(CheckerContext checkerContext) {
        checkerContext.getClass();
        return checkerContext.get$languageVersionSettings().getFlag(AnalysisFlags.getReturnValueCheckerMode()) != ReturnValueCheckerMode.DISABLED;
    }

    @Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00060\u0001R\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0018\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0018\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0018\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0018\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0018\u0010!\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0018\u0010#\u001a\u00020\u00102\u0006\u0010$\u001a\u00020%2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u001d\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006&"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/crv/FirUnusedReturnValueChecker$UsageVisitor;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirUnusedCheckerBase$UsageVisitorBase;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirUnusedCheckerBase;", "context", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "reporter", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "<init>", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;)V", "returnsToCheck", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirReturnExpression;", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "getReturnsToCheck", "()Ljava/util/Map;", "checkExpression", Argument.Delimiters.none, "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "data", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirUnusedCheckerBase$UsageState;", "visitElvisExpression", "elvisExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirElvisExpression;", "visitSafeCallExpression", "safeCallExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirSafeCallExpression;", "visitCheckNotNullCall", "checkNotNullCall", "Lorg/jetbrains/kotlin/fir/expressions/FirCheckNotNullCall;", "visitTypeOperatorCall", "typeOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;", "visitReturnExpression", "returnExpression", "visitCallableReferenceAccess", "callableReferenceAccess", "Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class UsageVisitor extends FirUnusedCheckerBase.UsageVisitorBase {
        private final Map<FirReturnExpression, FirFunctionCall> returnsToCheck;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public UsageVisitor(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter) {
            super(FirUnusedReturnValueChecker.INSTANCE, checkerContext, diagnosticReporter);
            checkerContext.getClass();
            diagnosticReporter.getClass();
            this.returnsToCheck = new HashMap();
        }

        @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirUnusedCheckerBase.UsageVisitorBase
        public void checkExpression(FirExpression expression, FirUnusedCheckerBase.UsageState data) {
            expression.getClass();
            data.getClass();
            if (data instanceof FirUnusedCheckerBase.UsageState.Used) {
                return;
            }
            if (data instanceof FirUnusedCheckerBase.UsageState.UsedInReturn) {
                if (!this.returnsToCheck.containsKey(((FirUnusedCheckerBase.UsageState.UsedInReturn) data).getReturnExpression())) {
                    return;
                }
            } else if (!FirUnusedCheckerBaseKt.hasSideEffect(expression)) {
                return;
            }
            FirUnusedReturnValueChecker.INSTANCE.checkIfExpressionUnused(getContext(), getReporter(), this, expression, data);
        }

        public final Map<FirReturnExpression, FirFunctionCall> getReturnsToCheck() {
            return this.returnsToCheck;
        }

        public void visitCallableReferenceAccess(FirCallableReferenceAccess callableReferenceAccess, FirUnusedCheckerBase.UsageState data) {
            FirCallableSymbol<?> resolvedCallableSymbol;
            callableReferenceAccess.getClass();
            data.getClass();
            if ((data instanceof FirUnusedCheckerBase.UsageState.UsedInReturn) || !HelpersKt.isFunctionalTypeThatReturnsUnit(FirTypeUtilsKt.getResolvedType(callableReferenceAccess), getContext().getSession()) || (resolvedCallableSymbol = FirReferenceUtilsKt.toResolvedCallableSymbol(callableReferenceAccess.getCalleeReference(), true)) == null) {
                return;
            }
            CheckerContext context = getContext();
            DiagnosticReporter reporter = getReporter();
            if (HelpersKt.isIgnorable(context, resolvedCallableSymbol.getResolvedReturnType())) {
                return;
            }
            FirUnusedReturnValueChecker.INSTANCE.reportForSymbol(context, reporter, this, callableReferenceAccess, resolvedCallableSymbol, FirUnusedCheckerBase.UsageState.UnusedFromCoercion.INSTANCE);
        }

        public void visitCheckNotNullCall(FirCheckNotNullCall checkNotNullCall, FirUnusedCheckerBase.UsageState data) {
            checkNotNullCall.getClass();
            data.getClass();
            ((FirExpression) CollectionsKt.first(checkNotNullCall.getArgumentList().getArguments())).accept(this, data);
        }

        public void visitElvisExpression(FirElvisExpression elvisExpression, FirUnusedCheckerBase.UsageState data) {
            elvisExpression.getClass();
            data.getClass();
            elvisExpression.getLhs().accept(this, data);
            elvisExpression.getRhs().accept(this, data);
        }

        public void visitReturnExpression(FirReturnExpression returnExpression, FirUnusedCheckerBase.UsageState data) {
            returnExpression.getClass();
            data.getClass();
            returnExpression.acceptChildren(this, new FirUnusedCheckerBase.UsageState.UsedInReturn(returnExpression));
        }

        public void visitSafeCallExpression(FirSafeCallExpression safeCallExpression, FirUnusedCheckerBase.UsageState data) {
            safeCallExpression.getClass();
            data.getClass();
            safeCallExpression.getSelector().accept(this, data);
        }

        public void visitTypeOperatorCall(FirTypeOperatorCall typeOperatorCall, FirUnusedCheckerBase.UsageState data) {
            typeOperatorCall.getClass();
            data.getClass();
            Iterator<T> it = typeOperatorCall.getArgumentList().getArguments().iterator();
            while (it.hasNext()) {
                ((FirExpression) it.next()).accept(this, data);
            }
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Object visitSafeCallExpression(FirSafeCallExpression firSafeCallExpression, Object obj) {
            visitSafeCallExpression(firSafeCallExpression, (FirUnusedCheckerBase.UsageState) obj);
            return Unit.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Object visitReturnExpression(FirReturnExpression firReturnExpression, Object obj) {
            visitReturnExpression(firReturnExpression, (FirUnusedCheckerBase.UsageState) obj);
            return Unit.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Object visitElvisExpression(FirElvisExpression firElvisExpression, Object obj) {
            visitElvisExpression(firElvisExpression, (FirUnusedCheckerBase.UsageState) obj);
            return Unit.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Object visitCheckNotNullCall(FirCheckNotNullCall firCheckNotNullCall, Object obj) {
            visitCheckNotNullCall(firCheckNotNullCall, (FirUnusedCheckerBase.UsageState) obj);
            return Unit.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Object visitTypeOperatorCall(FirTypeOperatorCall firTypeOperatorCall, Object obj) {
            visitTypeOperatorCall(firTypeOperatorCall, (FirUnusedCheckerBase.UsageState) obj);
            return Unit.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Object visitCallableReferenceAccess(FirCallableReferenceAccess firCallableReferenceAccess, Object obj) {
            visitCallableReferenceAccess(firCallableReferenceAccess, (FirUnusedCheckerBase.UsageState) obj);
            return Unit.INSTANCE;
        }
    }
}
