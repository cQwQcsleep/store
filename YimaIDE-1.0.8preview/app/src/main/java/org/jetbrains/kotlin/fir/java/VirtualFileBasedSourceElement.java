package org.jetbrains.kotlin.fir.java;

import com.intellij.openapi.vfs.VirtualFile;
import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.SourceElement;
import org.jetbrains.kotlin.descriptors.SourceFile;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/VirtualFileBasedSourceElement;", "Lorg/jetbrains/kotlin/descriptors/SourceElement;", "virtualFile", "Lcom/intellij/openapi/vfs/VirtualFile;", "<init>", "(Lcom/intellij/openapi/vfs/VirtualFile;)V", "getVirtualFile", "()Lcom/intellij/openapi/vfs/VirtualFile;", "getContainingFile", "Lorg/jetbrains/kotlin/descriptors/SourceFile;", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class VirtualFileBasedSourceElement implements SourceElement {
    private final VirtualFile virtualFile;

    public VirtualFileBasedSourceElement(VirtualFile virtualFile) {
        virtualFile.getClass();
        this.virtualFile = virtualFile;
    }

    @Override // org.jetbrains.kotlin.descriptors.SourceElement
    public SourceFile getContainingFile() {
        SourceFile sourceFile = SourceFile.NO_SOURCE_FILE;
        sourceFile.getClass();
        return sourceFile;
    }

    public final VirtualFile getVirtualFile() {
        return this.virtualFile;
    }
}
