package javax.tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class DiagnosticCollector<S> implements DiagnosticListener<S> {
    private List<Diagnostic<? extends S>> diagnostics = Collections.synchronizedList(new ArrayList());

    public List<Diagnostic<? extends S>> getDiagnostics() {
        return Collections.unmodifiableList(this.diagnostics);
    }

    public void report(Diagnostic<? extends S> diagnostic) {
        Objects.requireNonNull(diagnostic);
        this.diagnostics.add(diagnostic);
    }
}
