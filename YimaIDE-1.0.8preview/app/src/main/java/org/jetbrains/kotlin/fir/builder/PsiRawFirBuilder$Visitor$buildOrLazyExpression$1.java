package org.jetbrains.kotlin.fir.builder;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.builder.FirLazyExpressionBuilder;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PsiRawFirBuilder$Visitor$buildOrLazyExpression$1 implements Function0<FirExpression> {
    final /* synthetic */ KtSourceElement $sourceElement;

    public PsiRawFirBuilder$Visitor$buildOrLazyExpression$1(KtSourceElement ktSourceElement) {
        this.$sourceElement = ktSourceElement;
    }

    public final FirExpression invoke() {
        KtSourceElement ktSourceElement = this.$sourceElement;
        FirLazyExpressionBuilder firLazyExpressionBuilder = new FirLazyExpressionBuilder();
        firLazyExpressionBuilder.setSource(ktSourceElement);
        return firLazyExpressionBuilder.build();
    }
}
