package org.jetbrains.kotlin.cli.common.localfs;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.local.CoreLocalFileSystem;
import java.io.File;
import java.nio.file.Path;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0016J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0012\u0010\r\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0016J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0007H\u0016J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0010\u001a\u00020\u0012H\u0016J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/localfs/KotlinLocalFileSystem;", "Lcom/intellij/openapi/vfs/local/CoreLocalFileSystem;", "<init>", "()V", "getProtocol", Argument.Delimiters.none, "findFileByPath", "Lcom/intellij/openapi/vfs/VirtualFile;", ModuleXmlParser.PATH, "refresh", Argument.Delimiters.none, "asynchronous", Argument.Delimiters.none, "refreshAndFindFileByPath", "getNioPath", "Ljava/nio/file/Path;", "file", "findFileByIoFile", "Ljava/io/File;", "findFileByNioFile", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KotlinLocalFileSystem extends CoreLocalFileSystem {
    public VirtualFile findFileByIoFile(File file) {
        file.getClass();
        if (file.exists()) {
            return new KotlinLocalVirtualFile(file, this, null, 4, null);
        }
        return null;
    }

    public VirtualFile findFileByNioFile(Path file) {
        file.getClass();
        File file2 = file.toFile();
        file2.getClass();
        return findFileByIoFile(file2);
    }

    public VirtualFile findFileByPath(String path) {
        path.getClass();
        return findFileByIoFile(new File(path));
    }

    public Path getNioPath(VirtualFile file) {
        File file2;
        file.getClass();
        KotlinLocalVirtualFile kotlinLocalVirtualFile = file instanceof KotlinLocalVirtualFile ? (KotlinLocalVirtualFile) file : null;
        if (kotlinLocalVirtualFile == null || (file2 = kotlinLocalVirtualFile.getFile()) == null) {
            return null;
        }
        return file2.toPath();
    }

    public String getProtocol() {
        return "file";
    }

    public void refresh(boolean asynchronous) {
    }

    public VirtualFile refreshAndFindFileByPath(String path) {
        path.getClass();
        return findFileByPath(path);
    }
}
