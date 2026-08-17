package com.sun.tools.javac.tree;

import com.intellij.psi.PsiKeyword;
import com.sun.source.doctree.AttributeTree;
import com.sun.source.doctree.AuthorTree;
import com.sun.source.doctree.BlockTagTree;
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
import com.sun.source.doctree.InlineTagTree;
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
import com.sun.source.util.DocTreeScanner;
import com.sun.tools.javac.parser.Tokens;
import com.sun.tools.javac.tree.DCTree;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.JCDiagnostic;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.function.Predicate;
import javax.lang.model.element.Name;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import nbjavac.StringWrapper;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class DCTree implements DocTree {
    public int pos;

    /* JADX INFO: renamed from: com.sun.tools.javac.tree.DCTree$3, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$source$doctree$DocTree$Kind;

        static {
            int[] iArr = new int[DocTree.Kind.values().length];
            $SwitchMap$com$sun$source$doctree$DocTree$Kind = iArr;
            try {
                iArr[DocTree.Kind.MARKDOWN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$source$doctree$DocTree$Kind[DocTree.Kind.TEXT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$source$doctree$DocTree$Kind[DocTree.Kind.ERRONEOUS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sun$source$doctree$DocTree$Kind[DocTree.Kind.ESCAPE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$sun$source$doctree$DocTree$Kind[DocTree.Kind.IDENTIFIER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$sun$source$doctree$DocTree$Kind[DocTree.Kind.AUTHOR.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$sun$source$doctree$DocTree$Kind[DocTree.Kind.DEPRECATED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$sun$source$doctree$DocTree$Kind[DocTree.Kind.HIDDEN.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$sun$source$doctree$DocTree$Kind[DocTree.Kind.PARAM.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$sun$source$doctree$DocTree$Kind[DocTree.Kind.PROVIDES.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$sun$source$doctree$DocTree$Kind[DocTree.Kind.RETURN.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$sun$source$doctree$DocTree$Kind[DocTree.Kind.SEE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$sun$source$doctree$DocTree$Kind[DocTree.Kind.SERIAL.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$sun$source$doctree$DocTree$Kind[DocTree.Kind.SERIAL_DATA.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$sun$source$doctree$DocTree$Kind[DocTree.Kind.SERIAL_FIELD.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$sun$source$doctree$DocTree$Kind[DocTree.Kind.SINCE.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$sun$source$doctree$DocTree$Kind[DocTree.Kind.THROWS.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$sun$source$doctree$DocTree$Kind[DocTree.Kind.UNKNOWN_BLOCK_TAG.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$sun$source$doctree$DocTree$Kind[DocTree.Kind.USES.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$sun$source$doctree$DocTree$Kind[DocTree.Kind.VERSION.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$sun$source$doctree$DocTree$Kind[DocTree.Kind.ENTITY.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$sun$source$doctree$DocTree$Kind[DocTree.Kind.COMMENT.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$sun$source$doctree$DocTree$Kind[DocTree.Kind.ATTRIBUTE.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$sun$source$doctree$DocTree$Kind[DocTree.Kind.DOC_COMMENT.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
        }
    }

    public static class DCAttribute extends DCTree implements AttributeTree {
        public final Name name;
        public final List<DCTree> value;
        public final AttributeTree.ValueKind vkind;

        public DCAttribute(Name name, AttributeTree.ValueKind valueKind, List<DCTree> list) {
            boolean z = false;
            if (valueKind != AttributeTree.ValueKind.EMPTY ? list != null : list == null) {
                z = true;
            }
            Assert.check(z);
            this.name = name;
            this.vkind = valueKind;
            this.value = list;
        }

        @Override // com.sun.source.doctree.DocTree
        public <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d) {
            return docTreeVisitor.visitAttribute(this, d);
        }

        @Override // com.sun.source.doctree.DocTree
        public DocTree.Kind getKind() {
            return DocTree.Kind.ATTRIBUTE;
        }

        @Override // com.sun.source.doctree.AttributeTree
        public Name getName() {
            return this.name;
        }

        @Override // com.sun.source.doctree.AttributeTree
        public List<DCTree> getValue() {
            return this.value;
        }

        @Override // com.sun.source.doctree.AttributeTree
        public AttributeTree.ValueKind getValueKind() {
            return this.vkind;
        }
    }

    public static class DCAuthor extends DCBlockTag implements AuthorTree {
        public final List<DCTree> name;

        public DCAuthor(List<DCTree> list) {
            this.name = list;
        }

        @Override // com.sun.source.doctree.DocTree
        public <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d) {
            return docTreeVisitor.visitAuthor(this, d);
        }

        @Override // com.sun.source.doctree.DocTree
        public DocTree.Kind getKind() {
            return DocTree.Kind.AUTHOR;
        }

        @Override // com.sun.source.doctree.AuthorTree
        public List<? extends DocTree> getName() {
            return this.name;
        }
    }

    public static abstract class DCBlockTag extends DCTree implements BlockTagTree {
        @Override // com.sun.source.doctree.BlockTagTree
        public String getTagName() {
            return getKind().tagName;
        }
    }

    public static class DCComment extends DCTree implements CommentTree {
        public final String body;

        public DCComment(String str) {
            this.body = str;
        }

        @Override // com.sun.source.doctree.DocTree
        public <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d) {
            return docTreeVisitor.visitComment(this, d);
        }

        @Override // com.sun.source.doctree.CommentTree
        public String getBody() {
            return this.body;
        }

        @Override // com.sun.source.doctree.DocTree
        public DocTree.Kind getKind() {
            return DocTree.Kind.COMMENT;
        }
    }

    public static class DCDeprecated extends DCBlockTag implements DeprecatedTree {
        public final List<DCTree> body;

        public DCDeprecated(List<DCTree> list) {
            this.body = list;
        }

        @Override // com.sun.source.doctree.DocTree
        public <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d) {
            return docTreeVisitor.visitDeprecated(this, d);
        }

        @Override // com.sun.source.doctree.DeprecatedTree
        public List<? extends DocTree> getBody() {
            return this.body;
        }

        @Override // com.sun.source.doctree.DocTree
        public DocTree.Kind getKind() {
            return DocTree.Kind.DEPRECATED;
        }
    }

    public static class DCDocComment extends DCTree implements DocCommentTree {
        public final List<DCTree> body;
        public final Tokens.Comment comment;
        public final List<DCTree> firstSentence;
        public final List<DCTree> fullBody;
        public final List<DCTree> postamble;
        public final List<DCTree> preamble;
        public final List<DCTree> tags;

        public DCDocComment(Tokens.Comment comment, List<DCTree> list, List<DCTree> list2, List<DCTree> list3, List<DCTree> list4, List<DCTree> list5, List<DCTree> list6) {
            this.comment = comment;
            this.firstSentence = list2;
            this.fullBody = list;
            this.body = list3;
            this.tags = list4;
            this.preamble = list5;
            this.postamble = list6;
        }

        @Override // com.sun.source.doctree.DocTree
        public <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d) {
            return docTreeVisitor.visitDocComment(this, d);
        }

        @Override // com.sun.source.doctree.DocCommentTree
        public List<? extends DocTree> getBlockTags() {
            return this.tags;
        }

        @Override // com.sun.source.doctree.DocCommentTree
        public List<? extends DocTree> getBody() {
            return this.body;
        }

        @Override // com.sun.source.doctree.DocCommentTree
        public List<? extends DocTree> getFirstSentence() {
            return this.firstSentence;
        }

        @Override // com.sun.source.doctree.DocCommentTree
        public List<? extends DocTree> getFullBody() {
            return this.fullBody;
        }

        @Override // com.sun.source.doctree.DocTree
        public DocTree.Kind getKind() {
            return DocTree.Kind.DOC_COMMENT;
        }

        @Override // com.sun.source.doctree.DocCommentTree
        public List<? extends DocTree> getPostamble() {
            return this.postamble;
        }

        @Override // com.sun.source.doctree.DocCommentTree
        public List<? extends DocTree> getPreamble() {
            return this.preamble;
        }

        public int getSourcePosition(int i) {
            return this.comment.getSourcePos(i);
        }
    }

    public static class DCDocRoot extends DCInlineTag<DCDocRoot> implements DocRootTree {
        @Override // com.sun.source.doctree.DocTree
        public <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d) {
            return docTreeVisitor.visitDocRoot(this, d);
        }

        @Override // com.sun.source.doctree.DocTree
        public DocTree.Kind getKind() {
            return DocTree.Kind.DOC_ROOT;
        }
    }

    public static class DCDocType extends DCTree implements DocTypeTree {
        public final String text;

        public DCDocType(String str) {
            this.text = str;
        }

        @Override // com.sun.source.doctree.DocTree
        public <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d) {
            return docTreeVisitor.visitDocType(this, d);
        }

        @Override // com.sun.source.doctree.DocTree
        public DocTree.Kind getKind() {
            return DocTree.Kind.DOC_TYPE;
        }

        @Override // com.sun.source.doctree.DocTypeTree
        public String getText() {
            return this.text;
        }
    }

    public static class DCEndElement extends DCEndPosTree<DCEndElement> implements EndElementTree {
        public final Name name;

        public DCEndElement(Name name) {
            this.name = name;
        }

        @Override // com.sun.source.doctree.DocTree
        public <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d) {
            return docTreeVisitor.visitEndElement(this, d);
        }

        @Override // com.sun.source.doctree.DocTree
        public DocTree.Kind getKind() {
            return DocTree.Kind.END_ELEMENT;
        }

        @Override // com.sun.source.doctree.EndElementTree
        public Name getName() {
            return this.name;
        }
    }

    public static abstract class DCEndPosTree<T extends DCEndPosTree<T>> extends DCTree {
        private int endPos = -1;

        public int getEndPos() {
            return this.endPos;
        }

        public T setEndPos(int i) {
            this.endPos = i;
            return this;
        }
    }

    public static class DCEntity extends DCTree implements EntityTree {
        public final Name name;

        public DCEntity(Name name) {
            this.name = name;
        }

        @Override // com.sun.source.doctree.DocTree
        public <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d) {
            return docTreeVisitor.visitEntity(this, d);
        }

        @Override // com.sun.source.doctree.DocTree
        public DocTree.Kind getKind() {
            return DocTree.Kind.ENTITY;
        }

        @Override // com.sun.source.doctree.EntityTree
        public Name getName() {
            return this.name;
        }
    }

    public static class DCErroneous extends DCTree implements ErroneousTree {
        public final String body;
        public final JCDiagnostic diag;
        private int prefPos = -1;

        public DCErroneous(String str, JCDiagnostic jCDiagnostic) {
            this.body = str;
            this.diag = jCDiagnostic;
        }

        @Override // com.sun.source.doctree.DocTree
        public <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d) {
            return docTreeVisitor.visitErroneous(this, d);
        }

        @Override // com.sun.source.doctree.TextTree
        public String getBody() {
            return this.body;
        }

        @Override // com.sun.source.doctree.ErroneousTree
        public Diagnostic<JavaFileObject> getDiagnostic() {
            return this.diag;
        }

        @Override // com.sun.tools.javac.tree.DCTree
        public int getEndPosition() {
            return this.pos + this.body.length();
        }

        @Override // com.sun.source.doctree.DocTree
        public DocTree.Kind getKind() {
            return DocTree.Kind.ERRONEOUS;
        }

        @Override // com.sun.tools.javac.tree.DCTree
        public int getPreferredPosition() {
            int i = this.prefPos;
            return i == -1 ? (this.pos + this.body.length()) - 1 : i;
        }

        @Override // com.sun.tools.javac.tree.DCTree
        public int getStartPosition() {
            return this.pos;
        }

        public DCErroneous setPrefPos(int i) {
            this.prefPos = i;
            return this;
        }
    }

    public static class DCEscape extends DCTree implements EscapeTree {
        public final char ch;

        public DCEscape(char c) {
            this.ch = c;
        }

        @Override // com.sun.source.doctree.DocTree
        public <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d) {
            return docTreeVisitor.visitEscape(this, d);
        }

        @Override // com.sun.source.doctree.EscapeTree, com.sun.source.doctree.TextTree
        public String getBody() {
            return String.valueOf(this.ch);
        }

        @Override // com.sun.source.doctree.DocTree
        public DocTree.Kind getKind() {
            return DocTree.Kind.ESCAPE;
        }
    }

    public static class DCHidden extends DCBlockTag implements HiddenTree {
        public final List<DCTree> body;

        public DCHidden(List<DCTree> list) {
            this.body = list;
        }

        @Override // com.sun.source.doctree.DocTree
        public <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d) {
            return docTreeVisitor.visitHidden(this, d);
        }

        @Override // com.sun.source.doctree.HiddenTree
        public List<? extends DocTree> getBody() {
            return this.body;
        }

        @Override // com.sun.source.doctree.DocTree
        public DocTree.Kind getKind() {
            return DocTree.Kind.HIDDEN;
        }
    }

    public static class DCIdentifier extends DCTree implements IdentifierTree {
        public final Name name;

        public DCIdentifier(Name name) {
            this.name = name;
        }

        @Override // com.sun.source.doctree.DocTree
        public <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d) {
            return docTreeVisitor.visitIdentifier(this, d);
        }

        @Override // com.sun.source.doctree.DocTree
        public DocTree.Kind getKind() {
            return DocTree.Kind.IDENTIFIER;
        }

        @Override // com.sun.source.doctree.IdentifierTree
        public Name getName() {
            return this.name;
        }
    }

    public static class DCIndex extends DCInlineTag<DCIndex> implements IndexTree {
        public final List<DCTree> description;
        public final DCTree term;

        public DCIndex(DCTree dCTree, List<DCTree> list) {
            this.term = dCTree;
            this.description = list;
        }

        @Override // com.sun.source.doctree.DocTree
        public <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d) {
            return docTreeVisitor.visitIndex(this, d);
        }

        @Override // com.sun.source.doctree.IndexTree
        public List<? extends DocTree> getDescription() {
            return this.description;
        }

        @Override // com.sun.source.doctree.DocTree
        public DocTree.Kind getKind() {
            return DocTree.Kind.INDEX;
        }

        @Override // com.sun.source.doctree.IndexTree
        public DocTree getSearchTerm() {
            return this.term;
        }
    }

    public static class DCInheritDoc extends DCInlineTag<DCInheritDoc> implements InheritDocTree {
        public final DCReference supertype;

        public DCInheritDoc(DCReference dCReference) {
            this.supertype = dCReference;
        }

        @Override // com.sun.source.doctree.DocTree
        public <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d) {
            return docTreeVisitor.visitInheritDoc(this, d);
        }

        @Override // com.sun.source.doctree.DocTree
        public DocTree.Kind getKind() {
            return DocTree.Kind.INHERIT_DOC;
        }

        @Override // com.sun.source.doctree.InheritDocTree
        public ReferenceTree getSupertype() {
            return this.supertype;
        }
    }

    public static abstract class DCInlineTag<T extends DCEndPosTree<T>> extends DCEndPosTree<T> implements InlineTagTree {
        @Override // com.sun.source.doctree.InlineTagTree
        public String getTagName() {
            return getKind().tagName;
        }
    }

    public static class DCLink extends DCInlineTag<DCLink> implements LinkTree {
        public final DocTree.Kind kind;
        public final List<DCTree> label;
        public final DCReference ref;

        public DCLink(DocTree.Kind kind, DCReference dCReference, List<DCTree> list) {
            Assert.check(kind == DocTree.Kind.LINK || kind == DocTree.Kind.LINK_PLAIN);
            this.kind = kind;
            this.ref = dCReference;
            this.label = list;
        }

        @Override // com.sun.source.doctree.DocTree
        public <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d) {
            return docTreeVisitor.visitLink(this, d);
        }

        @Override // com.sun.source.doctree.DocTree
        public DocTree.Kind getKind() {
            return this.kind;
        }

        @Override // com.sun.source.doctree.LinkTree
        public List<? extends DocTree> getLabel() {
            return this.label;
        }

        @Override // com.sun.source.doctree.LinkTree
        public ReferenceTree getReference() {
            return this.ref;
        }
    }

    public static class DCParam extends DCBlockTag implements ParamTree {
        public final List<DCTree> description;
        public final boolean isTypeParameter;
        public final DCIdentifier name;

        public DCParam(boolean z, DCIdentifier dCIdentifier, List<DCTree> list) {
            this.isTypeParameter = z;
            this.name = dCIdentifier;
            this.description = list;
        }

        @Override // com.sun.source.doctree.DocTree
        public <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d) {
            return docTreeVisitor.visitParam(this, d);
        }

        @Override // com.sun.source.doctree.ParamTree
        public List<? extends DocTree> getDescription() {
            return this.description;
        }

        @Override // com.sun.source.doctree.DocTree
        public DocTree.Kind getKind() {
            return DocTree.Kind.PARAM;
        }

        @Override // com.sun.source.doctree.ParamTree
        public IdentifierTree getName() {
            return this.name;
        }

        @Override // com.sun.source.doctree.ParamTree
        public boolean isTypeParameter() {
            return this.isTypeParameter;
        }
    }

    public static class DCProvides extends DCBlockTag implements ProvidesTree {
        public final List<DCTree> description;
        public final DCReference serviceType;

        public DCProvides(DCReference dCReference, List<DCTree> list) {
            this.serviceType = dCReference;
            this.description = list;
        }

        @Override // com.sun.source.doctree.DocTree
        public <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d) {
            return docTreeVisitor.visitProvides(this, d);
        }

        @Override // com.sun.source.doctree.ProvidesTree
        public List<? extends DocTree> getDescription() {
            return this.description;
        }

        @Override // com.sun.source.doctree.DocTree
        public DocTree.Kind getKind() {
            return DocTree.Kind.PROVIDES;
        }

        @Override // com.sun.source.doctree.ProvidesTree
        public ReferenceTree getServiceType() {
            return this.serviceType;
        }
    }

    public static class DCRawText extends DCTree implements RawTextTree {
        public final String code;
        public final DocTree.Kind kind;

        public DCRawText(DocTree.Kind kind, String str) {
            if (kind != DocTree.Kind.MARKDOWN) {
                throw new IllegalArgumentException(String.valueOf(kind));
            }
            this.kind = kind;
            this.code = str;
        }

        @Override // com.sun.source.doctree.DocTree
        public <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d) {
            return docTreeVisitor.visitRawText(this, d);
        }

        @Override // com.sun.source.doctree.RawTextTree
        public String getContent() {
            return this.code;
        }

        @Override // com.sun.source.doctree.DocTree
        public DocTree.Kind getKind() {
            return DocTree.Kind.MARKDOWN;
        }

        @Override // com.sun.tools.javac.tree.DCTree
        public boolean isBlank() {
            return StringWrapper.isBlank(this.code);
        }
    }

    public static class DCReference extends DCEndPosTree<DCReference> implements ReferenceTree {
        public final Name memberName;
        public final JCTree.JCExpression moduleName;
        public final List<JCTree> paramTypes;
        public final JCTree qualifierExpression;
        public final String signature;

        public DCReference(String str, JCTree.JCExpression jCExpression, JCTree jCTree, Name name, List<JCTree> list) {
            this.signature = str;
            this.moduleName = jCExpression;
            this.qualifierExpression = jCTree;
            this.memberName = name;
            this.paramTypes = list;
        }

        @Override // com.sun.source.doctree.DocTree
        public <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d) {
            return docTreeVisitor.visitReference(this, d);
        }

        @Override // com.sun.source.doctree.DocTree
        public DocTree.Kind getKind() {
            return DocTree.Kind.REFERENCE;
        }

        @Override // com.sun.source.doctree.ReferenceTree
        public String getSignature() {
            return this.signature;
        }
    }

    public static class DCReturn extends DCEndPosTree<DCReturn> implements ReturnTree {
        public final List<DCTree> description;
        public final boolean inline;

        public DCReturn(boolean z, List<DCTree> list) {
            this.inline = z;
            this.description = list;
        }

        @Override // com.sun.source.doctree.DocTree
        public <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d) {
            return docTreeVisitor.visitReturn(this, d);
        }

        @Override // com.sun.source.doctree.ReturnTree
        public List<? extends DocTree> getDescription() {
            return this.description;
        }

        @Override // com.sun.source.doctree.DocTree
        public DocTree.Kind getKind() {
            return DocTree.Kind.RETURN;
        }

        @Override // com.sun.source.doctree.BlockTagTree
        public String getTagName() {
            return PsiKeyword.RETURN;
        }

        @Override // com.sun.source.doctree.ReturnTree
        public boolean isInline() {
            return this.inline;
        }
    }

    public static class DCSee extends DCBlockTag implements SeeTree {
        public final List<DCTree> reference;

        public DCSee(List<DCTree> list) {
            this.reference = list;
        }

        @Override // com.sun.source.doctree.DocTree
        public <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d) {
            return docTreeVisitor.visitSee(this, d);
        }

        @Override // com.sun.source.doctree.DocTree
        public DocTree.Kind getKind() {
            return DocTree.Kind.SEE;
        }

        @Override // com.sun.source.doctree.SeeTree
        public List<? extends DocTree> getReference() {
            return this.reference;
        }
    }

    public static class DCSerial extends DCBlockTag implements SerialTree {
        public final List<DCTree> description;

        public DCSerial(List<DCTree> list) {
            this.description = list;
        }

        @Override // com.sun.source.doctree.DocTree
        public <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d) {
            return docTreeVisitor.visitSerial(this, d);
        }

        @Override // com.sun.source.doctree.SerialTree
        public List<? extends DocTree> getDescription() {
            return this.description;
        }

        @Override // com.sun.source.doctree.DocTree
        public DocTree.Kind getKind() {
            return DocTree.Kind.SERIAL;
        }
    }

    public static class DCSerialData extends DCBlockTag implements SerialDataTree {
        public final List<DCTree> description;

        public DCSerialData(List<DCTree> list) {
            this.description = list;
        }

        @Override // com.sun.source.doctree.DocTree
        public <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d) {
            return docTreeVisitor.visitSerialData(this, d);
        }

        @Override // com.sun.source.doctree.SerialDataTree
        public List<? extends DocTree> getDescription() {
            return this.description;
        }

        @Override // com.sun.source.doctree.DocTree
        public DocTree.Kind getKind() {
            return DocTree.Kind.SERIAL_DATA;
        }
    }

    public static class DCSerialField extends DCBlockTag implements SerialFieldTree {
        public final List<DCTree> description;
        public final DCIdentifier name;
        public final DCReference type;

        public DCSerialField(DCIdentifier dCIdentifier, DCReference dCReference, List<DCTree> list) {
            this.description = list;
            this.name = dCIdentifier;
            this.type = dCReference;
        }

        @Override // com.sun.source.doctree.DocTree
        public <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d) {
            return docTreeVisitor.visitSerialField(this, d);
        }

        @Override // com.sun.source.doctree.SerialFieldTree
        public List<? extends DocTree> getDescription() {
            return this.description;
        }

        @Override // com.sun.source.doctree.DocTree
        public DocTree.Kind getKind() {
            return DocTree.Kind.SERIAL_FIELD;
        }

        @Override // com.sun.source.doctree.SerialFieldTree
        public IdentifierTree getName() {
            return this.name;
        }

        @Override // com.sun.source.doctree.SerialFieldTree
        public ReferenceTree getType() {
            return this.type;
        }
    }

    public static class DCSince extends DCBlockTag implements SinceTree {
        public final List<DCTree> body;

        public DCSince(List<DCTree> list) {
            this.body = list;
        }

        @Override // com.sun.source.doctree.DocTree
        public <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d) {
            return docTreeVisitor.visitSince(this, d);
        }

        @Override // com.sun.source.doctree.SinceTree
        public List<? extends DocTree> getBody() {
            return this.body;
        }

        @Override // com.sun.source.doctree.DocTree
        public DocTree.Kind getKind() {
            return DocTree.Kind.SINCE;
        }
    }

    public static class DCSnippet extends DCInlineTag<DCSnippet> implements SnippetTree {
        public final List<? extends DocTree> attributes;
        public final DCText body;

        public DCSnippet(List<DCTree> list, DCText dCText) {
            this.body = dCText;
            this.attributes = list;
        }

        @Override // com.sun.source.doctree.DocTree
        public <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d) {
            return docTreeVisitor.visitSnippet(this, d);
        }

        @Override // com.sun.source.doctree.SnippetTree
        public List<? extends DocTree> getAttributes() {
            return this.attributes;
        }

        @Override // com.sun.source.doctree.SnippetTree
        public TextTree getBody() {
            return this.body;
        }

        @Override // com.sun.source.doctree.DocTree
        public DocTree.Kind getKind() {
            return DocTree.Kind.SNIPPET;
        }
    }

    public static class DCSpec extends DCBlockTag implements SpecTree {
        public final List<DCTree> title;
        public final DCText uri;

        public DCSpec(DCText dCText, List<DCTree> list) {
            this.uri = dCText;
            this.title = list;
        }

        @Override // com.sun.source.doctree.DocTree
        public <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d) {
            return docTreeVisitor.visitSpec(this, d);
        }

        @Override // com.sun.source.doctree.DocTree
        public DocTree.Kind getKind() {
            return DocTree.Kind.SPEC;
        }

        @Override // com.sun.tools.javac.tree.DCTree.DCBlockTag, com.sun.source.doctree.BlockTagTree
        public String getTagName() {
            return "spec";
        }

        @Override // com.sun.source.doctree.SpecTree
        public List<? extends DocTree> getTitle() {
            return this.title;
        }

        @Override // com.sun.source.doctree.SpecTree
        public TextTree getURL() {
            return this.uri;
        }
    }

    public static class DCStartElement extends DCEndPosTree<DCStartElement> implements StartElementTree {
        public final List<DCTree> attrs;
        public final Name name;
        public final boolean selfClosing;

        public DCStartElement(Name name, List<DCTree> list, boolean z) {
            this.name = name;
            this.attrs = list;
            this.selfClosing = z;
        }

        @Override // com.sun.source.doctree.DocTree
        public <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d) {
            return docTreeVisitor.visitStartElement(this, d);
        }

        @Override // com.sun.source.doctree.StartElementTree
        public List<? extends DocTree> getAttributes() {
            return this.attrs;
        }

        @Override // com.sun.source.doctree.DocTree
        public DocTree.Kind getKind() {
            return DocTree.Kind.START_ELEMENT;
        }

        @Override // com.sun.source.doctree.StartElementTree
        public Name getName() {
            return this.name;
        }

        @Override // com.sun.source.doctree.StartElementTree
        public boolean isSelfClosing() {
            return this.selfClosing;
        }
    }

    public static class DCSummary extends DCInlineTag<DCSummary> implements SummaryTree {
        public final List<DCTree> summary;

        public DCSummary(List<DCTree> list) {
            this.summary = list;
        }

        @Override // com.sun.source.doctree.DocTree
        public <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d) {
            return docTreeVisitor.visitSummary(this, d);
        }

        @Override // com.sun.source.doctree.DocTree
        public DocTree.Kind getKind() {
            return DocTree.Kind.SUMMARY;
        }

        @Override // com.sun.source.doctree.SummaryTree
        public List<? extends DocTree> getSummary() {
            return this.summary;
        }
    }

    public static class DCSystemProperty extends DCInlineTag<DCSystemProperty> implements SystemPropertyTree {
        public final Name propertyName;

        public DCSystemProperty(Name name) {
            this.propertyName = name;
        }

        @Override // com.sun.source.doctree.DocTree
        public <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d) {
            return docTreeVisitor.visitSystemProperty(this, d);
        }

        @Override // com.sun.source.doctree.DocTree
        public DocTree.Kind getKind() {
            return DocTree.Kind.SYSTEM_PROPERTY;
        }

        @Override // com.sun.source.doctree.SystemPropertyTree
        public Name getPropertyName() {
            return this.propertyName;
        }
    }

    public static class DCText extends DCTree implements TextTree {
        public final String text;

        public DCText(String str) {
            this.text = str;
        }

        @Override // com.sun.source.doctree.DocTree
        public <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d) {
            return docTreeVisitor.visitText(this, d);
        }

        @Override // com.sun.source.doctree.TextTree
        public String getBody() {
            return this.text;
        }

        @Override // com.sun.source.doctree.DocTree
        public DocTree.Kind getKind() {
            return DocTree.Kind.TEXT;
        }

        @Override // com.sun.tools.javac.tree.DCTree
        public boolean isBlank() {
            return StringWrapper.isBlank(this.text);
        }
    }

    public static class DCThrows extends DCBlockTag implements ThrowsTree {
        public final List<DCTree> description;
        public final DocTree.Kind kind;
        public final DCReference name;

        public DCThrows(DocTree.Kind kind, DCReference dCReference, List<DCTree> list) {
            Assert.check(kind == DocTree.Kind.EXCEPTION || kind == DocTree.Kind.THROWS);
            this.kind = kind;
            this.name = dCReference;
            this.description = list;
        }

        @Override // com.sun.source.doctree.DocTree
        public <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d) {
            return docTreeVisitor.visitThrows(this, d);
        }

        @Override // com.sun.source.doctree.ThrowsTree
        public List<? extends DocTree> getDescription() {
            return this.description;
        }

        @Override // com.sun.source.doctree.ThrowsTree
        public ReferenceTree getExceptionName() {
            return this.name;
        }

        @Override // com.sun.source.doctree.DocTree
        public DocTree.Kind getKind() {
            return this.kind;
        }
    }

    public static class DCUnknownBlockTag extends DCBlockTag implements UnknownBlockTagTree {
        public final List<DCTree> content;
        public final Name name;

        public DCUnknownBlockTag(Name name, List<DCTree> list) {
            this.name = name;
            this.content = list;
        }

        @Override // com.sun.source.doctree.DocTree
        public <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d) {
            return docTreeVisitor.visitUnknownBlockTag(this, d);
        }

        @Override // com.sun.source.doctree.UnknownBlockTagTree
        public List<? extends DocTree> getContent() {
            return this.content;
        }

        @Override // com.sun.source.doctree.DocTree
        public DocTree.Kind getKind() {
            return DocTree.Kind.UNKNOWN_BLOCK_TAG;
        }

        @Override // com.sun.tools.javac.tree.DCTree.DCBlockTag, com.sun.source.doctree.BlockTagTree
        public String getTagName() {
            return this.name.toString();
        }
    }

    public static class DCUnknownInlineTag extends DCInlineTag<DCUnknownInlineTag> implements UnknownInlineTagTree {
        public final List<DCTree> content;
        public final Name name;

        public DCUnknownInlineTag(Name name, List<DCTree> list) {
            this.name = name;
            this.content = list;
        }

        @Override // com.sun.source.doctree.DocTree
        public <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d) {
            return docTreeVisitor.visitUnknownInlineTag(this, d);
        }

        @Override // com.sun.source.doctree.UnknownInlineTagTree
        public List<? extends DocTree> getContent() {
            return this.content;
        }

        @Override // com.sun.source.doctree.DocTree
        public DocTree.Kind getKind() {
            return DocTree.Kind.UNKNOWN_INLINE_TAG;
        }

        @Override // com.sun.tools.javac.tree.DCTree.DCInlineTag, com.sun.source.doctree.InlineTagTree
        public String getTagName() {
            return this.name.toString();
        }
    }

    public static class DCUses extends DCBlockTag implements UsesTree {
        public final List<DCTree> description;
        public final DCReference serviceType;

        public DCUses(DCReference dCReference, List<DCTree> list) {
            this.serviceType = dCReference;
            this.description = list;
        }

        @Override // com.sun.source.doctree.DocTree
        public <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d) {
            return docTreeVisitor.visitUses(this, d);
        }

        @Override // com.sun.source.doctree.UsesTree
        public List<? extends DocTree> getDescription() {
            return this.description;
        }

        @Override // com.sun.source.doctree.DocTree
        public DocTree.Kind getKind() {
            return DocTree.Kind.USES;
        }

        @Override // com.sun.source.doctree.UsesTree
        public ReferenceTree getServiceType() {
            return this.serviceType;
        }
    }

    public static class DCValue extends DCInlineTag<DCValue> implements ValueTree {
        public final DCText format;
        public final DCReference ref;

        public DCValue(DCText dCText, DCReference dCReference) {
            this.format = dCText;
            this.ref = dCReference;
        }

        @Override // com.sun.source.doctree.DocTree
        public <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d) {
            return docTreeVisitor.visitValue(this, d);
        }

        @Override // com.sun.source.doctree.ValueTree
        public TextTree getFormat() {
            return this.format;
        }

        @Override // com.sun.source.doctree.DocTree
        public DocTree.Kind getKind() {
            return DocTree.Kind.VALUE;
        }

        @Override // com.sun.source.doctree.ValueTree
        public ReferenceTree getReference() {
            return this.ref;
        }
    }

    public static class DCVersion extends DCBlockTag implements VersionTree {
        public final List<DCTree> body;

        public DCVersion(List<DCTree> list) {
            this.body = list;
        }

        @Override // com.sun.source.doctree.DocTree
        public <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d) {
            return docTreeVisitor.visitVersion(this, d);
        }

        @Override // com.sun.source.doctree.VersionTree
        public List<? extends DocTree> getBody() {
            return this.body;
        }

        @Override // com.sun.source.doctree.DocTree
        public DocTree.Kind getKind() {
            return DocTree.Kind.VERSION;
        }
    }

    public static JCDiagnostic.DiagnosticPosition createDiagnosticPosition(final Tokens.Comment comment, final int i, final int i2, final int i3) {
        return new JCDiagnostic.DiagnosticPosition() { // from class: com.sun.tools.javac.tree.DCTree.2
            @Override // com.sun.tools.javac.util.JCDiagnostic.DiagnosticPosition
            public int getEndPosition(EndPosTable endPosTable) {
                return comment.getSourcePos(i3);
            }

            @Override // com.sun.tools.javac.util.JCDiagnostic.DiagnosticPosition
            public int getPreferredPosition() {
                return comment.getSourcePos(i2);
            }

            @Override // com.sun.tools.javac.util.JCDiagnostic.DiagnosticPosition
            public int getStartPosition() {
                return comment.getSourcePos(i);
            }

            @Override // com.sun.tools.javac.util.JCDiagnostic.DiagnosticPosition
            public JCTree getTree() {
                return null;
            }
        };
    }

    private DCTree getLastChild() {
        final DCTree[] dCTreeArr = {null};
        accept(new DocTreeScanner<Void, Void>(this) { // from class: com.sun.tools.javac.tree.DCTree.1
            final /* synthetic */ DCTree this$0;

            {
                this.this$0 = this;
            }

            @Override // com.sun.source.util.DocTreeScanner
            public Void scan(DocTree docTree, Void r2) {
                if (!(docTree instanceof DCTree)) {
                    return null;
                }
                dCTreeArr[0] = (DCTree) docTree;
                return null;
            }
        }, null);
        return dCTreeArr[0];
    }

    public static boolean isBlank(List<? extends DCTree> list) {
        return list.stream().allMatch(new Predicate() { // from class: m73
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((DCTree) obj).isBlank();
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int getEndPosition() {
        int endPos;
        if ((this instanceof DCEndPosTree) && (endPos = ((DCEndPosTree) this).getEndPos()) != -1) {
            return endPos;
        }
        int i = 0;
        switch (AnonymousClass3.$SwitchMap$com$sun$source$doctree$DocTree$Kind[getKind().ordinal()]) {
            case 1:
                DCRawText dCRawText = (DCRawText) this;
                return dCRawText.pos + dCRawText.code.length();
            case 2:
                DCText dCText = (DCText) this;
                return dCText.pos + dCText.text.length();
            case 3:
                DCErroneous dCErroneous = (DCErroneous) this;
                return dCErroneous.pos + dCErroneous.body.length();
            case 4:
                return ((DCEscape) this).pos + 2;
            case 5:
                DCIdentifier dCIdentifier = (DCIdentifier) this;
                return dCIdentifier.pos + dCIdentifier.name.length();
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
                DCTree lastChild = getLastChild();
                if (lastChild == null) {
                    return this.pos + ((BlockTagTree) this).getTagName().length() + 1;
                }
                if (this instanceof DCParam) {
                    DCParam dCParam = (DCParam) this;
                    if (dCParam.isTypeParameter && dCParam.getDescription().isEmpty()) {
                        i = 1;
                    }
                }
                return lastChild.getEndPosition() + i;
            case 21:
                DCEntity dCEntity = (DCEntity) this;
                return dCEntity.pos + dCEntity.name.length() + 2;
            case 22:
                DCComment dCComment = (DCComment) this;
                return dCComment.pos + dCComment.body.length();
            case 23:
                DCAttribute dCAttribute = (DCAttribute) this;
                if (dCAttribute.vkind == AttributeTree.ValueKind.EMPTY) {
                    return dCAttribute.pos + dCAttribute.name.length();
                }
                DCTree lastChild2 = getLastChild();
                if (lastChild2 != null) {
                    return lastChild2.getEndPosition() + (dCAttribute.vkind != AttributeTree.ValueKind.UNQUOTED ? 1 : 0);
                }
                return -1;
            case 24:
                DCDocComment dCDocComment = (DCDocComment) this;
                DCTree lastChild3 = getLastChild();
                return lastChild3 == null ? dCDocComment.pos : lastChild3.getEndPosition();
            default:
                DCTree lastChild4 = getLastChild();
                if (lastChild4 != null) {
                    return lastChild4.getEndPosition();
                }
                return -1;
        }
    }

    public int getPreferredPosition() {
        return this.pos;
    }

    public int getStartPosition() {
        return this.pos;
    }

    public JCDiagnostic.DiagnosticPosition pos(DCDocComment dCDocComment) {
        return createDiagnosticPosition(dCDocComment.comment, getStartPosition(), getPreferredPosition(), getEndPosition());
    }

    public String toString() {
        StringWriter stringWriter = new StringWriter();
        try {
            new DocPretty(stringWriter).print((DocTree) this);
            return stringWriter.toString();
        } catch (IOException e) {
            x01.a(e);
            return null;
        }
    }

    public static class DCLiteral extends DCInlineTag<DCLiteral> implements LiteralTree {
        public final DCText body;
        public final DocTree.Kind kind;

        public DCLiteral(DocTree.Kind kind, DCText dCText) {
            Assert.check(kind == DocTree.Kind.CODE || kind == DocTree.Kind.LITERAL);
            this.kind = kind;
            this.body = dCText;
        }

        @Override // com.sun.source.doctree.DocTree
        public <R, D> R accept(DocTreeVisitor<R, D> docTreeVisitor, D d) {
            return docTreeVisitor.visitLiteral(this, d);
        }

        @Override // com.sun.source.doctree.DocTree
        public DocTree.Kind getKind() {
            return this.kind;
        }

        @Override // com.sun.source.doctree.LiteralTree
        public DCText getBody() {
            return this.body;
        }
    }

    public boolean isBlank() {
        return false;
    }
}
