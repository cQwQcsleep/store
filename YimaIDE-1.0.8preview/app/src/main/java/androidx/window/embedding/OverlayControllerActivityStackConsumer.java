package androidx.window.embedding;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.window.reflection.Consumer2;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B!\u0012\u0018\u0010\u0004\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0016\u0010\t\u001a\u00020\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016R \u0010\u0004\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Landroidx/window/embedding/OverlayControllerActivityStackConsumer;", "Landroidx/window/reflection/Consumer2;", "", "Landroidx/window/extensions/embedding/ActivityStack;", "block", "Lkotlin/Function1;", "", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "accept", "value", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class OverlayControllerActivityStackConsumer implements Consumer2<List<? extends androidx.window.extensions.embedding.ActivityStack>> {
    private final Function1<List<? extends androidx.window.extensions.embedding.ActivityStack>, Unit> block;

    /* JADX WARN: Multi-variable type inference failed */
    public OverlayControllerActivityStackConsumer(Function1<? super List<? extends androidx.window.extensions.embedding.ActivityStack>, Unit> function1) {
        function1.getClass();
        this.block = function1;
    }

    @Override // androidx.window.reflection.Consumer2
    public void accept(List<? extends androidx.window.extensions.embedding.ActivityStack> value) {
        value.getClass();
        this.block.invoke(value);
    }
}
