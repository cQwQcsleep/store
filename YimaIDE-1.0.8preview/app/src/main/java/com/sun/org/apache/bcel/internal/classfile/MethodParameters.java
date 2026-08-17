package com.sun.org.apache.bcel.internal.classfile;

import com.sun.org.apache.bcel.internal.Const;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.IntFunction;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class MethodParameters extends Attribute implements Iterable<MethodParameter> {
    private static final MethodParameter[] EMPTY_METHOD_PARAMETER_ARRAY = new MethodParameter[0];
    private MethodParameter[] parameters;

    public MethodParameters(int i, int i2, DataInput dataInput, ConstantPool constantPool) throws IOException {
        super(Const.ATTR_METHOD_PARAMETERS, i, i2, constantPool);
        this.parameters = EMPTY_METHOD_PARAMETER_ARRAY;
        int unsignedByte = dataInput.readUnsignedByte();
        this.parameters = new MethodParameter[unsignedByte];
        for (int i3 = 0; i3 < unsignedByte; i3++) {
            this.parameters[i3] = new MethodParameter(dataInput);
        }
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute, com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitMethodParameters(this);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public Attribute copy(ConstantPool constantPool) {
        MethodParameters methodParameters = (MethodParameters) clone();
        MethodParameter[] methodParameterArr = new MethodParameter[this.parameters.length];
        methodParameters.parameters = methodParameterArr;
        Arrays.setAll(methodParameterArr, new IntFunction() { // from class: t1a
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return this.b.parameters[i].copy();
            }
        });
        methodParameters.setConstantPool(constantPool);
        return methodParameters;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        super.dump(dataOutputStream);
        dataOutputStream.writeByte(this.parameters.length);
        for (MethodParameter methodParameter : this.parameters) {
            methodParameter.dump(dataOutputStream);
        }
    }

    public MethodParameter[] getParameters() {
        return this.parameters;
    }

    @Override // java.lang.Iterable
    public Iterator<MethodParameter> iterator() {
        return Stream.of((Object[]) this.parameters).iterator();
    }

    public void setParameters(MethodParameter[] methodParameterArr) {
        this.parameters = methodParameterArr;
    }
}
