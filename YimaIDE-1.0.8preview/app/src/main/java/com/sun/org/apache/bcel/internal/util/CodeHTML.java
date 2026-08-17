package com.sun.org.apache.bcel.internal.util;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.classfile.Attribute;
import com.sun.org.apache.bcel.internal.classfile.Code;
import com.sun.org.apache.bcel.internal.classfile.CodeException;
import com.sun.org.apache.bcel.internal.classfile.ConstantFieldref;
import com.sun.org.apache.bcel.internal.classfile.ConstantInterfaceMethodref;
import com.sun.org.apache.bcel.internal.classfile.ConstantInvokeDynamic;
import com.sun.org.apache.bcel.internal.classfile.ConstantMethodref;
import com.sun.org.apache.bcel.internal.classfile.ConstantNameAndType;
import com.sun.org.apache.bcel.internal.classfile.ConstantPool;
import com.sun.org.apache.bcel.internal.classfile.LocalVariable;
import com.sun.org.apache.bcel.internal.classfile.LocalVariableTable;
import com.sun.org.apache.bcel.internal.classfile.Method;
import com.sun.org.apache.bcel.internal.classfile.Utility;
import com.sun.org.apache.xalan.internal.templates.Constants;
import defpackage.ena;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.BitSet;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class CodeHTML {
    private static boolean wide;
    private final String className;
    private final ConstantHTML constantHtml;
    private final ConstantPool constantPool;
    private BitSet gotoSet;
    private final PrintWriter printWriter;

    public CodeHTML(String str, String str2, Method[] methodArr, ConstantPool constantPool, ConstantHTML constantHTML, Charset charset) throws IOException {
        this.className = str2;
        this.constantPool = constantPool;
        this.constantHtml = constantHTML;
        PrintWriter printWriter = new PrintWriter(str + str2 + "_code.html", charset.name());
        try {
            this.printWriter = printWriter;
            printWriter.print("<HTML><head><meta charset=\"");
            printWriter.print(charset.name());
            printWriter.println("\"></head>");
            printWriter.println("<BODY BGCOLOR=\"#C0C0C0\">");
            for (int i = 0; i < methodArr.length; i++) {
                writeMethod(methodArr[i], i);
            }
            this.printWriter.println("</BODY></HTML>");
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

    public static /* synthetic */ void a(CodeHTML codeHTML, LocalVariable localVariable) {
        codeHTML.getClass();
        int startPC = localVariable.getStartPC();
        codeHTML.gotoSet.set(startPC);
        codeHTML.gotoSet.set(startPC + localVariable.getLength());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:80:0x0367  */
    /* JADX WARN: Code duplicated, block: B:84:0x038e  */
    /* JADX WARN: Code duplicated, block: B:86:0x0399  */
    /* JADX WARN: Code duplicated, block: B:90:0x0402  */
    private String codeToHTML(ByteSequence byteSequence, int i) throws IOException {
        int i2;
        int i3;
        int unsignedByte;
        short s;
        int unsignedByte2;
        int nameAndTypeIndex;
        String strReferenceClass;
        short unsignedByte3 = (short) byteSequence.readUnsignedByte();
        StringBuilder sb = new StringBuilder(256);
        sb.append("<TT>");
        sb.append(Const.getOpcodeName(unsignedByte3));
        sb.append("</TT></TD><TD>");
        if (unsignedByte3 == 170 || unsignedByte3 == 171) {
            int index = byteSequence.getIndex() % 4;
            i2 = index == 0 ? 0 : 4 - index;
            for (int i4 = 0; i4 < i2; i4++) {
                byteSequence.readByte();
            }
            i3 = byteSequence.readInt();
        } else {
            i2 = 0;
            i3 = 0;
        }
        if (unsignedByte3 == 132) {
            if (wide) {
                unsignedByte = byteSequence.readShort();
                s = byteSequence.readShort();
                wide = false;
            } else {
                unsignedByte = byteSequence.readUnsignedByte();
                s = byteSequence.readByte();
            }
            sb.append("%");
            sb.append(unsignedByte);
            sb.append(" ");
            sb.append((int) s);
        } else if (unsignedByte3 != 192 && unsignedByte3 != 193) {
            switch (unsignedByte3) {
                case 18:
                    int unsignedByte4 = byteSequence.readUnsignedByte();
                    sb.append("<A HREF=\"");
                    sb.append(this.className);
                    sb.append("_cp.html#cp");
                    sb.append(unsignedByte4);
                    sb.append("\" TARGET=\"ConstantPool\">");
                    ConstantPool constantPool = this.constantPool;
                    sb.append(Class2HTML.toHTML(constantPool.constantToString(unsignedByte4, constantPool.getConstant(unsignedByte4).getTag())));
                    sb.append("</a>");
                    break;
                case 19:
                case 20:
                    short s2 = byteSequence.readShort();
                    sb.append("<A HREF=\"");
                    sb.append(this.className);
                    sb.append("_cp.html#cp");
                    sb.append((int) s2);
                    sb.append("\" TARGET=\"ConstantPool\">");
                    ConstantPool constantPool2 = this.constantPool;
                    sb.append(Class2HTML.toHTML(constantPool2.constantToString(s2, constantPool2.getConstant(s2).getTag())));
                    sb.append("</a>");
                    break;
                default:
                    switch (unsignedByte3) {
                        case 54:
                        case 55:
                        case 56:
                        case 57:
                        case 58:
                            break;
                        default:
                            int i5 = i2;
                            int i6 = i3;
                            switch (unsignedByte3) {
                                case 153:
                                case 154:
                                case 155:
                                case 156:
                                case 157:
                                case 158:
                                case 159:
                                case 160:
                                case 161:
                                case 162:
                                case 163:
                                case 164:
                                case 165:
                                case 166:
                                case 167:
                                case 168:
                                    int index2 = (byteSequence.getIndex() + byteSequence.readShort()) - 1;
                                    sb.append("<A HREF=\"#code");
                                    sb.append(i);
                                    sb.append("@");
                                    sb.append(index2);
                                    sb.append("\">");
                                    sb.append(index2);
                                    sb.append("</A>");
                                    break;
                                case 169:
                                    if (wide) {
                                        unsignedByte2 = byteSequence.readShort();
                                        wide = false;
                                    } else {
                                        unsignedByte2 = byteSequence.readUnsignedByte();
                                    }
                                    sb.append("%");
                                    sb.append(unsignedByte2);
                                    break;
                                case 170:
                                    int i7 = byteSequence.readInt();
                                    int i8 = byteSequence.readInt();
                                    int index3 = ((byteSequence.getIndex() - 12) - i5) - 1;
                                    int i9 = i6 + index3;
                                    sb.append("<TABLE BORDER=1><TR>");
                                    int i10 = (i8 - i7) + 1;
                                    int[] iArr = new int[i10];
                                    int i11 = 0;
                                    while (i11 < i10) {
                                        iArr[i11] = index3 + byteSequence.readInt();
                                        sb.append("<TH>");
                                        int i12 = i7;
                                        sb.append(i12 + i11);
                                        sb.append("</TH>");
                                        i11++;
                                        i7 = i12;
                                    }
                                    sb.append("<TH>default</TH></TR>\n<TR>");
                                    for (int i13 = 0; i13 < i10; i13++) {
                                        int i14 = iArr[i13];
                                        sb.append("<TD><A HREF=\"#code");
                                        sb.append(i);
                                        sb.append("@");
                                        sb.append(i14);
                                        sb.append("\">");
                                        sb.append(i14);
                                        sb.append("</A></TD>");
                                    }
                                    sb.append("<TD><A HREF=\"#code");
                                    sb.append(i);
                                    sb.append("@");
                                    sb.append(i9);
                                    sb.append("\">");
                                    sb.append(i9);
                                    sb.append("</A></TD></TR>\n</TABLE>\n");
                                    break;
                                case 171:
                                    int i15 = byteSequence.readInt();
                                    int index4 = ((byteSequence.getIndex() - 8) - i5) - 1;
                                    int[] iArr2 = new int[i15];
                                    int i16 = i6 + index4;
                                    sb.append("<TABLE BORDER=1><TR>");
                                    for (int i17 = 0; i17 < i15; i17++) {
                                        int i18 = byteSequence.readInt();
                                        iArr2[i17] = byteSequence.readInt() + index4;
                                        sb.append("<TH>");
                                        sb.append(i18);
                                        sb.append("</TH>");
                                    }
                                    sb.append("<TH>default</TH></TR>\n<TR>");
                                    for (int i19 = 0; i19 < i15; i19++) {
                                        sb.append("<TD><A HREF=\"#code");
                                        sb.append(i);
                                        sb.append("@");
                                        sb.append(iArr2[i19]);
                                        sb.append("\">");
                                        sb.append(iArr2[i19]);
                                        sb.append("</A></TD>");
                                    }
                                    sb.append("<TD><A HREF=\"#code");
                                    sb.append(i);
                                    sb.append("@");
                                    sb.append(i16);
                                    sb.append("\">");
                                    sb.append(i16);
                                    sb.append("</A></TD></TR>\n</TABLE>\n");
                                    break;
                                default:
                                    switch (unsignedByte3) {
                                        case 178:
                                        case 179:
                                        case 180:
                                        case 181:
                                            ConstantFieldref constantFieldref = (ConstantFieldref) this.constantPool.getConstant(byteSequence.readShort(), (byte) 9, ConstantFieldref.class);
                                            int classIndex = constantFieldref.getClassIndex();
                                            String strCompactClassName = Utility.compactClassName(this.constantPool.getConstantString(classIndex, (byte) 7), false);
                                            String strConstantToString = this.constantPool.constantToString(constantFieldref.getNameAndTypeIndex(), (byte) 12);
                                            if (strCompactClassName.equals(this.className)) {
                                                sb.append("<A HREF=\"");
                                                sb.append(this.className);
                                                sb.append("_methods.html#field");
                                                sb.append(strConstantToString);
                                                sb.append("\" TARGET=Methods>");
                                                sb.append(strConstantToString);
                                                sb.append("</A>\n");
                                            } else {
                                                sb.append(this.constantHtml.referenceConstant(classIndex));
                                                sb.append(Constants.ATTRVAL_THIS);
                                                sb.append(strConstantToString);
                                            }
                                            break;
                                        case 182:
                                        case 183:
                                        case 184:
                                        case 185:
                                        case 186:
                                            short s3 = byteSequence.readShort();
                                            if (unsignedByte3 == 185) {
                                                byteSequence.readUnsignedByte();
                                                byteSequence.readUnsignedByte();
                                                ConstantInterfaceMethodref constantInterfaceMethodref = (ConstantInterfaceMethodref) this.constantPool.getConstant(s3, (byte) 11, ConstantInterfaceMethodref.class);
                                                int classIndex2 = constantInterfaceMethodref.getClassIndex();
                                                nameAndTypeIndex = constantInterfaceMethodref.getNameAndTypeIndex();
                                                strReferenceClass = Class2HTML.referenceClass(classIndex2);
                                            } else if (unsignedByte3 == 186) {
                                                byteSequence.readUnsignedByte();
                                                byteSequence.readUnsignedByte();
                                                ConstantInvokeDynamic constantInvokeDynamic = (ConstantInvokeDynamic) this.constantPool.getConstant(s3, (byte) 18, ConstantInvokeDynamic.class);
                                                int nameAndTypeIndex2 = constantInvokeDynamic.getNameAndTypeIndex();
                                                strReferenceClass = "#" + constantInvokeDynamic.getBootstrapMethodAttrIndex();
                                                nameAndTypeIndex = nameAndTypeIndex2;
                                            } else {
                                                ConstantMethodref constantMethodref = (ConstantMethodref) this.constantPool.getConstant(s3, (byte) 10, ConstantMethodref.class);
                                                int classIndex3 = constantMethodref.getClassIndex();
                                                nameAndTypeIndex = constantMethodref.getNameAndTypeIndex();
                                                strReferenceClass = Class2HTML.referenceClass(classIndex3);
                                            }
                                            ConstantPool constantPool3 = this.constantPool;
                                            String html = Class2HTML.toHTML(constantPool3.constantToString(constantPool3.getConstant(nameAndTypeIndex, (byte) 12)));
                                            String strConstantToString2 = this.constantPool.constantToString(((ConstantNameAndType) this.constantPool.getConstant(nameAndTypeIndex, (byte) 12, ConstantNameAndType.class)).getSignatureIndex(), (byte) 1);
                                            String[] strArrMethodSignatureArgumentTypes = Utility.methodSignatureArgumentTypes(strConstantToString2, false);
                                            String strMethodSignatureReturnType = Utility.methodSignatureReturnType(strConstantToString2, false);
                                            sb.append(strReferenceClass);
                                            sb.append(".<A HREF=\"");
                                            sb.append(this.className);
                                            sb.append("_cp.html#cp");
                                            sb.append((int) s3);
                                            sb.append("\" TARGET=ConstantPool>");
                                            sb.append(html);
                                            sb.append("</A>(");
                                            for (int i20 = 0; i20 < strArrMethodSignatureArgumentTypes.length; i20++) {
                                                sb.append(Class2HTML.referenceType(strArrMethodSignatureArgumentTypes[i20]));
                                                if (i20 < strArrMethodSignatureArgumentTypes.length - 1) {
                                                    sb.append(", ");
                                                }
                                            }
                                            sb.append("):");
                                            sb.append(Class2HTML.referenceType(strMethodSignatureReturnType));
                                            break;
                                        case 187:
                                            sb.append(this.constantHtml.referenceConstant(byteSequence.readShort()));
                                            break;
                                        case 188:
                                            sb.append("<FONT COLOR=\"#00FF00\">");
                                            sb.append(Const.getTypeName(byteSequence.readByte()));
                                            sb.append("</FONT>");
                                            break;
                                        case 189:
                                            sb.append(this.constantHtml.referenceConstant(byteSequence.readShort()));
                                            break;
                                        default:
                                            switch (unsignedByte3) {
                                                case 196:
                                                    wide = true;
                                                    sb.append("(wide)");
                                                    break;
                                                case 197:
                                                    short s4 = byteSequence.readShort();
                                                    byte b = byteSequence.readByte();
                                                    sb.append(this.constantHtml.referenceConstant(s4));
                                                    sb.append(":");
                                                    sb.append((int) b);
                                                    sb.append("-dimensional");
                                                    break;
                                                case 198:
                                                case 199:
                                                    int index5 = (byteSequence.getIndex() + byteSequence.readShort()) - 1;
                                                    sb.append("<A HREF=\"#code");
                                                    sb.append(i);
                                                    sb.append("@");
                                                    sb.append(index5);
                                                    sb.append("\">");
                                                    sb.append(index5);
                                                    sb.append("</A>");
                                                    break;
                                                case 200:
                                                case 201:
                                                    int index6 = (byteSequence.getIndex() + byteSequence.readInt()) - 1;
                                                    sb.append("<A HREF=\"#code");
                                                    sb.append(i);
                                                    sb.append("@");
                                                    sb.append(index6);
                                                    sb.append("\">");
                                                    sb.append(index6);
                                                    sb.append("</A>");
                                                    break;
                                                default:
                                                    if (Const.getNoOfOperands(unsignedByte3) > 0) {
                                                        for (int i21 = 0; i21 < Const.getOperandTypeCount(unsignedByte3); i21++) {
                                                            switch (Const.getOperandType(unsignedByte3, i21)) {
                                                                case 8:
                                                                    sb.append(byteSequence.readUnsignedByte());
                                                                    break;
                                                                case 9:
                                                                    sb.append((int) byteSequence.readShort());
                                                                    break;
                                                                case 10:
                                                                    sb.append(byteSequence.readInt());
                                                                    break;
                                                                default:
                                                                    ena.a("Unreachable default case reached! ", Const.getOperandType(unsignedByte3, i21));
                                                                    return null;
                                                            }
                                                            sb.append("&nbsp;");
                                                        }
                                                    }
                                                    break;
                                            }
                                            break;
                                    }
                                    break;
                            }
                            break;
                    }
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                    if (wide) {
                        unsignedByte2 = byteSequence.readShort();
                        wide = false;
                    } else {
                        unsignedByte2 = byteSequence.readUnsignedByte();
                    }
                    sb.append("%");
                    sb.append(unsignedByte2);
                    break;
            }
        } else {
            sb.append(this.constantHtml.referenceConstant(byteSequence.readShort()));
        }
        sb.append("</TD>");
        return sb.toString();
    }

    private void findGotos(ByteSequence byteSequence, Code code) throws IOException {
        this.gotoSet = new BitSet(byteSequence.available());
        if (code != null) {
            for (CodeException codeException : code.getExceptionTable()) {
                this.gotoSet.set(codeException.getStartPC());
                this.gotoSet.set(codeException.getEndPC());
                this.gotoSet.set(codeException.getHandlerPC());
            }
            for (Attribute attribute : code.getAttributes()) {
                if (attribute.getTag() == 5) {
                    ((LocalVariableTable) attribute).forEach(new Consumer() { // from class: com.sun.org.apache.bcel.internal.util.c
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            CodeHTML.a(this.b, (LocalVariable) obj);
                        }
                    });
                    break;
                }
            }
        }
        while (byteSequence.available() > 0) {
            int unsignedByte = byteSequence.readUnsignedByte();
            if (unsignedByte != 170 && unsignedByte != 171) {
                switch (unsignedByte) {
                    case 153:
                    case 154:
                    case 155:
                    case 156:
                    case 157:
                    case 158:
                    case 159:
                    case 160:
                    case 161:
                    case 162:
                    case 163:
                    case 164:
                    case 165:
                    case 166:
                    case 167:
                    case 168:
                        this.gotoSet.set((byteSequence.getIndex() + byteSequence.readShort()) - 1);
                        break;
                    default:
                        switch (unsignedByte) {
                            case 198:
                            case 199:
                                break;
                            case 200:
                            case 201:
                                this.gotoSet.set((byteSequence.getIndex() + byteSequence.readInt()) - 1);
                                continue;
                            default:
                                byteSequence.unreadByte();
                                codeToHTML(byteSequence, 0);
                                continue;
                        }
                        this.gotoSet.set((byteSequence.getIndex() + byteSequence.readShort()) - 1);
                        break;
                }
            } else {
                int index = byteSequence.getIndex() % 4;
                int i = index == 0 ? 0 : 4 - index;
                for (int i2 = 0; i2 < i; i2++) {
                    byteSequence.readByte();
                }
                int i3 = byteSequence.readInt();
                if (unsignedByte == 170) {
                    int i4 = byteSequence.readInt();
                    int i5 = byteSequence.readInt();
                    int index2 = ((byteSequence.getIndex() - 12) - i) - 1;
                    this.gotoSet.set(i3 + index2);
                    for (int i6 = 0; i6 < (i5 - i4) + 1; i6++) {
                        this.gotoSet.set(byteSequence.readInt() + index2);
                    }
                } else {
                    int i7 = byteSequence.readInt();
                    int index3 = ((byteSequence.getIndex() - 8) - i) - 1;
                    this.gotoSet.set(i3 + index3);
                    for (int i8 = 0; i8 < i7; i8++) {
                        byteSequence.readInt();
                        this.gotoSet.set(byteSequence.readInt() + index3);
                    }
                }
            }
        }
    }

    private void writeMethod(Method method, int i) throws IOException {
        PrintWriter printWriter;
        Code code;
        String signature = method.getSignature();
        String[] strArrMethodSignatureArgumentTypes = Utility.methodSignatureArgumentTypes(signature, false);
        String strMethodSignatureReturnType = Utility.methodSignatureReturnType(signature, false);
        String html = Class2HTML.toHTML(method.getName());
        String strReplace = Utility.replace(Utility.accessToString(method.getAccessFlags()), " ", "&nbsp;");
        Attribute[] attributes = method.getAttributes();
        this.printWriter.print("<P><B><FONT COLOR=\"#FF0000\">" + strReplace + "</FONT>&nbsp;<A NAME=method" + i + ">" + Class2HTML.referenceType(strMethodSignatureReturnType) + "</A>&nbsp<A HREF=\"" + this.className + "_methods.html#method" + i + "\" TARGET=Methods>" + html + "</A>(");
        int i2 = 0;
        while (true) {
            int length = strArrMethodSignatureArgumentTypes.length;
            printWriter = this.printWriter;
            if (i2 >= length) {
                break;
            }
            printWriter.print(Class2HTML.referenceType(strArrMethodSignatureArgumentTypes[i2]));
            if (i2 < strArrMethodSignatureArgumentTypes.length - 1) {
                this.printWriter.print(",&nbsp;");
            }
            i2++;
        }
        printWriter.println(")</B></P>");
        byte[] code2 = null;
        if (attributes.length > 0) {
            this.printWriter.print("<H4>Attributes</H4><UL>\n");
            Code code3 = null;
            for (int i3 = 0; i3 < attributes.length; i3++) {
                byte tag = attributes[i3].getTag();
                PrintWriter printWriter2 = this.printWriter;
                if (tag != -1) {
                    printWriter2.print("<LI><A HREF=\"" + this.className + "_attributes.html#method" + i + "@" + i3 + "\" TARGET=Attributes>" + Const.getAttributeName(tag) + "</A></LI>\n");
                } else {
                    printWriter2.print("<LI>" + attributes[i3] + "</LI>");
                }
                if (tag == 2) {
                    Code code4 = (Code) attributes[i3];
                    Attribute[] attributes2 = code4.getAttributes();
                    code2 = code4.getCode();
                    this.printWriter.print("<UL>");
                    int i4 = 0;
                    while (i4 < attributes2.length) {
                        this.printWriter.print("<LI><A HREF=\"" + this.className + "_attributes.html#method" + i + "@" + i3 + "@" + i4 + "\" TARGET=Attributes>" + Const.getAttributeName(attributes2[i4].getTag()) + "</A></LI>\n");
                        i4++;
                        code4 = code4;
                        attributes2 = attributes2;
                        code2 = code2;
                    }
                    code3 = code4;
                    this.printWriter.print("</UL>");
                }
                code3 = code3;
            }
            this.printWriter.println("</UL>");
            code = code3;
        } else {
            code = null;
        }
        if (code2 != null) {
            ByteSequence byteSequence = new ByteSequence(code2);
            try {
                byteSequence.mark(byteSequence.available());
                findGotos(byteSequence, code);
                byteSequence.reset();
                this.printWriter.println("<TABLE BORDER=0><TR><TH ALIGN=LEFT>Byte<BR>offset</TH><TH ALIGN=LEFT>Instruction</TH><TH ALIGN=LEFT>Argument</TH>");
                while (byteSequence.available() > 0) {
                    int index = byteSequence.getIndex();
                    String strCodeToHTML = codeToHTML(byteSequence, i);
                    String str = this.gotoSet.get(index) ? "<A NAME=code" + i + "@" + index + "></A>" : "";
                    this.printWriter.println("<TR VALIGN=TOP><TD>" + (byteSequence.getIndex() == code2.length ? "<A NAME=code" + i + "@" + code2.length + ">" + index + "</A>" : "" + index) + "</TD><TD>" + str + strCodeToHTML + "</TR>");
                }
                byteSequence.close();
                this.printWriter.println("<TR><TD> </A></TD></TR>");
                this.printWriter.println("</TABLE>");
            } catch (Throwable th) {
                try {
                    byteSequence.close();
                    throw th;
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                    throw th;
                }
            }
        }
    }
}
