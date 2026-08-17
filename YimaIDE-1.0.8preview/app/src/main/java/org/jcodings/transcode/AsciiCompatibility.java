package org.jcodings.transcode;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public enum AsciiCompatibility {
    CONVERTER,
    DECODER,
    ENCODER;

    public boolean isConverter() {
        return this == CONVERTER;
    }

    public boolean isDecoder() {
        return this == DECODER;
    }

    public boolean isEncoder() {
        return this == ENCODER;
    }
}
