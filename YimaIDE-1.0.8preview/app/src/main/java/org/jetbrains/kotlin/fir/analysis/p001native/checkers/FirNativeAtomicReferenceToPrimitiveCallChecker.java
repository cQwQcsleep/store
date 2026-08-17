package org.jetbrains.kotlin.fir.analysis.p001native.checkers;

import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.AbstractAtomicReferenceToPrimitiveCallChecker;
import org.jetbrains.kotlin.name.NativeRuntimeNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeAtomicReferenceToPrimitiveCallChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/AbstractAtomicReferenceToPrimitiveCallChecker;", "<init>", "()V", "org.jetbrains.kotlin:checkers.native"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirNativeAtomicReferenceToPrimitiveCallChecker extends AbstractAtomicReferenceToPrimitiveCallChecker {
    public static final FirNativeAtomicReferenceToPrimitiveCallChecker INSTANCE = new FirNativeAtomicReferenceToPrimitiveCallChecker();

    /* JADX WARN: Illegal instructions before constructor call */
    private FirNativeAtomicReferenceToPrimitiveCallChecker() {
        Map atomicByPrimitive = NativeRuntimeNames.INSTANCE.getAtomicByPrimitive();
        MppCheckerKind mppCheckerKind = MppCheckerKind.Platform;
        NativeRuntimeNames.Callables callables = NativeRuntimeNames.Callables.INSTANCE;
        super(atomicByPrimitive, mppCheckerKind, callables.getAtomicReferenceCompareAndSet(), callables.getAtomicReferenceCompareAndExchange());
    }
}
