package kotlin.text;

import kotlin.Metadata;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\bÂ\u0002\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0084\b\u0092\u0002\u0002\b\u0006¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lkotlin/text/SystemProperties;", HttpUrl.FRAGMENT_ENCODE_SET, "<init>", "()V", "LINE_SEPARATOR", HttpUrl.FRAGMENT_ENCODE_SET, "Lkotlin/jvm/JvmField;", "kotlin-stdlib"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class SystemProperties {
    public static final SystemProperties INSTANCE = new SystemProperties();
    public static final String LINE_SEPARATOR;

    static {
        String property = System.getProperty("line.separator");
        property.getClass();
        LINE_SEPARATOR = property;
    }

    private SystemProperties() {
    }
}
