package androidx.compose.foundation.text.input.internal;

import android.os.CancellationSignal;
import android.view.inputmethod.DeleteGesture;
import android.view.inputmethod.DeleteRangeGesture;
import android.view.inputmethod.HandwritingGesture;
import android.view.inputmethod.InsertGesture;
import android.view.inputmethod.JoinOrSplitGesture;
import android.view.inputmethod.PreviewableHandwritingGesture;
import android.view.inputmethod.RemoveSpaceGesture;
import android.view.inputmethod.SelectGesture;
import android.view.inputmethod.SelectRangeGesture;
import androidx.compose.foundation.text.LegacyTextFieldState;
import androidx.compose.foundation.text.TextLayoutResultProxy;
import androidx.compose.foundation.text.input.InputTransformation;
import androidx.compose.foundation.text.input.TextFieldBuffer;
import androidx.compose.foundation.text.input.TextFieldState;
import androidx.compose.foundation.text.input.TextHighlightType;
import androidx.compose.foundation.text.input.internal.HandwritingGestureApi34;
import androidx.compose.foundation.text.input.internal.undo.TextFieldEditUndoBehavior;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.ui.graphics.RectHelper_androidKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextGranularity;
import androidx.compose.ui.text.TextInclusionStrategy;
import androidx.compose.ui.text.TextLayoutInput;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.input.CommitTextCommand;
import androidx.compose.ui.text.input.DeleteSurroundingTextCommand;
import androidx.compose.ui.text.input.EditCommand;
import androidx.compose.ui.text.input.SetSelectionCommand;
import defpackage.a56;
import defpackage.b56;
import defpackage.c56;
import defpackage.n56;
import defpackage.o46;
import defpackage.p46;
import defpackage.q46;
import defpackage.r46;
import defpackage.s46;
import defpackage.t46;
import defpackage.u46;
import defpackage.v46;
import defpackage.x46;
import defpackage.y46;
import defpackage.z46;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.MatchResult;
import kotlin.text.Regex;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000º\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J;\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0000¢\u0006\u0002\b\u0010J+\u0010\u0011\u001a\u00020\u0012*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\n2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0000¢\u0006\u0002\b\u0016J,\u0010\u0017\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\t\u001a\u00020\n2\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fH\u0002J\u001c\u0010\u001a\u001a\u00020\r*\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\t\u001a\u00020\nH\u0002J\u001c\u0010\u001b\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u001c2\u0006\u0010\t\u001a\u00020\nH\u0002J\u001c\u0010\u001d\u001a\u00020\r*\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u001c2\u0006\u0010\t\u001a\u00020\nH\u0002J,\u0010\u001e\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u001f2\u0006\u0010\t\u001a\u00020\n2\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fH\u0002J\u001c\u0010 \u001a\u00020\r*\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u001f2\u0006\u0010\t\u001a\u00020\nH\u0002J\u001c\u0010!\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0018\u001a\u00020\"2\u0006\u0010\t\u001a\u00020\nH\u0002J\u001c\u0010#\u001a\u00020\r*\u00020\u00062\u0006\u0010\u0018\u001a\u00020\"2\u0006\u0010\t\u001a\u00020\nH\u0002J&\u0010$\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0018\u001a\u00020%2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0002J&\u0010&\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0018\u001a\u00020'2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0002J&\u0010(\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0018\u001a\u00020)2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0002J#\u0010*\u001a\u00020\r*\u00020\u00062\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u0012H\u0002¢\u0006\u0004\b.\u0010/J\u0014\u00100\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0018\u001a\u00020\bH\u0002J#\u00101\u001a\u00020\r*\u00020\u00062\u0006\u00102\u001a\u00020,2\u0006\u00103\u001a\u000204H\u0002¢\u0006\u0004\b5\u00106JA\u0010\u0004\u001a\u00020\u0005*\u0002072\u0006\u0010\u0018\u001a\u00020\b2\b\u00108\u001a\u0004\u0018\u0001092\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\r0;H\u0000¢\u0006\u0002\b\u0010J-\u0010\u0011\u001a\u00020\u0012*\u0002072\u0006\u0010\u0018\u001a\u00020\u00132\b\u00108\u001a\u0004\u0018\u0001092\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0000¢\u0006\u0002\b\u0016J2\u0010\u0017\u001a\u00020\u0005*\u0002072\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010=\u001a\u0004\u0018\u0001092\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\r0;H\u0002J\u001e\u0010\u001a\u001a\u00020\r*\u0002072\u0006\u0010\u0018\u001a\u00020\u00192\b\u00108\u001a\u0004\u0018\u000109H\u0002J0\u0010\u001b\u001a\u00020\u0005*\u0002072\u0006\u0010\u0018\u001a\u00020\u001c2\u0006\u0010>\u001a\u00020?2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\r0;H\u0002J\u001e\u0010\u001d\u001a\u00020\r*\u0002072\u0006\u0010\u0018\u001a\u00020\u001c2\b\u00108\u001a\u0004\u0018\u000109H\u0002J2\u0010\u001e\u001a\u00020\u0005*\u0002072\u0006\u0010\u0018\u001a\u00020\u001f2\b\u0010=\u001a\u0004\u0018\u0001092\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\r0;H\u0002J\u001e\u0010 \u001a\u00020\r*\u0002072\u0006\u0010\u0018\u001a\u00020\u001f2\b\u00108\u001a\u0004\u0018\u000109H\u0002J0\u0010!\u001a\u00020\u0005*\u0002072\u0006\u0010\u0018\u001a\u00020\"2\u0006\u0010>\u001a\u00020?2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\r0;H\u0002J\u001e\u0010#\u001a\u00020\r*\u0002072\u0006\u0010\u0018\u001a\u00020\"2\b\u00108\u001a\u0004\u0018\u000109H\u0002J:\u0010$\u001a\u00020\u0005*\u0002072\u0006\u0010\u0018\u001a\u00020%2\u0006\u0010>\u001a\u00020?2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\r0;H\u0002J2\u0010&\u001a\u00020\u0005*\u0002072\u0006\u0010\u0018\u001a\u00020'2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\r0;H\u0002J:\u0010(\u001a\u00020\u0005*\u0002072\u0006\u0010\u0018\u001a\u00020)2\u0006\u0010>\u001a\u00020?2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\r0;H\u0002J,\u0010@\u001a\u00020\r2\u0006\u0010A\u001a\u00020\u00052\u0006\u0010>\u001a\u00020B2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\r0;H\u0002J5\u0010C\u001a\u00020\r2\u0006\u00102\u001a\u00020,2\b\u0010=\u001a\u0004\u0018\u0001092\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\r0;H\u0002¢\u0006\u0004\bD\u0010EJ;\u0010F\u001a\u00020\r2\u0006\u00102\u001a\u00020,2\u0006\u0010>\u001a\u00020?2\u0006\u0010-\u001a\u00020\u00122\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\r0;H\u0002¢\u0006\u0004\bG\u0010HJ$\u0010I\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\b2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\r0;H\u0002J\u0013\u0010J\u001a\u00020K*\u00020\u0005H\u0002¢\u0006\u0004\bL\u0010M¨\u0006N"}, d2 = {"Landroidx/compose/foundation/text/input/internal/HandwritingGestureApi34;", "", "<init>", "()V", "performHandwritingGesture", "", "Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;", "handwritingGesture", "Landroid/view/inputmethod/HandwritingGesture;", "layoutState", "Landroidx/compose/foundation/text/input/internal/TextLayoutState;", "updateSelectionState", "Lkotlin/Function0;", "", "viewConfiguration", "Landroidx/compose/ui/platform/ViewConfiguration;", "performHandwritingGesture$foundation", "previewHandwritingGesture", "", "Landroid/view/inputmethod/PreviewableHandwritingGesture;", "cancellationSignal", "Landroid/os/CancellationSignal;", "previewHandwritingGesture$foundation", "performSelectGesture", "gesture", "Landroid/view/inputmethod/SelectGesture;", "previewSelectGesture", "performDeleteGesture", "Landroid/view/inputmethod/DeleteGesture;", "previewDeleteGesture", "performSelectRangeGesture", "Landroid/view/inputmethod/SelectRangeGesture;", "previewSelectRangeGesture", "performDeleteRangeGesture", "Landroid/view/inputmethod/DeleteRangeGesture;", "previewDeleteRangeGesture", "performJoinOrSplitGesture", "Landroid/view/inputmethod/JoinOrSplitGesture;", "performInsertGesture", "Landroid/view/inputmethod/InsertGesture;", "performRemoveSpaceGesture", "Landroid/view/inputmethod/RemoveSpaceGesture;", "performDeletion", "rangeInTransformedText", "Landroidx/compose/ui/text/TextRange;", "adjustRange", "performDeletion-Sb-Bc2M", "(Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;JZ)V", "fallback", "highlightRange", "range", "type", "Landroidx/compose/foundation/text/input/TextHighlightType;", "highlightRange-XJREzCE", "(Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;JI)V", "Landroidx/compose/foundation/text/LegacyTextFieldState;", "textFieldSelectionManager", "Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "editCommandConsumer", "Lkotlin/Function1;", "Landroidx/compose/ui/text/input/EditCommand;", "textSelectionManager", "text", "Landroidx/compose/ui/text/AnnotatedString;", "performInsertionOnLegacyTextField", "offset", "", "performSelectionOnLegacyTextField", "performSelectionOnLegacyTextField-8ffj60Q", "(JLandroidx/compose/foundation/text/selection/TextFieldSelectionManager;Lkotlin/jvm/functions/Function1;)V", "performDeletionOnLegacyTextField", "performDeletionOnLegacyTextField-vJH6DeI", "(JLandroidx/compose/ui/text/AnnotatedString;ZLkotlin/jvm/functions/Function1;)V", "fallbackOnLegacyTextField", "toTextGranularity", "Landroidx/compose/ui/text/TextGranularity;", "toTextGranularity-NUwxegE", "(I)I", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class HandwritingGestureApi34 {
    public static final int $stable = 0;
    public static final HandwritingGestureApi34 INSTANCE = new HandwritingGestureApi34();

    private HandwritingGestureApi34() {
    }

    public static CharSequence a(Ref.IntRef intRef, Ref.IntRef intRef2, MatchResult matchResult) {
        if (intRef.element == -1) {
            intRef.element = matchResult.getRange().getFirst();
        }
        intRef2.element = matchResult.getRange().getLast() + 1;
        return "";
    }

    public static CharSequence b(Ref.IntRef intRef, Ref.IntRef intRef2, MatchResult matchResult) {
        if (intRef.element == -1) {
            intRef.element = matchResult.getRange().getFirst();
        }
        intRef2.element = matchResult.getRange().getLast() + 1;
        return "";
    }

    public static void c(TransformedTextFieldState transformedTextFieldState) {
        TextFieldState textFieldState = transformedTextFieldState.textFieldState;
        InputTransformation inputTransformation = transformedTextFieldState.inputTransformation;
        TextFieldEditUndoBehavior textFieldEditUndoBehavior = TextFieldEditUndoBehavior.MergeIfPossible;
        textFieldState.getMainBuffer().getChangeTracker$foundation().clearChanges();
        TextFieldBuffer mainBuffer = textFieldState.getMainBuffer();
        mainBuffer.clearHighlight$foundation();
        transformedTextFieldState.updateWedgeAffinity(mainBuffer);
        textFieldState.commitEditAsUser(inputTransformation, true, textFieldEditUndoBehavior);
    }

    public static void d(TextFieldSelectionManager textFieldSelectionManager) {
        if (textFieldSelectionManager != null) {
            textFieldSelectionManager.clearPreviewHighlight$foundation();
        }
    }

    private final int fallback(TransformedTextFieldState transformedTextFieldState, HandwritingGesture handwritingGesture) {
        TextFieldState textFieldState = transformedTextFieldState.textFieldState;
        InputTransformation inputTransformation = transformedTextFieldState.inputTransformation;
        TextFieldEditUndoBehavior textFieldEditUndoBehavior = TextFieldEditUndoBehavior.MergeIfPossible;
        textFieldState.getMainBuffer().getChangeTracker$foundation().clearChanges();
        TextFieldBuffer mainBuffer = textFieldState.getMainBuffer();
        mainBuffer.clearHighlight$foundation();
        transformedTextFieldState.updateWedgeAffinity(mainBuffer);
        textFieldState.commitEditAsUser(inputTransformation, true, textFieldEditUndoBehavior);
        String fallbackText = handwritingGesture.getFallbackText();
        if (fallbackText == null) {
            return 3;
        }
        TransformedTextFieldState.replaceSelectedText$default(transformedTextFieldState, fallbackText, true, null, false, 12, null);
        return 5;
    }

    private final int fallbackOnLegacyTextField(HandwritingGesture gesture, Function1<? super EditCommand, Unit> editCommandConsumer) {
        String fallbackText = gesture.getFallbackText();
        if (fallbackText == null) {
            return 3;
        }
        editCommandConsumer.invoke(new CommitTextCommand(fallbackText, 1));
        return 5;
    }

    /* JADX INFO: renamed from: highlightRange-XJREzCE, reason: not valid java name */
    private final void m1532highlightRangeXJREzCE(TransformedTextFieldState transformedTextFieldState, long j, int i) {
        if (!TextRange.getCollapsed-impl(j)) {
            transformedTextFieldState.m1610highlightCharsIn7RAjNK8(i, j);
            return;
        }
        TextFieldState textFieldState = transformedTextFieldState.textFieldState;
        InputTransformation inputTransformation = transformedTextFieldState.inputTransformation;
        TextFieldEditUndoBehavior textFieldEditUndoBehavior = TextFieldEditUndoBehavior.MergeIfPossible;
        textFieldState.getMainBuffer().getChangeTracker$foundation().clearChanges();
        TextFieldBuffer mainBuffer = textFieldState.getMainBuffer();
        mainBuffer.clearHighlight$foundation();
        transformedTextFieldState.updateWedgeAffinity(mainBuffer);
        textFieldState.commitEditAsUser(inputTransformation, true, textFieldEditUndoBehavior);
    }

    private final int performDeleteGesture(LegacyTextFieldState legacyTextFieldState, DeleteGesture deleteGesture, AnnotatedString annotatedString, Function1<? super EditCommand, Unit> function1) {
        int iM1536toTextGranularityNUwxegE = m1536toTextGranularityNUwxegE(deleteGesture.getGranularity());
        long jM1553getRangeForScreenRectOH9lIzo = HandwritingGesture_androidKt.m1553getRangeForScreenRectOH9lIzo(legacyTextFieldState, RectHelper_androidKt.toComposeRect(deleteGesture.getDeletionArea()), iM1536toTextGranularityNUwxegE, TextInclusionStrategy.Companion.getContainsCenter());
        if (TextRange.getCollapsed-impl(jM1553getRangeForScreenRectOH9lIzo)) {
            return INSTANCE.fallbackOnLegacyTextField(n56.a(deleteGesture), function1);
        }
        m1534performDeletionOnLegacyTextFieldvJH6DeI(jM1553getRangeForScreenRectOH9lIzo, annotatedString, TextGranularity.equals-impl0(iM1536toTextGranularityNUwxegE, TextGranularity.Companion.getWord-DRrd7Zo()), function1);
        return 1;
    }

    private final int performDeleteRangeGesture(LegacyTextFieldState legacyTextFieldState, DeleteRangeGesture deleteRangeGesture, AnnotatedString annotatedString, Function1<? super EditCommand, Unit> function1) {
        int iM1536toTextGranularityNUwxegE = m1536toTextGranularityNUwxegE(deleteRangeGesture.getGranularity());
        long jM1555getRangeForScreenRectsO048IG0 = HandwritingGesture_androidKt.m1555getRangeForScreenRectsO048IG0(legacyTextFieldState, RectHelper_androidKt.toComposeRect(deleteRangeGesture.getDeletionStartArea()), RectHelper_androidKt.toComposeRect(deleteRangeGesture.getDeletionEndArea()), iM1536toTextGranularityNUwxegE, TextInclusionStrategy.Companion.getContainsCenter());
        if (TextRange.getCollapsed-impl(jM1555getRangeForScreenRectsO048IG0)) {
            return INSTANCE.fallbackOnLegacyTextField(n56.a(deleteRangeGesture), function1);
        }
        m1534performDeletionOnLegacyTextFieldvJH6DeI(jM1555getRangeForScreenRectsO048IG0, annotatedString, TextGranularity.equals-impl0(iM1536toTextGranularityNUwxegE, TextGranularity.Companion.getWord-DRrd7Zo()), function1);
        return 1;
    }

    /* JADX INFO: renamed from: performDeletion-Sb-Bc2M, reason: not valid java name */
    private final void m1533performDeletionSbBc2M(TransformedTextFieldState transformedTextFieldState, long j, boolean z) {
        if (z) {
            j = HandwritingGesture_androidKt.m1545adjustHandwritingDeleteGestureRange72CqOWE(j, transformedTextFieldState.getVisualText());
        }
        TransformedTextFieldState.m1609replaceTextM8tDOmk$default(transformedTextFieldState, "", j, null, false, 12, null);
    }

    /* JADX INFO: renamed from: performDeletionOnLegacyTextField-vJH6DeI, reason: not valid java name */
    private final void m1534performDeletionOnLegacyTextFieldvJH6DeI(long range, AnnotatedString text, boolean adjustRange, Function1<? super EditCommand, Unit> editCommandConsumer) {
        if (adjustRange) {
            range = HandwritingGesture_androidKt.m1545adjustHandwritingDeleteGestureRange72CqOWE(range, text);
        }
        editCommandConsumer.invoke(HandwritingGesture_androidKt.compoundEditCommand(new SetSelectionCommand(TextRange.getEnd-impl(range), TextRange.getEnd-impl(range)), new DeleteSurroundingTextCommand(TextRange.getLength-impl(range), 0)));
    }

    private final int performInsertGesture(LegacyTextFieldState legacyTextFieldState, InsertGesture insertGesture, ViewConfiguration viewConfiguration, Function1<? super EditCommand, Unit> function1) {
        TextLayoutResultProxy layoutResult;
        TextLayoutResult value;
        if (viewConfiguration == null) {
            return fallbackOnLegacyTextField(n56.a(insertGesture), function1);
        }
        int iM1548getOffsetForHandwritingGestured4ec7I = HandwritingGesture_androidKt.m1548getOffsetForHandwritingGestured4ec7I(legacyTextFieldState, HandwritingGesture_androidKt.toOffset(insertGesture.getInsertionPoint()), viewConfiguration);
        if (iM1548getOffsetForHandwritingGestured4ec7I == -1 || !((layoutResult = legacyTextFieldState.getLayoutResult()) == null || (value = layoutResult.getValue()) == null || !HandwritingGesture_androidKt.isBiDiBoundary(value, iM1548getOffsetForHandwritingGestured4ec7I))) {
            return fallbackOnLegacyTextField(n56.a(insertGesture), function1);
        }
        performInsertionOnLegacyTextField(iM1548getOffsetForHandwritingGestured4ec7I, insertGesture.getTextToInsert(), function1);
        return 1;
    }

    private final void performInsertionOnLegacyTextField(int offset, String text, Function1<? super EditCommand, Unit> editCommandConsumer) {
        editCommandConsumer.invoke(HandwritingGesture_androidKt.compoundEditCommand(new SetSelectionCommand(offset, offset), new CommitTextCommand(text, 1)));
    }

    private final int performJoinOrSplitGesture(LegacyTextFieldState legacyTextFieldState, JoinOrSplitGesture joinOrSplitGesture, AnnotatedString annotatedString, ViewConfiguration viewConfiguration, Function1<? super EditCommand, Unit> function1) {
        TextLayoutResultProxy layoutResult;
        TextLayoutResult value;
        if (viewConfiguration == null) {
            return fallbackOnLegacyTextField(n56.a(joinOrSplitGesture), function1);
        }
        int iM1548getOffsetForHandwritingGestured4ec7I = HandwritingGesture_androidKt.m1548getOffsetForHandwritingGestured4ec7I(legacyTextFieldState, HandwritingGesture_androidKt.toOffset(joinOrSplitGesture.getJoinOrSplitPoint()), viewConfiguration);
        if (iM1548getOffsetForHandwritingGestured4ec7I == -1 || !((layoutResult = legacyTextFieldState.getLayoutResult()) == null || (value = layoutResult.getValue()) == null || !HandwritingGesture_androidKt.isBiDiBoundary(value, iM1548getOffsetForHandwritingGestured4ec7I))) {
            return fallbackOnLegacyTextField(n56.a(joinOrSplitGesture), function1);
        }
        long jRangeOfWhitespaces = HandwritingGesture_androidKt.rangeOfWhitespaces(annotatedString, iM1548getOffsetForHandwritingGestured4ec7I);
        if (TextRange.getCollapsed-impl(jRangeOfWhitespaces)) {
            performInsertionOnLegacyTextField(TextRange.getStart-impl(jRangeOfWhitespaces), " ", function1);
        } else {
            m1534performDeletionOnLegacyTextFieldvJH6DeI(jRangeOfWhitespaces, annotatedString, false, function1);
        }
        return 1;
    }

    private final int performRemoveSpaceGesture(LegacyTextFieldState legacyTextFieldState, RemoveSpaceGesture removeSpaceGesture, AnnotatedString annotatedString, ViewConfiguration viewConfiguration, Function1<? super EditCommand, Unit> function1) {
        TextLayoutResultProxy layoutResult = legacyTextFieldState.getLayoutResult();
        long jM1551getRangeForRemoveSpaceGesture5iVPX68 = HandwritingGesture_androidKt.m1551getRangeForRemoveSpaceGesture5iVPX68(layoutResult != null ? layoutResult.getValue() : null, HandwritingGesture_androidKt.toOffset(removeSpaceGesture.getStartPoint()), HandwritingGesture_androidKt.toOffset(removeSpaceGesture.getEndPoint()), legacyTextFieldState.getLayoutCoordinates(), viewConfiguration);
        if (TextRange.getCollapsed-impl(jM1551getRangeForRemoveSpaceGesture5iVPX68)) {
            return INSTANCE.fallbackOnLegacyTextField(n56.a(removeSpaceGesture), function1);
        }
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = -1;
        final Ref.IntRef intRef2 = new Ref.IntRef();
        intRef2.element = -1;
        String strReplace = new Regex("\\s+").replace(TextRangeKt.substring-FDrldGo(annotatedString, jM1551getRangeForRemoveSpaceGesture5iVPX68), new Function1() { // from class: s56
            public final Object invoke(Object obj) {
                return HandwritingGestureApi34.b(intRef, intRef2, (MatchResult) obj);
            }
        });
        if (intRef.element == -1 || intRef2.element == -1) {
            return fallbackOnLegacyTextField(n56.a(removeSpaceGesture), function1);
        }
        function1.invoke(HandwritingGesture_androidKt.compoundEditCommand(new SetSelectionCommand(TextRange.getStart-impl(jM1551getRangeForRemoveSpaceGesture5iVPX68) + intRef.element, TextRange.getStart-impl(jM1551getRangeForRemoveSpaceGesture5iVPX68) + intRef2.element), new CommitTextCommand(strReplace.substring(intRef.element, strReplace.length() - (TextRange.getLength-impl(jM1551getRangeForRemoveSpaceGesture5iVPX68) - intRef2.element)), 1)));
        return 1;
    }

    private final int performSelectGesture(TransformedTextFieldState transformedTextFieldState, SelectGesture selectGesture, TextLayoutState textLayoutState, Function0<Unit> function0) {
        long jM1554getRangeForScreenRectOH9lIzo = HandwritingGesture_androidKt.m1554getRangeForScreenRectOH9lIzo(textLayoutState, RectHelper_androidKt.toComposeRect(selectGesture.getSelectionArea()), m1536toTextGranularityNUwxegE(selectGesture.getGranularity()), TextInclusionStrategy.Companion.getContainsCenter());
        if (TextRange.getCollapsed-impl(jM1554getRangeForScreenRectOH9lIzo)) {
            return INSTANCE.fallback(transformedTextFieldState, n56.a(selectGesture));
        }
        transformedTextFieldState.m1616selectCharsIn5zctL8(jM1554getRangeForScreenRectOH9lIzo);
        if (function0 == null) {
            return 1;
        }
        function0.invoke();
        return 1;
    }

    private final int performSelectRangeGesture(TransformedTextFieldState transformedTextFieldState, SelectRangeGesture selectRangeGesture, TextLayoutState textLayoutState, Function0<Unit> function0) {
        long jM1556getRangeForScreenRectsO048IG0 = HandwritingGesture_androidKt.m1556getRangeForScreenRectsO048IG0(textLayoutState, RectHelper_androidKt.toComposeRect(selectRangeGesture.getSelectionStartArea()), RectHelper_androidKt.toComposeRect(selectRangeGesture.getSelectionEndArea()), m1536toTextGranularityNUwxegE(selectRangeGesture.getGranularity()), TextInclusionStrategy.Companion.getContainsCenter());
        if (TextRange.getCollapsed-impl(jM1556getRangeForScreenRectsO048IG0)) {
            return INSTANCE.fallback(transformedTextFieldState, n56.a(selectRangeGesture));
        }
        transformedTextFieldState.m1616selectCharsIn5zctL8(jM1556getRangeForScreenRectsO048IG0);
        if (function0 == null) {
            return 1;
        }
        function0.invoke();
        return 1;
    }

    /* JADX INFO: renamed from: performSelectionOnLegacyTextField-8ffj60Q, reason: not valid java name */
    private final void m1535performSelectionOnLegacyTextField8ffj60Q(long range, TextFieldSelectionManager textSelectionManager, Function1<? super EditCommand, Unit> editCommandConsumer) {
        editCommandConsumer.invoke(new SetSelectionCommand(TextRange.getStart-impl(range), TextRange.getEnd-impl(range)));
        if (textSelectionManager != null) {
            textSelectionManager.enterSelectionMode$foundation(true);
        }
    }

    private final void previewDeleteGesture(TransformedTextFieldState transformedTextFieldState, DeleteGesture deleteGesture, TextLayoutState textLayoutState) {
        m1532highlightRangeXJREzCE(transformedTextFieldState, HandwritingGesture_androidKt.m1554getRangeForScreenRectOH9lIzo(textLayoutState, RectHelper_androidKt.toComposeRect(deleteGesture.getDeletionArea()), m1536toTextGranularityNUwxegE(deleteGesture.getGranularity()), TextInclusionStrategy.Companion.getContainsCenter()), TextHighlightType.INSTANCE.m1502getHandwritingDeletePreviewsxJuwY());
    }

    private final void previewDeleteRangeGesture(TransformedTextFieldState transformedTextFieldState, DeleteRangeGesture deleteRangeGesture, TextLayoutState textLayoutState) {
        m1532highlightRangeXJREzCE(transformedTextFieldState, HandwritingGesture_androidKt.m1556getRangeForScreenRectsO048IG0(textLayoutState, RectHelper_androidKt.toComposeRect(deleteRangeGesture.getDeletionStartArea()), RectHelper_androidKt.toComposeRect(deleteRangeGesture.getDeletionEndArea()), m1536toTextGranularityNUwxegE(deleteRangeGesture.getGranularity()), TextInclusionStrategy.Companion.getContainsCenter()), TextHighlightType.INSTANCE.m1502getHandwritingDeletePreviewsxJuwY());
    }

    private final void previewSelectGesture(TransformedTextFieldState transformedTextFieldState, SelectGesture selectGesture, TextLayoutState textLayoutState) {
        m1532highlightRangeXJREzCE(transformedTextFieldState, HandwritingGesture_androidKt.m1554getRangeForScreenRectOH9lIzo(textLayoutState, RectHelper_androidKt.toComposeRect(selectGesture.getSelectionArea()), m1536toTextGranularityNUwxegE(selectGesture.getGranularity()), TextInclusionStrategy.Companion.getContainsCenter()), TextHighlightType.INSTANCE.m1503getHandwritingSelectPreviewsxJuwY());
    }

    private final void previewSelectRangeGesture(TransformedTextFieldState transformedTextFieldState, SelectRangeGesture selectRangeGesture, TextLayoutState textLayoutState) {
        m1532highlightRangeXJREzCE(transformedTextFieldState, HandwritingGesture_androidKt.m1556getRangeForScreenRectsO048IG0(textLayoutState, RectHelper_androidKt.toComposeRect(selectRangeGesture.getSelectionStartArea()), RectHelper_androidKt.toComposeRect(selectRangeGesture.getSelectionEndArea()), m1536toTextGranularityNUwxegE(selectRangeGesture.getGranularity()), TextInclusionStrategy.Companion.getContainsCenter()), TextHighlightType.INSTANCE.m1503getHandwritingSelectPreviewsxJuwY());
    }

    /* JADX INFO: renamed from: toTextGranularity-NUwxegE, reason: not valid java name */
    private final int m1536toTextGranularityNUwxegE(int i) {
        if (i != 1) {
            return i != 2 ? TextGranularity.Companion.getCharacter-DRrd7Zo() : TextGranularity.Companion.getCharacter-DRrd7Zo();
        }
        return TextGranularity.Companion.getWord-DRrd7Zo();
    }

    public final int performHandwritingGesture$foundation(LegacyTextFieldState legacyTextFieldState, HandwritingGesture handwritingGesture, TextFieldSelectionManager textFieldSelectionManager, ViewConfiguration viewConfiguration, Function1<? super EditCommand, Unit> function1) {
        TextLayoutResult value;
        TextLayoutInput layoutInput;
        AnnotatedString untransformedText = legacyTextFieldState.getUntransformedText();
        if (untransformedText == null) {
            return 3;
        }
        TextLayoutResultProxy layoutResult = legacyTextFieldState.getLayoutResult();
        if (!Intrinsics.areEqual(untransformedText, (layoutResult == null || (value = layoutResult.getValue()) == null || (layoutInput = value.getLayoutInput()) == null) ? null : layoutInput.getText())) {
            return 3;
        }
        if (o46.a(handwritingGesture)) {
            return performSelectGesture(legacyTextFieldState, t46.a(handwritingGesture), textFieldSelectionManager, function1);
        }
        if (u46.a(handwritingGesture)) {
            return performDeleteGesture(legacyTextFieldState, v46.a(handwritingGesture), untransformedText, function1);
        }
        if (x46.a(handwritingGesture)) {
            return performSelectRangeGesture(legacyTextFieldState, y46.a(handwritingGesture), textFieldSelectionManager, function1);
        }
        if (z46.a(handwritingGesture)) {
            return performDeleteRangeGesture(legacyTextFieldState, a56.a(handwritingGesture), untransformedText, function1);
        }
        if (b56.a(handwritingGesture)) {
            return performJoinOrSplitGesture(legacyTextFieldState, c56.a(handwritingGesture), untransformedText, viewConfiguration, function1);
        }
        if (p46.a(handwritingGesture)) {
            return performInsertGesture(legacyTextFieldState, q46.a(handwritingGesture), viewConfiguration, function1);
        }
        if (r46.a(handwritingGesture)) {
            return performRemoveSpaceGesture(legacyTextFieldState, s46.a(handwritingGesture), untransformedText, viewConfiguration, function1);
        }
        return 2;
    }

    public final boolean previewHandwritingGesture$foundation(LegacyTextFieldState legacyTextFieldState, PreviewableHandwritingGesture previewableHandwritingGesture, final TextFieldSelectionManager textFieldSelectionManager, CancellationSignal cancellationSignal) {
        TextLayoutResult value;
        TextLayoutInput layoutInput;
        AnnotatedString untransformedText = legacyTextFieldState.getUntransformedText();
        if (untransformedText == null) {
            return false;
        }
        TextLayoutResultProxy layoutResult = legacyTextFieldState.getLayoutResult();
        if (!Intrinsics.areEqual(untransformedText, (layoutResult == null || (value = layoutResult.getValue()) == null || (layoutInput = value.getLayoutInput()) == null) ? null : layoutInput.getText())) {
            return false;
        }
        if (o46.a(previewableHandwritingGesture)) {
            previewSelectGesture(legacyTextFieldState, t46.a(previewableHandwritingGesture), textFieldSelectionManager);
        } else if (u46.a(previewableHandwritingGesture)) {
            previewDeleteGesture(legacyTextFieldState, v46.a(previewableHandwritingGesture), textFieldSelectionManager);
        } else if (x46.a(previewableHandwritingGesture)) {
            previewSelectRangeGesture(legacyTextFieldState, y46.a(previewableHandwritingGesture), textFieldSelectionManager);
        } else {
            if (!z46.a(previewableHandwritingGesture)) {
                return false;
            }
            previewDeleteRangeGesture(legacyTextFieldState, a56.a(previewableHandwritingGesture), textFieldSelectionManager);
        }
        if (cancellationSignal == null) {
            return true;
        }
        cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: t56
            @Override // android.os.CancellationSignal.OnCancelListener
            public final void onCancel() {
                HandwritingGestureApi34.d(textFieldSelectionManager);
            }
        });
        return true;
    }

    private final void previewDeleteGesture(LegacyTextFieldState legacyTextFieldState, DeleteGesture deleteGesture, TextFieldSelectionManager textFieldSelectionManager) {
        if (textFieldSelectionManager != null) {
            textFieldSelectionManager.m1822setDeletionPreviewHighlight5zctL8$foundation(HandwritingGesture_androidKt.m1553getRangeForScreenRectOH9lIzo(legacyTextFieldState, RectHelper_androidKt.toComposeRect(deleteGesture.getDeletionArea()), m1536toTextGranularityNUwxegE(deleteGesture.getGranularity()), TextInclusionStrategy.Companion.getContainsCenter()));
        }
    }

    private final void previewSelectGesture(LegacyTextFieldState legacyTextFieldState, SelectGesture selectGesture, TextFieldSelectionManager textFieldSelectionManager) {
        if (textFieldSelectionManager != null) {
            textFieldSelectionManager.m1824setSelectionPreviewHighlight5zctL8$foundation(HandwritingGesture_androidKt.m1553getRangeForScreenRectOH9lIzo(legacyTextFieldState, RectHelper_androidKt.toComposeRect(selectGesture.getSelectionArea()), m1536toTextGranularityNUwxegE(selectGesture.getGranularity()), TextInclusionStrategy.Companion.getContainsCenter()));
        }
    }

    private final void previewDeleteRangeGesture(LegacyTextFieldState legacyTextFieldState, DeleteRangeGesture deleteRangeGesture, TextFieldSelectionManager textFieldSelectionManager) {
        if (textFieldSelectionManager != null) {
            textFieldSelectionManager.m1822setDeletionPreviewHighlight5zctL8$foundation(HandwritingGesture_androidKt.m1555getRangeForScreenRectsO048IG0(legacyTextFieldState, RectHelper_androidKt.toComposeRect(deleteRangeGesture.getDeletionStartArea()), RectHelper_androidKt.toComposeRect(deleteRangeGesture.getDeletionEndArea()), m1536toTextGranularityNUwxegE(deleteRangeGesture.getGranularity()), TextInclusionStrategy.Companion.getContainsCenter()));
        }
    }

    private final void previewSelectRangeGesture(LegacyTextFieldState legacyTextFieldState, SelectRangeGesture selectRangeGesture, TextFieldSelectionManager textFieldSelectionManager) {
        if (textFieldSelectionManager != null) {
            textFieldSelectionManager.m1824setSelectionPreviewHighlight5zctL8$foundation(HandwritingGesture_androidKt.m1555getRangeForScreenRectsO048IG0(legacyTextFieldState, RectHelper_androidKt.toComposeRect(selectRangeGesture.getSelectionStartArea()), RectHelper_androidKt.toComposeRect(selectRangeGesture.getSelectionEndArea()), m1536toTextGranularityNUwxegE(selectRangeGesture.getGranularity()), TextInclusionStrategy.Companion.getContainsCenter()));
        }
    }

    private final int performSelectGesture(LegacyTextFieldState legacyTextFieldState, SelectGesture selectGesture, TextFieldSelectionManager textFieldSelectionManager, Function1<? super EditCommand, Unit> function1) {
        long jM1553getRangeForScreenRectOH9lIzo = HandwritingGesture_androidKt.m1553getRangeForScreenRectOH9lIzo(legacyTextFieldState, RectHelper_androidKt.toComposeRect(selectGesture.getSelectionArea()), m1536toTextGranularityNUwxegE(selectGesture.getGranularity()), TextInclusionStrategy.Companion.getContainsCenter());
        if (TextRange.getCollapsed-impl(jM1553getRangeForScreenRectOH9lIzo)) {
            return INSTANCE.fallbackOnLegacyTextField(n56.a(selectGesture), function1);
        }
        m1535performSelectionOnLegacyTextField8ffj60Q(jM1553getRangeForScreenRectOH9lIzo, textFieldSelectionManager, function1);
        return 1;
    }

    private final int performDeleteGesture(TransformedTextFieldState transformedTextFieldState, DeleteGesture deleteGesture, TextLayoutState textLayoutState) {
        int iM1536toTextGranularityNUwxegE = m1536toTextGranularityNUwxegE(deleteGesture.getGranularity());
        long jM1554getRangeForScreenRectOH9lIzo = HandwritingGesture_androidKt.m1554getRangeForScreenRectOH9lIzo(textLayoutState, RectHelper_androidKt.toComposeRect(deleteGesture.getDeletionArea()), iM1536toTextGranularityNUwxegE, TextInclusionStrategy.Companion.getContainsCenter());
        if (TextRange.getCollapsed-impl(jM1554getRangeForScreenRectOH9lIzo)) {
            return INSTANCE.fallback(transformedTextFieldState, n56.a(deleteGesture));
        }
        m1533performDeletionSbBc2M(transformedTextFieldState, jM1554getRangeForScreenRectOH9lIzo, TextGranularity.equals-impl0(iM1536toTextGranularityNUwxegE, TextGranularity.Companion.getWord-DRrd7Zo()));
        return 1;
    }

    private final int performSelectRangeGesture(LegacyTextFieldState legacyTextFieldState, SelectRangeGesture selectRangeGesture, TextFieldSelectionManager textFieldSelectionManager, Function1<? super EditCommand, Unit> function1) {
        long jM1555getRangeForScreenRectsO048IG0 = HandwritingGesture_androidKt.m1555getRangeForScreenRectsO048IG0(legacyTextFieldState, RectHelper_androidKt.toComposeRect(selectRangeGesture.getSelectionStartArea()), RectHelper_androidKt.toComposeRect(selectRangeGesture.getSelectionEndArea()), m1536toTextGranularityNUwxegE(selectRangeGesture.getGranularity()), TextInclusionStrategy.Companion.getContainsCenter());
        if (TextRange.getCollapsed-impl(jM1555getRangeForScreenRectsO048IG0)) {
            return INSTANCE.fallbackOnLegacyTextField(n56.a(selectRangeGesture), function1);
        }
        m1535performSelectionOnLegacyTextField8ffj60Q(jM1555getRangeForScreenRectsO048IG0, textFieldSelectionManager, function1);
        return 1;
    }

    private final int performInsertGesture(TransformedTextFieldState transformedTextFieldState, InsertGesture insertGesture, TextLayoutState textLayoutState, ViewConfiguration viewConfiguration) {
        int iM1549getOffsetForHandwritingGestured4ec7I = HandwritingGesture_androidKt.m1549getOffsetForHandwritingGestured4ec7I(textLayoutState, HandwritingGesture_androidKt.toOffset(insertGesture.getInsertionPoint()), viewConfiguration);
        if (iM1549getOffsetForHandwritingGestured4ec7I == -1) {
            return fallback(transformedTextFieldState, n56.a(insertGesture));
        }
        TransformedTextFieldState.m1609replaceTextM8tDOmk$default(transformedTextFieldState, insertGesture.getTextToInsert(), TextRangeKt.TextRange(iM1549getOffsetForHandwritingGestured4ec7I), null, false, 12, null);
        return 1;
    }

    private final int performDeleteRangeGesture(TransformedTextFieldState transformedTextFieldState, DeleteRangeGesture deleteRangeGesture, TextLayoutState textLayoutState) {
        int iM1536toTextGranularityNUwxegE = m1536toTextGranularityNUwxegE(deleteRangeGesture.getGranularity());
        long jM1556getRangeForScreenRectsO048IG0 = HandwritingGesture_androidKt.m1556getRangeForScreenRectsO048IG0(textLayoutState, RectHelper_androidKt.toComposeRect(deleteRangeGesture.getDeletionStartArea()), RectHelper_androidKt.toComposeRect(deleteRangeGesture.getDeletionEndArea()), iM1536toTextGranularityNUwxegE, TextInclusionStrategy.Companion.getContainsCenter());
        if (TextRange.getCollapsed-impl(jM1556getRangeForScreenRectsO048IG0)) {
            return INSTANCE.fallback(transformedTextFieldState, n56.a(deleteRangeGesture));
        }
        m1533performDeletionSbBc2M(transformedTextFieldState, jM1556getRangeForScreenRectsO048IG0, TextGranularity.equals-impl0(iM1536toTextGranularityNUwxegE, TextGranularity.Companion.getWord-DRrd7Zo()));
        return 1;
    }

    private final int performJoinOrSplitGesture(TransformedTextFieldState transformedTextFieldState, JoinOrSplitGesture joinOrSplitGesture, TextLayoutState textLayoutState, ViewConfiguration viewConfiguration) {
        TextLayoutResult layoutResult;
        if (transformedTextFieldState.getOutputText() != transformedTextFieldState.getUntransformedText()) {
            return 3;
        }
        int iM1549getOffsetForHandwritingGestured4ec7I = HandwritingGesture_androidKt.m1549getOffsetForHandwritingGestured4ec7I(textLayoutState, HandwritingGesture_androidKt.toOffset(joinOrSplitGesture.getJoinOrSplitPoint()), viewConfiguration);
        if (iM1549getOffsetForHandwritingGestured4ec7I != -1 && ((layoutResult = textLayoutState.getLayoutResult()) == null || !HandwritingGesture_androidKt.isBiDiBoundary(layoutResult, iM1549getOffsetForHandwritingGestured4ec7I))) {
            long jRangeOfWhitespaces = HandwritingGesture_androidKt.rangeOfWhitespaces(transformedTextFieldState.getVisualText(), iM1549getOffsetForHandwritingGestured4ec7I);
            if (TextRange.getCollapsed-impl(jRangeOfWhitespaces)) {
                TransformedTextFieldState.m1609replaceTextM8tDOmk$default(transformedTextFieldState, " ", jRangeOfWhitespaces, null, false, 12, null);
            } else {
                m1533performDeletionSbBc2M(transformedTextFieldState, jRangeOfWhitespaces, false);
            }
            return 1;
        }
        return fallback(transformedTextFieldState, n56.a(joinOrSplitGesture));
    }

    public final boolean previewHandwritingGesture$foundation(final TransformedTextFieldState transformedTextFieldState, PreviewableHandwritingGesture previewableHandwritingGesture, TextLayoutState textLayoutState, CancellationSignal cancellationSignal) {
        if (o46.a(previewableHandwritingGesture)) {
            previewSelectGesture(transformedTextFieldState, t46.a(previewableHandwritingGesture), textLayoutState);
        } else if (u46.a(previewableHandwritingGesture)) {
            previewDeleteGesture(transformedTextFieldState, v46.a(previewableHandwritingGesture), textLayoutState);
        } else if (x46.a(previewableHandwritingGesture)) {
            previewSelectRangeGesture(transformedTextFieldState, y46.a(previewableHandwritingGesture), textLayoutState);
        } else {
            if (!z46.a(previewableHandwritingGesture)) {
                return false;
            }
            previewDeleteRangeGesture(transformedTextFieldState, a56.a(previewableHandwritingGesture), textLayoutState);
        }
        if (cancellationSignal == null) {
            return true;
        }
        cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: r56
            @Override // android.os.CancellationSignal.OnCancelListener
            public final void onCancel() {
                HandwritingGestureApi34.c(transformedTextFieldState);
            }
        });
        return true;
    }

    public final int performHandwritingGesture$foundation(TransformedTextFieldState transformedTextFieldState, HandwritingGesture handwritingGesture, TextLayoutState textLayoutState, Function0<Unit> function0, ViewConfiguration viewConfiguration) {
        if (o46.a(handwritingGesture)) {
            return performSelectGesture(transformedTextFieldState, t46.a(handwritingGesture), textLayoutState, function0);
        }
        if (u46.a(handwritingGesture)) {
            return performDeleteGesture(transformedTextFieldState, v46.a(handwritingGesture), textLayoutState);
        }
        if (x46.a(handwritingGesture)) {
            return performSelectRangeGesture(transformedTextFieldState, y46.a(handwritingGesture), textLayoutState, function0);
        }
        if (z46.a(handwritingGesture)) {
            return performDeleteRangeGesture(transformedTextFieldState, a56.a(handwritingGesture), textLayoutState);
        }
        if (b56.a(handwritingGesture)) {
            return performJoinOrSplitGesture(transformedTextFieldState, c56.a(handwritingGesture), textLayoutState, viewConfiguration);
        }
        if (p46.a(handwritingGesture)) {
            return performInsertGesture(transformedTextFieldState, q46.a(handwritingGesture), textLayoutState, viewConfiguration);
        }
        if (r46.a(handwritingGesture)) {
            return performRemoveSpaceGesture(transformedTextFieldState, s46.a(handwritingGesture), textLayoutState, viewConfiguration);
        }
        return 2;
    }

    private final int performRemoveSpaceGesture(TransformedTextFieldState transformedTextFieldState, RemoveSpaceGesture removeSpaceGesture, TextLayoutState textLayoutState, ViewConfiguration viewConfiguration) {
        long jM1551getRangeForRemoveSpaceGesture5iVPX68 = HandwritingGesture_androidKt.m1551getRangeForRemoveSpaceGesture5iVPX68(textLayoutState.getLayoutResult(), HandwritingGesture_androidKt.toOffset(removeSpaceGesture.getStartPoint()), HandwritingGesture_androidKt.toOffset(removeSpaceGesture.getEndPoint()), textLayoutState.getTextLayoutNodeCoordinates(), viewConfiguration);
        if (TextRange.getCollapsed-impl(jM1551getRangeForRemoveSpaceGesture5iVPX68)) {
            return INSTANCE.fallback(transformedTextFieldState, n56.a(removeSpaceGesture));
        }
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = -1;
        final Ref.IntRef intRef2 = new Ref.IntRef();
        intRef2.element = -1;
        String strReplace = new Regex("\\s+").replace(TextRangeKt.substring-FDrldGo(transformedTextFieldState.getVisualText(), jM1551getRangeForRemoveSpaceGesture5iVPX68), new Function1() { // from class: q56
            public final Object invoke(Object obj) {
                return HandwritingGestureApi34.a(intRef, intRef2, (MatchResult) obj);
            }
        });
        if (intRef.element != -1 && intRef2.element != -1) {
            TransformedTextFieldState.m1609replaceTextM8tDOmk$default(transformedTextFieldState, strReplace.substring(intRef.element, strReplace.length() - (TextRange.getLength-impl(jM1551getRangeForRemoveSpaceGesture5iVPX68) - intRef2.element)), TextRangeKt.TextRange(TextRange.getStart-impl(jM1551getRangeForRemoveSpaceGesture5iVPX68) + intRef.element, TextRange.getStart-impl(jM1551getRangeForRemoveSpaceGesture5iVPX68) + intRef2.element), null, false, 12, null);
            return 1;
        }
        return fallback(transformedTextFieldState, n56.a(removeSpaceGesture));
    }
}
