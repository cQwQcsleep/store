package com.google.common.collect;

import java.util.function.BinaryOperator;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
public final /* synthetic */ class s implements BinaryOperator {
    @Override // java.util.function.BiFunction
    public final Object apply(Object obj, Object obj2) {
        return ((CollectCollectors.EnumMapAccumulator) obj).combine((CollectCollectors.EnumMapAccumulator) obj2);
    }
}
