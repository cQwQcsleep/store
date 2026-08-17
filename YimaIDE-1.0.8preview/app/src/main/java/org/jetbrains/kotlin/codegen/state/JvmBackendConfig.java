package org.jetbrains.kotlin.codegen.state;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.CodegenUtilKt;
import org.jetbrains.kotlin.config.ApiVersion;
import org.jetbrains.kotlin.config.CommonConfigurationKeys;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.JVMAssertionsMode;
import org.jetbrains.kotlin.config.JVMConfigurationKeys;
import org.jetbrains.kotlin.config.JvmAbiStability;
import org.jetbrains.kotlin.config.JvmAnalysisFlags;
import org.jetbrains.kotlin.config.JvmClosureGenerationScheme;
import org.jetbrains.kotlin.config.JvmDefaultMode;
import org.jetbrains.kotlin.config.JvmDefaultModeKt;
import org.jetbrains.kotlin.config.JvmStringConcat;
import org.jetbrains.kotlin.config.JvmTarget;
import org.jetbrains.kotlin.config.JvmWhenGenerationScheme;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.LanguageVersion;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.metadata.deserialization.BinaryVersion;
import org.jetbrains.kotlin.util.MetadataHelpersKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0016\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u001a\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0011\u0010\u001c\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0011R\u0011\u0010\u001e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0011R\u0011\u0010 \u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0011R\u0011\u0010!\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0011R\u0011\u0010\"\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0011R\u0011\u0010#\u001a\u00020$¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010'\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0011R\u0011\u0010(\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0011R\u0011\u0010*\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0011R\u0011\u0010,\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0011R\u0011\u0010.\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u0011R\u0011\u00100\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u0011R\u0011\u00102\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u0011R\u0011\u00104\u001a\u000205¢\u0006\b\n\u0000\u001a\u0004\b6\u00107R\u0011\u00108\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b9\u0010\u0011R\u0011\u0010:\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b;\u0010\u0011R\u0011\u0010<\u001a\u00020=¢\u0006\b\n\u0000\u001a\u0004\b>\u0010?R\u0011\u0010@\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\bA\u0010\u0011R\u0011\u0010B\u001a\u00020C¢\u0006\b\n\u0000\u001a\u0004\bD\u0010ER\u0013\u0010F\u001a\u0004\u0018\u00010G¢\u0006\b\n\u0000\u001a\u0004\bH\u0010IR\u0011\u0010J\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\bK\u0010\u0011R\u0011\u0010L\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\bM\u0010\u0011R\u0011\u0010N\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\bO\u0010\u0011R\u0011\u0010P\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\bQ\u0010\u0011R\u0011\u0010R\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\bS\u0010\u0011R\u0011\u0010T\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\bU\u0010\u0011R\u0011\u0010V\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\bW\u0010\u0011R\u0011\u0010X\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\bY\u0010\u0011R\u0011\u0010Z\u001a\u00020[¢\u0006\b\n\u0000\u001a\u0004\b\\\u0010]R\u0011\u0010^\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b_\u0010\u0011R\u0011\u0010`\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\ba\u0010\u0011¨\u0006b"}, d2 = {"Lorg/jetbrains/kotlin/codegen/state/JvmBackendConfig;", Argument.Delimiters.none, "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "<init>", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)V", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "getLanguageVersionSettings", "()Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "target", "Lorg/jetbrains/kotlin/config/JvmTarget;", "getTarget", "()Lorg/jetbrains/kotlin/config/JvmTarget;", "useOldManglingSchemeForFunctionsWithInlineClassesInSignatures", Argument.Delimiters.none, "getUseOldManglingSchemeForFunctionsWithInlineClassesInSignatures", "()Z", "runtimeStringConcat", "Lorg/jetbrains/kotlin/config/JvmStringConcat;", "getRuntimeStringConcat", "()Lorg/jetbrains/kotlin/config/JvmStringConcat;", "samConversionsScheme", "Lorg/jetbrains/kotlin/config/JvmClosureGenerationScheme;", "getSamConversionsScheme", "()Lorg/jetbrains/kotlin/config/JvmClosureGenerationScheme;", "lambdasScheme", "getLambdasScheme", "indyAllowAnnotatedLambdas", "getIndyAllowAnnotatedLambdas", "stableTypeOf", "getStableTypeOf", "isCallAssertionsDisabled", "isReceiverAssertionsDisabled", "isParamAssertionsDisabled", "assertionsMode", "Lorg/jetbrains/kotlin/config/JVMAssertionsMode;", "getAssertionsMode", "()Lorg/jetbrains/kotlin/config/JVMAssertionsMode;", "isInlineDisabled", "useTypeTableInSerializer", "getUseTypeTableInSerializer", "unifiedNullChecks", "getUnifiedNullChecks", "noSourceCodeInNotNullAssertionExceptions", "getNoSourceCodeInNotNullAssertionExceptions", "generateSmapCopyToAnnotation", "getGenerateSmapCopyToAnnotation", "functionsWithInlineClassReturnTypesMangled", "getFunctionsWithInlineClassReturnTypesMangled", "shouldValidateBytecode", "getShouldValidateBytecode", "classFileVersion", Argument.Delimiters.none, "getClassFileVersion", "()I", "generateParametersMetadata", "getGenerateParametersMetadata", "shouldInlineConstVals", "getShouldInlineConstVals", "jvmDefaultMode", "Lorg/jetbrains/kotlin/config/JvmDefaultMode;", "getJvmDefaultMode", "()Lorg/jetbrains/kotlin/config/JvmDefaultMode;", "disableOptimization", "getDisableOptimization", "metadataVersion", "Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "getMetadataVersion", "()Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "abiStability", "Lorg/jetbrains/kotlin/config/JvmAbiStability;", "getAbiStability", "()Lorg/jetbrains/kotlin/config/JvmAbiStability;", "noNewJavaAnnotationTargets", "getNoNewJavaAnnotationTargets", "supportJvmInlineMultiFieldValueClasses", "getSupportJvmInlineMultiFieldValueClasses", "enableDebugMode", "getEnableDebugMode", "enhancedCoroutinesDebugging", "getEnhancedCoroutinesDebugging", "useFir", "getUseFir", "emitJvmTypeAnnotations", "getEmitJvmTypeAnnotations", "nullOutSpilledCoroutineLocalsUsingStdlibFunction", "getNullOutSpilledCoroutineLocalsUsingStdlibFunction", "wrapContinuationForTailCallFunctions", "getWrapContinuationForTailCallFunctions", "whenGenerationScheme", "Lorg/jetbrains/kotlin/config/JvmWhenGenerationScheme;", "getWhenGenerationScheme", "()Lorg/jetbrains/kotlin/config/JvmWhenGenerationScheme;", "generateDebugMetadataV2", "getGenerateDebugMetadataV2", "implicitJvmExposeBoxed", "getImplicitJvmExposeBoxed", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmBackendConfig {
    private final JvmAbiStability abiStability;
    private final JVMAssertionsMode assertionsMode;
    private final int classFileVersion;
    private final boolean disableOptimization;
    private final boolean emitJvmTypeAnnotations;
    private final boolean enableDebugMode;
    private final boolean enhancedCoroutinesDebugging;
    private final boolean functionsWithInlineClassReturnTypesMangled;
    private final boolean generateDebugMetadataV2;
    private final boolean generateParametersMetadata;
    private final boolean generateSmapCopyToAnnotation;
    private final boolean implicitJvmExposeBoxed;
    private final boolean indyAllowAnnotatedLambdas;
    private final boolean isCallAssertionsDisabled;
    private final boolean isInlineDisabled;
    private final boolean isParamAssertionsDisabled;
    private final boolean isReceiverAssertionsDisabled;
    private final JvmDefaultMode jvmDefaultMode;
    private final JvmClosureGenerationScheme lambdasScheme;
    private final LanguageVersionSettings languageVersionSettings;
    private final BinaryVersion metadataVersion;
    private final boolean noNewJavaAnnotationTargets;
    private final boolean noSourceCodeInNotNullAssertionExceptions;
    private final boolean nullOutSpilledCoroutineLocalsUsingStdlibFunction;
    private final JvmStringConcat runtimeStringConcat;
    private final JvmClosureGenerationScheme samConversionsScheme;
    private final boolean shouldInlineConstVals;
    private final boolean shouldValidateBytecode;
    private final boolean stableTypeOf;
    private final boolean supportJvmInlineMultiFieldValueClasses;
    private final JvmTarget target;
    private final boolean unifiedNullChecks;
    private final boolean useFir;
    private final boolean useOldManglingSchemeForFunctionsWithInlineClassesInSignatures;
    private final boolean useTypeTableInSerializer;
    private final JvmWhenGenerationScheme whenGenerationScheme;
    private final boolean wrapContinuationForTailCallFunctions;

    /* JADX WARN: Code duplicated, block: B:13:0x0038  */
    public JvmBackendConfig(CompilerConfiguration compilerConfiguration) {
        boolean z;
        JvmStringConcat jvmStringConcat;
        compilerConfiguration.getClass();
        LanguageVersionSettings languageVersionSettings = CommonConfigurationKeysKt.getLanguageVersionSettings(compilerConfiguration);
        this.languageVersionSettings = languageVersionSettings;
        JvmTarget jvmTarget = (JvmTarget) compilerConfiguration.get(JVMConfigurationKeys.JVM_TARGET);
        jvmTarget = jvmTarget == null ? JvmTarget.DEFAULT : jvmTarget;
        this.target = jvmTarget;
        if (compilerConfiguration.getBoolean(JVMConfigurationKeys.USE_OLD_INLINE_CLASSES_MANGLING_SCHEME)) {
            z = true;
        } else {
            LanguageVersion languageVersion = languageVersionSettings.getLanguageVersion();
            if (languageVersion.getMajor() != 1 || languageVersion.getMinor() >= 4) {
                z = false;
            } else {
                z = true;
            }
        }
        this.useOldManglingSchemeForFunctionsWithInlineClassesInSignatures = z;
        if (jvmTarget.getMajorVersion() >= JvmTarget.JVM_9.getMajorVersion()) {
            jvmStringConcat = (JvmStringConcat) compilerConfiguration.get(JVMConfigurationKeys.STRING_CONCAT);
            if (jvmStringConcat == null) {
                jvmStringConcat = JvmStringConcat.INDY_WITH_CONSTANTS;
            }
        } else {
            jvmStringConcat = JvmStringConcat.INLINE;
        }
        this.runtimeStringConcat = jvmStringConcat;
        JvmClosureGenerationScheme jvmClosureGenerationScheme = (JvmClosureGenerationScheme) compilerConfiguration.get(JVMConfigurationKeys.SAM_CONVERSIONS);
        if (jvmClosureGenerationScheme == null) {
            jvmClosureGenerationScheme = languageVersionSettings.supportsFeature(LanguageFeature.SamWrapperClassesAreSynthetic) ? JvmClosureGenerationScheme.INDY : JvmClosureGenerationScheme.CLASS;
        }
        this.samConversionsScheme = jvmClosureGenerationScheme;
        JvmClosureGenerationScheme jvmClosureGenerationScheme2 = (JvmClosureGenerationScheme) compilerConfiguration.get(JVMConfigurationKeys.LAMBDAS);
        if (jvmClosureGenerationScheme2 == null) {
            jvmClosureGenerationScheme2 = languageVersionSettings.supportsFeature(LanguageFeature.LightweightLambdas) ? JvmClosureGenerationScheme.INDY : JvmClosureGenerationScheme.CLASS;
        }
        this.lambdasScheme = jvmClosureGenerationScheme2;
        this.indyAllowAnnotatedLambdas = languageVersionSettings.supportsFeature(LanguageFeature.JvmIndyAllowLambdasWithAnnotations);
        this.stableTypeOf = languageVersionSettings.getApiVersion().compareTo(ApiVersion.KOTLIN_1_6) >= 0;
        this.isCallAssertionsDisabled = compilerConfiguration.getBoolean(JVMConfigurationKeys.DISABLE_CALL_ASSERTIONS);
        this.isReceiverAssertionsDisabled = compilerConfiguration.getBoolean(JVMConfigurationKeys.DISABLE_RECEIVER_ASSERTIONS) || !languageVersionSettings.supportsFeature(LanguageFeature.NullabilityAssertionOnExtensionReceiver);
        this.isParamAssertionsDisabled = compilerConfiguration.getBoolean(JVMConfigurationKeys.DISABLE_PARAM_ASSERTIONS);
        this.assertionsMode = (JVMAssertionsMode) compilerConfiguration.get(JVMConfigurationKeys.ASSERTIONS_MODE, JVMAssertionsMode.DEFAULT);
        this.isInlineDisabled = compilerConfiguration.getBoolean(CommonConfigurationKeys.DISABLE_INLINE);
        this.useTypeTableInSerializer = compilerConfiguration.getBoolean(JVMConfigurationKeys.USE_TYPE_TABLE);
        boolean z2 = languageVersionSettings.getApiVersion().compareTo(ApiVersion.KOTLIN_1_4) >= 0 && !compilerConfiguration.getBoolean(JVMConfigurationKeys.NO_UNIFIED_NULL_CHECKS);
        this.unifiedNullChecks = z2;
        this.noSourceCodeInNotNullAssertionExceptions = (languageVersionSettings.supportsFeature(LanguageFeature.NoSourceCodeInNotNullAssertionExceptions) && z2) || languageVersionSettings.getLanguageVersion().getUsesK2();
        this.generateSmapCopyToAnnotation = !compilerConfiguration.getBoolean(JVMConfigurationKeys.NO_SOURCE_DEBUG_EXTENSION);
        this.functionsWithInlineClassReturnTypesMangled = languageVersionSettings.supportsFeature(LanguageFeature.MangleClassMembersReturningInlineClasses);
        this.shouldValidateBytecode = compilerConfiguration.getBoolean(JVMConfigurationKeys.VALIDATE_BYTECODE);
        this.classFileVersion = ((compilerConfiguration.getBoolean(JVMConfigurationKeys.ENABLE_JVM_PREVIEW) ? CodegenUtilKt.STRING_UTF8_ENCODING_BYTE_LIMIT : 0) << 16) + jvmTarget.getMajorVersion();
        this.generateParametersMetadata = compilerConfiguration.getBoolean(JVMConfigurationKeys.PARAMETERS_METADATA);
        this.shouldInlineConstVals = languageVersionSettings.supportsFeature(LanguageFeature.InlineConstVals);
        this.jvmDefaultMode = JvmDefaultModeKt.getJvmDefaultMode(languageVersionSettings);
        this.disableOptimization = compilerConfiguration.getBoolean(JVMConfigurationKeys.DISABLE_OPTIMIZATION);
        this.metadataVersion = MetadataHelpersKt.jvmMetadataVersion$default(compilerConfiguration, (LanguageVersion) null, 1, (Object) null);
        this.abiStability = (JvmAbiStability) compilerConfiguration.get(JVMConfigurationKeys.ABI_STABILITY);
        this.noNewJavaAnnotationTargets = compilerConfiguration.getBoolean(JVMConfigurationKeys.NO_NEW_JAVA_ANNOTATION_TARGETS);
        this.supportJvmInlineMultiFieldValueClasses = languageVersionSettings.supportsFeature(LanguageFeature.JvmInlineMultiFieldValueClasses);
        this.enableDebugMode = compilerConfiguration.getBoolean(JVMConfigurationKeys.ENABLE_DEBUG_MODE);
        this.enhancedCoroutinesDebugging = compilerConfiguration.getBoolean(JVMConfigurationKeys.ENHANCED_COROUTINES_DEBUGGING);
        this.useFir = compilerConfiguration.getBoolean(CommonConfigurationKeys.USE_FIR);
        this.emitJvmTypeAnnotations = compilerConfiguration.getBoolean(JVMConfigurationKeys.EMIT_JVM_TYPE_ANNOTATIONS);
        this.nullOutSpilledCoroutineLocalsUsingStdlibFunction = languageVersionSettings.supportsFeature(LanguageFeature.JvmNullOutSpilledCoroutineLocals);
        this.wrapContinuationForTailCallFunctions = languageVersionSettings.supportsFeature(LanguageFeature.WrapContinuationForTailCallFunctions);
        this.whenGenerationScheme = jvmTarget.getMajorVersion() >= JvmTarget.JVM_21.getMajorVersion() ? (JvmWhenGenerationScheme) compilerConfiguration.get(JVMConfigurationKeys.WHEN_GENERATION_SCHEME, JvmWhenGenerationScheme.INLINE) : JvmWhenGenerationScheme.INLINE;
        this.generateDebugMetadataV2 = languageVersionSettings.getApiVersion().compareTo(ApiVersion.KOTLIN_2_3) >= 0;
        this.implicitJvmExposeBoxed = ((Boolean) languageVersionSettings.getFlag(JvmAnalysisFlags.getImplicitJvmExposeBoxed())).booleanValue();
    }

    public final JvmAbiStability getAbiStability() {
        return this.abiStability;
    }

    public final JVMAssertionsMode getAssertionsMode() {
        return this.assertionsMode;
    }

    public final int getClassFileVersion() {
        return this.classFileVersion;
    }

    public final boolean getDisableOptimization() {
        return this.disableOptimization;
    }

    public final boolean getEmitJvmTypeAnnotations() {
        return this.emitJvmTypeAnnotations;
    }

    public final boolean getEnableDebugMode() {
        return this.enableDebugMode;
    }

    public final boolean getEnhancedCoroutinesDebugging() {
        return this.enhancedCoroutinesDebugging;
    }

    public final boolean getFunctionsWithInlineClassReturnTypesMangled() {
        return this.functionsWithInlineClassReturnTypesMangled;
    }

    public final boolean getGenerateDebugMetadataV2() {
        return this.generateDebugMetadataV2;
    }

    public final boolean getGenerateParametersMetadata() {
        return this.generateParametersMetadata;
    }

    public final boolean getGenerateSmapCopyToAnnotation() {
        return this.generateSmapCopyToAnnotation;
    }

    public final boolean getImplicitJvmExposeBoxed() {
        return this.implicitJvmExposeBoxed;
    }

    public final boolean getIndyAllowAnnotatedLambdas() {
        return this.indyAllowAnnotatedLambdas;
    }

    public final JvmDefaultMode getJvmDefaultMode() {
        return this.jvmDefaultMode;
    }

    public final JvmClosureGenerationScheme getLambdasScheme() {
        return this.lambdasScheme;
    }

    public final LanguageVersionSettings getLanguageVersionSettings() {
        return this.languageVersionSettings;
    }

    public final BinaryVersion getMetadataVersion() {
        return this.metadataVersion;
    }

    public final boolean getNoNewJavaAnnotationTargets() {
        return this.noNewJavaAnnotationTargets;
    }

    public final boolean getNoSourceCodeInNotNullAssertionExceptions() {
        return this.noSourceCodeInNotNullAssertionExceptions;
    }

    public final boolean getNullOutSpilledCoroutineLocalsUsingStdlibFunction() {
        return this.nullOutSpilledCoroutineLocalsUsingStdlibFunction;
    }

    public final JvmStringConcat getRuntimeStringConcat() {
        return this.runtimeStringConcat;
    }

    public final JvmClosureGenerationScheme getSamConversionsScheme() {
        return this.samConversionsScheme;
    }

    public final boolean getShouldInlineConstVals() {
        return this.shouldInlineConstVals;
    }

    public final boolean getShouldValidateBytecode() {
        return this.shouldValidateBytecode;
    }

    public final boolean getStableTypeOf() {
        return this.stableTypeOf;
    }

    public final boolean getSupportJvmInlineMultiFieldValueClasses() {
        return this.supportJvmInlineMultiFieldValueClasses;
    }

    public final JvmTarget getTarget() {
        return this.target;
    }

    public final boolean getUnifiedNullChecks() {
        return this.unifiedNullChecks;
    }

    public final boolean getUseFir() {
        return this.useFir;
    }

    public final boolean getUseOldManglingSchemeForFunctionsWithInlineClassesInSignatures() {
        return this.useOldManglingSchemeForFunctionsWithInlineClassesInSignatures;
    }

    public final boolean getUseTypeTableInSerializer() {
        return this.useTypeTableInSerializer;
    }

    public final JvmWhenGenerationScheme getWhenGenerationScheme() {
        return this.whenGenerationScheme;
    }

    public final boolean getWrapContinuationForTailCallFunctions() {
        return this.wrapContinuationForTailCallFunctions;
    }

    /* JADX INFO: renamed from: isCallAssertionsDisabled, reason: from getter */
    public final boolean getIsCallAssertionsDisabled() {
        return this.isCallAssertionsDisabled;
    }

    /* JADX INFO: renamed from: isInlineDisabled, reason: from getter */
    public final boolean getIsInlineDisabled() {
        return this.isInlineDisabled;
    }

    /* JADX INFO: renamed from: isParamAssertionsDisabled, reason: from getter */
    public final boolean getIsParamAssertionsDisabled() {
        return this.isParamAssertionsDisabled;
    }

    /* JADX INFO: renamed from: isReceiverAssertionsDisabled, reason: from getter */
    public final boolean getIsReceiverAssertionsDisabled() {
        return this.isReceiverAssertionsDisabled;
    }
}
