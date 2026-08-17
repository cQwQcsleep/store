package javax.tools;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface DiagnosticListener<S> {
    void report(Diagnostic<? extends S> diagnostic);
}
