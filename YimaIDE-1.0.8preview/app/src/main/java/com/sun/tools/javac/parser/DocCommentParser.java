package com.sun.tools.javac.parser;

import com.sun.source.doctree.AttributeTree;
import com.sun.source.doctree.DocTree;
import com.sun.source.doctree.IdentifierTree;
import com.sun.source.doctree.LiteralTree;
import com.sun.source.doctree.ReferenceTree;
import com.sun.source.doctree.StartElementTree;
import com.sun.source.doctree.TextTree;
import com.sun.source.util.SimpleDocTreeVisitor;
import com.sun.tools.javac.tree.DCTree;
import com.sun.tools.javac.tree.DocTreeMaker;
import com.sun.tools.javac.util.DiagnosticSource;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import com.sun.tools.javac.util.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import nbjavac.StringWrapper;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DocCommentParser {
    private int bp;
    private char[] buf;
    private int buflen;
    private char ch;
    private final Tokens.Comment comment;
    private final DiagnosticSource diagSource;
    private final JCDiagnostic.Factory diags;
    private final ParserFactory fac;
    private boolean inPre;
    private final boolean isHtmlFile;
    private int lastNonWhite;
    private final DocTreeMaker m;
    private final Markdown markdown;
    private final Names names;
    private boolean newline;
    private final Map<Name, TagParser> tagParsers;
    private final DocTree.Kind textKind;
    private int textStart;

    /* JADX INFO: renamed from: com.sun.tools.javac.parser.DocCommentParser$1Context, reason: invalid class name */
    public class C1Context {
        C1State state = C1State.BEFORE_CODE;

        public C1Context() {
        }

        public void unexpectedTree() {
            if (this.state != C1State.SUCCEEDED) {
                this.state = C1State.FAILED;
            }
        }
    }

    /* JADX INFO: renamed from: com.sun.tools.javac.parser.DocCommentParser$1State, reason: invalid class name */
    public enum C1State {
        BEFORE_CODE,
        AFTER_CODE,
        SUCCEEDED,
        FAILED
    }

    /* JADX INFO: renamed from: com.sun.tools.javac.parser.DocCommentParser$29, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass29 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$source$doctree$DocTree$Kind;
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$parser$Tokens$Comment$CommentStyle;

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
            int[] iArr2 = new int[Tokens.Comment.CommentStyle.values().length];
            $SwitchMap$com$sun$tools$javac$parser$Tokens$Comment$CommentStyle = iArr2;
            try {
                iArr2[Tokens.Comment.CommentStyle.JAVADOC_BLOCK.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$Comment$CommentStyle[Tokens.Comment.CommentStyle.JAVADOC_LINE.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public enum LineKind {
        BLANK(Pattern.compile("[ \t]*")),
        ATX_HEADER(Pattern.compile("#{1,6}([ \t].*|$)")),
        SETEXT_UNDERLINE(Pattern.compile("(=+|-+)[ \t]*")),
        THEMATIC_BREAK(Pattern.compile("((\\*[ \t]*+){3,})|((-[ \t]*+){3,})|((_[ \t]*+){3,})")),
        CODE_FENCE(Pattern.compile("(`{3,}[^`]*+)|(~{3,}.*+)")),
        BULLETED_LIST_ITEM(Pattern.compile("[-+*][ \t].*")),
        ORDERED_LIST_ITEM(Pattern.compile("[0-9]{1,9}[.)][ \t].*")),
        BLOCK_QUOTE(Pattern.compile(">.*")),
        OTHER(Pattern.compile(".*"));

        final Pattern pattern;

        LineKind(Pattern pattern) {
            this.pattern = pattern;
        }
    }

    public class Markdown {
        private final List<BlockInfo> containers = new ArrayList();
        private LeafBlockKind leafKind = LeafBlockKind.NONE;
        private int blockId = 0;

        public static final class BlockInfo {
            private final ContainerBlockKind blockKind;
            private final int indent;

            private BlockInfo(ContainerBlockKind containerBlockKind, int i) {
                this.blockKind = containerBlockKind;
                this.indent = i;
            }

            public ContainerBlockKind blockKind() {
                return this.blockKind;
            }

            public final boolean equals(Object obj) {
                if (!(obj instanceof BlockInfo)) {
                    return false;
                }
                BlockInfo blockInfo = (BlockInfo) obj;
                return this.indent == blockInfo.indent && Objects.equals(this.blockKind, blockInfo.blockKind);
            }

            public final int hashCode() {
                return (Objects.hashCode(this.blockKind) * 31) + Integer.hashCode(this.indent);
            }

            public int indent() {
                return this.indent;
            }

            public final String toString() {
                return "BlockInfo[blockKind=" + Objects.toString(this.blockKind) + ", indent=" + Integer.toString(this.indent) + "]";
            }
        }

        public enum ContainerBlockKind {
            LIST_ITEM,
            QUOTE
        }

        public enum LeafBlockKind {
            NONE,
            PARAGRAPH,
            FENCED_CODE,
            INDENTED_CODE
        }

        public Markdown() {
        }

        private void closeContainer(int i) {
            List<BlockInfo> list = this.containers;
            list.subList(i, list.size()).clear();
        }

        private int count(char c) {
            DocCommentParser.this.nextChar();
            int i = 1;
            while (DocCommentParser.this.bp < DocCommentParser.this.buflen && DocCommentParser.this.ch == c) {
                i++;
                DocCommentParser.this.nextChar();
            }
            return i;
        }

        /* JADX WARN: Code duplicated, block: B:26:0x0036  */
        /* JADX WARN: Code duplicated, block: B:28:0x003d  */
        /* JADX WARN: Code duplicated, block: B:31:0x004c A[LOOP:0: B:27:0x003b->B:31:0x004c, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:35:0x004b A[SYNTHETIC] */
        /* JADX WARN: Instruction removed from duplicated block: B:26:0x0036, please report this as an issue */
        private LineKind getLineKind(String str) {
            if (StringWrapper.isBlank(str)) {
                return LineKind.BLANK;
            }
            char cCharAt = str.charAt(0);
            if (cCharAt != '#' && cCharAt != '-' && cCharAt != '~' && cCharAt != '*' && cCharAt != '+' && cCharAt != '=' && cCharAt != '>' && cCharAt != '_' && cCharAt != '`') {
                switch (cCharAt) {
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        for (LineKind lineKind : LineKind.values()) {
                            if (lineKind.pattern.matcher(str).matches()) {
                                return lineKind;
                            }
                        }
                        break;
                }
            } else {
                while (i < r1) {
                    if (lineKind.pattern.matcher(str).matches()) {
                        return lineKind;
                    }
                }
            }
            return LineKind.OTHER;
        }

        private String peekLine() {
            int i = DocCommentParser.this.bp;
            while (true) {
                int i2 = DocCommentParser.this.buflen;
                DocCommentParser docCommentParser = DocCommentParser.this;
                if (i >= i2) {
                    return docCommentParser.newString(docCommentParser.bp, DocCommentParser.this.buflen);
                }
                char c = docCommentParser.buf[i];
                if (c == '\n' || c == '\r') {
                    DocCommentParser docCommentParser2 = DocCommentParser.this;
                    return docCommentParser2.newString(docCommentParser2.bp, i);
                }
                i++;
            }
        }

        private int readIndent(int i) {
            while (DocCommentParser.this.bp < DocCommentParser.this.buflen) {
                char c = DocCommentParser.this.ch;
                if (c == '\t') {
                    i += 4 - (i % 4);
                } else {
                    if (c != ' ') {
                        break;
                    }
                    i++;
                }
                DocCommentParser.this.nextChar();
            }
            return i;
        }

        public boolean isCodeFence() {
            return this.leafKind == LeafBlockKind.FENCED_CODE;
        }

        public boolean isIndentedCodeBlock() {
            return this.leafKind == LeafBlockKind.INDENTED_CODE;
        }

        public int skipCode() {
            char c = DocCommentParser.this.ch;
            int iCount = count(c);
            LeafBlockKind leafBlockKind = this.leafKind;
            boolean z = leafBlockKind == LeafBlockKind.FENCED_CODE;
            int i = this.blockId;
            while (DocCommentParser.this.bp < DocCommentParser.this.buflen) {
                char c2 = DocCommentParser.this.ch;
                if (c2 == '\n' || c2 == '\r') {
                    DocCommentParser.this.nextChar();
                    update();
                    if (z) {
                        if ((this.leafKind == LeafBlockKind.FENCED_CODE && DocCommentParser.this.ch == c && count(DocCommentParser.this.ch) >= iCount) || this.blockId != i) {
                            this.leafKind = LeafBlockKind.NONE;
                            return DocCommentParser.this.bp;
                        }
                    } else if (this.blockId != i) {
                        break;
                    }
                } else {
                    if (DocCommentParser.this.ch == c && leafBlockKind != LeafBlockKind.FENCED_CODE && count(DocCommentParser.this.ch) == iCount) {
                        return DocCommentParser.this.bp;
                    }
                    DocCommentParser.this.nextChar();
                }
            }
            return -1;
        }

        public void skipLine() {
            while (DocCommentParser.this.bp < DocCommentParser.this.buflen && DocCommentParser.this.ch != '\n' && DocCommentParser.this.ch != '\r') {
                DocCommentParser.this.nextChar();
            }
        }

        public String toString() {
            return getClass().getSimpleName() + "[containers:" + this.containers + ", leafKind:" + this.leafKind + ", blockId:" + this.blockId + "]";
        }

        public void update() {
            int i;
            LineKind lineKind;
            LeafBlockKind leafBlockKind = this.leafKind;
            int indent = readIndent(0);
            String strPeekLine = peekLine();
            int i2 = 0;
            int i3 = 0;
            while (true) {
                if (i2 == this.containers.size()) {
                    LeafBlockKind leafBlockKind2 = LeafBlockKind.FENCED_CODE;
                    if (leafBlockKind == leafBlockKind2) {
                        return;
                    }
                    if (this.containers.isEmpty()) {
                        i = 0;
                    } else {
                        List<BlockInfo> list = this.containers;
                        i = list.get(list.size() - 1).indent;
                    }
                    if (indent >= i + 4 && leafBlockKind != LeafBlockKind.PARAGRAPH) {
                        LeafBlockKind leafBlockKind3 = LeafBlockKind.INDENTED_CODE;
                        this.leafKind = leafBlockKind3;
                        if (leafBlockKind3 != leafBlockKind) {
                            this.blockId++;
                            return;
                        }
                        return;
                    }
                    switch (getLineKind(strPeekLine.substring(i3))) {
                        case BLANK:
                        case ATX_HEADER:
                        case SETEXT_UNDERLINE:
                        case THEMATIC_BREAK:
                            this.leafKind = LeafBlockKind.NONE;
                            this.blockId++;
                            return;
                        case CODE_FENCE:
                            this.leafKind = leafBlockKind2;
                            this.blockId++;
                            return;
                        case BULLETED_LIST_ITEM:
                        case ORDERED_LIST_ITEM:
                            int i4 = indent;
                            while (DocCommentParser.this.ch != ' ' && DocCommentParser.this.ch != '\t') {
                                i4++;
                                DocCommentParser.this.nextChar();
                            }
                            int indent2 = readIndent(i4);
                            this.containers.add(new BlockInfo(ContainerBlockKind.LIST_ITEM, indent2));
                            i2++;
                            i3 = indent2 - indent;
                            this.blockId++;
                            break;
                        case BLOCK_QUOTE:
                            this.containers.add(new BlockInfo(ContainerBlockKind.QUOTE, indent + 1));
                            i2++;
                            i3++;
                            this.blockId++;
                            break;
                        case OTHER:
                            this.leafKind = LeafBlockKind.PARAGRAPH;
                            return;
                    }
                } else {
                    BlockInfo blockInfo = this.containers.get(i2);
                    ContainerBlockKind containerBlockKind = blockInfo.blockKind;
                    int iOrdinal = containerBlockKind.ordinal();
                    if (iOrdinal != 0) {
                        if (iOrdinal != 1) {
                            mx5.a(containerBlockKind);
                            return;
                        }
                        LineKind lineKind2 = getLineKind(strPeekLine.substring(i3));
                        if (lineKind2 == LineKind.BLOCK_QUOTE) {
                            i2++;
                            i3++;
                        } else {
                            if (lineKind2 == LineKind.OTHER && leafBlockKind == LeafBlockKind.PARAGRAPH) {
                                return;
                            }
                            closeContainer(i2);
                            this.blockId++;
                        }
                    } else if (indent >= blockInfo.indent || (lineKind = getLineKind(strPeekLine.substring(i3))) == LineKind.BLANK) {
                        i2++;
                    } else {
                        if (lineKind == LineKind.OTHER && leafBlockKind == LeafBlockKind.PARAGRAPH) {
                            return;
                        }
                        closeContainer(i2);
                        this.blockId++;
                    }
                }
            }
        }
    }

    public enum Phase {
        PREAMBLE,
        BODY,
        POSTAMBLE,
        INLINE
    }

    public enum WhitespaceRetentionPolicy {
        RETAIN_ALL,
        REMOVE_FIRST_SPACE,
        REMOVE_ALL
    }

    public DocCommentParser(ParserFactory parserFactory, DiagnosticSource diagnosticSource, Tokens.Comment comment, boolean z) {
        this.textStart = -1;
        this.lastNonWhite = -1;
        this.newline = true;
        this.inPre = false;
        this.fac = parserFactory;
        this.diags = parserFactory.log.diags;
        this.diagSource = diagnosticSource;
        this.comment = comment.stripIndent();
        this.names = parserFactory.names;
        this.isHtmlFile = z;
        DocTree.Kind textKind = z ? DocTree.Kind.TEXT : getTextKind(comment);
        this.textKind = textKind;
        this.m = parserFactory.docTreeMaker;
        this.tagParsers = createTagParsers();
        this.markdown = textKind == DocTree.Kind.MARKDOWN ? new Markdown() : null;
    }

    private Map<Name, TagParser> createTagParsers() {
        TagParser.Kind kind = TagParser.Kind.BLOCK;
        TagParser tagParser = new TagParser(kind, DocTree.Kind.AUTHOR) { // from class: com.sun.tools.javac.parser.DocCommentParser.2
            @Override // com.sun.tools.javac.parser.DocCommentParser.TagParser
            public DCTree parse(int i) {
                return DocCommentParser.this.m.at(i).newAuthorTree((List<? extends DocTree>) DocCommentParser.this.blockContent());
            }
        };
        TagParser.Kind kind2 = TagParser.Kind.INLINE;
        boolean z = true;
        TagParser[] tagParserArr = {tagParser, new TagParser(kind2, DocTree.Kind.CODE, z) { // from class: com.sun.tools.javac.parser.DocCommentParser.3
            @Override // com.sun.tools.javac.parser.DocCommentParser.TagParser
            public DCTree parse(int i) throws ParseException {
                DCTree.DCText dCTextInlineText = DocCommentParser.this.inlineText(WhitespaceRetentionPolicy.REMOVE_FIRST_SPACE);
                DocCommentParser.this.nextChar();
                return DocCommentParser.this.m.at(i).newCodeTree((TextTree) dCTextInlineText);
            }
        }, new TagParser(kind, DocTree.Kind.DEPRECATED) { // from class: com.sun.tools.javac.parser.DocCommentParser.4
            @Override // com.sun.tools.javac.parser.DocCommentParser.TagParser
            public DCTree parse(int i) {
                return DocCommentParser.this.m.at(i).newDeprecatedTree((List<? extends DocTree>) DocCommentParser.this.blockContent());
            }
        }, new TagParser(kind2, DocTree.Kind.DOC_ROOT) { // from class: com.sun.tools.javac.parser.DocCommentParser.5
            @Override // com.sun.tools.javac.parser.DocCommentParser.TagParser
            public DCTree parse(int i) throws ParseException {
                char c = DocCommentParser.this.ch;
                DocCommentParser docCommentParser = DocCommentParser.this;
                if (c == '}') {
                    docCommentParser.nextChar();
                    return DocCommentParser.this.m.at(i).newDocRootTree();
                }
                int i2 = docCommentParser.bp;
                DocCommentParser.this.inlineText(WhitespaceRetentionPolicy.REMOVE_ALL);
                DocCommentParser.this.nextChar();
                throw new ParseException(i2, "dc.unexpected.content");
            }
        }, new TagParser(kind, DocTree.Kind.EXCEPTION) { // from class: com.sun.tools.javac.parser.DocCommentParser.6
            @Override // com.sun.tools.javac.parser.DocCommentParser.TagParser
            public DCTree parse(int i) throws ParseException {
                DocCommentParser.this.skipWhitespace();
                return DocCommentParser.this.m.at(i).newExceptionTree((ReferenceTree) DocCommentParser.this.reference(ReferenceParser.Mode.MEMBER_DISALLOWED), (List<? extends DocTree>) DocCommentParser.this.blockContent());
            }
        }, new TagParser(kind, DocTree.Kind.HIDDEN) { // from class: com.sun.tools.javac.parser.DocCommentParser.7
            @Override // com.sun.tools.javac.parser.DocCommentParser.TagParser
            public DCTree parse(int i) {
                return DocCommentParser.this.m.at(i).newHiddenTree((List<? extends DocTree>) DocCommentParser.this.blockContent());
            }
        }, new TagParser(kind2, DocTree.Kind.INDEX) { // from class: com.sun.tools.javac.parser.DocCommentParser.8
            @Override // com.sun.tools.javac.parser.DocCommentParser.TagParser
            public DCTree parse(int i) throws ParseException {
                DocCommentParser.this.skipWhitespace();
                if (DocCommentParser.this.ch == '}') {
                    throw new ParseException("dc.no.content");
                }
                char c = DocCommentParser.this.ch;
                DocCommentParser docCommentParser = DocCommentParser.this;
                DCTree.DCText dCTextQuotedString = c == '\"' ? docCommentParser.quotedString() : docCommentParser.inlineWord();
                if (dCTextQuotedString == null) {
                    throw new ParseException("dc.no.content");
                }
                DocCommentParser.this.skipWhitespace();
                com.sun.tools.javac.util.List listNil = com.sun.tools.javac.util.List.nil();
                char c2 = DocCommentParser.this.ch;
                DocCommentParser docCommentParser2 = DocCommentParser.this;
                if (c2 != '}') {
                    listNil = docCommentParser2.inlineContent();
                } else {
                    docCommentParser2.nextChar();
                }
                return DocCommentParser.this.m.at(i).newIndexTree((DocTree) dCTextQuotedString, (List<? extends DocTree>) listNil);
            }
        }, new TagParser(kind2, DocTree.Kind.INHERIT_DOC) { // from class: com.sun.tools.javac.parser.DocCommentParser.9
            @Override // com.sun.tools.javac.parser.DocCommentParser.TagParser
            public DCTree parse(int i) throws ParseException {
                DCTree.DCReference dCReferenceReference = DocCommentParser.this.reference(ReferenceParser.Mode.MEMBER_DISALLOWED);
                DocCommentParser.this.skipWhitespace();
                char c = DocCommentParser.this.ch;
                DocCommentParser docCommentParser = DocCommentParser.this;
                if (c == '}') {
                    docCommentParser.nextChar();
                    DocCommentParser docCommentParser2 = DocCommentParser.this;
                    return dCReferenceReference == null ? docCommentParser2.m.at(i).newInheritDocTree() : docCommentParser2.m.at(i).newInheritDocTree((ReferenceTree) dCReferenceReference);
                }
                int i2 = docCommentParser.bp;
                DocCommentParser.this.inlineText(WhitespaceRetentionPolicy.REMOVE_ALL);
                DocCommentParser.this.nextChar();
                throw new ParseException(i2, "dc.unexpected.content");
            }
        }, new TagParser(kind2, DocTree.Kind.LINK) { // from class: com.sun.tools.javac.parser.DocCommentParser.10
            @Override // com.sun.tools.javac.parser.DocCommentParser.TagParser
            public DCTree parse(int i) throws ParseException {
                return DocCommentParser.this.m.at(i).newLinkTree((ReferenceTree) DocCommentParser.this.reference(ReferenceParser.Mode.MEMBER_OPTIONAL), (List<? extends DocTree>) DocCommentParser.this.inlineContent());
            }
        }, new TagParser(kind2, DocTree.Kind.LINK_PLAIN) { // from class: com.sun.tools.javac.parser.DocCommentParser.11
            @Override // com.sun.tools.javac.parser.DocCommentParser.TagParser
            public DCTree parse(int i) throws ParseException {
                return DocCommentParser.this.m.at(i).newLinkPlainTree((ReferenceTree) DocCommentParser.this.reference(ReferenceParser.Mode.MEMBER_OPTIONAL), (List<? extends DocTree>) DocCommentParser.this.inlineContent());
            }
        }, new TagParser(kind2, DocTree.Kind.LITERAL, z) { // from class: com.sun.tools.javac.parser.DocCommentParser.12
            @Override // com.sun.tools.javac.parser.DocCommentParser.TagParser
            public DCTree parse(int i) throws ParseException {
                DCTree.DCText dCTextInlineText = DocCommentParser.this.inlineText(WhitespaceRetentionPolicy.REMOVE_FIRST_SPACE);
                DocCommentParser.this.nextChar();
                return DocCommentParser.this.m.at(i).newLiteralTree((TextTree) dCTextInlineText);
            }
        }, new TagParser(kind, DocTree.Kind.PARAM) { // from class: com.sun.tools.javac.parser.DocCommentParser.13
            @Override // com.sun.tools.javac.parser.DocCommentParser.TagParser
            public DCTree parse(int i) throws ParseException {
                boolean z2;
                DocCommentParser.this.skipWhitespace();
                if (DocCommentParser.this.ch == '<') {
                    DocCommentParser.this.nextChar();
                    z2 = true;
                } else {
                    z2 = false;
                }
                DCTree.DCIdentifier dCIdentifierIdentifier = DocCommentParser.this.identifier();
                if (z2) {
                    if (DocCommentParser.this.ch != '>') {
                        throw new ParseException(DocCommentParser.this.bp, "dc.gt.expected");
                    }
                    DocCommentParser.this.nextChar();
                }
                DocCommentParser.this.skipWhitespace();
                return DocCommentParser.this.m.at(i).newParamTree(z2, (IdentifierTree) dCIdentifierIdentifier, (List<? extends DocTree>) DocCommentParser.this.blockContent());
            }
        }, new TagParser(kind, DocTree.Kind.PROVIDES) { // from class: com.sun.tools.javac.parser.DocCommentParser.14
            @Override // com.sun.tools.javac.parser.DocCommentParser.TagParser
            public DCTree parse(int i) throws ParseException {
                DocCommentParser.this.skipWhitespace();
                return DocCommentParser.this.m.at(i).newProvidesTree((ReferenceTree) DocCommentParser.this.reference(ReferenceParser.Mode.MEMBER_DISALLOWED), (List<? extends DocTree>) DocCommentParser.this.blockContent());
            }
        }, new TagParser(TagParser.Kind.EITHER, DocTree.Kind.RETURN) { // from class: com.sun.tools.javac.parser.DocCommentParser.15
            @Override // com.sun.tools.javac.parser.DocCommentParser.TagParser
            public DCTree parse(int i, TagParser.Kind kind3) {
                com.sun.tools.javac.util.List<DCTree> listInlineContent;
                int iOrdinal = kind3.ordinal();
                if (iOrdinal == 0) {
                    listInlineContent = DocCommentParser.this.inlineContent();
                } else {
                    if (iOrdinal != 1) {
                        b6c.a(kind3);
                        return null;
                    }
                    listInlineContent = DocCommentParser.this.blockContent();
                }
                return DocCommentParser.this.m.at(i).newReturnTree(kind3 == TagParser.Kind.INLINE, (List<? extends DocTree>) listInlineContent);
            }
        }, new TagParser(kind, DocTree.Kind.SEE) { // from class: com.sun.tools.javac.parser.DocCommentParser.16
            @Override // com.sun.tools.javac.parser.DocCommentParser.TagParser
            public DCTree parse(int i) throws ParseException {
                DocCommentParser.this.skipWhitespace();
                char c = DocCommentParser.this.ch;
                if (c != 26) {
                    if (c == '\"') {
                        DCTree.DCText dCTextQuotedString = DocCommentParser.this.quotedString();
                        if (dCTextQuotedString != null) {
                            DocCommentParser.this.skipWhitespace();
                            if (DocCommentParser.this.ch == '@' || (DocCommentParser.this.ch == 26 && DocCommentParser.this.bp == DocCommentParser.this.buf.length - 1)) {
                                return DocCommentParser.this.m.at(i).newSeeTree((List<? extends DocTree>) com.sun.tools.javac.util.List.of(dCTextQuotedString));
                            }
                        }
                    } else if (c != '<') {
                        DocCommentParser docCommentParser = DocCommentParser.this;
                        if (c != '@') {
                            if (docCommentParser.isJavaIdentifierStart(docCommentParser.ch) || DocCommentParser.this.ch == '#') {
                                return DocCommentParser.this.m.at(i).newSeeTree((List<? extends DocTree>) DocCommentParser.this.blockContent().prepend(DocCommentParser.this.reference(ReferenceParser.Mode.MEMBER_OPTIONAL)));
                            }
                        } else if (docCommentParser.newline) {
                            throw new ParseException("dc.no.content");
                        }
                    } else {
                        com.sun.tools.javac.util.List<DCTree> listBlockContent = DocCommentParser.this.blockContent();
                        if (listBlockContent != null) {
                            return DocCommentParser.this.m.at(i).newSeeTree((List<? extends DocTree>) listBlockContent);
                        }
                    }
                } else if (DocCommentParser.this.bp == DocCommentParser.this.buf.length - 1) {
                    throw new ParseException("dc.no.content");
                }
                throw new ParseException("dc.unexpected.content");
            }
        }, new TagParser(kind, DocTree.Kind.SERIAL_DATA) { // from class: com.sun.tools.javac.parser.DocCommentParser.17
            @Override // com.sun.tools.javac.parser.DocCommentParser.TagParser
            public DCTree parse(int i) {
                return DocCommentParser.this.m.at(i).newSerialDataTree((List<? extends DocTree>) DocCommentParser.this.blockContent());
            }
        }, new TagParser(kind, DocTree.Kind.SERIAL_FIELD) { // from class: com.sun.tools.javac.parser.DocCommentParser.18
            @Override // com.sun.tools.javac.parser.DocCommentParser.TagParser
            public DCTree parse(int i) throws ParseException {
                com.sun.tools.javac.util.List<DCTree> listBlockContent;
                DocCommentParser.this.skipWhitespace();
                DCTree.DCIdentifier dCIdentifierIdentifier = DocCommentParser.this.identifier();
                DocCommentParser.this.skipWhitespace();
                DCTree.DCReference dCReferenceReference = DocCommentParser.this.reference(ReferenceParser.Mode.MEMBER_DISALLOWED);
                DocCommentParser docCommentParser = DocCommentParser.this;
                if (docCommentParser.isWhitespace(docCommentParser.ch)) {
                    DocCommentParser.this.skipWhitespace();
                    listBlockContent = DocCommentParser.this.blockContent();
                } else {
                    listBlockContent = null;
                }
                return DocCommentParser.this.m.at(i).newSerialFieldTree((IdentifierTree) dCIdentifierIdentifier, (ReferenceTree) dCReferenceReference, (List<? extends DocTree>) listBlockContent);
            }
        }, new TagParser(kind, DocTree.Kind.SERIAL) { // from class: com.sun.tools.javac.parser.DocCommentParser.19
            @Override // com.sun.tools.javac.parser.DocCommentParser.TagParser
            public DCTree parse(int i) {
                return DocCommentParser.this.m.at(i).newSerialTree((List<? extends DocTree>) DocCommentParser.this.blockContent());
            }
        }, new TagParser(kind, DocTree.Kind.SINCE) { // from class: com.sun.tools.javac.parser.DocCommentParser.20
            @Override // com.sun.tools.javac.parser.DocCommentParser.TagParser
            public DCTree parse(int i) {
                return DocCommentParser.this.m.at(i).newSinceTree((List<? extends DocTree>) DocCommentParser.this.blockContent());
            }
        }, new TagParser(kind2, DocTree.Kind.SNIPPET) { // from class: com.sun.tools.javac.parser.DocCommentParser.21
            private com.sun.tools.javac.util.List<DCTree> tagAttrs() {
                com.sun.tools.javac.util.List<DCTree> list;
                AttributeTree.ValueKind valueKind;
                ListBuffer listBuffer = new ListBuffer();
                DocCommentParser.this.skipWhitespace();
                while (DocCommentParser.this.bp < DocCommentParser.this.buflen) {
                    DocCommentParser docCommentParser = DocCommentParser.this;
                    if (!docCommentParser.isIdentifierStart(docCommentParser.ch)) {
                        break;
                    }
                    int i = DocCommentParser.this.bp;
                    Name attributeName = DocCommentParser.this.readAttributeName();
                    DocCommentParser.this.skipWhitespace();
                    AttributeTree.ValueKind valueKind2 = AttributeTree.ValueKind.EMPTY;
                    if (DocCommentParser.this.ch == '=') {
                        ListBuffer<DCTree> listBuffer2 = new ListBuffer<>();
                        DocCommentParser.this.nextChar();
                        DocCommentParser.this.skipWhitespace();
                        if (DocCommentParser.this.ch == '\'' || DocCommentParser.this.ch == '\"') {
                            DocCommentParser.this.newline = false;
                            valueKind = DocCommentParser.this.ch == '\'' ? AttributeTree.ValueKind.SINGLE : AttributeTree.ValueKind.DOUBLE;
                            char c = DocCommentParser.this.ch;
                            DocCommentParser.this.nextChar();
                            DocCommentParser docCommentParser2 = DocCommentParser.this;
                            docCommentParser2.textStart = docCommentParser2.bp;
                            while (DocCommentParser.this.bp < DocCommentParser.this.buflen && DocCommentParser.this.ch != c) {
                                DocCommentParser.this.nextChar();
                            }
                            DocCommentParser docCommentParser3 = DocCommentParser.this;
                            docCommentParser3.addPendingText(listBuffer2, docCommentParser3.bp - 1, DocTree.Kind.TEXT);
                            DocCommentParser.this.nextChar();
                        } else {
                            valueKind = AttributeTree.ValueKind.UNQUOTED;
                            DocCommentParser docCommentParser4 = DocCommentParser.this;
                            docCommentParser4.textStart = docCommentParser4.bp;
                            while (DocCommentParser.this.bp < DocCommentParser.this.buflen && DocCommentParser.this.ch != '}' && DocCommentParser.this.ch != ':') {
                                DocCommentParser docCommentParser5 = DocCommentParser.this;
                                if (docCommentParser5.isUnquotedAttrValueTerminator(docCommentParser5.ch)) {
                                    break;
                                }
                                DocCommentParser.this.nextChar();
                            }
                            DocCommentParser docCommentParser6 = DocCommentParser.this;
                            docCommentParser6.addPendingText(listBuffer2, docCommentParser6.bp - 1, DocTree.Kind.TEXT);
                        }
                        DocCommentParser.this.skipWhitespace();
                        AttributeTree.ValueKind valueKind3 = valueKind;
                        list = listBuffer2.toList();
                        valueKind2 = valueKind3;
                    } else {
                        list = null;
                    }
                    listBuffer.add(DocCommentParser.this.m.at(i).newAttributeTree((javax.lang.model.element.Name) attributeName, valueKind2, (List<? extends DocTree>) list));
                }
                return listBuffer.toList();
            }

            @Override // com.sun.tools.javac.parser.DocCommentParser.TagParser
            public DCTree parse(int i) throws ParseException {
                DocCommentParser.this.skipWhitespace();
                com.sun.tools.javac.util.List<DCTree> listTagAttrs = tagAttrs();
                char c = DocCommentParser.this.ch;
                DocCommentParser docCommentParser = DocCommentParser.this;
                if (c == '}') {
                    docCommentParser.nextChar();
                    return DocCommentParser.this.m.at(i).newSnippetTree((List<? extends DocTree>) listTagAttrs, (TextTree) null);
                }
                char c2 = docCommentParser.ch;
                DocCommentParser docCommentParser2 = DocCommentParser.this;
                if (c2 != ':') {
                    if (docCommentParser2.bp >= DocCommentParser.this.buf.length - 1) {
                        throw new ParseException("dc.no.content");
                    }
                    throw new ParseException("dc.unexpected.content");
                }
                docCommentParser2.newline = false;
                DocCommentParser.this.nextChar();
                while (DocCommentParser.this.bp < DocCommentParser.this.buflen) {
                    DocCommentParser docCommentParser3 = DocCommentParser.this;
                    if (!docCommentParser3.isHorizontalWhitespace(docCommentParser3.ch)) {
                        break;
                    }
                    DocCommentParser.this.nextChar();
                }
                boolean z2 = DocCommentParser.this.newline;
                DocCommentParser docCommentParser4 = DocCommentParser.this;
                if (!z2) {
                    if (docCommentParser4.bp >= DocCommentParser.this.buf.length - 1) {
                        throw new ParseException("dc.no.content");
                    }
                    throw new ParseException("dc.unexpected.content");
                }
                docCommentParser4.nextChar();
                DCTree.DCText dCTextInlineText = DocCommentParser.this.inlineText(WhitespaceRetentionPolicy.RETAIN_ALL);
                DocCommentParser.this.nextChar();
                return DocCommentParser.this.m.at(i).newSnippetTree((List<? extends DocTree>) listTagAttrs, (TextTree) dCTextInlineText);
            }
        }, new TagParser(kind, DocTree.Kind.SPEC) { // from class: com.sun.tools.javac.parser.DocCommentParser.22
            @Override // com.sun.tools.javac.parser.DocCommentParser.TagParser
            public DCTree parse(int i) throws ParseException {
                DocCommentParser.this.skipWhitespace();
                DCTree.DCText dCTextInlineWord = DocCommentParser.this.inlineWord();
                if (dCTextInlineWord == null || dCTextInlineWord.isBlank()) {
                    throw new ParseException("dc.no.url");
                }
                DocCommentParser.this.skipWhitespace();
                com.sun.tools.javac.util.List<DCTree> listBlockContent = DocCommentParser.this.blockContent();
                if (listBlockContent.isEmpty() || DCTree.isBlank(listBlockContent)) {
                    throw new ParseException("dc.no.title");
                }
                return DocCommentParser.this.m.at(i).newSpecTree((TextTree) dCTextInlineWord, (List<? extends DocTree>) listBlockContent);
            }
        }, new TagParser(kind2, DocTree.Kind.SUMMARY) { // from class: com.sun.tools.javac.parser.DocCommentParser.23
            @Override // com.sun.tools.javac.parser.DocCommentParser.TagParser
            public DCTree parse(int i) {
                return DocCommentParser.this.m.at(i).newSummaryTree((List<? extends DocTree>) DocCommentParser.this.inlineContent());
            }
        }, new TagParser(kind2, DocTree.Kind.SYSTEM_PROPERTY) { // from class: com.sun.tools.javac.parser.DocCommentParser.24
            @Override // com.sun.tools.javac.parser.DocCommentParser.TagParser
            public DCTree parse(int i) throws ParseException {
                DocCommentParser.this.skipWhitespace();
                if (DocCommentParser.this.ch == '}') {
                    throw new ParseException("dc.no.content");
                }
                Name systemPropertyName = DocCommentParser.this.readSystemPropertyName();
                if (systemPropertyName == null) {
                    throw new ParseException("dc.no.content");
                }
                DocCommentParser.this.skipWhitespace();
                char c = DocCommentParser.this.ch;
                DocCommentParser docCommentParser = DocCommentParser.this;
                if (c == '}') {
                    docCommentParser.nextChar();
                    return DocCommentParser.this.m.at(i).newSystemPropertyTree((javax.lang.model.element.Name) systemPropertyName);
                }
                docCommentParser.nextChar();
                throw new ParseException("dc.unexpected.content");
            }
        }, new TagParser(kind, DocTree.Kind.THROWS) { // from class: com.sun.tools.javac.parser.DocCommentParser.25
            @Override // com.sun.tools.javac.parser.DocCommentParser.TagParser
            public DCTree parse(int i) throws ParseException {
                DocCommentParser.this.skipWhitespace();
                return DocCommentParser.this.m.at(i).newThrowsTree((ReferenceTree) DocCommentParser.this.reference(ReferenceParser.Mode.MEMBER_DISALLOWED), (List<? extends DocTree>) DocCommentParser.this.blockContent());
            }
        }, new TagParser(kind, DocTree.Kind.USES) { // from class: com.sun.tools.javac.parser.DocCommentParser.26
            @Override // com.sun.tools.javac.parser.DocCommentParser.TagParser
            public DCTree parse(int i) throws ParseException {
                DocCommentParser.this.skipWhitespace();
                return DocCommentParser.this.m.at(i).newUsesTree((ReferenceTree) DocCommentParser.this.reference(ReferenceParser.Mode.MEMBER_DISALLOWED), (List<? extends DocTree>) DocCommentParser.this.blockContent());
            }
        }, new TagParser(kind2, DocTree.Kind.VALUE) { // from class: com.sun.tools.javac.parser.DocCommentParser.27
            @Override // com.sun.tools.javac.parser.DocCommentParser.TagParser
            public DCTree parse(int i) throws ParseException {
                DCTree.DCText dCTextQuotedString;
                DocCommentParser.this.skipWhitespace();
                char c = DocCommentParser.this.ch;
                if (c == '\"') {
                    dCTextQuotedString = DocCommentParser.this.quotedString();
                    DocCommentParser.this.skipWhitespace();
                } else if (c != '%') {
                    dCTextQuotedString = null;
                } else {
                    dCTextQuotedString = DocCommentParser.this.inlineWord();
                    DocCommentParser.this.skipWhitespace();
                }
                DCTree.DCReference dCReferenceReference = DocCommentParser.this.reference(ReferenceParser.Mode.MEMBER_REQUIRED);
                DocCommentParser.this.skipWhitespace();
                char c2 = DocCommentParser.this.ch;
                DocCommentParser docCommentParser = DocCommentParser.this;
                if (c2 != '}') {
                    docCommentParser.nextChar();
                    throw new ParseException("dc.unexpected.content");
                }
                docCommentParser.nextChar();
                DocCommentParser docCommentParser2 = DocCommentParser.this;
                return dCTextQuotedString == null ? docCommentParser2.m.at(i).newValueTree((ReferenceTree) dCReferenceReference) : docCommentParser2.m.at(i).newValueTree((TextTree) dCTextQuotedString, (ReferenceTree) dCReferenceReference);
            }
        }, new TagParser(kind, DocTree.Kind.VERSION) { // from class: com.sun.tools.javac.parser.DocCommentParser.28
            @Override // com.sun.tools.javac.parser.DocCommentParser.TagParser
            public DCTree parse(int i) {
                return DocCommentParser.this.m.at(i).newVersionTree((List<? extends DocTree>) DocCommentParser.this.blockContent());
            }
        }};
        HashMap map = new HashMap();
        for (int i = 0; i < 27; i++) {
            TagParser tagParser2 = tagParserArr[i];
            map.put(this.names.fromString(tagParser2.getTreeKind().tagName), tagParser2);
        }
        return map;
    }

    private static DocTree.Kind getTextKind(Tokens.Comment comment) {
        int i = AnonymousClass29.$SwitchMap$com$sun$tools$javac$parser$Tokens$Comment$CommentStyle[comment.getStyle().ordinal()];
        if (i == 1) {
            return DocTree.Kind.TEXT;
        }
        if (i == 2) {
            return DocTree.Kind.MARKDOWN;
        }
        w01.a(comment.getStyle().name());
        return null;
    }

    private DCTree html() {
        int i = this.bp;
        nextChar();
        boolean z = false;
        if (!isIdentifierStart(this.ch)) {
            char c = this.ch;
            if (c == '/') {
                nextChar();
                if (isIdentifierStart(this.ch)) {
                    Name identifier = readIdentifier();
                    skipWhitespace();
                    if (this.ch == '>') {
                        nextChar();
                        if ("pre".equalsIgnoreCase(identifier.toString())) {
                            this.inPre = false;
                        }
                        return this.m.at(i).newEndElementTree((javax.lang.model.element.Name) identifier).setEndPos(this.bp);
                    }
                }
            } else if (c == '!') {
                nextChar();
                char c2 = this.ch;
                if (c2 != '-') {
                    if (!isIdentifierStart(c2) || !peek("doctype")) {
                        for (int i2 = 0; i2 < 7; i2++) {
                            if (this.ch != "[CDATA[".charAt(i2)) {
                                return erroneous("dc.invalid.html", i);
                            }
                            nextChar();
                        }
                        while (this.bp < this.buflen) {
                            if (this.ch == ']') {
                                int i3 = 0;
                                while (this.bp < this.buflen && this.ch == ']') {
                                    i3++;
                                    nextChar();
                                }
                                if (i3 >= 2 && this.ch == '>') {
                                    nextChar();
                                    return this.m.at(i).newTextTree(newString(i, this.bp));
                                }
                            } else {
                                nextChar();
                            }
                        }
                        return erroneous("dc.invalid.html", i);
                    }
                    readIdentifier();
                    nextChar();
                    skipWhitespace();
                    int i4 = this.bp;
                    while (true) {
                        int i5 = this.bp;
                        if (i5 >= this.buflen) {
                            break;
                        }
                        if (this.ch == '>') {
                            nextChar();
                            return this.m.at(i4).newDocTypeTree(newString(i4, i5));
                        }
                        nextChar();
                    }
                } else {
                    nextChar();
                    if (this.ch == '-') {
                        nextChar();
                        while (this.bp < this.buflen) {
                            int i6 = 0;
                            while (this.bp < this.buflen && this.ch == '-') {
                                i6++;
                                nextChar();
                            }
                            if (i6 >= 2 && this.ch == '>') {
                                nextChar();
                                return this.m.at(i).newCommentTree(newString(i, this.bp));
                            }
                            nextChar();
                        }
                    }
                }
            }
        } else {
            Name identifier2 = readIdentifier();
            com.sun.tools.javac.util.List<DCTree> listHtmlAttrs = htmlAttrs();
            if (listHtmlAttrs != null) {
                if (this.ch == '/') {
                    nextChar();
                    z = true;
                }
                if (this.ch == '>') {
                    nextChar();
                    if ("pre".equalsIgnoreCase(identifier2.toString())) {
                        this.inPre = true;
                    }
                    return this.m.at(i).newStartElementTree((javax.lang.model.element.Name) identifier2, (List<? extends DocTree>) listHtmlAttrs, z).setEndPos(this.bp);
                }
            }
        }
        int i7 = i + 1;
        this.bp = i7;
        this.ch = this.buf[i7];
        return erroneous("dc.malformed.html", i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public com.sun.tools.javac.util.List<DCTree> inlineContent() {
        skipWhitespace();
        return content(Phase.INLINE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DCTree.DCText inlineText(WhitespaceRetentionPolicy whitespaceRetentionPolicy) throws ParseException {
        int iOrdinal = whitespaceRetentionPolicy.ordinal();
        int i = 1;
        if (iOrdinal != 1) {
            if (iOrdinal == 2) {
                skipWhitespace();
            }
        } else if (this.ch == ' ') {
            nextChar();
        }
        int i2 = this.bp;
        while (true) {
            int i3 = this.bp;
            if (i3 >= this.buflen) {
                throw new ParseException("dc.unterminated.inline.tag");
            }
            char c = this.ch;
            if (c != '\t' && c != '\n' && c != '\f' && c != '\r' && c != ' ') {
                if (c == '{') {
                    this.newline = false;
                    this.lastNonWhite = i3;
                    i++;
                } else if (c != '}') {
                    this.newline = false;
                    this.lastNonWhite = i3;
                } else {
                    i--;
                    if (i == 0) {
                        return this.m.at(i2).newTextTree(newString(i2, this.bp));
                    }
                    this.newline = false;
                    this.lastNonWhite = i3;
                }
            }
            nextChar();
        }
    }

    private String showPos(int i) {
        StringBuilder sb = new StringBuilder("[");
        sb.append(i);
        sb.append("] ");
        if (i >= 0) {
            for (int iMax = Math.max(i - 10, 0); iMax < Math.min(i + 10, this.buflen); iMax++) {
                if (iMax == i) {
                    sb.append("[");
                }
                char c = this.buf[iMax];
                if (c == '\n') {
                    c = '|';
                } else if (c == ' ') {
                    c = '_';
                }
                sb.append(c);
                if (iMax == i) {
                    sb.append("]");
                }
            }
        }
        return sb.toString();
    }

    private IllegalStateException unknownTextKind(DocTree.Kind kind) {
        return new IllegalStateException(kind.toString());
    }

    public void addPendingText(ListBuffer<DCTree> listBuffer, int i, DocTree.Kind kind) {
        int i2 = this.textStart;
        if (i2 != -1) {
            if (i2 <= i) {
                int i3 = AnonymousClass29.$SwitchMap$com$sun$source$doctree$DocTree$Kind[kind.ordinal()];
                if (i3 == 1) {
                    listBuffer.add(this.m.at(this.textStart).newRawTextTree(DocTree.Kind.MARKDOWN, newString(this.textStart, i + 1)));
                } else {
                    if (i3 != 2) {
                        b6c.a(kind);
                        return;
                    }
                    listBuffer.add(this.m.at(this.textStart).newTextTree(newString(this.textStart, i + 1)));
                }
            }
            this.textStart = -1;
        }
    }

    public void attrValueChar(ListBuffer<DCTree> listBuffer) {
        char c = this.ch;
        if (c == '&') {
            entity(listBuffer);
        } else if (c != '{') {
            nextChar();
        } else {
            inlineTag(listBuffer);
        }
    }

    public com.sun.tools.javac.util.List<DCTree> blockContent() {
        while (isHorizontalWhitespace(this.ch) && this.bp < this.buflen) {
            nextChar();
        }
        return content(Phase.BODY);
    }

    public DCTree blockTag() {
        this.newline = false;
        int i = this.bp;
        try {
            nextChar();
            if (!isIdentifierStart(this.ch)) {
                int i2 = this.bp;
                blockContent();
                return erroneous("dc.no.tag.name", i, i2);
            }
            Name tagName = readTagName();
            TagParser tagParser = this.tagParsers.get(tagName);
            if (tagParser == null) {
                return this.m.at(i).newUnknownBlockTagTree((javax.lang.model.element.Name) tagName, (List<? extends DocTree>) blockContent());
            }
            return tagParser.allowsBlock() ? tagParser.parse(i, TagParser.Kind.BLOCK) : erroneous("dc.bad.inline.tag", i);
        } catch (ParseException e) {
            blockContent();
            return erroneous(e.getMessage(), i, e.pos);
        }
    }

    public com.sun.tools.javac.util.List<DCTree> blockTags() {
        ListBuffer listBuffer = new ListBuffer();
        while (this.bp < this.buflen && this.ch == '@') {
            listBuffer.add(blockTag());
        }
        return listBuffer.toList();
    }

    public com.sun.tools.javac.util.List<DCTree> content(Phase phase) {
        ListBuffer<DCTree> listBuffer = new ListBuffer<>();
        this.textStart = -1;
        int i = this.bp;
        if (this.textKind == DocTree.Kind.MARKDOWN) {
            initMarkdownLine();
        }
        ListBuffer<DCTree> listBuffer2 = null;
        int i2 = 1;
        while (true) {
            int i3 = this.bp;
            if (i3 >= this.buflen) {
                break;
            }
            char c = this.ch;
            if (c != '\t') {
                if (c == '\n' || c == '\r') {
                    nextChar();
                    if (this.textKind == DocTree.Kind.MARKDOWN) {
                        initMarkdownLine();
                    }
                } else if (c != ' ') {
                    if (c == '&') {
                        int i4 = AnonymousClass29.$SwitchMap$com$sun$source$doctree$DocTree$Kind[this.textKind.ordinal()];
                        if (i4 == 1) {
                            defaultContentCharacter();
                        } else {
                            if (i4 != 2) {
                                throw unknownTextKind(this.textKind);
                            }
                            entity(listBuffer);
                        }
                    } else if (c == '<') {
                        int i5 = AnonymousClass29.$SwitchMap$com$sun$source$doctree$DocTree$Kind[this.textKind.ordinal()];
                        if (i5 == 1) {
                            defaultContentCharacter();
                        } else {
                            if (i5 != 2) {
                                throw unknownTextKind(this.textKind);
                            }
                            this.newline = false;
                            if (this.isHtmlFile) {
                                int iOrdinal = phase.ordinal();
                                if (iOrdinal == 0) {
                                    if (isEndPreamble()) {
                                        listBuffer.add(html());
                                        if (this.textStart == -1) {
                                            this.textStart = this.bp;
                                            this.lastNonWhite = -1;
                                        }
                                        this.newline = true;
                                        break;
                                    }
                                } else if (iOrdinal == 1 && isEndBody()) {
                                    addPendingText(listBuffer, this.lastNonWhite);
                                    break;
                                }
                            }
                            addPendingText(listBuffer, this.bp - 1);
                            listBuffer.add(html());
                            if (this.inPre) {
                                if (listBuffer2 == null) {
                                    listBuffer2 = listBuffer;
                                    listBuffer = new ListBuffer<>();
                                }
                            } else if (listBuffer2 != null) {
                                listBuffer2.addAll(normalizePreContent(listBuffer));
                                listBuffer = listBuffer2;
                                listBuffer2 = null;
                            }
                            if (phase != Phase.PREAMBLE && phase != Phase.POSTAMBLE && this.textStart == -1) {
                                this.textStart = this.bp;
                                this.lastNonWhite = -1;
                            }
                        }
                    } else if (c != '@') {
                        if (c != '\\') {
                            if (c != '`') {
                                if (c == '{') {
                                    int iOrdinal2 = phase.ordinal();
                                    if (iOrdinal2 != 0) {
                                        if (iOrdinal2 == 1) {
                                            inlineTag(listBuffer);
                                        } else if (iOrdinal2 != 2) {
                                            if (iOrdinal2 == 3 && !inlineTag(listBuffer)) {
                                                i2++;
                                            }
                                        }
                                    }
                                    defaultContentCharacter();
                                } else if (c != '}') {
                                    if (c != '~') {
                                        defaultContentCharacter();
                                    }
                                } else if (phase == Phase.INLINE) {
                                    this.newline = false;
                                    i2--;
                                    if (i2 == 0) {
                                        addPendingText(listBuffer, i3 - 1);
                                        nextChar();
                                        return listBuffer.toList();
                                    }
                                    nextChar();
                                } else {
                                    defaultContentCharacter();
                                }
                            }
                            int i6 = AnonymousClass29.$SwitchMap$com$sun$source$doctree$DocTree$Kind[this.textKind.ordinal()];
                            if (i6 == 1) {
                                this.newline = false;
                                if (this.textStart == -1) {
                                    this.textStart = this.bp;
                                }
                                this.lastNonWhite = this.bp;
                                boolean zIsCodeFence = this.markdown.isCodeFence();
                                char c2 = this.ch;
                                if (c2 == '`' || (c2 == '~' && zIsCodeFence)) {
                                    int iSkipCode = this.markdown.skipCode();
                                    if (iSkipCode != -1) {
                                        this.lastNonWhite = iSkipCode - 1;
                                    } else if (zIsCodeFence) {
                                        this.lastNonWhite = this.buflen - 1;
                                    } else {
                                        this.bp = this.lastNonWhite;
                                        this.newline = false;
                                        nextChar();
                                    }
                                } else {
                                    nextChar();
                                }
                            } else if (i6 == 2) {
                                defaultContentCharacter();
                            }
                        } else {
                            int i7 = AnonymousClass29.$SwitchMap$com$sun$source$doctree$DocTree$Kind[this.textKind.ordinal()];
                            if (i7 == 1) {
                                defaultContentCharacter();
                                nextChar();
                                defaultContentCharacter();
                            } else if (i7 == 2) {
                                defaultContentCharacter();
                            }
                        }
                    } else if (this.newline) {
                        char cPeekChar = peekChar();
                        if (cPeekChar == '@' || cPeekChar == '*') {
                            addPendingText(listBuffer, this.bp - 1);
                            nextChar();
                            listBuffer.add(this.m.at(this.bp - 1).newEscapeTree(this.ch));
                            this.newline = false;
                            nextChar();
                            this.textStart = this.bp;
                        } else {
                            if (phase == Phase.BODY) {
                                addPendingText(listBuffer, this.lastNonWhite);
                                break;
                            }
                            defaultContentCharacter();
                        }
                    } else if (this.textStart != -1 && this.buf[i3 - 1] == '*' && peekChar() == '/') {
                        addPendingText(listBuffer, this.bp - 1);
                        nextChar();
                        listBuffer.add(this.m.at(this.bp - 1).newEscapeTree('/'));
                        this.newline = false;
                        nextChar();
                        this.textStart = this.bp;
                    } else {
                        defaultContentCharacter();
                    }
                }
            }
            if (this.textKind == DocTree.Kind.MARKDOWN && this.textStart == -1) {
                this.textStart = i3;
            }
            nextChar();
        }
        int i8 = this.lastNonWhite;
        if (i8 != -1) {
            addPendingText(listBuffer, i8);
        }
        if (listBuffer2 != null) {
            listBuffer2.addAll(listBuffer);
            listBuffer = listBuffer2;
        }
        return phase == Phase.INLINE ? com.sun.tools.javac.util.List.of(erroneous("dc.unterminated.inline.tag", i)) : listBuffer.toList();
    }

    public void defaultContentCharacter() {
        this.newline = false;
        if (this.textStart == -1) {
            this.textStart = this.bp;
        }
        this.lastNonWhite = this.bp;
        nextChar();
    }

    public DCTree entity() {
        int i = this.bp;
        nextChar();
        char c = this.ch;
        Name identifier = null;
        if (c == '#') {
            int i2 = this.bp;
            nextChar();
            if (isDecimalDigit(this.ch)) {
                nextChar();
                while (this.bp < this.buflen && isDecimalDigit(this.ch)) {
                    nextChar();
                }
                identifier = this.names.fromChars(this.buf, i2, this.bp - i2);
            } else {
                char c2 = this.ch;
                if (c2 == 'x' || c2 == 'X') {
                    nextChar();
                    if (isHexDigit(this.ch)) {
                        nextChar();
                        while (this.bp < this.buflen && isHexDigit(this.ch)) {
                            nextChar();
                        }
                        identifier = this.names.fromChars(this.buf, i2, this.bp - i2);
                    }
                }
            }
        } else if (isIdentifierStart(c)) {
            identifier = readIdentifier();
        }
        if (identifier == null) {
            return erroneous("dc.bad.entity", i);
        }
        if (this.ch != ';') {
            return erroneous("dc.missing.semicolon", i);
        }
        nextChar();
        return this.m.at(i).newEntityTree((javax.lang.model.element.Name) identifier);
    }

    public DCTree.DCErroneous erroneous(String str, int i, int i2) {
        int i3 = this.bp - 1;
        while (i3 > i) {
            char c = this.buf[i3];
            if (c != '\t') {
                if (c == '\n' || c == '\f' || c == '\r') {
                    this.newline = true;
                } else if (c != ' ') {
                    break;
                }
            }
            i3--;
        }
        if (i2 == -1) {
            i2 = i3;
        }
        int i4 = i3 + 1;
        this.textStart = -1;
        return this.m.at(i).newErroneousTree(newString(i, i4), (Diagnostic<JavaFileObject>) this.diags.error(null, this.diagSource, DCTree.createDiagnosticPosition(this.comment, i, i2, i4), str, new Object[0])).setPrefPos(i2);
    }

    public com.sun.tools.javac.util.List<DCTree> htmlAttrs() {
        com.sun.tools.javac.util.List<DCTree> list;
        int i;
        AttributeTree.ValueKind valueKind;
        ListBuffer listBuffer = new ListBuffer();
        skipWhitespace();
        while (this.bp < this.buflen && isIdentifierStart(this.ch)) {
            int i2 = this.bp;
            Name attributeName = readAttributeName();
            skipWhitespace();
            AttributeTree.ValueKind valueKind2 = AttributeTree.ValueKind.EMPTY;
            if (this.ch == '=') {
                ListBuffer<DCTree> listBuffer2 = new ListBuffer<>();
                nextChar();
                skipWhitespace();
                char c = this.ch;
                if (c == '\'' || c == '\"') {
                    this.newline = false;
                    AttributeTree.ValueKind valueKind3 = c == '\'' ? AttributeTree.ValueKind.SINGLE : AttributeTree.ValueKind.DOUBLE;
                    nextChar();
                    this.textStart = this.bp;
                    while (true) {
                        i = this.bp;
                        if (i >= this.buflen || this.ch == c) {
                            break;
                        }
                        attrValueChar(listBuffer2);
                    }
                    addPendingText(listBuffer2, i - 1, DocTree.Kind.TEXT);
                    nextChar();
                    valueKind = valueKind3;
                } else {
                    valueKind = AttributeTree.ValueKind.UNQUOTED;
                    this.textStart = this.bp;
                    while (this.bp < this.buflen && !isUnquotedAttrValueTerminator(this.ch)) {
                        attrValueChar(listBuffer2);
                    }
                    addPendingText(listBuffer2, this.bp - 1, DocTree.Kind.TEXT);
                }
                skipWhitespace();
                AttributeTree.ValueKind valueKind4 = valueKind;
                list = listBuffer2.toList();
                valueKind2 = valueKind4;
            } else {
                list = null;
            }
            listBuffer.add(this.m.at(i2).newAttributeTree((javax.lang.model.element.Name) attributeName, valueKind2, (List<? extends DocTree>) list));
        }
        return listBuffer.toList();
    }

    public DCTree.DCIdentifier identifier() throws ParseException {
        skipWhitespace();
        int i = this.bp;
        if (!isJavaIdentifierStart(this.ch)) {
            throw new ParseException("dc.identifier.expected");
        }
        return this.m.at(i).newIdentifierTree((javax.lang.model.element.Name) readJavaIdentifier());
    }

    public void initMarkdownLine() {
        if (this.textStart == -1) {
            this.textStart = this.bp;
        }
        this.markdown.update();
        if (this.markdown.isIndentedCodeBlock()) {
            this.markdown.skipLine();
            this.lastNonWhite = this.bp - 1;
        }
    }

    /* JADX WARN: Type inference failed for: r4v2, types: [com.sun.tools.javac.tree.DCTree, com.sun.tools.javac.tree.DCTree$DCEndPosTree] */
    /* JADX WARN: Type inference failed for: r4v4, types: [com.sun.tools.javac.tree.DCTree, com.sun.tools.javac.tree.DCTree$DCEndPosTree] */
    public DCTree inlineTag() {
        int i = this.bp - 1;
        try {
            nextChar();
            if (!isIdentifierStart(this.ch)) {
                return erroneous("dc.no.tag.name", i, this.bp);
            }
            Name tagName = readTagName();
            TagParser tagParser = this.tagParsers.get(tagName);
            if (tagParser == null) {
                skipWhitespace();
                DCTree.DCText dCTextInlineText = inlineText(WhitespaceRetentionPolicy.REMOVE_ALL);
                nextChar();
                return this.m.at(i).newUnknownInlineTagTree((javax.lang.model.element.Name) tagName, (List<? extends DocTree>) com.sun.tools.javac.util.List.of(dCTextInlineText)).setEndPos(this.bp);
            }
            if (!tagParser.retainWhiteSpace) {
                skipWhitespace();
            }
            if (tagParser.allowsInline()) {
                return ((DCTree.DCEndPosTree) tagParser.parse(i, TagParser.Kind.INLINE)).setEndPos(this.bp);
            }
            DCTree.DCText dCTextInlineText2 = inlineText(WhitespaceRetentionPolicy.REMOVE_ALL);
            nextChar();
            return this.m.at(i).newUnknownInlineTagTree((javax.lang.model.element.Name) tagName, (List<? extends DocTree>) com.sun.tools.javac.util.List.of(dCTextInlineText2)).setEndPos(this.bp);
        } catch (ParseException e) {
            return erroneous(e.getMessage(), i, e.pos);
        }
    }

    public DCTree.DCText inlineWord() {
        int i = this.bp;
        int i2 = 0;
        while (this.bp < this.buflen) {
            char c = this.ch;
            if (c == '\t' || c == '\n' || c == '\f' || c == '\r' || c == ' ') {
                return this.m.at(i).newTextTree(newString(i, this.bp));
            }
            if (c != '@') {
                if (c == '{') {
                    i2++;
                } else if (c != '}') {
                    continue;
                } else {
                    if (i2 == 0) {
                        return this.m.at(i).newTextTree(newString(i, this.bp));
                    }
                    i2--;
                }
            } else if (this.newline) {
                return null;
            }
            this.newline = false;
            nextChar();
        }
        return null;
    }

    public boolean isDecimalDigit(char c) {
        return '0' <= c && c <= '9';
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0049, code lost:
    
        if (r1.equals("body") != false) goto L22;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean isEndBody() {
        int i = this.bp;
        try {
            if (this.ch == '<') {
                nextChar();
            }
            if (this.ch == '/') {
                nextChar();
                if (isIdentifierStart(this.ch)) {
                    String lowerCase = StringUtils.toLowerCase(readIdentifier().toString());
                    int iHashCode = lowerCase.hashCode();
                    if (iHashCode != 3029410) {
                        if (iHashCode == 3343801 && lowerCase.equals("main")) {
                            this.bp = i;
                            this.ch = this.buf[i];
                            return true;
                        }
                    }
                }
            }
            return false;
        } finally {
            this.bp = i;
            this.ch = this.buf[i];
        }
    }

    /* JADX WARN: Code duplicated, block: B:46:0x00a8  */
    public boolean isEndPreamble() {
        char c;
        char c2;
        int i = this.bp;
        try {
            if (this.ch == '<') {
                nextChar();
            }
            if (isIdentifierStart(this.ch)) {
                String lowerCase = StringUtils.toLowerCase(readIdentifier().toString());
                int iHashCode = lowerCase.hashCode();
                if (iHashCode != 3029410) {
                    if (iHashCode == 3343801 && lowerCase.equals("main")) {
                        this.bp = i;
                        c2 = this.buf[i];
                        this.ch = c2;
                        return true;
                    }
                    this.bp = i;
                    c = this.buf[i];
                } else {
                    if (lowerCase.equals("body")) {
                        while (this.bp < this.buflen && this.ch != '>') {
                            nextChar();
                        }
                        if (this.ch == '>') {
                            nextChar();
                        }
                        while (this.bp < this.buflen && isWhitespace(this.ch)) {
                            nextChar();
                        }
                        if (this.ch == '<') {
                            nextChar();
                            if (isIdentifierStart(this.ch) && StringUtils.toLowerCase(readIdentifier().toString()).equals("main")) {
                                this.bp = i;
                                c = this.buf[i];
                            }
                        }
                        this.bp = i;
                        c2 = this.buf[i];
                        this.ch = c2;
                        return true;
                    }
                    this.bp = i;
                    c = this.buf[i];
                }
            } else {
                this.bp = i;
                c = this.buf[i];
            }
            this.ch = c;
            return false;
        } catch (Throwable th) {
            this.bp = i;
            this.ch = this.buf[i];
            throw th;
        }
    }

    public boolean isHexDigit(char c) {
        if ('0' <= c && c <= '9') {
            return true;
        }
        if ('a' > c || c > 'f') {
            return 'A' <= c && c <= 'F';
        }
        return true;
    }

    public boolean isHorizontalWhitespace(char c) {
        return c == ' ' || c == '\t';
    }

    public boolean isIdentifierStart(char c) {
        return Character.isUnicodeIdentifierStart(c);
    }

    public boolean isJavaIdentifierStart(char c) {
        return Character.isJavaIdentifierStart(c);
    }

    public boolean isUnquotedAttrValueTerminator(char c) {
        if (c == '\t' || c == '\n' || c == '\f' || c == '\r' || c == ' ' || c == '\"' || c == '\'' || c == '`') {
            return true;
        }
        switch (c) {
            case '<':
            case '=':
            case '>':
                return true;
            default:
                return false;
        }
    }

    public boolean isWhitespace(char c) {
        return Character.isWhitespace(c);
    }

    public String newString(int i, int i2) {
        return new String(this.buf, i, i2 - i);
    }

    public void nextChar() {
        int i;
        char[] cArr = this.buf;
        int i2 = this.bp;
        int i3 = this.buflen;
        if (i2 < i3) {
            i = i2 + 1;
            this.bp = i;
        } else {
            i = i3;
        }
        char c = cArr[i];
        this.ch = c;
        if (c == '\n') {
            this.newline = true;
            return;
        }
        if (c != '\r') {
            return;
        }
        int i4 = this.bp;
        if (i4 + 1 < i3 && cArr[i4 + 1] == '\n') {
            this.bp = i4 + 1;
            this.ch = '\n';
        }
        this.newline = true;
    }

    public ListBuffer<DCTree> normalizePreContent(ListBuffer<DCTree> listBuffer) {
        if (this.textKind != DocTree.Kind.MARKDOWN && !this.isHtmlFile) {
            SimpleDocTreeVisitor<DCTree, C1Context> simpleDocTreeVisitor = new SimpleDocTreeVisitor<DCTree, C1Context>() { // from class: com.sun.tools.javac.parser.DocCommentParser.1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.sun.source.util.SimpleDocTreeVisitor, com.sun.source.doctree.DocTreeVisitor
                public DCTree visitLiteral(LiteralTree literalTree, C1Context c1Context) {
                    if (c1Context.state != C1State.BEFORE_CODE || !literalTree.getBody().getBody().startsWith("\n")) {
                        c1Context.unexpectedTree();
                        return (DCTree) literalTree;
                    }
                    c1Context.state = C1State.SUCCEEDED;
                    DCTree.DCText dCText = (DCTree.DCText) literalTree.getBody();
                    DCTree.DCText dCTextNewTextTree = DocCommentParser.this.m.at(dCText.pos + 1).newTextTree(dCText.getBody().substring(1));
                    DocCommentParser.this.m.at(((DCTree) literalTree).pos);
                    DocTree.Kind kind = literalTree.getKind();
                    DocTree.Kind kind2 = DocTree.Kind.CODE;
                    DocCommentParser docCommentParser = DocCommentParser.this;
                    return kind == kind2 ? docCommentParser.m.newCodeTree((TextTree) dCTextNewTextTree) : docCommentParser.m.newLiteralTree((TextTree) dCTextNewTextTree);
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.sun.source.util.SimpleDocTreeVisitor, com.sun.source.doctree.DocTreeVisitor
                public DCTree visitStartElement(StartElementTree startElementTree, C1Context c1Context) {
                    if (c1Context.state == C1State.BEFORE_CODE && startElementTree.getName().toString().equalsIgnoreCase("code")) {
                        c1Context.state = C1State.AFTER_CODE;
                    } else {
                        c1Context.unexpectedTree();
                    }
                    return (DCTree) startElementTree;
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.sun.source.util.SimpleDocTreeVisitor, com.sun.source.doctree.DocTreeVisitor
                public DCTree visitText(TextTree textTree, C1Context c1Context) {
                    if (c1Context.state == C1State.BEFORE_CODE && textTree.getBody().matches("[ \t]+")) {
                        return null;
                    }
                    if (c1Context.state == C1State.AFTER_CODE && textTree.getBody().startsWith("\n")) {
                        c1Context.state = C1State.SUCCEEDED;
                        return DocCommentParser.this.m.at(((DCTree.DCText) textTree).pos + 1).newTextTree(textTree.getBody().substring(1));
                    }
                    c1Context.unexpectedTree();
                    return (DCTree) textTree;
                }

                @Override // com.sun.source.util.SimpleDocTreeVisitor
                public DCTree defaultAction(DocTree docTree, C1Context c1Context) {
                    c1Context.unexpectedTree();
                    return (DCTree) docTree;
                }
            };
            C1Context c1Context = new C1Context();
            ListBuffer<DCTree> listBuffer2 = new ListBuffer<>();
            Iterator<DCTree> it = listBuffer.iterator();
            while (it.hasNext()) {
                DCTree dCTreeVisit = simpleDocTreeVisitor.visit(it.next(), c1Context);
                if (dCTreeVisit != null) {
                    listBuffer2.add(dCTreeVisit);
                }
                if (c1Context.state == C1State.FAILED) {
                }
            }
            if (c1Context.state == C1State.SUCCEEDED) {
                return listBuffer2;
            }
        }
        return listBuffer;
    }

    public DCTree.DCDocComment parse() {
        String text = this.comment.getText();
        this.buf = new char[text.length() + 1];
        int i = 0;
        text.getChars(0, text.length(), this.buf, 0);
        char[] cArr = this.buf;
        cArr[cArr.length - 1] = 26;
        this.buflen = cArr.length - 1;
        this.bp = -1;
        nextChar();
        com.sun.tools.javac.util.List<DCTree> listContent = this.isHtmlFile ? content(Phase.PREAMBLE) : com.sun.tools.javac.util.List.nil();
        com.sun.tools.javac.util.List<DCTree> listContent2 = content(Phase.BODY);
        com.sun.tools.javac.util.List<DCTree> listBlockTags = blockTags();
        com.sun.tools.javac.util.List<DCTree> listContent3 = this.isHtmlFile ? content(Phase.POSTAMBLE) : com.sun.tools.javac.util.List.nil();
        if (this.textKind != DocTree.Kind.MARKDOWN) {
            if (!listContent.isEmpty()) {
                i = listContent.head.pos;
            } else if (!listContent2.isEmpty()) {
                i = listContent2.head.pos;
            } else if (!listBlockTags.isEmpty()) {
                i = listBlockTags.head.pos;
            } else if (!listContent3.isEmpty()) {
                i = listContent3.head.pos;
            }
        }
        return this.m.at(i).newDocCommentTree(this.comment, listContent2, listBlockTags, listContent, listContent3);
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0036 A[Catch: all -> 0x000c, TRY_LEAVE, TryCatch #0 {all -> 0x000c, blocks: (B:3:0x0002, B:5:0x0008, B:8:0x000e, B:10:0x0015, B:16:0x0027, B:17:0x002e, B:19:0x0036), top: B:25:0x0002 }] */
    /* JADX WARN: Code duplicated, block: B:22:0x004f  */
    public boolean peek(String str) {
        char c;
        int i = this.bp;
        try {
            if (this.ch == '<') {
                nextChar();
            }
            if (this.ch != '/') {
                if (isIdentifierStart(this.ch)) {
                    boolean zEquals = StringUtils.toLowerCase(readIdentifier().toString()).equals(str);
                    this.bp = i;
                    this.ch = this.buf[i];
                    return zEquals;
                }
                this.bp = i;
                c = this.buf[i];
            } else if (str.charAt(0) != this.ch) {
                this.bp = i;
                c = this.buf[i];
            } else {
                str = str.substring(1);
                nextChar();
                if (isIdentifierStart(this.ch)) {
                    boolean zEquals2 = StringUtils.toLowerCase(readIdentifier().toString()).equals(str);
                    this.bp = i;
                    this.ch = this.buf[i];
                    return zEquals2;
                }
                this.bp = i;
                c = this.buf[i];
            }
            this.ch = c;
            return false;
        } catch (Throwable th) {
            this.bp = i;
            this.ch = this.buf[i];
            throw th;
        }
    }

    public char peekChar() {
        char[] cArr = this.buf;
        int i = this.bp;
        int i2 = this.buflen;
        if (i < i2) {
            i2 = i + 1;
        }
        return cArr[i2];
    }

    public DCTree.DCText quotedString() {
        this.newline = false;
        int i = this.bp;
        nextChar();
        while (this.bp < this.buflen) {
            char c = this.ch;
            if (c == '\"') {
                nextChar();
                return this.m.at(i).newTextTree(newString(i, this.bp));
            }
            if (c == '@' && this.newline) {
                return null;
            }
            nextChar();
        }
        return null;
    }

    public Name readAttributeName() {
        int i = this.bp;
        nextChar();
        while (this.bp < this.buflen && (Character.isUnicodeIdentifierPart(this.ch) || this.ch == '-')) {
            nextChar();
        }
        return this.names.fromChars(this.buf, i, this.bp - i);
    }

    public Name readIdentifier() {
        int i = this.bp;
        nextChar();
        while (this.bp < this.buflen && Character.isUnicodeIdentifierPart(this.ch)) {
            nextChar();
        }
        return this.names.fromChars(this.buf, i, this.bp - i);
    }

    public Name readJavaIdentifier() {
        int i = this.bp;
        nextChar();
        while (this.bp < this.buflen && Character.isJavaIdentifierPart(this.ch)) {
            nextChar();
        }
        return this.names.fromChars(this.buf, i, this.bp - i);
    }

    public Name readSystemPropertyName() {
        int i = this.bp;
        nextChar();
        while (true) {
            if ((this.bp >= this.buflen || !Character.isUnicodeIdentifierPart(this.ch)) && this.ch != '.') {
                return this.names.fromChars(this.buf, i, this.bp - i);
            }
            nextChar();
        }
    }

    public Name readTagName() {
        char c;
        int i = this.bp;
        nextChar();
        while (this.bp < this.buflen && (Character.isUnicodeIdentifierPart(this.ch) || (c = this.ch) == '.' || c == '-' || c == ':')) {
            nextChar();
        }
        return this.names.fromChars(this.buf, i, this.bp - i);
    }

    /* JADX WARN: Code duplicated, block: B:35:0x0047  */
    /* JADX WARN: Code duplicated, block: B:36:0x004c  */
    public DCTree.DCReference reference(ReferenceParser.Mode mode) throws ParseException {
        int i;
        int i2 = this.bp;
        int i3 = 0;
        while (true) {
            i = this.bp;
            if (i >= this.buflen) {
                break;
            }
            char c = this.ch;
            if (c != '\t' && c != '\n' && c != '\f' && c != '\r' && c != ' ') {
                if (c == '<') {
                    this.newline = false;
                    i3++;
                } else if (c == '>') {
                    this.newline = false;
                    i3--;
                } else if (c != '@') {
                    if (c == '}') {
                        if (i != i2) {
                            this.newline = false;
                            break;
                        }
                        return null;
                    }
                    if (c == '(') {
                        this.newline = false;
                        i3++;
                    } else if (c != ')') {
                        this.newline = false;
                    } else {
                        this.newline = false;
                        i3--;
                    }
                } else if (this.newline) {
                    break;
                }
                nextChar();
            } else {
                if (i3 == 0) {
                    break;
                }
                nextChar();
            }
        }
        if (i3 > 0) {
            throw new ParseException("dc.unterminated.signature");
        }
        String strNewString = newString(i2, i);
        try {
            return this.m.at(i2).newReferenceTree(strNewString, new ReferenceParser(this.fac).parse(strNewString, mode)).setEndPos(this.bp);
        } catch (ReferenceParser.ParseException e) {
            throw new ParseException(i2 + e.pos, e.getMessage());
        }
    }

    public void skipWhitespace() {
        while (this.bp < this.buflen && isWhitespace(this.ch)) {
            nextChar();
        }
    }

    public static class ParseException extends Exception {
        private static final long serialVersionUID = 0;
        final int pos;

        public ParseException(int i, String str) {
            super(str);
            this.pos = i;
        }

        public ParseException(String str) {
            this(-1, str);
        }
    }

    public static abstract class TagParser {
        final Kind kind;
        final boolean retainWhiteSpace;
        final DocTree.Kind treeKind;

        public enum Kind {
            INLINE,
            BLOCK,
            EITHER
        }

        public TagParser(Kind kind, DocTree.Kind kind2) {
            this.kind = kind;
            this.treeKind = kind2;
            this.retainWhiteSpace = false;
        }

        public boolean allowsBlock() {
            return this.kind != Kind.INLINE;
        }

        public boolean allowsInline() {
            return this.kind != Kind.BLOCK;
        }

        public DocTree.Kind getTreeKind() {
            return this.treeKind;
        }

        public DCTree parse(int i, Kind kind) throws ParseException {
            Kind kind2 = this.kind;
            if (kind == kind2 || kind2 == Kind.EITHER) {
                return parse(i);
            }
            b6c.a(kind);
            return null;
        }

        public TagParser(Kind kind, DocTree.Kind kind2, boolean z) {
            this.kind = kind;
            this.treeKind = kind2;
            this.retainWhiteSpace = z;
        }

        public DCTree parse(int i) throws ParseException {
            throw new UnsupportedOperationException();
        }
    }

    public DocCommentParser(ParserFactory parserFactory, DiagnosticSource diagnosticSource, Tokens.Comment comment) {
        this(parserFactory, diagnosticSource, comment, false);
    }

    public void addPendingText(ListBuffer<DCTree> listBuffer, int i) {
        addPendingText(listBuffer, i, this.textKind);
    }

    public DCTree.DCErroneous erroneous(String str, int i) {
        return erroneous(str, i, -1);
    }

    public boolean inlineTag(ListBuffer<DCTree> listBuffer) {
        this.newline = false;
        nextChar();
        if (this.ch == '@') {
            if (peekChar() == '@') {
                if (this.textStart == -1) {
                    this.textStart = this.bp - 1;
                }
                addPendingText(listBuffer, this.bp - 1);
                nextChar();
                listBuffer.add(this.m.at(this.bp - 1).newEscapeTree('@'));
                nextChar();
                this.textStart = -1;
                this.lastNonWhite = this.bp;
            } else {
                addPendingText(listBuffer, this.bp - 2);
                listBuffer.add(inlineTag());
                this.textStart = this.bp;
                this.lastNonWhite = -1;
                return true;
            }
        } else {
            if (this.textStart == -1) {
                this.textStart = this.bp - 1;
            }
            this.lastNonWhite = this.bp;
        }
        return false;
    }

    public void entity(ListBuffer<DCTree> listBuffer) {
        this.newline = false;
        addPendingText(listBuffer, this.bp - 1);
        listBuffer.add(entity());
        if (this.textStart == -1) {
            this.textStart = this.bp;
            this.lastNonWhite = -1;
        }
    }
}
