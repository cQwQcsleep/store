package com.sun.tools.javac.util;

import com.sun.tools.javac.file.JavacFileManager;
import com.sun.tools.javac.tree.EndPosTable;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.CharBuffer;
import javax.tools.JavaFileObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DiagnosticSource {
    public static final DiagnosticSource NO_SOURCE = new DiagnosticSource() { // from class: com.sun.tools.javac.util.DiagnosticSource.1
        @Override // com.sun.tools.javac.util.DiagnosticSource
        public boolean findLine(int i) {
            return false;
        }
    };
    protected char[] buf;
    protected int bufLen;
    protected EndPosTable endPosTable;
    protected JavaFileObject fileObject;
    protected int line;
    protected int lineStart;
    protected AbstractLog log;
    protected SoftReference<char[]> refBuf;

    public DiagnosticSource(JavaFileObject javaFileObject, AbstractLog abstractLog) {
        this.fileObject = javaFileObject;
        this.log = abstractLog;
    }

    public boolean findLine(int i) {
        int i2;
        SoftReference<char[]> softReference;
        if (i == -1) {
            return false;
        }
        try {
            if (this.buf == null && (softReference = this.refBuf) != null) {
                this.buf = softReference.get();
            }
            if (this.buf == null) {
                this.buf = initBuf(this.fileObject);
                this.lineStart = 0;
                this.line = 1;
            } else if (this.lineStart > i) {
                this.lineStart = 0;
                this.line = 1;
            }
            int i3 = this.lineStart;
            while (true) {
                i2 = this.bufLen;
                if (i3 >= i2 || i3 >= i) {
                    break;
                }
                char[] cArr = this.buf;
                int i4 = i3 + 1;
                char c = cArr[i3];
                if (c == '\n') {
                    this.line++;
                    this.lineStart = i4;
                } else if (c == '\r') {
                    if (i4 < i2 && cArr[i4] == '\n') {
                        i4 = i3 + 2;
                    }
                    this.line++;
                    this.lineStart = i4;
                }
                i3 = i4;
            }
            return i3 <= i2;
        } catch (IOException unused) {
            this.log.directError("source.unavailable", new Object[0]);
            this.buf = new char[0];
            return false;
        }
    }

    public int getColumnNumber(int i, boolean z) {
        try {
            if (!findLine(i)) {
                this.buf = null;
                return 0;
            }
            int iTabulate = 0;
            for (int i2 = this.lineStart; i2 < i; i2++) {
                if (i2 >= this.bufLen) {
                    this.buf = null;
                    return 0;
                }
                iTabulate = (this.buf[i2] == '\t' && z) ? LayoutCharacters.tabulate(iTabulate) : iTabulate + 1;
            }
            int i3 = iTabulate + 1;
            this.buf = null;
            return i3;
        } catch (Throwable th) {
            this.buf = null;
            throw th;
        }
    }

    public EndPosTable getEndPosTable() {
        return this.endPosTable;
    }

    public JavaFileObject getFile() {
        return this.fileObject;
    }

    public String getLine(int i) {
        char c;
        try {
            if (!findLine(i)) {
                return null;
            }
            int i2 = this.lineStart;
            while (i2 < this.bufLen && (c = this.buf[i2]) != '\r' && c != '\n') {
                i2++;
            }
            int i3 = this.lineStart;
            if (i2 - i3 == 0) {
                return null;
            }
            return new String(this.buf, i3, i2 - i3);
        } finally {
            this.buf = null;
        }
    }

    public int getLineNumber(int i) {
        try {
            if (findLine(i)) {
                return this.line;
            }
            return 0;
        } finally {
            this.buf = null;
        }
    }

    public char[] initBuf(JavaFileObject javaFileObject) throws IOException {
        char[] charArray;
        CharSequence charContent = javaFileObject.getCharContent(true);
        if (charContent instanceof CharBuffer) {
            CharBuffer charBuffer = (CharBuffer) charContent;
            charArray = JavacFileManager.toArray(charBuffer);
            this.bufLen = charBuffer.limit();
        } else {
            charArray = charContent.toString().toCharArray();
            this.bufLen = charArray.length;
        }
        this.refBuf = new SoftReference<>(charArray);
        return charArray;
    }

    public void setEndPosTable(EndPosTable endPosTable) {
        EndPosTable endPosTable2 = this.endPosTable;
        if (endPosTable2 == null || endPosTable2 == endPosTable) {
            this.endPosTable = endPosTable;
        } else {
            k2d.a("endPosTable already set");
        }
    }

    private DiagnosticSource() {
    }
}
