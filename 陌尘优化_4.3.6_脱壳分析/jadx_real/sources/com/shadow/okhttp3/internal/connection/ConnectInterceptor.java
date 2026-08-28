package com.shadow.okhttp3.internal.connection;

import com.shadow.kotlin.io.CloseableKt;
import com.shadow.okhttp3.Interceptor;
import com.shadow.okhttp3.Response;
import com.shadow.okhttp3.internal.http.RealInterceptorChain;
import java.io.IOException;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class ConnectInterceptor implements Interceptor {
    public static final ConnectInterceptor INSTANCE = new ConnectInterceptor();

    private ConnectInterceptor() {
    }

    @Override // com.shadow.okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        CloseableKt.checkNotNullParameter(chain, "chain");
        RealInterceptorChain realInterceptorChain = (RealInterceptorChain) chain;
        return RealInterceptorChain.copy$okhttp$default(realInterceptorChain, 0, realInterceptorChain.getCall$okhttp().initExchange$okhttp(realInterceptorChain), null, 0, 0, 0, 61, null).proceed(realInterceptorChain.getRequest$okhttp());
    }
}
