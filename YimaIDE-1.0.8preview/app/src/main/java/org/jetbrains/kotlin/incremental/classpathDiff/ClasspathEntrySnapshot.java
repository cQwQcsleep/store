package org.jetbrains.kotlin.incremental.classpathDiff;

import java.util.LinkedHashMap;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B+\u0012\"\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006¢\u0006\u0004\b\u0007\u0010\bR-\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/incremental/classpathDiff/ClasspathEntrySnapshot;", "", "classSnapshots", "Ljava/util/LinkedHashMap;", "", "Lorg/jetbrains/kotlin/incremental/classpathDiff/ClassSnapshot;", "Lkotlin/collections/LinkedHashMap;", "<init>", "(Ljava/util/LinkedHashMap;)V", "getClassSnapshots", "()Ljava/util/LinkedHashMap;", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ClasspathEntrySnapshot {
    private final LinkedHashMap<String, ClassSnapshot> classSnapshots;

    public ClasspathEntrySnapshot(LinkedHashMap<String, ClassSnapshot> linkedHashMap) {
        linkedHashMap.getClass();
        this.classSnapshots = linkedHashMap;
    }

    public final LinkedHashMap<String, ClassSnapshot> getClassSnapshots() {
        return this.classSnapshots;
    }
}
