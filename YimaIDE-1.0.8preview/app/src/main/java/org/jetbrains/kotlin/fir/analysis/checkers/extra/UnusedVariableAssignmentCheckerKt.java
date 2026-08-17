package org.jetbrains.kotlin.fir.analysis.checkers.extra;

import java.util.Map;
import kotlin.Metadata;
import kotlinx.collections.immutable.PersistentMap;
import kotlinx.collections.immutable.PersistentSet;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.EdgeLabel;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\u001a\u0098\u0001\u0010\u0000\u001a:\u0012\u0004\u0012\u00020\u0002\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004`\u00050\u0001j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004`\u0006*:\u0012\u0004\u0012\u00020\u0002\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004`\u00050\u0001j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004`\u00062\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0002\u001a\u0084\u0001\u0010\f\u001a:\u0012\u0004\u0012\u00020\u0002\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004`\u00050\u0001j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004`\u0006*:\u0012\u0004\u0012\u00020\u0002\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004`\u00050\u0001j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004`\u00062\u0006\u0010\b\u001a\u00020\tH\u0002\u001a\u0096\u0001\u0010\r\u001a:\u0012\u0004\u0012\u00020\u0002\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004`\u00050\u0001j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004`\u0006*:\u0012\u0004\u0012\u00020\u0002\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004`\u00050\u0001j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004`\u00062\u0006\u0010\b\u001a\u00020\t2\u0010\u0010\u000e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\u000fH\u0002\u001a\\\u0010\u0010\u001a\u00020\u0011*\u00020\u00122N\u0010\u0013\u001aJ\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b\u0012<\u0012:\u0012\u0004\u0012\u00020\u0002\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004`\u00050\u0001j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004`\u00060\u0014H\u0002¨\u0006\u0015"}, d2 = {"add", "Lkotlinx/collections/immutable/PersistentMap;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EdgeLabel;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/PropertyAccessType;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/VariableWriteData;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/ControlFlowInfo;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/PathAwareControlFlowInfo;", ModuleXmlParser.TYPE, "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "node", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "remove", "overwrite", "nodes", "Lkotlinx/collections/immutable/PersistentSet;", "render", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", "data", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class UnusedVariableAssignmentCheckerKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final PersistentMap<EdgeLabel, PersistentMap<PropertyAccessType, VariableWriteData>> add(PersistentMap<EdgeLabel, ? extends PersistentMap<PropertyAccessType, VariableWriteData>> persistentMap, PropertyAccessType propertyAccessType, FirPropertySymbol firPropertySymbol, CFGNode<?> cFGNode) {
        PersistentMap.Builder builder = persistentMap.builder();
        for (Map.Entry entry : persistentMap.entrySet()) {
            Object key = entry.getKey();
            PersistentMap persistentMap2 = (PersistentMap) entry.getValue();
            VariableWriteData variableWriteData = (VariableWriteData) persistentMap2.get(propertyAccessType);
            PersistentMap value = variableWriteData != null ? variableWriteData.getValue() : null;
            builder.put(key, persistentMap2.put(propertyAccessType, VariableWriteData.m232boximpl(value != null ? VariableWriteData.m231addpnGJifQ(value, firPropertySymbol, cFGNode) : VariableWriteData.m235constructorimpl(firPropertySymbol, cFGNode))));
        }
        return builder.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PersistentMap<EdgeLabel, PersistentMap<PropertyAccessType, VariableWriteData>> overwrite(PersistentMap<EdgeLabel, ? extends PersistentMap<PropertyAccessType, VariableWriteData>> persistentMap, FirPropertySymbol firPropertySymbol, PersistentSet<? extends CFGNode<?>> persistentSet) {
        PersistentMap.Builder builder = persistentMap.builder();
        for (Map.Entry entry : persistentMap.entrySet()) {
            Object key = entry.getKey();
            PersistentMap persistentMap2 = (PersistentMap) entry.getValue();
            PropertyAccessType propertyAccessType = PropertyAccessType.InPlace;
            VariableWriteData variableWriteData = (VariableWriteData) persistentMap2.get(propertyAccessType);
            PersistentMap value = variableWriteData != null ? variableWriteData.getValue() : null;
            builder.put(key, persistentMap2.put(propertyAccessType, VariableWriteData.m232boximpl(value == null ? VariableWriteData.m234constructorimpl(firPropertySymbol, persistentSet) : VariableWriteData.m233constructorimpl(value.put(firPropertySymbol, persistentSet)))));
        }
        return builder.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PersistentMap<EdgeLabel, PersistentMap<PropertyAccessType, VariableWriteData>> remove(PersistentMap<EdgeLabel, ? extends PersistentMap<PropertyAccessType, VariableWriteData>> persistentMap, FirPropertySymbol firPropertySymbol) {
        PersistentMap.Builder builder = persistentMap.builder();
        for (Map.Entry entry : persistentMap.entrySet()) {
            Object key = entry.getKey();
            PersistentMap persistentMap2 = (PersistentMap) entry.getValue();
            PersistentMap.Builder builder2 = persistentMap2.builder();
            for (Map.Entry entry2 : persistentMap2.entrySet()) {
                PropertyAccessType propertyAccessType = (PropertyAccessType) entry2.getKey();
                PersistentMap<FirPropertySymbol, ? extends PersistentSet<? extends CFGNode<?>>> persistentMapM242removeYvUXerM = VariableWriteData.m242removeYvUXerM(((VariableWriteData) entry2.getValue()).getValue(), firPropertySymbol);
                if (persistentMapM242removeYvUXerM == null) {
                    builder2.remove(propertyAccessType);
                } else {
                    builder2.put(propertyAccessType, VariableWriteData.m232boximpl(persistentMapM242removeYvUXerM));
                }
            }
            builder.put(key, builder2.build());
        }
        return builder.build();
    }
}
