package com.sun.tools.classfile;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StackMapTable_attribute extends Attribute {
    public final stack_map_frame[] entries;
    public final int number_of_entries;

    public static class InvalidStackMap extends AttributeException {
        private static final long serialVersionUID = -5659038410855089780L;

        public InvalidStackMap(String str) {
            super(str);
        }
    }

    public static class Object_variable_info extends verification_type_info {
        public final int cpool_index;

        public Object_variable_info(ClassReader classReader) throws IOException {
            super(7);
            this.cpool_index = classReader.readUnsignedShort();
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.verification_type_info
        public int length() {
            return super.length() + 2;
        }
    }

    public static class Uninitialized_variable_info extends verification_type_info {
        public final int offset;

        public Uninitialized_variable_info(ClassReader classReader) throws IOException {
            super(8);
            this.offset = classReader.readUnsignedShort();
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.verification_type_info
        public int length() {
            return super.length() + 2;
        }
    }

    public static class append_frame extends stack_map_frame {
        public final verification_type_info[] locals;
        public final int offset_delta;

        public append_frame(int i, ClassReader classReader) throws InvalidStackMap, IOException {
            super(i);
            this.offset_delta = classReader.readUnsignedShort();
            this.locals = new verification_type_info[i - 251];
            int i2 = 0;
            while (true) {
                verification_type_info[] verification_type_infoVarArr = this.locals;
                if (i2 >= verification_type_infoVarArr.length) {
                    return;
                }
                verification_type_infoVarArr[i2] = verification_type_info.read(classReader);
                i2++;
            }
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame
        public <R, D> R accept(stack_map_frame.Visitor<R, D> visitor, D d) {
            return visitor.visit_append_frame(this, d);
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame
        public int getOffsetDelta() {
            return this.offset_delta;
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame
        public int length() {
            int length = super.length() + 2;
            for (verification_type_info verification_type_infoVar : this.locals) {
                length += verification_type_infoVar.length();
            }
            return length;
        }
    }

    public static class chop_frame extends stack_map_frame {
        public final int offset_delta;

        public chop_frame(int i, ClassReader classReader) throws IOException {
            super(i);
            this.offset_delta = classReader.readUnsignedShort();
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame
        public <R, D> R accept(stack_map_frame.Visitor<R, D> visitor, D d) {
            return visitor.visit_chop_frame(this, d);
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame
        public int getOffsetDelta() {
            return this.offset_delta;
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame
        public int length() {
            return super.length() + 2;
        }
    }

    public static class full_frame extends stack_map_frame {
        public final verification_type_info[] locals;
        public final int number_of_locals;
        public final int number_of_stack_items;
        public final int offset_delta;
        public final verification_type_info[] stack;

        public full_frame(int i, ClassReader classReader) throws InvalidStackMap, IOException {
            super(i);
            this.offset_delta = classReader.readUnsignedShort();
            int unsignedShort = classReader.readUnsignedShort();
            this.number_of_locals = unsignedShort;
            this.locals = new verification_type_info[unsignedShort];
            int i2 = 0;
            int i3 = 0;
            while (true) {
                verification_type_info[] verification_type_infoVarArr = this.locals;
                if (i3 >= verification_type_infoVarArr.length) {
                    break;
                }
                verification_type_infoVarArr[i3] = verification_type_info.read(classReader);
                i3++;
            }
            int unsignedShort2 = classReader.readUnsignedShort();
            this.number_of_stack_items = unsignedShort2;
            this.stack = new verification_type_info[unsignedShort2];
            while (true) {
                verification_type_info[] verification_type_infoVarArr2 = this.stack;
                if (i2 >= verification_type_infoVarArr2.length) {
                    return;
                }
                verification_type_infoVarArr2[i2] = verification_type_info.read(classReader);
                i2++;
            }
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame
        public <R, D> R accept(stack_map_frame.Visitor<R, D> visitor, D d) {
            return visitor.visit_full_frame(this, d);
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame
        public int getOffsetDelta() {
            return this.offset_delta;
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame
        public int length() {
            int length = super.length() + 2;
            for (verification_type_info verification_type_infoVar : this.locals) {
                length += verification_type_infoVar.length();
            }
            int length2 = length + 2;
            for (verification_type_info verification_type_infoVar2 : this.stack) {
                length2 += verification_type_infoVar2.length();
            }
            return length2;
        }
    }

    public static class same_frame extends stack_map_frame {
        public same_frame(int i) {
            super(i);
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame
        public <R, D> R accept(stack_map_frame.Visitor<R, D> visitor, D d) {
            return visitor.visit_same_frame(this, d);
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame
        public int getOffsetDelta() {
            return this.frame_type;
        }
    }

    public static class same_frame_extended extends stack_map_frame {
        public final int offset_delta;

        public same_frame_extended(int i, ClassReader classReader) throws IOException {
            super(i);
            this.offset_delta = classReader.readUnsignedShort();
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame
        public <R, D> R accept(stack_map_frame.Visitor<R, D> visitor, D d) {
            return visitor.visit_same_frame_extended(this, d);
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame
        public int getOffsetDelta() {
            return this.offset_delta;
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame
        public int length() {
            return super.length() + 2;
        }
    }

    public static class same_locals_1_stack_item_frame extends stack_map_frame {
        public final verification_type_info[] stack;

        public same_locals_1_stack_item_frame(int i, ClassReader classReader) throws InvalidStackMap, IOException {
            super(i);
            this.stack = new verification_type_info[]{verification_type_info.read(classReader)};
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame
        public <R, D> R accept(stack_map_frame.Visitor<R, D> visitor, D d) {
            return visitor.visit_same_locals_1_stack_item_frame(this, d);
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame
        public int getOffsetDelta() {
            return this.frame_type - 64;
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame
        public int length() {
            return super.length() + this.stack[0].length();
        }
    }

    public static class same_locals_1_stack_item_frame_extended extends stack_map_frame {
        public final int offset_delta;
        public final verification_type_info[] stack;

        public same_locals_1_stack_item_frame_extended(int i, ClassReader classReader) throws InvalidStackMap, IOException {
            super(i);
            this.offset_delta = classReader.readUnsignedShort();
            this.stack = new verification_type_info[]{verification_type_info.read(classReader)};
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame
        public <R, D> R accept(stack_map_frame.Visitor<R, D> visitor, D d) {
            return visitor.visit_same_locals_1_stack_item_frame_extended(this, d);
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame
        public int getOffsetDelta() {
            return this.offset_delta;
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame
        public int length() {
            return super.length() + 2 + this.stack[0].length();
        }
    }

    public static abstract class stack_map_frame {
        public final int frame_type;

        public interface Visitor<R, P> {
            R visit_append_frame(append_frame append_frameVar, P p);

            R visit_chop_frame(chop_frame chop_frameVar, P p);

            R visit_full_frame(full_frame full_frameVar, P p);

            R visit_same_frame(same_frame same_frameVar, P p);

            R visit_same_frame_extended(same_frame_extended same_frame_extendedVar, P p);

            R visit_same_locals_1_stack_item_frame(same_locals_1_stack_item_frame same_locals_1_stack_item_frameVar, P p);

            R visit_same_locals_1_stack_item_frame_extended(same_locals_1_stack_item_frame_extended same_locals_1_stack_item_frame_extendedVar, P p);
        }

        public stack_map_frame(int i) {
            this.frame_type = i;
        }

        public static stack_map_frame read(ClassReader classReader) throws InvalidStackMap, IOException {
            int unsignedByte = classReader.readUnsignedByte();
            if (unsignedByte <= 63) {
                return new same_frame(unsignedByte);
            }
            if (unsignedByte <= 127) {
                return new same_locals_1_stack_item_frame(unsignedByte, classReader);
            }
            if (unsignedByte <= 246) {
                throw new Error("unknown frame_type " + unsignedByte);
            }
            if (unsignedByte == 247) {
                return new same_locals_1_stack_item_frame_extended(unsignedByte, classReader);
            }
            if (unsignedByte <= 250) {
                return new chop_frame(unsignedByte, classReader);
            }
            if (unsignedByte == 251) {
                return new same_frame_extended(unsignedByte, classReader);
            }
            return unsignedByte <= 254 ? new append_frame(unsignedByte, classReader) : new full_frame(unsignedByte, classReader);
        }

        public abstract <R, D> R accept(Visitor<R, D> visitor, D d);

        public abstract int getOffsetDelta();

        public int length() {
            return 1;
        }
    }

    public static class verification_type_info {
        public static final int ITEM_Double = 3;
        public static final int ITEM_Float = 2;
        public static final int ITEM_Integer = 1;
        public static final int ITEM_Long = 4;
        public static final int ITEM_Null = 5;
        public static final int ITEM_Object = 7;
        public static final int ITEM_Top = 0;
        public static final int ITEM_Uninitialized = 8;
        public static final int ITEM_UninitializedThis = 6;
        public final int tag;

        public verification_type_info(int i) {
            this.tag = i;
        }

        public static verification_type_info read(ClassReader classReader) throws InvalidStackMap, IOException {
            int unsignedByte = classReader.readUnsignedByte();
            switch (unsignedByte) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    return new verification_type_info(unsignedByte);
                case 7:
                    return new Object_variable_info(classReader);
                case 8:
                    return new Uninitialized_variable_info(classReader);
                default:
                    throw new InvalidStackMap("unrecognized verification_type_info tag");
            }
        }

        public int length() {
            return 1;
        }
    }

    public StackMapTable_attribute(ClassReader classReader, int i, int i2) throws InvalidStackMap, IOException {
        super(i, i2);
        int unsignedShort = classReader.readUnsignedShort();
        this.number_of_entries = unsignedShort;
        this.entries = new stack_map_frame[unsignedShort];
        for (int i3 = 0; i3 < this.number_of_entries; i3++) {
            this.entries[i3] = stack_map_frame.read(classReader);
        }
    }

    public static int length(stack_map_frame[] stack_map_frameVarArr) {
        int length = 2;
        for (stack_map_frame stack_map_frameVar : stack_map_frameVarArr) {
            length += stack_map_frameVar.length();
        }
        return length;
    }

    @Override // com.sun.tools.classfile.Attribute
    public <R, D> R accept(Attribute.Visitor<R, D> visitor, D d) {
        return visitor.visitStackMapTable(this, d);
    }

    public StackMapTable_attribute(ConstantPool constantPool, stack_map_frame[] stack_map_frameVarArr) throws ConstantPoolException {
        this(constantPool.getUTF8Index(Attribute.StackMapTable), stack_map_frameVarArr);
    }

    public StackMapTable_attribute(int i, stack_map_frame[] stack_map_frameVarArr) {
        super(i, length(stack_map_frameVarArr));
        this.number_of_entries = stack_map_frameVarArr.length;
        this.entries = stack_map_frameVarArr;
    }
}
