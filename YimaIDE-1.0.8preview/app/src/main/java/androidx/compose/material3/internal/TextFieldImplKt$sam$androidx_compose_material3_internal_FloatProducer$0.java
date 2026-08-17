package androidx.compose.material3.internal;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Function;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionAdapter;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class TextFieldImplKt$sam$androidx_compose_material3_internal_FloatProducer$0 implements FloatProducer, FunctionAdapter {
    private final /* synthetic */ Function0 function;

    public TextFieldImplKt$sam$androidx_compose_material3_internal_FloatProducer$0(Function0 function0) {
        this.function = function0;
    }

    public final boolean equals(Object obj) {
        if ((obj instanceof FloatProducer) && (obj instanceof FunctionAdapter)) {
            return Intrinsics.areEqual(getFunctionDelegate(), ((FunctionAdapter) obj).getFunctionDelegate());
        }
        return false;
    }

    public final Function<?> getFunctionDelegate() {
        return this.function;
    }

    public final int hashCode() {
        return getFunctionDelegate().hashCode();
    }

    @Override // androidx.compose.material3.internal.FloatProducer
    public final /* synthetic */ float invoke() {
        return ((Number) this.function.invoke()).floatValue();
    }
}
