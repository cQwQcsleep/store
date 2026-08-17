package org.bouncycastle.pqc.crypto.sphincsplus;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
class NodeEntry {
    final int nodeHeight;
    final byte[] nodeValue;

    public NodeEntry(byte[] bArr, int i) {
        this.nodeValue = bArr;
        this.nodeHeight = i;
    }
}
