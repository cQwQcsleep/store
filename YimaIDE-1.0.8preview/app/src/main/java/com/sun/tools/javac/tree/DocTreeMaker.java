package com.sun.tools.javac.tree;

import com.sun.org.apache.xml.internal.utils.LocaleUtility;
import com.sun.source.doctree.AttributeTree;
import com.sun.source.doctree.AuthorTree;
import com.sun.source.doctree.DeprecatedTree;
import com.sun.source.doctree.DocCommentTree;
import com.sun.source.doctree.DocTree;
import com.sun.source.doctree.EndElementTree;
import com.sun.source.doctree.ErroneousTree;
import com.sun.source.doctree.HiddenTree;
import com.sun.source.doctree.IdentifierTree;
import com.sun.source.doctree.IndexTree;
import com.sun.source.doctree.LinkTree;
import com.sun.source.doctree.ParamTree;
import com.sun.source.doctree.ProvidesTree;
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
import com.sun.source.doctree.TextTree;
import com.sun.source.doctree.ThrowsTree;
import com.sun.source.doctree.UnknownBlockTagTree;
import com.sun.source.doctree.UnknownInlineTagTree;
import com.sun.source.doctree.UsesTree;
import com.sun.source.doctree.VersionTree;
import com.sun.source.util.DocTreeFactory;
import com.sun.tools.javac.api.JavacTrees;
import com.sun.tools.javac.parser.ParserFactory;
import com.sun.tools.javac.parser.ReferenceParser;
import com.sun.tools.javac.parser.Tokens;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Pair;
import com.sun.tools.javac.util.StringUtils;
import defpackage.nrd;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.lang.model.element.Name;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import nbjavac.StringWrapper;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DocTreeMaker implements DocTreeFactory {
    protected static final Context.Key<DocTreeMaker> treeMakerKey = new Context.Key<>();
    private final SentenceBreaker breaker;
    public int pos;
    private final ReferenceParser referenceParser;
    private final JavacTrees trees;

    /* JADX INFO: renamed from: com.sun.tools.javac.tree.DocTreeMaker$2, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$source$doctree$DocTree$Kind;

        static {
            int[] iArr = new int[DocTree.Kind.values().length];
            $SwitchMap$com$sun$source$doctree$DocTree$Kind = iArr;
            try {
                iArr[DocTree.Kind.RETURN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$source$doctree$DocTree$Kind[DocTree.Kind.SUMMARY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$source$doctree$DocTree$Kind[DocTree.Kind.TEXT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sun$source$doctree$DocTree$Kind[DocTree.Kind.MARKDOWN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$sun$source$doctree$DocTree$Kind[DocTree.Kind.START_ELEMENT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$sun$source$doctree$DocTree$Kind[DocTree.Kind.END_ELEMENT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public DocTreeMaker(Context context) {
        context.put(treeMakerKey, this);
        this.pos = -1;
        this.trees = JavacTrees.instance(context);
        this.referenceParser = new ReferenceParser(ParserFactory.instance(context));
        this.breaker = new SentenceBreaker(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static List<DCTree> cast(List<? extends DocTree> list) {
        return list;
    }

    public static DocTreeMaker instance(Context context) {
        DocTreeMaker docTreeMaker = (DocTreeMaker) context.get(treeMakerKey);
        return docTreeMaker == null ? new DocTreeMaker(context) : docTreeMaker;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public List<DocTree> getFirstSentence(List<? extends DocTree> list) {
        return new ArrayList(splitBody(list).fst);
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCAttribute newAttributeTree(Name name, AttributeTree.ValueKind valueKind, List<? extends DocTree> list) {
        DCTree.DCAttribute dCAttribute = new DCTree.DCAttribute(name, valueKind, cast(list));
        dCAttribute.pos = this.pos;
        return dCAttribute;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCAuthor newAuthorTree(List<? extends DocTree> list) {
        DCTree.DCAuthor dCAuthor = new DCTree.DCAuthor(cast(list));
        dCAuthor.pos = this.pos;
        return dCAuthor;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCLiteral newCodeTree(TextTree textTree) {
        DCTree.DCLiteral dCLiteral = new DCTree.DCLiteral(DocTree.Kind.CODE, (DCTree.DCText) textTree);
        dCLiteral.pos = this.pos;
        return dCLiteral;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCComment newCommentTree(String str) {
        DCTree.DCComment dCComment = new DCTree.DCComment(str);
        dCComment.pos = this.pos;
        return dCComment;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCDeprecated newDeprecatedTree(List<? extends DocTree> list) {
        DCTree.DCDeprecated dCDeprecated = new DCTree.DCDeprecated(cast(list));
        dCDeprecated.pos = this.pos;
        return dCDeprecated;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCDocComment newDocCommentTree(List<? extends DocTree> list, List<? extends DocTree> list2, List<? extends DocTree> list3, List<? extends DocTree> list4) {
        ListBuffer listBuffer = new ListBuffer();
        listBuffer.addAll(cast(list));
        com.sun.tools.javac.util.List list5 = listBuffer.toList();
        Tokens.Comment comment = new Tokens.Comment() { // from class: com.sun.tools.javac.tree.DocTreeMaker.1
            @Override // com.sun.tools.javac.parser.Tokens.Comment
            public JCDiagnostic.DiagnosticPosition getPos() {
                return null;
            }

            @Override // com.sun.tools.javac.parser.Tokens.Comment
            public int getSourcePos(int i) {
                return -1;
            }

            @Override // com.sun.tools.javac.parser.Tokens.Comment
            public Tokens.Comment.CommentStyle getStyle() {
                throw new UnsupportedOperationException(getClass() + ".getStyle");
            }

            @Override // com.sun.tools.javac.parser.Tokens.Comment
            public String getText() {
                throw new UnsupportedOperationException(getClass() + ".getText");
            }

            @Override // com.sun.tools.javac.parser.Tokens.Comment
            public boolean isDeprecated() {
                throw new UnsupportedOperationException(getClass() + ".isDeprecated");
            }

            @Override // com.sun.tools.javac.parser.Tokens.Comment
            public Tokens.Comment stripIndent() {
                return this;
            }
        };
        Pair<List<DCTree>, List<DCTree>> pairSplitBody = splitBody(list);
        return new DCTree.DCDocComment(comment, list5, pairSplitBody.fst, pairSplitBody.snd, cast(list2), cast(list3), cast(list4));
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCDocRoot newDocRootTree() {
        DCTree.DCDocRoot dCDocRoot = new DCTree.DCDocRoot();
        dCDocRoot.pos = this.pos;
        return dCDocRoot;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCDocType newDocTypeTree(String str) {
        DCTree.DCDocType dCDocType = new DCTree.DCDocType(str);
        dCDocType.pos = this.pos;
        return dCDocType;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCEndElement newEndElementTree(Name name) {
        DCTree.DCEndElement dCEndElement = new DCTree.DCEndElement(name);
        dCEndElement.pos = this.pos;
        return dCEndElement;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCEntity newEntityTree(Name name) {
        DCTree.DCEntity dCEntity = new DCTree.DCEntity(name);
        dCEntity.pos = this.pos;
        return dCEntity;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCErroneous newErroneousTree(String str, Diagnostic<JavaFileObject> diagnostic) {
        DCTree.DCErroneous dCErroneous = new DCTree.DCErroneous(str, (JCDiagnostic) diagnostic);
        dCErroneous.pos = this.pos;
        return dCErroneous;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCEscape newEscapeTree(char c) {
        DCTree.DCEscape dCEscape = new DCTree.DCEscape(c);
        dCEscape.pos = this.pos;
        return dCEscape;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCThrows newExceptionTree(ReferenceTree referenceTree, List<? extends DocTree> list) {
        DCTree.DCThrows dCThrows = new DCTree.DCThrows(DocTree.Kind.EXCEPTION, (DCTree.DCReference) referenceTree, cast(list));
        dCThrows.pos = this.pos;
        return dCThrows;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCHidden newHiddenTree(List<? extends DocTree> list) {
        DCTree.DCHidden dCHidden = new DCTree.DCHidden(cast(list));
        dCHidden.pos = this.pos;
        return dCHidden;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCIdentifier newIdentifierTree(Name name) {
        DCTree.DCIdentifier dCIdentifier = new DCTree.DCIdentifier(name);
        dCIdentifier.pos = this.pos;
        return dCIdentifier;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCIndex newIndexTree(DocTree docTree, List<? extends DocTree> list) {
        DCTree.DCIndex dCIndex = new DCTree.DCIndex((DCTree) docTree, cast(list));
        dCIndex.pos = this.pos;
        return dCIndex;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCInheritDoc newInheritDocTree(ReferenceTree referenceTree) {
        DCTree.DCInheritDoc dCInheritDoc = new DCTree.DCInheritDoc((DCTree.DCReference) referenceTree);
        dCInheritDoc.pos = this.pos;
        return dCInheritDoc;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCLink newLinkPlainTree(ReferenceTree referenceTree, List<? extends DocTree> list) {
        DCTree.DCLink dCLink = new DCTree.DCLink(DocTree.Kind.LINK_PLAIN, (DCTree.DCReference) referenceTree, cast(list));
        dCLink.pos = this.pos;
        return dCLink;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCLink newLinkTree(ReferenceTree referenceTree, List<? extends DocTree> list) {
        DCTree.DCLink dCLink = new DCTree.DCLink(DocTree.Kind.LINK, (DCTree.DCReference) referenceTree, cast(list));
        dCLink.pos = this.pos;
        return dCLink;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCLiteral newLiteralTree(TextTree textTree) {
        DCTree.DCLiteral dCLiteral = new DCTree.DCLiteral(DocTree.Kind.LITERAL, (DCTree.DCText) textTree);
        dCLiteral.pos = this.pos;
        return dCLiteral;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCParam newParamTree(boolean z, IdentifierTree identifierTree, List<? extends DocTree> list) {
        DCTree.DCParam dCParam = new DCTree.DCParam(z, (DCTree.DCIdentifier) identifierTree, cast(list));
        dCParam.pos = this.pos;
        return dCParam;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCProvides newProvidesTree(ReferenceTree referenceTree, List<? extends DocTree> list) {
        DCTree.DCProvides dCProvides = new DCTree.DCProvides((DCTree.DCReference) referenceTree, cast(list));
        dCProvides.pos = this.pos;
        return dCProvides;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCRawText newRawTextTree(DocTree.Kind kind, String str) {
        DCTree.DCRawText dCRawText = new DCTree.DCRawText(kind, str);
        dCRawText.pos = this.pos;
        return dCRawText;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCReference newReferenceTree(String str) {
        try {
            DCTree.DCReference dCReferenceNewReferenceTree = newReferenceTree(str, this.referenceParser.parse(str, ReferenceParser.Mode.MEMBER_OPTIONAL));
            dCReferenceNewReferenceTree.pos = this.pos;
            return dCReferenceNewReferenceTree;
        } catch (ReferenceParser.ParseException e) {
            nrd.a("invalid signature", e);
            return null;
        }
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCReturn newReturnTree(boolean z, List<? extends DocTree> list) {
        DCTree.DCReturn dCReturn = new DCTree.DCReturn(z, cast(list));
        dCReturn.pos = this.pos;
        return dCReturn;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCSee newSeeTree(List<? extends DocTree> list) {
        DCTree.DCSee dCSee = new DCTree.DCSee(cast(list));
        dCSee.pos = this.pos;
        return dCSee;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCSerialData newSerialDataTree(List<? extends DocTree> list) {
        DCTree.DCSerialData dCSerialData = new DCTree.DCSerialData(cast(list));
        dCSerialData.pos = this.pos;
        return dCSerialData;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCSerialField newSerialFieldTree(IdentifierTree identifierTree, ReferenceTree referenceTree, List<? extends DocTree> list) {
        DCTree.DCSerialField dCSerialField = new DCTree.DCSerialField((DCTree.DCIdentifier) identifierTree, (DCTree.DCReference) referenceTree, cast(list));
        dCSerialField.pos = this.pos;
        return dCSerialField;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCSerial newSerialTree(List<? extends DocTree> list) {
        DCTree.DCSerial dCSerial = new DCTree.DCSerial(cast(list));
        dCSerial.pos = this.pos;
        return dCSerial;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCSince newSinceTree(List<? extends DocTree> list) {
        DCTree.DCSince dCSince = new DCTree.DCSince(cast(list));
        dCSince.pos = this.pos;
        return dCSince;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCSnippet newSnippetTree(List<? extends DocTree> list, TextTree textTree) {
        DCTree.DCSnippet dCSnippet = new DCTree.DCSnippet(cast(list), (DCTree.DCText) textTree);
        dCSnippet.pos = this.pos;
        return dCSnippet;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCSpec newSpecTree(TextTree textTree, List<? extends DocTree> list) {
        DCTree.DCSpec dCSpec = new DCTree.DCSpec((DCTree.DCText) textTree, cast(list));
        dCSpec.pos = this.pos;
        return dCSpec;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCStartElement newStartElementTree(Name name, List<? extends DocTree> list, boolean z) {
        DCTree.DCStartElement dCStartElement = new DCTree.DCStartElement(name, cast(list), z);
        dCStartElement.pos = this.pos;
        return dCStartElement;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCSummary newSummaryTree(List<? extends DocTree> list) {
        DCTree.DCSummary dCSummary = new DCTree.DCSummary(cast(list));
        dCSummary.pos = this.pos;
        return dCSummary;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCSystemProperty newSystemPropertyTree(Name name) {
        DCTree.DCSystemProperty dCSystemProperty = new DCTree.DCSystemProperty(name);
        dCSystemProperty.pos = this.pos;
        return dCSystemProperty;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCText newTextTree(String str) {
        DCTree.DCText dCText = new DCTree.DCText(str);
        dCText.pos = this.pos;
        return dCText;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCThrows newThrowsTree(ReferenceTree referenceTree, List<? extends DocTree> list) {
        DCTree.DCThrows dCThrows = new DCTree.DCThrows(DocTree.Kind.THROWS, (DCTree.DCReference) referenceTree, cast(list));
        dCThrows.pos = this.pos;
        return dCThrows;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCUnknownBlockTag newUnknownBlockTagTree(Name name, List<? extends DocTree> list) {
        DCTree.DCUnknownBlockTag dCUnknownBlockTag = new DCTree.DCUnknownBlockTag(name, cast(list));
        dCUnknownBlockTag.pos = this.pos;
        return dCUnknownBlockTag;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCUnknownInlineTag newUnknownInlineTagTree(Name name, List<? extends DocTree> list) {
        DCTree.DCUnknownInlineTag dCUnknownInlineTag = new DCTree.DCUnknownInlineTag(name, cast(list));
        dCUnknownInlineTag.pos = this.pos;
        return dCUnknownInlineTag;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCUses newUsesTree(ReferenceTree referenceTree, List<? extends DocTree> list) {
        DCTree.DCUses dCUses = new DCTree.DCUses((DCTree.DCReference) referenceTree, cast(list));
        dCUses.pos = this.pos;
        return dCUses;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCValue newValueTree(TextTree textTree, ReferenceTree referenceTree) {
        DCTree.DCValue dCValue = new DCTree.DCValue((DCTree.DCText) textTree, (DCTree.DCReference) referenceTree);
        dCValue.pos = this.pos;
        return dCValue;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCVersion newVersionTree(List<? extends DocTree> list) {
        DCTree.DCVersion dCVersion = new DCTree.DCVersion(cast(list));
        dCVersion.pos = this.pos;
        return dCVersion;
    }

    public Pair<List<DCTree>, List<DCTree>> splitBody(List<? extends DocTree> list) {
        return this.breaker.splitBody(list);
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DocTreeMaker at(int i) {
        this.pos = i;
        return this;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public /* bridge */ /* synthetic */ ErroneousTree newErroneousTree(String str, Diagnostic diagnostic) {
        return newErroneousTree(str, (Diagnostic<JavaFileObject>) diagnostic);
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCInheritDoc newInheritDocTree() {
        return newInheritDocTree((ReferenceTree) null);
    }

    @Override // com.sun.source.util.DocTreeFactory
    public /* bridge */ /* synthetic */ AttributeTree newAttributeTree(Name name, AttributeTree.ValueKind valueKind, List list) {
        return newAttributeTree(name, valueKind, (List<? extends DocTree>) list);
    }

    @Override // com.sun.source.util.DocTreeFactory
    public /* bridge */ /* synthetic */ AuthorTree newAuthorTree(List list) {
        return newAuthorTree((List<? extends DocTree>) list);
    }

    @Override // com.sun.source.util.DocTreeFactory
    public /* bridge */ /* synthetic */ DeprecatedTree newDeprecatedTree(List list) {
        return newDeprecatedTree((List<? extends DocTree>) list);
    }

    @Override // com.sun.source.util.DocTreeFactory
    public /* bridge */ /* synthetic */ HiddenTree newHiddenTree(List list) {
        return newHiddenTree((List<? extends DocTree>) list);
    }

    @Override // com.sun.source.util.DocTreeFactory
    public /* bridge */ /* synthetic */ ReturnTree newReturnTree(boolean z, List list) {
        return newReturnTree(z, (List<? extends DocTree>) list);
    }

    @Override // com.sun.source.util.DocTreeFactory
    public /* bridge */ /* synthetic */ SeeTree newSeeTree(List list) {
        return newSeeTree((List<? extends DocTree>) list);
    }

    @Override // com.sun.source.util.DocTreeFactory
    public /* bridge */ /* synthetic */ SerialDataTree newSerialDataTree(List list) {
        return newSerialDataTree((List<? extends DocTree>) list);
    }

    @Override // com.sun.source.util.DocTreeFactory
    public /* bridge */ /* synthetic */ SerialTree newSerialTree(List list) {
        return newSerialTree((List<? extends DocTree>) list);
    }

    @Override // com.sun.source.util.DocTreeFactory
    public /* bridge */ /* synthetic */ SinceTree newSinceTree(List list) {
        return newSinceTree((List<? extends DocTree>) list);
    }

    @Override // com.sun.source.util.DocTreeFactory
    public /* bridge */ /* synthetic */ StartElementTree newStartElementTree(Name name, List list, boolean z) {
        return newStartElementTree(name, (List<? extends DocTree>) list, z);
    }

    @Override // com.sun.source.util.DocTreeFactory
    public /* bridge */ /* synthetic */ SummaryTree newSummaryTree(List list) {
        return newSummaryTree((List<? extends DocTree>) list);
    }

    @Override // com.sun.source.util.DocTreeFactory
    public /* bridge */ /* synthetic */ UnknownBlockTagTree newUnknownBlockTagTree(Name name, List list) {
        return newUnknownBlockTagTree(name, (List<? extends DocTree>) list);
    }

    @Override // com.sun.source.util.DocTreeFactory
    public /* bridge */ /* synthetic */ UnknownInlineTagTree newUnknownInlineTagTree(Name name, List list) {
        return newUnknownInlineTagTree(name, (List<? extends DocTree>) list);
    }

    @Override // com.sun.source.util.DocTreeFactory
    public /* bridge */ /* synthetic */ VersionTree newVersionTree(List list) {
        return newVersionTree((List<? extends DocTree>) list);
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCReturn newReturnTree(List<? extends DocTree> list) {
        return newReturnTree(false, list);
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCValue newValueTree(ReferenceTree referenceTree) {
        return newValueTree((TextTree) null, referenceTree);
    }

    @Override // com.sun.source.util.DocTreeFactory
    public /* bridge */ /* synthetic */ IndexTree newIndexTree(DocTree docTree, List list) {
        return newIndexTree(docTree, (List<? extends DocTree>) list);
    }

    @Override // com.sun.source.util.DocTreeFactory
    public /* bridge */ /* synthetic */ ParamTree newParamTree(boolean z, IdentifierTree identifierTree, List list) {
        return newParamTree(z, identifierTree, (List<? extends DocTree>) list);
    }

    @Override // com.sun.source.util.DocTreeFactory
    public /* bridge */ /* synthetic */ ProvidesTree newProvidesTree(ReferenceTree referenceTree, List list) {
        return newProvidesTree(referenceTree, (List<? extends DocTree>) list);
    }

    @Override // com.sun.source.util.DocTreeFactory
    public /* bridge */ /* synthetic */ ReturnTree newReturnTree(List list) {
        return newReturnTree((List<? extends DocTree>) list);
    }

    @Override // com.sun.source.util.DocTreeFactory
    public /* bridge */ /* synthetic */ SnippetTree newSnippetTree(List list, TextTree textTree) {
        return newSnippetTree((List<? extends DocTree>) list, textTree);
    }

    @Override // com.sun.source.util.DocTreeFactory
    public /* bridge */ /* synthetic */ SpecTree newSpecTree(TextTree textTree, List list) {
        return newSpecTree(textTree, (List<? extends DocTree>) list);
    }

    @Override // com.sun.source.util.DocTreeFactory
    public /* bridge */ /* synthetic */ UsesTree newUsesTree(ReferenceTree referenceTree, List list) {
        return newUsesTree(referenceTree, (List<? extends DocTree>) list);
    }

    @Override // com.sun.source.util.DocTreeFactory
    public /* bridge */ /* synthetic */ ThrowsTree newExceptionTree(ReferenceTree referenceTree, List list) {
        return newExceptionTree(referenceTree, (List<? extends DocTree>) list);
    }

    @Override // com.sun.source.util.DocTreeFactory
    public /* bridge */ /* synthetic */ LinkTree newLinkPlainTree(ReferenceTree referenceTree, List list) {
        return newLinkPlainTree(referenceTree, (List<? extends DocTree>) list);
    }

    @Override // com.sun.source.util.DocTreeFactory
    public /* bridge */ /* synthetic */ LinkTree newLinkTree(ReferenceTree referenceTree, List list) {
        return newLinkTree(referenceTree, (List<? extends DocTree>) list);
    }

    @Override // com.sun.source.util.DocTreeFactory
    public /* bridge */ /* synthetic */ SerialFieldTree newSerialFieldTree(IdentifierTree identifierTree, ReferenceTree referenceTree, List list) {
        return newSerialFieldTree(identifierTree, referenceTree, (List<? extends DocTree>) list);
    }

    @Override // com.sun.source.util.DocTreeFactory
    public /* bridge */ /* synthetic */ ThrowsTree newThrowsTree(ReferenceTree referenceTree, List list) {
        return newThrowsTree(referenceTree, (List<? extends DocTree>) list);
    }

    public DCTree.DCReference newReferenceTree(String str, ReferenceParser.Reference reference) {
        DCTree.DCReference dCReference = new DCTree.DCReference(str, reference.moduleName, reference.qualExpr, reference.member, reference.paramTypes);
        dCReference.pos = this.pos;
        return dCReference;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public /* bridge */ /* synthetic */ DocCommentTree newDocCommentTree(List list, List list2, List list3, List list4) {
        return newDocCommentTree((List<? extends DocTree>) list, (List<? extends DocTree>) list2, (List<? extends DocTree>) list3, (List<? extends DocTree>) list4);
    }

    @Override // com.sun.source.util.DocTreeFactory
    public DCTree.DCDocComment newDocCommentTree(List<? extends DocTree> list, List<? extends DocTree> list2) {
        List<? extends DocTree> list3 = Collections.EMPTY_LIST;
        return newDocCommentTree(list, list2, list3, list3);
    }

    public DCTree.DCDocComment newDocCommentTree(Tokens.Comment comment, List<? extends DocTree> list, List<? extends DocTree> list2, List<? extends DocTree> list3, List<? extends DocTree> list4) {
        Pair<List<DCTree>, List<DCTree>> pairSplitBody = splitBody(list);
        DCTree.DCDocComment dCDocComment = new DCTree.DCDocComment(comment, cast(list), pairSplitBody.fst, pairSplitBody.snd, cast(list2), cast(list3), cast(list4));
        dCDocComment.pos = this.pos;
        return dCDocComment;
    }

    @Override // com.sun.source.util.DocTreeFactory
    public /* bridge */ /* synthetic */ DocCommentTree newDocCommentTree(List list, List list2) {
        return newDocCommentTree((List<? extends DocTree>) list, (List<? extends DocTree>) list2);
    }

    public static class SentenceBreaker {
        final DocTreeMaker m;
        static final Set<String> sentenceBreakTags = Collections.unmodifiableSet(new HashSet(Arrays.asList("H1", "H2", "H3", "H4", "H5", "H6", "PRE", "P")));
        private static final Pattern INDENT = Pattern.compile(" {4}| {0,3}\t");
        private static final Pattern endPara = Pattern.compile("\n(([ \t]*\n)|( {0,3}[-+*#=_>]))");

        public SentenceBreaker(DocTreeMaker docTreeMaker) {
            this.m = docTreeMaker;
        }

        private int defaultSentenceBreak(DocTree.Kind kind, String str) {
            String strNormalize = normalize(kind, str);
            int i = -1;
            for (int i2 = 0; i2 < strNormalize.length(); i2++) {
                char cCharAt = strNormalize.charAt(i2);
                if (cCharAt == '\t' || cCharAt == '\n' || cCharAt == '\f' || cCharAt == '\r' || cCharAt == ' ') {
                    if (i >= 0) {
                        int i3 = AnonymousClass2.$SwitchMap$com$sun$source$doctree$DocTree$Kind[kind.ordinal()];
                        if (i3 != 3) {
                            if (i3 != 4) {
                                b6c.a(kind);
                                return 0;
                            }
                            int iEndParaPos = endParaPos(strNormalize);
                            if (iEndParaPos != -1 && i2 >= iEndParaPos) {
                                return iEndParaPos;
                            }
                        }
                        return i2;
                    }
                } else {
                    i = cCharAt != '.' ? -1 : i2;
                }
            }
            int i4 = AnonymousClass2.$SwitchMap$com$sun$source$doctree$DocTree$Kind[kind.ordinal()];
            if (i4 == 3) {
                return -1;
            }
            if (i4 == 4) {
                return endParaPos(strNormalize);
            }
            b6c.a(kind);
            return 0;
        }

        private static int endParaPos(String str) {
            Matcher matcher = endPara.matcher(str);
            if (matcher.find()) {
                return matcher.start();
            }
            return -1;
        }

        private static String firstParaText(String str) {
            int iEndParaPos = endParaPos(str);
            return iEndParaPos == -1 ? str : str.substring(0, iEndParaPos);
        }

        private String getContent(DCTree dCTree) {
            int i = AnonymousClass2.$SwitchMap$com$sun$source$doctree$DocTree$Kind[dCTree.getKind().ordinal()];
            if (i == 3) {
                return ((DCTree.DCText) dCTree).text;
            }
            if (i == 4) {
                return ((DCTree.DCRawText) dCTree).code;
            }
            throw new IllegalArgumentException(dCTree.getKind().toString());
        }

        /* JADX WARN: Code duplicated, block: B:32:0x008b  */
        /* JADX WARN: Code duplicated, block: B:35:0x0093  */
        /* JADX WARN: Code duplicated, block: B:37:0x00a2 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:38:0x00a3 A[RETURN] */
        private int getSentenceBreak(DocTree.Kind kind, String str, DCTree dCTree) {
            int next;
            int iEndParaPos;
            BreakIterator breakIterator = this.m.trees.getBreakIterator();
            if (breakIterator == null) {
                return defaultSentenceBreak(kind, str);
            }
            DocTree.Kind kind2 = DocTree.Kind.MARKDOWN;
            String strNormalize = normalize(kind, kind == kind2 ? firstParaText(str) : str);
            breakIterator.setText(strNormalize);
            int next2 = breakIterator.next();
            int[] iArr = AnonymousClass2.$SwitchMap$com$sun$source$doctree$DocTree$Kind;
            if (iArr[kind.ordinal()] == 4 && (iEndParaPos = endParaPos(strNormalize)) != -1) {
                return Math.min(next2, iEndParaPos);
            }
            if (dCTree != null && ((kind != kind2 || strNormalize.length() >= str.length()) && next2 >= str.length() - 1)) {
                int i = iArr[dCTree.getKind().ordinal()];
                if (i == 3 || i == 4) {
                    breakIterator.setText(strNormalize + normalize(dCTree.getKind(), getContent(dCTree)));
                    if (next2 >= breakIterator.next()) {
                        if (!isSentenceBreak(dCTree, false)) {
                            breakIterator.setText(str.concat("Dummy Sentence."));
                            next = breakIterator.next();
                            if (next <= next2) {
                                return next;
                            }
                            return -1;
                        }
                    }
                } else if (!isSentenceBreak(dCTree, false)) {
                    breakIterator.setText(str.concat("Dummy Sentence."));
                    next = breakIterator.next();
                    if (next <= next2) {
                        return next;
                    }
                    return -1;
                }
            }
            return next2;
        }

        private boolean isIndented(String str) {
            return INDENT.matcher(str).lookingAt();
        }

        /* JADX WARN: Multi-variable type inference failed */
        private boolean isSentenceBreak(DCTree dCTree, boolean z) {
            int i = AnonymousClass2.$SwitchMap$com$sun$source$doctree$DocTree$Kind[dCTree.getKind().ordinal()];
            if (i == 5) {
                return !z && dCTree.pos > 1 && isSentenceBreak(((StartElementTree) dCTree).getName());
            }
            if (i != 6) {
                return false;
            }
            return !z && dCTree.pos > 1 && isSentenceBreak(((EndElementTree) dCTree).getName());
        }

        private DCTree newNode(DocTree.Kind kind, int i, String str) {
            int i2 = AnonymousClass2.$SwitchMap$com$sun$source$doctree$DocTree$Kind[kind.ordinal()];
            if (i2 == 3) {
                return this.m.at(i).newTextTree(str);
            }
            if (i2 == 4) {
                return this.m.at(i).newRawTextTree(kind, str);
            }
            b6c.a(kind);
            return null;
        }

        private String normalize(DocTree.Kind kind, String str) {
            int i = AnonymousClass2.$SwitchMap$com$sun$source$doctree$DocTree$Kind[kind.ordinal()];
            if (i == 3) {
                return str;
            }
            if (i == 4) {
                return normalizeMarkdown(str);
            }
            b6c.a(kind);
            return null;
        }

        private String normalizeMarkdown(String str) {
            SentenceBreaker sentenceBreaker;
            String str2;
            int iSkip;
            StringBuilder sb = new StringBuilder();
            int length = str.length();
            int i = 0;
            while (i < length) {
                char cCharAt = str.charAt(i);
                if (cCharAt == '(') {
                    sentenceBreaker = this;
                    str2 = str;
                    iSkip = sentenceBreaker.skip(sb, str2, i, cCharAt, ')');
                } else if (cCharAt != '<') {
                    if (cCharAt == '`') {
                        str2 = str;
                        SentenceBreaker sentenceBreaker2 = this;
                        int length2 = i + 1;
                        while (length2 < length && str2.charAt(length2) == '`') {
                            length2++;
                        }
                        String strSubstring = str2.substring(i, length2);
                        sb.append(strSubstring);
                        int iIndexOf = str2.indexOf(strSubstring, length2);
                        if (iIndexOf > length2) {
                            sb.append(str2.substring(length2, iIndexOf).replace('.', LocaleUtility.IETF_SEPARATOR));
                            sb.append(strSubstring);
                            length2 = strSubstring.length() + iIndexOf;
                        }
                        i = length2;
                        sentenceBreaker = sentenceBreaker2;
                    } else if (cCharAt != '[') {
                        if (cCharAt != '\\') {
                            sb.append(cCharAt);
                            i++;
                        } else {
                            sb.append(cCharAt);
                            int i2 = i + 1;
                            if (i2 < length) {
                                sb.append(str.charAt(i2));
                                i += 2;
                            } else {
                                str2 = str;
                                i = i2;
                                sentenceBreaker = this;
                            }
                        }
                        sentenceBreaker = this;
                        str2 = str;
                    } else {
                        sentenceBreaker = this;
                        str2 = str;
                        iSkip = sentenceBreaker.skip(sb, str2, i, cCharAt, ']');
                    }
                    this = sentenceBreaker;
                    str = str2;
                } else {
                    str2 = str;
                    sentenceBreaker = this;
                    iSkip = sentenceBreaker.skip(sb, str2, i, cCharAt, '>');
                }
                i = iSkip;
                this = sentenceBreaker;
                str = str2;
            }
            return sb.toString();
        }

        private int skip(StringBuilder sb, String str, int i, char c, char c2) {
            sb.append(c);
            int i2 = i + 1;
            int iIndexOf = str.indexOf(c2, i2);
            if (iIndexOf == -1) {
                return i2;
            }
            sb.append(str.substring(i2, iIndexOf).replace('.', LocaleUtility.IETF_SEPARATOR));
            return iIndexOf;
        }

        private int skipWhiteSpace(String str, int i) {
            while (i < str.length()) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return i;
                }
                i++;
            }
            return -1;
        }

        public Pair<List<DCTree>, List<DCTree>> splitBody(List<? extends DocTree> list) {
            if (list.isEmpty()) {
                return new Pair<>(Collections.unmodifiableList(Arrays.asList(new DCTree[0])), Collections.unmodifiableList(Arrays.asList(new DCTree[0])));
            }
            int i = this.m.pos;
            try {
                ListBuffer listBuffer = new ListBuffer();
                ListBuffer listBuffer2 = new ListBuffer();
                ArrayList arrayList = new ArrayList(DocTreeMaker.cast(list));
                ListIterator listIterator = arrayList.listIterator();
                boolean z = false;
                while (listIterator.hasNext() && !z) {
                    boolean zHasPrevious = listIterator.hasPrevious();
                    boolean z2 = !zHasPrevious;
                    DCTree dCTree = (DCTree) listIterator.next();
                    int i2 = AnonymousClass2.$SwitchMap$com$sun$source$doctree$DocTree$Kind[dCTree.getKind().ordinal()];
                    if (i2 == 1 || i2 == 2) {
                        listBuffer.add(dCTree);
                        z = true;
                    } else if (i2 == 3 || i2 == 4) {
                        DCTree dCTree2 = listIterator.hasNext() ? (DCTree) arrayList.get(listIterator.nextIndex()) : null;
                        String content = getContent(dCTree);
                        if (!zHasPrevious && dCTree.getKind() == DocTree.Kind.MARKDOWN && isIndented(content)) {
                            listBuffer2.add(dCTree);
                        } else {
                            int sentenceBreak = getSentenceBreak(dCTree.getKind(), content, dCTree2);
                            if (sentenceBreak > 0) {
                                listBuffer.add(newNode(dCTree.getKind(), dCTree.pos, StringWrapper.stripTrailing(content.substring(0, sentenceBreak))));
                                int iSkipWhiteSpace = skipWhiteSpace(content, sentenceBreak);
                                if (iSkipWhiteSpace > 0) {
                                    listBuffer2.add(newNode(dCTree.getKind(), dCTree.pos + iSkipWhiteSpace, content.substring(iSkipWhiteSpace)));
                                }
                            } else if (dCTree2 == null || !isSentenceBreak(dCTree2, false)) {
                                listBuffer.add(dCTree);
                            } else {
                                listBuffer.add(newNode(dCTree.getKind(), dCTree.pos, StringWrapper.stripTrailing(content)));
                            }
                        }
                        z = true;
                    } else if (isSentenceBreak(dCTree, z2)) {
                        listBuffer2.add(dCTree);
                        z = true;
                    } else {
                        listBuffer.add(dCTree);
                    }
                }
                while (listIterator.hasNext()) {
                    listBuffer2.add((DCTree) listIterator.next());
                }
                return new Pair<>(listBuffer.toList(), listBuffer2.toList());
            } finally {
                this.m.pos = i;
            }
        }

        private boolean isSentenceBreak(Name name) {
            return sentenceBreakTags.contains(StringUtils.toUpperCase(name.toString()));
        }
    }
}
