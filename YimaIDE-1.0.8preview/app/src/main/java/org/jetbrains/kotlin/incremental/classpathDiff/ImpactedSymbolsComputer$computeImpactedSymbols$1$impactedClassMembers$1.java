package org.jetbrains.kotlin.incremental.classpathDiff;

import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
public final /* synthetic */ class ImpactedSymbolsComputer$computeImpactedSymbols$1$impactedClassMembers$1 extends FunctionReferenceImpl implements Function1<ClassMembers, Set<? extends ClassMembers>> {
    public ImpactedSymbolsComputer$computeImpactedSymbols$1$impactedClassMembers$1(Object obj) {
        super(1, obj, ImpactedSymbolsResolver.class, "getImpactedClassMembers", "getImpactedClassMembers(Lorg/jetbrains/kotlin/incremental/classpathDiff/ClassMembers;)Ljava/util/Set;", 0);
    }

    public final Set<ClassMembers> invoke(ClassMembers classMembers) {
        classMembers.getClass();
        return ((ImpactedSymbolsResolver) ((CallableReference) this).receiver).getImpactedClassMembers(classMembers);
    }
}
