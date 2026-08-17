package com.sun.org.apache.xml.internal.serialize;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@Deprecated
public class Printer {
    private static final int BufferSize = 4096;
    protected final OutputFormat _format;
    protected Writer _writer;
    private final char[] _buffer = new char[4096];
    protected IOException _exception = null;
    protected StringWriter _dtdWriter = null;
    protected Writer _docWriter = null;
    private int _pos = 0;

    public Printer(Writer writer, OutputFormat outputFormat) {
        this._writer = writer;
        this._format = outputFormat;
    }

    public void breakLine() throws IOException {
        try {
            if (this._pos == 4096) {
                this._writer.write(this._buffer);
                this._pos = 0;
            }
            char[] cArr = this._buffer;
            int i = this._pos;
            cArr[i] = '\n';
            this._pos = i + 1;
        } catch (IOException e) {
            if (this._exception == null) {
                this._exception = e;
            }
            throw e;
        }
    }

    public void enterDTD() throws IOException {
        if (this._dtdWriter == null) {
            flushLine(false);
            StringWriter stringWriter = new StringWriter();
            this._dtdWriter = stringWriter;
            this._docWriter = this._writer;
            this._writer = stringWriter;
        }
    }

    public void flush() throws IOException {
        try {
            this._writer.write(this._buffer, 0, this._pos);
            this._writer.flush();
            this._pos = 0;
        } catch (IOException e) {
            if (this._exception == null) {
                this._exception = e;
            }
            throw e;
        }
    }

    public void flushLine(boolean z) throws IOException {
        try {
            this._writer.write(this._buffer, 0, this._pos);
        } catch (IOException e) {
            if (this._exception == null) {
                this._exception = e;
            }
        }
        this._pos = 0;
    }

    public IOException getException() {
        return this._exception;
    }

    public int getNextIndent() {
        return 0;
    }

    public void indent() {
    }

    public String leaveDTD() throws IOException {
        if (this._writer != this._dtdWriter) {
            return null;
        }
        flushLine(false);
        this._writer = this._docWriter;
        return this._dtdWriter.toString();
    }

    public void printSpace() throws IOException {
        try {
            if (this._pos == 4096) {
                this._writer.write(this._buffer);
                this._pos = 0;
            }
            char[] cArr = this._buffer;
            int i = this._pos;
            cArr[i] = ' ';
            this._pos = i + 1;
        } catch (IOException e) {
            if (this._exception == null) {
                this._exception = e;
            }
            throw e;
        }
    }

    public void printText(String str) throws IOException {
        try {
            int length = str.length();
            for (int i = 0; i < length; i++) {
                if (this._pos == 4096) {
                    this._writer.write(this._buffer);
                    this._pos = 0;
                }
                this._buffer[this._pos] = str.charAt(i);
                this._pos++;
            }
        } catch (IOException e) {
            if (this._exception == null) {
                this._exception = e;
            }
            throw e;
        }
    }

    public void setNextIndent(int i) {
    }

    public void setThisIndent(int i) {
    }

    public void unindent() {
    }

    public void breakLine(boolean z) throws IOException {
        breakLine();
    }

    public void printText(StringBuffer stringBuffer) throws IOException {
        try {
            int length = stringBuffer.length();
            for (int i = 0; i < length; i++) {
                if (this._pos == 4096) {
                    this._writer.write(this._buffer);
                    this._pos = 0;
                }
                this._buffer[this._pos] = stringBuffer.charAt(i);
                this._pos++;
            }
        } catch (IOException e) {
            if (this._exception == null) {
                this._exception = e;
            }
            throw e;
        }
    }

    public void printText(char[] cArr, int i, int i2) throws IOException {
        while (true) {
            int i3 = i2 - 1;
            if (i2 <= 0) {
                return;
            }
            try {
                if (this._pos == 4096) {
                    this._writer.write(this._buffer);
                    this._pos = 0;
                }
                char[] cArr2 = this._buffer;
                int i4 = this._pos;
                cArr2[i4] = cArr[i];
                i++;
                this._pos = i4 + 1;
                i2 = i3;
            } catch (IOException e) {
                if (this._exception == null) {
                    this._exception = e;
                }
                throw e;
            }
        }
    }

    public void printText(char c) throws IOException {
        try {
            if (this._pos == 4096) {
                this._writer.write(this._buffer);
                this._pos = 0;
            }
            char[] cArr = this._buffer;
            int i = this._pos;
            cArr[i] = c;
            this._pos = i + 1;
        } catch (IOException e) {
            if (this._exception == null) {
                this._exception = e;
            }
            throw e;
        }
    }
}
