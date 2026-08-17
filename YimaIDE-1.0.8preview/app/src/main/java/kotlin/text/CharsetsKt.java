package kotlin.text;

import java.nio.charset.Charset;
import kotlin.Metadata;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\u0088\u0004b\u0002\b\u0004¨\u0006\u0005"}, d2 = {"charset", "Ljava/nio/charset/Charset;", "charsetName", HttpUrl.FRAGMENT_ENCODE_SET, "Lkotlin/internal/InlineOnly;", "kotlin-stdlib"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class CharsetsKt {
    private static final Charset charset(String str) {
        str.getClass();
        Charset charsetForName = Charset.forName(str);
        charsetForName.getClass();
        return charsetForName;
    }
}
