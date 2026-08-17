package org.jetbrains.kotlin.konan.library;

import kotlin.Metadata;
import org.jetbrains.kotlin.konan.target.KonanTarget;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/konan/library/KlibNativeDistributionLibraryProviderSpec;", "", "withPlatformLibs", "", "target", "Lorg/jetbrains/kotlin/konan/target/KonanTarget;", "withStdlib", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface KlibNativeDistributionLibraryProviderSpec {
    void withPlatformLibs(KonanTarget target);

    void withStdlib();
}
