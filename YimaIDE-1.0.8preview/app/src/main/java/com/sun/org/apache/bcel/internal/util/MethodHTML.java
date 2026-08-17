package com.sun.org.apache.bcel.internal.util;

import com.sun.org.apache.bcel.internal.classfile.Attribute;
import com.sun.org.apache.bcel.internal.classfile.Code;
import com.sun.org.apache.bcel.internal.classfile.ExceptionTable;
import com.sun.org.apache.bcel.internal.classfile.Field;
import com.sun.org.apache.bcel.internal.classfile.Method;
import com.sun.org.apache.bcel.internal.classfile.Utility;
import com.sun.org.apache.xalan.internal.templates.Constants;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class MethodHTML {
    private final AttributeHTML attributeHtml;
    private final String className;
    private final ConstantHTML constantHtml;
    private final PrintWriter printWriter;

    public MethodHTML(String str, String str2, Method[] methodArr, Field[] fieldArr, ConstantHTML constantHTML, AttributeHTML attributeHTML, Charset charset) throws UnsupportedEncodingException, FileNotFoundException {
        this.className = str2;
        this.attributeHtml = attributeHTML;
        this.constantHtml = constantHTML;
        PrintWriter printWriter = new PrintWriter(str + str2 + "_methods.html", charset.name());
        try {
            this.printWriter = printWriter;
            printWriter.print("<HTML><head><meta charset=\"");
            printWriter.print(charset.name());
            printWriter.println("\"></head>");
            printWriter.println("<BODY BGCOLOR=\"#C0C0C0\"><TABLE BORDER=0>");
            printWriter.println("<TR><TH ALIGN=LEFT>Access&nbsp;flags</TH><TH ALIGN=LEFT>Type</TH><TH ALIGN=LEFT>Field&nbsp;name</TH></TR>");
            for (Field field : fieldArr) {
                writeField(field);
            }
            this.printWriter.println("</TABLE>");
            this.printWriter.println("<TABLE BORDER=0><TR><TH ALIGN=LEFT>Access&nbsp;flags</TH><TH ALIGN=LEFT>Return&nbsp;type</TH><TH ALIGN=LEFT>Method&nbsp;name</TH><TH ALIGN=LEFT>Arguments</TH></TR>");
            for (int i = 0; i < methodArr.length; i++) {
                writeMethod(methodArr[i], i);
            }
            this.printWriter.println("</TABLE></BODY></HTML>");
            printWriter.close();
        } catch (Throwable th) {
            try {
                printWriter.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private void writeField(Field field) {
        String strSignatureToString = Utility.signatureToString(field.getSignature());
        String name = field.getName();
        String strReplace = Utility.replace(Utility.accessToString(field.getAccessFlags()), " ", "&nbsp;");
        this.printWriter.print("<TR><TD><FONT COLOR=\"#FF0000\">" + strReplace + "</FONT></TD>\n<TD>" + Class2HTML.referenceType(strSignatureToString) + "</TD><TD><A NAME=\"field" + name + "\">" + name + "</A></TD>");
        Attribute[] attributes = field.getAttributes();
        for (int i = 0; i < attributes.length; i++) {
            this.attributeHtml.writeAttribute(attributes[i], name + "@" + i);
        }
        for (int i2 = 0; i2 < attributes.length; i2++) {
            if (attributes[i2].getTag() == 1) {
                String string = attributes[i2].toString();
                this.printWriter.print("<TD>= <A HREF=\"" + this.className + "_attributes.html#" + name + "@" + i2 + "\" TARGET=\"Attributes\">" + string + "</TD>\n");
                break;
            }
        }
        this.printWriter.println("</TR>");
    }

    private void writeMethod(Method method, int i) {
        PrintWriter printWriter;
        PrintWriter printWriter2;
        String signature = method.getSignature();
        String[] strArrMethodSignatureArgumentTypes = Utility.methodSignatureArgumentTypes(signature, false);
        String strMethodSignatureReturnType = Utility.methodSignatureReturnType(signature, false);
        String name = method.getName();
        String strAccessToString = Utility.accessToString(method.getAccessFlags());
        Attribute[] attributes = method.getAttributes();
        String strReplace = Utility.replace(strAccessToString, " ", "&nbsp;");
        String html = Class2HTML.toHTML(name);
        this.printWriter.print("<TR VALIGN=TOP><TD><FONT COLOR=\"#FF0000\"><A NAME=method" + i + ">" + strReplace + "</A></FONT></TD>");
        this.printWriter.print("<TD>" + Class2HTML.referenceType(strMethodSignatureReturnType) + "</TD><TD><A HREF=" + this.className + "_code.html#method" + i + " TARGET=Code>" + html + "</A></TD>\n<TD>(");
        int i2 = 0;
        while (true) {
            int length = strArrMethodSignatureArgumentTypes.length;
            printWriter = this.printWriter;
            if (i2 >= length) {
                break;
            }
            printWriter.print(Class2HTML.referenceType(strArrMethodSignatureArgumentTypes[i2]));
            if (i2 < strArrMethodSignatureArgumentTypes.length - 1) {
                this.printWriter.print(", ");
            }
            i2++;
        }
        printWriter.print(")</TD></TR>");
        for (int i3 = 0; i3 < attributes.length; i3++) {
            this.attributeHtml.writeAttribute(attributes[i3], Constants.ATTRNAME_OUTPUT_METHOD + i + "@" + i3, i);
            byte tag = attributes[i3].getTag();
            if (tag == 3) {
                this.printWriter.print("<TR VALIGN=TOP><TD COLSPAN=2></TD><TH ALIGN=LEFT>throws</TH><TD>");
                int[] exceptionIndexTable = ((ExceptionTable) attributes[i3]).getExceptionIndexTable();
                int i4 = 0;
                while (true) {
                    int length2 = exceptionIndexTable.length;
                    printWriter2 = this.printWriter;
                    if (i4 >= length2) {
                        break;
                    }
                    printWriter2.print(this.constantHtml.referenceConstant(exceptionIndexTable[i4]));
                    if (i4 < exceptionIndexTable.length - 1) {
                        this.printWriter.print(", ");
                    }
                    i4++;
                }
                printWriter2.println("</TD></TR>");
            } else if (tag == 2) {
                Attribute[] attributes2 = ((Code) attributes[i3]).getAttributes();
                for (int i5 = 0; i5 < attributes2.length; i5++) {
                    this.attributeHtml.writeAttribute(attributes2[i5], Constants.ATTRNAME_OUTPUT_METHOD + i + "@" + i3 + "@" + i5, i);
                }
            }
        }
    }
}
