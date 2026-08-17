package org.jetbrains.kotlin.cli.jvm.index;

import com.intellij.openapi.vfs.VirtualFile;
import java.util.EnumSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u001c2\u00020\u0001:\u0002\u001b\u001cB#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0007HÆ\u0003J)\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/index/JavaRoot;", Argument.Delimiters.none, "file", "Lcom/intellij/openapi/vfs/VirtualFile;", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/cli/jvm/index/JavaRoot$RootType;", "prefixFqName", "Lorg/jetbrains/kotlin/name/FqName;", "<init>", "(Lcom/intellij/openapi/vfs/VirtualFile;Lorg/jetbrains/kotlin/cli/jvm/index/JavaRoot$RootType;Lorg/jetbrains/kotlin/name/FqName;)V", "getFile", "()Lcom/intellij/openapi/vfs/VirtualFile;", "getType", "()Lorg/jetbrains/kotlin/cli/jvm/index/JavaRoot$RootType;", "getPrefixFqName", "()Lorg/jetbrains/kotlin/name/FqName;", "component1", "component2", "component3", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "RootType", "RootTypes", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class JavaRoot {
    private static final Set<RootType> OnlyBinary;
    private static final Set<RootType> OnlySource;

    /* JADX INFO: renamed from: RootTypes, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Set<RootType> SourceAndBinary;
    private final VirtualFile file;
    private final FqName prefixFqName;
    private final RootType type;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/index/JavaRoot$RootType;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "SOURCE", "BINARY", "BINARY_SIG", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public enum RootType {
        SOURCE,
        BINARY,
        BINARY_SIG;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<RootType> getEntries() {
            return $ENTRIES;
        }
    }

    static {
        RootType rootType = RootType.BINARY;
        RootType rootType2 = RootType.BINARY_SIG;
        EnumSet enumSetOf = EnumSet.of(rootType, rootType2);
        enumSetOf.getClass();
        OnlyBinary = enumSetOf;
        RootType rootType3 = RootType.SOURCE;
        EnumSet enumSetOf2 = EnumSet.of(rootType3);
        enumSetOf2.getClass();
        OnlySource = enumSetOf2;
        EnumSet enumSetOf3 = EnumSet.of(rootType, rootType2, rootType3);
        enumSetOf3.getClass();
        SourceAndBinary = enumSetOf3;
    }

    public JavaRoot(VirtualFile virtualFile, RootType rootType, FqName fqName) {
        virtualFile.getClass();
        rootType.getClass();
        this.file = virtualFile;
        this.type = rootType;
        this.prefixFqName = fqName;
    }

    public static /* synthetic */ JavaRoot copy$default(JavaRoot javaRoot, VirtualFile virtualFile, RootType rootType, FqName fqName, int i, Object obj) {
        if ((i & 1) != 0) {
            virtualFile = javaRoot.file;
        }
        if ((i & 2) != 0) {
            rootType = javaRoot.type;
        }
        if ((i & 4) != 0) {
            fqName = javaRoot.prefixFqName;
        }
        return javaRoot.copy(virtualFile, rootType, fqName);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final VirtualFile getFile() {
        return this.file;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final RootType getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final FqName getPrefixFqName() {
        return this.prefixFqName;
    }

    public final JavaRoot copy(VirtualFile file, RootType type, FqName prefixFqName) {
        file.getClass();
        type.getClass();
        return new JavaRoot(file, type, prefixFqName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof JavaRoot)) {
            return false;
        }
        JavaRoot javaRoot = (JavaRoot) other;
        return Intrinsics.areEqual(this.file, javaRoot.file) && this.type == javaRoot.type && Intrinsics.areEqual(this.prefixFqName, javaRoot.prefixFqName);
    }

    public final VirtualFile getFile() {
        return this.file;
    }

    public final FqName getPrefixFqName() {
        return this.prefixFqName;
    }

    public final RootType getType() {
        return this.type;
    }

    public int hashCode() {
        int iHashCode = ((this.file.hashCode() * 31) + this.type.hashCode()) * 31;
        FqName fqName = this.prefixFqName;
        return iHashCode + (fqName == null ? 0 : fqName.hashCode());
    }

    public String toString() {
        return "JavaRoot(file=" + this.file + ", type=" + this.type + ", prefixFqName=" + this.prefixFqName + ')';
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.cli.jvm.index.JavaRoot$RootTypes, reason: from kotlin metadata */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\b¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/index/JavaRoot$RootTypes;", Argument.Delimiters.none, "<init>", "()V", "OnlyBinary", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/jvm/index/JavaRoot$RootType;", "getOnlyBinary", "()Ljava/util/Set;", "OnlySource", "getOnlySource", "SourceAndBinary", "getSourceAndBinary", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Set<RootType> getOnlyBinary() {
            return JavaRoot.OnlyBinary;
        }

        public final Set<RootType> getOnlySource() {
            return JavaRoot.OnlySource;
        }

        public final Set<RootType> getSourceAndBinary() {
            return JavaRoot.SourceAndBinary;
        }

        private Companion() {
        }
    }

    public /* synthetic */ JavaRoot(VirtualFile virtualFile, RootType rootType, FqName fqName, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(virtualFile, rootType, (i & 4) != 0 ? null : fqName);
    }
}
