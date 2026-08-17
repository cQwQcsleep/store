package org.jetbrains.kotlin.incremental.classpathDiff;

import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/incremental/classpathDiff/ClasspathSnapshot;", "", "classpathEntrySnapshots", "", "Lorg/jetbrains/kotlin/incremental/classpathDiff/ClasspathEntrySnapshot;", "<init>", "(Ljava/util/List;)V", "getClasspathEntrySnapshots", "()Ljava/util/List;", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ClasspathSnapshot {
    private final List<ClasspathEntrySnapshot> classpathEntrySnapshots;

    public ClasspathSnapshot(List<ClasspathEntrySnapshot> list) {
        list.getClass();
        this.classpathEntrySnapshots = list;
    }

    public final List<ClasspathEntrySnapshot> getClasspathEntrySnapshots() {
        return this.classpathEntrySnapshots;
    }
}
