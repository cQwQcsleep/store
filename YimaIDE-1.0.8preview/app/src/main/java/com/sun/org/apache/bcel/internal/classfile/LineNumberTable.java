package com.sun.org.apache.bcel.internal.classfile;

import com.sun.org.apache.bcel.internal.util.Args;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.IntFunction;
import java.util.stream.Stream;
import jdk.xml.internal.SecuritySupport;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class LineNumberTable extends Attribute implements Iterable<LineNumber> {
    private static final int MAX_LINE_LENGTH = 72;
    private LineNumber[] lineNumberTable;

    public LineNumberTable(int i, int i2, DataInput dataInput, ConstantPool constantPool) throws IOException {
        this(i, i2, (LineNumber[]) null, constantPool);
        int unsignedShort = dataInput.readUnsignedShort();
        this.lineNumberTable = new LineNumber[unsignedShort];
        for (int i3 = 0; i3 < unsignedShort; i3++) {
            this.lineNumberTable[i3] = new LineNumber(dataInput);
        }
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute, com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitLineNumberTable(this);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public Attribute copy(ConstantPool constantPool) {
        LineNumberTable lineNumberTable = (LineNumberTable) clone();
        LineNumber[] lineNumberArr = new LineNumber[this.lineNumberTable.length];
        lineNumberTable.lineNumberTable = lineNumberArr;
        Arrays.setAll(lineNumberArr, new IntFunction() { // from class: w19
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return this.b.lineNumberTable[i].copy();
            }
        });
        lineNumberTable.setConstantPool(constantPool);
        return lineNumberTable;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        super.dump(dataOutputStream);
        dataOutputStream.writeShort(this.lineNumberTable.length);
        for (LineNumber lineNumber : this.lineNumberTable) {
            lineNumber.dump(dataOutputStream);
        }
    }

    public LineNumber[] getLineNumberTable() {
        return this.lineNumberTable;
    }

    public int getSourceLine(int i) {
        int length = this.lineNumberTable.length - 1;
        if (length < 0) {
            return -1;
        }
        int i2 = 0;
        int i3 = -1;
        int i4 = -1;
        do {
            int i5 = (i2 + length) >>> 1;
            int startPC = this.lineNumberTable[i5].getStartPC();
            if (startPC == i) {
                return this.lineNumberTable[i5].getLineNumber();
            }
            if (i < startPC) {
                length = i5 - 1;
            } else {
                i2 = i5 + 1;
            }
            if (startPC < i && startPC > i3) {
                i4 = i5;
                i3 = startPC;
            }
        } while (i2 <= length);
        if (i4 < 0) {
            return -1;
        }
        return this.lineNumberTable[i4].getLineNumber();
    }

    public int getTableLength() {
        LineNumber[] lineNumberArr = this.lineNumberTable;
        if (lineNumberArr == null) {
            return 0;
        }
        return lineNumberArr.length;
    }

    @Override // java.lang.Iterable
    public Iterator<LineNumber> iterator() {
        return Stream.of((Object[]) this.lineNumberTable).iterator();
    }

    public void setLineNumberTable(LineNumber[] lineNumberArr) {
        this.lineNumberTable = lineNumberArr;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        int i = 0;
        while (true) {
            LineNumber[] lineNumberArr = this.lineNumberTable;
            if (i >= lineNumberArr.length) {
                sb.append((CharSequence) sb2);
                return sb.toString();
            }
            sb2.append(lineNumberArr[i].toString());
            if (i < this.lineNumberTable.length - 1) {
                sb2.append(", ");
            }
            if (sb2.length() > 72 && i < this.lineNumberTable.length - 1) {
                sb2.append(SecuritySupport.NEWLINE);
                sb.append((CharSequence) sb2);
                sb2.setLength(0);
            }
            i++;
        }
    }

    public LineNumberTable(int i, int i2, LineNumber[] lineNumberArr, ConstantPool constantPool) {
        super((byte) 4, i, i2, constantPool);
        lineNumberArr = lineNumberArr == null ? LineNumber.EMPTY_ARRAY : lineNumberArr;
        this.lineNumberTable = lineNumberArr;
        Args.requireU2(lineNumberArr.length, "lineNumberTable.length");
    }

    public LineNumberTable(LineNumberTable lineNumberTable) {
        this(lineNumberTable.getNameIndex(), lineNumberTable.getLength(), lineNumberTable.getLineNumberTable(), lineNumberTable.getConstantPool());
    }
}
