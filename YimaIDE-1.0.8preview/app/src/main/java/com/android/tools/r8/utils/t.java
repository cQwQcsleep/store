package com.android.tools.r8.utils;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.PartitionMapConsumer;
import com.android.tools.r8.internal.C1975l7;
import com.android.tools.r8.internal.InterfaceC2706th0;
import com.android.tools.r8.retrace.MappingPartition;
import com.android.tools.r8.retrace.MappingPartitionMetadata;
import java.io.IOException;
import java.nio.file.Path;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class t implements PartitionMapConsumer {
    public final C1975l7 a = new C1975l7();
    public final Path b;

    public t(Path path) {
        this.b = path;
    }

    public final /* synthetic */ v.b a() {
        return v.b.a(this.b);
    }

    @Override // com.android.tools.r8.PartitionMapConsumer
    public final void acceptMappingPartition(MappingPartition mappingPartition) {
        try {
            C1975l7 c1975l7 = this.a;
            InterfaceC2706th0 interfaceC2706th0 = new InterfaceC2706th0() { // from class: nbi
                @Override // com.android.tools.r8.internal.InterfaceC2706th0
                public final Object get() {
                    return this.a.a();
                }
            };
            if (!c1975l7.b()) {
                c1975l7.a(interfaceC2706th0.get());
            }
            ((v.b) c1975l7.a()).a(mappingPartition.getKey(), mappingPartition.getPayload());
        } catch (IOException e) {
            rc6.a(e);
        }
    }

    @Override // com.android.tools.r8.PartitionMapConsumer
    public final void acceptMappingPartitionMetadata(MappingPartitionMetadata mappingPartitionMetadata) {
        try {
            C1975l7 c1975l7 = this.a;
            InterfaceC2706th0 interfaceC2706th0 = new InterfaceC2706th0() { // from class: lbi
                @Override // com.android.tools.r8.internal.InterfaceC2706th0
                public final Object get() {
                    return this.a.b();
                }
            };
            if (!c1975l7.b()) {
                c1975l7.a(interfaceC2706th0.get());
            }
            ((v.b) c1975l7.a()).a("METADATA", mappingPartitionMetadata.getBytes());
        } catch (IOException e) {
            rc6.a(e);
        }
    }

    public final /* synthetic */ v.b b() {
        return v.b.a(this.b);
    }

    public final /* synthetic */ v.b c() {
        return v.b.a(this.b);
    }

    @Override // com.android.tools.r8.I
    public final void finished(DiagnosticsHandler diagnosticsHandler) {
        try {
            C1975l7 c1975l7 = this.a;
            InterfaceC2706th0 interfaceC2706th0 = new InterfaceC2706th0() { // from class: jbi
                @Override // com.android.tools.r8.internal.InterfaceC2706th0
                public final Object get() {
                    return this.a.c();
                }
            };
            if (!c1975l7.b()) {
                c1975l7.a(interfaceC2706th0.get());
            }
            ((v.b) c1975l7.a()).a();
        } catch (IOException e) {
            rc6.a(e);
        }
    }
}
