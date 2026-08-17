package io.github.rosemoe.sora.text;

import java.io.IOException;
import java.io.Reader;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class ContentReference extends TextReference {
    private final Content content;

    public class RefReader extends Reader {
        private int column;
        private int line;
        private int markedColumn;
        private int markedLine;

        private RefReader() {
        }

        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.line = Integer.MAX_VALUE;
            this.column = Integer.MAX_VALUE;
        }

        @Override // java.io.Reader
        public void mark(int i) throws IOException {
            this.markedLine = this.line;
            this.markedColumn = this.column;
        }

        @Override // java.io.Reader
        public boolean markSupported() {
            return true;
        }

        @Override // java.io.Reader
        public int read(char[] cArr, int i, int i2) {
            if (cArr.length < i + i2) {
                w01.a("size not enough");
                return 0;
            }
            int i3 = 0;
            while (i3 < i2 && this.line < ContentReference.this.getLineCount()) {
                ContentLine line = ContentReference.this.content.getLine(this.line);
                int length = line.getLineSeparator().getLength();
                int length2 = line.length();
                int iMax = Math.max(0, Math.min(length2 - this.column, i2 - i3));
                if (iMax > 0) {
                    Content content = ContentReference.this.content;
                    int i4 = this.line;
                    int i5 = this.column;
                    content.getRegionOnLine(i4, i5, i5 + iMax, cArr, i + i3);
                }
                this.column += iMax;
                i3 += iMax;
                while (i3 < i2) {
                    int i6 = this.column;
                    if (length2 > i6 || i6 >= length2 + length) {
                        break;
                    }
                    cArr[i + i3] = line.getLineSeparator().getContent().charAt(this.column - length2);
                    i3++;
                    this.column++;
                }
                if (this.column >= length2 + length) {
                    this.line++;
                    this.column = 0;
                }
            }
            if (i3 == 0) {
                return -1;
            }
            return i3;
        }

        @Override // java.io.Reader
        public void reset() throws IOException {
            this.line = this.markedLine;
            this.column = this.markedColumn;
        }
    }

    public ContentReference(Content content) {
        super(content);
        this.content = content;
    }

    public void appendLineTo(StringBuilder sb, int i) {
        validateAccess();
        this.content.getLine(i).appendTo(sb);
    }

    @Override // io.github.rosemoe.sora.text.TextReference, java.lang.CharSequence
    public char charAt(int i) {
        validateAccess();
        return this.content.charAt(i);
    }

    public Reader createReader() {
        return new RefReader();
    }

    public int getCharIndex(int i, int i2) {
        validateAccess();
        return this.content.getCharIndex(i, i2);
    }

    public CharPosition getCharPosition(int i, int i2) {
        validateAccess();
        return this.content.getIndexer().getCharPosition(i, i2);
    }

    public int getColumnCount(int i) {
        validateAccess();
        return this.content.getColumnCount(i);
    }

    public long getDocumentVersion() {
        validateAccess();
        return this.content.getDocumentVersion();
    }

    public String getLine(int i) {
        validateAccess();
        return this.content.getLineString(i);
    }

    public void getLineChars(int i, char[] cArr) {
        validateAccess();
        this.content.getLineChars(i, cArr);
    }

    public int getLineCount() {
        validateAccess();
        return this.content.getLineCount();
    }

    public String getLineSeparator(int i) {
        validateAccess();
        return this.content.getLineSeparatorUnsafe(i).getContent();
    }

    @Override // io.github.rosemoe.sora.text.TextReference
    public Content getReference() {
        return (Content) super.getReference();
    }

    @Override // io.github.rosemoe.sora.text.TextReference
    public ContentReference setValidator(TextReference.Validator validator) {
        super.setValidator(validator);
        return this;
    }

    public char charAt(int i, int i2) {
        validateAccess();
        return this.content.charAt(i, i2);
    }

    public CharPosition getCharPosition(int i) {
        validateAccess();
        return this.content.getIndexer().getCharPosition(i);
    }
}
