package org.jetbrains.kotlin.library.impl;

import java.io.DataOutput;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.library.SerializedDeclaration;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0014R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/library/impl/IrDeclarationWriter;", "Lorg/jetbrains/kotlin/library/impl/IrDataWriter;", "declarations", "", "Lorg/jetbrains/kotlin/library/SerializedDeclaration;", "(Ljava/util/List;)V", "writeData", "", "dataOutput", "Ljava/io/DataOutput;", "Companion", "kotlin-util-klib"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class IrDeclarationWriter extends IrDataWriter {
    private final List<SerializedDeclaration> declarations;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IrDeclarationWriter(List<SerializedDeclaration> list) {
        super(null);
        list.getClass();
        this.declarations = list;
    }

    @Override // org.jetbrains.kotlin.library.impl.IrDataWriter
    public void writeData(DataOutput dataOutput) throws IOException {
        dataOutput.getClass();
        dataOutput.writeInt(this.declarations.size());
        int size = (this.declarations.size() * 12) + 4;
        for (SerializedDeclaration serializedDeclaration : this.declarations) {
            dataOutput.writeInt(serializedDeclaration.getId());
            dataOutput.writeInt(size);
            dataOutput.writeInt(serializedDeclaration.getSize());
            size += serializedDeclaration.getSize();
        }
        Iterator<SerializedDeclaration> it = this.declarations.iterator();
        while (it.hasNext()) {
            dataOutput.write(it.next().getBytes());
        }
    }
}
