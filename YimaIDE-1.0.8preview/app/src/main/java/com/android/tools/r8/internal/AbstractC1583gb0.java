package com.android.tools.r8.internal;

import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/* JADX INFO: renamed from: com.android.tools.r8.internal.gb0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1583gb0 {
    public static void a(DataOutputStream dataOutputStream, String str) throws IOException {
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        dataOutputStream.writeInt(bytes.length);
        dataOutputStream.write(bytes);
    }
}
