package org.jetbrains.kotlin.fir.builder;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.expressions.FirExpression;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PsiRawFirBuilder$Visitor$toFirExpression$3 implements Function1<FirExpression, Boolean> {
    public static final PsiRawFirBuilder$Visitor$toFirExpression$3 INSTANCE = new PsiRawFirBuilder$Visitor$toFirExpression$3();

    public final Boolean invoke(FirExpression firExpression) {
        firExpression.getClass();
        return Boolean.valueOf(!UtilsKt.isStatementLikeExpression(firExpression));
    }
}
