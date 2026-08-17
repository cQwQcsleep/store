package org.jetbrains.kotlin.cli.pipeline.jvm;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.util.PerformanceManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class JvmFrontendPipelinePhase$executePhase$countFilesAndLines$1 extends FunctionReferenceImpl implements Function2<Integer, Integer, Unit> {
    public JvmFrontendPipelinePhase$executePhase$countFilesAndLines$1(Object obj) {
        super(2, obj, PerformanceManager.class, "addSourcesStats", "addSourcesStats(II)V", 0);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke(((Number) obj).intValue(), ((Number) obj2).intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(int i, int i2) {
        ((PerformanceManager) ((CallableReference) this).receiver).addSourcesStats(i, i2);
    }
}
