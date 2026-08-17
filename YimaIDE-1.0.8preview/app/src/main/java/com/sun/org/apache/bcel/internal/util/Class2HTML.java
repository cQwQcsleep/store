package com.sun.org.apache.bcel.internal.util;

import com.intellij.psi.PsiKeyword;
import com.sun.org.apache.bcel.internal.classfile.Attribute;
import com.sun.org.apache.bcel.internal.classfile.ClassParser;
import com.sun.org.apache.bcel.internal.classfile.ConstantPool;
import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import com.sun.org.apache.bcel.internal.classfile.Method;
import com.sun.org.apache.bcel.internal.classfile.Utility;
import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xml.internal.serializer.SerializerConstants;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Class2HTML {
    private static final Set<String> basicTypes;
    private static String className;
    private static String classPackage;
    private static ConstantPool constantPool;
    private final String dir;
    private final JavaClass javaClass;

    static {
        HashSet hashSet = new HashSet();
        basicTypes = hashSet;
        hashSet.add("int");
        hashSet.add("short");
        hashSet.add("boolean");
        hashSet.add(PsiKeyword.VOID);
        hashSet.add(PsiKeyword.CHAR);
        hashSet.add("byte");
        hashSet.add("long");
        hashSet.add("double");
        hashSet.add("float");
    }

    private Class2HTML(JavaClass javaClass, String str, Charset charset) throws Throwable {
        AttributeHTML attributeHTML;
        Method[] methods = javaClass.getMethods();
        this.javaClass = javaClass;
        this.dir = str;
        className = javaClass.getClassName();
        constantPool = javaClass.getConstantPool();
        int iLastIndexOf = className.lastIndexOf(46);
        if (iLastIndexOf > -1) {
            classPackage = className.substring(0, iLastIndexOf);
        } else {
            classPackage = "";
        }
        ConstantHTML constantHTML = new ConstantHTML(str, className, classPackage, methods, constantPool, charset);
        AttributeHTML attributeHTML2 = new AttributeHTML(str, className, constantPool, constantHTML, charset);
        try {
            try {
                new MethodHTML(str, className, methods, javaClass.getFields(), constantHTML, attributeHTML2, charset);
                attributeHTML = attributeHTML2;
                try {
                    writeMainHTML(attributeHTML, charset);
                    new CodeHTML(str, className, methods, constantPool, constantHTML, charset);
                    attributeHTML.close();
                } catch (Throwable th) {
                    th = th;
                    Throwable th2 = th;
                    try {
                        attributeHTML.close();
                        throw th2;
                    } catch (Throwable th3) {
                        th2.addSuppressed(th3);
                        throw th2;
                    }
                }
            } catch (Throwable th4) {
                th = th4;
                attributeHTML = attributeHTML2;
            }
        } catch (Throwable th5) {
            th = th5;
            attributeHTML = attributeHTML2;
        }
    }

    public static void _main(String[] strArr) throws IOException {
        String[] strArr2 = new String[strArr.length];
        char c = File.separatorChar;
        String str = Constants.ATTRVAL_THIS + c;
        String str2 = null;
        int i = 0;
        int i2 = 0;
        while (i < strArr.length) {
            if (strArr[i].charAt(0) != '-') {
                strArr2[i2] = strArr[i];
                i2++;
            } else if (strArr[i].equals("-d")) {
                i++;
                str = strArr[i];
                if (!str.endsWith("" + c)) {
                    str = str + c;
                }
                File file = new File(str);
                if (!file.isDirectory() && !file.mkdirs() && !file.isDirectory()) {
                    System.out.println("Tried to create the directory " + str + " but failed");
                }
            } else if (strArr[i].equals("-zip")) {
                i++;
                str2 = strArr[i];
            } else {
                System.out.println("Unknown option " + strArr[i]);
            }
            i++;
        }
        if (i2 == 0) {
            System.err.println("Class2HTML: No input files specified.");
            return;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            System.out.print("Processing " + strArr2[i3] + "...");
            new Class2HTML((str2 == null ? new ClassParser(strArr2[i3]) : new ClassParser(str2, strArr2[i3])).parse(), str);
            System.out.println("Done.");
        }
    }

    public static String referenceClass(int i) {
        return "<A HREF=\"" + className + "_cp.html#cp" + i + "\" TARGET=ConstantPool>" + Utility.compactClassName(Utility.compactClassName(constantPool.getConstantString(i, (byte) 7)), classPackage + Constants.ATTRVAL_THIS, true) + "</A>";
    }

    public static String referenceType(String str) {
        String strCompactClassName = Utility.compactClassName(Utility.compactClassName(str), classPackage + Constants.ATTRVAL_THIS, true);
        int iIndexOf = str.indexOf(91);
        String strSubstring = iIndexOf > -1 ? str.substring(0, iIndexOf) : str;
        if (basicTypes.contains(strSubstring)) {
            return "<FONT COLOR=\"#00FF00\">" + str + "</FONT>";
        }
        return "<A HREF=\"" + strSubstring + ".html\" TARGET=_top>" + strCompactClassName + "</A>";
    }

    public static String toHTML(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\n') {
                sb.append("\\n");
            } else if (cCharAt == '\r') {
                sb.append("\\r");
            } else if (cCharAt == '<') {
                sb.append(SerializerConstants.ENTITY_LT);
            } else if (cCharAt != '>') {
                sb.append(cCharAt);
            } else {
                sb.append(SerializerConstants.ENTITY_GT);
            }
        }
        return sb.toString();
    }

    private void writeMainHTML(AttributeHTML attributeHTML, Charset charset) throws UnsupportedEncodingException, FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(this.dir + className + ".html", charset.name());
        try {
            printWriter.println("<HTML>\n<HEAD><TITLE>Documentation for " + className + "</TITLE></HEAD>\n<FRAMESET BORDER=1 cols=\"30%,*\">\n<FRAMESET BORDER=1 rows=\"80%,*\">\n<FRAME NAME=\"ConstantPool\" SRC=\"" + className + "_cp.html\"\n MARGINWIDTH=\"0\" MARGINHEIGHT=\"0\" FRAMEBORDER=\"1\" SCROLLING=\"AUTO\">\n<FRAME NAME=\"Attributes\" SRC=\"" + className + "_attributes.html\"\n MARGINWIDTH=\"0\" MARGINHEIGHT=\"0\" FRAMEBORDER=\"1\" SCROLLING=\"AUTO\">\n</FRAMESET>\n<FRAMESET BORDER=1 rows=\"80%,*\">\n<FRAME NAME=\"Code\" SRC=\"" + className + "_code.html\"\n MARGINWIDTH=0 MARGINHEIGHT=0 FRAMEBORDER=1 SCROLLING=\"AUTO\">\n<FRAME NAME=\"Methods\" SRC=\"" + className + "_methods.html\"\n MARGINWIDTH=0 MARGINHEIGHT=0 FRAMEBORDER=1 SCROLLING=\"AUTO\">\n</FRAMESET></FRAMESET></HTML>");
            printWriter.close();
            Attribute[] attributes = this.javaClass.getAttributes();
            for (int i = 0; i < attributes.length; i++) {
                attributeHTML.writeAttribute(attributes[i], "class" + i);
            }
        } catch (Throwable th) {
            try {
                printWriter.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public Class2HTML(JavaClass javaClass, String str) throws IOException {
        this(javaClass, str, StandardCharsets.UTF_8);
    }
}
