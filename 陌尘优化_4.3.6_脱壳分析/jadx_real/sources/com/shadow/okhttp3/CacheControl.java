package com.shadow.okhttp3;

import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.jvm.internal.DefaultConstructorMarker;
import com.shadow.kotlin.text.StringsKt;
import com.shadow.okhttp3.internal.Util;
import core.pro.android.notify.h;
import java.util.concurrent.TimeUnit;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class CacheControl {
    private String headerValue;
    private final boolean immutable;
    private final boolean isPrivate;
    private final boolean isPublic;
    private final int maxAgeSeconds;
    private final int maxStaleSeconds;
    private final int minFreshSeconds;
    private final boolean mustRevalidate;
    private final boolean noCache;
    private final boolean noStore;
    private final boolean noTransform;
    private final boolean onlyIfCached;
    private final int sMaxAgeSeconds;
    public static final Companion Companion = new Companion(null);
    public static final CacheControl FORCE_NETWORK = new Builder().noCache().build();
    public static final CacheControl FORCE_CACHE = new Builder().onlyIfCached().maxStale(Integer.MAX_VALUE, TimeUnit.SECONDS).build();

    public static final class Builder {
        private boolean immutable;
        private int maxAgeSeconds = -1;
        private int maxStaleSeconds = -1;
        private int minFreshSeconds = -1;
        private boolean noCache;
        private boolean noStore;
        private boolean noTransform;
        private boolean onlyIfCached;

        private final int clampToInt(long j) {
            if (j > 2147483647L) {
                return Integer.MAX_VALUE;
            }
            return (int) j;
        }

        public final CacheControl build() {
            return new CacheControl(this.noCache, this.noStore, this.maxAgeSeconds, -1, false, false, false, this.maxStaleSeconds, this.minFreshSeconds, this.onlyIfCached, this.noTransform, this.immutable, null, null);
        }

        public final Builder immutable() {
            this.immutable = true;
            return this;
        }

        public final Builder maxAge(int i, TimeUnit timeUnit) {
            CloseableKt.checkNotNullParameter(timeUnit, "timeUnit");
            if (i < 0) {
                throw new IllegalArgumentException(h.a(i, "maxAge < 0: ").toString());
            }
            this.maxAgeSeconds = clampToInt(timeUnit.toSeconds(i));
            return this;
        }

        public final Builder maxStale(int i, TimeUnit timeUnit) {
            CloseableKt.checkNotNullParameter(timeUnit, "timeUnit");
            if (i < 0) {
                throw new IllegalArgumentException(h.a(i, "maxStale < 0: ").toString());
            }
            this.maxStaleSeconds = clampToInt(timeUnit.toSeconds(i));
            return this;
        }

        public final Builder minFresh(int i, TimeUnit timeUnit) {
            CloseableKt.checkNotNullParameter(timeUnit, "timeUnit");
            if (i < 0) {
                throw new IllegalArgumentException(h.a(i, "minFresh < 0: ").toString());
            }
            this.minFreshSeconds = clampToInt(timeUnit.toSeconds(i));
            return this;
        }

        public final Builder noCache() {
            this.noCache = true;
            return this;
        }

        public final Builder noStore() {
            this.noStore = true;
            return this;
        }

        public final Builder noTransform() {
            this.noTransform = true;
            return this;
        }

        public final Builder onlyIfCached() {
            this.onlyIfCached = true;
            return this;
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final int indexOfElement(String str, String str2, int i) {
            int length = str.length();
            while (i < length) {
                if (StringsKt.d(str2, str.charAt(i))) {
                    return i;
                }
                i++;
            }
            return str.length();
        }

        public static /* synthetic */ int indexOfElement$default(Companion companion, String str, String str2, int i, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                i = 0;
            }
            return companion.indexOfElement(str, str2, i);
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x004a  */
        /* JADX WARN: Removed duplicated region for block: B:80:0x00dd A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:82:0x00d3 A[SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final CacheControl parse(Headers headers) {
            int i;
            String str;
            int i2;
            int i3;
            String str2;
            int iIndexOfElement;
            String string;
            Headers headers2 = headers;
            CloseableKt.checkNotNullParameter(headers2, "headers");
            int size = headers.size();
            int i4 = 0;
            boolean z = true;
            String str3 = null;
            boolean z2 = false;
            boolean z3 = false;
            int nonNegativeInt = -1;
            int nonNegativeInt2 = -1;
            boolean z4 = false;
            boolean z5 = false;
            boolean z6 = false;
            int nonNegativeInt3 = -1;
            int nonNegativeInt4 = -1;
            boolean z7 = false;
            boolean z8 = false;
            boolean z9 = false;
            while (i4 < size) {
                String strName = headers2.name(i4);
                String strValue = headers2.value(i4);
                if (StringsKt.g(strName, "Cache-Control")) {
                    if (str3 == null) {
                        str3 = strValue;
                    }
                    i2 = 0;
                    while (i2 < strValue.length()) {
                        int iIndexOfElement2 = indexOfElement(strValue, "=,;", i2);
                        String strSubstring = strValue.substring(i2, iIndexOfElement2);
                        CloseableKt.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                        String string2 = StringsKt.w(strSubstring).toString();
                        if (iIndexOfElement2 != strValue.length()) {
                            i3 = size;
                            if (strValue.charAt(iIndexOfElement2) != ',' && strValue.charAt(iIndexOfElement2) != ';') {
                                int iIndexOfNonWhitespace = Util.indexOfNonWhitespace(strValue, iIndexOfElement2 + 1);
                                if (iIndexOfNonWhitespace >= strValue.length() || strValue.charAt(iIndexOfNonWhitespace) != '\"') {
                                    str2 = str3;
                                    iIndexOfElement = indexOfElement(strValue, ",;", iIndexOfNonWhitespace);
                                    String strSubstring2 = strValue.substring(iIndexOfNonWhitespace, iIndexOfElement);
                                    CloseableKt.checkNotNullExpressionValue(strSubstring2, "this as java.lang.String…ing(startIndex, endIndex)");
                                    string = StringsKt.w(strSubstring2).toString();
                                } else {
                                    int i5 = iIndexOfNonWhitespace + 1;
                                    str2 = str3;
                                    int iH = StringsKt.h(strValue, '\"', i5, false, 4);
                                    string = strValue.substring(i5, iH);
                                    CloseableKt.checkNotNullExpressionValue(string, "this as java.lang.String…ing(startIndex, endIndex)");
                                    iIndexOfElement = iH + 1;
                                }
                            }
                            if (!"no-cache".equalsIgnoreCase(string2)) {
                                i2 = iIndexOfElement;
                                size = i3;
                                str3 = str2;
                                z2 = true;
                            } else if ("no-store".equalsIgnoreCase(string2)) {
                                i2 = iIndexOfElement;
                                size = i3;
                                str3 = str2;
                                z3 = true;
                            } else {
                                if ("max-age".equalsIgnoreCase(string2)) {
                                    nonNegativeInt = Util.toNonNegativeInt(string, -1);
                                } else if ("s-maxage".equalsIgnoreCase(string2)) {
                                    nonNegativeInt2 = Util.toNonNegativeInt(string, -1);
                                } else if ("private".equalsIgnoreCase(string2)) {
                                    i2 = iIndexOfElement;
                                    size = i3;
                                    str3 = str2;
                                    z4 = true;
                                } else if ("public".equalsIgnoreCase(string2)) {
                                    i2 = iIndexOfElement;
                                    size = i3;
                                    str3 = str2;
                                    z5 = true;
                                } else if ("must-revalidate".equalsIgnoreCase(string2)) {
                                    i2 = iIndexOfElement;
                                    size = i3;
                                    str3 = str2;
                                    z6 = true;
                                } else if ("max-stale".equalsIgnoreCase(string2)) {
                                    nonNegativeInt3 = Util.toNonNegativeInt(string, Integer.MAX_VALUE);
                                } else if ("min-fresh".equalsIgnoreCase(string2)) {
                                    nonNegativeInt4 = Util.toNonNegativeInt(string, -1);
                                } else if ("only-if-cached".equalsIgnoreCase(string2)) {
                                    i2 = iIndexOfElement;
                                    size = i3;
                                    str3 = str2;
                                    z7 = true;
                                } else if ("no-transform".equalsIgnoreCase(string2)) {
                                    i2 = iIndexOfElement;
                                    size = i3;
                                    str3 = str2;
                                    z8 = true;
                                } else if ("immutable".equalsIgnoreCase(string2)) {
                                    i2 = iIndexOfElement;
                                    size = i3;
                                    str3 = str2;
                                    z9 = true;
                                }
                                i2 = iIndexOfElement;
                                size = i3;
                                str3 = str2;
                            }
                        } else {
                            i3 = size;
                        }
                        str2 = str3;
                        iIndexOfElement = iIndexOfElement2 + 1;
                        string = null;
                        if (!"no-cache".equalsIgnoreCase(string2)) {
                        }
                    }
                    i = size;
                    str = str3;
                    i4++;
                    headers2 = headers;
                    str3 = str;
                    size = i;
                } else if (!StringsKt.g(strName, "Pragma")) {
                    i = size;
                    str = str3;
                    i4++;
                    headers2 = headers;
                    str3 = str;
                    size = i;
                }
                z = false;
                i2 = 0;
                while (i2 < strValue.length()) {
                }
                i = size;
                str = str3;
                i4++;
                headers2 = headers;
                str3 = str;
                size = i;
            }
            return new CacheControl(z2, z3, nonNegativeInt, nonNegativeInt2, z4, z5, z6, nonNegativeInt3, nonNegativeInt4, z7, z8, z9, !z ? null : str3, null);
        }

        private Companion() {
        }
    }

    public /* synthetic */ CacheControl(boolean z, boolean z2, int i, int i2, boolean z3, boolean z4, boolean z5, int i3, int i4, boolean z6, boolean z7, boolean z8, String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, z2, i, i2, z3, z4, z5, i3, i4, z6, z7, z8, str);
    }

    public static final CacheControl parse(Headers headers) {
        return Companion.parse(headers);
    }

    /* renamed from: -deprecated_immutable, reason: not valid java name */
    public final boolean m26deprecated_immutable() {
        return this.immutable;
    }

    /* renamed from: -deprecated_maxAgeSeconds, reason: not valid java name */
    public final int m27deprecated_maxAgeSeconds() {
        return this.maxAgeSeconds;
    }

    /* renamed from: -deprecated_maxStaleSeconds, reason: not valid java name */
    public final int m28deprecated_maxStaleSeconds() {
        return this.maxStaleSeconds;
    }

    /* renamed from: -deprecated_minFreshSeconds, reason: not valid java name */
    public final int m29deprecated_minFreshSeconds() {
        return this.minFreshSeconds;
    }

    /* renamed from: -deprecated_mustRevalidate, reason: not valid java name */
    public final boolean m30deprecated_mustRevalidate() {
        return this.mustRevalidate;
    }

    /* renamed from: -deprecated_noCache, reason: not valid java name */
    public final boolean m31deprecated_noCache() {
        return this.noCache;
    }

    /* renamed from: -deprecated_noStore, reason: not valid java name */
    public final boolean m32deprecated_noStore() {
        return this.noStore;
    }

    /* renamed from: -deprecated_noTransform, reason: not valid java name */
    public final boolean m33deprecated_noTransform() {
        return this.noTransform;
    }

    /* renamed from: -deprecated_onlyIfCached, reason: not valid java name */
    public final boolean m34deprecated_onlyIfCached() {
        return this.onlyIfCached;
    }

    /* renamed from: -deprecated_sMaxAgeSeconds, reason: not valid java name */
    public final int m35deprecated_sMaxAgeSeconds() {
        return this.sMaxAgeSeconds;
    }

    public final boolean immutable() {
        return this.immutable;
    }

    public final boolean isPrivate() {
        return this.isPrivate;
    }

    public final boolean isPublic() {
        return this.isPublic;
    }

    public final int maxAgeSeconds() {
        return this.maxAgeSeconds;
    }

    public final int maxStaleSeconds() {
        return this.maxStaleSeconds;
    }

    public final int minFreshSeconds() {
        return this.minFreshSeconds;
    }

    public final boolean mustRevalidate() {
        return this.mustRevalidate;
    }

    public final boolean noCache() {
        return this.noCache;
    }

    public final boolean noStore() {
        return this.noStore;
    }

    public final boolean noTransform() {
        return this.noTransform;
    }

    public final boolean onlyIfCached() {
        return this.onlyIfCached;
    }

    public final int sMaxAgeSeconds() {
        return this.sMaxAgeSeconds;
    }

    public String toString() {
        String str = this.headerValue;
        if (str != null) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        if (this.noCache) {
            sb.append("no-cache, ");
        }
        if (this.noStore) {
            sb.append("no-store, ");
        }
        if (this.maxAgeSeconds != -1) {
            sb.append("max-age=");
            sb.append(this.maxAgeSeconds);
            sb.append(", ");
        }
        if (this.sMaxAgeSeconds != -1) {
            sb.append("s-maxage=");
            sb.append(this.sMaxAgeSeconds);
            sb.append(", ");
        }
        if (this.isPrivate) {
            sb.append("private, ");
        }
        if (this.isPublic) {
            sb.append("public, ");
        }
        if (this.mustRevalidate) {
            sb.append("must-revalidate, ");
        }
        if (this.maxStaleSeconds != -1) {
            sb.append("max-stale=");
            sb.append(this.maxStaleSeconds);
            sb.append(", ");
        }
        if (this.minFreshSeconds != -1) {
            sb.append("min-fresh=");
            sb.append(this.minFreshSeconds);
            sb.append(", ");
        }
        if (this.onlyIfCached) {
            sb.append("only-if-cached, ");
        }
        if (this.noTransform) {
            sb.append("no-transform, ");
        }
        if (this.immutable) {
            sb.append("immutable, ");
        }
        if (sb.length() == 0) {
            return "";
        }
        sb.delete(sb.length() - 2, sb.length());
        String string = sb.toString();
        CloseableKt.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        this.headerValue = string;
        return string;
    }

    private CacheControl(boolean z, boolean z2, int i, int i2, boolean z3, boolean z4, boolean z5, int i3, int i4, boolean z6, boolean z7, boolean z8, String str) {
        this.noCache = z;
        this.noStore = z2;
        this.maxAgeSeconds = i;
        this.sMaxAgeSeconds = i2;
        this.isPrivate = z3;
        this.isPublic = z4;
        this.mustRevalidate = z5;
        this.maxStaleSeconds = i3;
        this.minFreshSeconds = i4;
        this.onlyIfCached = z6;
        this.noTransform = z7;
        this.immutable = z8;
        this.headerValue = str;
    }
}
