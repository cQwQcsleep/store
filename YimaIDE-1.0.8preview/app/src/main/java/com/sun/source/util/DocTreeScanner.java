package com.sun.source.util;

import com.sun.source.doctree.AttributeTree;
import com.sun.source.doctree.AuthorTree;
import com.sun.source.doctree.CommentTree;
import com.sun.source.doctree.DeprecatedTree;
import com.sun.source.doctree.DocCommentTree;
import com.sun.source.doctree.DocRootTree;
import com.sun.source.doctree.DocTree;
import com.sun.source.doctree.DocTreeVisitor;
import com.sun.source.doctree.DocTypeTree;
import com.sun.source.doctree.EndElementTree;
import com.sun.source.doctree.EntityTree;
import com.sun.source.doctree.ErroneousTree;
import com.sun.source.doctree.EscapeTree;
import com.sun.source.doctree.HiddenTree;
import com.sun.source.doctree.IdentifierTree;
import com.sun.source.doctree.IndexTree;
import com.sun.source.doctree.InheritDocTree;
import com.sun.source.doctree.LinkTree;
import com.sun.source.doctree.LiteralTree;
import com.sun.source.doctree.ParamTree;
import com.sun.source.doctree.ProvidesTree;
import com.sun.source.doctree.RawTextTree;
import com.sun.source.doctree.ReferenceTree;
import com.sun.source.doctree.ReturnTree;
import com.sun.source.doctree.SeeTree;
import com.sun.source.doctree.SerialDataTree;
import com.sun.source.doctree.SerialFieldTree;
import com.sun.source.doctree.SerialTree;
import com.sun.source.doctree.SinceTree;
import com.sun.source.doctree.SnippetTree;
import com.sun.source.doctree.SpecTree;
import com.sun.source.doctree.StartElementTree;
import com.sun.source.doctree.SummaryTree;
import com.sun.source.doctree.SystemPropertyTree;
import com.sun.source.doctree.TextTree;
import com.sun.source.doctree.ThrowsTree;
import com.sun.source.doctree.UnknownBlockTagTree;
import com.sun.source.doctree.UnknownInlineTagTree;
import com.sun.source.doctree.UsesTree;
import com.sun.source.doctree.ValueTree;
import com.sun.source.doctree.VersionTree;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DocTreeScanner<R, P> implements DocTreeVisitor<R, P> {
    private R scanAndReduce(DocTree docTree, P p, R r) {
        return reduce(scan(docTree, p), r);
    }

    public R reduce(R r, R r2) {
        return r;
    }

    public R scan(Iterable<? extends DocTree> iterable, P p) {
        R rScan = null;
        if (iterable != null) {
            boolean z = true;
            for (DocTree docTree : iterable) {
                rScan = z ? scan(docTree, p) : scanAndReduce(docTree, p, rScan);
                z = false;
            }
        }
        return rScan;
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitAttribute(AttributeTree attributeTree, P p) {
        return scan(attributeTree.getValue(), p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitAuthor(AuthorTree authorTree, P p) {
        return scan(authorTree.getName(), p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitComment(CommentTree commentTree, P p) {
        return null;
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitDeprecated(DeprecatedTree deprecatedTree, P p) {
        return scan(deprecatedTree.getBody(), p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitDocComment(DocCommentTree docCommentTree, P p) {
        return scanAndReduce(docCommentTree.getBlockTags(), p, scanAndReduce(docCommentTree.getBody(), p, scan(docCommentTree.getFirstSentence(), p)));
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitDocRoot(DocRootTree docRootTree, P p) {
        return null;
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitDocType(DocTypeTree docTypeTree, P p) {
        return null;
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitEndElement(EndElementTree endElementTree, P p) {
        return null;
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitEntity(EntityTree entityTree, P p) {
        return null;
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitErroneous(ErroneousTree erroneousTree, P p) {
        return null;
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitEscape(EscapeTree escapeTree, P p) {
        return null;
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitHidden(HiddenTree hiddenTree, P p) {
        return scan(hiddenTree.getBody(), p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitIdentifier(IdentifierTree identifierTree, P p) {
        return null;
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitIndex(IndexTree indexTree, P p) {
        return scanAndReduce(indexTree.getDescription(), p, scan(indexTree.getSearchTerm(), p));
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitInheritDoc(InheritDocTree inheritDocTree, P p) {
        return scan(inheritDocTree.getSupertype(), p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitLink(LinkTree linkTree, P p) {
        return scanAndReduce(linkTree.getLabel(), p, scan(linkTree.getReference(), p));
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitLiteral(LiteralTree literalTree, P p) {
        return scan(literalTree.getBody(), p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitOther(DocTree docTree, P p) {
        return null;
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitParam(ParamTree paramTree, P p) {
        return scanAndReduce(paramTree.getDescription(), p, scan(paramTree.getName(), p));
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitProvides(ProvidesTree providesTree, P p) {
        return scanAndReduce(providesTree.getDescription(), p, scan(providesTree.getServiceType(), p));
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitRawText(RawTextTree rawTextTree, P p) {
        return null;
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitReference(ReferenceTree referenceTree, P p) {
        return null;
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitReturn(ReturnTree returnTree, P p) {
        return scan(returnTree.getDescription(), p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitSee(SeeTree seeTree, P p) {
        return scan(seeTree.getReference(), p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitSerial(SerialTree serialTree, P p) {
        return scan(serialTree.getDescription(), p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitSerialData(SerialDataTree serialDataTree, P p) {
        return scan(serialDataTree.getDescription(), p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitSerialField(SerialFieldTree serialFieldTree, P p) {
        return scanAndReduce(serialFieldTree.getDescription(), p, scanAndReduce(serialFieldTree.getType(), p, scan(serialFieldTree.getName(), p)));
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitSince(SinceTree sinceTree, P p) {
        return scan(sinceTree.getBody(), p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitSnippet(SnippetTree snippetTree, P p) {
        return scanAndReduce(snippetTree.getBody(), p, scan(snippetTree.getAttributes(), p));
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitSpec(SpecTree specTree, P p) {
        return scanAndReduce(specTree.getTitle(), p, scan(specTree.getURL(), p));
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitStartElement(StartElementTree startElementTree, P p) {
        return scan(startElementTree.getAttributes(), p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitSummary(SummaryTree summaryTree, P p) {
        return scan(summaryTree.getSummary(), p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitSystemProperty(SystemPropertyTree systemPropertyTree, P p) {
        return null;
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitText(TextTree textTree, P p) {
        return null;
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitThrows(ThrowsTree throwsTree, P p) {
        return scanAndReduce(throwsTree.getDescription(), p, scan(throwsTree.getExceptionName(), p));
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitUnknownBlockTag(UnknownBlockTagTree unknownBlockTagTree, P p) {
        return scan(unknownBlockTagTree.getContent(), p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitUnknownInlineTag(UnknownInlineTagTree unknownInlineTagTree, P p) {
        return scan(unknownInlineTagTree.getContent(), p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitUses(UsesTree usesTree, P p) {
        return scanAndReduce(usesTree.getDescription(), p, scan(usesTree.getServiceType(), p));
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitValue(ValueTree valueTree, P p) {
        return scanAndReduce(valueTree.getReference(), p, scan(valueTree.getFormat(), p));
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitVersion(VersionTree versionTree, P p) {
        return scan(versionTree.getBody(), p);
    }

    private R scanAndReduce(Iterable<? extends DocTree> iterable, P p, R r) {
        return reduce(scan(iterable, p), r);
    }

    public R scan(DocTree docTree, P p) {
        if (docTree == null) {
            return null;
        }
        return (R) docTree.accept(this, p);
    }
}
