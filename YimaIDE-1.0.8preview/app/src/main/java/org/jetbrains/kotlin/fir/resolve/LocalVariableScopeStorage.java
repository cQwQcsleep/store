package org.jetbrains.kotlin.fir.resolve;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function0;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.PersistentMap;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.SessionAndScopeSessionHolder;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ExpressionReceiverValue;
import org.jetbrains.kotlin.fir.resolve.dfa.DataFlowVariable;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B;\b\u0002\u00120\u0010\u0002\u001a,\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u001e\u0012\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006\u0012\u0006\u0012\u0004\u0018\u00010\t0\u00050\u0003¢\u0006\u0004\b\n\u0010\u000bB\t\b\u0016¢\u0006\u0004\b\n\u0010\fJ\u0012\u0010\r\u001a\u00020\u00002\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u0004J1\u0010\u000f\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0012\u001a\u00020\u00132\u000e\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0015R\u00020\u0010j\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0002\u0010\u0016R8\u0010\u0002\u001a,\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u001e\u0012\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006\u0012\u0006\u0012\u0004\u0018\u00010\t0\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/LocalVariableScopeStorage;", Argument.Delimiters.none, "map", "Lkotlinx/collections/immutable/PersistentMap;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", Argument.Delimiters.none, "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/DataFlowVariable;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "<init>", "(Lkotlinx/collections/immutable/PersistentMap;)V", "()V", "addLocalVariable", "symbol", "getScope", "Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;", "c", "receiverValue", "Lorg/jetbrains/kotlin/fir/resolve/calls/ExpressionReceiverValue;", "getDataFlowVariable", "Lkotlin/Function0;", "(Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;Lorg/jetbrains/kotlin/fir/resolve/calls/ExpressionReceiverValue;Lkotlin/jvm/functions/Function0;)Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class LocalVariableScopeStorage {
    private final PersistentMap<FirVariableSymbol<?>, Map<Pair<DataFlowVariable, ConeKotlinType>, FirTypeScope>> map;

    public LocalVariableScopeStorage() {
        this(ExtensionsKt.persistentMapOf());
    }

    public final LocalVariableScopeStorage addLocalVariable(FirVariableSymbol<?> symbol) {
        symbol.getClass();
        return new LocalVariableScopeStorage(this.map.put(symbol, new LinkedHashMap()));
    }

    public final FirTypeScope getScope(SessionAndScopeSessionHolder sessionAndScopeSessionHolder, ExpressionReceiverValue expressionReceiverValue, Function0<? extends DataFlowVariable> function0) {
        Map map;
        DataFlowVariable dataFlowVariable;
        sessionAndScopeSessionHolder.getClass();
        expressionReceiverValue.getClass();
        function0.getClass();
        FirCallableSymbol<?> resolvedCallableSymbol = ReferenceUtilsKt.toResolvedCallableSymbol(expressionReceiverValue.getReceiverExpression(), sessionAndScopeSessionHolder.getSession());
        FirVariableSymbol firVariableSymbol = resolvedCallableSymbol instanceof FirVariableSymbol ? (FirVariableSymbol) resolvedCallableSymbol : null;
        if (firVariableSymbol != null && (map = (Map) this.map.get(firVariableSymbol)) != null && (dataFlowVariable = (DataFlowVariable) function0.invoke()) != null) {
            Pair pair = TuplesKt.to(dataFlowVariable, expressionReceiverValue.getType());
            Object objScope = map.get(pair);
            if (objScope == null) {
                objScope = expressionReceiverValue.scope(sessionAndScopeSessionHolder);
                map.put(pair, objScope);
            }
            return (FirTypeScope) objScope;
        }
        return expressionReceiverValue.scope(sessionAndScopeSessionHolder);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private LocalVariableScopeStorage(PersistentMap<FirVariableSymbol<?>, ? extends Map<Pair<DataFlowVariable, ConeKotlinType>, FirTypeScope>> persistentMap) {
        this.map = persistentMap;
    }
}
