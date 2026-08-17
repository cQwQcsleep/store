package com.sun.tools.javac.parser;

import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.Position;
import java.nio.CharBuffer;
import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class JavadocTokenizer extends JavaTokenizer {
    final ScannerFactory fac;

    public static class JavadocComment extends JavaTokenizer.BasicComment {
        private String docComment;
        private boolean firstLine;
        OffsetMap offsetMap;
        private StringBuilder sb;

        public JavadocComment(Tokens.Comment.CommentStyle commentStyle, UnicodeReader unicodeReader, int i, int i2) {
            super(commentStyle, unicodeReader, i, i2);
            this.docComment = null;
            this.firstLine = true;
            this.offsetMap = new OffsetMap();
            this.sb = new StringBuilder();
        }

        @Override // com.sun.tools.javac.parser.JavaTokenizer.BasicComment, com.sun.tools.javac.parser.Tokens.Comment
        public int getSourcePos(int i) {
            if (i == -1) {
                return -1;
            }
            if (i < 0 || i > this.docComment.length()) {
                throw new StringIndexOutOfBoundsException(String.valueOf(i));
            }
            return this.offsetMap.getSourcePos(i);
        }

        @Override // com.sun.tools.javac.parser.JavaTokenizer.BasicComment, com.sun.tools.javac.parser.Tokens.Comment
        public String getText() {
            if (!this.scanned) {
                scanDocComment();
            }
            return this.docComment;
        }

        @Override // com.sun.tools.javac.parser.JavaTokenizer.BasicComment
        public void putLine(UnicodeReader unicodeReader) {
            if (this.firstLine) {
                this.firstLine = false;
            } else {
                this.sb.append('\n');
                this.offsetMap.add(this.sb.length(), unicodeReader.position());
            }
            while (unicodeReader.isAvailable()) {
                this.offsetMap.add(this.sb.length(), unicodeReader.position());
                boolean zIsSurrogate = unicodeReader.isSurrogate();
                StringBuilder sb = this.sb;
                if (zIsSurrogate) {
                    sb.appendCodePoint(unicodeReader.getCodepoint());
                } else {
                    sb.append(unicodeReader.get());
                }
                unicodeReader.next();
            }
        }

        @Override // com.sun.tools.javac.parser.JavaTokenizer.BasicComment
        public void scanDocComment() {
            try {
                super.scanDocComment();
            } finally {
                this.docComment = this.sb.toString();
                this.sb = null;
                this.offsetMap.trim();
            }
        }

        @Override // com.sun.tools.javac.parser.JavaTokenizer.BasicComment, com.sun.tools.javac.parser.Tokens.Comment
        public Tokens.Comment stripIndent() {
            return StrippedComment.of(this);
        }
    }

    public static class OffsetMap {
        private static final int NOFFSETS = 2;
        private static final int POS_OFFSET = 1;
        private static final int SB_OFFSET = 0;
        private int[] map = new int[128];
        private int size = 0;

        private void ensure(int i) {
            int i2 = i + this.size;
            int length = this.map.length;
            while (i2 > length) {
                length <<= 1;
                if (length <= 0) {
                    qc6.a();
                    return;
                }
            }
            int[] iArr = this.map;
            if (length != iArr.length) {
                this.map = Arrays.copyOf(iArr, length);
            }
        }

        private int lastPosOffset() {
            int i = this.size;
            if (i == 0) {
                return 0;
            }
            return this.map[i - 1];
        }

        private int lastSBOffset() {
            int i = this.size;
            if (i == 0) {
                return 0;
            }
            return this.map[i - 2];
        }

        public void add(int i, int i2) {
            if (this.size == 0 || shouldAdd(i, i2)) {
                ensure(2);
                int[] iArr = this.map;
                int i3 = this.size;
                iArr[i3] = i;
                iArr[i3 + 1] = i2;
                this.size = i3 + 2;
            }
        }

        public int getSourcePos(int i) {
            int i2 = this.size;
            if (i2 == 0) {
                return -1;
            }
            int i3 = i2 / 2;
            int i4 = 0;
            while (true) {
                int i5 = i3 - 1;
                int[] iArr = this.map;
                if (i4 >= i5) {
                    int i6 = i4 * 2;
                    return iArr[i6 + 1] + (i - iArr[i6]);
                }
                int i7 = (i4 + i3) / 2;
                int i8 = i7 * 2;
                int i9 = iArr[i8];
                if (i9 < i) {
                    i4 = i7;
                } else {
                    if (i9 == i) {
                        return iArr[i8 + 1];
                    }
                    i3 = i7;
                }
            }
        }

        public boolean shouldAdd(int i, int i2) {
            return i - lastSBOffset() != i2 - lastPosOffset();
        }

        public void trim() {
            this.map = Arrays.copyOf(this.map, this.size);
        }
    }

    public static class StrippedComment implements Tokens.Comment {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        final boolean deprecated;
        final JCDiagnostic.DiagnosticPosition diagPos;
        final OffsetMap sourceMap;
        final OffsetMap strippedMap = new OffsetMap();
        final Tokens.Comment.CommentStyle style;
        String text;

        private StrippedComment(JavadocComment javadocComment, int i) {
            this.diagPos = javadocComment.getPos();
            this.style = javadocComment.getStyle();
            this.deprecated = javadocComment.isDeprecated();
            this.sourceMap = javadocComment.offsetMap;
            stripComment(javadocComment, i);
        }

        public static int getIndent(Tokens.Comment comment) {
            String text = comment.getText();
            int length = text.length();
            int iMin = Integer.MAX_VALUE;
            int i = 0;
            while (i < length) {
                boolean z = true;
                int i2 = i;
                while (i2 < length && text.charAt(i2) != '\n') {
                    if (z && !Character.isWhitespace(text.charAt(i2))) {
                        iMin = Math.min(iMin, i2 - i);
                        z = false;
                    }
                    i2++;
                }
                i = i2 + 1;
            }
            if (iMin == Integer.MAX_VALUE) {
                return 0;
            }
            return iMin;
        }

        public static Tokens.Comment of(JavadocComment javadocComment) {
            int indent;
            return (javadocComment.getStyle() == Tokens.Comment.CommentStyle.JAVADOC_BLOCK && (indent = getIndent(javadocComment)) > 0) ? new StrippedComment(javadocComment, indent) : javadocComment;
        }

        private void stripComment(JavadocComment javadocComment, int i) {
            String text = javadocComment.getText();
            int length = text.length();
            StringBuilder sb = new StringBuilder(length);
            int i2 = 0;
            while (i2 < length) {
                int i3 = i2;
                while (i3 < length && i3 < i2 + i && text.charAt(i3) != '\n') {
                    i3++;
                }
                if (i3 == length) {
                    break;
                }
                i2 = i3 + 1;
                while (i2 < length && text.charAt(i2 - 1) != '\n') {
                    i2++;
                }
                this.strippedMap.add(sb.length(), i3);
                sb.append((CharSequence) text, i3, i2);
            }
            this.text = sb.toString();
            this.strippedMap.trim();
        }

        @Override // com.sun.tools.javac.parser.Tokens.Comment
        public JCDiagnostic.DiagnosticPosition getPos() {
            return this.diagPos;
        }

        @Override // com.sun.tools.javac.parser.Tokens.Comment
        public int getSourcePos(int i) {
            if (i == -1) {
                return -1;
            }
            if (i < 0 || i > this.text.length()) {
                throw new StringIndexOutOfBoundsException(String.valueOf(i));
            }
            return this.sourceMap.getSourcePos(this.strippedMap.getSourcePos(i));
        }

        @Override // com.sun.tools.javac.parser.Tokens.Comment
        public Tokens.Comment.CommentStyle getStyle() {
            return this.style;
        }

        @Override // com.sun.tools.javac.parser.Tokens.Comment
        public String getText() {
            return this.text;
        }

        @Override // com.sun.tools.javac.parser.Tokens.Comment
        public boolean isDeprecated() {
            return this.deprecated;
        }

        @Override // com.sun.tools.javac.parser.Tokens.Comment
        public Tokens.Comment stripIndent() {
            return this;
        }
    }

    public JavadocTokenizer(ScannerFactory scannerFactory, CharBuffer charBuffer) {
        super(scannerFactory, charBuffer);
        this.fac = scannerFactory;
    }

    @Override // com.sun.tools.javac.parser.JavaTokenizer
    public Position.LineMap getLineMap() {
        char[] rawCharacters = getRawCharacters();
        return Position.makeLineMap(rawCharacters, rawCharacters.length, true);
    }

    @Override // com.sun.tools.javac.parser.JavaTokenizer
    public Tokens.Comment processComment(int i, int i2, Tokens.Comment.CommentStyle commentStyle) {
        return new JavadocComment(commentStyle, this, i, i2);
    }

    public JavadocTokenizer(ScannerFactory scannerFactory, char[] cArr, int i) {
        super(scannerFactory, cArr, i);
        this.fac = scannerFactory;
    }
}
