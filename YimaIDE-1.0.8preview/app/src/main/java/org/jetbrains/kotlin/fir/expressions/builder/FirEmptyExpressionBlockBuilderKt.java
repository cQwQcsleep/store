package org.jetbrains.kotlin.fir.expressions.builder;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.impl.FirEmptyExpressionBlock;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0006\u0010\u0000\u001a\u00020\u0001¨\u0006\u0002"}, d2 = {"buildEmptyExpressionBlock", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirEmptyExpressionBlockBuilderKt {
    public static final FirBlock buildEmptyExpressionBlock() {
        return new FirEmptyExpressionBlock();
    }
}
