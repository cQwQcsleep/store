package com.sun.tools.javac.util;

import java.util.HashMap;
import java.util.Map;
import javax.tools.JavaFileObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class AbstractLog {
    public final JCDiagnostic.Factory diags;
    protected DiagnosticSource source;
    protected Map<JavaFileObject, DiagnosticSource> sourceMap = new HashMap();

    public AbstractLog(JCDiagnostic.Factory factory) {
        this.diags = factory;
    }

    private JCDiagnostic.DiagnosticPosition wrap(int i) {
        if (i == -1) {
            return null;
        }
        return new JCDiagnostic.SimpleDiagnosticPosition(i);
    }

    public DiagnosticSource currentSource() {
        return this.source;
    }

    public abstract void directError(String str, Object... objArr);

    public void error(int i, JCDiagnostic.Error error) {
        report(this.diags.error(null, this.source, wrap(i), error));
    }

    public DiagnosticSource getSource(JavaFileObject javaFileObject) {
        if (javaFileObject == null) {
            return DiagnosticSource.NO_SOURCE;
        }
        DiagnosticSource diagnosticSource = this.sourceMap.get(javaFileObject);
        if (diagnosticSource != null) {
            return diagnosticSource;
        }
        DiagnosticSource diagnosticSource2 = new DiagnosticSource(javaFileObject, this);
        this.sourceMap.put(javaFileObject, diagnosticSource2);
        return diagnosticSource2;
    }

    public void note(int i, JCDiagnostic.Note note) {
        report(this.diags.note((JCDiagnostic.DiagnosticFlag) null, this.source, wrap(i), note));
    }

    public abstract void report(JCDiagnostic jCDiagnostic);

    public JavaFileObject useSource(JavaFileObject javaFileObject) {
        DiagnosticSource diagnosticSource = this.source;
        JavaFileObject file = diagnosticSource == null ? null : diagnosticSource.getFile();
        this.source = getSource(javaFileObject);
        return file;
    }

    public void warning(int i, JCDiagnostic.Warning warning) {
        report(this.diags.warning(null, this.source, wrap(i), warning));
    }

    public void error(JCDiagnostic.Error error) {
        report(this.diags.error(null, this.source, null, error));
    }

    public void note(JCDiagnostic.DiagnosticPosition diagnosticPosition, JCDiagnostic.Note note) {
        report(this.diags.note((JCDiagnostic.DiagnosticFlag) null, this.source, diagnosticPosition, note));
    }

    public void warning(JCDiagnostic.DiagnosticPosition diagnosticPosition, JCDiagnostic.Warning warning) {
        report(this.diags.warning(null, this.source, diagnosticPosition, warning));
    }

    public void error(JCDiagnostic.DiagnosticPosition diagnosticPosition, JCDiagnostic.Error error) {
        report(this.diags.error(null, this.source, diagnosticPosition, error));
    }

    public void note(JCDiagnostic.DiagnosticFlag diagnosticFlag, JCDiagnostic.DiagnosticPosition diagnosticPosition, JCDiagnostic.Note note) {
        report(this.diags.note(diagnosticFlag, this.source, diagnosticPosition, note));
    }

    public void warning(JCDiagnostic.DiagnosticFlag diagnosticFlag, JCDiagnostic.DiagnosticPosition diagnosticPosition, JCDiagnostic.Warning warning) {
        report(this.diags.warning(diagnosticFlag, this.source, diagnosticPosition, warning));
    }

    public void error(JCDiagnostic.DiagnosticFlag diagnosticFlag, JCDiagnostic.DiagnosticPosition diagnosticPosition, JCDiagnostic.Error error) {
        report(this.diags.error(diagnosticFlag, this.source, diagnosticPosition, error));
    }

    public void note(JCDiagnostic.Note note) {
        report(this.diags.note((JCDiagnostic.DiagnosticFlag) null, this.source, (JCDiagnostic.DiagnosticPosition) null, note));
    }

    public void warning(JCDiagnostic.Warning warning) {
        report(this.diags.warning(null, this.source, null, warning));
    }

    public void error(int i, String str, Object... objArr) {
        error(i, this.diags.errorKey(str, objArr));
    }

    public void note(JavaFileObject javaFileObject, JCDiagnostic.Note note) {
        report(this.diags.note((JCDiagnostic.DiagnosticFlag) null, getSource(javaFileObject), (JCDiagnostic.DiagnosticPosition) null, note));
    }

    public void error(String str, Object... objArr) {
        error(this.diags.errorKey(str, objArr));
    }

    public void error(JCDiagnostic.DiagnosticFlag diagnosticFlag, int i, JCDiagnostic.Error error) {
        report(this.diags.error(diagnosticFlag, this.source, wrap(i), error));
    }
}
