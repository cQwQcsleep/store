package androidx.compose.compiler.plugins.kotlin.analysis;

import kotlin.Metadata;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0003X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0003X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0003X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"STABILITY_WILDCARD_SINGLE", HttpUrl.FRAGMENT_ENCODE_SET, "STABILITY_WILDCARD_MULTI", HttpUrl.FRAGMENT_ENCODE_SET, "STABILITY_GENERIC_OPEN", "STABILITY_GENERIC_CLOSE", "STABILITY_GENERIC_INCLUDE", "STABILITY_GENERIC_EXCLUDE", "STABILITY_GENERIC_SEPARATOR", "STABILITY_PACKAGE_SEPARATOR", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class StabilityExternalClassNameMatchingKt {
    public static final char STABILITY_GENERIC_CLOSE = '>';
    public static final String STABILITY_GENERIC_EXCLUDE = "_";
    public static final String STABILITY_GENERIC_INCLUDE = "*";
    public static final char STABILITY_GENERIC_OPEN = '<';
    public static final String STABILITY_GENERIC_SEPARATOR = ",";
    public static final char STABILITY_PACKAGE_SEPARATOR = '.';
    public static final String STABILITY_WILDCARD_MULTI = "**";
    public static final char STABILITY_WILDCARD_SINGLE = '*';
}
