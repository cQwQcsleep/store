package org.jetbrains.kotlin.codegen.signature;

import com.intellij.util.containers.Stack;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.types.Variance;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.signature.SignatureVisitor;
import org.jetbrains.org.objectweb.asm.signature.SignatureWriter;
import org.jetbrains.org.objectweb.asm.util.CheckSignatureAdapter;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public class BothSignatureWriter extends JvmSignatureWriter {
    private boolean generic;
    private final SignatureVisitor signatureVisitor;
    private final SignatureWriter signatureWriter;
    private final Stack<SignatureVisitor> visitors;

    /* JADX INFO: renamed from: org.jetbrains.kotlin.codegen.signature.BothSignatureWriter$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$jetbrains$kotlin$types$Variance;

        static {
            int[] iArr = new int[Variance.values().length];
            $SwitchMap$org$jetbrains$kotlin$types$Variance = iArr;
            try {
                iArr[Variance.INVARIANT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jetbrains$kotlin$types$Variance[Variance.IN_VARIANCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$jetbrains$kotlin$types$Variance[Variance.OUT_VARIANCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0026  */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i == 1) {
            objArr[0] = "asmType";
        } else if (i == 2) {
            objArr[0] = "variance";
        } else if (i == 3) {
            objArr[0] = "projectionKind";
        } else if (i == 4) {
            objArr[0] = ModuleXmlParser.NAME;
        } else if (i != 5) {
            objArr[0] = "mode";
        } else {
            objArr[0] = "asmType";
        }
        objArr[1] = "org/jetbrains/kotlin/codegen/signature/BothSignatureWriter";
        if (i == 1) {
            objArr[2] = "writeAsmType";
        } else if (i == 2) {
            objArr[2] = "toJvmVariance";
        } else if (i == 3) {
            objArr[2] = "writeTypeArgument";
        } else if (i == 4 || i == 5) {
            objArr[2] = "writeTypeVariable";
        } else {
            objArr[2] = "<init>";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public BothSignatureWriter(Mode mode) {
        if (mode == null) {
            $$$reportNull$$$0(0);
        }
        SignatureVisitor signatureWriter = new SignatureWriter();
        this.signatureWriter = signatureWriter;
        this.generic = false;
        this.visitors = new Stack<>();
        this.signatureVisitor = Mode.access$000(mode) != null ? new CheckSignatureAdapter(Mode.access$000(mode).intValue(), signatureWriter) : signatureWriter;
    }

    private void pop() {
        this.visitors.pop();
    }

    private void push(SignatureVisitor signatureVisitor) {
        this.visitors.push(signatureVisitor);
    }

    private SignatureVisitor signatureVisitor() {
        return !this.visitors.isEmpty() ? (SignatureVisitor) this.visitors.peek() : this.signatureVisitor;
    }

    private static char toJvmVariance(Variance variance) {
        if (variance == null) {
            $$$reportNull$$$0(2);
        }
        int i = AnonymousClass1.$SwitchMap$org$jetbrains$kotlin$types$Variance[variance.ordinal()];
        if (i == 1) {
            return '=';
        }
        if (i == 2) {
            return '-';
        }
        if (i == 3) {
            return '+';
        }
        qu7.a("Unknown variance: ", variance);
        return (char) 0;
    }

    @Override // org.jetbrains.kotlin.codegen.signature.JvmSignatureWriter
    public String makeJavaGenericSignature() {
        if (this.generic) {
            return this.signatureWriter.toString();
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.codegen.signature.JvmSignatureWriter
    public boolean skipGenericSignature() {
        return false;
    }

    @Override // org.jetbrains.kotlin.codegen.signature.JvmSignatureWriter
    public String toString() {
        return this.signatureWriter.toString();
    }

    @Override // org.jetbrains.kotlin.load.kotlin.JvmDescriptorTypeWriter
    public void writeArrayEnd() {
        pop();
        super.writeArrayEnd();
    }

    @Override // org.jetbrains.kotlin.load.kotlin.JvmDescriptorTypeWriter
    public void writeArrayType() {
        push(signatureVisitor().visitArrayType());
        super.writeArrayType();
    }

    @Override // org.jetbrains.kotlin.codegen.signature.JvmSignatureWriter
    public void writeAsmType(Type type) {
        if (type == null) {
            $$$reportNull$$$0(1);
        }
        if (type.getSort() != 10 && type.getSort() != 9) {
            signatureVisitor().visitBaseType(type.getDescriptor().charAt(0));
        }
        super.writeAsmType(type);
    }

    @Override // org.jetbrains.kotlin.codegen.signature.JvmSignatureWriter
    public void writeClassBegin(Type type) {
        signatureVisitor().visitClassType(type.getInternalName());
        super.writeClassBegin(type);
    }

    @Override // org.jetbrains.kotlin.codegen.signature.JvmSignatureWriter
    public void writeClassBound() {
        push(signatureVisitor().visitClassBound());
        super.writeClassBound();
    }

    @Override // org.jetbrains.kotlin.codegen.signature.JvmSignatureWriter
    public void writeClassBoundEnd() {
        pop();
        super.writeClassBoundEnd();
    }

    @Override // org.jetbrains.kotlin.codegen.signature.JvmSignatureWriter
    public void writeClassEnd() {
        signatureVisitor().visitEnd();
        super.writeClassEnd();
    }

    @Override // org.jetbrains.kotlin.codegen.signature.JvmSignatureWriter
    public void writeFormalTypeParameter(String str) {
        signatureVisitor().visitFormalTypeParameter(str);
        this.generic = true;
        super.writeFormalTypeParameter(str);
    }

    @Override // org.jetbrains.kotlin.codegen.signature.JvmSignatureWriter
    public void writeInnerClass(String str) {
        signatureVisitor().visitInnerClassType(str);
        super.writeInnerClass(str);
    }

    @Override // org.jetbrains.kotlin.codegen.signature.JvmSignatureWriter
    public void writeInterface() {
        push(signatureVisitor().visitInterface());
        super.writeInterface();
    }

    @Override // org.jetbrains.kotlin.codegen.signature.JvmSignatureWriter
    public void writeInterfaceBound() {
        push(signatureVisitor().visitInterfaceBound());
        super.writeInterfaceBound();
    }

    @Override // org.jetbrains.kotlin.codegen.signature.JvmSignatureWriter
    public void writeInterfaceBoundEnd() {
        pop();
        super.writeInterfaceBoundEnd();
    }

    @Override // org.jetbrains.kotlin.codegen.signature.JvmSignatureWriter
    public void writeInterfaceEnd() {
        pop();
        super.writeInterfaceEnd();
    }

    @Override // org.jetbrains.kotlin.codegen.signature.JvmSignatureWriter
    public void writeOuterClassBegin(Type type, String str) {
        signatureVisitor().visitClassType(str);
        super.writeOuterClassBegin(type, str);
    }

    @Override // org.jetbrains.kotlin.codegen.signature.JvmSignatureWriter
    public void writeParameterType(WritingParameterToGenericSignatureMode writingParameterToGenericSignatureMode) {
        if (writingParameterToGenericSignatureMode == WritingParameterToGenericSignatureMode.REGULAR) {
            push(signatureVisitor().visitParameterType());
        } else {
            push(new SignatureWriter());
        }
        if (writingParameterToGenericSignatureMode == WritingParameterToGenericSignatureMode.ENUM_CONSTRUCTOR_SYNTHETIC_PARAMETER) {
            this.generic = true;
        }
        super.writeParameterType(writingParameterToGenericSignatureMode);
    }

    @Override // org.jetbrains.kotlin.codegen.signature.JvmSignatureWriter
    public void writeParameterTypeEnd() {
        pop();
        super.writeParameterTypeEnd();
    }

    @Override // org.jetbrains.kotlin.codegen.signature.JvmSignatureWriter
    public void writeParametersStart() {
        super.writeParametersStart();
    }

    @Override // org.jetbrains.kotlin.codegen.signature.JvmSignatureWriter
    public void writeReturnType() {
        push(signatureVisitor().visitReturnType());
        super.writeReturnType();
    }

    @Override // org.jetbrains.kotlin.codegen.signature.JvmSignatureWriter
    public void writeReturnTypeEnd() {
        pop();
        super.writeReturnTypeEnd();
    }

    @Override // org.jetbrains.kotlin.codegen.signature.JvmSignatureWriter
    public void writeSuperclass() {
        push(signatureVisitor().visitSuperclass());
        super.writeSuperclass();
    }

    @Override // org.jetbrains.kotlin.codegen.signature.JvmSignatureWriter
    public void writeSuperclassEnd() {
        pop();
        super.writeSuperclassEnd();
    }

    @Override // org.jetbrains.kotlin.codegen.signature.JvmSignatureWriter
    public void writeTypeArgument(Variance variance) {
        if (variance == null) {
            $$$reportNull$$$0(3);
        }
        push(signatureVisitor().visitTypeArgument(toJvmVariance(variance)));
        this.generic = true;
        super.writeTypeArgument(variance);
    }

    @Override // org.jetbrains.kotlin.codegen.signature.JvmSignatureWriter
    public void writeTypeArgumentEnd() {
        pop();
        super.writeTypeArgumentEnd();
    }

    @Override // org.jetbrains.kotlin.load.kotlin.JvmDescriptorTypeWriter
    public void writeTypeVariable(Name name, Type type) {
        if (name == null) {
            $$$reportNull$$$0(4);
        }
        if (type == null) {
            $$$reportNull$$$0(5);
        }
        signatureVisitor().visitTypeVariable(name.asString());
        this.generic = true;
        super.writeTypeVariable(name, type);
    }

    @Override // org.jetbrains.kotlin.codegen.signature.JvmSignatureWriter
    public void writeUnboundedWildcard() {
        signatureVisitor().visitTypeArgument();
        this.generic = true;
        super.writeUnboundedWildcard();
    }
}
