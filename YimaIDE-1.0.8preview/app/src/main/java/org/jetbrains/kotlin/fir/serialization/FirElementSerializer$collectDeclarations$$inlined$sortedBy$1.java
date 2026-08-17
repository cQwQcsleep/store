package org.jetbrains.kotlin.fir.serialization;

import java.util.Comparator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirElementSerializer$collectDeclarations$$inlined$sortedBy$1<T> implements Comparator {
    final /* synthetic */ Map $indexByDeclaration$inlined;

    public FirElementSerializer$collectDeclarations$$inlined$sortedBy$1(Map map) {
        this.$indexByDeclaration$inlined = map;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Comparator
    public final int compare(T t, T t2) {
        Integer num = (Integer) this.$indexByDeclaration$inlined.get((FirCallableDeclaration) t);
        Integer numValueOf = Integer.valueOf(num != null ? num.intValue() : Integer.MAX_VALUE);
        Integer num2 = (Integer) this.$indexByDeclaration$inlined.get((FirCallableDeclaration) t2);
        return ComparisonsKt.compareValues(numValueOf, Integer.valueOf(num2 != null ? num2.intValue() : Integer.MAX_VALUE));
    }
}
