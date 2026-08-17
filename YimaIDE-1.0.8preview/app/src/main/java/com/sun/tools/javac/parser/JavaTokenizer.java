package com.sun.tools.javac.parser;

import com.sun.org.apache.xml.internal.serializer.CharInfo;
import com.sun.org.apache.xml.internal.utils.LocaleUtility;
import com.sun.tools.javac.code.Preview;
import com.sun.tools.javac.code.Source;
import com.sun.tools.javac.file.JavacFileManager;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.tree.EndPosTable;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import com.sun.tools.javac.util.Position;
import java.nio.CharBuffer;
import java.util.Set;
import nbjavac.StringWrapper;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class JavaTokenizer extends UnicodeReader {
    private static final int NOT_FOUND = -1;
    private static final boolean scannerDebug = false;
    protected final boolean enableLineDocComments;
    protected int errPos;
    protected final ScannerFactory fac;
    protected boolean hasEscapeSequences;
    protected boolean isTextBlock;
    protected final Log log;
    protected Name name;
    private final Names names;
    private final Preview preview;
    protected int radix;
    protected final StringBuilder sb;
    private final Source source;
    protected Tokens.TokenKind tk;
    private final Tokens tokens;

    public static class BasicComment extends UnicodeReader.PositionTrackingReader implements Tokens.Comment {
        final Tokens.Comment.CommentStyle cs;
        protected boolean deprecatedFlag;
        JCDiagnostic.DiagnosticPosition pos;
        protected boolean scanned;

        public BasicComment(Tokens.Comment.CommentStyle commentStyle, UnicodeReader unicodeReader, int i, final int i2) {
            super(unicodeReader, i, i2);
            this.deprecatedFlag = false;
            this.scanned = false;
            this.cs = commentStyle;
            this.pos = new JCDiagnostic.SimpleDiagnosticPosition(this, i) { // from class: com.sun.tools.javac.parser.JavaTokenizer.BasicComment.1
                final /* synthetic */ BasicComment this$0;

                {
                    this.this$0 = this;
                }

                @Override // com.sun.tools.javac.util.JCDiagnostic.SimpleDiagnosticPosition, com.sun.tools.javac.util.JCDiagnostic.DiagnosticPosition
                public int getEndPosition(EndPosTable endPosTable) {
                    return i2;
                }
            };
        }

        public int getJavadocLineCommentIndent() {
            UnicodeReader unicodeReaderLineReader = lineReader(position(), position() + length());
            int iMin = Integer.MAX_VALUE;
            while (unicodeReaderLineReader.isAvailable()) {
                UnicodeReader unicodeReaderLineReader2 = unicodeReaderLineReader.lineReader();
                unicodeReaderLineReader2.skipWhitespace();
                unicodeReaderLineReader2.accept("///");
                int iPosition = unicodeReaderLineReader2.position();
                unicodeReaderLineReader2.skipWhitespace();
                if (unicodeReaderLineReader2.isAvailable()) {
                    iMin = Integer.min(iMin, unicodeReaderLineReader2.position() - iPosition);
                }
            }
            if (iMin == Integer.MAX_VALUE) {
                return 0;
            }
            return iMin;
        }

        @Override // com.sun.tools.javac.parser.Tokens.Comment
        public JCDiagnostic.DiagnosticPosition getPos() {
            return this.pos;
        }

        @Override // com.sun.tools.javac.parser.Tokens.Comment
        public int getSourcePos(int i) {
            return -1;
        }

        @Override // com.sun.tools.javac.parser.Tokens.Comment
        public Tokens.Comment.CommentStyle getStyle() {
            return this.cs;
        }

        @Override // com.sun.tools.javac.parser.Tokens.Comment
        public String getText() {
            return null;
        }

        @Override // com.sun.tools.javac.parser.Tokens.Comment
        public boolean isDeprecated() {
            if (!this.scanned) {
                scanDocComment();
            }
            return this.deprecatedFlag;
        }

        public void putLine(UnicodeReader unicodeReader) {
        }

        public void scanDocComment() {
            Tokens.Comment.CommentStyle commentStyle;
            if (this.scanned) {
                return;
            }
            int javadocLineCommentIndent = 0;
            this.deprecatedFlag = false;
            this.scanned = true;
            int iPosition = position();
            if (accept("/**")) {
                commentStyle = Tokens.Comment.CommentStyle.JAVADOC_BLOCK;
                if (skip('*') != 0 && is('/')) {
                    return;
                }
                skipWhitespace();
                if (isEOLN()) {
                    accept(CharInfo.S_CARRIAGERETURN);
                    accept('\n');
                }
            } else {
                if (!accept("///")) {
                    return;
                }
                Tokens.Comment.CommentStyle commentStyle2 = Tokens.Comment.CommentStyle.JAVADOC_LINE;
                reset(iPosition);
                commentStyle = commentStyle2;
                javadocLineCommentIndent = getJavadocLineCommentIndent();
            }
            while (isAvailable()) {
                UnicodeReader unicodeReaderLineReader = lineReader();
                UnicodeReader unicodeReaderTrimJavadocLineComment = commentStyle == Tokens.Comment.CommentStyle.JAVADOC_LINE ? trimJavadocLineComment(unicodeReaderLineReader, javadocLineCommentIndent) : trimJavadocComment(unicodeReaderLineReader);
                if (this.cs == Tokens.Comment.CommentStyle.JAVADOC_BLOCK) {
                    int iPosition2 = unicodeReaderTrimJavadocLineComment.position();
                    unicodeReaderTrimJavadocLineComment.skipWhitespace();
                    if (unicodeReaderTrimJavadocLineComment.accept("@deprecated") && (!unicodeReaderTrimJavadocLineComment.isAvailable() || unicodeReaderTrimJavadocLineComment.isWhitespace() || unicodeReaderTrimJavadocLineComment.isEOLN() || unicodeReaderTrimJavadocLineComment.get() == 26)) {
                        this.deprecatedFlag = true;
                    }
                    unicodeReaderTrimJavadocLineComment.reset(iPosition2);
                }
                putLine(unicodeReaderTrimJavadocLineComment);
            }
        }

        @Override // com.sun.tools.javac.parser.Tokens.Comment
        public Tokens.Comment stripIndent() {
            return this;
        }

        public UnicodeReader trimEndOfComment(UnicodeReader unicodeReader) {
            int iPosition = unicodeReader.position();
            boolean z = true;
            while (unicodeReader.isAvailable()) {
                int iPosition2 = unicodeReader.position();
                if (unicodeReader.skip('*') != 0 && unicodeReader.is('/')) {
                    if (z) {
                        iPosition = iPosition2;
                    }
                    return unicodeReader.lineReader(iPosition, iPosition2);
                }
                z = z && unicodeReader.isWhitespace();
                unicodeReader.next();
            }
            unicodeReader.reset(iPosition);
            return unicodeReader;
        }

        public UnicodeReader trimJavadocComment(UnicodeReader unicodeReader) {
            UnicodeReader unicodeReaderTrimEndOfComment = trimEndOfComment(unicodeReader);
            int iPosition = unicodeReaderTrimEndOfComment.position();
            unicodeReaderTrimEndOfComment.skipWhitespace();
            if (unicodeReaderTrimEndOfComment.isAvailable() && unicodeReaderTrimEndOfComment.skip('*') == 0) {
                unicodeReaderTrimEndOfComment.reset(iPosition);
            }
            return unicodeReaderTrimEndOfComment;
        }

        public UnicodeReader trimJavadocLineComment(UnicodeReader unicodeReader, int i) {
            unicodeReader.skipWhitespace();
            unicodeReader.accept("///");
            for (int i2 = 0; unicodeReader.isAvailable() && i2 < i; i2++) {
                unicodeReader.next();
            }
            return unicodeReader;
        }
    }

    public JavaTokenizer(ScannerFactory scannerFactory, char[] cArr, int i) {
        super(scannerFactory, cArr, i);
        this.errPos = -1;
        this.fac = scannerFactory;
        this.log = scannerFactory.log;
        this.names = scannerFactory.names;
        this.tokens = scannerFactory.tokens;
        this.source = scannerFactory.source;
        this.preview = scannerFactory.preview;
        this.enableLineDocComments = scannerFactory.enableLineDocComments;
        this.sb = new StringBuilder(256);
    }

    private void checkIdent() {
        Name nameFromString = this.names.fromString(this.sb.toString());
        this.name = nameFromString;
        this.tk = this.tokens.lookupKind(nameFromString);
    }

    private boolean isSpecial(char c) {
        if (c == '!' || c == '-' || c == ':' || c == '^' || c == '|' || c == '~' || c == '%' || c == '&' || c == '*' || c == '+') {
            return true;
        }
        switch (c) {
            case '<':
            case '=':
            case '>':
            case '?':
            case '@':
                return true;
            default:
                return false;
        }
    }

    private void scanDigits(int i, int i2) {
        int iPosition;
        int iPosition2 = is('_') ? position() : -1;
        while (true) {
            if (is('_')) {
                iPosition = position();
            } else {
                put();
                iPosition = -1;
            }
            next();
            if (digit(i, i2) < 0 && !is('_')) {
                break;
            }
        }
        if (iPosition2 != -1) {
            lexError(iPosition2, CompilerProperties.Errors.IllegalUnderscore);
        } else if (iPosition != -1) {
            lexError(iPosition, CompilerProperties.Errors.IllegalUnderscore);
        }
    }

    private void scanFraction(int i) {
        skipIllegalUnderscores();
        if (digit(i, 10) >= 0) {
            scanDigits(i, 10);
        }
        int length = this.sb.length();
        if (acceptOneOfThenPut('e', 'E')) {
            skipIllegalUnderscores();
            acceptOneOfThenPut('+', LocaleUtility.IETF_SEPARATOR);
            skipIllegalUnderscores();
            if (digit(i, 10) >= 0) {
                scanDigits(i, 10);
            } else {
                lexError(i, CompilerProperties.Errors.MalformedFpLit);
                this.sb.setLength(length);
            }
        }
    }

    private void scanFractionAndSuffix(int i) {
        this.radix = 10;
        scanFraction(i);
        if (acceptOneOfThenPut('f', 'F')) {
            this.tk = Tokens.TokenKind.FLOATLITERAL;
        } else {
            acceptOneOfThenPut('d', 'D');
            this.tk = Tokens.TokenKind.DOUBLELITERAL;
        }
    }

    private void scanHexExponentAndSuffix(int i) {
        if (acceptOneOfThenPut('p', 'P')) {
            skipIllegalUnderscores();
            acceptOneOfThenPut('+', LocaleUtility.IETF_SEPARATOR);
            skipIllegalUnderscores();
            if (digit(i, 10) >= 0) {
                scanDigits(i, 10);
            } else {
                lexError(i, CompilerProperties.Errors.MalformedFpLit);
            }
        } else {
            lexError(i, CompilerProperties.Errors.MalformedFpLit);
        }
        if (acceptOneOfThenPut('f', 'F')) {
            this.tk = Tokens.TokenKind.FLOATLITERAL;
            this.radix = 16;
        } else {
            acceptOneOfThenPut('d', 'D');
            this.tk = Tokens.TokenKind.DOUBLELITERAL;
            this.radix = 16;
        }
    }

    private void scanHexFractionAndSuffix(int i, boolean z) {
        this.radix = 16;
        Assert.check(is('.'));
        putThenNext();
        skipIllegalUnderscores();
        if (digit(i, 16) >= 0) {
            scanDigits(i, 16);
            z = true;
        }
        if (z) {
            scanHexExponentAndSuffix(i);
        } else {
            lexError(i, CompilerProperties.Errors.InvalidHexNumber);
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:9:0x0013. Please report as an issue. */
    private void scanIdent() {
        boolean zIsJavaIdentifierPart;
        putThenNext();
        while (true) {
            char c = get();
            if (c != '$' && c != '_') {
                if (c != 127) {
                    switch (c) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case '\b':
                            break;
                    }
                    switch (c) {
                        case 14:
                        case 15:
                        case 16:
                        case 17:
                        case 18:
                        case 19:
                        case 20:
                        case 21:
                        case 22:
                        case 23:
                        case 24:
                        case 25:
                        case 27:
                            break;
                        case 26:
                            if (!isAvailable()) {
                                checkIdent();
                            } else {
                                next();
                            }
                            break;
                        default:
                            switch (c) {
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
                                    break;
                                default:
                                    switch (c) {
                                        case 'A':
                                        case 'B':
                                        case 'C':
                                        case 'D':
                                        case 'E':
                                        case 'F':
                                        case 'G':
                                        case 'H':
                                        case 'I':
                                        case 'J':
                                        case 'K':
                                        case 'L':
                                        case 'M':
                                        case 'N':
                                        case 'O':
                                        case 'P':
                                        case 'Q':
                                        case 'R':
                                        case 'S':
                                        case 'T':
                                        case 'U':
                                        case 'V':
                                        case 'W':
                                        case 'X':
                                        case 'Y':
                                        case 'Z':
                                            break;
                                        default:
                                            switch (c) {
                                                case 'a':
                                                case 'b':
                                                case 'c':
                                                case 'd':
                                                case 'e':
                                                case 'f':
                                                case 'g':
                                                case 'h':
                                                case 'i':
                                                case 'j':
                                                case 'k':
                                                case 'l':
                                                case 'm':
                                                case 'n':
                                                case 'o':
                                                case 'p':
                                                case 'q':
                                                case 'r':
                                                case 's':
                                                case 't':
                                                case 'u':
                                                case 'v':
                                                case 'w':
                                                case 'x':
                                                case 'y':
                                                case 'z':
                                                    break;
                                                default:
                                                    if (isASCII()) {
                                                        zIsJavaIdentifierPart = false;
                                                    } else if (Character.isIdentifierIgnorable(get())) {
                                                        next();
                                                    } else {
                                                        zIsJavaIdentifierPart = isSurrogate() ? Character.isJavaIdentifierPart(getCodepoint()) : Character.isJavaIdentifierPart(get());
                                                    }
                                                    if (!zIsJavaIdentifierPart) {
                                                        checkIdent();
                                                    }
                                                    break;
                                            }
                                            break;
                                    }
                                    break;
                            }
                            break;
                    }
                    return;
                }
                next();
            }
            putThenNext();
        }
    }

    private void scanLitChar(int i, boolean z) {
        position();
        if (!acceptThenPut('\\')) {
            if (!z && !Character.isBmpCodePoint(getCodepoint())) {
                lexError(i, CompilerProperties.Errors.IllegalCharLiteralMultipleSurrogates);
            }
            putThenNext();
            return;
        }
        this.hasEscapeSequences = true;
        char c = get();
        if (c == '\n' || c == '\r') {
            if (!this.isTextBlock) {
                lexError(position(), CompilerProperties.Errors.IllegalEscChar);
                return;
            } else {
                skipLineTerminator();
                put('\n');
                return;
            }
        }
        if (c != '\"' && c != '\'' && c != '\\' && c != 'b' && c != 'f' && c != 'n') {
            switch (c) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                    char c2 = get();
                    putThenNext();
                    if (inRange('0', '7')) {
                        putThenNext();
                        if (c2 > '3' || !inRange('0', '7')) {
                            return;
                        }
                        putThenNext();
                        return;
                    }
                    return;
                default:
                    switch (c) {
                        case 'r':
                        case 't':
                            break;
                        case 's':
                            checkSourceLevel(position(), Source.Feature.TEXT_BLOCKS);
                            putThenNext();
                            break;
                        default:
                            lexError(position(), CompilerProperties.Errors.IllegalEscChar);
                            break;
                    }
                    return;
            }
        }
        putThenNext();
    }

    private void scanNumber(int i, int i2) {
        JavaTokenizer javaTokenizer;
        this.radix = i2;
        boolean z = i2 == 8 || i2 == 10;
        int iMax = Math.max(10, i2);
        int iDigit = digit(i, iMax);
        boolean z2 = iDigit >= 0;
        boolean z3 = iDigit >= 0 && iDigit < iMax;
        if (z2) {
            scanDigits(i, iMax);
        }
        if (i2 == 16 && is('.')) {
            scanHexFractionAndSuffix(i, z2);
            return;
        }
        if (z2 && i2 == 16 && isOneOf('p', 'P')) {
            scanHexExponentAndSuffix(i);
            return;
        }
        if (z && is('.')) {
            putThenNext();
            scanFractionAndSuffix(i);
            return;
        }
        if (z) {
            javaTokenizer = this;
            if (javaTokenizer.isOneOf('e', 'E', 'f', 'F', 'd', 'D')) {
                javaTokenizer.scanFractionAndSuffix(i);
                return;
            }
        } else {
            javaTokenizer = this;
        }
        if (!z3) {
            if (i2 == 2) {
                javaTokenizer.lexError(i, CompilerProperties.Errors.InvalidBinaryNumber);
            } else if (i2 == 16) {
                javaTokenizer.lexError(i, CompilerProperties.Errors.InvalidHexNumber);
            }
        }
        if (javaTokenizer.acceptOneOf('l', 'L')) {
            javaTokenizer.tk = Tokens.TokenKind.LONGLITERAL;
        } else {
            javaTokenizer.tk = Tokens.TokenKind.INTLITERAL;
        }
    }

    private void scanOperator() {
        do {
            put();
            Tokens.TokenKind tokenKindLookupKind = this.tokens.lookupKind(this.sb.toString());
            if (tokenKindLookupKind == Tokens.TokenKind.IDENTIFIER) {
                StringBuilder sb = this.sb;
                sb.setLength(sb.length() - 1);
                return;
            } else {
                this.tk = tokenKindLookupKind;
                next();
            }
        } while (isSpecial(get()));
    }

    private void scanString(int i) {
        int iPosition;
        this.tk = Tokens.TokenKind.STRINGLITERAL;
        boolean zAccept = accept("\"\"\"");
        this.isTextBlock = zAccept;
        if (zAccept) {
            checkSourceLevel(i, Source.Feature.TEXT_BLOCKS);
            skipWhitespace();
            if (!isEOLN()) {
                lexError(position(), CompilerProperties.Errors.IllegalTextBlockOpen);
                return;
            }
            skipLineTerminator();
            iPosition = -1;
            while (isAvailable()) {
                if (accept("\"\"\"")) {
                    return;
                }
                if (isEOLN()) {
                    skipLineTerminator();
                    put('\n');
                    if (iPosition == -1) {
                        iPosition = position();
                    }
                } else {
                    scanLitChar(i, true);
                }
            }
        } else {
            next();
            while (isAvailable()) {
                if (accept('\"')) {
                    return;
                }
                if (isEOLN()) {
                    break;
                } else {
                    scanLitChar(i, true);
                }
            }
            iPosition = -1;
        }
        lexError(i, this.isTextBlock ? CompilerProperties.Errors.UnclosedTextBlock : CompilerProperties.Errors.UnclosedStrLit);
        if (iPosition != -1) {
            reset(iPosition);
        }
    }

    private void skipIllegalUnderscores() {
        if (is('_')) {
            lexError(position(), CompilerProperties.Errors.IllegalUnderscore);
            skip('_');
        }
    }

    private void skipLineTerminator() {
        int iPosition = position();
        accept(CharInfo.S_CARRIAGERETURN);
        accept('\n');
        processLineTerminator(iPosition, position());
    }

    public boolean acceptOneOfThenPut(char c, char c2) {
        if (!isOneOf(c, c2)) {
            return false;
        }
        put(get());
        next();
        return true;
    }

    public boolean acceptThenPut(char c) {
        if (!is(c)) {
            return false;
        }
        put(get());
        next();
        return true;
    }

    public List<Tokens.Comment> appendComment(List<Tokens.Comment> list, Tokens.Comment comment) {
        return list == null ? List.of(comment) : list.prepend(comment);
    }

    public void checkSourceLevel(int i, Source.Feature feature) {
        if (this.preview.isPreview(feature) && !this.preview.isEnabled()) {
            lexError(i, this.preview.disabledError(feature));
        } else if (!feature.allowedInSource(this.source)) {
            lexError(i, feature.error(this.source.name));
        } else if (this.preview.isPreview(feature)) {
            this.preview.warnPreview(i, feature);
        }
    }

    public int errPos() {
        return this.errPos;
    }

    public Position.LineMap getLineMap() {
        return Position.makeLineMap(getRawCharacters(), length(), false);
    }

    public void lexError(int i, JCDiagnostic.Error error) {
        this.log.error(i, error);
        if (!error.hasFlag(JCDiagnostic.DiagnosticFlag.SOURCE_LEVEL)) {
            this.tk = Tokens.TokenKind.ERROR;
        }
        this.errPos = i;
    }

    public Tokens.Comment processComment(int i, int i2, Tokens.Comment.CommentStyle commentStyle) {
        return new BasicComment(commentStyle, this, i, i2);
    }

    public void processLineTerminator(int i, int i2) {
    }

    public void processWhiteSpace(int i, int i2) {
    }

    public void put() {
        if (isSurrogate()) {
            putCodePoint(getCodepoint());
        } else {
            put(get());
        }
    }

    public void putCodePoint(int i) {
        this.sb.appendCodePoint(i);
    }

    public char putThenNext() {
        put();
        return next();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:103:0x01cd A[Catch: all -> 0x0065, LOOP:1: B:103:0x01cd->B:244:0x01cd, LOOP_START, TryCatch #0 {all -> 0x0065, blocks: (B:3:0x0012, B:27:0x004a, B:28:0x004d, B:29:0x0050, B:30:0x0053, B:31:0x0056, B:33:0x0060, B:158:0x02e5, B:160:0x02f1, B:163:0x02fa, B:165:0x02fe, B:168:0x0309, B:170:0x0313, B:172:0x031f, B:173:0x0326, B:175:0x032e, B:176:0x0335, B:177:0x0339, B:179:0x033d, B:181:0x0342, B:183:0x034a, B:186:0x0353, B:36:0x0068, B:45:0x008a, B:46:0x008f, B:48:0x0095, B:49:0x0099, B:51:0x00a1, B:54:0x00a8, B:56:0x00ae, B:63:0x00ea, B:57:0x00cd, B:61:0x00d7, B:62:0x00dc, B:64:0x00f5, B:39:0x006f, B:41:0x0075, B:43:0x007f, B:66:0x0100, B:67:0x0108, B:68:0x0110, B:69:0x0114, B:71:0x0121, B:72:0x012a, B:74:0x0134, B:75:0x013c, B:77:0x0147, B:79:0x0154, B:80:0x0159, B:81:0x015f, B:83:0x016a, B:85:0x016e, B:87:0x0174, B:89:0x0189, B:90:0x0195, B:92:0x019e, B:93:0x01ae, B:95:0x01b6, B:97:0x01bc, B:103:0x01cd, B:105:0x01d3, B:107:0x01d9, B:110:0x01e0, B:111:0x01e4, B:113:0x01ea, B:114:0x01f8, B:101:0x01c8, B:115:0x01ff, B:117:0x0207, B:118:0x020d, B:119:0x0213, B:121:0x0219, B:122:0x0222, B:124:0x0231, B:125:0x0238, B:127:0x023e, B:128:0x0246, B:129:0x024c, B:130:0x0255, B:131:0x025e, B:133:0x0269, B:134:0x0270, B:136:0x0276, B:137:0x027b, B:139:0x0284, B:141:0x028a, B:143:0x0290, B:145:0x0298, B:146:0x029b, B:147:0x029f, B:148:0x02a4, B:150:0x02aa, B:151:0x02b0, B:152:0x02b7, B:153:0x02c0, B:154:0x02c9, B:155:0x02d2, B:156:0x02db, B:157:0x02e0, B:189:0x035f, B:190:0x036e, B:191:0x037a), top: B:197:0x0012 }] */
    /* JADX WARN: Code duplicated, block: B:105:0x01d3 A[Catch: all -> 0x0065, TryCatch #0 {all -> 0x0065, blocks: (B:3:0x0012, B:27:0x004a, B:28:0x004d, B:29:0x0050, B:30:0x0053, B:31:0x0056, B:33:0x0060, B:158:0x02e5, B:160:0x02f1, B:163:0x02fa, B:165:0x02fe, B:168:0x0309, B:170:0x0313, B:172:0x031f, B:173:0x0326, B:175:0x032e, B:176:0x0335, B:177:0x0339, B:179:0x033d, B:181:0x0342, B:183:0x034a, B:186:0x0353, B:36:0x0068, B:45:0x008a, B:46:0x008f, B:48:0x0095, B:49:0x0099, B:51:0x00a1, B:54:0x00a8, B:56:0x00ae, B:63:0x00ea, B:57:0x00cd, B:61:0x00d7, B:62:0x00dc, B:64:0x00f5, B:39:0x006f, B:41:0x0075, B:43:0x007f, B:66:0x0100, B:67:0x0108, B:68:0x0110, B:69:0x0114, B:71:0x0121, B:72:0x012a, B:74:0x0134, B:75:0x013c, B:77:0x0147, B:79:0x0154, B:80:0x0159, B:81:0x015f, B:83:0x016a, B:85:0x016e, B:87:0x0174, B:89:0x0189, B:90:0x0195, B:92:0x019e, B:93:0x01ae, B:95:0x01b6, B:97:0x01bc, B:103:0x01cd, B:105:0x01d3, B:107:0x01d9, B:110:0x01e0, B:111:0x01e4, B:113:0x01ea, B:114:0x01f8, B:101:0x01c8, B:115:0x01ff, B:117:0x0207, B:118:0x020d, B:119:0x0213, B:121:0x0219, B:122:0x0222, B:124:0x0231, B:125:0x0238, B:127:0x023e, B:128:0x0246, B:129:0x024c, B:130:0x0255, B:131:0x025e, B:133:0x0269, B:134:0x0270, B:136:0x0276, B:137:0x027b, B:139:0x0284, B:141:0x028a, B:143:0x0290, B:145:0x0298, B:146:0x029b, B:147:0x029f, B:148:0x02a4, B:150:0x02aa, B:151:0x02b0, B:152:0x02b7, B:153:0x02c0, B:154:0x02c9, B:155:0x02d2, B:156:0x02db, B:157:0x02e0, B:189:0x035f, B:190:0x036e, B:191:0x037a), top: B:197:0x0012 }] */
    /* JADX WARN: Code duplicated, block: B:113:0x01ea A[Catch: all -> 0x0065, TryCatch #0 {all -> 0x0065, blocks: (B:3:0x0012, B:27:0x004a, B:28:0x004d, B:29:0x0050, B:30:0x0053, B:31:0x0056, B:33:0x0060, B:158:0x02e5, B:160:0x02f1, B:163:0x02fa, B:165:0x02fe, B:168:0x0309, B:170:0x0313, B:172:0x031f, B:173:0x0326, B:175:0x032e, B:176:0x0335, B:177:0x0339, B:179:0x033d, B:181:0x0342, B:183:0x034a, B:186:0x0353, B:36:0x0068, B:45:0x008a, B:46:0x008f, B:48:0x0095, B:49:0x0099, B:51:0x00a1, B:54:0x00a8, B:56:0x00ae, B:63:0x00ea, B:57:0x00cd, B:61:0x00d7, B:62:0x00dc, B:64:0x00f5, B:39:0x006f, B:41:0x0075, B:43:0x007f, B:66:0x0100, B:67:0x0108, B:68:0x0110, B:69:0x0114, B:71:0x0121, B:72:0x012a, B:74:0x0134, B:75:0x013c, B:77:0x0147, B:79:0x0154, B:80:0x0159, B:81:0x015f, B:83:0x016a, B:85:0x016e, B:87:0x0174, B:89:0x0189, B:90:0x0195, B:92:0x019e, B:93:0x01ae, B:95:0x01b6, B:97:0x01bc, B:103:0x01cd, B:105:0x01d3, B:107:0x01d9, B:110:0x01e0, B:111:0x01e4, B:113:0x01ea, B:114:0x01f8, B:101:0x01c8, B:115:0x01ff, B:117:0x0207, B:118:0x020d, B:119:0x0213, B:121:0x0219, B:122:0x0222, B:124:0x0231, B:125:0x0238, B:127:0x023e, B:128:0x0246, B:129:0x024c, B:130:0x0255, B:131:0x025e, B:133:0x0269, B:134:0x0270, B:136:0x0276, B:137:0x027b, B:139:0x0284, B:141:0x028a, B:143:0x0290, B:145:0x0298, B:146:0x029b, B:147:0x029f, B:148:0x02a4, B:150:0x02aa, B:151:0x02b0, B:152:0x02b7, B:153:0x02c0, B:154:0x02c9, B:155:0x02d2, B:156:0x02db, B:157:0x02e0, B:189:0x035f, B:190:0x036e, B:191:0x037a), top: B:197:0x0012 }] */
    /* JADX WARN: Code duplicated, block: B:228:0x01f8 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:241:0x01e0 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:242:0x01df A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:243:0x01d9 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:246:0x01cd A[SYNTHETIC] */
    public Tokens.Token readToken() {
        int iPosition;
        String strValueOf;
        int iPosition2;
        Tokens.Comment.CommentStyle commentStyle;
        boolean z;
        boolean zIsJavaIdentifierStart = false;
        this.sb.setLength(0);
        this.name = null;
        this.radix = 0;
        this.isTextBlock = false;
        this.hasEscapeSequences = false;
        List<Tokens.Comment> listAppendComment = null;
        while (true) {
            try {
                iPosition = position();
                char c = get();
                if (c != '\t') {
                    if (c == '\n') {
                        next();
                        processLineTerminator(iPosition, position());
                    } else if (c != '\f') {
                        if (c == '\r') {
                            next();
                            accept('\n');
                            processLineTerminator(iPosition, position());
                        } else if (c != ' ') {
                            if (c != '\"') {
                                if (c != '$') {
                                    if (c == ',') {
                                        next();
                                        this.tk = Tokens.TokenKind.COMMA;
                                    } else if (c == ';') {
                                        next();
                                        this.tk = Tokens.TokenKind.SEMI;
                                    } else if (c == ']') {
                                        next();
                                        this.tk = Tokens.TokenKind.RBRACKET;
                                    } else if (c != '_') {
                                        if (c != '}') {
                                            switch (c) {
                                                case '\'':
                                                    next();
                                                    if (!accept('\'')) {
                                                        if (isEOLN()) {
                                                            lexError(iPosition, CompilerProperties.Errors.IllegalLineEndInCharLit);
                                                        }
                                                        scanLitChar(iPosition, false);
                                                        if (this.tk == Tokens.TokenKind.ERROR) {
                                                            while (isAvailable() && !is('\'')) {
                                                                if (is('\\')) {
                                                                    next();
                                                                }
                                                                next();
                                                            }
                                                            accept('\'');
                                                        } else if (!accept('\'')) {
                                                            lexError(iPosition, CompilerProperties.Errors.UnclosedCharLit);
                                                        } else {
                                                            this.tk = Tokens.TokenKind.CHARLITERAL;
                                                        }
                                                    } else {
                                                        lexError(iPosition, CompilerProperties.Errors.EmptyCharLit);
                                                    }
                                                    break;
                                                case '(':
                                                    next();
                                                    this.tk = Tokens.TokenKind.LPAREN;
                                                    break;
                                                case ')':
                                                    next();
                                                    this.tk = Tokens.TokenKind.RPAREN;
                                                    break;
                                                default:
                                                    switch (c) {
                                                        case '.':
                                                            if (!accept("...")) {
                                                                next();
                                                                int iPosition3 = position();
                                                                if (accept('.')) {
                                                                    lexError(iPosition3, CompilerProperties.Errors.IllegalDot);
                                                                } else if (digit(iPosition, 10) < 0) {
                                                                    this.tk = Tokens.TokenKind.DOT;
                                                                } else {
                                                                    put('.');
                                                                    scanFractionAndSuffix(iPosition);
                                                                }
                                                            } else {
                                                                put("...");
                                                                this.tk = Tokens.TokenKind.ELLIPSIS;
                                                            }
                                                            break;
                                                        case '/':
                                                            next();
                                                            if (!accept('/')) {
                                                                if (accept('*')) {
                                                                    if (accept('*')) {
                                                                        commentStyle = Tokens.Comment.CommentStyle.JAVADOC_BLOCK;
                                                                        z = is('/');
                                                                        if (!z) {
                                                                            while (isAvailable()) {
                                                                                if (accept('*')) {
                                                                                    next();
                                                                                } else if (is('/')) {
                                                                                }
                                                                            }
                                                                        }
                                                                        if (accept('/')) {
                                                                            lexError(iPosition, CompilerProperties.Errors.UnclosedComment);
                                                                        } else {
                                                                            listAppendComment = appendComment(listAppendComment, processComment(iPosition, position(), commentStyle));
                                                                        }
                                                                    } else {
                                                                        commentStyle = Tokens.Comment.CommentStyle.BLOCK;
                                                                    }
                                                                    if (!z) {
                                                                        while (isAvailable()) {
                                                                            if (accept('*')) {
                                                                                next();
                                                                            } else if (is('/')) {
                                                                            }
                                                                        }
                                                                    }
                                                                    if (accept('/')) {
                                                                        lexError(iPosition, CompilerProperties.Errors.UnclosedComment);
                                                                    } else {
                                                                        listAppendComment = appendComment(listAppendComment, processComment(iPosition, position(), commentStyle));
                                                                    }
                                                                    break;
                                                                } else if (!accept('=')) {
                                                                    this.tk = Tokens.TokenKind.SLASH;
                                                                } else {
                                                                    this.tk = Tokens.TokenKind.SLASHEQ;
                                                                }
                                                            } else if (this.enableLineDocComments && accept('/')) {
                                                                do {
                                                                    skipToEOLN();
                                                                    iPosition2 = position();
                                                                    skipLineTerminator();
                                                                    skipWhitespace();
                                                                } while (accept("///"));
                                                                listAppendComment = appendComment(listAppendComment, processComment(iPosition, iPosition2, Tokens.Comment.CommentStyle.JAVADOC_LINE));
                                                            } else {
                                                                skipToEOLN();
                                                                if (isAvailable()) {
                                                                    listAppendComment = appendComment(listAppendComment, processComment(iPosition, position(), Tokens.Comment.CommentStyle.LINE));
                                                                }
                                                            }
                                                            break;
                                                        case '0':
                                                            next();
                                                            if (acceptOneOf('x', 'X')) {
                                                                skipIllegalUnderscores();
                                                                scanNumber(iPosition, 16);
                                                            } else if (!acceptOneOf('b', 'B')) {
                                                                put('0');
                                                                if (is('_')) {
                                                                    int iPosition4 = position();
                                                                    skip('_');
                                                                    if (digit(iPosition, 10) < 0) {
                                                                        lexError(iPosition4, CompilerProperties.Errors.IllegalUnderscore);
                                                                    }
                                                                }
                                                                scanNumber(iPosition, 8);
                                                            } else {
                                                                skipIllegalUnderscores();
                                                                scanNumber(iPosition, 2);
                                                            }
                                                            break;
                                                        case '1':
                                                        case '2':
                                                        case '3':
                                                        case '4':
                                                        case '5':
                                                        case '6':
                                                        case '7':
                                                        case '8':
                                                        case '9':
                                                            scanNumber(iPosition, 10);
                                                            break;
                                                        default:
                                                            switch (c) {
                                                                case 'A':
                                                                case 'B':
                                                                case 'C':
                                                                case 'D':
                                                                case 'E':
                                                                case 'F':
                                                                case 'G':
                                                                case 'H':
                                                                case 'I':
                                                                case 'J':
                                                                case 'K':
                                                                case 'L':
                                                                case 'M':
                                                                case 'N':
                                                                case 'O':
                                                                case 'P':
                                                                case 'Q':
                                                                case 'R':
                                                                case 'S':
                                                                case 'T':
                                                                case 'U':
                                                                case 'V':
                                                                case 'W':
                                                                case 'X':
                                                                case 'Y':
                                                                case 'Z':
                                                                    break;
                                                                case '[':
                                                                    next();
                                                                    this.tk = Tokens.TokenKind.LBRACKET;
                                                                    break;
                                                                default:
                                                                    switch (c) {
                                                                        case 'a':
                                                                        case 'b':
                                                                        case 'c':
                                                                        case 'd':
                                                                        case 'e':
                                                                        case 'f':
                                                                        case 'g':
                                                                        case 'h':
                                                                        case 'i':
                                                                        case 'j':
                                                                        case 'k':
                                                                        case 'l':
                                                                        case 'm':
                                                                        case 'n':
                                                                        case 'o':
                                                                        case 'p':
                                                                        case 'q':
                                                                        case 'r':
                                                                        case 's':
                                                                        case 't':
                                                                        case 'u':
                                                                        case 'v':
                                                                        case 'w':
                                                                        case 'x':
                                                                        case 'y':
                                                                        case 'z':
                                                                            break;
                                                                        case '{':
                                                                            next();
                                                                            this.tk = Tokens.TokenKind.LBRACE;
                                                                            break;
                                                                        default:
                                                                            if (!isSpecial(get())) {
                                                                                if (!isASCII()) {
                                                                                    zIsJavaIdentifierStart = isSurrogate() ? Character.isJavaIdentifierStart(getCodepoint()) : Character.isJavaIdentifierStart(get());
                                                                                }
                                                                                if (zIsJavaIdentifierStart) {
                                                                                    scanIdent();
                                                                                } else if (digit(iPosition, 10) >= 0) {
                                                                                    scanNumber(iPosition, 10);
                                                                                } else if (!is((char) 26) && isAvailable()) {
                                                                                    if (isSurrogate()) {
                                                                                        int codepoint = getCodepoint();
                                                                                        strValueOf = String.format("\\u%04x\\u%04x", Integer.valueOf(Character.highSurrogate(codepoint)), Integer.valueOf(Character.lowSurrogate(codepoint)));
                                                                                    } else {
                                                                                        char c2 = get();
                                                                                        strValueOf = (' ' >= c2 || c2 >= 127) ? String.format("\\u%04x", Integer.valueOf(c2)) : String.valueOf(c2);
                                                                                    }
                                                                                    lexError(iPosition, CompilerProperties.Errors.IllegalChar(strValueOf));
                                                                                    next();
                                                                                } else {
                                                                                    this.tk = Tokens.TokenKind.EOF;
                                                                                    iPosition = position();
                                                                                }
                                                                            } else {
                                                                                scanOperator();
                                                                            }
                                                                            break;
                                                                    }
                                                                    break;
                                                            }
                                                            break;
                                                    }
                                                    break;
                                            }
                                        } else {
                                            next();
                                            this.tk = Tokens.TokenKind.RBRACE;
                                        }
                                    }
                                }
                                scanIdent();
                            } else {
                                scanString(iPosition);
                            }
                        }
                    }
                }
                skipWhitespace();
                processWhiteSpace(iPosition, position());
            } catch (Throwable th) {
                position();
                throw th;
            }
        }
        int i = iPosition;
        int iPosition5 = position();
        Tokens.TokenKind tokenKind = this.tk;
        Tokens.Token.Tag tag = tokenKind.tag;
        if (tag == Tokens.Token.Tag.DEFAULT) {
            Tokens.Token token = new Tokens.Token(tokenKind, i, iPosition5, listAppendComment);
            position();
            return token;
        }
        if (tag == Tokens.Token.Tag.NAMED) {
            Tokens.NamedToken namedToken = new Tokens.NamedToken(tokenKind, i, iPosition5, this.name, listAppendComment);
            position();
            return namedToken;
        }
        String string = this.sb.toString();
        if (this.isTextBlock) {
            Set<TextBlockSupport.WhitespaceChecks> setCheckWhitespace = TextBlockSupport.checkWhitespace(string);
            if (setCheckWhitespace.contains(TextBlockSupport.WhitespaceChecks.INCONSISTENT)) {
                this.log.warning(i, CompilerProperties.LintWarnings.InconsistentWhiteSpaceIndentation);
            }
            if (setCheckWhitespace.contains(TextBlockSupport.WhitespaceChecks.TRAILING)) {
                this.log.warning(i, CompilerProperties.LintWarnings.TrailingWhiteSpaceWillBeRemoved);
            }
            try {
                string = StringWrapper.stripIndent(string);
            } catch (Exception unused) {
            }
        }
        if (this.hasEscapeSequences) {
            try {
                string = StringWrapper.translateEscapes(string);
            } catch (Exception unused2) {
            }
        }
        String str = string;
        Tokens.TokenKind tokenKind2 = this.tk;
        if (tokenKind2.tag == Tokens.Token.Tag.STRING) {
            Tokens.StringToken stringToken = new Tokens.StringToken(tokenKind2, i, iPosition5, str, listAppendComment);
            position();
            return stringToken;
        }
        Tokens.NumericToken numericToken = new Tokens.NumericToken(tokenKind2, i, iPosition5, str, this.radix, listAppendComment);
        position();
        return numericToken;
    }

    public void errPos(int i) {
        this.errPos = i;
    }

    public void put(char c) {
        this.sb.append(c);
    }

    public void put(String str) {
        this.sb.append(str);
    }

    public JavaTokenizer(ScannerFactory scannerFactory, CharBuffer charBuffer) {
        this(scannerFactory, JavacFileManager.toArray(charBuffer), charBuffer.limit());
    }
}
