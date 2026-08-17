package org.jetbrains.kotlin.fir.resolve.providers.impl;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.builtins.BuiltInsBinaryVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0082\b\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/providers/impl/BinaryVersionAndPackageFragment;", Argument.Delimiters.none, "version", "Lorg/jetbrains/kotlin/metadata/builtins/BuiltInsBinaryVersion;", "packageFragment", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$PackageFragment;", "<init>", "(Lorg/jetbrains/kotlin/metadata/builtins/BuiltInsBinaryVersion;Lorg/jetbrains/kotlin/metadata/ProtoBuf$PackageFragment;)V", "getVersion", "()Lorg/jetbrains/kotlin/metadata/builtins/BuiltInsBinaryVersion;", "getPackageFragment", "()Lorg/jetbrains/kotlin/metadata/ProtoBuf$PackageFragment;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "Companion", "org.jetbrains.kotlin:fir-deserialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final /* data */ class BinaryVersionAndPackageFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final ProtoBuf.PackageFragment packageFragment;
    private final BuiltInsBinaryVersion version;

    public BinaryVersionAndPackageFragment(BuiltInsBinaryVersion builtInsBinaryVersion, ProtoBuf.PackageFragment packageFragment) {
        builtInsBinaryVersion.getClass();
        packageFragment.getClass();
        this.version = builtInsBinaryVersion;
        this.packageFragment = packageFragment;
    }

    public static /* synthetic */ BinaryVersionAndPackageFragment copy$default(BinaryVersionAndPackageFragment binaryVersionAndPackageFragment, BuiltInsBinaryVersion builtInsBinaryVersion, ProtoBuf.PackageFragment packageFragment, int i, Object obj) {
        if ((i & 1) != 0) {
            builtInsBinaryVersion = binaryVersionAndPackageFragment.version;
        }
        if ((i & 2) != 0) {
            packageFragment = binaryVersionAndPackageFragment.packageFragment;
        }
        return binaryVersionAndPackageFragment.copy(builtInsBinaryVersion, packageFragment);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final BuiltInsBinaryVersion getVersion() {
        return this.version;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final ProtoBuf.PackageFragment getPackageFragment() {
        return this.packageFragment;
    }

    public final BinaryVersionAndPackageFragment copy(BuiltInsBinaryVersion version, ProtoBuf.PackageFragment packageFragment) {
        version.getClass();
        packageFragment.getClass();
        return new BinaryVersionAndPackageFragment(version, packageFragment);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BinaryVersionAndPackageFragment)) {
            return false;
        }
        BinaryVersionAndPackageFragment binaryVersionAndPackageFragment = (BinaryVersionAndPackageFragment) other;
        return Intrinsics.areEqual(this.version, binaryVersionAndPackageFragment.version) && Intrinsics.areEqual(this.packageFragment, binaryVersionAndPackageFragment.packageFragment);
    }

    public final ProtoBuf.PackageFragment getPackageFragment() {
        return this.packageFragment;
    }

    public final BuiltInsBinaryVersion getVersion() {
        return this.version;
    }

    public int hashCode() {
        return (this.version.hashCode() * 31) + this.packageFragment.hashCode();
    }

    public String toString() {
        return "BinaryVersionAndPackageFragment(version=" + this.version + ", packageFragment=" + this.packageFragment + ')';
    }
}
