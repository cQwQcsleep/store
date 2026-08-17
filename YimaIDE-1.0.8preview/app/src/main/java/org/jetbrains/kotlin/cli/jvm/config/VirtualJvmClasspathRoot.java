package org.jetbrains.kotlin.cli.jvm.config;

import com.intellij.openapi.vfs.VirtualFile;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bB\u0011\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\tJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\f¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/config/VirtualJvmClasspathRoot;", "Lorg/jetbrains/kotlin/cli/jvm/config/JvmClasspathRootBase;", "file", "Lcom/intellij/openapi/vfs/VirtualFile;", "isSdkRoot", Argument.Delimiters.none, "isFriend", "<init>", "(Lcom/intellij/openapi/vfs/VirtualFile;ZZ)V", "(Lcom/intellij/openapi/vfs/VirtualFile;)V", "getFile", "()Lcom/intellij/openapi/vfs/VirtualFile;", "()Z", "component1", "component2", "component3", "copy", "equals", "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "kotlin-compiler"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 3, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class VirtualJvmClasspathRoot implements JvmClasspathRootBase {
    private final VirtualFile file;
    private final boolean isFriend;
    private final boolean isSdkRoot;

    public VirtualJvmClasspathRoot(VirtualFile virtualFile, boolean z, boolean z2) {
        virtualFile.getClass();
        this.file = virtualFile;
        this.isSdkRoot = z;
        this.isFriend = z2;
    }

    public static /* synthetic */ VirtualJvmClasspathRoot copy$default(VirtualJvmClasspathRoot virtualJvmClasspathRoot, VirtualFile virtualFile, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            virtualFile = virtualJvmClasspathRoot.file;
        }
        if ((i & 2) != 0) {
            z = virtualJvmClasspathRoot.isSdkRoot;
        }
        if ((i & 4) != 0) {
            z2 = virtualJvmClasspathRoot.isFriend;
        }
        return virtualJvmClasspathRoot.copy(virtualFile, z, z2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final VirtualFile getFile() {
        return this.file;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getIsSdkRoot() {
        return this.isSdkRoot;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getIsFriend() {
        return this.isFriend;
    }

    public final VirtualJvmClasspathRoot copy(VirtualFile file, boolean isSdkRoot, boolean isFriend) {
        file.getClass();
        return new VirtualJvmClasspathRoot(file, isSdkRoot, isFriend);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof VirtualJvmClasspathRoot)) {
            return false;
        }
        VirtualJvmClasspathRoot virtualJvmClasspathRoot = (VirtualJvmClasspathRoot) other;
        return Intrinsics.areEqual(this.file, virtualJvmClasspathRoot.file) && this.isSdkRoot == virtualJvmClasspathRoot.isSdkRoot && this.isFriend == virtualJvmClasspathRoot.isFriend;
    }

    public final VirtualFile getFile() {
        return this.file;
    }

    public int hashCode() {
        return (((this.file.hashCode() * 31) + Boolean.hashCode(this.isSdkRoot)) * 31) + Boolean.hashCode(this.isFriend);
    }

    public final boolean isFriend() {
        return this.isFriend;
    }

    @Override // org.jetbrains.kotlin.cli.jvm.config.JvmClasspathRootBase
    public boolean isSdkRoot() {
        return this.isSdkRoot;
    }

    public String toString() {
        return "VirtualJvmClasspathRoot(file=" + this.file + ", isSdkRoot=" + this.isSdkRoot + ", isFriend=" + this.isFriend + ")";
    }

    public /* synthetic */ VirtualJvmClasspathRoot(VirtualFile virtualFile, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(virtualFile, z, (i & 4) != 0 ? false : z2);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public VirtualJvmClasspathRoot(VirtualFile virtualFile) {
        this(virtualFile, false, false, 4, null);
        virtualFile.getClass();
    }
}
