package com.reandroid.archive.block;

import com.reandroid.arsc.container.ExpandableBlockContainer;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public abstract class SignatureScheme extends ExpandableBlockContainer {
    private final SignatureId signatureId;

    public SignatureScheme(int i, SignatureId signatureId) {
        super(i);
        this.signatureId = signatureId;
    }

    public abstract Iterator<CertificateBlock> getCertificates();

    public SignatureId getSignatureId() {
        return this.signatureId;
    }

    public SignatureInfo getSignatureInfo() {
        return (SignatureInfo) getParent(SignatureInfo.class);
    }

    public String toString() {
        return "id=" + getSignatureId();
    }
}
