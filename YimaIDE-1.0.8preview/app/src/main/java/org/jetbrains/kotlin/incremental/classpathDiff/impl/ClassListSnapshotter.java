package org.jetbrains.kotlin.incremental.classpathDiff.impl;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.incremental.classpathDiff.ClassSnapshot;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bp\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&\u0082\u0001\u0002\u0005\u0006ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/incremental/classpathDiff/impl/ClassListSnapshotter;", "", "snapshot", "", "Lorg/jetbrains/kotlin/incremental/classpathDiff/ClassSnapshot;", "Lorg/jetbrains/kotlin/incremental/classpathDiff/impl/ClassListSnapshotterWithInlinedClassSupport;", "Lorg/jetbrains/kotlin/incremental/classpathDiff/impl/PlainClassListSnapshotter;", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface ClassListSnapshotter {
    List<ClassSnapshot> snapshot();
}
