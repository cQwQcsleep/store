package com.android.tools.r8.ir.desugar.desugaredlibrary.lint;

import com.android.tools.r8.ArchiveClassFileProvider;
import com.android.tools.r8.ArchiveProgramResourceProvider;
import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.t0;
import com.android.tools.r8.utils.StringDiagnostic;
import defpackage.x0g;
import java.nio.file.Paths;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class d {
    public static DesugaredMethodsListCommand a(String[] strArr, DiagnosticsHandler diagnosticsHandler) {
        DesugaredMethodsListCommand.Builder builder = DesugaredMethodsListCommand.builder(diagnosticsHandler);
        int i = 0;
        while (i < strArr.length) {
            String strTrim = strArr[i].trim();
            if (strTrim.length() != 0) {
                if (strTrim.equals("--help")) {
                    builder.setHelp();
                } else if (strTrim.equals("--version")) {
                    builder.setVersion();
                } else if (strTrim.equals("--android-platform-build")) {
                    builder.setAndroidPlatformBuild();
                } else {
                    i++;
                    if (i >= strArr.length) {
                        diagnosticsHandler.error(new StringDiagnostic("Missing value for arg ".concat(strTrim)));
                        break;
                    }
                    String strTrim2 = strArr[i].trim();
                    if (strTrim.equals("--min-api")) {
                        builder.setMinApi(Integer.parseInt(strTrim2));
                    } else if (strTrim.equals("--desugared-lib")) {
                        builder.setDesugarLibrarySpecification(t0.a(Paths.get(strTrim2, new String[0])));
                    } else if (strTrim.equals("--desugared-lib-jar")) {
                        builder.addDesugarLibraryImplementation(ArchiveProgramResourceProvider.fromArchive(Paths.get(strTrim2, new String[0])));
                    } else if (strTrim.equals("--output")) {
                        builder.setOutputPath(Paths.get(strTrim2, new String[0]));
                    } else {
                        if (!strTrim.equals("--lib")) {
                            x0g.a("Unsupported argument ".concat(strTrim));
                            return null;
                        }
                        builder.addLibrary(new ArchiveClassFileProvider(Paths.get(strTrim2, new String[0])));
                    }
                }
            }
            i++;
        }
        return builder.build();
    }
}
