package com.sun.tools.classfile;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class InnerClasses_attribute extends Attribute {
    public final Info[] classes;
    public final int number_of_classes;

    public InnerClasses_attribute(ClassReader classReader, int i, int i2) throws IOException {
        super(i, i2);
        int unsignedShort = classReader.readUnsignedShort();
        this.number_of_classes = unsignedShort;
        this.classes = new Info[unsignedShort];
        for (int i3 = 0; i3 < this.number_of_classes; i3++) {
            this.classes[i3] = new Info(classReader);
        }
    }

    @Override // com.sun.tools.classfile.Attribute
    public <R, D> R accept(Attribute.Visitor<R, D> visitor, D d) {
        return visitor.visitInnerClasses(this, d);
    }

    public InnerClasses_attribute(ConstantPool constantPool, Info[] infoArr) throws ConstantPoolException {
        this(constantPool.getUTF8Index(Attribute.InnerClasses), infoArr);
    }

    public static class Info {
        public final AccessFlags inner_class_access_flags;
        public final int inner_class_info_index;
        public final int inner_name_index;
        public final int outer_class_info_index;

        public Info(ClassReader classReader) throws IOException {
            this.inner_class_info_index = classReader.readUnsignedShort();
            this.outer_class_info_index = classReader.readUnsignedShort();
            this.inner_name_index = classReader.readUnsignedShort();
            this.inner_class_access_flags = new AccessFlags(classReader.readUnsignedShort());
        }

        public static int length() {
            return 8;
        }

        public ConstantPool.CONSTANT_Class_info getInnerClassInfo(ConstantPool constantPool) throws ConstantPoolException {
            int i = this.inner_class_info_index;
            if (i == 0) {
                return null;
            }
            return constantPool.getClassInfo(i);
        }

        public String getInnerName(ConstantPool constantPool) throws ConstantPoolException {
            int i = this.inner_name_index;
            if (i == 0) {
                return null;
            }
            return constantPool.getUTF8Value(i);
        }

        public ConstantPool.CONSTANT_Class_info getOuterClassInfo(ConstantPool constantPool) throws ConstantPoolException {
            int i = this.outer_class_info_index;
            if (i == 0) {
                return null;
            }
            return constantPool.getClassInfo(i);
        }

        public Info(int i, int i2, int i3, AccessFlags accessFlags) {
            this.inner_class_info_index = i;
            this.outer_class_info_index = i2;
            this.inner_name_index = i3;
            this.inner_class_access_flags = accessFlags;
        }
    }

    public InnerClasses_attribute(int i, Info[] infoArr) {
        super(i, (Info.length() * infoArr.length) + 2);
        this.number_of_classes = infoArr.length;
        this.classes = infoArr;
    }
}
