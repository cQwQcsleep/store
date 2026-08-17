package org.jetbrains.kotlin.fir.backend;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;
import org.jetbrains.kotlin.config.CommonConfigurationKeys;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.IrVerificationMode;
import org.jetbrains.kotlin.config.JVMConfigurationKeys;
import org.jetbrains.kotlin.config.JvmClosureGenerationScheme;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.impl.BaseDiagnosticsCollector;
import org.jetbrains.kotlin.incremental.components.ExpectActualTracker;
import org.jetbrains.kotlin.incremental.components.InlineConstTracker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\u0018\u0000 '2\u00020\u0001:\u0002&'B]\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\r\u0012\u0006\u0010\u0012\u001a\u00020\r¢\u0006\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u000e\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b!\u0010 R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u0011\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b$\u0010 R\u0011\u0010\u0012\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b%\u0010 ¨\u0006("}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", Argument.Delimiters.none, "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "diagnosticReporter", "Lorg/jetbrains/kotlin/diagnostics/impl/BaseDiagnosticsCollector;", "messageCollector", "Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "inlineConstTracker", "Lorg/jetbrains/kotlin/incremental/components/InlineConstTracker;", "expectActualTracker", "Lorg/jetbrains/kotlin/incremental/components/ExpectActualTracker;", "allowNonCachedDeclarations", Argument.Delimiters.none, "skipBodies", "irVerificationSettings", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration$IrVerificationSettings;", "carefulApproximationOfContravariantProjectionForSam", "propagateLazyIrPrivateMembers", "<init>", "(Lorg/jetbrains/kotlin/config/LanguageVersionSettings;Lorg/jetbrains/kotlin/diagnostics/impl/BaseDiagnosticsCollector;Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;Lorg/jetbrains/kotlin/incremental/components/InlineConstTracker;Lorg/jetbrains/kotlin/incremental/components/ExpectActualTracker;ZZLorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration$IrVerificationSettings;ZZ)V", "getLanguageVersionSettings", "()Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "getDiagnosticReporter", "()Lorg/jetbrains/kotlin/diagnostics/impl/BaseDiagnosticsCollector;", "getMessageCollector", "()Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "getInlineConstTracker", "()Lorg/jetbrains/kotlin/incremental/components/InlineConstTracker;", "getExpectActualTracker", "()Lorg/jetbrains/kotlin/incremental/components/ExpectActualTracker;", "getAllowNonCachedDeclarations", "()Z", "getSkipBodies", "getIrVerificationSettings", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration$IrVerificationSettings;", "getCarefulApproximationOfContravariantProjectionForSam", "getPropagateLazyIrPrivateMembers", "IrVerificationSettings", "Companion", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrConfiguration {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final boolean allowNonCachedDeclarations;
    private final boolean carefulApproximationOfContravariantProjectionForSam;
    private final BaseDiagnosticsCollector diagnosticReporter;
    private final ExpectActualTracker expectActualTracker;
    private final InlineConstTracker inlineConstTracker;
    private final IrVerificationSettings irVerificationSettings;
    private final LanguageVersionSettings languageVersionSettings;
    private final MessageCollector messageCollector;
    private final boolean propagateLazyIrPrivateMembers;
    private final boolean skipBodies;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000e¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration$IrVerificationSettings;", Argument.Delimiters.none, "mode", "Lorg/jetbrains/kotlin/config/IrVerificationMode;", "validateForKlibSerialization", Argument.Delimiters.none, "enableIrVisibilityChecks", "enableIrVarargTypesChecks", "enableIrNestedOffsetsChecks", "<init>", "(Lorg/jetbrains/kotlin/config/IrVerificationMode;ZZZZ)V", "getMode", "()Lorg/jetbrains/kotlin/config/IrVerificationMode;", "getValidateForKlibSerialization", "()Z", "getEnableIrVisibilityChecks", "getEnableIrVarargTypesChecks", "getEnableIrNestedOffsetsChecks", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class IrVerificationSettings {
        private final boolean enableIrNestedOffsetsChecks;
        private final boolean enableIrVarargTypesChecks;
        private final boolean enableIrVisibilityChecks;
        private final IrVerificationMode mode;
        private final boolean validateForKlibSerialization;

        public IrVerificationSettings(IrVerificationMode irVerificationMode, boolean z, boolean z2, boolean z3, boolean z4) {
            irVerificationMode.getClass();
            this.mode = irVerificationMode;
            this.validateForKlibSerialization = z;
            this.enableIrVisibilityChecks = z2;
            this.enableIrVarargTypesChecks = z3;
            this.enableIrNestedOffsetsChecks = z4;
        }

        public final boolean getEnableIrNestedOffsetsChecks() {
            return this.enableIrNestedOffsetsChecks;
        }

        public final boolean getEnableIrVarargTypesChecks() {
            return this.enableIrVarargTypesChecks;
        }

        public final boolean getEnableIrVisibilityChecks() {
            return this.enableIrVisibilityChecks;
        }

        public final IrVerificationMode getMode() {
            return this.mode;
        }

        public final boolean getValidateForKlibSerialization() {
            return this.validateForKlibSerialization;
        }
    }

    private Fir2IrConfiguration(LanguageVersionSettings languageVersionSettings, BaseDiagnosticsCollector baseDiagnosticsCollector, MessageCollector messageCollector, InlineConstTracker inlineConstTracker, ExpectActualTracker expectActualTracker, boolean z, boolean z2, IrVerificationSettings irVerificationSettings, boolean z3, boolean z4) {
        this.languageVersionSettings = languageVersionSettings;
        this.diagnosticReporter = baseDiagnosticsCollector;
        this.messageCollector = messageCollector;
        this.inlineConstTracker = inlineConstTracker;
        this.expectActualTracker = expectActualTracker;
        this.allowNonCachedDeclarations = z;
        this.skipBodies = z2;
        this.irVerificationSettings = irVerificationSettings;
        this.carefulApproximationOfContravariantProjectionForSam = z3;
        this.propagateLazyIrPrivateMembers = z4;
    }

    public final boolean getAllowNonCachedDeclarations() {
        return this.allowNonCachedDeclarations;
    }

    public final boolean getCarefulApproximationOfContravariantProjectionForSam() {
        return this.carefulApproximationOfContravariantProjectionForSam;
    }

    public final BaseDiagnosticsCollector getDiagnosticReporter() {
        return this.diagnosticReporter;
    }

    public final ExpectActualTracker getExpectActualTracker() {
        return this.expectActualTracker;
    }

    public final InlineConstTracker getInlineConstTracker() {
        return this.inlineConstTracker;
    }

    public final IrVerificationSettings getIrVerificationSettings() {
        return this.irVerificationSettings;
    }

    public final LanguageVersionSettings getLanguageVersionSettings() {
        return this.languageVersionSettings;
    }

    public final MessageCollector getMessageCollector() {
        return this.messageCollector;
    }

    public final boolean getPropagateLazyIrPrivateMembers() {
        return this.propagateLazyIrPrivateMembers;
    }

    public final boolean getSkipBodies() {
        return this.skipBodies;
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u001e\u0010\f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\t¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration$Companion;", Argument.Delimiters.none, "<init>", "()V", "forJvmCompilation", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "compilerConfiguration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "diagnosticReporter", "Lorg/jetbrains/kotlin/diagnostics/impl/BaseDiagnosticsCollector;", "forJKlibCompilation", "forKlibCompilation", "forAnalysisApi", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Fir2IrConfiguration forAnalysisApi(CompilerConfiguration compilerConfiguration, LanguageVersionSettings languageVersionSettings, BaseDiagnosticsCollector diagnosticReporter) {
            compilerConfiguration.getClass();
            languageVersionSettings.getClass();
            diagnosticReporter.getClass();
            return new Fir2IrConfiguration(languageVersionSettings, diagnosticReporter, CommonConfigurationKeysKt.getMessageCollector(compilerConfiguration), (InlineConstTracker) compilerConfiguration.get(CommonConfigurationKeys.INLINE_CONST_TRACKER), (ExpectActualTracker) compilerConfiguration.get(CommonConfigurationKeys.EXPECT_ACTUAL_TRACKER), true, false, new IrVerificationSettings((IrVerificationMode) compilerConfiguration.get(CommonConfigurationKeys.VERIFY_IR, IrVerificationMode.NONE), false, CommonConfigurationKeysKt.getEnableIrVisibilityChecks(compilerConfiguration), CommonConfigurationKeysKt.getEnableIrVarargTypesChecks(compilerConfiguration), CommonConfigurationKeysKt.getEnableIrNestedOffsetsChecks(compilerConfiguration)), compilerConfiguration.get(JVMConfigurationKeys.SAM_CONVERSIONS) != JvmClosureGenerationScheme.CLASS, false, null);
        }

        public final Fir2IrConfiguration forJKlibCompilation(CompilerConfiguration compilerConfiguration, BaseDiagnosticsCollector diagnosticReporter) {
            compilerConfiguration.getClass();
            diagnosticReporter.getClass();
            return new Fir2IrConfiguration(CommonConfigurationKeysKt.getLanguageVersionSettings(compilerConfiguration), diagnosticReporter, CommonConfigurationKeysKt.getMessageCollector(compilerConfiguration), (InlineConstTracker) compilerConfiguration.get(CommonConfigurationKeys.INLINE_CONST_TRACKER), (ExpectActualTracker) compilerConfiguration.get(CommonConfigurationKeys.EXPECT_ACTUAL_TRACKER), false, compilerConfiguration.getBoolean(JVMConfigurationKeys.SKIP_BODIES), new IrVerificationSettings((IrVerificationMode) compilerConfiguration.get(CommonConfigurationKeys.VERIFY_IR, IrVerificationMode.NONE), false, CommonConfigurationKeysKt.getEnableIrVisibilityChecks(compilerConfiguration), CommonConfigurationKeysKt.getEnableIrVarargTypesChecks(compilerConfiguration), CommonConfigurationKeysKt.getEnableIrNestedOffsetsChecks(compilerConfiguration)), compilerConfiguration.get(JVMConfigurationKeys.SAM_CONVERSIONS) != JvmClosureGenerationScheme.CLASS, true, null);
        }

        public final Fir2IrConfiguration forJvmCompilation(CompilerConfiguration compilerConfiguration, BaseDiagnosticsCollector diagnosticReporter) {
            compilerConfiguration.getClass();
            diagnosticReporter.getClass();
            return new Fir2IrConfiguration(CommonConfigurationKeysKt.getLanguageVersionSettings(compilerConfiguration), diagnosticReporter, CommonConfigurationKeysKt.getMessageCollector(compilerConfiguration), (InlineConstTracker) compilerConfiguration.get(CommonConfigurationKeys.INLINE_CONST_TRACKER), (ExpectActualTracker) compilerConfiguration.get(CommonConfigurationKeys.EXPECT_ACTUAL_TRACKER), false, compilerConfiguration.getBoolean(JVMConfigurationKeys.SKIP_BODIES), new IrVerificationSettings((IrVerificationMode) compilerConfiguration.get(CommonConfigurationKeys.VERIFY_IR, IrVerificationMode.NONE), false, CommonConfigurationKeysKt.getEnableIrVisibilityChecks(compilerConfiguration), CommonConfigurationKeysKt.getEnableIrVarargTypesChecks(compilerConfiguration), CommonConfigurationKeysKt.getEnableIrNestedOffsetsChecks(compilerConfiguration)), compilerConfiguration.get(JVMConfigurationKeys.SAM_CONVERSIONS) != JvmClosureGenerationScheme.CLASS, false, null);
        }

        public final Fir2IrConfiguration forKlibCompilation(CompilerConfiguration compilerConfiguration, BaseDiagnosticsCollector diagnosticReporter) {
            compilerConfiguration.getClass();
            diagnosticReporter.getClass();
            return new Fir2IrConfiguration(CommonConfigurationKeysKt.getLanguageVersionSettings(compilerConfiguration), diagnosticReporter, CommonConfigurationKeysKt.getMessageCollector(compilerConfiguration), null, (ExpectActualTracker) compilerConfiguration.get(CommonConfigurationKeys.EXPECT_ACTUAL_TRACKER), false, false, new IrVerificationSettings((IrVerificationMode) compilerConfiguration.get(CommonConfigurationKeys.VERIFY_IR, IrVerificationMode.NONE), true, CommonConfigurationKeysKt.getEnableIrVisibilityChecks(compilerConfiguration), CommonConfigurationKeysKt.getEnableIrVarargTypesChecks(compilerConfiguration), CommonConfigurationKeysKt.getEnableIrNestedOffsetsChecks(compilerConfiguration)), false, false, null);
        }

        private Companion() {
        }
    }

    public /* synthetic */ Fir2IrConfiguration(LanguageVersionSettings languageVersionSettings, BaseDiagnosticsCollector baseDiagnosticsCollector, MessageCollector messageCollector, InlineConstTracker inlineConstTracker, ExpectActualTracker expectActualTracker, boolean z, boolean z2, IrVerificationSettings irVerificationSettings, boolean z3, boolean z4, DefaultConstructorMarker defaultConstructorMarker) {
        this(languageVersionSettings, baseDiagnosticsCollector, messageCollector, inlineConstTracker, expectActualTracker, z, z2, irVerificationSettings, z3, z4);
    }
}
