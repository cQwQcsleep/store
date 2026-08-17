package com.android.tools.r8.retrace;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.StringConsumer;
import com.android.tools.r8.internal.C1156bZ;
import com.android.tools.r8.internal.C1397eO;
import com.android.tools.r8.internal.CW;
import com.android.tools.r8.internal.MX;
import com.android.tools.r8.internal.QM;
import com.android.tools.r8.naming.C3313b;
import com.android.tools.r8.retrace.c;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class c {
    public final StringConsumer a;
    public final CW b;
    public final DiagnosticsHandler c;

    public c(StringConsumer stringConsumer, CW cw, DiagnosticsHandler diagnosticsHandler) {
        this.a = stringConsumer;
        this.b = cw;
        this.c = diagnosticsHandler;
    }

    public final void a(QM qm, MappingPartitionFromKeySupplier mappingPartitionFromKeySupplier) {
        final d dVar = new d(this.a, this.c);
        if (qm.c()) {
            C1397eO c1397eOE = qm.e();
            if (c1397eOE.d()) {
                c1397eOE.b().forEach(new Consumer() { // from class: fig
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        c.a(dVar, (String) obj);
                    }
                });
            }
        }
        Iterator<String> it = qm.a().iterator();
        while (it.hasNext()) {
            try {
                C3313b.a(new C1156bZ(new ByteArrayInputStream(mappingPartitionFromKeySupplier.get(it.next())), MX.b, true), qm.b(), this.c, true, true, new Consumer() { // from class: kig
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        c.a((C3313b.a) obj);
                    }
                }).a(dVar);
            } catch (IOException e) {
                throw new RetracePartitionException(e);
            }
        }
        dVar.a.finished(this.c);
        this.b.finished(this.c);
    }

    public void b() throws RetracePartitionException {
        QM metadata = this.b.getMetadata(this.c);
        if (metadata == null || !metadata.d()) {
            throw new RetracePartitionException("Cannot obtain all partition keys from metadata");
        }
        PartitionMappingSupplier partitionMappingSupplier = this.b.getPartitionMappingSupplier();
        if (partitionMappingSupplier == null) {
            throw new RetracePartitionException("Running synchronously requires a synchronous partition mapping provider. Use runAsync() if you have an asynchronous provider.");
        }
        Iterator<String> it = metadata.a().iterator();
        while (it.hasNext()) {
            this.b.registerKeyUse(it.next());
        }
        a(metadata, partitionMappingSupplier.getMappingPartitionFromKeySupplier());
    }

    public static class a {
        public StringConsumer a;
        public CW b;
        public DiagnosticsHandler c;

        public c a() {
            return new c(this.a, this.b, this.c);
        }

        public a a(CW<?> cw) {
            this.b = cw;
            return this;
        }

        public a a(DiagnosticsHandler diagnosticsHandler) {
            this.c = diagnosticsHandler;
            return this;
        }

        public a a(StringConsumer stringConsumer) {
            this.a = stringConsumer;
            return this;
        }
    }

    public static void a(C3313b.a aVar) {
        aVar.a = true;
    }

    public static void a(d dVar, String str) {
        dVar.a.accept(str, dVar.b);
        dVar.a.accept("\n", dVar.b);
    }

    public static a a() {
        return new a();
    }
}
