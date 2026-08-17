package org.jetbrains.kotlin.cli.common.arguments;

import java.util.Arrays;
import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001¨\u0006\u0004"}, d2 = {"copyK2MetadataCompilerArguments", "Lorg/jetbrains/kotlin/cli/common/arguments/K2MetadataCompilerArguments;", "from", "to", "org.jetbrains.kotlin:cli-base"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class K2MetadataCompilerArgumentsCopyGeneratedKt {
    public static final K2MetadataCompilerArguments copyK2MetadataCompilerArguments(K2MetadataCompilerArguments k2MetadataCompilerArguments, K2MetadataCompilerArguments k2MetadataCompilerArguments2) {
        k2MetadataCompilerArguments.getClass();
        k2MetadataCompilerArguments2.getClass();
        CommonCompilerArgumentsCopyGeneratedKt.copyCommonCompilerArguments(k2MetadataCompilerArguments, k2MetadataCompilerArguments2);
        k2MetadataCompilerArguments2.setClasspath(k2MetadataCompilerArguments.getClasspath());
        k2MetadataCompilerArguments2.setDestination(k2MetadataCompilerArguments.getDestination());
        String[] friendPaths = k2MetadataCompilerArguments.getFriendPaths();
        k2MetadataCompilerArguments2.setFriendPaths((String[]) Arrays.copyOf(friendPaths, friendPaths.length));
        k2MetadataCompilerArguments2.setKlibZipFileAccessorCacheLimit(k2MetadataCompilerArguments.getKlibZipFileAccessorCacheLimit());
        k2MetadataCompilerArguments2.setLegacyMetadataJar(k2MetadataCompilerArguments.getLegacyMetadataJar());
        k2MetadataCompilerArguments2.setModuleName(k2MetadataCompilerArguments.getModuleName());
        String[] refinesPaths = k2MetadataCompilerArguments.getRefinesPaths();
        k2MetadataCompilerArguments2.setRefinesPaths((String[]) Arrays.copyOf(refinesPaths, refinesPaths.length));
        String[] targetPlatform = k2MetadataCompilerArguments.getTargetPlatform();
        k2MetadataCompilerArguments2.setTargetPlatform((String[]) Arrays.copyOf(targetPlatform, targetPlatform.length));
        return k2MetadataCompilerArguments2;
    }
}
