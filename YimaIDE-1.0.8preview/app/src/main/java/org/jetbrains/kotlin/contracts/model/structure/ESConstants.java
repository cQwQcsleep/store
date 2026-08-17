package org.jetbrains.kotlin.contracts.model.structure;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.expressions.BooleanConstantReference;
import org.jetbrains.kotlin.contracts.description.expressions.ConstantReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/contracts/model/structure/ESConstants;", Argument.Delimiters.none, "<init>", "()V", "trueValue", "Lorg/jetbrains/kotlin/contracts/model/structure/ESConstant;", "getTrueValue", "()Lorg/jetbrains/kotlin/contracts/model/structure/ESConstant;", "falseValue", "getFalseValue", "nullValue", "getNullValue", "notNullValue", "getNotNullValue", "wildcard", "getWildcard", "booleanValue", "value", Argument.Delimiters.none, "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ESConstants {
    public static final ESConstants INSTANCE = new ESConstants();
    private static final ESConstant falseValue;
    private static final ESConstant notNullValue;
    private static final ESConstant nullValue;
    private static final ESConstant trueValue;
    private static final ESConstant wildcard;

    static {
        BooleanConstantReference.Companion companion = BooleanConstantReference.INSTANCE;
        BooleanConstantReference booleanConstantReference = companion.getTRUE();
        ESBooleanType eSBooleanType = ESBooleanType.INSTANCE;
        trueValue = new ESConstant(booleanConstantReference, eSBooleanType);
        falseValue = new ESConstant(companion.getFALSE(), eSBooleanType);
        ConstantReference.Companion companion2 = ConstantReference.INSTANCE;
        nullValue = new ESConstant(companion2.getNULL(), ESNullableNothingType.INSTANCE);
        notNullValue = new ESConstant(companion2.getNOT_NULL(), ESAnyType.INSTANCE);
        wildcard = new ESConstant(companion2.getWILDCARD(), ESNullableAnyType.INSTANCE);
    }

    private ESConstants() {
    }

    public final ESConstant booleanValue(boolean value) {
        return value ? trueValue : falseValue;
    }

    public final ESConstant getFalseValue() {
        return falseValue;
    }

    public final ESConstant getNotNullValue() {
        return notNullValue;
    }

    public final ESConstant getNullValue() {
        return nullValue;
    }

    public final ESConstant getTrueValue() {
        return trueValue;
    }

    public final ESConstant getWildcard() {
        return wildcard;
    }
}
