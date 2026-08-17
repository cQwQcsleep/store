package com.sun.org.apache.bcel.internal.classfile;

import com.sun.org.apache.bcel.internal.util.Args;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class LineNumber implements Cloneable, Node {
    static final LineNumber[] EMPTY_ARRAY = new LineNumber[0];
    private int lineNumber;
    private int startPc;

    public LineNumber(int i, int i2) {
        this.startPc = Args.requireU2(i, "startPc");
        this.lineNumber = Args.requireU2(i2, "lineNumber");
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitLineNumber(this);
    }

    public LineNumber copy() {
        try {
            return (LineNumber) clone();
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.startPc);
        dataOutputStream.writeShort(this.lineNumber);
    }

    public int getLineNumber() {
        return this.lineNumber & 65535;
    }

    public int getStartPC() {
        return this.startPc & 65535;
    }

    public void setLineNumber(int i) {
        this.lineNumber = (short) i;
    }

    public void setStartPC(int i) {
        this.startPc = (short) i;
    }

    public String toString() {
        return "LineNumber(" + getStartPC() + ", " + getLineNumber() + ")";
    }

    public LineNumber(DataInput dataInput) throws IOException {
        this(dataInput.readUnsignedShort(), dataInput.readUnsignedShort());
    }

    public LineNumber(LineNumber lineNumber) {
        this(lineNumber.getStartPC(), lineNumber.getLineNumber());
    }
}
