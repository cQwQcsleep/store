package com.reandroid.xml.kxml2;

import com.reandroid.apk.XmlHelper;
import com.sun.org.apache.xml.internal.serializer.CharInfo;
import com.sun.org.apache.xml.internal.serializer.SerializerConstants;
import defpackage.krd;
import defpackage.zba;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Locale;
import jdk.xml.internal.JdkConstants;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class KXmlSerializer implements XmlSerializer {
    private static final int BUFFER_LEN = 8192;
    private int auto;
    private int depth;
    private String encoding;
    private boolean firstAttributeWritten;
    private int indentAttributeReference;
    private int mPos;
    private boolean pending;
    private boolean unicode;
    private Writer writer;
    private final char[] mText = new char[8192];
    private String[] elementStack = new String[12];
    private int[] nspCounts = new int[4];
    private String[] nspStack = new String[8];
    private boolean[] indent = new boolean[4];
    private boolean enableIndentAttributes = true;

    private void append(String str, int i, int i2) throws IOException {
        while (i2 > 0) {
            if (this.mPos == 8192) {
                flushBuffer();
            }
            int i3 = this.mPos;
            int i4 = 8192 - i3;
            if (i4 > i2) {
                i4 = i2;
            }
            int i5 = i + i4;
            str.getChars(i, i5, this.mText, i3);
            i2 -= i4;
            this.mPos += i4;
            i = i5;
        }
    }

    private void appendSpace(int i) throws IOException {
        while (i > 0) {
            if (this.mPos == 8192) {
                flushBuffer();
            }
            int i2 = this.mPos;
            int i3 = 8192 - i2;
            if (i3 > i) {
                i3 = i;
            }
            Arrays.fill(this.mText, i2, i2 + i3, ' ');
            i -= i3;
            this.mPos += i3;
        }
    }

    private void attributeIndent() throws IOException {
        int i;
        if (isEnableIndentAttributes() && this.firstAttributeWritten && this.indent[this.depth] && (i = this.indentAttributeReference) > 0) {
            append(CharInfo.S_CARRIAGERETURN);
            append('\n');
            appendSpace(i);
        }
    }

    private void check(boolean z, boolean z2) throws IOException {
        if (!this.pending) {
            return;
        }
        int i = this.depth;
        int i2 = i + 1;
        this.depth = i2;
        this.pending = false;
        boolean[] zArr = this.indent;
        if (zArr.length <= i2) {
            boolean[] zArr2 = new boolean[i + 5];
            System.arraycopy(zArr, 0, zArr2, 0, i2);
            this.indent = zArr2;
        }
        boolean[] zArr3 = this.indent;
        int i3 = this.depth;
        zArr3[i3] = zArr3[i3 - 1];
        int i4 = this.nspCounts[i3 - 1];
        while (true) {
            int[] iArr = this.nspCounts;
            int i5 = this.depth;
            if (i4 >= iArr[i5]) {
                if (iArr.length <= i5 + 1) {
                    int[] iArr2 = new int[i5 + 8];
                    System.arraycopy(iArr, 0, iArr2, 0, i5 + 1);
                    this.nspCounts = iArr2;
                }
                int[] iArr3 = this.nspCounts;
                int i6 = this.depth;
                iArr3[i6 + 1] = iArr3[i6];
                if (!z || z2) {
                    append('>');
                    return;
                } else {
                    append(" />");
                    return;
                }
            }
            if (!z2) {
                append(" xmlns");
            }
            int i7 = i4 * 2;
            if (this.nspStack[i7].isEmpty()) {
                if (getNamespace().isEmpty() && !this.nspStack[i7 + 1].isEmpty()) {
                    k2d.a("Cannot set default namespace for elements in no namespace");
                    return;
                }
            } else if (!z2) {
                append(':');
                append(this.nspStack[i7]);
            }
            if (!z2) {
                append("=\"");
                writeEscaped(this.nspStack[i7 + 1], 34);
                append('\"');
            }
            i4++;
        }
    }

    private void flushBuffer() throws IOException {
        int i = this.mPos;
        if (i > 0) {
            this.writer.write(this.mText, 0, i);
            this.writer.flush();
            this.mPos = 0;
        }
    }

    private String getPrefix(String str, boolean z, boolean z2) throws IOException {
        String string;
        int[] iArr = this.nspCounts;
        int i = this.depth;
        String[] strArr = this.nspStack;
        int i2 = i + 1;
        int i3 = iArr[i2] * 2;
        while (true) {
            i3 -= 2;
            String str2 = null;
            if (i3 < 0) {
                if (!z2) {
                    return null;
                }
                if (str.isEmpty()) {
                    string = "";
                } else {
                    do {
                        StringBuilder sb = new StringBuilder("n");
                        int i4 = this.auto;
                        this.auto = i4 + 1;
                        sb.append(i4);
                        string = sb.toString();
                        for (int i5 = (iArr[i2] * 2) - 2; i5 >= 0; i5 -= 2) {
                            if (string.equals(strArr[i5])) {
                                string = null;
                                break;
                            }
                        }
                    } while (string == null);
                }
                boolean z3 = this.pending;
                this.pending = false;
                setPrefix(string, str);
                this.pending = z3;
                return string;
            }
            if (strArr[i3 + 1].equals(str) && (z || !strArr[i3].isEmpty())) {
                String str3 = strArr[i3];
                int i6 = i3 + 2;
                while (true) {
                    if (i6 >= iArr[i2] * 2) {
                        str2 = str3;
                        break;
                    }
                    if (strArr[i6].equals(str3)) {
                        break;
                    }
                    i6++;
                }
                if (str2 != null) {
                    return str2;
                }
            }
        }
    }

    private static void reportInvalidCharacter(char c) {
        throw new IllegalArgumentException("Illegal character (U+" + Integer.toHexString(c) + ")");
    }

    private void writeEscaped(String str, int i) throws IOException {
        int length = str.length();
        int i2 = 0;
        while (i2 < length) {
            char cCharAt = str.charAt(i2);
            if (cCharAt == '\t' || cCharAt == '\n' || cCharAt == '\r') {
                if (i == -1) {
                    append(cCharAt);
                } else {
                    append("&#" + ((int) cCharAt) + ';');
                }
            } else if (cCharAt == '&') {
                append(SerializerConstants.ENTITY_AMP);
            } else if (cCharAt == '<') {
                append(SerializerConstants.ENTITY_LT);
            } else if (cCharAt == '>') {
                append(SerializerConstants.ENTITY_GT);
            } else if (cCharAt == i) {
                append(cCharAt == '\"' ? SerializerConstants.ENTITY_QUOT : "&apos;");
            } else if ((cCharAt < ' ' || cCharAt > 55295) && (cCharAt < 57344 || cCharAt > 65533)) {
                if (!Character.isHighSurrogate(cCharAt) || i2 >= length - 1) {
                    append("&#" + ((int) cCharAt) + ";");
                } else {
                    i2++;
                    writeSurrogate(cCharAt, str.charAt(i2));
                }
            } else if (this.unicode || cCharAt < 127) {
                append(cCharAt);
            } else {
                append("&#" + ((int) cCharAt) + ";");
            }
            i2++;
        }
    }

    private void writeSurrogate(char c, char c2) throws IOException {
        if (!Character.isLowSurrogate(c2)) {
            krd.a("Bad surrogate pair (U+", Integer.toHexString(c), " U+", Integer.toHexString(c2), ")");
            return;
        }
        append("&#" + Character.toCodePoint(c, c2) + ";");
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public XmlSerializer attribute(String str, String str2, String str3) throws IOException {
        if (!this.pending) {
            k2d.a("illegal position for attribute");
            return null;
        }
        if (str == null) {
            str = "";
        }
        String prefix = str.isEmpty() ? "" : getPrefix(str, false, true);
        attributeIndent();
        append(' ');
        if (!prefix.isEmpty()) {
            append(prefix);
            append(':');
        }
        append(str2);
        append('=');
        char c = str3.indexOf(34) != -1 ? '\'' : '\"';
        append(c);
        writeEscaped(str3, c);
        append(c);
        this.firstAttributeWritten = true;
        return this;
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void cdsect(String str) throws IOException {
        int i = 0;
        check(false, false);
        String strReplace = str.replace("]]>", SerializerConstants.CDATA_CONTINUE);
        append("<![CDATA[");
        while (i < strReplace.length()) {
            char cCharAt = strReplace.charAt(i);
            if ((cCharAt >= ' ' && cCharAt <= 55295) || cCharAt == '\t' || cCharAt == '\n' || cCharAt == '\r' || (cCharAt >= 57344 && cCharAt <= 65533)) {
                append(cCharAt);
            } else if (!Character.isHighSurrogate(cCharAt) || i >= strReplace.length() - 1) {
                reportInvalidCharacter(cCharAt);
            } else {
                append("]]>");
                i++;
                writeSurrogate(cCharAt, strReplace.charAt(i));
                append("<![CDATA[");
            }
            i++;
        }
        append("]]>");
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void comment(String str) throws IOException {
        check(false, false);
        append("<!--");
        append(str);
        append("-->");
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void docdecl(String str) throws IOException {
        append("<!DOCTYPE");
        append(str);
        append('>');
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void endDocument() throws IOException {
        while (true) {
            int i = this.depth;
            if (i <= 0) {
                flush();
                return;
            } else {
                String[] strArr = this.elementStack;
                endTag(strArr[(i * 3) - 3], strArr[(i * 3) - 1]);
            }
        }
    }

    public XmlSerializer endTag(boolean z, String str, String str2) throws IOException {
        if (!this.pending) {
            this.depth--;
        }
        if ((str == null && this.elementStack[this.depth * 3] != null) || ((str != null && !str.equals(this.elementStack[this.depth * 3])) || !this.elementStack[(this.depth * 3) + 2].equals(str2))) {
            zba.a("</{", str, "}", str2, "> does not match start");
            return null;
        }
        if (this.pending) {
            check(true, z);
            this.depth--;
        } else {
            if (this.indent[this.depth + 1] && !z) {
                append(CharInfo.S_CARRIAGERETURN);
                append('\n');
                appendSpace(this.depth * 2);
            }
            if (!z) {
                append("</");
                String str3 = this.elementStack[(this.depth * 3) + 1];
                if (!str3.isEmpty()) {
                    append(str3);
                    append(':');
                }
                append(str2);
                append('>');
            }
        }
        int[] iArr = this.nspCounts;
        int i = this.depth;
        iArr[i + 1] = iArr[i];
        return this;
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void entityRef(String str) throws IOException {
        check(false, false);
        append('&');
        append(str);
        append(';');
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void flush() throws IOException {
        check(false, false);
        flushBuffer();
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public int getDepth() {
        boolean z = this.pending;
        int i = this.depth;
        return z ? i + 1 : i;
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public boolean getFeature(String str) {
        return XmlHelper.FEATURE_INDENT.equals(str) && this.indent[this.depth];
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public String getName() {
        if (getDepth() == 0) {
            return null;
        }
        return this.elementStack[(getDepth() * 3) - 1];
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public String getNamespace() {
        if (getDepth() == 0) {
            return null;
        }
        return this.elementStack[(getDepth() * 3) - 3];
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public Object getProperty(String str) {
        throw new RuntimeException("Unsupported property: " + str);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void ignorableWhitespace(String str) throws IOException {
        text(str);
    }

    public boolean isEnableIndentAttributes() {
        return this.enableIndentAttributes;
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void processingInstruction(String str) throws IOException {
        check(false, false);
        append("<?");
        append(str);
        append("?>");
    }

    public void setEnableIndentAttributes(boolean z) {
        this.enableIndentAttributes = z;
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void setFeature(String str, boolean z) {
        if (!XmlHelper.FEATURE_INDENT.equals(str)) {
            y04.a("Unsupported Feature: ", str);
        } else {
            this.indent[this.depth] = z;
            this.firstAttributeWritten = false;
        }
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void setOutput(OutputStream outputStream, String str) throws IOException {
        if (outputStream == null) {
            w01.a("os == null");
            return;
        }
        setOutput(str == null ? new OutputStreamWriter(outputStream) : new OutputStreamWriter(outputStream, str));
        this.encoding = str;
        if (str == null || !str.toLowerCase(Locale.US).startsWith("utf")) {
            return;
        }
        this.unicode = true;
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void setPrefix(String str, String str2) throws IOException {
        check(false, false);
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        if (str.equals(getPrefix(str2, true, false))) {
            return;
        }
        int[] iArr = this.nspCounts;
        int i = this.depth + 1;
        int i2 = iArr[i];
        iArr[i] = i2 + 1;
        int i3 = i2 << 1;
        String[] strArr = this.nspStack;
        int i4 = i3 + 1;
        if (strArr.length < i4) {
            String[] strArr2 = new String[strArr.length + 16];
            System.arraycopy(strArr, 0, strArr2, 0, i3);
            this.nspStack = strArr2;
        }
        String[] strArr3 = this.nspStack;
        strArr3[i3] = str;
        strArr3[i4] = str2;
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void setProperty(String str, Object obj) {
        throw new RuntimeException("Unsupported Property:" + obj);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void startDocument(String str, Boolean bool) throws IOException {
        append("<?xml version='1.0' ");
        if (str != null) {
            this.encoding = str;
            if (str.toLowerCase(Locale.US).startsWith("utf")) {
                this.unicode = true;
            }
        }
        if (this.encoding != null) {
            append("encoding='");
            append(this.encoding);
            append("' ");
        }
        if (bool != null) {
            append("standalone='");
            append(bool.booleanValue() ? JdkConstants.JDK_YES : "no");
            append("' ");
        }
        append("?>");
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public XmlSerializer startTag(String str, String str2) throws IOException {
        check(false, false);
        this.firstAttributeWritten = false;
        this.indentAttributeReference = 0;
        if (this.indent[this.depth]) {
            append(CharInfo.S_CARRIAGERETURN);
            append('\n');
            int i = this.depth * 2;
            appendSpace(i);
            this.indentAttributeReference = i;
        }
        int i2 = this.depth * 3;
        String[] strArr = this.elementStack;
        if (strArr.length < i2 + 3) {
            String[] strArr2 = new String[strArr.length + 12];
            System.arraycopy(strArr, 0, strArr2, 0, i2);
            this.elementStack = strArr2;
        }
        String prefix = str == null ? "" : getPrefix(str, true, true);
        if (str != null && str.isEmpty()) {
            for (int i3 = this.nspCounts[this.depth]; i3 < this.nspCounts[this.depth + 1]; i3++) {
                int i4 = i3 * 2;
                if (this.nspStack[i4].isEmpty() && !this.nspStack[i4 + 1].isEmpty()) {
                    k2d.a("Cannot set default namespace for elements in no namespace");
                    return null;
                }
            }
        }
        String[] strArr3 = this.elementStack;
        strArr3[i2] = str;
        strArr3[i2 + 1] = prefix;
        strArr3[i2 + 2] = str2;
        append('<');
        this.indentAttributeReference++;
        if (!prefix.isEmpty()) {
            append(prefix);
            append(':');
            this.indentAttributeReference += prefix.length() + 1;
        }
        append(str2);
        int length = str2.length();
        if (length > 20) {
            length = 20;
        }
        this.indentAttributeReference += length;
        this.pending = true;
        return this;
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public XmlSerializer text(String str) throws IOException {
        check(false, false);
        this.indent[this.depth] = false;
        writeEscaped(str, -1);
        return this;
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public XmlSerializer text(char[] cArr, int i, int i2) throws IOException {
        text(new String(cArr, i, i2));
        return this;
    }

    private void append(char c) throws IOException {
        if (this.mPos >= 8192) {
            flushBuffer();
        }
        char[] cArr = this.mText;
        int i = this.mPos;
        this.mPos = i + 1;
        cArr[i] = c;
    }

    private void append(String str) throws IOException {
        append(str, 0, str.length());
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void setOutput(Writer writer) {
        this.writer = writer;
        int[] iArr = this.nspCounts;
        iArr[0] = 2;
        iArr[1] = 2;
        String[] strArr = this.nspStack;
        strArr[0] = "";
        strArr[1] = "";
        strArr[2] = "xml";
        strArr[3] = "http://www.w3.org/XML/1998/namespace";
        this.pending = false;
        this.auto = 0;
        this.depth = 0;
        this.unicode = false;
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public String getPrefix(String str, boolean z) {
        try {
            return getPrefix(str, false, z);
        } catch (IOException e) {
            xxf.a(e);
            return null;
        }
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public XmlSerializer endTag(String str, String str2) throws IOException {
        if (!this.pending) {
            this.depth--;
        }
        if ((str != null || this.elementStack[this.depth * 3] == null) && ((str == null || str.equals(this.elementStack[this.depth * 3])) && this.elementStack[(this.depth * 3) + 2].equals(str2))) {
            if (this.pending) {
                check(true, false);
                this.depth--;
            } else {
                if (this.indent[this.depth + 1]) {
                    append(CharInfo.S_CARRIAGERETURN);
                    append('\n');
                    appendSpace(this.depth * 2);
                }
                append("</");
                String str3 = this.elementStack[(this.depth * 3) + 1];
                if (!str3.isEmpty()) {
                    append(str3);
                    append(':');
                }
                append(str2);
                append('>');
            }
            int[] iArr = this.nspCounts;
            int i = this.depth;
            iArr[i + 1] = iArr[i];
            return this;
        }
        zba.a("</{", str, "}", str2, "> does not match start");
        return null;
    }
}
