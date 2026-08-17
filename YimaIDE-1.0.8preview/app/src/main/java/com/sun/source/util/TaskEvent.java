package com.sun.source.util;

import com.sun.source.tree.CompilationUnitTree;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class TaskEvent {
    private TypeElement clazz;
    private JavaFileObject file;
    private Kind kind;
    private CompilationUnitTree unit;

    public enum Kind {
        PARSE,
        ENTER,
        ANALYZE,
        GENERATE,
        ANNOTATION_PROCESSING,
        ANNOTATION_PROCESSING_ROUND,
        COMPILATION
    }

    private TaskEvent(Kind kind, JavaFileObject javaFileObject, CompilationUnitTree compilationUnitTree, TypeElement typeElement) {
        this.kind = kind;
        this.file = javaFileObject;
        this.unit = compilationUnitTree;
        this.clazz = typeElement;
    }

    public CompilationUnitTree getCompilationUnit() {
        return this.unit;
    }

    public Kind getKind() {
        return this.kind;
    }

    public JavaFileObject getSourceFile() {
        return this.file;
    }

    public TypeElement getTypeElement() {
        return this.clazz;
    }

    public String toString() {
        return "TaskEvent[" + this.kind + "," + this.file + "," + this.clazz + "]";
    }

    public TaskEvent(Kind kind, JavaFileObject javaFileObject) {
        this(kind, javaFileObject, null, null);
    }

    public TaskEvent(Kind kind, CompilationUnitTree compilationUnitTree) {
        this(kind, compilationUnitTree.getSourceFile(), compilationUnitTree, null);
    }

    public TaskEvent(Kind kind, CompilationUnitTree compilationUnitTree, TypeElement typeElement) {
        this(kind, compilationUnitTree.getSourceFile(), compilationUnitTree, typeElement);
    }

    public TaskEvent(Kind kind) {
        this(kind, null, null, null);
    }
}
