package com.android.tools.r8.retrace;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.ParseFlagInfo;
import com.android.tools.r8.ParseFlagPrinter;
import com.android.tools.r8.Version;
import com.android.tools.r8.a0;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.AbstractC2632so;
import com.android.tools.r8.internal.AbstractC2686tV;
import com.android.tools.r8.internal.AbstractC2835v90;
import com.android.tools.r8.internal.AbstractC3120yb;
import com.android.tools.r8.internal.C0473Eu;
import com.android.tools.r8.internal.C2600sV;
import com.android.tools.r8.internal.C2664t90;
import com.android.tools.r8.internal.Ch0;
import com.android.tools.r8.internal.InterfaceC1938ki0;
import com.android.tools.r8.internal.Nd0;
import com.android.tools.r8.internal.Wf0;
import com.android.tools.r8.naming.mappinginformation.b;
import com.android.tools.r8.retrace.Retrace;
import com.android.tools.r8.retrace.RetraceFailedException;
import com.android.tools.r8.retrace.StackTraceElementProxy;
import com.android.tools.r8.utils.ExceptionDiagnostic;
import com.android.tools.r8.utils.StringDiagnostic;
import com.android.tools.r8.utils.u;
import defpackage.g3c;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class Retrace<T, ST extends StackTraceElementProxy<T, ST>> extends AbstractC2835v90 {
    private static final String f = Wf0.b("Usage: retrace [options] <proguard-map> [stack-trace-file] where <proguard-map> is a generated mapping file and options are:");
    static final /* synthetic */ boolean g = true;
    private final MappingSupplier d;
    private final DiagnosticsHandler e;

    public Retrace(StackTraceLineParser<T, ST> stackTraceLineParser, MappingSupplier<?> mappingSupplier, DiagnosticsHandler diagnosticsHandler, boolean z) {
        super(stackTraceLineParser, mappingSupplier, diagnosticsHandler, z);
        this.d = mappingSupplier;
        this.e = diagnosticsHandler;
    }

    private static void a(String[] strArr, final h hVar) {
        C2600sV c2600sV = new C2600sV(strArr);
        RetraceCommand.Builder builder = RetraceCommand.builder(hVar);
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        while (true) {
            if (c2600sV.a() == null) {
                if (!z) {
                    hVar.error(new StringDiagnostic("Mapping file not specified"));
                    throw new C2664t90();
                }
                if (!z2) {
                    if (!z3) {
                        System.out.println("Waiting for stack-trace input...");
                    }
                    Scanner scanner = new Scanner(new InputStreamReader(System.in, AbstractC3120yb.a));
                    ArrayList arrayList = new ArrayList();
                    while (scanner.hasNext()) {
                        arrayList.add(scanner.nextLine());
                    }
                    builder.setStackTrace(arrayList);
                    break;
                }
                break;
            }
            if (AbstractC2686tV.a(c2600sV, "--help") != null || AbstractC2686tV.a(c2600sV, "--version") != null) {
                builder = null;
                break;
            }
            if (AbstractC2686tV.a(c2600sV, "--info") == null) {
                if (AbstractC2686tV.a(c2600sV, "--verbose") != null) {
                    builder.setVerbose(true);
                } else if (AbstractC2686tV.a(c2600sV, "--quiet") != null) {
                    z3 = true;
                } else {
                    String strA = AbstractC2686tV.a(c2600sV, "--regex", "--r");
                    if (strA == null || strA.isEmpty()) {
                        if (AbstractC2686tV.a(c2600sV, "--verify-mapping-file-hash") != null) {
                            builder.setVerifyMappingFileHash(true);
                        } else {
                            String strA2 = AbstractC2686tV.a(c2600sV, "--partition-map", "--p");
                            if (strA2 != null && !strA2.isEmpty()) {
                                Path path = Paths.get(strA2, new String[0]);
                                if (!Files.exists(path, new LinkOption[0])) {
                                    hVar.error(new StringDiagnostic("Could not find mapping file '" + strA2 + "'."));
                                    throw new C2664t90();
                                }
                                try {
                                    builder.setMappingSupplier(u.a(path));
                                } catch (Exception e) {
                                    hVar.error(new ExceptionDiagnostic(e));
                                    throw new C2664t90();
                                }
                            } else if (!z) {
                                String strA3 = c2600sV.a();
                                if (!Files.exists(Paths.get(strA3, new String[0]), new LinkOption[0])) {
                                    hVar.error(new StringDiagnostic("Could not find mapping file '" + strA3 + "'."));
                                    throw new C2664t90();
                                }
                                builder.setMappingSupplier(ProguardMappingSupplier.builder().setProguardMapProducer(ProguardMapProducer.fromPath(Paths.get(strA3, new String[0]))).setAllowExperimental(System.getProperty("com.android.tools.r8.experimentalmapping") != null).setLoadAllDefinitions(false).build());
                                c2600sV.b();
                            } else {
                                if (z2) {
                                    hVar.error(new StringDiagnostic("Too many arguments specified for builder at '" + c2600sV.a() + "'"));
                                    hVar.error(new StringDiagnostic(a()));
                                    throw new C2664t90();
                                }
                                try {
                                    builder.setStackTrace(Files.readAllLines(Paths.get(c2600sV.a(), new String[0]), AbstractC3120yb.a));
                                    c2600sV.b();
                                } catch (IOException e2) {
                                    hVar.error(new ExceptionDiagnostic(e2));
                                    throw new C2664t90();
                                }
                            }
                            z = true;
                        }
                        z2 = true;
                    } else {
                        builder.setRegularExpression(strA);
                    }
                }
            }
        }
        if (builder != null) {
            builder.setRetracedStackTraceConsumer(new Consumer() { // from class: cjc
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    Retrace.a(hVar, (List) obj);
                }
            });
            run(builder.build());
            return;
        }
        if (Arrays.asList(strArr).contains("--version")) {
            System.out.println("Retrace " + Version.getVersionString());
            return;
        }
        if (!g && !Arrays.asList(strArr).contains("--help")) {
            x1f.a();
            return;
        }
        PrintStream printStream = System.out;
        printStream.println("Retrace " + Version.getVersionString());
        printStream.print(a());
    }

    public static <T, ST extends StackTraceElementProxy<T, ST>> Builder<T, ST> builder() {
        return new Builder<>();
    }

    public static List<ParseFlagInfo> getFlags() {
        C0473Eu c0473EuA = AbstractC0551Hu.g().a(a0.a("--regex", Collections.singletonList("<regexp>"), Arrays.asList("Regular expression for parsing stack-trace-file as lines")));
        List list = Collections.EMPTY_LIST;
        return c0473EuA.a(a0.a("--verbose", list, Arrays.asList("Get verbose retraced output"))).a(a0.a("--info", list, Arrays.asList("Write information messages to stdout"))).a(a0.a("--quiet", list, Arrays.asList("Silence ordinary messages printed to stdout"))).a(a0.a("--verify-mapping-file-hash", list, Arrays.asList("Verify the mapping file hash"))).a(a0.c()).a();
    }

    public static void main(final String... strArr) {
        try {
            new g() { // from class: ejc
                @Override // com.android.tools.r8.retrace.g
                public final void run() throws RetraceFailedException {
                    Retrace.run(strArr);
                }
            }.run();
        } catch (C2664t90 | RetraceFailedException e) {
            g3c.a("Retrace failed", e);
        } catch (Throwable th) {
            g3c.a("Retrace failed with an internal error.", th);
        }
    }

    public static void run(RetraceCommand retraceCommand) {
        try {
            Ch0 ch0 = new Ch0("R8 retrace", retraceCommand.printMemory());
            RetraceOptions options = retraceCommand.getOptions();
            MappingSupplier<?> mappingSupplier = options.getMappingSupplier();
            if (retraceCommand.getOptions().isVerifyMappingFileHash()) {
                mappingSupplier.verifyMappingFileHash(options.getDiagnosticsHandler());
                return;
            }
            final DiagnosticsHandler diagnosticsHandler = options.getDiagnosticsHandler();
            Nd0 nd0 = new Nd0(options.getRegularExpression());
            StackTraceSupplier stacktraceSupplier = retraceCommand.getStacktraceSupplier();
            RetraceStackTraceContext retraceStackTraceContextEmpty = RetraceStackTraceContext.empty();
            int i = 0;
            while (true) {
                List<String> list = stacktraceSupplier.get();
                if (list == null) {
                    if (retraceCommand.printTimes()) {
                        ch0.c();
                    }
                    mappingSupplier.getMapVersions(diagnosticsHandler).forEach(new Consumer() { // from class: djc
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            Retrace.a(diagnosticsHandler, (b) obj);
                        }
                    });
                    mappingSupplier.finished(diagnosticsHandler);
                    return;
                }
                ch0.a("Parsing");
                ArrayList arrayList = new ArrayList();
                for (String str : list) {
                    if (str == null) {
                        diagnosticsHandler.error(RetraceInvalidStackTraceLineDiagnostics.createNull(i));
                        throw new C2664t90();
                    }
                    arrayList.add(nd0.parse(str));
                    i++;
                }
                ch0.b();
                ch0.a("Read proguard map");
                StringRetrace stringRetrace = new StringRetrace(nd0, mappingSupplier, diagnosticsHandler, options.isVerbose());
                ch0.b();
                ch0.a("Retracing");
                RetraceStackFrameResultWithContext<String> retraceStackFrameResultWithContextRetraceParsed = stringRetrace.retraceParsed(arrayList, retraceStackTraceContextEmpty);
                ch0.b();
                ch0.a("Report result");
                RetraceStackTraceContext context = retraceStackFrameResultWithContextRetraceParsed.getContext();
                if (!retraceStackFrameResultWithContextRetraceParsed.isEmpty() || list.isEmpty()) {
                    retraceCommand.getRetracedStackTraceConsumer().accept(retraceStackFrameResultWithContextRetraceParsed.getResult());
                }
                ch0.b();
                retraceStackTraceContextEmpty = context;
            }
        } catch (InvalidMappingFileException e) {
            retraceCommand.getOptions().getDiagnosticsHandler().error(new ExceptionDiagnostic(e));
            throw e;
        }
    }

    public RetraceStackFrameAmbiguousResultWithContext<T> retraceFrame(T t, RetraceStackTraceContext retraceStackTraceContext) {
        StackTraceElementProxy<Object, Object> stackTraceElementProxy = parse(t);
        registerUses(stackTraceElementProxy);
        return (RetraceStackFrameAmbiguousResultWithContext<T>) retraceFrameWithRetracer(this.d.createRetracer(this.e), stackTraceElementProxy, retraceStackTraceContext);
    }

    public RetraceStackFrameResultWithContext<T> retraceLine(T t, RetraceStackTraceContext retraceStackTraceContext) {
        StackTraceElementProxy<Object, Object> stackTraceElementProxy = parse(t);
        registerUses(stackTraceElementProxy);
        return (RetraceStackFrameResultWithContext<T>) retraceLineWithRetracer(this.d.createRetracer(this.e), stackTraceElementProxy, retraceStackTraceContext);
    }

    public RetraceStackTraceResult<T> retraceStackTrace(List<T> list, RetraceStackTraceContext retraceStackTraceContext) {
        return retraceStackTraceParsed(parse((List<Object>) list), retraceStackTraceContext);
    }

    public RetraceStackTraceResult<T> retraceStackTraceParsed(List<ST> list, RetraceStackTraceContext retraceStackTraceContext) {
        registerUses(list);
        return (RetraceStackTraceResult<T>) retraceStackTraceParsedWithRetracer(this.d.createRetracer(this.e), list, retraceStackTraceContext);
    }

    public static class Builder<T, ST extends StackTraceElementProxy<T, ST>> extends RetraceBuilderBase<Builder<T, ST>, T, ST> {
        private MappingSupplier a;

        public Retrace<T, ST> build() {
            return new Retrace<>(this.stackTraceLineParser, this.a, this.diagnosticsHandler, this.isVerbose);
        }

        public Builder<T, ST> setMappingSupplier(MappingSupplier<?> mappingSupplier) {
            this.a = mappingSupplier;
            return this;
        }

        @Override // com.android.tools.r8.retrace.RetraceBuilderBase
        public Builder<T, ST> self() {
            return this;
        }
    }

    public static void run(String[] strArr) throws RetraceFailedException {
        String[] strArr2 = new String[strArr.length];
        boolean z = false;
        for (int i = 0; i < strArr.length; i++) {
            String str = strArr[i];
            if (str != null && str.length() >= 2) {
                if (str.charAt(0) == '-' && str.charAt(1) != '-') {
                    strArr2[i] = "-".concat(str);
                } else {
                    strArr2[i] = str;
                }
                if (strArr2[i].equals("--info")) {
                    z = true;
                }
            } else {
                strArr2[i] = str;
            }
        }
        h hVar = new h(new f(), z);
        try {
            a(strArr2, hVar);
        } catch (Throwable th) {
            throw ((RetraceFailedException) AbstractC2632so.a(hVar, th, new InterfaceC1938ki0() { // from class: bjc
                @Override // com.android.tools.r8.internal.InterfaceC1938ki0
                public final Object a(Object obj, Object obj2, Object obj3) {
                    return Retrace.a((String) obj, (Throwable) obj2, (Boolean) obj3);
                }
            }, C2664t90.class));
        }
    }

    public static String a() {
        StringBuilder sb = new StringBuilder();
        Wf0.a(sb, f);
        new ParseFlagPrinter().addFlags(getFlags()).appendLinesToBuilder(sb);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(DiagnosticsHandler diagnosticsHandler, com.android.tools.r8.naming.mappinginformation.b bVar) {
        if (bVar.s().isUnknown()) {
            diagnosticsHandler.warning(RetraceUnknownMapVersionDiagnostic.create(bVar.b));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ RetraceFailedException a(String str, Throwable th, Boolean bool) {
        return new RetraceFailedException(str, th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(DiagnosticsHandler diagnosticsHandler, List list) {
        try {
            PrintStream printStream = new PrintStream((OutputStream) System.out, true, AbstractC3120yb.a.name());
            try {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    printStream.println((String) it.next());
                }
                printStream.close();
            } catch (Throwable th) {
                try {
                    printStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (UnsupportedEncodingException e) {
            diagnosticsHandler.error(new StringDiagnostic(e.getMessage()));
        }
    }
}
