package org.eclipse.jdt.internal.compiler.classfmt;

import org.eclipse.jdt.internal.compiler.env.IBinaryNestedType;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class InnerClassInfo extends ClassFileStruct implements IBinaryNestedType {
    private int accessFlags;
    private char[] innerClassName;
    int innerClassNameIndex;
    private char[] innerName;
    int innerNameIndex;
    private char[] outerClassName;
    int outerClassNameIndex;
    private boolean readInnerClassName;
    private boolean readInnerName;
    private boolean readOuterClassName;

    public InnerClassInfo(byte[] bArr, int[] iArr, int i) {
        super(bArr, iArr, i);
        this.innerClassNameIndex = -1;
        this.outerClassNameIndex = -1;
        this.innerNameIndex = -1;
        this.accessFlags = -1;
        this.innerClassNameIndex = u2At(0);
        this.outerClassNameIndex = u2At(2);
        this.innerNameIndex = u2At(4);
    }

    @Override // org.eclipse.jdt.internal.compiler.env.IBinaryNestedType
    public char[] getEnclosingTypeName() {
        if (!this.readOuterClassName) {
            int i = this.outerClassNameIndex;
            if (i != 0) {
                int[] iArr = this.constantPoolOffsets;
                int i2 = iArr[u2At((iArr[i] - this.structOffset) + 1)] - this.structOffset;
                this.outerClassName = utf8At(i2 + 3, u2At(i2 + 1));
            }
            this.readOuterClassName = true;
        }
        return this.outerClassName;
    }

    @Override // org.eclipse.jdt.internal.compiler.env.IBinaryNestedType
    public int getModifiers() {
        if (this.accessFlags == -1) {
            this.accessFlags = u2At(6);
        }
        return this.accessFlags;
    }

    @Override // org.eclipse.jdt.internal.compiler.env.IBinaryNestedType
    public char[] getName() {
        if (!this.readInnerClassName) {
            int i = this.innerClassNameIndex;
            if (i != 0) {
                int[] iArr = this.constantPoolOffsets;
                int i2 = iArr[u2At((iArr[i] - this.structOffset) + 1)] - this.structOffset;
                this.innerClassName = utf8At(i2 + 3, u2At(i2 + 1));
            }
            this.readInnerClassName = true;
        }
        return this.innerClassName;
    }

    public char[] getSourceName() {
        if (!this.readInnerName) {
            int i = this.innerNameIndex;
            if (i != 0) {
                int i2 = this.constantPoolOffsets[i] - this.structOffset;
                this.innerName = utf8At(i2 + 3, u2At(i2 + 1));
            }
            this.readInnerName = true;
        }
        return this.innerName;
    }

    public void initialize() {
        getModifiers();
        getName();
        getSourceName();
        getEnclosingTypeName();
        reset();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (getName() != null) {
            sb.append(getName());
        }
        sb.append("\n");
        if (getEnclosingTypeName() != null) {
            sb.append(getEnclosingTypeName());
        }
        sb.append("\n");
        if (getSourceName() != null) {
            sb.append(getSourceName());
        }
        return sb.toString();
    }
}
