package org.jetbrains.kotlin.analysis.decompiler.stub.file;

import com.intellij.openapi.vfs.VirtualFile;
import java.io.DataInput;
import java.io.DataOutput;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.backend.konan.InteropFqNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u0016J;\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\f0\u000b\"\u000e\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\r2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u0002H\fH\u0016¢\u0006\u0002\u0010\u0011J>\u0010\u0012\u001a\n\u0012\u0004\u0012\u0002H\f\u0018\u00010\u000b\"\u000e\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\r2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\f0\u0014H\u0016J&\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\t0\u000b2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\tH\u0016J \u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u000b2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000fH\u0016JK\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\f0\u000b\"\u0004\b\u0000\u0010\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u0002H\f2\u0018\u0010\u0018\u001a\u0014\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u00020\u00030\u0019H&¢\u0006\u0002\u0010\u001bJ:\u0010\u001c\u001a\n\u0012\u0004\u0012\u0002H\f\u0018\u00010\u000b\"\u0004\b\u0000\u0010\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u0002H\f0\u001eH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006 À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/analysis/decompiler/stub/file/FileAttributeService;", "", "register", "", "id", "", "version", "", "fixedSize", "", "writeEnumAttribute", "Lorg/jetbrains/kotlin/analysis/decompiler/stub/file/CachedAttributeData;", "T", "", "file", "Lcom/intellij/openapi/vfs/VirtualFile;", "value", "(Ljava/lang/String;Lcom/intellij/openapi/vfs/VirtualFile;Ljava/lang/Enum;)Lorg/jetbrains/kotlin/analysis/decompiler/stub/file/CachedAttributeData;", "readEnumAttribute", "klass", "Ljava/lang/Class;", "writeBooleanAttribute", "readBooleanAttribute", InteropFqNames.cValueWriteFunName, "writeValueFun", "Lkotlin/Function2;", "Ljava/io/DataOutput;", "(Lcom/intellij/openapi/vfs/VirtualFile;Ljava/lang/String;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Lorg/jetbrains/kotlin/analysis/decompiler/stub/file/CachedAttributeData;", "read", "readValueFun", "Lkotlin/Function1;", "Ljava/io/DataInput;", "org.jetbrains.kotlin:decompiler-to-file-stubs"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface FileAttributeService {
    static /* synthetic */ void register$default(FileAttributeService fileAttributeService, String str, int i, boolean z, int i2, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: register");
            return;
        }
        if ((i2 & 4) != 0) {
            z = true;
        }
        fileAttributeService.register(str, i, z);
    }

    <T> CachedAttributeData<T> read(VirtualFile file, String id, Function1<? super DataInput, ? extends T> readValueFun);

    default CachedAttributeData<Boolean> readBooleanAttribute(String id, VirtualFile file) {
        id.getClass();
        file.getClass();
        return null;
    }

    default <T extends Enum<T>> CachedAttributeData<T> readEnumAttribute(String id, VirtualFile file, Class<T> klass) {
        id.getClass();
        file.getClass();
        klass.getClass();
        return null;
    }

    default void register(String id, int version, boolean fixedSize) {
        id.getClass();
    }

    <T> CachedAttributeData<T> write(VirtualFile file, String id, T value, Function2<? super DataOutput, ? super T, Unit> writeValueFun);

    default CachedAttributeData<Boolean> writeBooleanAttribute(String id, VirtualFile file, boolean value) {
        id.getClass();
        file.getClass();
        return new CachedAttributeData<>(Boolean.valueOf(value), file.getTimeStamp());
    }

    default <T extends Enum<T>> CachedAttributeData<T> writeEnumAttribute(String id, VirtualFile file, T value) {
        id.getClass();
        file.getClass();
        value.getClass();
        return new CachedAttributeData<>(value, file.getTimeStamp());
    }
}
