package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlinx.collections.immutable.PersistentMap;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.EventOccurrencesRangeKt;
import org.jetbrains.kotlin.contracts.description.MarkedEventOccurrencesRange;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.analysis.cfa.FirPropertyInitializationAnalyzerKt;
import org.jetbrains.kotlin.fir.analysis.cfa.PropertyInitializationCheckProcessor;
import org.jetbrains.kotlin.fir.analysis.cfa.util.EventOccurrencesRangeAtNode;
import org.jetbrains.kotlin.fir.analysis.cfa.util.PropertyInitializationInfoData;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirMemberPropertiesChecker;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousInitializer;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirControlFlowGraphOwner;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.references.FirControlFlowGraphReference;
import org.jetbrains.kotlin.fir.resolve.dfa.FirControlFlowGraphReferenceImplKt;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraph;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.NormalPath;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJE\u0010\u000e\u001a\u001e\u0012\f\u0012\n\u0012\u0002\b\u00030\u0010j\u0002`\u0011\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u000fj\u0004\u0018\u0001`\u0013*\u00020\u0002H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0014¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirMemberPropertiesChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirClassChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "collectInitializationInfo", "Lkotlinx/collections/immutable/PersistentMap;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/VariableInitializationEvent;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/EventOccurrencesRangeAtNode;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/VariableInitializationInfo;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)Lkotlinx/collections/immutable/PersistentMap;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirMemberPropertiesChecker extends FirDeclarationChecker<FirClass> {
    public static final FirMemberPropertiesChecker INSTANCE = new FirMemberPropertiesChecker();

    private FirMemberPropertiesChecker() {
        super(MppCheckerKind.Common);
    }

    public static Unit b(Set set, FirCallableSymbol firCallableSymbol) {
        firCallableSymbol.getClass();
        if ((firCallableSymbol instanceof FirPropertySymbol) && FirPropertyInitializationAnalyzerKt.requiresInitialization((FirPropertySymbol) firCallableSymbol, true)) {
            set.add(firCallableSymbol);
        }
        return Unit.INSTANCE;
    }

    private final PersistentMap<FirVariableSymbol<?>, EventOccurrencesRangeAtNode> collectInitializationInfo(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass) {
        FirControlFlowGraphReference controlFlowGraphReference;
        ControlFlowGraph controlFlowGraph;
        FirClass firClass2 = firClass != null ? firClass : null;
        if (firClass2 == null || (controlFlowGraphReference = firClass2.getControlFlowGraphReference()) == null || (controlFlowGraph = FirControlFlowGraphReferenceImplKt.getControlFlowGraph(controlFlowGraphReference)) == null) {
            return null;
        }
        final LinkedHashSet linkedHashSet = new LinkedHashSet();
        org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.processAllDeclaredCallables$default(firClass.getSymbol(), checkerContext.getSession(), null, new Function1() { // from class: qa5
            public final Object invoke(Object obj) {
                return FirMemberPropertiesChecker.b(linkedHashSet, (FirCallableSymbol) obj);
            }
        }, 2, null);
        if (linkedHashSet.isEmpty()) {
            return null;
        }
        PropertyInitializationInfoData propertyInitializationInfoData = new PropertyInitializationInfoData(linkedHashSet, SetsKt.emptySet(), firClass.getSymbol(), controlFlowGraph);
        PropertyInitializationCheckProcessor.INSTANCE.check(checkerContext, diagnosticReporter, propertyInitializationInfoData, true);
        return (PersistentMap) propertyInitializationInfoData.getValue(controlFlowGraph.getExitNode()).get(NormalPath.INSTANCE);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirControlFlowGraphReference controlFlowGraphReference;
        ControlFlowGraph controlFlowGraph;
        CFGNode<?> exitNode;
        MarkedEventOccurrencesRange<CFGNode<?>> range;
        ControlFlowGraph controlFlowGraph2;
        CFGNode<?> enterNode;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firClass.getClass();
        PersistentMap<FirVariableSymbol<?>, EventOccurrencesRangeAtNode> persistentMapCollectInitializationInfo = collectInitializationInfo(checkerContext, diagnosticReporter, firClass);
        FirControlFlowGraphReference controlFlowGraphReference2 = firClass.getControlFlowGraphReference();
        boolean z = (controlFlowGraphReference2 == null || (controlFlowGraph2 = FirControlFlowGraphReferenceImplKt.getControlFlowGraph(controlFlowGraphReference2)) == null || (enterNode = controlFlowGraph2.getEnterNode()) == null || !enterNode.getIsDead()) ? false : true;
        for (FirAnnotationContainer firAnnotationContainer : firClass.getDeclarations()) {
            boolean z2 = firAnnotationContainer instanceof FirProperty;
            if (z2) {
                FirPropertySymbol symbol = ((FirProperty) firAnnotationContainer).getSymbol();
                EventOccurrencesRangeAtNode eventOccurrencesRangeAtNode = persistentMapCollectInitializationInfo != null ? (EventOccurrencesRangeAtNode) persistentMapCollectInitializationInfo.get(symbol) : null;
                FirMemberPropertiesCheckerKt.checkProperty(checkerContext, diagnosticReporter, firClass, symbol, (eventOccurrencesRangeAtNode == null || (range = eventOccurrencesRangeAtNode.getRange()) == null || !EventOccurrencesRangeKt.isDefinitelyVisited(range) || (symbol.getRawStatus().isLateInit() && eventOccurrencesRangeAtNode.getMustBeLateinit())) ? false : true, !z);
            }
            if (z2 || (firAnnotationContainer instanceof FirAnonymousInitializer) || (firAnnotationContainer instanceof FirConstructor)) {
                z = z || !((controlFlowGraphReference = ((FirControlFlowGraphOwner) firAnnotationContainer).getControlFlowGraphReference()) == null || (controlFlowGraph = FirControlFlowGraphReferenceImplKt.getControlFlowGraph(controlFlowGraphReference)) == null || (exitNode = controlFlowGraph.getExitNode()) == null || !exitNode.getIsDead());
            }
        }
    }
}
