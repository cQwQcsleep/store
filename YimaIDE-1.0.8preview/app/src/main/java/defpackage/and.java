package defpackage;

import com.google.common.math.StatsAccumulator;
import java.util.function.ObjLongConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
public final /* synthetic */ class and implements ObjLongConsumer {
    @Override // java.util.function.ObjLongConsumer
    public final void accept(Object obj, long j) {
        ((StatsAccumulator) obj).add(j);
    }
}
