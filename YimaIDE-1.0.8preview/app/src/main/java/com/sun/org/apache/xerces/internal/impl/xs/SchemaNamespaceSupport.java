package com.sun.org.apache.xerces.internal.impl.xs;

import com.sun.org.apache.xerces.internal.util.NamespaceSupport;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SchemaNamespaceSupport extends NamespaceSupport {
    public SchemaNamespaceSupport(SchemaNamespaceSupport schemaNamespaceSupport) {
        int i = schemaNamespaceSupport.fNamespaceSize;
        this.fNamespaceSize = i;
        if (this.fNamespace.length < i) {
            this.fNamespace = new String[i];
        }
        System.arraycopy(schemaNamespaceSupport.fNamespace, 0, this.fNamespace, 0, i);
        int i2 = schemaNamespaceSupport.fCurrentContext;
        this.fCurrentContext = i2;
        if (this.fContext.length <= i2) {
            this.fContext = new int[i2 + 1];
        }
        System.arraycopy(schemaNamespaceSupport.fContext, 0, this.fContext, 0, i2 + 1);
    }

    public String[] getEffectiveLocalContext() {
        int i;
        int i2;
        if (this.fCurrentContext < 3 || (i2 = this.fNamespaceSize - (i = this.fContext[3])) <= 0) {
            return null;
        }
        String[] strArr = new String[i2];
        System.arraycopy(this.fNamespace, i, strArr, 0, i2);
        return strArr;
    }

    public void makeGlobal() {
        if (this.fCurrentContext >= 3) {
            this.fCurrentContext = 3;
            this.fNamespaceSize = this.fContext[3];
        }
    }

    public void setEffectiveContext(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return;
        }
        pushContext();
        int length = this.fNamespaceSize + strArr.length;
        String[] strArr2 = this.fNamespace;
        if (strArr2.length < length) {
            String[] strArr3 = new String[length];
            System.arraycopy(strArr2, 0, strArr3, 0, strArr2.length);
            this.fNamespace = strArr3;
        }
        System.arraycopy(strArr, 0, this.fNamespace, this.fNamespaceSize, strArr.length);
        this.fNamespaceSize = length;
    }

    public SchemaNamespaceSupport() {
    }
}
