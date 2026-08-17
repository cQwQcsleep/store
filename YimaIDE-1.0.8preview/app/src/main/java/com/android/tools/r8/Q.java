package com.android.tools.r8;

import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.AbstractC2379pq;
import com.android.tools.r8.internal.AbstractC2554rv;
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
public final class Q {
    public static final String a = Wf0.b("Usage: globalsyntheticsgenerator [options] where options are:");
    public static final AbstractC2554rv b = AbstractC2554rv.a(3, 3, "--output", "--lib", "--min-api");

    public static GlobalSyntheticsGeneratorCommand.Builder a(String[] strArr, Origin origin, final GlobalSyntheticsGeneratorCommand.Builder builder) {
        int i;
        String str;
        Objects.requireNonNull(builder);
        String[] strArrA = AbstractC2379pq.a(strArr, new Consumer() { // from class: tvb
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                builder.error((Diagnostic) obj);
            }
        });
        Path path = null;
        boolean z = false;
        for (int i2 = 0; i2 < strArrA.length; i2 = i + 1) {
            String strTrim = strArrA[i2].trim();
            if (b.contains(strTrim)) {
                i = i2 + 1;
                if (i >= strArrA.length) {
                    builder.error(new StringDiagnostic("Missing parameter for " + strArrA[i2] + ".", origin));
                    break;
                }
                str = strArrA[i];
            } else {
                i = i2;
                str = null;
            }
            if (strTrim.length() != 0) {
                if (strTrim.equals("--help")) {
                    builder.setPrintHelp(true);
                } else if (strTrim.equals("--version")) {
                    builder.setPrintVersion(true);
                } else if (strTrim.equals("--output")) {
                    if (path != null) {
                        builder.error(new StringDiagnostic("Cannot output both to '" + path + "' and '" + str + "'", origin));
                    } else {
                        path = Paths.get(str, new String[0]);
                    }
                } else if (strTrim.equals("--min-api")) {
                    if (z) {
                        builder.error(new StringDiagnostic("Cannot set multiple --min-api options", origin));
                    } else {
                        AbstractC3309m.a(new Consumer() { // from class: tvb
                            @Override // java.util.function.Consumer
                            public final void accept(Object obj) {
                                builder.error((Diagnostic) obj);
                            }
                        }, "--min-api", str, origin, new Consumer() { // from class: wvb
                            @Override // java.util.function.Consumer
                            public final void accept(Object obj) {
                                builder.setMinApiLevel(((Integer) obj).intValue());
                            }
                        });
                        z = true;
                    }
                } else if (strTrim.equals("--lib")) {
                    builder.addLibraryFiles(Paths.get(str, new String[0]));
                } else if (strTrim.equals("--classfile")) {
                    builder.setClassfileDesugaringOnly(true);
                } else if (strTrim.startsWith("--")) {
                    builder.error(new StringDiagnostic("Unknown option: ".concat(strTrim), origin));
                }
            }
        }
        if (path == null) {
            path = Paths.get(".", new String[0]);
        }
        return builder.setGlobalSyntheticsOutput(path);
    }

    public static String a() {
        StringBuilder sb = new StringBuilder();
        Wf0.a(sb, a);
        new ParseFlagPrinter().addFlags(AbstractC0551Hu.g().a(a0.e()).a(a0.a("--lib", Collections.singletonList("<file|jdk-home>"), Arrays.asList("Add <file|jdk-home> as a library resource."))).a(a0.a("--output", Collections.singletonList("<globals-file>"), Arrays.asList("Output result in <globals-file>."))).a(a0.a("--classfile", Collections.EMPTY_LIST, Arrays.asList("Generate globals for only classfile to classfile desugaring.", "(By default globals for both classfile and dex desugaring are generated)."))).a(a0.a("globalsyntheticsgenerator")).a(a0.c()).a()).appendLinesToBuilder(sb);
        return sb.toString();
    }
}
