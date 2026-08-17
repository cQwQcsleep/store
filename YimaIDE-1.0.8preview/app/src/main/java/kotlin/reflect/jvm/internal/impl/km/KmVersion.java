package kotlin.reflect.jvm.internal.impl.km;

import androidx.compose.compiler.plugins.kotlin.analysis.StabilityExternalClassNameMatchingKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public final class KmVersion {
    private final int major;
    private final int minor;
    private final int patch;

    public KmVersion(int i, int i2, int i3) {
        this.major = i;
        this.minor = i2;
        this.patch = i3;
    }

    public final int component1() {
        return this.major;
    }

    public final int component2() {
        return this.minor;
    }

    public final int component3() {
        return this.patch;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof KmVersion)) {
            return false;
        }
        KmVersion kmVersion = (KmVersion) obj;
        return this.major == kmVersion.major && this.minor == kmVersion.minor && this.patch == kmVersion.patch;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.major) * 31) + Integer.hashCode(this.minor)) * 31) + Integer.hashCode(this.patch);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.major);
        sb.append(StabilityExternalClassNameMatchingKt.STABILITY_PACKAGE_SEPARATOR);
        sb.append(this.minor);
        sb.append(StabilityExternalClassNameMatchingKt.STABILITY_PACKAGE_SEPARATOR);
        sb.append(this.patch);
        return sb.toString();
    }
}
