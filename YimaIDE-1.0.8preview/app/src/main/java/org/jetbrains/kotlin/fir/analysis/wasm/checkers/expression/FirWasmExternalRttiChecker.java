package org.jetbrains.kotlin.fir.analysis.wasm.checkers.expression;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.wasm.checkers.FirWasmWebCheckerUtils;
import org.jetbrains.kotlin.fir.analysis.web.common.checkers.expression.FirAbstractNativeRttiChecker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/wasm/checkers/expression/FirWasmExternalRttiChecker;", "Lorg/jetbrains/kotlin/fir/analysis/web/common/checkers/expression/FirAbstractNativeRttiChecker;", "<init>", "()V", "org.jetbrains.kotlin:checkers.wasm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirWasmExternalRttiChecker extends FirAbstractNativeRttiChecker {
    public static final FirWasmExternalRttiChecker INSTANCE = new FirWasmExternalRttiChecker();

    private FirWasmExternalRttiChecker() {
        super(FirWasmWebCheckerUtils.INSTANCE);
    }
}
