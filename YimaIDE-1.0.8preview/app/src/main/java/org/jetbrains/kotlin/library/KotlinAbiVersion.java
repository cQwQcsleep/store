package org.jetbrains.kotlin.library;

import javax.xml.transform.OutputKeys;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.library.abi.AbiCompoundName;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u001d\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\u001e\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003J\u000e\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0000J\u001e\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003J\u000e\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0000J\u0006\u0010\u0018\u001a\u00020\u0012J\u0010\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u0000H\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0016R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/library/KotlinAbiVersion;", "", "single", "", "(I)V", "major", "minor", "patch", "(III)V", "getMajor", "()I", "getMinor", "getPatch", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "isAtLeast", OutputKeys.VERSION, "isAtMost", "isCompatible", "isCompatibleTo", "ourVersion", "toString", "", "Companion", "kotlin-util-klib"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class KotlinAbiVersion {
    private final int major;
    private final int minor;
    private final int patch;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final KotlinAbiVersion CURRENT = new KotlinAbiVersion(2, 4, 0);
    private static final KotlinAbiVersion FIRST_WITH_EXPERIMENTAL_BACKWARD_COMPATIBILITY = new KotlinAbiVersion(1, 4, 1);

    public KotlinAbiVersion(int i, int i2, int i3) {
        this.major = i;
        this.minor = i2;
        this.patch = i3;
    }

    public static /* synthetic */ KotlinAbiVersion copy$default(KotlinAbiVersion kotlinAbiVersion, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = kotlinAbiVersion.major;
        }
        if ((i4 & 2) != 0) {
            i2 = kotlinAbiVersion.minor;
        }
        if ((i4 & 4) != 0) {
            i3 = kotlinAbiVersion.patch;
        }
        return kotlinAbiVersion.copy(i, i2, i3);
    }

    private final boolean isCompatibleTo(KotlinAbiVersion ourVersion) {
        return isAtLeast(FIRST_WITH_EXPERIMENTAL_BACKWARD_COMPATIBILITY) ? isAtMost(ourVersion) : Intrinsics.areEqual(this, ourVersion);
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

    public final KotlinAbiVersion copy(int major, int minor, int patch) {
        return new KotlinAbiVersion(major, minor, patch);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KotlinAbiVersion)) {
            return false;
        }
        KotlinAbiVersion kotlinAbiVersion = (KotlinAbiVersion) other;
        return this.major == kotlinAbiVersion.major && this.minor == kotlinAbiVersion.minor && this.patch == kotlinAbiVersion.patch;
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

    public final boolean isAtLeast(int major, int minor, int patch) {
        int i = this.major;
        if (i > major) {
            return true;
        }
        if (i < major) {
            return false;
        }
        int i2 = this.minor;
        if (i2 > minor) {
            return true;
        }
        return i2 >= minor && this.patch >= patch;
    }

    public final boolean isAtMost(int major, int minor, int patch) {
        int i = this.major;
        if (i < major) {
            return true;
        }
        if (i > major) {
            return false;
        }
        int i2 = this.minor;
        if (i2 < minor) {
            return true;
        }
        return i2 <= minor && this.patch <= patch;
    }

    public final boolean isCompatible() {
        return isCompatibleTo(CURRENT);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.major);
        sb.append(AbiCompoundName.SEPARATOR);
        sb.append(this.minor);
        sb.append(AbiCompoundName.SEPARATOR);
        sb.append(this.patch);
        return sb.toString();
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/library/KotlinAbiVersion$Companion;", "", "()V", "CURRENT", "Lorg/jetbrains/kotlin/library/KotlinAbiVersion;", "getCURRENT", "()Lorg/jetbrains/kotlin/library/KotlinAbiVersion;", "FIRST_WITH_EXPERIMENTAL_BACKWARD_COMPATIBILITY", "kotlin-util-klib"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KotlinAbiVersion getCURRENT() {
            return KotlinAbiVersion.CURRENT;
        }

        private Companion() {
        }
    }

    public KotlinAbiVersion(int i) {
        this(0, i, 0);
    }

    public final boolean isAtLeast(KotlinAbiVersion version) {
        version.getClass();
        return isAtLeast(version.major, version.minor, version.patch);
    }

    public final boolean isAtMost(KotlinAbiVersion version) {
        version.getClass();
        return isAtMost(version.major, version.minor, version.patch);
    }
}
