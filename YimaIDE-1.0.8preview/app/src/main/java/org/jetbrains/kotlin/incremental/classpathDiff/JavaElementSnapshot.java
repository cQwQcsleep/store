package org.jetbrains.kotlin.incremental.classpathDiff;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/incremental/classpathDiff/JavaElementSnapshot;", "", "name", "", "abiHash", "", "<init>", "(Ljava/lang/String;J)V", "getName", "()Ljava/lang/String;", "getAbiHash", "()J", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class JavaElementSnapshot {
    private final long abiHash;
    private final String name;

    public JavaElementSnapshot(String str, long j) {
        str.getClass();
        this.name = str;
        this.abiHash = j;
    }

    public final long getAbiHash() {
        return this.abiHash;
    }

    public final String getName() {
        return this.name;
    }
}
