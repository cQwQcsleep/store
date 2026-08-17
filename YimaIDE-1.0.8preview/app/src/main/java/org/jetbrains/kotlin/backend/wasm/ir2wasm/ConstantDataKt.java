package org.jetbrains.kotlin.backend.wasm.ir2wasm;

import kotlin.Metadata;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0001H\u0002\u001a\"\u0010\t\u001a\u00020\n*\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0001\u001a\u001a\u0010\u0010\u001a\u00020\n*\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0001\u001a\"\u0010\u0010\u001a\u00020\n*\u00020\u00112\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0013\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"CHAR_SIZE_BYTES", "", "BYTE_SIZE_BYTES", "SHORT_SIZE_BYTES", "INT_SIZE_BYTES", "LONG_SIZE_BYTES", "addressToString", "", "address", "toLittleEndianBytesTo", "", "", "to", "", "offset", "size", "toLittleEndianBytes", "", "fitsLatin1", "", "org.jetbrains.kotlin:backend.wasm"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class ConstantDataKt {
    public static final int BYTE_SIZE_BYTES = 1;
    public static final int CHAR_SIZE_BYTES = 2;
    public static final int INT_SIZE_BYTES = 4;
    public static final int LONG_SIZE_BYTES = 8;
    public static final int SHORT_SIZE_BYTES = 2;

    /* JADX INFO: Access modifiers changed from: private */
    public static final String addressToString(int i) {
        return StringsKt.padEnd(String.valueOf(i), 6, ' ');
    }

    public static final void toLittleEndianBytes(int i, byte[] bArr, int i2) {
        bArr.getClass();
        bArr[i2] = (byte) i;
        bArr[i2 + 1] = (byte) (i >>> 8);
        bArr[i2 + 2] = (byte) (i >>> 16);
        bArr[i2 + 3] = (byte) (i >>> 24);
    }

    public static final void toLittleEndianBytesTo(long j, byte[] bArr, int i, int i2) {
        bArr.getClass();
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i + i3] = (byte) (j >>> (i3 * 8));
        }
    }

    public static final void toLittleEndianBytes(char c, byte[] bArr, int i, boolean z) {
        bArr.getClass();
        bArr[i] = (byte) c;
        if (z) {
            return;
        }
        bArr[i + 1] = (byte) (c >>> '\b');
    }
}
