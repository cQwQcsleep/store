package com.sun.source.util;

import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.Tree;
import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TreePath implements Iterable<Tree> {
    private CompilationUnitTree compilationUnit;
    private Tree leaf;
    private TreePath parent;

    public TreePath(TreePath treePath, Tree tree) {
        if (tree.getKind() == Tree.Kind.COMPILATION_UNIT) {
            this.compilationUnit = (CompilationUnitTree) tree;
            this.parent = null;
        } else {
            this.compilationUnit = treePath.compilationUnit;
            this.parent = treePath;
        }
        this.leaf = tree;
    }

    public static TreePath getPath(TreePath treePath, Tree tree) {
        Objects.requireNonNull(treePath);
        Objects.requireNonNull(tree);
        return treePath.getLeaf() == tree ? treePath : new TreePathScanner<TreePath, Tree>() { // from class: com.sun.source.util.TreePath.1PathFinder
            private TreePath result;

            public TreePath scan(Iterable<? extends Tree> iterable, Tree tree2) {
                if (iterable != null && this.result == null) {
                    Iterator<? extends Tree> it = iterable.iterator();
                    while (it.hasNext()) {
                        scan(it.next(), tree2);
                        if (this.result != null) {
                            break;
                        }
                    }
                }
                return this.result;
            }

            @Override // com.sun.source.util.TreeScanner
            public /* bridge */ /* synthetic */ Object scan(Iterable iterable, Object obj) {
                return scan((Iterable<? extends Tree>) iterable, (Tree) obj);
            }

            @Override // com.sun.source.util.TreePathScanner
            public TreePath scan(TreePath treePath2, Tree tree2) {
                super.scan(treePath2, tree2);
                return this.result;
            }

            @Override // com.sun.source.util.TreePathScanner, com.sun.source.util.TreeScanner
            public TreePath scan(Tree tree2, Tree tree3) {
                if (this.result == null) {
                    if (tree2 == tree3) {
                        this.result = new TreePath(getCurrentPath(), tree3);
                    } else {
                        super.scan(tree2, tree3);
                    }
                }
                return this.result;
            }
        }.scan(treePath, tree);
    }

    public CompilationUnitTree getCompilationUnit() {
        return this.compilationUnit;
    }

    public Tree getLeaf() {
        return this.leaf;
    }

    public TreePath getParentPath() {
        return this.parent;
    }

    @Override // java.lang.Iterable
    public Iterator<Tree> iterator() {
        return new Iterator<Tree>() { // from class: com.sun.source.util.TreePath.1
            private TreePath next;

            {
                this.next = TreePath.this;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.next != null;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public Tree next() {
                TreePath treePath = this.next;
                if (treePath == null) {
                    z0e.a();
                    return null;
                }
                Tree tree = treePath.leaf;
                this.next = this.next.parent;
                return tree;
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static TreePath getPath(CompilationUnitTree compilationUnitTree, Tree tree) {
        return getPath(new TreePath(compilationUnitTree), tree);
    }

    public TreePath(CompilationUnitTree compilationUnitTree) {
        this(null, compilationUnitTree);
    }
}
