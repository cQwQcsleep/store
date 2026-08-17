package com.sun.tools.javac.parser;

import com.sun.source.doctree.DocCommentTree;
import com.sun.tools.javac.tree.DocCommentTable;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.DiagnosticSource;
import java.util.HashMap;
import java.util.Map;
import javax.lang.model.util.Elements;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class LazyDocCommentTable implements DocCommentTable {
    private final DiagnosticSource diagSource;
    private final ParserFactory fac;
    private final Map<JCTree, Entry> table = new HashMap();

    /* JADX INFO: renamed from: com.sun.tools.javac.parser.LazyDocCommentTable$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$parser$Tokens$Comment$CommentStyle;

        static {
            int[] iArr = new int[Tokens.Comment.CommentStyle.values().length];
            $SwitchMap$com$sun$tools$javac$parser$Tokens$Comment$CommentStyle = iArr;
            try {
                iArr[Tokens.Comment.CommentStyle.JAVADOC_BLOCK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$Comment$CommentStyle[Tokens.Comment.CommentStyle.JAVADOC_LINE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static class Entry {
        final Tokens.Comment comment;
        DocCommentTree tree;

        public Entry(Tokens.Comment comment) {
            this.comment = comment;
        }
    }

    public LazyDocCommentTable(ParserFactory parserFactory) {
        this.fac = parserFactory;
        this.diagSource = parserFactory.log.currentSource();
    }

    @Override // com.sun.tools.javac.tree.DocCommentTable
    public Tokens.Comment getComment(JCTree jCTree) {
        Entry entry = this.table.get(jCTree);
        if (entry == null) {
            return null;
        }
        return entry.comment;
    }

    @Override // com.sun.tools.javac.tree.DocCommentTable
    public Elements.DocCommentKind getCommentKind(JCTree jCTree) {
        Tokens.Comment comment = getComment(jCTree);
        if (comment == null) {
            return null;
        }
        int i = AnonymousClass1.$SwitchMap$com$sun$tools$javac$parser$Tokens$Comment$CommentStyle[comment.getStyle().ordinal()];
        if (i == 1) {
            return Elements.DocCommentKind.TRADITIONAL;
        }
        if (i == 2) {
            return Elements.DocCommentKind.END_OF_LINE;
        }
        dwe.a(comment.getStyle());
        return null;
    }

    @Override // com.sun.tools.javac.tree.DocCommentTable
    public String getCommentText(JCTree jCTree) {
        Tokens.Comment comment = getComment(jCTree);
        if (comment == null) {
            return null;
        }
        return comment.getText();
    }

    @Override // com.sun.tools.javac.tree.DocCommentTable
    public DocCommentTree getCommentTree(JCTree jCTree) {
        Entry entry = this.table.get(jCTree);
        if (entry == null) {
            return null;
        }
        if (entry.tree == null) {
            entry.tree = this.fac.getTrees().getDocCommentTree(this.diagSource, entry.comment);
        }
        return entry.tree;
    }

    @Override // com.sun.tools.javac.tree.DocCommentTable
    public boolean hasComment(JCTree jCTree) {
        return this.table.containsKey(jCTree);
    }

    @Override // com.sun.tools.javac.tree.DocCommentTable
    public void putComment(JCTree jCTree, Tokens.Comment comment) {
        this.table.put(jCTree, new Entry(comment));
    }
}
