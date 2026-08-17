package com.sun.org.apache.xerces.internal.impl.xs.identity;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface FieldActivator {
    XPathMatcher activateField(Field field, int i);

    void endValueScopeFor(IdentityConstraint identityConstraint, int i);

    void startValueScopeFor(IdentityConstraint identityConstraint, int i);
}
