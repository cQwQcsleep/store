package kotlin.io.encoding;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import java.io.InputStream;
import java.io.OutputStream;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a(\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\u0080\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007\u001a(\u0010\b\u001a\u00020\t*\u00020\t2\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\u0080\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007¨\u0006\n"}, d2 = {"decodingWith", "Ljava/io/InputStream;", "base64", "Lkotlin/io/encoding/Base64;", "Lkotlin/SinceKotlin;", "version", "1.8", "Lkotlin/io/encoding/ExperimentalEncodingApi;", "encodingWith", "Ljava/io/OutputStream;", "kotlin-stdlib"}, k = 5, mv = {2, 4, 0}, xi = EditorColorScheme.TEXT_INLAY_HINT_BACKGROUND, xs = "kotlin/io/encoding/StreamEncodingKt")
class StreamEncodingKt__Base64IOStreamKt {
    public static final InputStream decodingWith(InputStream inputStream, Base64 base64) {
        inputStream.getClass();
        base64.getClass();
        return new DecodeInputStream(inputStream, base64);
    }

    public static final OutputStream encodingWith(OutputStream outputStream, Base64 base64) {
        outputStream.getClass();
        base64.getClass();
        return new EncodeOutputStream(outputStream, base64);
    }
}
