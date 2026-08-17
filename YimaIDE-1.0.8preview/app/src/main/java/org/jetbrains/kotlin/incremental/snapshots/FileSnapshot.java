package org.jetbrains.kotlin.incremental.snapshots;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0014\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u0096\u0082\u0004J\n\u0010\u000f\u001a\u00020\u0010H\u0096\u0080\u0004J\n\u0010\u0011\u001a\u00020\u0012H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/incremental/snapshots/FileSnapshot;", "", "length", "", "hash", "", "<init>", "(J[B)V", "getLength", "()J", "getHash", "()[B", "equals", "", "other", "hashCode", "", "toString", "", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class FileSnapshot {
    private final byte[] hash;
    private final long length;

    public FileSnapshot(long j, byte[] bArr) {
        bArr.getClass();
        this.length = j;
        this.hash = bArr;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(FileSnapshot.class, other != null ? other.getClass() : null)) {
            return false;
        }
        other.getClass();
        FileSnapshot fileSnapshot = (FileSnapshot) other;
        return this.length == fileSnapshot.length && Arrays.equals(this.hash, fileSnapshot.hash);
    }

    public final byte[] getHash() {
        return this.hash;
    }

    public final long getLength() {
        return this.length;
    }

    public int hashCode() {
        return (Long.hashCode(this.length) * 31) + Arrays.hashCode(this.hash);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("FileSnapshot(length=");
        sb.append(this.length);
        sb.append(", hash=");
        String string = Arrays.toString(this.hash);
        string.getClass();
        sb.append(string);
        sb.append(')');
        return sb.toString();
    }
}
