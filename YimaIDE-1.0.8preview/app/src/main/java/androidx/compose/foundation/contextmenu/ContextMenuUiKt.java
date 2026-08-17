package androidx.compose.foundation.contextmenu;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.contextmenu.ContextMenuUiKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.IntrinsicKt;
import androidx.compose.foundation.layout.IntrinsicSize;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.BasicTextKt;
import androidx.compose.foundation.text.TextAutoSize;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ShadowKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorProducer;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.window.AndroidPopup_androidKt;
import androidx.compose.ui.window.PopupPositionProvider;
import androidx.compose.ui.window.PopupProperties;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0004\u001aF\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00030\u000b¢\u0006\u0002\b\rH\u0001¢\u0006\u0002\u0010\u000e\u001aN\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u00102\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00030\u000b¢\u0006\u0002\b\rH\u0001¢\u0006\u0002\u0010\u0011\u001a:\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00030\u000b¢\u0006\u0002\b\rH\u0001¢\u0006\u0002\u0010\u0013\u001a=\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\b\u001a\u00020\t2\u001c\u0010\u0015\u001a\u0018\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00030\u000b¢\u0006\u0002\b\u0017¢\u0006\u0002\b\rH\u0001¢\u0006\u0002\u0010\u0018\u001ai\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\b\u001a\u00020\t2*\b\u0002\u0010\u001e\u001a$\u0012\u0013\u0012\u00110\u001f¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\"\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b¢\u0006\u0002\b\u00172\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007H\u0001¢\u0006\u0002\u0010$\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010%\u001a\u00020&X\u0082T¢\u0006\u0002\n\u0000\"\u0014\u0010'\u001a\u00020\u0010X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)¨\u0006*"}, d2 = {"DefaultPopupProperties", "Landroidx/compose/ui/window/PopupProperties;", "ContextMenuPopup", "", "popupPositionProvider", "Landroidx/compose/ui/window/PopupPositionProvider;", "onDismiss", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "contextMenuBuilderBlock", "Lkotlin/Function1;", "Landroidx/compose/foundation/contextmenu/ContextMenuScope;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/ui/window/PopupPositionProvider;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "colors", "Landroidx/compose/foundation/contextmenu/ContextMenuColors;", "(Landroidx/compose/ui/window/PopupPositionProvider;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/contextmenu/ContextMenuColors;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "ContextMenuColumnBuilder", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/contextmenu/ContextMenuColors;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "ContextMenuColumn", "content", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/foundation/contextmenu/ContextMenuColors;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "ContextMenuItem", "label", "", "enabled", "", "leadingIcon", "Landroidx/compose/ui/graphics/Color;", "Lkotlin/ParameterName;", "name", "iconColor", "onClick", "(Ljava/lang/String;ZLandroidx/compose/foundation/contextmenu/ContextMenuColors;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "DisabledAlpha", "", "DefaultContextMenuColors", "getDefaultContextMenuColors", "()Landroidx/compose/foundation/contextmenu/ContextMenuColors;", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ContextMenuUiKt {
    private static final ContextMenuColors DefaultContextMenuColors;
    private static final PopupProperties DefaultPopupProperties = new PopupProperties(true, false, false, false, 14, (DefaultConstructorMarker) null);
    private static final float DisabledAlpha = 0.38f;

    static {
        Color.Companion companion = Color.Companion;
        DefaultContextMenuColors = new ContextMenuColors(companion.getWhite-0d7_KjU(), companion.getBlack-0d7_KjU(), companion.getBlack-0d7_KjU(), Color.copy-wmQWz5c$default(companion.getBlack-0d7_KjU(), DisabledAlpha, 0.0f, 0.0f, 0.0f, 14, (Object) null), Color.copy-wmQWz5c$default(companion.getBlack-0d7_KjU(), DisabledAlpha, 0.0f, 0.0f, 0.0f, 14, (Object) null), null);
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0040  */
    /* JADX WARN: Code duplicated, block: B:25:0x0046  */
    /* JADX WARN: Code duplicated, block: B:26:0x0049  */
    /* JADX WARN: Code duplicated, block: B:30:0x0054  */
    /* JADX WARN: Code duplicated, block: B:31:0x0056  */
    /* JADX WARN: Code duplicated, block: B:34:0x005f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:35:0x0061  */
    /* JADX WARN: Code duplicated, block: B:36:0x0065  */
    /* JADX WARN: Code duplicated, block: B:39:0x006c  */
    /* JADX WARN: Code duplicated, block: B:42:0x00eb  */
    /* JADX WARN: Code duplicated, block: B:45:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:46:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:49:0x0141  */
    /* JADX WARN: Code duplicated, block: B:50:0x0145  */
    /* JADX WARN: Code duplicated, block: B:53:0x014f  */
    /* JADX WARN: Code duplicated, block: B:55:? A[RETURN, SYNTHETIC] */
    public static final void ContextMenuColumn(ContextMenuColors contextMenuColors, Modifier modifier, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i, final int i2) {
        ContextMenuColors contextMenuColors2;
        int i3;
        Modifier modifier2;
        boolean z;
        Modifier modifier3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Function0 constructor;
        int i4;
        Composer composerStartRestartGroup = composer.startRestartGroup(-527864079);
        if ((i & 6) == 0) {
            contextMenuColors2 = contextMenuColors;
            i3 = (composerStartRestartGroup.changed(contextMenuColors2) ? 4 : 2) | i;
        } else {
            contextMenuColors2 = contextMenuColors;
            i3 = i;
        }
        int i5 = i2 & 2;
        if (i5 == 0) {
            if ((i & 48) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            if ((i & 384) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i4 = 256;
                } else {
                    i4 = 128;
                }
                i3 |= i4;
            }
            if ((i3 & 147) != 146) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                if (i5 != 0) {
                    modifier3 = Modifier.Companion;
                } else {
                    modifier3 = modifier2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-527864079, i3, -1, "androidx.compose.foundation.contextmenu.ContextMenuColumn (ContextMenuUi.kt:153)");
                }
                ContextMenuSpec contextMenuSpec = ContextMenuSpec.INSTANCE;
                Modifier modifierVerticalScroll$default = ScrollKt.verticalScroll$default(PaddingKt.m932paddingVpY3zN4$default(IntrinsicKt.width(BackgroundKt.m323backgroundbw27NRU$default(ShadowKt.shadow-s4CzXII$default(modifier3, contextMenuSpec.m464getMenuContainerElevationD9Ej5fM(), RoundedCornerShapeKt.m1289RoundedCornerShape0680j_4(contextMenuSpec.m454getCornerRadiusD9Ej5fM()), false, 0L, 0L, 28, (Object) null), contextMenuColors2.getBackgroundColor(), null, 2, null), IntrinsicSize.Max), 0.0f, contextMenuSpec.m465getVerticalPaddingD9Ej5fM(), 1, null), ScrollKt.rememberScrollState(0, composerStartRestartGroup, 0, 1), false, null, false, 14, null);
                int i6 = (i3 << 3) & 7168;
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), composerStartRestartGroup, 0);
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierVerticalScroll$default);
                ComposeUiNode.Companion companion = ComposeUiNode.Companion;
                constructor = companion.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composer2 = Updater.constructor-impl(composerStartRestartGroup);
                Updater.set-impl(composer2, measurePolicyColumnMeasurePolicy, companion.getSetMeasurePolicy());
                Updater.set-impl(composer2, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                Updater.init-impl(composer2, Integer.valueOf(iHashCode), companion.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer2, companion.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer2, modifierMaterializeModifier, companion.getSetModifier());
                function3.invoke(ColumnScopeInstance.INSTANCE, composerStartRestartGroup, Integer.valueOf(((i6 >> 6) & 112) | 6));
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final ContextMenuColors contextMenuColors3 = contextMenuColors2;
                final Modifier modifier4 = modifier3;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ev2
                    public final Object invoke(Object obj, Object obj2) {
                        return ContextMenuUiKt.f(contextMenuColors3, modifier4, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        modifier2 = modifier;
        if ((i & 384) == 0) {
            if (composerStartRestartGroup.changedInstance(function3)) {
                i4 = 256;
            } else {
                i4 = 128;
            }
            i3 |= i4;
        }
        if ((i3 & 147) != 146) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
            if (i5 != 0) {
                modifier3 = Modifier.Companion;
            } else {
                modifier3 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-527864079, i3, -1, "androidx.compose.foundation.contextmenu.ContextMenuColumn (ContextMenuUi.kt:153)");
            }
            ContextMenuSpec contextMenuSpec2 = ContextMenuSpec.INSTANCE;
            Modifier modifierVerticalScroll$default2 = ScrollKt.verticalScroll$default(PaddingKt.m932paddingVpY3zN4$default(IntrinsicKt.width(BackgroundKt.m323backgroundbw27NRU$default(ShadowKt.shadow-s4CzXII$default(modifier3, contextMenuSpec2.m464getMenuContainerElevationD9Ej5fM(), RoundedCornerShapeKt.m1289RoundedCornerShape0680j_4(contextMenuSpec2.m454getCornerRadiusD9Ej5fM()), false, 0L, 0L, 28, (Object) null), contextMenuColors2.getBackgroundColor(), null, 2, null), IntrinsicSize.Max), 0.0f, contextMenuSpec2.m465getVerticalPaddingD9Ej5fM(), 1, null), ScrollKt.rememberScrollState(0, composerStartRestartGroup, 0, 1), false, null, false, 14, null);
            int i7 = (i3 << 3) & 7168;
            MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), composerStartRestartGroup, 0);
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierVerticalScroll$default2);
            ComposeUiNode.Companion companion2 = ComposeUiNode.Companion;
            constructor = companion2.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composer3 = Updater.constructor-impl(composerStartRestartGroup);
            Updater.set-impl(composer3, measurePolicyColumnMeasurePolicy2, companion2.getSetMeasurePolicy());
            Updater.set-impl(composer3, currentCompositionLocalMap2, companion2.getSetResolvedCompositionLocals());
            Updater.init-impl(composer3, Integer.valueOf(iHashCode2), companion2.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer3, companion2.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer3, modifierMaterializeModifier2, companion2.getSetModifier());
            function3.invoke(ColumnScopeInstance.INSTANCE, composerStartRestartGroup, Integer.valueOf(((i7 >> 6) & 112) | 6));
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final ContextMenuColors contextMenuColors4 = contextMenuColors2;
            final Modifier modifier5 = modifier3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ev2
                public final Object invoke(Object obj, Object obj2) {
                    return ContextMenuUiKt.f(contextMenuColors4, modifier5, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void ContextMenuColumnBuilder(Modifier modifier, ContextMenuColors contextMenuColors, final Function1<? super ContextMenuScope, Unit> function1, Composer composer, final int i, final int i2) {
        int i3;
        final Modifier modifier2;
        final ContextMenuColors contextMenuColors2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-625529233);
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i5 = i2 & 2;
        if (i5 != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(contextMenuColors) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function1) ? 256 : 128;
        }
        if (composerStartRestartGroup.shouldExecute((i3 & 147) != 146, i3 & 1)) {
            if (i4 != 0) {
                modifier = Modifier.Companion;
            }
            Modifier modifier3 = modifier;
            if (i5 != 0) {
                contextMenuColors = DefaultContextMenuColors;
            }
            final ContextMenuColors contextMenuColors3 = contextMenuColors;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-625529233, i3, -1, "androidx.compose.foundation.contextmenu.ContextMenuColumnBuilder (ContextMenuUi.kt:132)");
            }
            ContextMenuColumn(contextMenuColors3, modifier3, ComposableLambdaKt.rememberComposableLambda(-250345048, true, new Function3() { // from class: fv2
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return ContextMenuUiKt.e(function1, contextMenuColors3, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i3 >> 3) & 14) | 384 | ((i3 << 3) & 112), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            contextMenuColors2 = contextMenuColors3;
            modifier2 = modifier3;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier2 = modifier;
            contextMenuColors2 = contextMenuColors;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gv2
                public final Object invoke(Object obj, Object obj2) {
                    return ContextMenuUiKt.c(modifier2, contextMenuColors2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0227  */
    /* JADX WARN: Code duplicated, block: B:102:0x022c  */
    /* JADX WARN: Code duplicated, block: B:105:0x0242  */
    /* JADX WARN: Code duplicated, block: B:106:0x0247  */
    /* JADX WARN: Code duplicated, block: B:109:0x027c  */
    /* JADX WARN: Code duplicated, block: B:111:0x0282  */
    /* JADX WARN: Code duplicated, block: B:114:0x028f  */
    /* JADX WARN: Code duplicated, block: B:116:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:37:0x0065  */
    /* JADX WARN: Code duplicated, block: B:39:0x006a  */
    /* JADX WARN: Code duplicated, block: B:41:0x006e  */
    /* JADX WARN: Code duplicated, block: B:43:0x0076  */
    /* JADX WARN: Code duplicated, block: B:44:0x0079  */
    /* JADX WARN: Code duplicated, block: B:48:0x0083  */
    /* JADX WARN: Code duplicated, block: B:50:0x0089  */
    /* JADX WARN: Code duplicated, block: B:51:0x008b  */
    /* JADX WARN: Code duplicated, block: B:55:0x009a  */
    /* JADX WARN: Code duplicated, block: B:56:0x009c  */
    /* JADX WARN: Code duplicated, block: B:59:0x00a5 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:60:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:61:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:64:0x00ae  */
    /* JADX WARN: Code duplicated, block: B:67:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:70:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:71:0x00d1  */
    /* JADX WARN: Code duplicated, block: B:74:0x00d7  */
    /* JADX WARN: Code duplicated, block: B:75:0x00d9  */
    /* JADX WARN: Code duplicated, block: B:78:0x00e1  */
    /* JADX WARN: Code duplicated, block: B:80:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:83:0x0154  */
    /* JADX WARN: Code duplicated, block: B:86:0x0160  */
    /* JADX WARN: Code duplicated, block: B:87:0x0164  */
    /* JADX WARN: Code duplicated, block: B:90:0x0196  */
    /* JADX WARN: Code duplicated, block: B:92:0x01a1  */
    /* JADX WARN: Code duplicated, block: B:94:0x01e3  */
    /* JADX WARN: Code duplicated, block: B:97:0x01ef  */
    /* JADX WARN: Code duplicated, block: B:98:0x01f3  */
    public static final void ContextMenuItem(final String str, final boolean z, final ContextMenuColors contextMenuColors, Modifier modifier, Function3<? super Color, ? super Composer, ? super Integer, Unit> function3, final Function0<Unit> function0, Composer composer, final int i, final int i2) {
        String str2;
        int i3;
        Modifier modifier2;
        int i4;
        Function3<? super Color, ? super Composer, ? super Integer, Unit> function4;
        int i5;
        int i6;
        boolean z2;
        Composer composer2;
        final Modifier modifier3;
        final Function3<? super Color, ? super Composer, ? super Integer, Unit> function5;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        ContextMenuSpec contextMenuSpec;
        boolean z3;
        boolean z4;
        boolean z5;
        Object objRememberedValue;
        ComposeUiNode.Companion companion;
        Function0 constructor;
        Function0 constructor2;
        long disabledIconColor;
        long disabledTextColor;
        int i7;
        Composer composerStartRestartGroup = composer.startRestartGroup(-2001167027);
        if ((i & 6) == 0) {
            str2 = str;
            i3 = (composerStartRestartGroup.changed(str2) ? 4 : 2) | i;
        } else {
            str2 = str;
            i3 = i;
        }
        if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i3 |= composerStartRestartGroup.changed(contextMenuColors) ? 256 : 128;
        }
        int i8 = i2 & 8;
        if (i8 == 0) {
            if ((i & 3072) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 2048 : 1024;
            }
            i4 = i2 & 16;
            if (i4 != 0) {
                if ((i & 24576) == 0) {
                    function4 = function3;
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i5 = 16384;
                    } else {
                        i5 = 8192;
                    }
                    i3 |= i5;
                }
                if ((196608 & i) == 0) {
                    if (composerStartRestartGroup.changedInstance(function0)) {
                        i7 = 131072;
                    } else {
                        i7 = 65536;
                    }
                    i3 |= i7;
                }
                i6 = i3;
                if ((74899 & i6) != 74898) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z2, i6 & 1)) {
                    if (i8 != 0) {
                        modifier4 = Modifier.Companion;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        function4 = null;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-2001167027, i6, -1, "androidx.compose.foundation.contextmenu.ContextMenuItem (ContextMenuUi.kt:191)");
                    }
                    contextMenuSpec = ContextMenuSpec.INSTANCE;
                    Alignment.Vertical labelVerticalTextAlignment = contextMenuSpec.getLabelVerticalTextAlignment();
                    Arrangement.HorizontalOrVertical horizontalOrVerticalM782spacedBy0680j_4 = Arrangement.INSTANCE.m782spacedBy0680j_4(contextMenuSpec.m458getHorizontalPaddingD9Ej5fM());
                    if ((i6 & 112) == 32) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if ((458752 & i6) == 131072) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    z5 = z3 | z4;
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z5 || objRememberedValue == Composer.Companion.getEmpty()) {
                        objRememberedValue = new Function0() { // from class: iv2
                            public final Object invoke() {
                                return ContextMenuUiKt.ContextMenuItem$lambda$0$0(z, function0);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    Modifier modifier5 = modifier4;
                    Modifier modifierM932paddingVpY3zN4$default = PaddingKt.m932paddingVpY3zN4$default(SizeKt.m986sizeInqDBjuR0(SizeKt.fillMaxWidth$default(ClickableKt.m359clickableoSLSa3U$default(modifier5, z, str2, null, null, (Function0) objRememberedValue, 12, null), 0.0f, 1, null), contextMenuSpec.m453getContainerWidthMinD9Ej5fM(), contextMenuSpec.m463getListItemHeightD9Ej5fM(), contextMenuSpec.m452getContainerWidthMaxD9Ej5fM(), contextMenuSpec.m463getListItemHeightD9Ej5fM()), contextMenuSpec.m458getHorizontalPaddingD9Ej5fM(), 0.0f, 2, null);
                    MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalM782spacedBy0680j_4, labelVerticalTextAlignment, composerStartRestartGroup, 54);
                    int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                    CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM932paddingVpY3zN4$default);
                    companion = ComposeUiNode.Companion;
                    constructor = companion.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    Composer composer3 = Updater.constructor-impl(composerStartRestartGroup);
                    Updater.set-impl(composer3, measurePolicyRowMeasurePolicy, companion.getSetMeasurePolicy());
                    Updater.set-impl(composer3, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer3, Integer.valueOf(iHashCode), companion.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer3, companion.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer3, modifierMaterializeModifier, companion.getSetModifier());
                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                    if (function4 == null) {
                        composerStartRestartGroup.startReplaceGroup(-1597947094);
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1597947093);
                        Modifier modifierM979requiredSizeInqDBjuR0$default = SizeKt.m979requiredSizeInqDBjuR0$default(Modifier.Companion, contextMenuSpec.m459getIconSizeD9Ej5fM(), 0.0f, contextMenuSpec.m459getIconSizeD9Ej5fM(), contextMenuSpec.m459getIconSizeD9Ej5fM(), 2, null);
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), false);
                        int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                        CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM979requiredSizeInqDBjuR0$default);
                        constructor2 = companion.getConstructor();
                        if (composerStartRestartGroup.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor2);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        Composer composer4 = Updater.constructor-impl(composerStartRestartGroup);
                        Updater.set-impl(composer4, measurePolicyMaybeCachedBoxMeasurePolicy, companion.getSetMeasurePolicy());
                        Updater.set-impl(composer4, currentCompositionLocalMap2, companion.getSetResolvedCompositionLocals());
                        Updater.init-impl(composer4, Integer.valueOf(iHashCode2), companion.getSetCompositeKeyHash());
                        Updater.reconcile-impl(composer4, companion.getApplyOnDeactivatedNodeAssertion());
                        Updater.set-impl(composer4, modifierMaterializeModifier2, companion.getSetModifier());
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        if (z) {
                            disabledIconColor = contextMenuColors.getIconColor();
                        } else {
                            disabledIconColor = contextMenuColors.getDisabledIconColor();
                        }
                        function4.invoke(Color.box-impl(disabledIconColor), composerStartRestartGroup, 0);
                        composerStartRestartGroup.endNode();
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    if (z) {
                        disabledTextColor = contextMenuColors.getTextColor();
                    } else {
                        disabledTextColor = contextMenuColors.getDisabledTextColor();
                    }
                    Function3<? super Color, ? super Composer, ? super Integer, Unit> function6 = function4;
                    composer2 = composerStartRestartGroup;
                    BasicTextKt.m1312BasicTextRWo7tUw(str, rowScopeInstance.weight(Modifier.Companion, 1.0f, true), contextMenuSpec.m466textStyle8_81llA(disabledTextColor), (Function1<? super TextLayoutResult, Unit>) null, 0, false, 1, 0, (ColorProducer) null, (TextAutoSize) null, composer2, (i6 & 14) | 1572864, 952);
                    composer2.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier5;
                    function5 = function6;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    function5 = function4;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: jv2
                        public final Object invoke(Object obj, Object obj2) {
                            return ContextMenuUiKt.h(str, z, contextMenuColors, modifier3, function5, function0, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            function4 = function3;
            if ((196608 & i) == 0) {
                if (composerStartRestartGroup.changedInstance(function0)) {
                    i7 = 131072;
                } else {
                    i7 = 65536;
                }
                i3 |= i7;
            }
            i6 = i3;
            if ((74899 & i6) != 74898) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z2, i6 & 1)) {
                if (i8 != 0) {
                    modifier4 = Modifier.Companion;
                } else {
                    modifier4 = modifier2;
                }
                if (i4 != 0) {
                    function4 = null;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-2001167027, i6, -1, "androidx.compose.foundation.contextmenu.ContextMenuItem (ContextMenuUi.kt:191)");
                }
                contextMenuSpec = ContextMenuSpec.INSTANCE;
                Alignment.Vertical labelVerticalTextAlignment2 = contextMenuSpec.getLabelVerticalTextAlignment();
                Arrangement.HorizontalOrVertical horizontalOrVerticalM782spacedBy0680j_5 = Arrangement.INSTANCE.m782spacedBy0680j_4(contextMenuSpec.m458getHorizontalPaddingD9Ej5fM());
                if ((i6 & 112) == 32) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if ((458752 & i6) == 131072) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                z5 = z3 | z4;
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z5) {
                    objRememberedValue = new Function0() { // from class: iv2
                        public final Object invoke() {
                            return ContextMenuUiKt.ContextMenuItem$lambda$0$0(z, function0);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new Function0() { // from class: iv2
                        public final Object invoke() {
                            return ContextMenuUiKt.ContextMenuItem$lambda$0$0(z, function0);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                Modifier modifier6 = modifier4;
                Modifier modifierM932paddingVpY3zN4$default2 = PaddingKt.m932paddingVpY3zN4$default(SizeKt.m986sizeInqDBjuR0(SizeKt.fillMaxWidth$default(ClickableKt.m359clickableoSLSa3U$default(modifier6, z, str2, null, null, (Function0) objRememberedValue, 12, null), 0.0f, 1, null), contextMenuSpec.m453getContainerWidthMinD9Ej5fM(), contextMenuSpec.m463getListItemHeightD9Ej5fM(), contextMenuSpec.m452getContainerWidthMaxD9Ej5fM(), contextMenuSpec.m463getListItemHeightD9Ej5fM()), contextMenuSpec.m458getHorizontalPaddingD9Ej5fM(), 0.0f, 2, null);
                MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(horizontalOrVerticalM782spacedBy0680j_5, labelVerticalTextAlignment2, composerStartRestartGroup, 54);
                int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM932paddingVpY3zN4$default2);
                companion = ComposeUiNode.Companion;
                constructor = companion.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composer5 = Updater.constructor-impl(composerStartRestartGroup);
                Updater.set-impl(composer5, measurePolicyRowMeasurePolicy2, companion.getSetMeasurePolicy());
                Updater.set-impl(composer5, currentCompositionLocalMap3, companion.getSetResolvedCompositionLocals());
                Updater.init-impl(composer5, Integer.valueOf(iHashCode3), companion.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer5, companion.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer5, modifierMaterializeModifier3, companion.getSetModifier());
                RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                if (function4 == null) {
                    composerStartRestartGroup.startReplaceGroup(-1597947094);
                } else {
                    composerStartRestartGroup.startReplaceGroup(-1597947093);
                    Modifier modifierM979requiredSizeInqDBjuR0$default2 = SizeKt.m979requiredSizeInqDBjuR0$default(Modifier.Companion, contextMenuSpec.m459getIconSizeD9Ej5fM(), 0.0f, contextMenuSpec.m459getIconSizeD9Ej5fM(), contextMenuSpec.m459getIconSizeD9Ej5fM(), 2, null);
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), false);
                    int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                    CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM979requiredSizeInqDBjuR0$default2);
                    constructor2 = companion.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor2);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    Composer composer6 = Updater.constructor-impl(composerStartRestartGroup);
                    Updater.set-impl(composer6, measurePolicyMaybeCachedBoxMeasurePolicy2, companion.getSetMeasurePolicy());
                    Updater.set-impl(composer6, currentCompositionLocalMap4, companion.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer6, Integer.valueOf(iHashCode4), companion.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer6, companion.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer6, modifierMaterializeModifier4, companion.getSetModifier());
                    BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                    if (z) {
                        disabledIconColor = contextMenuColors.getIconColor();
                    } else {
                        disabledIconColor = contextMenuColors.getDisabledIconColor();
                    }
                    function4.invoke(Color.box-impl(disabledIconColor), composerStartRestartGroup, 0);
                    composerStartRestartGroup.endNode();
                }
                composerStartRestartGroup.endReplaceGroup();
                if (z) {
                    disabledTextColor = contextMenuColors.getTextColor();
                } else {
                    disabledTextColor = contextMenuColors.getDisabledTextColor();
                }
                Function3<? super Color, ? super Composer, ? super Integer, Unit> function7 = function4;
                composer2 = composerStartRestartGroup;
                BasicTextKt.m1312BasicTextRWo7tUw(str, rowScopeInstance2.weight(Modifier.Companion, 1.0f, true), contextMenuSpec.m466textStyle8_81llA(disabledTextColor), (Function1<? super TextLayoutResult, Unit>) null, 0, false, 1, 0, (ColorProducer) null, (TextAutoSize) null, composer2, (i6 & 14) | 1572864, 952);
                composer2.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier6;
                function5 = function7;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
                function5 = function4;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: jv2
                    public final Object invoke(Object obj, Object obj2) {
                        return ContextMenuUiKt.h(str, z, contextMenuColors, modifier3, function5, function0, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        modifier2 = modifier;
        i4 = i2 & 16;
        if (i4 != 0) {
            if ((i & 24576) == 0) {
                function4 = function3;
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i5 = 16384;
                } else {
                    i5 = 8192;
                }
                i3 |= i5;
            }
            if ((196608 & i) == 0) {
                if (composerStartRestartGroup.changedInstance(function0)) {
                    i7 = 131072;
                } else {
                    i7 = 65536;
                }
                i3 |= i7;
            }
            i6 = i3;
            if ((74899 & i6) != 74898) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z2, i6 & 1)) {
                if (i8 != 0) {
                    modifier4 = Modifier.Companion;
                } else {
                    modifier4 = modifier2;
                }
                if (i4 != 0) {
                    function4 = null;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-2001167027, i6, -1, "androidx.compose.foundation.contextmenu.ContextMenuItem (ContextMenuUi.kt:191)");
                }
                contextMenuSpec = ContextMenuSpec.INSTANCE;
                Alignment.Vertical labelVerticalTextAlignment3 = contextMenuSpec.getLabelVerticalTextAlignment();
                Arrangement.HorizontalOrVertical horizontalOrVerticalM782spacedBy0680j_6 = Arrangement.INSTANCE.m782spacedBy0680j_4(contextMenuSpec.m458getHorizontalPaddingD9Ej5fM());
                if ((i6 & 112) == 32) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if ((458752 & i6) == 131072) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                z5 = z3 | z4;
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z5) {
                    objRememberedValue = new Function0() { // from class: iv2
                        public final Object invoke() {
                            return ContextMenuUiKt.ContextMenuItem$lambda$0$0(z, function0);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new Function0() { // from class: iv2
                        public final Object invoke() {
                            return ContextMenuUiKt.ContextMenuItem$lambda$0$0(z, function0);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                Modifier modifier7 = modifier4;
                Modifier modifierM932paddingVpY3zN4$default3 = PaddingKt.m932paddingVpY3zN4$default(SizeKt.m986sizeInqDBjuR0(SizeKt.fillMaxWidth$default(ClickableKt.m359clickableoSLSa3U$default(modifier7, z, str2, null, null, (Function0) objRememberedValue, 12, null), 0.0f, 1, null), contextMenuSpec.m453getContainerWidthMinD9Ej5fM(), contextMenuSpec.m463getListItemHeightD9Ej5fM(), contextMenuSpec.m452getContainerWidthMaxD9Ej5fM(), contextMenuSpec.m463getListItemHeightD9Ej5fM()), contextMenuSpec.m458getHorizontalPaddingD9Ej5fM(), 0.0f, 2, null);
                MeasurePolicy measurePolicyRowMeasurePolicy3 = RowKt.rowMeasurePolicy(horizontalOrVerticalM782spacedBy0680j_6, labelVerticalTextAlignment3, composerStartRestartGroup, 54);
                int iHashCode5 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap5 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM932paddingVpY3zN4$default3);
                companion = ComposeUiNode.Companion;
                constructor = companion.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composer7 = Updater.constructor-impl(composerStartRestartGroup);
                Updater.set-impl(composer7, measurePolicyRowMeasurePolicy3, companion.getSetMeasurePolicy());
                Updater.set-impl(composer7, currentCompositionLocalMap5, companion.getSetResolvedCompositionLocals());
                Updater.init-impl(composer7, Integer.valueOf(iHashCode5), companion.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer7, companion.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer7, modifierMaterializeModifier5, companion.getSetModifier());
                RowScopeInstance rowScopeInstance3 = RowScopeInstance.INSTANCE;
                if (function4 == null) {
                    composerStartRestartGroup.startReplaceGroup(-1597947094);
                } else {
                    composerStartRestartGroup.startReplaceGroup(-1597947093);
                    Modifier modifierM979requiredSizeInqDBjuR0$default3 = SizeKt.m979requiredSizeInqDBjuR0$default(Modifier.Companion, contextMenuSpec.m459getIconSizeD9Ej5fM(), 0.0f, contextMenuSpec.m459getIconSizeD9Ej5fM(), contextMenuSpec.m459getIconSizeD9Ej5fM(), 2, null);
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), false);
                    int iHashCode6 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                    CompositionLocalMap currentCompositionLocalMap6 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM979requiredSizeInqDBjuR0$default3);
                    constructor2 = companion.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor2);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    Composer composer8 = Updater.constructor-impl(composerStartRestartGroup);
                    Updater.set-impl(composer8, measurePolicyMaybeCachedBoxMeasurePolicy3, companion.getSetMeasurePolicy());
                    Updater.set-impl(composer8, currentCompositionLocalMap6, companion.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer8, Integer.valueOf(iHashCode6), companion.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer8, companion.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer8, modifierMaterializeModifier6, companion.getSetModifier());
                    BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                    if (z) {
                        disabledIconColor = contextMenuColors.getIconColor();
                    } else {
                        disabledIconColor = contextMenuColors.getDisabledIconColor();
                    }
                    function4.invoke(Color.box-impl(disabledIconColor), composerStartRestartGroup, 0);
                    composerStartRestartGroup.endNode();
                }
                composerStartRestartGroup.endReplaceGroup();
                if (z) {
                    disabledTextColor = contextMenuColors.getTextColor();
                } else {
                    disabledTextColor = contextMenuColors.getDisabledTextColor();
                }
                Function3<? super Color, ? super Composer, ? super Integer, Unit> function8 = function4;
                composer2 = composerStartRestartGroup;
                BasicTextKt.m1312BasicTextRWo7tUw(str, rowScopeInstance3.weight(Modifier.Companion, 1.0f, true), contextMenuSpec.m466textStyle8_81llA(disabledTextColor), (Function1<? super TextLayoutResult, Unit>) null, 0, false, 1, 0, (ColorProducer) null, (TextAutoSize) null, composer2, (i6 & 14) | 1572864, 952);
                composer2.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier7;
                function5 = function8;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
                function5 = function4;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: jv2
                    public final Object invoke(Object obj, Object obj2) {
                        return ContextMenuUiKt.h(str, z, contextMenuColors, modifier3, function5, function0, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 24576;
        function4 = function3;
        if ((196608 & i) == 0) {
            if (composerStartRestartGroup.changedInstance(function0)) {
                i7 = 131072;
            } else {
                i7 = 65536;
            }
            i3 |= i7;
        }
        i6 = i3;
        if ((74899 & i6) != 74898) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z2, i6 & 1)) {
            if (i8 != 0) {
                modifier4 = Modifier.Companion;
            } else {
                modifier4 = modifier2;
            }
            if (i4 != 0) {
                function4 = null;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2001167027, i6, -1, "androidx.compose.foundation.contextmenu.ContextMenuItem (ContextMenuUi.kt:191)");
            }
            contextMenuSpec = ContextMenuSpec.INSTANCE;
            Alignment.Vertical labelVerticalTextAlignment4 = contextMenuSpec.getLabelVerticalTextAlignment();
            Arrangement.HorizontalOrVertical horizontalOrVerticalM782spacedBy0680j_7 = Arrangement.INSTANCE.m782spacedBy0680j_4(contextMenuSpec.m458getHorizontalPaddingD9Ej5fM());
            if ((i6 & 112) == 32) {
                z3 = true;
            } else {
                z3 = false;
            }
            if ((458752 & i6) == 131072) {
                z4 = true;
            } else {
                z4 = false;
            }
            z5 = z3 | z4;
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z5) {
                objRememberedValue = new Function0() { // from class: iv2
                    public final Object invoke() {
                        return ContextMenuUiKt.ContextMenuItem$lambda$0$0(z, function0);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = new Function0() { // from class: iv2
                    public final Object invoke() {
                        return ContextMenuUiKt.ContextMenuItem$lambda$0$0(z, function0);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Modifier modifier8 = modifier4;
            Modifier modifierM932paddingVpY3zN4$default4 = PaddingKt.m932paddingVpY3zN4$default(SizeKt.m986sizeInqDBjuR0(SizeKt.fillMaxWidth$default(ClickableKt.m359clickableoSLSa3U$default(modifier8, z, str2, null, null, (Function0) objRememberedValue, 12, null), 0.0f, 1, null), contextMenuSpec.m453getContainerWidthMinD9Ej5fM(), contextMenuSpec.m463getListItemHeightD9Ej5fM(), contextMenuSpec.m452getContainerWidthMaxD9Ej5fM(), contextMenuSpec.m463getListItemHeightD9Ej5fM()), contextMenuSpec.m458getHorizontalPaddingD9Ej5fM(), 0.0f, 2, null);
            MeasurePolicy measurePolicyRowMeasurePolicy4 = RowKt.rowMeasurePolicy(horizontalOrVerticalM782spacedBy0680j_7, labelVerticalTextAlignment4, composerStartRestartGroup, 54);
            int iHashCode7 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap7 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier7 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM932paddingVpY3zN4$default4);
            companion = ComposeUiNode.Companion;
            constructor = companion.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composer9 = Updater.constructor-impl(composerStartRestartGroup);
            Updater.set-impl(composer9, measurePolicyRowMeasurePolicy4, companion.getSetMeasurePolicy());
            Updater.set-impl(composer9, currentCompositionLocalMap7, companion.getSetResolvedCompositionLocals());
            Updater.init-impl(composer9, Integer.valueOf(iHashCode7), companion.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer9, companion.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer9, modifierMaterializeModifier7, companion.getSetModifier());
            RowScopeInstance rowScopeInstance4 = RowScopeInstance.INSTANCE;
            if (function4 == null) {
                composerStartRestartGroup.startReplaceGroup(-1597947094);
            } else {
                composerStartRestartGroup.startReplaceGroup(-1597947093);
                Modifier modifierM979requiredSizeInqDBjuR0$default4 = SizeKt.m979requiredSizeInqDBjuR0$default(Modifier.Companion, contextMenuSpec.m459getIconSizeD9Ej5fM(), 0.0f, contextMenuSpec.m459getIconSizeD9Ej5fM(), contextMenuSpec.m459getIconSizeD9Ej5fM(), 2, null);
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy4 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), false);
                int iHashCode8 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap8 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier8 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM979requiredSizeInqDBjuR0$default4);
                constructor2 = companion.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor2);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composer10 = Updater.constructor-impl(composerStartRestartGroup);
                Updater.set-impl(composer10, measurePolicyMaybeCachedBoxMeasurePolicy4, companion.getSetMeasurePolicy());
                Updater.set-impl(composer10, currentCompositionLocalMap8, companion.getSetResolvedCompositionLocals());
                Updater.init-impl(composer10, Integer.valueOf(iHashCode8), companion.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer10, companion.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer10, modifierMaterializeModifier8, companion.getSetModifier());
                BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
                if (z) {
                    disabledIconColor = contextMenuColors.getIconColor();
                } else {
                    disabledIconColor = contextMenuColors.getDisabledIconColor();
                }
                function4.invoke(Color.box-impl(disabledIconColor), composerStartRestartGroup, 0);
                composerStartRestartGroup.endNode();
            }
            composerStartRestartGroup.endReplaceGroup();
            if (z) {
                disabledTextColor = contextMenuColors.getTextColor();
            } else {
                disabledTextColor = contextMenuColors.getDisabledTextColor();
            }
            Function3<? super Color, ? super Composer, ? super Integer, Unit> function9 = function4;
            composer2 = composerStartRestartGroup;
            BasicTextKt.m1312BasicTextRWo7tUw(str, rowScopeInstance4.weight(Modifier.Companion, 1.0f, true), contextMenuSpec.m466textStyle8_81llA(disabledTextColor), (Function1<? super TextLayoutResult, Unit>) null, 0, false, 1, 0, (ColorProducer) null, (TextAutoSize) null, composer2, (i6 & 14) | 1572864, 952);
            composer2.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier8;
            function5 = function9;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            modifier3 = modifier2;
            function5 = function4;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: jv2
                public final Object invoke(Object obj, Object obj2) {
                    return ContextMenuUiKt.h(str, z, contextMenuColors, modifier3, function5, function0, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContextMenuItem$lambda$0$0(boolean z, Function0 function0) {
        if (z) {
            function0.invoke();
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:30:0x004e  */
    /* JADX WARN: Code duplicated, block: B:32:0x0054  */
    /* JADX WARN: Code duplicated, block: B:33:0x0057  */
    /* JADX WARN: Code duplicated, block: B:37:0x005e  */
    /* JADX WARN: Code duplicated, block: B:39:0x0064  */
    /* JADX WARN: Code duplicated, block: B:40:0x0067  */
    /* JADX WARN: Code duplicated, block: B:44:0x0071  */
    /* JADX WARN: Code duplicated, block: B:45:0x0073  */
    /* JADX WARN: Code duplicated, block: B:48:0x007c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:49:0x007e  */
    /* JADX WARN: Code duplicated, block: B:50:0x0081  */
    /* JADX WARN: Code duplicated, block: B:53:0x0088  */
    /* JADX WARN: Code duplicated, block: B:56:0x00b3  */
    /* JADX WARN: Code duplicated, block: B:58:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:61:0x00c1  */
    /* JADX WARN: Code duplicated, block: B:63:? A[RETURN, SYNTHETIC] */
    public static final void ContextMenuPopup(final PopupPositionProvider popupPositionProvider, final Function0<Unit> function0, Modifier modifier, final ContextMenuColors contextMenuColors, final Function1<? super ContextMenuScope, Unit> function1, Composer composer, final int i, final int i2) {
        int i3;
        final Modifier modifier2;
        boolean z;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        final Modifier modifier3;
        int i4;
        int i5;
        Composer composerStartRestartGroup = composer.startRestartGroup(-305401220);
        if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(popupPositionProvider) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function0) ? 32 : 16;
        }
        int i6 = i2 & 4;
        if (i6 == 0) {
            if ((i & 384) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 256 : 128;
            }
            if ((i & 3072) == 0) {
                if (composerStartRestartGroup.changed(contextMenuColors)) {
                    i5 = 2048;
                } else {
                    i5 = 1024;
                }
                i3 |= i5;
            }
            if ((i & 24576) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i4 = 16384;
                } else {
                    i4 = 8192;
                }
                i3 |= i4;
            }
            if ((i3 & 9363) != 9362) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                if (i6 != 0) {
                    modifier3 = Modifier.Companion;
                } else {
                    modifier3 = modifier2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-305401220, i3, -1, "androidx.compose.foundation.contextmenu.ContextMenuPopup (ContextMenuUi.kt:117)");
                }
                AndroidPopup_androidKt.Popup(popupPositionProvider, function0, DefaultPopupProperties, ComposableLambdaKt.rememberComposableLambda(-1271367778, true, new Function2() { // from class: kv2
                    public final Object invoke(Object obj, Object obj2) {
                        return ContextMenuUiKt.a(modifier3, contextMenuColors, function1, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i3 & 14) | 3456 | (i3 & 112), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier3;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: lv2
                    public final Object invoke(Object obj, Object obj2) {
                        return ContextMenuUiKt.g(popupPositionProvider, function0, modifier2, contextMenuColors, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 384;
        modifier2 = modifier;
        if ((i & 3072) == 0) {
            if (composerStartRestartGroup.changed(contextMenuColors)) {
                i5 = 2048;
            } else {
                i5 = 1024;
            }
            i3 |= i5;
        }
        if ((i & 24576) == 0) {
            if (composerStartRestartGroup.changedInstance(function1)) {
                i4 = 16384;
            } else {
                i4 = 8192;
            }
            i3 |= i4;
        }
        if ((i3 & 9363) != 9362) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
            if (i6 != 0) {
                modifier3 = Modifier.Companion;
            } else {
                modifier3 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-305401220, i3, -1, "androidx.compose.foundation.contextmenu.ContextMenuPopup (ContextMenuUi.kt:117)");
            }
            AndroidPopup_androidKt.Popup(popupPositionProvider, function0, DefaultPopupProperties, ComposableLambdaKt.rememberComposableLambda(-1271367778, true, new Function2() { // from class: kv2
                public final Object invoke(Object obj, Object obj2) {
                    return ContextMenuUiKt.a(modifier3, contextMenuColors, function1, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, (i3 & 14) | 3456 | (i3 & 112), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: lv2
                public final Object invoke(Object obj, Object obj2) {
                    return ContextMenuUiKt.g(popupPositionProvider, function0, modifier2, contextMenuColors, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static Unit a(Modifier modifier, ContextMenuColors contextMenuColors, Function1 function1, Composer composer, int i) {
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1271367778, i, -1, "androidx.compose.foundation.contextmenu.ContextMenuPopup.<anonymous> (ContextMenuUi.kt:123)");
            }
            ContextMenuColumnBuilder(modifier, contextMenuColors, function1, composer, 0, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit b(PopupPositionProvider popupPositionProvider, Function0 function0, Modifier modifier, Function1 function1, int i, int i2, Composer composer, int i3) {
        ContextMenuPopup(popupPositionProvider, function0, modifier, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit c(Modifier modifier, ContextMenuColors contextMenuColors, Function1 function1, int i, int i2, Composer composer, int i3) {
        ContextMenuColumnBuilder(modifier, contextMenuColors, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit e(Function1 function1, ContextMenuColors contextMenuColors, ColumnScope columnScope, Composer composer, int i) {
        if (composer.shouldExecute((i & 17) != 16, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-250345048, i, -1, "androidx.compose.foundation.contextmenu.ContextMenuColumnBuilder.<anonymous> (ContextMenuUi.kt:134)");
            }
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = new ContextMenuScope(ComposableSingletons$ContextMenuUiKt.INSTANCE.m445getLambda$1571120048$foundation());
                composer.updateRememberedValue(objRememberedValue);
            }
            ContextMenuScope contextMenuScope = (ContextMenuScope) objRememberedValue;
            contextMenuScope.clear$foundation();
            function1.invoke(contextMenuScope);
            contextMenuScope.Content$foundation(contextMenuColors, composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit f(ContextMenuColors contextMenuColors, Modifier modifier, Function3 function3, int i, int i2, Composer composer, int i3) {
        ContextMenuColumn(contextMenuColors, modifier, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit g(PopupPositionProvider popupPositionProvider, Function0 function0, Modifier modifier, ContextMenuColors contextMenuColors, Function1 function1, int i, int i2, Composer composer, int i3) {
        ContextMenuPopup(popupPositionProvider, function0, modifier, contextMenuColors, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final ContextMenuColors getDefaultContextMenuColors() {
        return DefaultContextMenuColors;
    }

    public static Unit h(String str, boolean z, ContextMenuColors contextMenuColors, Modifier modifier, Function3 function3, Function0 function0, int i, int i2, Composer composer, int i3) {
        ContextMenuItem(str, z, contextMenuColors, modifier, function3, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final void ContextMenuPopup(PopupPositionProvider popupPositionProvider, Function0<Unit> function0, Modifier modifier, Function1<? super ContextMenuScope, Unit> function1, Composer composer, final int i, final int i2) {
        int i3;
        Function0<Unit> function2;
        final Function1<? super ContextMenuScope, Unit> function3;
        final PopupPositionProvider popupPositionProvider2;
        final Modifier modifier2;
        Composer composerStartRestartGroup = composer.startRestartGroup(307841774);
        if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(popupPositionProvider) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function0) ? 32 : 16;
        }
        int i4 = i2 & 4;
        if (i4 != 0) {
            i3 |= 384;
        } else if ((i & 384) == 0) {
            i3 |= composerStartRestartGroup.changed(modifier) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function1) ? 2048 : 1024;
        }
        if (composerStartRestartGroup.shouldExecute((i3 & 1171) != 1170, i3 & 1)) {
            if (i4 != 0) {
                modifier = Modifier.Companion;
            }
            Modifier modifier3 = modifier;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(307841774, i3, -1, "androidx.compose.foundation.contextmenu.ContextMenuPopup (ContextMenuUi.kt:99)");
            }
            function2 = function0;
            ContextMenuPopup(popupPositionProvider, function2, modifier3, ContextMenuUi_androidKt.computeContextMenuColors(composerStartRestartGroup, 0), function1, composerStartRestartGroup, (i3 & 1022) | ((i3 << 3) & 57344), 0);
            popupPositionProvider2 = popupPositionProvider;
            function3 = function1;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
        } else {
            function2 = function0;
            function3 = function1;
            popupPositionProvider2 = popupPositionProvider;
            composerStartRestartGroup.skipToGroupEnd();
            modifier2 = modifier;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Function0<Unit> function4 = function2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hv2
                public final Object invoke(Object obj, Object obj2) {
                    return ContextMenuUiKt.b(popupPositionProvider2, function4, modifier2, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
