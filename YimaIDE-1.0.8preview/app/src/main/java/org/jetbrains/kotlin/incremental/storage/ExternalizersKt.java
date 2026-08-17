package org.jetbrains.kotlin.incremental.storage;

import com.intellij.util.containers.hash.EqualityPolicy;
import com.intellij.util.io.DataExternalizer;
import com.intellij.util.io.KeyDescriptor;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import org.jetbrains.kotlin.metadata.jvm.deserialization.JvmProtoBufUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003\u001a+\u0010\u0004\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u0002H\u0002¢\u0006\u0002\u0010\t\u001a#\u0010\n\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\u000b\u001a#\u0010\f\u001a\u00020\r\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\b\u001a\u0002H\u0002¢\u0006\u0002\u0010\u000e\u001a#\u0010\u000f\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0010\u001a\u00020\r¢\u0006\u0002\u0010\u0011\u001a\u0012\u0010\u0012\u001a\u00020\u0005*\u00020\u00132\u0006\u0010\b\u001a\u00020\u0014\u001a\n\u0010\u0015\u001a\u00020\u0014*\u00020\u0016¨\u0006\u0017"}, d2 = {"toDescriptor", "Lcom/intellij/util/io/KeyDescriptor;", "T", "Lcom/intellij/util/io/DataExternalizer;", "saveToFile", "", "file", "Ljava/io/File;", "value", "(Lcom/intellij/util/io/DataExternalizer;Ljava/io/File;Ljava/lang/Object;)V", "loadFromFile", "(Lcom/intellij/util/io/DataExternalizer;Ljava/io/File;)Ljava/lang/Object;", "toByteArray", "", "(Lcom/intellij/util/io/DataExternalizer;Ljava/lang/Object;)[B", "fromByteArray", "byteArray", "(Lcom/intellij/util/io/DataExternalizer;[B)Ljava/lang/Object;", "writeString", "Ljava/io/DataOutput;", "", "readString", "Ljava/io/DataInput;", "org.jetbrains.kotlin:kotlin-build-common"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class ExternalizersKt {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: org.jetbrains.kotlin.incremental.storage.ExternalizersKt$toDescriptor$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000?\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u00012\b\u0012\u0004\u0012\u00028\u00000\u00022\b\u0012\u0004\u0012\u00028\u00000\u0003J\u001e\u0010\u0004\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n \u0007*\u0004\u0018\u00018\u00008\u0000H\u0096\u0001¢\u0006\u0002\u0010\bJ.\u0010\t\u001a\u00020\n2\u000e\u0010\u0006\u001a\n \u0007*\u0004\u0018\u00018\u00008\u00002\u000e\u0010\u000b\u001a\n \u0007*\u0004\u0018\u00018\u00008\u0000H\u0096\u0001¢\u0006\u0002\u0010\fJ*\u0010\r\u001a\n \u0007*\u0004\u0018\u00018\u00008\u00002\u0012\b\u0001\u0010\u0006\u001a\f0\u000e¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0096\u0001¢\u0006\u0002\u0010\u0011J2\u0010\u0012\u001a\u00020\u00132\u0012\b\u0001\u0010\u0006\u001a\f0\u0014¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u00102\u000e\u0010\u000b\u001a\n \u0007*\u0004\u0018\u00018\u00008\u0000H\u0096\u0001¢\u0006\u0002\u0010\u0015¨\u0006\u0016"}, d2 = {"org/jetbrains/kotlin/incremental/storage/ExternalizersKt$toDescriptor$1", "Lcom/intellij/util/io/KeyDescriptor;", "Lcom/intellij/util/io/DataExternalizer;", "Lcom/intellij/util/containers/hash/EqualityPolicy;", "getHashCode", "", "p0", JvmProtoBufUtil.PLATFORM_TYPE_ID, "(Ljava/lang/Object;)I", "isEqual", "", "p1", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "read", "Ljava/io/DataInput;", "Lorg/jetbrains/annotations/NotNull;", "Lkotlin/jvm/internal/EnhancedNullability;", "(Ljava/io/DataInput;)Ljava/lang/Object;", "save", "", "Ljava/io/DataOutput;", "(Ljava/io/DataOutput;Ljava/lang/Object;)V", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class AnonymousClass1<T> implements EqualityPolicy<T>, DataExternalizer<T>, KeyDescriptor<T> {
        private final /* synthetic */ DataExternalizer<T> $$delegate_0;
        private final /* synthetic */ DefaultEqualityPolicy<T> $$delegate_1 = new DefaultEqualityPolicy<>();

        public AnonymousClass1(DataExternalizer<T> dataExternalizer) {
            this.$$delegate_0 = dataExternalizer;
        }

        public int getHashCode(T p0) {
            return this.$$delegate_1.getHashCode(p0);
        }

        public boolean isEqual(T p0, T p1) {
            return this.$$delegate_1.isEqual(p0, p1);
        }

        public T read(DataInput p0) {
            p0.getClass();
            return (T) this.$$delegate_0.read(p0);
        }

        public void save(DataOutput p0, T p1) {
            p0.getClass();
            this.$$delegate_0.save(p0, p1);
        }
    }

    public static final <T> T fromByteArray(DataExternalizer<T> dataExternalizer, byte[] bArr) {
        dataExternalizer.getClass();
        bArr.getClass();
        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new ByteArrayInputStream(bArr), 8192));
        try {
            T t = (T) dataExternalizer.read(dataInputStream);
            CloseableKt.closeFinally(dataInputStream, (Throwable) null);
            return t;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(dataInputStream, th);
                throw th2;
            }
        }
    }

    public static final <T> T loadFromFile(DataExternalizer<T> dataExternalizer, File file) {
        dataExternalizer.getClass();
        file.getClass();
        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(file), 8192));
        try {
            T t = (T) dataExternalizer.read(dataInputStream);
            CloseableKt.closeFinally(dataInputStream, (Throwable) null);
            return t;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(dataInputStream, th);
                throw th2;
            }
        }
    }

    public static final String readString(DataInput dataInput) {
        dataInput.getClass();
        return StringExternalizer.INSTANCE.read(dataInput);
    }

    public static final <T> void saveToFile(DataExternalizer<T> dataExternalizer, File file, T t) {
        dataExternalizer.getClass();
        file.getClass();
        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file), 8192));
        try {
            dataExternalizer.save(dataOutputStream, t);
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(dataOutputStream, (Throwable) null);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(dataOutputStream, th);
                throw th2;
            }
        }
    }

    public static final <T> byte[] toByteArray(DataExternalizer<T> dataExternalizer, T t) {
        dataExternalizer.getClass();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(byteArrayOutputStream, 8192));
        try {
            dataExternalizer.save(dataOutputStream, t);
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

    public static final <T> KeyDescriptor<T> toDescriptor(DataExternalizer<T> dataExternalizer) {
        dataExternalizer.getClass();
        return new AnonymousClass1(dataExternalizer);
    }

    public static final void writeString(DataOutput dataOutput, String str) {
        dataOutput.getClass();
        str.getClass();
        StringExternalizer.INSTANCE.save(dataOutput, str);
    }
}
