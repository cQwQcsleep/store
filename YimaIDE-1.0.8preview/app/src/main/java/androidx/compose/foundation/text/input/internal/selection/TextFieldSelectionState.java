package androidx.compose.foundation.text.input.internal.selection;

import androidx.collection.SieveCacheKt;
import androidx.compose.foundation.ComposeFoundationFlags;
import androidx.compose.foundation.content.TransferableContent;
import androidx.compose.foundation.content.TransferableContent_androidKt;
import androidx.compose.foundation.content.internal.ReceiveContentConfiguration;
import androidx.compose.foundation.gestures.DragGestureDetectorKt;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.foundation.internal.ClipboardUtils_androidKt;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.text.Handle;
import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.foundation.text.TextFieldCursor_androidKt;
import androidx.compose.foundation.text.TextLayoutHelperKt;
import androidx.compose.foundation.text.contextmenu.modifier.ToolbarRequester;
import androidx.compose.foundation.text.input.TextFieldCharSequence;
import androidx.compose.foundation.text.input.TextFieldCharSequenceKt;
import androidx.compose.foundation.text.input.internal.IndexTransformationType;
import androidx.compose.foundation.text.input.internal.MathUtilsKt;
import androidx.compose.foundation.text.input.internal.SelectionWedgeAffinity;
import androidx.compose.foundation.text.input.internal.TextLayoutState;
import androidx.compose.foundation.text.input.internal.TextLayoutStateKt;
import androidx.compose.foundation.text.input.internal.TransformedTextFieldState;
import androidx.compose.foundation.text.input.internal.WedgeAffinity;
import androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState;
import androidx.compose.foundation.text.input.internal.undo.TextFieldEditUndoBehavior;
import androidx.compose.foundation.text.selection.MouseSelectionObserver;
import androidx.compose.foundation.text.selection.PlatformSelectionBehaviors;
import androidx.compose.foundation.text.selection.SelectionAdjustment;
import androidx.compose.foundation.text.selection.SelectionHandlesKt;
import androidx.compose.foundation.text.selection.SelectionLayout;
import androidx.compose.foundation.text.selection.SelectionLayoutKt;
import androidx.compose.foundation.text.selection.SelectionManagerKt;
import androidx.compose.foundation.text.selection.TextSelectionDelegateKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.focus.FocusProperties;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.hapticfeedback.HapticFeedbackType;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.platform.ClipEntry;
import androidx.compose.ui.platform.Clipboard;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000ì\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b$\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0001\u0018\u00002\u00020\u0001:\u0006ã\u0001ä\u0001å\u0001Ba\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\t\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014¢\u0006\u0004\b\u0015\u0010\u0016J\u0015\u0010w\u001a\u00020x2\u0006\u0010y\u001a\u00020\tH\u0000¢\u0006\u0002\bzJ\b\u0010{\u001a\u00020\tH\u0002J\u0006\u0010|\u001a\u00020}J\u0006\u0010~\u001a\u00020}J\u001c\u0010\u007f\u001a\u00020}2\b\u0010\u0080\u0001\u001a\u00030\u0081\u00012\b\u0010\u0082\u0001\u001a\u00030\u0083\u0001H\u0002J\u001d\u0010\u0084\u0001\u001a\u00020}2\b\u0010\u0080\u0001\u001a\u00030\u0081\u00012\b\u0010\u0082\u0001\u001a\u00030\u0083\u0001H\u0002J@\u0010\u0085\u0001\u001a\u0002022\u0006\u0010#\u001a\u00020$2\u0006\u0010\u0013\u001a\u00020\u00142\u0007\u0010\u0086\u0001\u001a\u00020*2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\tJ\u0015\u0010\u0087\u0001\u001a\u000202*\u00030\u0088\u0001H\u0086@¢\u0006\u0003\u0010\u0089\u0001J\u001e\u0010\u008a\u0001\u001a\u000202*\u00030\u0088\u00012\u0007\u0010\u008b\u0001\u001a\u00020\tH\u0086@¢\u0006\u0003\u0010\u008c\u0001J\u0010\u0010\u008d\u0001\u001a\u000202H\u0086@¢\u0006\u0003\u0010\u008e\u0001J\u000f\u0010\u008f\u0001\u001a\u0002022\u0006\u0010]\u001a\u00020\\J\u0007\u0010\u0090\u0001\u001a\u000202J\u0015\u0010\u0091\u0001\u001a\u000202*\u00030\u0088\u0001H\u0086@¢\u0006\u0003\u0010\u0089\u0001J?\u0010\u0092\u0001\u001a\u000202*\u00030\u0088\u00012\n\u0010\u0093\u0001\u001a\u0005\u0018\u00010\u0094\u00012\r\u0010\u0095\u0001\u001a\b\u0012\u0004\u0012\u000202012\r\u0010\u0096\u0001\u001a\b\u0012\u0004\u0012\u00020201H\u0086@¢\u0006\u0003\u0010\u0097\u0001J\u0019\u0010\u0098\u0001\u001a\u00020\t2\u0007\u0010\u0099\u0001\u001a\u00020;¢\u0006\u0006\b\u009a\u0001\u0010\u009b\u0001J\u0015\u0010\u009c\u0001\u001a\u000202*\u00030\u0088\u0001H\u0082@¢\u0006\u0003\u0010\u0089\u0001J$\u0010\u009d\u0001\u001a\u000202*\u00030\u0088\u00012\r\u0010\u0095\u0001\u001a\b\u0012\u0004\u0012\u00020201H\u0086@¢\u0006\u0003\u0010\u009e\u0001J\u0007\u0010\u009f\u0001\u001a\u000202J\u001e\u0010 \u0001\u001a\u000202*\u00030\u0088\u00012\u0007\u0010\u008b\u0001\u001a\u00020\tH\u0082@¢\u0006\u0003\u0010\u008c\u0001J\u0010\u0010¡\u0001\u001a\u000202H\u0082@¢\u0006\u0003\u0010\u008e\u0001J\u0010\u0010¢\u0001\u001a\u000202H\u0082@¢\u0006\u0003\u0010\u008e\u0001J\t\u0010¨\u0001\u001a\u00020}H\u0002J \u0010©\u0001\u001a\u00020x2\u0007\u0010\u008b\u0001\u001a\u00020\t2\u0006\u0010y\u001a\u00020\tH\u0000¢\u0006\u0003\bª\u0001J\u001b\u0010«\u0001\u001a\u00020;2\u0007\u0010\u008b\u0001\u001a\u00020\tH\u0002¢\u0006\u0006\b¬\u0001\u0010\u00ad\u0001J\"\u0010®\u0001\u001a\u0002022\u0007\u0010¯\u0001\u001a\u00020J2\u0007\u0010°\u0001\u001a\u00020;¢\u0006\u0006\b±\u0001\u0010²\u0001J\t\u0010³\u0001\u001a\u000202H\u0002J\u0007\u0010´\u0001\u001a\u000202J\u0007\u0010µ\u0001\u001a\u00020\tJ\n\u0010¶\u0001\u001a\u00020\tH\u0086\bJ\u0010\u0010·\u0001\u001a\u000202H\u0086@¢\u0006\u0003\u0010\u008e\u0001J\n\u0010¸\u0001\u001a\u0005\u0018\u00010¹\u0001J\u0007\u0010º\u0001\u001a\u00020\tJ\n\u0010»\u0001\u001a\u00020\tH\u0086\bJ\u001b\u0010¼\u0001\u001a\u0002022\t\b\u0002\u0010½\u0001\u001a\u00020\tH\u0086@¢\u0006\u0003\u0010¾\u0001J\u001d\u0010¿\u0001\u001a\u0005\u0018\u00010¹\u00012\t\b\u0002\u0010½\u0001\u001a\u00020\tH\u0000¢\u0006\u0003\bÀ\u0001J\u0010\u0010Ã\u0001\u001a\u000202H\u0086@¢\u0006\u0003\u0010\u008e\u0001J\u0007\u0010Ä\u0001\u001a\u00020\tJ\n\u0010Å\u0001\u001a\u00020\tH\u0086\bJ\u0010\u0010Æ\u0001\u001a\u000202H\u0086@¢\u0006\u0003\u0010\u008e\u0001J\u0010\u0010Ç\u0001\u001a\u000202H\u0082@¢\u0006\u0003\u0010\u008e\u0001J\u0018\u0010È\u0001\u001a\u0002022\u0007\u0010 \u001a\u00030¹\u0001H\u0000¢\u0006\u0003\bÉ\u0001J\u0007\u0010Ê\u0001\u001a\u00020\tJ\u0007\u0010Ë\u0001\u001a\u000202J\u0007\u0010Ì\u0001\u001a\u00020\tJ\u0007\u0010Í\u0001\u001a\u000202J\u0019\u0010\u0086\u0001\u001a\u0002022\u0007\u0010Î\u0001\u001a\u00020}H\u0082@¢\u0006\u0003\u0010Ï\u0001J\u0007\u0010Ð\u0001\u001a\u000202J\t\u0010Ñ\u0001\u001a\u000202H\u0002JX\u0010Ò\u0001\u001a\u00030Ó\u00012\b\u0010Ô\u0001\u001a\u00030\u0083\u00012\u0007\u0010Õ\u0001\u001a\u00020p2\u0007\u0010Ö\u0001\u001a\u00020p2\u0007\u0010\u008b\u0001\u001a\u00020\t2\b\u0010×\u0001\u001a\u00030Ø\u00012\t\b\u0002\u0010Ù\u0001\u001a\u00020\t2\t\b\u0002\u0010Ú\u0001\u001a\u00020\tH\u0000¢\u0006\u0006\bÛ\u0001\u0010Ü\u0001JD\u0010Ý\u0001\u001a\u00030Ó\u00012\u0007\u0010Þ\u0001\u001a\u00020p2\u0007\u0010ß\u0001\u001a\u00020p2\n\u0010à\u0001\u001a\u0005\u0018\u00010Ó\u00012\u0007\u0010\u008b\u0001\u001a\u00020\t2\b\u0010×\u0001\u001a\u00030Ø\u0001H\u0002¢\u0006\u0006\bá\u0001\u0010â\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u000e\u0010\f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u00020\t2\u0006\u0010 \u001a\u00020\t@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001bR\u001e\u0010\n\u001a\u00020\t2\u0006\u0010 \u001a\u00020\t@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001bR\u001c\u0010#\u001a\u0004\u0018\u00010$X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010,\u001a\u00020\t2\u0006\u0010+\u001a\u00020\t8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b.\u0010/\u001a\u0004\b,\u0010\u001b\"\u0004\b-\u0010\u001dR\"\u00100\u001a\n\u0012\u0004\u0012\u000202\u0018\u000101X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106R$\u00107\u001a\f\u0012\u0006\u0012\u0004\u0018\u000108\u0018\u000101X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u00104\"\u0004\b:\u00106R+\u0010<\u001a\u00020;2\u0006\u0010+\u001a\u00020;8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bA\u0010/\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R\u0014\u0010B\u001a\u00020;8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bC\u0010>R+\u0010D\u001a\u00020;2\u0006\u0010+\u001a\u00020;8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bG\u0010/\u001a\u0004\bE\u0010>\"\u0004\bF\u0010@R\u0011\u0010H\u001a\u00020;8F¢\u0006\u0006\u001a\u0004\bI\u0010>R/\u0010K\u001a\u0004\u0018\u00010J2\b\u0010+\u001a\u0004\u0018\u00010J8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bP\u0010/\u001a\u0004\bL\u0010M\"\u0004\bN\u0010OR+\u0010R\u001a\u00020Q2\u0006\u0010+\u001a\u00020Q8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bW\u0010/\u001a\u0004\bS\u0010T\"\u0004\bU\u0010VR+\u0010X\u001a\u00020\t2\u0006\u0010+\u001a\u00020\t8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b[\u0010/\u001a\u0004\bY\u0010\u001b\"\u0004\bZ\u0010\u001dR+\u0010]\u001a\u00020\\2\u0006\u0010+\u001a\u00020\\8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bb\u0010/\u001a\u0004\b^\u0010_\"\u0004\b`\u0010aR+\u0010c\u001a\u00020\t2\u0006\u0010+\u001a\u00020\t8F@@X\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bf\u0010/\u001a\u0004\bd\u0010\u001b\"\u0004\be\u0010\u001dR\u0016\u0010g\u001a\u0004\u0018\u00010h8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bi\u0010jR\u0014\u0010k\u001a\u00020\t8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bl\u0010\u001bR\u0010\u0010m\u001a\u0004\u0018\u00010nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010o\u001a\u00020pX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010q\u001a\u0004\u0018\u00010rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bs\u0010t\"\u0004\bu\u0010vR\"\u0010£\u0001\u001a\u0004\u0018\u00010}8@X\u0080\u0084\u0002¢\u0006\u0010\n\u0006\b¦\u0001\u0010§\u0001\u001a\u0006\b¤\u0001\u0010¥\u0001R\u0010\u0010Á\u0001\u001a\u00030Â\u0001X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006æ\u0001"}, d2 = {"Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;", "", "textFieldState", "Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;", "textLayoutState", "Landroidx/compose/foundation/text/input/internal/TextLayoutState;", "density", "Landroidx/compose/ui/unit/Density;", "enabled", "", "readOnly", "isFocused", "isPassword", "toolbarRequester", "Landroidx/compose/foundation/text/contextmenu/modifier/ToolbarRequester;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "platformSelectionBehaviors", "Landroidx/compose/foundation/text/selection/PlatformSelectionBehaviors;", "clipboard", "Landroidx/compose/ui/platform/Clipboard;", "<init>", "(Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;Landroidx/compose/foundation/text/input/internal/TextLayoutState;Landroidx/compose/ui/unit/Density;ZZZZLandroidx/compose/foundation/text/contextmenu/modifier/ToolbarRequester;Lkotlinx/coroutines/CoroutineScope;Landroidx/compose/foundation/text/selection/PlatformSelectionBehaviors;Landroidx/compose/ui/platform/Clipboard;)V", "getTextFieldState$foundation", "()Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;", "getTextLayoutState$foundation", "()Landroidx/compose/foundation/text/input/internal/TextLayoutState;", "()Z", "setFocused", "(Z)V", "getPlatformSelectionBehaviors$foundation", "()Landroidx/compose/foundation/text/selection/PlatformSelectionBehaviors;", "value", "getEnabled", "getReadOnly", "hapticFeedBack", "Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "getHapticFeedBack", "()Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "setHapticFeedBack", "(Landroidx/compose/ui/hapticfeedback/HapticFeedback;)V", "textToolbarHandler", "Landroidx/compose/foundation/text/input/internal/selection/TextToolbarHandler;", "<set-?>", "isInTouchMode", "setInTouchMode", "isInTouchMode$delegate", "Landroidx/compose/runtime/MutableState;", "requestAutofillAction", "Lkotlin/Function0;", "", "getRequestAutofillAction", "()Lkotlin/jvm/functions/Function0;", "setRequestAutofillAction", "(Lkotlin/jvm/functions/Function0;)V", "receiveContentConfiguration", "Landroidx/compose/foundation/content/internal/ReceiveContentConfiguration;", "getReceiveContentConfiguration", "setReceiveContentConfiguration", "Landroidx/compose/ui/geometry/Offset;", "startTextLayoutPositionInWindow", "getStartTextLayoutPositionInWindow-F1C5BW0", "()J", "setStartTextLayoutPositionInWindow-k-4lQ0M", "(J)V", "startTextLayoutPositionInWindow$delegate", "currentTextLayoutPositionInWindow", "getCurrentTextLayoutPositionInWindow-F1C5BW0", "rawHandleDragPosition", "getRawHandleDragPosition-F1C5BW0", "setRawHandleDragPosition-k-4lQ0M", "rawHandleDragPosition$delegate", "handleDragPosition", "getHandleDragPosition-F1C5BW0", "Landroidx/compose/foundation/text/Handle;", "draggingHandle", "getDraggingHandle", "()Landroidx/compose/foundation/text/Handle;", "setDraggingHandle", "(Landroidx/compose/foundation/text/Handle;)V", "draggingHandle$delegate", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState$InputType;", "directDragGestureInitiator", "getDirectDragGestureInitiator", "()Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState$InputType;", "setDirectDragGestureInitiator", "(Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState$InputType;)V", "directDragGestureInitiator$delegate", "showCursorHandle", "getShowCursorHandle", "setShowCursorHandle", "showCursorHandle$delegate", "Landroidx/compose/foundation/text/input/internal/selection/TextToolbarState;", "textToolbarState", "getTextToolbarState", "()Landroidx/compose/foundation/text/input/internal/selection/TextToolbarState;", "setTextToolbarState", "(Landroidx/compose/foundation/text/input/internal/selection/TextToolbarState;)V", "textToolbarState$delegate", "textToolbarShown", "getTextToolbarShown", "setTextToolbarShown$foundation", "textToolbarShown$delegate", "textLayoutCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "getTextLayoutCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "editable", "getEditable$foundation", "previousSelectionLayout", "Landroidx/compose/foundation/text/selection/SelectionLayout;", "previousRawDragOffset", "", "pressInteraction", "Landroidx/compose/foundation/interaction/PressInteraction$Press;", "getPressInteraction", "()Landroidx/compose/foundation/interaction/PressInteraction$Press;", "setPressInteraction", "(Landroidx/compose/foundation/interaction/PressInteraction$Press;)V", "getCursorHandleState", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldHandleState;", "includePosition", "getCursorHandleState$foundation", "isCursorHandleInVisibleBounds", "getCursorRect", "Landroidx/compose/ui/geometry/Rect;", "getFocusRect", "calculateCursorRect", "layoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "visualText", "Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "calculateSelectionRect", "update", "showTextToolbar", "cursorHandleGestures", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "selectionHandleGestures", "isStartHandle", "(Landroidx/compose/ui/input/pointer/PointerInputScope;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startToolbarAndHandlesVisibilityObserver", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateTextToolbarState", "dispose", "detectTouchMode", "detectTextFieldTapGestures", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "requestFocus", "showKeyboard", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "placeCursorAtNearestOffset", "offset", "placeCursorAtNearestOffset-k-4lQ0M", "(J)Z", "detectCursorHandleDragGestures", "textFieldSelectionGestures", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "maybeSuggestSelectionRange", "detectSelectionHandleDragGestures", "observeTextChanges", "observeTextToolbarVisibility", "derivedVisibleContentBounds", "getDerivedVisibleContentBounds$foundation", "()Landroidx/compose/ui/geometry/Rect;", "derivedVisibleContentBounds$delegate", "Landroidx/compose/runtime/State;", "getContentRect", "getSelectionHandleState", "getSelectionHandleState$foundation", "getHandlePosition", "getHandlePosition-tuRUvjQ", "(Z)J", "updateHandleDragging", "handle", "position", "updateHandleDragging-Uv8p0NA", "(Landroidx/compose/foundation/text/Handle;J)V", "markStartContentVisibleOffset", "clearHandleDragging", "canShowCutMenuItem", "isCutAllowed", "cut", "cutWithResult", "Landroidx/compose/ui/text/AnnotatedString;", "canShowCopyMenuItem", "isCopyAllowed", "copy", "cancelSelection", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "copyWithResult", "copyWithResult$foundation", "clipboardPasteState", "Landroidx/compose/foundation/text/input/internal/selection/ClipboardPasteState;", "updateClipboardEntry", "canShowPasteMenuItem", "isPasteAllowed", "paste", "pasteAsPlainText", "onPasteEvent", "onPasteEvent$foundation", "canShowSelectAllMenuItem", "selectAll", "canShowAutofillMenuItem", "autofill", "contentRect", "(Landroidx/compose/ui/geometry/Rect;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deselect", "hideTextToolbar", "updateSelection", "Landroidx/compose/ui/text/TextRange;", "textFieldCharSequence", "startOffset", "endOffset", "adjustment", "Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "allowPreviousSelectionCollapsed", "isStartOfSelection", "updateSelection-SsL-Rf8$foundation", "(Landroidx/compose/foundation/text/input/TextFieldCharSequence;IIZLandroidx/compose/foundation/text/selection/SelectionAdjustment;ZZ)J", "getTextFieldSelection", "rawStartOffset", "rawEndOffset", "previousSelection", "getTextFieldSelection-qeG_v_k", "(IILandroidx/compose/ui/text/TextRange;ZLandroidx/compose/foundation/text/selection/SelectionAdjustment;)J", "InputType", "TextFieldMouseSelectionObserver", "TextFieldTextDragObserver", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class TextFieldSelectionState {
    public static final int $stable = 8;
    private Clipboard clipboard;
    private ClipboardPasteState clipboardPasteState;
    private final CoroutineScope coroutineScope;
    private Density density;

    /* JADX INFO: renamed from: derivedVisibleContentBounds$delegate, reason: from kotlin metadata */
    private final State derivedVisibleContentBounds;

    /* JADX INFO: renamed from: directDragGestureInitiator$delegate, reason: from kotlin metadata */
    private final MutableState directDragGestureInitiator;

    /* JADX INFO: renamed from: draggingHandle$delegate, reason: from kotlin metadata */
    private final MutableState draggingHandle;
    private boolean enabled;
    private HapticFeedback hapticFeedBack;
    private boolean isFocused;

    /* JADX INFO: renamed from: isInTouchMode$delegate, reason: from kotlin metadata */
    private final MutableState isInTouchMode = SnapshotStateKt.mutableStateOf$default(Boolean.TRUE, (SnapshotMutationPolicy) null, 2, (Object) null);
    private boolean isPassword;
    private final PlatformSelectionBehaviors platformSelectionBehaviors;
    private PressInteraction.Press pressInteraction;
    private int previousRawDragOffset;
    private SelectionLayout previousSelectionLayout;

    /* JADX INFO: renamed from: rawHandleDragPosition$delegate, reason: from kotlin metadata */
    private final MutableState rawHandleDragPosition;
    private boolean readOnly;
    private Function0<? extends ReceiveContentConfiguration> receiveContentConfiguration;
    private Function0<Unit> requestAutofillAction;

    /* JADX INFO: renamed from: showCursorHandle$delegate, reason: from kotlin metadata */
    private final MutableState showCursorHandle;

    /* JADX INFO: renamed from: startTextLayoutPositionInWindow$delegate, reason: from kotlin metadata */
    private final MutableState startTextLayoutPositionInWindow;
    private final TransformedTextFieldState textFieldState;
    private final TextLayoutState textLayoutState;
    private TextToolbarHandler textToolbarHandler;

    /* JADX INFO: renamed from: textToolbarShown$delegate, reason: from kotlin metadata */
    private final MutableState textToolbarShown;

    /* JADX INFO: renamed from: textToolbarState$delegate, reason: from kotlin metadata */
    private final MutableState textToolbarState;
    private final ToolbarRequester toolbarRequester;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState$InputType;", "", "<init>", "(Ljava/lang/String;I)V", "None", "Touch", "Mouse", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public enum InputType {
        None,
        Touch,
        Mouse;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<InputType> getEntries() {
            return $ENTRIES;
        }
    }

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J'\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u001f\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J/\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\rH\u0002¢\u0006\u0004\b\u001e\u0010\u001fJ\b\u0010 \u001a\u00020\u0004H\u0016J\u0017\u0010!\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\nH\u0016¢\u0006\u0004\b\"\u0010#J\u0017\u0010$\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\nH\u0016¢\u0006\u0004\b%\u0010#R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState$TextFieldMouseSelectionObserver;", "Landroidx/compose/foundation/text/selection/MouseSelectionObserver;", "requestFocus", "Lkotlin/Function0;", "", "<init>", "(Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;Lkotlin/jvm/functions/Function0;)V", "dragBeginOffsetInText", "", "dragBeginPosition", "Landroidx/compose/ui/geometry/Offset;", "J", "isDoubleOrTripleClickOnly", "", "onStart", "downPosition", "adjustment", "Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "clickCount", "onStart-9KIMszo", "(JLandroidx/compose/foundation/text/selection/SelectionAdjustment;I)Z", "onDrag", "dragPosition", "onDrag-3MmeM6k", "(JLandroidx/compose/foundation/text/selection/SelectionAdjustment;)Z", "updateSelection", "Landroidx/compose/ui/text/TextRange;", "layoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "isStartOfSelection", "updateSelection-12glfjA", "(JLandroidx/compose/foundation/text/selection/SelectionAdjustment;Landroidx/compose/ui/text/TextLayoutResult;Z)J", "onDragDone", "onExtend", "onExtend-k-4lQ0M", "(J)Z", "onExtendDrag", "onExtendDrag-k-4lQ0M", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public final class TextFieldMouseSelectionObserver implements MouseSelectionObserver {
        private int dragBeginOffsetInText = -1;
        private long dragBeginPosition = Offset.Companion.getUnspecified-F1C5BW0();
        private boolean isDoubleOrTripleClickOnly = true;
        private final Function0<Unit> requestFocus;

        public TextFieldMouseSelectionObserver(Function0<Unit> function0) {
            this.requestFocus = function0;
        }

        public static String a(long j) {
            return "Mouse.onDrag " + ((Object) Offset.toString-impl(j));
        }

        public static String b() {
            return "Mouse.onStart";
        }

        public static String c() {
            return "Mouse.onExtendDrag";
        }

        public static String d() {
            return "Mouse.onDragDone";
        }

        public static String e() {
            return "Mouse.onExtend";
        }

        /* JADX INFO: renamed from: updateSelection-12glfjA, reason: not valid java name */
        private final long m1661updateSelection12glfjA(long dragPosition, SelectionAdjustment adjustment, TextLayoutResult layoutResult, boolean isStartOfSelection) {
            int length = layoutResult.getLayoutInput().getText().length();
            int iM1599getOffsetForPosition3MmeM6k = this.dragBeginOffsetInText;
            if (iM1599getOffsetForPosition3MmeM6k < 0 || iM1599getOffsetForPosition3MmeM6k > length) {
                iM1599getOffsetForPosition3MmeM6k = TextFieldSelectionState.this.getTextLayoutState().m1599getOffsetForPosition3MmeM6k(this.dragBeginPosition, false);
            }
            int i = iM1599getOffsetForPosition3MmeM6k;
            int iM1599getOffsetForPosition3MmeM6k2 = TextFieldSelectionState.this.getTextLayoutState().m1599getOffsetForPosition3MmeM6k(dragPosition, false);
            TextFieldSelectionState textFieldSelectionState = TextFieldSelectionState.this;
            long jM1660updateSelectionSsLRf8$foundation = textFieldSelectionState.m1660updateSelectionSsLRf8$foundation(textFieldSelectionState.getTextFieldState().getVisualText(), i, iM1599getOffsetForPosition3MmeM6k2, false, adjustment, false, isStartOfSelection);
            if (this.dragBeginOffsetInText == -1 && !TextRange.getCollapsed-impl(jM1660updateSelectionSsLRf8$foundation)) {
                this.dragBeginOffsetInText = TextRange.getStart-impl(jM1660updateSelectionSsLRf8$foundation);
            }
            if (TextRange.getReversed-impl(jM1660updateSelectionSsLRf8$foundation)) {
                jM1660updateSelectionSsLRf8$foundation = TextFieldSelectionStateKt.m1667reverse5zctL8(jM1660updateSelectionSsLRf8$foundation);
            }
            TextFieldSelectionState.this.getTextFieldState().m1616selectCharsIn5zctL8(jM1660updateSelectionSsLRf8$foundation);
            TextFieldSelectionState.this.updateTextToolbarState(TextToolbarState.Selection);
            return jM1660updateSelectionSsLRf8$foundation;
        }

        @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
        /* JADX INFO: renamed from: onDrag-3MmeM6k, reason: not valid java name */
        public boolean mo1662onDrag3MmeM6k(final long dragPosition, SelectionAdjustment adjustment) {
            TextLayoutResult layoutResult = TextFieldSelectionState.this.getTextLayoutState().getLayoutResult();
            if (!TextFieldSelectionState.this.getEnabled() || layoutResult == null || TextFieldSelectionState.this.getTextFieldState().getVisualText().length() == 0) {
                return false;
            }
            TextFieldSelectionStateKt.logDebug(new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.f
                public final Object invoke() {
                    return TextFieldSelectionState.TextFieldMouseSelectionObserver.a(dragPosition);
                }
            });
            if (TextRange.equals-impl0(TextFieldSelectionState.this.getTextFieldState().getVisualText().getSelection(), m1661updateSelection12glfjA(dragPosition, adjustment, layoutResult, false))) {
                return true;
            }
            this.isDoubleOrTripleClickOnly = false;
            return true;
        }

        @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
        public void onDragDone() {
            TextFieldSelectionStateKt.logDebug(new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.d
                public final Object invoke() {
                    return TextFieldSelectionState.TextFieldMouseSelectionObserver.d();
                }
            });
            TextFieldSelectionState.this.setDirectDragGestureInitiator(InputType.None);
            if (this.isDoubleOrTripleClickOnly) {
                TextFieldSelectionState.this.maybeSuggestSelectionRange();
            }
        }

        @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
        /* JADX INFO: renamed from: onExtend-k-4lQ0M, reason: not valid java name */
        public boolean mo1663onExtendk4lQ0M(long downPosition) {
            TextLayoutResult layoutResult = TextFieldSelectionState.this.getTextLayoutState().getLayoutResult();
            if (!TextFieldSelectionState.this.getEnabled() || layoutResult == null || TextFieldSelectionState.this.getTextFieldState().getVisualText().length() == 0) {
                return false;
            }
            TextFieldSelectionStateKt.logDebug(new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.c
                public final Object invoke() {
                    return TextFieldSelectionState.TextFieldMouseSelectionObserver.e();
                }
            });
            this.isDoubleOrTripleClickOnly = false;
            this.requestFocus.invoke();
            m1661updateSelection12glfjA(downPosition, SelectionAdjustment.INSTANCE.getNone(), layoutResult, false);
            return true;
        }

        @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
        /* JADX INFO: renamed from: onExtendDrag-k-4lQ0M, reason: not valid java name */
        public boolean mo1664onExtendDragk4lQ0M(long dragPosition) {
            TextFieldSelectionStateKt.logDebug(new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.b
                public final Object invoke() {
                    return TextFieldSelectionState.TextFieldMouseSelectionObserver.c();
                }
            });
            return true;
        }

        @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
        /* JADX INFO: renamed from: onStart-9KIMszo, reason: not valid java name */
        public boolean mo1665onStart9KIMszo(long downPosition, SelectionAdjustment adjustment, int clickCount) {
            TextLayoutResult layoutResult = TextFieldSelectionState.this.getTextLayoutState().getLayoutResult();
            if (!TextFieldSelectionState.this.getEnabled() || layoutResult == null || TextFieldSelectionState.this.getTextFieldState().getVisualText().length() == 0) {
                return false;
            }
            this.isDoubleOrTripleClickOnly = clickCount >= 2;
            TextFieldSelectionStateKt.logDebug(new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.e
                public final Object invoke() {
                    return TextFieldSelectionState.TextFieldMouseSelectionObserver.b();
                }
            });
            TextFieldSelectionState.this.setDirectDragGestureInitiator(InputType.Mouse);
            this.requestFocus.invoke();
            TextFieldSelectionState.this.previousRawDragOffset = -1;
            this.dragBeginOffsetInText = -1;
            this.dragBeginPosition = downPosition;
            this.dragBeginOffsetInText = TextRange.getStart-impl(m1661updateSelection12glfjA(downPosition, adjustment, layoutResult, true));
            return true;
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0013\u001a\u00020\u0004H\u0002J\u0017\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\b\u0010\u0018\u001a\u00020\u0004H\u0016J\b\u0010\u0019\u001a\u00020\u0004H\u0016J\b\u0010\u001a\u001a\u00020\u0004H\u0016J\u001f\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\nH\u0016¢\u0006\u0004\b\"\u0010\u0017R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0010\u0010\f\u001a\u00020\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState$TextFieldTextDragObserver;", "Landroidx/compose/foundation/text/TextDragObserver;", "requestFocus", "Lkotlin/Function0;", "", "<init>", "(Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;Lkotlin/jvm/functions/Function0;)V", "dragBeginOffsetInText", "", "dragBeginPosition", "Landroidx/compose/ui/geometry/Offset;", "J", "dragTotalDistance", "actingHandle", "Landroidx/compose/foundation/text/Handle;", "isLongPressSelectionOnly", "", "selectionAdjustmentMode", "Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "onDragStop", "onDown", "point", "onDown-k-4lQ0M", "(J)V", "onUp", "onStop", "onCancel", "onStart", "startPoint", "selectionAdjustment", "onStart-3MmeM6k", "(JLandroidx/compose/foundation/text/selection/SelectionAdjustment;)V", "onDrag", "delta", "onDrag-k-4lQ0M", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public final class TextFieldTextDragObserver implements TextDragObserver {
        private Handle actingHandle;
        private int dragBeginOffsetInText = -1;
        private long dragBeginPosition;
        private long dragTotalDistance;
        private boolean isLongPressSelectionOnly;
        private final Function0<Unit> requestFocus;
        private SelectionAdjustment selectionAdjustmentMode;

        public TextFieldTextDragObserver(Function0<Unit> function0) {
            this.requestFocus = function0;
            Offset.Companion companion = Offset.Companion;
            this.dragBeginPosition = companion.getUnspecified-F1C5BW0();
            this.dragTotalDistance = companion.getZero-F1C5BW0();
            this.actingHandle = Handle.SelectionEnd;
            this.isLongPressSelectionOnly = true;
            this.selectionAdjustmentMode = SelectionAdjustment.INSTANCE.getNone();
        }

        public static String a(long j) {
            return "Touch.onDrag at " + ((Object) Offset.toString-impl(j));
        }

        public static String b() {
            return "Touch.onDragStop";
        }

        public static String c(long j) {
            return "Touch.onDragStart after longPress at " + ((Object) Offset.toString-impl(j));
        }

        private final void onDragStop() {
            if ((this.dragBeginPosition & SieveCacheKt.InvalidMapping) != 9205357640488583168L) {
                TextFieldSelectionStateKt.logDebug(new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.i
                    public final Object invoke() {
                        return TextFieldSelectionState.TextFieldTextDragObserver.b();
                    }
                });
                TextFieldSelectionState.this.clearHandleDragging();
                this.dragBeginOffsetInText = -1;
                Offset.Companion companion = Offset.Companion;
                this.dragBeginPosition = companion.getUnspecified-F1C5BW0();
                this.dragTotalDistance = companion.getZero-F1C5BW0();
                TextFieldSelectionState.this.previousRawDragOffset = -1;
                this.selectionAdjustmentMode = SelectionAdjustment.INSTANCE.getNone();
                TextFieldSelectionState.this.setDirectDragGestureInitiator(InputType.None);
                this.requestFocus.invoke();
                if (this.isLongPressSelectionOnly) {
                    TextFieldSelectionState.this.maybeSuggestSelectionRange();
                }
            }
        }

        @Override // androidx.compose.foundation.text.TextDragObserver
        public void onCancel() {
            onDragStop();
        }

        @Override // androidx.compose.foundation.text.TextDragObserver
        /* JADX INFO: renamed from: onDown-k-4lQ0M */
        public void mo1409onDownk4lQ0M(long point) {
        }

        @Override // androidx.compose.foundation.text.TextDragObserver
        /* JADX INFO: renamed from: onDrag-k-4lQ0M */
        public void mo1410onDragk4lQ0M(long delta) {
            int iIntValue;
            int iM1599getOffsetForPosition3MmeM6k;
            SelectionAdjustment none;
            Handle handle;
            if (!TextFieldSelectionState.this.getEnabled() || TextFieldSelectionState.this.getTextLayoutState().getLayoutResult() == null || TextFieldSelectionState.this.getTextFieldState().getVisualText().length() == 0) {
                return;
            }
            long j = Offset.plus-MK-Hz9U(this.dragTotalDistance, delta);
            this.dragTotalDistance = j;
            final long j2 = Offset.plus-MK-Hz9U(this.dragBeginPosition, j);
            TextFieldSelectionStateKt.logDebug(new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.g
                public final Object invoke() {
                    return TextFieldSelectionState.TextFieldTextDragObserver.a(j2);
                }
            });
            if (this.dragBeginOffsetInText >= 0 || TextFieldSelectionState.this.getTextLayoutState().m1600isPositionOnTextk4lQ0M(j2)) {
                Integer numValueOf = Integer.valueOf(this.dragBeginOffsetInText);
                if (numValueOf.intValue() < 0) {
                    numValueOf = null;
                }
                iIntValue = numValueOf != null ? numValueOf.intValue() : TextFieldSelectionState.this.getTextLayoutState().m1599getOffsetForPosition3MmeM6k(this.dragBeginPosition, false);
                iM1599getOffsetForPosition3MmeM6k = TextFieldSelectionState.this.getTextLayoutState().m1599getOffsetForPosition3MmeM6k(j2, false);
                if (this.dragBeginOffsetInText < 0 && iIntValue == iM1599getOffsetForPosition3MmeM6k) {
                    return;
                }
                none = this.selectionAdjustmentMode;
                TextFieldSelectionState.this.updateTextToolbarState(TextToolbarState.Selection);
            } else {
                iIntValue = TextLayoutState.m1596getOffsetForPosition3MmeM6k$default(TextFieldSelectionState.this.getTextLayoutState(), this.dragBeginPosition, false, 2, null);
                iM1599getOffsetForPosition3MmeM6k = TextLayoutState.m1596getOffsetForPosition3MmeM6k$default(TextFieldSelectionState.this.getTextLayoutState(), j2, false, 2, null);
                none = iIntValue == iM1599getOffsetForPosition3MmeM6k ? SelectionAdjustment.INSTANCE.getNone() : this.selectionAdjustmentMode;
            }
            int i = iIntValue;
            int i2 = iM1599getOffsetForPosition3MmeM6k;
            SelectionAdjustment selectionAdjustment = none;
            long selection = TextFieldSelectionState.this.getTextFieldState().getVisualText().getSelection();
            TextFieldSelectionState textFieldSelectionState = TextFieldSelectionState.this;
            long jM1656updateSelectionSsLRf8$foundation$default = TextFieldSelectionState.m1656updateSelectionSsLRf8$foundation$default(textFieldSelectionState, textFieldSelectionState.getTextFieldState().getVisualText(), i, i2, false, selectionAdjustment, false, false, 64, null);
            if (this.dragBeginOffsetInText == -1 && !TextRange.getCollapsed-impl(jM1656updateSelectionSsLRf8$foundation$default)) {
                this.dragBeginOffsetInText = TextRange.getStart-impl(jM1656updateSelectionSsLRf8$foundation$default);
            }
            if (TextRange.getReversed-impl(jM1656updateSelectionSsLRf8$foundation$default)) {
                jM1656updateSelectionSsLRf8$foundation$default = TextFieldSelectionStateKt.m1667reverse5zctL8(jM1656updateSelectionSsLRf8$foundation$default);
            }
            if (!TextRange.equals-impl0(jM1656updateSelectionSsLRf8$foundation$default, selection)) {
                if (TextRange.getStart-impl(jM1656updateSelectionSsLRf8$foundation$default) == TextRange.getStart-impl(selection) || TextRange.getEnd-impl(jM1656updateSelectionSsLRf8$foundation$default) != TextRange.getEnd-impl(selection)) {
                    handle = ((TextRange.getStart-impl(jM1656updateSelectionSsLRf8$foundation$default) != TextRange.getStart-impl(selection) || TextRange.getEnd-impl(jM1656updateSelectionSsLRf8$foundation$default) == TextRange.getEnd-impl(selection)) && ((float) (TextRange.getStart-impl(jM1656updateSelectionSsLRf8$foundation$default) + TextRange.getEnd-impl(jM1656updateSelectionSsLRf8$foundation$default))) / 2.0f <= ((float) (TextRange.getStart-impl(selection) + TextRange.getEnd-impl(selection))) / 2.0f) ? Handle.SelectionStart : Handle.SelectionEnd;
                } else {
                    handle = Handle.SelectionStart;
                }
                this.actingHandle = handle;
                this.isLongPressSelectionOnly = false;
            }
            if (TextRange.getCollapsed-impl(selection) || !TextRange.getCollapsed-impl(jM1656updateSelectionSsLRf8$foundation$default)) {
                TextFieldSelectionState.this.getTextFieldState().m1616selectCharsIn5zctL8(jM1656updateSelectionSsLRf8$foundation$default);
            }
            TextFieldSelectionState.this.m1659updateHandleDraggingUv8p0NA(this.actingHandle, j2);
        }

        @Override // androidx.compose.foundation.text.TextDragObserver
        /* JADX INFO: renamed from: onStart-3MmeM6k */
        public void mo1411onStart3MmeM6k(final long startPoint, SelectionAdjustment selectionAdjustment) {
            if (TextFieldSelectionState.this.getEnabled()) {
                TextFieldSelectionStateKt.logDebug(new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.h
                    public final Object invoke() {
                        return TextFieldSelectionState.TextFieldTextDragObserver.c(startPoint);
                    }
                });
                TextFieldSelectionState.this.m1659updateHandleDraggingUv8p0NA(this.actingHandle, startPoint);
                TextFieldSelectionState.this.setShowCursorHandle(false);
                TextFieldSelectionState.this.setDirectDragGestureInitiator(InputType.Touch);
                this.dragBeginPosition = startPoint;
                this.dragTotalDistance = Offset.Companion.getZero-F1C5BW0();
                TextFieldSelectionState.this.previousRawDragOffset = -1;
                this.isLongPressSelectionOnly = true;
                this.selectionAdjustmentMode = selectionAdjustment;
                if (TextFieldSelectionState.this.getTextLayoutState().getLayoutResult() == null) {
                    return;
                }
                boolean zM1600isPositionOnTextk4lQ0M = TextFieldSelectionState.this.getTextLayoutState().m1600isPositionOnTextk4lQ0M(startPoint);
                TextFieldSelectionState textFieldSelectionState = TextFieldSelectionState.this;
                if (zM1600isPositionOnTextk4lQ0M) {
                    if (textFieldSelectionState.getTextFieldState().getVisualText().length() == 0) {
                        return;
                    }
                    int iM1596getOffsetForPosition3MmeM6k$default = TextLayoutState.m1596getOffsetForPosition3MmeM6k$default(TextFieldSelectionState.this.getTextLayoutState(), startPoint, false, 2, null);
                    long jM1656updateSelectionSsLRf8$foundation$default = TextFieldSelectionState.m1656updateSelectionSsLRf8$foundation$default(TextFieldSelectionState.this, new TextFieldCharSequence(TextFieldSelectionState.this.getTextFieldState().getVisualText(), TextRange.Companion.getZero-d9O1mEE(), null, null, null, null, 60, null), iM1596getOffsetForPosition3MmeM6k$default, iM1596getOffsetForPosition3MmeM6k$default, false, this.selectionAdjustmentMode, false, false, 96, null);
                    TextFieldSelectionState.this.getTextFieldState().m1616selectCharsIn5zctL8(jM1656updateSelectionSsLRf8$foundation$default);
                    TextFieldSelectionState.this.updateTextToolbarState(TextToolbarState.Selection);
                    this.dragBeginOffsetInText = TextRange.getStart-impl(jM1656updateSelectionSsLRf8$foundation$default);
                    return;
                }
                int iM1596getOffsetForPosition3MmeM6k$default2 = TextLayoutState.m1596getOffsetForPosition3MmeM6k$default(textFieldSelectionState.getTextLayoutState(), startPoint, false, 2, null);
                HapticFeedback hapticFeedBack = TextFieldSelectionState.this.getHapticFeedBack();
                if (hapticFeedBack != null) {
                    hapticFeedBack.performHapticFeedback-CdsT49E(HapticFeedbackType.Companion.getTextHandleMove-5zf0vsI());
                }
                TextFieldSelectionState.this.getTextFieldState().placeCursorBeforeCharAt(iM1596getOffsetForPosition3MmeM6k$default2);
                TextFieldSelectionState.this.setShowCursorHandle(true);
                this.isLongPressSelectionOnly = false;
                TextFieldSelectionState.this.updateTextToolbarState(TextToolbarState.Cursor);
            }
        }

        @Override // androidx.compose.foundation.text.TextDragObserver
        public void onStop() {
            onDragStop();
        }

        @Override // androidx.compose.foundation.text.TextDragObserver
        public void onUp() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[IndexTransformationType.values().length];
            try {
                iArr[IndexTransformationType.Untransformed.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[IndexTransformationType.Deletion.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[IndexTransformationType.Insertion.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[IndexTransformationType.Replacement.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2", f = "TextFieldSelectionState.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Job>, Object> {
        final /* synthetic */ PointerInputScope $this_cursorHandleGestures;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2$1", f = "TextFieldSelectionState.kt", i = {}, l = {493}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ PointerInputScope $this_cursorHandleGestures;
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(TextFieldSelectionState textFieldSelectionState, PointerInputScope pointerInputScope, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = textFieldSelectionState;
                this.$this_cursorHandleGestures = pointerInputScope;
            }

            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this$0, this.$this_cursorHandleGestures, continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    TextFieldSelectionState textFieldSelectionState = this.this$0;
                    PointerInputScope pointerInputScope = this.$this_cursorHandleGestures;
                    this.label = 1;
                    if (textFieldSelectionState.detectTouchMode(pointerInputScope, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        k2d.a("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2$2, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2$2", f = "TextFieldSelectionState.kt", i = {}, l = {494}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        public static final class C00372 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ PointerInputScope $this_cursorHandleGestures;
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00372(TextFieldSelectionState textFieldSelectionState, PointerInputScope pointerInputScope, Continuation<? super C00372> continuation) {
                super(2, continuation);
                this.this$0 = textFieldSelectionState;
                this.$this_cursorHandleGestures = pointerInputScope;
            }

            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00372(this.this$0, this.$this_cursorHandleGestures, continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    TextFieldSelectionState textFieldSelectionState = this.this$0;
                    PointerInputScope pointerInputScope = this.$this_cursorHandleGestures;
                    this.label = 1;
                    if (textFieldSelectionState.detectCursorHandleDragGestures(pointerInputScope, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        k2d.a("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2$3, reason: invalid class name */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2$3", f = "TextFieldSelectionState.kt", i = {}, l = {496}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        public static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ PointerInputScope $this_cursorHandleGestures;
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass3(PointerInputScope pointerInputScope, TextFieldSelectionState textFieldSelectionState, Continuation<? super AnonymousClass3> continuation) {
                super(2, continuation);
                this.$this_cursorHandleGestures = pointerInputScope;
                this.this$0 = textFieldSelectionState;
            }

            public static Unit b(TextFieldSelectionState textFieldSelectionState, Offset offset) {
                TextToolbarState textToolbarState = textFieldSelectionState.getTextToolbarState();
                TextToolbarState textToolbarState2 = TextToolbarState.Cursor;
                if (textToolbarState == textToolbarState2) {
                    textToolbarState2 = TextToolbarState.None;
                }
                textFieldSelectionState.setTextToolbarState(textToolbarState2);
                return Unit.INSTANCE;
            }

            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass3(this.$this_cursorHandleGestures, this.this$0, continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    PointerInputScope pointerInputScope = this.$this_cursorHandleGestures;
                    final TextFieldSelectionState textFieldSelectionState = this.this$0;
                    Function1 function1 = new Function1() { // from class: androidx.compose.foundation.text.input.internal.selection.j
                        public final Object invoke(Object obj2) {
                            return TextFieldSelectionState.AnonymousClass2.AnonymousClass3.b(textFieldSelectionState, (Offset) obj2);
                        }
                    };
                    this.label = 1;
                    if (TapGestureDetectorKt.detectTapGestures$default(pointerInputScope, null, null, null, function1, this, 7, null) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        k2d.a("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(PointerInputScope pointerInputScope, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$this_cursorHandleGestures = pointerInputScope;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = TextFieldSelectionState.this.new AnonymousClass2(this.$this_cursorHandleGestures, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Job> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            CoroutineStart coroutineStart = CoroutineStart.UNDISPATCHED;
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, coroutineStart, new AnonymousClass1(TextFieldSelectionState.this, this.$this_cursorHandleGestures, null), 1, (Object) null);
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, coroutineStart, new C00372(TextFieldSelectionState.this, this.$this_cursorHandleGestures, null), 1, (Object) null);
            return BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, coroutineStart, new AnonymousClass3(this.$this_cursorHandleGestures, TextFieldSelectionState.this, null), 1, (Object) null);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState", f = "TextFieldSelectionState.kt", i = {0, 0}, l = {676}, m = "detectCursorHandleDragGestures", n = {"cursorDragStart", "cursorDragDelta"}, s = {"L$0", "L$1"}, v = 1)
    public static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TextFieldSelectionState.this.detectCursorHandleDragGestures(null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectSelectionHandleDragGestures$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState", f = "TextFieldSelectionState.kt", i = {0, 0, 0}, l = {1137}, m = "detectSelectionHandleDragGestures", n = {"dragBeginPosition", "dragTotalDistance", "handle"}, s = {"L$0", "L$1", "L$2"}, v = 1)
    public static final class C01831 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C01831(Continuation<? super C01831> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TextFieldSelectionState.this.detectSelectionHandleDragGestures(null, false, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectTouchMode$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectTouchMode$2", f = "TextFieldSelectionState.kt", i = {0}, l = {566}, m = "invokeSuspend", n = {"$this$awaitPointerEventScope"}, s = {"L$0"}, v = 1)
    public static final class C01842 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        public C01842(Continuation<? super C01842> continuation) {
            super(2, continuation);
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C01842 c01842 = TextFieldSelectionState.this.new C01842(continuation);
            c01842.L$0 = obj;
            return c01842;
        }

        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return create(awaitPointerEventScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:11:0x002e A[RETURN] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x002c -> B:12:0x002f). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:11:0x002e
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        public final java.lang.Object invokeSuspend(java.lang.Object r5) {
            /*
                r4 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r4.label
                r2 = 1
                if (r1 == 0) goto L1a
                if (r1 != r2) goto L13
                java.lang.Object r1 = r4.L$0
                androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
                kotlin.ResultKt.throwOnFailure(r5)
                goto L2f
            L13:
                java.lang.String r4 = "call to 'resume' before 'invoke' with coroutine"
                k2d.a(r4)
                r4 = 0
                return r4
            L1a:
                kotlin.ResultKt.throwOnFailure(r5)
                java.lang.Object r5 = r4.L$0
                androidx.compose.ui.input.pointer.AwaitPointerEventScope r5 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r5
                r1 = r5
            L22:
                androidx.compose.ui.input.pointer.PointerEventPass r5 = androidx.compose.ui.input.pointer.PointerEventPass.Initial
                r4.L$0 = r1
                r4.label = r2
                java.lang.Object r5 = r1.awaitPointerEvent(r5, r4)
                if (r5 != r0) goto L2f
                return r0
            L2f:
                androidx.compose.ui.input.pointer.PointerEvent r5 = (androidx.compose.ui.input.pointer.PointerEvent) r5
                androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r3 = androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.this
                boolean r5 = androidx.compose.foundation.text.selection.SelectionGestures_androidKt.isMouseOrTouchPad(r5)
                r5 = r5 ^ r2
                r3.setInTouchMode(r5)
                goto L22
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.C01842.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$maybeSuggestSelectionRange$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$maybeSuggestSelectionRange$1", f = "TextFieldSelectionState.kt", i = {}, l = {1095}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class C01851 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ PlatformSelectionBehaviors $platformSelectionBehaviors;
        final /* synthetic */ long $selection;
        final /* synthetic */ CharSequence $text;
        int label;
        final /* synthetic */ TextFieldSelectionState this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C01851(PlatformSelectionBehaviors platformSelectionBehaviors, CharSequence charSequence, long j, TextFieldSelectionState textFieldSelectionState, Continuation<? super C01851> continuation) {
            super(2, continuation);
            this.$platformSelectionBehaviors = platformSelectionBehaviors;
            this.$text = charSequence;
            this.$selection = j;
            this.this$0 = textFieldSelectionState;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C01851(this.$platformSelectionBehaviors, this.$text, this.$selection, this.this$0, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PlatformSelectionBehaviors platformSelectionBehaviors = this.$platformSelectionBehaviors;
                CharSequence charSequence = this.$text;
                long j = this.$selection;
                this.label = 1;
                obj = platformSelectionBehaviors.mo1749suggestSelectionForLongPressOrDoubleClickpYaCww(charSequence, j, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            TextRange textRange = (TextRange) obj;
            if (!this.this$0.isPassword && textRange != null && Intrinsics.areEqual(this.this$0.getTextFieldState().getVisualText().getText(), this.$text) && TextRange.equals-impl0(this.this$0.getTextFieldState().getVisualText().getSelection(), this.$selection)) {
                if (!TextRange.equals-impl0(textRange.unbox-impl(), this.this$0.getTextFieldState().getVisualText().getSelection())) {
                    this.this$0.getTextFieldState().m1616selectCharsIn5zctL8(textRange.unbox-impl());
                }
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$observeTextChanges$3, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final /* synthetic */ class AnonymousClass3 extends FunctionReferenceImpl implements Function2<TextFieldCharSequence, CharSequence, Boolean> {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

        public AnonymousClass3() {
            super(2, TextFieldCharSequence.class, "contentEquals", "contentEquals(Ljava/lang/CharSequence;)Z", 0);
        }

        public final Boolean invoke(TextFieldCharSequence textFieldCharSequence, CharSequence charSequence) {
            return Boolean.valueOf(textFieldCharSequence.contentEquals(charSequence));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$paste$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState", f = "TextFieldSelectionState.kt", i = {1}, l = {1544, 1546, 1546}, m = "paste", n = {"receiveContentConfiguration"}, s = {"L$0"}, v = 1)
    public static final class C01871 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C01871(Continuation<? super C01871> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TextFieldSelectionState.this.paste(this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$pasteAsPlainText$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState", f = "TextFieldSelectionState.kt", i = {}, l = {1577, 1577}, m = "pasteAsPlainText", n = {}, s = {}, v = 1)
    public static final class C01881 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        public C01881(Continuation<? super C01881> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TextFieldSelectionState.this.pasteAsPlainText(this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2", f = "TextFieldSelectionState.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class C01892 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Job>, Object> {
        final /* synthetic */ boolean $isStartHandle;
        final /* synthetic */ PointerInputScope $this_selectionHandleGestures;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2$1", f = "TextFieldSelectionState.kt", i = {}, l = {506}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ PointerInputScope $this_selectionHandleGestures;
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(TextFieldSelectionState textFieldSelectionState, PointerInputScope pointerInputScope, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = textFieldSelectionState;
                this.$this_selectionHandleGestures = pointerInputScope;
            }

            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this$0, this.$this_selectionHandleGestures, continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    TextFieldSelectionState textFieldSelectionState = this.this$0;
                    PointerInputScope pointerInputScope = this.$this_selectionHandleGestures;
                    this.label = 1;
                    if (textFieldSelectionState.detectTouchMode(pointerInputScope, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        k2d.a("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2$2, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2$2", f = "TextFieldSelectionState.kt", i = {}, l = {508}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        public static final class C00382 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ boolean $isStartHandle;
            final /* synthetic */ PointerInputScope $this_selectionHandleGestures;
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00382(PointerInputScope pointerInputScope, TextFieldSelectionState textFieldSelectionState, boolean z, Continuation<? super C00382> continuation) {
                super(2, continuation);
                this.$this_selectionHandleGestures = pointerInputScope;
                this.this$0 = textFieldSelectionState;
                this.$isStartHandle = z;
            }

            public static Unit b(TextFieldSelectionState textFieldSelectionState) {
                textFieldSelectionState.clearHandleDragging();
                return Unit.INSTANCE;
            }

            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00382(this.$this_selectionHandleGestures, this.this$0, this.$isStartHandle, continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    PointerInputScope pointerInputScope = this.$this_selectionHandleGestures;
                    final TextFieldSelectionState textFieldSelectionState = this.this$0;
                    final boolean z = this.$isStartHandle;
                    TapOnPosition tapOnPosition = new TapOnPosition() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.selectionHandleGestures.2.2.1
                        @Override // androidx.compose.foundation.text.input.internal.selection.TapOnPosition
                        /* JADX INFO: renamed from: onEvent-k-4lQ0M */
                        public final void mo1638onEventk4lQ0M(long j) {
                            textFieldSelectionState.markStartContentVisibleOffset();
                            TextFieldSelectionState textFieldSelectionState2 = textFieldSelectionState;
                            boolean z2 = z;
                            textFieldSelectionState2.m1659updateHandleDraggingUv8p0NA(z2 ? Handle.SelectionStart : Handle.SelectionEnd, SelectionHandlesKt.m1764getAdjustedCoordinatesk4lQ0M(textFieldSelectionState2.m1650getHandlePositiontuRUvjQ(z2)));
                        }
                    };
                    final TextFieldSelectionState textFieldSelectionState2 = this.this$0;
                    Function0 function0 = new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.l
                        public final Object invoke() {
                            return TextFieldSelectionState.C01892.C00382.b(textFieldSelectionState2);
                        }
                    };
                    this.label = 1;
                    if (PressDownGestureKt.detectPressDownGesture(pointerInputScope, tapOnPosition, function0, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        k2d.a("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2$4, reason: invalid class name */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2$4", f = "TextFieldSelectionState.kt", i = {}, l = {526}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        public static final class AnonymousClass4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ boolean $isStartHandle;
            final /* synthetic */ PointerInputScope $this_selectionHandleGestures;
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass4(TextFieldSelectionState textFieldSelectionState, PointerInputScope pointerInputScope, boolean z, Continuation<? super AnonymousClass4> continuation) {
                super(2, continuation);
                this.this$0 = textFieldSelectionState;
                this.$this_selectionHandleGestures = pointerInputScope;
                this.$isStartHandle = z;
            }

            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass4(this.this$0, this.$this_selectionHandleGestures, this.$isStartHandle, continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    TextFieldSelectionState textFieldSelectionState = this.this$0;
                    PointerInputScope pointerInputScope = this.$this_selectionHandleGestures;
                    boolean z = this.$isStartHandle;
                    this.label = 1;
                    if (textFieldSelectionState.detectSelectionHandleDragGestures(pointerInputScope, z, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        k2d.a("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C01892(PointerInputScope pointerInputScope, boolean z, Continuation<? super C01892> continuation) {
            super(2, continuation);
            this.$this_selectionHandleGestures = pointerInputScope;
            this.$isStartHandle = z;
        }

        public static Unit b(TextFieldSelectionState textFieldSelectionState, Throwable th) {
            textFieldSelectionState.clearHandleDragging();
            return Unit.INSTANCE;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C01892 c01892 = TextFieldSelectionState.this.new C01892(this.$this_selectionHandleGestures, this.$isStartHandle, continuation);
            c01892.L$0 = obj;
            return c01892;
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Job> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            CoroutineStart coroutineStart = CoroutineStart.UNDISPATCHED;
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, coroutineStart, new AnonymousClass1(TextFieldSelectionState.this, this.$this_selectionHandleGestures, null), 1, (Object) null);
            Job jobLaunch$default = BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, coroutineStart, new C00382(this.$this_selectionHandleGestures, TextFieldSelectionState.this, this.$isStartHandle, null), 1, (Object) null);
            final TextFieldSelectionState textFieldSelectionState = TextFieldSelectionState.this;
            jobLaunch$default.invokeOnCompletion(new Function1() { // from class: androidx.compose.foundation.text.input.internal.selection.k
                public final Object invoke(Object obj2) {
                    return TextFieldSelectionState.C01892.b(textFieldSelectionState, (Throwable) obj2);
                }
            });
            return BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, coroutineStart, new AnonymousClass4(TextFieldSelectionState.this, this.$this_selectionHandleGestures, this.$isStartHandle, null), 1, (Object) null);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$startToolbarAndHandlesVisibilityObserver$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState", f = "TextFieldSelectionState.kt", i = {}, l = {537}, m = "startToolbarAndHandlesVisibilityObserver", n = {}, s = {}, v = 1)
    public static final class C01901 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        public C01901(Continuation<? super C01901> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TextFieldSelectionState.this.startToolbarAndHandlesVisibilityObserver(this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$startToolbarAndHandlesVisibilityObserver$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$startToolbarAndHandlesVisibilityObserver$2", f = "TextFieldSelectionState.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class C01912 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Job>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$startToolbarAndHandlesVisibilityObserver$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$startToolbarAndHandlesVisibilityObserver$2$1", f = "TextFieldSelectionState.kt", i = {}, l = {538}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(TextFieldSelectionState textFieldSelectionState, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = textFieldSelectionState;
            }

            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this$0, continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    TextFieldSelectionState textFieldSelectionState = this.this$0;
                    this.label = 1;
                    if (textFieldSelectionState.observeTextChanges(this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        k2d.a("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$startToolbarAndHandlesVisibilityObserver$2$2, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$startToolbarAndHandlesVisibilityObserver$2$2", f = "TextFieldSelectionState.kt", i = {}, l = {539}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        public static final class C00392 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00392(TextFieldSelectionState textFieldSelectionState, Continuation<? super C00392> continuation) {
                super(2, continuation);
                this.this$0 = textFieldSelectionState;
            }

            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00392(this.this$0, continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    TextFieldSelectionState textFieldSelectionState = this.this$0;
                    this.label = 1;
                    if (textFieldSelectionState.observeTextToolbarVisibility(this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        k2d.a("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }

        public C01912(Continuation<? super C01912> continuation) {
            super(2, continuation);
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C01912 c01912 = TextFieldSelectionState.this.new C01912(continuation);
            c01912.L$0 = obj;
            return c01912;
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Job> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(TextFieldSelectionState.this, null), 3, (Object) null);
            return BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C00392(TextFieldSelectionState.this, null), 3, (Object) null);
        }
    }

    public TextFieldSelectionState(TransformedTextFieldState transformedTextFieldState, TextLayoutState textLayoutState, Density density, boolean z, boolean z2, boolean z3, boolean z4, ToolbarRequester toolbarRequester, CoroutineScope coroutineScope, PlatformSelectionBehaviors platformSelectionBehaviors, Clipboard clipboard) {
        this.textFieldState = transformedTextFieldState;
        this.textLayoutState = textLayoutState;
        this.density = density;
        this.isFocused = z3;
        this.isPassword = z4;
        this.toolbarRequester = toolbarRequester;
        this.coroutineScope = coroutineScope;
        this.platformSelectionBehaviors = platformSelectionBehaviors;
        this.clipboard = clipboard;
        this.enabled = z;
        this.readOnly = z2;
        Offset.Companion companion = Offset.Companion;
        this.startTextLayoutPositionInWindow = SnapshotStateKt.mutableStateOf$default(Offset.box-impl(companion.getUnspecified-F1C5BW0()), (SnapshotMutationPolicy) null, 2, (Object) null);
        this.rawHandleDragPosition = SnapshotStateKt.mutableStateOf$default(Offset.box-impl(companion.getUnspecified-F1C5BW0()), (SnapshotMutationPolicy) null, 2, (Object) null);
        this.draggingHandle = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.directDragGestureInitiator = SnapshotStateKt.mutableStateOf$default(InputType.None, (SnapshotMutationPolicy) null, 2, (Object) null);
        Boolean bool = Boolean.FALSE;
        this.showCursorHandle = SnapshotStateKt.mutableStateOf$default(bool, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.textToolbarState = SnapshotStateKt.mutableStateOf$default(TextToolbarState.None, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.textToolbarShown = SnapshotStateKt.mutableStateOf$default(bool, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.previousRawDragOffset = -1;
        this.derivedVisibleContentBounds = SnapshotStateKt.derivedStateOf(new Function0() { // from class: j9e
            public final Object invoke() {
                return TextFieldSelectionState.k(this.b);
            }
        });
        this.clipboardPasteState = new ClipboardPasteState(this.clipboard);
    }

    public static Unit a(Ref.LongRef longRef, Ref.LongRef longRef2, TextFieldSelectionState textFieldSelectionState) {
        detectCursorHandleDragGestures$onDragStop(longRef, longRef2, textFieldSelectionState);
        return Unit.INSTANCE;
    }

    public static Unit b(Ref.LongRef longRef, Ref.LongRef longRef2, TextFieldSelectionState textFieldSelectionState) {
        detectCursorHandleDragGestures$onDragStop(longRef, longRef2, textFieldSelectionState);
        return Unit.INSTANCE;
    }

    public static TextFieldCharSequence c(TextFieldSelectionState textFieldSelectionState) {
        return textFieldSelectionState.textFieldState.getVisualText();
    }

    private final Rect calculateCursorRect(TextLayoutResult layoutResult, TextFieldCharSequence visualText) {
        if (!TextRange.getCollapsed-impl(visualText.getSelection())) {
            return Rect.Companion.getZero();
        }
        Rect cursorRect = layoutResult.getCursorRect(TextRange.getStart-impl(visualText.getSelection()));
        float fCoerceAtLeast = RangesKt.coerceAtLeast((float) Math.floor(this.density.toPx-0680j_4(TextFieldCursor_androidKt.getDefaultCursorThickness())), 1.0f);
        float f = fCoerceAtLeast / 2.0f;
        float fCoerceAtLeast2 = RangesKt.coerceAtLeast(RangesKt.coerceAtMost(layoutResult.getLayoutInput().getLayoutDirection() == LayoutDirection.Ltr ? cursorRect.getLeft() + (fCoerceAtLeast / 2.0f) : cursorRect.getRight() - (fCoerceAtLeast / 2.0f), ((int) (layoutResult.getSize-YbymL2g() >> 32)) - f), f);
        float fFloor = ((int) fCoerceAtLeast) % 2 == 1 ? ((float) Math.floor(fCoerceAtLeast2)) + 0.5f : (float) Math.rint(fCoerceAtLeast2);
        return new Rect(fFloor - f, cursorRect.getTop(), fFloor + f, cursorRect.getBottom());
    }

    private final Rect calculateSelectionRect(TextLayoutResult layoutResult, TextFieldCharSequence visualText) {
        if (TextRange.getCollapsed-impl(visualText.getSelection())) {
            return Rect.Companion.getZero();
        }
        int lineForOffset = layoutResult.getLineForOffset(TextRange.getStart-impl(visualText.getSelection()));
        int lineForOffset2 = layoutResult.getLineForOffset(TextRange.getEnd-impl(visualText.getSelection()));
        if (lineForOffset != lineForOffset2) {
            return layoutResult.getPathForRange(TextRange.getMin-impl(visualText.getSelection()), TextRange.getMax-impl(visualText.getSelection())).getBounds();
        }
        float horizontalPosition = layoutResult.getHorizontalPosition(TextRange.getStart-impl(visualText.getSelection()), true);
        float horizontalPosition2 = layoutResult.getHorizontalPosition(TextRange.getEnd-impl(visualText.getSelection()), true);
        return new Rect(Math.min(horizontalPosition, horizontalPosition2), layoutResult.getLineTop(lineForOffset), Math.max(horizontalPosition, horizontalPosition2), layoutResult.getLineBottom(lineForOffset2));
    }

    public static /* synthetic */ Object copy$default(TextFieldSelectionState textFieldSelectionState, boolean z, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return textFieldSelectionState.copy(z, continuation);
    }

    public static /* synthetic */ AnnotatedString copyWithResult$foundation$default(TextFieldSelectionState textFieldSelectionState, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return textFieldSelectionState.copyWithResult$foundation(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:8:0x0014  */
    public final Object detectCursorHandleDragGestures(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        Ref.LongRef longRef;
        Throwable th;
        Ref.LongRef longRef2;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            int i = anonymousClass1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label = i - Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        AnonymousClass1 anonymousClass2 = anonymousClass1;
        Object obj = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass2.label;
        if (i2 != 0) {
            if (i2 != 1) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            longRef2 = (Ref.LongRef) anonymousClass2.L$1;
            longRef = (Ref.LongRef) anonymousClass2.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                detectCursorHandleDragGestures$onDragStop(longRef, longRef2, this);
                return Unit.INSTANCE;
            } catch (Throwable th2) {
                th = th2;
                detectCursorHandleDragGestures$onDragStop(longRef, longRef2, this);
                throw th;
            }
        }
        ResultKt.throwOnFailure(obj);
        final Ref.LongRef longRef3 = new Ref.LongRef();
        Offset.Companion companion = Offset.Companion;
        longRef3.element = companion.getUnspecified-F1C5BW0();
        final Ref.LongRef longRef4 = new Ref.LongRef();
        longRef4.element = companion.getUnspecified-F1C5BW0();
        try {
            Function1 function1 = new Function1() { // from class: l9e
                public final Object invoke(Object obj2) {
                    return TextFieldSelectionState.l(longRef3, this, longRef4, (Offset) obj2);
                }
            };
            Function0 function0 = new Function0() { // from class: m9e
                public final Object invoke() {
                    return TextFieldSelectionState.b(longRef3, longRef4, this);
                }
            };
            Function0 function2 = new Function0() { // from class: n9e
                public final Object invoke() {
                    return TextFieldSelectionState.a(longRef3, longRef4, this);
                }
            };
            Function2 function3 = new Function2() { // from class: o9e
                public final Object invoke(Object obj2, Object obj3) {
                    return TextFieldSelectionState.e(longRef4, this, longRef3, (PointerInputChange) obj2, (Offset) obj3);
                }
            };
            anonymousClass2.L$0 = longRef3;
            anonymousClass2.L$1 = longRef4;
            anonymousClass2.label = 1;
            if (DragGestureDetectorKt.detectDragGestures(pointerInputScope, function1, function0, function2, function3, anonymousClass2) == coroutine_suspended) {
                return coroutine_suspended;
            }
            longRef = longRef3;
            longRef2 = longRef4;
            detectCursorHandleDragGestures$onDragStop(longRef, longRef2, this);
            return Unit.INSTANCE;
        } catch (Throwable th3) {
            longRef = longRef3;
            th = th3;
            longRef2 = longRef4;
            detectCursorHandleDragGestures$onDragStop(longRef, longRef2, this);
            throw th;
        }
    }

    private static final void detectCursorHandleDragGestures$onDragStop(Ref.LongRef longRef, Ref.LongRef longRef2, TextFieldSelectionState textFieldSelectionState) {
        if ((longRef.element & SieveCacheKt.InvalidMapping) != 9205357640488583168L) {
            Offset.Companion companion = Offset.Companion;
            longRef.element = companion.getUnspecified-F1C5BW0();
            longRef2.element = companion.getUnspecified-F1C5BW0();
            textFieldSelectionState.clearHandleDragging();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:38:0x00ad  */
    /* JADX WARN: Code duplicated, block: B:49:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:8:0x0016  */
    public final Object detectSelectionHandleDragGestures(PointerInputScope pointerInputScope, final boolean z, Continuation<? super Unit> continuation) throws Throwable {
        C01831 c01831;
        final Handle handle;
        Ref.LongRef longRef;
        Ref.LongRef longRef2;
        if (continuation instanceof C01831) {
            c01831 = (C01831) continuation;
            int i = c01831.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c01831.label = i - Integer.MIN_VALUE;
            } else {
                c01831 = new C01831(continuation);
            }
        } else {
            c01831 = new C01831(continuation);
        }
        C01831 c01832 = c01831;
        Object obj = c01832.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c01832.label;
        if (i2 != 0) {
            if (i2 != 1) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            handle = (Handle) c01832.L$2;
            longRef2 = (Ref.LongRef) c01832.L$1;
            longRef = (Ref.LongRef) c01832.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                TextFieldSelectionStateKt.logDebug(new Function0() { // from class: h9e
                    public final Object invoke() {
                        return TextFieldSelectionState.f(this.b, handle);
                    }
                });
                if (getDraggingHandle() == handle) {
                    detectSelectionHandleDragGestures$onDragStop(longRef, this, longRef2);
                }
                return Unit.INSTANCE;
            } catch (Throwable th) {
                th = th;
                TextFieldSelectionStateKt.logDebug(new Function0() { // from class: h9e
                    public final Object invoke() {
                        return TextFieldSelectionState.f(this.b, handle);
                    }
                });
                if (getDraggingHandle() == handle) {
                    detectSelectionHandleDragGestures$onDragStop(longRef, this, longRef2);
                }
                throw th;
            }
        }
        ResultKt.throwOnFailure(obj);
        final Ref.LongRef longRef3 = new Ref.LongRef();
        Offset.Companion companion = Offset.Companion;
        longRef3.element = companion.getUnspecified-F1C5BW0();
        final Ref.LongRef longRef4 = new Ref.LongRef();
        longRef4.element = companion.getZero-F1C5BW0();
        final Handle handle2 = z ? Handle.SelectionStart : Handle.SelectionEnd;
        try {
            try {
                Function1 function1 = new Function1() { // from class: p9e
                    public final Object invoke(Object obj2) {
                        return TextFieldSelectionState.j(longRef3, this, z, handle2, longRef4, (Offset) obj2);
                    }
                };
                handle2 = handle2;
                longRef3 = longRef3;
                Function0 function0 = new Function0() { // from class: q9e
                    public final Object invoke() {
                        return TextFieldSelectionState.i(longRef3, this, longRef4);
                    }
                };
                Function0 function2 = new Function0() { // from class: f9e
                    public final Object invoke() {
                        return TextFieldSelectionState.m(longRef3, this, longRef4);
                    }
                };
                try {
                    Function2 function3 = new Function2() { // from class: g9e
                        public final Object invoke(Object obj2, Object obj3) {
                            return TextFieldSelectionState.h(longRef4, this, handle2, longRef3, z, (PointerInputChange) obj2, (Offset) obj3);
                        }
                    };
                    longRef4 = longRef4;
                    c01832.L$0 = longRef3;
                    c01832.L$1 = longRef4;
                    c01832.L$2 = handle2;
                    c01832.label = 1;
                    if (DragGestureDetectorKt.detectDragGestures(pointerInputScope, function1, function0, function2, function3, c01832) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    handle = handle2;
                    longRef = longRef3;
                    longRef2 = longRef4;
                    TextFieldSelectionStateKt.logDebug(new Function0() { // from class: h9e
                        public final Object invoke() {
                            return TextFieldSelectionState.f(this.b, handle);
                        }
                    });
                    if (getDraggingHandle() == handle) {
                        detectSelectionHandleDragGestures$onDragStop(longRef, this, longRef2);
                    }
                    return Unit.INSTANCE;
                } catch (Throwable th2) {
                    th = th2;
                    longRef4 = longRef4;
                    handle = handle2;
                    longRef = longRef3;
                    longRef2 = longRef4;
                    TextFieldSelectionStateKt.logDebug(new Function0() { // from class: h9e
                        public final Object invoke() {
                            return TextFieldSelectionState.f(this.b, handle);
                        }
                    });
                    if (getDraggingHandle() == handle) {
                        detectSelectionHandleDragGestures$onDragStop(longRef, this, longRef2);
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                handle2 = handle2;
                longRef3 = longRef3;
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }

    private static final void detectSelectionHandleDragGestures$onDragStop(Ref.LongRef longRef, TextFieldSelectionState textFieldSelectionState, Ref.LongRef longRef2) {
        if ((longRef.element & SieveCacheKt.InvalidMapping) != 9205357640488583168L) {
            textFieldSelectionState.clearHandleDragging();
            Offset.Companion companion = Offset.Companion;
            longRef.element = companion.getUnspecified-F1C5BW0();
            longRef2.element = companion.getZero-F1C5BW0();
            textFieldSelectionState.previousRawDragOffset = -1;
        }
    }

    public static Unit e(Ref.LongRef longRef, TextFieldSelectionState textFieldSelectionState, Ref.LongRef longRef2, PointerInputChange pointerInputChange, Offset offset) {
        long j = Offset.plus-MK-Hz9U(longRef.element, offset.unbox-impl());
        longRef.element = j;
        textFieldSelectionState.m1659updateHandleDraggingUv8p0NA(Handle.Cursor, Offset.plus-MK-Hz9U(longRef2.element, j));
        if (textFieldSelectionState.m1658placeCursorAtNearestOffsetk4lQ0M(textFieldSelectionState.m1657getHandleDragPositionF1C5BW0())) {
            pointerInputChange.consume();
            HapticFeedback hapticFeedback = textFieldSelectionState.hapticFeedBack;
            if (hapticFeedback != null) {
                hapticFeedback.performHapticFeedback-CdsT49E(HapticFeedbackType.Companion.getTextHandleMove-5zf0vsI());
            }
        }
        return Unit.INSTANCE;
    }

    public static String f(TextFieldSelectionState textFieldSelectionState, Handle handle) {
        return "Selection Handle drag cancelled for draggingHandle: " + textFieldSelectionState.getDraggingHandle() + " definedOn: " + handle;
    }

    public static Rect g(TextFieldSelectionState textFieldSelectionState) {
        return textFieldSelectionState.getDerivedVisibleContentBounds$foundation();
    }

    private final Rect getContentRect() {
        LayoutCoordinates textLayoutCoordinates = getTextLayoutCoordinates();
        if (textLayoutCoordinates == null) {
            InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("textLayoutCoordinates should not be null.");
            wq6.a();
            return null;
        }
        TextFieldCharSequence visualText = this.textFieldState.getVisualText();
        if (TextRange.getCollapsed-impl(visualText.getSelection())) {
            Rect cursorRect = getCursorRect();
            return RectKt.Rect-tz77jQw(textLayoutCoordinates.localToRoot-MK-Hz9U(cursorRect.getTopLeft-F1C5BW0()), cursorRect.getSize-NH-jbRc());
        }
        long j = textLayoutCoordinates.localToRoot-MK-Hz9U(m1650getHandlePositiontuRUvjQ(true));
        long j2 = textLayoutCoordinates.localToRoot-MK-Hz9U(m1650getHandlePositiontuRUvjQ(false));
        TextLayoutResult layoutResult = this.textLayoutState.getLayoutResult();
        if (layoutResult == null) {
            return Rect.Companion.getZero();
        }
        float fIntBitsToFloat = Float.intBitsToFloat((int) (textLayoutCoordinates.localToRoot-MK-Hz9U(Offset.constructor-impl((((long) Float.floatToRawIntBits(0.0f)) << 32) | (((long) Float.floatToRawIntBits(layoutResult.getCursorRect(TextRange.getStart-impl(visualText.getSelection())).getTop())) & 4294967295L))) & 4294967295L));
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (textLayoutCoordinates.localToRoot-MK-Hz9U(Offset.constructor-impl((((long) Float.floatToRawIntBits(layoutResult.getCursorRect(TextRange.getEnd-impl(visualText.getSelection())).getTop())) & 4294967295L) | (Float.floatToRawIntBits(0.0f) << 32))) & 4294967295L));
        int i = (int) (j >> 32);
        int i2 = (int) (j2 >> 32);
        return new Rect(Math.min(Float.intBitsToFloat(i), Float.intBitsToFloat(i2)), Math.min(fIntBitsToFloat, fIntBitsToFloat2), Math.max(Float.intBitsToFloat(i), Float.intBitsToFloat(i2)), Math.max(Float.intBitsToFloat((int) (j & 4294967295L)), Float.intBitsToFloat((int) (j2 & 4294967295L))));
    }

    /* JADX INFO: renamed from: getCurrentTextLayoutPositionInWindow-F1C5BW0, reason: not valid java name */
    private final long m1649getCurrentTextLayoutPositionInWindowF1C5BW0() {
        LayoutCoordinates textLayoutCoordinates = getTextLayoutCoordinates();
        return textLayoutCoordinates != null ? LayoutCoordinatesKt.positionInWindow(textLayoutCoordinates) : Offset.Companion.getUnspecified-F1C5BW0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getHandlePosition-tuRUvjQ, reason: not valid java name */
    public final long m1650getHandlePositiontuRUvjQ(boolean isStartHandle) {
        TextLayoutResult layoutResult = this.textLayoutState.getLayoutResult();
        if (layoutResult == null) {
            return Offset.Companion.getZero-F1C5BW0();
        }
        long selection = this.textFieldState.getVisualText().getSelection();
        return TextSelectionDelegateKt.getSelectionHandleCoordinates(layoutResult, isStartHandle ? TextRange.getStart-impl(selection) : TextRange.getEnd-impl(selection), isStartHandle, TextRange.getReversed-impl(selection));
    }

    /* JADX INFO: renamed from: getRawHandleDragPosition-F1C5BW0, reason: not valid java name */
    private final long m1651getRawHandleDragPositionF1C5BW0() {
        return ((Offset) this.rawHandleDragPosition.getValue()).unbox-impl();
    }

    /* JADX INFO: renamed from: getStartTextLayoutPositionInWindow-F1C5BW0, reason: not valid java name */
    private final long m1652getStartTextLayoutPositionInWindowF1C5BW0() {
        return ((Offset) this.startTextLayoutPositionInWindow.getValue()).unbox-impl();
    }

    /* JADX INFO: renamed from: getTextFieldSelection-qeG_v_k, reason: not valid java name */
    private final long m1653getTextFieldSelectionqeG_v_k(int rawStartOffset, int rawEndOffset, TextRange previousSelection, boolean isStartHandle, SelectionAdjustment adjustment) {
        TextLayoutResult layoutResult = this.textLayoutState.getLayoutResult();
        if (layoutResult == null) {
            return TextRange.Companion.getZero-d9O1mEE();
        }
        if (previousSelection == null && Intrinsics.areEqual(adjustment, SelectionAdjustment.INSTANCE.getCharacter())) {
            return TextRangeKt.TextRange(rawStartOffset, rawEndOffset);
        }
        SelectionLayout selectionLayoutM1767getTextFieldSelectionLayoutRcvTLA = SelectionLayoutKt.m1767getTextFieldSelectionLayoutRcvTLA(layoutResult, rawStartOffset, rawEndOffset, this.previousRawDragOffset, previousSelection != null ? previousSelection.unbox-impl() : TextRange.Companion.getZero-d9O1mEE(), previousSelection == null, isStartHandle);
        if (previousSelection != null && !selectionLayoutM1767getTextFieldSelectionLayoutRcvTLA.shouldRecomputeSelection(this.previousSelectionLayout)) {
            return previousSelection.unbox-impl();
        }
        long jM1759toTextRanged9O1mEE = adjustment.adjust(selectionLayoutM1767getTextFieldSelectionLayoutRcvTLA).m1759toTextRanged9O1mEE();
        this.previousSelectionLayout = selectionLayoutM1767getTextFieldSelectionLayoutRcvTLA;
        this.previousRawDragOffset = isStartHandle ? rawStartOffset : rawEndOffset;
        return jM1759toTextRanged9O1mEE;
    }

    private final LayoutCoordinates getTextLayoutCoordinates() {
        LayoutCoordinates textLayoutNodeCoordinates = this.textLayoutState.getTextLayoutNodeCoordinates();
        if (textLayoutNodeCoordinates == null || !textLayoutNodeCoordinates.isAttached()) {
            return null;
        }
        return textLayoutNodeCoordinates;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final TextToolbarState getTextToolbarState() {
        return (TextToolbarState) this.textToolbarState.getValue();
    }

    public static Unit h(Ref.LongRef longRef, TextFieldSelectionState textFieldSelectionState, Handle handle, Ref.LongRef longRef2, boolean z, PointerInputChange pointerInputChange, Offset offset) {
        longRef.element = Offset.plus-MK-Hz9U(longRef.element, offset.unbox-impl());
        TextLayoutResult layoutResult = textFieldSelectionState.textLayoutState.getLayoutResult();
        if (layoutResult == null) {
            return Unit.INSTANCE;
        }
        textFieldSelectionState.m1659updateHandleDraggingUv8p0NA(handle, Offset.plus-MK-Hz9U(longRef2.element, longRef.element));
        int i = z ? layoutResult.getOffsetForPosition-k-4lQ0M(textFieldSelectionState.m1657getHandleDragPositionF1C5BW0()) : TextRange.getStart-impl(textFieldSelectionState.textFieldState.getVisualText().getSelection());
        int i2 = z ? TextRange.getEnd-impl(textFieldSelectionState.textFieldState.getVisualText().getSelection()) : layoutResult.getOffsetForPosition-k-4lQ0M(textFieldSelectionState.m1657getHandleDragPositionF1C5BW0());
        long selection = textFieldSelectionState.textFieldState.getVisualText().getSelection();
        long jM1656updateSelectionSsLRf8$foundation$default = m1656updateSelectionSsLRf8$foundation$default(textFieldSelectionState, textFieldSelectionState.textFieldState.getVisualText(), i, i2, z, SelectionAdjustment.INSTANCE.getCharacterWithWordAccelerate(), false, false, 96, null);
        if (TextRange.getCollapsed-impl(selection) || !TextRange.getCollapsed-impl(jM1656updateSelectionSsLRf8$foundation$default)) {
            textFieldSelectionState.textFieldState.m1616selectCharsIn5zctL8(jM1656updateSelectionSsLRf8$foundation$default);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideTextToolbar() {
        if (ComposeFoundationFlags.isNewContextMenuEnabled) {
            this.toolbarRequester.hide();
            return;
        }
        TextToolbarHandler textToolbarHandler = this.textToolbarHandler;
        if (textToolbarHandler != null) {
            textToolbarHandler.hideTextToolbar();
        }
    }

    public static Unit i(Ref.LongRef longRef, TextFieldSelectionState textFieldSelectionState, Ref.LongRef longRef2) {
        detectSelectionHandleDragGestures$onDragStop(longRef, textFieldSelectionState, longRef2);
        return Unit.INSTANCE;
    }

    private final boolean isCursorHandleInVisibleBounds() {
        Rect rectVisibleBounds;
        Snapshot.Companion companion = Snapshot.Companion;
        Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
        Function1 readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
        Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
        try {
            long j = getCursorRect().getBottomCenter-F1C5BW0();
            companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
            LayoutCoordinates textLayoutCoordinates = getTextLayoutCoordinates();
            if (textLayoutCoordinates == null || (rectVisibleBounds = SelectionManagerKt.visibleBounds(textLayoutCoordinates)) == null) {
                return false;
            }
            return SelectionManagerKt.m1793containsInclusiveUv8p0NA(rectVisibleBounds, j);
        } catch (Throwable th) {
            companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
            throw th;
        }
    }

    public static Unit j(Ref.LongRef longRef, TextFieldSelectionState textFieldSelectionState, boolean z, Handle handle, Ref.LongRef longRef2, Offset offset) {
        long jM1764getAdjustedCoordinatesk4lQ0M = SelectionHandlesKt.m1764getAdjustedCoordinatesk4lQ0M(textFieldSelectionState.m1650getHandlePositiontuRUvjQ(z));
        longRef.element = jM1764getAdjustedCoordinatesk4lQ0M;
        textFieldSelectionState.m1659updateHandleDraggingUv8p0NA(handle, jM1764getAdjustedCoordinatesk4lQ0M);
        longRef2.element = Offset.Companion.getZero-F1C5BW0();
        textFieldSelectionState.previousRawDragOffset = -1;
        return Unit.INSTANCE;
    }

    public static Rect k(TextFieldSelectionState textFieldSelectionState) {
        LayoutCoordinates textLayoutCoordinates;
        boolean z = TextRange.getCollapsed-impl(textFieldSelectionState.textFieldState.getVisualText().getSelection());
        if ((!(z && textFieldSelectionState.getTextToolbarState() == TextToolbarState.Cursor) && (z || textFieldSelectionState.getTextToolbarState() != TextToolbarState.Selection)) || textFieldSelectionState.getDraggingHandle() != null || !textFieldSelectionState.isInTouchMode() || (textLayoutCoordinates = textFieldSelectionState.getTextLayoutCoordinates()) == null) {
            return null;
        }
        Rect rectVisibleBounds = SelectionManagerKt.visibleBounds(textLayoutCoordinates);
        Rect rect = RectKt.Rect-tz77jQw(textLayoutCoordinates.localToRoot-MK-Hz9U(rectVisibleBounds.getTopLeft-F1C5BW0()), rectVisibleBounds.getSize-NH-jbRc());
        Rect contentRect = textFieldSelectionState.getContentRect();
        if (contentRect.overlaps(rect)) {
            return contentRect.intersect(rect);
        }
        return null;
    }

    public static Unit l(Ref.LongRef longRef, TextFieldSelectionState textFieldSelectionState, Ref.LongRef longRef2, Offset offset) {
        longRef.element = SelectionHandlesKt.m1764getAdjustedCoordinatesk4lQ0M(textFieldSelectionState.getCursorRect().getBottomCenter-F1C5BW0());
        longRef2.element = Offset.Companion.getZero-F1C5BW0();
        textFieldSelectionState.setInTouchMode(true);
        textFieldSelectionState.markStartContentVisibleOffset();
        textFieldSelectionState.m1659updateHandleDraggingUv8p0NA(Handle.Cursor, longRef.element);
        return Unit.INSTANCE;
    }

    public static Unit m(Ref.LongRef longRef, TextFieldSelectionState textFieldSelectionState, Ref.LongRef longRef2) {
        detectSelectionHandleDragGestures$onDragStop(longRef, textFieldSelectionState, longRef2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void markStartContentVisibleOffset() {
        m1655setStartTextLayoutPositionInWindowk4lQ0M(m1649getCurrentTextLayoutPositionInWindowF1C5BW0());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object observeTextChanges(Continuation<? super Unit> continuation) {
        Object objCollect = FlowKt.drop(FlowKt.distinctUntilChanged(SnapshotStateKt.snapshotFlow(new Function0() { // from class: k9e
            public final Object invoke() {
                return TextFieldSelectionState.c(this.b);
            }
        }), AnonymousClass3.INSTANCE), 1).collect(new FlowCollector() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.observeTextChanges.4
            public final Object emit(TextFieldCharSequence textFieldCharSequence, Continuation<? super Unit> continuation2) {
                TextFieldSelectionState.this.setShowCursorHandle(false);
                TextFieldSelectionState.this.updateTextToolbarState(TextToolbarState.None);
                return Unit.INSTANCE;
            }

            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation2) {
                return emit((TextFieldCharSequence) obj, (Continuation<? super Unit>) continuation2);
            }
        }, continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object observeTextToolbarVisibility(Continuation<? super Unit> continuation) {
        Flow flowSnapshotFlow = SnapshotStateKt.snapshotFlow(new Function0() { // from class: e9e
            public final Object invoke() {
                return TextFieldSelectionState.g(this.b);
            }
        });
        if (ComposeFoundationFlags.isNewContextMenuEnabled) {
            flowSnapshotFlow = FlowKt.distinctUntilChangedBy(flowSnapshotFlow, new Function1() { // from class: i9e
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(TextFieldSelectionState.observeTextToolbarVisibility$lambda$1$0((Rect) obj));
                }
            });
        }
        Object objCollect = flowSnapshotFlow.collect(new FlowCollector() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.observeTextToolbarVisibility.4
            public final Object emit(Rect rect, Continuation<? super Unit> continuation2) {
                TextFieldSelectionState textFieldSelectionState = TextFieldSelectionState.this;
                if (rect != null) {
                    Object objShowTextToolbar = textFieldSelectionState.showTextToolbar(rect, continuation2);
                    return objShowTextToolbar == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objShowTextToolbar : Unit.INSTANCE;
                }
                textFieldSelectionState.hideTextToolbar();
                return Unit.INSTANCE;
            }

            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation2) {
                return emit((Rect) obj, (Continuation<? super Unit>) continuation2);
            }
        }, continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean observeTextToolbarVisibility$lambda$1$0(Rect rect) {
        return rect == null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004f, code lost:
    
        if (r8 == r1) goto L23;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object pasteAsPlainText(Continuation<? super Unit> continuation) {
        C01881 c01881;
        if (continuation instanceof C01881) {
            c01881 = (C01881) continuation;
            int i = c01881.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c01881.label = i - Integer.MIN_VALUE;
            } else {
                c01881 = new C01881(continuation);
            }
        } else {
            c01881 = new C01881(continuation);
        }
        Object clipEntry = c01881.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c01881.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(clipEntry);
            Clipboard clipboard = this.clipboard;
            c01881.label = 1;
            clipEntry = clipboard.getClipEntry(c01881);
            if (clipEntry != coroutine_suspended) {
            }
            return coroutine_suspended;
        }
        if (i2 == 1) {
            ResultKt.throwOnFailure(clipEntry);
        } else {
            if (i2 != 2) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(clipEntry);
        }
        String str = (String) clipEntry;
        if (str != null) {
            TransformedTextFieldState.replaceSelectedText$default(this.textFieldState, str, false, TextFieldEditUndoBehavior.NeverMerge, false, 10, null);
            return Unit.INSTANCE;
        }
        return Unit.INSTANCE;
        ClipEntry clipEntry2 = (ClipEntry) clipEntry;
        if (clipEntry2 != null) {
            c01881.label = 2;
            clipEntry = ClipboardUtils_androidKt.readText(clipEntry2, c01881);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: setRawHandleDragPosition-k-4lQ0M, reason: not valid java name */
    private final void m1654setRawHandleDragPositionk4lQ0M(long j) {
        this.rawHandleDragPosition.setValue(Offset.box-impl(j));
    }

    /* JADX INFO: renamed from: setStartTextLayoutPositionInWindow-k-4lQ0M, reason: not valid java name */
    private final void m1655setStartTextLayoutPositionInWindowk4lQ0M(long j) {
        this.startTextLayoutPositionInWindow.setValue(Offset.box-impl(j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setTextToolbarState(TextToolbarState textToolbarState) {
        this.textToolbarState.setValue(textToolbarState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object showTextToolbar(Rect rect, Continuation<? super Unit> continuation) {
        if (ComposeFoundationFlags.isNewContextMenuEnabled) {
            this.toolbarRequester.show();
        } else {
            TextToolbarHandler textToolbarHandler = this.textToolbarHandler;
            if (textToolbarHandler != null) {
                Object objShowTextToolbar = textToolbarHandler.showTextToolbar(this, rect, continuation);
                return objShowTextToolbar == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objShowTextToolbar : Unit.INSTANCE;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: updateSelection-SsL-Rf8$foundation$default, reason: not valid java name */
    public static /* synthetic */ long m1656updateSelectionSsLRf8$foundation$default(TextFieldSelectionState textFieldSelectionState, TextFieldCharSequence textFieldCharSequence, int i, int i2, boolean z, SelectionAdjustment selectionAdjustment, boolean z2, boolean z3, int i3, Object obj) {
        if ((i3 & 32) != 0) {
            z2 = false;
        }
        if ((i3 & 64) != 0) {
            z3 = false;
        }
        return textFieldSelectionState.m1660updateSelectionSsLRf8$foundation(textFieldCharSequence, i, i2, z, selectionAdjustment, z2, z3);
    }

    public final void autofill() {
        Function0<Unit> function0 = this.requestAutofillAction;
        if (function0 != null) {
            function0.invoke();
        }
    }

    public final boolean canShowAutofillMenuItem() {
        return getEditable$foundation() && TextRange.getCollapsed-impl(this.textFieldState.getVisualText().getSelection());
    }

    public final boolean canShowCopyMenuItem() {
        return (TextRange.getCollapsed-impl(getTextFieldState().getVisualText().getSelection()) || this.isPassword || !ClipboardUtils_androidKt.isWriteSupported(this.clipboard)) ? false : true;
    }

    public final boolean canShowCutMenuItem() {
        return !TextRange.getCollapsed-impl(getTextFieldState().getVisualText().getSelection()) && getEditable$foundation() && !this.isPassword && ClipboardUtils_androidKt.isWriteSupported(this.clipboard);
    }

    public final boolean canShowPasteMenuItem() {
        if (getEditable$foundation() && ClipboardUtils_androidKt.isReadSupported(this.clipboard)) {
            if (this.clipboardPasteState.get_hasText()) {
                return true;
            }
            Function0<? extends ReceiveContentConfiguration> function0 = this.receiveContentConfiguration;
            if ((function0 != null ? (ReceiveContentConfiguration) function0.invoke() : null) != null && this.clipboardPasteState.get_hasClip()) {
                return true;
            }
        }
        return false;
    }

    public final boolean canShowSelectAllMenuItem() {
        return TextRange.getLength-impl(this.textFieldState.getVisualText().getSelection()) != this.textFieldState.getVisualText().length();
    }

    public final void clearHandleDragging() {
        setDraggingHandle(null);
        Offset.Companion companion = Offset.Companion;
        m1654setRawHandleDragPositionk4lQ0M(companion.getUnspecified-F1C5BW0());
        m1655setStartTextLayoutPositionInWindowk4lQ0M(companion.getUnspecified-F1C5BW0());
    }

    public final Object copy(boolean z, Continuation<? super Unit> continuation) {
        Object clipEntry;
        AnnotatedString annotatedStringCopyWithResult$foundation = copyWithResult$foundation(z);
        return (annotatedStringCopyWithResult$foundation != null && (clipEntry = this.clipboard.setClipEntry(ClipboardUtils_androidKt.toClipEntry(annotatedStringCopyWithResult$foundation), continuation)) == IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? clipEntry : Unit.INSTANCE;
    }

    public final AnnotatedString copyWithResult$foundation(boolean cancelSelection) {
        if (TextRange.getCollapsed-impl(getTextFieldState().getVisualText().getSelection()) || this.isPassword) {
            return null;
        }
        AnnotatedString annotatedString = new AnnotatedString(TextFieldCharSequenceKt.getSelectedText(this.textFieldState.getVisualText()).toString(), (List) null, 2, (DefaultConstructorMarker) null);
        if (cancelSelection) {
            this.textFieldState.collapseSelectionToMax();
        }
        return annotatedString;
    }

    public final Object cursorHandleGestures(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new AnonymousClass2(pointerInputScope, null), continuation);
        return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
    }

    public final Object cut(Continuation<? super Unit> continuation) {
        Object clipEntry;
        AnnotatedString annotatedStringCutWithResult = cutWithResult();
        return (annotatedStringCutWithResult != null && (clipEntry = this.clipboard.setClipEntry(ClipboardUtils_androidKt.toClipEntry(annotatedStringCutWithResult), continuation)) == IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? clipEntry : Unit.INSTANCE;
    }

    public final AnnotatedString cutWithResult() {
        if (TextRange.getCollapsed-impl(getTextFieldState().getVisualText().getSelection()) || !getEditable$foundation() || this.isPassword) {
            return null;
        }
        AnnotatedString annotatedString = new AnnotatedString(TextFieldCharSequenceKt.getSelectedText(this.textFieldState.getVisualText()).toString(), (List) null, 2, (DefaultConstructorMarker) null);
        this.textFieldState.deleteSelectedText();
        return annotatedString;
    }

    public final void deselect() {
        if (!TextRange.getCollapsed-impl(this.textFieldState.getVisualText().getSelection())) {
            this.textFieldState.collapseSelectionToEnd();
        }
        setShowCursorHandle(false);
        updateTextToolbarState(TextToolbarState.None);
    }

    public final Object detectTextFieldTapGestures(PointerInputScope pointerInputScope, MutableInteractionSource mutableInteractionSource, Function0<Unit> function0, Function0<Unit> function1, Continuation<? super Unit> continuation) {
        Object objDetectTextFieldTapGestures = TextFieldSelectionState_androidKt.detectTextFieldTapGestures(this, pointerInputScope, mutableInteractionSource, function0, function1, continuation);
        return objDetectTextFieldTapGestures == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDetectTextFieldTapGestures : Unit.INSTANCE;
    }

    public final Object detectTouchMode(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
        Object objAwaitPointerEventScope = pointerInputScope.awaitPointerEventScope(new C01842(null), continuation);
        return objAwaitPointerEventScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitPointerEventScope : Unit.INSTANCE;
    }

    public final void dispose() {
        hideTextToolbar();
        this.hapticFeedBack = null;
    }

    public final TextFieldHandleState getCursorHandleState$foundation(boolean includePosition) {
        TextFieldCharSequence visualText = this.textFieldState.getVisualText();
        boolean showCursorHandle = getShowCursorHandle();
        boolean z = getDirectDragGestureInitiator() == InputType.None;
        Handle draggingHandle = getDraggingHandle();
        if (showCursorHandle && z && TextRange.getCollapsed-impl(visualText.getSelection()) && visualText.shouldShowSelection() && visualText.length() > 0 && (draggingHandle == Handle.Cursor || isCursorHandleInVisibleBounds())) {
            return new TextFieldHandleState(true, includePosition ? getCursorRect().getBottomCenter-F1C5BW0() : Offset.Companion.getUnspecified-F1C5BW0(), 0.0f, ResolvedTextDirection.Ltr, false, null);
        }
        return TextFieldHandleState.INSTANCE.getHidden();
    }

    public final Rect getCursorRect() {
        TextLayoutResult layoutResult = this.textLayoutState.getLayoutResult();
        return layoutResult == null ? Rect.Companion.getZero() : calculateCursorRect(layoutResult, this.textFieldState.getVisualText());
    }

    public final Rect getDerivedVisibleContentBounds$foundation() {
        return (Rect) this.derivedVisibleContentBounds.getValue();
    }

    public final InputType getDirectDragGestureInitiator() {
        return (InputType) this.directDragGestureInitiator.getValue();
    }

    public final Handle getDraggingHandle() {
        return (Handle) this.draggingHandle.getValue();
    }

    public final boolean getEditable$foundation() {
        return this.enabled && !this.readOnly;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final Rect getFocusRect() {
        TextLayoutResult layoutResult = this.textLayoutState.getLayoutResult();
        if (layoutResult == null) {
            return Rect.Companion.getZero();
        }
        if (!this.isFocused) {
            return FocusProperties.Companion.getUnsetFocusRect();
        }
        TextFieldCharSequence visualText = this.textFieldState.getVisualText();
        return TextLayoutStateKt.fromTextLayoutToDecoration(this.textLayoutState, TextRange.getCollapsed-impl(visualText.getSelection()) ? calculateCursorRect(layoutResult, visualText) : calculateSelectionRect(layoutResult, visualText));
    }

    /* JADX INFO: renamed from: getHandleDragPosition-F1C5BW0, reason: not valid java name */
    public final long m1657getHandleDragPositionF1C5BW0() {
        if ((m1651getRawHandleDragPositionF1C5BW0() & SieveCacheKt.InvalidMapping) == 9205357640488583168L) {
            return Offset.Companion.getUnspecified-F1C5BW0();
        }
        return (m1652getStartTextLayoutPositionInWindowF1C5BW0() & SieveCacheKt.InvalidMapping) == 9205357640488583168L ? TextLayoutStateKt.m1604fromDecorationToTextLayoutUv8p0NA(this.textLayoutState, m1651getRawHandleDragPositionF1C5BW0()) : Offset.plus-MK-Hz9U(m1651getRawHandleDragPositionF1C5BW0(), Offset.minus-MK-Hz9U(m1652getStartTextLayoutPositionInWindowF1C5BW0(), m1649getCurrentTextLayoutPositionInWindowF1C5BW0()));
    }

    public final HapticFeedback getHapticFeedBack() {
        return this.hapticFeedBack;
    }

    /* JADX INFO: renamed from: getPlatformSelectionBehaviors$foundation, reason: from getter */
    public final PlatformSelectionBehaviors getPlatformSelectionBehaviors() {
        return this.platformSelectionBehaviors;
    }

    public final PressInteraction.Press getPressInteraction() {
        return this.pressInteraction;
    }

    public final boolean getReadOnly() {
        return this.readOnly;
    }

    public final Function0<ReceiveContentConfiguration> getReceiveContentConfiguration() {
        return this.receiveContentConfiguration;
    }

    public final Function0<Unit> getRequestAutofillAction() {
        return this.requestAutofillAction;
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0052, code lost:
    
        if (((r0 == null || (r0 = androidx.compose.foundation.text.selection.SelectionManagerKt.visibleBounds(r0)) == null) ? false : androidx.compose.foundation.text.selection.SelectionManagerKt.m1793containsInclusiveUv8p0NA(r0, r4)) != false) goto L24;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final TextFieldHandleState getSelectionHandleState$foundation(boolean isStartHandle, boolean includePosition) {
        Rect rectVisibleBounds;
        Handle handle = isStartHandle ? Handle.SelectionStart : Handle.SelectionEnd;
        TextLayoutResult layoutResult = this.textLayoutState.getLayoutResult();
        if (layoutResult == null) {
            return TextFieldHandleState.INSTANCE.getHidden();
        }
        long selection = this.textFieldState.getVisualText().getSelection();
        if (TextRange.getCollapsed-impl(selection)) {
            return TextFieldHandleState.INSTANCE.getHidden();
        }
        long jM1650getHandlePositiontuRUvjQ = m1650getHandlePositiontuRUvjQ(isStartHandle);
        if (getDirectDragGestureInitiator() == InputType.None) {
            if (getDraggingHandle() != handle) {
                LayoutCoordinates textLayoutCoordinates = getTextLayoutCoordinates();
            }
            if (!this.textFieldState.getVisualText().shouldShowSelection()) {
                return TextFieldHandleState.INSTANCE.getHidden();
            }
            ResolvedTextDirection bidiRunDirection = layoutResult.getBidiRunDirection(isStartHandle ? TextRange.getStart-impl(selection) : Math.max(TextRange.getEnd-impl(selection) - 1, 0));
            boolean z = TextRange.getReversed-impl(selection);
            if (includePosition) {
                LayoutCoordinates textLayoutCoordinates2 = getTextLayoutCoordinates();
                if (textLayoutCoordinates2 != null && (rectVisibleBounds = SelectionManagerKt.visibleBounds(textLayoutCoordinates2)) != null) {
                    jM1650getHandlePositiontuRUvjQ = TextLayoutStateKt.m1603coerceIn3MmeM6k(jM1650getHandlePositiontuRUvjQ, rectVisibleBounds);
                }
            } else {
                jM1650getHandlePositiontuRUvjQ = Offset.Companion.getUnspecified-F1C5BW0();
            }
            return new TextFieldHandleState(true, jM1650getHandlePositiontuRUvjQ, TextLayoutHelperKt.getLineHeight(layoutResult, isStartHandle ? TextRange.getStart-impl(selection) : TextRange.getEnd-impl(selection)), bidiRunDirection, z, null);
        }
        return TextFieldHandleState.INSTANCE.getHidden();
    }

    public final boolean getShowCursorHandle() {
        return ((Boolean) this.showCursorHandle.getValue()).booleanValue();
    }

    /* JADX INFO: renamed from: getTextFieldState$foundation, reason: from getter */
    public final TransformedTextFieldState getTextFieldState() {
        return this.textFieldState;
    }

    /* JADX INFO: renamed from: getTextLayoutState$foundation, reason: from getter */
    public final TextLayoutState getTextLayoutState() {
        return this.textLayoutState;
    }

    public final boolean getTextToolbarShown() {
        return ((Boolean) this.textToolbarShown.getValue()).booleanValue();
    }

    public final boolean isCopyAllowed() {
        return (TextRange.getCollapsed-impl(getTextFieldState().getVisualText().getSelection()) || this.isPassword) ? false : true;
    }

    public final boolean isCutAllowed() {
        return (TextRange.getCollapsed-impl(getTextFieldState().getVisualText().getSelection()) || !getEditable$foundation() || this.isPassword) ? false : true;
    }

    /* JADX INFO: renamed from: isFocused, reason: from getter */
    public final boolean getIsFocused() {
        return this.isFocused;
    }

    public final boolean isInTouchMode() {
        return ((Boolean) this.isInTouchMode.getValue()).booleanValue();
    }

    public final boolean isPasteAllowed() {
        return getEditable$foundation();
    }

    public final void maybeSuggestSelectionRange() {
        PlatformSelectionBehaviors platformSelectionBehaviors = this.platformSelectionBehaviors;
        if (platformSelectionBehaviors == null) {
            return;
        }
        CharSequence text = this.textFieldState.getVisualText().getText();
        long selection = this.textFieldState.getVisualText().getSelection();
        if (text.length() <= 0 || TextRange.getCollapsed-impl(selection)) {
            return;
        }
        BuildersKt.launch$default(this.coroutineScope, (CoroutineContext) null, CoroutineStart.UNDISPATCHED, new C01851(platformSelectionBehaviors, text, selection, this, null), 1, (Object) null);
    }

    public final void onPasteEvent$foundation(AnnotatedString value) {
        if (getEditable$foundation()) {
            TransformedTextFieldState.replaceSelectedText$default(this.textFieldState, value.getText(), false, TextFieldEditUndoBehavior.NeverMerge, false, 10, null);
        }
    }

    /* JADX WARN: Code duplicated, block: B:28:0x0066  */
    /* JADX WARN: Code duplicated, block: B:33:0x0074  */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x006e, code lost:
    
        if (pasteAsPlainText(r0) == r1) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00b2, code lost:
    
        if (pasteAsPlainText(r0) == r1) goto L44;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object paste(Continuation<? super Unit> continuation) {
        C01871 c01871;
        ReceiveContentConfiguration receiveContentConfiguration;
        ClipEntry clipEntry;
        TransferableContent transferableContentOnReceive;
        ClipEntry clipEntry2;
        String plainText;
        if (continuation instanceof C01871) {
            c01871 = (C01871) continuation;
            int i = c01871.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c01871.label = i - Integer.MIN_VALUE;
            } else {
                c01871 = new C01871(continuation);
            }
        } else {
            c01871 = new C01871(continuation);
        }
        Object clipEntry3 = c01871.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c01871.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(clipEntry3);
            Function0<? extends ReceiveContentConfiguration> function0 = this.receiveContentConfiguration;
            if (function0 == null || (receiveContentConfiguration = (ReceiveContentConfiguration) function0.invoke()) == null) {
                c01871.label = 1;
            } else {
                Clipboard clipboard = this.clipboard;
                c01871.L$0 = receiveContentConfiguration;
                c01871.label = 2;
                clipEntry3 = clipboard.getClipEntry(c01871);
                if (clipEntry3 != coroutine_suspended) {
                    clipEntry = (ClipEntry) clipEntry3;
                    if (clipEntry == null) {
                        transferableContentOnReceive = receiveContentConfiguration.getReceiveContentListener().onReceive(new TransferableContent(clipEntry, clipEntry.getClipMetadata(), TransferableContent.Source.INSTANCE.m441getClipboardkB6V9T0(), null, 8, null));
                        if (transferableContentOnReceive != null) {
                            TransformedTextFieldState.replaceSelectedText$default(this.textFieldState, plainText, false, TextFieldEditUndoBehavior.NeverMerge, false, 10, null);
                        }
                        return Unit.INSTANCE;
                    }
                    c01871.L$0 = null;
                    c01871.label = 3;
                }
            }
            return coroutine_suspended;
        }
        if (i2 == 1) {
            ResultKt.throwOnFailure(clipEntry3);
            return Unit.INSTANCE;
        }
        if (i2 == 2) {
            receiveContentConfiguration = (ReceiveContentConfiguration) c01871.L$0;
            ResultKt.throwOnFailure(clipEntry3);
            clipEntry = (ClipEntry) clipEntry3;
            if (clipEntry == null) {
                transferableContentOnReceive = receiveContentConfiguration.getReceiveContentListener().onReceive(new TransferableContent(clipEntry, clipEntry.getClipMetadata(), TransferableContent.Source.INSTANCE.m441getClipboardkB6V9T0(), null, 8, null));
                if (transferableContentOnReceive != null && (clipEntry2 = transferableContentOnReceive.getClipEntry()) != null && (plainText = TransferableContent_androidKt.readPlainText(clipEntry2)) != null) {
                    TransformedTextFieldState.replaceSelectedText$default(this.textFieldState, plainText, false, TextFieldEditUndoBehavior.NeverMerge, false, 10, null);
                }
                return Unit.INSTANCE;
            }
            c01871.L$0 = null;
            c01871.label = 3;
        } else {
            if (i2 != 3) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(clipEntry3);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: placeCursorAtNearestOffset-k-4lQ0M, reason: not valid java name */
    public final boolean m1658placeCursorAtNearestOffsetk4lQ0M(long offset) {
        int i;
        IndexTransformationType indexTransformationType;
        int i2;
        TextLayoutResult layoutResult = this.textLayoutState.getLayoutResult();
        if (layoutResult == null || (i = layoutResult.getOffsetForPosition-k-4lQ0M(offset)) == -1) {
            return false;
        }
        TransformedTextFieldState transformedTextFieldState = this.textFieldState;
        long jM1611mapFromTransformedjx7JFs = transformedTextFieldState.m1611mapFromTransformedjx7JFs(i);
        long jM1614mapToTransformedGEjPoXI = transformedTextFieldState.m1614mapToTransformedGEjPoXI(jM1611mapFromTransformedjx7JFs);
        if (TextRange.getCollapsed-impl(jM1611mapFromTransformedjx7JFs) && TextRange.getCollapsed-impl(jM1614mapToTransformedGEjPoXI)) {
            indexTransformationType = IndexTransformationType.Untransformed;
        } else if (TextRange.getCollapsed-impl(jM1611mapFromTransformedjx7JFs) || TextRange.getCollapsed-impl(jM1614mapToTransformedGEjPoXI)) {
            indexTransformationType = (!TextRange.getCollapsed-impl(jM1611mapFromTransformedjx7JFs) || TextRange.getCollapsed-impl(jM1614mapToTransformedGEjPoXI)) ? IndexTransformationType.Deletion : IndexTransformationType.Insertion;
        } else {
            indexTransformationType = IndexTransformationType.Replacement;
        }
        int i3 = WhenMappings.$EnumSwitchMapping$0[indexTransformationType.ordinal()];
        SelectionWedgeAffinity selectionWedgeAffinity = null;
        if (i3 == 1 || i3 == 2) {
            i2 = TextRange.getStart-impl(jM1611mapFromTransformedjx7JFs);
        } else if (i3 == 3) {
            selectionWedgeAffinity = MathUtilsKt.m1561findClosestRect9KIMszo(offset, layoutResult.getCursorRect(TextRange.getStart-impl(jM1614mapToTransformedGEjPoXI)), layoutResult.getCursorRect(TextRange.getEnd-impl(jM1614mapToTransformedGEjPoXI))) < 0 ? new SelectionWedgeAffinity(WedgeAffinity.Start) : new SelectionWedgeAffinity(WedgeAffinity.End);
            i2 = TextRange.getStart-impl(jM1611mapFromTransformedjx7JFs);
        } else {
            if (i3 != 4) {
                bu8.a();
                return false;
            }
            i2 = MathUtilsKt.m1561findClosestRect9KIMszo(offset, layoutResult.getCursorRect(TextRange.getStart-impl(jM1614mapToTransformedGEjPoXI)), layoutResult.getCursorRect(TextRange.getEnd-impl(jM1614mapToTransformedGEjPoXI))) < 0 ? TextRange.getStart-impl(jM1611mapFromTransformedjx7JFs) : TextRange.getEnd-impl(jM1611mapFromTransformedjx7JFs);
        }
        long jTextRange = TextRangeKt.TextRange(i2);
        if (TextRange.equals-impl0(jTextRange, this.textFieldState.getUntransformedText().getSelection()) && (selectionWedgeAffinity == null || Intrinsics.areEqual(selectionWedgeAffinity, this.textFieldState.getSelectionWedgeAffinity()))) {
            return false;
        }
        this.textFieldState.m1617selectUntransformedCharsIn5zctL8(jTextRange);
        if (selectionWedgeAffinity != null) {
            this.textFieldState.setSelectionWedgeAffinity(selectionWedgeAffinity);
        }
        return true;
    }

    public final void selectAll() {
        this.textFieldState.selectAll();
    }

    public final Object selectionHandleGestures(PointerInputScope pointerInputScope, boolean z, Continuation<? super Unit> continuation) {
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new C01892(pointerInputScope, z, null), continuation);
        return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
    }

    public final void setDirectDragGestureInitiator(InputType inputType) {
        this.directDragGestureInitiator.setValue(inputType);
    }

    public final void setDraggingHandle(Handle handle) {
        this.draggingHandle.setValue(handle);
    }

    public final void setFocused(boolean z) {
        this.isFocused = z;
    }

    public final void setHapticFeedBack(HapticFeedback hapticFeedback) {
        this.hapticFeedBack = hapticFeedback;
    }

    public final void setInTouchMode(boolean z) {
        this.isInTouchMode.setValue(Boolean.valueOf(z));
    }

    public final void setPressInteraction(PressInteraction.Press press) {
        this.pressInteraction = press;
    }

    public final void setReceiveContentConfiguration(Function0<? extends ReceiveContentConfiguration> function0) {
        this.receiveContentConfiguration = function0;
    }

    public final void setRequestAutofillAction(Function0<Unit> function0) {
        this.requestAutofillAction = function0;
    }

    public final void setShowCursorHandle(boolean z) {
        this.showCursorHandle.setValue(Boolean.valueOf(z));
    }

    public final void setTextToolbarShown$foundation(boolean z) {
        this.textToolbarShown.setValue(Boolean.valueOf(z));
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object startToolbarAndHandlesVisibilityObserver(Continuation<? super Unit> continuation) {
        C01901 c01901;
        if (continuation instanceof C01901) {
            c01901 = (C01901) continuation;
            int i = c01901.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c01901.label = i - Integer.MIN_VALUE;
            } else {
                c01901 = new C01901(continuation);
            }
        } else {
            c01901 = new C01901(continuation);
        }
        Object objCoroutineScope = c01901.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c01901.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(objCoroutineScope);
                C01912 c01912 = new C01912(null);
                c01901.label = 1;
                objCoroutineScope = CoroutineScopeKt.coroutineScope(c01912, c01901);
                if (objCoroutineScope == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i2 != 1) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(objCoroutineScope);
            }
            setShowCursorHandle(false);
            if (getTextToolbarState() != TextToolbarState.None) {
                hideTextToolbar();
            }
            return Unit.INSTANCE;
        } catch (Throwable th) {
            setShowCursorHandle(false);
            if (getTextToolbarState() != TextToolbarState.None) {
                hideTextToolbar();
            }
            throw th;
        }
    }

    public final Object textFieldSelectionGestures(PointerInputScope pointerInputScope, Function0<Unit> function0, Continuation<? super Unit> continuation) {
        Object objTextFieldSelectionGestures = TextFieldSelectionState_androidKt.textFieldSelectionGestures(this, pointerInputScope, new TextFieldMouseSelectionObserver(function0), new TextFieldTextDragObserver(function0), continuation);
        return objTextFieldSelectionGestures == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objTextFieldSelectionGestures : Unit.INSTANCE;
    }

    public final void update(HapticFeedback hapticFeedBack, Clipboard clipboard, TextToolbarHandler showTextToolbar, Density density, boolean enabled, boolean readOnly, boolean isPassword) {
        if (!enabled) {
            hideTextToolbar();
        }
        this.hapticFeedBack = hapticFeedBack;
        this.clipboard = clipboard;
        this.textToolbarHandler = showTextToolbar;
        this.density = density;
        this.enabled = enabled;
        this.readOnly = readOnly;
        this.isPassword = isPassword;
    }

    public final Object updateClipboardEntry(Continuation<? super Unit> continuation) {
        Object objUpdate = this.clipboardPasteState.update(continuation);
        return objUpdate == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objUpdate : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: updateHandleDragging-Uv8p0NA, reason: not valid java name */
    public final void m1659updateHandleDraggingUv8p0NA(Handle handle, long position) {
        setDraggingHandle(handle);
        m1654setRawHandleDragPositionk4lQ0M(position);
    }

    /* JADX INFO: renamed from: updateSelection-SsL-Rf8$foundation, reason: not valid java name */
    public final long m1660updateSelectionSsLRf8$foundation(TextFieldCharSequence textFieldCharSequence, int startOffset, int endOffset, boolean isStartHandle, SelectionAdjustment adjustment, boolean allowPreviousSelectionCollapsed, boolean isStartOfSelection) {
        HapticFeedback hapticFeedback;
        TextRange textRange = TextRange.box-impl(textFieldCharSequence.getSelection());
        long j = textRange.unbox-impl();
        if (isStartOfSelection || (!allowPreviousSelectionCollapsed && TextRange.getCollapsed-impl(j))) {
            textRange = null;
        }
        long jM1653getTextFieldSelectionqeG_v_k = m1653getTextFieldSelectionqeG_v_k(startOffset, endOffset, textRange, isStartHandle, adjustment);
        if (!TextRange.equals-impl0(jM1653getTextFieldSelectionqeG_v_k, textFieldCharSequence.getSelection())) {
            boolean z = TextRange.getReversed-impl(jM1653getTextFieldSelectionqeG_v_k) != TextRange.getReversed-impl(textFieldCharSequence.getSelection()) && TextRange.equals-impl0(TextRangeKt.TextRange(TextRange.getEnd-impl(jM1653getTextFieldSelectionqeG_v_k), TextRange.getStart-impl(jM1653getTextFieldSelectionqeG_v_k)), textFieldCharSequence.getSelection());
            if (isInTouchMode() && !z && (hapticFeedback = this.hapticFeedBack) != null) {
                hapticFeedback.performHapticFeedback-CdsT49E(HapticFeedbackType.Companion.getTextHandleMove-5zf0vsI());
            }
        }
        return jM1653getTextFieldSelectionqeG_v_k;
    }

    public final void updateTextToolbarState(TextToolbarState textToolbarState) {
        setTextToolbarState(textToolbarState);
    }
}
