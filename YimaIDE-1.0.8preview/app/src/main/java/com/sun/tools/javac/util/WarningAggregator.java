package com.sun.tools.javac.util;

import com.sun.tools.javac.code.Lint;
import com.sun.tools.javac.code.Source;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.tools.JavaFileObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class WarningAggregator {
    private boolean anyWarningEmitted;
    private Object deferredDiagnosticArg;
    private DeferredDiagnosticKind deferredDiagnosticKind;
    private JavaFileObject deferredDiagnosticSource;
    private final Lint.LintCategory lintCategory;
    private final Log log;
    private final String prefix;
    private final Source source;
    private Set<JavaFileObject> sourcesWithReportedWarnings;

    public enum DeferredDiagnosticKind {
        IN_FILE(".filename"),
        ADDITIONAL_IN_FILE(".filename.additional"),
        IN_FILES(".plural"),
        ADDITIONAL_IN_FILES(".plural.additional");

        private final String value;

        DeferredDiagnosticKind(String str) {
            this.value = str;
        }

        public String getKey(String str) {
            return str + this.value;
        }
    }

    public WarningAggregator(Log log, Source source, Lint.LintCategory lintCategory, String str) {
        this.log = log;
        this.source = source;
        this.prefix = str == null ? lintCategory.option : str;
        this.lintCategory = lintCategory;
    }

    private void addNote(java.util.List<JCDiagnostic> list, JavaFileObject javaFileObject, String str, Object... objArr) {
        Log log = this.log;
        list.add(log.diags.note(JCDiagnostic.DiagnosticFlag.MANDATORY, log.getSource(javaFileObject), (JCDiagnostic.DiagnosticPosition) null, new JCDiagnostic.Note(null, "compiler", str, objArr)));
    }

    public boolean aggregate(JCDiagnostic jCDiagnostic, boolean z) {
        Assert.check(jCDiagnostic.getLintCategory() == this.lintCategory);
        JavaFileObject javaFileObjectCurrentSourceFile = this.log.currentSourceFile();
        if (z) {
            if (this.sourcesWithReportedWarnings == null) {
                this.sourcesWithReportedWarnings = new HashSet();
            }
            Log log = this.log;
            if (log.nwarnings < log.MaxWarnings) {
                this.sourcesWithReportedWarnings.add(javaFileObjectCurrentSourceFile);
                this.anyWarningEmitted = true;
                return true;
            }
            DeferredDiagnosticKind deferredDiagnosticKind = this.deferredDiagnosticKind;
            if (deferredDiagnosticKind == null) {
                if (this.sourcesWithReportedWarnings.contains(javaFileObjectCurrentSourceFile)) {
                    this.deferredDiagnosticKind = DeferredDiagnosticKind.ADDITIONAL_IN_FILE;
                } else {
                    this.deferredDiagnosticKind = DeferredDiagnosticKind.IN_FILE;
                }
                this.deferredDiagnosticSource = javaFileObjectCurrentSourceFile;
                this.deferredDiagnosticArg = javaFileObjectCurrentSourceFile;
            } else if ((deferredDiagnosticKind == DeferredDiagnosticKind.IN_FILE || deferredDiagnosticKind == DeferredDiagnosticKind.ADDITIONAL_IN_FILE) && !Objects.equals(this.deferredDiagnosticSource, javaFileObjectCurrentSourceFile)) {
                this.deferredDiagnosticKind = DeferredDiagnosticKind.ADDITIONAL_IN_FILES;
                this.deferredDiagnosticArg = null;
            }
        } else {
            DeferredDiagnosticKind deferredDiagnosticKind2 = this.deferredDiagnosticKind;
            if (deferredDiagnosticKind2 == null) {
                this.deferredDiagnosticKind = DeferredDiagnosticKind.IN_FILE;
                this.deferredDiagnosticSource = javaFileObjectCurrentSourceFile;
                this.deferredDiagnosticArg = javaFileObjectCurrentSourceFile;
            } else if (deferredDiagnosticKind2 == DeferredDiagnosticKind.IN_FILE && !Objects.equals(this.deferredDiagnosticSource, javaFileObjectCurrentSourceFile)) {
                this.deferredDiagnosticKind = DeferredDiagnosticKind.IN_FILES;
                this.deferredDiagnosticArg = null;
            }
        }
        return false;
    }

    public java.util.List<JCDiagnostic> aggregationNotes() {
        ArrayList arrayList = new ArrayList(2);
        DeferredDiagnosticKind deferredDiagnosticKind = this.deferredDiagnosticKind;
        if (deferredDiagnosticKind != null) {
            Object obj = this.deferredDiagnosticArg;
            Source source = this.source;
            if (obj == null) {
                JavaFileObject javaFileObject = this.deferredDiagnosticSource;
                if (source != null) {
                    addNote(arrayList, javaFileObject, deferredDiagnosticKind.getKey(this.prefix), this.source);
                } else {
                    addNote(arrayList, javaFileObject, deferredDiagnosticKind.getKey(this.prefix), new Object[0]);
                }
            } else {
                JavaFileObject javaFileObject2 = this.deferredDiagnosticSource;
                if (source != null) {
                    addNote(arrayList, javaFileObject2, deferredDiagnosticKind.getKey(this.prefix), this.deferredDiagnosticArg, this.source);
                } else {
                    addNote(arrayList, javaFileObject2, deferredDiagnosticKind.getKey(this.prefix), this.deferredDiagnosticArg);
                }
            }
            if (!this.anyWarningEmitted) {
                addNote(arrayList, this.deferredDiagnosticSource, this.prefix + ".recompile", new Object[0]);
            }
        }
        return arrayList;
    }

    public void clear() {
        this.sourcesWithReportedWarnings = null;
        this.deferredDiagnosticKind = null;
        this.deferredDiagnosticSource = null;
        this.deferredDiagnosticArg = null;
    }

    public WarningAggregator(Log log, Source source, Lint.LintCategory lintCategory) {
        this(log, source, lintCategory, null);
    }
}
