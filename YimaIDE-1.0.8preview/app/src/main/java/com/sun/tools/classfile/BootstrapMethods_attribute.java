package com.sun.tools.classfile;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class BootstrapMethods_attribute extends Attribute {
    public final BootstrapMethodSpecifier[] bootstrap_method_specifiers;

    public BootstrapMethods_attribute(ClassReader classReader, int i, int i2) throws AttributeException, IOException {
        super(i, i2);
        this.bootstrap_method_specifiers = new BootstrapMethodSpecifier[classReader.readUnsignedShort()];
        int i3 = 0;
        while (true) {
            BootstrapMethodSpecifier[] bootstrapMethodSpecifierArr = this.bootstrap_method_specifiers;
            if (i3 >= bootstrapMethodSpecifierArr.length) {
                return;
            }
            bootstrapMethodSpecifierArr[i3] = new BootstrapMethodSpecifier(classReader);
            i3++;
        }
    }

    public static int length(BootstrapMethodSpecifier[] bootstrapMethodSpecifierArr) {
        int length = 2;
        for (BootstrapMethodSpecifier bootstrapMethodSpecifier : bootstrapMethodSpecifierArr) {
            length += bootstrapMethodSpecifier.length();
        }
        return length;
    }

    @Override // com.sun.tools.classfile.Attribute
    public <R, P> R accept(Attribute.Visitor<R, P> visitor, P p) {
        return visitor.visitBootstrapMethods(this, p);
    }

    public BootstrapMethods_attribute(int i, BootstrapMethodSpecifier[] bootstrapMethodSpecifierArr) {
        super(i, length(bootstrapMethodSpecifierArr));
        this.bootstrap_method_specifiers = bootstrapMethodSpecifierArr;
    }

    public static class BootstrapMethodSpecifier {
        public int[] bootstrap_arguments;
        public int bootstrap_method_ref;

        public BootstrapMethodSpecifier(ClassReader classReader) throws IOException {
            this.bootstrap_method_ref = classReader.readUnsignedShort();
            this.bootstrap_arguments = new int[classReader.readUnsignedShort()];
            int i = 0;
            while (true) {
                int[] iArr = this.bootstrap_arguments;
                if (i >= iArr.length) {
                    return;
                }
                iArr[i] = classReader.readUnsignedShort();
                i++;
            }
        }

        public int length() {
            return (this.bootstrap_arguments.length * 2) + 4;
        }

        public BootstrapMethodSpecifier(int i, int[] iArr) {
            this.bootstrap_method_ref = i;
            this.bootstrap_arguments = iArr;
        }
    }
}
