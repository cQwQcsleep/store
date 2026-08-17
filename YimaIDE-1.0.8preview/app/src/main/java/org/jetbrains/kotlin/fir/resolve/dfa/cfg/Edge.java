package org.jetbrains.kotlin.fir.resolve.dfa.cfg;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/Edge;", Argument.Delimiters.none, CoroutineCodegenUtilKt.COROUTINE_LABEL_FIELD_NAME, "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EdgeLabel;", "kind", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EdgeKind;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EdgeLabel;Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EdgeKind;)V", "getLabel", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EdgeLabel;", "getKind", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EdgeKind;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "Companion", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class Edge {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Edge Normal_CfgBackward;
    private static final Edge Normal_CfgForward;
    private static final Edge Normal_DeadCfgBackward;
    private static final Edge Normal_DeadCfgForward;
    private static final Edge Normal_DeadDfgForward;
    private static final Edge Normal_DeadForward;
    private static final Edge Normal_DfgForward;
    private static final Edge Normal_Forward;
    private final EdgeKind kind;
    private final EdgeLabel label;

    static {
        NormalPath normalPath = NormalPath.INSTANCE;
        Normal_Forward = new Edge(normalPath, EdgeKind.Forward);
        Normal_DfgForward = new Edge(normalPath, EdgeKind.DfgForward);
        Normal_CfgForward = new Edge(normalPath, EdgeKind.CfgForward);
        Normal_DeadForward = new Edge(normalPath, EdgeKind.DeadForward);
        Normal_DeadDfgForward = new Edge(normalPath, EdgeKind.DeadDfgForward);
        Normal_DeadCfgForward = new Edge(normalPath, EdgeKind.DeadCfgForward);
        Normal_CfgBackward = new Edge(normalPath, EdgeKind.CfgBackward);
        Normal_DeadCfgBackward = new Edge(normalPath, EdgeKind.DeadCfgBackward);
    }

    public Edge(EdgeLabel edgeLabel, EdgeKind edgeKind) {
        edgeLabel.getClass();
        edgeKind.getClass();
        this.label = edgeLabel;
        this.kind = edgeKind;
    }

    public static /* synthetic */ Edge copy$default(Edge edge, EdgeLabel edgeLabel, EdgeKind edgeKind, int i, Object obj) {
        if ((i & 1) != 0) {
            edgeLabel = edge.label;
        }
        if ((i & 2) != 0) {
            edgeKind = edge.kind;
        }
        return edge.copy(edgeLabel, edgeKind);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final EdgeLabel getLabel() {
        return this.label;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final EdgeKind getKind() {
        return this.kind;
    }

    public final Edge copy(EdgeLabel label, EdgeKind kind) {
        label.getClass();
        kind.getClass();
        return new Edge(label, kind);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Edge)) {
            return false;
        }
        Edge edge = (Edge) other;
        return Intrinsics.areEqual(this.label, edge.label) && this.kind == edge.kind;
    }

    public final EdgeKind getKind() {
        return this.kind;
    }

    public final EdgeLabel getLabel() {
        return this.label;
    }

    public int hashCode() {
        return (this.label.hashCode() * 31) + this.kind.hashCode();
    }

    public String toString() {
        return "Edge(label=" + this.label + ", kind=" + this.kind + ')';
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/Edge$Companion;", Argument.Delimiters.none, "<init>", "()V", "Normal_Forward", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/Edge;", "getNormal_Forward", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/Edge;", "Normal_DfgForward", "Normal_CfgForward", "Normal_DeadForward", "Normal_DeadDfgForward", "Normal_DeadCfgForward", "Normal_CfgBackward", "Normal_DeadCfgBackward", CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME, CoroutineCodegenUtilKt.COROUTINE_LABEL_FIELD_NAME, "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EdgeLabel;", "kind", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EdgeKind;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {

        @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[EdgeKind.values().length];
                try {
                    iArr[EdgeKind.Forward.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[EdgeKind.DfgForward.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[EdgeKind.CfgForward.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[EdgeKind.DeadForward.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr[EdgeKind.DeadDfgForward.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                try {
                    iArr[EdgeKind.DeadCfgForward.ordinal()] = 6;
                } catch (NoSuchFieldError unused6) {
                }
                try {
                    iArr[EdgeKind.CfgBackward.ordinal()] = 7;
                } catch (NoSuchFieldError unused7) {
                }
                try {
                    iArr[EdgeKind.DeadCfgBackward.ordinal()] = 8;
                } catch (NoSuchFieldError unused8) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Edge create(EdgeLabel label, EdgeKind kind) {
            label.getClass();
            kind.getClass();
            if (!Intrinsics.areEqual(label, NormalPath.INSTANCE)) {
                return new Edge(label, kind);
            }
            switch (WhenMappings.$EnumSwitchMapping$0[kind.ordinal()]) {
                case 1:
                    return getNormal_Forward();
                case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
                    return Edge.Normal_DfgForward;
                case 3:
                    return Edge.Normal_CfgForward;
                case 4:
                    return Edge.Normal_DeadForward;
                case 5:
                    return Edge.Normal_DeadDfgForward;
                case 6:
                    return Edge.Normal_DeadCfgForward;
                case 7:
                    return Edge.Normal_CfgBackward;
                case 8:
                    return Edge.Normal_DeadCfgBackward;
                default:
                    bu8.a();
                    return null;
            }
        }

        public final Edge getNormal_Forward() {
            return Edge.Normal_Forward;
        }

        private Companion() {
        }
    }
}
