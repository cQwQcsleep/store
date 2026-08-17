package defpackage;

import com.google.common.math.StatsAccumulator;
import java.util.function.ObjIntConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
public final /* synthetic */ class cnd implements ObjIntConsumer {
    @Override // java.util.function.ObjIntConsumer
    public final void accept(Object obj, int i) {
        ((StatsAccumulator) obj).add(i);
    }
}
