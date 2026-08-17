package androidx.compose.material3;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.material3.SheetDefaultsKt;
import androidx.compose.material3.SheetValue;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.Velocity;
import androidx.profileinstaller.ProfileVerifier;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import com.android.apksig.internal.apk.v4.V4Signature;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000V\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u001a$\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0011\u0010\u0003\u001a\r\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\u0002\b\u0005H\u0001¢\u0006\u0002\u0010\u0006\u001a;\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2!\u0010\r\u001a\u001d\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u00010\u000eH\u0000\u001aW\u0010\u0013\u001a\u00020\n2\b\b\u0002\u0010\u0014\u001a\u00020\u00152\u0014\b\u0002\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00150\u000e2\b\b\u0002\u0010\u0018\u001a\u00020\u00172\b\b\u0002\u0010\u0019\u001a\u00020\u00152\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001bH\u0001¢\u0006\u0004\b\u001d\u0010\u001e\"\u0010\u0010\u001f\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010 \"\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\u000f0\"X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"DragHandleWithTooltip", "", "Landroidx/compose/foundation/layout/ColumnScope;", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/foundation/layout/ColumnScope;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "sheetState", "Landroidx/compose/material3/SheetState;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "onFling", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "velocity", "rememberSheetState", "skipPartiallyExpanded", "", "confirmValueChange", "Landroidx/compose/material3/SheetValue;", "initialValue", "skipHiddenState", "positionalThreshold", "Landroidx/compose/ui/unit/Dp;", "velocityThreshold", "rememberSheetState-AGcomas", "(ZLkotlin/jvm/functions/Function1;Landroidx/compose/material3/SheetValue;ZFFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/SheetState;", "DragHandleVerticalPadding", "F", "BottomSheetAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "material3"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SheetDefaultsKt {
    private static final float DragHandleVerticalPadding = Dp.m6022constructorimpl(22.0f);
    private static final AnimationSpec<Float> BottomSheetAnimationSpec = AnimationSpecKt.tween$default(ProgressIndicatorKt.CircularAnimationAdditionalRotationDuration, 0, EasingKt.getFastOutSlowInEasing(), 2, (Object) null);

    public static final NestedScrollConnection ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection(final SheetState sheetState, final Orientation orientation, final Function1<? super Float, Unit> function1) {
        return new NestedScrollConnection() { // from class: androidx.compose.material3.SheetDefaultsKt.ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection.1
            private final float offsetToFloat(long j) {
                return Float.intBitsToFloat((int) (orientation == Orientation.Horizontal ? j >> 32 : j & 4294967295L));
            }

            private final long toOffset(float f) {
                Orientation orientation2 = orientation;
                float f2 = orientation2 == Orientation.Horizontal ? f : 0.0f;
                if (orientation2 != Orientation.Vertical) {
                    f = 0.0f;
                }
                return Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(f)) & 4294967295L) | (((long) Float.floatToRawIntBits(f2)) << 32));
            }

            private final float velocityToFloat(long j) {
                return orientation == Orientation.Horizontal ? Velocity.m6260getXimpl(j) : Velocity.m6261getYimpl(j);
            }

            @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
            /* JADX INFO: renamed from: onPostFling-RZ2iAVY */
            public Object mo428onPostFlingRZ2iAVY(long j, long j2, Continuation<? super Velocity> continuation) {
                function1.invoke(Boxing.boxFloat(velocityToFloat(j2)));
                return Velocity.m6251boximpl(j2);
            }

            @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
            /* JADX INFO: renamed from: onPostScroll-DzOQY0M */
            public long mo429onPostScrollDzOQY0M(long consumed, long available, int source) {
                return NestedScrollSource.m4324equalsimpl0(source, NestedScrollSource.INSTANCE.m4336getUserInputWNlRxjI()) ? toOffset(sheetState.getAnchoredDraggableState$material3().dispatchRawDelta(offsetToFloat(available))) : Offset.INSTANCE.m2905getZeroF1C5BW0();
            }

            @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
            /* JADX INFO: renamed from: onPreFling-QWom1Mo, reason: not valid java name */
            public Object mo863onPreFlingQWom1Mo(long j, Continuation<? super Velocity> continuation) {
                float fVelocityToFloat = velocityToFloat(j);
                float fRequireOffset = sheetState.requireOffset();
                float fMinAnchor = sheetState.getAnchoredDraggableState$material3().getAnchors().minAnchor();
                if (fVelocityToFloat >= 0.0f || fRequireOffset <= fMinAnchor) {
                    j = Velocity.INSTANCE.m6271getZero9UxMQ8M();
                } else {
                    function1.invoke(Boxing.boxFloat(fVelocityToFloat));
                }
                return Velocity.m6251boximpl(j);
            }

            @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
            /* JADX INFO: renamed from: onPreScroll-OzD1aCk */
            public long mo430onPreScrollOzD1aCk(long available, int source) {
                float fOffsetToFloat = offsetToFloat(available);
                return (fOffsetToFloat >= 0.0f || !NestedScrollSource.m4324equalsimpl0(source, NestedScrollSource.INSTANCE.m4336getUserInputWNlRxjI())) ? Offset.INSTANCE.m2905getZeroF1C5BW0() : toOffset(sheetState.getAnchoredDraggableState$material3().dispatchRawDelta(fOffsetToFloat));
            }
        };
    }

    public static final void DragHandleWithTooltip(final ColumnScope columnScope, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1033612924);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(columnScope) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 32 : 16;
        }
        int i3 = i2;
        if (composerStartRestartGroup.shouldExecute((i3 & 19) != 18, i3 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1033612924, i3, -1, "androidx.compose.material3.DragHandleWithTooltip (SheetDefaults.kt:432)");
            }
            Strings.Companion companion = Strings.INSTANCE;
            final String strM1471getString2EP1pXo = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_bottom_sheet_drag_handle_description), composerStartRestartGroup, 0);
            Modifier.Companion companion2 = Modifier.INSTANCE;
            Alignment.Companion companion3 = Alignment.INSTANCE;
            Modifier modifierAlign = columnScope.align(companion2, companion3.getCenterHorizontally());
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(companion3.getTopStart(), false);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierAlign);
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
            TooltipKt.TooltipBox(TooltipDefaults.INSTANCE.m1276rememberTooltipPositionProviderHu5FAss(TooltipAnchorPosition.INSTANCE.m1263getAbovelOKsHw4(), 0.0f, composerStartRestartGroup, 390, 2), ComposableLambdaKt.rememberComposableLambda(2059851063, true, new Function3<TooltipScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SheetDefaultsKt$DragHandleWithTooltip$1$1
                public final void invoke(TooltipScope tooltipScope, Composer composer2, int i4) {
                    int i5;
                    if ((i4 & 6) == 0) {
                        i5 = i4 | ((i4 & 8) == 0 ? composer2.changed(tooltipScope) : composer2.changedInstance(tooltipScope) ? 4 : 2);
                    } else {
                        i5 = i4;
                    }
                    if (!composer2.shouldExecute((i5 & 19) != 18, i5 & 1)) {
                        composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(2059851063, i5, -1, "androidx.compose.material3.DragHandleWithTooltip.<anonymous>.<anonymous> (SheetDefaults.kt:439)");
                    }
                    final String str = strM1471getString2EP1pXo;
                    TooltipKt.m1279PlainTooltipgv3ox5I(tooltipScope, null, null, 0.0f, null, 0L, 0L, 0.0f, 0.0f, ComposableLambdaKt.rememberComposableLambda(-999924215, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SheetDefaultsKt$DragHandleWithTooltip$1$1.1
                        public final void invoke(Composer composer3, int i6) {
                            if (!composer3.shouldExecute((i6 & 3) != 2, i6 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-999924215, i6, -1, "androidx.compose.material3.DragHandleWithTooltip.<anonymous>.<anonymous>.<anonymous> (SheetDefaults.kt:439)");
                            }
                            TextKt.m1097TextNvy7gAk(str, null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer3, 0, 0, 262142);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composer2, 54), composer2, (i5 & 14) | 805306368, 255);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(TooltipScope tooltipScope, Composer composer2, Integer num) {
                    invoke(tooltipScope, composer2, num.intValue());
                    return Unit.INSTANCE;
                }
            }, composerStartRestartGroup, 54), TooltipKt.rememberTooltipState(false, false, null, composerStartRestartGroup, 0, 7), null, null, false, false, false, function2, composerStartRestartGroup, ((i3 << 21) & 234881024) | 48, 248);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: b9d
                public final Object invoke(Object obj, Object obj2) {
                    return SheetDefaultsKt.d(columnScope, function2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static float a(Density density, float f) {
        return density.mo4557toPx0680j_4(f);
    }

    public static boolean b(SheetValue sheetValue) {
        return true;
    }

    public static SheetState c(boolean z, Function0 function0, Function0 function1, SheetValue sheetValue, Function1 function2, boolean z2) {
        return new SheetState(z, function0, function1, sheetValue, function2, z2);
    }

    public static Unit d(ColumnScope columnScope, Function2 function2, int i, Composer composer, int i2) {
        DragHandleWithTooltip(columnScope, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static float e(Density density, float f) {
        return density.mo4557toPx0680j_4(f);
    }

    /* JADX INFO: renamed from: rememberSheetState-AGcomas, reason: not valid java name */
    public static final SheetState m862rememberSheetStateAGcomas(boolean z, Function1<? super SheetValue, Boolean> function1, SheetValue sheetValue, boolean z2, float f, float f2, Composer composer, int i, int i2) {
        final Function1<? super SheetValue, Boolean> function2;
        final boolean z3 = (i2 & 1) != 0 ? false : z;
        if ((i2 & 2) != 0) {
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: x8d
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(SheetDefaultsKt.b((SheetValue) obj));
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            function2 = (Function1) objRememberedValue;
        } else {
            function2 = function1;
        }
        final SheetValue sheetValue2 = (i2 & 4) != 0 ? SheetValue.Hidden : sheetValue;
        final boolean z4 = (i2 & 8) != 0 ? false : z2;
        final float fM120getPositionalThresholdD9Ej5fM$material3 = (i2 & 16) != 0 ? BottomSheetDefaults.INSTANCE.m120getPositionalThresholdD9Ej5fM$material3() : f;
        final float fM123getVelocityThresholdD9Ej5fM$material3 = (i2 & 32) != 0 ? BottomSheetDefaults.INSTANCE.m123getVelocityThresholdD9Ej5fM$material3() : f2;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-20307384, i, -1, "androidx.compose.material3.rememberSheetState (SheetDefaults.kt:514)");
        }
        final Density density = (Density) composer.consume(CompositionLocalsKt.getLocalDensity());
        boolean z5 = true;
        boolean zChanged = composer.changed(density) | ((((57344 & i) ^ 24576) > 16384 && composer.changed(fM120getPositionalThresholdD9Ej5fM$material3)) || (i & 24576) == 16384);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new Function0() { // from class: y8d
                public final Object invoke() {
                    return Float.valueOf(SheetDefaultsKt.a(density, fM120getPositionalThresholdD9Ej5fM$material3));
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        final Function0<Float> function0 = (Function0) objRememberedValue2;
        boolean zChanged2 = composer.changed(density) | ((((458752 & i) ^ ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) > 131072 && composer.changed(fM123getVelocityThresholdD9Ej5fM$material3)) || (i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 131072);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue3 = new Function0() { // from class: z8d
                public final Object invoke() {
                    return Float.valueOf(SheetDefaultsKt.e(density, fM123getVelocityThresholdD9Ej5fM$material3));
                }
            };
            composer.updateRememberedValue(objRememberedValue3);
        }
        final Function0<Float> function3 = (Function0) objRememberedValue3;
        Object[] objArr = {Boolean.valueOf(z3), function2, Boolean.valueOf(z4)};
        Saver<SheetState, SheetValue> Saver = SheetState.INSTANCE.Saver(z3, function0, function3, function2, z4);
        boolean zChanged3 = ((((i & 112) ^ 48) > 32 && composer.changed(function2)) || (i & 48) == 32) | ((((i & 14) ^ 6) > 4 && composer.changed(z3)) || (i & 6) == 4) | composer.changed(function0) | composer.changed(function3) | ((((i & 896) ^ AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) > 256 && composer.changed(sheetValue2.ordinal())) || (i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 256);
        if ((((i & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 || !composer.changed(z4)) && (i & 3072) != 2048) {
            z5 = false;
        }
        boolean z6 = zChanged3 | z5;
        Object objRememberedValue4 = composer.rememberedValue();
        if (z6 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue4 = new Function0() { // from class: a9d
                public final Object invoke() {
                    return SheetDefaultsKt.c(z3, function0, function3, sheetValue2, function2, z4);
                }
            };
            composer.updateRememberedValue(objRememberedValue4);
        }
        SheetState sheetState = (SheetState) RememberSaveableKt.m2564rememberSaveable(objArr, (Saver) Saver, (Function0) objRememberedValue4, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return sheetState;
    }
}
