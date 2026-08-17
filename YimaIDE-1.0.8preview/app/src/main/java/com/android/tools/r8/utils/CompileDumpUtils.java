package com.android.tools.r8.utils;

import com.android.tools.r8.internal.C1798j4;
import com.android.tools.r8.internal.C2312p4;
import com.android.tools.r8.profile.art.ArtProfileConsumer;
import com.android.tools.r8.profile.art.ArtProfileProvider;
import com.android.tools.r8.startup.StartupProfileProvider;
import java.nio.file.Path;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
class CompileDumpUtils {
    public static ArtProfileProvider createArtProfileProviderFromDumpFile(Path path) {
        return new C2312p4(path);
    }

    public static ArtProfileConsumer createResidualArtProfileConsumerFromDumpFile(Path path) {
        return new C1798j4(path);
    }

    public static StartupProfileProvider createStartupProfileProviderFromDumpFile(Path path) {
        return new s(path);
    }
}
