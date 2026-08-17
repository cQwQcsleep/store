package org.jetbrains.kotlin.fir.session;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.JvmAnalysisFlags;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.LanguageVersionSettingsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.KtRegisteredDiagnosticFactoriesStorage;
import org.jetbrains.kotlin.fir.FirCliExceptionHandler;
import org.jetbrains.kotlin.fir.FirDeclarationNameInvalidCharsProvider;
import org.jetbrains.kotlin.fir.FirEnumWhenTrackerComponent;
import org.jetbrains.kotlin.fir.FirExceptionHandler;
import org.jetbrains.kotlin.fir.FirExpectActualMatchingContextFactory;
import org.jetbrains.kotlin.fir.FirImportTrackerComponent;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponent;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirLookupTrackerComponent;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirModuleVisibilityChecker;
import org.jetbrains.kotlin.fir.FirNameConflictsTracker;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.FirVisibilityChecker;
import org.jetbrains.kotlin.fir.IncrementalPassThroughLookupTrackerComponent;
import org.jetbrains.kotlin.fir.analysis.CheckersComponent;
import org.jetbrains.kotlin.fir.analysis.FirOverridesBackwardCompatibilityHelper;
import org.jetbrains.kotlin.fir.analysis.checkers.FirInlineCheckerPlatformSpecificComponent;
import org.jetbrains.kotlin.fir.analysis.checkers.FirPlatformSpecificCastChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.FirPlatformSpecificEqualityChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.FirPlatformUpperBoundsProvider;
import org.jetbrains.kotlin.fir.analysis.checkers.FirPrimaryConstructorSuperTypeCheckerPlatformComponent;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirNameConflictsTrackerImpl;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirGenericArrayClassLiteralSupport;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirRegisteredDiagnosticFactoriesStorage;
import org.jetbrains.kotlin.fir.analysis.jvm.FirJvmOverridesBackwardCompatibilityHelper;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.FirJavaNullabilityWarningUpperBoundsProvider;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.FirJvmAnnotationsPlatformSpecificSupportComponent;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.FirJvmInlineCheckerComponent;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.FirJvmPrimaryConstructorSuperTypeCheckerPlatformComponent;
import org.jetbrains.kotlin.fir.caches.FirCachesFactory;
import org.jetbrains.kotlin.fir.caches.FirThreadUnsafeCachesFactory;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationsPlatformSpecificSupportComponent;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOverloadabilityHelper;
import org.jetbrains.kotlin.fir.declarations.FirExpectActualMappingStorage;
import org.jetbrains.kotlin.fir.declarations.FirHiddenDeprecationProvider;
import org.jetbrains.kotlin.fir.declarations.FirMustUseReturnValueStatusComponent;
import org.jetbrains.kotlin.fir.declarations.FirTypeSpecificityComparatorProvider;
import org.jetbrains.kotlin.fir.declarations.SealedClassInheritorsProvider;
import org.jetbrains.kotlin.fir.declarations.SealedClassInheritorsProviderImpl;
import org.jetbrains.kotlin.fir.deserialization.FirDeserializationExtension;
import org.jetbrains.kotlin.fir.expressions.FirInlineConstTrackerComponent;
import org.jetbrains.kotlin.fir.extensions.FirExtensionService;
import org.jetbrains.kotlin.fir.extensions.FirPredicateBasedProvider;
import org.jetbrains.kotlin.fir.extensions.FirPredicateBasedProviderImpl;
import org.jetbrains.kotlin.fir.extensions.FirRegisteredPluginAnnotations;
import org.jetbrains.kotlin.fir.extensions.FirRegisteredPluginAnnotationsImpl;
import org.jetbrains.kotlin.fir.java.FirJavaVisibilityChecker;
import org.jetbrains.kotlin.fir.java.FirSyntheticPropertiesStorage;
import org.jetbrains.kotlin.fir.java.JvmSupertypeUpdater;
import org.jetbrains.kotlin.fir.java.deserialization.FirJvmDeserializationExtension;
import org.jetbrains.kotlin.fir.java.enhancement.FirAnnotationTypeQualifierResolver;
import org.jetbrains.kotlin.fir.java.enhancement.FirEnhancedSymbolsStorage;
import org.jetbrains.kotlin.fir.java.scopes.FirRenamedForOverrideSymbolsStorage;
import org.jetbrains.kotlin.fir.java.scopes.JavaOverridabilityRules;
import org.jetbrains.kotlin.fir.modules.FirJavaModuleResolverProvider;
import org.jetbrains.kotlin.fir.resolve.FirDefaultParametersResolver;
import org.jetbrains.kotlin.fir.resolve.FirJavaClassMapper;
import org.jetbrains.kotlin.fir.resolve.FirJavaSyntheticNamesProvider;
import org.jetbrains.kotlin.fir.resolve.FirJvmDefaultImportsProvider;
import org.jetbrains.kotlin.fir.resolve.FirQualifierResolver;
import org.jetbrains.kotlin.fir.resolve.FirSamConstructorStorage;
import org.jetbrains.kotlin.fir.resolve.FirTypeResolver;
import org.jetbrains.kotlin.fir.resolve.calls.FirSyntheticNamesProvider;
import org.jetbrains.kotlin.fir.resolve.calls.jvm.JvmCallConflictResolverFactory;
import org.jetbrains.kotlin.fir.resolve.calls.overloads.ConeCallConflictResolverFactory;
import org.jetbrains.kotlin.fir.resolve.calls.overloads.FirDeclarationOverloadabilityHelperImpl;
import org.jetbrains.kotlin.fir.resolve.inference.InferenceComponents;
import org.jetbrains.kotlin.fir.resolve.providers.impl.FirQualifierResolverImpl;
import org.jetbrains.kotlin.fir.resolve.providers.impl.FirTypeResolverImpl;
import org.jetbrains.kotlin.fir.resolve.transformers.FirCliJumpingPhaseComputationSessionForLocalClassesProvider;
import org.jetbrains.kotlin.fir.resolve.transformers.FirDummyCompilerLazyDeclarationResolver;
import org.jetbrains.kotlin.fir.resolve.transformers.FirJumpingPhaseComputationSessionForLocalClassesProvider;
import org.jetbrains.kotlin.fir.resolve.transformers.PlatformSupertypeUpdater;
import org.jetbrains.kotlin.fir.resolve.transformers.mpp.FirExpectActualMatchingContextImpl;
import org.jetbrains.kotlin.fir.scopes.FirDefaultImportsProviderHolder;
import org.jetbrains.kotlin.fir.scopes.FirLookupDefaultStarImportsInSourcesSettingHolder;
import org.jetbrains.kotlin.fir.scopes.FirOverrideChecker;
import org.jetbrains.kotlin.fir.scopes.FirOverrideService;
import org.jetbrains.kotlin.fir.scopes.FirPlatformClassMapper;
import org.jetbrains.kotlin.fir.scopes.PlatformSpecificOverridabilityRules;
import org.jetbrains.kotlin.fir.scopes.SubstitutionScopeKeyFactory;
import org.jetbrains.kotlin.fir.scopes.impl.FirDeclaredMemberScopeProvider;
import org.jetbrains.kotlin.fir.scopes.impl.FirDelegatedMembersFilter;
import org.jetbrains.kotlin.fir.scopes.impl.FirDynamicMembersStorage;
import org.jetbrains.kotlin.fir.scopes.impl.FirEnumEntriesSupport;
import org.jetbrains.kotlin.fir.scopes.impl.FirGeneratedMemberDeclarationsStorage;
import org.jetbrains.kotlin.fir.scopes.impl.FirIntersectionOverrideStorage;
import org.jetbrains.kotlin.fir.scopes.impl.FirJvmEnumEntriesSupport;
import org.jetbrains.kotlin.fir.scopes.impl.FirStandardOverrideChecker;
import org.jetbrains.kotlin.fir.scopes.impl.FirSubstitutionOverrideStorage;
import org.jetbrains.kotlin.fir.scopes.impl.FirSynthesizedStorage;
import org.jetbrains.kotlin.fir.scopes.impl.FirTypealiasConstructorStorage;
import org.jetbrains.kotlin.fir.scopes.jvm.FirJvmDelegatedMembersFilter;
import org.jetbrains.kotlin.fir.scopes.jvm.JvmMappedScope;
import org.jetbrains.kotlin.fir.serialization.FirProvidedDeclarationsForMetadataService;
import org.jetbrains.kotlin.fir.session.ComponentsContainersKt;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolver;
import org.jetbrains.kotlin.fir.types.FirCorrespondingSupertypesCache;
import org.jetbrains.kotlin.fir.types.FirFunctionTypeKindService;
import org.jetbrains.kotlin.fir.types.FirFunctionTypeKindServiceImpl;
import org.jetbrains.kotlin.fir.types.FirMissingDependencyStorage;
import org.jetbrains.kotlin.fir.types.TypeComponents;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.incremental.components.EnumWhenTracker;
import org.jetbrains.kotlin.incremental.components.ICFileMappingTracker;
import org.jetbrains.kotlin.incremental.components.ImportTracker;
import org.jetbrains.kotlin.incremental.components.InlineConstTracker;
import org.jetbrains.kotlin.incremental.components.LookupTracker;
import org.jetbrains.kotlin.load.java.JavaTypeEnhancementState;
import org.jetbrains.kotlin.resolve.jvm.JvmConstants;
import org.jetbrains.kotlin.resolve.jvm.JvmTypeSpecificityComparator;
import org.jetbrains.kotlin.resolve.jvm.modules.JavaModuleResolver;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000^\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a\n\u0010\u0007\u001a\u00020\u0001*\u00020\u0002\u001a\u001a\u0010\f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a4\u0010\r\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00062\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u001aB\u0010\u0015\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f\u001a\n\u0010 \u001a\u00020\u0001*\u00020\u0002\u001a\u0012\u0010!\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\"\u001a\u00020#\"\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006$"}, d2 = {"registerCommonComponents", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/FirSession;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "isMetadataCompilation", Argument.Delimiters.none, "registerCommonComponentsAfterExtensionsAreConfigured", "firCachesFactoryForCliMode", "Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory;", "getFirCachesFactoryForCliMode", "()Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory;", "registerCliCompilerAndCommonComponents", "registerJavaComponents", "javaModuleResolver", "Lorg/jetbrains/kotlin/resolve/jvm/modules/JavaModuleResolver;", "predefinedComponents", "Lorg/jetbrains/kotlin/fir/session/FirSharableJavaComponents;", "registerJvmDeserializationExtension", "inlineConstTracker", "Lorg/jetbrains/kotlin/incremental/components/InlineConstTracker;", "registerResolveComponents", "diagnosticFactoriesStorage", "Lorg/jetbrains/kotlin/diagnostics/KtRegisteredDiagnosticFactoriesStorage;", "lookupTracker", "Lorg/jetbrains/kotlin/incremental/components/LookupTracker;", "enumWhenTracker", "Lorg/jetbrains/kotlin/incremental/components/EnumWhenTracker;", "importTracker", "Lorg/jetbrains/kotlin/incremental/components/ImportTracker;", "fileMappingTracker", "Lorg/jetbrains/kotlin/incremental/components/ICFileMappingTracker;", "registerCliCompilerOnlyResolveComponents", "registerModuleData", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "org.jetbrains.kotlin:entrypoint"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ComponentsContainersKt {
    public static String a(FirSession firSession, KtSourceElement ktSourceElement) {
        ktSourceElement.getClass();
        return SourcesToPathsMapperKt.getSourcesToPathsMapper(firSession).getSourceFilePath(ktSourceElement);
    }

    public static final FirCachesFactory getFirCachesFactoryForCliMode() {
        return FirThreadUnsafeCachesFactory.INSTANCE;
    }

    public static final void registerCliCompilerAndCommonComponents(FirSession firSession, LanguageVersionSettings languageVersionSettings, boolean z) {
        firSession.getClass();
        languageVersionSettings.getClass();
        firSession.register(Reflection.getOrCreateKotlinClass(FirCachesFactory.class), getFirCachesFactoryForCliMode());
        registerCommonComponents(firSession, languageVersionSettings, z);
        firSession.register(Reflection.getOrCreateKotlinClass(SealedClassInheritorsProvider.class), SealedClassInheritorsProviderImpl.INSTANCE);
        firSession.register(Reflection.getOrCreateKotlinClass(FirLazyDeclarationResolver.class), FirDummyCompilerLazyDeclarationResolver.INSTANCE);
        firSession.register(Reflection.getOrCreateKotlinClass(FirExceptionHandler.class), FirCliExceptionHandler.INSTANCE);
        firSession.register(Reflection.getOrCreateKotlinClass(FirLookupDefaultStarImportsInSourcesSettingHolder.class), FirLookupDefaultStarImportsInSourcesSettingHolder.INSTANCE.createDefault(languageVersionSettings));
        firSession.register(Reflection.getOrCreateKotlinClass(FirRegisteredPluginAnnotations.class), new FirRegisteredPluginAnnotationsImpl(firSession));
        firSession.register(Reflection.getOrCreateKotlinClass(FirPredicateBasedProvider.class), new FirPredicateBasedProviderImpl(firSession));
        firSession.register(Reflection.getOrCreateKotlinClass(FirHiddenDeprecationProvider.class), new FirHiddenDeprecationProvider(firSession));
        firSession.register(Reflection.getOrCreateKotlinClass(FirJumpingPhaseComputationSessionForLocalClassesProvider.class), FirCliJumpingPhaseComputationSessionForLocalClassesProvider.INSTANCE);
        firSession.register(Reflection.getOrCreateKotlinClass(SubstitutionScopeKeyFactory.class), SubstitutionScopeKeyFactory.Default.INSTANCE);
    }

    public static final void registerCliCompilerOnlyResolveComponents(FirSession firSession) {
        firSession.getClass();
        firSession.register(Reflection.getOrCreateKotlinClass(FirNameConflictsTracker.class), new FirNameConflictsTrackerImpl());
        firSession.register(Reflection.getOrCreateKotlinClass(CheckersComponent.class), new CheckersComponent());
    }

    public static final void registerCommonComponents(FirSession firSession, LanguageVersionSettings languageVersionSettings, boolean z) {
        firSession.getClass();
        languageVersionSettings.getClass();
        firSession.register(Reflection.getOrCreateKotlinClass(FirLanguageSettingsComponent.class), new FirLanguageSettingsComponent(languageVersionSettings, z));
        firSession.register(Reflection.getOrCreateKotlinClass(TypeComponents.class), (FirSessionComponent) new TypeComponents(firSession));
        firSession.register(Reflection.getOrCreateKotlinClass(InferenceComponents.class), new InferenceComponents(firSession));
        firSession.register(Reflection.getOrCreateKotlinClass(FirDeclaredMemberScopeProvider.class), new FirDeclaredMemberScopeProvider(firSession));
        firSession.register(Reflection.getOrCreateKotlinClass(FirCorrespondingSupertypesCache.class), new FirCorrespondingSupertypesCache(firSession));
        firSession.register(Reflection.getOrCreateKotlinClass(FirDefaultParametersResolver.class), new FirDefaultParametersResolver());
        firSession.register(Reflection.getOrCreateKotlinClass(FirExtensionService.class), new FirExtensionService(firSession));
        firSession.register(Reflection.getOrCreateKotlinClass(FirSubstitutionOverrideStorage.class), new FirSubstitutionOverrideStorage(firSession));
        firSession.register(Reflection.getOrCreateKotlinClass(FirIntersectionOverrideStorage.class), new FirIntersectionOverrideStorage(firSession));
        firSession.register(Reflection.getOrCreateKotlinClass(FirTypealiasConstructorStorage.class), new FirTypealiasConstructorStorage(firSession));
        firSession.register(Reflection.getOrCreateKotlinClass(FirSynthesizedStorage.class), new FirSynthesizedStorage(firSession));
        firSession.register(Reflection.getOrCreateKotlinClass(FirGeneratedMemberDeclarationsStorage.class), new FirGeneratedMemberDeclarationsStorage(firSession));
        firSession.register(Reflection.getOrCreateKotlinClass(FirSamConstructorStorage.class), new FirSamConstructorStorage(firSession));
        firSession.register(Reflection.getOrCreateKotlinClass(FirOverrideService.class), new FirOverrideService(firSession));
        firSession.register(Reflection.getOrCreateKotlinClass(FirDynamicMembersStorage.class), new FirDynamicMembersStorage(firSession));
        firSession.register(Reflection.getOrCreateKotlinClass(FirOverrideChecker.class), new FirStandardOverrideChecker(firSession));
        firSession.register(Reflection.getOrCreateKotlinClass(FirDeclarationOverloadabilityHelper.class), new FirDeclarationOverloadabilityHelperImpl(firSession));
        firSession.register((KClass<? extends FirAnnotationsPlatformSpecificSupportComponent.Default>) Reflection.getOrCreateKotlinClass(FirAnnotationsPlatformSpecificSupportComponent.class), FirAnnotationsPlatformSpecificSupportComponent.Default.INSTANCE);
        firSession.register((KClass<? extends FirPrimaryConstructorSuperTypeCheckerPlatformComponent.Default>) Reflection.getOrCreateKotlinClass(FirPrimaryConstructorSuperTypeCheckerPlatformComponent.class), FirPrimaryConstructorSuperTypeCheckerPlatformComponent.Default.INSTANCE);
        firSession.register(Reflection.getOrCreateKotlinClass(FirGenericArrayClassLiteralSupport.class), FirGenericArrayClassLiteralSupport.Disabled.INSTANCE);
        firSession.register(Reflection.getOrCreateKotlinClass(FirMissingDependencyStorage.class), new FirMissingDependencyStorage(firSession));
        firSession.register(Reflection.getOrCreateKotlinClass(FirPlatformSpecificCastChecker.class), FirPlatformSpecificCastChecker.Default.INSTANCE);
        firSession.register(Reflection.getOrCreateKotlinClass(FirPlatformSpecificEqualityChecker.class), FirPlatformSpecificEqualityChecker.Default.INSTANCE);
        firSession.register(Reflection.getOrCreateKotlinClass(FirMustUseReturnValueStatusComponent.class), FirMustUseReturnValueStatusComponent.INSTANCE.create(languageVersionSettings));
        firSession.register((KClass<? extends FirInlineCheckerPlatformSpecificComponent.NonJvmDefault>) Reflection.getOrCreateKotlinClass(FirInlineCheckerPlatformSpecificComponent.class), FirInlineCheckerPlatformSpecificComponent.NonJvmDefault.INSTANCE);
        firSession.register(Reflection.getOrCreateKotlinClass(FirExpectActualMappingStorage.class), new FirExpectActualMappingStorage(firSession));
        firSession.register(Reflection.getOrCreateKotlinClass(FirInlineConstTrackerComponent.class), FirInlineConstTrackerComponent.Default.INSTANCE);
    }

    public static final void registerCommonComponentsAfterExtensionsAreConfigured(FirSession firSession) {
        firSession.getClass();
        firSession.register(Reflection.getOrCreateKotlinClass(FirFunctionTypeKindService.class), new FirFunctionTypeKindServiceImpl(firSession));
        firSession.register(Reflection.getOrCreateKotlinClass(FirProvidedDeclarationsForMetadataService.class), FirProvidedDeclarationsForMetadataService.INSTANCE.create(firSession));
    }

    public static final void registerJavaComponents(FirSession firSession, JavaModuleResolver javaModuleResolver, FirSharableJavaComponents firSharableJavaComponents, boolean z, InlineConstTracker inlineConstTracker) {
        FirEnhancedSymbolsStorage firEnhancedSymbolsStorage;
        JvmMappedScope.FirMappedSymbolStorage firMappedSymbolStorage;
        FirRenamedForOverrideSymbolsStorage firRenamedForOverrideSymbolsStorage;
        firSession.getClass();
        javaModuleResolver.getClass();
        firSession.register(Reflection.getOrCreateKotlinClass(FirJavaModuleResolverProvider.class), new FirJavaModuleResolverProvider(javaModuleResolver));
        JavaTypeEnhancementState javaTypeEnhancementState = (JavaTypeEnhancementState) FirLanguageSettingsComponentKt.getLanguageVersionSettings(firSession).getFlag(JvmAnalysisFlags.getJavaTypeEnhancementState());
        if (javaTypeEnhancementState == null) {
            javaTypeEnhancementState = JavaTypeEnhancementState.Companion.getDefault(LanguageVersionSettingsKt.toKotlinVersion(FirLanguageSettingsComponentKt.getLanguageVersionSettings(firSession)));
        }
        firSession.register(Reflection.getOrCreateKotlinClass(FirAnnotationTypeQualifierResolver.class), new FirAnnotationTypeQualifierResolver(firSession, javaTypeEnhancementState, javaModuleResolver));
        KClass<? extends FirSessionComponent> orCreateKotlinClass = Reflection.getOrCreateKotlinClass(FirEnhancedSymbolsStorage.class);
        if (firSharableJavaComponents == null || (firEnhancedSymbolsStorage = firSharableJavaComponents.getEnhancementStorage()) == null) {
            firEnhancedSymbolsStorage = new FirEnhancedSymbolsStorage(firSession);
        }
        firSession.register(orCreateKotlinClass, firEnhancedSymbolsStorage);
        KClass<? extends FirSessionComponent> orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(JvmMappedScope.FirMappedSymbolStorage.class);
        if (firSharableJavaComponents == null || (firMappedSymbolStorage = firSharableJavaComponents.getMappedStorage()) == null) {
            firMappedSymbolStorage = new JvmMappedScope.FirMappedSymbolStorage(firSession);
        }
        firSession.register(orCreateKotlinClass2, firMappedSymbolStorage);
        KClass<? extends FirSessionComponent> orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(FirRenamedForOverrideSymbolsStorage.class);
        if (firSharableJavaComponents == null || (firRenamedForOverrideSymbolsStorage = firSharableJavaComponents.getRenamedFunctionsStorage()) == null) {
            firRenamedForOverrideSymbolsStorage = new FirRenamedForOverrideSymbolsStorage(firSession);
        }
        firSession.register(orCreateKotlinClass3, firRenamedForOverrideSymbolsStorage);
        firSession.register(Reflection.getOrCreateKotlinClass(FirSyntheticPropertiesStorage.class), new FirSyntheticPropertiesStorage(firSession));
        firSession.register(Reflection.getOrCreateKotlinClass(PlatformSupertypeUpdater.class), new JvmSupertypeUpdater(firSession));
        firSession.register((KClass<? extends JavaOverridabilityRules>) Reflection.getOrCreateKotlinClass(PlatformSpecificOverridabilityRules.class), new JavaOverridabilityRules(firSession));
        if (z) {
            firSession.register((KClass<? extends FirJvmDeserializationExtension>) Reflection.getOrCreateKotlinClass(FirDeserializationExtension.class), new FirJvmDeserializationExtension(firSession));
        }
        firSession.register((KClass<? extends FirJvmEnumEntriesSupport>) Reflection.getOrCreateKotlinClass(FirEnumEntriesSupport.class), new FirJvmEnumEntriesSupport(firSession));
        firSession.register((KClass<? extends FirJvmAnnotationsPlatformSpecificSupportComponent>) Reflection.getOrCreateKotlinClass(FirAnnotationsPlatformSpecificSupportComponent.class), FirJvmAnnotationsPlatformSpecificSupportComponent.INSTANCE);
        firSession.register((KClass<? extends FirJvmPrimaryConstructorSuperTypeCheckerPlatformComponent>) Reflection.getOrCreateKotlinClass(FirPrimaryConstructorSuperTypeCheckerPlatformComponent.class), FirJvmPrimaryConstructorSuperTypeCheckerPlatformComponent.INSTANCE);
        firSession.register((KClass<? extends FirJavaVisibilityChecker>) Reflection.getOrCreateKotlinClass(FirVisibilityChecker.class), FirJavaVisibilityChecker.INSTANCE);
        firSession.register((KClass<? extends JvmCallConflictResolverFactory>) Reflection.getOrCreateKotlinClass(ConeCallConflictResolverFactory.class), JvmCallConflictResolverFactory.INSTANCE);
        firSession.register((KClass<? extends FirTypeSpecificityComparatorProvider>) Reflection.getOrCreateKotlinClass(FirTypeSpecificityComparatorProvider.class), FirTypeSpecificityComparatorProvider.INSTANCE.of(new JvmTypeSpecificityComparator(TypeComponentsKt.getTypeContext(firSession), FirLanguageSettingsComponentKt.getLanguageVersionSettings(firSession))));
        firSession.register((KClass<? extends FirJavaClassMapper>) Reflection.getOrCreateKotlinClass(FirPlatformClassMapper.class), new FirJavaClassMapper(firSession));
        firSession.register(Reflection.getOrCreateKotlinClass(FirSyntheticNamesProvider.class), FirJavaSyntheticNamesProvider.INSTANCE);
        firSession.register((KClass<? extends FirJvmOverridesBackwardCompatibilityHelper>) Reflection.getOrCreateKotlinClass(FirOverridesBackwardCompatibilityHelper.class), FirJvmOverridesBackwardCompatibilityHelper.INSTANCE);
        firSession.register((KClass<? extends FirJvmInlineCheckerComponent>) Reflection.getOrCreateKotlinClass(FirInlineCheckerPlatformSpecificComponent.class), new FirJvmInlineCheckerComponent());
        firSession.register(Reflection.getOrCreateKotlinClass(FirGenericArrayClassLiteralSupport.class), FirGenericArrayClassLiteralSupport.Enabled.INSTANCE);
        firSession.register((KClass<? extends FirJvmDelegatedMembersFilter>) Reflection.getOrCreateKotlinClass(FirDelegatedMembersFilter.class), new FirJvmDelegatedMembersFilter(firSession));
        firSession.register((KClass<? extends FirJavaNullabilityWarningUpperBoundsProvider>) Reflection.getOrCreateKotlinClass(FirPlatformUpperBoundsProvider.class), new FirJavaNullabilityWarningUpperBoundsProvider(firSession));
        firSession.register((KClass<? extends FirDefaultImportsProviderHolder>) Reflection.getOrCreateKotlinClass(FirDefaultImportsProviderHolder.class), FirDefaultImportsProviderHolder.INSTANCE.of(FirJvmDefaultImportsProvider.INSTANCE));
        firSession.register((KClass<? extends FirDeclarationNameInvalidCharsProvider>) Reflection.getOrCreateKotlinClass(FirDeclarationNameInvalidCharsProvider.class), FirDeclarationNameInvalidCharsProvider.INSTANCE.of(JvmConstants.INSTANCE.getINVALID_CHARS()));
        firSession.register(Reflection.getOrCreateKotlinClass(FirInlineConstTrackerComponent.class), new FirInlineConstTrackerComponent(inlineConstTracker));
    }

    public static /* synthetic */ void registerJavaComponents$default(FirSession firSession, JavaModuleResolver javaModuleResolver, FirSharableJavaComponents firSharableJavaComponents, boolean z, InlineConstTracker inlineConstTracker, int i, Object obj) {
        if ((i & 2) != 0) {
            firSharableJavaComponents = null;
        }
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            inlineConstTracker = null;
        }
        registerJavaComponents(firSession, javaModuleResolver, firSharableJavaComponents, z, inlineConstTracker);
    }

    public static final void registerModuleData(FirSession firSession, FirModuleData firModuleData) {
        firSession.getClass();
        firModuleData.getClass();
        firSession.register(Reflection.getOrCreateKotlinClass(FirModuleData.class), firModuleData);
    }

    public static final void registerResolveComponents(final FirSession firSession, KtRegisteredDiagnosticFactoriesStorage ktRegisteredDiagnosticFactoriesStorage, LookupTracker lookupTracker, EnumWhenTracker enumWhenTracker, ImportTracker importTracker, ICFileMappingTracker iCFileMappingTracker) {
        firSession.getClass();
        ktRegisteredDiagnosticFactoriesStorage.getClass();
        firSession.register(Reflection.getOrCreateKotlinClass(FirQualifierResolver.class), new FirQualifierResolverImpl(firSession));
        firSession.register(Reflection.getOrCreateKotlinClass(FirTypeResolver.class), new FirTypeResolverImpl(firSession));
        firSession.register(Reflection.getOrCreateKotlinClass(FirModuleVisibilityChecker.class), new FirModuleVisibilityChecker.Standard(firSession));
        firSession.register(Reflection.getOrCreateKotlinClass(SourcesToPathsMapper.class), new SourcesToPathsMapper());
        if (lookupTracker != null) {
            firSession.register(Reflection.getOrCreateKotlinClass(FirLookupTrackerComponent.class), new IncrementalPassThroughLookupTrackerComponent(firSession, lookupTracker, iCFileMappingTracker, new Function1() { // from class: lb2
                public final Object invoke(Object obj) {
                    return ComponentsContainersKt.a(firSession, (KtSourceElement) obj);
                }
            }));
        }
        if (enumWhenTracker != null) {
            firSession.register(Reflection.getOrCreateKotlinClass(FirEnumWhenTrackerComponent.class), new IncrementalPassThroughEnumWhenTrackerComponent(enumWhenTracker));
        }
        if (importTracker != null) {
            firSession.register(Reflection.getOrCreateKotlinClass(FirImportTrackerComponent.class), new IncrementalPassThroughImportTrackerComponent(importTracker));
        }
        firSession.register(Reflection.getOrCreateKotlinClass(FirExpectActualMatchingContextFactory.class), FirExpectActualMatchingContextImpl.Factory.INSTANCE);
        firSession.register(Reflection.getOrCreateKotlinClass(FirRegisteredDiagnosticFactoriesStorage.class), new FirRegisteredDiagnosticFactoriesStorage(ktRegisteredDiagnosticFactoriesStorage));
    }

    public static /* synthetic */ void registerResolveComponents$default(FirSession firSession, KtRegisteredDiagnosticFactoriesStorage ktRegisteredDiagnosticFactoriesStorage, LookupTracker lookupTracker, EnumWhenTracker enumWhenTracker, ImportTracker importTracker, ICFileMappingTracker iCFileMappingTracker, int i, Object obj) {
        if ((i & 2) != 0) {
            lookupTracker = null;
        }
        if ((i & 4) != 0) {
            enumWhenTracker = null;
        }
        if ((i & 8) != 0) {
            importTracker = null;
        }
        if ((i & 16) != 0) {
            iCFileMappingTracker = null;
        }
        registerResolveComponents(firSession, ktRegisteredDiagnosticFactoriesStorage, lookupTracker, enumWhenTracker, importTracker, iCFileMappingTracker);
    }
}
