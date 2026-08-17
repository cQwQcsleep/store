package com.sun.org.apache.bcel.internal.classfile;

import com.sun.jna.platform.win32.COM.tlb.imp.TlbBase;
import com.sun.org.apache.bcel.internal.util.Args;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class CodeException implements Cloneable, Node {
    static final CodeException[] EMPTY_CODE_EXCEPTION_ARRAY = new CodeException[0];
    private int catchType;
    private int endPc;
    private int handlerPc;
    private int startPc;

    public CodeException(int i, int i2, int i3, int i4) {
        this.startPc = Args.requireU2(i, "startPc");
        this.endPc = Args.requireU2(i2, "endPc");
        this.handlerPc = Args.requireU2(i3, "handlerPc");
        this.catchType = Args.requireU2(i4, "catchType");
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitCodeException(this);
    }

    public CodeException copy() {
        try {
            return (CodeException) clone();
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.startPc);
        dataOutputStream.writeShort(this.endPc);
        dataOutputStream.writeShort(this.handlerPc);
        dataOutputStream.writeShort(this.catchType);
    }

    public int getCatchType() {
        return this.catchType;
    }

    public int getEndPC() {
        return this.endPc;
    }

    public int getHandlerPC() {
        return this.handlerPc;
    }

    public int getStartPC() {
        return this.startPc;
    }

    public void setCatchType(int i) {
        this.catchType = i;
    }

    public void setEndPC(int i) {
        this.endPc = i;
    }

    public void setHandlerPC(int i) {
        this.handlerPc = i;
    }

    public void setStartPC(int i) {
        this.startPc = i;
    }

    public String toString(ConstantPool constantPool, boolean z) {
        String str;
        String string;
        if (this.catchType == 0) {
            string = "<Any exception>(0)";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(Utility.compactClassName(constantPool.getConstantString(this.catchType, (byte) 7), false));
            if (z) {
                str = "(" + this.catchType + ")";
            } else {
                str = "";
            }
            sb.append(str);
            string = sb.toString();
        }
        return this.startPc + TlbBase.TAB + this.endPc + TlbBase.TAB + this.handlerPc + TlbBase.TAB + string;
    }

    public CodeException(DataInput dataInput) throws IOException {
        this(dataInput.readUnsignedShort(), dataInput.readUnsignedShort(), dataInput.readUnsignedShort(), dataInput.readUnsignedShort());
    }

    public CodeException(CodeException codeException) {
        this(codeException.getStartPC(), codeException.getEndPC(), codeException.getHandlerPC(), codeException.getCatchType());
    }

    public String toString(ConstantPool constantPool) {
        return toString(constantPool, true);
    }

    public String toString() {
        return "CodeException(startPc = " + this.startPc + ", endPc = " + this.endPc + ", handlerPc = " + this.handlerPc + ", catchType = " + this.catchType + ")";
    }
}
