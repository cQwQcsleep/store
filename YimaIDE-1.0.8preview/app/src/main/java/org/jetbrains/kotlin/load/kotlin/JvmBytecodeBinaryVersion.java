package org.jetbrains.kotlin.load.kotlin;

import kotlin.Metadata;
import kotlin.collections.ArraysKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0015\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0013\u0012\n\u0010\u0002\u001a\u00020\u0003\"\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0006\u0010\u000e\u001a\u00020\u0003J\n\u0010\u000f\u001a\u00020\u0010H\u0096\u0080\u0004R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\t¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/load/kotlin/JvmBytecodeBinaryVersion;", "", "numbers", "", "", "<init>", "([I)V", "major", "getMajor", "()I", "minor", "getMinor", "patch", "getPatch", "toArray", "toString", "", "Companion", "org.jetbrains.kotlin:frontend.java"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class JvmBytecodeBinaryVersion {
    public static final JvmBytecodeBinaryVersion INSTANCE = new JvmBytecodeBinaryVersion(1, 0, 3);
    private final int major;
    private final int minor;
    private final int patch;

    public JvmBytecodeBinaryVersion(int... iArr) {
        iArr.getClass();
        Integer orNull = ArraysKt.getOrNull(iArr, 0);
        this.major = orNull != null ? orNull.intValue() : -1;
        Integer orNull2 = ArraysKt.getOrNull(iArr, 1);
        this.minor = orNull2 != null ? orNull2.intValue() : -1;
        Integer orNull3 = ArraysKt.getOrNull(iArr, 2);
        this.patch = orNull3 != null ? orNull3.intValue() : -1;
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

    public final int[] toArray() {
        return new int[]{this.major, this.minor, this.patch};
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.major);
        if (this.minor != -1) {
            sb.append("." + this.minor);
            if (this.patch != -1) {
                sb.append("." + this.patch);
            }
        }
        return sb.toString();
    }
}
