package org.jetbrains.kotlin.incremental.classpathDiff;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\f\u001a\u00020\rH\u0096\u0080\u0004R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b\u0082\u0001\u0002\u000e\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/incremental/classpathDiff/AccessibleClassSnapshot;", "Lorg/jetbrains/kotlin/incremental/classpathDiff/ClassSnapshot;", "<init>", "()V", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "getClassId", "()Lorg/jetbrains/kotlin/name/ClassId;", "classAbiHash", "", "getClassAbiHash", "()J", "toString", "", "Lorg/jetbrains/kotlin/incremental/classpathDiff/JavaClassSnapshot;", "Lorg/jetbrains/kotlin/incremental/classpathDiff/KotlinClassSnapshot;", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class AccessibleClassSnapshot extends ClassSnapshot {
    private AccessibleClassSnapshot() {
        super(null);
    }

    public abstract long getClassAbiHash();

    public abstract ClassId getClassId();

    public String toString() {
        return getClassId().toString();
    }

    public /* synthetic */ AccessibleClassSnapshot(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
