package com.sun.org.apache.xerces.internal.impl.xs.identity;

import com.sun.org.apache.xerces.internal.xs.XSIDCDefinition;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class KeyRef extends IdentityConstraint {
    protected UniqueOrKey fKey;

    public KeyRef(String str, String str2, String str3, UniqueOrKey uniqueOrKey) {
        super(str, str2, str3);
        this.fKey = uniqueOrKey;
        this.type = (short) 2;
    }

    public UniqueOrKey getKey() {
        return this.fKey;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.identity.IdentityConstraint, com.sun.org.apache.xerces.internal.xs.XSIDCDefinition
    public XSIDCDefinition getRefKey() {
        return this.fKey;
    }
}
