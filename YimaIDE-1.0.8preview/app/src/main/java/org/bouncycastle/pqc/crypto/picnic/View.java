package org.bouncycastle.pqc.crypto.picnic;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
class View {
    final byte[] communicatedBits;
    final int[] inputShare;
    final int[] outputShare;

    public View(PicnicEngine picnicEngine) {
        int i = picnicEngine.stateSizeWords;
        this.inputShare = new int[i];
        this.communicatedBits = new byte[picnicEngine.andSizeBytes];
        this.outputShare = new int[i];
    }
}
