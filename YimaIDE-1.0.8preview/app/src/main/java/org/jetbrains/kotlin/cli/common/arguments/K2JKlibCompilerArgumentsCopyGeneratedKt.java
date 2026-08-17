package org.jetbrains.kotlin.cli.common.arguments;

import java.util.Arrays;
import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001¨\u0006\u0004"}, d2 = {"copyK2JKlibCompilerArguments", "Lorg/jetbrains/kotlin/cli/common/arguments/K2JKlibCompilerArguments;", "from", "to", "org.jetbrains.kotlin:cli-base"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class K2JKlibCompilerArgumentsCopyGeneratedKt {
    public static final K2JKlibCompilerArguments copyK2JKlibCompilerArguments(K2JKlibCompilerArguments k2JKlibCompilerArguments, K2JKlibCompilerArguments k2JKlibCompilerArguments2) {
        k2JKlibCompilerArguments.getClass();
        k2JKlibCompilerArguments2.getClass();
        CommonCompilerArgumentsCopyGeneratedKt.copyCommonCompilerArguments(k2JKlibCompilerArguments, k2JKlibCompilerArguments2);
        k2JKlibCompilerArguments2.setClasspath(k2JKlibCompilerArguments.getClasspath());
        k2JKlibCompilerArguments2.setCompileIr(k2JKlibCompilerArguments.getCompileIr());
        k2JKlibCompilerArguments2.setDestination(k2JKlibCompilerArguments.getDestination());
        k2JKlibCompilerArguments2.setEnhanceTypeParameterTypesToDefNotNull(k2JKlibCompilerArguments.getEnhanceTypeParameterTypesToDefNotNull());
        k2JKlibCompilerArguments2.setExpectBuiltinsAsPartOfStdlib(k2JKlibCompilerArguments.getExpectBuiltinsAsPartOfStdlib());
        k2JKlibCompilerArguments2.setFriendModules(k2JKlibCompilerArguments.getFriendModules());
        String[] friendPaths = k2JKlibCompilerArguments.getFriendPaths();
        k2JKlibCompilerArguments2.setFriendPaths(friendPaths != null ? (String[]) Arrays.copyOf(friendPaths, friendPaths.length) : null);
        k2JKlibCompilerArguments2.setInheritMultifileParts(k2JKlibCompilerArguments.getInheritMultifileParts());
        k2JKlibCompilerArguments2.setJspecifyAnnotations(k2JKlibCompilerArguments.getJspecifyAnnotations());
        String[] jsr305 = k2JKlibCompilerArguments.getJsr305();
        k2JKlibCompilerArguments2.setJsr305(jsr305 != null ? (String[]) Arrays.copyOf(jsr305, jsr305.length) : null);
        k2JKlibCompilerArguments2.setJvmDefault(k2JKlibCompilerArguments.getJvmDefault());
        k2JKlibCompilerArguments2.setKlibLibraries(k2JKlibCompilerArguments.getKlibLibraries());
        k2JKlibCompilerArguments2.setModuleName(k2JKlibCompilerArguments.getModuleName());
        k2JKlibCompilerArguments2.setNoJdk(k2JKlibCompilerArguments.getNoJdk());
        k2JKlibCompilerArguments2.setNoReflect(k2JKlibCompilerArguments.getNoReflect());
        k2JKlibCompilerArguments2.setNoStdlib(k2JKlibCompilerArguments.getNoStdlib());
        String[] nullabilityAnnotations = k2JKlibCompilerArguments.getNullabilityAnnotations();
        k2JKlibCompilerArguments2.setNullabilityAnnotations(nullabilityAnnotations != null ? (String[]) Arrays.copyOf(nullabilityAnnotations, nullabilityAnnotations.length) : null);
        k2JKlibCompilerArguments2.setOutputBuiltinsMetadata(k2JKlibCompilerArguments.getOutputBuiltinsMetadata());
        k2JKlibCompilerArguments2.setSamConversions(k2JKlibCompilerArguments.getSamConversions());
        k2JKlibCompilerArguments2.setSupportCompatqualCheckerFrameworkAnnotations(k2JKlibCompilerArguments.getSupportCompatqualCheckerFrameworkAnnotations());
        k2JKlibCompilerArguments2.setTypeEnhancementImprovementsInStrictMode(k2JKlibCompilerArguments.getTypeEnhancementImprovementsInStrictMode());
        k2JKlibCompilerArguments2.setValueClasses(k2JKlibCompilerArguments.getValueClasses());
        return k2JKlibCompilerArguments2;
    }
}
