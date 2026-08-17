package org.jetbrains.kotlin.resolve;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.NameRenderingUtils;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\n\u0010\u0013\u001a\u00020\u0010H\u0096\u0080\u0004J\u0006\u0010\u0014\u001a\u00020\u0005J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0007HÆ\u0003J)\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0014\u0010\u001b\u001a\u00020\u00052\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001d\u001a\u00020\u001eHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u00078F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u000e¨\u0006 "}, d2 = {"Lorg/jetbrains/kotlin/resolve/ImportPath;", Argument.Delimiters.none, "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "isAllUnder", Argument.Delimiters.none, "alias", "Lorg/jetbrains/kotlin/name/Name;", "<init>", "(Lorg/jetbrains/kotlin/name/FqName;ZLorg/jetbrains/kotlin/name/Name;)V", "getFqName", "()Lorg/jetbrains/kotlin/name/FqName;", "()Z", "getAlias", "()Lorg/jetbrains/kotlin/name/Name;", "pathStr", Argument.Delimiters.none, "getPathStr", "()Ljava/lang/String;", "toString", "hasAlias", "importedName", "getImportedName", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", Argument.Delimiters.none, "Companion", "org.jetbrains.kotlin:names"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class ImportPath {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final Name alias;
    private final FqName fqName;
    private final boolean isAllUnder;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ImportPath(FqName fqName, boolean z) {
        this(fqName, z, null, 4, null);
        fqName.getClass();
    }

    public static /* synthetic */ ImportPath copy$default(ImportPath importPath, FqName fqName, boolean z, Name name, int i, Object obj) {
        if ((i & 1) != 0) {
            fqName = importPath.fqName;
        }
        if ((i & 2) != 0) {
            z = importPath.isAllUnder;
        }
        if ((i & 4) != 0) {
            name = importPath.alias;
        }
        return importPath.copy(fqName, z, name);
    }

    @JvmStatic
    public static final ImportPath fromString(String str) {
        return Companion.fromString(str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final FqName getFqName() {
        return this.fqName;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getIsAllUnder() {
        return this.isAllUnder;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Name getAlias() {
        return this.alias;
    }

    public final ImportPath copy(FqName fqName, boolean isAllUnder, Name alias) {
        fqName.getClass();
        return new ImportPath(fqName, isAllUnder, alias);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ImportPath)) {
            return false;
        }
        ImportPath importPath = (ImportPath) other;
        return Intrinsics.areEqual(this.fqName, importPath.fqName) && this.isAllUnder == importPath.isAllUnder && Intrinsics.areEqual(this.alias, importPath.alias);
    }

    public final Name getAlias() {
        return this.alias;
    }

    public final FqName getFqName() {
        return this.fqName;
    }

    public final Name getImportedName() {
        if (this.isAllUnder) {
            return null;
        }
        Name name = this.alias;
        return name == null ? this.fqName.shortName() : name;
    }

    public final String getPathStr() {
        StringBuilder sb = new StringBuilder();
        sb.append(NameRenderingUtils.render(this.fqName.toUnsafe()));
        sb.append(this.isAllUnder ? ".*" : Argument.Delimiters.none);
        return sb.toString();
    }

    public final boolean hasAlias() {
        return this.alias != null;
    }

    public int hashCode() {
        int iHashCode = ((this.fqName.hashCode() * 31) + Boolean.hashCode(this.isAllUnder)) * 31;
        Name name = this.alias;
        return iHashCode + (name == null ? 0 : name.hashCode());
    }

    public final boolean isAllUnder() {
        return this.isAllUnder;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(getPathStr());
        if (this.alias != null) {
            str = " as " + this.alias.asString();
        } else {
            str = Argument.Delimiters.none;
        }
        sb.append(str);
        return sb.toString();
    }

    public ImportPath(FqName fqName, boolean z, Name name) {
        fqName.getClass();
        this.fqName = fqName;
        this.isAllUnder = z;
        this.alias = name;
    }

    public /* synthetic */ ImportPath(FqName fqName, boolean z, Name name, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(fqName, z, (i & 4) != 0 ? null : name);
    }
}
