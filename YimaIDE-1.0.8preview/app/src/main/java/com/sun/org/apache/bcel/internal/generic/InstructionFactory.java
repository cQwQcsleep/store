package com.sun.org.apache.bcel.internal.generic;

import com.sun.jna.platform.win32.COM.tlb.imp.TlbConst;
import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Constants;
import defpackage.aca;
import defpackage.h0f;
import defpackage.nrd;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class InstructionFactory {
    private static final String APPEND = "append";
    private static final String FQCN_STRING_BUFFER = "java.lang.StringBuffer";
    private static final MethodObject[] appendMethodObjects;
    private static final String[] shortNames = {"C", "F", "D", "B", "S", "I", "L"};

    @Deprecated
    protected ClassGen cg;

    @Deprecated
    protected ConstantPoolGen cp;

    public static class MethodObject {
        final Type[] argTypes;
        final String className;
        final String name;
        final Type resultType;

        public MethodObject(String str, String str2, Type type, Type[] typeArr) {
            this.className = str;
            this.name = str2;
            this.resultType = type;
            this.argTypes = typeArr;
        }
    }

    static {
        ObjectType objectType = Type.STRINGBUFFER;
        MethodObject methodObject = new MethodObject("java.lang.StringBuffer", APPEND, objectType, new Type[]{Type.STRING});
        MethodObject methodObject2 = new MethodObject("java.lang.StringBuffer", APPEND, objectType, new Type[]{Type.OBJECT});
        MethodObject methodObject3 = new MethodObject("java.lang.StringBuffer", APPEND, objectType, new Type[]{Type.BOOLEAN});
        MethodObject methodObject4 = new MethodObject("java.lang.StringBuffer", APPEND, objectType, new Type[]{Type.CHAR});
        MethodObject methodObject5 = new MethodObject("java.lang.StringBuffer", APPEND, objectType, new Type[]{Type.FLOAT});
        MethodObject methodObject6 = new MethodObject("java.lang.StringBuffer", APPEND, objectType, new Type[]{Type.DOUBLE});
        BasicType basicType = Type.INT;
        appendMethodObjects = new MethodObject[]{methodObject, methodObject2, null, null, methodObject3, methodObject4, methodObject5, methodObject6, new MethodObject("java.lang.StringBuffer", APPEND, objectType, new Type[]{basicType}), new MethodObject("java.lang.StringBuffer", APPEND, objectType, new Type[]{basicType}), new MethodObject("java.lang.StringBuffer", APPEND, objectType, new Type[]{basicType}), new MethodObject("java.lang.StringBuffer", APPEND, objectType, new Type[]{Type.LONG})};
    }

    public InstructionFactory(ClassGen classGen) {
        this(classGen, classGen.getConstantPool());
    }

    public static ArrayInstruction createArrayLoad(Type type) {
        switch (type.getType()) {
            case 4:
            case 8:
                return InstructionConst.BALOAD;
            case 5:
                return InstructionConst.CALOAD;
            case 6:
                return InstructionConst.FALOAD;
            case 7:
                return InstructionConst.DALOAD;
            case 9:
                return InstructionConst.SALOAD;
            case 10:
                return InstructionConst.IALOAD;
            case 11:
                return InstructionConst.LALOAD;
            case 12:
            default:
                aca.a("Invalid type ", type);
                return null;
            case 13:
            case 14:
                return InstructionConst.AALOAD;
        }
    }

    public static ArrayInstruction createArrayStore(Type type) {
        switch (type.getType()) {
            case 4:
            case 8:
                return InstructionConst.BASTORE;
            case 5:
                return InstructionConst.CASTORE;
            case 6:
                return InstructionConst.FASTORE;
            case 7:
                return InstructionConst.DASTORE;
            case 9:
                return InstructionConst.SASTORE;
            case 10:
                return InstructionConst.IASTORE;
            case 11:
                return InstructionConst.LASTORE;
            case 12:
            default:
                aca.a("Invalid type ", type);
                return null;
            case 13:
            case 14:
                return InstructionConst.AASTORE;
        }
    }

    private static ArithmeticInstruction createBinaryDoubleOp(char c) {
        if (c == '%') {
            return InstructionConst.DREM;
        }
        if (c == '-') {
            return InstructionConst.DSUB;
        }
        if (c == '/') {
            return InstructionConst.DDIV;
        }
        if (c == '*') {
            return InstructionConst.DMUL;
        }
        if (c == '+') {
            return InstructionConst.DADD;
        }
        a.a("Invalid operand ", c);
        return null;
    }

    private static ArithmeticInstruction createBinaryFloatOp(char c) {
        if (c == '%') {
            return InstructionConst.FREM;
        }
        if (c == '-') {
            return InstructionConst.FSUB;
        }
        if (c == '/') {
            return InstructionConst.FDIV;
        }
        if (c == '*') {
            return InstructionConst.FMUL;
        }
        if (c == '+') {
            return InstructionConst.FADD;
        }
        a.a("Invalid operand ", c);
        return null;
    }

    private static ArithmeticInstruction createBinaryIntOp(char c, String str) {
        if (c == '%') {
            return InstructionConst.IREM;
        }
        if (c == '&') {
            return InstructionConst.IAND;
        }
        if (c == '*') {
            return InstructionConst.IMUL;
        }
        if (c == '+') {
            return InstructionConst.IADD;
        }
        if (c == '-') {
            return InstructionConst.ISUB;
        }
        if (c == '/') {
            return InstructionConst.IDIV;
        }
        if (c == '<') {
            return InstructionConst.ISHL;
        }
        if (c == '>') {
            return str.equals(">>>") ? InstructionConst.IUSHR : InstructionConst.ISHR;
        }
        if (c == '^') {
            return InstructionConst.IXOR;
        }
        if (c == '|') {
            return InstructionConst.IOR;
        }
        aca.a("Invalid operand ", str);
        return null;
    }

    private static ArithmeticInstruction createBinaryLongOp(char c, String str) {
        if (c == '%') {
            return InstructionConst.LREM;
        }
        if (c == '&') {
            return InstructionConst.LAND;
        }
        if (c == '*') {
            return InstructionConst.LMUL;
        }
        if (c == '+') {
            return InstructionConst.LADD;
        }
        if (c == '-') {
            return InstructionConst.LSUB;
        }
        if (c == '/') {
            return InstructionConst.LDIV;
        }
        if (c == '<') {
            return InstructionConst.LSHL;
        }
        if (c == '>') {
            return str.equals(">>>") ? InstructionConst.LUSHR : InstructionConst.LSHR;
        }
        if (c == '^') {
            return InstructionConst.LXOR;
        }
        if (c == '|') {
            return InstructionConst.LOR;
        }
        aca.a("Invalid operand ", str);
        return null;
    }

    public static ArithmeticInstruction createBinaryOperation(String str, Type type) {
        char cCharAt = str.charAt(0);
        switch (type.getType()) {
            case 5:
            case 8:
            case 9:
            case 10:
                return createBinaryIntOp(cCharAt, str);
            case 6:
                return createBinaryFloatOp(cCharAt);
            case 7:
                return createBinaryDoubleOp(cCharAt);
            case 11:
                return createBinaryLongOp(cCharAt, str);
            default:
                aca.a("Invalid type ", type);
                return null;
        }
    }

    public static BranchInstruction createBranchInstruction(short s, InstructionHandle instructionHandle) {
        switch (s) {
            case 153:
                return new IFEQ(instructionHandle);
            case 154:
                return new IFNE(instructionHandle);
            case 155:
                return new IFLT(instructionHandle);
            case 156:
                return new IFGE(instructionHandle);
            case 157:
                return new IFGT(instructionHandle);
            case 158:
                return new IFLE(instructionHandle);
            case 159:
                return new IF_ICMPEQ(instructionHandle);
            case 160:
                return new IF_ICMPNE(instructionHandle);
            case 161:
                return new IF_ICMPLT(instructionHandle);
            case 162:
                return new IF_ICMPGE(instructionHandle);
            case 163:
                return new IF_ICMPGT(instructionHandle);
            case 164:
                return new IF_ICMPLE(instructionHandle);
            case 165:
                return new IF_ACMPEQ(instructionHandle);
            case 166:
                return new IF_ACMPNE(instructionHandle);
            case 167:
                return new GOTO(instructionHandle);
            case 168:
                return new JSR(instructionHandle);
            default:
                switch (s) {
                    case 198:
                        return new IFNULL(instructionHandle);
                    case 199:
                        return new IFNONNULL(instructionHandle);
                    case 200:
                        return new GOTO_W(instructionHandle);
                    case 201:
                        return new JSR_W(instructionHandle);
                    default:
                        qf1.a("Invalid opcode: ", s);
                        return null;
                }
        }
    }

    public static StackInstruction createDup(int i) {
        return i == 2 ? InstructionConst.DUP2 : InstructionConst.DUP;
    }

    public static StackInstruction createDup_1(int i) {
        return i == 2 ? InstructionConst.DUP2_X1 : InstructionConst.DUP_X1;
    }

    public static StackInstruction createDup_2(int i) {
        return i == 2 ? InstructionConst.DUP2_X2 : InstructionConst.DUP_X2;
    }

    public static LocalVariableInstruction createLoad(Type type, int i) {
        switch (type.getType()) {
            case 4:
            case 5:
            case 8:
            case 9:
            case 10:
                return new ILOAD(i);
            case 6:
                return new FLOAD(i);
            case 7:
                return new DLOAD(i);
            case 11:
                return new LLOAD(i);
            case 12:
            default:
                aca.a("Invalid type ", type);
                return null;
            case 13:
            case 14:
                return new ALOAD(i);
        }
    }

    public static Instruction createNull(Type type) {
        switch (type.getType()) {
            case 4:
            case 5:
            case 8:
            case 9:
            case 10:
                return InstructionConst.ICONST_0;
            case 6:
                return InstructionConst.FCONST_0;
            case 7:
                return InstructionConst.DCONST_0;
            case 11:
                return InstructionConst.LCONST_0;
            case 12:
                return InstructionConst.NOP;
            case 13:
            case 14:
                return InstructionConst.ACONST_NULL;
            default:
                aca.a("Invalid type: ", type);
                return null;
        }
    }

    public static StackInstruction createPop(int i) {
        return i == 2 ? InstructionConst.POP2 : InstructionConst.POP;
    }

    public static ReturnInstruction createReturn(Type type) {
        switch (type.getType()) {
            case 4:
            case 5:
            case 8:
            case 9:
            case 10:
                return InstructionConst.IRETURN;
            case 6:
                return InstructionConst.FRETURN;
            case 7:
                return InstructionConst.DRETURN;
            case 11:
                return InstructionConst.LRETURN;
            case 12:
                return InstructionConst.RETURN;
            case 13:
            case 14:
                return InstructionConst.ARETURN;
            default:
                aca.a("Invalid type: ", type);
                return null;
        }
    }

    public static LocalVariableInstruction createStore(Type type, int i) {
        switch (type.getType()) {
            case 4:
            case 5:
            case 8:
            case 9:
            case 10:
                return new ISTORE(i);
            case 6:
                return new FSTORE(i);
            case 7:
                return new DSTORE(i);
            case 11:
                return new LSTORE(i);
            case 12:
            default:
                aca.a("Invalid type ", type);
                return null;
            case 13:
            case 14:
                return new ASTORE(i);
        }
    }

    public static Instruction createThis() {
        return new ALOAD(0);
    }

    private static boolean isString(Type type) {
        return (type instanceof ObjectType) && ((ObjectType) type).getClassName().equals("java.lang.String");
    }

    public Instruction createAppend(Type type) {
        byte type2 = type.getType();
        if (isString(type)) {
            return createInvoke(appendMethodObjects[0], Const.INVOKEVIRTUAL);
        }
        switch (type2) {
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                return createInvoke(appendMethodObjects[type2], Const.INVOKEVIRTUAL);
            case 12:
            default:
                aca.a("No append for this type? ", type);
                return null;
            case 13:
            case 14:
                return createInvoke(appendMethodObjects[1], Const.INVOKEVIRTUAL);
        }
    }

    public Instruction createCast(Type type, Type type2) {
        if (!(type instanceof BasicType) || !(type2 instanceof BasicType)) {
            if ((type instanceof ReferenceType) && (type2 instanceof ReferenceType)) {
                return type2 instanceof ArrayType ? new CHECKCAST(this.cp.addArrayClass((ArrayType) type2)) : new CHECKCAST(this.cp.addClass(((ObjectType) type2).getClassName()));
            }
            h0f.a("Cannot cast ", type, " to ", type2);
            return null;
        }
        byte type3 = type2.getType();
        byte type4 = type.getType();
        if (type3 == 11 && (type4 == 5 || type4 == 8 || type4 == 9)) {
            type4 = 10;
        }
        StringBuilder sb = new StringBuilder("com.sun.org.apache.bcel.internal.generic.");
        String[] strArr = shortNames;
        sb.append(strArr[type4 - 5]);
        sb.append(TlbConst.TYPELIB_MAJOR_VERSION_OFFICE);
        sb.append(strArr[type3 - 5]);
        String string = sb.toString();
        try {
            return (Instruction) Class.forName(string).getDeclaredConstructor(null).newInstance(null);
        } catch (Exception e) {
            nrd.a("Could not find instruction: ".concat(string), e);
            return null;
        }
    }

    public CHECKCAST createCheckCast(ReferenceType referenceType) {
        return referenceType instanceof ArrayType ? new CHECKCAST(this.cp.addArrayClass((ArrayType) referenceType)) : new CHECKCAST(this.cp.addClass((ObjectType) referenceType));
    }

    public Instruction createConstant(Object obj) {
        PUSH push;
        if (obj instanceof Number) {
            push = new PUSH(this.cp, (Number) obj);
        } else if (obj instanceof String) {
            push = new PUSH(this.cp, (String) obj);
        } else if (obj instanceof Boolean) {
            push = new PUSH(this.cp, (Boolean) obj);
        } else {
            if (!(obj instanceof Character)) {
                throw new ClassGenException("Illegal type: " + obj.getClass());
            }
            push = new PUSH(this.cp, (Character) obj);
        }
        return push.getInstruction();
    }

    public FieldInstruction createFieldAccess(String str, String str2, Type type, short s) {
        int iAddFieldref = this.cp.addFieldref(str, str2, type.getSignature());
        switch (s) {
            case 178:
                return new GETSTATIC(iAddFieldref);
            case 179:
                return new PUTSTATIC(iAddFieldref);
            case 180:
                return new GETFIELD(iAddFieldref);
            case 181:
                return new PUTFIELD(iAddFieldref);
            default:
                qf1.a("Unknown getfield kind:", s);
                return null;
        }
    }

    public GETFIELD createGetField(String str, String str2, Type type) {
        return new GETFIELD(this.cp.addFieldref(str, str2, type.getSignature()));
    }

    public GETSTATIC createGetStatic(String str, String str2, Type type) {
        return new GETSTATIC(this.cp.addFieldref(str, str2, type.getSignature()));
    }

    public INSTANCEOF createInstanceOf(ReferenceType referenceType) {
        return referenceType instanceof ArrayType ? new INSTANCEOF(this.cp.addArrayClass((ArrayType) referenceType)) : new INSTANCEOF(this.cp.addClass((ObjectType) referenceType));
    }

    public InvokeInstruction createInvoke(String str, String str2, Type type, Type[] typeArr, short s, boolean z) {
        if (s != 183 && s != 182 && s != 184 && s != 185 && s != 186) {
            qf1.a("Unknown invoke kind: ", s);
            return null;
        }
        String methodSignature = Type.getMethodSignature(type, typeArr);
        int size = 0;
        for (Type type2 : typeArr) {
            size += type2.getSize();
        }
        ConstantPoolGen constantPoolGen = this.cp;
        int iAddInterfaceMethodref = z ? constantPoolGen.addInterfaceMethodref(str, str2, methodSignature) : constantPoolGen.addMethodref(str, str2, methodSignature);
        switch (s) {
            case 182:
                return new INVOKEVIRTUAL(iAddInterfaceMethodref);
            case 183:
                return new INVOKESPECIAL(iAddInterfaceMethodref);
            case 184:
                return new INVOKESTATIC(iAddInterfaceMethodref);
            case 185:
                return new INVOKEINTERFACE(iAddInterfaceMethodref, size + 1);
            case 186:
                return new INVOKEDYNAMIC(iAddInterfaceMethodref);
            default:
                pu7.a("Unknown invoke kind: ", s);
                return null;
        }
    }

    public NEW createNew(ObjectType objectType) {
        return new NEW(this.cp.addClass(objectType));
    }

    public Instruction createNewArray(Type type, short s) {
        if (s != 1) {
            return new MULTIANEWARRAY(this.cp.addArrayClass(type instanceof ArrayType ? (ArrayType) type : new ArrayType(type, s)), s);
        }
        if (type instanceof ObjectType) {
            return new ANEWARRAY(this.cp.addClass((ObjectType) type));
        }
        return type instanceof ArrayType ? new ANEWARRAY(this.cp.addArrayClass((ArrayType) type)) : new NEWARRAY(type.getType());
    }

    public InstructionList createPrintln(String str) {
        InstructionList instructionList = new InstructionList();
        instructionList.append(createGetStatic("java.lang.System", "out", Type.getType("Ljava/io/PrintStream;")));
        instructionList.append(new PUSH(this.cp, str));
        instructionList.append(createInvoke(new MethodObject("java.io.PrintStream", "println", Type.VOID, new Type[]{Type.getType(Constants.STRING_SIG)}), Const.INVOKEVIRTUAL));
        return instructionList;
    }

    public PUTFIELD createPutField(String str, String str2, Type type) {
        return new PUTFIELD(this.cp.addFieldref(str, str2, type.getSignature()));
    }

    public PUTSTATIC createPutStatic(String str, String str2, Type type) {
        return new PUTSTATIC(this.cp.addFieldref(str, str2, type.getSignature()));
    }

    public ClassGen getClassGen() {
        return this.cg;
    }

    public ConstantPoolGen getConstantPool() {
        return this.cp;
    }

    public void setClassGen(ClassGen classGen) {
        this.cg = classGen;
    }

    public void setConstantPool(ConstantPoolGen constantPoolGen) {
        this.cp = constantPoolGen;
    }

    public InstructionFactory(ClassGen classGen, ConstantPoolGen constantPoolGen) {
        this.cg = classGen;
        this.cp = constantPoolGen;
    }

    public InstructionFactory(ConstantPoolGen constantPoolGen) {
        this(null, constantPoolGen);
    }

    public NEW createNew(String str) {
        return createNew(ObjectType.getInstance(str));
    }

    public InvokeInstruction createInvoke(String str, String str2, Type type, Type[] typeArr, short s) {
        return createInvoke(str, str2, type, typeArr, s, s == 185);
    }

    private InvokeInstruction createInvoke(MethodObject methodObject, short s) {
        return createInvoke(methodObject.className, methodObject.name, methodObject.resultType, methodObject.argTypes, s);
    }
}
