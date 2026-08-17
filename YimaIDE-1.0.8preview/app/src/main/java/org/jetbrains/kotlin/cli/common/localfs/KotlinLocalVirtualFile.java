package org.jetbrains.kotlin.cli.common.localfs;

import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileSystem;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\u0014\u001a\u00020\fH\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\fH\u0016J\b\u0010\u0018\u001a\u00020\u000fH\u0016J\b\u0010\u0019\u001a\u00020\u000fH\u0016J\b\u0010\u001a\u001a\u00020\u000fH\u0016J\n\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u0016J\u0013\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00010\u0012H\u0016¢\u0006\u0002\u0010\u001dJ\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u001f\u001a\u00020\fH\u0016JL\u0010 \u001a\u0004\u0018\u0001H!\"\u0004\b\u0000\u0010!\"\u000e\b\u0001\u0010\"*\b\u0012\u0004\u0012\u0002H\"0#*\b\u0012\u0004\u0012\u0002H!0\u00122\u0006\u0010$\u001a\u0002H\"2\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u0002H!\u0012\u0004\u0012\u0002H\"0&H\u0082\b¢\u0006\u0002\u0010'J\"\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010+2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020-H\u0016J\b\u0010/\u001a\u000200H\u0016J\b\u00101\u001a\u00020-H\u0016J\b\u00102\u001a\u00020-H\u0016J\"\u00103\u001a\u0002042\u0006\u00105\u001a\u00020\u000f2\u0006\u00106\u001a\u00020\u000f2\b\u00107\u001a\u0004\u0018\u000108H\u0016J\b\u00109\u001a\u00020:H\u0016J\b\u0010;\u001a\u00020-H\u0016J\b\u0010<\u001a\u00020\u000fH\u0016J\u0014\u0010=\u001a\u00020\u000f2\b\u0010>\u001a\u0004\u0018\u00010+H\u0096\u0082\u0004J\n\u0010?\u001a\u00020@H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0010R\u0018\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0013¨\u0006A"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/localfs/KotlinLocalVirtualFile;", "Lcom/intellij/openapi/vfs/VirtualFile;", "file", "Ljava/io/File;", "_fileSystem", "Lorg/jetbrains/kotlin/cli/common/localfs/KotlinLocalFileSystem;", "parent", "<init>", "(Ljava/io/File;Lorg/jetbrains/kotlin/cli/common/localfs/KotlinLocalFileSystem;Lorg/jetbrains/kotlin/cli/common/localfs/KotlinLocalVirtualFile;)V", "getFile", "()Ljava/io/File;", "_name", Argument.Delimiters.none, "_parent", "_isDirectory", Argument.Delimiters.none, "Ljava/lang/Boolean;", "_children", Argument.Delimiters.none, "[Lcom/intellij/openapi/vfs/VirtualFile;", "getName", "getFileSystem", "Lcom/intellij/openapi/vfs/VirtualFileSystem;", "getPath", "isWritable", "isDirectory", "isValid", "getParent", "getChildren", "()[Lcom/intellij/openapi/vfs/VirtualFile;", "findChild", ModuleXmlParser.NAME, "binarySearchBy", "T", "K", Argument.Delimiters.none, "key", "selector", "Lkotlin/Function1;", "([Ljava/lang/Object;Ljava/lang/Comparable;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "getOutputStream", "Ljava/io/OutputStream;", "requestor", Argument.Delimiters.none, "newModificationStamp", Argument.Delimiters.none, "newTimeStamp", "contentsToByteArray", Argument.Delimiters.none, "getTimeStamp", "getLength", "refresh", Argument.Delimiters.none, "asynchronous", "recursive", "postRunnable", "Ljava/lang/Runnable;", "getInputStream", "Ljava/io/InputStream;", "getModificationStamp", "isInLocalFileSystem", "equals", "other", "hashCode", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KotlinLocalVirtualFile extends VirtualFile {
    private VirtualFile[] _children;
    private final KotlinLocalFileSystem _fileSystem;
    private Boolean _isDirectory;
    private String _name;
    private KotlinLocalVirtualFile _parent;
    private final File file;

    public KotlinLocalVirtualFile(File file, KotlinLocalFileSystem kotlinLocalFileSystem, KotlinLocalVirtualFile kotlinLocalVirtualFile) {
        file.getClass();
        kotlinLocalFileSystem.getClass();
        this.file = file;
        this._fileSystem = kotlinLocalFileSystem;
        this._parent = kotlinLocalVirtualFile;
    }

    public byte[] contentsToByteArray() {
        byte[] bArrLoadFileBytes = FileUtil.loadFileBytes(this.file);
        bArrLoadFileBytes.getClass();
        return bArrLoadFileBytes;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(KotlinLocalVirtualFile.class, other != null ? other.getClass() : null)) {
            return false;
        }
        other.getClass();
        return Intrinsics.areEqual(this.file, ((KotlinLocalVirtualFile) other).file);
    }

    public VirtualFile findChild(String name) {
        name.getClass();
        VirtualFile[] children = getChildren();
        int length = children.length - 1;
        int i = 0;
        while (i <= length) {
            int i2 = (i + length) >>> 1;
            VirtualFile virtualFile = children[i2];
            String name2 = virtualFile.getName();
            name2.getClass();
            int iCompareValues = ComparisonsKt.compareValues(name2, name);
            if (iCompareValues < 0) {
                i = i2 + 1;
            } else {
                if (iCompareValues <= 0) {
                    return virtualFile;
                }
                length = i2 - 1;
            }
        }
        return null;
    }

    public VirtualFile[] getChildren() {
        VirtualFile[] virtualFileArr = this._children;
        if (virtualFileArr != null) {
            return virtualFileArr;
        }
        File[] fileArrListFiles = this.file.listFiles();
        if (fileArrListFiles == null) {
            fileArrListFiles = new File[0];
        }
        ArrayList arrayList = new ArrayList(fileArrListFiles.length);
        for (File file : fileArrListFiles) {
            file.getClass();
            arrayList.add(new KotlinLocalVirtualFile(file, this._fileSystem, this));
        }
        VirtualFile[] virtualFileArr2 = (VirtualFile[]) CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: org.jetbrains.kotlin.cli.common.localfs.KotlinLocalVirtualFile$getChildren$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(((KotlinLocalVirtualFile) t).getName(), ((KotlinLocalVirtualFile) t2).getName());
            }
        }).toArray(new VirtualFile[0]);
        this._children = virtualFileArr2;
        virtualFileArr2.getClass();
        return virtualFileArr2;
    }

    public final File getFile() {
        return this.file;
    }

    public VirtualFileSystem getFileSystem() {
        return this._fileSystem;
    }

    public InputStream getInputStream() {
        InputStream inputStreamInputStreamSkippingBOM = VfsUtilCore.inputStreamSkippingBOM(new BufferedInputStream(new FileInputStream(this.file)), this);
        inputStreamInputStreamSkippingBOM.getClass();
        return inputStreamInputStreamSkippingBOM;
    }

    public long getLength() {
        return this.file.length();
    }

    public long getModificationStamp() {
        return 0L;
    }

    public String getName() {
        String str = this._name;
        if (str != null) {
            return str;
        }
        String name = this.file.getName();
        this._name = name;
        name.getClass();
        return name;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public OutputStream getOutputStream(Object requestor, long newModificationStamp, long newTimeStamp) throws KotlinNothingValueException {
        AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
        throw new KotlinNothingValueException();
    }

    public VirtualFile getParent() {
        KotlinLocalVirtualFile kotlinLocalVirtualFile = this._parent;
        if (kotlinLocalVirtualFile != null) {
            return kotlinLocalVirtualFile;
        }
        File parentFile = this.file.getParentFile();
        if (parentFile == null) {
            return null;
        }
        KotlinLocalVirtualFile kotlinLocalVirtualFile2 = new KotlinLocalVirtualFile(parentFile, this._fileSystem, null, 4, null);
        this._parent = kotlinLocalVirtualFile2;
        return kotlinLocalVirtualFile2;
    }

    public String getPath() {
        String systemIndependentName = FileUtil.toSystemIndependentName(this.file.getAbsolutePath());
        systemIndependentName.getClass();
        return systemIndependentName;
    }

    public long getTimeStamp() {
        return this.file.lastModified();
    }

    public int hashCode() {
        return this.file.hashCode();
    }

    public boolean isDirectory() {
        Boolean bool = this._isDirectory;
        if (bool != null) {
            return bool.booleanValue();
        }
        boolean zIsDirectory = this.file.isDirectory();
        this._isDirectory = Boolean.valueOf(zIsDirectory);
        return zIsDirectory;
    }

    public boolean isInLocalFileSystem() {
        return true;
    }

    public boolean isValid() {
        return true;
    }

    public boolean isWritable() {
        return false;
    }

    public void refresh(boolean asynchronous, boolean recursive, Runnable postRunnable) {
    }

    public /* synthetic */ KotlinLocalVirtualFile(File file, KotlinLocalFileSystem kotlinLocalFileSystem, KotlinLocalVirtualFile kotlinLocalVirtualFile, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(file, kotlinLocalFileSystem, (i & 4) != 0 ? null : kotlinLocalVirtualFile);
    }
}
