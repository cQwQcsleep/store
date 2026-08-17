package com.fasterxml.aalto.in;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class ElementScope {
    PName mName;
    ElementScope mParent;

    public ElementScope(PName pName, ElementScope elementScope) {
        this.mParent = elementScope;
        this.mName = pName;
    }

    public PName getName() {
        return this.mName;
    }

    public ElementScope getParent() {
        return this.mParent;
    }

    public String toString() {
        if (this.mParent == null) {
            return this.mName.toString();
        }
        return this.mParent.toString() + "/" + this.mName;
    }
}
