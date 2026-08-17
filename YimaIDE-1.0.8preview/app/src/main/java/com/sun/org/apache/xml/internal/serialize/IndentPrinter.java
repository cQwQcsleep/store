package com.sun.org.apache.xml.internal.serialize;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@Deprecated
public class IndentPrinter extends Printer {
    private StringBuffer _line;
    private int _nextIndent;
    private int _spaces;
    private StringBuffer _text;
    private int _thisIndent;

    public IndentPrinter(Writer writer, OutputFormat outputFormat) {
        super(writer, outputFormat);
        this._line = new StringBuffer(80);
        this._text = new StringBuffer(20);
        this._spaces = 0;
        this._nextIndent = 0;
        this._thisIndent = 0;
    }

    @Override // com.sun.org.apache.xml.internal.serialize.Printer
    public void breakLine(boolean z) {
        StringBuffer stringBuffer;
        if (this._text.length() > 0) {
            while (true) {
                int i = this._spaces;
                stringBuffer = this._line;
                if (i <= 0) {
                    break;
                }
                stringBuffer.append(' ');
                this._spaces--;
            }
            stringBuffer.append(this._text);
            this._text = new StringBuffer(20);
        }
        flushLine(z);
        try {
            this._writer.write(this._format.getLineSeparator());
        } catch (IOException e) {
            if (this._exception == null) {
                this._exception = e;
            }
        }
    }

    @Override // com.sun.org.apache.xml.internal.serialize.Printer
    public void enterDTD() {
        if (this._dtdWriter == null) {
            this._line.append(this._text);
            this._text = new StringBuffer(20);
            flushLine(false);
            StringWriter stringWriter = new StringWriter();
            this._dtdWriter = stringWriter;
            this._docWriter = this._writer;
            this._writer = stringWriter;
        }
    }

    @Override // com.sun.org.apache.xml.internal.serialize.Printer
    public void flush() {
        if (this._line.length() > 0 || this._text.length() > 0) {
            breakLine();
        }
        try {
            this._writer.flush();
        } catch (IOException e) {
            if (this._exception == null) {
                this._exception = e;
            }
        }
    }

    @Override // com.sun.org.apache.xml.internal.serialize.Printer
    public void flushLine(boolean z) {
        if (this._line.length() > 0) {
            try {
                if (this._format.getIndenting() && !z) {
                    int lineWidth = this._thisIndent;
                    if (lineWidth * 2 > this._format.getLineWidth() && this._format.getLineWidth() > 0) {
                        lineWidth = this._format.getLineWidth() / 2;
                    }
                    while (lineWidth > 0) {
                        this._writer.write(32);
                        lineWidth--;
                    }
                }
                this._thisIndent = this._nextIndent;
                this._spaces = 0;
                this._writer.write(this._line.toString());
                this._line = new StringBuffer(40);
            } catch (IOException e) {
                if (this._exception == null) {
                    this._exception = e;
                }
            }
        }
    }

    @Override // com.sun.org.apache.xml.internal.serialize.Printer
    public int getNextIndent() {
        return this._nextIndent;
    }

    @Override // com.sun.org.apache.xml.internal.serialize.Printer
    public void indent() {
        this._nextIndent += this._format.getIndent();
    }

    @Override // com.sun.org.apache.xml.internal.serialize.Printer
    public String leaveDTD() {
        if (this._writer != this._dtdWriter) {
            return null;
        }
        this._line.append(this._text);
        this._text = new StringBuffer(20);
        flushLine(false);
        this._writer = this._docWriter;
        return this._dtdWriter.toString();
    }

    @Override // com.sun.org.apache.xml.internal.serialize.Printer
    public void printSpace() {
        StringBuffer stringBuffer;
        if (this._text.length() > 0) {
            if (this._format.getLineWidth() > 0 && this._thisIndent + this._line.length() + this._spaces + this._text.length() > this._format.getLineWidth()) {
                flushLine(false);
                try {
                    this._writer.write(this._format.getLineSeparator());
                } catch (IOException e) {
                    if (this._exception == null) {
                        this._exception = e;
                    }
                }
            }
            while (true) {
                int i = this._spaces;
                stringBuffer = this._line;
                if (i <= 0) {
                    break;
                }
                stringBuffer.append(' ');
                this._spaces--;
            }
            stringBuffer.append(this._text);
            this._text = new StringBuffer(20);
        }
        this._spaces++;
    }

    @Override // com.sun.org.apache.xml.internal.serialize.Printer
    public void printText(StringBuffer stringBuffer) {
        this._text.append(stringBuffer.toString());
    }

    @Override // com.sun.org.apache.xml.internal.serialize.Printer
    public void setNextIndent(int i) {
        this._nextIndent = i;
    }

    @Override // com.sun.org.apache.xml.internal.serialize.Printer
    public void setThisIndent(int i) {
        this._thisIndent = i;
    }

    @Override // com.sun.org.apache.xml.internal.serialize.Printer
    public void unindent() {
        int indent = this._nextIndent - this._format.getIndent();
        this._nextIndent = indent;
        if (indent < 0) {
            this._nextIndent = 0;
        }
        if (this._line.length() + this._spaces + this._text.length() == 0) {
            this._thisIndent = this._nextIndent;
        }
    }

    @Override // com.sun.org.apache.xml.internal.serialize.Printer
    public void printText(String str) {
        this._text.append(str);
    }

    @Override // com.sun.org.apache.xml.internal.serialize.Printer
    public void printText(char c) {
        this._text.append(c);
    }

    @Override // com.sun.org.apache.xml.internal.serialize.Printer
    public void printText(char[] cArr, int i, int i2) {
        this._text.append(cArr, i, i2);
    }

    @Override // com.sun.org.apache.xml.internal.serialize.Printer
    public void breakLine() {
        breakLine(false);
    }
}
