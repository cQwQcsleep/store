package androidx.compose.material3;

import androidx.compose.material3.CustomAppBarItem;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0001\u0018\u00002\u00020\u0001B3\u0012\u0011\u0010\u0002\u001a\r\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005\u0012\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00040\u0007¢\u0006\u0002\b\u0005¢\u0006\u0004\b\t\u0010\nJ\r\u0010\r\u001a\u00020\u0004H\u0017¢\u0006\u0002\u0010\u000eJ\u0015\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\bH\u0017¢\u0006\u0002\u0010\u0011R\u001b\u0010\u0002\u001a\r\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000bR!\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00040\u0007¢\u0006\u0002\b\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\f¨\u0006\u0012"}, d2 = {"Landroidx/compose/material3/CustomAppBarItem;", "Landroidx/compose/material3/AppBarItem;", "appbarContent", "Lkotlin/Function0;", "", "Landroidx/compose/runtime/Composable;", "menuContent", "Lkotlin/Function1;", "Landroidx/compose/material3/AppBarMenuState;", "<init>", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;)V", "Lkotlin/jvm/functions/Function2;", "Lkotlin/jvm/functions/Function3;", "AppbarContent", "(Landroidx/compose/runtime/Composer;I)V", "MenuContent", "state", "(Landroidx/compose/material3/AppBarMenuState;Landroidx/compose/runtime/Composer;I)V", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class CustomAppBarItem implements AppBarItem {
    public static final int $stable = 0;
    private final Function2<Composer, Integer, Unit> appbarContent;
    private final Function3<AppBarMenuState, Composer, Integer, Unit> menuContent;

    /* JADX WARN: Multi-variable type inference failed */
    public CustomAppBarItem(Function2<? super Composer, ? super Integer, Unit> function2, Function3<? super AppBarMenuState, ? super Composer, ? super Integer, Unit> function3) {
        this.appbarContent = function2;
        this.menuContent = function3;
    }

    public static Unit a(CustomAppBarItem customAppBarItem, AppBarMenuState appBarMenuState, int i, Composer composer, int i2) {
        customAppBarItem.MenuContent(appBarMenuState, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static Unit b(CustomAppBarItem customAppBarItem, int i, Composer composer, int i2) {
        customAppBarItem.AppbarContent(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.material3.AppBarItem
    public void AppbarContent(Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1121775917);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(this) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1121775917, i2, -1, "androidx.compose.material3.CustomAppBarItem.AppbarContent (AppBarDsl.kt:228)");
            }
            this.appbarContent.invoke(composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: k33
                public final Object invoke(Object obj, Object obj2) {
                    return CustomAppBarItem.b(this.b, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    @Override // androidx.compose.material3.AppBarItem
    public void MenuContent(final AppBarMenuState appBarMenuState, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1853735176);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(appBarMenuState) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(this) ? 32 : 16;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1853735176, i2, -1, "androidx.compose.material3.CustomAppBarItem.MenuContent (AppBarDsl.kt:233)");
            }
            this.menuContent.invoke(appBarMenuState, composerStartRestartGroup, Integer.valueOf(i2 & 14));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: l33
                public final Object invoke(Object obj, Object obj2) {
                    return CustomAppBarItem.a(this.b, appBarMenuState, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
