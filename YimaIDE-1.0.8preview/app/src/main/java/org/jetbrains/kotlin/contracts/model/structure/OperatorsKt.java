package org.jetbrains.kotlin.contracts.model.structure;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.model.ESExpression;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u001a\u0014\u0010\u0003\u001a\u00020\u0001*\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001¨\u0006\u0004"}, d2 = {"and", "Lorg/jetbrains/kotlin/contracts/model/ESExpression;", "other", "or", "org.jetbrains.kotlin:resolution"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class OperatorsKt {
    public static final ESExpression and(ESExpression eSExpression, ESExpression eSExpression2) {
        eSExpression.getClass();
        return eSExpression2 == null ? eSExpression : new ESAnd(eSExpression, eSExpression2);
    }

    public static final ESExpression or(ESExpression eSExpression, ESExpression eSExpression2) {
        eSExpression.getClass();
        return eSExpression2 == null ? eSExpression : new ESOr(eSExpression, eSExpression2);
    }
}
