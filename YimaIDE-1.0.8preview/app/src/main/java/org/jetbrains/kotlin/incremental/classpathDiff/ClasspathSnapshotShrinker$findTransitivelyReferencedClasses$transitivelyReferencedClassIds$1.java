package org.jetbrains.kotlin.incremental.classpathDiff;

import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
public final /* synthetic */ class ClasspathSnapshotShrinker$findTransitivelyReferencedClasses$transitivelyReferencedClassIds$1 extends FunctionReferenceImpl implements Function1<ClassId, Set<? extends ClassId>> {
    public ClasspathSnapshotShrinker$findTransitivelyReferencedClasses$transitivelyReferencedClassIds$1(Object obj) {
        super(1, obj, ImpactingClassesResolver.class, "getImpactingClasses", "getImpactingClasses(Lorg/jetbrains/kotlin/name/ClassId;)Ljava/util/Set;", 0);
    }

    public final Set<ClassId> invoke(ClassId classId) {
        classId.getClass();
        return ((ImpactingClassesResolver) ((CallableReference) this).receiver).getImpactingClasses(classId);
    }
}
