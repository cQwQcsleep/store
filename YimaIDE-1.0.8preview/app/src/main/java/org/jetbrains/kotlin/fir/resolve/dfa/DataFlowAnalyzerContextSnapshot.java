package org.jetbrains.kotlin.fir.resolve.dfa;

import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraph;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/DataFlowAnalyzerContextSnapshot;", Argument.Delimiters.none, "context", "Lorg/jetbrains/kotlin/fir/resolve/dfa/DataFlowAnalyzerContext;", "graphMapping", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/DataFlowAnalyzerContext;Ljava/util/Map;)V", "getContext", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/DataFlowAnalyzerContext;", "getGraphMapping", "()Ljava/util/Map;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DataFlowAnalyzerContextSnapshot {
    private final DataFlowAnalyzerContext context;
    private final Map<ControlFlowGraph, ControlFlowGraph> graphMapping;

    public DataFlowAnalyzerContextSnapshot(DataFlowAnalyzerContext dataFlowAnalyzerContext, Map<ControlFlowGraph, ControlFlowGraph> map) {
        dataFlowAnalyzerContext.getClass();
        map.getClass();
        this.context = dataFlowAnalyzerContext;
        this.graphMapping = map;
    }

    public final DataFlowAnalyzerContext getContext() {
        return this.context;
    }

    public final Map<ControlFlowGraph, ControlFlowGraph> getGraphMapping() {
        return this.graphMapping;
    }
}
