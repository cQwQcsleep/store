package com.android.tools.r8.tracereferences;

import com.android.tools.r8.AbstractC0007c;
import com.android.tools.r8.AbstractC3309m;
import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.JdkClassFileProvider;
import com.android.tools.r8.ParseFlagPrinter;
import com.android.tools.r8.StringConsumer;
import com.android.tools.r8.a0;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.AbstractC2379pq;
import com.android.tools.r8.internal.AbstractC2554rv;
import com.android.tools.r8.internal.Bc0;
import com.android.tools.r8.internal.Wf0;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.s0;
import com.android.tools.r8.utils.ExceptionDiagnostic;
import com.android.tools.r8.utils.StringDiagnostic;
import defpackage.hkh;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class h {
    public static final AbstractC2554rv a = AbstractC2554rv.a(4, 4, "--lib", "--target", "--source", "--output");

    public static TraceReferencesCommand.Builder a(String[] strArr, Origin origin, final TraceReferencesCommand.Builder builder) {
        int i;
        String str;
        Origin origin2;
        Objects.requireNonNull(builder);
        String[] strArrA = AbstractC2379pq.a(strArr, new Consumer() { // from class: fzg
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                builder.a((Diagnostic) obj);
            }
        });
        if (strArrA.length == 0) {
            builder.a(new StringDiagnostic("Missing command"));
            return builder;
        }
        int i2 = 0;
        int i3 = 0;
        boolean z = false;
        Path path = null;
        while (i2 < strArrA.length) {
            String strTrim = strArrA[i2].trim();
            if (a.contains(strTrim)) {
                int i4 = i2 + 1;
                if (i4 >= strArrA.length) {
                    builder.a(new StringDiagnostic("Missing parameter for " + strArrA[i2] + ".", origin));
                    break;
                }
                str = strArrA[i4];
                i = i4;
            } else {
                i = i2;
                str = null;
            }
            if (strTrim.length() == 0) {
                origin2 = origin;
            } else {
                if (strTrim.equals("--help")) {
                    builder.setPrintHelp(true);
                    return builder;
                }
                if (strTrim.equals("--version")) {
                    builder.setPrintVersion(true);
                    return builder;
                }
                if (strTrim.equals("--check")) {
                    if (i3 != 0) {
                        builder.a(new StringDiagnostic("Multiple commands specified", origin));
                    }
                    origin2 = origin;
                    i3 = 1;
                } else if (strTrim.equals("--keep-rules")) {
                    if (i3 != 0) {
                        builder.a(new StringDiagnostic("Multiple commands specified", origin));
                    }
                    origin2 = origin;
                    i3 = 2;
                } else if (strTrim.equals("--allowobfuscation")) {
                    origin2 = origin;
                    z = true;
                } else {
                    if (strTrim.equals("--lib")) {
                        Path path2 = Paths.get(str, new String[0]);
                        if (Files.exists(path2.resolve("lib").resolve("jrt-fs.jar"), new LinkOption[0]) || Files.exists(path2.resolve("jre").resolve("lib").resolve("rt.jar"), new LinkOption[0]) || Files.exists(path2.resolve("lib").resolve("rt.jar"), new LinkOption[0])) {
                            try {
                                builder.addLibraryResourceProvider(JdkClassFileProvider.fromJdkHome(path2));
                            } catch (IOException e) {
                                builder.a(new ExceptionDiagnostic(e, origin));
                            }
                        } else {
                            builder.addLibraryFiles(path2);
                        }
                    } else if (strTrim.equals("--target")) {
                        builder.addTargetFiles(Paths.get(str, new String[0]));
                    } else if (strTrim.equals("--source")) {
                        builder.addSourceFiles(Paths.get(str, new String[0]));
                    } else if (strTrim.equals("--output")) {
                        if (path != null) {
                            builder.a(new StringDiagnostic("Option '--output' passed multiple times.", origin));
                        } else {
                            path = Paths.get(str, new String[0]);
                        }
                    } else if (strTrim.startsWith("@")) {
                        builder.a(new StringDiagnostic("Recursive @argfiles are not supported: ", origin));
                    } else {
                        origin2 = origin;
                        int iA = AbstractC3309m.a(new Consumer() { // from class: fzg
                            @Override // java.util.function.Consumer
                            public final void accept(Object obj) {
                                builder.a((Diagnostic) obj);
                            }
                        }, builder.a(), strTrim, strArrA, i, origin2);
                        if (iA >= 0) {
                            i += iA;
                        } else {
                            builder.a(new StringDiagnostic("Unsupported option '" + strTrim + "'", origin2));
                        }
                    }
                    origin2 = origin;
                }
            }
            i2 = i + 1;
            origin = origin2;
        }
        Origin origin3 = origin;
        if (i3 == 0) {
            builder.a(new StringDiagnostic("Missing command, specify one of 'check' or '--keep-rules'", origin3));
            return builder;
        }
        if (i3 == 1 && path != null) {
            builder.a(new StringDiagnostic("Using '--output' requires command '--keep-rules'", origin3));
            return builder;
        }
        if (i3 != 2 && z) {
            builder.a(new StringDiagnostic("Using '--allowobfuscation' requires command '--keep-rules'", origin3));
            return builder;
        }
        int iB = AbstractC0007c.b(i3);
        if (iB == 0) {
            builder.setConsumer(new TraceReferencesCheckConsumer(TraceReferencesConsumer.emptyConsumer()));
        } else {
            if (iB != 1) {
                hkh.a();
                return null;
            }
            builder.setConsumer(new TraceReferencesCheckConsumer(TraceReferencesKeepRules.builder().setAllowObfuscation(z).setOutputConsumer(path != null ? new StringConsumer.FileConsumer(path) : new s0(null, new PrintWriter(System.out))).build()));
        }
        return builder;
    }

    public static String a() {
        StringBuilder sb = new StringBuilder();
        Wf0.a(sb, "Usage: tracereferences <command> [<options>] [@<argfile>]", " Where <command> is one of:");
        ParseFlagPrinter parseFlagPrinter = new ParseFlagPrinter();
        List list = Collections.EMPTY_LIST;
        parseFlagPrinter.addFlags(AbstractC0551Hu.a(a0.a("--check", list, Arrays.asList("Run emitting only diagnostics messages.")), a0.a("--keep-rules", Collections.singletonList("[<keep-rules-options>]"), Arrays.asList("Traced references will be output in the keep-rules", "format.")))).appendLinesToBuilder(sb);
        Wf0.a(sb, " and each <argfile> is a file containing additional options (one per line)", " and options are:");
        new ParseFlagPrinter().addFlags(AbstractC0551Hu.g().a(a0.a("--lib", Collections.singletonList("<file|jdk-home>"), Arrays.asList("Add <file|jdk-home> runtime library."))).a(a0.a("--source", Collections.singletonList("<file>"), Arrays.asList("Add <file> as a source for tracing references."))).a(a0.a("--target", Collections.singletonList("<file>"), Arrays.asList("Add <file> as a target for tracing references. When", "target is not specified all references from source", "outside of library are treated as a missing", "references."))).a(a0.a("--output", Collections.singletonList("<file>"), Arrays.asList("Output result in <outfile>. If not passed the", "result will go to standard out."))).a(a0.d()).a(a0.a("tracereferences")).a(a0.c()).a()).appendLinesToBuilder(sb);
        Wf0.a(sb, " and <keep-rule-options> are:");
        new ParseFlagPrinter().addFlags(new Bc0(a0.a("--allowobfuscation", list, Arrays.asList("Output keep rules with the allowobfuscation", "modifier (defaults to rules without the modifier)")))).appendLinesToBuilder(sb);
        return sb.toString();
    }
}
