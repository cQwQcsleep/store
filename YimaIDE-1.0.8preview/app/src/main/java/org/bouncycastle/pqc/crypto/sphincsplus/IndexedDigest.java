package org.bouncycastle.pqc.crypto.sphincsplus;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
class IndexedDigest {
    final byte[] digest;
    final int idx_leaf;
    final long idx_tree;

    public IndexedDigest(long j, int i, byte[] bArr) {
        this.idx_tree = j;
        this.idx_leaf = i;
        this.digest = bArr;
    }
}
