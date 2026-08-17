package org.jetbrains.kotlin.backend.wasm.ir2wasm;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\u000f\u001a\u00020\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/ir2wasm/ConstantDataStruct;", "Lorg/jetbrains/kotlin/backend/wasm/ir2wasm/ConstantDataElement;", "elements", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/util/List;)V", "getElements", "()Ljava/util/List;", "toBytes", "", "dump", "", "indent", "startAddress", "", "sizeInBytes", "getSizeInBytes", "()I", "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ConstantDataStruct extends ConstantDataElement {
    private final List<ConstantDataElement> elements;
    private final int sizeInBytes;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ConstantDataStruct(List<? extends ConstantDataElement> list) {
        super(null);
        list.getClass();
        this.elements = list;
        Iterator it = list.iterator();
        int sizeInBytes = 0;
        while (it.hasNext()) {
            sizeInBytes += ((ConstantDataElement) it.next()).getSizeInBytes();
        }
        this.sizeInBytes = sizeInBytes;
    }

    @Override // org.jetbrains.kotlin.backend.wasm.ir2wasm.ConstantDataElement
    public String dump(String indent, int startAddress) {
        indent.getClass();
        String string = indent + ";;\n";
        for (ConstantDataElement constantDataElement : this.elements) {
            StringBuilder sb = new StringBuilder();
            sb.append(string);
            sb.append(constantDataElement.dump(indent + "  ", startAddress));
            string = sb.toString();
            startAddress += constantDataElement.getSizeInBytes();
        }
        return string;
    }

    public final List<ConstantDataElement> getElements() {
        return this.elements;
    }

    @Override // org.jetbrains.kotlin.backend.wasm.ir2wasm.ConstantDataElement
    public int getSizeInBytes() {
        return this.sizeInBytes;
    }

    @Override // org.jetbrains.kotlin.backend.wasm.ir2wasm.ConstantDataElement
    public byte[] toBytes() {
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        Iterator<T> it = this.elements.iterator();
        while (it.hasNext()) {
            for (byte b : ((ConstantDataElement) it.next()).toBytes()) {
                listCreateListBuilder.add(Byte.valueOf(b));
            }
        }
        return CollectionsKt.toByteArray(CollectionsKt.build(listCreateListBuilder));
    }
}
