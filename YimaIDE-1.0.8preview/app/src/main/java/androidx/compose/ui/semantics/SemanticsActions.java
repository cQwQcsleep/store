package androidx.compose.ui.semantics;

import androidx.compose.ui.autofill.FillableData;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.core.app.NotificationCompat;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R/\u0010\u0004\u001a \u0012\u001c\u0012\u001a\u0012\u0016\u0012\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0004\u0012\u00020\n0\u00070\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR#\u0010\r\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000e0\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR#\u0010\u0010\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000e0\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fRM\u0010\u0012\u001a>\u0012:\u00128\u00124\u00122\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\n0\u00130\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\fRB\u0010\u001a\u001a3\u0012/\u0012-\b\u0001\u0012\u0013\u0012\u00110\u001b¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u001c\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001d\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00130\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\fR)\u0010\u001f\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\n0\u00070\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\fR4\u0010\"\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\n0\u00070\u00060\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b$\u0010\u0003\u001a\u0004\b%\u0010\fR)\u0010&\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020\n0\u00070\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\fR8\u0010)\u001a)\u0012%\u0012#\u0012\u001f\u0012\u001d\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(*\u0012\u0004\u0012\u00020\n0\u00070\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\fR5\u0010,\u001a&\u0012\"\u0012 \u0012\u001c\u0012\u001a\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0-0\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\fR)\u0010/\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\n0\u00070\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\fR)\u00101\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\n0\u00070\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\fR)\u00103\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\u00070\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\fR#\u00105\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000e0\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\fR)\u00107\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\n0\u00070\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\fR#\u00109\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000e0\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b:\u0010\fR.\u0010;\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000e0\u00060\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b<\u0010\u0003\u001a\u0004\b=\u0010\fR#\u0010>\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000e0\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b?\u0010\fR#\u0010@\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000e0\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\bA\u0010\fR#\u0010B\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000e0\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\bC\u0010\fR#\u0010D\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000e0\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\bE\u0010\fR#\u0010F\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000e0\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\bG\u0010\fR#\u0010H\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000e0\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\bI\u0010\fR#\u0010J\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000e0\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\bK\u0010\fR\u001d\u0010L\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020N0M0\u0005¢\u0006\b\n\u0000\u001a\u0004\bO\u0010\fR#\u0010P\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000e0\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\bQ\u0010\fR#\u0010R\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000e0\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\bS\u0010\fR#\u0010T\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000e0\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\bU\u0010\fR#\u0010V\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000e0\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\bW\u0010\fR/\u0010X\u001a \u0012\u001c\u0012\u001a\u0012\u0016\u0012\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\b\u0012\u0004\u0012\u00020\n0\u00070\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\bY\u0010\f¨\u0006Z"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsActions;", "", "<init>", "()V", "GetTextLayoutResult", "Landroidx/compose/ui/semantics/SemanticsPropertyKey;", "Landroidx/compose/ui/semantics/AccessibilityAction;", "Lkotlin/Function1;", "", "Landroidx/compose/ui/text/TextLayoutResult;", "", "getGetTextLayoutResult", "()Landroidx/compose/ui/semantics/SemanticsPropertyKey;", "OnClick", "Lkotlin/Function0;", "getOnClick", "OnLongClick", "getOnLongClick", "ScrollBy", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "x", "y", "getScrollBy", "ScrollByOffset", "Landroidx/compose/ui/geometry/Offset;", "offset", "Lkotlin/coroutines/Continuation;", "getScrollByOffset", "ScrollToIndex", "", "getScrollToIndex", "OnAutofillText", "Landroidx/compose/ui/text/AnnotatedString;", "getOnAutofillText$annotations", "getOnAutofillText", "OnFillData", "Landroidx/compose/ui/autofill/FillableData;", "getOnFillData", "SetProgress", NotificationCompat.CATEGORY_PROGRESS, "getSetProgress", "SetSelection", "Lkotlin/Function3;", "getSetSelection", "SetText", "getSetText", "SetTextSubstitution", "getSetTextSubstitution", "ShowTextSubstitution", "getShowTextSubstitution", "ClearTextSubstitution", "getClearTextSubstitution", "InsertTextAtCursor", "getInsertTextAtCursor", "OnImeAction", "getOnImeAction", "PerformImeAction", "getPerformImeAction$annotations", "getPerformImeAction", "CopyText", "getCopyText", "CutText", "getCutText", "PasteText", "getPasteText", "Expand", "getExpand", "Collapse", "getCollapse", "Dismiss", "getDismiss", "RequestFocus", "getRequestFocus", "CustomActions", "", "Landroidx/compose/ui/semantics/CustomAccessibilityAction;", "getCustomActions", "PageUp", "getPageUp", "PageLeft", "getPageLeft", "PageDown", "getPageDown", "PageRight", "getPageRight", "GetScrollViewportLength", "getGetScrollViewportLength", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SemanticsActions {
    public static final int $stable;
    private static final SemanticsPropertyKey<AccessibilityAction<Function0<Boolean>>> ClearTextSubstitution;
    private static final SemanticsPropertyKey<AccessibilityAction<Function0<Boolean>>> Collapse;
    private static final SemanticsPropertyKey<AccessibilityAction<Function0<Boolean>>> CopyText;
    private static final SemanticsPropertyKey<List<CustomAccessibilityAction>> CustomActions;
    private static final SemanticsPropertyKey<AccessibilityAction<Function0<Boolean>>> CutText;
    private static final SemanticsPropertyKey<AccessibilityAction<Function0<Boolean>>> Dismiss;
    private static final SemanticsPropertyKey<AccessibilityAction<Function0<Boolean>>> Expand;
    private static final SemanticsPropertyKey<AccessibilityAction<Function1<List<Float>, Boolean>>> GetScrollViewportLength;
    private static final SemanticsPropertyKey<AccessibilityAction<Function1<List<TextLayoutResult>, Boolean>>> GetTextLayoutResult;
    public static final SemanticsActions INSTANCE = new SemanticsActions();
    private static final SemanticsPropertyKey<AccessibilityAction<Function1<AnnotatedString, Boolean>>> InsertTextAtCursor;
    private static final SemanticsPropertyKey<AccessibilityAction<Function1<AnnotatedString, Boolean>>> OnAutofillText;
    private static final SemanticsPropertyKey<AccessibilityAction<Function0<Boolean>>> OnClick;
    private static final SemanticsPropertyKey<AccessibilityAction<Function1<FillableData, Boolean>>> OnFillData;
    private static final SemanticsPropertyKey<AccessibilityAction<Function0<Boolean>>> OnImeAction;
    private static final SemanticsPropertyKey<AccessibilityAction<Function0<Boolean>>> OnLongClick;
    private static final SemanticsPropertyKey<AccessibilityAction<Function0<Boolean>>> PageDown;
    private static final SemanticsPropertyKey<AccessibilityAction<Function0<Boolean>>> PageLeft;
    private static final SemanticsPropertyKey<AccessibilityAction<Function0<Boolean>>> PageRight;
    private static final SemanticsPropertyKey<AccessibilityAction<Function0<Boolean>>> PageUp;
    private static final SemanticsPropertyKey<AccessibilityAction<Function0<Boolean>>> PasteText;
    private static final SemanticsPropertyKey<AccessibilityAction<Function0<Boolean>>> PerformImeAction;
    private static final SemanticsPropertyKey<AccessibilityAction<Function0<Boolean>>> RequestFocus;
    private static final SemanticsPropertyKey<AccessibilityAction<Function2<Float, Float, Boolean>>> ScrollBy;
    private static final SemanticsPropertyKey<Function2<Offset, Continuation<? super Offset>, Object>> ScrollByOffset;
    private static final SemanticsPropertyKey<AccessibilityAction<Function1<Integer, Boolean>>> ScrollToIndex;
    private static final SemanticsPropertyKey<AccessibilityAction<Function1<Float, Boolean>>> SetProgress;
    private static final SemanticsPropertyKey<AccessibilityAction<Function3<Integer, Integer, Boolean, Boolean>>> SetSelection;
    private static final SemanticsPropertyKey<AccessibilityAction<Function1<AnnotatedString, Boolean>>> SetText;
    private static final SemanticsPropertyKey<AccessibilityAction<Function1<AnnotatedString, Boolean>>> SetTextSubstitution;
    private static final SemanticsPropertyKey<AccessibilityAction<Function1<Boolean, Boolean>>> ShowTextSubstitution;

    static {
        SemanticsPropertiesKt.AnonymousClass1 anonymousClass1 = SemanticsPropertiesKt.AnonymousClass1.INSTANCE;
        GetTextLayoutResult = new SemanticsPropertyKey<>("GetTextLayoutResult", true, anonymousClass1, null, 8, null);
        OnClick = new SemanticsPropertyKey<>("OnClick", true, anonymousClass1, null, 8, null);
        OnLongClick = new SemanticsPropertyKey<>("OnLongClick", true, anonymousClass1, null, 8, null);
        ScrollBy = new SemanticsPropertyKey<>("ScrollBy", true, anonymousClass1, null, 8, null);
        ScrollByOffset = new SemanticsPropertyKey<>("ScrollByOffset", (Function2) null, 2, (DefaultConstructorMarker) null);
        ScrollToIndex = new SemanticsPropertyKey<>("ScrollToIndex", true, anonymousClass1, null, 8, null);
        OnAutofillText = new SemanticsPropertyKey<>("OnAutofillText", true, anonymousClass1, null, 8, null);
        OnFillData = new SemanticsPropertyKey<>("OnFillData", true, anonymousClass1, null, 8, null);
        SetProgress = new SemanticsPropertyKey<>("SetProgress", true, anonymousClass1, null, 8, null);
        SetSelection = new SemanticsPropertyKey<>("SetSelection", true, anonymousClass1, null, 8, null);
        SetText = new SemanticsPropertyKey<>("SetText", true, anonymousClass1, null, 8, null);
        SetTextSubstitution = new SemanticsPropertyKey<>("SetTextSubstitution", true, anonymousClass1, null, 8, null);
        ShowTextSubstitution = new SemanticsPropertyKey<>("ShowTextSubstitution", true, anonymousClass1, null, 8, null);
        ClearTextSubstitution = new SemanticsPropertyKey<>("ClearTextSubstitution", true, anonymousClass1, null, 8, null);
        InsertTextAtCursor = new SemanticsPropertyKey<>("InsertTextAtCursor", true, anonymousClass1, null, 8, null);
        OnImeAction = new SemanticsPropertyKey<>("PerformImeAction", true, anonymousClass1, null, 8, null);
        PerformImeAction = new SemanticsPropertyKey<>("PerformImeAction", true, anonymousClass1, null, 8, null);
        CopyText = new SemanticsPropertyKey<>("CopyText", true, anonymousClass1, null, 8, null);
        CutText = new SemanticsPropertyKey<>("CutText", true, anonymousClass1, null, 8, null);
        PasteText = new SemanticsPropertyKey<>("PasteText", true, anonymousClass1, null, 8, null);
        Expand = new SemanticsPropertyKey<>("Expand", true, anonymousClass1, null, 8, null);
        Collapse = new SemanticsPropertyKey<>("Collapse", true, anonymousClass1, null, 8, null);
        Dismiss = new SemanticsPropertyKey<>("Dismiss", true, anonymousClass1, null, 8, null);
        RequestFocus = new SemanticsPropertyKey<>("RequestFocus", true, anonymousClass1, null, 8, null);
        CustomActions = new SemanticsPropertyKey<>("CustomActions", true, new Function2<List<? extends CustomAccessibilityAction>, List<? extends CustomAccessibilityAction>, List<? extends CustomAccessibilityAction>>() { // from class: androidx.compose.ui.semantics.SemanticsActions$CustomActions$1
            public final List<CustomAccessibilityAction> invoke(List<CustomAccessibilityAction> list, List<CustomAccessibilityAction> list2) {
                if (list == null) {
                    list = CollectionsKt.emptyList();
                }
                return CollectionsKt.plus(list, list2);
            }
        }, null, 8, null);
        PageUp = new SemanticsPropertyKey<>("PageUp", true, anonymousClass1, null, 8, null);
        PageLeft = new SemanticsPropertyKey<>("PageLeft", true, anonymousClass1, null, 8, null);
        PageDown = new SemanticsPropertyKey<>("PageDown", true, anonymousClass1, null, 8, null);
        PageRight = new SemanticsPropertyKey<>("PageRight", true, anonymousClass1, null, 8, null);
        GetScrollViewportLength = new SemanticsPropertyKey<>("GetScrollViewportLength", true, anonymousClass1, null, 8, null);
        $stable = 8;
    }

    private SemanticsActions() {
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use `SemanticsActions.OnFillData` instead.", replaceWith = @ReplaceWith(expression = "OnFillData", imports = {"androidx.compose.ui.semantics.SemanticsActions.OnFillData"}))
    public static /* synthetic */ void getOnAutofillText$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use `SemanticsActions.OnImeAction` instead.", replaceWith = @ReplaceWith(expression = "OnImeAction", imports = {"androidx.compose.ui.semantics.SemanticsActions.OnImeAction"}))
    public static /* synthetic */ void getPerformImeAction$annotations() {
    }

    public final SemanticsPropertyKey<AccessibilityAction<Function0<Boolean>>> getClearTextSubstitution() {
        return ClearTextSubstitution;
    }

    public final SemanticsPropertyKey<AccessibilityAction<Function0<Boolean>>> getCollapse() {
        return Collapse;
    }

    public final SemanticsPropertyKey<AccessibilityAction<Function0<Boolean>>> getCopyText() {
        return CopyText;
    }

    public final SemanticsPropertyKey<List<CustomAccessibilityAction>> getCustomActions() {
        return CustomActions;
    }

    public final SemanticsPropertyKey<AccessibilityAction<Function0<Boolean>>> getCutText() {
        return CutText;
    }

    public final SemanticsPropertyKey<AccessibilityAction<Function0<Boolean>>> getDismiss() {
        return Dismiss;
    }

    public final SemanticsPropertyKey<AccessibilityAction<Function0<Boolean>>> getExpand() {
        return Expand;
    }

    public final SemanticsPropertyKey<AccessibilityAction<Function1<List<Float>, Boolean>>> getGetScrollViewportLength() {
        return GetScrollViewportLength;
    }

    public final SemanticsPropertyKey<AccessibilityAction<Function1<List<TextLayoutResult>, Boolean>>> getGetTextLayoutResult() {
        return GetTextLayoutResult;
    }

    public final SemanticsPropertyKey<AccessibilityAction<Function1<AnnotatedString, Boolean>>> getInsertTextAtCursor() {
        return InsertTextAtCursor;
    }

    public final SemanticsPropertyKey<AccessibilityAction<Function1<AnnotatedString, Boolean>>> getOnAutofillText() {
        return OnAutofillText;
    }

    public final SemanticsPropertyKey<AccessibilityAction<Function0<Boolean>>> getOnClick() {
        return OnClick;
    }

    public final SemanticsPropertyKey<AccessibilityAction<Function1<FillableData, Boolean>>> getOnFillData() {
        return OnFillData;
    }

    public final SemanticsPropertyKey<AccessibilityAction<Function0<Boolean>>> getOnImeAction() {
        return OnImeAction;
    }

    public final SemanticsPropertyKey<AccessibilityAction<Function0<Boolean>>> getOnLongClick() {
        return OnLongClick;
    }

    public final SemanticsPropertyKey<AccessibilityAction<Function0<Boolean>>> getPageDown() {
        return PageDown;
    }

    public final SemanticsPropertyKey<AccessibilityAction<Function0<Boolean>>> getPageLeft() {
        return PageLeft;
    }

    public final SemanticsPropertyKey<AccessibilityAction<Function0<Boolean>>> getPageRight() {
        return PageRight;
    }

    public final SemanticsPropertyKey<AccessibilityAction<Function0<Boolean>>> getPageUp() {
        return PageUp;
    }

    public final SemanticsPropertyKey<AccessibilityAction<Function0<Boolean>>> getPasteText() {
        return PasteText;
    }

    public final SemanticsPropertyKey<AccessibilityAction<Function0<Boolean>>> getPerformImeAction() {
        return PerformImeAction;
    }

    public final SemanticsPropertyKey<AccessibilityAction<Function0<Boolean>>> getRequestFocus() {
        return RequestFocus;
    }

    public final SemanticsPropertyKey<AccessibilityAction<Function2<Float, Float, Boolean>>> getScrollBy() {
        return ScrollBy;
    }

    public final SemanticsPropertyKey<Function2<Offset, Continuation<? super Offset>, Object>> getScrollByOffset() {
        return ScrollByOffset;
    }

    public final SemanticsPropertyKey<AccessibilityAction<Function1<Integer, Boolean>>> getScrollToIndex() {
        return ScrollToIndex;
    }

    public final SemanticsPropertyKey<AccessibilityAction<Function1<Float, Boolean>>> getSetProgress() {
        return SetProgress;
    }

    public final SemanticsPropertyKey<AccessibilityAction<Function3<Integer, Integer, Boolean, Boolean>>> getSetSelection() {
        return SetSelection;
    }

    public final SemanticsPropertyKey<AccessibilityAction<Function1<AnnotatedString, Boolean>>> getSetText() {
        return SetText;
    }

    public final SemanticsPropertyKey<AccessibilityAction<Function1<AnnotatedString, Boolean>>> getSetTextSubstitution() {
        return SetTextSubstitution;
    }

    public final SemanticsPropertyKey<AccessibilityAction<Function1<Boolean, Boolean>>> getShowTextSubstitution() {
        return ShowTextSubstitution;
    }
}
