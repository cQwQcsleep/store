package com.android.tools.r8.retrace;

import com.android.tools.r8.ParseFlagInfo;
import com.android.tools.r8.ParseFlagPrinter;
import com.android.tools.r8.PartitionMapConsumer;
import com.android.tools.r8.Version;
import com.android.tools.r8.a0;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.AbstractC2632so;
import com.android.tools.r8.internal.AbstractC2686tV;
import com.android.tools.r8.internal.C2600sV;
import com.android.tools.r8.internal.InterfaceC1938ki0;
import com.android.tools.r8.internal.InterfaceC2718to;
import com.android.tools.r8.internal.Wf0;
import com.android.tools.r8.retrace.Partition;
import com.android.tools.r8.utils.StringDiagnostic;
import com.android.tools.r8.utils.t;
import defpackage.a1g;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class Partition {
    private static final String a = Wf0.b("Usage: partition [options] <proguard-map> where <proguard-map> is a generated mapping file and options are:");
    static final /* synthetic */ boolean b = true;

    private static void a(String[] strArr, a aVar) {
        C2600sV c2600sV = new C2600sV(strArr);
        PartitionCommand.Builder builder = PartitionCommand.builder();
        boolean z = false;
        while (c2600sV.a() != null) {
            if (AbstractC2686tV.a(c2600sV, "--help") != null) {
                builder = null;
                break;
            }
            String strA = AbstractC2686tV.a(c2600sV, "--output", null);
            if (strA != null && !strA.isEmpty()) {
                builder.setPartitionMapConsumer(new t(Paths.get(strA, new String[0])));
            } else {
                if (z) {
                    StringBuilder sb = new StringBuilder();
                    Wf0.a(sb, a);
                    new ParseFlagPrinter().addFlags(getFlags()).appendLinesToBuilder(sb);
                    aVar.error(new StringDiagnostic(sb.toString()));
                    throw new RetracePartitionException("Too many arguments specified for builder at '" + c2600sV.a() + "'");
                }
                builder.setProguardMapProducer(ProguardMapProducer.fromPath(Paths.get(c2600sV.a(), new String[0])));
                c2600sV.b();
                z = true;
            }
        }
        if (builder != null) {
            run(builder.build());
            return;
        }
        if (!b && !Arrays.asList(strArr).contains("--help")) {
            x1f.a();
            return;
        }
        PrintStream printStream = System.out;
        printStream.println("Partition " + Version.getVersionString());
        StringBuilder sb2 = new StringBuilder();
        Wf0.a(sb2, a);
        new ParseFlagPrinter().addFlags(getFlags()).appendLinesToBuilder(sb2);
        printStream.print(sb2.toString());
    }

    public static List<ParseFlagInfo> getFlags() {
        return AbstractC0551Hu.g().a(a0.a("--output", Collections.singletonList("<partition-map>"), Arrays.asList("Output destination of partitioned map"))).a(a0.c()).a();
    }

    public static void main(final String... strArr) {
        AbstractC2632so.a(new InterfaceC2718to() { // from class: mza
            @Override // com.android.tools.r8.internal.InterfaceC2718to
            public final void run() {
                Partition.run(strArr);
            }
        });
    }

    public static void run(PartitionCommand partitionCommand) {
        try {
            PartitionMapConsumer partitionMapConsumer = partitionCommand.getPartitionMapConsumer();
            ProguardMapPartitionerBuilder proguardMapProducer = ProguardMapPartitioner.builder(partitionCommand.getDiagnosticsHandler()).setProguardMapProducer(partitionCommand.getProguardMapProducer());
            PartitionMapConsumer partitionMapConsumer2 = partitionCommand.getPartitionMapConsumer();
            Objects.requireNonNull(partitionMapConsumer2);
            partitionMapConsumer.acceptMappingPartitionMetadata(proguardMapProducer.setPartitionConsumer(new a1g(partitionMapConsumer2)).setAllowEmptyMappedRanges(true).setAllowExperimentalMapping(false).build().run());
            partitionCommand.getPartitionMapConsumer().finished(partitionCommand.getDiagnosticsHandler());
        } catch (Throwable th) {
            throw ((RetracePartitionException) AbstractC2632so.a(partitionCommand.getDiagnosticsHandler(), th, new InterfaceC1938ki0() { // from class: lza
                @Override // com.android.tools.r8.internal.InterfaceC1938ki0
                public final Object a(Object obj, Object obj2, Object obj3) {
                    return Partition.a((String) obj, (Throwable) obj2, (Boolean) obj3);
                }
            }, RetracePartitionException.class));
        }
    }

    public static void run(String[] strArr) throws RetracePartitionException {
        a(strArr, new a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ RetracePartitionException a(String str, Throwable th, Boolean bool) {
        return new RetracePartitionException(str, th);
    }
}
