package androidx.compose.foundation.text;

import androidx.compose.foundation.text.LegacyTextFieldState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScope;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.focus.FocusManager;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.input.EditProcessor;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.TextInputSession;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000¼\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u0007\u0010\u0087\u0001\u001a\u00020\u001dJx\u0010\u0088\u0001\u001a\u00020j2\u0006\u0010<\u001a\u00020=2\u0007\u0010\u0089\u0001\u001a\u00020=2\b\u0010\u008a\u0001\u001a\u00030\u008b\u00012\u0007\u0010\u008c\u0001\u001a\u00020\u001d2\b\u0010\u008d\u0001\u001a\u00030\u008e\u00012\b\u0010\u008f\u0001\u001a\u00030\u0090\u00012\u0012\u0010k\u001a\u000e\u0012\u0004\u0012\u00020i\u0012\u0004\u0012\u00020j0h2\b\u0010\u0091\u0001\u001a\u00030\u0092\u00012\b\u0010\u0093\u0001\u001a\u00030\u0094\u00012\u0006\u0010w\u001a\u00020x¢\u0006\u0006\b\u0095\u0001\u0010\u0096\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR+\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001d8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b#\u0010$\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R+\u0010&\u001a\u00020%2\u0006\u0010\u001c\u001a\u00020%8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b+\u0010$\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u0010\u0010,\u001a\u0004\u0018\u00010-X\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010/\u001a\u0004\u0018\u00010-2\b\u0010.\u001a\u0004\u0018\u00010-8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u0016\u00104\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010605X\u0082\u0004¢\u0006\u0002\n\u0000R(\u00107\u001a\u0004\u0018\u0001062\b\u0010.\u001a\u0004\u0018\u0001068F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\u001c\u0010<\u001a\u0004\u0018\u00010=X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR+\u0010C\u001a\u00020B2\u0006\u0010\u001c\u001a\u00020B8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bH\u0010$\u001a\u0004\bD\u0010E\"\u0004\bF\u0010GR+\u0010I\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001d8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bL\u0010$\u001a\u0004\bJ\u0010 \"\u0004\bK\u0010\"R+\u0010M\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001d8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bP\u0010$\u001a\u0004\bN\u0010 \"\u0004\bO\u0010\"R+\u0010Q\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001d8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bT\u0010$\u001a\u0004\bR\u0010 \"\u0004\bS\u0010\"R+\u0010U\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001d8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bX\u0010$\u001a\u0004\bV\u0010 \"\u0004\bW\u0010\"R\u001e\u0010Y\u001a\u00020\u001d2\u0006\u0010.\u001a\u00020\u001d@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\bY\u0010 R+\u0010Z\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001d8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\\\u0010$\u001a\u0004\bZ\u0010 \"\u0004\b[\u0010\"R\u000e\u0010]\u001a\u00020^X\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010_\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001d8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bb\u0010$\u001a\u0004\b`\u0010 \"\u0004\ba\u0010\"R+\u0010c\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001d8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bf\u0010$\u001a\u0004\bd\u0010 \"\u0004\be\u0010\"R\u001a\u0010g\u001a\u000e\u0012\u0004\u0012\u00020i\u0012\u0004\u0012\u00020j0hX\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010k\u001a\u000e\u0012\u0004\u0012\u00020i\u0012\u0004\u0012\u00020j0h¢\u0006\b\n\u0000\u001a\u0004\bl\u0010mR\u001d\u0010n\u001a\u000e\u0012\u0004\u0012\u00020o\u0012\u0004\u0012\u00020j0h¢\u0006\b\n\u0000\u001a\u0004\bp\u0010mR\u001d\u0010q\u001a\u000e\u0012\u0004\u0012\u00020o\u0012\u0004\u0012\u00020\u001d0h¢\u0006\b\n\u0000\u001a\u0004\br\u0010mR\u0011\u0010s\u001a\u00020t¢\u0006\b\n\u0000\u001a\u0004\bu\u0010vR\u001c\u0010w\u001a\u00020xX\u0086\u000e¢\u0006\u0010\n\u0002\u0010}\u001a\u0004\by\u0010z\"\u0004\b{\u0010|R.\u0010\u007f\u001a\u00020~2\u0006\u0010\u001c\u001a\u00020~8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b\u0082\u0001\u0010$\u001a\u0005\b\u0080\u0001\u0010z\"\u0005\b\u0081\u0001\u0010|R/\u0010\u0083\u0001\u001a\u00020~2\u0006\u0010\u001c\u001a\u00020~8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b\u0086\u0001\u0010$\u001a\u0005\b\u0084\u0001\u0010z\"\u0005\b\u0085\u0001\u0010|¨\u0006\u0097\u0001"}, d2 = {"Landroidx/compose/foundation/text/LegacyTextFieldState;", "", "textDelegate", "Landroidx/compose/foundation/text/TextDelegate;", "recomposeScope", "Landroidx/compose/runtime/RecomposeScope;", "keyboardController", "Landroidx/compose/ui/platform/SoftwareKeyboardController;", "<init>", "(Landroidx/compose/foundation/text/TextDelegate;Landroidx/compose/runtime/RecomposeScope;Landroidx/compose/ui/platform/SoftwareKeyboardController;)V", "getTextDelegate", "()Landroidx/compose/foundation/text/TextDelegate;", "setTextDelegate", "(Landroidx/compose/foundation/text/TextDelegate;)V", "getRecomposeScope", "()Landroidx/compose/runtime/RecomposeScope;", "getKeyboardController", "()Landroidx/compose/ui/platform/SoftwareKeyboardController;", "processor", "Landroidx/compose/ui/text/input/EditProcessor;", "getProcessor", "()Landroidx/compose/ui/text/input/EditProcessor;", "inputSession", "Landroidx/compose/ui/text/input/TextInputSession;", "getInputSession", "()Landroidx/compose/ui/text/input/TextInputSession;", "setInputSession", "(Landroidx/compose/ui/text/input/TextInputSession;)V", "<set-?>", "", "hasFocus", "getHasFocus", "()Z", "setHasFocus", "(Z)V", "hasFocus$delegate", "Landroidx/compose/runtime/MutableState;", "Landroidx/compose/ui/unit/Dp;", "minHeightForSingleLineField", "getMinHeightForSingleLineField-D9Ej5fM", "()F", "setMinHeightForSingleLineField-0680j_4", "(F)V", "minHeightForSingleLineField$delegate", "_layoutCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "value", "layoutCoordinates", "getLayoutCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "setLayoutCoordinates", "(Landroidx/compose/ui/layout/LayoutCoordinates;)V", "layoutResultState", "Landroidx/compose/runtime/MutableState;", "Landroidx/compose/foundation/text/TextLayoutResultProxy;", "layoutResult", "getLayoutResult", "()Landroidx/compose/foundation/text/TextLayoutResultProxy;", "setLayoutResult", "(Landroidx/compose/foundation/text/TextLayoutResultProxy;)V", "untransformedText", "Landroidx/compose/ui/text/AnnotatedString;", "getUntransformedText", "()Landroidx/compose/ui/text/AnnotatedString;", "setUntransformedText", "(Landroidx/compose/ui/text/AnnotatedString;)V", "Landroidx/compose/foundation/text/HandleState;", "handleState", "getHandleState", "()Landroidx/compose/foundation/text/HandleState;", "setHandleState", "(Landroidx/compose/foundation/text/HandleState;)V", "handleState$delegate", "showFloatingToolbar", "getShowFloatingToolbar", "setShowFloatingToolbar", "showFloatingToolbar$delegate", "showSelectionHandleStart", "getShowSelectionHandleStart", "setShowSelectionHandleStart", "showSelectionHandleStart$delegate", "showSelectionHandleEnd", "getShowSelectionHandleEnd", "setShowSelectionHandleEnd", "showSelectionHandleEnd$delegate", "showCursorHandle", "getShowCursorHandle", "setShowCursorHandle", "showCursorHandle$delegate", "isLayoutResultStale", "isInTouchMode", "setInTouchMode", "isInTouchMode$delegate", "keyboardActionRunner", "Landroidx/compose/foundation/text/KeyboardActionRunner;", "autofillHighlightOn", "getAutofillHighlightOn", "setAutofillHighlightOn", "autofillHighlightOn$delegate", "justAutofilled", "getJustAutofilled", "setJustAutofilled", "justAutofilled$delegate", "onValueChangeOriginal", "Lkotlin/Function1;", "Landroidx/compose/ui/text/input/TextFieldValue;", "", "onValueChange", "getOnValueChange", "()Lkotlin/jvm/functions/Function1;", "onImeActionPerformed", "Landroidx/compose/ui/text/input/ImeAction;", "getOnImeActionPerformed", "onImeActionPerformedWithResult", "getOnImeActionPerformedWithResult", "highlightPaint", "Landroidx/compose/ui/graphics/Paint;", "getHighlightPaint", "()Landroidx/compose/ui/graphics/Paint;", "selectionBackgroundColor", "Landroidx/compose/ui/graphics/Color;", "getSelectionBackgroundColor-0d7_KjU", "()J", "setSelectionBackgroundColor-8_81llA", "(J)V", "J", "Landroidx/compose/ui/text/TextRange;", "selectionPreviewHighlightRange", "getSelectionPreviewHighlightRange-d9O1mEE", "setSelectionPreviewHighlightRange-5zc-tL8", "selectionPreviewHighlightRange$delegate", "deletionPreviewHighlightRange", "getDeletionPreviewHighlightRange-d9O1mEE", "setDeletionPreviewHighlightRange-5zc-tL8", "deletionPreviewHighlightRange$delegate", "hasHighlight", "update", "visualText", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "softWrap", "density", "Landroidx/compose/ui/unit/Density;", "fontFamilyResolver", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "keyboardActions", "Landroidx/compose/foundation/text/KeyboardActions;", "focusManager", "Landroidx/compose/ui/focus/FocusManager;", "update-fnh65Uc", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/text/TextStyle;ZLandroidx/compose/ui/unit/Density;Landroidx/compose/ui/text/font/FontFamily$Resolver;Lkotlin/jvm/functions/Function1;Landroidx/compose/foundation/text/KeyboardActions;Landroidx/compose/ui/focus/FocusManager;J)V", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class LegacyTextFieldState {
    public static final int $stable = 8;
    private LayoutCoordinates _layoutCoordinates;

    /* JADX INFO: renamed from: autofillHighlightOn$delegate, reason: from kotlin metadata */
    private final MutableState autofillHighlightOn;

    /* JADX INFO: renamed from: deletionPreviewHighlightRange$delegate, reason: from kotlin metadata */
    private final MutableState deletionPreviewHighlightRange;

    /* JADX INFO: renamed from: handleState$delegate, reason: from kotlin metadata */
    private final MutableState handleState;

    /* JADX INFO: renamed from: hasFocus$delegate, reason: from kotlin metadata */
    private final MutableState hasFocus;
    private final Paint highlightPaint;
    private TextInputSession inputSession;

    /* JADX INFO: renamed from: isInTouchMode$delegate, reason: from kotlin metadata */
    private final MutableState isInTouchMode;
    private boolean isLayoutResultStale;

    /* JADX INFO: renamed from: justAutofilled$delegate, reason: from kotlin metadata */
    private final MutableState justAutofilled;
    private final KeyboardActionRunner keyboardActionRunner;
    private final SoftwareKeyboardController keyboardController;
    private final MutableState<TextLayoutResultProxy> layoutResultState;

    /* JADX INFO: renamed from: minHeightForSingleLineField$delegate, reason: from kotlin metadata */
    private final MutableState minHeightForSingleLineField;
    private final Function1<ImeAction, Unit> onImeActionPerformed;
    private final Function1<ImeAction, Boolean> onImeActionPerformedWithResult;
    private final Function1<TextFieldValue, Unit> onValueChange;
    private Function1<? super TextFieldValue, Unit> onValueChangeOriginal;
    private final EditProcessor processor = new EditProcessor();
    private final RecomposeScope recomposeScope;
    private long selectionBackgroundColor;

    /* JADX INFO: renamed from: selectionPreviewHighlightRange$delegate, reason: from kotlin metadata */
    private final MutableState selectionPreviewHighlightRange;

    /* JADX INFO: renamed from: showCursorHandle$delegate, reason: from kotlin metadata */
    private final MutableState showCursorHandle;

    /* JADX INFO: renamed from: showFloatingToolbar$delegate, reason: from kotlin metadata */
    private final MutableState showFloatingToolbar;

    /* JADX INFO: renamed from: showSelectionHandleEnd$delegate, reason: from kotlin metadata */
    private final MutableState showSelectionHandleEnd;

    /* JADX INFO: renamed from: showSelectionHandleStart$delegate, reason: from kotlin metadata */
    private final MutableState showSelectionHandleStart;
    private TextDelegate textDelegate;
    private AnnotatedString untransformedText;

    public LegacyTextFieldState(TextDelegate textDelegate, RecomposeScope recomposeScope, SoftwareKeyboardController softwareKeyboardController) {
        this.textDelegate = textDelegate;
        this.recomposeScope = recomposeScope;
        this.keyboardController = softwareKeyboardController;
        Boolean bool = Boolean.FALSE;
        this.hasFocus = SnapshotStateKt.mutableStateOf$default(bool, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.minHeightForSingleLineField = SnapshotStateKt.mutableStateOf$default(Dp.box-impl(Dp.constructor-impl(0.0f)), (SnapshotMutationPolicy) null, 2, (Object) null);
        this.layoutResultState = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.handleState = SnapshotStateKt.mutableStateOf$default(HandleState.None, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.showFloatingToolbar = SnapshotStateKt.mutableStateOf$default(bool, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.showSelectionHandleStart = SnapshotStateKt.mutableStateOf$default(bool, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.showSelectionHandleEnd = SnapshotStateKt.mutableStateOf$default(bool, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.showCursorHandle = SnapshotStateKt.mutableStateOf$default(bool, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.isLayoutResultStale = true;
        this.isInTouchMode = SnapshotStateKt.mutableStateOf$default(Boolean.TRUE, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.keyboardActionRunner = new KeyboardActionRunner(softwareKeyboardController);
        this.autofillHighlightOn = SnapshotStateKt.mutableStateOf$default(bool, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.justAutofilled = SnapshotStateKt.mutableStateOf$default(bool, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.onValueChangeOriginal = new Function1() { // from class: jy8
            public final Object invoke(Object obj) {
                return LegacyTextFieldState.d((TextFieldValue) obj);
            }
        };
        this.onValueChange = new Function1() { // from class: ky8
            public final Object invoke(Object obj) {
                return LegacyTextFieldState.a(this.b, (TextFieldValue) obj);
            }
        };
        this.onImeActionPerformed = new Function1() { // from class: ly8
            public final Object invoke(Object obj) {
                return LegacyTextFieldState.c(this.b, (ImeAction) obj);
            }
        };
        this.onImeActionPerformedWithResult = new Function1() { // from class: my8
            public final Object invoke(Object obj) {
                return Boolean.valueOf(LegacyTextFieldState.b(this.b, (ImeAction) obj));
            }
        };
        this.highlightPaint = AndroidPaint_androidKt.Paint();
        this.selectionBackgroundColor = Color.Companion.getUnspecified-0d7_KjU();
        TextRange.Companion companion = TextRange.Companion;
        this.selectionPreviewHighlightRange = SnapshotStateKt.mutableStateOf$default(TextRange.box-impl(companion.getZero-d9O1mEE()), (SnapshotMutationPolicy) null, 2, (Object) null);
        this.deletionPreviewHighlightRange = SnapshotStateKt.mutableStateOf$default(TextRange.box-impl(companion.getZero-d9O1mEE()), (SnapshotMutationPolicy) null, 2, (Object) null);
    }

    public static Unit a(LegacyTextFieldState legacyTextFieldState, TextFieldValue textFieldValue) {
        String text = textFieldValue.getText();
        AnnotatedString annotatedString = legacyTextFieldState.untransformedText;
        if (!Intrinsics.areEqual(text, annotatedString != null ? annotatedString.getText() : null)) {
            legacyTextFieldState.setHandleState(HandleState.None);
            if (legacyTextFieldState.getJustAutofilled()) {
                legacyTextFieldState.setJustAutofilled(false);
            } else {
                legacyTextFieldState.setAutofillHighlightOn(false);
            }
        }
        TextRange.Companion companion = TextRange.Companion;
        legacyTextFieldState.m1379setSelectionPreviewHighlightRange5zctL8(companion.getZero-d9O1mEE());
        legacyTextFieldState.m1376setDeletionPreviewHighlightRange5zctL8(companion.getZero-d9O1mEE());
        legacyTextFieldState.onValueChangeOriginal.invoke(textFieldValue);
        legacyTextFieldState.recomposeScope.invalidate();
        return Unit.INSTANCE;
    }

    public static boolean b(LegacyTextFieldState legacyTextFieldState, ImeAction imeAction) {
        return legacyTextFieldState.keyboardActionRunner.m1357runActionKlQnJC8(imeAction.unbox-impl());
    }

    public static Unit c(LegacyTextFieldState legacyTextFieldState, ImeAction imeAction) {
        legacyTextFieldState.keyboardActionRunner.m1357runActionKlQnJC8(imeAction.unbox-impl());
        return Unit.INSTANCE;
    }

    public static Unit d(TextFieldValue textFieldValue) {
        return Unit.INSTANCE;
    }

    public final boolean getAutofillHighlightOn() {
        return ((Boolean) this.autofillHighlightOn.getValue()).booleanValue();
    }

    /* JADX INFO: renamed from: getDeletionPreviewHighlightRange-d9O1mEE, reason: not valid java name */
    public final long m1372getDeletionPreviewHighlightRanged9O1mEE() {
        return ((TextRange) this.deletionPreviewHighlightRange.getValue()).unbox-impl();
    }

    public final HandleState getHandleState() {
        return (HandleState) this.handleState.getValue();
    }

    public final boolean getHasFocus() {
        return ((Boolean) this.hasFocus.getValue()).booleanValue();
    }

    public final Paint getHighlightPaint() {
        return this.highlightPaint;
    }

    public final TextInputSession getInputSession() {
        return this.inputSession;
    }

    public final boolean getJustAutofilled() {
        return ((Boolean) this.justAutofilled.getValue()).booleanValue();
    }

    public final SoftwareKeyboardController getKeyboardController() {
        return this.keyboardController;
    }

    public final LayoutCoordinates getLayoutCoordinates() {
        LayoutCoordinates layoutCoordinates = this._layoutCoordinates;
        if (layoutCoordinates == null || !layoutCoordinates.isAttached()) {
            return null;
        }
        return layoutCoordinates;
    }

    public final TextLayoutResultProxy getLayoutResult() {
        return (TextLayoutResultProxy) this.layoutResultState.getValue();
    }

    /* JADX INFO: renamed from: getMinHeightForSingleLineField-D9Ej5fM, reason: not valid java name */
    public final float m1373getMinHeightForSingleLineFieldD9Ej5fM() {
        return ((Dp) this.minHeightForSingleLineField.getValue()).unbox-impl();
    }

    public final Function1<ImeAction, Unit> getOnImeActionPerformed() {
        return this.onImeActionPerformed;
    }

    public final Function1<ImeAction, Boolean> getOnImeActionPerformedWithResult() {
        return this.onImeActionPerformedWithResult;
    }

    public final Function1<TextFieldValue, Unit> getOnValueChange() {
        return this.onValueChange;
    }

    public final EditProcessor getProcessor() {
        return this.processor;
    }

    public final RecomposeScope getRecomposeScope() {
        return this.recomposeScope;
    }

    /* JADX INFO: renamed from: getSelectionBackgroundColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getSelectionBackgroundColor() {
        return this.selectionBackgroundColor;
    }

    /* JADX INFO: renamed from: getSelectionPreviewHighlightRange-d9O1mEE, reason: not valid java name */
    public final long m1375getSelectionPreviewHighlightRanged9O1mEE() {
        return ((TextRange) this.selectionPreviewHighlightRange.getValue()).unbox-impl();
    }

    public final boolean getShowCursorHandle() {
        return ((Boolean) this.showCursorHandle.getValue()).booleanValue();
    }

    public final boolean getShowFloatingToolbar() {
        return ((Boolean) this.showFloatingToolbar.getValue()).booleanValue();
    }

    public final boolean getShowSelectionHandleEnd() {
        return ((Boolean) this.showSelectionHandleEnd.getValue()).booleanValue();
    }

    public final boolean getShowSelectionHandleStart() {
        return ((Boolean) this.showSelectionHandleStart.getValue()).booleanValue();
    }

    public final TextDelegate getTextDelegate() {
        return this.textDelegate;
    }

    public final AnnotatedString getUntransformedText() {
        return this.untransformedText;
    }

    public final boolean hasHighlight() {
        return (TextRange.getCollapsed-impl(m1375getSelectionPreviewHighlightRanged9O1mEE()) && TextRange.getCollapsed-impl(m1372getDeletionPreviewHighlightRanged9O1mEE())) ? false : true;
    }

    public final boolean isInTouchMode() {
        return ((Boolean) this.isInTouchMode.getValue()).booleanValue();
    }

    /* JADX INFO: renamed from: isLayoutResultStale, reason: from getter */
    public final boolean getIsLayoutResultStale() {
        return this.isLayoutResultStale;
    }

    public final void setAutofillHighlightOn(boolean z) {
        this.autofillHighlightOn.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: renamed from: setDeletionPreviewHighlightRange-5zc-tL8, reason: not valid java name */
    public final void m1376setDeletionPreviewHighlightRange5zctL8(long j) {
        this.deletionPreviewHighlightRange.setValue(TextRange.box-impl(j));
    }

    public final void setHandleState(HandleState handleState) {
        this.handleState.setValue(handleState);
    }

    public final void setHasFocus(boolean z) {
        this.hasFocus.setValue(Boolean.valueOf(z));
    }

    public final void setInTouchMode(boolean z) {
        this.isInTouchMode.setValue(Boolean.valueOf(z));
    }

    public final void setInputSession(TextInputSession textInputSession) {
        this.inputSession = textInputSession;
    }

    public final void setJustAutofilled(boolean z) {
        this.justAutofilled.setValue(Boolean.valueOf(z));
    }

    public final void setLayoutCoordinates(LayoutCoordinates layoutCoordinates) {
        this._layoutCoordinates = layoutCoordinates;
    }

    public final void setLayoutResult(TextLayoutResultProxy textLayoutResultProxy) {
        this.layoutResultState.setValue(textLayoutResultProxy);
        this.isLayoutResultStale = false;
    }

    /* JADX INFO: renamed from: setMinHeightForSingleLineField-0680j_4, reason: not valid java name */
    public final void m1377setMinHeightForSingleLineField0680j_4(float f) {
        this.minHeightForSingleLineField.setValue(Dp.box-impl(f));
    }

    /* JADX INFO: renamed from: setSelectionBackgroundColor-8_81llA, reason: not valid java name */
    public final void m1378setSelectionBackgroundColor8_81llA(long j) {
        this.selectionBackgroundColor = j;
    }

    /* JADX INFO: renamed from: setSelectionPreviewHighlightRange-5zc-tL8, reason: not valid java name */
    public final void m1379setSelectionPreviewHighlightRange5zctL8(long j) {
        this.selectionPreviewHighlightRange.setValue(TextRange.box-impl(j));
    }

    public final void setShowCursorHandle(boolean z) {
        this.showCursorHandle.setValue(Boolean.valueOf(z));
    }

    public final void setShowFloatingToolbar(boolean z) {
        this.showFloatingToolbar.setValue(Boolean.valueOf(z));
    }

    public final void setShowSelectionHandleEnd(boolean z) {
        this.showSelectionHandleEnd.setValue(Boolean.valueOf(z));
    }

    public final void setShowSelectionHandleStart(boolean z) {
        this.showSelectionHandleStart.setValue(Boolean.valueOf(z));
    }

    public final void setTextDelegate(TextDelegate textDelegate) {
        this.textDelegate = textDelegate;
    }

    public final void setUntransformedText(AnnotatedString annotatedString) {
        this.untransformedText = annotatedString;
    }

    /* JADX INFO: renamed from: update-fnh65Uc, reason: not valid java name */
    public final void m1380updatefnh65Uc(AnnotatedString untransformedText, AnnotatedString visualText, TextStyle textStyle, boolean softWrap, Density density, FontFamily.Resolver fontFamilyResolver, Function1<? super TextFieldValue, Unit> onValueChange, KeyboardActions keyboardActions, FocusManager focusManager, long selectionBackgroundColor) {
        this.onValueChangeOriginal = onValueChange;
        this.selectionBackgroundColor = selectionBackgroundColor;
        KeyboardActionRunner keyboardActionRunner = this.keyboardActionRunner;
        keyboardActionRunner.setKeyboardActions(keyboardActions);
        keyboardActionRunner.setFocusManager(focusManager);
        this.untransformedText = untransformedText;
        TextDelegate textDelegateM1408updateTextDelegaterm0N8CA$default = TextDelegateKt.m1408updateTextDelegaterm0N8CA$default(this.textDelegate, visualText, textStyle, density, fontFamilyResolver, softWrap, 0, 0, 0, CollectionsKt.emptyList(), 448, null);
        if (this.textDelegate != textDelegateM1408updateTextDelegaterm0N8CA$default) {
            this.isLayoutResultStale = true;
        }
        this.textDelegate = textDelegateM1408updateTextDelegaterm0N8CA$default;
    }
}
