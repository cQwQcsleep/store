package com.sun.org.apache.bcel.internal.util;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.classfile.Constant;
import com.sun.org.apache.bcel.internal.classfile.ConstantClass;
import com.sun.org.apache.bcel.internal.classfile.ConstantFieldref;
import com.sun.org.apache.bcel.internal.classfile.ConstantInterfaceMethodref;
import com.sun.org.apache.bcel.internal.classfile.ConstantMethodref;
import com.sun.org.apache.bcel.internal.classfile.ConstantNameAndType;
import com.sun.org.apache.bcel.internal.classfile.ConstantPool;
import com.sun.org.apache.bcel.internal.classfile.ConstantString;
import com.sun.org.apache.bcel.internal.classfile.Method;
import com.sun.org.apache.bcel.internal.classfile.Utility;
import com.sun.org.apache.xalan.internal.templates.Constants;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class ConstantHTML {
    private final String className;
    private final String classPackage;
    private final ConstantPool constantPool;
    private final String[] constantRef;
    private final Constant[] constants;
    private final Method[] methods;
    private final PrintWriter printWriter;

    public ConstantHTML(String str, String str2, String str3, Method[] methodArr, ConstantPool constantPool, Charset charset) throws UnsupportedEncodingException, FileNotFoundException {
        this.className = str2;
        this.classPackage = str3;
        this.constantPool = constantPool;
        this.methods = methodArr;
        Constant[] constantPool2 = constantPool.getConstantPool();
        this.constants = constantPool2;
        PrintWriter printWriter = new PrintWriter(str + str2 + "_cp.html", charset.name());
        try {
            this.printWriter = printWriter;
            String[] strArr = new String[constantPool2.length];
            this.constantRef = strArr;
            strArr[0] = "&lt;unknown&gt;";
            printWriter.print("<HTML><head><meta charset=\"");
            printWriter.print(charset.name());
            printWriter.println("\"></head>");
            printWriter.println("<BODY BGCOLOR=\"#C0C0C0\"><TABLE BORDER=0>");
            for (int i = 1; i < this.constants.length; i++) {
                int i2 = i % 2;
                PrintWriter printWriter2 = this.printWriter;
                if (i2 == 0) {
                    printWriter2.print("<TR BGCOLOR=\"#C0C0C0\"><TD>");
                } else {
                    printWriter2.print("<TR BGCOLOR=\"#A0A0A0\"><TD>");
                }
                if (this.constants[i] != null) {
                    writeConstant(i);
                }
                this.printWriter.print("</TD></TR>\n");
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

    private int getMethodNumber(String str) {
        for (int i = 0; i < this.methods.length; i++) {
            if ((this.methods[i].getName() + this.methods[i].getSignature()).equals(str)) {
                return i;
            }
        }
        return -1;
    }

    private void writeConstant(int i) {
        int classIndex;
        int nameAndTypeIndex;
        byte tag = this.constants[i].getTag();
        this.printWriter.println("<H4> <A NAME=cp" + i + ">" + i + "</A> " + Const.getConstantName(tag) + "</H4>");
        switch (tag) {
            case 7:
                int nameIndex = ((ConstantClass) this.constantPool.getConstant(i, (byte) 7, ConstantClass.class)).getNameIndex();
                String strConstantToString = this.constantPool.constantToString(i, tag);
                String strCompactClassName = Utility.compactClassName(Utility.compactClassName(strConstantToString), this.classPackage + Constants.ATTRVAL_THIS, true);
                this.constantRef[i] = "<A HREF=\"" + this.className + "_cp.html#cp" + i + "\" TARGET=ConstantPool>" + strCompactClassName + "</A>";
                this.printWriter.println("<P><TT>" + ("<A HREF=\"" + strConstantToString + ".html\" TARGET=_top>" + strCompactClassName + "</A>") + "</TT><UL><LI><A HREF=\"#cp" + nameIndex + "\">Name index(" + nameIndex + ")</A></UL>\n");
                break;
            case 8:
                int stringIndex = ((ConstantString) this.constantPool.getConstant(i, (byte) 8, ConstantString.class)).getStringIndex();
                this.printWriter.println("<P><TT>" + Class2HTML.toHTML(this.constantPool.constantToString(i, tag)) + "</TT><UL><LI><A HREF=\"#cp" + stringIndex + "\">Name index(" + stringIndex + ")</A></UL>\n");
                break;
            case 9:
                ConstantFieldref constantFieldref = (ConstantFieldref) this.constantPool.getConstant(i, (byte) 9, ConstantFieldref.class);
                int classIndex2 = constantFieldref.getClassIndex();
                int nameAndTypeIndex2 = constantFieldref.getNameAndTypeIndex();
                String strConstantToString2 = this.constantPool.constantToString(classIndex2, (byte) 7);
                String strCompactClassName2 = Utility.compactClassName(Utility.compactClassName(strConstantToString2), this.classPackage + Constants.ATTRVAL_THIS, true);
                String strConstantToString3 = this.constantPool.constantToString(nameAndTypeIndex2, (byte) 12);
                String str = strConstantToString2.equals(this.className) ? "<A HREF=\"" + strConstantToString2 + "_methods.html#field" + strConstantToString3 + "\" TARGET=Methods>" + strConstantToString3 + "</A>" : "<A HREF=\"" + strConstantToString2 + ".html\" TARGET=_top>" + strCompactClassName2 + "</A>." + strConstantToString3 + "\n";
                this.constantRef[i] = "<A HREF=\"" + this.className + "_cp.html#cp" + classIndex2 + "\" TARGET=Constants>" + strCompactClassName2 + "</A>.<A HREF=\"" + this.className + "_cp.html#cp" + i + "\" TARGET=ConstantPool>" + strConstantToString3 + "</A>";
                PrintWriter printWriter = this.printWriter;
                StringBuilder sb = new StringBuilder("<P><TT>");
                sb.append(str);
                sb.append("</TT><BR>\n<UL><LI><A HREF=\"#cp");
                sb.append(classIndex2);
                sb.append("\">Class(");
                sb.append(classIndex2);
                sb.append(")</A><BR>\n<LI><A HREF=\"#cp");
                sb.append(nameAndTypeIndex2);
                sb.append("\">NameAndType(");
                sb.append(nameAndTypeIndex2);
                sb.append(")</A></UL>");
                printWriter.println(sb.toString());
                break;
            case 10:
            case 11:
                ConstantPool constantPool = this.constantPool;
                if (tag == 10) {
                    ConstantMethodref constantMethodref = (ConstantMethodref) constantPool.getConstant(i, (byte) 10, ConstantMethodref.class);
                    classIndex = constantMethodref.getClassIndex();
                    nameAndTypeIndex = constantMethodref.getNameAndTypeIndex();
                } else {
                    ConstantInterfaceMethodref constantInterfaceMethodref = (ConstantInterfaceMethodref) constantPool.getConstant(i, (byte) 11, ConstantInterfaceMethodref.class);
                    classIndex = constantInterfaceMethodref.getClassIndex();
                    nameAndTypeIndex = constantInterfaceMethodref.getNameAndTypeIndex();
                }
                String strConstantToString4 = this.constantPool.constantToString(nameAndTypeIndex, (byte) 12);
                String html = Class2HTML.toHTML(strConstantToString4);
                String strConstantToString5 = this.constantPool.constantToString(classIndex, (byte) 7);
                String strCompactClassName3 = Utility.compactClassName(Utility.compactClassName(strConstantToString5), this.classPackage + Constants.ATTRVAL_THIS, true);
                String strConstantToString6 = this.constantPool.constantToString(((ConstantNameAndType) this.constantPool.getConstant(nameAndTypeIndex, (byte) 12, ConstantNameAndType.class)).getSignatureIndex(), (byte) 1);
                String[] strArrMethodSignatureArgumentTypes = Utility.methodSignatureArgumentTypes(strConstantToString6, false);
                String strReferenceType = Class2HTML.referenceType(Utility.methodSignatureReturnType(strConstantToString6, false));
                int i2 = nameAndTypeIndex;
                StringBuilder sb2 = new StringBuilder("(");
                int i3 = classIndex;
                for (int i4 = 0; i4 < strArrMethodSignatureArgumentTypes.length; i4++) {
                    sb2.append(Class2HTML.referenceType(strArrMethodSignatureArgumentTypes[i4]));
                    if (i4 < strArrMethodSignatureArgumentTypes.length - 1) {
                        sb2.append(",&nbsp;");
                    }
                }
                sb2.append(")");
                String string = sb2.toString();
                String str2 = strConstantToString5.equals(this.className) ? "<A HREF=\"" + this.className + "_code.html#method" + getMethodNumber(strConstantToString4 + strConstantToString6) + "\" TARGET=Code>" + html + "</A>" : "<A HREF=\"" + strConstantToString5 + ".html\" TARGET=_top>" + strCompactClassName3 + "</A>." + html;
                this.constantRef[i] = strReferenceType + "&nbsp;<A HREF=\"" + this.className + "_cp.html#cp" + i3 + "\" TARGET=Constants>" + strCompactClassName3 + "</A>.<A HREF=\"" + this.className + "_cp.html#cp" + i + "\" TARGET=ConstantPool>" + html + "</A>&nbsp;" + string;
                PrintWriter printWriter2 = this.printWriter;
                StringBuilder sb3 = new StringBuilder("<P><TT>");
                sb3.append(strReferenceType);
                sb3.append("&nbsp;");
                sb3.append(str2);
                sb3.append(string);
                sb3.append("&nbsp;</TT>\n<UL><LI><A HREF=\"#cp");
                sb3.append(i3);
                sb3.append("\">Class index(");
                sb3.append(i3);
                sb3.append(")</A>\n<LI><A HREF=\"#cp");
                sb3.append(i2);
                sb3.append("\">NameAndType index(");
                sb3.append(i2);
                sb3.append(")</A></UL>");
                printWriter2.println(sb3.toString());
                break;
            case 12:
                ConstantNameAndType constantNameAndType = (ConstantNameAndType) this.constantPool.getConstant(i, (byte) 12, ConstantNameAndType.class);
                int nameIndex2 = constantNameAndType.getNameIndex();
                int signatureIndex = constantNameAndType.getSignatureIndex();
                this.printWriter.println("<P><TT>" + Class2HTML.toHTML(this.constantPool.constantToString(i, tag)) + "</TT><UL><LI><A HREF=\"#cp" + nameIndex2 + "\">Name index(" + nameIndex2 + ")</A>\n<LI><A HREF=\"#cp" + signatureIndex + "\">Signature index(" + signatureIndex + ")</A></UL>\n");
                break;
            default:
                this.printWriter.println("<P><TT>" + Class2HTML.toHTML(this.constantPool.constantToString(i, tag)) + "</TT>\n");
                break;
        }
    }

    public String referenceConstant(int i) {
        return this.constantRef[i];
    }
}
