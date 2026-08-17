package org.jetbrains.kotlin.ir.backend.js.utils.serialization;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u000eJ\u000e\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u001cJ4\u0010\u001d\u001a\u00020\u000b\"\u0004\b\u0000\u0010\u001e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u001e0 2\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u00020\u000b0\"H\u0086\bø\u0001\u0000J7\u0010#\u001a\u0004\u0018\u0001H\u001e\"\u0004\b\u0000\u0010\u001e2\b\u0010$\u001a\u0004\u0018\u0001H\u001e2\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u00020\u000b0\"H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010&J\"\u0010'\u001a\u00020\u000b2\u0006\u0010(\u001a\u00020\u00172\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u000b0)H\u0086\bø\u0001\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006*"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/utils/serialization/DataWriter;", "", "<init>", "()V", "data", "Ljava/io/ByteArrayOutputStream;", "getData", "()Ljava/io/ByteArrayOutputStream;", "output", "Ljava/io/DataOutputStream;", "saveTo", "", "writeByte", "byte", "", "writeByteArray", "byteArray", "", "writeString", "string", "", "writeBoolean", "boolean", "", "writeInt", "int", "writeDouble", "double", "", "writeCollection", "T", "collection", "", "writeItem", "Lkotlin/Function1;", "ifNotNull", "t", "write", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "ifTrue", "condition", "Lkotlin/Function0;", "org.jetbrains.kotlin:backend.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class DataWriter {
    private final ByteArrayOutputStream data;
    private final DataOutputStream output;

    public DataWriter() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.data = byteArrayOutputStream;
        this.output = new DataOutputStream(byteArrayOutputStream);
    }

    public final ByteArrayOutputStream getData() {
        return this.data;
    }

    public final <T> T ifNotNull(T t, Function1<? super T, Unit> write) throws IOException {
        write.getClass();
        this.output.writeBoolean(t != null);
        if (t != null) {
            write.invoke(t);
        }
        return t;
    }

    public final void ifTrue(boolean condition, Function0<Unit> write) throws IOException {
        write.getClass();
        this.output.writeBoolean(condition);
        if (condition) {
            write.invoke();
        }
    }

    public final void saveTo(DataOutputStream output) throws IOException {
        output.getClass();
        this.data.writeTo(output);
    }

    public final void writeBoolean(boolean z) throws IOException {
        this.output.writeBoolean(z);
    }

    public final void writeByte(int i) throws IOException {
        if ((i & (-128)) == 0) {
            this.output.writeByte(i);
        } else {
            a3d.a("Byte out of bounds: ", i);
        }
    }

    public final void writeByteArray(byte[] byteArray) throws IOException {
        byteArray.getClass();
        this.output.writeInt(byteArray.length);
        this.output.write(byteArray);
    }

    public final <T> void writeCollection(Collection<? extends T> collection, Function1<? super T, Unit> writeItem) throws IOException {
        collection.getClass();
        writeItem.getClass();
        this.output.writeInt(collection.size());
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            writeItem.invoke(it.next());
        }
    }

    public final void writeDouble(double d) throws IOException {
        this.output.writeDouble(d);
    }

    public final void writeInt(int i) throws IOException {
        this.output.writeInt(i);
    }

    public final void writeString(String string) throws IOException {
        string.getClass();
        byte[] bytes = string.getBytes(ConstantsKt.getSerializationCharset());
        bytes.getClass();
        writeByteArray(bytes);
    }
}
