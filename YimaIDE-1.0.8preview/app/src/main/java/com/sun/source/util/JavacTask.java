package com.sun.source.util;

import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.Tree;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class JavacTask implements JavaCompiler.CompilationTask {
    public static JavacTask instance(ProcessingEnvironment processingEnvironment) {
        if (!processingEnvironment.getClass().getName().equals("com.sun.tools.javac.processing.JavacProcessingEnvironment")) {
            j2d.a();
            return null;
        }
        try {
            return (JavacTask) processingEnvironment.getClass().getMethod("getJavacTask", null).invoke(processingEnvironment, null);
        } catch (IllegalAccessException e) {
            throw new UnsupportedOperationException(e);
        } catch (IllegalArgumentException e2) {
            throw new UnsupportedOperationException(e2);
        } catch (NoSuchMethodException e3) {
            throw new UnsupportedOperationException(e3);
        } catch (SecurityException e4) {
            throw new UnsupportedOperationException(e4);
        } catch (InvocationTargetException e5) {
            throw new UnsupportedOperationException(e5);
        }
    }

    public abstract void addTaskListener(TaskListener taskListener);

    public abstract Iterable<? extends Element> analyze() throws IOException;

    public abstract Iterable<? extends JavaFileObject> generate() throws IOException;

    public abstract Elements getElements();

    public abstract TypeMirror getTypeMirror(Iterable<? extends Tree> iterable);

    public abstract Types getTypes();

    public abstract Iterable<? extends CompilationUnitTree> parse() throws IOException;

    public abstract void removeTaskListener(TaskListener taskListener);

    public void setParameterNameProvider(ParameterNameProvider parameterNameProvider) {
    }

    public abstract void setTaskListener(TaskListener taskListener);
}
