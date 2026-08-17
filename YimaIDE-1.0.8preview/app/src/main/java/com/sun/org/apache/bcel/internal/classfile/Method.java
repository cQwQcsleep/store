package com.sun.org.apache.bcel.internal.classfile;

import com.sun.org.apache.bcel.internal.generic.Type;
import com.sun.org.apache.bcel.internal.util.BCELComparator;
import java.io.DataInput;
import java.io.IOException;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class Method extends FieldOrMethod {
    private ParameterAnnotationEntry[] parameterAnnotationEntries;
    public static final Method[] EMPTY_ARRAY = new Method[0];
    private static BCELComparator bcelComparator = new BCELComparator() { // from class: com.sun.org.apache.bcel.internal.classfile.Method.1
        @Override // com.sun.org.apache.bcel.internal.util.BCELComparator
        public boolean equals(Object obj, Object obj2) {
            Method method = (Method) obj;
            Method method2 = (Method) obj2;
            return Objects.equals(method.getName(), method2.getName()) && Objects.equals(method.getSignature(), method2.getSignature());
        }

        @Override // com.sun.org.apache.bcel.internal.util.BCELComparator
        public int hashCode(Object obj) {
            Method method = (Method) obj;
            return method.getSignature().hashCode() ^ method.getName().hashCode();
        }
    };
    static final Method[] EMPTY_METHOD_ARRAY = new Method[0];

    public Method() {
    }

    public static BCELComparator getComparator() {
        return bcelComparator;
    }

    public static void setComparator(BCELComparator bCELComparator) {
        bcelComparator = bCELComparator;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitMethod(this);
    }

    public Method copy(ConstantPool constantPool) {
        return (Method) copy_(constantPool);
    }

    public boolean equals(Object obj) {
        return bcelComparator.equals(this, obj);
    }

    public Type[] getArgumentTypes() {
        return Type.getArgumentTypes(getSignature());
    }

    public Code getCode() {
        for (Attribute attribute : super.getAttributes()) {
            if (attribute instanceof Code) {
                return (Code) attribute;
            }
        }
        return null;
    }

    public ExceptionTable getExceptionTable() {
        for (Attribute attribute : super.getAttributes()) {
            if (attribute instanceof ExceptionTable) {
                return (ExceptionTable) attribute;
            }
        }
        return null;
    }

    public LineNumberTable getLineNumberTable() {
        Code code = getCode();
        if (code == null) {
            return null;
        }
        return code.getLineNumberTable();
    }

    public LocalVariableTable getLocalVariableTable() {
        Code code = getCode();
        if (code == null) {
            return null;
        }
        return code.getLocalVariableTable();
    }

    public ParameterAnnotationEntry[] getParameterAnnotationEntries() {
        if (this.parameterAnnotationEntries == null) {
            this.parameterAnnotationEntries = ParameterAnnotationEntry.createParameterAnnotationEntries(getAttributes());
        }
        return this.parameterAnnotationEntries;
    }

    public Type getReturnType() {
        return Type.getReturnType(getSignature());
    }

    public int hashCode() {
        return bcelComparator.hashCode(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(Utility.methodSignatureToString(super.getConstantPool().getConstantUtf8(super.getSignatureIndex()).getBytes(), super.getConstantPool().getConstantUtf8(super.getNameIndex()).getBytes(), Utility.accessToString(super.getAccessFlags()), true, getLocalVariableTable()));
        for (Attribute attribute : super.getAttributes()) {
            if (!(attribute instanceof Code) && !(attribute instanceof ExceptionTable)) {
                sb.append(" [");
                sb.append(attribute);
                sb.append("]");
            }
        }
        ExceptionTable exceptionTable = getExceptionTable();
        if (exceptionTable != null) {
            String string = exceptionTable.toString();
            if (!string.isEmpty()) {
                sb.append("\n\t\tthrows ");
                sb.append(string);
            }
        }
        return sb.toString();
    }

    public Method(DataInput dataInput, ConstantPool constantPool) throws IOException, ClassFormatException {
        super(dataInput, constantPool);
    }

    public Method(int i, int i2, int i3, Attribute[] attributeArr, ConstantPool constantPool) {
        super(i, i2, i3, attributeArr, constantPool);
    }

    public Method(Method method) {
        super(method);
    }
}
