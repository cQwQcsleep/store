package org.bouncycastle.tsp.ers;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class IndexedHash {
    final byte[] digest;
    final int order;

    public IndexedHash(int i, byte[] bArr) {
        this.order = i;
        this.digest = bArr;
    }
}
