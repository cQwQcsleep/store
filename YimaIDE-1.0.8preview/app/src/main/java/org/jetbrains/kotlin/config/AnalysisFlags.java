package org.jetbrains.kotlin.config;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b0\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R'\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u0012\u0004\b\u0007\u0010\u0003\u001a\u0004\b\b\u0010\tR'\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b\u000f\u0010\u000b\u0012\u0004\b\r\u0010\u0003\u001a\u0004\b\u000e\u0010\tR'\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b\u0013\u0010\u000b\u0012\u0004\b\u0011\u0010\u0003\u001a\u0004\b\u0012\u0010\tR'\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b\u0017\u0010\u000b\u0012\u0004\b\u0015\u0010\u0003\u001a\u0004\b\u0016\u0010\tR'\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b\u001b\u0010\u000b\u0012\u0004\b\u0019\u0010\u0003\u001a\u0004\b\u001a\u0010\tR-\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u001d0\u00058FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b!\u0010\u000b\u0012\u0004\b\u001f\u0010\u0003\u001a\u0004\b \u0010\tR'\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b%\u0010\u000b\u0012\u0004\b#\u0010\u0003\u001a\u0004\b$\u0010\tR'\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b)\u0010\u000b\u0012\u0004\b'\u0010\u0003\u001a\u0004\b(\u0010\tR'\u0010*\u001a\b\u0012\u0004\u0012\u00020+0\u00058FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b.\u0010\u000b\u0012\u0004\b,\u0010\u0003\u001a\u0004\b-\u0010\tR'\u0010/\u001a\b\u0012\u0004\u0012\u00020+0\u00058FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b2\u0010\u000b\u0012\u0004\b0\u0010\u0003\u001a\u0004\b1\u0010\tR'\u00103\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b6\u0010\u000b\u0012\u0004\b4\u0010\u0003\u001a\u0004\b5\u0010\tR'\u00107\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b:\u0010\u000b\u0012\u0004\b8\u0010\u0003\u001a\u0004\b9\u0010\tR'\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b>\u0010\u000b\u0012\u0004\b<\u0010\u0003\u001a\u0004\b=\u0010\tR'\u0010?\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\bB\u0010\u000b\u0012\u0004\b@\u0010\u0003\u001a\u0004\bA\u0010\tR'\u0010C\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\bF\u0010\u000b\u0012\u0004\bD\u0010\u0003\u001a\u0004\bE\u0010\tR'\u0010G\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\bJ\u0010\u000b\u0012\u0004\bH\u0010\u0003\u001a\u0004\bI\u0010\tR'\u0010K\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\bN\u0010\u000b\u0012\u0004\bL\u0010\u0003\u001a\u0004\bM\u0010\tR'\u0010O\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\bR\u0010\u000b\u0012\u0004\bP\u0010\u0003\u001a\u0004\bQ\u0010\tR'\u0010S\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\bV\u0010\u000b\u0012\u0004\bT\u0010\u0003\u001a\u0004\bU\u0010\tR'\u0010W\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\bZ\u0010\u000b\u0012\u0004\bX\u0010\u0003\u001a\u0004\bY\u0010\tR-\u0010[\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020]0\\0\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b_\u0010\u000b\u001a\u0004\b^\u0010\tR'\u0010`\u001a\b\u0012\u0004\u0012\u00020a0\u00058FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\bd\u0010\u000b\u0012\u0004\bb\u0010\u0003\u001a\u0004\bc\u0010\tR!\u0010e\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bg\u0010\u000b\u001a\u0004\bf\u0010\tR!\u0010h\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bj\u0010\u000b\u001a\u0004\bi\u0010\tR!\u0010k\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bm\u0010\u000b\u001a\u0004\bl\u0010\tR!\u0010n\u001a\b\u0012\u0004\u0012\u00020o0\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bq\u0010\u000b\u001a\u0004\bp\u0010\t¨\u0006r"}, d2 = {"Lorg/jetbrains/kotlin/config/AnalysisFlags;", Argument.Delimiters.none, "<init>", "()V", "skipMetadataVersionCheck", "Lorg/jetbrains/kotlin/config/AnalysisFlag;", Argument.Delimiters.none, "getSkipMetadataVersionCheck$annotations", "getSkipMetadataVersionCheck", "()Lorg/jetbrains/kotlin/config/AnalysisFlag;", "skipMetadataVersionCheck$delegate", "Lorg/jetbrains/kotlin/config/AnalysisFlag$Delegate;", "metadataCompilation", "getMetadataCompilation$annotations", "getMetadataCompilation", "metadataCompilation$delegate", "skipPrereleaseCheck", "getSkipPrereleaseCheck$annotations", "getSkipPrereleaseCheck", "skipPrereleaseCheck$delegate", "multiPlatformDoNotCheckActual", "getMultiPlatformDoNotCheckActual$annotations", "getMultiPlatformDoNotCheckActual", "multiPlatformDoNotCheckActual$delegate", "skipExpectedActualDeclarationChecker", "getSkipExpectedActualDeclarationChecker$annotations", "getSkipExpectedActualDeclarationChecker", "skipExpectedActualDeclarationChecker$delegate", "optIn", Argument.Delimiters.none, Argument.Delimiters.none, "getOptIn$annotations", "getOptIn", "optIn$delegate", "explicitApiVersion", "getExplicitApiVersion$annotations", "getExplicitApiVersion", "explicitApiVersion$delegate", "ignoreDataFlowInAssert", "getIgnoreDataFlowInAssert$annotations", "getIgnoreDataFlowInAssert", "ignoreDataFlowInAssert$delegate", "explicitApiMode", "Lorg/jetbrains/kotlin/config/ExplicitApiMode;", "getExplicitApiMode$annotations", "getExplicitApiMode", "explicitApiMode$delegate", "explicitReturnTypes", "getExplicitReturnTypes$annotations", "getExplicitReturnTypes", "explicitReturnTypes$delegate", "ideMode", "getIdeMode$annotations", "getIdeMode", "ideMode$delegate", "allowUnstableDependencies", "getAllowUnstableDependencies$annotations", "getAllowUnstableDependencies", "allowUnstableDependencies$delegate", "libraryToSourceAnalysis", "getLibraryToSourceAnalysis$annotations", "getLibraryToSourceAnalysis", "libraryToSourceAnalysis$delegate", "allowKotlinPackage", "getAllowKotlinPackage$annotations", "getAllowKotlinPackage", "allowKotlinPackage$delegate", "muteExpectActualClassesWarning", "getMuteExpectActualClassesWarning$annotations", "getMuteExpectActualClassesWarning", "muteExpectActualClassesWarning$delegate", "allowFullyQualifiedNameInKClass", "getAllowFullyQualifiedNameInKClass$annotations", "getAllowFullyQualifiedNameInKClass", "allowFullyQualifiedNameInKClass$delegate", "eagerResolveOfLightClasses", "getEagerResolveOfLightClasses$annotations", "getEagerResolveOfLightClasses", "eagerResolveOfLightClasses$delegate", "dontWarnOnErrorSuppression", "getDontWarnOnErrorSuppression$annotations", "getDontWarnOnErrorSuppression", "dontWarnOnErrorSuppression$delegate", "stdlibCompilation", "getStdlibCompilation$annotations", "getStdlibCompilation", "stdlibCompilation$delegate", "expandTypeAliasesInTypeResolution", "getExpandTypeAliasesInTypeResolution$annotations", "getExpandTypeAliasesInTypeResolution", "expandTypeAliasesInTypeResolution$delegate", "warningLevels", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/WarningLevel;", "getWarningLevels", "warningLevels$delegate", "returnValueCheckerMode", "Lorg/jetbrains/kotlin/config/ReturnValueCheckerMode;", "getReturnValueCheckerMode$annotations", "getReturnValueCheckerMode", "returnValueCheckerMode$delegate", "lenientMode", "getLenientMode", "lenientMode$delegate", "hierarchicalMultiplatformCompilation", "getHierarchicalMultiplatformCompilation", "hierarchicalMultiplatformCompilation$delegate", "headerMode", "getHeaderMode", "headerMode$delegate", "headerModeType", "Lorg/jetbrains/kotlin/config/HeaderMode;", "getHeaderModeType", "headerModeType$delegate", "org.jetbrains.kotlin:config"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class AnalysisFlags {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    public static final AnalysisFlags INSTANCE;

    /* JADX INFO: renamed from: allowFullyQualifiedNameInKClass$delegate, reason: from kotlin metadata */
    private static final AnalysisFlag.Delegate allowFullyQualifiedNameInKClass;

    /* JADX INFO: renamed from: allowKotlinPackage$delegate, reason: from kotlin metadata */
    private static final AnalysisFlag.Delegate allowKotlinPackage;

    /* JADX INFO: renamed from: allowUnstableDependencies$delegate, reason: from kotlin metadata */
    private static final AnalysisFlag.Delegate allowUnstableDependencies;

    /* JADX INFO: renamed from: dontWarnOnErrorSuppression$delegate, reason: from kotlin metadata */
    private static final AnalysisFlag.Delegate dontWarnOnErrorSuppression;

    /* JADX INFO: renamed from: eagerResolveOfLightClasses$delegate, reason: from kotlin metadata */
    private static final AnalysisFlag.Delegate eagerResolveOfLightClasses;

    /* JADX INFO: renamed from: expandTypeAliasesInTypeResolution$delegate, reason: from kotlin metadata */
    private static final AnalysisFlag.Delegate expandTypeAliasesInTypeResolution;

    /* JADX INFO: renamed from: explicitApiMode$delegate, reason: from kotlin metadata */
    private static final AnalysisFlag.Delegate explicitApiMode;

    /* JADX INFO: renamed from: explicitApiVersion$delegate, reason: from kotlin metadata */
    private static final AnalysisFlag.Delegate explicitApiVersion;

    /* JADX INFO: renamed from: explicitReturnTypes$delegate, reason: from kotlin metadata */
    private static final AnalysisFlag.Delegate explicitReturnTypes;

    /* JADX INFO: renamed from: headerMode$delegate, reason: from kotlin metadata */
    private static final AnalysisFlag.Delegate headerMode;

    /* JADX INFO: renamed from: headerModeType$delegate, reason: from kotlin metadata */
    private static final AnalysisFlag.Delegate headerModeType;

    /* JADX INFO: renamed from: hierarchicalMultiplatformCompilation$delegate, reason: from kotlin metadata */
    private static final AnalysisFlag.Delegate hierarchicalMultiplatformCompilation;

    /* JADX INFO: renamed from: ideMode$delegate, reason: from kotlin metadata */
    private static final AnalysisFlag.Delegate ideMode;

    /* JADX INFO: renamed from: ignoreDataFlowInAssert$delegate, reason: from kotlin metadata */
    private static final AnalysisFlag.Delegate ignoreDataFlowInAssert;

    /* JADX INFO: renamed from: lenientMode$delegate, reason: from kotlin metadata */
    private static final AnalysisFlag.Delegate lenientMode;

    /* JADX INFO: renamed from: libraryToSourceAnalysis$delegate, reason: from kotlin metadata */
    private static final AnalysisFlag.Delegate libraryToSourceAnalysis;

    /* JADX INFO: renamed from: metadataCompilation$delegate, reason: from kotlin metadata */
    private static final AnalysisFlag.Delegate metadataCompilation;

    /* JADX INFO: renamed from: multiPlatformDoNotCheckActual$delegate, reason: from kotlin metadata */
    private static final AnalysisFlag.Delegate multiPlatformDoNotCheckActual;

    /* JADX INFO: renamed from: muteExpectActualClassesWarning$delegate, reason: from kotlin metadata */
    private static final AnalysisFlag.Delegate muteExpectActualClassesWarning;

    /* JADX INFO: renamed from: optIn$delegate, reason: from kotlin metadata */
    private static final AnalysisFlag.Delegate optIn;

    /* JADX INFO: renamed from: returnValueCheckerMode$delegate, reason: from kotlin metadata */
    private static final AnalysisFlag.Delegate returnValueCheckerMode;

    /* JADX INFO: renamed from: skipExpectedActualDeclarationChecker$delegate, reason: from kotlin metadata */
    private static final AnalysisFlag.Delegate skipExpectedActualDeclarationChecker;

    /* JADX INFO: renamed from: skipMetadataVersionCheck$delegate, reason: from kotlin metadata */
    private static final AnalysisFlag.Delegate skipMetadataVersionCheck;

    /* JADX INFO: renamed from: skipPrereleaseCheck$delegate, reason: from kotlin metadata */
    private static final AnalysisFlag.Delegate skipPrereleaseCheck;

    /* JADX INFO: renamed from: stdlibCompilation$delegate, reason: from kotlin metadata */
    private static final AnalysisFlag.Delegate stdlibCompilation;

    /* JADX INFO: renamed from: warningLevels$delegate, reason: from kotlin metadata */
    private static final AnalysisFlag.Delegate warningLevels;

    static {
        KProperty<?>[] kPropertyArr = {new PropertyReference1Impl<>(AnalysisFlags.class, "skipMetadataVersionCheck", "getSkipMetadataVersionCheck()Lorg/jetbrains/kotlin/config/AnalysisFlag;", 0), new PropertyReference1Impl<>(AnalysisFlags.class, "metadataCompilation", "getMetadataCompilation()Lorg/jetbrains/kotlin/config/AnalysisFlag;", 0), new PropertyReference1Impl<>(AnalysisFlags.class, "skipPrereleaseCheck", "getSkipPrereleaseCheck()Lorg/jetbrains/kotlin/config/AnalysisFlag;", 0), new PropertyReference1Impl<>(AnalysisFlags.class, "multiPlatformDoNotCheckActual", "getMultiPlatformDoNotCheckActual()Lorg/jetbrains/kotlin/config/AnalysisFlag;", 0), new PropertyReference1Impl<>(AnalysisFlags.class, "skipExpectedActualDeclarationChecker", "getSkipExpectedActualDeclarationChecker()Lorg/jetbrains/kotlin/config/AnalysisFlag;", 0), new PropertyReference1Impl<>(AnalysisFlags.class, "optIn", "getOptIn()Lorg/jetbrains/kotlin/config/AnalysisFlag;", 0), new PropertyReference1Impl<>(AnalysisFlags.class, "explicitApiVersion", "getExplicitApiVersion()Lorg/jetbrains/kotlin/config/AnalysisFlag;", 0), new PropertyReference1Impl<>(AnalysisFlags.class, "ignoreDataFlowInAssert", "getIgnoreDataFlowInAssert()Lorg/jetbrains/kotlin/config/AnalysisFlag;", 0), new PropertyReference1Impl<>(AnalysisFlags.class, "explicitApiMode", "getExplicitApiMode()Lorg/jetbrains/kotlin/config/AnalysisFlag;", 0), new PropertyReference1Impl<>(AnalysisFlags.class, "explicitReturnTypes", "getExplicitReturnTypes()Lorg/jetbrains/kotlin/config/AnalysisFlag;", 0), new PropertyReference1Impl<>(AnalysisFlags.class, "ideMode", "getIdeMode()Lorg/jetbrains/kotlin/config/AnalysisFlag;", 0), new PropertyReference1Impl<>(AnalysisFlags.class, "allowUnstableDependencies", "getAllowUnstableDependencies()Lorg/jetbrains/kotlin/config/AnalysisFlag;", 0), new PropertyReference1Impl<>(AnalysisFlags.class, "libraryToSourceAnalysis", "getLibraryToSourceAnalysis()Lorg/jetbrains/kotlin/config/AnalysisFlag;", 0), new PropertyReference1Impl<>(AnalysisFlags.class, "allowKotlinPackage", "getAllowKotlinPackage()Lorg/jetbrains/kotlin/config/AnalysisFlag;", 0), new PropertyReference1Impl<>(AnalysisFlags.class, "muteExpectActualClassesWarning", "getMuteExpectActualClassesWarning()Lorg/jetbrains/kotlin/config/AnalysisFlag;", 0), new PropertyReference1Impl<>(AnalysisFlags.class, "allowFullyQualifiedNameInKClass", "getAllowFullyQualifiedNameInKClass()Lorg/jetbrains/kotlin/config/AnalysisFlag;", 0), new PropertyReference1Impl<>(AnalysisFlags.class, "eagerResolveOfLightClasses", "getEagerResolveOfLightClasses()Lorg/jetbrains/kotlin/config/AnalysisFlag;", 0), new PropertyReference1Impl<>(AnalysisFlags.class, "dontWarnOnErrorSuppression", "getDontWarnOnErrorSuppression()Lorg/jetbrains/kotlin/config/AnalysisFlag;", 0), new PropertyReference1Impl<>(AnalysisFlags.class, "stdlibCompilation", "getStdlibCompilation()Lorg/jetbrains/kotlin/config/AnalysisFlag;", 0), new PropertyReference1Impl<>(AnalysisFlags.class, "expandTypeAliasesInTypeResolution", "getExpandTypeAliasesInTypeResolution()Lorg/jetbrains/kotlin/config/AnalysisFlag;", 0), new PropertyReference1Impl<>(AnalysisFlags.class, "warningLevels", "getWarningLevels()Lorg/jetbrains/kotlin/config/AnalysisFlag;", 0), new PropertyReference1Impl<>(AnalysisFlags.class, "returnValueCheckerMode", "getReturnValueCheckerMode()Lorg/jetbrains/kotlin/config/AnalysisFlag;", 0), new PropertyReference1Impl<>(AnalysisFlags.class, "lenientMode", "getLenientMode()Lorg/jetbrains/kotlin/config/AnalysisFlag;", 0), new PropertyReference1Impl<>(AnalysisFlags.class, "hierarchicalMultiplatformCompilation", "getHierarchicalMultiplatformCompilation()Lorg/jetbrains/kotlin/config/AnalysisFlag;", 0), new PropertyReference1Impl<>(AnalysisFlags.class, "headerMode", "getHeaderMode()Lorg/jetbrains/kotlin/config/AnalysisFlag;", 0), new PropertyReference1Impl<>(AnalysisFlags.class, "headerModeType", "getHeaderModeType()Lorg/jetbrains/kotlin/config/AnalysisFlag;", 0)};
        $$delegatedProperties = kPropertyArr;
        AnalysisFlags analysisFlags = new AnalysisFlags();
        INSTANCE = analysisFlags;
        AnalysisFlag.Delegates.Boolean.Companion companion = AnalysisFlag.Delegates.Boolean.INSTANCE;
        skipMetadataVersionCheck = companion.provideDelegate(analysisFlags, kPropertyArr[0]);
        metadataCompilation = companion.provideDelegate(analysisFlags, kPropertyArr[1]);
        skipPrereleaseCheck = companion.provideDelegate(analysisFlags, kPropertyArr[2]);
        multiPlatformDoNotCheckActual = companion.provideDelegate(analysisFlags, kPropertyArr[3]);
        skipExpectedActualDeclarationChecker = companion.provideDelegate(analysisFlags, kPropertyArr[4]);
        optIn = AnalysisFlag.Delegates.ListOfStrings.INSTANCE.provideDelegate(analysisFlags, kPropertyArr[5]);
        explicitApiVersion = companion.provideDelegate(analysisFlags, kPropertyArr[6]);
        ignoreDataFlowInAssert = companion.provideDelegate(analysisFlags, kPropertyArr[7]);
        AnalysisFlag.Delegates.ApiModeDisabledByDefault apiModeDisabledByDefault = AnalysisFlag.Delegates.ApiModeDisabledByDefault.INSTANCE;
        explicitApiMode = apiModeDisabledByDefault.provideDelegate(analysisFlags, kPropertyArr[8]);
        explicitReturnTypes = apiModeDisabledByDefault.provideDelegate(analysisFlags, kPropertyArr[9]);
        ideMode = companion.provideDelegate(analysisFlags, kPropertyArr[10]);
        allowUnstableDependencies = companion.provideDelegate(analysisFlags, kPropertyArr[11]);
        libraryToSourceAnalysis = companion.provideDelegate(analysisFlags, kPropertyArr[12]);
        allowKotlinPackage = companion.provideDelegate(analysisFlags, kPropertyArr[13]);
        muteExpectActualClassesWarning = companion.provideDelegate(analysisFlags, kPropertyArr[14]);
        allowFullyQualifiedNameInKClass = companion.provideDelegate(analysisFlags, kPropertyArr[15]);
        eagerResolveOfLightClasses = companion.provideDelegate(analysisFlags, kPropertyArr[16]);
        dontWarnOnErrorSuppression = companion.provideDelegate(analysisFlags, kPropertyArr[17]);
        stdlibCompilation = companion.provideDelegate(analysisFlags, kPropertyArr[18]);
        expandTypeAliasesInTypeResolution = new AnalysisFlag.Delegates.Boolean(true).provideDelegate(analysisFlags, kPropertyArr[19]);
        warningLevels = AnalysisFlag.Delegates.WarningLevelMap.INSTANCE.provideDelegate(analysisFlags, kPropertyArr[20]);
        returnValueCheckerMode = AnalysisFlag.Delegates.ReturnValueCheckerDisabledByDefault.INSTANCE.provideDelegate(analysisFlags, kPropertyArr[21]);
        lenientMode = companion.provideDelegate(analysisFlags, kPropertyArr[22]);
        hierarchicalMultiplatformCompilation = new AnalysisFlag.Delegates.Boolean(false).provideDelegate(analysisFlags, kPropertyArr[23]);
        headerMode = companion.provideDelegate(analysisFlags, kPropertyArr[24]);
        headerModeType = AnalysisFlag.Delegates.HeaderModeTypeAnyByDefault.INSTANCE.provideDelegate(analysisFlags, kPropertyArr[25]);
    }

    private AnalysisFlags() {
    }

    public static final AnalysisFlag<Boolean> getAllowFullyQualifiedNameInKClass() {
        return allowFullyQualifiedNameInKClass.getValue((Object) INSTANCE, $$delegatedProperties[15]);
    }

    @JvmStatic
    public static /* synthetic */ void getAllowFullyQualifiedNameInKClass$annotations() {
    }

    public static final AnalysisFlag<Boolean> getAllowKotlinPackage() {
        return allowKotlinPackage.getValue((Object) INSTANCE, $$delegatedProperties[13]);
    }

    @JvmStatic
    public static /* synthetic */ void getAllowKotlinPackage$annotations() {
    }

    public static final AnalysisFlag<Boolean> getAllowUnstableDependencies() {
        return allowUnstableDependencies.getValue((Object) INSTANCE, $$delegatedProperties[11]);
    }

    @JvmStatic
    public static /* synthetic */ void getAllowUnstableDependencies$annotations() {
    }

    public static final AnalysisFlag<Boolean> getDontWarnOnErrorSuppression() {
        return dontWarnOnErrorSuppression.getValue((Object) INSTANCE, $$delegatedProperties[17]);
    }

    @JvmStatic
    public static /* synthetic */ void getDontWarnOnErrorSuppression$annotations() {
    }

    public static final AnalysisFlag<Boolean> getEagerResolveOfLightClasses() {
        return eagerResolveOfLightClasses.getValue((Object) INSTANCE, $$delegatedProperties[16]);
    }

    @JvmStatic
    public static /* synthetic */ void getEagerResolveOfLightClasses$annotations() {
    }

    public static final AnalysisFlag<Boolean> getExpandTypeAliasesInTypeResolution() {
        return expandTypeAliasesInTypeResolution.getValue((Object) INSTANCE, $$delegatedProperties[19]);
    }

    @JvmStatic
    public static /* synthetic */ void getExpandTypeAliasesInTypeResolution$annotations() {
    }

    public static final AnalysisFlag<ExplicitApiMode> getExplicitApiMode() {
        return explicitApiMode.getValue((Object) INSTANCE, $$delegatedProperties[8]);
    }

    @JvmStatic
    public static /* synthetic */ void getExplicitApiMode$annotations() {
    }

    public static final AnalysisFlag<Boolean> getExplicitApiVersion() {
        return explicitApiVersion.getValue((Object) INSTANCE, $$delegatedProperties[6]);
    }

    @JvmStatic
    public static /* synthetic */ void getExplicitApiVersion$annotations() {
    }

    public static final AnalysisFlag<ExplicitApiMode> getExplicitReturnTypes() {
        return explicitReturnTypes.getValue((Object) INSTANCE, $$delegatedProperties[9]);
    }

    @JvmStatic
    public static /* synthetic */ void getExplicitReturnTypes$annotations() {
    }

    public static final AnalysisFlag<Boolean> getIdeMode() {
        return ideMode.getValue((Object) INSTANCE, $$delegatedProperties[10]);
    }

    @JvmStatic
    public static /* synthetic */ void getIdeMode$annotations() {
    }

    public static final AnalysisFlag<Boolean> getIgnoreDataFlowInAssert() {
        return ignoreDataFlowInAssert.getValue((Object) INSTANCE, $$delegatedProperties[7]);
    }

    @JvmStatic
    public static /* synthetic */ void getIgnoreDataFlowInAssert$annotations() {
    }

    public static final AnalysisFlag<Boolean> getLibraryToSourceAnalysis() {
        return libraryToSourceAnalysis.getValue((Object) INSTANCE, $$delegatedProperties[12]);
    }

    @JvmStatic
    public static /* synthetic */ void getLibraryToSourceAnalysis$annotations() {
    }

    public static final AnalysisFlag<Boolean> getMetadataCompilation() {
        return metadataCompilation.getValue((Object) INSTANCE, $$delegatedProperties[1]);
    }

    @JvmStatic
    public static /* synthetic */ void getMetadataCompilation$annotations() {
    }

    public static final AnalysisFlag<Boolean> getMultiPlatformDoNotCheckActual() {
        return multiPlatformDoNotCheckActual.getValue((Object) INSTANCE, $$delegatedProperties[3]);
    }

    @JvmStatic
    public static /* synthetic */ void getMultiPlatformDoNotCheckActual$annotations() {
    }

    public static final AnalysisFlag<Boolean> getMuteExpectActualClassesWarning() {
        return muteExpectActualClassesWarning.getValue((Object) INSTANCE, $$delegatedProperties[14]);
    }

    @JvmStatic
    public static /* synthetic */ void getMuteExpectActualClassesWarning$annotations() {
    }

    public static final AnalysisFlag<List<String>> getOptIn() {
        return optIn.getValue((Object) INSTANCE, $$delegatedProperties[5]);
    }

    @JvmStatic
    public static /* synthetic */ void getOptIn$annotations() {
    }

    public static final AnalysisFlag<ReturnValueCheckerMode> getReturnValueCheckerMode() {
        return returnValueCheckerMode.getValue((Object) INSTANCE, $$delegatedProperties[21]);
    }

    @JvmStatic
    public static /* synthetic */ void getReturnValueCheckerMode$annotations() {
    }

    public static final AnalysisFlag<Boolean> getSkipExpectedActualDeclarationChecker() {
        return skipExpectedActualDeclarationChecker.getValue((Object) INSTANCE, $$delegatedProperties[4]);
    }

    @JvmStatic
    public static /* synthetic */ void getSkipExpectedActualDeclarationChecker$annotations() {
    }

    public static final AnalysisFlag<Boolean> getSkipMetadataVersionCheck() {
        return skipMetadataVersionCheck.getValue((Object) INSTANCE, $$delegatedProperties[0]);
    }

    @JvmStatic
    public static /* synthetic */ void getSkipMetadataVersionCheck$annotations() {
    }

    public static final AnalysisFlag<Boolean> getSkipPrereleaseCheck() {
        return skipPrereleaseCheck.getValue((Object) INSTANCE, $$delegatedProperties[2]);
    }

    @JvmStatic
    public static /* synthetic */ void getSkipPrereleaseCheck$annotations() {
    }

    public static final AnalysisFlag<Boolean> getStdlibCompilation() {
        return stdlibCompilation.getValue((Object) INSTANCE, $$delegatedProperties[18]);
    }

    @JvmStatic
    public static /* synthetic */ void getStdlibCompilation$annotations() {
    }

    public final AnalysisFlag<Boolean> getHeaderMode() {
        return headerMode.getValue((Object) this, $$delegatedProperties[24]);
    }

    public final AnalysisFlag<HeaderMode> getHeaderModeType() {
        return headerModeType.getValue((Object) this, $$delegatedProperties[25]);
    }

    public final AnalysisFlag<Boolean> getHierarchicalMultiplatformCompilation() {
        return hierarchicalMultiplatformCompilation.getValue((Object) this, $$delegatedProperties[23]);
    }

    public final AnalysisFlag<Boolean> getLenientMode() {
        return lenientMode.getValue((Object) this, $$delegatedProperties[22]);
    }

    public final AnalysisFlag<Map<String, WarningLevel>> getWarningLevels() {
        return warningLevels.getValue((Object) this, $$delegatedProperties[20]);
    }
}
