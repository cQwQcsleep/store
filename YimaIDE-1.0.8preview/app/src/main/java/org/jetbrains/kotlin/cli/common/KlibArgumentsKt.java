package org.jetbrains.kotlin.cli.common;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.util.Disposer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.reflect.KProperty1;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.CliDiagnosticReportingKt;
import org.jetbrains.kotlin.cli.CliDiagnostics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.ArgumentUtilsKt;
import org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArguments;
import org.jetbrains.kotlin.cli.common.arguments.CommonKlibBasedCompilerArguments;
import org.jetbrains.kotlin.config.CommonConfigurationKeys;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.DuplicatedUniqueNameStrategy;
import org.jetbrains.kotlin.config.KlibAbiCompatibilityLevel;
import org.jetbrains.kotlin.config.KlibConfigurationKeysKt;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.LanguageVersion;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.KtSourcelessDiagnosticFactory;
import org.jetbrains.kotlin.konan.file.ZipFileSystemAccessor;
import org.jetbrains.kotlin.konan.file.ZipFileSystemInPlaceAccessor;
import org.jetbrains.kotlin.library.KotlinAbiVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000X\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\u001a\"\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u001a\u0012\u0010\t\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\n\u001a\u00020\u0002\u001a\u0018\u0010\u000b\u001a\u0004\u0018\u00010\f*\u00020\u00022\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0002\u001a?\u0010\u000f\u001a\u0004\u0018\u00010\u0010\"\b\b\u0000\u0010\u0011*\u00020\u0012*\u0002H\u00112\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u0002H\u0011\u0012\u0004\u0012\u00020\u000e0\u00142\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\u0016\u001a\n\u0010\u0017\u001a\u00020\u0001*\u00020\u0002\u001a6\u0010\u001c\u001a\u00020\u0001*\u00020\u00022\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001e2\u0006\u0010\u001f\u001a\u00020\u000e2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u000e0\u001e2\u0006\u0010!\u001a\u00020\u000e\u001a\n\u0010\"\u001a\u00020\u0001*\u00020\u0002\"\u001a\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b0\u0019X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"setupCommonKlibArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "arguments", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonKlibBasedCompilerArguments;", "canBeMetadataKlibCompilation", Argument.Delimiters.none, "rootDisposable", "Lcom/intellij/openapi/Disposable;", "copyCommonKlibArgumentsFrom", "source", "parseCustomKotlinAbiVersion", "Lorg/jetbrains/kotlin/library/KotlinAbiVersion;", "customKlibAbiVersion", Argument.Delimiters.none, "getZipFileSystemAccessor", "Lorg/jetbrains/kotlin/konan/file/ZipFileSystemAccessor;", "A", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;", "zipFileAccessorCacheLimitArgument", "Lkotlin/reflect/KProperty1;", "configuration", "(Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;Lkotlin/reflect/KProperty1;Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lcom/intellij/openapi/Disposable;)Lorg/jetbrains/kotlin/konan/file/ZipFileSystemAccessor;", "setupKlibAbiCompatibilityLevel", "LANGUAGE_VERSION_TO_ABI_COMPATIBILITY_LEVEL", "Ljava/util/EnumMap;", "Lorg/jetbrains/kotlin/config/LanguageVersion;", "Lorg/jetbrains/kotlin/config/KlibAbiCompatibilityLevel;", "checkForUnexpectedKlibLibraries", "librariesToCheck", Argument.Delimiters.none, "librariesToCheckArgument", "allLibraries", "allLibrariesArgument", "prohibitExportKlibToOlderAbiVersionAtSecondStage", "org.jetbrains.kotlin:cli"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KlibArgumentsKt {
    private static final EnumMap<LanguageVersion, KlibAbiCompatibilityLevel> LANGUAGE_VERSION_TO_ABI_COMPATIBILITY_LEVEL;

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[KlibAbiCompatibilityLevel.values().length];
            try {
                iArr[KlibAbiCompatibilityLevel.ABI_LEVEL_2_3.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[KlibAbiCompatibilityLevel.ABI_LEVEL_2_4.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static {
        LanguageVersion languageVersion;
        EnumMap<LanguageVersion, KlibAbiCompatibilityLevel> enumMap = new EnumMap<>(LanguageVersion.class);
        for (Object obj : KlibAbiCompatibilityLevel.getEntries()) {
            int i = WhenMappings.$EnumSwitchMapping$0[((KlibAbiCompatibilityLevel) obj).ordinal()];
            if (i == 1) {
                languageVersion = LanguageVersion.KOTLIN_2_3;
            } else {
                if (i != 2) {
                    bu8.a();
                    return;
                }
                languageVersion = LanguageVersion.KOTLIN_2_4;
            }
            enumMap.put(languageVersion, obj);
        }
        if (enumMap.size() != KlibAbiCompatibilityLevel.getEntries().size()) {
            k2d.a("Check failed.");
            return;
        }
        for (LanguageVersion languageVersion2 : LanguageVersion.getEntries()) {
            if (languageVersion2.compareTo(LanguageVersion.KOTLIN_2_1) >= 0 && !enumMap.containsKey(languageVersion2)) {
                enumMap.put(languageVersion2, KlibAbiCompatibilityLevel.INSTANCE.getLATEST_STABLE());
            }
        }
        LANGUAGE_VERSION_TO_ABI_COMPATIBILITY_LEVEL = enumMap;
    }

    public static final void checkForUnexpectedKlibLibraries(CompilerConfiguration compilerConfiguration, List<String> list, String str, List<String> list2, String str2) {
        compilerConfiguration.getClass();
        list.getClass();
        str.getClass();
        list2.getClass();
        str2.getClass();
        if (list.isEmpty()) {
            return;
        }
        Set setSubtract = CollectionsKt.subtract(list, CollectionsKt.toSet(list2));
        if (setSubtract.isEmpty()) {
            return;
        }
        CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_WARNING(), "There are libraries in " + str + " CLI argument that are not included in " + str2 + " CLI argument: " + CollectionsKt.joinToString$default(setSubtract, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null), null, 4, null);
    }

    public static final void copyCommonKlibArgumentsFrom(CompilerConfiguration compilerConfiguration, CompilerConfiguration compilerConfiguration2) {
        compilerConfiguration.getClass();
        compilerConfiguration2.getClass();
        KlibConfigurationKeysKt.setKlibRelativePathBases(compilerConfiguration, KlibConfigurationKeysKt.getKlibRelativePathBases(compilerConfiguration2));
        KlibConfigurationKeysKt.setKlibNormalizeAbsolutePath(compilerConfiguration, KlibConfigurationKeysKt.getKlibNormalizeAbsolutePath(compilerConfiguration2));
        KlibConfigurationKeysKt.setProduceKlibSignaturesClashChecks(compilerConfiguration, KlibConfigurationKeysKt.getProduceKlibSignaturesClashChecks(compilerConfiguration2));
        CLIConfigurationKeysKt.setRenderDiagnosticInternalName(compilerConfiguration, CLIConfigurationKeysKt.getRenderDiagnosticInternalName(compilerConfiguration2));
        KlibConfigurationKeysKt.setSkipLibrarySpecialCompatibilityChecks(compilerConfiguration, KlibConfigurationKeysKt.getSkipLibrarySpecialCompatibilityChecks(compilerConfiguration2));
        DuplicatedUniqueNameStrategy duplicatedUniqueNameStrategy = KlibConfigurationKeysKt.getDuplicatedUniqueNameStrategy(compilerConfiguration2);
        if (duplicatedUniqueNameStrategy != null) {
            KlibConfigurationKeysKt.setDuplicatedUniqueNameStrategy(compilerConfiguration, duplicatedUniqueNameStrategy);
        }
        KlibConfigurationKeysKt.setCustomKlibAbiVersion(compilerConfiguration, KlibConfigurationKeysKt.getCustomKlibAbiVersion(compilerConfiguration2));
        KlibConfigurationKeysKt.setKlibAbiCompatibilityLevel(compilerConfiguration, KlibConfigurationKeysKt.getKlibAbiCompatibilityLevel(compilerConfiguration2));
        KlibConfigurationKeysKt.setZipFileSystemAccessor(compilerConfiguration, KlibConfigurationKeysKt.getZipFileSystemAccessor(compilerConfiguration2));
    }

    public static final <A extends CommonCompilerArguments> ZipFileSystemAccessor getZipFileSystemAccessor(A a, KProperty1<A, String> kProperty1, CompilerConfiguration compilerConfiguration, Disposable disposable) {
        a.getClass();
        kProperty1.getClass();
        compilerConfiguration.getClass();
        disposable.getClass();
        String str = (String) kProperty1.get(a);
        Integer intOrNull = StringsKt.toIntOrNull(str);
        if (intOrNull != null && intOrNull.intValue() >= 0) {
            if (intOrNull.intValue() <= 0) {
                return ZipFileSystemInPlaceAccessor.INSTANCE;
            }
            DisposableZipFileSystemAccessor disposableZipFileSystemAccessor = new DisposableZipFileSystemAccessor(intOrNull.intValue());
            Disposer.register(disposable, disposableZipFileSystemAccessor);
            return disposableZipFileSystemAccessor;
        }
        KtSourcelessDiagnosticFactory compiler_arguments_error = CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_ERROR();
        String str2 = "Cannot parse " + ArgumentUtilsKt.getCliArgument(kProperty1) + " value: \"" + str + "\". ";
        Unit unit = Unit.INSTANCE;
        CliDiagnosticReportingKt.report$default(compilerConfiguration, compiler_arguments_error, str2.concat("It must be an integer >= 0."), null, 4, null);
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:31:0x0084  */
    /* JADX WARN: Code duplicated, block: B:34:0x008e  */
    /* JADX WARN: Code duplicated, block: B:39:0x00a0  */
    /* JADX WARN: Code duplicated, block: B:52:0x00a3 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:55:? A[LOOP:1: B:32:0x0088->B:55:?, LOOP_END, SYNTHETIC] */
    private static final KotlinAbiVersion parseCustomKotlinAbiVersion(CompilerConfiguration compilerConfiguration, String str) {
        List listSplit$default;
        Iterator it;
        int iIntValue;
        boolean z;
        if (str == null || (listSplit$default = StringsKt.split$default(str, new char[]{'.'}, false, 0, 6, (Object) null)) == null) {
            return null;
        }
        if (listSplit$default.size() != 3) {
            CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_ERROR(), "Invalid ABI version format. Expected format: <major>.<minor>.<patch>", null, 4, null);
            return null;
        }
        List list = listSplit$default;
        ArrayList arrayList = new ArrayList();
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            Integer intOrNull = StringsKt.toIntOrNull((String) it2.next());
            if (intOrNull != null) {
                arrayList.add(intOrNull);
            }
        }
        Regex regex = new Regex("(0|[1-9]\\d{0,2})");
        if ((list instanceof Collection) && list.isEmpty()) {
            if (!arrayList.isEmpty()) {
                it = arrayList.iterator();
                while (it.hasNext()) {
                    iIntValue = ((Number) it.next()).intValue();
                    if (iIntValue >= 0) {
                        z = false;
                    } else {
                        z = false;
                    }
                    if (!z) {
                    }
                }
            }
            return new KotlinAbiVersion(((Number) arrayList.get(0)).intValue(), ((Number) arrayList.get(1)).intValue(), ((Number) arrayList.get(2)).intValue());
        }
        Iterator it3 = list.iterator();
        while (it3.hasNext()) {
            if (!regex.matches((String) it3.next())) {
            }
        }
        if (!arrayList.isEmpty()) {
            it = arrayList.iterator();
            while (it.hasNext()) {
                iIntValue = ((Number) it.next()).intValue();
                if (iIntValue >= 0 || iIntValue >= 256) {
                    z = false;
                } else {
                    z = true;
                }
                if (!z) {
                }
            }
        }
        return new KotlinAbiVersion(((Number) arrayList.get(0)).intValue(), ((Number) arrayList.get(1)).intValue(), ((Number) arrayList.get(2)).intValue());
        CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_ERROR(), "Invalid ABI version numbers. Each part must be in the range 0..255.", null, 4, null);
        return null;
    }

    public static final void prohibitExportKlibToOlderAbiVersionAtSecondStage(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        if (CommonConfigurationKeysKt.getLanguageVersionSettings(compilerConfiguration).supportsFeature(LanguageFeature.ExportKlibToOlderAbiVersion)) {
            CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_ERROR(), "The language feature 'ExportKlibToOlderAbiVersion' is only intended for producing KLIBs and cannot be used during the second stage of compilation (KLIB to executable/binary).", null, 4, null);
        }
    }

    public static final void setupCommonKlibArguments(CompilerConfiguration compilerConfiguration, CommonKlibBasedCompilerArguments commonKlibBasedCompilerArguments, boolean z, Disposable disposable) {
        compilerConfiguration.getClass();
        commonKlibBasedCompilerArguments.getClass();
        disposable.getClass();
        boolean z2 = z && commonKlibBasedCompilerArguments.getMetadataKlib();
        String[] relativePathBases = commonKlibBasedCompilerArguments.getRelativePathBases();
        if (relativePathBases != null) {
            KlibConfigurationKeysKt.setKlibRelativePathBases(compilerConfiguration, CollectionsKt.plus(KlibConfigurationKeysKt.getKlibRelativePathBases(compilerConfiguration), relativePathBases));
        }
        KlibConfigurationKeysKt.setKlibNormalizeAbsolutePath(compilerConfiguration, commonKlibBasedCompilerArguments.getNormalizeAbsolutePath());
        KlibConfigurationKeysKt.setProduceKlibSignaturesClashChecks(compilerConfiguration, commonKlibBasedCompilerArguments.getEnableSignatureClashChecks());
        CLIConfigurationKeysKt.setRenderDiagnosticInternalName(compilerConfiguration, commonKlibBasedCompilerArguments.getRenderInternalDiagnosticNames());
        KlibConfigurationKeysKt.setSkipLibrarySpecialCompatibilityChecks(compilerConfiguration, commonKlibBasedCompilerArguments.getSkipLibrarySpecialCompatibilityChecks());
        KlibConfigurationKeysKt.setDuplicatedUniqueNameStrategy(compilerConfiguration, DuplicatedUniqueNameStrategy.INSTANCE.parseOrDefault(commonKlibBasedCompilerArguments.getDuplicatedUniqueNameStrategy(), z2 ? DuplicatedUniqueNameStrategy.ALLOW_ALL_WITH_WARNING : DuplicatedUniqueNameStrategy.DENY));
        KlibConfigurationKeysKt.setCustomKlibAbiVersion(compilerConfiguration, parseCustomKotlinAbiVersion(compilerConfiguration, commonKlibBasedCompilerArguments.getCustomKlibAbiVersion()));
        if (!z2) {
            setupKlibAbiCompatibilityLevel(compilerConfiguration);
        }
        KlibConfigurationKeysKt.setZipFileSystemAccessor(compilerConfiguration, getZipFileSystemAccessor(commonKlibBasedCompilerArguments, new MutablePropertyReference1Impl() { // from class: org.jetbrains.kotlin.cli.common.KlibArgumentsKt.setupCommonKlibArguments.2
            public Object get(Object obj) {
                return ((CommonKlibBasedCompilerArguments) obj).getKlibZipFileAccessorCacheLimit();
            }

            public void set(Object obj, Object obj2) {
                ((CommonKlibBasedCompilerArguments) obj).setKlibZipFileAccessorCacheLimit((String) obj2);
            }
        }, compilerConfiguration, disposable));
    }

    public static final void setupKlibAbiCompatibilityLevel(CompilerConfiguration compilerConfiguration) {
        KlibAbiCompatibilityLevel latest_stable;
        compilerConfiguration.getClass();
        LanguageVersionSettings languageVersionSettings = (LanguageVersionSettings) compilerConfiguration.get(CommonConfigurationKeys.LANGUAGE_VERSION_SETTINGS);
        if (languageVersionSettings == null) {
            k2d.a("Language version settings should be already set up");
            return;
        }
        if (languageVersionSettings.supportsFeature(LanguageFeature.ExportKlibToOlderAbiVersion)) {
            LanguageVersion languageVersion = languageVersionSettings.getLanguageVersion();
            EnumMap<LanguageVersion, KlibAbiCompatibilityLevel> enumMap = LANGUAGE_VERSION_TO_ABI_COMPATIBILITY_LEVEL;
            latest_stable = enumMap.get(languageVersion);
            if (latest_stable == null) {
                KtSourcelessDiagnosticFactory compiler_arguments_error = CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_ERROR();
                StringBuilder sb = new StringBuilder();
                sb.append("Exporting KLIBs in older ABI format is only supported for the following language versions: ");
                Iterable iterableKeySet = enumMap.keySet();
                iterableKeySet.getClass();
                ArrayList arrayList = new ArrayList();
                for (Object obj : iterableKeySet) {
                    if (((LanguageVersion) obj).compareTo(LanguageVersion.LATEST_STABLE) >= 0) {
                        break;
                    } else {
                        arrayList.add(obj);
                    }
                }
                CollectionsKt.joinTo$default(arrayList, sb, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 126, (Object) null);
                sb.append(". The current language version is ");
                sb.append(languageVersion);
                Unit unit = Unit.INSTANCE;
                CliDiagnosticReportingKt.report$default(compilerConfiguration, compiler_arguments_error, sb.toString(), null, 4, null);
                return;
            }
        } else {
            latest_stable = KlibAbiCompatibilityLevel.INSTANCE.getLATEST_STABLE();
        }
        KlibConfigurationKeysKt.setKlibAbiCompatibilityLevel(compilerConfiguration, latest_stable);
    }
}
