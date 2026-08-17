package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.classfile.AnnotationEntry;
import com.sun.org.apache.bcel.internal.classfile.Annotations;
import com.sun.org.apache.bcel.internal.classfile.Attribute;
import com.sun.org.apache.bcel.internal.classfile.ConstantObject;
import com.sun.org.apache.bcel.internal.classfile.ConstantPool;
import com.sun.org.apache.bcel.internal.classfile.ConstantValue;
import com.sun.org.apache.bcel.internal.classfile.Field;
import com.sun.org.apache.bcel.internal.classfile.Utility;
import com.sun.org.apache.bcel.internal.generic.FieldGen;
import com.sun.org.apache.bcel.internal.util.BCELComparator;
import com.sun.org.apache.xpath.internal.XPath;
import defpackage.ena;
import defpackage.lo4;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FieldGen extends FieldGenOrMethodGen {
    private static BCELComparator bcelComparator = new BCELComparator() { // from class: com.sun.org.apache.bcel.internal.generic.FieldGen.1
        @Override // com.sun.org.apache.bcel.internal.util.BCELComparator
        public boolean equals(Object obj, Object obj2) {
            FieldGen fieldGen = (FieldGen) obj;
            FieldGen fieldGen2 = (FieldGen) obj2;
            return Objects.equals(fieldGen.getName(), fieldGen2.getName()) && Objects.equals(fieldGen.getSignature(), fieldGen2.getSignature());
        }

        @Override // com.sun.org.apache.bcel.internal.util.BCELComparator
        public int hashCode(Object obj) {
            FieldGen fieldGen = (FieldGen) obj;
            return fieldGen.getSignature().hashCode() ^ fieldGen.getName().hashCode();
        }
    };
    private List<FieldObserver> observers;
    private Object value;

    public FieldGen(Field field, final ConstantPoolGen constantPoolGen) {
        this(field.getAccessFlags(), Type.getType(field.getSignature()), field.getName(), constantPoolGen);
        for (Attribute attribute : field.getAttributes()) {
            if (attribute instanceof ConstantValue) {
                setValue(((ConstantValue) attribute).getConstantValueIndex());
            } else if (attribute instanceof Annotations) {
                ((Annotations) attribute).forEach(new Consumer() { // from class: mo4
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        FieldGen.a(this.b, constantPoolGen, (AnnotationEntry) obj);
                    }
                });
            } else {
                addAttribute(attribute);
            }
        }
    }

    public static /* synthetic */ void a(FieldGen fieldGen, ConstantPoolGen constantPoolGen, AnnotationEntry annotationEntry) {
        fieldGen.getClass();
        fieldGen.addAnnotationEntry(new AnnotationEntryGen(annotationEntry, constantPoolGen, false));
    }

    private void addAnnotationsAsAttribute(ConstantPoolGen constantPoolGen) {
        Stream.of((Object[]) AnnotationEntryGen.getAnnotationAttributes(constantPoolGen, super.getAnnotationEntries())).forEach(new Consumer() { // from class: no4
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.addAttribute((Attribute) obj);
            }
        });
    }

    private int addConstant() {
        byte type = super.getType().getType();
        if (type == 14) {
            return super.getConstantPool().addString((String) this.value);
        }
        switch (type) {
            case 4:
            case 5:
            case 8:
            case 9:
            case 10:
                return super.getConstantPool().addInteger(((Integer) this.value).intValue());
            case 6:
                return super.getConstantPool().addFloat(((Float) this.value).floatValue());
            case 7:
                return super.getConstantPool().addDouble(((Double) this.value).doubleValue());
            case 11:
                return super.getConstantPool().addLong(((Long) this.value).longValue());
            default:
                ena.a("Unhandled : ", super.getType().getType());
                return 0;
        }
    }

    private void checkType(Type type) {
        Type type2 = super.getType();
        if (type2 == null) {
            throw new ClassGenException("You haven't defined the type of the field yet");
        }
        if (!isFinal()) {
            throw new ClassGenException("Only final fields may have an initial value!");
        }
        if (type2.equals(type)) {
            return;
        }
        lo4.a("Types are not compatible: ", type2, " vs. ", type);
    }

    public static BCELComparator getComparator() {
        return bcelComparator;
    }

    public static void setComparator(BCELComparator bCELComparator) {
        bcelComparator = bCELComparator;
    }

    private void setValue(int i) {
        ConstantPool constantPool = super.getConstantPool().getConstantPool();
        this.value = ((ConstantObject) constantPool.getConstant(i)).getConstantValue(constantPool);
    }

    public void addObserver(FieldObserver fieldObserver) {
        if (this.observers == null) {
            this.observers = new ArrayList();
        }
        this.observers.add(fieldObserver);
    }

    public void cancelInitValue() {
        this.value = null;
    }

    public FieldGen copy(ConstantPoolGen constantPoolGen) {
        FieldGen fieldGen = (FieldGen) clone();
        fieldGen.setConstantPool(constantPoolGen);
        return fieldGen;
    }

    public boolean equals(Object obj) {
        return bcelComparator.equals(this, obj);
    }

    public Field getField() {
        String signature = getSignature();
        int iAddUtf8 = super.getConstantPool().addUtf8(super.getName());
        int iAddUtf9 = super.getConstantPool().addUtf8(signature);
        if (this.value != null) {
            checkType(super.getType());
            addAttribute(new ConstantValue(super.getConstantPool().addUtf8(com.sun.tools.classfile.Attribute.ConstantValue), 2, addConstant(), super.getConstantPool().getConstantPool()));
        }
        addAnnotationsAsAttribute(super.getConstantPool());
        return new Field(super.getAccessFlags(), iAddUtf8, iAddUtf9, getAttributes(), super.getConstantPool().getConstantPool());
    }

    public String getInitValue() {
        Object obj = this.value;
        if (obj != null) {
            return obj.toString();
        }
        return null;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.FieldGenOrMethodGen
    public String getSignature() {
        return super.getType().getSignature();
    }

    public int hashCode() {
        return bcelComparator.hashCode(this);
    }

    public void removeObserver(FieldObserver fieldObserver) {
        List<FieldObserver> list = this.observers;
        if (list != null) {
            list.remove(fieldObserver);
        }
    }

    public void setInitValue(float f) {
        checkType(Type.FLOAT);
        if (f != XPath.MATCH_SCORE_QNAME) {
            this.value = Float.valueOf(f);
        }
    }

    public final String toString() {
        String strAccessToString = Utility.accessToString(super.getAccessFlags());
        String strConcat = strAccessToString.isEmpty() ? "" : strAccessToString.concat(" ");
        String string = super.getType().toString();
        String name = getName();
        StringBuilder sb = new StringBuilder(32);
        sb.append(strConcat);
        sb.append(string);
        sb.append(" ");
        sb.append(name);
        String initValue = getInitValue();
        if (initValue != null) {
            sb.append(" = ");
            sb.append(initValue);
        }
        return sb.toString();
    }

    public void update() {
        List<FieldObserver> list = this.observers;
        if (list != null) {
            Iterator<FieldObserver> it = list.iterator();
            while (it.hasNext()) {
                it.next().notify(this);
            }
        }
    }

    public void setInitValue(byte b) {
        checkType(Type.BYTE);
        if (b != 0) {
            this.value = Integer.valueOf(b);
        }
    }

    public void setInitValue(char c) {
        checkType(Type.CHAR);
        if (c != 0) {
            this.value = Integer.valueOf(c);
        }
    }

    public void setInitValue(double d) {
        checkType(Type.DOUBLE);
        if (d != XPath.MATCH_SCORE_QNAME) {
            this.value = Double.valueOf(d);
        }
    }

    public void setInitValue(boolean z) {
        checkType(Type.BOOLEAN);
        if (z) {
            this.value = 1;
        }
    }

    public void setInitValue(int i) {
        checkType(Type.INT);
        if (i != 0) {
            this.value = Integer.valueOf(i);
        }
    }

    public void setInitValue(long j) {
        checkType(Type.LONG);
        if (j != 0) {
            this.value = Long.valueOf(j);
        }
    }

    public void setInitValue(short s) {
        checkType(Type.SHORT);
        if (s != 0) {
            this.value = Integer.valueOf(s);
        }
    }

    public void setInitValue(String str) {
        checkType(ObjectType.getInstance("java.lang.String"));
        if (str != null) {
            this.value = str;
        }
    }

    public FieldGen(int i, Type type, String str, ConstantPoolGen constantPoolGen) {
        super(i);
        setType(type);
        setName(str);
        setConstantPool(constantPoolGen);
    }
}
