package org.jetbrains.kotlin.wasm.ir;

import kotlin.Metadata;
import kotlin.UInt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\f\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b\r\u0010\bJ\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\b\u000fJ&\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0014\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/WasmLimits;", "", "minSize", "Lkotlin/UInt;", "maxSize", "<init>", "(ILkotlin/UInt;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getMinSize-pVg5ArA", "()I", "I", "getMaxSize-0hXNFcg", "()Lkotlin/UInt;", "component1", "component1-pVg5ArA", "component2", "component2-0hXNFcg", "copy", "copy-Ut0gzDY", "(ILkotlin/UInt;)Lorg/jetbrains/kotlin/wasm/ir/WasmLimits;", "equals", "", "other", "hashCode", "", "toString", "", "org.jetbrains.kotlin:wasm.ir"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final /* data */ class WasmLimits {
    private final UInt maxSize;
    private final int minSize;

    private WasmLimits(int i, UInt uInt) {
        this.minSize = i;
        this.maxSize = uInt;
    }

    /* JADX INFO: renamed from: copy-Ut0gzDY$default, reason: not valid java name */
    public static /* synthetic */ WasmLimits m1312copyUt0gzDY$default(WasmLimits wasmLimits, int i, UInt uInt, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = wasmLimits.minSize;
        }
        if ((i2 & 2) != 0) {
            uInt = wasmLimits.maxSize;
        }
        return wasmLimits.m1315copyUt0gzDY(i, uInt);
    }

    /* JADX INFO: renamed from: component1-pVg5ArA, reason: not valid java name and from getter */
    public final int getMinSize() {
        return this.minSize;
    }

    /* JADX INFO: renamed from: component2-0hXNFcg, reason: not valid java name and from getter */
    public final UInt getMaxSize() {
        return this.maxSize;
    }

    /* JADX INFO: renamed from: copy-Ut0gzDY, reason: not valid java name */
    public final WasmLimits m1315copyUt0gzDY(int minSize, UInt maxSize) {
        return new WasmLimits(minSize, maxSize, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WasmLimits)) {
            return false;
        }
        WasmLimits wasmLimits = (WasmLimits) other;
        return this.minSize == wasmLimits.minSize && Intrinsics.areEqual(this.maxSize, wasmLimits.maxSize);
    }

    /* JADX INFO: renamed from: getMaxSize-0hXNFcg, reason: not valid java name */
    public final UInt m1316getMaxSize0hXNFcg() {
        return this.maxSize;
    }

    /* JADX INFO: renamed from: getMinSize-pVg5ArA, reason: not valid java name */
    public final int m1317getMinSizepVg5ArA() {
        return this.minSize;
    }

    public int hashCode() {
        int i = UInt.hashCode-impl(this.minSize) * 31;
        UInt uInt = this.maxSize;
        return i + (uInt == null ? 0 : UInt.hashCode-impl(uInt.unbox-impl()));
    }

    public String toString() {
        return "WasmLimits(minSize=" + ((Object) UInt.toString-impl(this.minSize)) + ", maxSize=" + this.maxSize + ')';
    }

    public /* synthetic */ WasmLimits(int i, UInt uInt, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, uInt);
    }
}
