package org.jetbrains.kotlin.backend.wasm.utils;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.types.IrTypesKt;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000bR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/utils/WasmArrayInfo;", "", "klass", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "isNullable", "", "isMutable", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/declarations/IrClass;ZZ)V", "getKlass", "()Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "()Z", "type", "Lorg/jetbrains/kotlin/ir/types/IrType;", "getType", "()Lorg/jetbrains/kotlin/ir/types/IrType;", "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class WasmArrayInfo {
    private final boolean isMutable;
    private final boolean isNullable;
    private final IrClass klass;
    private final IrType type;

    public WasmArrayInfo(IrClass irClass, boolean z, boolean z2) {
        irClass.getClass();
        this.klass = irClass;
        this.isNullable = z;
        this.isMutable = z2;
        IrType defaultType = IrUtilsKt.getDefaultType(irClass);
        this.type = z ? IrTypesKt.makeNullable(defaultType) : defaultType;
    }

    public final IrClass getKlass() {
        return this.klass;
    }

    public final IrType getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: isMutable, reason: from getter */
    public final boolean getIsMutable() {
        return this.isMutable;
    }

    /* JADX INFO: renamed from: isNullable, reason: from getter */
    public final boolean getIsNullable() {
        return this.isNullable;
    }
}
