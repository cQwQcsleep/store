package com.sun.tools.javac.code;

import com.sun.tools.javac.tree.EndPosTable;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeInfo;
import com.sun.tools.javac.tree.TreeScanner;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.JCDiagnostic;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import javax.tools.JavaFileObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class LintMapper {
    private static final Context.Key<LintMapper> CONTEXT_KEY = new Context.Key<>();
    private final Context context;
    private final Map<JavaFileObject, FileInfo> fileInfoMap = new HashMap();
    private Lint rootLint;

    public static class FileInfo {
        final LintRange rootRange;
        final List<Span> unmappedDecls = new LinkedList();

        public FileInfo(Lint lint, JCTree.JCCompilationUnit jCCompilationUnit) {
            this.rootRange = new LintRange(lint);
            for (JCTree jCTree : jCCompilationUnit.defs) {
                if (isTopLevelDecl(jCTree)) {
                    this.unmappedDecls.add(new Span(jCTree, jCCompilationUnit.endPositions));
                }
            }
        }

        public void afterAttr(JCTree jCTree, EndPosTable endPosTable) {
            Iterator<Span> it = this.unmappedDecls.iterator();
            while (it.hasNext()) {
                if (it.next().contains(jCTree.pos())) {
                    this.rootRange.populateSubtree(jCTree, endPosTable);
                    it.remove();
                    return;
                }
            }
            x01.a("top-level declaration not found");
        }

        public boolean isTopLevelDecl(JCTree jCTree) {
            return jCTree.getTag() == JCTree.Tag.MODULEDEF || jCTree.getTag() == JCTree.Tag.PACKAGEDEF || jCTree.getTag() == JCTree.Tag.CLASSDEF;
        }

        public Optional<Lint> lintAt(JCDiagnostic.DiagnosticPosition diagnosticPosition) {
            Iterator<Span> it = this.unmappedDecls.iterator();
            while (it.hasNext()) {
                if (it.next().contains(diagnosticPosition)) {
                    return Optional.empty();
                }
            }
            return Optional.of(this.rootRange.bestMatch(diagnosticPosition).lint);
        }
    }

    public LintMapper(Context context) {
        context.put(CONTEXT_KEY, this);
        this.context = context;
    }

    private void initializeIfNeeded() {
        if (this.rootLint == null) {
            this.rootLint = Lint.instance(this.context);
        }
    }

    public static LintMapper instance(Context context) {
        LintMapper lintMapper = (LintMapper) context.get(CONTEXT_KEY);
        return lintMapper == null ? new LintMapper(context) : lintMapper;
    }

    public void calculateLints(JavaFileObject javaFileObject, JCTree jCTree, EndPosTable endPosTable) {
        Assert.check(this.rootLint != null);
        this.fileInfoMap.get(javaFileObject).afterAttr(jCTree, endPosTable);
    }

    public void clear() {
        this.fileInfoMap.clear();
    }

    public void finishParsingFile(JCTree.JCCompilationUnit jCCompilationUnit) {
        Assert.check(this.rootLint != null);
        this.fileInfoMap.put(jCCompilationUnit.sourcefile, new FileInfo(this.rootLint, jCCompilationUnit));
    }

    public boolean isKnown(JavaFileObject javaFileObject) {
        return this.fileInfoMap.containsKey(javaFileObject);
    }

    public Optional<Lint> lintAt(JavaFileObject javaFileObject, JCDiagnostic.DiagnosticPosition diagnosticPosition) {
        initializeIfNeeded();
        FileInfo fileInfo = this.fileInfoMap.get(javaFileObject);
        return fileInfo != null ? fileInfo.lintAt(diagnosticPosition) : Optional.empty();
    }

    public void startParsingFile(JavaFileObject javaFileObject) {
        initializeIfNeeded();
        this.fileInfoMap.put(javaFileObject, null);
    }

    public static final class Span {
        static final Span MAXIMAL = new Span(Integer.MIN_VALUE, Integer.MAX_VALUE);
        private final int endPos;
        private final int startPos;

        public Span(JCTree jCTree, EndPosTable endPosTable) {
            this(TreeInfo.getStartPos(jCTree), TreeInfo.getEndPos(jCTree, endPosTable));
        }

        public boolean contains(JCDiagnostic.DiagnosticPosition diagnosticPosition) {
            int lintPosition = diagnosticPosition.getLintPosition();
            int i = this.startPos;
            if (lintPosition != i) {
                return lintPosition > i && lintPosition < this.endPos;
            }
            return true;
        }

        public int endPos() {
            return this.endPos;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof Span)) {
                return false;
            }
            Span span = (Span) obj;
            return this.endPos == span.endPos && this.startPos == span.startPos;
        }

        public final int hashCode() {
            return (Integer.hashCode(this.startPos) * 31) + Integer.hashCode(this.endPos);
        }

        public int startPos() {
            return this.startPos;
        }

        public final String toString() {
            return "Span[startPos=" + Integer.toString(this.startPos) + ", endPos=" + Integer.toString(this.endPos) + "]";
        }

        private Span(int i, int i2) {
            this.startPos = i;
            this.endPos = i2;
        }

        public boolean contains(Span span) {
            return this.startPos <= span.startPos && this.endPos >= span.endPos;
        }
    }

    public static final class LintRange {
        private final List<LintRange> children;
        private final Lint lint;
        private final Span span;

        /* JADX INFO: renamed from: com.sun.tools.javac.code.LintMapper$LintRange$1, reason: invalid class name */
        public class AnonymousClass1 extends TreeScanner {
            private LintRange currentNode;
            final /* synthetic */ LintRange this$0;
            final /* synthetic */ EndPosTable val$endPositions;

            public AnonymousClass1(LintRange lintRange, EndPosTable endPosTable) {
                this.val$endPositions = endPosTable;
                this.this$0 = lintRange;
                this.currentNode = lintRange;
            }

            private <T extends JCTree> void scanDecl(T t, Symbol symbol, Consumer<? super T> consumer) {
                if (symbol == null) {
                    consumer.accept(t);
                    return;
                }
                Lint lintAugment = this.currentNode.lint.augment(symbol);
                if (lintAugment == this.currentNode.lint) {
                    consumer.accept(t);
                    return;
                }
                LintRange lintRange = this.currentNode;
                this.currentNode = new LintRange(t, this.val$endPositions, lintAugment);
                lintRange.children.add(this.currentNode);
                try {
                    consumer.accept(t);
                } finally {
                    this.currentNode = lintRange;
                }
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
                scanDecl(jCClassDecl, jCClassDecl.sym, new Consumer() { // from class: com.sun.tools.javac.code.h
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        super/*com.sun.tools.javac.tree.TreeScanner*/.visitClassDef((JCTree.JCClassDecl) obj);
                    }
                });
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitMethodDef(JCTree.JCMethodDecl jCMethodDecl) {
                scanDecl(jCMethodDecl, jCMethodDecl.sym, new Consumer() { // from class: com.sun.tools.javac.code.g
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        super/*com.sun.tools.javac.tree.TreeScanner*/.visitMethodDef((JCTree.JCMethodDecl) obj);
                    }
                });
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitModuleDef(JCTree.JCModuleDecl jCModuleDecl) {
                scanDecl(jCModuleDecl, jCModuleDecl.sym, new Consumer() { // from class: com.sun.tools.javac.code.e
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        super/*com.sun.tools.javac.tree.TreeScanner*/.visitModuleDef((JCTree.JCModuleDecl) obj);
                    }
                });
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitPackageDef(JCTree.JCPackageDecl jCPackageDecl) {
                scanDecl(jCPackageDecl, jCPackageDecl.packge, new Consumer() { // from class: com.sun.tools.javac.code.f
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        super/*com.sun.tools.javac.tree.TreeScanner*/.visitPackageDef((JCTree.JCPackageDecl) obj);
                    }
                });
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitVarDef(JCTree.JCVariableDecl jCVariableDecl) {
                scanDecl(jCVariableDecl, jCVariableDecl.sym, new Consumer() { // from class: com.sun.tools.javac.code.d
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        super/*com.sun.tools.javac.tree.TreeScanner*/.visitVarDef((JCTree.JCVariableDecl) obj);
                    }
                });
            }
        }

        public LintRange(JCTree jCTree, EndPosTable endPosTable, Lint lint) {
            this(new Span(jCTree, endPosTable), lint, new LinkedList());
        }

        public LintRange bestMatch(JCDiagnostic.DiagnosticPosition diagnosticPosition) {
            LintRange lintRangeBestMatch;
            LintRange lintRange = null;
            for (LintRange lintRange2 : this.children) {
                if (lintRange2.span.contains(diagnosticPosition) && (lintRangeBestMatch = lintRange2.bestMatch(diagnosticPosition)) != null && (lintRange == null || lintRange.span.contains(lintRangeBestMatch.span))) {
                    lintRange = lintRangeBestMatch;
                }
            }
            if (lintRange != null) {
                return lintRange;
            }
            if (this.span.contains(diagnosticPosition)) {
                return this;
            }
            return null;
        }

        public List<LintRange> children() {
            return this.children;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof LintRange)) {
                return false;
            }
            LintRange lintRange = (LintRange) obj;
            return Objects.equals(this.children, lintRange.children) && Objects.equals(this.lint, lintRange.lint) && Objects.equals(this.span, lintRange.span);
        }

        public final int hashCode() {
            return (((Objects.hashCode(this.span) * 31) + Objects.hashCode(this.lint)) * 31) + Objects.hashCode(this.children);
        }

        public Lint lint() {
            return this.lint;
        }

        public void populateSubtree(JCTree jCTree, EndPosTable endPosTable) {
            new AnonymousClass1(this, endPosTable).scan(jCTree);
        }

        public Span span() {
            return this.span;
        }

        public final String toString() {
            return "LintRange[span=" + Objects.toString(this.span) + ", lint=" + Objects.toString(this.lint) + ", children=" + Objects.toString(this.children) + "]";
        }

        public LintRange(Lint lint) {
            this(Span.MAXIMAL, lint, new LinkedList());
        }

        private LintRange(Span span, Lint lint, List<LintRange> list) {
            this.span = span;
            this.lint = lint;
            this.children = list;
        }
    }
}
