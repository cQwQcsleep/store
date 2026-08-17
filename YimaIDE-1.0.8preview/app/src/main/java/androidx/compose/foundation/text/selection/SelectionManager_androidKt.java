package androidx.compose.foundation.text.selection;

import android.content.Context;
import android.view.KeyEvent;
import androidx.compose.foundation.Magnifier_androidKt;
import androidx.compose.foundation.PlatformMagnifierFactory;
import androidx.compose.foundation.text.ContextMenu_androidKt;
import androidx.compose.foundation.text.KeyCommand;
import androidx.compose.foundation.text.KeyMapping_androidKt;
import androidx.compose.foundation.text.TextContextMenuItems;
import androidx.compose.foundation.text.contextmenu.builder.TextContextMenuBuilderScope;
import androidx.compose.foundation.text.contextmenu.data.TextContextMenuSession;
import androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuModifier_androidKt;
import androidx.compose.foundation.text.selection.SelectionManager_androidKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpSize;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\u001a\u0017\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u0014\u0010\u0006\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0000\u001a\u0014\u0010\n\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u000b\u001a\u00020\tH\u0000¨\u0006\f²\u0006\n\u0010\r\u001a\u00020\u000eX\u008a\u008e\u0002"}, d2 = {"isCopyKeyEvent", "", "keyEvent", "Landroidx/compose/ui/input/key/KeyEvent;", "isCopyKeyEvent-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "selectionMagnifier", "Landroidx/compose/ui/Modifier;", "manager", "Landroidx/compose/foundation/text/selection/SelectionManager;", "addSelectionContainerTextContextMenuComponents", "selectionManager", "foundation", "magnifierSize", "Landroidx/compose/ui/unit/IntSize;"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SelectionManager_androidKt {
    public static final Modifier addSelectionContainerTextContextMenuComponents(Modifier modifier, final SelectionManager selectionManager) {
        return TextContextMenuModifier_androidKt.addTextContextMenuComponentsWithContext(modifier, new Function2() { // from class: d5d
            public final Object invoke(Object obj, Object obj2) {
                return SelectionManager_androidKt.c(selectionManager, (TextContextMenuBuilderScope) obj, (Context) obj2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addSelectionContainerTextContextMenuComponents$lambda$0$1(final SelectionManager selectionManager, Context context, TextContextMenuBuilderScope textContextMenuBuilderScope) {
        textContextMenuBuilderScope.separator();
        addSelectionContainerTextContextMenuComponents$lambda$0$selectionContainerItem$default(textContextMenuBuilderScope, context, TextContextMenuItems.Copy, selectionManager.isNonEmptySelection$foundation(), null, new Function0() { // from class: i5d
            public final Object invoke() {
                return SelectionManager_androidKt.addSelectionContainerTextContextMenuComponents$lambda$0$1$0$0(selectionManager);
            }
        }, 8, null);
        addSelectionContainerTextContextMenuComponents$lambda$0$selectionContainerItem(textContextMenuBuilderScope, context, TextContextMenuItems.SelectAll, !selectionManager.isEntireContainerSelected$foundation(), new Function0() { // from class: j5d
            public final Object invoke() {
                return Boolean.valueOf(SelectionManager_androidKt.addSelectionContainerTextContextMenuComponents$lambda$0$1$0$1(selectionManager));
            }
        }, new Function0() { // from class: k5d
            public final Object invoke() {
                return SelectionManager_androidKt.addSelectionContainerTextContextMenuComponents$lambda$0$1$0$2(selectionManager);
            }
        });
        textContextMenuBuilderScope.separator();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addSelectionContainerTextContextMenuComponents$lambda$0$1$0$0(SelectionManager selectionManager) {
        selectionManager.copy$foundation();
        if (selectionManager.isInTouchMode()) {
            selectionManager.onRelease();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean addSelectionContainerTextContextMenuComponents$lambda$0$1$0$1(SelectionManager selectionManager) {
        return (selectionManager.getShowToolbar() && selectionManager.isInTouchMode()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addSelectionContainerTextContextMenuComponents$lambda$0$1$0$2(SelectionManager selectionManager) {
        selectionManager.selectAll$foundation();
        return Unit.INSTANCE;
    }

    private static final void addSelectionContainerTextContextMenuComponents$lambda$0$selectionContainerItem(TextContextMenuBuilderScope textContextMenuBuilderScope, Context context, TextContextMenuItems textContextMenuItems, boolean z, final Function0<Boolean> function0, final Function0<Unit> function1) {
        ContextMenu_androidKt.textItem(textContextMenuBuilderScope, context.getResources(), textContextMenuItems, z, new Function1() { // from class: b5d
            public final Object invoke(Object obj) {
                return SelectionManager_androidKt.addSelectionContainerTextContextMenuComponents$lambda$0$selectionContainerItem$0(function1, function0, (TextContextMenuSession) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addSelectionContainerTextContextMenuComponents$lambda$0$selectionContainerItem$0(Function0 function0, Function0 function1, TextContextMenuSession textContextMenuSession) {
        function0.invoke();
        if (function1 != null ? ((Boolean) function1.invoke()).booleanValue() : true) {
            textContextMenuSession.close();
        }
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void addSelectionContainerTextContextMenuComponents$lambda$0$selectionContainerItem$default(TextContextMenuBuilderScope textContextMenuBuilderScope, Context context, TextContextMenuItems textContextMenuItems, boolean z, Function0 function0, Function0 function1, int i, Object obj) {
        if ((i & 8) != 0) {
            function0 = null;
        }
        addSelectionContainerTextContextMenuComponents$lambda$0$selectionContainerItem(textContextMenuBuilderScope, context, textContextMenuItems, z, function0, function1);
    }

    public static Unit c(final SelectionManager selectionManager, TextContextMenuBuilderScope textContextMenuBuilderScope, final Context context) {
        Pair<AnnotatedString, TextRange> contextTextAndSelection$foundation = selectionManager.getContextTextAndSelection$foundation();
        PlatformSelectionBehaviors_androidKt.m1757addPlatformTextContextMenuItems71BSaZU(textContextMenuBuilderScope, context, false, contextTextAndSelection$foundation != null ? (AnnotatedString) contextTextAndSelection$foundation.getFirst() : null, contextTextAndSelection$foundation != null ? (TextRange) contextTextAndSelection$foundation.getSecond() : null, selectionManager.getPlatformSelectionBehaviors(), new Function1() { // from class: e5d
            public final Object invoke(Object obj) {
                return SelectionManager_androidKt.addSelectionContainerTextContextMenuComponents$lambda$0$1(selectionManager, context, (TextContextMenuBuilderScope) obj);
            }
        });
        return Unit.INSTANCE;
    }

    public static Modifier d(final SelectionManager selectionManager, Modifier modifier, Composer composer, int i) {
        composer.startReplaceGroup(-1914520728);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1914520728, i, -1, "androidx.compose.foundation.text.selection.selectionMagnifier.<anonymous> (SelectionManager.android.kt:51)");
        }
        final Density density = (Density) composer.consume(CompositionLocalsKt.getLocalDensity());
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion = Composer.Companion;
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = SnapshotStateKt.mutableStateOf$default(IntSize.box-impl(IntSize.Companion.getZero-YbymL2g()), (SnapshotMutationPolicy) null, 2, (Object) null);
            composer.updateRememberedValue(objRememberedValue);
        }
        final MutableState mutableState = (MutableState) objRememberedValue;
        boolean zChangedInstance = composer.changedInstance(selectionManager);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue2 == companion.getEmpty()) {
            objRememberedValue2 = new Function0() { // from class: g5d
                public final Object invoke() {
                    return SelectionManager_androidKt.selectionMagnifier$lambda$0$3$0(selectionManager, mutableState);
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        Function0 function0 = (Function0) objRememberedValue2;
        boolean zChanged = composer.changed(density);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChanged || objRememberedValue3 == companion.getEmpty()) {
            objRememberedValue3 = new Function1() { // from class: h5d
                public final Object invoke(Object obj) {
                    return SelectionManager_androidKt.selectionMagnifier$lambda$0$4$0(density, mutableState, (Function0) obj);
                }
            };
            composer.updateRememberedValue(objRememberedValue3);
        }
        Modifier modifierAnimatedSelectionMagnifier = SelectionMagnifierKt.animatedSelectionMagnifier(modifier, function0, (Function1) objRememberedValue3);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        return modifierAnimatedSelectionMagnifier;
    }

    /* JADX INFO: renamed from: isCopyKeyEvent-ZmokQxo, reason: not valid java name */
    public static final boolean m1795isCopyKeyEventZmokQxo(KeyEvent keyEvent) {
        return KeyMapping_androidKt.getPlatformDefaultKeyMapping().mo1354mapZmokQxo(keyEvent) == KeyCommand.COPY;
    }

    public static final Modifier selectionMagnifier(Modifier modifier, final SelectionManager selectionManager) {
        return !Magnifier_androidKt.isPlatformMagnifierSupported$default(0, 1, null) ? modifier : ComposedModifierKt.composed$default(modifier, (Function1) null, new Function3() { // from class: f5d
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return SelectionManager_androidKt.d(selectionManager, (Modifier) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }, 1, (Object) null);
    }

    private static final long selectionMagnifier$lambda$0$1(MutableState<IntSize> mutableState) {
        return ((IntSize) mutableState.getValue()).unbox-impl();
    }

    private static final void selectionMagnifier$lambda$0$2(MutableState<IntSize> mutableState, long j) {
        mutableState.setValue(IntSize.box-impl(j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Offset selectionMagnifier$lambda$0$3$0(SelectionManager selectionManager, MutableState mutableState) {
        return Offset.box-impl(SelectionManagerKt.m1792calculateSelectionMagnifierCenterAndroidO0kMr_c(selectionManager, selectionMagnifier$lambda$0$1(mutableState)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Modifier selectionMagnifier$lambda$0$4$0(final Density density, final MutableState mutableState, final Function0 function0) {
        return Magnifier_androidKt.m402magnifierjPUL71Q$default(Modifier.Companion, new Function1() { // from class: l5d
            public final Object invoke(Object obj) {
                return SelectionManager_androidKt.selectionMagnifier$lambda$0$4$0$0(function0, (Density) obj);
            }
        }, null, new Function1() { // from class: c5d
            public final Object invoke(Object obj) {
                return SelectionManager_androidKt.selectionMagnifier$lambda$0$4$0$1(density, mutableState, (DpSize) obj);
            }
        }, 0.0f, true, 0L, 0.0f, 0.0f, false, PlatformMagnifierFactory.INSTANCE.getForCurrentPlatform(), 490, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Offset selectionMagnifier$lambda$0$4$0$0(Function0 function0, Density density) {
        return (Offset) function0.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit selectionMagnifier$lambda$0$4$0$1(Density density, MutableState mutableState, DpSize dpSize) {
        selectionMagnifier$lambda$0$2(mutableState, IntSize.constructor-impl((((long) density.roundToPx-0680j_4(DpSize.getWidth-D9Ej5fM(dpSize.unbox-impl()))) << 32) | (((long) density.roundToPx-0680j_4(DpSize.getHeight-D9Ej5fM(dpSize.unbox-impl()))) & 4294967295L)));
        return Unit.INSTANCE;
    }
}
