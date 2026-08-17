package androidx.compose.material3;

import androidx.compose.animation.SingleValueAnimationKt;
import androidx.compose.foundation.gestures.DraggableKt;
import androidx.compose.foundation.gestures.DraggableState;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.WindowInsetsPaddingKt;
import androidx.compose.material3.DefaultSingleRowTopAppBarOverride;
import androidx.compose.material3.internal.FloatProducer;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\u0004\u001a\u00020\u0005*\u00020\u0006H\u0017¢\u0006\u0002\u0010\u0007¨\u0006\b²\u0006\n\u0010\t\u001a\u00020\nX\u008a\u0084\u0002"}, d2 = {"Landroidx/compose/material3/DefaultSingleRowTopAppBarOverride;", "Landroidx/compose/material3/SingleRowTopAppBarOverride;", "<init>", "()V", "SingleRowTopAppBar", "", "Landroidx/compose/material3/SingleRowTopAppBarOverrideScope;", "(Landroidx/compose/material3/SingleRowTopAppBarOverrideScope;Landroidx/compose/runtime/Composer;I)V", "material3", "targetColor", "Landroidx/compose/ui/graphics/Color;"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class DefaultSingleRowTopAppBarOverride implements SingleRowTopAppBarOverride {
    public static final int $stable = 0;
    public static final DefaultSingleRowTopAppBarOverride INSTANCE = new DefaultSingleRowTopAppBarOverride();

    private DefaultSingleRowTopAppBarOverride() {
    }

    private static final long SingleRowTopAppBar$lambda$2(State<Color> state) {
        return state.getValue().m3144unboximpl();
    }

    public static Unit a(DefaultSingleRowTopAppBarOverride defaultSingleRowTopAppBarOverride, SingleRowTopAppBarOverrideScope singleRowTopAppBarOverrideScope, int i, Composer composer, int i2) {
        defaultSingleRowTopAppBarOverride.SingleRowTopAppBar(singleRowTopAppBarOverrideScope, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static Unit b(SingleRowTopAppBarOverrideScope singleRowTopAppBarOverrideScope, float f) {
        TopAppBarState state = singleRowTopAppBarOverrideScope.getScrollBehavior().getState();
        state.setHeightOffset(state.getHeightOffset() + f);
        return Unit.INSTANCE;
    }

    public static float c() {
        return 1.0f;
    }

    public static Unit d(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.setTraversalGroup(semanticsPropertyReceiver, true);
        return Unit.INSTANCE;
    }

    public static Unit e(State state, DrawScope drawScope) {
        long jM3144unboximpl = ((Color) state.getValue()).m3144unboximpl();
        if (!Color.m3135equalsimpl0(jM3144unboximpl, Color.INSTANCE.m3170getUnspecified0d7_KjU())) {
            DrawScope.m3702drawRectnJ9OG0$default(drawScope, jM3144unboximpl, 0L, 0L, 0.0f, null, null, 0, 126, null);
        }
        return Unit.INSTANCE;
    }

    public static float f(SingleRowTopAppBarOverrideScope singleRowTopAppBarOverrideScope) {
        TopAppBarState state;
        TopAppBarScrollBehavior scrollBehavior = singleRowTopAppBarOverrideScope.getScrollBehavior();
        if (scrollBehavior == null || (state = scrollBehavior.getState()) == null) {
            return 0.0f;
        }
        return state.getHeightOffset();
    }

    @Override // androidx.compose.material3.SingleRowTopAppBarOverride
    public void SingleRowTopAppBar(final SingleRowTopAppBarOverrideScope singleRowTopAppBarOverrideScope, Composer composer, final int i) {
        int i2;
        Modifier modifierDraggable$default;
        Composer composerStartRestartGroup = composer.startRestartGroup(2137486921);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(singleRowTopAppBarOverrideScope) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2137486921, i2, -1, "androidx.compose.material3.DefaultSingleRowTopAppBarOverride.SingleRowTopAppBar (AppBar.kt:2510)");
            }
            if (Float.isNaN(singleRowTopAppBarOverrideScope.getExpandedHeight()) || (Float.floatToRawIntBits(singleRowTopAppBarOverrideScope.getExpandedHeight()) & Integer.MAX_VALUE) >= 2139095040) {
                w01.a("The expandedHeight is expected to be specified and finite");
                return;
            }
            boolean zChanged = composerStartRestartGroup.changed(singleRowTopAppBarOverrideScope.getColors()) | composerStartRestartGroup.changed(singleRowTopAppBarOverrideScope.getScrollBehavior());
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt.derivedStateOf(new Function0<Color>() { // from class: androidx.compose.material3.DefaultSingleRowTopAppBarOverride$SingleRowTopAppBar$targetColor$2$1
                    public /* bridge */ /* synthetic */ Object invoke() {
                        return Color.m3124boximpl(m400invoke0d7_KjU());
                    }

                    /* JADX INFO: renamed from: invoke-0d7_KjU, reason: not valid java name */
                    public final long m400invoke0d7_KjU() {
                        TopAppBarState state;
                        TopAppBarScrollBehavior scrollBehavior = singleRowTopAppBarOverrideScope.getScrollBehavior();
                        return singleRowTopAppBarOverrideScope.getColors().m1292containerColorvNxB06k$material3(((scrollBehavior == null || (state = scrollBehavior.getState()) == null) ? 0.0f : state.getOverlappedFraction()) > 0.01f ? 1.0f : 0.0f);
                    }
                });
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final State state = SingleValueAnimationKt.animateColorAsState-euL9pac(SingleRowTopAppBar$lambda$2((State) objRememberedValue), MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, composerStartRestartGroup, 6), (String) null, (Function1) null, composerStartRestartGroup, 0, 12);
            ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1658896622, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DefaultSingleRowTopAppBarOverride$SingleRowTopAppBar$actionsRow$1
                public final void invoke(Composer composer2, int i3) {
                    if (!composer2.shouldExecute((i3 & 3) != 2, i3 & 1)) {
                        composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1658896622, i3, -1, "androidx.compose.material3.DefaultSingleRowTopAppBarOverride.SingleRowTopAppBar.<anonymous> (AppBar.kt:2537)");
                    }
                    Arrangement.Horizontal end = Arrangement.INSTANCE.getEnd();
                    Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                    Function3<RowScope, Composer, Integer, Unit> actions = singleRowTopAppBarOverrideScope.getActions();
                    Modifier.Companion companion = Modifier.INSTANCE;
                    MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(end, centerVertically, composer2, 54);
                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                    CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, companion);
                    ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                    Function0<ComposeUiNode> constructor = companion2.getConstructor();
                    if (composer2.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composer2.startReusableNode();
                    if (composer2.getInserting()) {
                        composer2.createNode(constructor);
                    } else {
                        composer2.useNode();
                    }
                    Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer2);
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion2.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                    actions.invoke(RowScopeInstance.INSTANCE, composer2, 6);
                    composer2.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }
            }, composerStartRestartGroup, 54);
            if (singleRowTopAppBarOverrideScope.getScrollBehavior() == null || singleRowTopAppBarOverrideScope.getScrollBehavior().getIsPinned()) {
                composerStartRestartGroup.startReplaceGroup(690108113);
                composerStartRestartGroup.endReplaceGroup();
                modifierDraggable$default = Modifier.INSTANCE;
            } else {
                composerStartRestartGroup.startReplaceGroup(689467622);
                Modifier.Companion companion = Modifier.INSTANCE;
                Orientation orientation = Orientation.Vertical;
                int i3 = i2 & 14;
                boolean z = i3 == 4;
                Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = new Function1() { // from class: ti3
                        public final Object invoke(Object obj) {
                            return DefaultSingleRowTopAppBarOverride.b(singleRowTopAppBarOverrideScope, ((Float) obj).floatValue());
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                DraggableState draggableStateRememberDraggableState = DraggableKt.rememberDraggableState((Function1) objRememberedValue2, composerStartRestartGroup, 0);
                boolean z2 = i3 == 4;
                Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (z2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue3 = new DefaultSingleRowTopAppBarOverride$SingleRowTopAppBar$appBarDragModifier$2$1(singleRowTopAppBarOverrideScope, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                modifierDraggable$default = DraggableKt.draggable$default(companion, draggableStateRememberDraggableState, orientation, false, (MutableInteractionSource) null, false, (Function3) null, (Function3) objRememberedValue3, false, 188, (Object) null);
                composerStartRestartGroup.endReplaceGroup();
            }
            Modifier modifierThen = singleRowTopAppBarOverrideScope.getModifier().then(modifierDraggable$default);
            boolean zChanged2 = composerStartRestartGroup.changed(state);
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = new Function1() { // from class: ui3
                    public final Object invoke(Object obj) {
                        return DefaultSingleRowTopAppBarOverride.e(state, (DrawScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            Modifier modifierDrawBehind = DrawModifierKt.drawBehind(modifierThen, (Function1) objRememberedValue4);
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion2 = Composer.INSTANCE;
            if (objRememberedValue5 == companion2.getEmpty()) {
                objRememberedValue5 = new Function1() { // from class: vi3
                    public final Object invoke(Object obj) {
                        return DefaultSingleRowTopAppBarOverride.d((SemanticsPropertyReceiver) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifierDrawBehind, false, (Function1) objRememberedValue5, 1, null);
            Unit unit = Unit.INSTANCE;
            Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue6 == companion2.getEmpty()) {
                objRememberedValue6 = new PointerInputEventHandler() { // from class: androidx.compose.material3.DefaultSingleRowTopAppBarOverride$SingleRowTopAppBar$4$1
                    @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
                    public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
                        return Unit.INSTANCE;
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
            }
            Modifier modifierPointerInput = SuspendingPointerInputFilterKt.pointerInput(modifierSemantics$default, unit, (PointerInputEventHandler) objRememberedValue6);
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierPointerInput);
            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
            Function0<ComposeUiNode> constructor = companion3.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, companion3.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion3.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion3.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion3.getSetModifier());
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            Modifier modifierAdjustHeightOffsetLimit = AppBarKt.adjustHeightOffsetLimit(ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, singleRowTopAppBarOverrideScope.getWindowInsets())), singleRowTopAppBarOverrideScope.getScrollBehavior());
            boolean z3 = (i2 & 14) == 4;
            Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (z3 || objRememberedValue7 == companion2.getEmpty()) {
                objRememberedValue7 = new FloatProducer() { // from class: wi3
                    @Override // androidx.compose.material3.internal.FloatProducer
                    public final float invoke() {
                        return DefaultSingleRowTopAppBarOverride.f(singleRowTopAppBarOverrideScope);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
            }
            FloatProducer floatProducer = (FloatProducer) objRememberedValue7;
            long navigationIconContentColor = singleRowTopAppBarOverrideScope.getColors().getNavigationIconContentColor();
            long titleContentColor = singleRowTopAppBarOverrideScope.getColors().getTitleContentColor();
            long actionIconContentColor = singleRowTopAppBarOverrideScope.getColors().getActionIconContentColor();
            long subtitleContentColor = singleRowTopAppBarOverrideScope.getColors().getSubtitleContentColor();
            Function2<Composer, Integer, Unit> title = singleRowTopAppBarOverrideScope.getTitle();
            TextStyle titleTextStyle = singleRowTopAppBarOverrideScope.getTitleTextStyle();
            Function2<Composer, Integer, Unit> subtitle = singleRowTopAppBarOverrideScope.getSubtitle();
            TextStyle subtitleTextStyle = singleRowTopAppBarOverrideScope.getSubtitleTextStyle();
            Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
            Alignment.Horizontal titleHorizontalAlignment = singleRowTopAppBarOverrideScope.getTitleHorizontalAlignment();
            Function2<Composer, Integer, Unit> navigationIcon = singleRowTopAppBarOverrideScope.getNavigationIcon();
            float expandedHeight = singleRowTopAppBarOverrideScope.getExpandedHeight();
            Object objRememberedValue8 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue8 == companion2.getEmpty()) {
                objRememberedValue8 = new Function0() { // from class: xi3
                    public final Object invoke() {
                        return Float.valueOf(DefaultSingleRowTopAppBarOverride.c());
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue8);
            }
            AppBarKt.m103TopAppBarLayoutlyUyIHI(modifierAdjustHeightOffsetLimit, floatProducer, navigationIconContentColor, titleContentColor, subtitleContentColor, actionIconContentColor, title, titleTextStyle, subtitle, subtitleTextStyle, (Function0) objRememberedValue8, center, titleHorizontalAlignment, 0, false, navigationIcon, composableLambdaRememberComposableLambda, expandedHeight, composerStartRestartGroup, 0, 1600566);
            composerStartRestartGroup = composerStartRestartGroup;
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: yi3
                public final Object invoke(Object obj, Object obj2) {
                    return DefaultSingleRowTopAppBarOverride.a(this.b, singleRowTopAppBarOverrideScope, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
