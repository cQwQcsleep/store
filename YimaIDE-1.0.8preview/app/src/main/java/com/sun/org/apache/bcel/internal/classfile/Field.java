package com.sun.org.apache.bcel.internal.classfile;

import com.sun.org.apache.bcel.internal.generic.Type;
import com.sun.org.apache.bcel.internal.util.BCELComparator;
import java.io.DataInput;
import java.io.IOException;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class Field extends FieldOrMethod {
    public static final Field[] EMPTY_ARRAY = new Field[0];
    private static BCELComparator bcelComparator = new BCELComparator() { // from class: com.sun.org.apache.bcel.internal.classfile.Field.1
        @Override // com.sun.org.apache.bcel.internal.util.BCELComparator
        public boolean equals(Object obj, Object obj2) {
            Field field = (Field) obj;
            Field field2 = (Field) obj2;
            return Objects.equals(field.getName(), field2.getName()) && Objects.equals(field.getSignature(), field2.getSignature());
        }

        @Override // com.sun.org.apache.bcel.internal.util.BCELComparator
        public int hashCode(Object obj) {
            Field field = (Field) obj;
            return field.getSignature().hashCode() ^ field.getName().hashCode();
        }
    };
    static final Field[] EMPTY_FIELD_ARRAY = new Field[0];

    public Field(DataInput dataInput, ConstantPool constantPool) throws IOException, ClassFormatException {
        super(dataInput, constantPool);
    }

    public static BCELComparator getComparator() {
        return bcelComparator;
    }

    public static void setComparator(BCELComparator bCELComparator) {
        bcelComparator = bCELComparator;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitField(this);
    }

    public Field copy(ConstantPool constantPool) {
        return (Field) copy_(constantPool);
    }

    public boolean equals(Object obj) {
        return bcelComparator.equals(this, obj);
    }

    public ConstantValue getConstantValue() {
        for (Attribute attribute : super.getAttributes()) {
            if (attribute.getTag() == 1) {
                return (ConstantValue) attribute;
            }
        }
        return null;
    }

    public Type getType() {
        return Type.getReturnType(getSignature());
    }

    public int hashCode() {
        return bcelComparator.hashCode(this);
    }

    public String toString() {
        String strAccessToString = Utility.accessToString(super.getAccessFlags());
        String strConcat = strAccessToString.isEmpty() ? "" : strAccessToString.concat(" ");
        String strSignatureToString = Utility.signatureToString(getSignature());
        String name = getName();
        StringBuilder sb = new StringBuilder(64);
        sb.append(strConcat);
        sb.append(strSignatureToString);
        sb.append(" ");
        sb.append(name);
        ConstantValue constantValue = getConstantValue();
        if (constantValue != null) {
            sb.append(" = ");
            sb.append(constantValue);
        }
        for (Attribute attribute : super.getAttributes()) {
            if (!(attribute instanceof ConstantValue)) {
                sb.append(" [");
                sb.append(attribute);
                sb.append("]");
            }
        }
        return sb.toString();
    }

    public Field(Field field) {
        super(field);
    }

    public Field(int i, int i2, int i3, Attribute[] attributeArr, ConstantPool constantPool) {
        super(i, i2, i3, attributeArr, constantPool);
    }
}
