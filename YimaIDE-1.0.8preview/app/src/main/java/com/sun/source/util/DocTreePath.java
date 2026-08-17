package com.sun.source.util;

import com.sun.source.doctree.DocCommentTree;
import com.sun.source.doctree.DocTree;
import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DocTreePath implements Iterable<DocTree> {
    private final DocCommentTree docComment;
    private final DocTree leaf;
    private final DocTreePath parent;
    private final TreePath treePath;

    public DocTreePath(DocTreePath docTreePath, DocTree docTree) {
        if (docTree.getKind() == DocTree.Kind.DOC_COMMENT) {
            w01.a("Use DocTreePath(TreePath, DocCommentTree) to construct DocTreePath for a DocCommentTree.");
            throw null;
        }
        this.treePath = docTreePath.treePath;
        this.docComment = docTreePath.docComment;
        this.parent = docTreePath;
        this.leaf = docTree;
    }

    public static DocTreePath getPath(DocTreePath docTreePath, DocTree docTree) {
        Objects.requireNonNull(docTreePath);
        Objects.requireNonNull(docTree);
        return docTreePath.getLeaf() == docTree ? docTreePath : new DocTreePathScanner<DocTreePath, DocTree>() { // from class: com.sun.source.util.DocTreePath.1PathFinder
            private DocTreePath result;

            public DocTreePath scan(Iterable<? extends DocTree> iterable, DocTree docTree2) {
                if (iterable != null && this.result == null) {
                    Iterator<? extends DocTree> it = iterable.iterator();
                    while (it.hasNext()) {
                        scan(it.next(), docTree2);
                        if (this.result != null) {
                            break;
                        }
                    }
                }
                return this.result;
            }

            @Override // com.sun.source.util.DocTreeScanner
            public /* bridge */ /* synthetic */ Object scan(Iterable iterable, Object obj) {
                return scan((Iterable<? extends DocTree>) iterable, (DocTree) obj);
            }

            @Override // com.sun.source.util.DocTreePathScanner
            public DocTreePath scan(DocTreePath docTreePath2, DocTree docTree2) {
                super.scan(docTreePath2, docTree2);
                return this.result;
            }

            @Override // com.sun.source.util.DocTreePathScanner, com.sun.source.util.DocTreeScanner
            public DocTreePath scan(DocTree docTree2, DocTree docTree3) {
                if (this.result == null) {
                    if (docTree2 == docTree3) {
                        this.result = new DocTreePath(getCurrentPath(), docTree3);
                    } else {
                        super.scan(docTree2, docTree3);
                    }
                }
                return this.result;
            }
        }.scan(docTreePath, docTree);
    }

    public DocCommentTree getDocComment() {
        return this.docComment;
    }

    public DocTree getLeaf() {
        return this.leaf;
    }

    public DocTreePath getParentPath() {
        return this.parent;
    }

    public TreePath getTreePath() {
        return this.treePath;
    }

    @Override // java.lang.Iterable
    public Iterator<DocTree> iterator() {
        return new Iterator<DocTree>() { // from class: com.sun.source.util.DocTreePath.1
            private DocTreePath next;

            {
                this.next = DocTreePath.this;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.next != null;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public DocTree next() {
                DocTreePath docTreePath = this.next;
                if (docTreePath == null) {
                    z0e.a();
                    return null;
                }
                DocTree docTree = docTreePath.leaf;
                this.next = this.next.parent;
                return docTree;
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static DocTreePath getPath(TreePath treePath, DocCommentTree docCommentTree, DocTree docTree) {
        return getPath(new DocTreePath(treePath, docCommentTree), docTree);
    }

    public DocTreePath(TreePath treePath, DocCommentTree docCommentTree) {
        this.treePath = treePath;
        Objects.requireNonNull(docCommentTree);
        this.docComment = docCommentTree;
        this.parent = null;
        this.leaf = docCommentTree;
    }
}
