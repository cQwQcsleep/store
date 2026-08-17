package defpackage;

import com.android.tools.r8.PartitionMapConsumer;
import com.android.tools.r8.retrace.MappingPartition;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final /* synthetic */ class a1g implements Consumer {
    public final /* synthetic */ PartitionMapConsumer b;

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.b.acceptMappingPartition((MappingPartition) obj);
    }
}
