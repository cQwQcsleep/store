package com.shadow.okhttp3;

import com.shadow.kotlin.io.CloseableKt;
import com.shadow.okhttp3.Interceptor;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.functions.Function1;

/* loaded from: /workspace/unpacked/classes2.dex */
public interface Interceptor {
    public static final Companion Companion = Companion.$$INSTANCE;

    public interface Chain {
        Call call();

        int connectTimeoutMillis();

        Connection connection();

        Response proceed(Request request) throws IOException;

        int readTimeoutMillis();

        Request request();

        Chain withConnectTimeout(int i, TimeUnit timeUnit);

        Chain withReadTimeout(int i, TimeUnit timeUnit);

        Chain withWriteTimeout(int i, TimeUnit timeUnit);

        int writeTimeoutMillis();
    }

    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final Interceptor invoke(final Function1<? super Chain, Response> function1) {
            CloseableKt.checkNotNullParameter(function1, "block");
            return new Interceptor() { // from class: com.shadow.okhttp3.Interceptor$Companion$invoke$1
                @Override // com.shadow.okhttp3.Interceptor
                public final Response intercept(Interceptor.Chain chain) {
                    CloseableKt.checkNotNullParameter(chain, "it");
                    return (Response) function1.invoke(chain);
                }
            };
        }
    }

    Response intercept(Chain chain) throws IOException;
}
