package com.sun.source.tree;

import java.util.List;
import javax.tools.JavaFileObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface CompilationUnitTree extends Tree {
    List<? extends ImportTree> getImports();

    LineMap getLineMap();

    default ModuleTree getModule() {
        throw new UnsupportedOperationException();
    }

    PackageTree getPackage();

    List<? extends AnnotationTree> getPackageAnnotations();

    ExpressionTree getPackageName();

    JavaFileObject getSourceFile();

    List<? extends Tree> getTypeDecls();
}
