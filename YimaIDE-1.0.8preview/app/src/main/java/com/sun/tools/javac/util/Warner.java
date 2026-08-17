package com.sun.tools.javac.util;

import com.sun.tools.javac.code.Lint;
import java.util.EnumSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Warner {
    private EnumSet<Lint.LintCategory> nonSilentLintSet;
    private JCDiagnostic.DiagnosticPosition pos;
    private EnumSet<Lint.LintCategory> silentLintSet;
    protected boolean warned;

    public Warner(JCDiagnostic.DiagnosticPosition diagnosticPosition) {
        this.pos = null;
        this.warned = false;
        this.nonSilentLintSet = Lint.LintCategory.newEmptySet();
        this.silentLintSet = Lint.LintCategory.newEmptySet();
        this.pos = diagnosticPosition;
    }

    public void clear() {
        this.nonSilentLintSet.clear();
        this.silentLintSet.clear();
        this.warned = false;
    }

    public boolean hasLint(Lint.LintCategory lintCategory) {
        return hasSilentLint(lintCategory) || hasNonSilentLint(lintCategory);
    }

    public boolean hasNonSilentLint(Lint.LintCategory lintCategory) {
        return this.nonSilentLintSet.contains(lintCategory);
    }

    public boolean hasSilentLint(Lint.LintCategory lintCategory) {
        return this.silentLintSet.contains(lintCategory);
    }

    public JCDiagnostic.DiagnosticPosition pos() {
        return this.pos;
    }

    public void silentWarn(Lint.LintCategory lintCategory) {
        this.silentLintSet.add(lintCategory);
    }

    public void warn(Lint.LintCategory lintCategory) {
        this.nonSilentLintSet.add(lintCategory);
    }

    public Warner() {
        this(null);
    }
}
