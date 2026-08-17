package androidx.compose.compiler.plugins.kotlin.k2;

import androidx.compose.compiler.plugins.kotlin.ComposeMetadata;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0002H\u0002¨\u0006\u0003"}, d2 = {"isMissingCompatMetadata", "", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class ComposableFunctionCheckerKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isMissingCompatMetadata(FirFunctionSymbol<?> firFunctionSymbol) {
        if (!Intrinsics.areEqual(firFunctionSymbol.getOrigin(), FirDeclarationOrigin.Library.INSTANCE)) {
            return false;
        }
        byte[] composeMetadata = FirUtilsKt.getComposeMetadata(firFunctionSymbol.getFir());
        return composeMetadata == null || !ComposeMetadata.m272supportsOpenFunctionsWithDefaultParamsimpl(composeMetadata);
    }
}
