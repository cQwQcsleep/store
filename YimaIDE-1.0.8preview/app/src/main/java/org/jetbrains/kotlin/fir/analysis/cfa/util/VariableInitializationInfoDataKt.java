package org.jetbrains.kotlin.fir.analysis.cfa.util;

import java.util.Map;
import kotlin.Metadata;
import kotlinx.collections.immutable.PersistentMap;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.EdgeLabel;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u000b\u001a\u00020\f*\u00020\r2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000f*\u0012\u0010\u0000\"\u0006\u0012\u0002\b\u00030\u00012\u0006\u0012\u0002\b\u00030\u0001*6\u0010\u0002\"\b\u0012\u0004\u0012\u0002`\u0004`\u00032(\u0012\f\u0012\n\u0012\u0002\b\u00030\u0001j\u0002`\u0004\u0012\u0004\u0012\u00020\u00060\u0005j\u0010\u0012\f\u0012\n\u0012\u0002\b\u00030\u0001j\u0002`\u0004`\u0003*Z\u0010\u0007\"\b\u0012\u0004\u0012\u0002`\u0004`\b2L\u0012\u0004\u0012\u00020\t\u00120\u0012.\u0012\f\u0012\n\u0012\u0002\b\u00030\u0001j\u0002`\u0004\u0012\u0004\u0012\u00020\u00060\u0005j\u0016\u0012\f\u0012\n\u0012\u0002\b\u00030\u0001j\u0002`\u0004\u0012\u0004\u0012\u00020\u0006`\n0\u0005j\u0010\u0012\f\u0012\n\u0012\u0002\b\u00030\u0001j\u0002`\u0004`\b¨\u0006\u0010"}, d2 = {"VariableInitializationEvent", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "VariableInitializationInfo", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/EventOccurrencesRangeInfo;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/VariableInitializationEvent;", "Lkotlinx/collections/immutable/PersistentMap;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/EventOccurrencesRangeAtNode;", "PathAwarePropertyInitializationInfo", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/PathAwareEventOccurrencesRangeInfo;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EdgeLabel;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/ControlFlowInfo;", "render", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/VariableInitializationInfoData;", "node", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class VariableInitializationInfoDataKt {
    public static final String render(VariableInitializationInfoData variableInitializationInfoData, CFGNode<?> cFGNode) {
        variableInitializationInfoData.getClass();
        cFGNode.getClass();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : variableInitializationInfoData.getValue(cFGNode).entrySet()) {
            EdgeLabel edgeLabel = (EdgeLabel) entry.getKey();
            PersistentMap persistentMap = (PersistentMap) entry.getValue();
            String label = edgeLabel.getLabel();
            if (label == null) {
                label = "NormalPath";
            }
            sb.append(label);
            sb.append('\n');
            for (Map.Entry entry2 : persistentMap.entrySet()) {
                FirVariableSymbol firVariableSymbol = (FirVariableSymbol) entry2.getKey();
                EventOccurrencesRangeAtNode eventOccurrencesRangeAtNode = (EventOccurrencesRangeAtNode) entry2.getValue();
                StringBuilder sb2 = new StringBuilder();
                sb2.append(firVariableSymbol);
                sb2.append(' ');
                sb2.append(eventOccurrencesRangeAtNode.getRange().getWithoutMarker());
                sb.append(sb2.toString());
                sb.append('\n');
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
