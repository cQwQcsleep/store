package com.shadow.okhttp3;

import com.shadow.kotlin.collections.EmptyList;
import com.shadow.kotlin.io.CloseableKt;
import java.util.List;

/* loaded from: /workspace/unpacked/classes2.dex */
public interface CookieJar {
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final CookieJar NO_COOKIES = new Companion.NoCookies();

    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        public static final class NoCookies implements CookieJar {
            @Override // com.shadow.okhttp3.CookieJar
            public List<Cookie> loadForRequest(HttpUrl httpUrl) {
                CloseableKt.checkNotNullParameter(httpUrl, "url");
                return EmptyList.INSTANCE;
            }

            @Override // com.shadow.okhttp3.CookieJar
            public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
                CloseableKt.checkNotNullParameter(httpUrl, "url");
                CloseableKt.checkNotNullParameter(list, "cookies");
            }
        }

        private Companion() {
        }
    }

    List<Cookie> loadForRequest(HttpUrl httpUrl);

    void saveFromResponse(HttpUrl httpUrl, List<Cookie> list);
}
