package org.jetbrains.kotlin.library.impl;

import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.UInt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.library.encodings.WobblyTF8;
import org.jetbrains.kotlin.utils.Leb128Kt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0014R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/library/impl/IrStringWriter;", "Lorg/jetbrains/kotlin/library/impl/IrDataWriter;", "data", "", "", "useVarInt", "", "(Ljava/util/List;Z)V", "writeData", "", "dataOutput", "Ljava/io/DataOutput;", "kotlin-util-klib"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class IrStringWriter extends IrDataWriter {
    private final List<String> data;
    private final boolean useVarInt;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IrStringWriter(List<String> list, boolean z) {
        super(null);
        list.getClass();
        this.data = list;
        this.useVarInt = z;
    }

    @Override // org.jetbrains.kotlin.library.impl.IrDataWriter
    public void writeData(final DataOutput dataOutput) throws IOException {
        dataOutput.getClass();
        boolean z = this.useVarInt;
        List<String> list = this.data;
        if (z) {
            dataOutput.writeInt(-list.size());
        } else {
            dataOutput.writeInt(list.size());
        }
        List<String> list2 = this.data;
        WobblyTF8 wobblyTF8 = WobblyTF8.INSTANCE;
        ArrayList<byte[]> arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(wobblyTF8.encode((String) it.next()));
        }
        for (byte[] bArr : arrayList) {
            if (this.useVarInt) {
                Leb128Kt.writeUnsignedLeb128-qim9Vi0(UInt.constructor-impl(bArr.length), new Function1<Byte, Unit>() { // from class: org.jetbrains.kotlin.library.impl.IrStringWriter$writeData$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj) throws IOException {
                        invoke(((Number) obj).byteValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(byte b) throws IOException {
                        dataOutput.write(b);
                    }
                });
            } else {
                dataOutput.writeInt(bArr.length);
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            dataOutput.write((byte[]) it2.next());
        }
    }
}
