package org.jetbrains.kotlin.incremental.classpathDiff;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/incremental/classpathDiff/InaccessibleClassSnapshot;", "Lorg/jetbrains/kotlin/incremental/classpathDiff/ClassSnapshot;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class InaccessibleClassSnapshot extends ClassSnapshot {
    public static final InaccessibleClassSnapshot INSTANCE = new InaccessibleClassSnapshot();

    private InaccessibleClassSnapshot() {
        super(null);
    }

    public boolean equals(Object other) {
        return this == other || (other instanceof InaccessibleClassSnapshot);
    }

    public int hashCode() {
        return -1477474346;
    }

    public String toString() {
        return "InaccessibleClassSnapshot";
    }
}
