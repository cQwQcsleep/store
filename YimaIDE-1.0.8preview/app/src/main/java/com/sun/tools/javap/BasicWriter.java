package com.sun.tools.javap;

import com.intellij.psi.PsiKeyword;
import com.sun.tools.classfile.AttributeException;
import com.sun.tools.classfile.ConstantPoolException;
import com.sun.tools.classfile.DescriptorException;
import java.io.PrintWriter;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class BasicWriter {
    private LineWriter lineWriter;
    protected Messages messages;
    private PrintWriter out;
    private String[] spaces = new String[80];

    public BasicWriter(Context context) {
        this.lineWriter = LineWriter.instance(context);
        this.out = (PrintWriter) context.get(PrintWriter.class);
        Messages messages = (Messages) context.get(Messages.class);
        this.messages = messages;
        if (messages != null) {
            return;
        }
        x1f.a();
        throw null;
    }

    public void indent(int i) {
        this.lineWriter.indent(i);
    }

    public void print(Object obj) {
        this.lineWriter.print(obj == null ? null : obj.toString());
    }

    public void println(Object obj) {
        this.lineWriter.print(obj == null ? null : obj.toString());
        this.lineWriter.println();
    }

    public String report(AttributeException attributeException) {
        this.out.println("Error: " + attributeException.getMessage());
        return "???";
    }

    public void setPendingNewline(boolean z) {
        this.lineWriter.pendingNewline = z;
    }

    public String space(int i) {
        String str;
        String[] strArr = this.spaces;
        if (i < strArr.length && (str = strArr[i]) != null) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(" ");
        }
        String string = sb.toString();
        String[] strArr2 = this.spaces;
        if (i < strArr2.length) {
            strArr2[i] = string;
        }
        return string;
    }

    public void tab() {
        this.lineWriter.tab();
    }

    public static class LineWriter {
        private final StringBuilder buffer;
        private int indentCount;
        private final int indentWidth;
        private final PrintWriter out;
        private boolean pendingNewline;
        private int pendingSpaces;
        private final int tabColumn;

        public LineWriter(Context context) {
            context.put(LineWriter.class, this);
            Options optionsInstance = Options.instance(context);
            this.indentWidth = optionsInstance.indentWidth;
            this.tabColumn = optionsInstance.tabColumn;
            this.out = (PrintWriter) context.get(PrintWriter.class);
            this.buffer = new StringBuilder();
        }

        private void indent() {
            this.pendingSpaces += this.indentCount * this.indentWidth;
        }

        public static LineWriter instance(Context context) {
            LineWriter lineWriter = (LineWriter) context.get(LineWriter.class);
            return lineWriter == null ? new LineWriter(context) : lineWriter;
        }

        public void print(String str) {
            if (this.pendingNewline) {
                println();
                this.pendingNewline = false;
            }
            if (str == null) {
                str = PsiKeyword.NULL;
            }
            for (int i = 0; i < str.length(); i++) {
                char cCharAt = str.charAt(i);
                if (cCharAt == '\n') {
                    println();
                } else if (cCharAt != ' ') {
                    if (this.buffer.length() == 0) {
                        indent();
                    }
                    if (this.pendingSpaces > 0) {
                        for (int i2 = 0; i2 < this.pendingSpaces; i2++) {
                            this.buffer.append(' ');
                        }
                        this.pendingSpaces = 0;
                    }
                    this.buffer.append(cCharAt);
                } else {
                    this.pendingSpaces++;
                }
            }
        }

        public void println() {
            this.pendingSpaces = 0;
            this.out.println(this.buffer);
            this.buffer.setLength(0);
        }

        public void tab() {
            int i = (this.indentCount * this.indentWidth) + this.tabColumn;
            this.pendingSpaces += i <= this.buffer.length() ? 1 : i - this.buffer.length();
        }

        public void indent(int i) {
            this.indentCount += i;
        }
    }

    public void print(String str) {
        this.lineWriter.print(str);
    }

    public void println(String str) {
        this.lineWriter.print(str);
        this.lineWriter.println();
    }

    public void println() {
        this.lineWriter.println();
    }

    public String report(ConstantPoolException constantPoolException) {
        this.out.println("Error: " + constantPoolException.getMessage());
        return "???";
    }

    public String report(DescriptorException descriptorException) {
        this.out.println("Error: " + descriptorException.getMessage());
        return "???";
    }

    public String report(String str) {
        this.out.println("Error: " + str);
        return "???";
    }
}
