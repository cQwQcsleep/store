package org.jetbrains.kotlin.incremental.classpathDiff;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/incremental/classpathDiff/PackageMember;", "Lorg/jetbrains/kotlin/incremental/classpathDiff/ProgramSymbol;", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "memberName", "", "<init>", "(Lorg/jetbrains/kotlin/name/FqName;Ljava/lang/String;)V", "getPackageFqName", "()Lorg/jetbrains/kotlin/name/FqName;", "getMemberName", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class PackageMember extends ProgramSymbol {
    private final String memberName;
    private final FqName packageFqName;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PackageMember(FqName fqName, String str) {
        super(null);
        fqName.getClass();
        str.getClass();
        this.packageFqName = fqName;
        this.memberName = str;
    }

    public static /* synthetic */ PackageMember copy$default(PackageMember packageMember, FqName fqName, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            fqName = packageMember.packageFqName;
        }
        if ((i & 2) != 0) {
            str = packageMember.memberName;
        }
        return packageMember.copy(fqName, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final FqName getPackageFqName() {
        return this.packageFqName;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getMemberName() {
        return this.memberName;
    }

    public final PackageMember copy(FqName packageFqName, String memberName) {
        packageFqName.getClass();
        memberName.getClass();
        return new PackageMember(packageFqName, memberName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PackageMember)) {
            return false;
        }
        PackageMember packageMember = (PackageMember) other;
        return Intrinsics.areEqual(this.packageFqName, packageMember.packageFqName) && Intrinsics.areEqual(this.memberName, packageMember.memberName);
    }

    public final String getMemberName() {
        return this.memberName;
    }

    public final FqName getPackageFqName() {
        return this.packageFqName;
    }

    public int hashCode() {
        return (this.packageFqName.hashCode() * 31) + this.memberName.hashCode();
    }

    public String toString() {
        return "PackageMember(packageFqName=" + this.packageFqName + ", memberName=" + this.memberName + ')';
    }
}
