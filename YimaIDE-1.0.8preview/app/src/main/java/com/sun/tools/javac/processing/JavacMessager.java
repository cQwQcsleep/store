package com.sun.tools.javac.processing;

import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Pair;
import javax.annotation.processing.Messager;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class JavacMessager implements Messager {
    Log log;
    JavacProcessingEnvironment processingEnv;
    int errorCount = 0;
    int warningCount = 0;

    /* JADX INFO: renamed from: com.sun.tools.javac.processing.JavacMessager$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$javax$tools$Diagnostic$Kind;

        static {
            int[] iArr = new int[Diagnostic.Kind.values().length];
            $SwitchMap$javax$tools$Diagnostic$Kind = iArr;
            try {
                iArr[Diagnostic.Kind.ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$javax$tools$Diagnostic$Kind[Diagnostic.Kind.WARNING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$javax$tools$Diagnostic$Kind[Diagnostic.Kind.MANDATORY_WARNING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public JavacMessager(Context context, JavacProcessingEnvironment javacProcessingEnvironment) {
        this.log = Log.instance(context);
        this.processingEnv = javacProcessingEnvironment;
    }

    public int errorCount() {
        return this.errorCount;
    }

    public boolean errorRaised() {
        return this.errorCount > 0;
    }

    public void newRound() {
        this.errorCount = 0;
    }

    public void printError(String str) {
        printMessage(Diagnostic.Kind.ERROR, str);
    }

    @Override // javax.annotation.processing.Messager
    public void printMessage(Diagnostic.Kind kind, CharSequence charSequence, Element element, AnnotationMirror annotationMirror, AnnotationValue annotationValue) {
        JavaFileObject javaFileObject;
        JavaFileObject javaFileObject2;
        Pair<JCTree, JCTree.JCCompilationUnit> treeAndTopLevel = this.processingEnv.getElementUtils().getTreeAndTopLevel(element, annotationMirror, annotationValue);
        JCDiagnostic.DiagnosticPosition diagnosticPositionPos = null;
        if (treeAndTopLevel != null) {
            javaFileObject2 = treeAndTopLevel.snd.sourcefile;
            if (javaFileObject2 != null) {
                JavaFileObject javaFileObjectUseSource = this.log.useSource(javaFileObject2);
                diagnosticPositionPos = treeAndTopLevel.fst.pos();
                javaFileObject = javaFileObjectUseSource;
            } else {
                javaFileObject = null;
            }
        } else {
            javaFileObject = null;
            javaFileObject2 = null;
        }
        try {
            int i = AnonymousClass1.$SwitchMap$javax$tools$Diagnostic$Kind[kind.ordinal()];
            if (i == 1) {
                this.errorCount++;
                this.log.error(JCDiagnostic.DiagnosticFlag.API, diagnosticPositionPos, CompilerProperties.Errors.ProcMessager(charSequence.toString()));
            } else if (i == 2) {
                this.warningCount++;
                this.log.warning(diagnosticPositionPos, CompilerProperties.Warnings.ProcMessager(charSequence.toString()));
            } else if (i != 3) {
                this.log.note(diagnosticPositionPos, CompilerProperties.Notes.ProcMessager(charSequence.toString()));
            } else {
                this.warningCount++;
                this.log.warning(JCDiagnostic.DiagnosticFlag.MANDATORY, diagnosticPositionPos, CompilerProperties.Warnings.ProcMessager(charSequence.toString()));
            }
        } finally {
            if (javaFileObject2 != null) {
                this.log.useSource(javaFileObject);
            }
        }
    }

    public void printNotice(String str) {
        printMessage(Diagnostic.Kind.NOTE, str);
    }

    public void printWarning(String str) {
        printMessage(Diagnostic.Kind.WARNING, str);
    }

    public String toString() {
        return "javac Messager";
    }

    public int warningCount() {
        return this.warningCount;
    }

    @Override // javax.annotation.processing.Messager
    public void printMessage(Diagnostic.Kind kind, CharSequence charSequence, Element element) {
        printMessage(kind, charSequence, element, null, null);
    }

    @Override // javax.annotation.processing.Messager
    public void printMessage(Diagnostic.Kind kind, CharSequence charSequence, Element element, AnnotationMirror annotationMirror) {
        printMessage(kind, charSequence, element, annotationMirror, null);
    }

    @Override // javax.annotation.processing.Messager
    public void printMessage(Diagnostic.Kind kind, CharSequence charSequence) {
        printMessage(kind, charSequence, null, null, null);
    }
}
