package androidx.compose.compiler.plugins.kotlin.k2;

import androidx.compose.compiler.plugins.kotlin.k2.ComposeErrorMessages;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryToRendererMap;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryToRendererMapKt;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticRenderers;
import org.jetbrains.kotlin.diagnostics.rendering.BaseDiagnosticRendererFactory;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirDiagnosticRenderers;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/k2/ComposeErrorMessages;", "Lorg/jetbrains/kotlin/diagnostics/rendering/BaseDiagnosticRendererFactory;", "<init>", "()V", "MAP", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryToRendererMap;", "getMAP", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryToRendererMap;", "MAP$delegate", "Lkotlin/Lazy;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ComposeErrorMessages extends BaseDiagnosticRendererFactory {
    public static final ComposeErrorMessages INSTANCE = new ComposeErrorMessages();

    /* JADX INFO: renamed from: MAP$delegate, reason: from kotlin metadata */
    private static final Lazy MAP = KtDiagnosticFactoryToRendererMapKt.KtDiagnosticFactoryToRendererMap("Compose", new Function1() { // from class: im2
        public final Object invoke(Object obj) {
            return ComposeErrorMessages.a((KtDiagnosticFactoryToRendererMap) obj);
        }
    });

    private ComposeErrorMessages() {
    }

    public static Unit a(KtDiagnosticFactoryToRendererMap ktDiagnosticFactoryToRendererMap) {
        ktDiagnosticFactoryToRendererMap.getClass();
        ComposeErrors composeErrors = ComposeErrors.INSTANCE;
        ktDiagnosticFactoryToRendererMap.put(composeErrors.getCOMPOSABLE_INVOCATION(), "@Composable invocations can only happen from the context of a @Composable function");
        ktDiagnosticFactoryToRendererMap.put(composeErrors.getCOMPOSABLE_EXPECTED(), "Functions which invoke @Composable functions must be marked with the @Composable annotation");
        ktDiagnosticFactoryToRendererMap.put(composeErrors.getNONREADONLY_CALL_IN_READONLY_COMPOSABLE(), "Composables marked with @ReadOnlyComposable can only call other @ReadOnlyComposable composables");
        KtDiagnosticFactory2<FirVariableSymbol<?>, FirCallableSymbol<?>> captured_composable_invocation = composeErrors.getCAPTURED_COMPOSABLE_INVOCATION();
        FirDiagnosticRenderers firDiagnosticRenderers = FirDiagnosticRenderers.INSTANCE;
        ktDiagnosticFactoryToRendererMap.put(captured_composable_invocation, "Composable calls are not allowed inside the {0} parameter of {1}", firDiagnosticRenderers.getVARIABLE_NAME(), firDiagnosticRenderers.getDECLARATION_NAME());
        ktDiagnosticFactoryToRendererMap.put(composeErrors.getILLEGAL_TRY_CATCH_AROUND_COMPOSABLE(), "Try catch is not supported around composable function invocations.");
        ktDiagnosticFactoryToRendererMap.put(composeErrors.getILLEGAL_RUN_CATCHING_AROUND_COMPOSABLE(), "runCatching call is not allowed to contain @Composable function invocations");
        ktDiagnosticFactoryToRendererMap.put(composeErrors.getMISSING_DISALLOW_COMPOSABLE_CALLS_ANNOTATION(), "Parameter {0} cannot be inlined inside of lambda argument {1} of {2} without also being annotated with @DisallowComposableCalls", firDiagnosticRenderers.getVARIABLE_NAME(), firDiagnosticRenderers.getVARIABLE_NAME(), firDiagnosticRenderers.getDECLARATION_NAME());
        ktDiagnosticFactoryToRendererMap.put(composeErrors.getDEPRECATED_OPEN_COMPOSABLE_DEFAULT_PARAMETER_VALUE(), "Detected a @Composable function that overrides an open function compiled with older compiler that is known to crash at runtime. Consider recompiling the dependency with a newer compiler version (>= 2.1.20) to get correct behavior. See https://issuetracker.google.com/165812010 for more details.");
        ktDiagnosticFactoryToRendererMap.put(composeErrors.getCOMPOSABLE_SUSPEND_FUN(), "Composable function cannot be annotated as suspend");
        ktDiagnosticFactoryToRendererMap.put(composeErrors.getCOMPOSABLE_FUN_MAIN(), "Composable main functions are not currently supported");
        ktDiagnosticFactoryToRendererMap.put(composeErrors.getCOMPOSABLE_PROPERTY_REFERENCE(), "@Composable property references are not currently supported.");
        ktDiagnosticFactoryToRendererMap.put(composeErrors.getCOMPOSABLE_PROPERTY_BACKING_FIELD(), "Composable properties are not able to have backing fields");
        ktDiagnosticFactoryToRendererMap.put(composeErrors.getCOMPOSABLE_VAR(), "Composable properties are not able to have backing fields");
        ktDiagnosticFactoryToRendererMap.put(composeErrors.getCOMPOSE_INVALID_DELEGATE(), "Composable setValue operator is not currently supported.");
        ktDiagnosticFactoryToRendererMap.put(composeErrors.getMISMATCHED_COMPOSABLE_IN_EXPECT_ACTUAL(), "Mismatched @Composable annotation between expect and actual declaration");
        ktDiagnosticFactoryToRendererMap.put(composeErrors.getCOMPOSABLE_INAPPLICABLE_TYPE(), "@Composable annotation is not applicable to {0}", firDiagnosticRenderers.getRENDER_TYPE());
        KtDiagnosticFactory2<String, String> compose_applier_call_mismatch = composeErrors.getCOMPOSE_APPLIER_CALL_MISMATCH();
        KtDiagnosticRenderers ktDiagnosticRenderers = KtDiagnosticRenderers.INSTANCE;
        ktDiagnosticFactoryToRendererMap.put(compose_applier_call_mismatch, "Calling a {1} composable function where a {0} composable was expected", ktDiagnosticRenderers.getTO_STRING(), ktDiagnosticRenderers.getTO_STRING());
        ktDiagnosticFactoryToRendererMap.put(composeErrors.getCOMPOSE_APPLIER_PARAMETER_MISMATCH(), "A {1} composable parameter was provided where a {0} composable was expected", ktDiagnosticRenderers.getTO_STRING(), ktDiagnosticRenderers.getTO_STRING());
        ktDiagnosticFactoryToRendererMap.put(composeErrors.getCOMPOSE_APPLIER_DECLARATION_MISMATCH(), "The composition target of an override must match the ancestor target");
        ktDiagnosticFactoryToRendererMap.put(composeErrors.getABSTRACT_COMPOSABLE_DEFAULT_PARAMETER_VALUE(), "Default parameters in abstract @Composable functions are not supported before language version 2.1 (configured version is {0})", ktDiagnosticRenderers.getTO_STRING());
        ktDiagnosticFactoryToRendererMap.put(composeErrors.getOPEN_COMPOSABLE_DEFAULT_PARAMETER_VALUE(), "Default parameters in open @Composable functions are not supported before language version 2.2 (configured version is {0})", ktDiagnosticRenderers.getTO_STRING());
        ktDiagnosticFactoryToRendererMap.put(composeErrors.getKEY_CALL_WITH_NO_ARGUMENTS(), "key requires at least one argument.");
        return Unit.INSTANCE;
    }

    public KtDiagnosticFactoryToRendererMap getMAP() {
        return (KtDiagnosticFactoryToRendererMap) MAP.getValue();
    }
}
