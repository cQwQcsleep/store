package org.jetbrains.kotlin.cli;

import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.CliDiagnostics;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryDslKt;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryToRendererMap;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryToRendererMapKt;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticsContainer;
import org.jetbrains.kotlin.diagnostics.KtSourcelessDiagnosticFactory;
import org.jetbrains.kotlin.diagnostics.rendering.BaseDiagnosticRendererFactory;
import org.jetbrains.kotlin.diagnostics.rendering.BaseSourcelessDiagnosticRendererFactory;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\bS\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001ZB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010X\u001a\u00020YH\u0016R\u001b\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\t\u001a\u0004\b\u000b\u0010\u0007R\u001b\u0010\r\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\t\u001a\u0004\b\u000e\u0010\u0007R\u001b\u0010\u0010\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\t\u001a\u0004\b\u0011\u0010\u0007R\u001b\u0010\u0013\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\t\u001a\u0004\b\u0014\u0010\u0007R\u001b\u0010\u0016\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\t\u001a\u0004\b\u0017\u0010\u0007R\u001b\u0010\u0019\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\t\u001a\u0004\b\u001a\u0010\u0007R\u001b\u0010\u001c\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\t\u001a\u0004\b\u001d\u0010\u0007R\u001b\u0010\u001f\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\t\u001a\u0004\b \u0010\u0007R\u001b\u0010\"\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b$\u0010\t\u001a\u0004\b#\u0010\u0007R\u001b\u0010%\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b'\u0010\t\u001a\u0004\b&\u0010\u0007R\u001b\u0010(\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b*\u0010\t\u001a\u0004\b)\u0010\u0007R\u001b\u0010+\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b-\u0010\t\u001a\u0004\b,\u0010\u0007R\u001b\u0010.\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b0\u0010\t\u001a\u0004\b/\u0010\u0007R\u001b\u00101\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b3\u0010\t\u001a\u0004\b2\u0010\u0007R\u001b\u00104\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b6\u0010\t\u001a\u0004\b5\u0010\u0007R\u001b\u00107\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b9\u0010\t\u001a\u0004\b8\u0010\u0007R\u001b\u0010:\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b<\u0010\t\u001a\u0004\b;\u0010\u0007R\u001b\u0010=\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b?\u0010\t\u001a\u0004\b>\u0010\u0007R\u001b\u0010@\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bB\u0010\t\u001a\u0004\bA\u0010\u0007R\u001b\u0010C\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bE\u0010\t\u001a\u0004\bD\u0010\u0007R\u001b\u0010F\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bH\u0010\t\u001a\u0004\bG\u0010\u0007R\u001b\u0010I\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bK\u0010\t\u001a\u0004\bJ\u0010\u0007R\u001b\u0010L\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bN\u0010\t\u001a\u0004\bM\u0010\u0007R\u001b\u0010O\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bQ\u0010\t\u001a\u0004\bP\u0010\u0007R\u001b\u0010R\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bT\u0010\t\u001a\u0004\bS\u0010\u0007R\u001b\u0010U\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bW\u0010\t\u001a\u0004\bV\u0010\u0007¨\u0006["}, d2 = {"Lorg/jetbrains/kotlin/cli/CliDiagnostics;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticsContainer;", "<init>", "()V", "COMPILER_PLUGIN_ARG_IS_EXPERIMENTAL", "Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", "getCOMPILER_PLUGIN_ARG_IS_EXPERIMENTAL", "()Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", "COMPILER_PLUGIN_ARG_IS_EXPERIMENTAL$delegate", "Lkotlin/properties/ReadOnlyProperty;", "REDUNDANT_CLI_ARG", "getREDUNDANT_CLI_ARG", "REDUNDANT_CLI_ARG$delegate", "CLASSPATH_RESOLUTION_WARNING", "getCLASSPATH_RESOLUTION_WARNING", "CLASSPATH_RESOLUTION_WARNING$delegate", "CLASSPATH_RESOLUTION_ERROR", "getCLASSPATH_RESOLUTION_ERROR", "CLASSPATH_RESOLUTION_ERROR$delegate", "JAVA_MODULE_RESOLUTION_ERROR", "getJAVA_MODULE_RESOLUTION_ERROR", "JAVA_MODULE_RESOLUTION_ERROR$delegate", "ROOTS_RESOLUTION_WARNING", "getROOTS_RESOLUTION_WARNING", "ROOTS_RESOLUTION_WARNING$delegate", "ROOTS_RESOLUTION_ERROR", "getROOTS_RESOLUTION_ERROR", "ROOTS_RESOLUTION_ERROR$delegate", "UNSUPPORTED_LANGUAGE_VERSION", "getUNSUPPORTED_LANGUAGE_VERSION", "UNSUPPORTED_LANGUAGE_VERSION$delegate", "DEPRECATED_LANGUAGE_VERSION", "getDEPRECATED_LANGUAGE_VERSION", "DEPRECATED_LANGUAGE_VERSION$delegate", "EXPERIMENTAL_LANGUAGE_VERSION", "getEXPERIMENTAL_LANGUAGE_VERSION", "EXPERIMENTAL_LANGUAGE_VERSION$delegate", "COMPILER_PLUGIN_INITIALIZATION_WARNING", "getCOMPILER_PLUGIN_INITIALIZATION_WARNING", "COMPILER_PLUGIN_INITIALIZATION_WARNING$delegate", "COMPILER_PLUGIN_INITIALIZATION_ERROR", "getCOMPILER_PLUGIN_INITIALIZATION_ERROR", "COMPILER_PLUGIN_INITIALIZATION_ERROR$delegate", "INITIALIZATION_WARNING", "getINITIALIZATION_WARNING", "INITIALIZATION_WARNING$delegate", "COMPILER_ARGUMENTS_WARNING", "getCOMPILER_ARGUMENTS_WARNING", "COMPILER_ARGUMENTS_WARNING$delegate", "COMPILER_ARGUMENTS_ERROR", "getCOMPILER_ARGUMENTS_ERROR", "COMPILER_ARGUMENTS_ERROR$delegate", "JAVAC_INTEGRATION_WARNING", "getJAVAC_INTEGRATION_WARNING", "JAVAC_INTEGRATION_WARNING$delegate", "JAVAC_INTEGRATION_ERROR", "getJAVAC_INTEGRATION_ERROR", "JAVAC_INTEGRATION_ERROR$delegate", "KOTLIN_PACKAGE_USAGE", "getKOTLIN_PACKAGE_USAGE", "KOTLIN_PACKAGE_USAGE$delegate", "IO_ERROR", "getIO_ERROR", "IO_ERROR$delegate", "COMPILER_EXCEPTION", "getCOMPILER_EXCEPTION", "COMPILER_EXCEPTION$delegate", "SCRIPTING_WARNING", "getSCRIPTING_WARNING", "SCRIPTING_WARNING$delegate", "SCRIPTING_ERROR", "getSCRIPTING_ERROR", "SCRIPTING_ERROR$delegate", "WEB_ARGUMENT_WARNING", "getWEB_ARGUMENT_WARNING", "WEB_ARGUMENT_WARNING$delegate", "WEB_ARGUMENT_ERROR", "getWEB_ARGUMENT_ERROR", "WEB_ARGUMENT_ERROR$delegate", "JS_IC_ERROR", "getJS_IC_ERROR", "JS_IC_ERROR$delegate", "KONAN_ARGUMENT_WARNING", "getKONAN_ARGUMENT_WARNING", "KONAN_ARGUMENT_WARNING$delegate", "KONAN_ARGUMENT_ERROR", "getKONAN_ARGUMENT_ERROR", "KONAN_ARGUMENT_ERROR$delegate", "getRendererFactory", "Lorg/jetbrains/kotlin/diagnostics/rendering/BaseDiagnosticRendererFactory;", "Messages", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CliDiagnostics extends KtDiagnosticsContainer {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;

    /* JADX INFO: renamed from: CLASSPATH_RESOLUTION_ERROR$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty CLASSPATH_RESOLUTION_ERROR;

    /* JADX INFO: renamed from: CLASSPATH_RESOLUTION_WARNING$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty CLASSPATH_RESOLUTION_WARNING;

    /* JADX INFO: renamed from: COMPILER_ARGUMENTS_ERROR$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty COMPILER_ARGUMENTS_ERROR;

    /* JADX INFO: renamed from: COMPILER_ARGUMENTS_WARNING$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty COMPILER_ARGUMENTS_WARNING;

    /* JADX INFO: renamed from: COMPILER_EXCEPTION$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty COMPILER_EXCEPTION;

    /* JADX INFO: renamed from: COMPILER_PLUGIN_ARG_IS_EXPERIMENTAL$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty COMPILER_PLUGIN_ARG_IS_EXPERIMENTAL;

    /* JADX INFO: renamed from: COMPILER_PLUGIN_INITIALIZATION_ERROR$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty COMPILER_PLUGIN_INITIALIZATION_ERROR;

    /* JADX INFO: renamed from: COMPILER_PLUGIN_INITIALIZATION_WARNING$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty COMPILER_PLUGIN_INITIALIZATION_WARNING;

    /* JADX INFO: renamed from: DEPRECATED_LANGUAGE_VERSION$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty DEPRECATED_LANGUAGE_VERSION;

    /* JADX INFO: renamed from: EXPERIMENTAL_LANGUAGE_VERSION$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty EXPERIMENTAL_LANGUAGE_VERSION;

    /* JADX INFO: renamed from: INITIALIZATION_WARNING$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty INITIALIZATION_WARNING;
    public static final CliDiagnostics INSTANCE;

    /* JADX INFO: renamed from: IO_ERROR$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty IO_ERROR;

    /* JADX INFO: renamed from: JAVAC_INTEGRATION_ERROR$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty JAVAC_INTEGRATION_ERROR;

    /* JADX INFO: renamed from: JAVAC_INTEGRATION_WARNING$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty JAVAC_INTEGRATION_WARNING;

    /* JADX INFO: renamed from: JAVA_MODULE_RESOLUTION_ERROR$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty JAVA_MODULE_RESOLUTION_ERROR;

    /* JADX INFO: renamed from: JS_IC_ERROR$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty JS_IC_ERROR;

    /* JADX INFO: renamed from: KONAN_ARGUMENT_ERROR$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty KONAN_ARGUMENT_ERROR;

    /* JADX INFO: renamed from: KONAN_ARGUMENT_WARNING$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty KONAN_ARGUMENT_WARNING;

    /* JADX INFO: renamed from: KOTLIN_PACKAGE_USAGE$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty KOTLIN_PACKAGE_USAGE;

    /* JADX INFO: renamed from: REDUNDANT_CLI_ARG$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty REDUNDANT_CLI_ARG;

    /* JADX INFO: renamed from: ROOTS_RESOLUTION_ERROR$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty ROOTS_RESOLUTION_ERROR;

    /* JADX INFO: renamed from: ROOTS_RESOLUTION_WARNING$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty ROOTS_RESOLUTION_WARNING;

    /* JADX INFO: renamed from: SCRIPTING_ERROR$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty SCRIPTING_ERROR;

    /* JADX INFO: renamed from: SCRIPTING_WARNING$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty SCRIPTING_WARNING;

    /* JADX INFO: renamed from: UNSUPPORTED_LANGUAGE_VERSION$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty UNSUPPORTED_LANGUAGE_VERSION;

    /* JADX INFO: renamed from: WEB_ARGUMENT_ERROR$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty WEB_ARGUMENT_ERROR;

    /* JADX INFO: renamed from: WEB_ARGUMENT_WARNING$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty WEB_ARGUMENT_WARNING;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/cli/CliDiagnostics$Messages;", "Lorg/jetbrains/kotlin/diagnostics/rendering/BaseSourcelessDiagnosticRendererFactory;", "<init>", "()V", "MAP", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryToRendererMap;", "getMAP", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryToRendererMap;", "MAP$delegate", "Lkotlin/Lazy;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Messages extends BaseSourcelessDiagnosticRendererFactory {
        public static final Messages INSTANCE = new Messages();

        /* JADX INFO: renamed from: MAP$delegate, reason: from kotlin metadata */
        private static final Lazy MAP = KtDiagnosticFactoryToRendererMapKt.KtDiagnosticFactoryToRendererMap("CLI", new Function1() { // from class: tz1
            public final Object invoke(Object obj) {
                return CliDiagnostics.Messages.a((KtDiagnosticFactoryToRendererMap) obj);
            }
        });

        private Messages() {
        }

        public static Unit a(KtDiagnosticFactoryToRendererMap ktDiagnosticFactoryToRendererMap) {
            ktDiagnosticFactoryToRendererMap.getClass();
            CliDiagnostics cliDiagnostics = CliDiagnostics.INSTANCE;
            ktDiagnosticFactoryToRendererMap.put(cliDiagnostics.getCOMPILER_PLUGIN_ARG_IS_EXPERIMENTAL(), BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER);
            ktDiagnosticFactoryToRendererMap.put(cliDiagnostics.getREDUNDANT_CLI_ARG(), BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER);
            ktDiagnosticFactoryToRendererMap.put(cliDiagnostics.getCLASSPATH_RESOLUTION_WARNING(), BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER);
            ktDiagnosticFactoryToRendererMap.put(cliDiagnostics.getCLASSPATH_RESOLUTION_ERROR(), BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER);
            ktDiagnosticFactoryToRendererMap.put(cliDiagnostics.getJAVA_MODULE_RESOLUTION_ERROR(), BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER);
            ktDiagnosticFactoryToRendererMap.put(cliDiagnostics.getROOTS_RESOLUTION_WARNING(), BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER);
            ktDiagnosticFactoryToRendererMap.put(cliDiagnostics.getROOTS_RESOLUTION_ERROR(), BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER);
            ktDiagnosticFactoryToRendererMap.put(cliDiagnostics.getUNSUPPORTED_LANGUAGE_VERSION(), BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER);
            ktDiagnosticFactoryToRendererMap.put(cliDiagnostics.getDEPRECATED_LANGUAGE_VERSION(), BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER);
            ktDiagnosticFactoryToRendererMap.put(cliDiagnostics.getEXPERIMENTAL_LANGUAGE_VERSION(), BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER);
            ktDiagnosticFactoryToRendererMap.put(cliDiagnostics.getCOMPILER_PLUGIN_INITIALIZATION_WARNING(), BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER);
            ktDiagnosticFactoryToRendererMap.put(cliDiagnostics.getCOMPILER_PLUGIN_INITIALIZATION_ERROR(), BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER);
            ktDiagnosticFactoryToRendererMap.put(cliDiagnostics.getINITIALIZATION_WARNING(), BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER);
            ktDiagnosticFactoryToRendererMap.put(cliDiagnostics.getCOMPILER_ARGUMENTS_WARNING(), BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER);
            ktDiagnosticFactoryToRendererMap.put(cliDiagnostics.getCOMPILER_ARGUMENTS_ERROR(), BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER);
            ktDiagnosticFactoryToRendererMap.put(cliDiagnostics.getJAVAC_INTEGRATION_WARNING(), BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER);
            ktDiagnosticFactoryToRendererMap.put(cliDiagnostics.getJAVAC_INTEGRATION_ERROR(), BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER);
            ktDiagnosticFactoryToRendererMap.put(cliDiagnostics.getKOTLIN_PACKAGE_USAGE(), BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER);
            ktDiagnosticFactoryToRendererMap.put(cliDiagnostics.getIO_ERROR(), BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER);
            ktDiagnosticFactoryToRendererMap.put(cliDiagnostics.getCOMPILER_EXCEPTION(), BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER);
            ktDiagnosticFactoryToRendererMap.put(cliDiagnostics.getSCRIPTING_WARNING(), BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER);
            ktDiagnosticFactoryToRendererMap.put(cliDiagnostics.getSCRIPTING_ERROR(), BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER);
            ktDiagnosticFactoryToRendererMap.put(cliDiagnostics.getWEB_ARGUMENT_WARNING(), BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER);
            ktDiagnosticFactoryToRendererMap.put(cliDiagnostics.getWEB_ARGUMENT_ERROR(), BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER);
            ktDiagnosticFactoryToRendererMap.put(cliDiagnostics.getJS_IC_ERROR(), BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER);
            ktDiagnosticFactoryToRendererMap.put(cliDiagnostics.getKONAN_ARGUMENT_WARNING(), BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER);
            ktDiagnosticFactoryToRendererMap.put(cliDiagnostics.getKONAN_ARGUMENT_ERROR(), BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER);
            return Unit.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.diagnostics.rendering.BaseDiagnosticRendererFactory
        public KtDiagnosticFactoryToRendererMap getMAP() {
            return (KtDiagnosticFactoryToRendererMap) MAP.getValue();
        }
    }

    static {
        KProperty<?>[] kPropertyArr = {new PropertyReference1Impl<>(CliDiagnostics.class, "COMPILER_PLUGIN_ARG_IS_EXPERIMENTAL", "getCOMPILER_PLUGIN_ARG_IS_EXPERIMENTAL()Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", 0), new PropertyReference1Impl<>(CliDiagnostics.class, "REDUNDANT_CLI_ARG", "getREDUNDANT_CLI_ARG()Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", 0), new PropertyReference1Impl<>(CliDiagnostics.class, "CLASSPATH_RESOLUTION_WARNING", "getCLASSPATH_RESOLUTION_WARNING()Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", 0), new PropertyReference1Impl<>(CliDiagnostics.class, "CLASSPATH_RESOLUTION_ERROR", "getCLASSPATH_RESOLUTION_ERROR()Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", 0), new PropertyReference1Impl<>(CliDiagnostics.class, "JAVA_MODULE_RESOLUTION_ERROR", "getJAVA_MODULE_RESOLUTION_ERROR()Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", 0), new PropertyReference1Impl<>(CliDiagnostics.class, "ROOTS_RESOLUTION_WARNING", "getROOTS_RESOLUTION_WARNING()Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", 0), new PropertyReference1Impl<>(CliDiagnostics.class, "ROOTS_RESOLUTION_ERROR", "getROOTS_RESOLUTION_ERROR()Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", 0), new PropertyReference1Impl<>(CliDiagnostics.class, "UNSUPPORTED_LANGUAGE_VERSION", "getUNSUPPORTED_LANGUAGE_VERSION()Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", 0), new PropertyReference1Impl<>(CliDiagnostics.class, "DEPRECATED_LANGUAGE_VERSION", "getDEPRECATED_LANGUAGE_VERSION()Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", 0), new PropertyReference1Impl<>(CliDiagnostics.class, "EXPERIMENTAL_LANGUAGE_VERSION", "getEXPERIMENTAL_LANGUAGE_VERSION()Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", 0), new PropertyReference1Impl<>(CliDiagnostics.class, "COMPILER_PLUGIN_INITIALIZATION_WARNING", "getCOMPILER_PLUGIN_INITIALIZATION_WARNING()Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", 0), new PropertyReference1Impl<>(CliDiagnostics.class, "COMPILER_PLUGIN_INITIALIZATION_ERROR", "getCOMPILER_PLUGIN_INITIALIZATION_ERROR()Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", 0), new PropertyReference1Impl<>(CliDiagnostics.class, "INITIALIZATION_WARNING", "getINITIALIZATION_WARNING()Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", 0), new PropertyReference1Impl<>(CliDiagnostics.class, "COMPILER_ARGUMENTS_WARNING", "getCOMPILER_ARGUMENTS_WARNING()Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", 0), new PropertyReference1Impl<>(CliDiagnostics.class, "COMPILER_ARGUMENTS_ERROR", "getCOMPILER_ARGUMENTS_ERROR()Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", 0), new PropertyReference1Impl<>(CliDiagnostics.class, "JAVAC_INTEGRATION_WARNING", "getJAVAC_INTEGRATION_WARNING()Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", 0), new PropertyReference1Impl<>(CliDiagnostics.class, "JAVAC_INTEGRATION_ERROR", "getJAVAC_INTEGRATION_ERROR()Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", 0), new PropertyReference1Impl<>(CliDiagnostics.class, "KOTLIN_PACKAGE_USAGE", "getKOTLIN_PACKAGE_USAGE()Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", 0), new PropertyReference1Impl<>(CliDiagnostics.class, "IO_ERROR", "getIO_ERROR()Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", 0), new PropertyReference1Impl<>(CliDiagnostics.class, "COMPILER_EXCEPTION", "getCOMPILER_EXCEPTION()Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", 0), new PropertyReference1Impl<>(CliDiagnostics.class, "SCRIPTING_WARNING", "getSCRIPTING_WARNING()Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", 0), new PropertyReference1Impl<>(CliDiagnostics.class, "SCRIPTING_ERROR", "getSCRIPTING_ERROR()Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", 0), new PropertyReference1Impl<>(CliDiagnostics.class, "WEB_ARGUMENT_WARNING", "getWEB_ARGUMENT_WARNING()Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", 0), new PropertyReference1Impl<>(CliDiagnostics.class, "WEB_ARGUMENT_ERROR", "getWEB_ARGUMENT_ERROR()Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", 0), new PropertyReference1Impl<>(CliDiagnostics.class, "JS_IC_ERROR", "getJS_IC_ERROR()Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", 0), new PropertyReference1Impl<>(CliDiagnostics.class, "KONAN_ARGUMENT_WARNING", "getKONAN_ARGUMENT_WARNING()Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", 0), new PropertyReference1Impl<>(CliDiagnostics.class, "KONAN_ARGUMENT_ERROR", "getKONAN_ARGUMENT_ERROR()Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", 0)};
        $$delegatedProperties = kPropertyArr;
        CliDiagnostics cliDiagnostics = new CliDiagnostics();
        INSTANCE = cliDiagnostics;
        COMPILER_PLUGIN_ARG_IS_EXPERIMENTAL = KtDiagnosticFactoryDslKt.strongWarningWithoutSource(cliDiagnostics).provideDelegate(cliDiagnostics, kPropertyArr[0]);
        REDUNDANT_CLI_ARG = KtDiagnosticFactoryDslKt.strongWarningWithoutSource(cliDiagnostics).provideDelegate(cliDiagnostics, kPropertyArr[1]);
        CLASSPATH_RESOLUTION_WARNING = KtDiagnosticFactoryDslKt.strongWarningWithoutSource(cliDiagnostics).provideDelegate(cliDiagnostics, kPropertyArr[2]);
        CLASSPATH_RESOLUTION_ERROR = KtDiagnosticFactoryDslKt.errorWithoutSource(cliDiagnostics).provideDelegate(cliDiagnostics, kPropertyArr[3]);
        JAVA_MODULE_RESOLUTION_ERROR = KtDiagnosticFactoryDslKt.errorWithoutSource(cliDiagnostics).provideDelegate(cliDiagnostics, kPropertyArr[4]);
        ROOTS_RESOLUTION_WARNING = KtDiagnosticFactoryDslKt.strongWarningWithoutSource(cliDiagnostics).provideDelegate(cliDiagnostics, kPropertyArr[5]);
        ROOTS_RESOLUTION_ERROR = KtDiagnosticFactoryDslKt.errorWithoutSource(cliDiagnostics).provideDelegate(cliDiagnostics, kPropertyArr[6]);
        UNSUPPORTED_LANGUAGE_VERSION = KtDiagnosticFactoryDslKt.errorWithoutSource(cliDiagnostics).provideDelegate(cliDiagnostics, kPropertyArr[7]);
        DEPRECATED_LANGUAGE_VERSION = KtDiagnosticFactoryDslKt.strongWarningWithoutSource(cliDiagnostics).provideDelegate(cliDiagnostics, kPropertyArr[8]);
        EXPERIMENTAL_LANGUAGE_VERSION = KtDiagnosticFactoryDslKt.strongWarningWithoutSource(cliDiagnostics).provideDelegate(cliDiagnostics, kPropertyArr[9]);
        COMPILER_PLUGIN_INITIALIZATION_WARNING = KtDiagnosticFactoryDslKt.strongWarningWithoutSource(cliDiagnostics).provideDelegate(cliDiagnostics, kPropertyArr[10]);
        COMPILER_PLUGIN_INITIALIZATION_ERROR = KtDiagnosticFactoryDslKt.errorWithoutSource(cliDiagnostics).provideDelegate(cliDiagnostics, kPropertyArr[11]);
        INITIALIZATION_WARNING = KtDiagnosticFactoryDslKt.strongWarningWithoutSource(cliDiagnostics).provideDelegate(cliDiagnostics, kPropertyArr[12]);
        COMPILER_ARGUMENTS_WARNING = KtDiagnosticFactoryDslKt.strongWarningWithoutSource(cliDiagnostics).provideDelegate(cliDiagnostics, kPropertyArr[13]);
        COMPILER_ARGUMENTS_ERROR = KtDiagnosticFactoryDslKt.errorWithoutSource(cliDiagnostics).provideDelegate(cliDiagnostics, kPropertyArr[14]);
        JAVAC_INTEGRATION_WARNING = KtDiagnosticFactoryDslKt.strongWarningWithoutSource(cliDiagnostics).provideDelegate(cliDiagnostics, kPropertyArr[15]);
        JAVAC_INTEGRATION_ERROR = KtDiagnosticFactoryDslKt.errorWithoutSource(cliDiagnostics).provideDelegate(cliDiagnostics, kPropertyArr[16]);
        KOTLIN_PACKAGE_USAGE = KtDiagnosticFactoryDslKt.errorWithoutSource(cliDiagnostics).provideDelegate(cliDiagnostics, kPropertyArr[17]);
        IO_ERROR = KtDiagnosticFactoryDslKt.errorWithoutSource(cliDiagnostics).provideDelegate(cliDiagnostics, kPropertyArr[18]);
        COMPILER_EXCEPTION = KtDiagnosticFactoryDslKt.errorWithoutSource(cliDiagnostics).provideDelegate(cliDiagnostics, kPropertyArr[19]);
        SCRIPTING_WARNING = KtDiagnosticFactoryDslKt.strongWarningWithoutSource(cliDiagnostics).provideDelegate(cliDiagnostics, kPropertyArr[20]);
        SCRIPTING_ERROR = KtDiagnosticFactoryDslKt.errorWithoutSource(cliDiagnostics).provideDelegate(cliDiagnostics, kPropertyArr[21]);
        WEB_ARGUMENT_WARNING = KtDiagnosticFactoryDslKt.strongWarningWithoutSource(cliDiagnostics).provideDelegate(cliDiagnostics, kPropertyArr[22]);
        WEB_ARGUMENT_ERROR = KtDiagnosticFactoryDslKt.errorWithoutSource(cliDiagnostics).provideDelegate(cliDiagnostics, kPropertyArr[23]);
        JS_IC_ERROR = KtDiagnosticFactoryDslKt.errorWithoutSource(cliDiagnostics).provideDelegate(cliDiagnostics, kPropertyArr[24]);
        KONAN_ARGUMENT_WARNING = KtDiagnosticFactoryDslKt.strongWarningWithoutSource(cliDiagnostics).provideDelegate(cliDiagnostics, kPropertyArr[25]);
        KONAN_ARGUMENT_ERROR = KtDiagnosticFactoryDslKt.errorWithoutSource(cliDiagnostics).provideDelegate(cliDiagnostics, kPropertyArr[26]);
    }

    private CliDiagnostics() {
    }

    public final KtSourcelessDiagnosticFactory getCLASSPATH_RESOLUTION_ERROR() {
        return (KtSourcelessDiagnosticFactory) CLASSPATH_RESOLUTION_ERROR.getValue(this, $$delegatedProperties[3]);
    }

    public final KtSourcelessDiagnosticFactory getCLASSPATH_RESOLUTION_WARNING() {
        return (KtSourcelessDiagnosticFactory) CLASSPATH_RESOLUTION_WARNING.getValue(this, $$delegatedProperties[2]);
    }

    public final KtSourcelessDiagnosticFactory getCOMPILER_ARGUMENTS_ERROR() {
        return (KtSourcelessDiagnosticFactory) COMPILER_ARGUMENTS_ERROR.getValue(this, $$delegatedProperties[14]);
    }

    public final KtSourcelessDiagnosticFactory getCOMPILER_ARGUMENTS_WARNING() {
        return (KtSourcelessDiagnosticFactory) COMPILER_ARGUMENTS_WARNING.getValue(this, $$delegatedProperties[13]);
    }

    public final KtSourcelessDiagnosticFactory getCOMPILER_EXCEPTION() {
        return (KtSourcelessDiagnosticFactory) COMPILER_EXCEPTION.getValue(this, $$delegatedProperties[19]);
    }

    public final KtSourcelessDiagnosticFactory getCOMPILER_PLUGIN_ARG_IS_EXPERIMENTAL() {
        return (KtSourcelessDiagnosticFactory) COMPILER_PLUGIN_ARG_IS_EXPERIMENTAL.getValue(this, $$delegatedProperties[0]);
    }

    public final KtSourcelessDiagnosticFactory getCOMPILER_PLUGIN_INITIALIZATION_ERROR() {
        return (KtSourcelessDiagnosticFactory) COMPILER_PLUGIN_INITIALIZATION_ERROR.getValue(this, $$delegatedProperties[11]);
    }

    public final KtSourcelessDiagnosticFactory getCOMPILER_PLUGIN_INITIALIZATION_WARNING() {
        return (KtSourcelessDiagnosticFactory) COMPILER_PLUGIN_INITIALIZATION_WARNING.getValue(this, $$delegatedProperties[10]);
    }

    public final KtSourcelessDiagnosticFactory getDEPRECATED_LANGUAGE_VERSION() {
        return (KtSourcelessDiagnosticFactory) DEPRECATED_LANGUAGE_VERSION.getValue(this, $$delegatedProperties[8]);
    }

    public final KtSourcelessDiagnosticFactory getEXPERIMENTAL_LANGUAGE_VERSION() {
        return (KtSourcelessDiagnosticFactory) EXPERIMENTAL_LANGUAGE_VERSION.getValue(this, $$delegatedProperties[9]);
    }

    public final KtSourcelessDiagnosticFactory getINITIALIZATION_WARNING() {
        return (KtSourcelessDiagnosticFactory) INITIALIZATION_WARNING.getValue(this, $$delegatedProperties[12]);
    }

    public final KtSourcelessDiagnosticFactory getIO_ERROR() {
        return (KtSourcelessDiagnosticFactory) IO_ERROR.getValue(this, $$delegatedProperties[18]);
    }

    public final KtSourcelessDiagnosticFactory getJAVAC_INTEGRATION_ERROR() {
        return (KtSourcelessDiagnosticFactory) JAVAC_INTEGRATION_ERROR.getValue(this, $$delegatedProperties[16]);
    }

    public final KtSourcelessDiagnosticFactory getJAVAC_INTEGRATION_WARNING() {
        return (KtSourcelessDiagnosticFactory) JAVAC_INTEGRATION_WARNING.getValue(this, $$delegatedProperties[15]);
    }

    public final KtSourcelessDiagnosticFactory getJAVA_MODULE_RESOLUTION_ERROR() {
        return (KtSourcelessDiagnosticFactory) JAVA_MODULE_RESOLUTION_ERROR.getValue(this, $$delegatedProperties[4]);
    }

    public final KtSourcelessDiagnosticFactory getJS_IC_ERROR() {
        return (KtSourcelessDiagnosticFactory) JS_IC_ERROR.getValue(this, $$delegatedProperties[24]);
    }

    public final KtSourcelessDiagnosticFactory getKONAN_ARGUMENT_ERROR() {
        return (KtSourcelessDiagnosticFactory) KONAN_ARGUMENT_ERROR.getValue(this, $$delegatedProperties[26]);
    }

    public final KtSourcelessDiagnosticFactory getKONAN_ARGUMENT_WARNING() {
        return (KtSourcelessDiagnosticFactory) KONAN_ARGUMENT_WARNING.getValue(this, $$delegatedProperties[25]);
    }

    public final KtSourcelessDiagnosticFactory getKOTLIN_PACKAGE_USAGE() {
        return (KtSourcelessDiagnosticFactory) KOTLIN_PACKAGE_USAGE.getValue(this, $$delegatedProperties[17]);
    }

    public final KtSourcelessDiagnosticFactory getREDUNDANT_CLI_ARG() {
        return (KtSourcelessDiagnosticFactory) REDUNDANT_CLI_ARG.getValue(this, $$delegatedProperties[1]);
    }

    public final KtSourcelessDiagnosticFactory getROOTS_RESOLUTION_ERROR() {
        return (KtSourcelessDiagnosticFactory) ROOTS_RESOLUTION_ERROR.getValue(this, $$delegatedProperties[6]);
    }

    public final KtSourcelessDiagnosticFactory getROOTS_RESOLUTION_WARNING() {
        return (KtSourcelessDiagnosticFactory) ROOTS_RESOLUTION_WARNING.getValue(this, $$delegatedProperties[5]);
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnosticsContainer
    public BaseDiagnosticRendererFactory getRendererFactory() {
        return Messages.INSTANCE;
    }

    public final KtSourcelessDiagnosticFactory getSCRIPTING_ERROR() {
        return (KtSourcelessDiagnosticFactory) SCRIPTING_ERROR.getValue(this, $$delegatedProperties[21]);
    }

    public final KtSourcelessDiagnosticFactory getSCRIPTING_WARNING() {
        return (KtSourcelessDiagnosticFactory) SCRIPTING_WARNING.getValue(this, $$delegatedProperties[20]);
    }

    public final KtSourcelessDiagnosticFactory getUNSUPPORTED_LANGUAGE_VERSION() {
        return (KtSourcelessDiagnosticFactory) UNSUPPORTED_LANGUAGE_VERSION.getValue(this, $$delegatedProperties[7]);
    }

    public final KtSourcelessDiagnosticFactory getWEB_ARGUMENT_ERROR() {
        return (KtSourcelessDiagnosticFactory) WEB_ARGUMENT_ERROR.getValue(this, $$delegatedProperties[23]);
    }

    public final KtSourcelessDiagnosticFactory getWEB_ARGUMENT_WARNING() {
        return (KtSourcelessDiagnosticFactory) WEB_ARGUMENT_WARNING.getValue(this, $$delegatedProperties[22]);
    }
}
