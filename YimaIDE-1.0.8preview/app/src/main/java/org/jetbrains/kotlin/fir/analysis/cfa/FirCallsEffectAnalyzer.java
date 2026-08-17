package org.jetbrains.kotlin.fir.analysis.cfa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.collections.immutable.PersistentMap;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.EventOccurrencesRange;
import org.jetbrains.kotlin.contracts.description.KtCallsEffectDeclaration;
import org.jetbrains.kotlin.contracts.description.KtEffectDeclaration;
import org.jetbrains.kotlin.contracts.description.MarkedEventOccurrencesRange;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory3;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.cfa.FirCallsEffectAnalyzer;
import org.jetbrains.kotlin.fir.analysis.cfa.util.CfgTraverserKt;
import org.jetbrains.kotlin.fir.analysis.cfa.util.EventCollectingControlFlowGraphVisitor;
import org.jetbrains.kotlin.fir.analysis.cfa.util.EventCollectingControlFlowGraphVisitorKt;
import org.jetbrains.kotlin.fir.analysis.cfa.util.EventOccurrencesRangeAtNode;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.cfa.FirControlFlowChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.contracts.ContractUtilsKt;
import org.jetbrains.kotlin.fir.contracts.FirContractDescription;
import org.jetbrains.kotlin.fir.contracts.FirEffectDeclaration;
import org.jetbrains.kotlin.fir.contracts.FirResolvedContractDescription;
import org.jetbrains.kotlin.fir.declarations.FirContractDescriptionOwner;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.expressions.FirArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentList;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.references.FirThisReference;
import org.jetbrains.kotlin.fir.resolve.CallableIdUtilsKt;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNodeWithSubgraphs;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraph;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.EdgeLabel;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.FunctionCallExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.NormalPath;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.PropertyInitializerEnterNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.VariableAssignmentNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.VariableDeclarationExitNode;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReceiverParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirThisOwnerSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.fir.util.MultimapKt;
import org.jetbrains.kotlin.fir.util.SetMultimap;
import org.jetbrains.kotlin.name.CallableId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001aB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\fJ\f\u0010\r\u001a\u00020\u000e*\u00020\u000eH\u0002J.\u0010\u000f\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0011\u0012\u0004\u0012\u00020\u00120\u0010*\u00020\u000b2\u0010\u0010\u0013\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110\u0014H\u0002J(\u0010\u0015\u001a\u00020\u0005*\u00020\u00162\u001a\u0010\u0017\u001a\u0016\u0012\u0004\u0012\u00020\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0012\u0004\u0012\u00020\u00050\u0018H\u0002J\u0012\u0010\u0019\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0011*\u00020\u0012H\u0002¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/cfa/FirCallsEffectAnalyzer;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/cfa/FirControlFlowChecker;", "<init>", "()V", "analyze", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "graph", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;)V", "coerceToInvocationKind", "Lorg/jetbrains/kotlin/contracts/description/EventOccurrencesRange;", "findNonInPlaceUsesOf", "Lorg/jetbrains/kotlin/fir/util/SetMultimap;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "lambdaSymbols", Argument.Delimiters.none, "forEachArgument", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "block", "Lkotlin/Function2;", "qualifiedAccessSymbol", "InvocationDataCollector", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirCallsEffectAnalyzer extends FirControlFlowChecker {
    public static final FirCallsEffectAnalyzer INSTANCE = new FirCallsEffectAnalyzer();

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u0010\u0012\f\u0012\n\u0012\u0002\b\u00030\u0002j\u0002`\u00030\u0001B\u0019\u0012\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0090\u0001\u0010\u000b\u001a>\u0012\u0004\u0012\u00020\r\u00120\u0012.\u0012\f\u0012\n\u0012\u0002\b\u00030\u0002j\u0002`\u0003\u0012\u0004\u0012\u00020\u000e0\fj\u0016\u0012\f\u0012\n\u0012\u0002\b\u00030\u0002j\u0002`\u0003\u0012\u0004\u0012\u00020\u000e`\u000f0\fj\u0002`\u00102\u0006\u0010\u0011\u001a\u00020\u00122B\u0010\u0013\u001a>\u0012\u0004\u0012\u00020\r\u00120\u0012.\u0012\f\u0012\n\u0012\u0002\b\u00030\u0002j\u0002`\u0003\u0012\u0004\u0012\u00020\u000e0\fj\u0016\u0012\f\u0012\n\u0012\u0002\b\u00030\u0002j\u0002`\u0003\u0012\u0004\u0012\u00020\u000e`\u000f0\fj\u0002`\u0010H\u0016R\u001b\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/cfa/FirCallsEffectAnalyzer$InvocationDataCollector;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/EventCollectingControlFlowGraphVisitor;", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/LambdaInvocationEvent;", "lambdaSymbols", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "<init>", "(Ljava/util/Set;)V", "getLambdaSymbols", "()Ljava/util/Set;", "visitFunctionCallExitNode", "Lkotlinx/collections/immutable/PersistentMap;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EdgeLabel;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/EventOccurrencesRangeAtNode;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/ControlFlowInfo;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/PathAwareLambdaInvocationInfo;", "node", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionCallExitNode;", "data", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class InvocationDataCollector extends EventCollectingControlFlowGraphVisitor<FirBasedSymbol<?>> {
        private final Set<FirCallableSymbol<?>> lambdaSymbols;

        /* JADX WARN: Multi-variable type inference failed */
        public InvocationDataCollector(Set<? extends FirCallableSymbol<?>> set) {
            set.getClass();
            this.lambdaSymbols = set;
        }

        public static Unit a(Ref.ObjectRef objectRef, FunctionCallExitNode functionCallExitNode, InvocationDataCollector invocationDataCollector, FirExpression firExpression, EventOccurrencesRange eventOccurrencesRange) {
            firExpression.getClass();
            if (eventOccurrencesRange != null) {
                FirCallableSymbol firCallableSymbolQualifiedAccessSymbol = FirCallsEffectAnalyzer.INSTANCE.qualifiedAccessSymbol(firExpression);
                if (firCallableSymbolQualifiedAccessSymbol != null) {
                    if (!invocationDataCollector.lambdaSymbols.contains(firCallableSymbolQualifiedAccessSymbol)) {
                        firCallableSymbolQualifiedAccessSymbol = null;
                    }
                    if (firCallableSymbolQualifiedAccessSymbol != null) {
                        objectRef.element = EventCollectingControlFlowGraphVisitorKt.addRange((PersistentMap) objectRef.element, firCallableSymbolQualifiedAccessSymbol, new EventOccurrencesRangeAtNode(eventOccurrencesRange.at(functionCallExitNode), false));
                    }
                }
                return Unit.INSTANCE;
            }
            return Unit.INSTANCE;
        }

        public final Set<FirCallableSymbol<?>> getLambdaSymbols() {
            return this.lambdaSymbols;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
        public PersistentMap<EdgeLabel, PersistentMap<FirBasedSymbol<?>, EventOccurrencesRangeAtNode>> visitFunctionCallExitNode(final FunctionCallExitNode node, PersistentMap<EdgeLabel, ? extends PersistentMap<FirBasedSymbol<?>, EventOccurrencesRangeAtNode>> data) {
            node.getClass();
            data.getClass();
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = visitNode((CFGNode<?>) node, (PersistentMap) data);
            FirCallsEffectAnalyzer.INSTANCE.forEachArgument(node.getFir(), new Function2() { // from class: org.jetbrains.kotlin.fir.analysis.cfa.a
                public final Object invoke(Object obj, Object obj2) {
                    return FirCallsEffectAnalyzer.InvocationDataCollector.a(objectRef, node, this, (FirExpression) obj, (EventOccurrencesRange) obj2);
                }
            });
            return (PersistentMap) objectRef.element;
        }
    }

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[EventOccurrencesRange.values().length];
            try {
                iArr[EventOccurrencesRange.ZERO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[EventOccurrencesRange.MORE_THAN_ONCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private FirCallsEffectAnalyzer() {
        super(MppCheckerKind.Common);
    }

    public static Unit a(boolean z, Set set, SetMultimap setMultimap, FirExpression firExpression, EventOccurrencesRange eventOccurrencesRange) {
        firExpression.getClass();
        if (!z || eventOccurrencesRange == null) {
            findNonInPlaceUsesOf$mark(firExpression, set, setMultimap);
        }
        return Unit.INSTANCE;
    }

    private final EventOccurrencesRange coerceToInvocationKind(EventOccurrencesRange eventOccurrencesRange) {
        int i = WhenMappings.$EnumSwitchMapping$0[eventOccurrencesRange.ordinal()];
        if (i != 1) {
            return i != 2 ? eventOccurrencesRange : EventOccurrencesRange.AT_LEAST_ONCE;
        }
        return EventOccurrencesRange.AT_MOST_ONCE;
    }

    private final SetMultimap<FirCallableSymbol<?>, FirExpression> findNonInPlaceUsesOf(ControlFlowGraph controlFlowGraph, Set<? extends FirCallableSymbol<?>> set) {
        SetMultimap<FirCallableSymbol<?>, FirExpression> multimapOf = MultimapKt.setMultimapOf();
        findNonInPlaceUsesOf$scan(controlFlowGraph, set, multimapOf, true);
        return multimapOf;
    }

    private static final void findNonInPlaceUsesOf$mark(FirExpression firExpression, Set<? extends FirCallableSymbol<?>> set, SetMultimap<FirCallableSymbol<?>, FirExpression> setMultimap) {
        FirCallableSymbol<?> firCallableSymbolQualifiedAccessSymbol = INSTANCE.qualifiedAccessSymbol(firExpression);
        if (firCallableSymbolQualifiedAccessSymbol != null && set.contains(firCallableSymbolQualifiedAccessSymbol)) {
            setMultimap.put(firCallableSymbolQualifiedAccessSymbol, firExpression);
        }
    }

    /* JADX WARN: Code duplicated, block: B:39:0x00ac  */
    private static final void findNonInPlaceUsesOf$scan(ControlFlowGraph controlFlowGraph, final Set<? extends FirCallableSymbol<?>> set, final SetMultimap<FirCallableSymbol<?>, FirExpression> setMultimap, final boolean z) {
        boolean z2;
        FirDeclaration declaration;
        for (CFGNode<?> cFGNode : controlFlowGraph.getNodes()) {
            if (cFGNode instanceof PropertyInitializerEnterNode) {
                PropertyInitializerEnterNode propertyInitializerEnterNode = (PropertyInitializerEnterNode) cFGNode;
                FirExpression initializer = propertyInitializerEnterNode.getFir().getInitializer();
                if (initializer != null) {
                    findNonInPlaceUsesOf$mark(initializer, set, setMultimap);
                }
                FirExpression delegate = propertyInitializerEnterNode.getFir().getDelegate();
                if (delegate != null) {
                    findNonInPlaceUsesOf$mark(delegate, set, setMultimap);
                }
            } else if (cFGNode instanceof VariableDeclarationExitNode) {
                VariableDeclarationExitNode variableDeclarationExitNode = (VariableDeclarationExitNode) cFGNode;
                FirExpression initializer2 = variableDeclarationExitNode.getFir().getInitializer();
                if (initializer2 != null) {
                    findNonInPlaceUsesOf$mark(initializer2, set, setMultimap);
                }
                FirExpression delegate2 = variableDeclarationExitNode.getFir().getDelegate();
                if (delegate2 != null) {
                    findNonInPlaceUsesOf$mark(delegate2, set, setMultimap);
                }
            } else if (cFGNode instanceof VariableAssignmentNode) {
                findNonInPlaceUsesOf$mark(((VariableAssignmentNode) cFGNode).getFir().getRValue(), set, setMultimap);
            } else if (cFGNode instanceof FunctionCallExitNode) {
                INSTANCE.forEachArgument(((FunctionCallExitNode) cFGNode).getFir(), new Function2() { // from class: ez4
                    public final Object invoke(Object obj, Object obj2) {
                        return FirCallsEffectAnalyzer.a(z, set, setMultimap, (FirExpression) obj, (EventOccurrencesRange) obj2);
                    }
                });
            }
            if (cFGNode instanceof CFGNodeWithSubgraphs) {
                for (ControlFlowGraph controlFlowGraph2 : ((CFGNodeWithSubgraphs) cFGNode).getSubGraphs()) {
                    if (!z || (declaration = controlFlowGraph2.getDeclaration()) == null) {
                        z2 = false;
                    } else {
                        z2 = true;
                        if (!FirPropertyInitializationAnalyzerKt.getEvaluatedInPlace(declaration)) {
                            z2 = false;
                        }
                    }
                    findNonInPlaceUsesOf$scan(controlFlowGraph2, set, setMultimap, z2);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:40:0x0086  */
    /* JADX WARN: Code duplicated, block: B:70:0x00fa  */
    /* JADX WARN: Code duplicated, block: B:90:0x014e  */
    public final void forEachArgument(FirFunctionCall firFunctionCall, Function2<? super FirExpression, ? super EventOccurrencesRange, Unit> function2) {
        ArrayList arrayList;
        EventOccurrencesRange kind;
        Object next;
        LinkedHashMap<FirExpression, FirValueParameter> mapping;
        EventOccurrencesRange kind2;
        Object next2;
        List<FirValueParameterSymbol> valueParameterSymbols;
        EventOccurrencesRange kind3;
        Object next3;
        CallableId callableId;
        FirResolvedContractDescription resolvedContractDescription;
        List<FirEffectDeclaration> effects;
        FirCallableSymbol<?> resolvedCallableSymbol = ReferenceUtilsKt.toResolvedCallableSymbol(firFunctionCall);
        FirFunctionSymbol firFunctionSymbol = resolvedCallableSymbol instanceof FirFunctionSymbol ? (FirFunctionSymbol) resolvedCallableSymbol : null;
        if (firFunctionSymbol == null || (resolvedContractDescription = firFunctionSymbol.getResolvedContractDescription()) == null || (effects = resolvedContractDescription.getEffects()) == null) {
            arrayList = null;
        } else {
            arrayList = new ArrayList();
            Iterator<T> it = effects.iterator();
            while (it.hasNext()) {
                KtEffectDeclaration<ConeKotlinType, ConeDiagnostic> effect = ((FirEffectDeclaration) it.next()).getEffect();
                KtCallsEffectDeclaration ktCallsEffectDeclaration = effect instanceof KtCallsEffectDeclaration ? (KtCallsEffectDeclaration) effect : null;
                if (ktCallsEffectDeclaration != null) {
                    arrayList.add(ktCallsEffectDeclaration);
                }
            }
        }
        FirExpression explicitReceiver = firFunctionCall.getExplicitReceiver();
        if (explicitReceiver != null) {
            if (firFunctionSymbol != null && (callableId = firFunctionSymbol.getCallableId()) != null && CallableIdUtilsKt.isFunctionOrSuspendFunctionInvoke(callableId)) {
                kind3 = EventOccurrencesRange.EXACTLY_ONCE;
            } else if (arrayList != null) {
                Iterator it2 = arrayList.iterator();
                do {
                    if (!it2.hasNext()) {
                        next3 = null;
                        break;
                    }
                    next3 = it2.next();
                } while (((KtCallsEffectDeclaration) next3).getValueParameterReference().getParameterIndex() != -1);
                KtCallsEffectDeclaration ktCallsEffectDeclaration2 = (KtCallsEffectDeclaration) next3;
                if (ktCallsEffectDeclaration2 != null) {
                    kind3 = ktCallsEffectDeclaration2.getKind();
                } else {
                    kind3 = null;
                }
            } else {
                kind3 = null;
            }
            function2.invoke(explicitReceiver, kind3);
        }
        FirArgumentList argumentList = firFunctionCall.getArgumentList();
        FirResolvedArgumentList firResolvedArgumentList = argumentList instanceof FirResolvedArgumentList ? (FirResolvedArgumentList) argumentList : null;
        if (firResolvedArgumentList != null && (mapping = firResolvedArgumentList.getMapping()) != null) {
            for (Map.Entry<FirExpression, FirValueParameter> entry : mapping.entrySet()) {
                FirExpression key = entry.getKey();
                int iIndexOf = (firFunctionSymbol == null || (valueParameterSymbols = firFunctionSymbol.getValueParameterSymbols()) == null) ? -1 : valueParameterSymbols.indexOf(entry.getValue().getSymbol());
                if (iIndexOf < 0 || arrayList == null) {
                    kind2 = null;
                } else {
                    Iterator it3 = arrayList.iterator();
                    do {
                        if (!it3.hasNext()) {
                            next2 = null;
                            break;
                        }
                        next2 = it3.next();
                    } while (((KtCallsEffectDeclaration) next2).getValueParameterReference().getParameterIndex() != iIndexOf);
                    KtCallsEffectDeclaration ktCallsEffectDeclaration3 = (KtCallsEffectDeclaration) next2;
                    if (ktCallsEffectDeclaration3 != null) {
                        kind2 = ktCallsEffectDeclaration3.getKind();
                    } else {
                        kind2 = null;
                    }
                }
                function2.invoke(key, kind2);
            }
        }
        int i = 0;
        for (Object obj : firFunctionCall.getContextArguments()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            FirExpression firExpression = (FirExpression) obj;
            if (arrayList != null) {
                Iterator it4 = arrayList.iterator();
                do {
                    if (!it4.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it4.next();
                } while (((KtCallsEffectDeclaration) next).getValueParameterReference().getParameterIndex() != firFunctionSymbol.getValueParameterSymbols().size() + i);
                KtCallsEffectDeclaration ktCallsEffectDeclaration4 = (KtCallsEffectDeclaration) next;
                if (ktCallsEffectDeclaration4 != null) {
                    kind = ktCallsEffectDeclaration4.getKind();
                } else {
                    kind = null;
                }
            } else {
                kind = null;
            }
            function2.invoke(firExpression, kind);
            i = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FirCallableSymbol<?> qualifiedAccessSymbol(FirExpression firExpression) {
        FirExpression firExpressionUnwrapArgument = FirExpressionUtilKt.unwrapArgument(firExpression);
        FirQualifiedAccessExpression firQualifiedAccessExpression = firExpressionUnwrapArgument instanceof FirQualifiedAccessExpression ? (FirQualifiedAccessExpression) firExpressionUnwrapArgument : null;
        FirReference calleeReference = firQualifiedAccessExpression != null ? firQualifiedAccessExpression.getCalleeReference() : null;
        if (calleeReference instanceof FirResolvedNamedReference) {
            FirBasedSymbol<?> resolvedSymbol = ((FirResolvedNamedReference) calleeReference).getResolvedSymbol();
            if (resolvedSymbol instanceof FirCallableSymbol) {
                return (FirCallableSymbol) resolvedSymbol;
            }
            return null;
        }
        if (calleeReference instanceof FirThisReference) {
            FirThisOwnerSymbol<?> boundSymbol = ((FirThisReference) calleeReference).getBoundSymbol();
            FirReceiverParameterSymbol firReceiverParameterSymbol = boundSymbol instanceof FirReceiverParameterSymbol ? (FirReceiverParameterSymbol) boundSymbol : null;
            FirBasedSymbol<?> containingDeclarationSymbol = firReceiverParameterSymbol != null ? firReceiverParameterSymbol.getContainingDeclarationSymbol() : null;
            if (containingDeclarationSymbol instanceof FirCallableSymbol) {
                return (FirCallableSymbol) containingDeclarationSymbol;
            }
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.cfa.FirControlFlowChecker
    public void analyze(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, ControlFlowGraph controlFlowGraph) {
        FirContractDescription contractDescription;
        EventOccurrencesRange withoutMarker;
        EventOccurrencesRangeAtNode eventOccurrencesRangeAtNode;
        MarkedEventOccurrencesRange<CFGNode<?>> range;
        FirTypeRef returnTypeRef;
        ConeKotlinType coneType;
        diagnosticReporter.getClass();
        checkerContext.getClass();
        controlFlowGraph.getClass();
        Iterator<ControlFlowGraph> it = controlFlowGraph.getSubGraphs().iterator();
        while (it.hasNext()) {
            analyze(diagnosticReporter, checkerContext, it.next());
        }
        CheckerContext checkerContext2 = checkerContext;
        FirDeclaration declaration = controlFlowGraph.getDeclaration();
        FirFunction firFunction = declaration instanceof FirFunction ? (FirFunction) declaration : null;
        if (firFunction == null) {
            return;
        }
        FirContractDescriptionOwner firContractDescriptionOwner = firFunction instanceof FirContractDescriptionOwner ? (FirContractDescriptionOwner) firFunction : null;
        if (firContractDescriptionOwner == null || (contractDescription = firContractDescriptionOwner.getContractDescription()) == null) {
            return;
        }
        Map mapCreateMapBuilder = MapsKt.createMapBuilder();
        List<FirEffectDeclaration> effects = ContractUtilsKt.getEffects(contractDescription);
        if (effects != null) {
            for (FirEffectDeclaration firEffectDeclaration : effects) {
                KtEffectDeclaration<ConeKotlinType, ConeDiagnostic> effect = firEffectDeclaration.getEffect();
                KtCallsEffectDeclaration ktCallsEffectDeclaration = effect instanceof KtCallsEffectDeclaration ? (KtCallsEffectDeclaration) effect : null;
                if (ktCallsEffectDeclaration != null) {
                    int parameterIndex = ktCallsEffectDeclaration.getValueParameterReference().getParameterIndex();
                    if (parameterIndex == -1) {
                        FirReceiverParameter receiverParameter = firFunction.getReceiverParameter();
                        returnTypeRef = receiverParameter != null ? receiverParameter.getTypeRef() : null;
                    } else {
                        returnTypeRef = (parameterIndex < 0 || parameterIndex >= ((FirContractDescriptionOwner) firFunction).getValueParameters().size()) ? FirDeclarationUtilKt.contextParametersForFunctionOrContainingProperty(firFunction).get(parameterIndex - ((FirContractDescriptionOwner) firFunction).getValueParameters().size()).getReturnTypeRef() : ((FirContractDescriptionOwner) firFunction).getValueParameters().get(parameterIndex).getReturnTypeRef();
                    }
                    if (returnTypeRef != null && (coneType = FirTypeUtilsKt.getConeType(returnTypeRef)) != null && FunctionalTypeUtilsKt.isSomeFunctionType(coneType, checkerContext2.getSession())) {
                        mapCreateMapBuilder.put(parameterIndex == -1 ? firFunction.getSymbol() : (parameterIndex < 0 || parameterIndex >= ((FirContractDescriptionOwner) firFunction).getValueParameters().size()) ? FirDeclarationUtilKt.contextParametersForFunctionOrContainingProperty(firFunction).get(parameterIndex - ((FirContractDescriptionOwner) firFunction).getValueParameters().size()).getSymbol() : ((FirContractDescriptionOwner) firFunction).getValueParameters().get(parameterIndex).getSymbol(), firEffectDeclaration);
                    }
                }
            }
        }
        Map mapBuild = MapsKt.build(mapCreateMapBuilder);
        if (mapBuild.isEmpty()) {
            return;
        }
        SetMultimap<FirCallableSymbol<?>, FirExpression> setMultimapFindNonInPlaceUsesOf = findNonInPlaceUsesOf(controlFlowGraph, mapBuild.keySet());
        Map mapTraverseToFixedPoint = CfgTraverserKt.traverseToFixedPoint(controlFlowGraph, new InvocationDataCollector(SetsKt.minus(mapBuild.keySet(), setMultimapFindNonInPlaceUsesOf.getKeys())));
        Iterator it2 = setMultimapFindNonInPlaceUsesOf.iterator();
        while (it2.hasNext()) {
            Map.Entry entry = (Map.Entry) it2.next();
            FirCallableSymbol firCallableSymbol = (FirCallableSymbol) entry.getKey();
            Set set = (Set) entry.getValue();
            FirEffectDeclaration firEffectDeclaration2 = (FirEffectDeclaration) mapBuild.get(firCallableSymbol);
            KtDiagnosticReportHelpersKt.reportOn$default(checkerContext2, diagnosticReporter, firEffectDeclaration2 != null ? firEffectDeclaration2.getSource() : null, FirErrors.INSTANCE.getLEAKED_IN_PLACE_LAMBDA(), firCallableSymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            Iterator it3 = set.iterator();
            while (it3.hasNext()) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirExpression) it3.next()).getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getLEAKED_IN_PLACE_LAMBDA(), (Object) firCallableSymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
            checkerContext2 = checkerContext;
        }
        for (Map.Entry entry2 : mapBuild.entrySet()) {
            FirCallableSymbol firCallableSymbol2 = (FirCallableSymbol) entry2.getKey();
            FirEffectDeclaration firEffectDeclaration3 = (FirEffectDeclaration) entry2.getValue();
            KtEffectDeclaration<ConeKotlinType, ConeDiagnostic> effect2 = firEffectDeclaration3.getEffect();
            effect2.getClass();
            EventOccurrencesRange kind = ((KtCallsEffectDeclaration) effect2).getKind();
            PersistentMap persistentMap = (PersistentMap) ((PersistentMap) MapsKt.getValue(mapTraverseToFixedPoint, controlFlowGraph.getExitNode())).get(NormalPath.INSTANCE);
            if (persistentMap == null || (eventOccurrencesRangeAtNode = (EventOccurrencesRangeAtNode) persistentMap.get(firCallableSymbol2)) == null || (range = eventOccurrencesRangeAtNode.getRange()) == null || (withoutMarker = range.getWithoutMarker()) == null) {
                withoutMarker = EventOccurrencesRange.ZERO;
            }
            EventOccurrencesRange eventOccurrencesRangeCoerceToInvocationKind = coerceToInvocationKind(withoutMarker);
            if (!kind.contains(withoutMarker)) {
                KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firEffectDeclaration3.getSource(), (KtDiagnosticFactory3<FirCallableSymbol, EventOccurrencesRange, EventOccurrencesRange>) ((KtDiagnosticFactory3<Object, Object, Object>) FirErrors.INSTANCE.getWRONG_INVOCATION_KIND()), firCallableSymbol2, kind, eventOccurrencesRangeCoerceToInvocationKind, (64 & 64) != 0 ? null : null);
            }
        }
    }
}
