package org.jetbrains.kotlin.fir.analysis.checkers.extra;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.PersistentMap;
import kotlinx.collections.immutable.PersistentSet;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010(\n\u0002\u0010&\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\b\u0083@\u0018\u00002\u00020\u0001B%\u0012\u001c\u0010\u0002\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u00050\u0003¢\u0006\u0004\b\u0007\u0010\bB\u001d\b\u0016\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u0006¢\u0006\u0004\b\u0007\u0010\u000bB#\b\u0016\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0010\u0010\f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¢\u0006\u0004\b\u0007\u0010\rJ$\u0010\u0010\u001a\u000e\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\u0004H\u0086\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u0018\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0000H\u0086\u0002¢\u0006\u0004\b\u0015\u0010\u0016J!\u0010\u0017\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u00042\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u0006¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001a\u001a\u0004\u0018\u00010\u00002\u0006\u0010\t\u001a\u00020\u0004¢\u0006\u0004\b\u001b\u0010\u001cJ,\u0010\u001d\u001a\u001e\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020\u0004\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u00050\u001f0\u001eH\u0086\u0002¢\u0006\u0004\b \u0010!J\u001b\u0010\"\u001a\u00020#2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004¢\u0006\u0004\b$\u0010%J\u0011\u0010&\u001a\u00020'HÖ\u0081\u0004¢\u0006\u0004\b(\u0010)J\u0011\u0010*\u001a\u00020+HÖ\u0081\u0004¢\u0006\u0004\b,\u0010-R'\u0010\u0002\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u0088\u0001\u0002Ê\u0001\u0002\b/¨\u0006."}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/VariableWriteData;", Argument.Delimiters.none, "value", "Lkotlinx/collections/immutable/PersistentMap;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "Lkotlinx/collections/immutable/PersistentSet;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "constructor-impl", "(Lkotlinx/collections/immutable/PersistentMap;)Lkotlinx/collections/immutable/PersistentMap;", "symbol", "node", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;)Lkotlinx/collections/immutable/PersistentMap;", "data", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;Lkotlinx/collections/immutable/PersistentSet;)Lkotlinx/collections/immutable/PersistentMap;", "getValue", "()Lkotlinx/collections/immutable/PersistentMap;", "get", "get-impl", "(Lkotlinx/collections/immutable/PersistentMap;Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;)Lkotlinx/collections/immutable/PersistentSet;", "plus", "other", "plus-JgvPeUI", "(Lkotlinx/collections/immutable/PersistentMap;Lkotlinx/collections/immutable/PersistentMap;)Lkotlinx/collections/immutable/PersistentMap;", "add", "add-pnGJifQ", "(Lkotlinx/collections/immutable/PersistentMap;Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;)Lkotlinx/collections/immutable/PersistentMap;", "remove", "remove-YvUXerM", "(Lkotlinx/collections/immutable/PersistentMap;Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;)Lkotlinx/collections/immutable/PersistentMap;", "iterator", Argument.Delimiters.none, Argument.Delimiters.none, "iterator-impl", "(Lkotlinx/collections/immutable/PersistentMap;)Ljava/util/Iterator;", "equals", Argument.Delimiters.none, "equals-impl", "(Lkotlinx/collections/immutable/PersistentMap;Ljava/lang/Object;)Z", "hashCode", Argument.Delimiters.none, "hashCode-impl", "(Lkotlinx/collections/immutable/PersistentMap;)I", "toString", Argument.Delimiters.none, "toString-impl", "(Lkotlinx/collections/immutable/PersistentMap;)Ljava/lang/String;", "org.jetbrains.kotlin:checkers", "Lkotlin/jvm/JvmInline;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
@JvmInline
final class VariableWriteData {
    private final PersistentMap<FirPropertySymbol, PersistentSet<CFGNode<?>>> value;

    private /* synthetic */ VariableWriteData(PersistentMap persistentMap) {
        this.value = persistentMap;
    }

    /* JADX INFO: renamed from: add-pnGJifQ, reason: not valid java name */
    public static final PersistentMap<FirPropertySymbol, ? extends PersistentSet<? extends CFGNode<?>>> m231addpnGJifQ(PersistentMap<FirPropertySymbol, ? extends PersistentSet<? extends CFGNode<?>>> persistentMap, FirPropertySymbol firPropertySymbol, CFGNode<?> cFGNode) {
        firPropertySymbol.getClass();
        cFGNode.getClass();
        PersistentSet persistentSetPersistentSetOf = (PersistentSet) persistentMap.get(firPropertySymbol);
        if (persistentSetPersistentSetOf == null) {
            persistentSetPersistentSetOf = ExtensionsKt.persistentSetOf();
        }
        return m233constructorimpl(persistentMap.put(firPropertySymbol, persistentSetPersistentSetOf.add(cFGNode)));
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ VariableWriteData m232boximpl(PersistentMap persistentMap) {
        return new VariableWriteData(persistentMap);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static PersistentMap<FirPropertySymbol, ? extends PersistentSet<? extends CFGNode<?>>> m235constructorimpl(FirPropertySymbol firPropertySymbol, CFGNode<?> cFGNode) {
        firPropertySymbol.getClass();
        cFGNode.getClass();
        return m233constructorimpl(ExtensionsKt.persistentMapOf(new Pair[]{TuplesKt.to(firPropertySymbol, ExtensionsKt.persistentSetOf(new CFGNode[]{cFGNode}))}));
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m236equalsimpl(PersistentMap<FirPropertySymbol, ? extends PersistentSet<? extends CFGNode<?>>> persistentMap, Object obj) {
        return (obj instanceof VariableWriteData) && Intrinsics.areEqual(persistentMap, ((VariableWriteData) obj).getValue());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m237equalsimpl0(PersistentMap<FirPropertySymbol, ? extends PersistentSet<? extends CFGNode<?>>> persistentMap, PersistentMap<FirPropertySymbol, ? extends PersistentSet<? extends CFGNode<?>>> persistentMap2) {
        return Intrinsics.areEqual(persistentMap, persistentMap2);
    }

    /* JADX INFO: renamed from: get-impl, reason: not valid java name */
    public static final PersistentSet<CFGNode<?>> m238getimpl(PersistentMap<FirPropertySymbol, ? extends PersistentSet<? extends CFGNode<?>>> persistentMap, FirPropertySymbol firPropertySymbol) {
        firPropertySymbol.getClass();
        return (PersistentSet) persistentMap.get(firPropertySymbol);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m239hashCodeimpl(PersistentMap<FirPropertySymbol, ? extends PersistentSet<? extends CFGNode<?>>> persistentMap) {
        return persistentMap.hashCode();
    }

    /* JADX INFO: renamed from: iterator-impl, reason: not valid java name */
    public static final Iterator<Map.Entry<FirPropertySymbol, PersistentSet<CFGNode<?>>>> m240iteratorimpl(PersistentMap<FirPropertySymbol, ? extends PersistentSet<? extends CFGNode<?>>> persistentMap) {
        return persistentMap.entrySet().iterator();
    }

    /* JADX INFO: renamed from: plus-JgvPeUI, reason: not valid java name */
    public static final PersistentMap<FirPropertySymbol, ? extends PersistentSet<? extends CFGNode<?>>> m241plusJgvPeUI(PersistentMap<FirPropertySymbol, ? extends PersistentSet<? extends CFGNode<?>>> persistentMap, PersistentMap<FirPropertySymbol, ? extends PersistentSet<? extends CFGNode<?>>> persistentMap2) {
        persistentMap2.getClass();
        PersistentMap.Builder builder = persistentMap.builder();
        for (Map.Entry entry : persistentMap2.entrySet()) {
            Object key = entry.getKey();
            Object key2 = entry.getKey();
            Object value = entry.getValue();
            Object obj = persistentMap.get(key2);
            if (obj != null) {
                PersistentSet persistentSetAddAll = ((PersistentSet) obj).addAll((Collection) value);
                if (persistentSetAddAll != null) {
                    value = persistentSetAddAll;
                }
            }
            builder.put(key, value);
        }
        return m233constructorimpl(builder.build());
    }

    /* JADX INFO: renamed from: remove-YvUXerM, reason: not valid java name */
    public static final PersistentMap<FirPropertySymbol, ? extends PersistentSet<? extends CFGNode<?>>> m242removeYvUXerM(PersistentMap<FirPropertySymbol, ? extends PersistentSet<? extends CFGNode<?>>> persistentMap, FirPropertySymbol firPropertySymbol) {
        firPropertySymbol.getClass();
        PersistentMap persistentMapRemove = persistentMap.remove(firPropertySymbol);
        if (persistentMapRemove.isEmpty()) {
            return null;
        }
        return m233constructorimpl(persistentMapRemove);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m243toStringimpl(PersistentMap<FirPropertySymbol, ? extends PersistentSet<? extends CFGNode<?>>> persistentMap) {
        return "VariableWriteData(value=" + persistentMap + ')';
    }

    public boolean equals(Object obj) {
        return m236equalsimpl(this.value, obj);
    }

    public final PersistentMap<FirPropertySymbol, PersistentSet<CFGNode<?>>> getValue() {
        return this.value;
    }

    public int hashCode() {
        return m239hashCodeimpl(this.value);
    }

    public String toString() {
        return m243toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ PersistentMap getValue() {
        return this.value;
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static PersistentMap<FirPropertySymbol, ? extends PersistentSet<? extends CFGNode<?>>> m233constructorimpl(PersistentMap<FirPropertySymbol, ? extends PersistentSet<? extends CFGNode<?>>> persistentMap) {
        persistentMap.getClass();
        return persistentMap;
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static PersistentMap<FirPropertySymbol, ? extends PersistentSet<? extends CFGNode<?>>> m234constructorimpl(FirPropertySymbol firPropertySymbol, PersistentSet<? extends CFGNode<?>> persistentSet) {
        firPropertySymbol.getClass();
        persistentSet.getClass();
        return m233constructorimpl(ExtensionsKt.persistentMapOf(new Pair[]{TuplesKt.to(firPropertySymbol, persistentSet)}));
    }
}
