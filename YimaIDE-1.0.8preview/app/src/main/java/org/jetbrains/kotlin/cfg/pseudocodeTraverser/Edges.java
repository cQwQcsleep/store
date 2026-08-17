package org.jetbrains.kotlin.cfg.pseudocodeTraverser;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\u0006\u0010\u0004\u001a\u00028\u0000¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\u000b\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\bJ\u000e\u0010\f\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\bJ(\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u00002\b\b\u0002\u0010\u0004\u001a\u00028\u0000HÆ\u0001¢\u0006\u0002\u0010\u000eJ\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0013\u0010\u0003\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\n\u0010\b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/cfg/pseudocodeTraverser/Edges;", "T", "", "incoming", "outgoing", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/Object;Ljava/lang/Object;)V", "getIncoming", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getOutgoing", "component1", "component2", "copy", "(Ljava/lang/Object;Ljava/lang/Object;)Lorg/jetbrains/kotlin/cfg/pseudocodeTraverser/Edges;", "equals", "", "other", "hashCode", "", "toString", "", "org.jetbrains.kotlin:cfg"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class Edges<T> {
    private final T incoming;
    private final T outgoing;

    public Edges(T t, T t2) {
        this.incoming = t;
        this.outgoing = t2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Edges copy$default(Edges edges, Object obj, Object obj2, int i, Object obj3) {
        if ((i & 1) != 0) {
            obj = edges.incoming;
        }
        if ((i & 2) != 0) {
            obj2 = edges.outgoing;
        }
        return edges.copy(obj, obj2);
    }

    public final T component1() {
        return this.incoming;
    }

    public final T component2() {
        return this.outgoing;
    }

    public final Edges<T> copy(T incoming, T outgoing) {
        return new Edges<>(incoming, outgoing);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Edges)) {
            return false;
        }
        Edges edges = (Edges) other;
        return Intrinsics.areEqual(this.incoming, edges.incoming) && Intrinsics.areEqual(this.outgoing, edges.outgoing);
    }

    public final T getIncoming() {
        return this.incoming;
    }

    public final T getOutgoing() {
        return this.outgoing;
    }

    public int hashCode() {
        T t = this.incoming;
        int iHashCode = (t == null ? 0 : t.hashCode()) * 31;
        T t2 = this.outgoing;
        return iHashCode + (t2 != null ? t2.hashCode() : 0);
    }

    public String toString() {
        return "Edges(incoming=" + this.incoming + ", outgoing=" + this.outgoing + Util.C_PARAM_END;
    }
}
