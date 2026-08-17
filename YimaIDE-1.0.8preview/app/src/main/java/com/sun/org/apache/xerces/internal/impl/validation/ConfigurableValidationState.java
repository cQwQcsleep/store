package com.sun.org.apache.xerces.internal.impl.validation;

import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ConfigurableValidationState extends ValidationState {
    private boolean fIdIdrefChecking = true;
    private boolean fUnparsedEntityChecking = true;

    @Override // com.sun.org.apache.xerces.internal.impl.validation.ValidationState, com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
    public void addId(String str) {
        if (this.fIdIdrefChecking) {
            super.addId(str);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.validation.ValidationState, com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
    public void addIdRef(String str) {
        if (this.fIdIdrefChecking) {
            super.addIdRef(str);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.validation.ValidationState
    public Iterator<String> checkIDRefID() {
        if (this.fIdIdrefChecking) {
            return super.checkIDRefID();
        }
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.validation.ValidationState, com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
    public boolean isEntityDeclared(String str) {
        if (this.fUnparsedEntityChecking) {
            return super.isEntityDeclared(str);
        }
        return true;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.validation.ValidationState, com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
    public boolean isEntityUnparsed(String str) {
        if (this.fUnparsedEntityChecking) {
            return super.isEntityUnparsed(str);
        }
        return true;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.validation.ValidationState, com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
    public boolean isIdDeclared(String str) {
        if (this.fIdIdrefChecking) {
            return super.isIdDeclared(str);
        }
        return false;
    }

    public void setIdIdrefChecking(boolean z) {
        this.fIdIdrefChecking = z;
    }

    public void setUnparsedEntityChecking(boolean z) {
        this.fUnparsedEntityChecking = z;
    }
}
