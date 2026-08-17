package org.jetbrains.kotlin.cli.jvm.compiler.jarfs;

import com.intellij.openapi.util.io.BufferExposingByteArrayInputStream;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileSystem;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.load.kotlin.LibraryContainerAwareVirtualFile;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B3\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0000\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\f\u0010\rJ\u0006\u0010\u0014\u001a\u00020\u0015J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0006H\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u0017H\u0016J\b\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020\u001fH\u0016J\b\u0010!\u001a\u00020\u001fH\u0016J\n\u0010\"\u001a\u0004\u0018\u00010\u0001H\u0016J\u0013\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00010\u000fH\u0016¢\u0006\u0002\u0010$J \u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\b2\u0006\u0010*\u001a\u00020\bH\u0016J\b\u0010+\u001a\u00020,H\u0016J\b\u0010-\u001a\u00020\bH\u0016J\b\u0010.\u001a\u00020\bH\u0016J\"\u0010/\u001a\u00020\u00152\u0006\u00100\u001a\u00020\u001f2\u0006\u00101\u001a\u00020\u001f2\b\u00102\u001a\u0004\u0018\u000103H\u0016J\b\u00104\u001a\u000205H\u0016J\b\u00106\u001a\u00020\bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R6\u0010\u000e\u001a(\u0012\f\u0012\n \u0010*\u0004\u0018\u00010\u00010\u0001 \u0010*\u0014\u0012\u000e\b\u0001\u0012\n \u0010*\u0004\u0018\u00010\u00010\u0001\u0018\u00010\u000f0\u000fX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0011R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00010\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00067"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/jarfs/FastJarVirtualFile;", "Lcom/intellij/openapi/vfs/VirtualFile;", "Lorg/jetbrains/kotlin/load/kotlin/LibraryContainerAwareVirtualFile;", "handler", "Lorg/jetbrains/kotlin/cli/jvm/compiler/jarfs/FastJarHandler;", ModuleXmlParser.NAME, Argument.Delimiters.none, "length", Argument.Delimiters.none, "parent", "entryDescription", "Lorg/jetbrains/kotlin/cli/jvm/compiler/jarfs/ZipEntryDescription;", "<init>", "(Lorg/jetbrains/kotlin/cli/jvm/compiler/jarfs/FastJarHandler;Ljava/lang/CharSequence;JLorg/jetbrains/kotlin/cli/jvm/compiler/jarfs/FastJarVirtualFile;Lorg/jetbrains/kotlin/cli/jvm/compiler/jarfs/ZipEntryDescription;)V", "myChildrenArray", Argument.Delimiters.none, "kotlin.jvm.PlatformType", "[Lcom/intellij/openapi/vfs/VirtualFile;", "myChildrenList", Argument.Delimiters.none, "initChildrenArrayFromList", Argument.Delimiters.none, "getName", Argument.Delimiters.none, "getNameSequence", "getFileSystem", "Lcom/intellij/openapi/vfs/VirtualFileSystem;", "getContainingLibraryPath", "Ljava/nio/file/Path;", "getPath", "isWritable", Argument.Delimiters.none, "isDirectory", "isValid", "getParent", "getChildren", "()[Lcom/intellij/openapi/vfs/VirtualFile;", "getOutputStream", "Ljava/io/OutputStream;", "requestor", Argument.Delimiters.none, "newModificationStamp", "newTimeStamp", "contentsToByteArray", Argument.Delimiters.none, "getTimeStamp", "getLength", "refresh", "asynchronous", "recursive", "postRunnable", "Ljava/lang/Runnable;", "getInputStream", "Ljava/io/InputStream;", "getModificationStamp", "kotlin-compiler"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 3, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FastJarVirtualFile extends VirtualFile implements LibraryContainerAwareVirtualFile {
    private final ZipEntryDescription entryDescription;
    private final FastJarHandler handler;
    private final long length;
    private VirtualFile[] myChildrenArray;
    private final List<VirtualFile> myChildrenList;
    private final CharSequence name;
    private final FastJarVirtualFile parent;

    public FastJarVirtualFile(FastJarHandler fastJarHandler, CharSequence charSequence, long j, FastJarVirtualFile fastJarVirtualFile, ZipEntryDescription zipEntryDescription) {
        List<VirtualFile> list;
        fastJarHandler.getClass();
        charSequence.getClass();
        this.handler = fastJarHandler;
        this.name = charSequence;
        this.length = j;
        this.parent = fastJarVirtualFile;
        this.entryDescription = zipEntryDescription;
        this.myChildrenArray = VirtualFile.EMPTY_ARRAY;
        this.myChildrenList = new ArrayList();
        if (fastJarVirtualFile == null || (list = fastJarVirtualFile.myChildrenList) == null) {
            return;
        }
        list.add(this);
    }

    public byte[] contentsToByteArray() throws IOException {
        ZipEntryDescription zipEntryDescription = this.entryDescription;
        return zipEntryDescription == null ? FastJarVirtualFileKt.EMPTY_BYTE_ARRAY : this.handler.contentsToByteArray(zipEntryDescription);
    }

    public VirtualFile[] getChildren() {
        VirtualFile[] virtualFileArr = this.myChildrenArray;
        virtualFileArr.getClass();
        return virtualFileArr;
    }

    public Path getContainingLibraryPath() {
        Path path = this.handler.getFile().toPath();
        path.getClass();
        return path;
    }

    public VirtualFileSystem getFileSystem() {
        return this.handler.getFileSystem();
    }

    public InputStream getInputStream() throws IOException {
        return new BufferExposingByteArrayInputStream(contentsToByteArray());
    }

    public long getLength() {
        return this.length;
    }

    public long getModificationStamp() {
        return 0L;
    }

    public String getName() {
        return this.name.toString();
    }

    /* JADX INFO: renamed from: getNameSequence, reason: from getter */
    public CharSequence getName() {
        return this.name;
    }

    public OutputStream getOutputStream(Object requestor, long newModificationStamp, long newTimeStamp) throws IOException {
        requestor.getClass();
        throw new UnsupportedOperationException("JarFileSystem is read-only");
    }

    public VirtualFile getParent() {
        return this.parent;
    }

    public String getPath() {
        FastJarVirtualFile fastJarVirtualFile = this.parent;
        if (fastJarVirtualFile == null) {
            return FileUtil.toSystemIndependentName(this.handler.getFile().getPath()) + "!/";
        }
        String path = fastJarVirtualFile.getPath();
        StringBuilder sb = new StringBuilder(path.length() + 1 + this.name.length());
        sb.append(path);
        if (sb.charAt(sb.length() - 1) != '/') {
            sb.append('/');
        }
        sb.append(this.name);
        return sb.toString();
    }

    public long getTimeStamp() {
        return 0L;
    }

    public final void initChildrenArrayFromList() {
        this.myChildrenArray = (VirtualFile[]) this.myChildrenList.toArray(new VirtualFile[0]);
        this.myChildrenList.clear();
    }

    public boolean isDirectory() {
        return this.length < 0;
    }

    public boolean isValid() {
        return true;
    }

    public boolean isWritable() {
        return false;
    }

    public void refresh(boolean asynchronous, boolean recursive, Runnable postRunnable) {
    }
}
