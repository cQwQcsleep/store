package com.reandroid.utils;

import com.intellij.psi.PsiKeyword;
import com.sun.org.apache.xerces.internal.impl.XMLEntityManager;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class HexBytesWriter {
    private static final int DEFAULT_COLUMNS = 4;
    private static final int DEFAULT_INDENT = 0;
    private static final int DEFAULT_WIDTH = 16;
    private final byte[] byteArray;
    private final int columns;
    private boolean decimalLineNumber;
    private final int indent;
    private int lineNumber;
    private String lineNumberFormat;
    private boolean mAppendString;
    private String mEncoding;
    private boolean showLineNumber;
    private final int width;

    public HexBytesWriter(byte[] bArr, int i, int i2, int i3) {
        this.mAppendString = true;
        this.byteArray = bArr;
        this.width = i <= 0 ? 16 : i;
        this.columns = i2 > 0 ? i2 : i;
        this.indent = i3;
    }

    private void fillLastRow(Writer writer, int i) throws IOException {
        int i2 = this.width;
        int i3 = i2 - (i % i2);
        for (int i4 = 0; i4 < i3; i4++) {
            if (i4 != 0 && i4 % this.columns == 0) {
                writer.write(32);
            }
            writer.write(32);
            writer.write(32);
            writer.write(32);
        }
        if (i3 % this.columns == 0) {
            writer.write(32);
        }
    }

    private String getEncoding() {
        if (this.mEncoding == null) {
            this.mEncoding = "UTF-8";
        }
        return this.mEncoding;
    }

    private void initLineNumber(int i) {
        this.lineNumber = 0;
        int i2 = this.decimalLineNumber ? 10 : 16;
        int i3 = 1;
        while (i > i2) {
            i3++;
            i /= i2;
        }
        StringBuilder sb = new StringBuilder("%0");
        sb.append(i3);
        sb.append(this.decimalLineNumber ? "d" : "x");
        this.lineNumberFormat = sb.toString();
    }

    private void printChar(Writer writer, char c) throws IOException {
        if (c == '\t') {
            writer.write(92);
            writer.write(116);
        } else if (c == '\n') {
            writer.write(92);
            writer.write(110);
        } else if (c != '\r') {
            writer.write(c);
        } else {
            writer.write(92);
            writer.write(114);
        }
    }

    public static String printHex(byte[] bArr) {
        return toHex(bArr, false, false);
    }

    public static String toHex(byte[] bArr, boolean z, boolean z2) {
        if (bArr == null) {
            return PsiKeyword.NULL;
        }
        StringWriter stringWriter = new StringWriter();
        HexBytesWriter hexBytesWriter = new HexBytesWriter(bArr);
        hexBytesWriter.setShowLineNumber(z);
        hexBytesWriter.setAppendString(z2);
        try {
            hexBytesWriter.write(stringWriter);
            stringWriter.flush();
            stringWriter.close();
        } catch (IOException unused) {
        }
        return stringWriter.toString();
    }

    private void writeHex(Writer writer, byte b) throws IOException {
        writer.write(HexUtil.toHex((String) null, b & 255, 2).toUpperCase());
    }

    private void writeIndent(Writer writer) throws IOException {
        for (int i = 0; i < this.indent; i++) {
            writer.write(32);
        }
    }

    private void writeLineNumber(Writer writer) throws IOException {
        if (this.showLineNumber) {
            String upperCase = String.format(this.lineNumberFormat, Integer.valueOf(this.lineNumber));
            if (!this.decimalLineNumber) {
                upperCase = upperCase.toUpperCase();
            }
            writer.write(upperCase);
            writer.write(": ");
            this.lineNumber += this.width;
        }
    }

    private void writeNewLine(Writer writer) throws IOException {
        writer.write(10);
    }

    private void writeString(Writer writer, int i, int i2) throws IOException {
        if (this.mAppendString) {
            int i3 = i2 - i;
            if (i3 < 0) {
                i3 = 0;
            }
            if (this.width - i > 0) {
                fillLastRow(writer, i2);
            }
            writer.write(32);
            writer.write(32);
            writer.write(32);
            writer.write(32);
            for (char c : new String(this.byteArray, i3, i, getEncoding()).toCharArray()) {
                printChar(writer, c);
            }
        }
    }

    public void setAppendString(boolean z) {
        this.mAppendString = z;
    }

    public void setDecimalLineNumber(boolean z) {
        this.decimalLineNumber = z;
    }

    public void setEncoding(String str) {
        if (!XMLEntityManager.EncodingInfo.STR_UTF16.equals(str)) {
            str = "UTF-8";
        }
        this.mEncoding = str;
    }

    public void setShowLineNumber(boolean z) {
        this.showLineNumber = z;
    }

    public void write(Writer writer, int i, int i2) throws IOException {
        int i3;
        boolean z;
        byte[] bArr = this.byteArray;
        if (bArr == null) {
            return;
        }
        initLineNumber(bArr.length);
        int i4 = this.width;
        int i5 = this.columns;
        int i6 = 0;
        for (int i7 = 0; i7 < i2; i7++) {
            if (i7 % i4 == 0) {
                if (i7 != 0) {
                    writeString(writer, i6, i7);
                    writeNewLine(writer);
                    z = true;
                } else {
                    z = false;
                }
                writeIndent(writer);
                writeLineNumber(writer);
                i3 = 0;
            } else {
                if (i6 % i5 == 0) {
                    writer.write(32);
                }
                i3 = i6;
                z = false;
            }
            if (!z && i7 != 0) {
                writer.write(32);
            }
            i6 = i3 + 1;
            writeHex(writer, bArr[i + i7]);
        }
        if (i6 > 0) {
            writeString(writer, i6, i2);
        }
    }

    public HexBytesWriter(byte[] bArr, int i) {
        this(bArr, i, 4, 0);
    }

    public HexBytesWriter(byte[] bArr) {
        this(bArr, 16, 4, 0);
    }

    public static String toHex(byte[] bArr, boolean z) {
        return toHex(bArr, z, true);
    }

    public static String toHex(byte[] bArr) {
        if (bArr == null) {
            return PsiKeyword.NULL;
        }
        return toHex(bArr, bArr.length > 64);
    }

    public void write(Writer writer) throws IOException {
        byte[] bArr = this.byteArray;
        if (bArr == null) {
            return;
        }
        write(writer, 0, bArr.length);
    }
}
