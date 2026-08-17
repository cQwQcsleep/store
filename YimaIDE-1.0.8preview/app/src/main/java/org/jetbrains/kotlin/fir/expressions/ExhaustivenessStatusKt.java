package org.jetbrains.kotlin.fir.expressions;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003\"\u0015\u0010\u0004\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0003¨\u0006\u0005"}, d2 = {"isExhaustive", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;)Z", "isProperlyExhaustive", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ExhaustivenessStatusKt {
    public static final boolean isExhaustive(FirWhenExpression firWhenExpression) {
        firWhenExpression.getClass();
        return Intrinsics.areEqual(firWhenExpression.getExhaustivenessStatus(), ExhaustivenessStatus.ProperlyExhaustive.INSTANCE) || Intrinsics.areEqual(firWhenExpression.getExhaustivenessStatus(), ExhaustivenessStatus.ExhaustiveAsNothing.INSTANCE) || Intrinsics.areEqual(firWhenExpression.getExhaustivenessStatus(), ExhaustivenessStatus.RedundantlyExhaustive.INSTANCE);
    }

    public static final boolean isProperlyExhaustive(FirWhenExpression firWhenExpression) {
        firWhenExpression.getClass();
        return Intrinsics.areEqual(firWhenExpression.getExhaustivenessStatus(), ExhaustivenessStatus.ProperlyExhaustive.INSTANCE) || Intrinsics.areEqual(firWhenExpression.getExhaustivenessStatus(), ExhaustivenessStatus.RedundantlyExhaustive.INSTANCE);
    }
}
