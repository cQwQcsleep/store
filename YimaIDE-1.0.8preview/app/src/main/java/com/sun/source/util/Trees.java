package com.sun.source.util;

import com.sun.source.tree.CatchTree;
import com.sun.source.tree.ClassTree;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.MethodTree;
import com.sun.source.tree.Scope;
import com.sun.source.tree.Tree;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ErrorType;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import javax.tools.JavaCompiler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Trees {
    public static Trees getJavacTrees(Class<?> cls, Object obj) {
        try {
            ClassLoader classLoader = obj.getClass().getClassLoader();
            return (Trees) Class.forName("com.sun.tools.javac.api.JavacTrees", false, classLoader).getMethod("instance", Class.forName(cls.getName(), false, classLoader)).invoke(null, obj);
        } catch (ReflectiveOperationException e) {
            x01.a(e);
            return null;
        }
    }

    public static Trees instance(JavaCompiler.CompilationTask compilationTask) {
        String name = compilationTask.getClass().getName();
        if (name.equals("com.sun.tools.javac.api.JavacTaskImpl") || name.equals("com.sun.tools.javac.api.BasicJavacTask")) {
            return getJavacTrees(JavaCompiler.CompilationTask.class, compilationTask);
        }
        j2d.a();
        return null;
    }

    public abstract String getDocComment(TreePath treePath);

    public abstract Element getElement(TreePath treePath);

    public abstract TypeMirror getLub(CatchTree catchTree);

    public abstract TypeMirror getOriginalType(ErrorType errorType);

    public abstract TreePath getPath(CompilationUnitTree compilationUnitTree, Tree tree);

    public abstract TreePath getPath(Element element);

    public abstract TreePath getPath(Element element, AnnotationMirror annotationMirror);

    public abstract TreePath getPath(Element element, AnnotationMirror annotationMirror, AnnotationValue annotationValue);

    public abstract Scope getScope(TreePath treePath);

    public abstract SourcePositions getSourcePositions();

    public abstract ClassTree getTree(TypeElement typeElement);

    public abstract MethodTree getTree(ExecutableElement executableElement);

    public abstract Tree getTree(Element element);

    public abstract Tree getTree(Element element, AnnotationMirror annotationMirror);

    public abstract Tree getTree(Element element, AnnotationMirror annotationMirror, AnnotationValue annotationValue);

    public abstract TypeMirror getTypeMirror(TreePath treePath);

    public abstract boolean isAccessible(Scope scope, Element element, DeclaredType declaredType);

    public abstract boolean isAccessible(Scope scope, TypeElement typeElement);

    public abstract void printMessage(Diagnostic.Kind kind, CharSequence charSequence, Tree tree, CompilationUnitTree compilationUnitTree);

    public static Trees instance(ProcessingEnvironment processingEnvironment) {
        if (processingEnvironment.getClass().getName().equals("com.sun.tools.javac.processing.JavacProcessingEnvironment")) {
            return getJavacTrees(ProcessingEnvironment.class, processingEnvironment);
        }
        j2d.a();
        return null;
    }
}
