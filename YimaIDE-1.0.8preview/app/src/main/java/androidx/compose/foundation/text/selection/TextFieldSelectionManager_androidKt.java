package androidx.compose.foundation.text.selection;

import android.content.Context;
import androidx.compose.foundation.Magnifier_androidKt;
import androidx.compose.foundation.PlatformMagnifierFactory;
import androidx.compose.foundation.internal.ClipboardUtils;
import androidx.compose.foundation.text.ContextMenu_androidKt;
import androidx.compose.foundation.text.TextContextMenuItems;
import androidx.compose.foundation.text.contextmenu.builder.TextContextMenuBuilderScope;
import androidx.compose.foundation.text.contextmenu.data.TextContextMenuSession;
import androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuModifier_androidKt;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.platform.Clipboard;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpSize;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u001c\u0010\u0004\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0000\u001a\u0012\u0010\u0007\u001a\u00020\b*\u00020\u0003H\u0080@¢\u0006\u0002\u0010\t\u001a\u0014\u0010\n\u001a\u00020\b*\u00020\u00032\u0006\u0010\u000b\u001a\u00020\bH\u0000¨\u0006\f²\u0006\n\u0010\r\u001a\u00020\u000eX\u008a\u008e\u0002"}, d2 = {"textFieldMagnifier", "Landroidx/compose/ui/Modifier;", "manager", "Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "addBasicTextFieldTextContextMenuComponents", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "hasAvailableTextToPaste", "", "(Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isSelectionHandleInVisibleBound", "isStartHandle", "foundation", "magnifierSize", "Landroidx/compose/ui/unit/IntSize;"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class TextFieldSelectionManager_androidKt {
    public static final Modifier addBasicTextFieldTextContextMenuComponents(Modifier modifier, final TextFieldSelectionManager textFieldSelectionManager, final CoroutineScope coroutineScope) {
        return TextContextMenuModifier_androidKt.addTextContextMenuComponentsWithContext(modifier, new Function2() { // from class: t8e
            public final Object invoke(Object obj, Object obj2) {
                return TextFieldSelectionManager_androidKt.l(textFieldSelectionManager, coroutineScope, (TextContextMenuBuilderScope) obj, (Context) obj2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addBasicTextFieldTextContextMenuComponents$lambda$0$3(final TextFieldSelectionManager textFieldSelectionManager, CoroutineScope coroutineScope, Context context, TextContextMenuBuilderScope textContextMenuBuilderScope) {
        textContextMenuBuilderScope.separator();
        addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldSuspendItem(textContextMenuBuilderScope, coroutineScope, context, TextContextMenuItems.Cut, textFieldSelectionManager.canShowCutMenuItem$foundation(), new TextFieldSelectionManager_androidKt$addBasicTextFieldTextContextMenuComponents$1$2$1$1(textFieldSelectionManager, null));
        addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldSuspendItem(textContextMenuBuilderScope, coroutineScope, context, TextContextMenuItems.Copy, textFieldSelectionManager.canShowCopyMenuItem$foundation(), new TextFieldSelectionManager_androidKt$addBasicTextFieldTextContextMenuComponents$1$2$1$2(textFieldSelectionManager, null));
        addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldSuspendItem(textContextMenuBuilderScope, coroutineScope, context, TextContextMenuItems.Paste, textFieldSelectionManager.canShowPasteMenuItem$foundation(), new TextFieldSelectionManager_androidKt$addBasicTextFieldTextContextMenuComponents$1$2$1$3(textFieldSelectionManager, null));
        addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldItem(textContextMenuBuilderScope, context, TextContextMenuItems.SelectAll, textFieldSelectionManager.canShowSelectAllMenuItem$foundation(), new Function0() { // from class: s8e
            public final Object invoke() {
                return Boolean.valueOf(TextFieldSelectionManager_androidKt.addBasicTextFieldTextContextMenuComponents$lambda$0$3$0$0(textFieldSelectionManager));
            }
        }, new Function0() { // from class: v8e
            public final Object invoke() {
                return TextFieldSelectionManager_androidKt.addBasicTextFieldTextContextMenuComponents$lambda$0$3$0$1(textFieldSelectionManager);
            }
        });
        addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldItem$default(textContextMenuBuilderScope, context, TextContextMenuItems.Autofill, textFieldSelectionManager.canShowAutofillMenuItem$foundation(), null, new Function0() { // from class: w8e
            public final Object invoke() {
                return TextFieldSelectionManager_androidKt.addBasicTextFieldTextContextMenuComponents$lambda$0$3$0$2(textFieldSelectionManager);
            }
        }, 8, null);
        textContextMenuBuilderScope.separator();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean addBasicTextFieldTextContextMenuComponents$lambda$0$3$0$0(TextFieldSelectionManager textFieldSelectionManager) {
        return !textFieldSelectionManager.getTextToolbarShown$foundation();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addBasicTextFieldTextContextMenuComponents$lambda$0$3$0$1(TextFieldSelectionManager textFieldSelectionManager) {
        textFieldSelectionManager.selectAll$foundation();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addBasicTextFieldTextContextMenuComponents$lambda$0$3$0$2(TextFieldSelectionManager textFieldSelectionManager) {
        textFieldSelectionManager.autofill$foundation();
        return Unit.INSTANCE;
    }

    private static final void addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldItem(TextContextMenuBuilderScope textContextMenuBuilderScope, Context context, TextContextMenuItems textContextMenuItems, boolean z, final Function0<Boolean> function0, final Function0<Unit> function1) {
        ContextMenu_androidKt.textItem(textContextMenuBuilderScope, context.getResources(), textContextMenuItems, z, new Function1() { // from class: a9e
            public final Object invoke(Object obj) {
                return TextFieldSelectionManager_androidKt.addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldItem$0(function1, function0, (TextContextMenuSession) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldItem$0(Function0 function0, Function0 function1, TextContextMenuSession textContextMenuSession) {
        function0.invoke();
        if (function1 != null ? ((Boolean) function1.invoke()).booleanValue() : true) {
            textContextMenuSession.close();
        }
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldItem$default(TextContextMenuBuilderScope textContextMenuBuilderScope, Context context, TextContextMenuItems textContextMenuItems, boolean z, Function0 function0, Function0 function1, int i, Object obj) {
        if ((i & 8) != 0) {
            function0 = null;
        }
        addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldItem(textContextMenuBuilderScope, context, textContextMenuItems, z, function0, function1);
    }

    private static final void addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldSuspendItem(TextContextMenuBuilderScope textContextMenuBuilderScope, final CoroutineScope coroutineScope, Context context, TextContextMenuItems textContextMenuItems, boolean z, final Function1<? super Continuation<? super Unit>, ? extends Object> function1) {
        addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldItem$default(textContextMenuBuilderScope, context, textContextMenuItems, z, null, new Function0() { // from class: u8e
            public final Object invoke() {
                return TextFieldSelectionManager_androidKt.addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldSuspendItem$1(coroutineScope, function1);
            }
        }, 8, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldSuspendItem$1(CoroutineScope coroutineScope, Function1 function1) {
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, CoroutineStart.UNDISPATCHED, new TextFieldSelectionManager_androidKt$addBasicTextFieldTextContextMenuComponents$1$textFieldSuspendItem$1$1(function1, null), 1, (Object) null);
        return Unit.INSTANCE;
    }

    public static Modifier c(final TextFieldSelectionManager textFieldSelectionManager, Modifier modifier, Composer composer, int i) {
        composer.startReplaceGroup(1980580247);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1980580247, i, -1, "androidx.compose.foundation.text.selection.textFieldMagnifier.<anonymous> (TextFieldSelectionManager.android.kt:54)");
        }
        final Density density = (Density) composer.consume(CompositionLocalsKt.getLocalDensity());
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion = Composer.Companion;
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = SnapshotStateKt.mutableStateOf$default(IntSize.box-impl(IntSize.Companion.getZero-YbymL2g()), (SnapshotMutationPolicy) null, 2, (Object) null);
            composer.updateRememberedValue(objRememberedValue);
        }
        final MutableState mutableState = (MutableState) objRememberedValue;
        boolean zChangedInstance = composer.changedInstance(textFieldSelectionManager);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue2 == companion.getEmpty()) {
            objRememberedValue2 = new Function0() { // from class: y8e
                public final Object invoke() {
                    return TextFieldSelectionManager_androidKt.textFieldMagnifier$lambda$0$3$0(textFieldSelectionManager, mutableState);
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        Function0 function0 = (Function0) objRememberedValue2;
        boolean zChanged = composer.changed(density);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChanged || objRememberedValue3 == companion.getEmpty()) {
            objRememberedValue3 = new Function1() { // from class: z8e
                public final Object invoke(Object obj) {
                    return TextFieldSelectionManager_androidKt.textFieldMagnifier$lambda$0$4$0(density, mutableState, (Function0) obj);
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

    public static final Object hasAvailableTextToPaste(TextFieldSelectionManager textFieldSelectionManager, Continuation<? super Boolean> continuation) {
        Clipboard clipboard = textFieldSelectionManager.getClipboard();
        return Boxing.boxBoolean(clipboard != null ? ClipboardUtils.hasText(clipboard) : false);
    }

    public static final boolean isSelectionHandleInVisibleBound(TextFieldSelectionManager textFieldSelectionManager, boolean z) {
        return TextFieldSelectionManagerKt.isSelectionHandleInVisibleBoundDefault(textFieldSelectionManager, z);
    }

    public static Unit l(final TextFieldSelectionManager textFieldSelectionManager, final CoroutineScope coroutineScope, TextContextMenuBuilderScope textContextMenuBuilderScope, final Context context) {
        boolean editable = textFieldSelectionManager.getEditable();
        AnnotatedString transformedText$foundation = textFieldSelectionManager.getTransformedText$foundation();
        TextRange textRange = null;
        String text = transformedText$foundation != null ? transformedText$foundation.getText() : null;
        TextRange latestSelection = textFieldSelectionManager.getLatestSelection();
        if (latestSelection != null) {
            long j = latestSelection.unbox-impl();
            OffsetMapping offsetMapping = textFieldSelectionManager.getOffsetMapping();
            textRange = TextRange.box-impl(TextRangeKt.TextRange(offsetMapping.originalToTransformed(TextRange.getStart-impl(j)), offsetMapping.originalToTransformed(TextRange.getEnd-impl(j))));
        }
        PlatformSelectionBehaviors_androidKt.m1757addPlatformTextContextMenuItems71BSaZU(textContextMenuBuilderScope, context, editable, text, textRange, textFieldSelectionManager.getPlatformSelectionBehaviors(), new Function1() { // from class: x8e
            public final Object invoke(Object obj) {
                return TextFieldSelectionManager_androidKt.addBasicTextFieldTextContextMenuComponents$lambda$0$3(textFieldSelectionManager, coroutineScope, context, (TextContextMenuBuilderScope) obj);
            }
        });
        return Unit.INSTANCE;
    }

    public static final Modifier textFieldMagnifier(Modifier modifier, final TextFieldSelectionManager textFieldSelectionManager) {
        return !Magnifier_androidKt.isPlatformMagnifierSupported$default(0, 1, null) ? modifier : ComposedModifierKt.composed$default(modifier, (Function1) null, new Function3() { // from class: b9e
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return TextFieldSelectionManager_androidKt.c(textFieldSelectionManager, (Modifier) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }, 1, (Object) null);
    }

    private static final long textFieldMagnifier$lambda$0$1(MutableState<IntSize> mutableState) {
        return ((IntSize) mutableState.getValue()).unbox-impl();
    }

    private static final void textFieldMagnifier$lambda$0$2(MutableState<IntSize> mutableState, long j) {
        mutableState.setValue(IntSize.box-impl(j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Offset textFieldMagnifier$lambda$0$3$0(TextFieldSelectionManager textFieldSelectionManager, MutableState mutableState) {
        return Offset.box-impl(TextFieldSelectionManagerKt.m1826calculateSelectionMagnifierCenterAndroidO0kMr_c(textFieldSelectionManager, textFieldMagnifier$lambda$0$1(mutableState)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Modifier textFieldMagnifier$lambda$0$4$0(final Density density, final MutableState mutableState, final Function0 function0) {
        return Magnifier_androidKt.m402magnifierjPUL71Q$default(Modifier.Companion, new Function1() { // from class: c9e
            public final Object invoke(Object obj) {
                return TextFieldSelectionManager_androidKt.textFieldMagnifier$lambda$0$4$0$0(function0, (Density) obj);
            }
        }, null, new Function1() { // from class: d9e
            public final Object invoke(Object obj) {
                return TextFieldSelectionManager_androidKt.textFieldMagnifier$lambda$0$4$0$1(density, mutableState, (DpSize) obj);
            }
        }, 0.0f, true, 0L, 0.0f, 0.0f, false, PlatformMagnifierFactory.INSTANCE.getForCurrentPlatform(), 490, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Offset textFieldMagnifier$lambda$0$4$0$0(Function0 function0, Density density) {
        return (Offset) function0.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit textFieldMagnifier$lambda$0$4$0$1(Density density, MutableState mutableState, DpSize dpSize) {
        textFieldMagnifier$lambda$0$2(mutableState, IntSize.constructor-impl((((long) density.roundToPx-0680j_4(DpSize.getWidth-D9Ej5fM(dpSize.unbox-impl()))) << 32) | (((long) density.roundToPx-0680j_4(DpSize.getHeight-D9Ej5fM(dpSize.unbox-impl()))) & 4294967295L)));
        return Unit.INSTANCE;
    }
}
