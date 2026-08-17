package org.jetbrains.kotlin.cli.jvm.config;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0005HÖ\u0081\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/config/JavaSourceRoot;", "Lorg/jetbrains/kotlin/cli/jvm/config/JvmContentRoot;", "file", "Ljava/io/File;", ModuleXmlParser.JAVA_SOURCE_PACKAGE_PREFIX, Argument.Delimiters.none, "<init>", "(Ljava/io/File;Ljava/lang/String;)V", "getFile", "()Ljava/io/File;", "getPackagePrefix", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", "kotlin-compiler"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 3, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class JavaSourceRoot implements JvmContentRoot {
    private final File file;
    private final String packagePrefix;

    public JavaSourceRoot(File file, String str) {
        file.getClass();
        this.file = file;
        this.packagePrefix = str;
    }

    public static /* synthetic */ JavaSourceRoot copy$default(JavaSourceRoot javaSourceRoot, File file, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            file = javaSourceRoot.file;
        }
        if ((i & 2) != 0) {
            str = javaSourceRoot.packagePrefix;
        }
        return javaSourceRoot.copy(file, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final File getFile() {
        return this.file;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getPackagePrefix() {
        return this.packagePrefix;
    }

    public final JavaSourceRoot copy(File file, String packagePrefix) {
        file.getClass();
        return new JavaSourceRoot(file, packagePrefix);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof JavaSourceRoot)) {
            return false;
        }
        JavaSourceRoot javaSourceRoot = (JavaSourceRoot) other;
        return Intrinsics.areEqual(this.file, javaSourceRoot.file) && Intrinsics.areEqual(this.packagePrefix, javaSourceRoot.packagePrefix);
    }

    @Override // org.jetbrains.kotlin.cli.jvm.config.JvmContentRoot
    public File getFile() {
        return this.file;
    }

    public final String getPackagePrefix() {
        return this.packagePrefix;
    }

    public int hashCode() {
        int iHashCode = this.file.hashCode() * 31;
        String str = this.packagePrefix;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "JavaSourceRoot(file=" + this.file + ", packagePrefix=" + this.packagePrefix + ")";
    }
}
