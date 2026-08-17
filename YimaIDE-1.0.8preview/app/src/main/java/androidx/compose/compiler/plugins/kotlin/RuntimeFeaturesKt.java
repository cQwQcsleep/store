package androidx.compose.compiler.plugins.kotlin;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\"\u0010\u0000\u001a\u00020\u0001*\u0004\u0018\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006¨\u0006\u0007"}, d2 = {"supportsFeature", "", "Landroidx/compose/compiler/plugins/kotlin/ComposeRuntimeVersion;", "feature", "Landroidx/compose/compiler/plugins/kotlin/ComposeRuntimeFeature;", "detector", "Lkotlin/Function0;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class RuntimeFeaturesKt {
    public static final boolean supportsFeature(ComposeRuntimeVersion composeRuntimeVersion, ComposeRuntimeFeature composeRuntimeFeature, Function0<Boolean> function0) {
        composeRuntimeFeature.getClass();
        function0.getClass();
        return composeRuntimeVersion != null ? composeRuntimeVersion.supportsFeature(composeRuntimeFeature) : ((Boolean) function0.invoke()).booleanValue();
    }
}
