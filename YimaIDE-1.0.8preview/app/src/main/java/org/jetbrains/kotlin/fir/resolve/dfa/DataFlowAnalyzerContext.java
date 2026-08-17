package org.jetbrains.kotlin.fir.resolve.dfa;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CfgInternals;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraph;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphBuilder;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphCopier;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B1\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rB\u0011\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\f\u0010\u000eJ\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0000J\u0006\u0010\u0016\u001a\u00020\u0014J\u0006\u0010%\u001a\u00020\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0017\u001a\u00020\u00188FX\u0087\u0004r\u0002\b\u001d¢\u0006\f\u0012\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u001e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u0005@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u001e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u0007@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u001e\u0010\b\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\t@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$¨\u0006&"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/DataFlowAnalyzerContext;", Argument.Delimiters.none, "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "graphBuilder", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraphBuilder;", "variableAssignmentAnalyzer", "Lorg/jetbrains/kotlin/fir/resolve/dfa/FirLocalVariableAssignmentAnalyzer;", "variableStorage", "Lorg/jetbrains/kotlin/fir/resolve/dfa/VariableStorage;", "assignmentCounter", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraphBuilder;Lorg/jetbrains/kotlin/fir/resolve/dfa/FirLocalVariableAssignmentAnalyzer;Lorg/jetbrains/kotlin/fir/resolve/dfa/VariableStorage;I)V", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "createSnapshot", "Lorg/jetbrains/kotlin/fir/resolve/dfa/DataFlowAnalyzerContextSnapshot;", "firMapper", "Lorg/jetbrains/kotlin/fir/resolve/dfa/SnapshotFirMapper;", "resetFrom", Argument.Delimiters.none, "source", "reset", "currentGraph", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", "getCurrentGraph$annotations", "()V", "getCurrentGraph", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CfgInternals;", "value", "getGraphBuilder$org_jetbrains_kotlin_resolve", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraphBuilder;", "getVariableAssignmentAnalyzer$org_jetbrains_kotlin_resolve", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/FirLocalVariableAssignmentAnalyzer;", "getVariableStorage$org_jetbrains_kotlin_resolve", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/VariableStorage;", "newAssignmentIndex", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DataFlowAnalyzerContext {
    private int assignmentCounter;
    private ControlFlowGraphBuilder graphBuilder;
    private final FirSession session;
    private FirLocalVariableAssignmentAnalyzer variableAssignmentAnalyzer;
    private VariableStorage variableStorage;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DataFlowAnalyzerContext(FirSession firSession) {
        this(firSession, new ControlFlowGraphBuilder(), new FirLocalVariableAssignmentAnalyzer(), new VariableStorage(firSession), 0);
        firSession.getClass();
    }

    @CfgInternals
    public static /* synthetic */ void getCurrentGraph$annotations() {
    }

    public final DataFlowAnalyzerContextSnapshot createSnapshot(SnapshotFirMapper firMapper) {
        firMapper.getClass();
        ControlFlowGraphCopier controlFlowGraphCopier = new ControlFlowGraphCopier();
        DataFlowAnalyzerContext dataFlowAnalyzerContext = new DataFlowAnalyzerContext(this.session, this.graphBuilder.createSnapshot$org_jetbrains_kotlin_resolve(controlFlowGraphCopier), this.variableAssignmentAnalyzer.createSnapshot$org_jetbrains_kotlin_resolve(firMapper), this.variableStorage.createSnapshot(), this.assignmentCounter);
        controlFlowGraphCopier.finish();
        return new DataFlowAnalyzerContextSnapshot(dataFlowAnalyzerContext, controlFlowGraphCopier.getGraphMapping());
    }

    public final ControlFlowGraph getCurrentGraph() {
        return this.graphBuilder.getCurrentGraph();
    }

    /* JADX INFO: renamed from: getGraphBuilder$org_jetbrains_kotlin_resolve, reason: from getter */
    public final ControlFlowGraphBuilder getGraphBuilder() {
        return this.graphBuilder;
    }

    /* JADX INFO: renamed from: getVariableAssignmentAnalyzer$org_jetbrains_kotlin_resolve, reason: from getter */
    public final FirLocalVariableAssignmentAnalyzer getVariableAssignmentAnalyzer() {
        return this.variableAssignmentAnalyzer;
    }

    /* JADX INFO: renamed from: getVariableStorage$org_jetbrains_kotlin_resolve, reason: from getter */
    public final VariableStorage getVariableStorage() {
        return this.variableStorage;
    }

    public final int newAssignmentIndex() {
        int i = this.assignmentCounter;
        this.assignmentCounter = i + 1;
        return i;
    }

    public final void reset() {
        this.graphBuilder.reset();
        this.variableAssignmentAnalyzer.reset();
        this.variableStorage = new VariableStorage(this.session);
    }

    public final void resetFrom(DataFlowAnalyzerContext source) {
        source.getClass();
        reset();
        this.graphBuilder = source.graphBuilder;
        this.variableAssignmentAnalyzer = source.variableAssignmentAnalyzer;
        this.variableStorage = source.variableStorage;
        this.assignmentCounter = source.assignmentCounter;
    }

    private DataFlowAnalyzerContext(FirSession firSession, ControlFlowGraphBuilder controlFlowGraphBuilder, FirLocalVariableAssignmentAnalyzer firLocalVariableAssignmentAnalyzer, VariableStorage variableStorage, int i) {
        this.session = firSession;
        this.assignmentCounter = i;
        this.graphBuilder = controlFlowGraphBuilder;
        this.variableAssignmentAnalyzer = firLocalVariableAssignmentAnalyzer;
        this.variableStorage = variableStorage;
    }
}
