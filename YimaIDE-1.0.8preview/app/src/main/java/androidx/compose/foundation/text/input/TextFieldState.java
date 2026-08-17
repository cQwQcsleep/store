package androidx.compose.foundation.text.input;

import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.text.input.internal.ChangeTracker;
import androidx.compose.foundation.text.input.internal.OffsetMappingCalculator;
import androidx.compose.foundation.text.input.internal.undo.TextFieldEditUndoBehavior;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\r\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001:\u0002\\]B!\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tB\u001d\b\u0017\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\nJ\"\u0010/\u001a\u0002002\u0017\u00101\u001a\u0013\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020002¢\u0006\u0002\b3H\u0086\bJ\b\u00104\u001a\u00020\u0003H\u0016J\b\u0010:\u001a\u00020\u000fH\u0001J\u0010\u0010;\u001a\u0002002\u0006\u0010<\u001a\u00020\u000fH\u0001J\b\u0010=\u001a\u000200H\u0001JE\u0010>\u001a\u0002002\b\u0010?\u001a\u0004\u0018\u00010@2\b\b\u0002\u0010A\u001a\u00020\u00172\b\b\u0002\u0010B\u001a\u00020C2\u0017\u00101\u001a\u0013\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020002¢\u0006\u0002\b3H\u0080\b¢\u0006\u0002\bDJ'\u0010E\u001a\u0002002\u0017\u00101\u001a\u0013\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020002¢\u0006\u0002\b3H\u0080\b¢\u0006\u0002\bFJ&\u0010G\u001a\u0002002\b\u0010?\u001a\u0004\u0018\u00010@2\b\b\u0002\u0010A\u001a\u00020\u00172\b\b\u0002\u0010B\u001a\u00020CH\u0002J \u0010H\u001a\u0002002\u0006\u0010I\u001a\u00020\u001e2\u0006\u0010<\u001a\u00020\u001e2\u0006\u0010A\u001a\u00020\u0017H\u0002J(\u0010J\u001a\u0002002\u0006\u0010K\u001a\u00020\u001e2\u0006\u0010L\u001a\u00020\u001e2\u0006\u0010M\u001a\u00020N2\u0006\u0010B\u001a\u00020CH\u0002J\u0015\u0010O\u001a\u0002002\u0006\u0010P\u001a\u00020QH\u0000¢\u0006\u0002\bRJ\u0015\u0010S\u001a\u0002002\u0006\u0010P\u001a\u00020QH\u0000¢\u0006\u0002\bTJ%\u0010U\u001a\u0002002\u0006\u0010V\u001a\u00020\u000f2\u0006\u0010W\u001a\u00020\u00172\u0006\u0010X\u001a\u00020\u0017H\u0001¢\u0006\u0002\bYR\u0014\u0010\u000b\u001a\u00020\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR$\u0010\u000e\u001a\u00020\u000f8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R+\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u00178B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR+\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u0016\u001a\u00020\u001e8@@BX\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b$\u0010\u001d\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u0011\u0010%\u001a\u00020&8F¢\u0006\u0006\u001a\u0004\b'\u0010(R\u0011\u0010)\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b*\u0010+R\u0013\u0010,\u001a\u0004\u0018\u00010\u00058F¢\u0006\u0006\u001a\u0004\b-\u0010.R\u001c\u00105\u001a\u0002068\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b7\u0010\u0011\u001a\u0004\b8\u00109R\u0014\u0010Z\u001a\b\u0012\u0004\u0012\u00020Q0[X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006^"}, d2 = {"Landroidx/compose/foundation/text/input/TextFieldState;", "", "initialText", "", "initialSelection", "Landroidx/compose/ui/text/TextRange;", "initialTextUndoManager", "Landroidx/compose/foundation/text/input/TextUndoManager;", "<init>", "(Ljava/lang/String;JLandroidx/compose/foundation/text/input/TextUndoManager;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "(Ljava/lang/String;JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "textUndoManager", "getTextUndoManager$foundation", "()Landroidx/compose/foundation/text/input/TextUndoManager;", "mainBuffer", "Landroidx/compose/foundation/text/input/TextFieldBuffer;", "getMainBuffer$foundation$annotations", "()V", "getMainBuffer$foundation", "()Landroidx/compose/foundation/text/input/TextFieldBuffer;", "setMainBuffer$foundation", "(Landroidx/compose/foundation/text/input/TextFieldBuffer;)V", "<set-?>", "", "isEditing", "()Z", "setEditing", "(Z)V", "isEditing$delegate", "Landroidx/compose/runtime/MutableState;", "Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "value", "getValue$foundation", "()Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "setValue", "(Landroidx/compose/foundation/text/input/TextFieldCharSequence;)V", "value$delegate", "text", "", "getText", "()Ljava/lang/CharSequence;", "selection", "getSelection-d9O1mEE", "()J", "composition", "getComposition-MzsxiRA", "()Landroidx/compose/ui/text/TextRange;", "edit", "", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "toString", "undoState", "Landroidx/compose/foundation/text/input/UndoState;", "getUndoState$annotations", "getUndoState", "()Landroidx/compose/foundation/text/input/UndoState;", "startEdit", "commitEdit", "newValue", "finishEditing", "editAsUser", "inputTransformation", "Landroidx/compose/foundation/text/input/InputTransformation;", "restartImeIfContentChanges", "undoBehavior", "Landroidx/compose/foundation/text/input/internal/undo/TextFieldEditUndoBehavior;", "editAsUser$foundation", "editWithNoSideEffects", "editWithNoSideEffects$foundation", "commitEditAsUser", "updateValueAndNotifyListeners", "oldValue", "recordEditForUndo", "previousValue", "postValue", "changes", "Landroidx/compose/foundation/text/input/TextFieldBuffer$ChangeList;", "addNotifyImeListener", "notifyImeListener", "Landroidx/compose/foundation/text/input/TextFieldState$NotifyImeListener;", "addNotifyImeListener$foundation", "removeNotifyImeListener", "removeNotifyImeListener$foundation", "syncMainBufferToTemporaryBuffer", "temporaryBuffer", "textChanged", "selectionChanged", "syncMainBufferToTemporaryBuffer$foundation", "notifyImeListeners", "Landroidx/compose/runtime/collection/MutableVector;", "NotifyImeListener", "Saver", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class TextFieldState {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: isEditing$delegate, reason: from kotlin metadata */
    private final MutableState isEditing;
    private TextFieldBuffer mainBuffer;
    private final MutableVector<NotifyImeListener> notifyImeListeners;
    private final TextUndoManager textUndoManager;
    private final UndoState undoState;

    /* JADX INFO: renamed from: value$delegate, reason: from kotlin metadata */
    private final MutableState value;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bà\u0080\u0001\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/text/input/TextFieldState$NotifyImeListener;", "", "onChange", "", "oldValue", "Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "newValue", "restartIme", "", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface NotifyImeListener {
        void onChange(TextFieldCharSequence oldValue, TextFieldCharSequence newValue, boolean restartIme);
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0003*\u00020\u00072\u0006\u0010\b\u001a\u00020\u0002H\u0016J\u0012\u0010\t\u001a\u0004\u0018\u00010\u00022\u0006\u0010\b\u001a\u00020\u0003H\u0016¨\u0006\n"}, d2 = {"Landroidx/compose/foundation/text/input/TextFieldState$Saver;", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/foundation/text/input/TextFieldState;", "", "<init>", "()V", "save", "Landroidx/compose/runtime/saveable/SaverScope;", "value", "restore", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Saver implements androidx.compose.runtime.saveable.Saver<TextFieldState, Object> {
        public static final int $stable = 0;
        public static final Saver INSTANCE = new Saver();

        private Saver() {
        }

        /* JADX INFO: renamed from: restore, reason: merged with bridge method [inline-methods] */
        public TextFieldState m1491restore(Object value) {
            value.getClass();
            List list = (List) value;
            Object obj = list.get(0);
            Object obj2 = list.get(1);
            Object obj3 = list.get(2);
            Object obj4 = list.get(3);
            obj.getClass();
            String str = (String) obj;
            obj2.getClass();
            int iIntValue = ((Integer) obj2).intValue();
            obj3.getClass();
            long jTextRange = TextRangeKt.TextRange(iIntValue, ((Integer) obj3).intValue());
            TextUndoManager.Companion.Saver saver = TextUndoManager.Companion.Saver.INSTANCE;
            obj4.getClass();
            TextUndoManager textUndoManagerRestore = saver.m1514restore(obj4);
            textUndoManagerRestore.getClass();
            return new TextFieldState(str, jTextRange, textUndoManagerRestore, (DefaultConstructorMarker) null);
        }

        public Object save(SaverScope saverScope, TextFieldState textFieldState) {
            return CollectionsKt.listOf(new Object[]{textFieldState.getText().toString(), Integer.valueOf(TextRange.getStart-impl(textFieldState.m1490getSelectiond9O1mEE())), Integer.valueOf(TextRange.getEnd-impl(textFieldState.m1490getSelectiond9O1mEE())), TextUndoManager.Companion.Saver.INSTANCE.save(saverScope, textFieldState.getTextUndoManager())});
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TextFieldEditUndoBehavior.values().length];
            try {
                iArr[TextFieldEditUndoBehavior.ClearHistory.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TextFieldEditUndoBehavior.MergeIfPossible.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[TextFieldEditUndoBehavior.NeverMerge.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private TextFieldState(String str, long j, TextUndoManager textUndoManager) {
        this.textUndoManager = textUndoManager;
        ChangeTracker changeTracker = null;
        TextFieldCharSequence textFieldCharSequence = null;
        OffsetMappingCalculator offsetMappingCalculator = null;
        this.mainBuffer = new TextFieldBuffer(new TextFieldCharSequence(str, TextRangeKt.coerceIn-8ffj60Q(j, 0, str.length()), null, null, null, null, 60, null), changeTracker, textFieldCharSequence, offsetMappingCalculator, 14, null);
        this.isEditing = SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.value = SnapshotStateKt.mutableStateOf$default(new TextFieldCharSequence(str, j, null, null, null, null, 60, null), (SnapshotMutationPolicy) null, 2, (Object) null);
        this.undoState = new UndoState(this);
        this.notifyImeListeners = new MutableVector<>(new NotifyImeListener[16], 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void commitEditAsUser(InputTransformation inputTransformation, boolean restartImeIfContentChanges, TextFieldEditUndoBehavior undoBehavior) {
        TextFieldCharSequence value$foundation = getValue$foundation();
        if (this.mainBuffer.getChangeTracker$foundation().getChangeCount() == 0 && TextRange.equals-impl0(value$foundation.getSelection(), this.mainBuffer.getSelectionInChars())) {
            if (Intrinsics.areEqual(value$foundation.getComposition(), this.mainBuffer.getComposition()) && Intrinsics.areEqual(value$foundation.getHighlight(), this.mainBuffer.getHighlight$foundation()) && Intrinsics.areEqual(value$foundation.getComposingAnnotations(), this.mainBuffer.getComposingAnnotations$foundation())) {
                return;
            }
            updateValueAndNotifyListeners(getValue$foundation(), new TextFieldCharSequence(this.mainBuffer.toString(), this.mainBuffer.getSelectionInChars(), this.mainBuffer.getComposition(), this.mainBuffer.getHighlight$foundation(), TextFieldStateKt.m1493finalizeComposingAnnotationsitr0ztk(this.mainBuffer.getComposition(), this.mainBuffer.getComposingAnnotations$foundation()), null, 32, null), restartImeIfContentChanges);
            return;
        }
        boolean z = false;
        boolean z2 = this.mainBuffer.getChangeTracker$foundation().getChangeCount() != 0;
        TextFieldCharSequence textFieldCharSequence = new TextFieldCharSequence(this.mainBuffer.toString(), this.mainBuffer.getSelectionInChars(), this.mainBuffer.getComposition(), this.mainBuffer.getHighlight$foundation(), TextFieldStateKt.m1493finalizeComposingAnnotationsitr0ztk(this.mainBuffer.getComposition(), this.mainBuffer.getComposingAnnotations$foundation()), null, 32, null);
        if (inputTransformation == null) {
            if (z2 && restartImeIfContentChanges) {
                z = true;
            }
            updateValueAndNotifyListeners(value$foundation, textFieldCharSequence, z);
            recordEditForUndo(value$foundation, textFieldCharSequence, this.mainBuffer.getChangeTracker$foundation(), undoBehavior);
            return;
        }
        TextFieldBuffer textFieldBuffer = new TextFieldBuffer(textFieldCharSequence, this.mainBuffer.getChangeTracker$foundation(), value$foundation, null, 8, null);
        inputTransformation.transformInput(textFieldBuffer);
        boolean zContentEquals = StringsKt.contentEquals(textFieldBuffer.asCharSequence(), textFieldCharSequence);
        boolean z3 = !zContentEquals;
        boolean z4 = TextRange.equals-impl0(textFieldBuffer.getSelectionInChars(), textFieldCharSequence.getSelection());
        boolean z5 = !z4;
        if (zContentEquals && z4) {
            updateValueAndNotifyListeners(value$foundation, TextFieldBuffer.m1477toTextFieldCharSequencewFTz33Y$foundation$default(textFieldBuffer, 0L, textFieldCharSequence.getComposition(), null, null, 13, null), restartImeIfContentChanges);
        } else {
            syncMainBufferToTemporaryBuffer$foundation(textFieldBuffer, z3, z5);
        }
        recordEditForUndo(value$foundation, getValue$foundation(), textFieldBuffer.getChanges(), undoBehavior);
    }

    public static /* synthetic */ void commitEditAsUser$default(TextFieldState textFieldState, InputTransformation inputTransformation, boolean z, TextFieldEditUndoBehavior textFieldEditUndoBehavior, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            textFieldEditUndoBehavior = TextFieldEditUndoBehavior.MergeIfPossible;
        }
        textFieldState.commitEditAsUser(inputTransformation, z, textFieldEditUndoBehavior);
    }

    public static /* synthetic */ void editAsUser$foundation$default(TextFieldState textFieldState, InputTransformation inputTransformation, boolean z, TextFieldEditUndoBehavior textFieldEditUndoBehavior, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            textFieldEditUndoBehavior = TextFieldEditUndoBehavior.MergeIfPossible;
        }
        textFieldState.getMainBuffer().getChangeTracker$foundation().clearChanges();
        function1.invoke(textFieldState.getMainBuffer());
        textFieldState.commitEditAsUser(inputTransformation, z, textFieldEditUndoBehavior);
    }

    public static /* synthetic */ void getMainBuffer$foundation$annotations() {
    }

    public static /* synthetic */ void getUndoState$annotations() {
    }

    private final boolean isEditing() {
        return ((Boolean) this.isEditing.getValue()).booleanValue();
    }

    private final void recordEditForUndo(TextFieldCharSequence previousValue, TextFieldCharSequence postValue, TextFieldBuffer.ChangeList changes, TextFieldEditUndoBehavior undoBehavior) {
        int i = WhenMappings.$EnumSwitchMapping$0[undoBehavior.ordinal()];
        if (i == 1) {
            this.textUndoManager.clearHistory();
            return;
        }
        if (i == 2) {
            TextUndoManagerKt.recordChanges(this.textUndoManager, previousValue, postValue, changes, true);
        } else if (i == 3) {
            TextUndoManagerKt.recordChanges(this.textUndoManager, previousValue, postValue, changes, false);
        } else {
            bu8.a();
        }
    }

    private final void setEditing(boolean z) {
        this.isEditing.setValue(Boolean.valueOf(z));
    }

    private final void setValue(TextFieldCharSequence textFieldCharSequence) {
        this.value.setValue(textFieldCharSequence);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateValueAndNotifyListeners(TextFieldCharSequence oldValue, TextFieldCharSequence newValue, boolean restartImeIfContentChanges) {
        setValue(newValue);
        finishEditing();
        MutableVector<NotifyImeListener> mutableVector = this.notifyImeListeners;
        Object[] objArr = mutableVector.content;
        int size = mutableVector.getSize();
        for (int i = 0; i < size; i++) {
            ((NotifyImeListener) objArr[i]).onChange(oldValue, newValue, (!restartImeIfContentChanges || oldValue.contentEquals(newValue) || oldValue.getComposition() == null) ? false : true);
        }
    }

    public final void addNotifyImeListener$foundation(NotifyImeListener notifyImeListener) {
        this.notifyImeListeners.add(notifyImeListener);
    }

    public final void commitEdit(TextFieldBuffer newValue) {
        TextFieldBuffer textFieldBuffer;
        boolean z = newValue.getChanges().getChangeCount() > 0;
        boolean z2 = TextRange.equals-impl0(newValue.getSelectionInChars(), this.mainBuffer.getSelectionInChars());
        boolean z3 = !z2;
        if (!z && z2) {
            newValue.setCanCallAddStyle$foundation(true);
        }
        if (z) {
            textFieldBuffer = newValue;
            recordEditForUndo(getValue$foundation(), TextFieldBuffer.m1477toTextFieldCharSequencewFTz33Y$foundation$default(textFieldBuffer, 0L, null, null, null, 15, null), textFieldBuffer.getChanges(), TextFieldEditUndoBehavior.NeverMerge);
        } else {
            textFieldBuffer = newValue;
        }
        syncMainBufferToTemporaryBuffer$foundation(textFieldBuffer, z, z3);
    }

    public final void edit(Function1<? super TextFieldBuffer, Unit> block) {
        TextFieldBuffer textFieldBufferStartEdit = startEdit();
        try {
            block.invoke(textFieldBufferStartEdit);
            commitEdit(textFieldBufferStartEdit);
        } finally {
            InlineMarker.finallyStart(1);
            finishEditing();
            InlineMarker.finallyEnd(1);
        }
    }

    public final void editAsUser$foundation(InputTransformation inputTransformation, boolean restartImeIfContentChanges, TextFieldEditUndoBehavior undoBehavior, Function1<? super TextFieldBuffer, Unit> block) {
        getMainBuffer().getChangeTracker$foundation().clearChanges();
        block.invoke(getMainBuffer());
        commitEditAsUser(inputTransformation, restartImeIfContentChanges, undoBehavior);
    }

    public final void editWithNoSideEffects$foundation(Function1<? super TextFieldBuffer, Unit> block) {
        getMainBuffer().getChangeTracker$foundation().clearChanges();
        block.invoke(getMainBuffer());
        updateValueAndNotifyListeners(getValue$foundation(), TextFieldBuffer.m1477toTextFieldCharSequencewFTz33Y$foundation$default(getMainBuffer(), 0L, null, null, null, 15, null), true);
    }

    public final void finishEditing() {
        setEditing(false);
    }

    /* JADX INFO: renamed from: getComposition-MzsxiRA, reason: not valid java name */
    public final TextRange m1489getCompositionMzsxiRA() {
        return getValue$foundation().getComposition();
    }

    /* JADX INFO: renamed from: getMainBuffer$foundation, reason: from getter */
    public final TextFieldBuffer getMainBuffer() {
        return this.mainBuffer;
    }

    /* JADX INFO: renamed from: getSelection-d9O1mEE, reason: not valid java name */
    public final long m1490getSelectiond9O1mEE() {
        return getValue$foundation().getSelection();
    }

    public final CharSequence getText() {
        return getValue$foundation().getText();
    }

    /* JADX INFO: renamed from: getTextUndoManager$foundation, reason: from getter */
    public final TextUndoManager getTextUndoManager() {
        return this.textUndoManager;
    }

    public final UndoState getUndoState() {
        return this.undoState;
    }

    public final TextFieldCharSequence getValue$foundation() {
        return (TextFieldCharSequence) this.value.getValue();
    }

    public final void removeNotifyImeListener$foundation(NotifyImeListener notifyImeListener) {
        this.notifyImeListeners.remove(notifyImeListener);
    }

    public final void setMainBuffer$foundation(TextFieldBuffer textFieldBuffer) {
        this.mainBuffer = textFieldBuffer;
    }

    public final TextFieldBuffer startEdit() {
        Snapshot.Companion companion = Snapshot.Companion;
        Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
        Function1 readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
        Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
        try {
            boolean zIsEditing = isEditing();
            companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
            if (zIsEditing) {
                InlineClassHelperKt.throwIllegalStateException("TextFieldState does not support concurrent or nested editing.");
            }
            setEditing(true);
            return new TextFieldBuffer(getValue$foundation(), null, null, null, 14, null);
        } catch (Throwable th) {
            companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
            throw th;
        }
    }

    public final void syncMainBufferToTemporaryBuffer$foundation(TextFieldBuffer temporaryBuffer, boolean textChanged, boolean selectionChanged) {
        TextFieldCharSequence textFieldCharSequenceM1477toTextFieldCharSequencewFTz33Y$foundation$default = TextFieldBuffer.m1477toTextFieldCharSequencewFTz33Y$foundation$default(this.mainBuffer, 0L, null, null, null, 15, null);
        if (textChanged) {
            ChangeTracker changeTracker = null;
            TextFieldCharSequence textFieldCharSequence = null;
            OffsetMappingCalculator offsetMappingCalculator = null;
            this.mainBuffer = new TextFieldBuffer(new TextFieldCharSequence(temporaryBuffer.toString(), temporaryBuffer.getSelectionInChars(), null, null, null, null, 60, null), changeTracker, textFieldCharSequence, offsetMappingCalculator, 14, null);
        } else if (selectionChanged) {
            this.mainBuffer.m1482setSelection5zctL8(TextRangeKt.TextRange(TextRange.getStart-impl(temporaryBuffer.getSelectionInChars()), TextRange.getEnd-impl(temporaryBuffer.getSelectionInChars())));
        }
        if (textChanged || selectionChanged || !Intrinsics.areEqual(textFieldCharSequenceM1477toTextFieldCharSequencewFTz33Y$foundation$default.getComposition(), temporaryBuffer.getComposition())) {
            this.mainBuffer.commitComposition$foundation();
        }
        updateValueAndNotifyListeners(textFieldCharSequenceM1477toTextFieldCharSequencewFTz33Y$foundation$default, TextFieldBuffer.m1477toTextFieldCharSequencewFTz33Y$foundation$default(this.mainBuffer, 0L, null, null, null, 15, null), true);
    }

    public String toString() {
        Snapshot.Companion companion = Snapshot.Companion;
        Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
        Function1 readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
        Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
        try {
            return "TextFieldState(selection=" + ((Object) TextRange.toString-impl(m1490getSelectiond9O1mEE())) + ", text=\"" + ((Object) getText()) + "\")";
        } finally {
            companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
        }
    }

    public /* synthetic */ TextFieldState(String str, long j, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, j);
    }

    public /* synthetic */ TextFieldState(String str, long j, TextUndoManager textUndoManager, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, j, textUndoManager);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ TextFieldState(String str, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        str = (i & 1) != 0 ? "" : str;
        this(str, (i & 2) != 0 ? TextRangeKt.TextRange(str.length()) : j, (DefaultConstructorMarker) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private TextFieldState(String str, long j) {
        this(str, j, new TextUndoManager(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0), (DefaultConstructorMarker) null);
    }
}
