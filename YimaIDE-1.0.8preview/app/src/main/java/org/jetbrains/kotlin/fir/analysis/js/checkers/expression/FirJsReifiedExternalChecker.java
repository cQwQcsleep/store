package org.jetbrains.kotlin.fir.analysis.js.checkers.expression;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.diagnostics.web.common.FirWebCommonErrors;
import org.jetbrains.kotlin.fir.analysis.js.checkers.FirJsWebCheckerUtils;
import org.jetbrains.kotlin.fir.analysis.web.common.checkers.expression.FirAbstractReifiedOnDeclarationWithoutRuntimeChecker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/js/checkers/expression/FirJsReifiedExternalChecker;", "Lorg/jetbrains/kotlin/fir/analysis/web/common/checkers/expression/FirAbstractReifiedOnDeclarationWithoutRuntimeChecker;", "<init>", "()V", "org.jetbrains.kotlin:checkers.js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJsReifiedExternalChecker extends FirAbstractReifiedOnDeclarationWithoutRuntimeChecker {
    public static final FirJsReifiedExternalChecker INSTANCE = new FirJsReifiedExternalChecker();

    private FirJsReifiedExternalChecker() {
        super(FirJsWebCheckerUtils.INSTANCE, FirWebCommonErrors.INSTANCE.getEXTERNAL_INTERFACE_AS_REIFIED_TYPE_ARGUMENT());
    }
}
