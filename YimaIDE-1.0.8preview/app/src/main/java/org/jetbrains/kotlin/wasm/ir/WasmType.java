package org.jetbrains.kotlin.wasm.ir;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\u0019\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\n\u0010\f\u001a\u00020\u0003H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u0082\u0001\u0015\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !¨\u0006\""}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/WasmType;", "", "name", "", "code", "", "<init>", "(Ljava/lang/String;B)V", "getName", "()Ljava/lang/String;", "getCode", "()B", "toString", "Lorg/jetbrains/kotlin/wasm/ir/WasmAnyRef;", "Lorg/jetbrains/kotlin/wasm/ir/WasmArrayRef;", "Lorg/jetbrains/kotlin/wasm/ir/WasmEqRef;", "Lorg/jetbrains/kotlin/wasm/ir/WasmExnRefType;", "Lorg/jetbrains/kotlin/wasm/ir/WasmExternRef;", "Lorg/jetbrains/kotlin/wasm/ir/WasmF32;", "Lorg/jetbrains/kotlin/wasm/ir/WasmF64;", "Lorg/jetbrains/kotlin/wasm/ir/WasmFuncRef;", "Lorg/jetbrains/kotlin/wasm/ir/WasmI16;", "Lorg/jetbrains/kotlin/wasm/ir/WasmI31Ref;", "Lorg/jetbrains/kotlin/wasm/ir/WasmI32;", "Lorg/jetbrains/kotlin/wasm/ir/WasmI64;", "Lorg/jetbrains/kotlin/wasm/ir/WasmI8;", "Lorg/jetbrains/kotlin/wasm/ir/WasmNullExnRefType;", "Lorg/jetbrains/kotlin/wasm/ir/WasmRefNullExternrefType;", "Lorg/jetbrains/kotlin/wasm/ir/WasmRefNullType;", "Lorg/jetbrains/kotlin/wasm/ir/WasmRefNullrefType;", "Lorg/jetbrains/kotlin/wasm/ir/WasmRefType;", "Lorg/jetbrains/kotlin/wasm/ir/WasmStructRef;", "Lorg/jetbrains/kotlin/wasm/ir/WasmUnreachableType;", "Lorg/jetbrains/kotlin/wasm/ir/WasmV128;", "org.jetbrains.kotlin:wasm.ir"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public abstract class WasmType {
    private final byte code;
    private final String name;

    private WasmType(String str, byte b) {
        this.name = str;
        this.code = b;
    }

    public final byte getCode() {
        return this.code;
    }

    public final String getName() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }

    public /* synthetic */ WasmType(String str, byte b, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, b);
    }
}
