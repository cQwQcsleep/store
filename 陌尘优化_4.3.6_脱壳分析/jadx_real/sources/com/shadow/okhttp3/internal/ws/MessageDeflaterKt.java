package com.shadow.okhttp3.internal.ws;

import com.shadow.okio.ByteString;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class MessageDeflaterKt {
    private static final ByteString EMPTY_DEFLATE_BLOCK = ByteString.Companion.decodeHex("000000ffff");
    private static final int LAST_OCTETS_COUNT_TO_REMOVE_AFTER_DEFLATION = 4;
}
