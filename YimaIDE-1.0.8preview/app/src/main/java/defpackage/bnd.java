package defpackage;

import com.google.common.math.StatsAccumulator;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
public final /* synthetic */ class bnd implements BiConsumer {
    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((StatsAccumulator) obj).addAll((StatsAccumulator) obj2);
    }
}
