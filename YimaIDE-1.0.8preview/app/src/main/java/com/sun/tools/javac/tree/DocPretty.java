package com.sun.tools.javac.tree;

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
import com.sun.tools.javac.tree.DocPretty;
import com.sun.tools.javac.util.Convert;
import defpackage.u8i;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DocPretty implements DocTreeVisitor<Void, Void> {
    final String lineSep = System.getProperty("line.separator");
    final Writer out;

    /* JADX INFO: renamed from: com.sun.tools.javac.tree.DocPretty$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$source$doctree$AttributeTree$ValueKind;

        static {
            int[] iArr = new int[AttributeTree.ValueKind.values().length];
            $SwitchMap$com$sun$source$doctree$AttributeTree$ValueKind = iArr;
            try {
                iArr[AttributeTree.ValueKind.EMPTY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$source$doctree$AttributeTree$ValueKind[AttributeTree.ValueKind.UNQUOTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$source$doctree$AttributeTree$ValueKind[AttributeTree.ValueKind.SINGLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sun$source$doctree$AttributeTree$ValueKind[AttributeTree.ValueKind.DOUBLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public DocPretty(Writer writer) {
        this.out = writer;
    }

    public static /* synthetic */ boolean a(DocTree docTree) {
        return (docTree instanceof TextTree) && ((TextTree) docTree).getBody().length() == 0;
    }

    private void print(List<? extends DocTree> list, char c) throws IOException {
        if (list.isEmpty()) {
            return;
        }
        boolean z = true;
        for (DocTree docTree : list) {
            if (!z) {
                print(c);
            }
            print(docTree);
            z = false;
        }
    }

    public void printTagName(DocTree docTree) throws IOException {
        this.out.write(64);
        this.out.write(docTree.getKind().tagName);
    }

    public void println() throws IOException {
        this.out.write(this.lineSep);
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public Void visitAttribute(AttributeTree attributeTree, Void r4) {
        String str;
        try {
            print(attributeTree.getName());
            int i = AnonymousClass1.$SwitchMap$com$sun$source$doctree$AttributeTree$ValueKind[attributeTree.getValueKind().ordinal()];
            if (i == 1) {
                str = null;
            } else if (i == 2) {
                str = "";
            } else if (i == 3) {
                str = "'";
            } else {
                if (i != 4) {
                    throw new IllegalStateException(null, null);
                }
                str = "\"";
            }
            if (str != null) {
                print('=');
                print(str);
                print(attributeTree.getValue());
                print(str);
            }
            return null;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public Void visitAuthor(AuthorTree authorTree, Void r2) {
        try {
            printTagName(authorTree);
            print(' ');
            print(authorTree.getName());
            return null;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public Void visitComment(CommentTree commentTree, Void r2) {
        try {
            print(commentTree.getBody());
            return null;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public Void visitDeprecated(DeprecatedTree deprecatedTree, Void r3) {
        try {
            printTagName(deprecatedTree);
            if (!deprecatedTree.getBody().isEmpty()) {
                print(' ');
                print(deprecatedTree.getBody());
            }
            return null;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public Void visitDocComment(DocCommentTree docCommentTree, Void r4) {
        try {
            List<? extends DocTree> fullBody = docCommentTree.getFullBody();
            List<? extends DocTree> blockTags = docCommentTree.getBlockTags();
            print(fullBody);
            if (!fullBody.isEmpty() && !blockTags.isEmpty()) {
                print('\n');
            }
            print(blockTags, '\n');
            return null;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public Void visitDocRoot(DocRootTree docRootTree, Void r2) {
        try {
            print('{');
            printTagName(docRootTree);
            print('}');
            return null;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public Void visitDocType(DocTypeTree docTypeTree, Void r2) {
        try {
            print(docTypeTree.getText());
            return null;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public Void visitEndElement(EndElementTree endElementTree, Void r2) {
        try {
            print("</");
            print(endElementTree.getName());
            print('>');
            return null;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public Void visitEntity(EntityTree entityTree, Void r2) {
        try {
            print('&');
            print(entityTree.getName());
            print(';');
            return null;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public Void visitErroneous(ErroneousTree erroneousTree, Void r2) {
        try {
            print(erroneousTree.getBody());
            return null;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public Void visitEscape(EscapeTree escapeTree, Void r3) {
        try {
            this.out.write(64);
            print(escapeTree.getBody());
            return null;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public Void visitHidden(HiddenTree hiddenTree, Void r3) {
        try {
            printTagName(hiddenTree);
            if (!hiddenTree.getBody().isEmpty()) {
                print(' ');
                print(hiddenTree.getBody());
            }
            return null;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public Void visitIdentifier(IdentifierTree identifierTree, Void r2) {
        try {
            print(identifierTree.getName());
            return null;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public Void visitIndex(IndexTree indexTree, Void r4) {
        try {
            print('{');
            printTagName(indexTree);
            print(' ');
            print(indexTree.getSearchTerm());
            if (!indexTree.getDescription().isEmpty()) {
                print(' ');
                print(indexTree.getDescription());
            }
            print('}');
            return null;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public Void visitInheritDoc(InheritDocTree inheritDocTree, Void r3) {
        try {
            print('{');
            printTagName(inheritDocTree);
            if (inheritDocTree.getSupertype() != null) {
                print(" ");
                print((DocTree) inheritDocTree.getSupertype());
            }
            print('}');
            return null;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public Void visitLink(LinkTree linkTree, Void r4) {
        try {
            print('{');
            printTagName(linkTree);
            print(' ');
            print((DocTree) linkTree.getReference());
            if (!linkTree.getLabel().isEmpty()) {
                print(' ');
                print(linkTree.getLabel());
            }
            print('}');
            return null;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public Void visitLiteral(LiteralTree literalTree, Void r4) {
        try {
            print('{');
            printTagName(literalTree);
            String body = literalTree.getBody().getBody();
            if (body.length() != 0 && body.charAt(0) != '\n') {
                print(' ');
            }
            print((DocTree) literalTree.getBody());
            print('}');
            return null;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public Void visitOther(DocTree docTree, Void r2) {
        try {
            print("(UNKNOWN: ");
            print(docTree);
            print(')');
            println();
            return null;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public Void visitParam(ParamTree paramTree, Void r4) {
        try {
            printTagName(paramTree);
            print(' ');
            if (paramTree.isTypeParameter()) {
                print('<');
            }
            print((DocTree) paramTree.getName());
            if (paramTree.isTypeParameter()) {
                print('>');
            }
            if (!paramTree.getDescription().isEmpty()) {
                print(' ');
                print(paramTree.getDescription());
            }
            return null;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public Void visitProvides(ProvidesTree providesTree, Void r4) {
        try {
            printTagName(providesTree);
            print(' ');
            print((DocTree) providesTree.getServiceType());
            if (!providesTree.getDescription().isEmpty()) {
                print(' ');
                print(providesTree.getDescription());
            }
            return null;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public Void visitRawText(RawTextTree rawTextTree, Void r2) {
        try {
            print(rawTextTree.getContent());
            return null;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public Void visitReference(ReferenceTree referenceTree, Void r2) {
        try {
            print(referenceTree.getSignature());
            return null;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public Void visitReturn(ReturnTree returnTree, Void r3) {
        try {
            if (returnTree.isInline()) {
                print('{');
            }
            printTagName(returnTree);
            print(' ');
            print(returnTree.getDescription());
            if (returnTree.isInline()) {
                print('}');
            }
            return null;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public Void visitSee(SeeTree seeTree, Void r7) {
        try {
            printTagName(seeTree);
            boolean z = true;
            boolean z2 = true;
            for (DocTree docTree : seeTree.getReference()) {
                if (z) {
                    print(' ');
                }
                boolean z3 = z2 && (docTree instanceof ReferenceTree);
                print(docTree);
                boolean z4 = z3;
                z2 = false;
                z = z4;
            }
            return null;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public Void visitSerial(SerialTree serialTree, Void r3) {
        try {
            printTagName(serialTree);
            if (!serialTree.getDescription().isEmpty()) {
                print(' ');
                print(serialTree.getDescription());
            }
            return null;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public Void visitSerialData(SerialDataTree serialDataTree, Void r3) {
        try {
            printTagName(serialDataTree);
            if (!serialDataTree.getDescription().isEmpty()) {
                print(' ');
                print(serialDataTree.getDescription());
            }
            return null;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public Void visitSerialField(SerialFieldTree serialFieldTree, Void r4) {
        try {
            printTagName(serialFieldTree);
            print(' ');
            print((DocTree) serialFieldTree.getName());
            print(' ');
            print((DocTree) serialFieldTree.getType());
            if (!serialFieldTree.getDescription().isEmpty()) {
                print(' ');
                print(serialFieldTree.getDescription());
            }
            return null;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public Void visitSince(SinceTree sinceTree, Void r2) {
        try {
            printTagName(sinceTree);
            print(' ');
            print(sinceTree.getBody());
            return null;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public Void visitSnippet(SnippetTree snippetTree, Void r4) {
        try {
            print('{');
            printTagName(snippetTree);
            List<? extends DocTree> attributes = snippetTree.getAttributes();
            if (!attributes.isEmpty()) {
                print(' ');
                print(attributes, ' ');
            }
            if (snippetTree.getBody() != null) {
                print(" :\n");
                print((DocTree) snippetTree.getBody());
            }
            print('}');
            return null;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public Void visitSpec(SpecTree specTree, Void r4) {
        try {
            printTagName(specTree);
            print(' ');
            print((DocTree) specTree.getURL());
            print(' ');
            print(specTree.getTitle());
            return null;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public Void visitStartElement(StartElementTree startElementTree, Void r5) {
        try {
            print('<');
            print(startElementTree.getName());
            List<? extends DocTree> attributes = startElementTree.getAttributes();
            if (!attributes.isEmpty()) {
                print(' ');
                print(attributes, ' ');
                DocTree docTree = startElementTree.getAttributes().get(attributes.size() - 1);
                if (startElementTree.isSelfClosing() && (docTree instanceof AttributeTree) && ((AttributeTree) docTree).getValueKind() == AttributeTree.ValueKind.UNQUOTED) {
                    print(' ');
                }
            }
            if (startElementTree.isSelfClosing()) {
                print('/');
            }
            print('>');
            return null;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public Void visitSummary(SummaryTree summaryTree, Void r3) {
        try {
            print('{');
            printTagName(summaryTree);
            if (!summaryTree.getSummary().isEmpty()) {
                print(' ');
                print(summaryTree.getSummary());
            }
            print('}');
            return null;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public Void visitSystemProperty(SystemPropertyTree systemPropertyTree, Void r2) {
        try {
            print('{');
            printTagName(systemPropertyTree);
            print(' ');
            print(systemPropertyTree.getPropertyName());
            print('}');
            return null;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public Void visitText(TextTree textTree, Void r2) {
        try {
            print(textTree.getBody());
            return null;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public Void visitThrows(ThrowsTree throwsTree, Void r4) {
        try {
            printTagName(throwsTree);
            print(' ');
            print((DocTree) throwsTree.getExceptionName());
            if (!throwsTree.getDescription().isEmpty()) {
                print(' ');
                print(throwsTree.getDescription());
            }
            return null;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public Void visitUnknownBlockTag(UnknownBlockTagTree unknownBlockTagTree, Void r3) {
        try {
            print('@');
            print(unknownBlockTagTree.getTagName());
            print(' ');
            print(unknownBlockTagTree.getContent());
            return null;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public Void visitUnknownInlineTag(UnknownInlineTagTree unknownInlineTagTree, Void r4) {
        try {
            print('{');
            print('@');
            print(unknownInlineTagTree.getTagName());
            List<? extends DocTree> content = unknownInlineTagTree.getContent();
            if (!content.stream().allMatch(new Predicate() { // from class: cv3
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return DocPretty.a((DocTree) obj);
                }
            })) {
                print(' ');
                print(content);
            }
            print('}');
            return null;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public Void visitUses(UsesTree usesTree, Void r4) {
        try {
            printTagName(usesTree);
            print(' ');
            print((DocTree) usesTree.getServiceType());
            if (!usesTree.getDescription().isEmpty()) {
                print(' ');
                print(usesTree.getDescription());
            }
            return null;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public Void visitValue(ValueTree valueTree, Void r4) {
        try {
            print('{');
            printTagName(valueTree);
            if (valueTree.getFormat() != null) {
                print(' ');
                print((DocTree) valueTree.getFormat());
            }
            if (valueTree.getReference() != null) {
                print(' ');
                print((DocTree) valueTree.getReference());
            }
            print('}');
            return null;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    @Override // com.sun.source.doctree.DocTreeVisitor
    public Void visitVersion(VersionTree versionTree, Void r2) {
        try {
            printTagName(versionTree);
            print(' ');
            print(versionTree.getBody());
            return null;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    public void print(Object obj) throws IOException {
        this.out.write(Convert.escapeUnicode(obj.toString()));
    }

    private void print(char c) throws IOException {
        this.out.write(c);
    }

    public void print(List<? extends DocTree> list) throws IOException {
        Iterator<? extends DocTree> it = list.iterator();
        while (it.hasNext()) {
            print(it.next());
        }
    }

    public void print(DocTree docTree) throws IOException {
        try {
            if (docTree == null) {
                print("/*missing*/");
            } else {
                docTree.accept(this, null);
            }
        } catch (UncheckedIOException e) {
            throw e.getCause();
        }
    }
}
