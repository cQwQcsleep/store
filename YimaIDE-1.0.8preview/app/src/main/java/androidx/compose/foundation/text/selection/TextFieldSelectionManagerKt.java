package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.contextmenu.ContextMenuScope;
import androidx.compose.foundation.contextmenu.ContextMenuState;
import androidx.compose.foundation.internal.PlatformUtils_androidKt;
import androidx.compose.foundation.text.CommonContextMenuAreaKt;
import androidx.compose.foundation.text.Handle;
import androidx.compose.foundation.text.LegacyTextFieldState;
import androidx.compose.foundation.text.LongPressTextDragObserverKt;
import androidx.compose.foundation.text.MenuItemsAvailability;
import androidx.compose.foundation.text.TextContextMenuItems;
import androidx.compose.foundation.text.TextDelegate;
import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.foundation.text.TextLayoutResultProxy;
import androidx.compose.foundation.text.selection.TextFieldSelectionManagerKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a%\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0001¢\u0006\u0002\u0010\b\u001a\u0014\u0010\t\u001a\u00020\u0003*\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u001f\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\rH\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a3\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00010\u0011¢\u0006\u0002\b\u0013*\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0000¨\u0006\u0019"}, d2 = {"TextFieldSelectionHandle", "", "isStartHandle", "", "direction", "Landroidx/compose/ui/text/style/ResolvedTextDirection;", "manager", "Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "(ZLandroidx/compose/ui/text/style/ResolvedTextDirection;Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;Landroidx/compose/runtime/Composer;I)V", "isSelectionHandleInVisibleBoundDefault", "calculateSelectionMagnifierCenterAndroid", "Landroidx/compose/ui/geometry/Offset;", "magnifierSize", "Landroidx/compose/ui/unit/IntSize;", "calculateSelectionMagnifierCenterAndroid-O0kMr_c", "(Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;J)J", "contextMenuBuilder", "Lkotlin/Function1;", "Landroidx/compose/foundation/contextmenu/ContextMenuScope;", "Lkotlin/ExtensionFunctionType;", "contextMenuState", "Landroidx/compose/foundation/contextmenu/ContextMenuState;", "itemsAvailability", "Landroidx/compose/runtime/State;", "Landroidx/compose/foundation/text/MenuItemsAvailability;", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class TextFieldSelectionManagerKt {

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Handle.values().length];
            try {
                iArr[Handle.Cursor.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Handle.SelectionStart.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Handle.SelectionEnd.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final void TextFieldSelectionHandle(final boolean z, final ResolvedTextDirection resolvedTextDirection, final TextFieldSelectionManager textFieldSelectionManager, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1344558920);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(resolvedTextDirection.ordinal()) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(textFieldSelectionManager) ? 256 : 128;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 147) != 146, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1344558920, i2, -1, "androidx.compose.foundation.text.selection.TextFieldSelectionHandle (TextFieldSelectionManager.kt:1356)");
            }
            int i3 = i2 & 14;
            boolean zChanged = (i3 == 4) | composerStartRestartGroup.changed(textFieldSelectionManager);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = textFieldSelectionManager.handleDragObserver$foundation(z);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final TextDragObserver textDragObserver = (TextDragObserver) objRememberedValue;
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(textFieldSelectionManager) | (i3 == 4);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue2 == Composer.Companion.getEmpty()) {
                objRememberedValue2 = new OffsetProvider() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManagerKt$TextFieldSelectionHandle$1$1
                    @Override // androidx.compose.foundation.text.selection.OffsetProvider
                    /* JADX INFO: renamed from: provide-F1C5BW0 */
                    public final long mo1306provideF1C5BW0() {
                        return textFieldSelectionManager.m1819getHandlePositiontuRUvjQ$foundation(z);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            OffsetProvider offsetProvider = (OffsetProvider) objRememberedValue2;
            boolean z2 = TextRange.getReversed-impl(textFieldSelectionManager.getValue$foundation().getSelection-d9O1mEE());
            float handleLineHeight$foundation = textFieldSelectionManager.getHandleLineHeight$foundation(z);
            Modifier.Companion companion = Modifier.Companion;
            boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(textDragObserver);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance2 || objRememberedValue3 == Composer.Companion.getEmpty()) {
                objRememberedValue3 = new PointerInputEventHandler() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManagerKt$TextFieldSelectionHandle$2$1
                    public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
                        Object objDetectDownAndDragGesturesWithObserver = LongPressTextDragObserverKt.detectDownAndDragGesturesWithObserver(pointerInputScope, textDragObserver, continuation);
                        return objDetectDownAndDragGesturesWithObserver == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDetectDownAndDragGesturesWithObserver : Unit.INSTANCE;
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            AndroidSelectionHandles_androidKt.m1735SelectionHandlewLIcFTc(offsetProvider, z, resolvedTextDirection, z2, 0L, handleLineHeight$foundation, SuspendingPointerInputFilterKt.pointerInput(companion, textDragObserver, (PointerInputEventHandler) objRememberedValue3), composerStartRestartGroup, (i2 << 3) & 1008, 16);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: m8e
                public final Object invoke(Object obj, Object obj2) {
                    return TextFieldSelectionManagerKt.c(z, resolvedTextDirection, textFieldSelectionManager, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static Unit c(boolean z, ResolvedTextDirection resolvedTextDirection, TextFieldSelectionManager textFieldSelectionManager, int i, Composer composer, int i2) {
        TextFieldSelectionHandle(z, resolvedTextDirection, textFieldSelectionManager, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: calculateSelectionMagnifierCenterAndroid-O0kMr_c, reason: not valid java name */
    public static final long m1826calculateSelectionMagnifierCenterAndroidO0kMr_c(TextFieldSelectionManager textFieldSelectionManager, long j) {
        int i;
        TextLayoutResultProxy layoutResult;
        TextDelegate textDelegate;
        AnnotatedString text;
        Offset offsetM1817getCurrentDragPosition_m7T9E = textFieldSelectionManager.m1817getCurrentDragPosition_m7T9E();
        if (offsetM1817getCurrentDragPosition_m7T9E == null) {
            return Offset.Companion.getUnspecified-F1C5BW0();
        }
        long j2 = offsetM1817getCurrentDragPosition_m7T9E.unbox-impl();
        AnnotatedString transformedText$foundation = textFieldSelectionManager.getTransformedText$foundation();
        if (transformedText$foundation == null || transformedText$foundation.length() == 0) {
            return Offset.Companion.getUnspecified-F1C5BW0();
        }
        Handle draggingHandle = textFieldSelectionManager.getDraggingHandle();
        int i2 = draggingHandle == null ? -1 : WhenMappings.$EnumSwitchMapping$0[draggingHandle.ordinal()];
        if (i2 == -1) {
            return Offset.Companion.getUnspecified-F1C5BW0();
        }
        if (i2 == 1 || i2 == 2) {
            i = TextRange.getStart-impl(textFieldSelectionManager.getValue$foundation().getSelection-d9O1mEE());
        } else {
            if (i2 != 3) {
                bu8.a();
                return 0L;
            }
            i = TextRange.getEnd-impl(textFieldSelectionManager.getValue$foundation().getSelection-d9O1mEE());
        }
        LegacyTextFieldState state = textFieldSelectionManager.getState();
        if (state == null || (layoutResult = state.getLayoutResult()) == null) {
            return Offset.Companion.getUnspecified-F1C5BW0();
        }
        LegacyTextFieldState state2 = textFieldSelectionManager.getState();
        if (state2 == null || (textDelegate = state2.getTextDelegate()) == null || (text = textDelegate.getText()) == null) {
            return Offset.Companion.getUnspecified-F1C5BW0();
        }
        int iCoerceIn = RangesKt.coerceIn(textFieldSelectionManager.getOffsetMapping().originalToTransformed(i), 0, text.length());
        float fIntBitsToFloat = Float.intBitsToFloat((int) (layoutResult.m1443translateDecorationToInnerCoordinatesMKHz9U$foundation(j2) >> 32));
        TextLayoutResult value = layoutResult.getValue();
        int lineForOffset = value.getLineForOffset(iCoerceIn);
        float lineLeft = value.getLineLeft(lineForOffset);
        float lineRight = value.getLineRight(lineForOffset);
        float fCoerceIn = RangesKt.coerceIn(fIntBitsToFloat, Math.min(lineLeft, lineRight), Math.max(lineLeft, lineRight));
        if (!IntSize.equals-impl0(j, IntSize.Companion.getZero-YbymL2g()) && Math.abs(fIntBitsToFloat - fCoerceIn) > ((int) (j >> 32)) / 2) {
            return Offset.Companion.getUnspecified-F1C5BW0();
        }
        float lineTop = value.getLineTop(lineForOffset);
        return Offset.constructor-impl((((long) Float.floatToRawIntBits(fCoerceIn)) << 32) | (((long) Float.floatToRawIntBits(((value.getLineBottom(lineForOffset) - lineTop) / 2.0f) + lineTop)) & 4294967295L));
    }

    public static final Function1<ContextMenuScope, Unit> contextMenuBuilder(final TextFieldSelectionManager textFieldSelectionManager, final ContextMenuState contextMenuState, final State<MenuItemsAvailability> state) {
        return new Function1() { // from class: l8e
            public final Object invoke(Object obj) {
                return TextFieldSelectionManagerKt.g(state, textFieldSelectionManager, contextMenuState, (ContextMenuScope) obj);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit contextMenuBuilder$lambda$0$0(TextFieldSelectionManager textFieldSelectionManager) {
        textFieldSelectionManager.cut$foundation();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit contextMenuBuilder$lambda$0$1(TextFieldSelectionManager textFieldSelectionManager) {
        textFieldSelectionManager.copy$foundation(false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit contextMenuBuilder$lambda$0$2(TextFieldSelectionManager textFieldSelectionManager) {
        textFieldSelectionManager.paste$foundation();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit contextMenuBuilder$lambda$0$3(TextFieldSelectionManager textFieldSelectionManager) {
        textFieldSelectionManager.selectAll$foundation();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit contextMenuBuilder$lambda$0$4(TextFieldSelectionManager textFieldSelectionManager) {
        textFieldSelectionManager.autofill$foundation();
        return Unit.INSTANCE;
    }

    private static final void contextMenuBuilder$lambda$0$textFieldItem(ContextMenuScope contextMenuScope, ContextMenuState contextMenuState, TextContextMenuItems textContextMenuItems, boolean z, Function0<Unit> function0) {
        if (z) {
            ContextMenuScope.item$default(contextMenuScope, new CommonContextMenuAreaKt.AnonymousClass1(textContextMenuItems), null, false, null, new CommonContextMenuAreaKt.AnonymousClass2(function0, contextMenuState), 14, null);
        }
    }

    public static Unit g(State state, final TextFieldSelectionManager textFieldSelectionManager, ContextMenuState contextMenuState, ContextMenuScope contextMenuScope) {
        int iM1394unboximpl = ((MenuItemsAvailability) state.getValue()).m1394unboximpl();
        contextMenuBuilder$lambda$0$textFieldItem(contextMenuScope, contextMenuState, TextContextMenuItems.Cut, MenuItemsAvailability.m1389getCanCutimpl(iM1394unboximpl), new Function0() { // from class: n8e
            public final Object invoke() {
                return TextFieldSelectionManagerKt.contextMenuBuilder$lambda$0$0(textFieldSelectionManager);
            }
        });
        contextMenuBuilder$lambda$0$textFieldItem(contextMenuScope, contextMenuState, TextContextMenuItems.Copy, MenuItemsAvailability.m1388getCanCopyimpl(iM1394unboximpl), new Function0() { // from class: o8e
            public final Object invoke() {
                return TextFieldSelectionManagerKt.contextMenuBuilder$lambda$0$1(textFieldSelectionManager);
            }
        });
        contextMenuBuilder$lambda$0$textFieldItem(contextMenuScope, contextMenuState, TextContextMenuItems.Paste, MenuItemsAvailability.m1390getCanPasteimpl(iM1394unboximpl), new Function0() { // from class: p8e
            public final Object invoke() {
                return TextFieldSelectionManagerKt.contextMenuBuilder$lambda$0$2(textFieldSelectionManager);
            }
        });
        contextMenuBuilder$lambda$0$textFieldItem(contextMenuScope, contextMenuState, TextContextMenuItems.SelectAll, MenuItemsAvailability.m1391getCanSelectAllimpl(iM1394unboximpl), new Function0() { // from class: q8e
            public final Object invoke() {
                return TextFieldSelectionManagerKt.contextMenuBuilder$lambda$0$3(textFieldSelectionManager);
            }
        });
        if (PlatformUtils_androidKt.isAutofillAvailable()) {
            contextMenuBuilder$lambda$0$textFieldItem(contextMenuScope, contextMenuState, TextContextMenuItems.Autofill, MenuItemsAvailability.m1387getCanAutofillimpl(iM1394unboximpl), new Function0() { // from class: r8e
                public final Object invoke() {
                    return TextFieldSelectionManagerKt.contextMenuBuilder$lambda$0$4(textFieldSelectionManager);
                }
            });
        }
        return Unit.INSTANCE;
    }

    public static final boolean isSelectionHandleInVisibleBoundDefault(TextFieldSelectionManager textFieldSelectionManager, boolean z) {
        LayoutCoordinates layoutCoordinates;
        Rect rectVisibleBounds;
        LegacyTextFieldState state = textFieldSelectionManager.getState();
        if (state == null || (layoutCoordinates = state.getLayoutCoordinates()) == null || (rectVisibleBounds = SelectionManagerKt.visibleBounds(layoutCoordinates)) == null) {
            return false;
        }
        return SelectionManagerKt.m1793containsInclusiveUv8p0NA(rectVisibleBounds, textFieldSelectionManager.m1819getHandlePositiontuRUvjQ$foundation(z));
    }
}
