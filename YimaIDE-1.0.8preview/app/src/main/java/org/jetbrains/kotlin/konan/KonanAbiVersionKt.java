package org.jetbrains.kotlin.konan;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"parseKonanAbiVersion", "Lorg/jetbrains/kotlin/konan/KonanAbiVersion;", "", "kotlin-native-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class KonanAbiVersionKt {
    public static final KonanAbiVersion parseKonanAbiVersion(String str) {
        str.getClass();
        return new KonanAbiVersion(Integer.parseInt(str));
    }
}
