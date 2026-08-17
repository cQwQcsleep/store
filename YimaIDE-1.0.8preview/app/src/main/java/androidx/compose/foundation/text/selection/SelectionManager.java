package androidx.compose.foundation.text.selection;

import androidx.collection.LongIntMapKt;
import androidx.collection.LongObjectMap;
import androidx.collection.LongObjectMapKt;
import androidx.collection.MutableLongIntMap;
import androidx.collection.MutableLongObjectMap;
import androidx.collection.SieveCacheKt;
import androidx.compose.foundation.ComposeFoundationFlags;
import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.gestures.DragGestureDetectorKt;
import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.text.Handle;
import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.foundation.text.TextLayoutHelperKt;
import androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuGesturesModifierKt;
import androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuToolbarHandlerModifierKt;
import androidx.compose.foundation.text.contextmenu.modifier.ToolbarRequester;
import androidx.compose.foundation.text.contextmenu.modifier.ToolbarRequesterImpl;
import androidx.compose.foundation.text.input.internal.TextLayoutStateKt;
import androidx.compose.foundation.text.selection.Selection;
import androidx.compose.foundation.text.selection.SelectionAdjustment;
import androidx.compose.foundation.text.selection.SelectionManager;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusChangedModifierKt;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.hapticfeedback.HapticFeedbackType;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.OnGloballyPositionedModifierKt;
import androidx.compose.ui.platform.TextToolbar;
import androidx.compose.ui.platform.TextToolbarStatus;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000ê\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0014\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\u0097\u0001\u001a\u00020\u0017H\u0002J\u001c\u0010\u0098\u0001\u001a\u0005\u0018\u00010\u0099\u00012\b\u0010\u009a\u0001\u001a\u00030\u009b\u0001H\u0000¢\u0006\u0003\b\u009c\u0001J\t\u0010\u009d\u0001\u001a\u00020\u0017H\u0002J\u000f\u0010\u009e\u0001\u001a\u00020PH\u0000¢\u0006\u0003\b\u009f\u0001J:\u0010 \u0001\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u000b\u0012\t\u0012\u0004\u0012\u00020\b0¢\u00010¡\u00012\b\u0010£\u0001\u001a\u00030¤\u00012\t\u0010¥\u0001\u001a\u0004\u0018\u00010\bH\u0000¢\u0006\u0003\b¦\u0001J\u000f\u0010§\u0001\u001a\u00020\u0010H\u0000¢\u0006\u0003\b¨\u0001J\u000f\u0010©\u0001\u001a\u00020\u0017H\u0000¢\u0006\u0003\bª\u0001J\u000f\u0010«\u0001\u001a\u00020\u0010H\u0000¢\u0006\u0003\b¬\u0001J\u000f\u0010\u00ad\u0001\u001a\u00020\u0010H\u0000¢\u0006\u0003\b®\u0001J\u0011\u0010¯\u0001\u001a\u0004\u0018\u00010$H\u0000¢\u0006\u0003\b°\u0001J\u001f\u0010±\u0001\u001a\u0012\u0012\u0004\u0012\u00020$\u0012\u0005\u0012\u00030²\u0001\u0018\u00010¡\u0001H\u0000¢\u0006\u0003\b³\u0001J\u0083\u0001\u0010´\u0001\u001a\u00020\u00172q\b\u0004\u0010µ\u0001\u001aj\u0012\u0017\u0012\u00150¤\u0001¢\u0006\u000f\b·\u0001\u0012\n\b¸\u0001\u0012\u0005\b\b(£\u0001\u0012\u0016\u0012\u00140$¢\u0006\u000f\b·\u0001\u0012\n\b¸\u0001\u0012\u0005\b\b(¹\u0001\u0012\u0016\u0012\u00140²\u0001¢\u0006\u000e\b·\u0001\u0012\t\b¸\u0001\u0012\u0004\b\b(\n\u0012\u0016\u0012\u00140\u0010¢\u0006\u000f\b·\u0001\u0012\n\b¸\u0001\u0012\u0005\b\b(º\u0001\u0012\u0004\u0012\u00020\u00100¶\u0001H\u0080\b¢\u0006\u0003\b»\u0001J\u000f\u0010¼\u0001\u001a\u00020\u0017H\u0000¢\u0006\u0003\b½\u0001J\t\u0010Á\u0001\u001a\u00020\u0017H\u0002J\t\u0010Â\u0001\u001a\u00020\u0017H\u0002J\u000f\u0010Ã\u0001\u001a\u00020\u0010H\u0000¢\u0006\u0003\bÄ\u0001J\t\u0010Å\u0001\u001a\u00020\u0017H\u0002J\u000b\u0010Æ\u0001\u001a\u0004\u0018\u00010GH\u0002J\u0007\u0010Ç\u0001\u001a\u00020\u0017J\u0011\u0010È\u0001\u001a\u00030É\u00012\u0007\u0010Ê\u0001\u001a\u00020\u0010J\r\u0010Ë\u0001\u001a\u00020C*\u00020CH\u0002J\u001d\u0010Ì\u0001\u001a\u00020C*\u00020C2\u000e\u0010µ\u0001\u001a\t\u0012\u0004\u0012\u00020\u00170Í\u0001H\u0002J$\u0010Î\u0001\u001a\u00020O2\u0007\u0010Ï\u0001\u001a\u00020P2\u0007\u0010Ð\u0001\u001a\u00020OH\u0002¢\u0006\u0006\bÑ\u0001\u0010Ò\u0001J.\u0010Ó\u0001\u001a\u00020\u00172\u0007\u0010Ô\u0001\u001a\u00020O2\u0007\u0010Ê\u0001\u001a\u00020\u00102\b\u0010Õ\u0001\u001a\u00030Ö\u0001H\u0002¢\u0006\u0006\b×\u0001\u0010Ø\u0001J8\u0010Ù\u0001\u001a\u00020\u00102\t\u0010Ú\u0001\u001a\u0004\u0018\u00010O2\u0006\u0010N\u001a\u00020O2\u0007\u0010Ê\u0001\u001a\u00020\u00102\b\u0010Õ\u0001\u001a\u00030Ö\u0001H\u0000¢\u0006\u0006\bÛ\u0001\u0010Ü\u0001J7\u0010Ù\u0001\u001a\u00020\u00102\u0007\u0010Ô\u0001\u001a\u00020O2\u0007\u0010Ý\u0001\u001a\u00020O2\u0007\u0010Ê\u0001\u001a\u00020\u00102\b\u0010Õ\u0001\u001a\u00030Ö\u0001H\u0000¢\u0006\u0006\bÞ\u0001\u0010ß\u0001J0\u0010à\u0001\u001a\u0005\u0018\u00010\u0084\u00012\u0007\u0010Ô\u0001\u001a\u00020O2\u0007\u0010Ý\u0001\u001a\u00020O2\u0007\u0010Ê\u0001\u001a\u00020\u0010H\u0002¢\u0006\u0006\bá\u0001\u0010â\u0001J\u001c\u0010ã\u0001\u001a\u00020\u00172\b\u0010ä\u0001\u001a\u00030\u0084\u00012\u0007\u0010å\u0001\u001a\u00020\bH\u0002J\u000f\u0010æ\u0001\u001a\u00020\u0010H\u0001¢\u0006\u0003\bç\u0001J\u0018\u0010è\u0001\u001a\u00020\u00172\u0007\u0010Ô\u0001\u001a\u00020O¢\u0006\u0005\bé\u0001\u0010`R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\n\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0011\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u00108F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R@\u0010\u0018\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\u00170\u00162\u0014\u0010\u0015\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\u00170\u0016@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R(\u0010#\u001a\u0010\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u001a\"\u0004\b&\u0010\u001cR\u001c\u0010'\u001a\u0004\u0018\u00010(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R$\u0010-\u001a\u00020.8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b/\u00100\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u001a\u00105\u001a\u000206X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R+\u0010<\u001a\u00020\u00102\u0006\u0010;\u001a\u00020\u00108F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b?\u0010@\u001a\u0004\b=\u0010\u0012\"\u0004\b>\u0010\u0014R\u0014\u0010A\u001a\u00020\u00108BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bA\u0010\u0012R\u0011\u0010B\u001a\u00020C8F¢\u0006\u0006\u001a\u0004\bD\u0010ER\u001d\u0010F\u001a\u0004\u0018\u00010G8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bJ\u0010K\u001a\u0004\bH\u0010IR\u0011\u0010L\u001a\u00020C8F¢\u0006\u0006\u001a\u0004\bM\u0010ER\u0010\u0010N\u001a\u0004\u0018\u00010OX\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010Q\u001a\u0004\u0018\u00010P2\b\u0010\t\u001a\u0004\u0018\u00010P@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010S\"\u0004\bT\u0010UR+\u0010V\u001a\u00020\u00172\u0006\u0010;\u001a\u00020\u00178B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b[\u0010@\u001a\u0004\bW\u0010X\"\u0004\bY\u0010ZR+\u0010\\\u001a\u00020O2\u0006\u0010;\u001a\u00020O8@@BX\u0080\u008e\u0002¢\u0006\u0012\n\u0004\ba\u0010@\u001a\u0004\b]\u0010^\"\u0004\b_\u0010`R+\u0010b\u001a\u00020O2\u0006\u0010;\u001a\u00020O8@@BX\u0080\u008e\u0002¢\u0006\u0012\n\u0004\be\u0010@\u001a\u0004\bc\u0010^\"\u0004\bd\u0010`R/\u0010f\u001a\u0004\u0018\u00010O2\b\u0010;\u001a\u0004\u0018\u00010O8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bk\u0010@\u001a\u0004\bg\u0010h\"\u0004\bi\u0010jR/\u0010l\u001a\u0004\u0018\u00010O2\b\u0010;\u001a\u0004\u0018\u00010O8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bo\u0010@\u001a\u0004\bm\u0010h\"\u0004\bn\u0010jR/\u0010q\u001a\u0004\u0018\u00010p2\b\u0010;\u001a\u0004\u0018\u00010p8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bv\u0010@\u001a\u0004\br\u0010s\"\u0004\bt\u0010uR\u0011\u0010w\u001a\u00020x8F¢\u0006\u0006\u001a\u0004\by\u0010zR\u0011\u0010{\u001a\u00020x8F¢\u0006\u0006\u001a\u0004\b|\u0010zR0\u0010}\u001a\u0004\u0018\u00010O2\b\u0010;\u001a\u0004\u0018\u00010O8F@BX\u0086\u008e\u0002¢\u0006\u0013\n\u0005\b\u0080\u0001\u0010@\u001a\u0004\b~\u0010h\"\u0004\b\u007f\u0010jR\u0016\u0010\u0081\u0001\u001a\u00020\u00108BX\u0082\u0004¢\u0006\u0007\u001a\u0005\b\u0082\u0001\u0010\u0012R-\u0010\u0083\u0001\u001a\u0005\u0018\u00010\u0084\u00018\u0000@\u0000X\u0081\u000e¢\u0006\u0019\n\u0000\u0012\u0005\b\u0085\u0001\u00100\u001a\u0006\b\u0086\u0001\u0010\u0087\u0001\"\u0006\b\u0088\u0001\u0010\u0089\u0001R\u000f\u0010\u008a\u0001\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u008b\u0001\u001a\u0005\u0018\u00010\u008c\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u008d\u0001\u0010\u008e\u0001\"\u0006\b\u008f\u0001\u0010\u0090\u0001R\"\u0010\u0091\u0001\u001a\u0005\u0018\u00010\u0092\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0093\u0001\u0010\u0094\u0001\"\u0006\b\u0095\u0001\u0010\u0096\u0001R'\u0010¾\u0001\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010@@X\u0080\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¿\u0001\u0010\u0012\"\u0005\bÀ\u0001\u0010\u0014¨\u0006ê\u0001"}, d2 = {"Landroidx/compose/foundation/text/selection/SelectionManager;", "", "selectionRegistrar", "Landroidx/compose/foundation/text/selection/SelectionRegistrarImpl;", "<init>", "(Landroidx/compose/foundation/text/selection/SelectionRegistrarImpl;)V", "_selection", "Landroidx/compose/runtime/MutableState;", "Landroidx/compose/foundation/text/selection/Selection;", "value", "selection", "getSelection", "()Landroidx/compose/foundation/text/selection/Selection;", "setSelection", "(Landroidx/compose/foundation/text/selection/Selection;)V", "_isInTouchMode", "", "isInTouchMode", "()Z", "setInTouchMode", "(Z)V", "newOnSelectionChange", "Lkotlin/Function1;", "", "onSelectionChange", "getOnSelectionChange", "()Lkotlin/jvm/functions/Function1;", "setOnSelectionChange", "(Lkotlin/jvm/functions/Function1;)V", "hapticFeedBack", "Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "getHapticFeedBack", "()Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "setHapticFeedBack", "(Landroidx/compose/ui/hapticfeedback/HapticFeedback;)V", "onCopyHandler", "Landroidx/compose/ui/text/AnnotatedString;", "getOnCopyHandler", "setOnCopyHandler", "textToolbar", "Landroidx/compose/ui/platform/TextToolbar;", "getTextToolbar", "()Landroidx/compose/ui/platform/TextToolbar;", "setTextToolbar", "(Landroidx/compose/ui/platform/TextToolbar;)V", "toolbarRequester", "Landroidx/compose/foundation/text/contextmenu/modifier/ToolbarRequester;", "getToolbarRequester$foundation$annotations", "()V", "getToolbarRequester$foundation", "()Landroidx/compose/foundation/text/contextmenu/modifier/ToolbarRequester;", "setToolbarRequester$foundation", "(Landroidx/compose/foundation/text/contextmenu/modifier/ToolbarRequester;)V", "focusRequester", "Landroidx/compose/ui/focus/FocusRequester;", "getFocusRequester", "()Landroidx/compose/ui/focus/FocusRequester;", "setFocusRequester", "(Landroidx/compose/ui/focus/FocusRequester;)V", "<set-?>", "hasFocus", "getHasFocus", "setHasFocus", "hasFocus$delegate", "Landroidx/compose/runtime/MutableState;", "isDraggingInProgress", "modifier", "Landroidx/compose/ui/Modifier;", "getModifier", "()Landroidx/compose/ui/Modifier;", "derivedContentRect", "Landroidx/compose/ui/geometry/Rect;", "getDerivedContentRect", "()Landroidx/compose/ui/geometry/Rect;", "derivedContentRect$delegate", "Landroidx/compose/runtime/State;", "contextMenuAreaModifier", "getContextMenuAreaModifier", "previousPosition", "Landroidx/compose/ui/geometry/Offset;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "containerLayoutCoordinates", "getContainerLayoutCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "setContainerLayoutCoordinates", "(Landroidx/compose/ui/layout/LayoutCoordinates;)V", "positionChangeState", "getPositionChangeState", "()Lkotlin/Unit;", "setPositionChangeState", "(Lkotlin/Unit;)V", "positionChangeState$delegate", "dragBeginPosition", "getDragBeginPosition-F1C5BW0$foundation", "()J", "setDragBeginPosition-k-4lQ0M", "(J)V", "dragBeginPosition$delegate", "dragTotalDistance", "getDragTotalDistance-F1C5BW0$foundation", "setDragTotalDistance-k-4lQ0M", "dragTotalDistance$delegate", "startHandlePosition", "getStartHandlePosition-_m7T9-E", "()Landroidx/compose/ui/geometry/Offset;", "setStartHandlePosition-_kEHs6E", "(Landroidx/compose/ui/geometry/Offset;)V", "startHandlePosition$delegate", "endHandlePosition", "getEndHandlePosition-_m7T9-E", "setEndHandlePosition-_kEHs6E", "endHandlePosition$delegate", "Landroidx/compose/foundation/text/Handle;", "draggingHandle", "getDraggingHandle", "()Landroidx/compose/foundation/text/Handle;", "setDraggingHandle", "(Landroidx/compose/foundation/text/Handle;)V", "draggingHandle$delegate", "startHandleLineHeight", "", "getStartHandleLineHeight", "()F", "endHandleLineHeight", "getEndHandleLineHeight", "currentDragPosition", "getCurrentDragPosition-_m7T9-E", "setCurrentDragPosition-_kEHs6E", "currentDragPosition$delegate", "shouldShowMagnifier", "getShouldShowMagnifier", "previousSelectionLayout", "Landroidx/compose/foundation/text/selection/SelectionLayout;", "getPreviousSelectionLayout$foundation$annotations", "getPreviousSelectionLayout$foundation", "()Landroidx/compose/foundation/text/selection/SelectionLayout;", "setPreviousSelectionLayout$foundation", "(Landroidx/compose/foundation/text/selection/SelectionLayout;)V", "isLongPressOrClickSelection", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "getCoroutineScope$foundation", "()Lkotlinx/coroutines/CoroutineScope;", "setCoroutineScope$foundation", "(Lkotlinx/coroutines/CoroutineScope;)V", "platformSelectionBehaviors", "Landroidx/compose/foundation/text/selection/PlatformSelectionBehaviors;", "getPlatformSelectionBehaviors$foundation", "()Landroidx/compose/foundation/text/selection/PlatformSelectionBehaviors;", "setPlatformSelectionBehaviors$foundation", "(Landroidx/compose/foundation/text/selection/PlatformSelectionBehaviors;)V", "suggestSelectionForLongPressOrDoubleClick", "getAnchorSelectable", "Landroidx/compose/foundation/text/selection/Selectable;", "anchor", "Landroidx/compose/foundation/text/selection/Selection$AnchorInfo;", "getAnchorSelectable$foundation", "updateHandleOffsets", "requireContainerCoordinates", "requireContainerCoordinates$foundation", "selectAllInSelectable", "Lkotlin/Pair;", "Landroidx/collection/LongObjectMap;", "selectableId", "", "previousSelection", "selectAllInSelectable$foundation", "isEntireContainerSelected", "isEntireContainerSelected$foundation", "selectAll", "selectAll$foundation", "isTriviallyCollapsedSelection", "isTriviallyCollapsedSelection$foundation", "isNonEmptySelection", "isNonEmptySelection$foundation", "getSelectedText", "getSelectedText$foundation", "getContextTextAndSelection", "Landroidx/compose/ui/text/TextRange;", "getContextTextAndSelection$foundation", "forEachSelectableWithSelection", "block", "Lkotlin/Function4;", "Lkotlin/ParameterName;", "name", "text", "isLastSelectable", "forEachSelectableWithSelection$foundation", "copy", "copy$foundation", "showToolbar", "getShowToolbar$foundation", "setShowToolbar$foundation", "toolbarCopy", "updateSelectionToolbar", "canCopy", "canCopy$foundation", "updateSelectionTextToolbar", "getContentRect", "onRelease", "handleDragObserver", "Landroidx/compose/foundation/text/TextDragObserver;", "isStartHandle", "addContextMenuComponents", "onClearSelectionRequested", "Lkotlin/Function0;", "convertToContainerCoordinates", "layoutCoordinates", "offset", "convertToContainerCoordinates-R5De75A", "(Landroidx/compose/ui/layout/LayoutCoordinates;J)J", "startSelection", "position", "adjustment", "Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "startSelection-9KIMszo", "(JZLandroidx/compose/foundation/text/selection/SelectionAdjustment;)V", "updateSelection", "newPosition", "updateSelection-qNKwrvQ$foundation", "(Landroidx/compose/ui/geometry/Offset;JZLandroidx/compose/foundation/text/selection/SelectionAdjustment;)Z", "previousHandlePosition", "updateSelection-jyLRC_s$foundation", "(JJZLandroidx/compose/foundation/text/selection/SelectionAdjustment;)Z", "getSelectionLayout", "getSelectionLayout-Wko1d7g", "(JJZ)Landroidx/compose/foundation/text/selection/SelectionLayout;", "selectionChanged", "selectionLayout", "newSelection", "shouldPerformHaptics", "shouldPerformHaptics$foundation", "selectWordAtPositionIfNotAlreadySelected", "selectWordAtPositionIfNotAlreadySelected-k-4lQ0M", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SelectionManager {
    public static final int $stable = 8;
    private LayoutCoordinates containerLayoutCoordinates;
    private CoroutineScope coroutineScope;

    /* JADX INFO: renamed from: currentDragPosition$delegate, reason: from kotlin metadata */
    private final MutableState currentDragPosition;

    /* JADX INFO: renamed from: dragBeginPosition$delegate, reason: from kotlin metadata */
    private final MutableState dragBeginPosition;

    /* JADX INFO: renamed from: dragTotalDistance$delegate, reason: from kotlin metadata */
    private final MutableState dragTotalDistance;

    /* JADX INFO: renamed from: draggingHandle$delegate, reason: from kotlin metadata */
    private final MutableState draggingHandle;

    /* JADX INFO: renamed from: endHandlePosition$delegate, reason: from kotlin metadata */
    private final MutableState endHandlePosition;
    private HapticFeedback hapticFeedBack;
    private boolean isLongPressOrClickSelection;
    private Function1<? super AnnotatedString, Unit> onCopyHandler;
    private PlatformSelectionBehaviors platformSelectionBehaviors;
    private Offset previousPosition;
    private SelectionLayout previousSelectionLayout;
    private final SelectionRegistrarImpl selectionRegistrar;
    private boolean showToolbar;

    /* JADX INFO: renamed from: startHandlePosition$delegate, reason: from kotlin metadata */
    private final MutableState startHandlePosition;
    private TextToolbar textToolbar;
    private final MutableState<Selection> _selection = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
    private final MutableState<Boolean> _isInTouchMode = SnapshotStateKt.mutableStateOf$default(Boolean.TRUE, (SnapshotMutationPolicy) null, 2, (Object) null);
    private Function1<? super Selection, Unit> onSelectionChange = new Function1() { // from class: t4d
        public final Object invoke(Object obj) {
            return SelectionManager.m(this.b, (Selection) obj);
        }
    };
    private ToolbarRequester toolbarRequester = new ToolbarRequesterImpl();
    private FocusRequester focusRequester = new FocusRequester();

    /* JADX INFO: renamed from: hasFocus$delegate, reason: from kotlin metadata */
    private final MutableState hasFocus = SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, (SnapshotMutationPolicy) null, 2, (Object) null);

    /* JADX INFO: renamed from: derivedContentRect$delegate, reason: from kotlin metadata */
    private final State derivedContentRect = SnapshotStateKt.derivedStateOf(new Function0() { // from class: u4d
        public final Object invoke() {
            return SelectionManager.a(this.b);
        }
    });

    /* JADX INFO: renamed from: positionChangeState$delegate, reason: from kotlin metadata */
    private final MutableState positionChangeState = SnapshotStateKt.mutableStateOf(Unit.INSTANCE, SnapshotStateKt.neverEqualPolicy());

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionManager$suggestSelectionForLongPressOrDoubleClick$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionManager$suggestSelectionForLongPressOrDoubleClick$2", f = "SelectionManager.kt", i = {}, l = {448}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef<TextRange> $selectionInSelectable;
        final /* synthetic */ Ref.LongRef $targetSelectableId;
        final /* synthetic */ Ref.ObjectRef<CharSequence> $textInSelectable;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(Ref.ObjectRef<CharSequence> objectRef, Ref.ObjectRef<TextRange> objectRef2, Ref.LongRef longRef, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$textInSelectable = objectRef;
            this.$selectionInSelectable = objectRef2;
            this.$targetSelectableId = longRef;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SelectionManager.this.new AnonymousClass2(this.$textInSelectable, this.$selectionInSelectable, this.$targetSelectableId, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:24:0x0070  */
        /* JADX WARN: Code duplicated, block: B:26:0x0073  */
        public final Object invokeSuspend(Object obj) {
            TextRange textRange;
            Selectable selectable;
            TextLayoutResult textLayoutResult;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PlatformSelectionBehaviors platformSelectionBehaviors = SelectionManager.this.getPlatformSelectionBehaviors();
                if (platformSelectionBehaviors != null) {
                    CharSequence charSequence = (CharSequence) this.$textInSelectable.element;
                    long j = ((TextRange) this.$selectionInSelectable.element).unbox-impl();
                    this.label = 1;
                    obj = platformSelectionBehaviors.mo1749suggestSelectionForLongPressOrDoubleClickpYaCww(charSequence, j, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    textRange = null;
                }
                if (textRange != null && !Intrinsics.areEqual(textRange, this.$selectionInSelectable.element) && (selectable = SelectionManager.this.selectionRegistrar.getSelectableMap$foundation().get(this.$targetSelectableId.element)) != null && selectable.getText() == this.$textInSelectable.element) {
                    textLayoutResult = selectable.textLayoutResult();
                    if (textLayoutResult == null) {
                        return Unit.INSTANCE;
                    }
                    Selection selection = new Selection(new Selection.AnchorInfo(SelectionHelpersKt.getTextDirectionForOffset(textLayoutResult, TextRange.getStart-impl(textRange.unbox-impl())), TextRange.getStart-impl(textRange.unbox-impl()), this.$targetSelectableId.element), new Selection.AnchorInfo(SelectionHelpersKt.getTextDirectionForOffset(textLayoutResult, TextRange.getEnd-impl(textRange.unbox-impl())), TextRange.getEnd-impl(textRange.unbox-impl()), this.$targetSelectableId.element), false, 4, null);
                    SelectionManager.this.selectionRegistrar.setSubselections(LongObjectMapKt.mutableLongObjectMapOf(this.$targetSelectableId.element, selection));
                    SelectionManager.this.getOnSelectionChange().invoke(selection);
                    SelectionManager.this.setPreviousSelectionLayout$foundation(null);
                }
                return Unit.INSTANCE;
            }
            if (i != 1) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            textRange = (TextRange) obj;
            if (textRange != null) {
                textLayoutResult = selectable.textLayoutResult();
                if (textLayoutResult == null) {
                    return Unit.INSTANCE;
                }
                Selection selection2 = new Selection(new Selection.AnchorInfo(SelectionHelpersKt.getTextDirectionForOffset(textLayoutResult, TextRange.getStart-impl(textRange.unbox-impl())), TextRange.getStart-impl(textRange.unbox-impl()), this.$targetSelectableId.element), new Selection.AnchorInfo(SelectionHelpersKt.getTextDirectionForOffset(textLayoutResult, TextRange.getEnd-impl(textRange.unbox-impl())), TextRange.getEnd-impl(textRange.unbox-impl()), this.$targetSelectableId.element), false, 4, null);
                SelectionManager.this.selectionRegistrar.setSubselections(LongObjectMapKt.mutableLongObjectMapOf(this.$targetSelectableId.element, selection2));
                SelectionManager.this.getOnSelectionChange().invoke(selection2);
                SelectionManager.this.setPreviousSelectionLayout$foundation(null);
            }
            return Unit.INSTANCE;
        }
    }

    public SelectionManager(SelectionRegistrarImpl selectionRegistrarImpl) {
        this.selectionRegistrar = selectionRegistrarImpl;
        Offset.Companion companion = Offset.Companion;
        this.dragBeginPosition = SnapshotStateKt.mutableStateOf$default(Offset.box-impl(companion.getZero-F1C5BW0()), (SnapshotMutationPolicy) null, 2, (Object) null);
        this.dragTotalDistance = SnapshotStateKt.mutableStateOf$default(Offset.box-impl(companion.getZero-F1C5BW0()), (SnapshotMutationPolicy) null, 2, (Object) null);
        this.startHandlePosition = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.endHandlePosition = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.draggingHandle = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.currentDragPosition = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
        selectionRegistrarImpl.setOnPositionChangeCallback$foundation(new Function1() { // from class: v4d
            public final Object invoke(Object obj) {
                return SelectionManager.d(this.b, ((Long) obj).longValue());
            }
        });
        selectionRegistrarImpl.setOnSelectionUpdateStartCallback$foundation(new Function4() { // from class: w4d
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return SelectionManager.c(this.b, ((Boolean) obj).booleanValue(), (LayoutCoordinates) obj2, (Offset) obj3, (SelectionAdjustment) obj4);
            }
        });
        selectionRegistrarImpl.setOnSelectionUpdateSelectAll$foundation(new Function2() { // from class: x4d
            public final Object invoke(Object obj, Object obj2) {
                return SelectionManager.k(this.b, ((Boolean) obj).booleanValue(), ((Long) obj2).longValue());
            }
        });
        selectionRegistrarImpl.setOnSelectionUpdateCallback$foundation(new Function6() { // from class: k4d
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
                return Boolean.valueOf(SelectionManager.j(this.b, ((Boolean) obj).booleanValue(), (LayoutCoordinates) obj2, (Offset) obj3, (Offset) obj4, ((Boolean) obj5).booleanValue(), (SelectionAdjustment) obj6));
            }
        });
        selectionRegistrarImpl.setOnSelectionUpdateEndCallback$foundation(new Function0() { // from class: l4d
            public final Object invoke() {
                return SelectionManager.i(this.b);
            }
        });
        selectionRegistrarImpl.setOnSelectableChangeCallback$foundation(new Function1() { // from class: m4d
            public final Object invoke(Object obj) {
                return SelectionManager.e(this.b, ((Long) obj).longValue());
            }
        });
        selectionRegistrarImpl.setAfterSelectableUnsubscribe$foundation(new Function1() { // from class: n4d
            public final Object invoke(Object obj) {
                return SelectionManager.h(this.b, ((Long) obj).longValue());
            }
        });
    }

    public static Rect a(SelectionManager selectionManager) {
        return selectionManager.getContentRect();
    }

    private final Modifier addContextMenuComponents(Modifier modifier) {
        return ComposeFoundationFlags.isNewContextMenuEnabled ? SelectionManager_androidKt.addSelectionContainerTextContextMenuComponents(modifier, this) : modifier;
    }

    public static Unit b(SelectionManager selectionManager, Function1 function1, Selection selection) {
        selectionManager.setSelection(selection);
        function1.invoke(selection);
        return Unit.INSTANCE;
    }

    public static Unit c(SelectionManager selectionManager, boolean z, LayoutCoordinates layoutCoordinates, Offset offset, SelectionAdjustment selectionAdjustment) {
        long j = layoutCoordinates.getSize-YbymL2g();
        Rect rect = new Rect(0.0f, 0.0f, (int) (j >> 32), (int) (j & 4294967295L));
        long jM1772convertToContainerCoordinatesR5De75A = selectionManager.m1772convertToContainerCoordinatesR5De75A(layoutCoordinates, SelectionManagerKt.m1793containsInclusiveUv8p0NA(rect, offset.unbox-impl()) ? offset.unbox-impl() : TextLayoutStateKt.m1603coerceIn3MmeM6k(offset.unbox-impl(), rect));
        if ((SieveCacheKt.InvalidMapping & jM1772convertToContainerCoordinatesR5De75A) != 9205357640488583168L) {
            selectionManager.setInTouchMode(z);
            selectionManager.m1779startSelection9KIMszo(jM1772convertToContainerCoordinatesR5De75A, false, selectionAdjustment);
            FocusRequester.requestFocus-3ESFkO8$default(selectionManager.focusRequester, 0, 1, (Object) null);
            selectionManager.setShowToolbar$foundation(false);
            selectionManager.isLongPressOrClickSelection = true;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: convertToContainerCoordinates-R5De75A, reason: not valid java name */
    private final long m1772convertToContainerCoordinatesR5De75A(LayoutCoordinates layoutCoordinates, long offset) {
        LayoutCoordinates layoutCoordinates2 = this.containerLayoutCoordinates;
        return (layoutCoordinates2 == null || !layoutCoordinates2.isAttached()) ? Offset.Companion.getUnspecified-F1C5BW0() : requireContainerCoordinates$foundation().localPositionOf-R5De75A(layoutCoordinates, offset);
    }

    public static Unit d(SelectionManager selectionManager, long j) {
        if (selectionManager.selectionRegistrar.getSubselections().containsKey(j)) {
            selectionManager.setPositionChangeState(Unit.INSTANCE);
            selectionManager.updateHandleOffsets();
            selectionManager.updateSelectionToolbar();
        }
        return Unit.INSTANCE;
    }

    public static Unit e(SelectionManager selectionManager, long j) {
        if (selectionManager.selectionRegistrar.getSubselections().containsKey(j)) {
            selectionManager.onRelease();
            selectionManager.setSelection(null);
        }
        return Unit.INSTANCE;
    }

    public static Rect f(SelectionManager selectionManager, LayoutCoordinates layoutCoordinates) {
        Rect derivedContentRect = selectionManager.getDerivedContentRect();
        if (derivedContentRect == null) {
            return null;
        }
        LayoutCoordinates layoutCoordinates2 = selectionManager.containerLayoutCoordinates;
        if (layoutCoordinates2 != null) {
            return TextContextMenuToolbarHandlerModifierKt.translateRootToDestination(derivedContentRect, layoutCoordinates2, layoutCoordinates);
        }
        InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Required value was null.");
        wq6.a();
        return null;
    }

    public static Unit g(SelectionManager selectionManager, LayoutCoordinates layoutCoordinates) {
        selectionManager.setContainerLayoutCoordinates(layoutCoordinates);
        return Unit.INSTANCE;
    }

    private final Rect getContentRect() {
        LayoutCoordinates layoutCoordinates;
        getPositionChangeState();
        if (getSelection() == null || (layoutCoordinates = this.containerLayoutCoordinates) == null || !layoutCoordinates.isAttached()) {
            return null;
        }
        List<Selectable> listSort = this.selectionRegistrar.sort(requireContainerCoordinates$foundation());
        ArrayList arrayList = new ArrayList(listSort.size());
        int size = listSort.size();
        for (int i = 0; i < size; i++) {
            Selectable selectable = listSort.get(i);
            Selection selection = this.selectionRegistrar.getSubselections().get(selectable.getSelectableId());
            Pair pair = selection != null ? TuplesKt.to(selectable, selection) : null;
            if (pair != null) {
                arrayList.add(pair);
            }
        }
        List listFirstAndLast = SelectionManagerKt.firstAndLast(arrayList);
        if (listFirstAndLast.isEmpty()) {
            return null;
        }
        Rect selectedRegionRect = SelectionManagerKt.getSelectedRegionRect(listFirstAndLast, layoutCoordinates);
        if (Intrinsics.areEqual(selectedRegionRect, SelectionManagerKt.invertedInfiniteRect)) {
            return null;
        }
        Rect rectIntersect = SelectionManagerKt.visibleBounds(layoutCoordinates).intersect(selectedRegionRect);
        if (rectIntersect.getRight() - rectIntersect.getLeft() < 0.0f || rectIntersect.getBottom() - rectIntersect.getTop() < 0.0f) {
            return null;
        }
        Rect rect = rectIntersect.translate-k-4lQ0M(LayoutCoordinatesKt.positionInRoot(layoutCoordinates));
        return Rect.copy$default(rect, 0.0f, 0.0f, 0.0f, rect.getBottom() + (SelectionHandlesKt.getHandleHeight() * 4.0f), 7, (Object) null);
    }

    private final Rect getDerivedContentRect() {
        return (Rect) this.derivedContentRect.getValue();
    }

    private final Unit getPositionChangeState() {
        this.positionChangeState.getValue();
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void getPreviousSelectionLayout$foundation$annotations() {
    }

    /* JADX INFO: renamed from: getSelectionLayout-Wko1d7g, reason: not valid java name */
    private final SelectionLayout m1773getSelectionLayoutWko1d7g(long position, long previousHandlePosition, boolean isStartHandle) {
        LayoutCoordinates layoutCoordinatesRequireContainerCoordinates$foundation = requireContainerCoordinates$foundation();
        List<Selectable> listSort = this.selectionRegistrar.sort(layoutCoordinatesRequireContainerCoordinates$foundation);
        final MutableLongIntMap mutableLongIntMapMutableLongIntMapOf = LongIntMapKt.mutableLongIntMapOf();
        List<Selectable> list = listSort;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            mutableLongIntMapMutableLongIntMapOf.set(listSort.get(i).getSelectableId(), i);
        }
        SelectionLayoutBuilder selectionLayoutBuilder = new SelectionLayoutBuilder(position, previousHandlePosition, layoutCoordinatesRequireContainerCoordinates$foundation, isStartHandle, (previousHandlePosition & SieveCacheKt.InvalidMapping) == 9205357640488583168L ? null : getSelection(), new Comparator() { // from class: androidx.compose.foundation.text.selection.SelectionManager$getSelectionLayout-Wko1d7g$$inlined$compareBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Integer.valueOf(mutableLongIntMapMutableLongIntMapOf.get(((Number) t).longValue())), Integer.valueOf(mutableLongIntMapMutableLongIntMapOf.get(((Number) t2).longValue())));
            }
        }, null);
        int size2 = list.size();
        for (int i2 = 0; i2 < size2; i2++) {
            listSort.get(i2).appendSelectableInfoToBuilder(selectionLayoutBuilder);
        }
        return selectionLayoutBuilder.build();
    }

    private final boolean getShouldShowMagnifier() {
        return isDraggingInProgress() && isInTouchMode() && !isTriviallyCollapsedSelection$foundation();
    }

    public static /* synthetic */ void getToolbarRequester$foundation$annotations() {
    }

    public static Unit h(SelectionManager selectionManager, long j) {
        Selection.AnchorInfo end;
        Selection.AnchorInfo start;
        Selection selection = selectionManager.getSelection();
        if (selection != null && (start = selection.getStart()) != null && j == start.getSelectableId()) {
            selectionManager.m1778setStartHandlePosition_kEHs6E(null);
        }
        Selection selection2 = selectionManager.getSelection();
        if (selection2 != null && (end = selection2.getEnd()) != null && j == end.getSelectableId()) {
            selectionManager.m1777setEndHandlePosition_kEHs6E(null);
        }
        if (selectionManager.selectionRegistrar.getSubselections().containsKey(j)) {
            selectionManager.updateSelectionToolbar();
        }
        return Unit.INSTANCE;
    }

    public static Unit i(SelectionManager selectionManager) {
        selectionManager.setShowToolbar$foundation(true);
        selectionManager.setDraggingHandle(null);
        selectionManager.m1774setCurrentDragPosition_kEHs6E(null);
        if (selectionManager.isLongPressOrClickSelection && selectionManager.isNonEmptySelection$foundation()) {
            selectionManager.suggestSelectionForLongPressOrDoubleClick();
        }
        selectionManager.isLongPressOrClickSelection = false;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isDraggingInProgress() {
        return getDraggingHandle() != null;
    }

    public static boolean j(SelectionManager selectionManager, boolean z, LayoutCoordinates layoutCoordinates, Offset offset, Offset offset2, boolean z2, SelectionAdjustment selectionAdjustment) {
        long jM1772convertToContainerCoordinatesR5De75A = selectionManager.m1772convertToContainerCoordinatesR5De75A(layoutCoordinates, offset.unbox-impl());
        long jM1772convertToContainerCoordinatesR5De75A2 = selectionManager.m1772convertToContainerCoordinatesR5De75A(layoutCoordinates, offset2.unbox-impl());
        selectionManager.setInTouchMode(z);
        return selectionManager.m1787updateSelectionqNKwrvQ$foundation(Offset.box-impl(jM1772convertToContainerCoordinatesR5De75A), jM1772convertToContainerCoordinatesR5De75A2, z2, selectionAdjustment);
    }

    public static Unit k(SelectionManager selectionManager, boolean z, long j) {
        Pair<Selection, LongObjectMap<Selection>> pairSelectAllInSelectable$foundation = selectionManager.selectAllInSelectable$foundation(j, selectionManager.getSelection());
        Selection selection = (Selection) pairSelectAllInSelectable$foundation.component1();
        LongObjectMap<Selection> longObjectMap = (LongObjectMap) pairSelectAllInSelectable$foundation.component2();
        if (!Intrinsics.areEqual(selection, selectionManager.getSelection())) {
            selectionManager.selectionRegistrar.setSubselections(longObjectMap);
            selectionManager.onSelectionChange.invoke(selection);
        }
        selectionManager.setInTouchMode(z);
        FocusRequester.requestFocus-3ESFkO8$default(selectionManager.focusRequester, 0, 1, (Object) null);
        selectionManager.setShowToolbar$foundation(false);
        return Unit.INSTANCE;
    }

    public static Unit l(SelectionManager selectionManager) {
        selectionManager.onRelease();
        return Unit.INSTANCE;
    }

    public static Unit m(SelectionManager selectionManager, Selection selection) {
        selectionManager.setSelection(selection);
        return Unit.INSTANCE;
    }

    public static Unit n(SelectionManager selectionManager, FocusState focusState) {
        if (!focusState.getHasFocus() && selectionManager.getHasFocus()) {
            selectionManager.onRelease();
        }
        selectionManager.setHasFocus(focusState.getHasFocus());
        return Unit.INSTANCE;
    }

    public static Unit o(SelectionManager selectionManager, boolean z) {
        selectionManager.setInTouchMode(z);
        return Unit.INSTANCE;
    }

    private final Modifier onClearSelectionRequested(Modifier modifier, final Function0<Unit> function0) {
        return SuspendingPointerInputFilterKt.pointerInput(modifier, Unit.INSTANCE, new PointerInputEventHandler() { // from class: androidx.compose.foundation.text.selection.SelectionManager.onClearSelectionRequested.1

            /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionManager$onClearSelectionRequested$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionManager$onClearSelectionRequested$1$1", f = "SelectionManager.kt", i = {0}, l = {994, 1000}, m = "invokeSuspend", n = {"$this$awaitEachGesture"}, s = {"L$0"}, v = 1)
            public static final class C00411 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ Function0<Unit> $block;
                private /* synthetic */ Object L$0;
                int label;
                final /* synthetic */ SelectionManager this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00411(SelectionManager selectionManager, Function0<Unit> function0, Continuation<? super C00411> continuation) {
                    super(2, continuation);
                    this.this$0 = selectionManager;
                    this.$block = function0;
                }

                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    C00411 c00411 = new C00411(this.this$0, this.$block, continuation);
                    c00411.L$0 = obj;
                    return c00411;
                }

                public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                    return create(awaitPointerEventScope, continuation).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Code restructure failed: missing block: B:15:0x0049, code lost:
                
                    if (r11 == r0) goto L16;
                 */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public final Object invokeSuspend(Object obj) {
                    C00411 c00411;
                    AwaitPointerEventScope awaitPointerEventScope;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        AwaitPointerEventScope awaitPointerEventScope2 = (AwaitPointerEventScope) this.L$0;
                        this.L$0 = awaitPointerEventScope2;
                        this.label = 1;
                        c00411 = this;
                        obj = TapGestureDetectorKt.awaitPrimaryFirstDown$default(awaitPointerEventScope2, false, null, c00411, 2, null);
                        if (obj != coroutine_suspended) {
                            awaitPointerEventScope = awaitPointerEventScope2;
                        }
                        return coroutine_suspended;
                    }
                    if (i == 1) {
                        awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        c00411 = this;
                    } else {
                        if (i != 2) {
                            k2d.a("call to 'resume' before 'invoke' with coroutine");
                            return null;
                        }
                        ResultKt.throwOnFailure(obj);
                        c00411 = this;
                    }
                    if (!((Boolean) obj).booleanValue() && !c00411.this$0.isDraggingInProgress()) {
                        c00411.$block.invoke();
                    }
                    return Unit.INSTANCE;
                    PointerEventPass pointerEventPass = PointerEventPass.Initial;
                    c00411.L$0 = null;
                    c00411.label = 2;
                    obj = DragGestureDetectorKt.awaitAllPointersUpWithSlopDetection(awaitPointerEventScope, (PointerInputChange) obj, pointerEventPass, c00411);
                }
            }

            public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
                Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture(pointerInputScope, new C00411(SelectionManager.this, function0, null), continuation);
                return objAwaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitEachGesture : Unit.INSTANCE;
            }
        });
    }

    private final void selectionChanged(SelectionLayout selectionLayout, Selection newSelection) {
        HapticFeedback hapticFeedback;
        if (shouldPerformHaptics$foundation() && (hapticFeedback = this.hapticFeedBack) != null) {
            hapticFeedback.performHapticFeedback-CdsT49E(HapticFeedbackType.Companion.getTextHandleMove-5zf0vsI());
        }
        this.selectionRegistrar.setSubselections(selectionLayout.createSubSelections(newSelection));
        this.onSelectionChange.invoke(newSelection);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setCurrentDragPosition-_kEHs6E, reason: not valid java name */
    public final void m1774setCurrentDragPosition_kEHs6E(Offset offset) {
        this.currentDragPosition.setValue(offset);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setDragBeginPosition-k-4lQ0M, reason: not valid java name */
    public final void m1775setDragBeginPositionk4lQ0M(long j) {
        this.dragBeginPosition.setValue(Offset.box-impl(j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setDragTotalDistance-k-4lQ0M, reason: not valid java name */
    public final void m1776setDragTotalDistancek4lQ0M(long j) {
        this.dragTotalDistance.setValue(Offset.box-impl(j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setDraggingHandle(Handle handle) {
        this.draggingHandle.setValue(handle);
    }

    /* JADX INFO: renamed from: setEndHandlePosition-_kEHs6E, reason: not valid java name */
    private final void m1777setEndHandlePosition_kEHs6E(Offset offset) {
        this.endHandlePosition.setValue(offset);
    }

    private final void setPositionChangeState(Unit unit) {
        this.positionChangeState.setValue(unit);
    }

    /* JADX INFO: renamed from: setStartHandlePosition-_kEHs6E, reason: not valid java name */
    private final void m1778setStartHandlePosition_kEHs6E(Offset offset) {
        this.startHandlePosition.setValue(offset);
    }

    /* JADX INFO: renamed from: startSelection-9KIMszo, reason: not valid java name */
    private final void m1779startSelection9KIMszo(long position, boolean isStartHandle, SelectionAdjustment adjustment) {
        this.previousSelectionLayout = null;
        m1786updateSelectionjyLRC_s$foundation(position, Offset.Companion.getUnspecified-F1C5BW0(), isStartHandle, adjustment);
    }

    private final void suggestSelectionForLongPressOrDoubleClick() {
        int iNextIndex;
        CoroutineScope coroutineScope;
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        Ref.LongRef longRef = new Ref.LongRef();
        List<Selectable> listSort = this.selectionRegistrar.sort(requireContainerCoordinates$foundation());
        ListIterator<Selectable> listIterator = listSort.listIterator(listSort.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                iNextIndex = -1;
                break;
            }
            Selection selection = this.selectionRegistrar.getSubselections().get(listIterator.previous().getSelectableId());
            if (selection != null && selection.getStart().getOffset() != selection.getEnd().getOffset()) {
                iNextIndex = listIterator.nextIndex();
                break;
            }
        }
        if (iNextIndex != -1) {
            int size = listSort.size();
            int i = 0;
            while (i < size) {
                Selectable selectable = listSort.get(i);
                Selection selection2 = this.selectionRegistrar.getSubselections().get(selectable.getSelectableId());
                if (selection2 != null) {
                    AnnotatedString text = selectable.getText();
                    long jTextRange = TextRangeKt.TextRange(selection2.getStart().getOffset(), selection2.getEnd().getOffset());
                    boolean z = i >= iNextIndex;
                    long selectableId = selectable.getSelectableId();
                    if (!z) {
                        break;
                    }
                    objectRef.element = text;
                    objectRef2.element = TextRange.box-impl(jTextRange);
                    longRef.element = selectableId;
                    break;
                }
                i++;
            }
        }
        Object obj = objectRef.element;
        if (obj == null || objectRef2.element == null || longRef.element == 0 || ((CharSequence) obj).length() <= 0 || (coroutineScope = this.coroutineScope) == null) {
            return;
        }
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass2(objectRef, objectRef2, longRef, null), 3, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void toolbarCopy() {
        copy$foundation();
        onRelease();
    }

    /* JADX WARN: Code duplicated, block: B:36:0x007b  */
    private final void updateHandleOffsets() {
        Offset offset;
        Selection.AnchorInfo end;
        Selection.AnchorInfo start;
        Selection selection = getSelection();
        LayoutCoordinates layoutCoordinates = this.containerLayoutCoordinates;
        Offset offset2 = null;
        Selectable anchorSelectable$foundation = (selection == null || (start = selection.getStart()) == null) ? null : getAnchorSelectable$foundation(start);
        Selectable anchorSelectable$foundation2 = (selection == null || (end = selection.getEnd()) == null) ? null : getAnchorSelectable$foundation(end);
        LayoutCoordinates layoutCoordinates2 = anchorSelectable$foundation != null ? anchorSelectable$foundation.getLayoutCoordinates() : null;
        LayoutCoordinates layoutCoordinates3 = anchorSelectable$foundation2 != null ? anchorSelectable$foundation2.getLayoutCoordinates() : null;
        if (selection == null || layoutCoordinates == null || !layoutCoordinates.isAttached() || (layoutCoordinates2 == null && layoutCoordinates3 == null)) {
            m1778setStartHandlePosition_kEHs6E(null);
            m1777setEndHandlePosition_kEHs6E(null);
            return;
        }
        Rect rectVisibleBounds = SelectionManagerKt.visibleBounds(layoutCoordinates);
        if (layoutCoordinates2 != null) {
            long jMo1741getHandlePositiondBAh8RU = anchorSelectable$foundation.mo1741getHandlePositiondBAh8RU(selection, true);
            if ((jMo1741getHandlePositiondBAh8RU & SieveCacheKt.InvalidMapping) == 9205357640488583168L) {
                offset = null;
            } else {
                offset = Offset.box-impl(layoutCoordinates.localPositionOf-R5De75A(layoutCoordinates2, jMo1741getHandlePositiondBAh8RU));
                long j = offset.unbox-impl();
                if (getDraggingHandle() != Handle.SelectionStart && !SelectionManagerKt.m1793containsInclusiveUv8p0NA(rectVisibleBounds, j)) {
                    offset = null;
                }
            }
        } else {
            offset = null;
        }
        m1778setStartHandlePosition_kEHs6E(offset);
        if (layoutCoordinates3 != null) {
            long jMo1741getHandlePositiondBAh8RU2 = anchorSelectable$foundation2.mo1741getHandlePositiondBAh8RU(selection, false);
            if ((SieveCacheKt.InvalidMapping & jMo1741getHandlePositiondBAh8RU2) != 9205357640488583168L) {
                Offset offset3 = Offset.box-impl(layoutCoordinates.localPositionOf-R5De75A(layoutCoordinates3, jMo1741getHandlePositiondBAh8RU2));
                long j2 = offset3.unbox-impl();
                if (getDraggingHandle() == Handle.SelectionEnd || SelectionManagerKt.m1793containsInclusiveUv8p0NA(rectVisibleBounds, j2)) {
                    offset2 = offset3;
                }
            }
        }
        m1777setEndHandlePosition_kEHs6E(offset2);
    }

    private final void updateSelectionTextToolbar() {
        TextToolbar textToolbar = this.textToolbar;
        if (textToolbar == null) {
            return;
        }
        if (!this.showToolbar || !isInTouchMode()) {
            if (textToolbar.getStatus() == TextToolbarStatus.Shown) {
                textToolbar.hide();
            }
        } else {
            Rect contentRect = getContentRect();
            if (contentRect == null) {
                return;
            }
            TextToolbar.showMenu$default(textToolbar, contentRect, canCopy$foundation() ? new C01971(this) : null, (Function0) null, (Function0) null, isEntireContainerSelected$foundation() ? null : new C01982(this), (Function0) null, 12, (Object) null);
        }
    }

    private final void updateSelectionToolbar() {
        if (getHasFocus()) {
            if (!ComposeFoundationFlags.isNewContextMenuEnabled) {
                updateSelectionTextToolbar();
                return;
            }
            if (!this.showToolbar || !isInTouchMode()) {
                this.toolbarRequester.hide();
            } else {
                if (getDerivedContentRect() == null) {
                    return;
                }
                this.toolbarRequester.show();
            }
        }
    }

    public final boolean canCopy$foundation() {
        return this.onCopyHandler != null && isNonEmptySelection$foundation();
    }

    public final void copy$foundation() {
        Function1<? super AnnotatedString, Unit> function1;
        AnnotatedString selectedText$foundation = getSelectedText$foundation();
        if (selectedText$foundation != null) {
            if (selectedText$foundation.length() <= 0) {
                selectedText$foundation = null;
            }
            if (selectedText$foundation == null || (function1 = this.onCopyHandler) == null) {
                return;
            }
            function1.invoke(selectedText$foundation);
        }
    }

    public final void forEachSelectableWithSelection$foundation(Function4<? super Long, ? super AnnotatedString, ? super TextRange, ? super Boolean, Boolean> block) {
        int iNextIndex;
        List<Selectable> listSort = this.selectionRegistrar.sort(requireContainerCoordinates$foundation());
        ListIterator<Selectable> listIterator = listSort.listIterator(listSort.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                iNextIndex = -1;
                break;
            }
            Selection selection = this.selectionRegistrar.getSubselections().get(listIterator.previous().getSelectableId());
            if (selection != null && selection.getStart().getOffset() != selection.getEnd().getOffset()) {
                iNextIndex = listIterator.nextIndex();
                break;
            }
        }
        if (iNextIndex == -1) {
            return;
        }
        int size = listSort.size();
        int i = 0;
        while (i < size) {
            Selectable selectable = listSort.get(i);
            Selection selection2 = this.selectionRegistrar.getSubselections().get(selectable.getSelectableId());
            if (selection2 != null) {
                if (!((Boolean) block.invoke(Long.valueOf(selectable.getSelectableId()), selectable.getText(), TextRange.box-impl(TextRangeKt.TextRange(selection2.getStart().getOffset(), selection2.getEnd().getOffset())), Boolean.valueOf(i >= iNextIndex))).booleanValue()) {
                    return;
                }
            }
            i++;
        }
    }

    public final Selectable getAnchorSelectable$foundation(Selection.AnchorInfo anchor) {
        return this.selectionRegistrar.getSelectableMap$foundation().get(anchor.getSelectableId());
    }

    public final LayoutCoordinates getContainerLayoutCoordinates() {
        return this.containerLayoutCoordinates;
    }

    public final Modifier getContextMenuAreaModifier() {
        return TextContextMenuToolbarHandlerModifierKt.textContextMenuToolbarHandler$default(TextContextMenuGesturesModifierKt.showTextContextMenuOnSecondaryClick(Modifier.Companion, new SelectionManager$contextMenuAreaModifier$1(this, null)), this.toolbarRequester, new SelectionManager$contextMenuAreaModifier$2(this, null), null, new Function1() { // from class: s4d
            public final Object invoke(Object obj) {
                return SelectionManager.f(this.b, (LayoutCoordinates) obj);
            }
        }, 4, null);
    }

    public final Pair<AnnotatedString, TextRange> getContextTextAndSelection$foundation() {
        int iNextIndex;
        int i;
        int length;
        Pair<AnnotatedString, TextRange> pair;
        Pair<AnnotatedString, TextRange> pair2 = null;
        if (getSelection() == null || this.selectionRegistrar.getSelectables$foundation().isEmpty()) {
            return null;
        }
        AnnotatedString.Builder builder = new AnnotatedString.Builder(0, 1, (DefaultConstructorMarker) null);
        List<Selectable> listSort = this.selectionRegistrar.sort(requireContainerCoordinates$foundation());
        ListIterator<Selectable> listIterator = listSort.listIterator(listSort.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                iNextIndex = -1;
                break;
            }
            Selection selection = this.selectionRegistrar.getSubselections().get(listIterator.previous().getSelectableId());
            if (selection != null && selection.getStart().getOffset() != selection.getEnd().getOffset()) {
                iNextIndex = listIterator.nextIndex();
                break;
            }
        }
        if (iNextIndex != -1) {
            int size = listSort.size();
            int i2 = 0;
            i = -1;
            length = -1;
            while (i2 < size) {
                Selectable selectable = listSort.get(i2);
                Selection selection2 = this.selectionRegistrar.getSubselections().get(selectable.getSelectableId());
                if (selection2 != null) {
                    AnnotatedString text = selectable.getText();
                    long jTextRange = TextRangeKt.TextRange(selection2.getStart().getOffset(), selection2.getEnd().getOffset());
                    boolean z = i2 >= iNextIndex;
                    selectable.getSelectableId();
                    if (i == -1) {
                        i = TextRange.getMin-impl(jTextRange);
                        builder.append(text, 0, TextRange.getMin-impl(jTextRange));
                    }
                    pair = pair2;
                    builder.append(text, TextRange.getMin-impl(jTextRange), TextRange.getMax-impl(jTextRange));
                    if (z) {
                        length = builder.getLength();
                        builder.append(text, TextRange.getMax-impl(jTextRange), text.length());
                    } else {
                        builder.append('\n');
                    }
                } else {
                    pair = pair2;
                }
                i2++;
                pair2 = pair;
            }
        } else {
            i = -1;
            length = -1;
        }
        Pair<AnnotatedString, TextRange> pair3 = pair2;
        return (i == -1 || length == -1) ? pair3 : new Pair<>(builder.toAnnotatedString(), TextRange.box-impl(TextRangeKt.TextRange(i, length)));
    }

    /* JADX INFO: renamed from: getCoroutineScope$foundation, reason: from getter */
    public final CoroutineScope getCoroutineScope() {
        return this.coroutineScope;
    }

    /* JADX INFO: renamed from: getCurrentDragPosition-_m7T9-E, reason: not valid java name */
    public final Offset m1780getCurrentDragPosition_m7T9E() {
        return (Offset) this.currentDragPosition.getValue();
    }

    /* JADX INFO: renamed from: getDragBeginPosition-F1C5BW0$foundation, reason: not valid java name */
    public final long m1781getDragBeginPositionF1C5BW0$foundation() {
        return ((Offset) this.dragBeginPosition.getValue()).unbox-impl();
    }

    /* JADX INFO: renamed from: getDragTotalDistance-F1C5BW0$foundation, reason: not valid java name */
    public final long m1782getDragTotalDistanceF1C5BW0$foundation() {
        return ((Offset) this.dragTotalDistance.getValue()).unbox-impl();
    }

    public final Handle getDraggingHandle() {
        return (Handle) this.draggingHandle.getValue();
    }

    public final float getEndHandleLineHeight() {
        Selectable anchorSelectable$foundation;
        Selection selection = getSelection();
        if (selection == null || (anchorSelectable$foundation = getAnchorSelectable$foundation(selection.getEnd())) == null) {
            return 0.0f;
        }
        return anchorSelectable$foundation.getLineHeight(selection.getEnd().getOffset());
    }

    /* JADX INFO: renamed from: getEndHandlePosition-_m7T9-E, reason: not valid java name */
    public final Offset m1783getEndHandlePosition_m7T9E() {
        return (Offset) this.endHandlePosition.getValue();
    }

    public final FocusRequester getFocusRequester() {
        return this.focusRequester;
    }

    public final HapticFeedback getHapticFeedBack() {
        return this.hapticFeedBack;
    }

    public final boolean getHasFocus() {
        return ((Boolean) this.hasFocus.getValue()).booleanValue();
    }

    public final Modifier getModifier() {
        Modifier modifierSelectionMagnifier = Modifier.Companion;
        Modifier modifierOnKeyEvent = KeyInputModifierKt.onKeyEvent(SelectionGesturesKt.updateSelectionTouchMode(FocusableKt.focusable$default(FocusChangedModifierKt.onFocusChanged(FocusRequesterModifierKt.focusRequester(OnGloballyPositionedModifierKt.onGloballyPositioned(onClearSelectionRequested(modifierSelectionMagnifier, new Function0() { // from class: j4d
            public final Object invoke() {
                return SelectionManager.l(this.b);
            }
        }), new Function1() { // from class: p4d
            public final Object invoke(Object obj) {
                return SelectionManager.g(this.b, (LayoutCoordinates) obj);
            }
        }), this.focusRequester), new Function1() { // from class: q4d
            public final Object invoke(Object obj) {
                return SelectionManager.n(this.b, (FocusState) obj);
            }
        }), false, null, 3, null), new Function1() { // from class: r4d
            public final Object invoke(Object obj) {
                return SelectionManager.o(this.b, ((Boolean) obj).booleanValue());
            }
        }), new Function1<KeyEvent, Boolean>() { // from class: androidx.compose.foundation.text.selection.SelectionManager$modifier$5
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return m1789invokeZmokQxo(((KeyEvent) obj).unbox-impl());
            }

            /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
            public final Boolean m1789invokeZmokQxo(android.view.KeyEvent keyEvent) {
                boolean z;
                if (SelectionManager_androidKt.m1795isCopyKeyEventZmokQxo(keyEvent)) {
                    this.this$0.copy$foundation();
                    z = true;
                } else {
                    z = false;
                }
                return Boolean.valueOf(z);
            }
        });
        if (getShouldShowMagnifier()) {
            modifierSelectionMagnifier = SelectionManager_androidKt.selectionMagnifier(modifierSelectionMagnifier, this);
        }
        return addContextMenuComponents(modifierOnKeyEvent.then(modifierSelectionMagnifier));
    }

    public final Function1<AnnotatedString, Unit> getOnCopyHandler() {
        return this.onCopyHandler;
    }

    public final Function1<Selection, Unit> getOnSelectionChange() {
        return this.onSelectionChange;
    }

    /* JADX INFO: renamed from: getPlatformSelectionBehaviors$foundation, reason: from getter */
    public final PlatformSelectionBehaviors getPlatformSelectionBehaviors() {
        return this.platformSelectionBehaviors;
    }

    /* JADX INFO: renamed from: getPreviousSelectionLayout$foundation, reason: from getter */
    public final SelectionLayout getPreviousSelectionLayout() {
        return this.previousSelectionLayout;
    }

    public final AnnotatedString getSelectedText$foundation() {
        int iNextIndex;
        if (getSelection() == null || this.selectionRegistrar.getSubselections().isEmpty()) {
            return null;
        }
        AnnotatedString.Builder builder = new AnnotatedString.Builder(0, 1, (DefaultConstructorMarker) null);
        List<Selectable> listSort = this.selectionRegistrar.sort(requireContainerCoordinates$foundation());
        ListIterator<Selectable> listIterator = listSort.listIterator(listSort.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                iNextIndex = -1;
                break;
            }
            Selection selection = this.selectionRegistrar.getSubselections().get(listIterator.previous().getSelectableId());
            if (selection != null && selection.getStart().getOffset() != selection.getEnd().getOffset()) {
                iNextIndex = listIterator.nextIndex();
                break;
            }
        }
        if (iNextIndex != -1) {
            int size = listSort.size();
            int i = 0;
            while (i < size) {
                Selectable selectable = listSort.get(i);
                Selection selection2 = this.selectionRegistrar.getSubselections().get(selectable.getSelectableId());
                if (selection2 != null) {
                    AnnotatedString text = selectable.getText();
                    long jTextRange = TextRangeKt.TextRange(selection2.getStart().getOffset(), selection2.getEnd().getOffset());
                    boolean z = i >= iNextIndex;
                    selectable.getSelectableId();
                    builder.append(text, TextRange.getMin-impl(jTextRange), TextRange.getMax-impl(jTextRange));
                    if (!z) {
                        builder.append('\n');
                    }
                }
                i++;
            }
        }
        return builder.toAnnotatedString();
    }

    public final Selection getSelection() {
        return (Selection) this._selection.getValue();
    }

    /* JADX INFO: renamed from: getShowToolbar$foundation, reason: from getter */
    public final boolean getShowToolbar() {
        return this.showToolbar;
    }

    public final float getStartHandleLineHeight() {
        Selectable anchorSelectable$foundation;
        Selection selection = getSelection();
        if (selection == null || (anchorSelectable$foundation = getAnchorSelectable$foundation(selection.getStart())) == null) {
            return 0.0f;
        }
        return anchorSelectable$foundation.getLineHeight(selection.getStart().getOffset());
    }

    /* JADX INFO: renamed from: getStartHandlePosition-_m7T9-E, reason: not valid java name */
    public final Offset m1784getStartHandlePosition_m7T9E() {
        return (Offset) this.startHandlePosition.getValue();
    }

    public final TextToolbar getTextToolbar() {
        return this.textToolbar;
    }

    /* JADX INFO: renamed from: getToolbarRequester$foundation, reason: from getter */
    public final ToolbarRequester getToolbarRequester() {
        return this.toolbarRequester;
    }

    public final TextDragObserver handleDragObserver(final boolean isStartHandle) {
        return new TextDragObserver() { // from class: androidx.compose.foundation.text.selection.SelectionManager.handleDragObserver.1
            private final void done() {
                this.setShowToolbar$foundation(true);
                this.setDraggingHandle(null);
                this.m1774setCurrentDragPosition_kEHs6E(null);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onCancel() {
                done();
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onDown-k-4lQ0M */
            public void mo1409onDownk4lQ0M(long point) {
                LayoutCoordinates layoutCoordinates;
                boolean z = isStartHandle;
                SelectionManager selectionManager = this;
                Offset offsetM1784getStartHandlePosition_m7T9E = z ? selectionManager.m1784getStartHandlePosition_m7T9E() : selectionManager.m1783getEndHandlePosition_m7T9E();
                if (offsetM1784getStartHandlePosition_m7T9E != null) {
                    offsetM1784getStartHandlePosition_m7T9E.unbox-impl();
                    Selection selection = this.getSelection();
                    if (selection == null) {
                        return;
                    }
                    Selectable anchorSelectable$foundation = this.getAnchorSelectable$foundation(isStartHandle ? selection.getStart() : selection.getEnd());
                    if (anchorSelectable$foundation == null || (layoutCoordinates = anchorSelectable$foundation.getLayoutCoordinates()) == null) {
                        return;
                    }
                    long jMo1741getHandlePositiondBAh8RU = anchorSelectable$foundation.mo1741getHandlePositiondBAh8RU(selection, isStartHandle);
                    if ((SieveCacheKt.InvalidMapping & jMo1741getHandlePositiondBAh8RU) == 9205357640488583168L) {
                        return;
                    }
                    long jM1764getAdjustedCoordinatesk4lQ0M = SelectionHandlesKt.m1764getAdjustedCoordinatesk4lQ0M(jMo1741getHandlePositiondBAh8RU);
                    SelectionManager selectionManager2 = this;
                    selectionManager2.m1774setCurrentDragPosition_kEHs6E(Offset.box-impl(selectionManager2.requireContainerCoordinates$foundation().localPositionOf-R5De75A(layoutCoordinates, jM1764getAdjustedCoordinatesk4lQ0M)));
                    this.setDraggingHandle(isStartHandle ? Handle.SelectionStart : Handle.SelectionEnd);
                    this.setShowToolbar$foundation(false);
                }
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onDrag-k-4lQ0M */
            public void mo1410onDragk4lQ0M(long delta) {
                if (this.getDraggingHandle() == null) {
                    return;
                }
                SelectionManager selectionManager = this;
                selectionManager.m1776setDragTotalDistancek4lQ0M(Offset.plus-MK-Hz9U(selectionManager.m1782getDragTotalDistanceF1C5BW0$foundation(), delta));
                long j = Offset.plus-MK-Hz9U(this.m1781getDragBeginPositionF1C5BW0$foundation(), this.m1782getDragTotalDistanceF1C5BW0$foundation());
                if (this.m1787updateSelectionqNKwrvQ$foundation(Offset.box-impl(j), this.m1781getDragBeginPositionF1C5BW0$foundation(), isStartHandle, SelectionAdjustment.INSTANCE.getCharacterWithWordAccelerate())) {
                    this.m1775setDragBeginPositionk4lQ0M(j);
                    this.m1776setDragTotalDistancek4lQ0M(Offset.Companion.getZero-F1C5BW0());
                }
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onStart-3MmeM6k */
            public void mo1411onStart3MmeM6k(long startPoint, SelectionAdjustment selectionAdjustment) {
                if (this.getDraggingHandle() == null) {
                    return;
                }
                Selection selection = this.getSelection();
                selection.getClass();
                Selectable selectable = this.selectionRegistrar.getSelectableMap$foundation().get((isStartHandle ? selection.getStart() : selection.getEnd()).getSelectableId());
                if (selectable == null) {
                    InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("SelectionRegistrar should contain the current selection's selectableIds");
                    wq6.a();
                    return;
                }
                Selectable selectable2 = selectable;
                LayoutCoordinates layoutCoordinates = selectable2.getLayoutCoordinates();
                if (layoutCoordinates == null) {
                    InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Current selectable should have layout coordinates.");
                    wq6.a();
                    return;
                }
                long jMo1741getHandlePositiondBAh8RU = selectable2.mo1741getHandlePositiondBAh8RU(selection, isStartHandle);
                if ((SieveCacheKt.InvalidMapping & jMo1741getHandlePositiondBAh8RU) == 9205357640488583168L) {
                    return;
                }
                long jM1764getAdjustedCoordinatesk4lQ0M = SelectionHandlesKt.m1764getAdjustedCoordinatesk4lQ0M(jMo1741getHandlePositiondBAh8RU);
                SelectionManager selectionManager = this;
                selectionManager.m1775setDragBeginPositionk4lQ0M(selectionManager.requireContainerCoordinates$foundation().localPositionOf-R5De75A(layoutCoordinates, jM1764getAdjustedCoordinatesk4lQ0M));
                this.m1776setDragTotalDistancek4lQ0M(Offset.Companion.getZero-F1C5BW0());
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onStop() {
                done();
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onUp() {
                done();
            }
        };
    }

    public final boolean isEntireContainerSelected$foundation() {
        Selection selection;
        List<Selectable> listSort = this.selectionRegistrar.sort(requireContainerCoordinates$foundation());
        if (listSort.isEmpty()) {
            return true;
        }
        int size = listSort.size();
        for (int i = 0; i < size; i++) {
            Selectable selectable = listSort.get(i);
            AnnotatedString text = selectable.getText();
            if (text.length() != 0 && ((selection = this.selectionRegistrar.getSubselections().get(selectable.getSelectableId())) == null || Math.abs(selection.getStart().getOffset() - selection.getEnd().getOffset()) != text.length())) {
                return false;
            }
        }
        return true;
    }

    public final boolean isInTouchMode() {
        return ((Boolean) this._isInTouchMode.getValue()).booleanValue();
    }

    public final boolean isNonEmptySelection$foundation() {
        Selection selection = getSelection();
        if (selection == null || Intrinsics.areEqual(selection.getStart(), selection.getEnd())) {
            return false;
        }
        if (selection.getStart().getSelectableId() == selection.getEnd().getSelectableId()) {
            return true;
        }
        List<Selectable> listSort = this.selectionRegistrar.sort(requireContainerCoordinates$foundation());
        int size = listSort.size();
        for (int i = 0; i < size; i++) {
            Selection selection2 = this.selectionRegistrar.getSubselections().get(listSort.get(i).getSelectableId());
            if (selection2 != null && selection2.getStart().getOffset() != selection2.getEnd().getOffset()) {
                return true;
            }
        }
        return false;
    }

    public final boolean isTriviallyCollapsedSelection$foundation() {
        Selection selection = getSelection();
        if (selection == null) {
            return true;
        }
        return Intrinsics.areEqual(selection.getStart(), selection.getEnd());
    }

    public final void onRelease() {
        HapticFeedback hapticFeedback;
        this.selectionRegistrar.setSubselections(LongObjectMapKt.emptyLongObjectMap());
        setShowToolbar$foundation(false);
        if (getSelection() != null) {
            this.onSelectionChange.invoke((Object) null);
            if (!isInTouchMode() || (hapticFeedback = this.hapticFeedBack) == null) {
                return;
            }
            hapticFeedback.performHapticFeedback-CdsT49E(HapticFeedbackType.Companion.getTextHandleMove-5zf0vsI());
        }
    }

    public final LayoutCoordinates requireContainerCoordinates$foundation() {
        LayoutCoordinates layoutCoordinates = this.containerLayoutCoordinates;
        if (layoutCoordinates != null) {
            if (!layoutCoordinates.isAttached()) {
                InlineClassHelperKt.throwIllegalArgumentException("unattached coordinates");
            }
            return layoutCoordinates;
        }
        InlineClassHelperKt.throwIllegalArgumentExceptionForNullCheck("null coordinates");
        wq6.a();
        return null;
    }

    public final void selectAll$foundation() {
        List<Selectable> listSort = this.selectionRegistrar.sort(requireContainerCoordinates$foundation());
        if (listSort.isEmpty()) {
            return;
        }
        MutableLongObjectMap mutableLongObjectMapMutableLongObjectMapOf = LongObjectMapKt.mutableLongObjectMapOf();
        int size = listSort.size();
        Selection selection = null;
        Selection selection2 = null;
        for (int i = 0; i < size; i++) {
            Selectable selectable = listSort.get(i);
            Selection selectAllSelection = selectable.getSelectAllSelection();
            if (selectAllSelection != null) {
                if (selection == null) {
                    selection = selectAllSelection;
                }
                mutableLongObjectMapMutableLongObjectMapOf.put(selectable.getSelectableId(), selectAllSelection);
                selection2 = selectAllSelection;
            }
        }
        if (mutableLongObjectMapMutableLongObjectMapOf.isEmpty()) {
            return;
        }
        if (selection != selection2) {
            selection.getClass();
            Selection.AnchorInfo start = selection.getStart();
            selection2.getClass();
            selection = new Selection(start, selection2.getEnd(), false);
        }
        this.selectionRegistrar.setSubselections(mutableLongObjectMapMutableLongObjectMapOf);
        this.onSelectionChange.invoke(selection);
        this.previousSelectionLayout = null;
    }

    public final Pair<Selection, LongObjectMap<Selection>> selectAllInSelectable$foundation(long selectableId, Selection previousSelection) {
        HapticFeedback hapticFeedback;
        MutableLongObjectMap mutableLongObjectMapMutableLongObjectMapOf = LongObjectMapKt.mutableLongObjectMapOf();
        List<Selectable> listSort = this.selectionRegistrar.sort(requireContainerCoordinates$foundation());
        int size = listSort.size();
        Selection selectionMerge = null;
        for (int i = 0; i < size; i++) {
            Selectable selectable = listSort.get(i);
            Selection selectAllSelection = selectable.getSelectableId() == selectableId ? selectable.getSelectAllSelection() : null;
            if (selectAllSelection != null) {
                mutableLongObjectMapMutableLongObjectMapOf.set(selectable.getSelectableId(), selectAllSelection);
            }
            selectionMerge = SelectionManagerKt.merge(selectionMerge, selectAllSelection);
        }
        if (isInTouchMode() && !Intrinsics.areEqual(selectionMerge, previousSelection) && (hapticFeedback = this.hapticFeedBack) != null) {
            hapticFeedback.performHapticFeedback-CdsT49E(HapticFeedbackType.Companion.getTextHandleMove-5zf0vsI());
        }
        return new Pair<>(selectionMerge, mutableLongObjectMapMutableLongObjectMapOf);
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0035  */
    /* JADX INFO: renamed from: selectWordAtPositionIfNotAlreadySelected-k-4lQ0M, reason: not valid java name */
    public final void m1785selectWordAtPositionIfNotAlreadySelectedk4lQ0M(long position) {
        LayoutCoordinates layoutCoordinates;
        boolean zM1438isPositionInsideSelectionuaM50fQ;
        LayoutCoordinates layoutCoordinates2 = this.containerLayoutCoordinates;
        if (layoutCoordinates2 != null && layoutCoordinates2.isAttached()) {
            List<Selectable> selectables$foundation = this.selectionRegistrar.getSelectables$foundation();
            int size = selectables$foundation.size();
            for (int i = 0; i < size; i++) {
                Selectable selectable = selectables$foundation.get(i);
                Selection selection = this.selectionRegistrar.getSubselections().get(selectable.getSelectableId());
                if (selection == null || (layoutCoordinates = selectable.getLayoutCoordinates()) == null) {
                    zM1438isPositionInsideSelectionuaM50fQ = false;
                } else {
                    long j = layoutCoordinates.localPositionOf-R5De75A(layoutCoordinates2, position);
                    TextLayoutResult textLayoutResult = selectable.textLayoutResult();
                    if (textLayoutResult == null) {
                        zM1438isPositionInsideSelectionuaM50fQ = false;
                    } else {
                        zM1438isPositionInsideSelectionuaM50fQ = TextLayoutHelperKt.m1438isPositionInsideSelectionuaM50fQ(textLayoutResult, j, TextRange.box-impl(selection.m1759toTextRanged9O1mEE()));
                    }
                }
                if (zM1438isPositionInsideSelectionuaM50fQ) {
                    return;
                }
            }
            m1779startSelection9KIMszo(position, true, SelectionAdjustment.INSTANCE.getWord());
        }
    }

    public final void setContainerLayoutCoordinates(LayoutCoordinates layoutCoordinates) {
        this.containerLayoutCoordinates = layoutCoordinates;
        if (!getHasFocus() || getSelection() == null) {
            return;
        }
        Offset offset = layoutCoordinates != null ? Offset.box-impl(LayoutCoordinatesKt.positionInWindow(layoutCoordinates)) : null;
        if (Intrinsics.areEqual(this.previousPosition, offset)) {
            return;
        }
        this.previousPosition = offset;
        updateHandleOffsets();
        updateSelectionToolbar();
    }

    public final void setCoroutineScope$foundation(CoroutineScope coroutineScope) {
        this.coroutineScope = coroutineScope;
    }

    public final void setFocusRequester(FocusRequester focusRequester) {
        this.focusRequester = focusRequester;
    }

    public final void setHapticFeedBack(HapticFeedback hapticFeedback) {
        this.hapticFeedBack = hapticFeedback;
    }

    public final void setHasFocus(boolean z) {
        this.hasFocus.setValue(Boolean.valueOf(z));
    }

    public final void setInTouchMode(boolean z) {
        if (((Boolean) this._isInTouchMode.getValue()).booleanValue() != z) {
            this._isInTouchMode.setValue(Boolean.valueOf(z));
            updateSelectionToolbar();
        }
    }

    public final void setOnCopyHandler(Function1<? super AnnotatedString, Unit> function1) {
        this.onCopyHandler = function1;
    }

    public final void setOnSelectionChange(final Function1<? super Selection, Unit> function1) {
        this.onSelectionChange = new Function1() { // from class: o4d
            public final Object invoke(Object obj) {
                return SelectionManager.b(this.b, function1, (Selection) obj);
            }
        };
    }

    public final void setPlatformSelectionBehaviors$foundation(PlatformSelectionBehaviors platformSelectionBehaviors) {
        this.platformSelectionBehaviors = platformSelectionBehaviors;
    }

    public final void setPreviousSelectionLayout$foundation(SelectionLayout selectionLayout) {
        this.previousSelectionLayout = selectionLayout;
    }

    public final void setSelection(Selection selection) {
        this._selection.setValue(selection);
        if (selection != null) {
            updateHandleOffsets();
        }
    }

    public final void setShowToolbar$foundation(boolean z) {
        this.showToolbar = z;
        updateSelectionToolbar();
    }

    public final void setTextToolbar(TextToolbar textToolbar) {
        this.textToolbar = textToolbar;
    }

    public final void setToolbarRequester$foundation(ToolbarRequester toolbarRequester) {
        this.toolbarRequester = toolbarRequester;
    }

    public final boolean shouldPerformHaptics$foundation() {
        if (isInTouchMode()) {
            List<Selectable> selectables$foundation = this.selectionRegistrar.getSelectables$foundation();
            int size = selectables$foundation.size();
            for (int i = 0; i < size; i++) {
                if (selectables$foundation.get(i).getText().length() > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: updateSelection-jyLRC_s$foundation, reason: not valid java name */
    public final boolean m1786updateSelectionjyLRC_s$foundation(long position, long previousHandlePosition, boolean isStartHandle, SelectionAdjustment adjustment) {
        setDraggingHandle(isStartHandle ? Handle.SelectionStart : Handle.SelectionEnd);
        m1774setCurrentDragPosition_kEHs6E(Offset.box-impl(position));
        SelectionLayout selectionLayoutM1773getSelectionLayoutWko1d7g = m1773getSelectionLayoutWko1d7g(position, previousHandlePosition, isStartHandle);
        if (selectionLayoutM1773getSelectionLayoutWko1d7g == null || !selectionLayoutM1773getSelectionLayoutWko1d7g.shouldRecomputeSelection(this.previousSelectionLayout)) {
            return false;
        }
        Selection selectionAdjust = adjustment.adjust(selectionLayoutM1773getSelectionLayoutWko1d7g);
        if (!Intrinsics.areEqual(selectionAdjust, getSelection())) {
            selectionChanged(selectionLayoutM1773getSelectionLayoutWko1d7g, selectionAdjust);
            this.isLongPressOrClickSelection = false;
        }
        this.previousSelectionLayout = selectionLayoutM1773getSelectionLayoutWko1d7g;
        return true;
    }

    /* JADX INFO: renamed from: updateSelection-qNKwrvQ$foundation, reason: not valid java name */
    public final boolean m1787updateSelectionqNKwrvQ$foundation(Offset newPosition, long previousPosition, boolean isStartHandle, SelectionAdjustment adjustment) {
        if (newPosition == null) {
            return false;
        }
        return m1786updateSelectionjyLRC_s$foundation(newPosition.unbox-impl(), previousPosition, isStartHandle, adjustment);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionManager$updateSelectionTextToolbar$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final /* synthetic */ class C01971 extends FunctionReferenceImpl implements Function0<Unit> {
        public C01971(Object obj) {
            super(0, obj, SelectionManager.class, "toolbarCopy", "toolbarCopy()V", 0);
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m1790invoke() {
            ((SelectionManager) ((CallableReference) this).receiver).toolbarCopy();
        }

        public /* bridge */ /* synthetic */ Object invoke() {
            m1790invoke();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionManager$updateSelectionTextToolbar$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final /* synthetic */ class C01982 extends FunctionReferenceImpl implements Function0<Unit> {
        public C01982(Object obj) {
            super(0, obj, SelectionManager.class, "selectAll", "selectAll$foundation()V", 0);
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m1791invoke() {
            ((SelectionManager) ((CallableReference) this).receiver).selectAll$foundation();
        }

        public /* bridge */ /* synthetic */ Object invoke() {
            m1791invoke();
            return Unit.INSTANCE;
        }
    }
}
