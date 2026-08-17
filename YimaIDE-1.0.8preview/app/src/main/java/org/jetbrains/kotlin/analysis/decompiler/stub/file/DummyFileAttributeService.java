package org.jetbrains.kotlin.analysis.decompiler.stub.file;

import com.intellij.openapi.vfs.VirtualFile;
import java.io.DataInput;
import java.io.DataOutput;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.backend.konan.InteropFqNames;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003JK\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u0002H\u00062\u0018\u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u00020\u000f0\rH\u0016¢\u0006\u0002\u0010\u0010J:\u0010\u0011\u001a\n\u0012\u0004\u0012\u0002H\u0006\u0018\u00010\u0005\"\u0004\b\u0000\u0010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u0002H\u00060\u0013H\u0016¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/analysis/decompiler/stub/file/DummyFileAttributeService;", "Lorg/jetbrains/kotlin/analysis/decompiler/stub/file/FileAttributeService;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", InteropFqNames.cValueWriteFunName, "Lorg/jetbrains/kotlin/analysis/decompiler/stub/file/CachedAttributeData;", "T", "file", "Lcom/intellij/openapi/vfs/VirtualFile;", "id", "", "value", "writeValueFun", "Lkotlin/Function2;", "Ljava/io/DataOutput;", "", "(Lcom/intellij/openapi/vfs/VirtualFile;Ljava/lang/String;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Lorg/jetbrains/kotlin/analysis/decompiler/stub/file/CachedAttributeData;", "read", "readValueFun", "Lkotlin/Function1;", "Ljava/io/DataInput;", "org.jetbrains.kotlin:decompiler-to-file-stubs"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class DummyFileAttributeService implements FileAttributeService {
    @Override // org.jetbrains.kotlin.analysis.decompiler.stub.file.FileAttributeService
    public <T> CachedAttributeData<T> read(VirtualFile file, String id, Function1<? super DataInput, ? extends T> readValueFun) {
        file.getClass();
        id.getClass();
        readValueFun.getClass();
        return null;
    }

    @Override // org.jetbrains.kotlin.analysis.decompiler.stub.file.FileAttributeService
    public <T> CachedAttributeData<T> write(VirtualFile file, String id, T value, Function2<? super DataOutput, ? super T, Unit> writeValueFun) {
        file.getClass();
        id.getClass();
        writeValueFun.getClass();
        return new CachedAttributeData<>(value, 0L);
    }
}
