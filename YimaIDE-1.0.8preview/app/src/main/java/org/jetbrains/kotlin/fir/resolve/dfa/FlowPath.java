package org.jetbrains.kotlin.fir.resolve.dfa;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.EdgeLabel;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/FlowPath;", Argument.Delimiters.none, "<init>", "()V", "Default", "CfgEdge", "Lorg/jetbrains/kotlin/fir/resolve/dfa/FlowPath$CfgEdge;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/FlowPath$Default;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FlowPath {

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/FlowPath$CfgEdge;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/FlowPath;", CoroutineCodegenUtilKt.COROUTINE_LABEL_FIELD_NAME, "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EdgeLabel;", "fir", "Lorg/jetbrains/kotlin/fir/FirElement;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EdgeLabel;Lorg/jetbrains/kotlin/fir/FirElement;)V", "getLabel", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EdgeLabel;", "getFir", "()Lorg/jetbrains/kotlin/fir/FirElement;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class CfgEdge extends FlowPath {
        private final FirElement fir;
        private final EdgeLabel label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CfgEdge(EdgeLabel edgeLabel, FirElement firElement) {
            super(null);
            edgeLabel.getClass();
            firElement.getClass();
            this.label = edgeLabel;
            this.fir = firElement;
        }

        public static /* synthetic */ CfgEdge copy$default(CfgEdge cfgEdge, EdgeLabel edgeLabel, FirElement firElement, int i, Object obj) {
            if ((i & 1) != 0) {
                edgeLabel = cfgEdge.label;
            }
            if ((i & 2) != 0) {
                firElement = cfgEdge.fir;
            }
            return cfgEdge.copy(edgeLabel, firElement);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final EdgeLabel getLabel() {
            return this.label;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final FirElement getFir() {
            return this.fir;
        }

        public final CfgEdge copy(EdgeLabel label, FirElement fir) {
            label.getClass();
            fir.getClass();
            return new CfgEdge(label, fir);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CfgEdge)) {
                return false;
            }
            CfgEdge cfgEdge = (CfgEdge) other;
            return Intrinsics.areEqual(this.label, cfgEdge.label) && Intrinsics.areEqual(this.fir, cfgEdge.fir);
        }

        public final FirElement getFir() {
            return this.fir;
        }

        public final EdgeLabel getLabel() {
            return this.label;
        }

        public int hashCode() {
            return (this.label.hashCode() * 31) + this.fir.hashCode();
        }

        public String toString() {
            return "CfgEdge(label=" + this.label + ", fir=" + this.fir + ')';
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/FlowPath$Default;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/FlowPath;", "<init>", "()V", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class Default extends FlowPath {
        public static final Default INSTANCE = new Default();

        private Default() {
            super(null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof Default);
        }

        public int hashCode() {
            return 1833072865;
        }

        public String toString() {
            return "Default";
        }
    }

    public /* synthetic */ FlowPath(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private FlowPath() {
    }
}
