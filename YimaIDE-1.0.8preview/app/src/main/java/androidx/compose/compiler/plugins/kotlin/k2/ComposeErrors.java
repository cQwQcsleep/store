package androidx.compose.compiler.plugins.kotlin.k2;

import com.intellij.psi.PsiElement;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.config.LanguageVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticFactory0DelegateProvider;
import org.jetbrains.kotlin.diagnostics.DiagnosticFactory1DelegateProvider;
import org.jetbrains.kotlin.diagnostics.DiagnosticFactory2DelegateProvider;
import org.jetbrains.kotlin.diagnostics.DiagnosticFactory3DelegateProvider;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory0;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory3;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticsContainer;
import org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategies;
import org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy;
import org.jetbrains.kotlin.diagnostics.OffsetsOnlyPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.PositioningStrategies;
import org.jetbrains.kotlin.diagnostics.PositioningStrategy;
import org.jetbrains.kotlin.diagnostics.Severity;
import org.jetbrains.kotlin.diagnostics.SourceElementPositioningStrategies;
import org.jetbrains.kotlin.diagnostics.SourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.rendering.BaseDiagnosticRendererFactory;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.psi.KtCallExpression;
import org.jetbrains.kotlin.psi.KtTryExpression;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010U\u001a\u00020VH\u0016R\u001b\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\t\u001a\u0004\b\u000b\u0010\u0007R\u001b\u0010\r\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\t\u001a\u0004\b\u000e\u0010\u0007R/\u0010\u0010\u001a\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00130\u00118FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\t\u001a\u0004\b\u0014\u0010\u0015R\u001b\u0010\u0017\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\t\u001a\u0004\b\u0018\u0010\u0007R\u001b\u0010\u001a\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\t\u001a\u0004\b\u001b\u0010\u0007R1\u0010\u001d\u001a\u0018\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u001f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00130\u001e8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010\t\u001a\u0004\b \u0010!R\u001b\u0010#\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b%\u0010\t\u001a\u0004\b$\u0010\u0007R\u001b\u0010&\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b(\u0010\t\u001a\u0004\b'\u0010\u0007R\u001b\u0010)\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b+\u0010\t\u001a\u0004\b*\u0010\u0007R\u001b\u0010,\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b.\u0010\t\u001a\u0004\b-\u0010\u0007R\u001b\u0010/\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b1\u0010\t\u001a\u0004\b0\u0010\u0007R\u001b\u00102\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b4\u0010\t\u001a\u0004\b3\u0010\u0007R\u001b\u00105\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b7\u0010\t\u001a\u0004\b6\u0010\u0007R\u001b\u00108\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b:\u0010\t\u001a\u0004\b9\u0010\u0007R'\u0010;\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020<0\u00118FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b>\u0010\t\u001a\u0004\b=\u0010\u0015R'\u0010?\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020<0\u00118FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bA\u0010\t\u001a\u0004\b@\u0010\u0015R\u001b\u0010B\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bD\u0010\t\u001a\u0004\bC\u0010\u0007R!\u0010E\u001a\b\u0012\u0004\u0012\u00020G0F8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bJ\u0010\t\u001a\u0004\bH\u0010IR!\u0010K\u001a\b\u0012\u0004\u0012\u00020L0F8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bN\u0010\t\u001a\u0004\bM\u0010IR!\u0010O\u001a\b\u0012\u0004\u0012\u00020L0F8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bQ\u0010\t\u001a\u0004\bP\u0010IR\u001b\u0010R\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bT\u0010\t\u001a\u0004\bS\u0010\u0007¨\u0006W"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/k2/ComposeErrors;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticsContainer;", "<init>", "()V", "COMPOSABLE_INVOCATION", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", "getCOMPOSABLE_INVOCATION", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", "COMPOSABLE_INVOCATION$delegate", "Lkotlin/properties/ReadOnlyProperty;", "COMPOSABLE_EXPECTED", "getCOMPOSABLE_EXPECTED", "COMPOSABLE_EXPECTED$delegate", "NONREADONLY_CALL_IN_READONLY_COMPOSABLE", "getNONREADONLY_CALL_IN_READONLY_COMPOSABLE", "NONREADONLY_CALL_IN_READONLY_COMPOSABLE$delegate", "CAPTURED_COMPOSABLE_INVOCATION", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory2;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "getCAPTURED_COMPOSABLE_INVOCATION", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory2;", "CAPTURED_COMPOSABLE_INVOCATION$delegate", "ILLEGAL_TRY_CATCH_AROUND_COMPOSABLE", "getILLEGAL_TRY_CATCH_AROUND_COMPOSABLE", "ILLEGAL_TRY_CATCH_AROUND_COMPOSABLE$delegate", "ILLEGAL_RUN_CATCHING_AROUND_COMPOSABLE", "getILLEGAL_RUN_CATCHING_AROUND_COMPOSABLE", "ILLEGAL_RUN_CATCHING_AROUND_COMPOSABLE$delegate", "MISSING_DISALLOW_COMPOSABLE_CALLS_ANNOTATION", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory3;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;", "getMISSING_DISALLOW_COMPOSABLE_CALLS_ANNOTATION", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory3;", "MISSING_DISALLOW_COMPOSABLE_CALLS_ANNOTATION$delegate", "DEPRECATED_OPEN_COMPOSABLE_DEFAULT_PARAMETER_VALUE", "getDEPRECATED_OPEN_COMPOSABLE_DEFAULT_PARAMETER_VALUE", "DEPRECATED_OPEN_COMPOSABLE_DEFAULT_PARAMETER_VALUE$delegate", "COMPOSABLE_SUSPEND_FUN", "getCOMPOSABLE_SUSPEND_FUN", "COMPOSABLE_SUSPEND_FUN$delegate", "COMPOSABLE_FUN_MAIN", "getCOMPOSABLE_FUN_MAIN", "COMPOSABLE_FUN_MAIN$delegate", "COMPOSABLE_PROPERTY_REFERENCE", "getCOMPOSABLE_PROPERTY_REFERENCE", "COMPOSABLE_PROPERTY_REFERENCE$delegate", "COMPOSABLE_PROPERTY_BACKING_FIELD", "getCOMPOSABLE_PROPERTY_BACKING_FIELD", "COMPOSABLE_PROPERTY_BACKING_FIELD$delegate", "COMPOSABLE_VAR", "getCOMPOSABLE_VAR", "COMPOSABLE_VAR$delegate", "COMPOSE_INVALID_DELEGATE", "getCOMPOSE_INVALID_DELEGATE", "COMPOSE_INVALID_DELEGATE$delegate", "MISMATCHED_COMPOSABLE_IN_EXPECT_ACTUAL", "getMISMATCHED_COMPOSABLE_IN_EXPECT_ACTUAL", "MISMATCHED_COMPOSABLE_IN_EXPECT_ACTUAL$delegate", "COMPOSE_APPLIER_CALL_MISMATCH", "", "getCOMPOSE_APPLIER_CALL_MISMATCH", "COMPOSE_APPLIER_CALL_MISMATCH$delegate", "COMPOSE_APPLIER_PARAMETER_MISMATCH", "getCOMPOSE_APPLIER_PARAMETER_MISMATCH", "COMPOSE_APPLIER_PARAMETER_MISMATCH$delegate", "COMPOSE_APPLIER_DECLARATION_MISMATCH", "getCOMPOSE_APPLIER_DECLARATION_MISMATCH", "COMPOSE_APPLIER_DECLARATION_MISMATCH$delegate", "COMPOSABLE_INAPPLICABLE_TYPE", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getCOMPOSABLE_INAPPLICABLE_TYPE", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", "COMPOSABLE_INAPPLICABLE_TYPE$delegate", "OPEN_COMPOSABLE_DEFAULT_PARAMETER_VALUE", "Lorg/jetbrains/kotlin/config/LanguageVersion;", "getOPEN_COMPOSABLE_DEFAULT_PARAMETER_VALUE", "OPEN_COMPOSABLE_DEFAULT_PARAMETER_VALUE$delegate", "ABSTRACT_COMPOSABLE_DEFAULT_PARAMETER_VALUE", "getABSTRACT_COMPOSABLE_DEFAULT_PARAMETER_VALUE", "ABSTRACT_COMPOSABLE_DEFAULT_PARAMETER_VALUE$delegate", "KEY_CALL_WITH_NO_ARGUMENTS", "getKEY_CALL_WITH_NO_ARGUMENTS", "KEY_CALL_WITH_NO_ARGUMENTS$delegate", "getRendererFactory", "Lorg/jetbrains/kotlin/diagnostics/rendering/BaseDiagnosticRendererFactory;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ComposeErrors extends KtDiagnosticsContainer {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;

    /* JADX INFO: renamed from: ABSTRACT_COMPOSABLE_DEFAULT_PARAMETER_VALUE$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty ABSTRACT_COMPOSABLE_DEFAULT_PARAMETER_VALUE;

    /* JADX INFO: renamed from: CAPTURED_COMPOSABLE_INVOCATION$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty CAPTURED_COMPOSABLE_INVOCATION;

    /* JADX INFO: renamed from: COMPOSABLE_EXPECTED$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty COMPOSABLE_EXPECTED;

    /* JADX INFO: renamed from: COMPOSABLE_FUN_MAIN$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty COMPOSABLE_FUN_MAIN;

    /* JADX INFO: renamed from: COMPOSABLE_INAPPLICABLE_TYPE$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty COMPOSABLE_INAPPLICABLE_TYPE;

    /* JADX INFO: renamed from: COMPOSABLE_INVOCATION$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty COMPOSABLE_INVOCATION;

    /* JADX INFO: renamed from: COMPOSABLE_PROPERTY_BACKING_FIELD$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty COMPOSABLE_PROPERTY_BACKING_FIELD;

    /* JADX INFO: renamed from: COMPOSABLE_PROPERTY_REFERENCE$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty COMPOSABLE_PROPERTY_REFERENCE;

    /* JADX INFO: renamed from: COMPOSABLE_SUSPEND_FUN$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty COMPOSABLE_SUSPEND_FUN;

    /* JADX INFO: renamed from: COMPOSABLE_VAR$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty COMPOSABLE_VAR;

    /* JADX INFO: renamed from: COMPOSE_APPLIER_CALL_MISMATCH$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty COMPOSE_APPLIER_CALL_MISMATCH;

    /* JADX INFO: renamed from: COMPOSE_APPLIER_DECLARATION_MISMATCH$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty COMPOSE_APPLIER_DECLARATION_MISMATCH;

    /* JADX INFO: renamed from: COMPOSE_APPLIER_PARAMETER_MISMATCH$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty COMPOSE_APPLIER_PARAMETER_MISMATCH;

    /* JADX INFO: renamed from: COMPOSE_INVALID_DELEGATE$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty COMPOSE_INVALID_DELEGATE;

    /* JADX INFO: renamed from: DEPRECATED_OPEN_COMPOSABLE_DEFAULT_PARAMETER_VALUE$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty DEPRECATED_OPEN_COMPOSABLE_DEFAULT_PARAMETER_VALUE;

    /* JADX INFO: renamed from: ILLEGAL_RUN_CATCHING_AROUND_COMPOSABLE$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty ILLEGAL_RUN_CATCHING_AROUND_COMPOSABLE;

    /* JADX INFO: renamed from: ILLEGAL_TRY_CATCH_AROUND_COMPOSABLE$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty ILLEGAL_TRY_CATCH_AROUND_COMPOSABLE;
    public static final ComposeErrors INSTANCE;

    /* JADX INFO: renamed from: KEY_CALL_WITH_NO_ARGUMENTS$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty KEY_CALL_WITH_NO_ARGUMENTS;

    /* JADX INFO: renamed from: MISMATCHED_COMPOSABLE_IN_EXPECT_ACTUAL$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty MISMATCHED_COMPOSABLE_IN_EXPECT_ACTUAL;

    /* JADX INFO: renamed from: MISSING_DISALLOW_COMPOSABLE_CALLS_ANNOTATION$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty MISSING_DISALLOW_COMPOSABLE_CALLS_ANNOTATION;

    /* JADX INFO: renamed from: NONREADONLY_CALL_IN_READONLY_COMPOSABLE$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty NONREADONLY_CALL_IN_READONLY_COMPOSABLE;

    /* JADX INFO: renamed from: OPEN_COMPOSABLE_DEFAULT_PARAMETER_VALUE$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty OPEN_COMPOSABLE_DEFAULT_PARAMETER_VALUE;

    static {
        KProperty<Object>[] kPropertyArr = {new PropertyReference1Impl<>(ComposeErrors.class, "COMPOSABLE_INVOCATION", "getCOMPOSABLE_INVOCATION()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", 0), new PropertyReference1Impl<>(ComposeErrors.class, "COMPOSABLE_EXPECTED", "getCOMPOSABLE_EXPECTED()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", 0), new PropertyReference1Impl<>(ComposeErrors.class, "NONREADONLY_CALL_IN_READONLY_COMPOSABLE", "getNONREADONLY_CALL_IN_READONLY_COMPOSABLE()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", 0), new PropertyReference1Impl<>(ComposeErrors.class, "CAPTURED_COMPOSABLE_INVOCATION", "getCAPTURED_COMPOSABLE_INVOCATION()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory2;", 0), new PropertyReference1Impl<>(ComposeErrors.class, "ILLEGAL_TRY_CATCH_AROUND_COMPOSABLE", "getILLEGAL_TRY_CATCH_AROUND_COMPOSABLE()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", 0), new PropertyReference1Impl<>(ComposeErrors.class, "ILLEGAL_RUN_CATCHING_AROUND_COMPOSABLE", "getILLEGAL_RUN_CATCHING_AROUND_COMPOSABLE()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", 0), new PropertyReference1Impl<>(ComposeErrors.class, "MISSING_DISALLOW_COMPOSABLE_CALLS_ANNOTATION", "getMISSING_DISALLOW_COMPOSABLE_CALLS_ANNOTATION()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory3;", 0), new PropertyReference1Impl<>(ComposeErrors.class, "DEPRECATED_OPEN_COMPOSABLE_DEFAULT_PARAMETER_VALUE", "getDEPRECATED_OPEN_COMPOSABLE_DEFAULT_PARAMETER_VALUE()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", 0), new PropertyReference1Impl<>(ComposeErrors.class, "COMPOSABLE_SUSPEND_FUN", "getCOMPOSABLE_SUSPEND_FUN()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", 0), new PropertyReference1Impl<>(ComposeErrors.class, "COMPOSABLE_FUN_MAIN", "getCOMPOSABLE_FUN_MAIN()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", 0), new PropertyReference1Impl<>(ComposeErrors.class, "COMPOSABLE_PROPERTY_REFERENCE", "getCOMPOSABLE_PROPERTY_REFERENCE()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", 0), new PropertyReference1Impl<>(ComposeErrors.class, "COMPOSABLE_PROPERTY_BACKING_FIELD", "getCOMPOSABLE_PROPERTY_BACKING_FIELD()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", 0), new PropertyReference1Impl<>(ComposeErrors.class, "COMPOSABLE_VAR", "getCOMPOSABLE_VAR()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", 0), new PropertyReference1Impl<>(ComposeErrors.class, "COMPOSE_INVALID_DELEGATE", "getCOMPOSE_INVALID_DELEGATE()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", 0), new PropertyReference1Impl<>(ComposeErrors.class, "MISMATCHED_COMPOSABLE_IN_EXPECT_ACTUAL", "getMISMATCHED_COMPOSABLE_IN_EXPECT_ACTUAL()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", 0), new PropertyReference1Impl<>(ComposeErrors.class, "COMPOSE_APPLIER_CALL_MISMATCH", "getCOMPOSE_APPLIER_CALL_MISMATCH()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory2;", 0), new PropertyReference1Impl<>(ComposeErrors.class, "COMPOSE_APPLIER_PARAMETER_MISMATCH", "getCOMPOSE_APPLIER_PARAMETER_MISMATCH()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory2;", 0), new PropertyReference1Impl<>(ComposeErrors.class, "COMPOSE_APPLIER_DECLARATION_MISMATCH", "getCOMPOSE_APPLIER_DECLARATION_MISMATCH()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", 0), new PropertyReference1Impl<>(ComposeErrors.class, "COMPOSABLE_INAPPLICABLE_TYPE", "getCOMPOSABLE_INAPPLICABLE_TYPE()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", 0), new PropertyReference1Impl<>(ComposeErrors.class, "OPEN_COMPOSABLE_DEFAULT_PARAMETER_VALUE", "getOPEN_COMPOSABLE_DEFAULT_PARAMETER_VALUE()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", 0), new PropertyReference1Impl<>(ComposeErrors.class, "ABSTRACT_COMPOSABLE_DEFAULT_PARAMETER_VALUE", "getABSTRACT_COMPOSABLE_DEFAULT_PARAMETER_VALUE()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", 0), new PropertyReference1Impl<>(ComposeErrors.class, "KEY_CALL_WITH_NO_ARGUMENTS", "getKEY_CALL_WITH_NO_ARGUMENTS()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", 0)};
        $$delegatedProperties = kPropertyArr;
        ComposeErrors composeErrors = new ComposeErrors();
        INSTANCE = composeErrors;
        SourceElementPositioningStrategies sourceElementPositioningStrategies = SourceElementPositioningStrategies.INSTANCE;
        SourceElementPositioningStrategy sourceElementPositioningStrategy = sourceElementPositioningStrategies.getDEFAULT();
        Severity severity = Severity.ERROR;
        COMPOSABLE_INVOCATION = new DiagnosticFactory0DelegateProvider(severity, sourceElementPositioningStrategy, Reflection.getOrCreateKotlinClass(PsiElement.class), composeErrors).provideDelegate(composeErrors, kPropertyArr[0]);
        COMPOSABLE_EXPECTED = new DiagnosticFactory0DelegateProvider(severity, sourceElementPositioningStrategies.getDECLARATION_NAME(), Reflection.getOrCreateKotlinClass(PsiElement.class), composeErrors).provideDelegate(composeErrors, kPropertyArr[1]);
        NONREADONLY_CALL_IN_READONLY_COMPOSABLE = new DiagnosticFactory0DelegateProvider(severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), composeErrors).provideDelegate(composeErrors, kPropertyArr[2]);
        CAPTURED_COMPOSABLE_INVOCATION = new DiagnosticFactory2DelegateProvider(severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), composeErrors).provideDelegate(composeErrors, kPropertyArr[3]);
        ComposeSourceElementPositioningStrategies composeSourceElementPositioningStrategies = ComposeSourceElementPositioningStrategies.INSTANCE;
        ILLEGAL_TRY_CATCH_AROUND_COMPOSABLE = new DiagnosticFactory0DelegateProvider(severity, composeSourceElementPositioningStrategies.getTRY_KEYWORD(), Reflection.getOrCreateKotlinClass(KtTryExpression.class), composeErrors).provideDelegate(composeErrors, kPropertyArr[4]);
        ILLEGAL_RUN_CATCHING_AROUND_COMPOSABLE = new DiagnosticFactory0DelegateProvider(severity, sourceElementPositioningStrategies.getREFERENCED_NAME_BY_QUALIFIED(), Reflection.getOrCreateKotlinClass(KtCallExpression.class), composeErrors).provideDelegate(composeErrors, kPropertyArr[5]);
        MISSING_DISALLOW_COMPOSABLE_CALLS_ANNOTATION = new DiagnosticFactory3DelegateProvider(severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), composeErrors).provideDelegate(composeErrors, kPropertyArr[6]);
        SourceElementPositioningStrategy sourceElementPositioningStrategy2 = sourceElementPositioningStrategies.getDEFAULT();
        Severity severity2 = Severity.WARNING;
        DEPRECATED_OPEN_COMPOSABLE_DEFAULT_PARAMETER_VALUE = new DiagnosticFactory0DelegateProvider(severity2, sourceElementPositioningStrategy2, Reflection.getOrCreateKotlinClass(PsiElement.class), composeErrors).provideDelegate(composeErrors, kPropertyArr[7]);
        COMPOSABLE_SUSPEND_FUN = new DiagnosticFactory0DelegateProvider(severity, sourceElementPositioningStrategies.getDECLARATION_NAME(), Reflection.getOrCreateKotlinClass(PsiElement.class), composeErrors).provideDelegate(composeErrors, kPropertyArr[8]);
        COMPOSABLE_FUN_MAIN = new DiagnosticFactory0DelegateProvider(severity, sourceElementPositioningStrategies.getDECLARATION_NAME(), Reflection.getOrCreateKotlinClass(PsiElement.class), composeErrors).provideDelegate(composeErrors, kPropertyArr[9]);
        COMPOSABLE_PROPERTY_REFERENCE = new DiagnosticFactory0DelegateProvider(severity, composeSourceElementPositioningStrategies.getDECLARATION_NAME_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), composeErrors).provideDelegate(composeErrors, kPropertyArr[10]);
        COMPOSABLE_PROPERTY_BACKING_FIELD = new DiagnosticFactory0DelegateProvider(severity, sourceElementPositioningStrategies.getDECLARATION_NAME(), Reflection.getOrCreateKotlinClass(PsiElement.class), composeErrors).provideDelegate(composeErrors, kPropertyArr[11]);
        COMPOSABLE_VAR = new DiagnosticFactory0DelegateProvider(severity, sourceElementPositioningStrategies.getDECLARATION_NAME(), Reflection.getOrCreateKotlinClass(PsiElement.class), composeErrors).provideDelegate(composeErrors, kPropertyArr[12]);
        COMPOSE_INVALID_DELEGATE = new DiagnosticFactory0DelegateProvider(severity, composeSourceElementPositioningStrategies.getDECLARATION_NAME_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), composeErrors).provideDelegate(composeErrors, kPropertyArr[13]);
        MISMATCHED_COMPOSABLE_IN_EXPECT_ACTUAL = new DiagnosticFactory0DelegateProvider(severity, sourceElementPositioningStrategies.getDECLARATION_NAME(), Reflection.getOrCreateKotlinClass(PsiElement.class), composeErrors).provideDelegate(composeErrors, kPropertyArr[14]);
        LightTreePositioningStrategies lightTreePositioningStrategies = LightTreePositioningStrategies.INSTANCE;
        LightTreePositioningStrategy referenced_name_by_qualified = lightTreePositioningStrategies.getREFERENCED_NAME_BY_QUALIFIED();
        PositioningStrategy positioningStrategy = PositioningStrategies.CALL_EXPRESSION;
        COMPOSE_APPLIER_CALL_MISMATCH = new DiagnosticFactory2DelegateProvider(severity2, new SourceElementPositioningStrategy(referenced_name_by_qualified, positioningStrategy, (OffsetsOnlyPositioningStrategy) null, 4, (DefaultConstructorMarker) null), Reflection.getOrCreateKotlinClass(PsiElement.class), composeErrors).provideDelegate(composeErrors, kPropertyArr[15]);
        COMPOSE_APPLIER_PARAMETER_MISMATCH = new DiagnosticFactory2DelegateProvider(severity2, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), composeErrors).provideDelegate(composeErrors, kPropertyArr[16]);
        COMPOSE_APPLIER_DECLARATION_MISMATCH = new DiagnosticFactory0DelegateProvider(severity2, composeSourceElementPositioningStrategies.getDECLARATION_NAME_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), composeErrors).provideDelegate(composeErrors, kPropertyArr[17]);
        COMPOSABLE_INAPPLICABLE_TYPE = new DiagnosticFactory1DelegateProvider(severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), composeErrors).provideDelegate(composeErrors, kPropertyArr[18]);
        OPEN_COMPOSABLE_DEFAULT_PARAMETER_VALUE = new DiagnosticFactory1DelegateProvider(severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), composeErrors).provideDelegate(composeErrors, kPropertyArr[19]);
        ABSTRACT_COMPOSABLE_DEFAULT_PARAMETER_VALUE = new DiagnosticFactory1DelegateProvider(severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), composeErrors).provideDelegate(composeErrors, kPropertyArr[20]);
        KEY_CALL_WITH_NO_ARGUMENTS = new DiagnosticFactory0DelegateProvider(severity, new SourceElementPositioningStrategy(lightTreePositioningStrategies.getREFERENCED_NAME_BY_QUALIFIED(), positioningStrategy, (OffsetsOnlyPositioningStrategy) null, 4, (DefaultConstructorMarker) null), Reflection.getOrCreateKotlinClass(PsiElement.class), composeErrors).provideDelegate(composeErrors, kPropertyArr[21]);
    }

    private ComposeErrors() {
    }

    public final KtDiagnosticFactory1<LanguageVersion> getABSTRACT_COMPOSABLE_DEFAULT_PARAMETER_VALUE() {
        return (KtDiagnosticFactory1) ABSTRACT_COMPOSABLE_DEFAULT_PARAMETER_VALUE.getValue(this, $$delegatedProperties[20]);
    }

    public final KtDiagnosticFactory2<FirVariableSymbol<?>, FirCallableSymbol<?>> getCAPTURED_COMPOSABLE_INVOCATION() {
        return (KtDiagnosticFactory2) CAPTURED_COMPOSABLE_INVOCATION.getValue(this, $$delegatedProperties[3]);
    }

    public final KtDiagnosticFactory0 getCOMPOSABLE_EXPECTED() {
        return (KtDiagnosticFactory0) COMPOSABLE_EXPECTED.getValue(this, $$delegatedProperties[1]);
    }

    public final KtDiagnosticFactory0 getCOMPOSABLE_FUN_MAIN() {
        return (KtDiagnosticFactory0) COMPOSABLE_FUN_MAIN.getValue(this, $$delegatedProperties[9]);
    }

    public final KtDiagnosticFactory1<ConeKotlinType> getCOMPOSABLE_INAPPLICABLE_TYPE() {
        return (KtDiagnosticFactory1) COMPOSABLE_INAPPLICABLE_TYPE.getValue(this, $$delegatedProperties[18]);
    }

    public final KtDiagnosticFactory0 getCOMPOSABLE_INVOCATION() {
        return (KtDiagnosticFactory0) COMPOSABLE_INVOCATION.getValue(this, $$delegatedProperties[0]);
    }

    public final KtDiagnosticFactory0 getCOMPOSABLE_PROPERTY_BACKING_FIELD() {
        return (KtDiagnosticFactory0) COMPOSABLE_PROPERTY_BACKING_FIELD.getValue(this, $$delegatedProperties[11]);
    }

    public final KtDiagnosticFactory0 getCOMPOSABLE_PROPERTY_REFERENCE() {
        return (KtDiagnosticFactory0) COMPOSABLE_PROPERTY_REFERENCE.getValue(this, $$delegatedProperties[10]);
    }

    public final KtDiagnosticFactory0 getCOMPOSABLE_SUSPEND_FUN() {
        return (KtDiagnosticFactory0) COMPOSABLE_SUSPEND_FUN.getValue(this, $$delegatedProperties[8]);
    }

    public final KtDiagnosticFactory0 getCOMPOSABLE_VAR() {
        return (KtDiagnosticFactory0) COMPOSABLE_VAR.getValue(this, $$delegatedProperties[12]);
    }

    public final KtDiagnosticFactory2<String, String> getCOMPOSE_APPLIER_CALL_MISMATCH() {
        return (KtDiagnosticFactory2) COMPOSE_APPLIER_CALL_MISMATCH.getValue(this, $$delegatedProperties[15]);
    }

    public final KtDiagnosticFactory0 getCOMPOSE_APPLIER_DECLARATION_MISMATCH() {
        return (KtDiagnosticFactory0) COMPOSE_APPLIER_DECLARATION_MISMATCH.getValue(this, $$delegatedProperties[17]);
    }

    public final KtDiagnosticFactory2<String, String> getCOMPOSE_APPLIER_PARAMETER_MISMATCH() {
        return (KtDiagnosticFactory2) COMPOSE_APPLIER_PARAMETER_MISMATCH.getValue(this, $$delegatedProperties[16]);
    }

    public final KtDiagnosticFactory0 getCOMPOSE_INVALID_DELEGATE() {
        return (KtDiagnosticFactory0) COMPOSE_INVALID_DELEGATE.getValue(this, $$delegatedProperties[13]);
    }

    public final KtDiagnosticFactory0 getDEPRECATED_OPEN_COMPOSABLE_DEFAULT_PARAMETER_VALUE() {
        return (KtDiagnosticFactory0) DEPRECATED_OPEN_COMPOSABLE_DEFAULT_PARAMETER_VALUE.getValue(this, $$delegatedProperties[7]);
    }

    public final KtDiagnosticFactory0 getILLEGAL_RUN_CATCHING_AROUND_COMPOSABLE() {
        return (KtDiagnosticFactory0) ILLEGAL_RUN_CATCHING_AROUND_COMPOSABLE.getValue(this, $$delegatedProperties[5]);
    }

    public final KtDiagnosticFactory0 getILLEGAL_TRY_CATCH_AROUND_COMPOSABLE() {
        return (KtDiagnosticFactory0) ILLEGAL_TRY_CATCH_AROUND_COMPOSABLE.getValue(this, $$delegatedProperties[4]);
    }

    public final KtDiagnosticFactory0 getKEY_CALL_WITH_NO_ARGUMENTS() {
        return (KtDiagnosticFactory0) KEY_CALL_WITH_NO_ARGUMENTS.getValue(this, $$delegatedProperties[21]);
    }

    public final KtDiagnosticFactory0 getMISMATCHED_COMPOSABLE_IN_EXPECT_ACTUAL() {
        return (KtDiagnosticFactory0) MISMATCHED_COMPOSABLE_IN_EXPECT_ACTUAL.getValue(this, $$delegatedProperties[14]);
    }

    public final KtDiagnosticFactory3<FirValueParameterSymbol, FirValueParameterSymbol, FirCallableSymbol<?>> getMISSING_DISALLOW_COMPOSABLE_CALLS_ANNOTATION() {
        return (KtDiagnosticFactory3) MISSING_DISALLOW_COMPOSABLE_CALLS_ANNOTATION.getValue(this, $$delegatedProperties[6]);
    }

    public final KtDiagnosticFactory0 getNONREADONLY_CALL_IN_READONLY_COMPOSABLE() {
        return (KtDiagnosticFactory0) NONREADONLY_CALL_IN_READONLY_COMPOSABLE.getValue(this, $$delegatedProperties[2]);
    }

    public final KtDiagnosticFactory1<LanguageVersion> getOPEN_COMPOSABLE_DEFAULT_PARAMETER_VALUE() {
        return (KtDiagnosticFactory1) OPEN_COMPOSABLE_DEFAULT_PARAMETER_VALUE.getValue(this, $$delegatedProperties[19]);
    }

    public BaseDiagnosticRendererFactory getRendererFactory() {
        return ComposeErrorMessages.INSTANCE;
    }
}
