package androidx.compose.foundation.text.input.internal;

import android.view.KeyEvent;
import androidx.collection.MutableLongSet;
import androidx.collection.ScatterMapKt;
import androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformerKt;
import androidx.compose.foundation.ComposeFoundationFlags;
import androidx.compose.foundation.text.DeadKeyCombiner;
import androidx.compose.foundation.text.KeyCommand;
import androidx.compose.foundation.text.KeyEventHelpers_androidKt;
import androidx.compose.foundation.text.KeyMapping;
import androidx.compose.foundation.text.KeyMapping_androidKt;
import androidx.compose.foundation.text.StringHelpers_jvmKt;
import androidx.compose.foundation.text.TextFieldKeyInput_androidKt;
import androidx.compose.foundation.text.input.internal.TextFieldKeyEventHandler;
import androidx.compose.foundation.text.input.internal.selection.SelectionMovementDeletionContext;
import androidx.compose.foundation.text.input.internal.selection.TextFieldPreparedSelectionState;
import androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState;
import androidx.compose.ui.focus.FocusManager;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import androidx.compose.ui.text.TextRange;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0000\b!\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J7\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J]\u0010\u001a\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\r2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\r0\"H\u0016¢\u0006\u0004\b#\u0010$JU\u0010%\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\r2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\r0\"H\u0002¢\u0006\u0004\b&\u0010'J\f\u0010(\u001a\u00020)*\u00020\u001cH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Landroidx/compose/foundation/text/input/internal/TextFieldKeyEventHandler;", "", "<init>", "()V", "preparedSelectionState", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldPreparedSelectionState;", "deadKeyCombiner", "Landroidx/compose/foundation/text/DeadKeyCombiner;", "keyMapping", "Landroidx/compose/foundation/text/KeyMapping;", "currentlyConsumedDownKeys", "Landroidx/collection/MutableLongSet;", "onPreKeyEvent", "", "event", "Landroidx/compose/ui/input/key/KeyEvent;", "textFieldState", "Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;", "textFieldSelectionState", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;", "focusManager", "Landroidx/compose/ui/focus/FocusManager;", "keyboardController", "Landroidx/compose/ui/platform/SoftwareKeyboardController;", "onPreKeyEvent-MyFupTE", "(Landroid/view/KeyEvent;Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;Landroidx/compose/ui/focus/FocusManager;Landroidx/compose/ui/platform/SoftwareKeyboardController;)Z", "onKeyEvent", "textLayoutState", "Landroidx/compose/foundation/text/input/internal/TextLayoutState;", "clipboardKeyCommandsHandler", "Landroidx/compose/foundation/text/input/internal/ClipboardKeyCommandsHandler;", "editable", "singleLine", "onSubmit", "Lkotlin/Function0;", "onKeyEvent-8zsqlwg", "(Landroid/view/KeyEvent;Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;Landroidx/compose/foundation/text/input/internal/TextLayoutState;Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/platform/SoftwareKeyboardController;ZZLkotlin/jvm/functions/Function0;)Z", "processKeyDownEvent", "processKeyDownEvent-q0GpTC0", "(Landroid/view/KeyEvent;Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;Landroidx/compose/foundation/text/input/internal/TextLayoutState;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/platform/SoftwareKeyboardController;ZZLkotlin/jvm/functions/Function0;)Z", "getVisibleTextLayoutHeight", "", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class TextFieldKeyEventHandler {
    public static final int $stable = 8;
    private MutableLongSet currentlyConsumedDownKeys;
    private final TextFieldPreparedSelectionState preparedSelectionState = new TextFieldPreparedSelectionState();
    private final DeadKeyCombiner deadKeyCombiner = new DeadKeyCombiner();
    private final KeyMapping keyMapping = KeyMapping_androidKt.getPlatformDefaultKeyMapping();

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[KeyCommand.values().length];
            try {
                iArr[KeyCommand.COPY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[KeyCommand.PASTE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[KeyCommand.CUT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[KeyCommand.LEFT_CHAR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[KeyCommand.RIGHT_CHAR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[KeyCommand.LEFT_WORD.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[KeyCommand.RIGHT_WORD.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[KeyCommand.PREV_PARAGRAPH.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[KeyCommand.NEXT_PARAGRAPH.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[KeyCommand.UP.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[KeyCommand.DOWN.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[KeyCommand.PAGE_UP.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[KeyCommand.PAGE_DOWN.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr[KeyCommand.LINE_START.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr[KeyCommand.LINE_END.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                iArr[KeyCommand.LINE_LEFT.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                iArr[KeyCommand.LINE_RIGHT.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                iArr[KeyCommand.HOME.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                iArr[KeyCommand.END.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                iArr[KeyCommand.DELETE_PREV_CHAR.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                iArr[KeyCommand.DELETE_NEXT_CHAR.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                iArr[KeyCommand.DELETE_PREV_WORD.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                iArr[KeyCommand.DELETE_NEXT_WORD.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                iArr[KeyCommand.DELETE_FROM_LINE_START.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                iArr[KeyCommand.DELETE_TO_LINE_END.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                iArr[KeyCommand.NEW_LINE.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                iArr[KeyCommand.TAB.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                iArr[KeyCommand.SELECT_ALL.ordinal()] = 28;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                iArr[KeyCommand.SELECT_LEFT_CHAR.ordinal()] = 29;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                iArr[KeyCommand.SELECT_RIGHT_CHAR.ordinal()] = 30;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                iArr[KeyCommand.SELECT_LEFT_WORD.ordinal()] = 31;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                iArr[KeyCommand.SELECT_RIGHT_WORD.ordinal()] = 32;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                iArr[KeyCommand.SELECT_PREV_PARAGRAPH.ordinal()] = 33;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                iArr[KeyCommand.SELECT_NEXT_PARAGRAPH.ordinal()] = 34;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                iArr[KeyCommand.SELECT_LINE_START.ordinal()] = 35;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                iArr[KeyCommand.SELECT_LINE_END.ordinal()] = 36;
            } catch (NoSuchFieldError unused36) {
            }
            try {
                iArr[KeyCommand.SELECT_LINE_LEFT.ordinal()] = 37;
            } catch (NoSuchFieldError unused37) {
            }
            try {
                iArr[KeyCommand.SELECT_LINE_RIGHT.ordinal()] = 38;
            } catch (NoSuchFieldError unused38) {
            }
            try {
                iArr[KeyCommand.SELECT_UP.ordinal()] = 39;
            } catch (NoSuchFieldError unused39) {
            }
            try {
                iArr[KeyCommand.SELECT_DOWN.ordinal()] = 40;
            } catch (NoSuchFieldError unused40) {
            }
            try {
                iArr[KeyCommand.SELECT_PAGE_UP.ordinal()] = 41;
            } catch (NoSuchFieldError unused41) {
            }
            try {
                iArr[KeyCommand.SELECT_PAGE_DOWN.ordinal()] = 42;
            } catch (NoSuchFieldError unused42) {
            }
            try {
                iArr[KeyCommand.SELECT_HOME.ordinal()] = 43;
            } catch (NoSuchFieldError unused43) {
            }
            try {
                iArr[KeyCommand.SELECT_END.ordinal()] = 44;
            } catch (NoSuchFieldError unused44) {
            }
            try {
                iArr[KeyCommand.DESELECT.ordinal()] = 45;
            } catch (NoSuchFieldError unused45) {
            }
            try {
                iArr[KeyCommand.UNDO.ordinal()] = 46;
            } catch (NoSuchFieldError unused46) {
            }
            try {
                iArr[KeyCommand.REDO.ordinal()] = 47;
            } catch (NoSuchFieldError unused47) {
            }
            try {
                iArr[KeyCommand.CHARACTER_PALETTE.ordinal()] = 48;
            } catch (NoSuchFieldError unused48) {
            }
            try {
                iArr[KeyCommand.CENTER.ordinal()] = 49;
            } catch (NoSuchFieldError unused49) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private final float getVisibleTextLayoutHeight(TextLayoutState textLayoutState) {
        LayoutCoordinates textLayoutNodeCoordinates = textLayoutState.getTextLayoutNodeCoordinates();
        if (textLayoutNodeCoordinates == null) {
            return Float.NaN;
        }
        Rect rectLocalBoundingBoxOf$default = null;
        if (!textLayoutNodeCoordinates.isAttached()) {
            textLayoutNodeCoordinates = null;
        }
        if (textLayoutNodeCoordinates == null) {
            return Float.NaN;
        }
        LayoutCoordinates decoratorNodeCoordinates = textLayoutState.getDecoratorNodeCoordinates();
        if (decoratorNodeCoordinates != null) {
            if (!decoratorNodeCoordinates.isAttached()) {
                decoratorNodeCoordinates = null;
            }
            if (decoratorNodeCoordinates != null) {
                rectLocalBoundingBoxOf$default = LayoutCoordinates.localBoundingBoxOf$default(decoratorNodeCoordinates, textLayoutNodeCoordinates, false, 2, (Object) null);
            }
        }
        if (rectLocalBoundingBoxOf$default != null) {
            return Float.intBitsToFloat((int) (rectLocalBoundingBoxOf$default.getSize-NH-jbRc() & 4294967295L));
        }
        return Float.NaN;
    }

    /* JADX WARN: Code duplicated, block: B:79:0x01ec  */
    /* JADX WARN: Code duplicated, block: B:90:0x0220  */
    /* JADX WARN: Code duplicated, block: B:93:0x022d  */
    /* JADX WARN: Code duplicated, block: B:97:0x0241  */
    /* JADX WARN: Code duplicated, block: B:99:0x024a  */
    /* JADX INFO: renamed from: processKeyDownEvent-q0GpTC0, reason: not valid java name */
    private final boolean m1583processKeyDownEventq0GpTC0(KeyEvent event, TransformedTextFieldState textFieldState, TextLayoutState textLayoutState, Function1<? super KeyCommand, ? extends Unit> clipboardKeyCommandsHandler, SoftwareKeyboardController keyboardController, boolean editable, boolean singleLine, Function0<Boolean> onSubmit) {
        WedgeAffinity wedgeAffinity;
        Integer numM1350consumeZmokQxo;
        boolean zBooleanValue = false;
        if (TextFieldKeyInput_androidKt.m1430isTypedEventZmokQxo(event) && (numM1350consumeZmokQxo = this.deadKeyCombiner.m1350consumeZmokQxo(event)) != null) {
            String string = StringHelpers_jvmKt.appendCodePointX(new StringBuilder(2), numM1350consumeZmokQxo.intValue()).toString();
            if (!editable) {
                return false;
            }
            TransformedTextFieldState.replaceSelectedText$default(textFieldState, string, true, null, !TextFieldKeyEventHandler_androidKt.m1585isFromSoftKeyboardZmokQxo(event), 4, null);
            this.preparedSelectionState.resetCachedX();
            return true;
        }
        KeyCommand keyCommandMo1354mapZmokQxo = this.keyMapping.mo1354mapZmokQxo(event);
        if (keyCommandMo1354mapZmokQxo != null && (!keyCommandMo1354mapZmokQxo.getEditsText() || editable)) {
            SelectionMovementDeletionContext selectionMovementDeletionContext = new SelectionMovementDeletionContext(textFieldState, textLayoutState.getLayoutResult(), TextFieldKeyEventHandler_androidKt.m1585isFromSoftKeyboardZmokQxo(event), getVisibleTextLayoutHeight(textLayoutState), this.preparedSelectionState);
            switch (WhenMappings.$EnumSwitchMapping$0[keyCommandMo1354mapZmokQxo.ordinal()]) {
                case 1:
                case 2:
                case 3:
                    clipboardKeyCommandsHandler.invoke(keyCommandMo1354mapZmokQxo);
                    Unit unit = Unit.INSTANCE;
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled && (keyCommandMo1354mapZmokQxo == KeyCommand.UP || keyCommandMo1354mapZmokQxo == KeyCommand.DOWN || keyCommandMo1354mapZmokQxo == KeyCommand.LEFT_CHAR || keyCommandMo1354mapZmokQxo == KeyCommand.RIGHT_CHAR)) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null && (wedgeAffinity = selectionMovementDeletionContext.getWedgeAffinity()) != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 4:
                    selectionMovementDeletionContext.collapseLeftOr(new Function1() { // from class: g7e
                        public final Object invoke(Object obj) {
                            return TextFieldKeyEventHandler.processKeyDownEvent_q0GpTC0$lambda$0$0((SelectionMovementDeletionContext) obj);
                        }
                    });
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 5:
                    selectionMovementDeletionContext.collapseRightOr(new Function1() { // from class: h7e
                        public final Object invoke(Object obj) {
                            return TextFieldKeyEventHandler.processKeyDownEvent_q0GpTC0$lambda$0$1((SelectionMovementDeletionContext) obj);
                        }
                    });
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                    selectionMovementDeletionContext.moveCursorLeftByWord();
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case ScatterMapKt.ClonedMetadataCount /* 7 */:
                    selectionMovementDeletionContext.moveCursorRightByWord();
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 8:
                    selectionMovementDeletionContext.moveCursorPrevByParagraph();
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 9:
                    selectionMovementDeletionContext.moveCursorNextByParagraph();
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 10:
                    selectionMovementDeletionContext.moveCursorUpByLine();
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 11:
                    selectionMovementDeletionContext.moveCursorDownByLine();
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 12:
                    selectionMovementDeletionContext.moveCursorUpByPage();
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 13:
                    selectionMovementDeletionContext.moveCursorDownByPage();
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 14:
                    selectionMovementDeletionContext.moveCursorToLineStart();
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 15:
                    selectionMovementDeletionContext.moveCursorToLineEnd();
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 16:
                    selectionMovementDeletionContext.moveCursorToLineLeftSide();
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 17:
                    selectionMovementDeletionContext.moveCursorToLineRightSide();
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 18:
                    selectionMovementDeletionContext.moveCursorToHome();
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 19:
                    selectionMovementDeletionContext.moveCursorToEnd();
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 20:
                    selectionMovementDeletionContext.moveCursorPrevByCodePointOrEmoji().deleteMovement();
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 21:
                    selectionMovementDeletionContext.moveCursorNextByChar().deleteMovement();
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 22:
                    selectionMovementDeletionContext.moveCursorPrevByWord().deleteMovement();
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 23:
                    selectionMovementDeletionContext.moveCursorNextByWord().deleteMovement();
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 24:
                    selectionMovementDeletionContext.moveCursorToLineStart().deleteMovement();
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 25:
                    selectionMovementDeletionContext.moveCursorToLineEnd().deleteMovement();
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 26:
                    if (singleLine) {
                        zBooleanValue = ((Boolean) onSubmit.invoke()).booleanValue();
                    } else {
                        TransformedTextFieldState.replaceSelectedText$default(textFieldState, "\n", true, null, !TextFieldKeyEventHandler_androidKt.m1585isFromSoftKeyboardZmokQxo(event), 4, null);
                        zBooleanValue = true;
                    }
                    Unit unit2 = Unit.INSTANCE;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 27:
                    if (!singleLine) {
                        TransformedTextFieldState.replaceSelectedText$default(textFieldState, "\t", true, null, !TextFieldKeyEventHandler_androidKt.m1585isFromSoftKeyboardZmokQxo(event), 4, null);
                        zBooleanValue = true;
                    }
                    Unit unit3 = Unit.INSTANCE;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 28:
                    selectionMovementDeletionContext.selectAll();
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 29:
                    selectionMovementDeletionContext.moveCursorLeftByChar().selectMovement();
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 30:
                    selectionMovementDeletionContext.moveCursorRightByChar().selectMovement();
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case ComposableFunctionBodyTransformerKt.BITS_PER_INT /* 31 */:
                    selectionMovementDeletionContext.moveCursorLeftByWord().selectMovement();
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 32:
                    selectionMovementDeletionContext.moveCursorRightByWord().selectMovement();
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 33:
                    selectionMovementDeletionContext.moveCursorPrevByParagraph().selectMovement();
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 34:
                    selectionMovementDeletionContext.moveCursorNextByParagraph().selectMovement();
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 35:
                    selectionMovementDeletionContext.moveCursorToLineStart().selectMovement();
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 36:
                    selectionMovementDeletionContext.moveCursorToLineEnd().selectMovement();
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 37:
                    selectionMovementDeletionContext.moveCursorToLineLeftSide().selectMovement();
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 38:
                    selectionMovementDeletionContext.moveCursorToLineRightSide().selectMovement();
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 39:
                    selectionMovementDeletionContext.moveCursorUpByLine().selectMovement();
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 40:
                    selectionMovementDeletionContext.moveCursorDownByLine().selectMovement();
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 41:
                    selectionMovementDeletionContext.moveCursorUpByPage().selectMovement();
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 42:
                    selectionMovementDeletionContext.moveCursorDownByPage().selectMovement();
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 43:
                    selectionMovementDeletionContext.moveCursorToHome().selectMovement();
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 44:
                    selectionMovementDeletionContext.moveCursorToEnd().selectMovement();
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 45:
                    selectionMovementDeletionContext.deselect();
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 46:
                    textFieldState.undo();
                    Unit unit4 = Unit.INSTANCE;
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 47:
                    textFieldState.redo();
                    Unit unit5 = Unit.INSTANCE;
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 48:
                    KeyEventHelpers_androidKt.showCharacterPalette();
                    Unit unit6 = Unit.INSTANCE;
                    zBooleanValue = true;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                case 49:
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        keyboardController.show();
                        zBooleanValue = true;
                    }
                    Unit unit7 = Unit.INSTANCE;
                    if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
                        zBooleanValue = !TextRange.equals-impl0(selectionMovementDeletionContext.getInitialValue().getSelection(), selectionMovementDeletionContext.getSelection());
                    }
                    if (!TextRange.equals-impl0(selectionMovementDeletionContext.getSelection(), selectionMovementDeletionContext.getInitialValue().getSelection())) {
                        textFieldState.m1616selectCharsIn5zctL8(selectionMovementDeletionContext.getSelection());
                    }
                    if (selectionMovementDeletionContext.getWedgeAffinity() != null) {
                        if (TextRange.getCollapsed-impl(textFieldState.getUntransformedText().getSelection())) {
                            textFieldState.setSelectionWedgeAffinity(new SelectionWedgeAffinity(wedgeAffinity));
                            return zBooleanValue;
                        }
                        textFieldState.setSelectionWedgeAffinity(SelectionWedgeAffinity.copy$default(selectionMovementDeletionContext.getInitialWedgeAffinity(), null, wedgeAffinity, 1, null));
                    }
                    break;
                default:
                    bu8.a();
                    return false;
            }
        }
        return zBooleanValue;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit processKeyDownEvent_q0GpTC0$lambda$0$0(SelectionMovementDeletionContext selectionMovementDeletionContext) {
        selectionMovementDeletionContext.moveCursorLeftByChar();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit processKeyDownEvent_q0GpTC0$lambda$0$1(SelectionMovementDeletionContext selectionMovementDeletionContext) {
        selectionMovementDeletionContext.moveCursorRightByChar();
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: onKeyEvent-8zsqlwg */
    public boolean mo1517onKeyEvent8zsqlwg(KeyEvent event, TransformedTextFieldState textFieldState, TextLayoutState textLayoutState, TextFieldSelectionState textFieldSelectionState, Function1<? super KeyCommand, ? extends Unit> clipboardKeyCommandsHandler, SoftwareKeyboardController keyboardController, boolean editable, boolean singleLine, Function0<Boolean> onSubmit) {
        long j = KeyEvent_androidKt.getKey-ZmokQxo(event);
        int i = KeyEvent_androidKt.getType-ZmokQxo(event);
        KeyEventType.Companion companion = KeyEventType.Companion;
        if (KeyEventType.equals-impl0(i, companion.getKeyUp-CS__XNY())) {
            MutableLongSet mutableLongSet = this.currentlyConsumedDownKeys;
            if (mutableLongSet == null || !mutableLongSet.contains(j)) {
                return false;
            }
            MutableLongSet mutableLongSet2 = this.currentlyConsumedDownKeys;
            if (mutableLongSet2 != null) {
                mutableLongSet2.remove(j);
            }
            return true;
        }
        if (KeyEventType.equals-impl0(KeyEvent_androidKt.getType-ZmokQxo(event), companion.getUnknown-CS__XNY()) && !TextFieldKeyInput_androidKt.m1430isTypedEventZmokQxo(event)) {
            return false;
        }
        boolean zM1583processKeyDownEventq0GpTC0 = m1583processKeyDownEventq0GpTC0(event, textFieldState, textLayoutState, clipboardKeyCommandsHandler, keyboardController, editable, singleLine, onSubmit);
        if (zM1583processKeyDownEventq0GpTC0) {
            MutableLongSet mutableLongSet3 = this.currentlyConsumedDownKeys;
            if (mutableLongSet3 == null) {
                mutableLongSet3 = new MutableLongSet(3);
                this.currentlyConsumedDownKeys = mutableLongSet3;
            }
            mutableLongSet3.plusAssign(j);
        }
        return zM1583processKeyDownEventq0GpTC0;
    }

    /* JADX INFO: renamed from: onPreKeyEvent-MyFupTE */
    public boolean mo1518onPreKeyEventMyFupTE(KeyEvent event, TransformedTextFieldState textFieldState, TextFieldSelectionState textFieldSelectionState, FocusManager focusManager, SoftwareKeyboardController keyboardController) {
        if (TextRange.getCollapsed-impl(textFieldState.getVisualText().getSelection()) || !KeyEventHelpers_androidKt.m1353cancelsTextSelectionZmokQxo(event)) {
            return false;
        }
        textFieldSelectionState.deselect();
        return true;
    }
}
