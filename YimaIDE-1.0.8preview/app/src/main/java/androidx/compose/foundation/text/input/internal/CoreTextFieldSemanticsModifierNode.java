package androidx.compose.foundation.text.input.internal;

import androidx.compose.foundation.text.CoreTextFieldKt;
import androidx.compose.foundation.text.LegacyTextFieldState;
import androidx.compose.foundation.text.TextFieldDelegate;
import androidx.compose.foundation.text.TextLayoutResultProxy;
import androidx.compose.foundation.text.input.internal.CoreTextFieldSemanticsModifierNode;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.ui.autofill.ContentDataType;
import androidx.compose.ui.autofill.ContentType;
import androidx.compose.ui.autofill.FillableData;
import androidx.compose.ui.autofill.FillableData_androidKt;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.SemanticsModifierNodeKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.input.CommitTextCommand;
import androidx.compose.ui.text.input.DeleteAllCommand;
import androidx.compose.ui.text.input.EditCommand;
import androidx.compose.ui.text.input.FinishComposingTextCommand;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.ImeOptions;
import androidx.compose.ui.text.input.KeyboardType;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.TransformedText;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b(\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0001\u0018\u00002\u00020\u00012\u00020\u0002BW\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014¢\u0006\u0004\b\u0015\u0010\u0016J\f\u0010<\u001a\u00020=*\u00020>H\u0016JV\u0010?\u001a\u00020=2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J(\u0010@\u001a\u00020=2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010A\u001a\u00020B2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001a\u0010\u000b\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010$\"\u0004\b(\u0010&R\u001a\u0010\f\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010$\"\u0004\b)\u0010&R\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u0014\u0010:\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b;\u0010$¨\u0006C"}, d2 = {"Landroidx/compose/foundation/text/input/internal/CoreTextFieldSemanticsModifierNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/node/SemanticsModifierNode;", "transformedText", "Landroidx/compose/ui/text/input/TransformedText;", "value", "Landroidx/compose/ui/text/input/TextFieldValue;", "state", "Landroidx/compose/foundation/text/LegacyTextFieldState;", "readOnly", "", "enabled", "isPassword", "offsetMapping", "Landroidx/compose/ui/text/input/OffsetMapping;", "manager", "Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "imeOptions", "Landroidx/compose/ui/text/input/ImeOptions;", "focusRequester", "Landroidx/compose/ui/focus/FocusRequester;", "<init>", "(Landroidx/compose/ui/text/input/TransformedText;Landroidx/compose/ui/text/input/TextFieldValue;Landroidx/compose/foundation/text/LegacyTextFieldState;ZZZLandroidx/compose/ui/text/input/OffsetMapping;Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;Landroidx/compose/ui/text/input/ImeOptions;Landroidx/compose/ui/focus/FocusRequester;)V", "getTransformedText", "()Landroidx/compose/ui/text/input/TransformedText;", "setTransformedText", "(Landroidx/compose/ui/text/input/TransformedText;)V", "getValue", "()Landroidx/compose/ui/text/input/TextFieldValue;", "setValue", "(Landroidx/compose/ui/text/input/TextFieldValue;)V", "getState", "()Landroidx/compose/foundation/text/LegacyTextFieldState;", "setState", "(Landroidx/compose/foundation/text/LegacyTextFieldState;)V", "getReadOnly", "()Z", "setReadOnly", "(Z)V", "getEnabled", "setEnabled", "setPassword", "getOffsetMapping", "()Landroidx/compose/ui/text/input/OffsetMapping;", "setOffsetMapping", "(Landroidx/compose/ui/text/input/OffsetMapping;)V", "getManager", "()Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "setManager", "(Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;)V", "getImeOptions", "()Landroidx/compose/ui/text/input/ImeOptions;", "setImeOptions", "(Landroidx/compose/ui/text/input/ImeOptions;)V", "getFocusRequester", "()Landroidx/compose/ui/focus/FocusRequester;", "setFocusRequester", "(Landroidx/compose/ui/focus/FocusRequester;)V", "shouldMergeDescendantSemantics", "getShouldMergeDescendantSemantics", "applySemantics", "", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "updateNodeSemantics", "handleTextUpdateFromSemantics", "text", "", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class CoreTextFieldSemanticsModifierNode extends DelegatingNode implements SemanticsModifierNode {
    public static final int $stable = 8;
    private boolean enabled;
    private FocusRequester focusRequester;
    private ImeOptions imeOptions;
    private boolean isPassword;
    private TextFieldSelectionManager manager;
    private OffsetMapping offsetMapping;
    private boolean readOnly;
    private LegacyTextFieldState state;
    private TransformedText transformedText;
    private TextFieldValue value;

    public CoreTextFieldSemanticsModifierNode(TransformedText transformedText, TextFieldValue textFieldValue, LegacyTextFieldState legacyTextFieldState, boolean z, boolean z2, boolean z3, OffsetMapping offsetMapping, TextFieldSelectionManager textFieldSelectionManager, ImeOptions imeOptions, FocusRequester focusRequester) {
        this.transformedText = transformedText;
        this.value = textFieldValue;
        this.state = legacyTextFieldState;
        this.readOnly = z;
        this.enabled = z2;
        this.isPassword = z3;
        this.offsetMapping = offsetMapping;
        this.manager = textFieldSelectionManager;
        this.imeOptions = imeOptions;
        this.focusRequester = focusRequester;
        textFieldSelectionManager.setRequestAutofillAction$foundation(new Function0() { // from class: q03
            public final Object invoke() {
                return CoreTextFieldSemanticsModifierNode.m(this.b);
            }
        });
    }

    public static boolean a(CoreTextFieldSemanticsModifierNode coreTextFieldSemanticsModifierNode) {
        TextFieldSelectionManager.enterSelectionMode$foundation$default(coreTextFieldSemanticsModifierNode.manager, false, 1, null);
        return true;
    }

    public static boolean b(CoreTextFieldSemanticsModifierNode coreTextFieldSemanticsModifierNode, SemanticsPropertyReceiver semanticsPropertyReceiver, AnnotatedString annotatedString) {
        if (coreTextFieldSemanticsModifierNode.readOnly || !coreTextFieldSemanticsModifierNode.enabled) {
            return false;
        }
        androidx.compose.ui.text.input.TextInputSession inputSession = coreTextFieldSemanticsModifierNode.state.getInputSession();
        if (inputSession != null) {
            TextFieldDelegate.INSTANCE.onEditCommand$foundation(CollectionsKt.listOf(new EditCommand[]{new FinishComposingTextCommand(), new CommitTextCommand(annotatedString, 1)}), coreTextFieldSemanticsModifierNode.state.getProcessor(), coreTextFieldSemanticsModifierNode.state.getOnValueChange(), inputSession);
            return true;
        }
        coreTextFieldSemanticsModifierNode.state.getOnValueChange().invoke(new TextFieldValue(StringsKt.replaceRange(coreTextFieldSemanticsModifierNode.value.getText(), TextRange.getStart-impl(coreTextFieldSemanticsModifierNode.value.getSelection-d9O1mEE()), TextRange.getEnd-impl(coreTextFieldSemanticsModifierNode.value.getSelection-d9O1mEE()), annotatedString).toString(), TextRangeKt.TextRange(TextRange.getStart-impl(coreTextFieldSemanticsModifierNode.value.getSelection-d9O1mEE()) + annotatedString.length()), (TextRange) null, 4, (DefaultConstructorMarker) null));
        return true;
    }

    public static boolean c(CoreTextFieldSemanticsModifierNode coreTextFieldSemanticsModifierNode, int i, int i2, boolean z) {
        if (!z) {
            i = coreTextFieldSemanticsModifierNode.offsetMapping.transformedToOriginal(i);
        }
        if (!z) {
            i2 = coreTextFieldSemanticsModifierNode.offsetMapping.transformedToOriginal(i2);
        }
        if (!coreTextFieldSemanticsModifierNode.enabled || (i == TextRange.getStart-impl(coreTextFieldSemanticsModifierNode.value.getSelection-d9O1mEE()) && i2 == TextRange.getEnd-impl(coreTextFieldSemanticsModifierNode.value.getSelection-d9O1mEE()))) {
            return false;
        }
        if (Math.min(i, i2) < 0 || Math.max(i, i2) > coreTextFieldSemanticsModifierNode.value.getAnnotatedString().length()) {
            coreTextFieldSemanticsModifierNode.manager.exitSelectionMode$foundation();
            return false;
        }
        if (z || i == i2) {
            coreTextFieldSemanticsModifierNode.manager.exitSelectionMode$foundation();
        } else {
            TextFieldSelectionManager.enterSelectionMode$foundation$default(coreTextFieldSemanticsModifierNode.manager, false, 1, null);
        }
        coreTextFieldSemanticsModifierNode.state.getOnValueChange().invoke(new TextFieldValue(coreTextFieldSemanticsModifierNode.value.getAnnotatedString(), TextRangeKt.TextRange(i, i2), (TextRange) null, 4, (DefaultConstructorMarker) null));
        return true;
    }

    public static boolean d(CoreTextFieldSemanticsModifierNode coreTextFieldSemanticsModifierNode, AnnotatedString annotatedString) {
        coreTextFieldSemanticsModifierNode.handleTextUpdateFromSemantics(coreTextFieldSemanticsModifierNode.state, annotatedString.getText(), coreTextFieldSemanticsModifierNode.readOnly, coreTextFieldSemanticsModifierNode.enabled);
        return true;
    }

    public static boolean e(CoreTextFieldSemanticsModifierNode coreTextFieldSemanticsModifierNode, FillableData fillableData) {
        coreTextFieldSemanticsModifierNode.state.setJustAutofilled(true);
        coreTextFieldSemanticsModifierNode.state.setAutofillHighlightOn(true);
        LegacyTextFieldState legacyTextFieldState = coreTextFieldSemanticsModifierNode.state;
        CharSequence textValue = fillableData.getTextValue();
        textValue.getClass();
        coreTextFieldSemanticsModifierNode.handleTextUpdateFromSemantics(legacyTextFieldState, (String) textValue, coreTextFieldSemanticsModifierNode.readOnly, coreTextFieldSemanticsModifierNode.enabled);
        return true;
    }

    public static boolean f(CoreTextFieldSemanticsModifierNode coreTextFieldSemanticsModifierNode) {
        coreTextFieldSemanticsModifierNode.state.getOnImeActionPerformed().invoke(ImeAction.box-impl(coreTextFieldSemanticsModifierNode.imeOptions.getImeAction()));
        return true;
    }

    public static Unit g(CoreTextFieldSemanticsModifierNode coreTextFieldSemanticsModifierNode) {
        DelegatableNodeKt.requestAutofill(coreTextFieldSemanticsModifierNode);
        return Unit.INSTANCE;
    }

    public static boolean h(CoreTextFieldSemanticsModifierNode coreTextFieldSemanticsModifierNode) {
        coreTextFieldSemanticsModifierNode.manager.cut$foundation();
        return true;
    }

    private final void handleTextUpdateFromSemantics(LegacyTextFieldState state, String text, boolean readOnly, boolean enabled) {
        if (readOnly || !enabled) {
            return;
        }
        androidx.compose.ui.text.input.TextInputSession inputSession = state.getInputSession();
        if (inputSession != null) {
            TextFieldDelegate.INSTANCE.onEditCommand$foundation(CollectionsKt.listOf(new EditCommand[]{new DeleteAllCommand(), new CommitTextCommand(text, 1)}), state.getProcessor(), state.getOnValueChange(), inputSession);
        } else {
            state.getOnValueChange().invoke(new TextFieldValue(text, TextRangeKt.TextRange(text.length()), (TextRange) null, 4, (DefaultConstructorMarker) null));
        }
    }

    public static boolean i(CoreTextFieldSemanticsModifierNode coreTextFieldSemanticsModifierNode) {
        coreTextFieldSemanticsModifierNode.manager.paste$foundation();
        return true;
    }

    public static boolean j(CoreTextFieldSemanticsModifierNode coreTextFieldSemanticsModifierNode) {
        TextFieldSelectionManager.copy$foundation$default(coreTextFieldSemanticsModifierNode.manager, false, 1, null);
        return true;
    }

    public static boolean k(CoreTextFieldSemanticsModifierNode coreTextFieldSemanticsModifierNode) {
        CoreTextFieldKt.tapToFocus(coreTextFieldSemanticsModifierNode.state, coreTextFieldSemanticsModifierNode.focusRequester, !coreTextFieldSemanticsModifierNode.readOnly);
        return true;
    }

    public static boolean l(CoreTextFieldSemanticsModifierNode coreTextFieldSemanticsModifierNode, List list) {
        if (coreTextFieldSemanticsModifierNode.state.getLayoutResult() == null) {
            return false;
        }
        TextLayoutResultProxy layoutResult = coreTextFieldSemanticsModifierNode.state.getLayoutResult();
        layoutResult.getClass();
        list.add(layoutResult.getValue());
        return true;
    }

    public static Unit m(CoreTextFieldSemanticsModifierNode coreTextFieldSemanticsModifierNode) {
        DelegatableNodeKt.requestAutofill(coreTextFieldSemanticsModifierNode);
        return Unit.INSTANCE;
    }

    public void applySemantics(final SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.setInputText(semanticsPropertyReceiver, this.value.getAnnotatedString());
        SemanticsPropertiesKt.setEditableText(semanticsPropertyReceiver, this.transformedText.getText());
        SemanticsPropertiesKt.setTextSelectionRange-FDrldGo(semanticsPropertyReceiver, this.value.getSelection-d9O1mEE());
        SemanticsPropertiesKt.setContentDataType(semanticsPropertyReceiver, ContentDataType.Companion.getText());
        FillableData fillableDataCreateFromText = FillableData_androidKt.createFromText(FillableData.Companion, this.value.getAnnotatedString());
        if (fillableDataCreateFromText != null) {
            SemanticsPropertiesKt.setFillableData(semanticsPropertyReceiver, fillableDataCreateFromText);
        }
        SemanticsPropertiesKt.onFillData$default(semanticsPropertyReceiver, (String) null, new Function1() { // from class: r03
            public final Object invoke(Object obj) {
                return Boolean.valueOf(CoreTextFieldSemanticsModifierNode.e(this.b, (FillableData) obj));
            }
        }, 1, (Object) null);
        int keyboardType = this.imeOptions.getKeyboardType();
        KeyboardType.Companion companion = KeyboardType.Companion;
        if (KeyboardType.equals-impl0(keyboardType, companion.getEmail-PjHm6EE())) {
            SemanticsPropertiesKt.setContentType(semanticsPropertyReceiver, ContentType.Companion.getEmailAddress());
        } else if (KeyboardType.equals-impl0(keyboardType, companion.getPassword-PjHm6EE()) || KeyboardType.equals-impl0(keyboardType, companion.getNumberPassword-PjHm6EE())) {
            SemanticsPropertiesKt.setContentType(semanticsPropertyReceiver, ContentType.Companion.getPassword());
        } else if (KeyboardType.equals-impl0(keyboardType, companion.getPhone-PjHm6EE())) {
            SemanticsPropertiesKt.setContentType(semanticsPropertyReceiver, ContentType.Companion.getPhoneNumber());
        }
        if (!this.enabled) {
            SemanticsPropertiesKt.disabled(semanticsPropertyReceiver);
        }
        if (this.isPassword) {
            SemanticsPropertiesKt.password(semanticsPropertyReceiver);
        }
        boolean z = this.enabled && !this.readOnly;
        SemanticsPropertiesKt.setEditable(semanticsPropertyReceiver, z);
        SemanticsPropertiesKt.getTextLayoutResult$default(semanticsPropertyReceiver, (String) null, new Function1() { // from class: t03
            public final Object invoke(Object obj) {
                return Boolean.valueOf(CoreTextFieldSemanticsModifierNode.l(this.b, (List) obj));
            }
        }, 1, (Object) null);
        if (z) {
            SemanticsPropertiesKt.setText$default(semanticsPropertyReceiver, (String) null, new Function1() { // from class: u03
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(CoreTextFieldSemanticsModifierNode.d(this.b, (AnnotatedString) obj));
                }
            }, 1, (Object) null);
            SemanticsPropertiesKt.insertTextAtCursor$default(semanticsPropertyReceiver, (String) null, new Function1() { // from class: v03
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(CoreTextFieldSemanticsModifierNode.b(this.b, semanticsPropertyReceiver, (AnnotatedString) obj));
                }
            }, 1, (Object) null);
        }
        SemanticsPropertiesKt.setSelection$default(semanticsPropertyReceiver, (String) null, new Function3() { // from class: w03
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return Boolean.valueOf(CoreTextFieldSemanticsModifierNode.c(this.b, ((Integer) obj).intValue(), ((Integer) obj2).intValue(), ((Boolean) obj3).booleanValue()));
            }
        }, 1, (Object) null);
        SemanticsPropertiesKt.onImeAction-9UiTYpY$default(semanticsPropertyReceiver, this.imeOptions.getImeAction(), (String) null, new Function0() { // from class: x03
            public final Object invoke() {
                return Boolean.valueOf(CoreTextFieldSemanticsModifierNode.f(this.b));
            }
        }, 2, (Object) null);
        SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, (String) null, new Function0() { // from class: y03
            public final Object invoke() {
                return Boolean.valueOf(CoreTextFieldSemanticsModifierNode.k(this.b));
            }
        }, 1, (Object) null);
        SemanticsPropertiesKt.onLongClick$default(semanticsPropertyReceiver, (String) null, new Function0() { // from class: n03
            public final Object invoke() {
                return Boolean.valueOf(CoreTextFieldSemanticsModifierNode.a(this.b));
            }
        }, 1, (Object) null);
        if (!TextRange.getCollapsed-impl(this.value.getSelection-d9O1mEE()) && !this.isPassword) {
            SemanticsPropertiesKt.copyText$default(semanticsPropertyReceiver, (String) null, new Function0() { // from class: o03
                public final Object invoke() {
                    return Boolean.valueOf(CoreTextFieldSemanticsModifierNode.j(this.b));
                }
            }, 1, (Object) null);
            if (this.enabled && !this.readOnly) {
                SemanticsPropertiesKt.cutText$default(semanticsPropertyReceiver, (String) null, new Function0() { // from class: p03
                    public final Object invoke() {
                        return Boolean.valueOf(CoreTextFieldSemanticsModifierNode.h(this.b));
                    }
                }, 1, (Object) null);
            }
        }
        if (!this.enabled || this.readOnly) {
            return;
        }
        SemanticsPropertiesKt.pasteText$default(semanticsPropertyReceiver, (String) null, new Function0() { // from class: s03
            public final Object invoke() {
                return Boolean.valueOf(CoreTextFieldSemanticsModifierNode.i(this.b));
            }
        }, 1, (Object) null);
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final FocusRequester getFocusRequester() {
        return this.focusRequester;
    }

    public final ImeOptions getImeOptions() {
        return this.imeOptions;
    }

    public final TextFieldSelectionManager getManager() {
        return this.manager;
    }

    public final OffsetMapping getOffsetMapping() {
        return this.offsetMapping;
    }

    public final boolean getReadOnly() {
        return this.readOnly;
    }

    public boolean getShouldMergeDescendantSemantics() {
        return true;
    }

    public final LegacyTextFieldState getState() {
        return this.state;
    }

    public final TransformedText getTransformedText() {
        return this.transformedText;
    }

    public final TextFieldValue getValue() {
        return this.value;
    }

    /* JADX INFO: renamed from: isPassword, reason: from getter */
    public final boolean getIsPassword() {
        return this.isPassword;
    }

    public final void setEnabled(boolean z) {
        this.enabled = z;
    }

    public final void setFocusRequester(FocusRequester focusRequester) {
        this.focusRequester = focusRequester;
    }

    public final void setImeOptions(ImeOptions imeOptions) {
        this.imeOptions = imeOptions;
    }

    public final void setManager(TextFieldSelectionManager textFieldSelectionManager) {
        this.manager = textFieldSelectionManager;
    }

    public final void setOffsetMapping(OffsetMapping offsetMapping) {
        this.offsetMapping = offsetMapping;
    }

    public final void setPassword(boolean z) {
        this.isPassword = z;
    }

    public final void setReadOnly(boolean z) {
        this.readOnly = z;
    }

    public final void setState(LegacyTextFieldState legacyTextFieldState) {
        this.state = legacyTextFieldState;
    }

    public final void setTransformedText(TransformedText transformedText) {
        this.transformedText = transformedText;
    }

    public final void setValue(TextFieldValue textFieldValue) {
        this.value = textFieldValue;
    }

    public final void updateNodeSemantics(TransformedText transformedText, TextFieldValue value, LegacyTextFieldState state, boolean readOnly, boolean enabled, boolean isPassword, OffsetMapping offsetMapping, TextFieldSelectionManager manager, ImeOptions imeOptions, FocusRequester focusRequester) {
        boolean z = this.enabled;
        boolean z2 = false;
        boolean z3 = z && !this.readOnly;
        boolean z4 = this.isPassword;
        ImeOptions imeOptions2 = this.imeOptions;
        TextFieldSelectionManager textFieldSelectionManager = this.manager;
        if (enabled && !readOnly) {
            z2 = true;
        }
        this.transformedText = transformedText;
        this.value = value;
        this.state = state;
        this.readOnly = readOnly;
        this.enabled = enabled;
        this.offsetMapping = offsetMapping;
        this.manager = manager;
        this.imeOptions = imeOptions;
        this.focusRequester = focusRequester;
        if (enabled != z || z2 != z3 || !Intrinsics.areEqual(imeOptions, imeOptions2) || isPassword != z4 || !TextRange.getCollapsed-impl(value.getSelection-d9O1mEE())) {
            SemanticsModifierNodeKt.invalidateSemantics(this);
        }
        if (Intrinsics.areEqual(manager, textFieldSelectionManager)) {
            return;
        }
        manager.setRequestAutofillAction$foundation(new Function0() { // from class: m03
            public final Object invoke() {
                return CoreTextFieldSemanticsModifierNode.g(this.b);
            }
        });
    }
}
