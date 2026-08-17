package org.jetbrains.kotlin.fir.resolve.dfa.cfg;

import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.state.InlineClassManglingUtilsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.dfa.FlowPath;
import org.jetbrains.kotlin.fir.resolve.dfa.PersistentFlow;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u001e\u001a\u00020\u001aH\u0016J\u001c\u0010\u001f\u001a\u00020 2\u0006\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u0012\u001a\u00020\u0011H\u0017b\u0002\b\u0017J5\u0010!\u001a\u0002H\"\"\u0004\b\u0000\u0010\"\"\u0004\b\u0001\u0010#2\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u0002H\"\u0012\u0004\u0012\u0002H#0%2\u0006\u0010&\u001a\u0002H#H\u0016¢\u0006\u0002\u0010'R\u0014\u0010\t\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR)\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u00118V@WX\u0096\u000e\u0082\u0001\u0002\b\u0017¢\u0006\f\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c¨\u0006("}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/StubNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FirStub;", "owner", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", "level", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;I)V", "fir", "getFir", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FirStub;", "flowInitialized", Argument.Delimiters.none, "getFlowInitialized", "()Z", InlineClassManglingUtilsKt.NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER, "Lorg/jetbrains/kotlin/fir/resolve/dfa/PersistentFlow;", "flow", "getFlow", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/PersistentFlow;", "setFlow", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/PersistentFlow;)V", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CfgInternals;", "alternateFlowPaths", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/dfa/FlowPath;", "getAlternateFlowPaths", "()Ljava/util/Set;", "getAlternateFlow", ModuleXmlParser.PATH, "addAlternateFlow", Argument.Delimiters.none, "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraphVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraphVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class StubNode extends CFGNode<FirStub> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StubNode(ControlFlowGraph controlFlowGraph, int i) {
        super(controlFlowGraph, i, null);
        controlFlowGraph.getClass();
        setDead(true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode
    public <R, D> R accept(ControlFlowGraphVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        return visitor.visitStubNode(this, data);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode
    @CfgInternals
    public void addAlternateFlow(FlowPath path, PersistentFlow flow) {
        path.getClass();
        flow.getClass();
        CFGNodeKt.getFirstPreviousNode(this).addAlternateFlow(path, flow);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode
    public PersistentFlow getAlternateFlow(FlowPath path) {
        path.getClass();
        return CFGNodeKt.getFirstPreviousNode(this).getAlternateFlow(path);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode
    public Set<FlowPath> getAlternateFlowPaths() {
        return CFGNodeKt.getFirstPreviousNode(this).getAlternateFlowPaths();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode
    public PersistentFlow getFlow() {
        return CFGNodeKt.getFirstPreviousNode(this).getFlow();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode
    public boolean getFlowInitialized() {
        return CFGNodeKt.getFirstPreviousNode(this).getFlowInitialized();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode
    @CfgInternals
    public void setFlow(PersistentFlow persistentFlow) {
        persistentFlow.getClass();
        throw new IllegalStateException("can't set flow for stub node");
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode
    public FirStub getFir() {
        return FirStub.INSTANCE;
    }
}
