package com.android.tools.r8.jetbrains.kotlinx.metadata.jvm;

import com.android.tools.r8.internal.C40;
import com.android.tools.r8.internal.KB;
import com.android.tools.r8.internal.RH;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class s {
    public static final void a(j jVar) {
        int[] iArr = r.b;
        int i = jVar.b;
        if (i < 1 || (i <= 1 && jVar.c < 4)) {
            wec.a("This version of kotlinx-metadata-jvm doesn't support writing Kotlin metadata of version earlier than 1.4. Please change the version from ", jVar, " to at least [1, 4].");
            return;
        }
        j jVar2 = j.f;
        if (jVar.compareTo(jVar2) <= 0) {
            return;
        }
        rza.a("kotlinx-metadata-jvm cannot write metadata for future compiler versions. Requested to write version ", jVar, ", but highest known version is ", jVar2);
    }

    public static h a(RH rh, int[] iArr) {
        KB.c(iArr, "metadataVersion");
        return new r.a(rh, new j(iArr), 0).b();
    }

    public static void a(String str, boolean z) {
        if (z) {
            return;
        }
        w01.a(C40.a("This ", str, " cannot be written because it represents metadata read in lenient mode"));
    }
}
