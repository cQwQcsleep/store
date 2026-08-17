package org.jetbrains.kotlin.backend.common;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.KlibConfigurationKeysKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.library.KotlinAbiVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"klibAbiVersionForManifest", "Lorg/jetbrains/kotlin/library/KotlinAbiVersion;", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "org.jetbrains.kotlin:ir.serialization.common"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KlibAbiVersionUtilsKt {
    public static final KotlinAbiVersion klibAbiVersionForManifest(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        KotlinAbiVersion customKlibAbiVersion = KlibConfigurationKeysKt.getCustomKlibAbiVersion(compilerConfiguration);
        return customKlibAbiVersion == null ? KlibConfigurationKeysKt.getKlibAbiCompatibilityLevel(compilerConfiguration).toAbiVersionForManifest() : customKlibAbiVersion;
    }
}
