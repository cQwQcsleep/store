package org.jetbrains.kotlin.contracts.model.structure;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.model.ESEffect;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a)\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0017\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0007"}, d2 = {"isReturns", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/contracts/model/ESEffect;", "block", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/contracts/model/structure/ESReturns;", "Lkotlin/ExtensionFunctionType;", "org.jetbrains.kotlin:resolution"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class EffectsKt {
    public static final boolean isReturns(ESEffect eSEffect, Function1<? super ESReturns, Boolean> function1) {
        eSEffect.getClass();
        function1.getClass();
        return (eSEffect instanceof ESReturns) && ((Boolean) function1.invoke(eSEffect)).booleanValue();
    }
}
