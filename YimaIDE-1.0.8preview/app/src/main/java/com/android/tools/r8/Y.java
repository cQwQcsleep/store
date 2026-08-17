package com.android.tools.r8;

import com.android.tools.r8.internal.ZY;
import com.android.tools.r8.naming.C3313b;
import com.android.tools.r8.retrace.ProguardMapPartitioner;
import com.android.tools.r8.retrace.ProguardMapPartitionerBuilder;
import defpackage.a1g;
import defpackage.x0g;
import java.io.IOException;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Y implements com.android.tools.r8.naming.Q {
    public static final /* synthetic */ boolean b = true;
    public final PartitionMapConsumer a;

    public Y(PartitionMapConsumer partitionMapConsumer) {
        if (b || partitionMapConsumer != null) {
            this.a = partitionMapConsumer;
        } else {
            x1f.a();
            throw null;
        }
    }

    @Override // com.android.tools.r8.naming.Q
    public final void a(DiagnosticsHandler diagnosticsHandler, C3313b c3313b) {
        try {
            PartitionMapConsumer partitionMapConsumer = this.a;
            ProguardMapPartitionerBuilder proguardMapProducer = ProguardMapPartitioner.builder(diagnosticsHandler).setProguardMapProducer(new ZY(c3313b));
            PartitionMapConsumer partitionMapConsumer2 = this.a;
            Objects.requireNonNull(partitionMapConsumer2);
            partitionMapConsumer.acceptMappingPartitionMetadata(proguardMapProducer.setPartitionConsumer(new a1g(partitionMapConsumer2)).setAllowEmptyMappedRanges(false).setAllowExperimentalMapping(false).build().run());
        } catch (IOException unused) {
            x0g.a("IOExceptions should only occur when parsing");
        }
    }

    @Override // com.android.tools.r8.I
    public final void finished(DiagnosticsHandler diagnosticsHandler) {
        this.a.finished(diagnosticsHandler);
    }
}
