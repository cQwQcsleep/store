package org.jetbrains.kotlin.library;

import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.metadata.deserialization.MetadataVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B3\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\u0010\u000bJ\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003J=\u0010\u0018\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/library/KotlinLibraryVersioning;", "", "compilerVersion", "", "abiVersion", "Lorg/jetbrains/kotlin/library/KotlinAbiVersion;", "metadataVersion", "Lorg/jetbrains/kotlin/metadata/deserialization/MetadataVersion;", "irSignatureVersions", "", "Lorg/jetbrains/kotlin/library/KotlinIrSignatureVersion;", "(Ljava/lang/String;Lorg/jetbrains/kotlin/library/KotlinAbiVersion;Lorg/jetbrains/kotlin/metadata/deserialization/MetadataVersion;Ljava/util/Set;)V", "getAbiVersion", "()Lorg/jetbrains/kotlin/library/KotlinAbiVersion;", "getCompilerVersion", "()Ljava/lang/String;", "getIrSignatureVersions", "()Ljava/util/Set;", "getMetadataVersion", "()Lorg/jetbrains/kotlin/metadata/deserialization/MetadataVersion;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "kotlin-util-klib"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class KotlinLibraryVersioning {
    private final KotlinAbiVersion abiVersion;
    private final String compilerVersion;
    private final Set<KotlinIrSignatureVersion> irSignatureVersions;
    private final MetadataVersion metadataVersion;

    public KotlinLibraryVersioning(String str, KotlinAbiVersion kotlinAbiVersion, MetadataVersion metadataVersion, Set<KotlinIrSignatureVersion> set) {
        set.getClass();
        this.compilerVersion = str;
        this.abiVersion = kotlinAbiVersion;
        this.metadataVersion = metadataVersion;
        this.irSignatureVersions = set;
        if (set.isEmpty()) {
            w01.a("Signature versions must not be empty!");
            throw null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ KotlinLibraryVersioning copy$default(KotlinLibraryVersioning kotlinLibraryVersioning, String str, KotlinAbiVersion kotlinAbiVersion, MetadataVersion metadataVersion, Set set, int i, Object obj) {
        if ((i & 1) != 0) {
            str = kotlinLibraryVersioning.compilerVersion;
        }
        if ((i & 2) != 0) {
            kotlinAbiVersion = kotlinLibraryVersioning.abiVersion;
        }
        if ((i & 4) != 0) {
            metadataVersion = kotlinLibraryVersioning.metadataVersion;
        }
        if ((i & 8) != 0) {
            set = kotlinLibraryVersioning.irSignatureVersions;
        }
        return kotlinLibraryVersioning.copy(str, kotlinAbiVersion, metadataVersion, set);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getCompilerVersion() {
        return this.compilerVersion;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final KotlinAbiVersion getAbiVersion() {
        return this.abiVersion;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final MetadataVersion getMetadataVersion() {
        return this.metadataVersion;
    }

    public final Set<KotlinIrSignatureVersion> component4() {
        return this.irSignatureVersions;
    }

    public final KotlinLibraryVersioning copy(String compilerVersion, KotlinAbiVersion abiVersion, MetadataVersion metadataVersion, Set<KotlinIrSignatureVersion> irSignatureVersions) {
        irSignatureVersions.getClass();
        return new KotlinLibraryVersioning(compilerVersion, abiVersion, metadataVersion, irSignatureVersions);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KotlinLibraryVersioning)) {
            return false;
        }
        KotlinLibraryVersioning kotlinLibraryVersioning = (KotlinLibraryVersioning) other;
        return Intrinsics.areEqual(this.compilerVersion, kotlinLibraryVersioning.compilerVersion) && Intrinsics.areEqual(this.abiVersion, kotlinLibraryVersioning.abiVersion) && Intrinsics.areEqual(this.metadataVersion, kotlinLibraryVersioning.metadataVersion) && Intrinsics.areEqual(this.irSignatureVersions, kotlinLibraryVersioning.irSignatureVersions);
    }

    public final KotlinAbiVersion getAbiVersion() {
        return this.abiVersion;
    }

    public final String getCompilerVersion() {
        return this.compilerVersion;
    }

    public final Set<KotlinIrSignatureVersion> getIrSignatureVersions() {
        return this.irSignatureVersions;
    }

    public final MetadataVersion getMetadataVersion() {
        return this.metadataVersion;
    }

    public int hashCode() {
        String str = this.compilerVersion;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        KotlinAbiVersion kotlinAbiVersion = this.abiVersion;
        int iHashCode2 = (iHashCode + (kotlinAbiVersion == null ? 0 : kotlinAbiVersion.hashCode())) * 31;
        MetadataVersion metadataVersion = this.metadataVersion;
        return ((iHashCode2 + (metadataVersion != null ? metadataVersion.hashCode() : 0)) * 31) + this.irSignatureVersions.hashCode();
    }

    public String toString() {
        return "KotlinLibraryVersioning(compilerVersion=" + this.compilerVersion + ", abiVersion=" + this.abiVersion + ", metadataVersion=" + this.metadataVersion + ", irSignatureVersions=" + this.irSignatureVersions + ')';
    }

    public /* synthetic */ KotlinLibraryVersioning(String str, KotlinAbiVersion kotlinAbiVersion, MetadataVersion metadataVersion, Set set, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, kotlinAbiVersion, metadataVersion, (i & 8) != 0 ? KotlinIrSignatureVersion.INSTANCE.getCURRENTLY_SUPPORTED_VERSIONS() : set);
    }
}
