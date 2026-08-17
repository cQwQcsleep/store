package com.sun.org.apache.xalan.internal.xsltc.compiler.util;

import com.sun.org.apache.bcel.internal.generic.BranchInstruction;
import com.sun.org.apache.bcel.internal.generic.Instruction;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Constants;
import com.sun.org.apache.xalan.internal.xsltc.compiler.FlowList;
import java.security.AccessController;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Type implements Constants {
    public static final Type Int = new IntType();
    public static final Type Real = new RealType();
    public static final Type Boolean = new BooleanType();
    public static final Type NodeSet = new NodeSetType();
    public static final Type String = new StringType();
    public static final Type ResultTree = new ResultTreeType();
    public static final Type Reference = new ReferenceType();
    public static final Type Void = new VoidType();
    public static final Type Object = new ObjectType((Class<?>) Object.class);
    public static final Type ObjectString = new ObjectType((Class<?>) String.class);
    public static final Type Node = new NodeType(-1);
    public static final Type Root = new NodeType(9);
    public static final Type Element = new NodeType(1);
    public static final Type Attribute = new NodeType(2);
    public static final Type Text = new NodeType(3);
    public static final Type Comment = new NodeType(8);
    public static final Type Processing_Instruction = new NodeType(7);

    public static Type newObjectType(String str) {
        if (str == Constants.OBJECT_CLASS) {
            return Object;
        }
        if (str == "java.lang.String") {
            return ObjectString;
        }
        AccessController.getContext().checkPermission(new RuntimePermission("getContextClassLoader"));
        return new ObjectType(str);
    }

    public Instruction ADD() {
        return null;
    }

    public Instruction CMP(boolean z) {
        return null;
    }

    public Instruction DIV() {
        return null;
    }

    public Instruction DUP() {
        return Constants.DUP;
    }

    public BranchInstruction GE(boolean z) {
        return null;
    }

    public BranchInstruction GT(boolean z) {
        return null;
    }

    public BranchInstruction LE(boolean z) {
        return null;
    }

    public Instruction LOAD(int i) {
        return null;
    }

    public BranchInstruction LT(boolean z) {
        return null;
    }

    public Instruction MUL() {
        return null;
    }

    public Instruction NEG() {
        return null;
    }

    public Instruction POP() {
        return Constants.POP;
    }

    public Instruction REM() {
        return null;
    }

    public Instruction STORE(int i) {
        return null;
    }

    public Instruction SUB() {
        return null;
    }

    public int distanceTo(Type type) {
        return type == this ? 0 : Integer.MAX_VALUE;
    }

    public String getClassName() {
        return "";
    }

    public abstract boolean identicalTo(Type type);

    public boolean implementedAsMethod() {
        return false;
    }

    public boolean isNumber() {
        return false;
    }

    public boolean isSimple() {
        return false;
    }

    public abstract com.sun.org.apache.bcel.internal.generic.Type toJCType();

    public abstract String toSignature();

    public abstract String toString();

    public void translateBox(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        classGenerator.getParser().reportError(2, new ErrorMsg("DATA_CONVERSION_ERR", toString(), "[" + toString() + "]"));
    }

    public void translateFrom(ClassGenerator classGenerator, MethodGenerator methodGenerator, Class<?> cls) {
        classGenerator.getParser().reportError(2, new ErrorMsg("DATA_CONVERSION_ERR", cls.getClass().toString(), toString()));
    }

    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, Class<?> cls) {
        classGenerator.getParser().reportError(2, new ErrorMsg("DATA_CONVERSION_ERR", toString(), cls.getClass().toString()));
    }

    public FlowList translateToDesynthesized(ClassGenerator classGenerator, MethodGenerator methodGenerator, BooleanType booleanType) {
        classGenerator.getParser().reportError(2, new ErrorMsg("DATA_CONVERSION_ERR", toString(), booleanType.toString()));
        return null;
    }

    public void translateUnBox(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        classGenerator.getParser().reportError(2, new ErrorMsg("DATA_CONVERSION_ERR", "[" + toString() + "]", toString()));
    }

    public FlowList translateToDesynthesized(ClassGenerator classGenerator, MethodGenerator methodGenerator, Type type) {
        if (type == Boolean) {
            return translateToDesynthesized(classGenerator, methodGenerator, (BooleanType) type);
        }
        translateTo(classGenerator, methodGenerator, type);
        return null;
    }

    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, Type type) {
        classGenerator.getParser().reportError(2, new ErrorMsg("DATA_CONVERSION_ERR", toString(), type.toString()));
    }

    public static Type newObjectType(Class<?> cls) {
        if (cls == Object.class) {
            return Object;
        }
        if (cls == String.class) {
            return ObjectString;
        }
        return new ObjectType(cls);
    }
}
