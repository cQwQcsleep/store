package kotlin.io.encoding;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;
import kotlin.text.Charsets;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u001a8\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0081\u0088\u0004b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\nb\u0002\b\u000b\u001a8\u0010\f\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0081\u0088\u0004b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\nb\u0002\b\u000b\u001aH\u0010\u000e\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0081\u0088\u0004b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\nb\u0002\b\u000b\u001a8\u0010\u0011\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0081\u0088\u0004b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\nb\u0002\b\u000b¨\u0006\u0012"}, d2 = {"platformCharsToBytes", "", "Lkotlin/io/encoding/Base64;", "source", "", "startIndex", "", "endIndex", "Lkotlin/SinceKotlin;", "version", "1.8", "Lkotlin/internal/InlineOnly;", "platformEncodeToString", "", "platformEncodeIntoByteArray", "destination", "destinationOffset", "platformEncodeToByteArray", "kotlin-stdlib"}, k = 2, mv = {2, 4, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class Base64JVMKt {
    private static final byte[] platformCharsToBytes(Base64 base64, CharSequence charSequence, int i, int i2) {
        base64.getClass();
        charSequence.getClass();
        if (!(charSequence instanceof String)) {
            return base64.charsToBytesImpl$kotlin_stdlib(charSequence, i, i2);
        }
        String str = (String) charSequence;
        base64.checkSourceBounds$kotlin_stdlib(str.length(), i, i2);
        byte[] bytes = str.substring(i, i2).getBytes(Charsets.ISO_8859_1);
        bytes.getClass();
        return bytes;
    }

    private static final int platformEncodeIntoByteArray(Base64 base64, byte[] bArr, byte[] bArr2, int i, int i2, int i3) {
        base64.getClass();
        bArr.getClass();
        bArr2.getClass();
        return base64.encodeIntoByteArrayImpl$kotlin_stdlib(bArr, bArr2, i, i2, i3);
    }

    private static final byte[] platformEncodeToByteArray(Base64 base64, byte[] bArr, int i, int i2) {
        base64.getClass();
        bArr.getClass();
        return base64.encodeToByteArrayImpl$kotlin_stdlib(bArr, i, i2);
    }

    private static final String platformEncodeToString(Base64 base64, byte[] bArr, int i, int i2) {
        base64.getClass();
        bArr.getClass();
        return new String(base64.encodeToByteArrayImpl$kotlin_stdlib(bArr, i, i2), Charsets.ISO_8859_1);
    }
}
