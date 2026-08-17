package org.jetbrains.kotlin;

import com.intellij.openapi.vfs.VirtualFile;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0096\u0082\u0004J\n\u0010\u0014\u001a\u00020\u0015H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/KtVirtualFileSourceFile;", "Lorg/jetbrains/kotlin/KtSourceFile;", "virtualFile", "Lcom/intellij/openapi/vfs/VirtualFile;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lcom/intellij/openapi/vfs/VirtualFile;)V", "getVirtualFile", "()Lcom/intellij/openapi/vfs/VirtualFile;", "name", "", "getName", "()Ljava/lang/String;", "path", "getPath", "getContentsAsStream", "Ljava/io/InputStream;", "equals", "", "other", "", "hashCode", "", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class KtVirtualFileSourceFile implements KtSourceFile {
    private final VirtualFile virtualFile;

    public KtVirtualFileSourceFile(VirtualFile virtualFile) {
        virtualFile.getClass();
        this.virtualFile = virtualFile;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        KtVirtualFileSourceFile ktVirtualFileSourceFile = other instanceof KtVirtualFileSourceFile ? (KtVirtualFileSourceFile) other : null;
        return Intrinsics.areEqual(ktVirtualFileSourceFile != null ? ktVirtualFileSourceFile.virtualFile : null, this.virtualFile);
    }

    public InputStream getContentsAsStream() {
        InputStream inputStream = this.virtualFile.getInputStream();
        inputStream.getClass();
        return inputStream;
    }

    public String getName() {
        String name = this.virtualFile.getName();
        name.getClass();
        return name;
    }

    public String getPath() {
        String path = this.virtualFile.getPath();
        path.getClass();
        return path;
    }

    public final VirtualFile getVirtualFile() {
        return this.virtualFile;
    }

    public int hashCode() {
        return this.virtualFile.hashCode();
    }
}
