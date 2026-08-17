package com.android.tools.r8.internal;

import com.android.tools.r8.naming.MappingComposeException;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.vk0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2883vk0 extends com.android.tools.r8.naming.mappinginformation.c {
    public final String a;
    public final String b;

    public C2883vk0(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public static void a(String str, C1898kD c1898kD, Consumer consumer) {
        consumer.accept(new C2883vk0(str, c1898kD.toString()));
    }

    @Override // com.android.tools.r8.naming.mappinginformation.e
    public final com.android.tools.r8.naming.mappinginformation.e b(com.android.tools.r8.naming.mappinginformation.e eVar) throws MappingComposeException {
        throw new MappingComposeException("Unable to compose unknown json mapping information");
    }

    @Override // com.android.tools.r8.naming.mappinginformation.e
    public final C2883vk0 k() {
        return this;
    }

    @Override // com.android.tools.r8.naming.mappinginformation.e
    public final String r() {
        return this.b;
    }

    @Override // com.android.tools.r8.naming.mappinginformation.e
    public final boolean a(com.android.tools.r8.naming.mappinginformation.e eVar) {
        return true;
    }
}
