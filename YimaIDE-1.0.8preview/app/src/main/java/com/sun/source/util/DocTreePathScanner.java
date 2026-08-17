package com.sun.source.util;

import com.sun.source.doctree.DocTree;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DocTreePathScanner<R, P> extends DocTreeScanner<R, P> {
    private DocTreePath path;

    public DocTreePath getCurrentPath() {
        return this.path;
    }

    @Override // com.sun.source.util.DocTreeScanner
    public R scan(DocTree docTree, P p) {
        if (docTree == null) {
            return null;
        }
        DocTreePath docTreePath = this.path;
        this.path = new DocTreePath(docTreePath, docTree);
        try {
            return (R) docTree.accept(this, p);
        } finally {
            this.path = docTreePath;
        }
    }

    public R scan(DocTreePath docTreePath, P p) {
        this.path = docTreePath;
        try {
            return (R) docTreePath.getLeaf().accept(this, p);
        } finally {
            this.path = null;
        }
    }
}
