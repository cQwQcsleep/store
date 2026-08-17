package org.jetbrains.kotlin.backend.common.serialization;

import kotlin.Metadata;
import kotlin.ULong;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u001c\u0010\u000b\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\f\u001a\u00020\u0003H\u0082\u0004¢\u0006\u0004\b\r\u0010\u000eJ\u000e\u0010\u000f\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0000J\u0010\u0010\u0010\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b\u0011\u0010\bJ\u0010\u0010\u0012\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b\u0013\u0010\bJ$\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\n\u0010\b¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/Hash128Bits;", "", "lowBytes", "Lkotlin/ULong;", "highBytes", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(JJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getLowBytes-s-VKNKU", "()J", "J", "getHighBytes-s-VKNKU", "combineHash", "other", "combineHash-oku0oEs", "(JJ)J", "combineWith", "component1", "component1-s-VKNKU", "component2", "component2-s-VKNKU", "copy", "copy-PWzV0Is", "(JJ)Lorg/jetbrains/kotlin/backend/common/serialization/Hash128Bits;", "equals", "", "hashCode", "", "toString", "", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class Hash128Bits {
    private final long highBytes;
    private final long lowBytes;

    public /* synthetic */ Hash128Bits(long j, long j2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? CityHashKt.k0 : j, (i & 2) != 0 ? CityHashKt.k1 : j2, null);
    }

    /* JADX INFO: renamed from: combineHash-oku0oEs, reason: not valid java name */
    private final long m227combineHashoku0oEs(long j, long j2) {
        return ULong.constructor-impl(ULong.constructor-impl(ULong.constructor-impl(ULong.constructor-impl(j + CityHashKt.kGoldenRatio) + ULong.constructor-impl(j2 << 12)) + ULong.constructor-impl(j2 >>> 4)) ^ j2);
    }

    /* JADX INFO: renamed from: copy-PWzV0Is$default, reason: not valid java name */
    public static /* synthetic */ Hash128Bits m228copyPWzV0Is$default(Hash128Bits hash128Bits, long j, long j2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = hash128Bits.lowBytes;
        }
        if ((i & 2) != 0) {
            j2 = hash128Bits.highBytes;
        }
        return hash128Bits.m231copyPWzV0Is(j, j2);
    }

    public final Hash128Bits combineWith(Hash128Bits other) {
        other.getClass();
        return new Hash128Bits(m227combineHashoku0oEs(this.highBytes, other.lowBytes), m227combineHashoku0oEs(this.lowBytes, other.highBytes), null);
    }

    /* JADX INFO: renamed from: component1-s-VKNKU, reason: not valid java name and from getter */
    public final long getLowBytes() {
        return this.lowBytes;
    }

    /* JADX INFO: renamed from: component2-s-VKNKU, reason: not valid java name and from getter */
    public final long getHighBytes() {
        return this.highBytes;
    }

    /* JADX INFO: renamed from: copy-PWzV0Is, reason: not valid java name */
    public final Hash128Bits m231copyPWzV0Is(long lowBytes, long highBytes) {
        return new Hash128Bits(lowBytes, highBytes, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Hash128Bits)) {
            return false;
        }
        Hash128Bits hash128Bits = (Hash128Bits) other;
        return this.lowBytes == hash128Bits.lowBytes && this.highBytes == hash128Bits.highBytes;
    }

    /* JADX INFO: renamed from: getHighBytes-s-VKNKU, reason: not valid java name */
    public final long m232getHighBytessVKNKU() {
        return this.highBytes;
    }

    /* JADX INFO: renamed from: getLowBytes-s-VKNKU, reason: not valid java name */
    public final long m233getLowBytessVKNKU() {
        return this.lowBytes;
    }

    public int hashCode() {
        return (ULong.hashCode-impl(this.lowBytes) * 31) + ULong.hashCode-impl(this.highBytes);
    }

    public String toString() {
        return "Hash128Bits(lowBytes=" + ((Object) ULong.toString-impl(this.lowBytes)) + ", highBytes=" + ((Object) ULong.toString-impl(this.highBytes)) + Util.C_PARAM_END;
    }

    private Hash128Bits(long j, long j2) {
        this.lowBytes = j;
        this.highBytes = j2;
    }

    public /* synthetic */ Hash128Bits(long j, long j2, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2);
    }
}
