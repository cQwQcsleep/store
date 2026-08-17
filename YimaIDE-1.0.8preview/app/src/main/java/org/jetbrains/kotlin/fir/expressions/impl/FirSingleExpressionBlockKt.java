package org.jetbrains.kotlin.fir.expressions.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirStatement;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0086\b¨\u0006\u0004"}, d2 = {"buildSingleExpressionBlock", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "statement", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSingleExpressionBlockKt {
    public static final FirBlock buildSingleExpressionBlock(FirStatement firStatement) {
        firStatement.getClass();
        return new FirSingleExpressionBlock(firStatement);
    }
}
