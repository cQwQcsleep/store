package com.reandroid.dex.smali;

import com.reandroid.dex.common.DexUtils;
import com.reandroid.dex.common.Modifier;
import com.reandroid.dex.common.RegistersTable;
import com.reandroid.dex.ins.Label;
import com.reandroid.dex.key.MethodKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.smali.formatters.SequentialLabelFactory;
import com.reandroid.utils.HexUtil;
import com.reandroid.utils.StringsUtil;
import java.io.Closeable;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliWriter implements Appendable, Closeable {
    private static final int INDENT_STEP = 4;
    private int columnNumber;
    private StringBuilder comment;
    private RegistersTable currentRegistersTable;
    private int indent;
    private boolean indentRequested;
    private int lineNumber;
    private SequentialLabelFactory sequentialLabelFactory;
    private boolean stateWritingFields;
    private boolean stateWritingInstructions;
    private boolean state_new_line;
    private Writer writer;
    private SmaliWriterSetting writerSetting;

    public SmaliWriter() {
        this.lineNumber = 1;
        this.state_new_line = true;
    }

    private void escapeCommentCharacters(StringBuilder sb, String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\n') {
                sb.append('\\');
                sb.append('n');
            } else if (cCharAt == '\t') {
                sb.append('\\');
                sb.append('t');
            } else if (cCharAt == '\r') {
                sb.append('\\');
                sb.append('r');
            } else if (cCharAt == '\b') {
                sb.append('\\');
                sb.append('b');
            } else if (cCharAt == '\f') {
                sb.append('\\');
                sb.append('f');
            } else if (cCharAt == ' ') {
                sb.append(' ');
            } else if (cCharAt < ' ' || !Character.isDefined(cCharAt)) {
                DexUtils.encodeToHexChar(sb, cCharAt);
            } else {
                sb.append(cCharAt);
            }
        }
    }

    private void flushComment() throws IOException {
        StringBuilder sb = this.comment;
        if (sb == null) {
            return;
        }
        append((CharSequence) sb.toString());
        this.comment = null;
    }

    private void flushIndent() throws IOException {
        if (this.indentRequested) {
            writeIndent();
        }
    }

    private SequentialLabelFactory getOrCreateSequentialLabelFactory() {
        SequentialLabelFactory sequentialLabelFactory = this.sequentialLabelFactory;
        SmaliWriterSetting writerSetting = getWriterSetting();
        if (writerSetting != null) {
            if (!writerSetting.isSequentialLabel()) {
                return null;
            }
            if (sequentialLabelFactory == null) {
                SequentialLabelFactory sequentialLabelFactory2 = new SequentialLabelFactory();
                this.sequentialLabelFactory = sequentialLabelFactory2;
                return sequentialLabelFactory2;
            }
        }
        return sequentialLabelFactory;
    }

    public static String toStringSafe(SmaliFormat smaliFormat) {
        if (smaliFormat == null) {
            return "# null";
        }
        StringWriter stringWriter = new StringWriter();
        SmaliWriter smaliWriter = new SmaliWriter(stringWriter);
        try {
            smaliFormat.append(smaliWriter);
            smaliWriter.close();
            return stringWriter.toString();
        } catch (IOException e) {
            return "# " + e.toString();
        }
    }

    private void write(char c) throws IOException {
        flushIndent();
        this.writer.append(c);
        this.columnNumber++;
        this.state_new_line = false;
    }

    private void writeIndent() throws IOException {
        this.indentRequested = false;
        Writer writer = this.writer;
        int i = this.indent;
        for (int i2 = 0; i2 < i; i2++) {
            writer.append(' ');
        }
    }

    public void append(float f) throws IOException {
        append((CharSequence) Float.toString(f));
        append('f');
    }

    public void appendAll(Iterator<? extends SmaliFormat> it, boolean z) throws IOException {
        boolean z2 = false;
        while (it.hasNext()) {
            if (z && z2) {
                newLine();
            }
            it.next().append(this);
            z2 = true;
        }
    }

    public void appendAllWithDoubleNewLine(Iterator<? extends SmaliFormat> it) throws IOException {
        while (it.hasNext()) {
            if (!this.state_new_line) {
                newLine();
            }
            if (this.indent == 0) {
                newLine();
            }
            it.next().append(this);
        }
    }

    public void appendAllWithIndent(Iterator<? extends SmaliFormat> it) throws IOException {
        boolean z = false;
        while (it.hasNext()) {
            if (!z) {
                indentPlus();
            }
            newLine();
            it.next().append(this);
            z = true;
        }
        if (z) {
            indentMinus();
        }
    }

    public void appendComment(String str, boolean z) {
        if (StringsUtil.isEmpty(str) || !isEnableComments()) {
            return;
        }
        if (z) {
            try {
                newLine();
            } catch (IOException unused) {
            }
        }
        StringBuilder sb = this.comment;
        if (sb == null || sb.length() == 0) {
            sb = new StringBuilder();
            this.comment = sb;
            if (this.indent != 0 || this.columnNumber != 0) {
                sb.append("    ");
            }
            sb.append('#');
            sb.append(' ');
            this.columnNumber += 2;
        } else {
            sb.append(' ');
            this.columnNumber++;
        }
        int length = sb.length();
        escapeCommentCharacters(sb, str);
        this.columnNumber += sb.length() - length;
    }

    public void appendCommentNewLine(String str) {
        appendComment(str, true);
    }

    public void appendHex(int i, long j) throws IOException {
        append((CharSequence) HexUtil.toSignedHex(j));
        if (i == 1) {
            append('t');
        } else if (i == 2) {
            append('S');
        } else if (i == 8 && ((-2147483648L) & j) != 0) {
            append('L');
        }
        appendResourceIdComment(j);
    }

    public void appendInteger(int i) throws IOException {
        append((CharSequence) Integer.toString(i));
    }

    public void appendLabelName(String str) throws IOException {
        SequentialLabelFactory sequentialLabelFactory = getSequentialLabelFactory();
        if (sequentialLabelFactory != null) {
            str = sequentialLabelFactory.get(str);
        }
        append((CharSequence) str);
    }

    public void appendModifiers(Iterator<? extends Modifier> it) throws IOException {
        while (it.hasNext()) {
            it.next().append(this);
        }
    }

    public boolean appendOptional(SmaliFormat smaliFormat, String str) throws IOException {
        if (smaliFormat == null) {
            return false;
        }
        newLine();
        appendCommentNewLine(str);
        smaliFormat.append(this);
        return true;
    }

    public void appendRegister(int i) throws IOException {
        RegistersTable currentRegistersTable = getCurrentRegistersTable();
        if (currentRegistersTable != null) {
            currentRegistersTable.getRegisterFor(i).append(this);
        } else {
            a16.a("Current registers table not set");
        }
    }

    public void appendRequired(SmaliFormat smaliFormat) throws IOException {
        if (smaliFormat != null) {
            smaliFormat.append(this);
        } else {
            a16.a("Null SmaliFormat");
        }
    }

    public void appendResourceIdComment(long j) throws IOException {
        SmaliWriterSetting writerSetting = getWriterSetting();
        if (writerSetting != null) {
            writerSetting.writeResourceIdComment(this, j);
        }
    }

    public void buildLabels(Iterator<? extends Label> it) {
        SequentialLabelFactory orCreateSequentialLabelFactory = getOrCreateSequentialLabelFactory();
        if (orCreateSequentialLabelFactory != null) {
            orCreateSequentialLabelFactory.build(it);
        }
    }

    public void clearSequentialLabels() {
        SequentialLabelFactory sequentialLabelFactory = getSequentialLabelFactory();
        if (sequentialLabelFactory != null) {
            sequentialLabelFactory.reset();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        Writer writer = this.writer;
        if (writer == null) {
            return;
        }
        flushComment();
        this.writer = null;
        writer.close();
    }

    public int getColumnNumber() {
        return this.columnNumber;
    }

    public Appendable getCommentAppender() {
        if (!isEnableComments()) {
            return null;
        }
        StringBuilder sb = this.comment;
        if (sb == null) {
            sb = new StringBuilder();
            this.comment = sb;
            if (this.indent != 0 || this.columnNumber != 0) {
                sb.append("    ");
            }
            sb.append('#');
            sb.append(' ');
            this.columnNumber += 2;
        }
        return sb;
    }

    public RegistersTable getCurrentRegistersTable() {
        return this.currentRegistersTable;
    }

    public SmaliFileNameFactory getFileNameFactory() {
        SmaliWriterSetting writerSetting = getWriterSetting();
        return writerSetting != null ? writerSetting.getFileNameFactory() : SmaliFileNameFactory.INSTANCE;
    }

    public int getLineNumber() {
        return this.lineNumber;
    }

    public SequentialLabelFactory getSequentialLabelFactory() {
        return this.sequentialLabelFactory;
    }

    public SmaliWriterSetting getWriterSetting() {
        return this.writerSetting;
    }

    public void indentMinus() {
        int i = this.indent - 4;
        this.indent = i;
        if (i < 0) {
            this.indent = 0;
        }
    }

    public void indentPlus() {
        this.indent += 4;
    }

    public void indentReset() {
        this.indent = 0;
    }

    public boolean isCommentUnicodeStrings() {
        SmaliWriterSetting writerSetting = getWriterSetting();
        if (writerSetting != null && writerSetting.isEnableComments() && writerSetting.isCommentUnicodeStrings()) {
            return this.stateWritingFields || this.stateWritingInstructions;
        }
        return false;
    }

    public boolean isEnableComments() {
        SmaliWriterSetting writerSetting = getWriterSetting();
        if (writerSetting != null) {
            return writerSetting.isEnableComments();
        }
        return true;
    }

    public boolean isLocalRegistersCount() {
        SmaliWriterSetting writerSetting = getWriterSetting();
        if (writerSetting != null) {
            return writerSetting.isLocalRegistersCount();
        }
        return true;
    }

    public void newLine(int i) throws IOException {
        if ((this.lineNumber == 1 && this.columnNumber == 0) || i == 0) {
            return;
        }
        flushComment();
        for (int i2 = 0; i2 < i; i2++) {
            this.writer.append('\n');
        }
        this.columnNumber = 0;
        this.lineNumber += i;
        this.state_new_line = true;
        this.indentRequested = true;
    }

    public void newLineDouble() throws IOException {
        newLine(2);
    }

    public void onWriteClass(TypeKey typeKey) throws IOException {
        SmaliWriterSetting writerSetting = getWriterSetting();
        if (writerSetting == null || !writerSetting.isEnableComments()) {
            return;
        }
        writerSetting.writeClassComment(this, typeKey);
    }

    public void onWriteMethod(MethodKey methodKey) throws IOException {
        SmaliWriterSetting writerSetting = getWriterSetting();
        if (writerSetting == null || !writerSetting.isEnableComments()) {
            return;
        }
        writerSetting.writeMethodComment(this, methodKey);
    }

    public void reset() {
        this.indent = 0;
        this.lineNumber = 1;
        this.columnNumber = 0;
        this.comment = null;
        this.indentRequested = false;
    }

    public void setCurrentRegistersTable(RegistersTable registersTable) {
        this.currentRegistersTable = registersTable;
        if (registersTable == null) {
            clearSequentialLabels();
        }
    }

    public void setSequentialLabelFactory(SequentialLabelFactory sequentialLabelFactory) {
        this.sequentialLabelFactory = sequentialLabelFactory;
    }

    public void setStateWritingFields(boolean z) {
        this.stateWritingFields = z;
    }

    public void setStateWritingInstructions(boolean z) {
        this.stateWritingInstructions = z;
    }

    public void setWriter(Writer writer) {
        reset();
        this.writer = writer;
    }

    public void setWriterSetting(SmaliWriterSetting smaliWriterSetting) {
        this.writerSetting = smaliWriterSetting;
    }

    public String toString() {
        return "line = " + getLineNumber() + ", column = " + getColumnNumber();
    }

    public SmaliWriter(Writer writer) {
        this();
        this.writer = writer;
    }

    public void appendResourceIdComment(int i) throws IOException {
        SmaliWriterSetting writerSetting = getWriterSetting();
        if (writerSetting != null) {
            writerSetting.writeResourceIdComment(this, i);
        }
    }

    @Override // java.lang.Appendable
    public SmaliWriter append(CharSequence charSequence) throws IOException {
        write(charSequence, 0, charSequence.length());
        return this;
    }

    public boolean appendOptional(SmaliFormat smaliFormat) throws IOException {
        if (smaliFormat == null) {
            return false;
        }
        smaliFormat.append(this);
        return true;
    }

    @Override // java.lang.Appendable
    public SmaliWriter append(CharSequence charSequence, int i, int i2) throws IOException {
        write(charSequence, i, i2);
        return this;
    }

    @Override // java.lang.Appendable
    public SmaliWriter append(char c) throws IOException {
        write(c);
        return this;
    }

    private void write(CharSequence charSequence, int i, int i2) throws IOException {
        while (i < i2) {
            write(charSequence.charAt(i));
            i++;
        }
    }

    public void append(double d) throws IOException {
        append((CharSequence) Double.toString(d));
    }

    public void appendAll(Iterator<? extends SmaliFormat> it) throws IOException {
        appendAll(it, true);
    }

    public static String toString(SmaliFormat smaliFormat) throws IOException {
        return toString(new SmaliWriter(), smaliFormat);
    }

    public static String toString(SmaliWriter smaliWriter, SmaliFormat smaliFormat) throws IOException {
        StringWriter stringWriter = new StringWriter();
        smaliWriter.setWriter(stringWriter);
        smaliFormat.append(smaliWriter);
        smaliWriter.close();
        return stringWriter.toString();
    }

    public void newLine() throws IOException {
        newLine(1);
    }

    public void appendHex(short s) throws IOException {
        append((CharSequence) HexUtil.toSignedHex((int) s));
        append('S');
    }

    public void appendHex(int i) throws IOException {
        append((CharSequence) HexUtil.toSignedHex(i));
        appendResourceIdComment(i);
    }

    public void appendHex(long j) throws IOException {
        append((CharSequence) HexUtil.toSignedHex(j));
        append('L');
        appendResourceIdComment(j);
    }

    public void appendHex(byte b) throws IOException {
        append((CharSequence) HexUtil.toSignedHex((int) b));
        append('t');
    }

    public void appendComment(String str) {
        appendComment(str, false);
    }
}
