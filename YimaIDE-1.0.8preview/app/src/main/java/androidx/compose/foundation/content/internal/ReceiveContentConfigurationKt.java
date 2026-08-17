package androidx.compose.foundation.content.internal;

import androidx.compose.foundation.content.internal.ReceiveContentConfigurationKt;
import androidx.compose.ui.modifier.ModifierLocalKt;
import androidx.compose.ui.modifier.ModifierLocalModifierNode;
import androidx.compose.ui.modifier.ProvidableModifierLocal;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0005\u001a\u0004\u0018\u00010\u0002*\u00020\u0006H\u0000\"\u001c\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0007"}, d2 = {"ModifierLocalReceiveContent", "Landroidx/compose/ui/modifier/ProvidableModifierLocal;", "Landroidx/compose/foundation/content/internal/ReceiveContentConfiguration;", "getModifierLocalReceiveContent", "()Landroidx/compose/ui/modifier/ProvidableModifierLocal;", "getReceiveContentConfiguration", "Landroidx/compose/ui/modifier/ModifierLocalModifierNode;", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ReceiveContentConfigurationKt {
    private static final ProvidableModifierLocal<ReceiveContentConfiguration> ModifierLocalReceiveContent = ModifierLocalKt.modifierLocalOf(new Function0() { // from class: k7c
        public final Object invoke() {
            return ReceiveContentConfigurationKt.a();
        }
    });

    public static ReceiveContentConfiguration a() {
        return null;
    }

    public static final ProvidableModifierLocal<ReceiveContentConfiguration> getModifierLocalReceiveContent() {
        return ModifierLocalReceiveContent;
    }

    public static final ReceiveContentConfiguration getReceiveContentConfiguration(ModifierLocalModifierNode modifierLocalModifierNode) {
        if (modifierLocalModifierNode.getNode().isAttached()) {
            return (ReceiveContentConfiguration) modifierLocalModifierNode.getCurrent(ModifierLocalReceiveContent);
        }
        return null;
    }
}
