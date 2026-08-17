package org.jetbrains.kotlin.cli.common.arguments;

import java.util.Arrays;
import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001¨\u0006\u0004"}, d2 = {"copyCommonKlibBasedCompilerArguments", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonKlibBasedCompilerArguments;", "from", "to", "org.jetbrains.kotlin:cli-base"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CommonKlibBasedCompilerArgumentsCopyGeneratedKt {
    public static final CommonKlibBasedCompilerArguments copyCommonKlibBasedCompilerArguments(CommonKlibBasedCompilerArguments commonKlibBasedCompilerArguments, CommonKlibBasedCompilerArguments commonKlibBasedCompilerArguments2) {
        commonKlibBasedCompilerArguments.getClass();
        commonKlibBasedCompilerArguments2.getClass();
        CommonCompilerArgumentsCopyGeneratedKt.copyCommonCompilerArguments(commonKlibBasedCompilerArguments, commonKlibBasedCompilerArguments2);
        commonKlibBasedCompilerArguments2.setCustomKlibAbiVersion(commonKlibBasedCompilerArguments.getCustomKlibAbiVersion());
        commonKlibBasedCompilerArguments2.setDuplicatedUniqueNameStrategy(commonKlibBasedCompilerArguments.getDuplicatedUniqueNameStrategy());
        commonKlibBasedCompilerArguments2.setEnableSignatureClashChecks(commonKlibBasedCompilerArguments.getEnableSignatureClashChecks());
        commonKlibBasedCompilerArguments2.setIrInlinerBeforeKlibSerialization(commonKlibBasedCompilerArguments.getIrInlinerBeforeKlibSerialization());
        commonKlibBasedCompilerArguments2.setKlibZipFileAccessorCacheLimit(commonKlibBasedCompilerArguments.getKlibZipFileAccessorCacheLimit());
        commonKlibBasedCompilerArguments2.setNormalizeAbsolutePath(commonKlibBasedCompilerArguments.getNormalizeAbsolutePath());
        commonKlibBasedCompilerArguments2.setPartialLinkageLogLevel(commonKlibBasedCompilerArguments.getPartialLinkageLogLevel());
        commonKlibBasedCompilerArguments2.setPartialLinkageMode(commonKlibBasedCompilerArguments.getPartialLinkageMode());
        String[] relativePathBases = commonKlibBasedCompilerArguments.getRelativePathBases();
        commonKlibBasedCompilerArguments2.setRelativePathBases((String[]) Arrays.copyOf(relativePathBases, relativePathBases.length));
        commonKlibBasedCompilerArguments2.setSkipLibrarySpecialCompatibilityChecks(commonKlibBasedCompilerArguments.getSkipLibrarySpecialCompatibilityChecks());
        return commonKlibBasedCompilerArguments2;
    }
}
