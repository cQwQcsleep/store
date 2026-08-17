package com.sun.org.apache.bcel.internal.classfile;

import com.intellij.psi.PsiKeyword;
import com.sun.jna.platform.win32.COM.tlb.imp.TlbBase;
import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.classfile.Utility;
import com.sun.org.apache.bcel.internal.util.ByteSequence;
import com.sun.org.apache.xalan.internal.templates.Constants;
import defpackage.ise;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.FilterReader;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.function.Supplier;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Utility {
    private static final char ESCAPE_CHAR = '$';
    private static final int FREE_CHARS = 48;
    private static boolean wide;
    private static final ThreadLocal<Integer> CONSUMER_CHARS = ThreadLocal.withInitial(new Supplier() { // from class: d2f
        @Override // java.util.function.Supplier
        public final Object get() {
            return Utility.a();
        }
    });
    private static final int[] CHAR_MAP = new int[48];
    private static final int[] MAP_CHAR = new int[256];

    static {
        int i = 0;
        for (int i2 = 65; i2 <= 90; i2++) {
            CHAR_MAP[i] = i2;
            MAP_CHAR[i2] = i;
            i++;
        }
        for (int i3 = 103; i3 <= 122; i3++) {
            CHAR_MAP[i] = i3;
            MAP_CHAR[i3] = i;
            i++;
        }
        int[] iArr = CHAR_MAP;
        iArr[i] = 36;
        int[] iArr2 = MAP_CHAR;
        iArr2[36] = i;
        int i4 = i + 1;
        iArr[i4] = 95;
        iArr2[95] = i4;
    }

    public static /* synthetic */ Integer a() {
        return 0;
    }

    public static String accessToString(int i, boolean z) {
        StringBuilder sb = new StringBuilder();
        int iPow2 = 0;
        int i2 = 0;
        while (iPow2 < 32768) {
            iPow2 = pow2(i2);
            if ((i & iPow2) != 0 && (!z || (iPow2 != 32 && iPow2 != 512))) {
                sb.append(Const.getAccessName(i2));
                sb.append(" ");
            }
            i2++;
        }
        return sb.toString().trim();
    }

    private static short byteToShort(byte b) {
        return b < 0 ? (short) (b + 256) : b;
    }

    public static String classOrInterface(int i) {
        return (i & 512) != 0 ? PsiKeyword.INTERFACE : "class";
    }

    public static int clearBit(int i, int i2) {
        int iPow2 = pow2(i2);
        return (i & iPow2) == 0 ? i : i ^ iPow2;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:105:0x0366  */
    /* JADX WARN: Code duplicated, block: B:108:0x037d  */
    /* JADX WARN: Code duplicated, block: B:91:0x02e3  */
    /* JADX WARN: Code duplicated, block: B:92:0x02f6 A[FALL_THROUGH] */
    /* JADX WARN: Code duplicated, block: B:94:0x02fa  */
    /* JADX WARN: Code duplicated, block: B:95:0x0303  */
    /* JADX WARN: Instruction removed from duplicated block: B:108:0x037d, please report this as an issue */
    public static String codeToString(ByteSequence byteSequence, ConstantPool constantPool, boolean z) throws IOException {
        int i;
        int i2;
        int unsignedByte;
        short s;
        int unsignedByte2;
        short unsignedByte3 = (short) byteSequence.readUnsignedByte();
        StringBuilder sb = new StringBuilder(Const.getOpcodeName(unsignedByte3));
        if (unsignedByte3 == 170 || unsignedByte3 == 171) {
            int index = byteSequence.getIndex() % 4;
            i = index == 0 ? 0 : 4 - index;
            for (int i3 = 0; i3 < i; i3++) {
                byte b = byteSequence.readByte();
                if (b != 0) {
                    System.err.println("Warning: Padding byte != 0 in " + Const.getOpcodeName(unsignedByte3) + ":" + ((int) b));
                }
            }
            i2 = byteSequence.readInt();
        } else {
            i = 0;
            i2 = 0;
        }
        if (unsignedByte3 != 132) {
            if (unsignedByte3 == 192) {
                sb.append(TlbBase.TAB);
                int unsignedShort = byteSequence.readUnsignedShort();
                sb.append("\t<");
                sb.append(constantPool.constantToString(unsignedShort, (byte) 7));
                sb.append(">");
                sb.append(z ? " (" + unsignedShort + ")" : "");
            } else if (unsignedByte3 != 193) {
                switch (unsignedByte3) {
                    case 18:
                        int unsignedByte4 = byteSequence.readUnsignedByte();
                        sb.append(TlbBase.TABTAB);
                        sb.append(constantPool.constantToString(unsignedByte4, constantPool.getConstant(unsignedByte4).getTag()));
                        sb.append(z ? " (" + unsignedByte4 + ")" : "");
                        break;
                    case 19:
                    case 20:
                        int unsignedShort2 = byteSequence.readUnsignedShort();
                        sb.append(TlbBase.TABTAB);
                        sb.append(constantPool.constantToString(unsignedShort2, constantPool.getConstant(unsignedShort2).getTag()));
                        sb.append(z ? " (" + unsignedShort2 + ")" : "");
                        break;
                    default:
                        switch (unsignedByte3) {
                            default:
                                int i4 = i;
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
                                        sb.append("\t\t#");
                                        sb.append((byteSequence.getIndex() - 1) + byteSequence.readShort());
                                        break;
                                    case 169:
                                        break;
                                    case 170:
                                        int i5 = byteSequence.readInt();
                                        int i6 = byteSequence.readInt();
                                        int index2 = ((byteSequence.getIndex() - 12) - i4) - 1;
                                        sb.append("\tdefault = ");
                                        sb.append(i2 + index2);
                                        sb.append(", low = ");
                                        sb.append(i5);
                                        sb.append(", high = ");
                                        sb.append(i6);
                                        sb.append("(");
                                        int i7 = i6 - i5;
                                        int i8 = i7 + 1;
                                        int[] iArr = new int[i8];
                                        for (int i9 = 0; i9 < i8; i9++) {
                                            int i10 = byteSequence.readInt() + index2;
                                            iArr[i9] = i10;
                                            sb.append(i10);
                                            if (i9 < i7) {
                                                sb.append(", ");
                                            }
                                        }
                                        sb.append(")");
                                        break;
                                    case 171:
                                        int i11 = byteSequence.readInt();
                                        int index3 = ((byteSequence.getIndex() - 8) - i4) - 1;
                                        int[] iArr2 = new int[i11];
                                        int[] iArr3 = new int[i11];
                                        sb.append("\tdefault = ");
                                        sb.append(i2 + index3);
                                        sb.append(", npairs = ");
                                        sb.append(i11);
                                        sb.append(" (");
                                        for (int i12 = 0; i12 < i11; i12++) {
                                            iArr2[i12] = byteSequence.readInt();
                                            iArr3[i12] = byteSequence.readInt() + index3;
                                            sb.append("(");
                                            sb.append(iArr2[i12]);
                                            sb.append(", ");
                                            sb.append(iArr3[i12]);
                                            sb.append(")");
                                            if (i12 < i11 - 1) {
                                                sb.append(", ");
                                            }
                                        }
                                        sb.append(")");
                                        break;
                                    default:
                                        switch (unsignedByte3) {
                                            case 178:
                                            case 179:
                                            case 180:
                                            case 181:
                                                int unsignedShort3 = byteSequence.readUnsignedShort();
                                                sb.append(TlbBase.TABTAB);
                                                sb.append(constantPool.constantToString(unsignedShort3, (byte) 9));
                                                sb.append(z ? " (" + unsignedShort3 + ")" : "");
                                                break;
                                            case 182:
                                                int unsignedShort4 = byteSequence.readUnsignedShort();
                                                sb.append(TlbBase.TAB);
                                                sb.append(constantPool.constantToString(unsignedShort4, (byte) 10));
                                                sb.append(z ? " (" + unsignedShort4 + ")" : "");
                                                break;
                                            case 183:
                                            case 184:
                                                int unsignedShort5 = byteSequence.readUnsignedShort();
                                                Constant constant = constantPool.getConstant(unsignedShort5);
                                                sb.append(TlbBase.TAB);
                                                sb.append(constantPool.constantToString(unsignedShort5, constant.getTag()));
                                                sb.append(z ? " (" + unsignedShort5 + ")" : "");
                                                break;
                                            case 185:
                                                int unsignedShort6 = byteSequence.readUnsignedShort();
                                                int unsignedByte5 = byteSequence.readUnsignedByte();
                                                sb.append(TlbBase.TAB);
                                                sb.append(constantPool.constantToString(unsignedShort6, (byte) 11));
                                                sb.append(z ? " (" + unsignedShort6 + ")\t" : "");
                                                sb.append(unsignedByte5);
                                                sb.append(TlbBase.TAB);
                                                sb.append(byteSequence.readUnsignedByte());
                                                break;
                                            case 186:
                                                int unsignedShort7 = byteSequence.readUnsignedShort();
                                                sb.append(TlbBase.TAB);
                                                sb.append(constantPool.constantToString(unsignedShort7, (byte) 18));
                                                sb.append(z ? " (" + unsignedShort7 + ")\t" : "");
                                                sb.append(byteSequence.readUnsignedByte());
                                                sb.append(byteSequence.readUnsignedByte());
                                                break;
                                            case 187:
                                                sb.append(TlbBase.TAB);
                                                int unsignedShort8 = byteSequence.readUnsignedShort();
                                                sb.append("\t<");
                                                sb.append(constantPool.constantToString(unsignedShort8, (byte) 7));
                                                sb.append(">");
                                                if (z) {
                                                }
                                                sb.append(z ? " (" + unsignedShort8 + ")" : "");
                                                break;
                                            case 188:
                                                sb.append("\t\t<");
                                                sb.append(Const.getTypeName(byteSequence.readByte()));
                                                sb.append(">");
                                                break;
                                            case 189:
                                                int unsignedShort9 = byteSequence.readUnsignedShort();
                                                sb.append("\t\t<");
                                                sb.append(compactClassName(constantPool.getConstantString(unsignedShort9, (byte) 7), false));
                                                sb.append(">");
                                                sb.append(z ? " (" + unsignedShort9 + ")" : "");
                                                break;
                                            default:
                                                switch (unsignedByte3) {
                                                    case 196:
                                                        wide = true;
                                                        sb.append("\t(wide)");
                                                        break;
                                                    case 197:
                                                        int unsignedShort10 = byteSequence.readUnsignedShort();
                                                        int unsignedByte6 = byteSequence.readUnsignedByte();
                                                        sb.append("\t<");
                                                        sb.append(compactClassName(constantPool.getConstantString(unsignedShort10, (byte) 7), false));
                                                        sb.append(">\t");
                                                        sb.append(unsignedByte6);
                                                        sb.append(z ? " (" + unsignedShort10 + ")" : "");
                                                        break;
                                                    case 198:
                                                    case 199:
                                                        sb.append("\t\t#");
                                                        sb.append((byteSequence.getIndex() - 1) + byteSequence.readShort());
                                                        break;
                                                    case 200:
                                                    case 201:
                                                        sb.append("\t\t#");
                                                        sb.append((byteSequence.getIndex() - 1) + byteSequence.readInt());
                                                        break;
                                                    default:
                                                        if (Const.getNoOfOperands(unsignedByte3) > 0) {
                                                            for (int i13 = 0; i13 < Const.getOperandTypeCount(unsignedByte3); i13++) {
                                                                sb.append(TlbBase.TABTAB);
                                                                switch (Const.getOperandType(unsignedByte3, i13)) {
                                                                    case 8:
                                                                        sb.append((int) byteSequence.readByte());
                                                                        break;
                                                                    case 9:
                                                                        sb.append((int) byteSequence.readShort());
                                                                        break;
                                                                    case 10:
                                                                        sb.append(byteSequence.readInt());
                                                                        break;
                                                                    default:
                                                                        k2d.a("Unreachable default case reached!");
                                                                        return null;
                                                                }
                                                            }
                                                        }
                                                        break;
                                                }
                                                break;
                                        }
                                        break;
                                }
                            case 54:
                            case 55:
                            case 56:
                            case 57:
                            case 58:
                                if (wide) {
                                    unsignedByte2 = byteSequence.readUnsignedShort();
                                    wide = false;
                                } else {
                                    unsignedByte2 = byteSequence.readUnsignedByte();
                                }
                                sb.append("\t\t%");
                                sb.append(unsignedByte2);
                                break;
                        }
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                        if (wide) {
                            unsignedByte2 = byteSequence.readUnsignedShort();
                            wide = false;
                        } else {
                            unsignedByte2 = byteSequence.readUnsignedByte();
                        }
                        sb.append("\t\t%");
                        sb.append(unsignedByte2);
                        break;
                }
            } else {
                int unsignedShort11 = byteSequence.readUnsignedShort();
                sb.append("\t<");
                sb.append(constantPool.constantToString(unsignedShort11, (byte) 7));
                sb.append(">");
                if (z) {
                }
                sb.append(z ? " (" + unsignedShort11 + ")" : "");
            }
        } else {
            if (wide) {
                unsignedByte = byteSequence.readUnsignedShort();
                s = byteSequence.readShort();
                wide = false;
            } else {
                unsignedByte = byteSequence.readUnsignedByte();
                s = byteSequence.readByte();
            }
            sb.append("\t\t%");
            sb.append(unsignedByte);
            sb.append(TlbBase.TAB);
            sb.append((int) s);
        }
        return sb.toString();
    }

    public static String compactClassName(String str, String str2, boolean z) {
        int length = str2.length();
        String strPathToPackage = pathToPackage(str);
        return (z && strPathToPackage.startsWith(str2) && strPathToPackage.substring(length).indexOf(46) == -1) ? strPathToPackage.substring(length) : strPathToPackage;
    }

    public static String convertString(String str) {
        char[] charArray = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : charArray) {
            if (c == '\n') {
                sb.append("\\n");
            } else if (c == '\r') {
                sb.append("\\r");
            } else if (c == '\"') {
                sb.append("\\\"");
            } else if (c == '\'') {
                sb.append("\\'");
            } else if (c != '\\') {
                sb.append(c);
            } else {
                sb.append("\\\\");
            }
        }
        return sb.toString();
    }

    private static int countBrackets(String str) {
        boolean z = false;
        int i = 0;
        for (char c : str.toCharArray()) {
            if (c == '[') {
                if (z) {
                    w01.a("Illegally nested brackets:".concat(str));
                    return 0;
                }
                z = true;
            } else if (c != ']') {
                continue;
            } else {
                if (!z) {
                    w01.a("Illegally nested brackets:".concat(str));
                    return 0;
                }
                i++;
                z = false;
            }
        }
        if (!z) {
            return i;
        }
        w01.a("Illegally nested brackets:".concat(str));
        return 0;
    }

    public static byte[] decode(String str, boolean z) throws IOException {
        JavaReader javaReader = new JavaReader(new CharArrayReader(str.toCharArray()));
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                try {
                    int i = javaReader.read();
                    if (i < 0) {
                        break;
                    }
                    byteArrayOutputStream.write(i);
                } catch (Throwable th) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
                try {
                    javaReader.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th;
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            javaReader.close();
            if (!z) {
                return byteArray;
            }
            GZIPInputStream gZIPInputStream = new GZIPInputStream(new ByteArrayInputStream(byteArray));
            byte[] bArr = new byte[byteArray.length * 3];
            int i2 = 0;
            while (true) {
                int i3 = gZIPInputStream.read();
                if (i3 < 0) {
                    return Arrays.copyOf(bArr, i2);
                }
                bArr[i2] = (byte) i3;
                i2++;
            }
        } catch (Throwable th4) {
            javaReader.close();
            throw th4;
        }
    }

    public static String encode(byte[] bArr, boolean z) throws IOException {
        if (z) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                try {
                    gZIPOutputStream.write(bArr, 0, bArr.length);
                    gZIPOutputStream.finish();
                    bArr = byteArrayOutputStream.toByteArray();
                    gZIPOutputStream.close();
                    byteArrayOutputStream.close();
                } catch (Throwable th) {
                    try {
                        gZIPOutputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th4) {
                    th3.addSuppressed(th4);
                }
                throw th3;
            }
        }
        CharArrayWriter charArrayWriter = new CharArrayWriter();
        JavaWriter javaWriter = new JavaWriter(charArrayWriter);
        try {
            for (byte b : bArr) {
                javaWriter.write(b & 255);
            }
            javaWriter.close();
            return charArrayWriter.toString();
        } catch (Throwable th5) {
            try {
                javaWriter.close();
            } catch (Throwable th6) {
                th5.addSuppressed(th6);
            }
            throw th5;
        }
    }

    public static String fillup(String str, int i, boolean z, char c) {
        char[] cArr = new char[Math.max(i - str.length(), 0)];
        Arrays.fill(cArr, c);
        return z ? str.concat(new String(cArr)) : new String(cArr).concat(str);
    }

    public static String format(int i, int i2, boolean z, char c) {
        return fillup(Integer.toString(i), i2, z, c);
    }

    public static String getSignature(String str) {
        StringBuilder sb = new StringBuilder();
        char[] charArray = str.toCharArray();
        boolean z = false;
        int i = 0;
        boolean z2 = false;
        boolean z3 = false;
        while (true) {
            if (i >= charArray.length) {
                i = -1;
                break;
            }
            char c = charArray[i];
            if (c != '\t' && c != '\n' && c != '\f' && c != '\r' && c != ' ') {
                if (c == '[') {
                    if (z2) {
                        break;
                    }
                    w01.a("Illegal type: ".concat(str));
                    return null;
                }
                if (!z3) {
                    sb.append(c);
                }
                z2 = true;
            } else if (z2) {
                z3 = true;
            }
            i++;
        }
        int iCountBrackets = i > 0 ? countBrackets(str.substring(i)) : 0;
        String string = sb.toString();
        sb.setLength(0);
        for (int i2 = 0; i2 < iCountBrackets; i2++) {
            sb.append('[');
        }
        for (int i3 = 4; i3 <= 12 && !z; i3++) {
            if (Const.getTypeName(i3).equals(string)) {
                sb.append(Const.getShortTypeName(i3));
                z = true;
            }
        }
        if (!z) {
            sb.append('L');
            sb.append(packageToPath(string));
            sb.append(';');
        }
        return sb.toString();
    }

    public static boolean isJavaIdentifierPart(char c) {
        if (c >= 'a' && c <= 'z') {
            return true;
        }
        if (c < 'A' || c > 'Z') {
            return (c >= '0' && c <= '9') || c == '_';
        }
        return true;
    }

    public static boolean isSet(int i, int i2) {
        return (i & pow2(i2)) != 0;
    }

    public static String[] methodSignatureArgumentTypes(String str, boolean z) throws ClassFormatException {
        ArrayList arrayList = new ArrayList();
        try {
            int iIndexOf = str.indexOf(40) + 1;
            if (iIndexOf <= 0) {
                throw new ClassFormatException("Invalid method signature: ".concat(str));
            }
            while (str.charAt(iIndexOf) != ')') {
                arrayList.add(typeSignatureToString(str.substring(iIndexOf), z));
                iIndexOf += unwrap(CONSUMER_CHARS);
            }
            return (String[]) arrayList.toArray(Const.EMPTY_STRING_ARRAY);
        } catch (StringIndexOutOfBoundsException e) {
            ise.a("Invalid method signature: ", str, e);
            return null;
        }
    }

    public static String methodSignatureReturnType(String str, boolean z) throws ClassFormatException {
        try {
            int iLastIndexOf = str.lastIndexOf(41) + 1;
            if (iLastIndexOf > 0) {
                return typeSignatureToString(str.substring(iLastIndexOf), z);
            }
            throw new ClassFormatException("Invalid method signature: ".concat(str));
        } catch (StringIndexOutOfBoundsException e) {
            ise.a("Invalid method signature: ", str, e);
            return null;
        }
    }

    public static String methodSignatureToString(String str, String str2, String str3, boolean z, LocalVariableTable localVariableTable) throws ClassFormatException {
        StringBuilder sb = new StringBuilder("(");
        int i = !str3.contains(PsiKeyword.STATIC) ? 1 : 0;
        try {
            int iIndexOf = str.indexOf(40) + 1;
            if (iIndexOf <= 0) {
                throw new ClassFormatException("Invalid method signature: ".concat(str));
            }
            while (str.charAt(iIndexOf) != ')') {
                String strTypeSignatureToString = typeSignatureToString(str.substring(iIndexOf), z);
                sb.append(strTypeSignatureToString);
                if (localVariableTable != null) {
                    LocalVariable localVariable = localVariableTable.getLocalVariable(i, 0);
                    if (localVariable != null) {
                        sb.append(" ");
                        sb.append(localVariable.getName());
                    }
                } else {
                    sb.append(" arg");
                    sb.append(i);
                }
                i = ("double".equals(strTypeSignatureToString) || "long".equals(strTypeSignatureToString)) ? i + 2 : i + 1;
                sb.append(", ");
                iIndexOf += unwrap(CONSUMER_CHARS);
            }
            String strTypeSignatureToString2 = typeSignatureToString(str.substring(iIndexOf + 1), z);
            if (sb.length() > 1) {
                sb.setLength(sb.length() - 2);
            }
            sb.append(")");
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str3);
            sb2.append(!str3.isEmpty() ? " " : "");
            sb2.append(strTypeSignatureToString2);
            sb2.append(" ");
            sb2.append(str2);
            sb2.append(sb.toString());
            return sb2.toString();
        } catch (StringIndexOutOfBoundsException e) {
            ise.a("Invalid method signature: ", str, e);
            return null;
        }
    }

    public static String methodTypeToSignature(String str, String[] strArr) throws ClassFormatException {
        StringBuilder sb = new StringBuilder("(");
        if (strArr != null) {
            for (String str2 : strArr) {
                String signature = getSignature(str2);
                if (signature.endsWith("V")) {
                    throw new ClassFormatException("Invalid type: " + str2);
                }
                sb.append(signature);
            }
        }
        String signature2 = getSignature(str);
        sb.append(")");
        sb.append(signature2);
        return sb.toString();
    }

    public static String packageToPath(String str) {
        return str.replace('.', '/');
    }

    public static String pathToPackage(String str) {
        return str.replace('/', '.');
    }

    private static int pow2(int i) {
        return 1 << i;
    }

    public static String printArray(Object[] objArr, boolean z, boolean z2) {
        if (objArr == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append('{');
        }
        for (int i = 0; i < objArr.length; i++) {
            if (objArr[i] != null) {
                sb.append(z2 ? "\"" : "");
                sb.append(objArr[i]);
                sb.append(z2 ? "\"" : "");
            } else {
                sb.append(PsiKeyword.NULL);
            }
            if (i < objArr.length - 1) {
                sb.append(", ");
            }
        }
        if (z) {
            sb.append('}');
        }
        return sb.toString();
    }

    public static String replace(String str, String str2, String str3) {
        try {
            if (!str.contains(str2)) {
                return str;
            }
            StringBuilder sb = new StringBuilder();
            int length = 0;
            while (true) {
                int iIndexOf = str.indexOf(str2, length);
                if (iIndexOf == -1) {
                    sb.append(str.substring(length));
                    return sb.toString();
                }
                sb.append((CharSequence) str, length, iIndexOf);
                sb.append(str3);
                length = str2.length() + iIndexOf;
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.err.println(e);
            return str;
        }
    }

    public static short searchOpcode(String str) {
        String lowerCase = str.toLowerCase(Locale.ENGLISH);
        for (short s = 0; s < Const.OPCODE_NAMES_LENGTH; s = (short) (s + 1)) {
            if (Const.getOpcodeName(s).equals(lowerCase)) {
                return s;
            }
        }
        return (short) -1;
    }

    public static int setBit(int i, int i2) {
        return i | pow2(i2);
    }

    public static String signatureToString(String str, boolean z) {
        String strTypeParamTypesToString;
        int iUnwrap = 0;
        if (str.charAt(0) == '<') {
            strTypeParamTypesToString = typeParamTypesToString(str, z);
            iUnwrap = unwrap(CONSUMER_CHARS);
        } else {
            strTypeParamTypesToString = "";
        }
        if (str.charAt(iUnwrap) == '(') {
            String str2 = strTypeParamTypesToString + typeSignaturesToString(str.substring(iUnwrap), z, ')');
            ThreadLocal<Integer> threadLocal = CONSUMER_CHARS;
            String str3 = str2 + typeSignatureToString(str.substring(iUnwrap + unwrap(threadLocal)), z);
            unwrap(threadLocal);
            return str3;
        }
        String strTypeSignatureToString = typeSignatureToString(str.substring(iUnwrap), z);
        ThreadLocal<Integer> threadLocal2 = CONSUMER_CHARS;
        int iUnwrap2 = iUnwrap + unwrap(threadLocal2);
        if (strTypeParamTypesToString.isEmpty() && iUnwrap2 == str.length()) {
            return strTypeSignatureToString;
        }
        StringBuilder sb = new StringBuilder(strTypeParamTypesToString);
        sb.append(" extends ");
        sb.append(strTypeSignatureToString);
        if (iUnwrap2 < str.length()) {
            sb.append(" implements ");
            sb.append(typeSignatureToString(str.substring(iUnwrap2), z));
            iUnwrap2 += unwrap(threadLocal2);
        }
        while (iUnwrap2 < str.length()) {
            sb.append(", ");
            sb.append(typeSignatureToString(str.substring(iUnwrap2), z));
            iUnwrap2 += unwrap(CONSUMER_CHARS);
        }
        return sb.toString();
    }

    public static String toHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bArr.length; i++) {
            short sByteToShort = byteToShort(bArr[i]);
            String hexString = Integer.toHexString(sByteToShort);
            if (sByteToShort < 16) {
                sb.append('0');
            }
            sb.append(hexString);
            if (i < bArr.length - 1) {
                sb.append(' ');
            }
        }
        return sb.toString();
    }

    public static byte typeOfMethodSignature(String str) throws ClassFormatException {
        try {
            if (str.charAt(0) == '(') {
                return typeOfSignature(str.substring(str.lastIndexOf(41) + 1));
            }
            throw new ClassFormatException("Invalid method signature: ".concat(str));
        } catch (StringIndexOutOfBoundsException e) {
            ise.a("Invalid method signature: ", str, e);
            return (byte) 0;
        }
    }

    public static byte typeOfSignature(String str) throws ClassFormatException {
        try {
            char cCharAt = str.charAt(0);
            if (cCharAt != '!') {
                if (cCharAt == 'F') {
                    return (byte) 6;
                }
                if (cCharAt == 'L') {
                    return (byte) 14;
                }
                if (cCharAt == 'V') {
                    return (byte) 12;
                }
                if (cCharAt != '*' && cCharAt != '+') {
                    if (cCharAt == 'I') {
                        return (byte) 10;
                    }
                    if (cCharAt == 'J') {
                        return (byte) 11;
                    }
                    if (cCharAt == 'S') {
                        return (byte) 9;
                    }
                    if (cCharAt == 'T') {
                        return (byte) 14;
                    }
                    if (cCharAt == 'Z') {
                        return (byte) 4;
                    }
                    if (cCharAt == '[') {
                        return (byte) 13;
                    }
                    switch (cCharAt) {
                        case 'B':
                            return (byte) 8;
                        case 'C':
                            return (byte) 5;
                        case 'D':
                            return (byte) 7;
                        default:
                            throw new ClassFormatException("Invalid method signature: ".concat(str));
                    }
                }
            }
            return typeOfSignature(str.substring(1));
        } catch (StringIndexOutOfBoundsException e) {
            ise.a("Invalid method signature: ", str, e);
            return (byte) 0;
        }
    }

    private static String typeParamTypeToString(String str, boolean z) {
        int iIndexOf = str.indexOf(58);
        if (iIndexOf <= 0) {
            throw new ClassFormatException("Invalid type parameter signature: ".concat(str));
        }
        StringBuilder sb = new StringBuilder(str.substring(0, iIndexOf));
        int iUnwrap = iIndexOf + 1;
        if (str.charAt(iUnwrap) != ':') {
            sb.append(" extends ");
            sb.append(typeSignatureToString(str.substring(iUnwrap), z));
            iUnwrap += unwrap(CONSUMER_CHARS);
        }
        while (str.charAt(iUnwrap) == ':') {
            int i = iUnwrap + 1;
            sb.append(" & ");
            sb.append(typeSignatureToString(str.substring(i), z));
            iUnwrap = i + unwrap(CONSUMER_CHARS);
        }
        wrap(CONSUMER_CHARS, iUnwrap);
        return sb.toString();
    }

    private static String typeParamTypesToString(String str, boolean z) {
        StringBuilder sb = new StringBuilder("<");
        sb.append(typeParamTypeToString(str.substring(1), z));
        int iUnwrap = unwrap(CONSUMER_CHARS) + 1;
        while (str.charAt(iUnwrap) != '>') {
            sb.append(", ");
            sb.append(typeParamTypeToString(str.substring(iUnwrap), z));
            iUnwrap += unwrap(CONSUMER_CHARS);
        }
        wrap(CONSUMER_CHARS, iUnwrap + 1);
        sb.append(">");
        return sb.toString();
    }

    /* JADX WARN: Code duplicated, block: B:100:0x01e3 A[Catch: StringIndexOutOfBoundsException -> 0x0051, TryCatch #0 {StringIndexOutOfBoundsException -> 0x0051, blocks: (B:3:0x000d, B:21:0x0037, B:22:0x003a, B:23:0x0050, B:32:0x005d, B:33:0x0062, B:35:0x0068, B:36:0x0070, B:40:0x0099, B:42:0x009f, B:44:0x00ad, B:45:0x00b6, B:54:0x00c5, B:59:0x00d7, B:61:0x00dd, B:63:0x00e7, B:65:0x00f5, B:68:0x00fd, B:70:0x010b, B:73:0x012d, B:74:0x0130, B:78:0x013d, B:81:0x0147, B:83:0x015f, B:85:0x0165, B:87:0x0170, B:88:0x0173, B:92:0x0180, B:94:0x0186, B:95:0x018c, B:89:0x0176, B:91:0x017c, B:96:0x01a2, B:98:0x01b1, B:100:0x01e3, B:102:0x01e9, B:104:0x01f5, B:105:0x0209, B:82:0x014c, B:75:0x0133, B:77:0x0139, B:106:0x020a, B:107:0x0213, B:108:0x0214, B:109:0x021d, B:57:0x00d1, B:110:0x021e, B:111:0x0227), top: B:116:0x000d }] */
    /* JADX WARN: Code duplicated, block: B:102:0x01e9 A[Catch: StringIndexOutOfBoundsException -> 0x0051, TryCatch #0 {StringIndexOutOfBoundsException -> 0x0051, blocks: (B:3:0x000d, B:21:0x0037, B:22:0x003a, B:23:0x0050, B:32:0x005d, B:33:0x0062, B:35:0x0068, B:36:0x0070, B:40:0x0099, B:42:0x009f, B:44:0x00ad, B:45:0x00b6, B:54:0x00c5, B:59:0x00d7, B:61:0x00dd, B:63:0x00e7, B:65:0x00f5, B:68:0x00fd, B:70:0x010b, B:73:0x012d, B:74:0x0130, B:78:0x013d, B:81:0x0147, B:83:0x015f, B:85:0x0165, B:87:0x0170, B:88:0x0173, B:92:0x0180, B:94:0x0186, B:95:0x018c, B:89:0x0176, B:91:0x017c, B:96:0x01a2, B:98:0x01b1, B:100:0x01e3, B:102:0x01e9, B:104:0x01f5, B:105:0x0209, B:82:0x014c, B:75:0x0133, B:77:0x0139, B:106:0x020a, B:107:0x0213, B:108:0x0214, B:109:0x021d, B:57:0x00d1, B:110:0x021e, B:111:0x0227), top: B:116:0x000d }] */
    /* JADX WARN: Code duplicated, block: B:104:0x01f5 A[Catch: StringIndexOutOfBoundsException -> 0x0051, TryCatch #0 {StringIndexOutOfBoundsException -> 0x0051, blocks: (B:3:0x000d, B:21:0x0037, B:22:0x003a, B:23:0x0050, B:32:0x005d, B:33:0x0062, B:35:0x0068, B:36:0x0070, B:40:0x0099, B:42:0x009f, B:44:0x00ad, B:45:0x00b6, B:54:0x00c5, B:59:0x00d7, B:61:0x00dd, B:63:0x00e7, B:65:0x00f5, B:68:0x00fd, B:70:0x010b, B:73:0x012d, B:74:0x0130, B:78:0x013d, B:81:0x0147, B:83:0x015f, B:85:0x0165, B:87:0x0170, B:88:0x0173, B:92:0x0180, B:94:0x0186, B:95:0x018c, B:89:0x0176, B:91:0x017c, B:96:0x01a2, B:98:0x01b1, B:100:0x01e3, B:102:0x01e9, B:104:0x01f5, B:105:0x0209, B:82:0x014c, B:75:0x0133, B:77:0x0139, B:106:0x020a, B:107:0x0213, B:108:0x0214, B:109:0x021d, B:57:0x00d1, B:110:0x021e, B:111:0x0227), top: B:116:0x000d }] */
    /* JADX WARN: Code duplicated, block: B:119:0x018c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:120:0x0186 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:81:0x0147 A[Catch: StringIndexOutOfBoundsException -> 0x0051, TRY_ENTER, TryCatch #0 {StringIndexOutOfBoundsException -> 0x0051, blocks: (B:3:0x000d, B:21:0x0037, B:22:0x003a, B:23:0x0050, B:32:0x005d, B:33:0x0062, B:35:0x0068, B:36:0x0070, B:40:0x0099, B:42:0x009f, B:44:0x00ad, B:45:0x00b6, B:54:0x00c5, B:59:0x00d7, B:61:0x00dd, B:63:0x00e7, B:65:0x00f5, B:68:0x00fd, B:70:0x010b, B:73:0x012d, B:74:0x0130, B:78:0x013d, B:81:0x0147, B:83:0x015f, B:85:0x0165, B:87:0x0170, B:88:0x0173, B:92:0x0180, B:94:0x0186, B:95:0x018c, B:89:0x0176, B:91:0x017c, B:96:0x01a2, B:98:0x01b1, B:100:0x01e3, B:102:0x01e9, B:104:0x01f5, B:105:0x0209, B:82:0x014c, B:75:0x0133, B:77:0x0139, B:106:0x020a, B:107:0x0213, B:108:0x0214, B:109:0x021d, B:57:0x00d1, B:110:0x021e, B:111:0x0227), top: B:116:0x000d }] */
    /* JADX WARN: Code duplicated, block: B:82:0x014c A[Catch: StringIndexOutOfBoundsException -> 0x0051, TryCatch #0 {StringIndexOutOfBoundsException -> 0x0051, blocks: (B:3:0x000d, B:21:0x0037, B:22:0x003a, B:23:0x0050, B:32:0x005d, B:33:0x0062, B:35:0x0068, B:36:0x0070, B:40:0x0099, B:42:0x009f, B:44:0x00ad, B:45:0x00b6, B:54:0x00c5, B:59:0x00d7, B:61:0x00dd, B:63:0x00e7, B:65:0x00f5, B:68:0x00fd, B:70:0x010b, B:73:0x012d, B:74:0x0130, B:78:0x013d, B:81:0x0147, B:83:0x015f, B:85:0x0165, B:87:0x0170, B:88:0x0173, B:92:0x0180, B:94:0x0186, B:95:0x018c, B:89:0x0176, B:91:0x017c, B:96:0x01a2, B:98:0x01b1, B:100:0x01e3, B:102:0x01e9, B:104:0x01f5, B:105:0x0209, B:82:0x014c, B:75:0x0133, B:77:0x0139, B:106:0x020a, B:107:0x0213, B:108:0x0214, B:109:0x021d, B:57:0x00d1, B:110:0x021e, B:111:0x0227), top: B:116:0x000d }] */
    /* JADX WARN: Code duplicated, block: B:85:0x0165 A[Catch: StringIndexOutOfBoundsException -> 0x0051, TryCatch #0 {StringIndexOutOfBoundsException -> 0x0051, blocks: (B:3:0x000d, B:21:0x0037, B:22:0x003a, B:23:0x0050, B:32:0x005d, B:33:0x0062, B:35:0x0068, B:36:0x0070, B:40:0x0099, B:42:0x009f, B:44:0x00ad, B:45:0x00b6, B:54:0x00c5, B:59:0x00d7, B:61:0x00dd, B:63:0x00e7, B:65:0x00f5, B:68:0x00fd, B:70:0x010b, B:73:0x012d, B:74:0x0130, B:78:0x013d, B:81:0x0147, B:83:0x015f, B:85:0x0165, B:87:0x0170, B:88:0x0173, B:92:0x0180, B:94:0x0186, B:95:0x018c, B:89:0x0176, B:91:0x017c, B:96:0x01a2, B:98:0x01b1, B:100:0x01e3, B:102:0x01e9, B:104:0x01f5, B:105:0x0209, B:82:0x014c, B:75:0x0133, B:77:0x0139, B:106:0x020a, B:107:0x0213, B:108:0x0214, B:109:0x021d, B:57:0x00d1, B:110:0x021e, B:111:0x0227), top: B:116:0x000d }] */
    /* JADX WARN: Code duplicated, block: B:87:0x0170 A[Catch: StringIndexOutOfBoundsException -> 0x0051, TryCatch #0 {StringIndexOutOfBoundsException -> 0x0051, blocks: (B:3:0x000d, B:21:0x0037, B:22:0x003a, B:23:0x0050, B:32:0x005d, B:33:0x0062, B:35:0x0068, B:36:0x0070, B:40:0x0099, B:42:0x009f, B:44:0x00ad, B:45:0x00b6, B:54:0x00c5, B:59:0x00d7, B:61:0x00dd, B:63:0x00e7, B:65:0x00f5, B:68:0x00fd, B:70:0x010b, B:73:0x012d, B:74:0x0130, B:78:0x013d, B:81:0x0147, B:83:0x015f, B:85:0x0165, B:87:0x0170, B:88:0x0173, B:92:0x0180, B:94:0x0186, B:95:0x018c, B:89:0x0176, B:91:0x017c, B:96:0x01a2, B:98:0x01b1, B:100:0x01e3, B:102:0x01e9, B:104:0x01f5, B:105:0x0209, B:82:0x014c, B:75:0x0133, B:77:0x0139, B:106:0x020a, B:107:0x0213, B:108:0x0214, B:109:0x021d, B:57:0x00d1, B:110:0x021e, B:111:0x0227), top: B:116:0x000d }] */
    /* JADX WARN: Code duplicated, block: B:89:0x0176 A[Catch: StringIndexOutOfBoundsException -> 0x0051, TryCatch #0 {StringIndexOutOfBoundsException -> 0x0051, blocks: (B:3:0x000d, B:21:0x0037, B:22:0x003a, B:23:0x0050, B:32:0x005d, B:33:0x0062, B:35:0x0068, B:36:0x0070, B:40:0x0099, B:42:0x009f, B:44:0x00ad, B:45:0x00b6, B:54:0x00c5, B:59:0x00d7, B:61:0x00dd, B:63:0x00e7, B:65:0x00f5, B:68:0x00fd, B:70:0x010b, B:73:0x012d, B:74:0x0130, B:78:0x013d, B:81:0x0147, B:83:0x015f, B:85:0x0165, B:87:0x0170, B:88:0x0173, B:92:0x0180, B:94:0x0186, B:95:0x018c, B:89:0x0176, B:91:0x017c, B:96:0x01a2, B:98:0x01b1, B:100:0x01e3, B:102:0x01e9, B:104:0x01f5, B:105:0x0209, B:82:0x014c, B:75:0x0133, B:77:0x0139, B:106:0x020a, B:107:0x0213, B:108:0x0214, B:109:0x021d, B:57:0x00d1, B:110:0x021e, B:111:0x0227), top: B:116:0x000d }] */
    /* JADX WARN: Code duplicated, block: B:91:0x017c A[Catch: StringIndexOutOfBoundsException -> 0x0051, TryCatch #0 {StringIndexOutOfBoundsException -> 0x0051, blocks: (B:3:0x000d, B:21:0x0037, B:22:0x003a, B:23:0x0050, B:32:0x005d, B:33:0x0062, B:35:0x0068, B:36:0x0070, B:40:0x0099, B:42:0x009f, B:44:0x00ad, B:45:0x00b6, B:54:0x00c5, B:59:0x00d7, B:61:0x00dd, B:63:0x00e7, B:65:0x00f5, B:68:0x00fd, B:70:0x010b, B:73:0x012d, B:74:0x0130, B:78:0x013d, B:81:0x0147, B:83:0x015f, B:85:0x0165, B:87:0x0170, B:88:0x0173, B:92:0x0180, B:94:0x0186, B:95:0x018c, B:89:0x0176, B:91:0x017c, B:96:0x01a2, B:98:0x01b1, B:100:0x01e3, B:102:0x01e9, B:104:0x01f5, B:105:0x0209, B:82:0x014c, B:75:0x0133, B:77:0x0139, B:106:0x020a, B:107:0x0213, B:108:0x0214, B:109:0x021d, B:57:0x00d1, B:110:0x021e, B:111:0x0227), top: B:116:0x000d }] */
    /* JADX WARN: Code duplicated, block: B:98:0x01b1 A[Catch: StringIndexOutOfBoundsException -> 0x0051, TryCatch #0 {StringIndexOutOfBoundsException -> 0x0051, blocks: (B:3:0x000d, B:21:0x0037, B:22:0x003a, B:23:0x0050, B:32:0x005d, B:33:0x0062, B:35:0x0068, B:36:0x0070, B:40:0x0099, B:42:0x009f, B:44:0x00ad, B:45:0x00b6, B:54:0x00c5, B:59:0x00d7, B:61:0x00dd, B:63:0x00e7, B:65:0x00f5, B:68:0x00fd, B:70:0x010b, B:73:0x012d, B:74:0x0130, B:78:0x013d, B:81:0x0147, B:83:0x015f, B:85:0x0165, B:87:0x0170, B:88:0x0173, B:92:0x0180, B:94:0x0186, B:95:0x018c, B:89:0x0176, B:91:0x017c, B:96:0x01a2, B:98:0x01b1, B:100:0x01e3, B:102:0x01e9, B:104:0x01f5, B:105:0x0209, B:82:0x014c, B:75:0x0133, B:77:0x0139, B:106:0x020a, B:107:0x0213, B:108:0x0214, B:109:0x021d, B:57:0x00d1, B:110:0x021e, B:111:0x0227), top: B:116:0x000d }] */
    /* JADX WARN: Instruction removed from duplicated block: B:104:0x01f5, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:98:0x01b1, please report this as an issue */
    public static String typeSignatureToString(String str, boolean z) throws ClassFormatException {
        int iIndexOf;
        int iUnwrap;
        int i;
        ThreadLocal<Integer> threadLocal = CONSUMER_CHARS;
        wrap(threadLocal, 1);
        int i2 = 0;
        try {
            char cCharAt = str.charAt(0);
            if (cCharAt == 'F') {
                return "float";
            }
            if (cCharAt != 'L') {
                if (cCharAt == 'V') {
                    return PsiKeyword.VOID;
                }
                if (cCharAt == 'I') {
                    return "int";
                }
                if (cCharAt == 'J') {
                    return "long";
                }
                if (cCharAt == 'S') {
                    return "short";
                }
                if (cCharAt == 'T') {
                    int iIndexOf2 = str.indexOf(59);
                    if (iIndexOf2 < 0) {
                        throw new ClassFormatException("Invalid type variable signature: ".concat(str));
                    }
                    wrap(threadLocal, iIndexOf2 + 1);
                    return compactClassName(str.substring(1, iIndexOf2), z);
                }
                if (cCharAt == 'Z') {
                    return "boolean";
                }
                if (cCharAt != '[') {
                    switch (cCharAt) {
                        case 'B':
                            return "byte";
                        case 'C':
                            return PsiKeyword.CHAR;
                        case 'D':
                            return "double";
                        default:
                            throw new ClassFormatException("Invalid signature: '" + str + "'");
                    }
                }
                StringBuilder sb = new StringBuilder();
                while (str.charAt(i2) == '[') {
                    sb.append("[]");
                    i2++;
                }
                String strTypeSignatureToString = typeSignatureToString(str.substring(i2), z);
                ThreadLocal<Integer> threadLocal2 = CONSUMER_CHARS;
                wrap(threadLocal2, unwrap(threadLocal2) + i2);
                return strTypeSignatureToString + sb.toString();
            }
            int iIndexOf3 = str.indexOf(60);
            if (iIndexOf3 < 0) {
                iIndexOf = 0;
            } else {
                iIndexOf = str.indexOf(62, iIndexOf3);
                if (iIndexOf < 0) {
                    throw new ClassFormatException("Invalid signature: ".concat(str));
                }
            }
            int iIndexOf4 = str.indexOf(59, iIndexOf);
            if (iIndexOf4 < 0) {
                throw new ClassFormatException("Invalid signature: ".concat(str));
            }
            int iIndexOf5 = str.substring(0, iIndexOf4).indexOf(60);
            if (iIndexOf5 < 0) {
                wrap(threadLocal, iIndexOf4 + 1);
                return compactClassName(str.substring(1, iIndexOf4), z);
            }
            int iIndexOf6 = str.indexOf(59);
            if (iIndexOf6 < 0) {
                throw new ClassFormatException("Invalid signature: ".concat(str));
            }
            if (iIndexOf6 < iIndexOf5) {
                wrap(threadLocal, iIndexOf6 + 1);
                return compactClassName(str.substring(1, iIndexOf6), z);
            }
            StringBuilder sb2 = new StringBuilder(compactClassName(str.substring(1, iIndexOf5), z));
            sb2.append("<");
            int i3 = iIndexOf5 + 1;
            if (str.charAt(i3) != '+') {
                if (str.charAt(i3) == '-') {
                    sb2.append("? super ");
                }
                if (str.charAt(i3) == '*') {
                    sb2.append("?");
                    iUnwrap = i3 + 1;
                } else {
                    sb2.append(typeSignatureToString(str.substring(i3), z));
                    iUnwrap = i3 + unwrap(threadLocal);
                    wrap(threadLocal, iUnwrap);
                }
                while (str.charAt(iUnwrap) != '>') {
                    sb2.append(", ");
                    if (str.charAt(iUnwrap) == '+') {
                        sb2.append("? extends ");
                    } else {
                        if (str.charAt(iUnwrap) == '-') {
                            sb2.append("? super ");
                        }
                        if (str.charAt(iUnwrap) == '*') {
                            sb2.append("?");
                            iUnwrap++;
                        } else {
                            sb2.append(typeSignatureToString(str.substring(iUnwrap), z));
                            ThreadLocal<Integer> threadLocal3 = CONSUMER_CHARS;
                            iUnwrap += unwrap(threadLocal3);
                            wrap(threadLocal3, iUnwrap);
                        }
                    }
                    iUnwrap++;
                    if (str.charAt(iUnwrap) == '*') {
                        sb2.append("?");
                        iUnwrap++;
                    } else {
                        sb2.append(typeSignatureToString(str.substring(iUnwrap), z));
                        ThreadLocal<Integer> threadLocal4 = CONSUMER_CHARS;
                        iUnwrap += unwrap(threadLocal4);
                        wrap(threadLocal4, iUnwrap);
                    }
                }
                i = iUnwrap + 1;
                sb2.append(">");
                if (str.charAt(i) == '.') {
                    if (str.charAt(i) == ';') {
                        wrap(CONSUMER_CHARS, iUnwrap + 2);
                        return sb2.toString();
                    }
                    throw new ClassFormatException("Invalid signature: " + str);
                }
                sb2.append(Constants.ATTRVAL_THIS);
                sb2.append(typeSignatureToString("L" + str.substring(iUnwrap + 2), z));
                ThreadLocal<Integer> threadLocal5 = CONSUMER_CHARS;
                wrap(threadLocal5, unwrap(threadLocal5) + i);
                return sb2.toString();
            }
            sb2.append("? extends ");
            i3 = iIndexOf5 + 2;
            if (str.charAt(i3) == '*') {
                sb2.append("?");
                iUnwrap = i3 + 1;
            } else {
                sb2.append(typeSignatureToString(str.substring(i3), z));
                iUnwrap = i3 + unwrap(threadLocal);
                wrap(threadLocal, iUnwrap);
            }
            while (str.charAt(iUnwrap) != '>') {
                sb2.append(", ");
                if (str.charAt(iUnwrap) == '+') {
                    sb2.append("? extends ");
                } else {
                    if (str.charAt(iUnwrap) == '-') {
                        sb2.append("? super ");
                    }
                    if (str.charAt(iUnwrap) == '*') {
                        sb2.append("?");
                        iUnwrap++;
                    } else {
                        sb2.append(typeSignatureToString(str.substring(iUnwrap), z));
                        ThreadLocal<Integer> threadLocal6 = CONSUMER_CHARS;
                        iUnwrap += unwrap(threadLocal6);
                        wrap(threadLocal6, iUnwrap);
                    }
                }
                iUnwrap++;
                if (str.charAt(iUnwrap) == '*') {
                    sb2.append("?");
                    iUnwrap++;
                } else {
                    sb2.append(typeSignatureToString(str.substring(iUnwrap), z));
                    ThreadLocal<Integer> threadLocal7 = CONSUMER_CHARS;
                    iUnwrap += unwrap(threadLocal7);
                    wrap(threadLocal7, iUnwrap);
                }
            }
            i = iUnwrap + 1;
            sb2.append(">");
            if (str.charAt(i) == '.') {
                if (str.charAt(i) == ';') {
                    wrap(CONSUMER_CHARS, iUnwrap + 2);
                    return sb2.toString();
                }
                throw new ClassFormatException("Invalid signature: " + str);
            }
            sb2.append(Constants.ATTRVAL_THIS);
            sb2.append(typeSignatureToString("L" + str.substring(iUnwrap + 2), z));
            ThreadLocal<Integer> threadLocal8 = CONSUMER_CHARS;
            wrap(threadLocal8, unwrap(threadLocal8) + i);
            return sb2.toString();
        } catch (StringIndexOutOfBoundsException e) {
            ise.a("Invalid signature: ", str, e);
            return null;
        }
    }

    private static String typeSignaturesToString(String str, boolean z, char c) {
        int iUnwrap;
        StringBuilder sb = new StringBuilder(str.substring(0, 1));
        if (str.charAt(1) != c) {
            sb.append(typeSignatureToString(str.substring(1), z));
            iUnwrap = unwrap(CONSUMER_CHARS) + 1;
        } else {
            iUnwrap = 1;
        }
        while (str.charAt(iUnwrap) != c) {
            sb.append(", ");
            sb.append(typeSignatureToString(str.substring(iUnwrap), z));
            iUnwrap += unwrap(CONSUMER_CHARS);
        }
        wrap(CONSUMER_CHARS, iUnwrap + 1);
        sb.append(c);
        return sb.toString();
    }

    private static int unwrap(ThreadLocal<Integer> threadLocal) {
        return threadLocal.get().intValue();
    }

    private static void wrap(ThreadLocal<Integer> threadLocal, int i) {
        threadLocal.set(Integer.valueOf(i));
    }

    public static String compactClassName(String str, boolean z) {
        return compactClassName(str, "java.lang.", z);
    }

    public static String compactClassName(String str) {
        return compactClassName(str, true);
    }

    public static String methodSignatureReturnType(String str) throws ClassFormatException {
        return methodSignatureReturnType(str, true);
    }

    public static String accessToString(int i) {
        return accessToString(i, false);
    }

    public static String[] methodSignatureArgumentTypes(String str) throws ClassFormatException {
        return methodSignatureArgumentTypes(str, true);
    }

    public static class JavaReader extends FilterReader {
        public JavaReader(Reader reader) {
            super(reader);
        }

        @Override // java.io.FilterReader, java.io.Reader
        public int read() throws IOException {
            int i = ((FilterReader) this).in.read();
            if (i != 36) {
                return i;
            }
            int i2 = ((FilterReader) this).in.read();
            if (i2 < 0) {
                return -1;
            }
            if ((i2 < 48 || i2 > 57) && (i2 < 97 || i2 > 102)) {
                return Utility.MAP_CHAR[i2];
            }
            int i3 = ((FilterReader) this).in.read();
            if (i3 < 0) {
                return -1;
            }
            return Integer.parseInt(new String(new char[]{(char) i2, (char) i3}), 16);
        }

        @Override // java.io.FilterReader, java.io.Reader
        public int read(char[] cArr, int i, int i2) throws IOException {
            for (int i3 = 0; i3 < i2; i3++) {
                cArr[i + i3] = (char) read();
            }
            return i2;
        }
    }

    public static class JavaWriter extends FilterWriter {
        public JavaWriter(Writer writer) {
            super(writer);
        }

        @Override // java.io.FilterWriter, java.io.Writer
        public void write(int i) throws IOException {
            if (Utility.isJavaIdentifierPart((char) i) && i != 36) {
                ((FilterWriter) this).out.write(i);
                return;
            }
            ((FilterWriter) this).out.write(36);
            if (i >= 0 && i < 48) {
                ((FilterWriter) this).out.write(Utility.CHAR_MAP[i]);
                return;
            }
            char[] charArray = Integer.toHexString(i).toCharArray();
            int length = charArray.length;
            Writer writer = ((FilterWriter) this).out;
            if (length == 1) {
                writer.write(48);
                ((FilterWriter) this).out.write(charArray[0]);
            } else {
                writer.write(charArray[0]);
                ((FilterWriter) this).out.write(charArray[1]);
            }
        }

        @Override // java.io.FilterWriter, java.io.Writer
        public void write(char[] cArr, int i, int i2) throws IOException {
            for (int i3 = 0; i3 < i2; i3++) {
                write(cArr[i + i3]);
            }
        }

        @Override // java.io.FilterWriter, java.io.Writer
        public void write(String str, int i, int i2) throws IOException {
            write(str.toCharArray(), i, i2);
        }
    }

    public static String printArray(Object[] objArr, boolean z) {
        return printArray(objArr, z, false);
    }

    public static String printArray(Object[] objArr) {
        return printArray(objArr, true);
    }

    public static void printArray(PrintStream printStream, Object[] objArr) {
        printStream.println(printArray(objArr, true));
    }

    public static void printArray(PrintWriter printWriter, Object[] objArr) {
        printWriter.println(printArray(objArr, true));
    }

    public static String signatureToString(String str) {
        return signatureToString(str, true);
    }

    public static String methodSignatureToString(String str, String str2, String str3, boolean z) {
        return methodSignatureToString(str, str2, str3, z, null);
    }

    public static String methodSignatureToString(String str, String str2, String str3) {
        return methodSignatureToString(str, str2, str3, true);
    }

    public static String codeToString(byte[] bArr, ConstantPool constantPool, int i, int i2, boolean z) {
        StringBuilder sb = new StringBuilder(bArr.length * 20);
        try {
            ByteSequence byteSequence = new ByteSequence(bArr);
            int i3 = 0;
            for (int i4 = 0; i4 < i; i4++) {
                try {
                    codeToString(byteSequence, constantPool, z);
                } catch (Throwable th) {
                    try {
                        byteSequence.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            }
            while (byteSequence.available() > 0) {
                if (i2 < 0 || i3 < i2) {
                    sb.append(fillup(byteSequence.getIndex() + ":", 6, true, ' '));
                    sb.append(codeToString(byteSequence, constantPool, z));
                    sb.append('\n');
                }
                i3++;
            }
            byteSequence.close();
            return sb.toString();
        } catch (IOException e) {
            throw new ClassFormatException("Byte code error: ".concat(sb.toString()), e);
        }
    }

    public static String codeToString(ByteSequence byteSequence, ConstantPool constantPool) throws IOException {
        return codeToString(byteSequence, constantPool, true);
    }

    public static String codeToString(byte[] bArr, ConstantPool constantPool, int i, int i2) {
        return codeToString(bArr, constantPool, i, i2, true);
    }
}
