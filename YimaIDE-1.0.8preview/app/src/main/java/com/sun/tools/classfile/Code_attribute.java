package com.sun.tools.classfile;

import com.sun.tools.classfile.Code_attribute;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Code_attribute extends Attribute {
    public final Attributes attributes;
    public final byte[] code;
    public final int code_length;
    public final Exception_data[] exception_table;
    public final int exception_table_length;
    public final int max_locals;
    public final int max_stack;

    public static class Exception_data {
        public final int catch_type;
        public final int end_pc;
        public final int handler_pc;
        public final int start_pc;

        public Exception_data(ClassReader classReader) throws IOException {
            this.start_pc = classReader.readUnsignedShort();
            this.end_pc = classReader.readUnsignedShort();
            this.handler_pc = classReader.readUnsignedShort();
            this.catch_type = classReader.readUnsignedShort();
        }
    }

    public static class InvalidIndex extends AttributeException {
        private static final long serialVersionUID = -8904527774589382802L;
        public final int index;

        public InvalidIndex(int i) {
            this.index = i;
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            return "invalid index " + this.index + " in Code attribute";
        }
    }

    public Code_attribute(ClassReader classReader, int i, int i2) throws IOException, ConstantPoolException {
        super(i, i2);
        this.max_stack = classReader.readUnsignedShort();
        this.max_locals = classReader.readUnsignedShort();
        int i3 = classReader.readInt();
        this.code_length = i3;
        byte[] bArr = new byte[i3];
        this.code = bArr;
        classReader.readFully(bArr);
        int unsignedShort = classReader.readUnsignedShort();
        this.exception_table_length = unsignedShort;
        this.exception_table = new Exception_data[unsignedShort];
        for (int i4 = 0; i4 < this.exception_table_length; i4++) {
            this.exception_table[i4] = new Exception_data(classReader);
        }
        this.attributes = new Attributes(classReader);
    }

    public static /* synthetic */ Iterator a(Code_attribute code_attribute) {
        code_attribute.getClass();
        return new Iterator<Instruction>() { // from class: com.sun.tools.classfile.Code_attribute.1
            Instruction next;
            Instruction current = null;
            int pc = 0;

            {
                byte[] bArr = Code_attribute.this.code;
                this.next = bArr.length > 0 ? new Instruction(bArr, 0) : null;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.next != null;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public Instruction next() {
                Instruction instruction = this.next;
                if (instruction == null) {
                    z0e.a();
                    return null;
                }
                this.current = instruction;
                int length = this.pc + instruction.length();
                this.pc = length;
                byte[] bArr = Code_attribute.this.code;
                this.next = length < bArr.length ? new Instruction(bArr, length) : null;
                return this.current;
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException("Not supported.");
            }
        };
    }

    @Override // com.sun.tools.classfile.Attribute
    public <R, D> R accept(Attribute.Visitor<R, D> visitor, D d) {
        return visitor.visitCode(this, d);
    }

    public int getByte(int i) throws InvalidIndex {
        if (i >= 0) {
            byte[] bArr = this.code;
            if (i < bArr.length) {
                return bArr[i];
            }
        }
        throw new InvalidIndex(i);
    }

    public Iterable<Instruction> getInstructions() {
        return new Iterable() { // from class: r32
            @Override // java.lang.Iterable
            public final Iterator iterator() {
                return Code_attribute.a(this.b);
            }
        };
    }

    public int getInt(int i) throws InvalidIndex {
        if (i < 0 || i + 3 >= this.code.length) {
            throw new InvalidIndex(i);
        }
        return (getShort(i + 2) & 65535) | (getShort(i) << 16);
    }

    public int getShort(int i) throws InvalidIndex {
        if (i >= 0) {
            int i2 = i + 1;
            byte[] bArr = this.code;
            if (i2 < bArr.length) {
                return (bArr[i2] & 255) | (bArr[i] << 8);
            }
        }
        throw new InvalidIndex(i);
    }

    public int getUnsignedByte(int i) throws InvalidIndex {
        if (i >= 0) {
            byte[] bArr = this.code;
            if (i < bArr.length) {
                return bArr[i] & 255;
            }
        }
        throw new InvalidIndex(i);
    }

    public int getUnsignedShort(int i) throws InvalidIndex {
        if (i >= 0) {
            int i2 = i + 1;
            byte[] bArr = this.code;
            if (i2 < bArr.length) {
                return ((bArr[i2] & 255) | (bArr[i] << 8)) & 65535;
            }
        }
        throw new InvalidIndex(i);
    }
}
