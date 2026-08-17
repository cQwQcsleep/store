package org.jetbrains.kotlin.contracts.interpretation;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.expressions.BooleanConstantReference;
import org.jetbrains.kotlin.contracts.description.expressions.ConstantReference;
import org.jetbrains.kotlin.contracts.model.structure.ESConstant;
import org.jetbrains.kotlin.contracts.model.structure.ESConstants;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/contracts/interpretation/ConstantValuesInterpreter;", Argument.Delimiters.none, "<init>", "()V", "interpretConstant", "Lorg/jetbrains/kotlin/contracts/model/structure/ESConstant;", "constantReference", "Lorg/jetbrains/kotlin/contracts/description/expressions/ConstantReference;", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConstantValuesInterpreter {
    public final ESConstant interpretConstant(ConstantReference constantReference) {
        constantReference.getClass();
        BooleanConstantReference.Companion companion = BooleanConstantReference.INSTANCE;
        if (Intrinsics.areEqual(constantReference, companion.getTRUE())) {
            return ESConstants.INSTANCE.getTrueValue();
        }
        if (Intrinsics.areEqual(constantReference, companion.getFALSE())) {
            return ESConstants.INSTANCE.getFalseValue();
        }
        ConstantReference.Companion companion2 = ConstantReference.INSTANCE;
        if (Intrinsics.areEqual(constantReference, companion2.getNULL())) {
            return ESConstants.INSTANCE.getNullValue();
        }
        if (Intrinsics.areEqual(constantReference, companion2.getNOT_NULL())) {
            return ESConstants.INSTANCE.getNotNullValue();
        }
        if (Intrinsics.areEqual(constantReference, companion2.getWILDCARD())) {
            return ESConstants.INSTANCE.getWildcard();
        }
        return null;
    }
}
