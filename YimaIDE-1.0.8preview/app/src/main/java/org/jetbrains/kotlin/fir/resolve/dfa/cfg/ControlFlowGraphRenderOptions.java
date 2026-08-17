package org.jetbrains.kotlin.fir.resolve.dfa.cfg;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphRenderOptions;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\u001a\b\u0002\u0010\u0005\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0006¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u001b\u0010\u0012\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0006HÆ\u0003J9\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u001a\b\u0002\u0010\u0005\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0006HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00032\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\bHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR#\u0010\u0005\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraphRenderOptions;", Argument.Delimiters.none, "renderLevels", Argument.Delimiters.none, "renderFlow", "data", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", Argument.Delimiters.none, "<init>", "(ZZLkotlin/jvm/functions/Function1;)V", "getRenderLevels", "()Z", "getRenderFlow", "getData", "()Lkotlin/jvm/functions/Function1;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", Argument.Delimiters.none, "toString", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class ControlFlowGraphRenderOptions {
    private final Function1<CFGNode<?>, String> data;
    private final boolean renderFlow;
    private final boolean renderLevels;

    public /* synthetic */ ControlFlowGraphRenderOptions(boolean z, boolean z2, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? false : z2, (i & 4) != 0 ? new Function1() { // from class: vw2
            public final Object invoke(Object obj) {
                return ControlFlowGraphRenderOptions.a((CFGNode) obj);
            }
        } : function1);
    }

    public static String a(CFGNode cFGNode) {
        cFGNode.getClass();
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ControlFlowGraphRenderOptions copy$default(ControlFlowGraphRenderOptions controlFlowGraphRenderOptions, boolean z, boolean z2, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            z = controlFlowGraphRenderOptions.renderLevels;
        }
        if ((i & 2) != 0) {
            z2 = controlFlowGraphRenderOptions.renderFlow;
        }
        if ((i & 4) != 0) {
            function1 = controlFlowGraphRenderOptions.data;
        }
        return controlFlowGraphRenderOptions.copy(z, z2, function1);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getRenderLevels() {
        return this.renderLevels;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getRenderFlow() {
        return this.renderFlow;
    }

    public final Function1<CFGNode<?>, String> component3() {
        return this.data;
    }

    public final ControlFlowGraphRenderOptions copy(boolean renderLevels, boolean renderFlow, Function1<? super CFGNode<?>, String> data) {
        data.getClass();
        return new ControlFlowGraphRenderOptions(renderLevels, renderFlow, data);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ControlFlowGraphRenderOptions)) {
            return false;
        }
        ControlFlowGraphRenderOptions controlFlowGraphRenderOptions = (ControlFlowGraphRenderOptions) other;
        return this.renderLevels == controlFlowGraphRenderOptions.renderLevels && this.renderFlow == controlFlowGraphRenderOptions.renderFlow && Intrinsics.areEqual(this.data, controlFlowGraphRenderOptions.data);
    }

    public final Function1<CFGNode<?>, String> getData() {
        return this.data;
    }

    public final boolean getRenderFlow() {
        return this.renderFlow;
    }

    public final boolean getRenderLevels() {
        return this.renderLevels;
    }

    public int hashCode() {
        return (((Boolean.hashCode(this.renderLevels) * 31) + Boolean.hashCode(this.renderFlow)) * 31) + this.data.hashCode();
    }

    public String toString() {
        return "ControlFlowGraphRenderOptions(renderLevels=" + this.renderLevels + ", renderFlow=" + this.renderFlow + ", data=" + this.data + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ControlFlowGraphRenderOptions(boolean z, boolean z2, Function1<? super CFGNode<?>, String> function1) {
        function1.getClass();
        this.renderLevels = z;
        this.renderFlow = z2;
        this.data = function1;
    }

    public ControlFlowGraphRenderOptions() {
        this(false, false, null, 7, null);
    }
}
