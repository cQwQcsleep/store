package com.android.tools.r8;

import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;
import java.io.PrintStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface DiagnosticsHandler {
    static void printDiagnosticToStream(Diagnostic diagnostic, String str, PrintStream printStream) {
        if (diagnostic.getOrigin() != Origin.unknown()) {
            printStream.print(str + " in " + diagnostic.getOrigin());
            if (diagnostic.getPosition() != Position.UNKNOWN) {
                printStream.print(" at " + diagnostic.getPosition().getDescription());
            }
            printStream.println(":");
        } else {
            printStream.print(str + ": ");
        }
        printStream.println(diagnostic.getDiagnosticMessage());
    }

    default void error(Diagnostic diagnostic) {
        printDiagnosticToStream(diagnostic, "Error", System.err);
    }

    default void info(Diagnostic diagnostic) {
        printDiagnosticToStream(diagnostic, "Info", System.out);
    }

    default DiagnosticsLevel modifyDiagnosticsLevel(DiagnosticsLevel diagnosticsLevel, Diagnostic diagnostic) {
        return diagnosticsLevel;
    }

    default void warning(Diagnostic diagnostic) {
        printDiagnosticToStream(diagnostic, "Warning", System.err);
    }
}
