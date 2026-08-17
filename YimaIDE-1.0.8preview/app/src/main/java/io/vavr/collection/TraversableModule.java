package io.vavr.collection;

import java.util.function.ToDoubleFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
interface TraversableModule {
    static <T> double[] neumaierSum(Iterable<T> iterable, ToDoubleFunction<T> toDoubleFunction) {
        java.util.Iterator<T> it2 = iterable.iterator();
        double d = 0.0d;
        double d2 = 0.0d;
        double d3 = 0.0d;
        int i = 0;
        while (it2.hasNext()) {
            double dApplyAsDouble = toDoubleFunction.applyAsDouble(it2.next());
            double d4 = d + dApplyAsDouble;
            d2 += Math.abs(d) >= Math.abs(dApplyAsDouble) ? (d - d4) + dApplyAsDouble : d + (dApplyAsDouble - d4);
            d3 += dApplyAsDouble;
            i++;
            d = d4;
        }
        double d5 = d + d2;
        if (i <= 0 || !Double.isNaN(d5) || !Double.isInfinite(d3)) {
            d3 = d5;
        }
        return new double[]{d3, i};
    }
}
