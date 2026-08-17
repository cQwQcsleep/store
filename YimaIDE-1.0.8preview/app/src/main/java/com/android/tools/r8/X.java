package com.android.tools.r8;

import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.AbstractC1883k4;
import com.android.tools.r8.internal.AbstractC2379pq;
import com.android.tools.r8.internal.AbstractC2554rv;
import com.android.tools.r8.internal.AbstractC2653t4;
import com.android.tools.r8.internal.Cc0;
import com.android.tools.r8.internal.Wf0;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.utils.StringDiagnostic;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class X extends AbstractC3309m {
    public static final AbstractC2554rv b = AbstractC2554rv.a("--output", "--lib", "--min-api", "--desugared-lib", "--thread-count", "--pg-conf", "--pg-map-output", "--partition-map-output", "--art-profile");
    public static final Cc0 c = new Cc0("--art-profile");

    public final L8Command.Builder a(String[] strArr, Origin origin, final L8Command.Builder builder) {
        int i;
        String str;
        String str2;
        OutputMode outputMode = OutputMode.DexIndexed;
        C3360p.a aVarA = C3360p.a();
        Objects.requireNonNull(builder);
        String[] strArrA = AbstractC2379pq.a(strArr, new Consumer() { // from class: lvf
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                builder.error((Diagnostic) obj);
            }
        });
        boolean z = false;
        CompilationMode compilationMode = null;
        Path path = null;
        for (int i2 = 0; i2 < strArrA.length; i2 = i + 1) {
            String strTrim = strArrA[i2].trim();
            if (b.contains(strTrim)) {
                i = i2 + 1;
                if (i >= strArrA.length) {
                    builder.error(new StringDiagnostic("Missing parameter for " + strArrA[i2] + ".", origin));
                    break;
                }
                str2 = strArrA[i];
                if (c.contains(strTrim)) {
                    int i3 = i2 + 2;
                    if (i3 >= strArrA.length) {
                        builder.error(new StringDiagnostic("Missing parameter for " + strArrA[i2] + ".", origin));
                        break;
                    }
                    str = strArrA[i3];
                    i = i3;
                } else {
                    str = null;
                }
            } else {
                i = i2;
                str = null;
                str2 = null;
            }
            if (strTrim.length() != 0) {
                if (strTrim.equals("--help")) {
                    builder.setPrintHelp(true);
                } else if (strTrim.equals("--version")) {
                    builder.setPrintVersion(true);
                } else if (strTrim.equals("--debug")) {
                    if (compilationMode == CompilationMode.RELEASE) {
                        builder.error(new StringDiagnostic("Cannot compile in both --debug and --release mode.", origin));
                    } else {
                        compilationMode = CompilationMode.DEBUG;
                    }
                } else if (strTrim.equals("--release")) {
                    if (compilationMode == CompilationMode.DEBUG) {
                        builder.error(new StringDiagnostic("Cannot compile in both --debug and --release mode.", origin));
                    } else {
                        compilationMode = CompilationMode.RELEASE;
                    }
                } else if (strTrim.equals("--output")) {
                    if (path != null) {
                        builder.error(new StringDiagnostic("Cannot output both to '" + path.toString() + "' and '" + str2 + "'", origin));
                    } else {
                        path = Paths.get(str2, new String[0]);
                    }
                } else if (strTrim.equals("--min-api")) {
                    if (z) {
                        builder.error(new StringDiagnostic("Cannot set multiple --min-api options", origin));
                    } else {
                        AbstractC3309m.a(new Consumer() { // from class: lvf
                            @Override // java.util.function.Consumer
                            public final void accept(Object obj) {
                                builder.error((Diagnostic) obj);
                            }
                        }, "--min-api", str2, origin, new Consumer() { // from class: mvf
                            @Override // java.util.function.Consumer
                            public final void accept(Object obj) {
                                builder.setMinApiLevel(((Integer) obj).intValue());
                            }
                        });
                        z = true;
                    }
                } else if (strTrim.equals("--lib")) {
                    AbstractC3309m.a(builder, origin, str2);
                } else if (strTrim.equals("--pg-conf")) {
                    builder.addProguardConfigurationFiles(Paths.get(str2, new String[0]));
                } else if (strTrim.equals("--pg-map-output")) {
                    builder.setProguardMapOutputPath(Paths.get(str2, new String[0]));
                } else if (strTrim.equals("--partition-map-output")) {
                    builder.setPartitionMapOutputPath(Paths.get(str2, new String[0]));
                } else if (strTrim.equals("--desugared-lib")) {
                    builder.addDesugaredLibraryConfiguration(t0.a(Paths.get(str2, new String[0])));
                } else if (strTrim.equals("--classfile")) {
                    outputMode = OutputMode.ClassFile;
                } else if (strTrim.equals("--art-profile")) {
                    builder.addArtProfileForRewriting(AbstractC2653t4.a(Paths.get(str2, new String[0])), AbstractC1883k4.a(Paths.get(str, new String[0])));
                } else if (strTrim.equals("--thread-count")) {
                    AbstractC3309m.a(new Consumer() { // from class: lvf
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            builder.error((Diagnostic) obj);
                        }
                    }, "--thread-count", str2, origin, new Consumer() { // from class: nvf
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            builder.a(((Integer) obj).intValue());
                        }
                    });
                } else if (!strTrim.startsWith("--")) {
                    builder.addProgramFiles(Paths.get(strTrim, new String[0]));
                } else if (!a(builder, strTrim, origin)) {
                    int iB = AbstractC3309m.b(builder, strTrim, strArrA, i, origin);
                    if (iB < 0 && (iB = AbstractC3309m.a(builder, strTrim, strArrA, i, origin)) < 0) {
                        builder.error(new StringDiagnostic("Unknown option: ".concat(strTrim), origin));
                    } else {
                        i += iB;
                    }
                }
            }
        }
        if (!aVarA.b()) {
            builder.addClasspathResourceProvider(aVarA.a());
        }
        if (compilationMode != null) {
            builder.setMode(compilationMode);
        }
        if (path == null) {
            path = Paths.get(".", new String[0]);
        }
        return builder.setOutput(path, outputMode);
    }

    public static String a() {
        StringBuilder sb = new StringBuilder();
        Wf0.a(sb, "Usage: l8 [options] <input-files>", " where <input-files> are any combination class, zip, or jar files", " where <input-files> are any combination of dex, class, zip, jar, or apk files", " and options are:");
        new ParseFlagPrinter().addFlags(AbstractC0551Hu.g().a(a0.a(true)).a(a0.b(false)).a(a0.a("--output", Collections.singletonList("<file>"), Arrays.asList("Output result in <file>.", "<file> must be an existing directory or a zip file."))).a(a0.a("--lib", Collections.singletonList("<file|jdk-home>"), Arrays.asList("Add <file|jdk-home> as a library resource."))).a(a0.e()).a(a0.a("--pg-conf", Collections.singletonList("<file>"), Arrays.asList("Proguard configuration <file>."))).a(a0.a("--pg-map-output", Collections.singletonList("<file>"), Arrays.asList("Output the resulting name and line mapping to <file>."))).a(a0.a("--partition-map-output", Collections.singletonList("<file>"), Arrays.asList("Output the resulting mapping to <file>."))).a(a0.a("--desugared-lib", Collections.singletonList("<file>"), Arrays.asList("Specify desugared library configuration.", "<file> is a desugared library configuration (json)."))).b((Iterable) a0.a()).a(a0.a("--thread-count", Collections.singletonList("<number>"), Arrays.asList("Use <number> of threads for compilation.", "If not specified the number will be based on", "heuristics taking the number of cores into account."))).a(a0.d()).a(a0.a("--art-profile", AbstractC0551Hu.a("<input>", "<output>"), Arrays.asList("Rewrite human readable ART profile read from <input> and write to <output>."))).a(a0.a("l8")).a(a0.c()).a()).appendLinesToBuilder(sb);
        return sb.toString();
    }
}
