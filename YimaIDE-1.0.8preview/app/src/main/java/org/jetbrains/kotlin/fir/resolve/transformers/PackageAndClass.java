package org.jetbrains.kotlin.fir.resolve.transformers;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u001f\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/PackageAndClass;", Argument.Delimiters.none, "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "relativeClassFqName", "<init>", "(Lorg/jetbrains/kotlin/name/FqName;Lorg/jetbrains/kotlin/name/FqName;)V", "getPackageFqName", "()Lorg/jetbrains/kotlin/name/FqName;", "getRelativeClassFqName", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class PackageAndClass {
    private final FqName packageFqName;
    private final FqName relativeClassFqName;

    public PackageAndClass(FqName fqName, FqName fqName2) {
        fqName.getClass();
        this.packageFqName = fqName;
        this.relativeClassFqName = fqName2;
    }

    public static /* synthetic */ PackageAndClass copy$default(PackageAndClass packageAndClass, FqName fqName, FqName fqName2, int i, Object obj) {
        if ((i & 1) != 0) {
            fqName = packageAndClass.packageFqName;
        }
        if ((i & 2) != 0) {
            fqName2 = packageAndClass.relativeClassFqName;
        }
        return packageAndClass.copy(fqName, fqName2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final FqName getPackageFqName() {
        return this.packageFqName;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final FqName getRelativeClassFqName() {
        return this.relativeClassFqName;
    }

    public final PackageAndClass copy(FqName packageFqName, FqName relativeClassFqName) {
        packageFqName.getClass();
        return new PackageAndClass(packageFqName, relativeClassFqName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PackageAndClass)) {
            return false;
        }
        PackageAndClass packageAndClass = (PackageAndClass) other;
        return Intrinsics.areEqual(this.packageFqName, packageAndClass.packageFqName) && Intrinsics.areEqual(this.relativeClassFqName, packageAndClass.relativeClassFqName);
    }

    public final FqName getPackageFqName() {
        return this.packageFqName;
    }

    public final FqName getRelativeClassFqName() {
        return this.relativeClassFqName;
    }

    public int hashCode() {
        int iHashCode = this.packageFqName.hashCode() * 31;
        FqName fqName = this.relativeClassFqName;
        return iHashCode + (fqName == null ? 0 : fqName.hashCode());
    }

    public String toString() {
        return "PackageAndClass(packageFqName=" + this.packageFqName + ", relativeClassFqName=" + this.relativeClassFqName + ')';
    }
}
