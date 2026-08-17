package com.sun.org.apache.bcel.internal.util;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.classfile.ClassParser;
import com.sun.org.apache.bcel.internal.classfile.ConstantValue;
import com.sun.org.apache.bcel.internal.classfile.EmptyVisitor;
import com.sun.org.apache.bcel.internal.classfile.ExceptionTable;
import com.sun.org.apache.bcel.internal.classfile.Field;
import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import com.sun.org.apache.bcel.internal.classfile.Method;
import com.sun.org.apache.bcel.internal.classfile.Utility;
import com.sun.org.apache.bcel.internal.generic.ArrayType;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.MethodGen;
import com.sun.org.apache.bcel.internal.generic.Type;
import com.sun.org.apache.xalan.internal.templates.Constants;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class BCELifier extends EmptyVisitor {
    private static final String BASE_PACKAGE = Const.class.getPackage().getName();
    private static final String CONSTANT_PREFIX = Const.class.getSimpleName().concat(Constants.ATTRVAL_THIS);
    private final JavaClass clazz;
    private final ConstantPoolGen constantPoolGen;
    private final PrintWriter printWriter;

    public enum FLAGS {
        UNKNOWN,
        CLASS,
        METHOD
    }

    public BCELifier(JavaClass javaClass, OutputStream outputStream) {
        this.clazz = javaClass;
        this.printWriter = new PrintWriter((Writer) new OutputStreamWriter(outputStream, StandardCharsets.UTF_8), false);
        this.constantPoolGen = new ConstantPoolGen(javaClass.getConstantPool());
    }

    public static void _main(String[] strArr) throws Exception {
        if (strArr.length == 1) {
            new BCELifier(getJavaClass(strArr[0]), System.out).start();
        } else {
            System.out.println("Usage: BCELifier className");
            System.out.println("\tThe class must exist on the classpath");
        }
    }

    public static JavaClass getJavaClass(String str) throws ClassNotFoundException, IOException {
        JavaClass javaClassLookupClass = com.sun.org.apache.bcel.internal.Repository.lookupClass(str);
        return javaClassLookupClass == null ? new ClassParser(str).parse() : javaClassLookupClass;
    }

    public static String printArgumentTypes(Type[] typeArr) {
        if (typeArr.length == 0) {
            return "Type.NO_ARGS";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < typeArr.length; i++) {
            sb.append(printType(typeArr[i]));
            if (i < typeArr.length - 1) {
                sb.append(", ");
            }
        }
        return "new Type[] { " + sb.toString() + " }";
    }

    private void printCreate() {
        this.printWriter.println("  public void create(OutputStream out) throws IOException {");
        if (this.clazz.getFields().length > 0) {
            this.printWriter.println("    createFields();");
        }
        Method[] methods = this.clazz.getMethods();
        int i = 0;
        while (true) {
            int length = methods.length;
            PrintWriter printWriter = this.printWriter;
            if (i >= length) {
                printWriter.println("    _cg.getJavaClass().dump(out);");
                this.printWriter.println("  }");
                this.printWriter.println();
                return;
            } else {
                printWriter.println("    createMethod_" + i + "();");
                i++;
            }
        }
    }

    public static String printFlags(int i, FLAGS flags) {
        if (i == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        int i2 = 1;
        int i3 = 0;
        while (i2 <= 32768) {
            if ((i & i2) != 0) {
                if (i2 == 32 && flags == FLAGS.CLASS) {
                    sb.append(CONSTANT_PREFIX);
                    sb.append("ACC_SUPER | ");
                } else if (i2 == 64 && flags == FLAGS.METHOD) {
                    sb.append(CONSTANT_PREFIX);
                    sb.append("ACC_BRIDGE | ");
                } else if (i2 == 128 && flags == FLAGS.METHOD) {
                    sb.append(CONSTANT_PREFIX);
                    sb.append("ACC_VARARGS | ");
                } else if (i3 < Const.ACCESS_NAMES_LENGTH) {
                    sb.append(CONSTANT_PREFIX);
                    sb.append("ACC_");
                    sb.append(Const.getAccessName(i3).toUpperCase(Locale.ENGLISH));
                    sb.append(" | ");
                } else {
                    sb.append(String.format(CONSTANT_PREFIX + "ACC_BIT %x | ", Integer.valueOf(i2)));
                }
            }
            i2 <<= 1;
            i3++;
        }
        String string = sb.toString();
        return string.substring(0, string.length() - 3);
    }

    private void printMain() {
        String className = this.clazz.getClassName();
        this.printWriter.println("  public static void main(String[] args) throws Exception {");
        this.printWriter.println("    " + className + "Creator creator = new " + className + "Creator();");
        PrintWriter printWriter = this.printWriter;
        StringBuilder sb = new StringBuilder("    creator.create(new FileOutputStream(\"");
        sb.append(className);
        sb.append(".class\"));");
        printWriter.println(sb.toString());
        this.printWriter.println("  }");
    }

    public static String printType(String str) {
        Type type = Type.getType(str);
        byte type2 = type.getType();
        if (type2 <= 12) {
            return "Type." + Const.getTypeName(type2).toUpperCase(Locale.ENGLISH);
        }
        if (type.toString().equals("java.lang.String")) {
            return "Type.STRING";
        }
        if (type.toString().equals(com.sun.org.apache.xalan.internal.xsltc.compiler.Constants.OBJECT_CLASS)) {
            return "Type.OBJECT";
        }
        if (type.toString().equals(com.sun.org.apache.xalan.internal.xsltc.compiler.Constants.STRING_BUFFER_CLASS)) {
            return "Type.STRINGBUFFER";
        }
        if (!(type instanceof ArrayType)) {
            return "new ObjectType(\"" + Utility.signatureToString(str, false) + "\")";
        }
        ArrayType arrayType = (ArrayType) type;
        return "new ArrayType(" + printType(arrayType.getBasicType()) + ", " + arrayType.getDimensions() + ")";
    }

    public void start() {
        visitJavaClass(this.clazz);
        this.printWriter.flush();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.EmptyVisitor, com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitField(Field field) {
        this.printWriter.println();
        this.printWriter.println("    field = new FieldGen(" + printFlags(field.getAccessFlags()) + ", " + printType(field.getSignature()) + ", \"" + field.getName() + "\", _cp);");
        ConstantValue constantValue = field.getConstantValue();
        if (constantValue != null) {
            this.printWriter.print("    field.setInitValue(");
            if (field.getType() == Type.CHAR) {
                this.printWriter.print("(char)");
            }
            if (field.getType() == Type.SHORT) {
                this.printWriter.print("(short)");
            }
            if (field.getType() == Type.BYTE) {
                this.printWriter.print("(byte)");
            }
            this.printWriter.print(constantValue);
            if (field.getType() == Type.LONG) {
                this.printWriter.print("L");
            }
            if (field.getType() == Type.FLOAT) {
                this.printWriter.print("F");
            }
            if (field.getType() == Type.DOUBLE) {
                this.printWriter.print("D");
            }
            this.printWriter.println(");");
        }
        this.printWriter.println("    _cg.addField(field.getField());");
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.EmptyVisitor, com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitJavaClass(JavaClass javaClass) {
        String className = javaClass.getClassName();
        String superclassName = javaClass.getSuperclassName();
        String packageName = javaClass.getPackageName();
        String strPrintArray = Utility.printArray(javaClass.getInterfaceNames(), false, true);
        if (packageName != null && !packageName.trim().isEmpty()) {
            className = className.substring(packageName.length() + 1);
            this.printWriter.println("package " + packageName + ";");
            this.printWriter.println();
        }
        PrintWriter printWriter = this.printWriter;
        StringBuilder sb = new StringBuilder("import ");
        String str = BASE_PACKAGE;
        sb.append(str);
        sb.append(".generic.*;");
        printWriter.println(sb.toString());
        this.printWriter.println("import " + str + ".classfile.*;");
        this.printWriter.println("import " + str + ".*;");
        this.printWriter.println("import java.io.*;");
        this.printWriter.println();
        this.printWriter.println("public class " + className + "Creator {");
        this.printWriter.println("  private InstructionFactory _factory;");
        this.printWriter.println("  private ConstantPoolGen    _cp;");
        this.printWriter.println("  private ClassGen           _cg;");
        this.printWriter.println();
        this.printWriter.println("  public " + className + "Creator() {");
        PrintWriter printWriter2 = this.printWriter;
        StringBuilder sb2 = new StringBuilder("    _cg = new ClassGen(\"");
        if (!packageName.isEmpty()) {
            className = packageName + Constants.ATTRVAL_THIS + className;
        }
        sb2.append(className);
        sb2.append("\", \"");
        sb2.append(superclassName);
        sb2.append("\", \"");
        sb2.append(javaClass.getSourceFileName());
        sb2.append("\", ");
        sb2.append(printFlags(javaClass.getAccessFlags(), FLAGS.CLASS));
        sb2.append(", new String[] { ");
        sb2.append(strPrintArray);
        sb2.append(" });");
        printWriter2.println(sb2.toString());
        this.printWriter.println("    _cg.setMajor(" + javaClass.getMajor() + ");");
        this.printWriter.println("    _cg.setMinor(" + javaClass.getMinor() + ");");
        this.printWriter.println();
        this.printWriter.println("    _cp = _cg.getConstantPool();");
        this.printWriter.println("    _factory = new InstructionFactory(_cg, _cp);");
        this.printWriter.println("  }");
        this.printWriter.println();
        printCreate();
        Field[] fields = javaClass.getFields();
        if (fields.length > 0) {
            this.printWriter.println("  private void createFields() {");
            this.printWriter.println("    FieldGen field;");
            for (Field field : fields) {
                field.accept(this);
            }
            this.printWriter.println("  }");
            this.printWriter.println();
        }
        Method[] methods = javaClass.getMethods();
        for (int i = 0; i < methods.length; i++) {
            this.printWriter.println("  private void createMethod_" + i + "() {");
            methods[i].accept(this);
            this.printWriter.println("  }");
            this.printWriter.println();
        }
        printMain();
        this.printWriter.println("}");
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.EmptyVisitor, com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitMethod(Method method) {
        MethodGen methodGen = new MethodGen(method, this.clazz.getClassName(), this.constantPoolGen);
        this.printWriter.println("    InstructionList il = new InstructionList();");
        PrintWriter printWriter = this.printWriter;
        StringBuilder sb = new StringBuilder("    MethodGen method = new MethodGen(");
        sb.append(printFlags(method.getAccessFlags(), FLAGS.METHOD));
        sb.append(", ");
        sb.append(printType(methodGen.getReturnType()));
        sb.append(", ");
        sb.append(printArgumentTypes(methodGen.getArgumentTypes()));
        sb.append(", new String[] { ");
        sb.append(Utility.printArray(methodGen.getArgumentNames(), false, true));
        sb.append(" }, \"");
        sb.append(method.getName());
        sb.append("\", \"");
        sb.append(this.clazz.getClassName());
        sb.append("\", il, _cp);");
        printWriter.println(sb.toString());
        ExceptionTable exceptionTable = method.getExceptionTable();
        if (exceptionTable != null) {
            for (String str : exceptionTable.getExceptionNames()) {
                this.printWriter.print("    method.addException(\"");
                this.printWriter.print(str);
                this.printWriter.println("\");");
            }
        }
        this.printWriter.println();
        new BCELFactory(methodGen, this.printWriter).start();
        this.printWriter.println("    method.setMaxStack();");
        this.printWriter.println("    method.setMaxLocals();");
        this.printWriter.println("    _cg.addMethod(method.getMethod());");
        this.printWriter.println("    il.dispose();");
    }

    public static String printType(Type type) {
        return printType(type.getSignature());
    }

    public static String printFlags(int i) {
        return printFlags(i, FLAGS.UNKNOWN);
    }
}
