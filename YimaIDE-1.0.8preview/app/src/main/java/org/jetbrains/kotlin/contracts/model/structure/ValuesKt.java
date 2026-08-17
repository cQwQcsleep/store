package org.jetbrains.kotlin.contracts.model.structure;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.expressions.BooleanConstantReference;
import org.jetbrains.kotlin.contracts.description.expressions.ConstantReference;
import org.jetbrains.kotlin.contracts.model.ESExpression;
import org.jetbrains.kotlin.contracts.model.ESValue;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003\"\u0018\u0010\u0004\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0003\"\u0018\u0010\u0005\u001a\u00020\u0001*\u00020\u00068@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0007¨\u0006\b"}, d2 = {"isTrue", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/contracts/model/ESExpression;", "(Lorg/jetbrains/kotlin/contracts/model/ESExpression;)Z", "isFalse", "isWildcard", "Lorg/jetbrains/kotlin/contracts/model/ESValue;", "(Lorg/jetbrains/kotlin/contracts/model/ESValue;)Z", "org.jetbrains.kotlin:resolution"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ValuesKt {
    public static final boolean isFalse(ESExpression eSExpression) {
        eSExpression.getClass();
        return (eSExpression instanceof ESConstant) && Intrinsics.areEqual(((ESConstant) eSExpression).getConstantReference(), BooleanConstantReference.INSTANCE.getFALSE());
    }

    public static final boolean isTrue(ESExpression eSExpression) {
        eSExpression.getClass();
        return (eSExpression instanceof ESConstant) && Intrinsics.areEqual(((ESConstant) eSExpression).getConstantReference(), BooleanConstantReference.INSTANCE.getTRUE());
    }

    public static final boolean isWildcard(ESValue eSValue) {
        eSValue.getClass();
        return (eSValue instanceof ESConstant) && Intrinsics.areEqual(((ESConstant) eSValue).getConstantReference(), ConstantReference.INSTANCE.getWILDCARD());
    }
}
