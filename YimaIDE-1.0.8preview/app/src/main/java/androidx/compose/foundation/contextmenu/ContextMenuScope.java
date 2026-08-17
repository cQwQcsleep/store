package androidx.compose.foundation.contextmenu;

import androidx.compose.foundation.contextmenu.ContextMenuColors;
import androidx.compose.foundation.contextmenu.ContextMenuScope;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function8;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\b\u0001\u0018\u00002\u00020\u0001BÄ\u0001\b\u0000\u0012¸\u0001\u0010\u0002\u001a³\u0001\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\r\u00125\u00123\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u000e¢\u0006\u0002\b\u0012¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0013\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00110\u0014¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00110\u0003¢\u0006\u0002\b\u0012¢\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u001b\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\fH\u0001¢\u0006\u0004\b\u001c\u0010\u001dJ\r\u0010\u001e\u001a\u00020\u0011H\u0000¢\u0006\u0002\b\u001fJl\u0010 \u001a\u00020\u00112\u0011\u0010\t\u001a\r\u0012\u0004\u0012\u00020\b0\u0014¢\u0006\u0002\b\u00122\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\n2*\b\u0002\u0010\u0013\u001a$\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u000e¢\u0006\u0002\b\u00122\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00110\u0014¢\u0006\u0002\u0010!J\u0006\u0010\"\u001a\u00020\u0011RÂ\u0001\u0010\u0002\u001a³\u0001\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\r\u00125\u00123\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u000e¢\u0006\u0002\b\u0012¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0013\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00110\u0014¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00110\u0003¢\u0006\u0002\b\u0012X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0018R4\u0010\u0019\u001a(\u0012$\u0012\"\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00110\u000e¢\u0006\u0002\b\u00120\u001aX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Landroidx/compose/foundation/contextmenu/ContextMenuScope;", "", "itemUi", "Lkotlin/Function6;", "Landroidx/compose/ui/Modifier;", "Lkotlin/ParameterName;", "name", "modifier", "", "label", "", "enabled", "Landroidx/compose/foundation/contextmenu/ContextMenuColors;", "colors", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/Color;", "iconColor", "", "Landroidx/compose/runtime/Composable;", "leadingIcon", "Lkotlin/Function0;", "onClick", "<init>", "(Lkotlin/jvm/functions/Function8;)V", "Lkotlin/jvm/functions/Function8;", "composables", "Landroidx/compose/runtime/snapshots/SnapshotStateList;", "Content", "Content$foundation", "(Landroidx/compose/foundation/contextmenu/ContextMenuColors;Landroidx/compose/runtime/Composer;I)V", "clear", "clear$foundation", "item", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function0;)V", "separator", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ContextMenuScope {
    public static final int $stable = 0;
    private final SnapshotStateList<Function3<ContextMenuColors, Composer, Integer, Unit>> composables = SnapshotStateKt.mutableStateListOf();
    private final Function8<Modifier, String, Boolean, ContextMenuColors, Function3<? super Color, ? super Composer, ? super Integer, Unit>, Function0<Unit>, Composer, Integer, Unit> itemUi;

    public ContextMenuScope(Function8<? super Modifier, ? super String, ? super Boolean, ? super ContextMenuColors, ? super Function3<? super Color, ? super Composer, ? super Integer, Unit>, ? super Function0<Unit>, ? super Composer, ? super Integer, Unit> function8) {
        this.itemUi = function8;
    }

    public static Unit a(Function2 function2, ContextMenuScope contextMenuScope, Modifier modifier, boolean z, Function3 function3, Function0 function0, ContextMenuColors contextMenuColors, Composer composer, int i) {
        int i2;
        if ((i & 6) == 0) {
            i2 = i | (composer.changed(contextMenuColors) ? 4 : 2);
        } else {
            i2 = i;
        }
        if (composer.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1789283891, i2, -1, "androidx.compose.foundation.contextmenu.ContextMenuScope.item.<anonymous> (ContextMenuUi.kt:297)");
            }
            String str = (String) function2.invoke(composer, 0);
            if (StringsKt.isBlank(str)) {
                InlineClassHelperKt.throwIllegalStateException("Label must not be blank");
            }
            contextMenuScope.itemUi.invoke(modifier, str, Boolean.valueOf(z), contextMenuColors, function3, function0, composer, Integer.valueOf((i2 << 9) & 7168));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit b(ContextMenuScope contextMenuScope, ContextMenuColors contextMenuColors, int i, Composer composer, int i2) {
        contextMenuScope.Content$foundation(contextMenuColors, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void item$default(ContextMenuScope contextMenuScope, Function2 function2, Modifier modifier, boolean z, Function3 function3, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            modifier = Modifier.Companion;
        }
        Modifier modifier2 = modifier;
        if ((i & 4) != 0) {
            z = true;
        }
        boolean z2 = z;
        if ((i & 8) != 0) {
            function3 = null;
        }
        contextMenuScope.item(function2, modifier2, z2, function3, function0);
    }

    public final void Content$foundation(final ContextMenuColors contextMenuColors, Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-798501095);
        int i2 = (i & 6) == 0 ? (composerStartRestartGroup.changed(contextMenuColors) ? 4 : 2) | i : i;
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(this) ? 32 : 16;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-798501095, i2, -1, "androidx.compose.foundation.contextmenu.ContextMenuScope.Content (ContextMenuUi.kt:255)");
            }
            SnapshotStateList<Function3<ContextMenuColors, Composer, Integer, Unit>> snapshotStateList = this.composables;
            int size = snapshotStateList.size();
            for (int i3 = 0; i3 < size; i3++) {
                ((Function3) snapshotStateList.get(i3)).invoke(contextMenuColors, composerStartRestartGroup, Integer.valueOf(i2 & 14));
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: dv2
                public final Object invoke(Object obj, Object obj2) {
                    return ContextMenuScope.b(this.b, contextMenuColors, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public final void clear$foundation() {
        this.composables.clear();
    }

    public final void item(final Function2<? super Composer, ? super Integer, String> label, final Modifier modifier, final boolean enabled, final Function3<? super Color, ? super Composer, ? super Integer, Unit> leadingIcon, final Function0<Unit> onClick) {
        this.composables.add(ComposableLambdaKt.composableLambdaInstance(-1789283891, true, new Function3() { // from class: cv2
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return ContextMenuScope.a(label, this, modifier, enabled, leadingIcon, onClick, (ContextMenuColors) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }));
    }

    public final void separator() {
        this.composables.add(ComposableSingletons$ContextMenuUiKt.INSTANCE.m444getLambda$1455401925$foundation());
    }
}
