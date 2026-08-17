package org.jetbrains.kotlin.incremental.classpathDiff;

import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
public final /* synthetic */ class ImpactedSymbolsComputer$computeImpactedSymbols$1$impactedClasses$1 extends FunctionReferenceImpl implements Function1<ClassId, Set<? extends ClassId>> {
    public ImpactedSymbolsComputer$computeImpactedSymbols$1$impactedClasses$1(Object obj) {
        super(1, obj, ImpactedSymbolsResolver.class, "getImpactedClasses", "getImpactedClasses(Lorg/jetbrains/kotlin/name/ClassId;)Ljava/util/Set;", 0);
    }

    public final Set<ClassId> invoke(ClassId classId) {
        classId.getClass();
        return ((ImpactedSymbolsResolver) ((CallableReference) this).receiver).getImpactedClasses(classId);
    }
}
