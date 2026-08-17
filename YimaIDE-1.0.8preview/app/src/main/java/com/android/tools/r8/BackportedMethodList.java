package com.android.tools.r8;

import com.android.tools.r8.BackportedMethodList;
import com.android.tools.r8.CompilationFailedException;
import com.android.tools.r8.internal.AbstractC2632so;
import com.android.tools.r8.internal.InterfaceC2718to;
import com.android.tools.r8.internal.Wf0;
import com.android.tools.r8.ir.desugar.desugaredlibrary.lint.DesugaredMethodsList;
import com.android.tools.r8.ir.desugar.desugaredlibrary.lint.DesugaredMethodsListCommand;
import com.android.tools.r8.origin.Origin;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class BackportedMethodList {
    static final String a = Wf0.a("Usage: BackportedMethodList [options]", " Options are:", "  --output <file>          # Output result in <file>.", "  --min-api <number>       # Minimum Android API level for the application", "  --desugared-lib <file>   # Desugared library configuration (JSON from the", "                           # configuration)", "  --lib <file>             # The compilation SDK library (android.jar)", "  --android-platform-build # Compilation of platform code", "  --version                # Print the version of BackportedMethodList.", "  --help                   # Print this message.");

    public static void main(final String[] strArr) {
        AbstractC2632so.a(new InterfaceC2718to() { // from class: qm0
            @Override // com.android.tools.r8.internal.InterfaceC2718to
            public final void run() throws CompilationFailedException {
                BackportedMethodList.run(strArr);
            }
        });
    }

    public static void run(BackportedMethodListCommand backportedMethodListCommand) throws CompilationFailedException {
        if (backportedMethodListCommand.isPrintHelp()) {
            System.out.println(a);
            return;
        }
        if (backportedMethodListCommand.isPrintVersion()) {
            System.out.println("BackportedMethodList " + Version.getVersionString());
            return;
        }
        DesugaredMethodsListCommand.Builder builder = DesugaredMethodsListCommand.builder(backportedMethodListCommand.b());
        Iterator<ClassFileResourceProvider> it = backportedMethodListCommand.a().h().iterator();
        while (it.hasNext()) {
            builder.addLibrary(it.next());
        }
        String strB = backportedMethodListCommand.getDesugaredLibraryConfiguration().b();
        if (strB != null) {
            builder.setDesugarLibrarySpecification(t0.a(strB, Origin.unknown()));
        }
        if (backportedMethodListCommand.isAndroidPlatformBuild()) {
            builder.setAndroidPlatformBuild();
        }
        DesugaredMethodsList.run(builder.setMinApi(backportedMethodListCommand.getMinApiLevel()).setOutputConsumer(backportedMethodListCommand.getBackportedMethodListConsumer()).build());
    }

    public static void run(String[] strArr) throws CompilationFailedException {
        run(BackportedMethodListCommand.parse(strArr).build());
    }
}
