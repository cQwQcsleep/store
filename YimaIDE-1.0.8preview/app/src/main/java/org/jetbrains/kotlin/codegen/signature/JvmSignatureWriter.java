package org.jetbrains.kotlin.codegen.signature;

import java.util.ArrayList;
import java.util.List;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.AsmUtil;
import org.jetbrains.kotlin.load.kotlin.JvmDescriptorTypeWriter;
import org.jetbrains.kotlin.resolve.jvm.jvmSignature.JvmMethodGenericSignature;
import org.jetbrains.kotlin.resolve.jvm.jvmSignature.JvmMethodParameterKind;
import org.jetbrains.kotlin.types.Variance;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.commons.Method;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public class JvmSignatureWriter extends JvmDescriptorTypeWriter<Type> {
    private int currentSignatureSize;
    private Type jvmReturnType;
    private final List<Type> kotlinParameterTypes;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i == 1) {
            objArr[0] = "asmType";
        } else if (i == 2) {
            objArr[0] = "projectionKind";
        } else if (i == 3) {
            objArr[0] = "parameterKind";
        } else if (i != 4) {
            objArr[0] = "objectType";
        } else {
            objArr[0] = ModuleXmlParser.NAME;
        }
        objArr[1] = "org/jetbrains/kotlin/codegen/signature/JvmSignatureWriter";
        if (i == 1) {
            objArr[2] = "writeAsmType";
        } else if (i == 2) {
            objArr[2] = "writeTypeArgument";
        } else if (i == 3) {
            objArr[2] = "writeParameterType";
        } else if (i != 4) {
            objArr[2] = "writeClass";
        } else {
            objArr[2] = "makeJvmMethodSignature";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public JvmSignatureWriter() {
        super(AsmTypeFactory.INSTANCE);
        this.kotlinParameterTypes = new ArrayList();
        this.currentSignatureSize = 0;
    }

    public int getCurrentSignatureSize() {
        return this.currentSignatureSize;
    }

    public String makeJavaGenericSignature() {
        return null;
    }

    public JvmMethodGenericSignature makeJvmMethodSignature(String str) {
        if (str == null) {
            $$$reportNull$$$0(4);
        }
        Type[] typeArr = new Type[this.kotlinParameterTypes.size()];
        for (int i = 0; i < this.kotlinParameterTypes.size(); i++) {
            typeArr[i] = this.kotlinParameterTypes.get(i);
        }
        return new JvmMethodGenericSignature(new Method(str, this.jvmReturnType, typeArr), this.kotlinParameterTypes, makeJavaGenericSignature());
    }

    public boolean skipGenericSignature() {
        return true;
    }

    public String toString() {
        return "empty";
    }

    public void writeAsmType(Type type) {
        if (type == null) {
            $$$reportNull$$$0(1);
        }
        int sort = type.getSort();
        if (sort == 9) {
            writeArrayType();
            writeAsmType(AsmUtil.correctElementType(type));
            writeArrayEnd();
        } else if (sort != 10) {
            writeJvmTypeAsIs(type);
        } else {
            writeClassBegin(type);
            writeClassEnd();
        }
    }

    @Override // org.jetbrains.kotlin.load.kotlin.JvmDescriptorTypeWriter
    public void writeClass(Type type) {
        if (type == null) {
            $$$reportNull$$$0(0);
        }
        writeClassBegin(type);
        writeClassEnd();
    }

    public void writeClassBegin(Type type) {
        writeJvmTypeAsIs(type);
    }

    public void writeClassBound() {
    }

    public void writeClassBoundEnd() {
    }

    public void writeClassEnd() {
    }

    public void writeFormalTypeParameter(String str) {
    }

    public void writeInnerClass(String str) {
    }

    public void writeInterface() {
    }

    public void writeInterfaceBound() {
    }

    public void writeInterfaceBoundEnd() {
    }

    public void writeInterfaceEnd() {
    }

    public void writeOuterClassBegin(Type type, String str) {
        writeJvmTypeAsIs(type);
    }

    public void writeParameterType(JvmMethodParameterKind jvmMethodParameterKind) {
        if (jvmMethodParameterKind == null) {
            $$$reportNull$$$0(3);
        }
        if (jvmMethodParameterKind == JvmMethodParameterKind.ENUM_NAME_OR_ORDINAL) {
            writeParameterType(WritingParameterToGenericSignatureMode.ENUM_CONSTRUCTOR_SYNTHETIC_PARAMETER);
        } else if (jvmMethodParameterKind == JvmMethodParameterKind.OUTER) {
            writeParameterType(WritingParameterToGenericSignatureMode.OUTER_THIS);
        } else {
            writeParameterType(WritingParameterToGenericSignatureMode.REGULAR);
        }
    }

    public void writeParameterTypeEnd() {
        this.kotlinParameterTypes.add(getJvmCurrentType());
        this.currentSignatureSize += getJvmCurrentType().getSize();
        clearCurrentType();
    }

    public void writeParametersStart() {
        clearCurrentType();
    }

    public void writeReturnType() {
    }

    public void writeReturnTypeEnd() {
        this.jvmReturnType = getJvmCurrentType();
        clearCurrentType();
    }

    public void writeSuperclass() {
    }

    public void writeSuperclassEnd() {
    }

    public void writeTypeArgument(Variance variance) {
        if (variance == null) {
            $$$reportNull$$$0(2);
        }
    }

    public void writeTypeArgumentEnd() {
    }

    public void writeUnboundedWildcard() {
    }

    public void writeParameterType(WritingParameterToGenericSignatureMode writingParameterToGenericSignatureMode) {
    }
}
