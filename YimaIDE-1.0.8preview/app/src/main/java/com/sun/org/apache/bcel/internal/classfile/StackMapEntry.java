package com.sun.org.apache.bcel.internal.classfile;

import com.sun.org.apache.bcel.internal.Const;
import defpackage.ena;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class StackMapEntry implements Node, Cloneable {
    static final StackMapEntry[] EMPTY_ARRAY = new StackMapEntry[0];
    private int byteCodeOffset;
    private ConstantPool constantPool;
    private int frameType;
    private StackMapType[] typesOfLocals;
    private StackMapType[] typesOfStackItems;

    public StackMapEntry(DataInput dataInput, ConstantPool constantPool) throws IOException {
        this(dataInput.readByte() & 255, -1, null, null, constantPool);
        int i = this.frameType;
        if (i >= 0 && i <= 63) {
            this.byteCodeOffset = i;
            return;
        }
        if (i >= 64 && i <= 127) {
            this.byteCodeOffset = i - 64;
            this.typesOfStackItems = new StackMapType[]{new StackMapType(dataInput, constantPool)};
            return;
        }
        if (i == 247) {
            this.byteCodeOffset = dataInput.readUnsignedShort();
            this.typesOfStackItems = new StackMapType[]{new StackMapType(dataInput, constantPool)};
            return;
        }
        if (i >= 248 && i <= 250) {
            this.byteCodeOffset = dataInput.readUnsignedShort();
            return;
        }
        if (i == 251) {
            this.byteCodeOffset = dataInput.readUnsignedShort();
            return;
        }
        int i2 = 0;
        if (i >= 252 && i <= 254) {
            this.byteCodeOffset = dataInput.readUnsignedShort();
            int i3 = this.frameType - Const.SAME_FRAME_EXTENDED;
            this.typesOfLocals = new StackMapType[i3];
            while (i2 < i3) {
                this.typesOfLocals[i2] = new StackMapType(dataInput, constantPool);
                i2++;
            }
            return;
        }
        if (i != 255) {
            throw new ClassFormatException("Invalid frame type found while parsing stack map table: " + this.frameType);
        }
        this.byteCodeOffset = dataInput.readUnsignedShort();
        int unsignedShort = dataInput.readUnsignedShort();
        this.typesOfLocals = new StackMapType[unsignedShort];
        for (int i4 = 0; i4 < unsignedShort; i4++) {
            this.typesOfLocals[i4] = new StackMapType(dataInput, constantPool);
        }
        int unsignedShort2 = dataInput.readUnsignedShort();
        this.typesOfStackItems = new StackMapType[unsignedShort2];
        while (i2 < unsignedShort2) {
            this.typesOfStackItems[i2] = new StackMapType(dataInput, constantPool);
            i2++;
        }
    }

    private boolean invalidFrameType(int i) {
        if (i == 247) {
            return false;
        }
        if ((i < 248 || i > 250) && i != 251) {
            return (i < 252 || i > 254) && i != 255;
        }
        return false;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitStackMapEntry(this);
    }

    public StackMapEntry copy() {
        try {
            StackMapEntry stackMapEntry = (StackMapEntry) clone();
            StackMapType[] stackMapTypeArr = new StackMapType[this.typesOfLocals.length];
            stackMapEntry.typesOfLocals = stackMapTypeArr;
            Arrays.setAll(stackMapTypeArr, new IntFunction() { // from class: jld
                @Override // java.util.function.IntFunction
                public final Object apply(int i) {
                    return this.b.typesOfLocals[i].copy();
                }
            });
            StackMapType[] stackMapTypeArr2 = new StackMapType[this.typesOfStackItems.length];
            stackMapEntry.typesOfStackItems = stackMapTypeArr2;
            Arrays.setAll(stackMapTypeArr2, new IntFunction() { // from class: kld
                @Override // java.util.function.IntFunction
                public final Object apply(int i) {
                    return this.b.typesOfStackItems[i].copy();
                }
            });
            return stackMapEntry;
        } catch (CloneNotSupportedException unused) {
            throw new Error("Clone Not Supported");
        }
    }

    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.write(this.frameType);
        int i = this.frameType;
        int i2 = 0;
        if (i >= 64 && i <= 127) {
            this.typesOfStackItems[0].dump(dataOutputStream);
            return;
        }
        if (i == 247) {
            dataOutputStream.writeShort(this.byteCodeOffset);
            this.typesOfStackItems[0].dump(dataOutputStream);
            return;
        }
        if (i >= 248 && i <= 250) {
            dataOutputStream.writeShort(this.byteCodeOffset);
            return;
        }
        if (i == 251) {
            dataOutputStream.writeShort(this.byteCodeOffset);
            return;
        }
        if (i >= 252 && i <= 254) {
            dataOutputStream.writeShort(this.byteCodeOffset);
            StackMapType[] stackMapTypeArr = this.typesOfLocals;
            int length = stackMapTypeArr.length;
            while (i2 < length) {
                stackMapTypeArr[i2].dump(dataOutputStream);
                i2++;
            }
            return;
        }
        if (i != 255) {
            if (i < 0 || i > 63) {
                throw new ClassFormatException("Invalid Stack map table tag: " + this.frameType);
            }
            return;
        }
        dataOutputStream.writeShort(this.byteCodeOffset);
        dataOutputStream.writeShort(this.typesOfLocals.length);
        for (StackMapType stackMapType : this.typesOfLocals) {
            stackMapType.dump(dataOutputStream);
        }
        dataOutputStream.writeShort(this.typesOfStackItems.length);
        StackMapType[] stackMapTypeArr2 = this.typesOfStackItems;
        int length2 = stackMapTypeArr2.length;
        while (i2 < length2) {
            stackMapTypeArr2[i2].dump(dataOutputStream);
            i2++;
        }
    }

    public int getByteCodeOffset() {
        return this.byteCodeOffset;
    }

    public ConstantPool getConstantPool() {
        return this.constantPool;
    }

    public int getFrameType() {
        return this.frameType;
    }

    public int getMapEntrySize() {
        int i = this.frameType;
        if (i >= 0 && i <= 63) {
            return 1;
        }
        int i2 = 0;
        if (i >= 64 && i <= 127) {
            return (this.typesOfStackItems[0].hasIndex() ? 3 : 1) + 1;
        }
        if (i == 247) {
            return (this.typesOfStackItems[0].hasIndex() ? 3 : 1) + 3;
        }
        if ((i >= 248 && i <= 250) || i == 251) {
            return 3;
        }
        if (i >= 252 && i <= 254) {
            StackMapType[] stackMapTypeArr = this.typesOfLocals;
            int length = stackMapTypeArr.length;
            int i3 = 3;
            while (i2 < length) {
                i3 += stackMapTypeArr[i2].hasIndex() ? 3 : 1;
                i2++;
            }
            return i3;
        }
        if (i != 255) {
            ena.a("Invalid StackMap frameType: ", this.frameType);
            return 0;
        }
        int i4 = 7;
        for (StackMapType stackMapType : this.typesOfLocals) {
            i4 += stackMapType.hasIndex() ? 3 : 1;
        }
        StackMapType[] stackMapTypeArr2 = this.typesOfStackItems;
        int length2 = stackMapTypeArr2.length;
        while (i2 < length2) {
            i4 += stackMapTypeArr2[i2].hasIndex() ? 3 : 1;
            i2++;
        }
        return i4;
    }

    public int getNumberOfLocals() {
        return this.typesOfLocals.length;
    }

    public int getNumberOfStackItems() {
        return this.typesOfStackItems.length;
    }

    public StackMapType[] getTypesOfLocals() {
        return this.typesOfLocals;
    }

    public StackMapType[] getTypesOfStackItems() {
        return this.typesOfStackItems;
    }

    public void setByteCodeOffset(int i) {
        if (i < 0 || i > 32767) {
            qf1.a("Invalid StackMap offset: ", i);
            return;
        }
        int i2 = this.frameType;
        if (i2 < 0 || i2 > 63) {
            if (i2 < 64 || i2 > 127) {
                if (invalidFrameType(i2)) {
                    ena.a("Invalid StackMap frameType: ", this.frameType);
                    return;
                }
            } else if (i > 63) {
                this.frameType = Const.SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED;
            } else {
                this.frameType = i + 64;
            }
        } else if (i > 63) {
            this.frameType = Const.SAME_FRAME_EXTENDED;
        } else {
            this.frameType = i;
        }
        this.byteCodeOffset = i;
    }

    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public void setFrameType(int i) {
        if (i >= 0 && i <= 63) {
            this.byteCodeOffset = i;
        } else if (i >= 64 && i <= 127) {
            this.byteCodeOffset = i - 64;
        } else if (invalidFrameType(i)) {
            w01.a("Invalid StackMap frameType");
            return;
        }
        this.frameType = i;
    }

    @java.lang.Deprecated
    public void setNumberOfLocals(int i) {
    }

    @java.lang.Deprecated
    public void setNumberOfStackItems(int i) {
    }

    public void setTypesOfLocals(StackMapType[] stackMapTypeArr) {
        if (stackMapTypeArr == null) {
            stackMapTypeArr = StackMapType.EMPTY_ARRAY;
        }
        this.typesOfLocals = stackMapTypeArr;
    }

    public void setTypesOfStackItems(StackMapType[] stackMapTypeArr) {
        if (stackMapTypeArr == null) {
            stackMapTypeArr = StackMapType.EMPTY_ARRAY;
        }
        this.typesOfStackItems = stackMapTypeArr;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append("(");
        int i = this.frameType;
        if (i >= 0 && i <= 63) {
            sb.append("SAME");
        } else if (i >= 64 && i <= 127) {
            sb.append("SAME_LOCALS_1_STACK");
        } else if (i == 247) {
            sb.append("SAME_LOCALS_1_STACK_EXTENDED");
        } else if (i >= 248 && i <= 250) {
            sb.append("CHOP ");
            sb.append(String.valueOf(Const.SAME_FRAME_EXTENDED - this.frameType));
        } else if (i == 251) {
            sb.append("SAME_EXTENDED");
        } else if (i >= 252 && i <= 254) {
            sb.append("APPEND ");
            sb.append(String.valueOf(this.frameType - Const.SAME_FRAME_EXTENDED));
        } else if (i == 255) {
            sb.append("FULL");
        } else {
            sb.append("UNKNOWN (");
            sb.append(this.frameType);
            sb.append(")");
        }
        sb.append(", offset delta=");
        sb.append(this.byteCodeOffset);
        int i2 = 0;
        if (this.typesOfLocals.length > 0) {
            sb.append(", locals={");
            int i3 = 0;
            while (true) {
                StackMapType[] stackMapTypeArr = this.typesOfLocals;
                if (i3 >= stackMapTypeArr.length) {
                    break;
                }
                sb.append(stackMapTypeArr[i3]);
                if (i3 < this.typesOfLocals.length - 1) {
                    sb.append(", ");
                }
                i3++;
            }
            sb.append("}");
        }
        if (this.typesOfStackItems.length > 0) {
            sb.append(", stack items={");
            while (true) {
                StackMapType[] stackMapTypeArr2 = this.typesOfStackItems;
                if (i2 >= stackMapTypeArr2.length) {
                    break;
                }
                sb.append(stackMapTypeArr2[i2]);
                if (i2 < this.typesOfStackItems.length - 1) {
                    sb.append(", ");
                }
                i2++;
            }
            sb.append("}");
        }
        sb.append(")");
        return sb.toString();
    }

    public void updateByteCodeOffset(int i) {
        setByteCodeOffset(this.byteCodeOffset + i);
    }

    @java.lang.Deprecated
    public StackMapEntry(int i, int i2, StackMapType[] stackMapTypeArr, int i3, StackMapType[] stackMapTypeArr2, ConstantPool constantPool) {
        this.byteCodeOffset = i;
        this.typesOfLocals = stackMapTypeArr == null ? StackMapType.EMPTY_ARRAY : stackMapTypeArr;
        this.typesOfStackItems = stackMapTypeArr2 == null ? StackMapType.EMPTY_ARRAY : stackMapTypeArr2;
        this.constantPool = constantPool;
        if (i2 < 0) {
            w01.a("numberOfLocals < 0");
            throw null;
        }
        if (i3 >= 0) {
            return;
        }
        w01.a("numberOfStackItems < 0");
        throw null;
    }

    public StackMapEntry(int i, int i2, StackMapType[] stackMapTypeArr, StackMapType[] stackMapTypeArr2, ConstantPool constantPool) {
        this.frameType = i;
        this.byteCodeOffset = i2;
        this.typesOfLocals = stackMapTypeArr == null ? StackMapType.EMPTY_ARRAY : stackMapTypeArr;
        this.typesOfStackItems = stackMapTypeArr2 == null ? StackMapType.EMPTY_ARRAY : stackMapTypeArr2;
        this.constantPool = constantPool;
    }
}
