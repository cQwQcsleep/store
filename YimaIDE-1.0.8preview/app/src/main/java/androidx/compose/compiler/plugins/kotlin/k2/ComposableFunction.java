package androidx.compose.compiler.plugins.kotlin.k2;

import androidx.compose.compiler.plugins.kotlin.ComposeClassIds;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKind;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u000e\u001a\u00020\u0001H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000f"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/k2/ComposableFunction;", "Lorg/jetbrains/kotlin/builtins/functions/FunctionTypeKind;", "<init>", "()V", "prefixForTypeRender", "", "getPrefixForTypeRender", "()Ljava/lang/String;", "serializeAsFunctionWithAnnotationUntil", "getSerializeAsFunctionWithAnnotationUntil", "supportsConversionFromSimpleFunctionType", "", "getSupportsConversionFromSimpleFunctionType", "()Z", "reflectKind", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ComposableFunction extends FunctionTypeKind {
    public static final ComposableFunction INSTANCE = new ComposableFunction();
    private static final String prefixForTypeRender = "@Composable";
    private static final String serializeAsFunctionWithAnnotationUntil = ComposeFirExtensionsKt.access$getUseLegacyCustomFunctionTypeSerializationUntil();
    private static final boolean supportsConversionFromSimpleFunctionType = false;

    private ComposableFunction() {
        super(new FqName("androidx.compose.runtime.internal"), "ComposableFunction", ComposeClassIds.INSTANCE.getComposable(), false, true, 0, 32, (DefaultConstructorMarker) null);
    }

    public String getPrefixForTypeRender() {
        return prefixForTypeRender;
    }

    public String getSerializeAsFunctionWithAnnotationUntil() {
        return serializeAsFunctionWithAnnotationUntil;
    }

    public boolean getSupportsConversionFromSimpleFunctionType() {
        return supportsConversionFromSimpleFunctionType;
    }

    public FunctionTypeKind reflectKind() {
        return KComposableFunction.INSTANCE;
    }
}
