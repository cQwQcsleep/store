package com.shadow.okhttp3;

import java.io.IOException;

/* loaded from: /workspace/unpacked/classes2.dex */
public interface Callback {
    void onFailure(Call call, IOException iOException);

    void onResponse(Call call, Response response) throws IOException;
}
