package org.jetbrains.kotlin.backend.wasm.ir2wasm;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.backend.wasm.ir2wasm.WasmSignature;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.util.DumpIrTreeOptions;
import org.jetbrains.kotlin.ir.util.RenderIrElementKt;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\b\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\b\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\n\u0010\u0017\u001a\u00020\u0018H\u0096\u0080\u0004J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00060\bHÆ\u0003J\t\u0010\u001d\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001e\u001a\u00020\u000bHÆ\u0003JO\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\b2\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0014\u0010 \u001a\u00020\u000b2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\"\u001a\u00020#HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0016¨\u0006$"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/ir2wasm/WasmSignature;", "", "name", "Lorg/jetbrains/kotlin/name/Name;", "moduleNameForInternals", "extensionReceiverType", "Lorg/jetbrains/kotlin/ir/types/IrType;", "valueParametersType", "", "returnType", "isVirtual", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/ir/types/IrType;Ljava/util/List;Lorg/jetbrains/kotlin/ir/types/IrType;Z)V", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "getModuleNameForInternals", "getExtensionReceiverType", "()Lorg/jetbrains/kotlin/ir/types/IrType;", "getValueParametersType", "()Ljava/util/List;", "getReturnType", "()Z", "toString", "", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class WasmSignature {
    private final IrType extensionReceiverType;
    private final boolean isVirtual;
    private final Name moduleNameForInternals;
    private final Name name;
    private final IrType returnType;
    private final List<IrType> valueParametersType;

    public WasmSignature(Name name, Name name2, IrType irType, List<? extends IrType> list, IrType irType2, boolean z) {
        name.getClass();
        list.getClass();
        irType2.getClass();
        this.name = name;
        this.moduleNameForInternals = name2;
        this.extensionReceiverType = irType;
        this.valueParametersType = list;
        this.returnType = irType2;
        this.isVirtual = z;
    }

    public static CharSequence a(IrType irType) {
        irType.getClass();
        return RenderIrElementKt.render$default(irType, (DumpIrTreeOptions) null, 1, (Object) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ WasmSignature copy$default(WasmSignature wasmSignature, Name name, Name name2, IrType irType, List list, IrType irType2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            name = wasmSignature.name;
        }
        if ((i & 2) != 0) {
            name2 = wasmSignature.moduleNameForInternals;
        }
        if ((i & 4) != 0) {
            irType = wasmSignature.extensionReceiverType;
        }
        if ((i & 8) != 0) {
            list = wasmSignature.valueParametersType;
        }
        if ((i & 16) != 0) {
            irType2 = wasmSignature.returnType;
        }
        if ((i & 32) != 0) {
            z = wasmSignature.isVirtual;
        }
        IrType irType3 = irType2;
        boolean z2 = z;
        return wasmSignature.copy(name, name2, irType, list, irType3, z2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Name getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Name getModuleNameForInternals() {
        return this.moduleNameForInternals;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final IrType getExtensionReceiverType() {
        return this.extensionReceiverType;
    }

    public final List<IrType> component4() {
        return this.valueParametersType;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final IrType getReturnType() {
        return this.returnType;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getIsVirtual() {
        return this.isVirtual;
    }

    public final WasmSignature copy(Name name, Name moduleNameForInternals, IrType extensionReceiverType, List<? extends IrType> valueParametersType, IrType returnType, boolean isVirtual) {
        name.getClass();
        valueParametersType.getClass();
        returnType.getClass();
        return new WasmSignature(name, moduleNameForInternals, extensionReceiverType, valueParametersType, returnType, isVirtual);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WasmSignature)) {
            return false;
        }
        WasmSignature wasmSignature = (WasmSignature) other;
        return Intrinsics.areEqual(this.name, wasmSignature.name) && Intrinsics.areEqual(this.moduleNameForInternals, wasmSignature.moduleNameForInternals) && Intrinsics.areEqual(this.extensionReceiverType, wasmSignature.extensionReceiverType) && Intrinsics.areEqual(this.valueParametersType, wasmSignature.valueParametersType) && Intrinsics.areEqual(this.returnType, wasmSignature.returnType) && this.isVirtual == wasmSignature.isVirtual;
    }

    public final IrType getExtensionReceiverType() {
        return this.extensionReceiverType;
    }

    public final Name getModuleNameForInternals() {
        return this.moduleNameForInternals;
    }

    public final Name getName() {
        return this.name;
    }

    public final IrType getReturnType() {
        return this.returnType;
    }

    public final List<IrType> getValueParametersType() {
        return this.valueParametersType;
    }

    public int hashCode() {
        int iHashCode = this.name.hashCode() * 31;
        Name name = this.moduleNameForInternals;
        int iHashCode2 = (iHashCode + (name == null ? 0 : name.hashCode())) * 31;
        IrType irType = this.extensionReceiverType;
        return ((((((iHashCode2 + (irType != null ? irType.hashCode() : 0)) * 31) + this.valueParametersType.hashCode()) * 31) + this.returnType.hashCode()) * 31) + Boolean.hashCode(this.isVirtual);
    }

    public final boolean isVirtual() {
        return this.isVirtual;
    }

    public String toString() {
        String str;
        IrType irType = this.extensionReceiverType;
        if (irType != null) {
            str = "(er: " + RenderIrElementKt.render$default(irType, (DumpIrTreeOptions) null, 1, (Object) null) + ") ";
        } else {
            str = "";
        }
        String strJoinToString$default = CollectionsKt.joinToString$default(this.valueParametersType, ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: mkf
            public final Object invoke(Object obj) {
                return WasmSignature.a((IrType) obj);
            }
        }, 30, (Object) null);
        return "[" + (this.isVirtual ? "" : "(non-virtual) ") + str + this.name + Util.C_PARAM_START + strJoinToString$default + ") -> " + RenderIrElementKt.render$default(this.returnType, (DumpIrTreeOptions) null, 1, (Object) null) + ']';
    }
}
