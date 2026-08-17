package com.android.tools.r8.internal;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.internal.YY;
import com.android.tools.r8.naming.C3313b;
import com.android.tools.r8.naming.C3331k;
import com.android.tools.r8.naming.MapVersion;
import com.android.tools.r8.retrace.MappingPartition;
import com.android.tools.r8.retrace.MappingPartitionMetadata;
import com.android.tools.r8.retrace.ProguardMapPartitioner;
import com.android.tools.r8.retrace.ProguardMapPartitionerBuilder;
import com.android.tools.r8.retrace.ProguardMapProducer;
import com.android.tools.r8.retrace.RetracePartitionException;
import com.android.tools.r8.utils.ExceptionDiagnostic;
import com.android.tools.r8.utils.StringDiagnostic;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class YY implements ProguardMapPartitioner {
    public final ProguardMapProducer a;
    public final Consumer b;
    public final DiagnosticsHandler c;
    public final boolean d;
    public final boolean e;
    public final OM f;

    public static class b extends a {
        public OM f;

        public b(DiagnosticsHandler diagnosticsHandler) {
            super(diagnosticsHandler);
            this.f = OM.a();
        }

        public b a(OM om) {
            this.f = om;
            return this;
        }

        @Override // com.android.tools.r8.internal.YY.a, com.android.tools.r8.retrace.ProguardMapPartitionerBuilder
        public final YY build() {
            return new YY(this.a, this.b, this.c, this.d, this.e, this.f);
        }
    }

    public YY(ProguardMapProducer proguardMapProducer, Consumer consumer, DiagnosticsHandler diagnosticsHandler, boolean z, boolean z2, OM om) {
        this.a = proguardMapProducer;
        this.b = consumer;
        this.c = diagnosticsHandler;
        this.d = z;
        this.e = z2;
        this.f = om;
    }

    public final void a(HashSet hashSet, final C3313b c3313b, C3331k c3331k, String str) {
        final HashSet hashSet2 = new HashSet();
        final AW aw = new AW();
        c3331k.a(new Consumer() { // from class: h2g
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                YY.a(c3313b, hashSet2, aw, (String) obj);
            }
        });
        StringBuilder sb = new StringBuilder();
        if (!aw.a.isEmpty()) {
            sb.append("# ");
            sb.append(new BW(aw.a).r());
            sb.append("\n");
        }
        sb.append(str);
        this.b.accept(new NM(c3331k.b, sb.toString().getBytes(StandardCharsets.UTF_8)));
        hashSet.add(c3331k.b);
    }

    public final C3313b b(final InterfaceC1853ji0 interfaceC1853ji0) {
        XY xy = new XY(this.a.isFileBacked() ? new cZ(this.a.getPath(), MX.b, true) : new C1156bZ(this.a.get(), MX.b, true));
        final C3313b c3313bA = C3313b.a(xy, MapVersion.MAP_VERSION_UNKNOWN, this.c, this.d, this.e, new Consumer() { // from class: f2g
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                YY.a((C3313b.a) obj);
            }
        });
        xy.a(new BiConsumer() { // from class: g2g
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a(interfaceC1853ji0, c3313bA, (String) obj, (List) obj2);
            }
        });
        return c3313bA;
    }

    @Override // com.android.tools.r8.retrace.ProguardMapPartitioner
    public MappingPartitionMetadata run() throws IOException {
        final LinkedHashSet linkedHashSet = new LinkedHashSet();
        InterfaceC1853ji0 interfaceC1853ji0 = new InterfaceC1853ji0() { // from class: e2g
            @Override // com.android.tools.r8.internal.InterfaceC1853ji0
            public final void a(Object obj, Object obj2, Object obj3) {
                this.a.a(linkedHashSet, (C3313b) obj, (C3331k) obj2, (String) obj3);
            }
        };
        C3313b c3313bA = this.a instanceof ZY ? a(interfaceC1853ji0) : b(interfaceC1853ji0);
        MapVersion mapVersionS = MapVersion.MAP_VERSION_UNKNOWN;
        com.android.tools.r8.naming.mappinginformation.b bVarC = c3313bA.c();
        if (bVarC != null) {
            mapVersionS = bVarC.s();
        }
        OM om = this.f;
        if (om == OM.d) {
            return QM.a.a(mapVersionS);
        }
        if (om == OM.e) {
            return new RM(mapVersionS, new C1824jO(linkedHashSet), new C1397eO(c3313bA.f, c3313bA.e()));
        }
        RetracePartitionException retracePartitionException = new RetracePartitionException("Unknown mapping partitioning strategy");
        this.c.error(new ExceptionDiagnostic(retracePartitionException));
        throw retracePartitionException;
    }

    public static class a implements ProguardMapPartitionerBuilder<a, YY> {
        public ProguardMapProducer a;
        public Consumer b;
        public final DiagnosticsHandler c;
        public boolean d = false;
        public boolean e = false;

        public a(DiagnosticsHandler diagnosticsHandler) {
            this.c = diagnosticsHandler;
        }

        @Override // com.android.tools.r8.retrace.ProguardMapPartitionerBuilder
        public YY build() {
            return new YY(this.a, this.b, this.c, this.d, this.e, OM.a());
        }

        @Override // com.android.tools.r8.retrace.ProguardMapPartitionerBuilder
        public final ProguardMapPartitionerBuilder setAllowEmptyMappedRanges(boolean z) {
            this.d = z;
            return this;
        }

        @Override // com.android.tools.r8.retrace.ProguardMapPartitionerBuilder
        public final ProguardMapPartitionerBuilder setAllowExperimentalMapping(boolean z) {
            this.e = z;
            return this;
        }

        @Override // com.android.tools.r8.retrace.ProguardMapPartitionerBuilder
        public final /* bridge */ /* synthetic */ ProguardMapPartitionerBuilder setPartitionConsumer(Consumer consumer) {
            return setPartitionConsumer((Consumer<MappingPartition>) consumer);
        }

        @Override // com.android.tools.r8.retrace.ProguardMapPartitionerBuilder
        public a setPartitionConsumer(Consumer<MappingPartition> consumer) {
            this.b = consumer;
            return this;
        }

        @Override // com.android.tools.r8.retrace.ProguardMapPartitionerBuilder
        public a setProguardMapProducer(ProguardMapProducer proguardMapProducer) {
            this.a = proguardMapProducer;
            return this;
        }
    }

    public final C3313b a(final InterfaceC1853ji0 interfaceC1853ji0) {
        final C3313b c3313b = ((ZY) this.a).a;
        c3313b.a.forEach(new BiConsumer() { // from class: d2g
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                C3331k c3331k = (C3331k) obj2;
                interfaceC1853ji0.a(c3313b, c3331k, c3331k.toString());
            }
        });
        return c3313b;
    }

    public final /* synthetic */ void a(InterfaceC1853ji0 interfaceC1853ji0, C3313b c3313b, String str, List list) {
        try {
            String strA = Wf0.a("\n", (Iterable) list);
            AbstractC0706Nu abstractC0706NuB = C3313b.a(strA, (DiagnosticsHandler) null, this.d, this.e, false).b();
            if (abstractC0706NuB.size() != 1) {
                this.c.error(new StringDiagnostic("Multiple class names in payload\n: " + strA));
                return;
            }
            interfaceC1853ji0.a(c3313b, (C3331k) abstractC0706NuB.values().iterator().next(), strA);
        } catch (IOException e) {
            this.c.error(new ExceptionDiagnostic(e));
        }
    }

    public static void a(C3313b.a aVar) {
        aVar.a = true;
        aVar.b = true;
    }

    public static void a(C3313b c3313b, Set set, AW aw, String str) {
        if (((String) c3313b.e.get(str)) == null || !set.add(str)) {
            return;
        }
        aw.a.put(str, (String) c3313b.e.get(str));
    }
}
