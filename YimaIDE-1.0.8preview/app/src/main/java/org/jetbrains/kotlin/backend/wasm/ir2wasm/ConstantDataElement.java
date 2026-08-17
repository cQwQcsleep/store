package org.jetbrains.kotlin.backend.wasm.ir2wasm;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\u0005H&J\b\u0010\f\u001a\u00020\rH&R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0006\u000e\u000f\u0010\u0011\u0012\u0013¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/ir2wasm/ConstantDataElement;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "sizeInBytes", "", "getSizeInBytes", "()I", "dump", "", "indent", "startAddress", "toBytes", "", "Lorg/jetbrains/kotlin/backend/wasm/ir2wasm/ConstantDataCharArray;", "Lorg/jetbrains/kotlin/backend/wasm/ir2wasm/ConstantDataCharField;", "Lorg/jetbrains/kotlin/backend/wasm/ir2wasm/ConstantDataIntArray;", "Lorg/jetbrains/kotlin/backend/wasm/ir2wasm/ConstantDataIntField;", "Lorg/jetbrains/kotlin/backend/wasm/ir2wasm/ConstantDataIntegerArray;", "Lorg/jetbrains/kotlin/backend/wasm/ir2wasm/ConstantDataStruct;", "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class ConstantDataElement {
    public /* synthetic */ ConstantDataElement(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public static /* synthetic */ String dump$default(ConstantDataElement constantDataElement, String str, int i, int i2, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: dump");
            return null;
        }
        if ((i2 & 1) != 0) {
            str = "";
        }
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return constantDataElement.dump(str, i);
    }

    public abstract String dump(String indent, int startAddress);

    public abstract int getSizeInBytes();

    public abstract byte[] toBytes();

    private ConstantDataElement() {
    }
}
