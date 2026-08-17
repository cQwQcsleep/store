package com.android.tools.r8.errors;

import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.errors.StartupClassesOverflowDiagnostic;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class StartupClassesOverflowDiagnostic implements Diagnostic {
    private final int b;

    public StartupClassesOverflowDiagnostic(int i) {
        this.b = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String a(int i) {
        return "classes" + i + ".dex";
    }

    @Override // com.android.tools.r8.Diagnostic
    public String getDiagnosticMessage() {
        return "Unable to include all startup classes in classes.dex. Startup classes were distributed in: classes.dex, " + ((String) IntStream.range(2, this.b + 1).mapToObj(new IntFunction() { // from class: cmd
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return StartupClassesOverflowDiagnostic.a(i);
            }
        }).collect(Collectors.joining(", "))) + ".";
    }

    @Override // com.android.tools.r8.Diagnostic
    public Origin getOrigin() {
        return Origin.unknown();
    }

    @Override // com.android.tools.r8.Diagnostic
    public Position getPosition() {
        return Position.UNKNOWN;
    }
}
