package org.jetbrains.kotlin.backend.jvm.lower;

import kotlin.Metadata;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004J\n\u0010\u0011\u001a\u00020\u0012HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/lower/IndyCallData;", "", "forceSerializability", "", "plainLambda", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(ZZ)V", "getForceSerializability", "()Z", "getPlainLambda", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "org.jetbrains.kotlin:backend.jvm.lower"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class IndyCallData {
    private final boolean forceSerializability;
    private final boolean plainLambda;

    public IndyCallData(boolean z, boolean z2) {
        this.forceSerializability = z;
        this.plainLambda = z2;
    }

    public static /* synthetic */ IndyCallData copy$default(IndyCallData indyCallData, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = indyCallData.forceSerializability;
        }
        if ((i & 2) != 0) {
            z2 = indyCallData.plainLambda;
        }
        return indyCallData.copy(z, z2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getForceSerializability() {
        return this.forceSerializability;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getPlainLambda() {
        return this.plainLambda;
    }

    public final IndyCallData copy(boolean forceSerializability, boolean plainLambda) {
        return new IndyCallData(forceSerializability, plainLambda);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IndyCallData)) {
            return false;
        }
        IndyCallData indyCallData = (IndyCallData) other;
        return this.forceSerializability == indyCallData.forceSerializability && this.plainLambda == indyCallData.plainLambda;
    }

    public final boolean getForceSerializability() {
        return this.forceSerializability;
    }

    public final boolean getPlainLambda() {
        return this.plainLambda;
    }

    public int hashCode() {
        return (Boolean.hashCode(this.forceSerializability) * 31) + Boolean.hashCode(this.plainLambda);
    }

    public String toString() {
        return "IndyCallData(forceSerializability=" + this.forceSerializability + ", plainLambda=" + this.plainLambda + Util.C_PARAM_END;
    }
}
