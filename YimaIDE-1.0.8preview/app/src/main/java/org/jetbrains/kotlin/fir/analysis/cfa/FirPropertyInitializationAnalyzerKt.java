package org.jetbrains.kotlin.fir.analysis.cfa;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.EventOccurrencesRangeKt;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraph;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousObjectSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirBackingFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirSyntheticPropertySymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\n\u0010\u0007\u001a\u00020\b*\u00020\b\u001a\u0012\u0010\t\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0001\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0019\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00058F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0006¨\u0006\f"}, d2 = {"evaluatedInPlace", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "getEvaluatedInPlace", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Z", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "(Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)Z", "nearestNonInPlaceGraph", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", "requiresInitialization", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "isForInitialization", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirPropertyInitializationAnalyzerKt {
    public static final boolean getEvaluatedInPlace(FirBasedSymbol<?> firBasedSymbol) {
        firBasedSymbol.getClass();
        if (firBasedSymbol instanceof FirAnonymousFunctionSymbol) {
            return EventOccurrencesRangeKt.isInPlace(((FirAnonymousFunctionSymbol) firBasedSymbol).getInvocationKind());
        }
        if (firBasedSymbol instanceof FirAnonymousObjectSymbol) {
            return ((FirAnonymousObjectSymbol) firBasedSymbol).getClassKind() != ClassKind.ENUM_ENTRY;
        }
        if (firBasedSymbol instanceof FirConstructorSymbol) {
            return true;
        }
        return ((firBasedSymbol instanceof FirFunctionSymbol) || (firBasedSymbol instanceof FirClassSymbol)) ? false : true;
    }

    public static final ControlFlowGraph nearestNonInPlaceGraph(ControlFlowGraph controlFlowGraph) {
        CFGNode cFGNode;
        ControlFlowGraph owner;
        ControlFlowGraph controlFlowGraphNearestNonInPlaceGraph;
        controlFlowGraph.getClass();
        FirDeclaration declaration = controlFlowGraph.getDeclaration();
        return (declaration == null || !getEvaluatedInPlace(declaration) || (cFGNode = (CFGNode) CollectionsKt.firstOrNull(controlFlowGraph.getEnterNode().getPreviousNodes())) == null || (owner = cFGNode.getOwner()) == null || (controlFlowGraphNearestNonInPlaceGraph = nearestNonInPlaceGraph(owner)) == null) ? controlFlowGraph : controlFlowGraphNearestNonInPlaceGraph;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean requiresInitialization(FirPropertySymbol firPropertySymbol, boolean z) {
        firPropertySymbol.getClass();
        if (firPropertySymbol instanceof FirSyntheticPropertySymbol) {
            return false;
        }
        if (z) {
            return firPropertySymbol.getHasDelegate() || DeclarationAttributesKt.getHasBackingField(firPropertySymbol);
        }
        if (DeclarationAttributesKt.getHasBackingField(firPropertySymbol) && !firPropertySymbol.getHasInitializer()) {
            FirBackingFieldSymbol backingFieldSymbol = firPropertySymbol.getBackingFieldSymbol();
            if ((backingFieldSymbol != null ? backingFieldSymbol.getResolvedInitializer() : null) == null && !Intrinsics.areEqual(ClassMembersKt.isCatchParameter((FirProperty) firPropertySymbol.getFir()), Boolean.TRUE)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean getEvaluatedInPlace(FirDeclaration firDeclaration) {
        firDeclaration.getClass();
        return getEvaluatedInPlace(firDeclaration.getSymbol());
    }
}
