package androidx.compose.material3;

import androidx.compose.foundation.gestures.DraggableKt;
import androidx.compose.foundation.gestures.DraggableState;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.WindowInsetsKt;
import androidx.compose.foundation.layout.WindowInsetsPaddingKt;
import androidx.compose.foundation.layout.WindowInsetsSides;
import androidx.compose.material3.DefaultTwoRowsTopAppBarOverride;
import androidx.compose.material3.internal.FloatProducer;
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
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\u0004\u001a\u00020\u0005*\u00020\u0006H\u0017¢\u0006\u0002\u0010\u0007¨\u0006\b²\u0006\n\u0010\t\u001a\u00020\nX\u008a\u0084\u0002"}, d2 = {"Landroidx/compose/material3/DefaultTwoRowsTopAppBarOverride;", "Landroidx/compose/material3/TwoRowsTopAppBarOverride;", "<init>", "()V", "TwoRowsTopAppBar", "", "Landroidx/compose/material3/TwoRowsTopAppBarOverrideScope;", "(Landroidx/compose/material3/TwoRowsTopAppBarOverrideScope;Landroidx/compose/runtime/Composer;I)V", "material3", "hideTopRowSemantics", ""}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class DefaultTwoRowsTopAppBarOverride implements TwoRowsTopAppBarOverride {
    public static final int $stable = 0;
    public static final DefaultTwoRowsTopAppBarOverride INSTANCE = new DefaultTwoRowsTopAppBarOverride();

    private DefaultTwoRowsTopAppBarOverride() {
    }

    private static final boolean TwoRowsTopAppBar$lambda$13(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    public static Unit a(TwoRowsTopAppBarOverrideScope twoRowsTopAppBarOverrideScope, float f) {
        TopAppBarState state = twoRowsTopAppBarOverrideScope.getScrollBehavior().getState();
        state.setHeightOffset(state.getHeightOffset() + f);
        return Unit.INSTANCE;
    }

    public static Unit b(DefaultTwoRowsTopAppBarOverride defaultTwoRowsTopAppBarOverride, TwoRowsTopAppBarOverrideScope twoRowsTopAppBarOverrideScope, int i, Composer composer, int i2) {
        defaultTwoRowsTopAppBarOverride.TwoRowsTopAppBar(twoRowsTopAppBarOverrideScope, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static boolean c(Function0 function0) {
        return ((Number) function0.invoke()).floatValue() < 0.5f;
    }

    public static Unit d(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.setTraversalGroup(semanticsPropertyReceiver, true);
        return Unit.INSTANCE;
    }

    public static float e() {
        return 0.0f;
    }

    public static float f(Function0 function0) {
        return AppBarKt.getTopTitleAlphaEasing().transform(((Number) function0.invoke()).floatValue());
    }

    public static Unit g(Function0 function0, DrawScope drawScope) {
        DrawScope.m3702drawRectnJ9OG0$default(drawScope, ((Color) function0.invoke()).m3144unboximpl(), 0L, 0L, 0.0f, null, null, 0, 126, null);
        return Unit.INSTANCE;
    }

    public static float h(TwoRowsTopAppBarOverrideScope twoRowsTopAppBarOverrideScope) {
        TopAppBarState state;
        TopAppBarScrollBehavior scrollBehavior = twoRowsTopAppBarOverrideScope.getScrollBehavior();
        if (scrollBehavior == null || (state = scrollBehavior.getState()) == null) {
            return 0.0f;
        }
        return state.getHeightOffset();
    }

    public static float i(Function0 function0) {
        return 1.0f - ((Number) function0.invoke()).floatValue();
    }

    public static float j(TwoRowsTopAppBarOverrideScope twoRowsTopAppBarOverrideScope) {
        TopAppBarState state;
        TopAppBarScrollBehavior scrollBehavior = twoRowsTopAppBarOverrideScope.getScrollBehavior();
        if (scrollBehavior == null || (state = scrollBehavior.getState()) == null) {
            return 0.0f;
        }
        return state.getCollapsedFraction();
    }

    @Override // androidx.compose.material3.TwoRowsTopAppBarOverride
    public void TwoRowsTopAppBar(final TwoRowsTopAppBarOverrideScope twoRowsTopAppBarOverrideScope, Composer composer, final int i) {
        int i2;
        final TwoRowsTopAppBarOverrideScope twoRowsTopAppBarOverrideScope2;
        Composer composer2;
        Modifier modifierDraggable$default;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1640665680);
        if ((i & 6) == 0) {
            i2 = i | (composerStartRestartGroup.changed(twoRowsTopAppBarOverrideScope) ? 4 : 2);
        } else {
            i2 = i;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1640665680, i2, -1, "androidx.compose.material3.DefaultTwoRowsTopAppBarOverride.TwoRowsTopAppBar (AppBar.kt:2732)");
            }
            if (Float.isNaN(twoRowsTopAppBarOverrideScope.getCollapsedHeight()) || (Float.floatToRawIntBits(twoRowsTopAppBarOverrideScope.getCollapsedHeight()) & Integer.MAX_VALUE) >= 2139095040) {
                w01.a("The collapsedHeight is expected to be specified and finite");
                return;
            }
            if (Float.isNaN(twoRowsTopAppBarOverrideScope.getExpandedHeight()) || (Float.floatToRawIntBits(twoRowsTopAppBarOverrideScope.getExpandedHeight()) & Integer.MAX_VALUE) >= 2139095040) {
                w01.a("The expandedHeight is expected to be specified and finite");
                return;
            }
            if (Dp.m6021compareTo0680j_4(twoRowsTopAppBarOverrideScope.getExpandedHeight(), twoRowsTopAppBarOverrideScope.getCollapsedHeight()) < 0) {
                w01.a("The expandedHeight is expected to be greater or equal to the collapsedHeight");
                return;
            }
            int iMo4551roundToPx0680j_4 = ((Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity())).mo4551roundToPx0680j_4(twoRowsTopAppBarOverrideScope.getTitleBottomPadding());
            int i3 = i2 & 14;
            boolean z = i3 == 4;
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: lj3
                    public final Object invoke() {
                        return Float.valueOf(DefaultTwoRowsTopAppBarOverride.j(twoRowsTopAppBarOverrideScope));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final Function0 function0 = (Function0) objRememberedValue;
            boolean zChanged = (i3 == 4) | composerStartRestartGroup.changed(function0);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function0<Color>() { // from class: androidx.compose.material3.DefaultTwoRowsTopAppBarOverride$TwoRowsTopAppBar$appBarContainerColor$1$1
                    public /* bridge */ /* synthetic */ Object invoke() {
                        return Color.m3124boximpl(m402invoke0d7_KjU());
                    }

                    /* JADX INFO: renamed from: invoke-0d7_KjU, reason: not valid java name */
                    public final long m402invoke0d7_KjU() {
                        return twoRowsTopAppBarOverrideScope.getColors().m1292containerColorvNxB06k$material3(((Number) function0.invoke()).floatValue());
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            final Function0 function1 = (Function0) objRememberedValue2;
            ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1333673671, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DefaultTwoRowsTopAppBarOverride$TwoRowsTopAppBar$actionsRow$1
                public final void invoke(Composer composer3, int i4) {
                    if (!composer3.shouldExecute((i4 & 3) != 2, i4 & 1)) {
                        composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1333673671, i4, -1, "androidx.compose.material3.DefaultTwoRowsTopAppBarOverride.TwoRowsTopAppBar.<anonymous> (AppBar.kt:2755)");
                    }
                    Arrangement.Horizontal end = Arrangement.INSTANCE.getEnd();
                    Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                    Function3<RowScope, Composer, Integer, Unit> actions = twoRowsTopAppBarOverrideScope.getActions();
                    Modifier.Companion companion = Modifier.INSTANCE;
                    MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(end, centerVertically, composer3, 54);
                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                    CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, companion);
                    ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                    Function0<ComposeUiNode> constructor = companion2.getConstructor();
                    if (composer3.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composer3.startReusableNode();
                    if (composer3.getInserting()) {
                        composer3.createNode(constructor);
                    } else {
                        composer3.useNode();
                    }
                    Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer3);
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion2.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                    actions.invoke(RowScopeInstance.INSTANCE, composer3, 6);
                    composer3.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }
            }, composerStartRestartGroup, 54);
            boolean zChanged2 = composerStartRestartGroup.changed(function0);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = new Function0() { // from class: mj3
                    public final Object invoke() {
                        return Float.valueOf(DefaultTwoRowsTopAppBarOverride.f(function0));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            Function0 function2 = (Function0) objRememberedValue3;
            boolean zChanged3 = composerStartRestartGroup.changed(function0);
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = new Function0() { // from class: nj3
                    public final Object invoke() {
                        return Float.valueOf(DefaultTwoRowsTopAppBarOverride.i(function0));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            Function0 function3 = (Function0) objRememberedValue4;
            boolean zChanged4 = composerStartRestartGroup.changed(function0);
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (zChanged4 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue5 = SnapshotStateKt.derivedStateOf(new Function0() { // from class: oj3
                    public final Object invoke() {
                        return Boolean.valueOf(DefaultTwoRowsTopAppBarOverride.c(function0));
                    }
                });
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            State state = (State) objRememberedValue5;
            boolean z2 = !TwoRowsTopAppBar$lambda$13(state);
            if (twoRowsTopAppBarOverrideScope.getScrollBehavior() == null || twoRowsTopAppBarOverrideScope.getScrollBehavior().getIsPinned()) {
                composerStartRestartGroup.startReplaceGroup(-340499894);
                composerStartRestartGroup.endReplaceGroup();
                modifierDraggable$default = Modifier.INSTANCE;
            } else {
                composerStartRestartGroup.startReplaceGroup(-341140385);
                Modifier.Companion companion = Modifier.INSTANCE;
                Orientation orientation = Orientation.Vertical;
                boolean z3 = i3 == 4;
                Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                if (z3 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue6 = new Function1() { // from class: pj3
                        public final Object invoke(Object obj) {
                            return DefaultTwoRowsTopAppBarOverride.a(twoRowsTopAppBarOverrideScope, ((Float) obj).floatValue());
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                }
                DraggableState draggableStateRememberDraggableState = DraggableKt.rememberDraggableState((Function1) objRememberedValue6, composerStartRestartGroup, 0);
                boolean z4 = i3 == 4;
                Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
                if (z4 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue7 = new DefaultTwoRowsTopAppBarOverride$TwoRowsTopAppBar$appBarDragModifier$2$1(twoRowsTopAppBarOverrideScope, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
                }
                modifierDraggable$default = DraggableKt.draggable$default(companion, draggableStateRememberDraggableState, orientation, false, (MutableInteractionSource) null, false, (Function3) null, (Function3) objRememberedValue7, false, 188, (Object) null);
                composerStartRestartGroup.endReplaceGroup();
            }
            Modifier modifierThen = twoRowsTopAppBarOverrideScope.getModifier().then(modifierDraggable$default);
            boolean zChanged5 = composerStartRestartGroup.changed(function1);
            Object objRememberedValue8 = composerStartRestartGroup.rememberedValue();
            if (zChanged5 || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue8 = new Function1() { // from class: qj3
                    public final Object invoke(Object obj) {
                        return DefaultTwoRowsTopAppBarOverride.g(function1, (DrawScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue8);
            }
            Modifier modifierDrawBehind = DrawModifierKt.drawBehind(modifierThen, (Function1) objRememberedValue8);
            Object objRememberedValue9 = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion2 = Composer.INSTANCE;
            if (objRememberedValue9 == companion2.getEmpty()) {
                objRememberedValue9 = new Function1() { // from class: rj3
                    public final Object invoke(Object obj) {
                        return DefaultTwoRowsTopAppBarOverride.d((SemanticsPropertyReceiver) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue9);
            }
            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifierDrawBehind, false, (Function1) objRememberedValue9, 1, null);
            Unit unit = Unit.INSTANCE;
            Object objRememberedValue10 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue10 == companion2.getEmpty()) {
                objRememberedValue10 = new PointerInputEventHandler() { // from class: androidx.compose.material3.DefaultTwoRowsTopAppBarOverride$TwoRowsTopAppBar$6$1
                    @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
                    public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
                        return Unit.INSTANCE;
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue10);
            }
            Modifier modifierPointerInput = SuspendingPointerInputFilterKt.pointerInput(modifierSemantics$default, unit, (PointerInputEventHandler) objRememberedValue10);
            Alignment.Companion companion3 = Alignment.INSTANCE;
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(companion3.getTopStart(), false);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierPointerInput);
            ComposeUiNode.Companion companion4 = ComposeUiNode.INSTANCE;
            Function0<ComposeUiNode> constructor = companion4.getConstructor();
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
            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, companion4.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion4.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion4.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion4.getSetModifier());
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            Modifier.Companion companion5 = Modifier.INSTANCE;
            Arrangement arrangement = Arrangement.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(arrangement.getTop(), companion3.getStart(), composerStartRestartGroup, 0);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion5);
            Function0<ComposeUiNode> constructor2 = companion4.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor2);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2388constructorimpl2 = Updater.m2388constructorimpl(composerStartRestartGroup);
            Updater.m2396setimpl(composerM2388constructorimpl2, measurePolicyColumnMeasurePolicy, companion4.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl2, currentCompositionLocalMap2, companion4.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = companion4.getSetCompositeKeyHash();
            if (composerM2388constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composerM2388constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM2388constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m2396setimpl(composerM2388constructorimpl2, modifierMaterializeModifier2, companion4.getSetModifier());
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            Modifier modifierClipToBounds = ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(companion5, twoRowsTopAppBarOverrideScope.getWindowInsets()));
            Object objRememberedValue11 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue11 == companion2.getEmpty()) {
                objRememberedValue11 = new FloatProducer() { // from class: sj3
                    @Override // androidx.compose.material3.internal.FloatProducer
                    public final float invoke() {
                        return DefaultTwoRowsTopAppBarOverride.e();
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue11);
            }
            AppBarKt.m103TopAppBarLayoutlyUyIHI(modifierClipToBounds, (FloatProducer) objRememberedValue11, twoRowsTopAppBarOverrideScope.getColors().getNavigationIconContentColor(), twoRowsTopAppBarOverrideScope.getColors().getTitleContentColor(), twoRowsTopAppBarOverrideScope.getColors().getSubtitleContentColor(), twoRowsTopAppBarOverrideScope.getColors().getActionIconContentColor(), twoRowsTopAppBarOverrideScope.getSmallTitle(), twoRowsTopAppBarOverrideScope.getSmallTitleTextStyle(), twoRowsTopAppBarOverrideScope.getSmallSubtitle(), twoRowsTopAppBarOverrideScope.getSmallSubtitleTextStyle(), function2, arrangement.getCenter(), twoRowsTopAppBarOverrideScope.getTitleHorizontalAlignment(), 0, TwoRowsTopAppBar$lambda$13(state), twoRowsTopAppBarOverrideScope.getNavigationIcon(), composableLambdaRememberComposableLambda, twoRowsTopAppBarOverrideScope.getCollapsedHeight(), composerStartRestartGroup, 0, 1575984);
            Modifier modifierAdjustHeightOffsetLimit = AppBarKt.adjustHeightOffsetLimit(ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(companion5, WindowInsetsKt.only-bOOhFvg(twoRowsTopAppBarOverrideScope.getWindowInsets(), WindowInsetsSides.Companion.getHorizontal-JoeWqyM()))), twoRowsTopAppBarOverrideScope.getScrollBehavior());
            boolean z5 = i3 == 4;
            Object objRememberedValue12 = composerStartRestartGroup.rememberedValue();
            if (z5 || objRememberedValue12 == companion2.getEmpty()) {
                twoRowsTopAppBarOverrideScope2 = twoRowsTopAppBarOverrideScope;
                objRememberedValue12 = new FloatProducer() { // from class: tj3
                    @Override // androidx.compose.material3.internal.FloatProducer
                    public final float invoke() {
                        return DefaultTwoRowsTopAppBarOverride.h(twoRowsTopAppBarOverrideScope2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue12);
            } else {
                twoRowsTopAppBarOverrideScope2 = twoRowsTopAppBarOverrideScope;
            }
            FloatProducer floatProducer = (FloatProducer) objRememberedValue12;
            long navigationIconContentColor = twoRowsTopAppBarOverrideScope2.getColors().getNavigationIconContentColor();
            long titleContentColor = twoRowsTopAppBarOverrideScope2.getColors().getTitleContentColor();
            long actionIconContentColor = twoRowsTopAppBarOverrideScope2.getColors().getActionIconContentColor();
            long subtitleContentColor = twoRowsTopAppBarOverrideScope2.getColors().getSubtitleContentColor();
            Function2<Composer, Integer, Unit> title = twoRowsTopAppBarOverrideScope2.getTitle();
            TextStyle titleTextStyle = twoRowsTopAppBarOverrideScope2.getTitleTextStyle();
            Function2<Composer, Integer, Unit> subtitle = twoRowsTopAppBarOverrideScope2.getSubtitle();
            TextStyle subtitleTextStyle = twoRowsTopAppBarOverrideScope2.getSubtitleTextStyle();
            Arrangement.Vertical bottom = arrangement.getBottom();
            Alignment.Horizontal titleHorizontalAlignment = twoRowsTopAppBarOverrideScope2.getTitleHorizontalAlignment();
            float fM6022constructorimpl = Dp.m6022constructorimpl(twoRowsTopAppBarOverrideScope2.getExpandedHeight() - twoRowsTopAppBarOverrideScope2.getCollapsedHeight());
            ComposableSingletons$AppBarKt composableSingletons$AppBarKt = ComposableSingletons$AppBarKt.INSTANCE;
            composer2 = composerStartRestartGroup;
            AppBarKt.m103TopAppBarLayoutlyUyIHI(modifierAdjustHeightOffsetLimit, floatProducer, navigationIconContentColor, titleContentColor, subtitleContentColor, actionIconContentColor, title, titleTextStyle, subtitle, subtitleTextStyle, function3, bottom, titleHorizontalAlignment, iMo4551roundToPx0680j_4, z2, composableSingletons$AppBarKt.m302getLambda$2101264077$material3(), composableSingletons$AppBarKt.getLambda$37575796$material3(), fM6022constructorimpl, composer2, 0, 1769520);
            composer2.endNode();
            composer2.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            twoRowsTopAppBarOverrideScope2 = twoRowsTopAppBarOverrideScope;
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: uj3
                public final Object invoke(Object obj, Object obj2) {
                    return DefaultTwoRowsTopAppBarOverride.b(this.b, twoRowsTopAppBarOverrideScope2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
