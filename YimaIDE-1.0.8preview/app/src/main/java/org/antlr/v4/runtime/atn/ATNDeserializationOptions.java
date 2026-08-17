package org.antlr.v4.runtime.atn;

import defpackage.k2d;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class ATNDeserializationOptions {
    private static final ATNDeserializationOptions defaultOptions;
    private boolean generateRuleBypassTransitions;
    private boolean readOnly;
    private boolean verifyATN;

    static {
        ATNDeserializationOptions aTNDeserializationOptions = new ATNDeserializationOptions();
        defaultOptions = aTNDeserializationOptions;
        aTNDeserializationOptions.makeReadOnly();
    }

    public ATNDeserializationOptions(ATNDeserializationOptions aTNDeserializationOptions) {
        this.verifyATN = aTNDeserializationOptions.verifyATN;
        this.generateRuleBypassTransitions = aTNDeserializationOptions.generateRuleBypassTransitions;
    }

    public static ATNDeserializationOptions getDefaultOptions() {
        return defaultOptions;
    }

    public final boolean isGenerateRuleBypassTransitions() {
        return this.generateRuleBypassTransitions;
    }

    public final boolean isReadOnly() {
        return this.readOnly;
    }

    public final boolean isVerifyATN() {
        return this.verifyATN;
    }

    public final void makeReadOnly() {
        this.readOnly = true;
    }

    public final void setGenerateRuleBypassTransitions(boolean z) {
        throwIfReadOnly();
        this.generateRuleBypassTransitions = z;
    }

    public final void setVerifyATN(boolean z) {
        throwIfReadOnly();
        this.verifyATN = z;
    }

    public void throwIfReadOnly() {
        if (isReadOnly()) {
            k2d.a("The object is read only.");
        }
    }

    public ATNDeserializationOptions() {
        this.verifyATN = true;
        this.generateRuleBypassTransitions = false;
    }
}
