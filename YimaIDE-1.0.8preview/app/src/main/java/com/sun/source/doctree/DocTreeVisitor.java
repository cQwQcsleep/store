package com.sun.source.doctree;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface DocTreeVisitor<R, P> {
    R visitAttribute(AttributeTree attributeTree, P p);

    R visitAuthor(AuthorTree authorTree, P p);

    R visitComment(CommentTree commentTree, P p);

    R visitDeprecated(DeprecatedTree deprecatedTree, P p);

    R visitDocComment(DocCommentTree docCommentTree, P p);

    R visitDocRoot(DocRootTree docRootTree, P p);

    default R visitDocType(DocTypeTree docTypeTree, P p) {
        return visitOther(docTypeTree, p);
    }

    R visitEndElement(EndElementTree endElementTree, P p);

    R visitEntity(EntityTree entityTree, P p);

    R visitErroneous(ErroneousTree erroneousTree, P p);

    default R visitEscape(EscapeTree escapeTree, P p) {
        return visitOther(escapeTree, p);
    }

    default R visitHidden(HiddenTree hiddenTree, P p) {
        return visitOther(hiddenTree, p);
    }

    R visitIdentifier(IdentifierTree identifierTree, P p);

    default R visitIndex(IndexTree indexTree, P p) {
        return visitOther(indexTree, p);
    }

    R visitInheritDoc(InheritDocTree inheritDocTree, P p);

    R visitLink(LinkTree linkTree, P p);

    R visitLiteral(LiteralTree literalTree, P p);

    R visitOther(DocTree docTree, P p);

    R visitParam(ParamTree paramTree, P p);

    default R visitProvides(ProvidesTree providesTree, P p) {
        return visitOther(providesTree, p);
    }

    default R visitRawText(RawTextTree rawTextTree, P p) {
        return visitOther(rawTextTree, p);
    }

    R visitReference(ReferenceTree referenceTree, P p);

    R visitReturn(ReturnTree returnTree, P p);

    R visitSee(SeeTree seeTree, P p);

    R visitSerial(SerialTree serialTree, P p);

    R visitSerialData(SerialDataTree serialDataTree, P p);

    R visitSerialField(SerialFieldTree serialFieldTree, P p);

    R visitSince(SinceTree sinceTree, P p);

    default R visitSnippet(SnippetTree snippetTree, P p) {
        return visitOther(snippetTree, p);
    }

    default R visitSpec(SpecTree specTree, P p) {
        return visitOther(specTree, p);
    }

    R visitStartElement(StartElementTree startElementTree, P p);

    default R visitSummary(SummaryTree summaryTree, P p) {
        return visitOther(summaryTree, p);
    }

    default R visitSystemProperty(SystemPropertyTree systemPropertyTree, P p) {
        return visitOther(systemPropertyTree, p);
    }

    R visitText(TextTree textTree, P p);

    R visitThrows(ThrowsTree throwsTree, P p);

    R visitUnknownBlockTag(UnknownBlockTagTree unknownBlockTagTree, P p);

    R visitUnknownInlineTag(UnknownInlineTagTree unknownInlineTagTree, P p);

    default R visitUses(UsesTree usesTree, P p) {
        return visitOther(usesTree, p);
    }

    R visitValue(ValueTree valueTree, P p);

    R visitVersion(VersionTree versionTree, P p);
}
