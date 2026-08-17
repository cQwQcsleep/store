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
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SimpleDocTreeVisitor<R, P> implements DocTreeVisitor<R, P> {
    protected final R DEFAULT_VALUE;

    public SimpleDocTreeVisitor() {
        this.DEFAULT_VALUE = null;
    }

    public R defaultAction(DocTree docTree, P p) {
        return this.DEFAULT_VALUE;
    }

    public final R visit(Iterable<? extends DocTree> iterable, P p) {
        R rVisit = null;
        if (iterable != null) {
            Iterator<? extends DocTree> it = iterable.iterator();
            while (it.hasNext()) {
                rVisit = visit(it.next(), p);
            }
        }
        return rVisit;
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitAttribute(AttributeTree attributeTree, P p) {
        return defaultAction(attributeTree, p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitAuthor(AuthorTree authorTree, P p) {
        return defaultAction(authorTree, p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitComment(CommentTree commentTree, P p) {
        return defaultAction(commentTree, p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitDeprecated(DeprecatedTree deprecatedTree, P p) {
        return defaultAction(deprecatedTree, p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitDocComment(DocCommentTree docCommentTree, P p) {
        return defaultAction(docCommentTree, p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitDocRoot(DocRootTree docRootTree, P p) {
        return defaultAction(docRootTree, p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitDocType(DocTypeTree docTypeTree, P p) {
        return defaultAction(docTypeTree, p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitEndElement(EndElementTree endElementTree, P p) {
        return defaultAction(endElementTree, p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitEntity(EntityTree entityTree, P p) {
        return defaultAction(entityTree, p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitErroneous(ErroneousTree erroneousTree, P p) {
        return defaultAction(erroneousTree, p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitEscape(EscapeTree escapeTree, P p) {
        return defaultAction(escapeTree, p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitHidden(HiddenTree hiddenTree, P p) {
        return defaultAction(hiddenTree, p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitIdentifier(IdentifierTree identifierTree, P p) {
        return defaultAction(identifierTree, p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitIndex(IndexTree indexTree, P p) {
        return defaultAction(indexTree, p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitInheritDoc(InheritDocTree inheritDocTree, P p) {
        return defaultAction(inheritDocTree, p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitLink(LinkTree linkTree, P p) {
        return defaultAction(linkTree, p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitLiteral(LiteralTree literalTree, P p) {
        return defaultAction(literalTree, p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitOther(DocTree docTree, P p) {
        return defaultAction(docTree, p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitParam(ParamTree paramTree, P p) {
        return defaultAction(paramTree, p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitProvides(ProvidesTree providesTree, P p) {
        return defaultAction(providesTree, p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitRawText(RawTextTree rawTextTree, P p) {
        return defaultAction(rawTextTree, p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitReference(ReferenceTree referenceTree, P p) {
        return defaultAction(referenceTree, p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitReturn(ReturnTree returnTree, P p) {
        return defaultAction(returnTree, p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitSee(SeeTree seeTree, P p) {
        return defaultAction(seeTree, p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitSerial(SerialTree serialTree, P p) {
        return defaultAction(serialTree, p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitSerialData(SerialDataTree serialDataTree, P p) {
        return defaultAction(serialDataTree, p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitSerialField(SerialFieldTree serialFieldTree, P p) {
        return defaultAction(serialFieldTree, p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitSince(SinceTree sinceTree, P p) {
        return defaultAction(sinceTree, p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitSnippet(SnippetTree snippetTree, P p) {
        return defaultAction(snippetTree, p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitSpec(SpecTree specTree, P p) {
        return defaultAction(specTree, p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitStartElement(StartElementTree startElementTree, P p) {
        return defaultAction(startElementTree, p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitSummary(SummaryTree summaryTree, P p) {
        return defaultAction(summaryTree, p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitSystemProperty(SystemPropertyTree systemPropertyTree, P p) {
        return defaultAction(systemPropertyTree, p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitText(TextTree textTree, P p) {
        return defaultAction(textTree, p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitThrows(ThrowsTree throwsTree, P p) {
        return defaultAction(throwsTree, p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitUnknownBlockTag(UnknownBlockTagTree unknownBlockTagTree, P p) {
        return defaultAction(unknownBlockTagTree, p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitUnknownInlineTag(UnknownInlineTagTree unknownInlineTagTree, P p) {
        return defaultAction(unknownInlineTagTree, p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitUses(UsesTree usesTree, P p) {
        return defaultAction(usesTree, p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitValue(ValueTree valueTree, P p) {
        return defaultAction(valueTree, p);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public R visitVersion(VersionTree versionTree, P p) {
        return defaultAction(versionTree, p);
    }

    public SimpleDocTreeVisitor(R r) {
        this.DEFAULT_VALUE = r;
    }

    public final R visit(DocTree docTree, P p) {
        if (docTree == null) {
            return null;
        }
        return (R) docTree.accept(this, p);
    }
}
