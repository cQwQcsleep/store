package com.sun.org.apache.bcel.internal.util;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.classfile.Attribute;
import com.sun.org.apache.bcel.internal.classfile.Code;
import com.sun.org.apache.bcel.internal.classfile.CodeException;
import com.sun.org.apache.bcel.internal.classfile.ConstantPool;
import com.sun.org.apache.bcel.internal.classfile.ConstantValue;
import com.sun.org.apache.bcel.internal.classfile.ExceptionTable;
import com.sun.org.apache.bcel.internal.classfile.InnerClass;
import com.sun.org.apache.bcel.internal.classfile.InnerClasses;
import com.sun.org.apache.bcel.internal.classfile.LineNumber;
import com.sun.org.apache.bcel.internal.classfile.LineNumberTable;
import com.sun.org.apache.bcel.internal.classfile.LocalVariable;
import com.sun.org.apache.bcel.internal.classfile.LocalVariableTable;
import com.sun.org.apache.bcel.internal.classfile.SourceFile;
import com.sun.org.apache.bcel.internal.classfile.Utility;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class AttributeHTML implements Closeable {
    private int attrCount;
    private final String className;
    private final ConstantHTML constantHtml;
    private final ConstantPool constantPool;
    private final PrintWriter printWriter;

    public AttributeHTML(String str, String str2, ConstantPool constantPool, ConstantHTML constantHTML, Charset charset) throws UnsupportedEncodingException, FileNotFoundException {
        this.className = str2;
        this.constantPool = constantPool;
        this.constantHtml = constantHTML;
        PrintWriter printWriter = new PrintWriter(str + str2 + "_attributes.html", charset.name());
        this.printWriter = printWriter;
        printWriter.print("<HTML><head><meta charset=\"");
        printWriter.print(charset.name());
        printWriter.println("\"></head>");
        printWriter.println("<BODY BGCOLOR=\"#C0C0C0\"><TABLE BORDER=0>");
    }

    public static /* synthetic */ void a(AttributeHTML attributeHTML, int i, LocalVariable localVariable) {
        attributeHTML.getClass();
        String strSignatureToString = Utility.signatureToString(attributeHTML.constantPool.getConstantUtf8(localVariable.getSignatureIndex()).getBytes(), false);
        int startPC = localVariable.getStartPC();
        int length = localVariable.getLength() + startPC;
        attributeHTML.printWriter.println("<LI>" + Class2HTML.referenceType(strSignatureToString) + "&nbsp;<B>" + localVariable.getName() + "</B> in slot %" + localVariable.getIndex() + "<BR>Valid from lines <A HREF=\"" + attributeHTML.className + "_code.html#code" + i + "@" + startPC + "\" TARGET=Code>" + startPC + "</A> to <A HREF=\"" + attributeHTML.className + "_code.html#code" + i + "@" + length + "\" TARGET=Code>" + length + "</A></LI>");
    }

    private String codeLink(int i, int i2) {
        return "<A HREF=\"" + this.className + "_code.html#code" + i2 + "@" + i + "\" TARGET=Code>" + i + "</A>";
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.printWriter.println("</TABLE></BODY></HTML>");
        this.printWriter.close();
    }

    public void writeAttribute(Attribute attribute, String str, final int i) {
        byte tag = attribute.getTag();
        if (tag == -1) {
            return;
        }
        int i2 = this.attrCount + 1;
        this.attrCount = i2;
        int i3 = i2 % 2;
        PrintWriter printWriter = this.printWriter;
        if (i3 == 0) {
            printWriter.print("<TR BGCOLOR=\"#C0C0C0\"><TD>");
        } else {
            printWriter.print("<TR BGCOLOR=\"#A0A0A0\"><TD>");
        }
        this.printWriter.println("<H4><A NAME=\"" + str + "\">" + this.attrCount + " " + Const.getAttributeName(tag) + "</A></H4>");
        int i4 = 0;
        switch (tag) {
            case 0:
                int sourceFileIndex = ((SourceFile) attribute).getSourceFileIndex();
                this.printWriter.print("<UL><LI><A HREF=\"" + this.className + "_cp.html#cp" + sourceFileIndex + "\" TARGET=\"ConstantPool\">Source file index(" + sourceFileIndex + ")</A></UL>\n");
                break;
            case 1:
                int constantValueIndex = ((ConstantValue) attribute).getConstantValueIndex();
                this.printWriter.print("<UL><LI><A HREF=\"" + this.className + "_cp.html#cp" + constantValueIndex + "\" TARGET=\"ConstantPool\">Constant value index(" + constantValueIndex + ")</A></UL>\n");
                break;
            case 2:
                Code code = (Code) attribute;
                this.printWriter.print("<UL><LI>Maximum stack size = " + code.getMaxStack() + "</LI>\n<LI>Number of local variables = " + code.getMaxLocals() + "</LI>\n<LI><A HREF=\"" + this.className + "_code.html#method" + i + "\" TARGET=Code>Byte code</A></LI></UL>\n");
                CodeException[] exceptionTable = code.getExceptionTable();
                if (exceptionTable.length > 0) {
                    this.printWriter.print("<P><B>Exceptions handled</B><UL>");
                    int length = exceptionTable.length;
                    while (i4 < length) {
                        CodeException codeException = exceptionTable[i4];
                        int catchType = codeException.getCatchType();
                        this.printWriter.print("<LI>");
                        PrintWriter printWriter2 = this.printWriter;
                        if (catchType != 0) {
                            printWriter2.print(this.constantHtml.referenceConstant(catchType));
                        } else {
                            printWriter2.print("Any Exception");
                        }
                        this.printWriter.print("<BR>(Ranging from lines " + codeLink(codeException.getStartPC(), i) + " to " + codeLink(codeException.getEndPC(), i) + ", handled at line " + codeLink(codeException.getHandlerPC(), i) + ")</LI>");
                        i4++;
                    }
                    this.printWriter.print("</UL>");
                }
                break;
            case 3:
                int[] exceptionIndexTable = ((ExceptionTable) attribute).getExceptionIndexTable();
                this.printWriter.print("<UL>");
                int length2 = exceptionIndexTable.length;
                while (i4 < length2) {
                    int i5 = exceptionIndexTable[i4];
                    this.printWriter.print("<LI><A HREF=\"" + this.className + "_cp.html#cp" + i5 + "\" TARGET=\"ConstantPool\">Exception class index(" + i5 + ")</A>\n");
                    i4++;
                }
                this.printWriter.print("</UL>\n");
                break;
            case 4:
                LineNumber[] lineNumberTable = ((LineNumberTable) attribute).getLineNumberTable();
                this.printWriter.print("<P>");
                while (i4 < lineNumberTable.length) {
                    this.printWriter.print("(" + lineNumberTable[i4].getStartPC() + ",&nbsp;" + lineNumberTable[i4].getLineNumber() + ")");
                    if (i4 < lineNumberTable.length - 1) {
                        this.printWriter.print(", ");
                    }
                    i4++;
                }
                break;
            case 5:
                this.printWriter.print("<UL>");
                ((LocalVariableTable) attribute).forEach(new Consumer() { // from class: com.sun.org.apache.bcel.internal.util.a
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        AttributeHTML.a(this.b, i, (LocalVariable) obj);
                    }
                });
                this.printWriter.print("</UL>\n");
                break;
            case 6:
                this.printWriter.print("<UL>");
                InnerClass[] innerClasses = ((InnerClasses) attribute).getInnerClasses();
                int length3 = innerClasses.length;
                while (i4 < length3) {
                    InnerClass innerClass = innerClasses[i4];
                    int innerNameIndex = innerClass.getInnerNameIndex();
                    String bytes = innerNameIndex > 0 ? this.constantPool.getConstantUtf8(innerNameIndex).getBytes() : "&lt;anonymous&gt;";
                    String strAccessToString = Utility.accessToString(innerClass.getInnerAccessFlags());
                    this.printWriter.print("<LI><FONT COLOR=\"#FF0000\">" + strAccessToString + "</FONT> " + this.constantHtml.referenceConstant(innerClass.getInnerClassIndex()) + " in&nbsp;class " + this.constantHtml.referenceConstant(innerClass.getOuterClassIndex()) + " named " + bytes + "</LI>\n");
                    i4++;
                }
                this.printWriter.print("</UL>\n");
                break;
            default:
                this.printWriter.print("<P>" + attribute);
                break;
        }
        this.printWriter.println("</TD></TR>");
        this.printWriter.flush();
    }

    public void writeAttribute(Attribute attribute, String str) {
        writeAttribute(attribute, str, 0);
    }
}
