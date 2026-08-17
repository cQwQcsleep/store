package org.jetbrains.kotlin.backend.wasm.ir2wasm;

import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0006H\u0016R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0013\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\f¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/ir2wasm/ConstantDataIntegerArray;", "Lorg/jetbrains/kotlin/backend/wasm/ir2wasm/ConstantDataElement;", "value", "", "", "integerSize", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/util/List;I)V", "getValue", "()Ljava/util/List;", "getIntegerSize", "()I", "toBytes", "", "dump", "", "indent", "startAddress", "sizeInBytes", "getSizeInBytes", "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ConstantDataIntegerArray extends ConstantDataElement {
    private final int integerSize;
    private final int sizeInBytes;
    private final List<Long> value;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConstantDataIntegerArray(List<Long> list, int i) {
        super(null);
        list.getClass();
        this.value = list;
        this.integerSize = i;
        this.sizeInBytes = list.size() * i;
    }

    @Override // org.jetbrains.kotlin.backend.wasm.ir2wasm.ConstantDataElement
    public String dump(String indent, int startAddress) {
        indent.getClass();
        if (this.value.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(ConstantDataKt.addressToString(startAddress));
        sb.append(": ");
        sb.append(indent);
        sb.append(" i");
        sb.append(this.integerSize * 8);
        sb.append("[] : ");
        String string = Arrays.toString(toBytes());
        string.getClass();
        sb.append(string);
        sb.append("   ;;\n");
        return sb.toString();
    }

    public final int getIntegerSize() {
        return this.integerSize;
    }

    @Override // org.jetbrains.kotlin.backend.wasm.ir2wasm.ConstantDataElement
    public int getSizeInBytes() {
        return this.sizeInBytes;
    }

    public final List<Long> getValue() {
        return this.value;
    }

    @Override // org.jetbrains.kotlin.backend.wasm.ir2wasm.ConstantDataElement
    public byte[] toBytes() {
        byte[] bArr = new byte[this.value.size() * this.integerSize];
        int size = this.value.size();
        for (int i = 0; i < size; i++) {
            long jLongValue = this.value.get(i).longValue();
            int i2 = this.integerSize;
            ConstantDataKt.toLittleEndianBytesTo(jLongValue, bArr, i * i2, i2);
        }
        return bArr;
    }
}
