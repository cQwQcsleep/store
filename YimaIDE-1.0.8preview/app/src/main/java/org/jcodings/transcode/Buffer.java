package org.jcodings.transcode;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
class Buffer {
    int bufEnd;
    int bufStart;
    byte[] bytes;
    int dataEnd;
    int dataStart;

    public void allocate(int i) {
        this.bytes = new byte[i];
        this.dataEnd = 0;
        this.dataStart = 0;
        this.bufStart = 0;
        this.bufEnd = i;
    }
}
