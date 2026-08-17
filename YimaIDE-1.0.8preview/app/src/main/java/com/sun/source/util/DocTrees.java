package com.sun.source.util;

import com.sun.source.doctree.DocCommentTree;
import com.sun.source.doctree.DocTree;
import com.sun.source.doctree.EntityTree;
import com.sun.source.tree.CompilationUnitTree;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.List;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.JavaCompiler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class DocTrees extends Trees {
    public static DocTrees instance(ProcessingEnvironment processingEnvironment) {
        if (processingEnvironment.getClass().getName().equals("com.sun.tools.javac.processing.JavacProcessingEnvironment")) {
            return (DocTrees) Trees.getJavacTrees(ProcessingEnvironment.class, processingEnvironment);
        }
        j2d.a();
        return null;
    }

    public abstract BreakIterator getBreakIterator();

    public abstract String getCharacters(EntityTree entityTree);

    public abstract Elements.DocCommentKind getDocCommentKind(TreePath treePath);

    public abstract DocCommentTree getDocCommentTree(TreePath treePath);

    public abstract DocCommentTree getDocCommentTree(Element element);

    public abstract DocCommentTree getDocCommentTree(Element element, String str) throws IOException;

    public abstract DocCommentTree getDocCommentTree(FileObject fileObject);

    public abstract DocTreeFactory getDocTreeFactory();

    public abstract DocTreePath getDocTreePath(FileObject fileObject, PackageElement packageElement);

    public abstract Element getElement(DocTreePath docTreePath);

    public abstract List<DocTree> getFirstSentence(List<? extends DocTree> list);

    @Override // com.sun.source.util.Trees
    public abstract DocSourcePositions getSourcePositions();

    public abstract TypeMirror getType(DocTreePath docTreePath);

    public abstract void printMessage(Diagnostic.Kind kind, CharSequence charSequence, DocTree docTree, DocCommentTree docCommentTree, CompilationUnitTree compilationUnitTree);

    public abstract void setBreakIterator(BreakIterator breakIterator);

    public static DocTrees instance(JavaCompiler.CompilationTask compilationTask) {
        return (DocTrees) Trees.instance(compilationTask);
    }
}
