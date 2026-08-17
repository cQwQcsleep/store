package org.jetbrains.kotlin.fir.analysis.js.checkers.expression;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.js.checkers.FirJsWebCheckerUtils;
import org.jetbrains.kotlin.fir.analysis.web.common.checkers.expression.FirAbstractNativeRttiChecker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/js/checkers/expression/FirJsNativeRttiChecker;", "Lorg/jetbrains/kotlin/fir/analysis/web/common/checkers/expression/FirAbstractNativeRttiChecker;", "<init>", "()V", "org.jetbrains.kotlin:checkers.js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJsNativeRttiChecker extends FirAbstractNativeRttiChecker {
    public static final FirJsNativeRttiChecker INSTANCE = new FirJsNativeRttiChecker();

    private FirJsNativeRttiChecker() {
        super(FirJsWebCheckerUtils.INSTANCE);
    }
}
