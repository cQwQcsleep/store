package org.jetbrains.kotlin.library.impl;

import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\u0007\b\u0004¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H$J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010\n\u001a\u00020\u000b\u0082\u0001\u0003\f\r\u000e¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/library/impl/IrDataWriter;", "", "()V", "writeData", "", "dataOutput", "Ljava/io/DataOutput;", "writeIntoFile", "path", "", "writeIntoMemory", "", "Lorg/jetbrains/kotlin/library/impl/IrArrayWriter;", "Lorg/jetbrains/kotlin/library/impl/IrDeclarationWriter;", "Lorg/jetbrains/kotlin/library/impl/IrStringWriter;", "kotlin-util-klib"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class IrDataWriter {
    public /* synthetic */ IrDataWriter(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract void writeData(DataOutput dataOutput);

    public final void writeIntoFile(String path) {
        path.getClass();
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
            try {
                writeData(dataOutputStream);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(dataOutputStream, (Throwable) null);
                CloseableKt.closeFinally(fileOutputStream, (Throwable) null);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(dataOutputStream, th);
                    throw th2;
                }
            }
        } catch (Throwable th3) {
            try {
                throw th3;
            } catch (Throwable th4) {
                CloseableKt.closeFinally(fileOutputStream, th3);
                throw th4;
            }
        }
    }

    public final byte[] writeIntoMemory() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            writeData(dataOutputStream);
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(dataOutputStream, (Throwable) null);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArray.getClass();
            return byteArray;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(dataOutputStream, th);
                throw th2;
            }
        }
    }

    private IrDataWriter() {
    }
}
