package org.bouncycastle.pqc.crypto.xmss;

import defpackage.x0e;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.crypto.Digest;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
final class WOTSPlusParameters {
    private final int digestSize;
    private final int len;
    private final int len1;
    private final int len2;
    private final XMSSOid oid;
    private final ASN1ObjectIdentifier treeDigest;
    private final int winternitzParameter;

    public WOTSPlusParameters(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        if (aSN1ObjectIdentifier == null) {
            x0e.a("treeDigest == null");
            throw null;
        }
        this.treeDigest = aSN1ObjectIdentifier;
        Digest digest = DigestUtil.getDigest(aSN1ObjectIdentifier);
        int digestSize = XMSSUtil.getDigestSize(digest);
        this.digestSize = digestSize;
        this.winternitzParameter = 16;
        int iCeil = (int) Math.ceil(((double) (digestSize * 8)) / ((double) XMSSUtil.log2(16)));
        this.len1 = iCeil;
        int iFloor = ((int) Math.floor(XMSSUtil.log2((16 - 1) * iCeil) / XMSSUtil.log2(16))) + 1;
        this.len2 = iFloor;
        int i = iCeil + iFloor;
        this.len = i;
        WOTSPlusOid wOTSPlusOidLookup = WOTSPlusOid.lookup(digest.getAlgorithmName(), digestSize, 16, i);
        this.oid = wOTSPlusOidLookup;
        if (wOTSPlusOidLookup != null) {
            return;
        }
        z01.a("cannot find OID for digest algorithm: ", digest.getAlgorithmName());
        throw null;
    }

    public int getLen() {
        return this.len;
    }

    public int getLen1() {
        return this.len1;
    }

    public int getLen2() {
        return this.len2;
    }

    public XMSSOid getOid() {
        return this.oid;
    }

    public ASN1ObjectIdentifier getTreeDigest() {
        return this.treeDigest;
    }

    public int getTreeDigestSize() {
        return this.digestSize;
    }

    public int getWinternitzParameter() {
        return this.winternitzParameter;
    }
}
