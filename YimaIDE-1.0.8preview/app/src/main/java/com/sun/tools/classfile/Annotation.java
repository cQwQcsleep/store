package com.sun.tools.classfile;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Annotation {
    public final element_value_pair[] element_value_pairs;
    public final int num_element_value_pairs;
    public final int type_index;

    public static class InvalidAnnotation extends AttributeException {
        private static final long serialVersionUID = -4620480740735772708L;

        public InvalidAnnotation(String str) {
            super(str);
        }
    }

    public static abstract class element_value {
        public final int tag;

        public interface Visitor<R, P> {
            R visitAnnotation(Annotation_element_value annotation_element_value, P p);

            R visitArray(Array_element_value array_element_value, P p);

            R visitClass(Class_element_value class_element_value, P p);

            R visitEnum(Enum_element_value enum_element_value, P p);

            R visitPrimitive(Primitive_element_value primitive_element_value, P p);
        }

        public element_value(int i) {
            this.tag = i;
        }

        public static element_value read(ClassReader classReader) throws InvalidAnnotation, IOException {
            int unsignedByte = classReader.readUnsignedByte();
            if (unsignedByte == 64) {
                return new Annotation_element_value(classReader, unsignedByte);
            }
            if (unsignedByte != 70 && unsignedByte != 83) {
                if (unsignedByte == 99) {
                    return new Class_element_value(classReader, unsignedByte);
                }
                if (unsignedByte == 101) {
                    return new Enum_element_value(classReader, unsignedByte);
                }
                if (unsignedByte != 115 && unsignedByte != 73 && unsignedByte != 74 && unsignedByte != 90) {
                    if (unsignedByte == 91) {
                        return new Array_element_value(classReader, unsignedByte);
                    }
                    switch (unsignedByte) {
                        case 66:
                        case 67:
                        case 68:
                            break;
                        default:
                            throw new InvalidAnnotation("unrecognized tag: " + unsignedByte);
                    }
                }
            }
            return new Primitive_element_value(classReader, unsignedByte);
        }

        public abstract <R, P> R accept(Visitor<R, P> visitor, P p);

        public abstract int length();
    }

    public Annotation(ClassReader classReader) throws InvalidAnnotation, IOException {
        this.type_index = classReader.readUnsignedShort();
        int unsignedShort = classReader.readUnsignedShort();
        this.num_element_value_pairs = unsignedShort;
        this.element_value_pairs = new element_value_pair[unsignedShort];
        int i = 0;
        while (true) {
            element_value_pair[] element_value_pairVarArr = this.element_value_pairs;
            if (i >= element_value_pairVarArr.length) {
                return;
            }
            element_value_pairVarArr[i] = new element_value_pair(classReader);
            i++;
        }
    }

    public int length() {
        int length = 4;
        for (element_value_pair element_value_pairVar : this.element_value_pairs) {
            length += element_value_pairVar.length();
        }
        return length;
    }

    public static class Class_element_value extends element_value {
        public final int class_info_index;

        public Class_element_value(ClassReader classReader, int i) throws IOException {
            super(i);
            this.class_info_index = classReader.readUnsignedShort();
        }

        @Override // com.sun.tools.classfile.Annotation.element_value
        public <R, P> R accept(element_value.Visitor<R, P> visitor, P p) {
            return visitor.visitClass(this, p);
        }

        @Override // com.sun.tools.classfile.Annotation.element_value
        public int length() {
            return 2;
        }

        public Class_element_value(int i, int i2) {
            super(i2);
            this.class_info_index = i;
        }
    }

    public static class Primitive_element_value extends element_value {
        public final int const_value_index;

        public Primitive_element_value(ClassReader classReader, int i) throws IOException {
            super(i);
            this.const_value_index = classReader.readUnsignedShort();
        }

        @Override // com.sun.tools.classfile.Annotation.element_value
        public <R, P> R accept(element_value.Visitor<R, P> visitor, P p) {
            return visitor.visitPrimitive(this, p);
        }

        @Override // com.sun.tools.classfile.Annotation.element_value
        public int length() {
            return 2;
        }

        public Primitive_element_value(int i, int i2) {
            super(i2);
            this.const_value_index = i;
        }
    }

    public static class Annotation_element_value extends element_value {
        public final Annotation annotation_value;

        public Annotation_element_value(ClassReader classReader, int i) throws InvalidAnnotation, IOException {
            super(i);
            this.annotation_value = new Annotation(classReader);
        }

        @Override // com.sun.tools.classfile.Annotation.element_value
        public <R, P> R accept(element_value.Visitor<R, P> visitor, P p) {
            return visitor.visitAnnotation(this, p);
        }

        @Override // com.sun.tools.classfile.Annotation.element_value
        public int length() {
            return this.annotation_value.length();
        }

        public Annotation_element_value(Annotation annotation, int i) {
            super(i);
            this.annotation_value = annotation;
        }
    }

    public static class Enum_element_value extends element_value {
        public final int const_name_index;
        public final int type_name_index;

        public Enum_element_value(ClassReader classReader, int i) throws IOException {
            super(i);
            this.type_name_index = classReader.readUnsignedShort();
            this.const_name_index = classReader.readUnsignedShort();
        }

        @Override // com.sun.tools.classfile.Annotation.element_value
        public <R, P> R accept(element_value.Visitor<R, P> visitor, P p) {
            return visitor.visitEnum(this, p);
        }

        @Override // com.sun.tools.classfile.Annotation.element_value
        public int length() {
            return 4;
        }

        public Enum_element_value(int i, int i2, int i3) {
            super(i3);
            this.type_name_index = i;
            this.const_name_index = i2;
        }
    }

    public static class element_value_pair {
        public final int element_name_index;
        public final element_value value;

        public element_value_pair(ClassReader classReader) throws InvalidAnnotation, IOException {
            this.element_name_index = classReader.readUnsignedShort();
            this.value = element_value.read(classReader);
        }

        public int length() {
            return this.value.length() + 2;
        }

        public element_value_pair(int i, element_value element_valueVar) {
            this.element_name_index = i;
            this.value = element_valueVar;
        }
    }

    public static class Array_element_value extends element_value {
        public final int num_values;
        public final element_value[] values;

        public Array_element_value(ClassReader classReader, int i) throws InvalidAnnotation, IOException {
            super(i);
            int unsignedShort = classReader.readUnsignedShort();
            this.num_values = unsignedShort;
            this.values = new element_value[unsignedShort];
            int i2 = 0;
            while (true) {
                element_value[] element_valueVarArr = this.values;
                if (i2 >= element_valueVarArr.length) {
                    return;
                }
                element_valueVarArr[i2] = element_value.read(classReader);
                i2++;
            }
        }

        @Override // com.sun.tools.classfile.Annotation.element_value
        public <R, P> R accept(element_value.Visitor<R, P> visitor, P p) {
            return visitor.visitArray(this, p);
        }

        @Override // com.sun.tools.classfile.Annotation.element_value
        public int length() {
            int length = 2;
            int i = 0;
            while (true) {
                element_value[] element_valueVarArr = this.values;
                if (i >= element_valueVarArr.length) {
                    return length;
                }
                length += element_valueVarArr[i].length();
                i++;
            }
        }

        public Array_element_value(element_value[] element_valueVarArr, int i) {
            super(i);
            this.num_values = element_valueVarArr.length;
            this.values = element_valueVarArr;
        }
    }

    public Annotation(ConstantPool constantPool, int i, element_value_pair[] element_value_pairVarArr) {
        this.type_index = i;
        this.num_element_value_pairs = element_value_pairVarArr.length;
        this.element_value_pairs = element_value_pairVarArr;
    }
}
