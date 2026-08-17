package org.jetbrains.kotlin.metadata.deserialization;

import javax.xml.transform.OutputKeys;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.library.abi.AbiCompoundName;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\f\u001a\u00020\rJ.\u0010\u000e\u001a\u00020\u000f2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000f0\u00112\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000f0\u0011J\n\u0010\u0013\u001a\u00020\rH\u0096\u0080\u0004J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J'\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/metadata/deserialization/VersionRequirement$Version;", "", "major", "", "minor", "patch", "<init>", "(III)V", "getMajor", "()I", "getMinor", "getPatch", "asString", "", "encode", "", "writeVersion", "Lkotlin/Function1;", "writeVersionFull", "toString", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "Companion", "org.jetbrains.kotlin:metadata"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class VersionRequirement$Version {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final VersionRequirement$Version INFINITY = new VersionRequirement$Version(256, 256, 256);
    private final int major;
    private final int minor;
    private final int patch;

    public VersionRequirement$Version(int i, int i2, int i3) {
        this.major = i;
        this.minor = i2;
        this.patch = i3;
    }

    public static /* synthetic */ VersionRequirement$Version copy$default(VersionRequirement$Version versionRequirement$Version, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = versionRequirement$Version.major;
        }
        if ((i4 & 2) != 0) {
            i2 = versionRequirement$Version.minor;
        }
        if ((i4 & 4) != 0) {
            i3 = versionRequirement$Version.patch;
        }
        return versionRequirement$Version.copy(i, i2, i3);
    }

    public final String asString() {
        StringBuilder sb;
        int i;
        int i2 = this.patch;
        int i3 = this.major;
        if (i2 == 0) {
            sb = new StringBuilder();
            sb.append(i3);
            sb.append(AbiCompoundName.SEPARATOR);
            i = this.minor;
        } else {
            sb = new StringBuilder();
            sb.append(i3);
            sb.append(AbiCompoundName.SEPARATOR);
            sb.append(this.minor);
            sb.append(AbiCompoundName.SEPARATOR);
            i = this.patch;
        }
        sb.append(i);
        return sb.toString();
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getMajor() {
        return this.major;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getMinor() {
        return this.minor;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getPatch() {
        return this.patch;
    }

    public final VersionRequirement$Version copy(int major, int minor, int patch) {
        return new VersionRequirement$Version(major, minor, patch);
    }

    public final void encode(Function1<? super Integer, Unit> writeVersion, Function1<? super Integer, Unit> writeVersionFull) {
        int i;
        int i2;
        writeVersion.getClass();
        writeVersionFull.getClass();
        if (Intrinsics.areEqual(this, INFINITY)) {
            return;
        }
        int i3 = this.major;
        if (i3 > 7 || (i = this.minor) > 15 || (i2 = this.patch) > 127) {
            writeVersionFull.invoke(Integer.valueOf((this.patch << 16) | (this.minor << 8) | i3));
        } else {
            writeVersion.invoke(Integer.valueOf((i << 3) | i3 | (i2 << 7)));
        }
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof VersionRequirement$Version)) {
            return false;
        }
        VersionRequirement$Version versionRequirement$Version = (VersionRequirement$Version) other;
        return this.major == versionRequirement$Version.major && this.minor == versionRequirement$Version.minor && this.patch == versionRequirement$Version.patch;
    }

    public final int getMajor() {
        return this.major;
    }

    public final int getMinor() {
        return this.minor;
    }

    public final int getPatch() {
        return this.patch;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.major) * 31) + Integer.hashCode(this.minor)) * 31) + Integer.hashCode(this.patch);
    }

    public String toString() {
        return asString();
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\r\u001a\u00020\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u00072\b\u0010\u000f\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\u0010R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/metadata/deserialization/VersionRequirement$Version$Companion;", "", "<init>", "()V", "INFINITY", "Lorg/jetbrains/kotlin/metadata/deserialization/VersionRequirement$Version;", "MAJOR_BITS", "", "MINOR_BITS", "PATCH_BITS", "MAJOR_MASK", "MINOR_MASK", "PATCH_MASK", "decode", OutputKeys.VERSION, "versionFull", "(Ljava/lang/Integer;Ljava/lang/Integer;)Lorg/jetbrains/kotlin/metadata/deserialization/VersionRequirement$Version;", "org.jetbrains.kotlin:metadata"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final VersionRequirement$Version decode(Integer version, Integer versionFull) {
            if (versionFull != null) {
                return new VersionRequirement$Version(versionFull.intValue() & 255, (versionFull.intValue() >> 8) & 255, (versionFull.intValue() >> 16) & 255);
            }
            return version != null ? new VersionRequirement$Version(version.intValue() & 7, (version.intValue() >> 3) & 15, (version.intValue() >> 7) & 127) : VersionRequirement$Version.INFINITY;
        }

        private Companion() {
        }
    }

    public /* synthetic */ VersionRequirement$Version(int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, (i4 & 4) != 0 ? 0 : i3);
    }
}
