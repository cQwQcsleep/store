package androidx.compose.animation;

import androidx.compose.ui.modifier.ModifierLocalKt;
import androidx.compose.ui.modifier.ProvidableModifierLocal;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u001c\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"ModifierLocalSharedElementInternalState", "Landroidx/compose/ui/modifier/ProvidableModifierLocal;", "Landroidx/compose/animation/SharedElementEntry;", "getModifierLocalSharedElementInternalState", "()Landroidx/compose/ui/modifier/ProvidableModifierLocal;", "animation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SharedContentNodeKt {
    private static final ProvidableModifierLocal<SharedElementEntry> ModifierLocalSharedElementInternalState = ModifierLocalKt.modifierLocalOf(new Function0<SharedElementEntry>() { // from class: androidx.compose.animation.SharedContentNodeKt$ModifierLocalSharedElementInternalState$1
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final SharedElementEntry m141invoke() {
            return null;
        }
    });

    public static final ProvidableModifierLocal<SharedElementEntry> getModifierLocalSharedElementInternalState() {
        return ModifierLocalSharedElementInternalState;
    }
}
