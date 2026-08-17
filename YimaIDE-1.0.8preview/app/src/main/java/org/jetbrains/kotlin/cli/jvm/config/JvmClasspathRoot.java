package org.jetbrains.kotlin.cli.jvm.config;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bB\u0011\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\tJ\t\u0010\r\u001a\u00020\u0004HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0006HÆ\u0003J\u001d\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\f¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/config/JvmClasspathRoot;", "Lorg/jetbrains/kotlin/cli/jvm/config/JvmContentRoot;", "Lorg/jetbrains/kotlin/cli/jvm/config/JvmClasspathRootBase;", "file", "Ljava/io/File;", "isSdkRoot", Argument.Delimiters.none, "<init>", "(Ljava/io/File;Z)V", "(Ljava/io/File;)V", "getFile", "()Ljava/io/File;", "()Z", "component1", "component2", "copy", "equals", "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "kotlin-compiler"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 3, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class JvmClasspathRoot implements JvmContentRoot, JvmClasspathRootBase {
    private final File file;
    private final boolean isSdkRoot;

    public JvmClasspathRoot(File file, boolean z) {
        file.getClass();
        this.file = file;
        this.isSdkRoot = z;
    }

    public static /* synthetic */ JvmClasspathRoot copy$default(JvmClasspathRoot jvmClasspathRoot, File file, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            file = jvmClasspathRoot.file;
        }
        if ((i & 2) != 0) {
            z = jvmClasspathRoot.isSdkRoot;
        }
        return jvmClasspathRoot.copy(file, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final File getFile() {
        return this.file;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getIsSdkRoot() {
        return this.isSdkRoot;
    }

    public final JvmClasspathRoot copy(File file, boolean isSdkRoot) {
        file.getClass();
        return new JvmClasspathRoot(file, isSdkRoot);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof JvmClasspathRoot)) {
            return false;
        }
        JvmClasspathRoot jvmClasspathRoot = (JvmClasspathRoot) other;
        return Intrinsics.areEqual(this.file, jvmClasspathRoot.file) && this.isSdkRoot == jvmClasspathRoot.isSdkRoot;
    }

    @Override // org.jetbrains.kotlin.cli.jvm.config.JvmContentRoot
    public File getFile() {
        return this.file;
    }

    public int hashCode() {
        return (this.file.hashCode() * 31) + Boolean.hashCode(this.isSdkRoot);
    }

    @Override // org.jetbrains.kotlin.cli.jvm.config.JvmClasspathRootBase
    public boolean isSdkRoot() {
        return this.isSdkRoot;
    }

    public String toString() {
        return "JvmClasspathRoot(file=" + this.file + ", isSdkRoot=" + this.isSdkRoot + ")";
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public JvmClasspathRoot(File file) {
        this(file, false);
        file.getClass();
    }
}
