package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.name.StandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCommonAtomicReferenceToPrimitiveCallChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/AbstractAtomicReferenceToPrimitiveCallChecker;", "<init>", "()V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirCommonAtomicReferenceToPrimitiveCallChecker extends AbstractAtomicReferenceToPrimitiveCallChecker {
    public static final FirCommonAtomicReferenceToPrimitiveCallChecker INSTANCE = new FirCommonAtomicReferenceToPrimitiveCallChecker();

    /* JADX WARN: Illegal instructions before constructor call */
    private FirCommonAtomicReferenceToPrimitiveCallChecker() {
        Map atomicByPrimitive = StandardClassIds.INSTANCE.getAtomicByPrimitive();
        MppCheckerKind mppCheckerKind = MppCheckerKind.Platform;
        StandardClassIds.Callables callables = StandardClassIds.Callables.INSTANCE;
        super(atomicByPrimitive, mppCheckerKind, callables.getAtomicReferenceCompareAndSet(), callables.getAtomicReferenceCompareAndExchange());
    }
}
