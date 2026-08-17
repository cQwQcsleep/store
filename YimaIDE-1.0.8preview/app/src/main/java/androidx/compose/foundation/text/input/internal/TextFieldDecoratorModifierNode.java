package androidx.compose.foundation.text.input.internal;

import android.view.KeyEvent;
import androidx.compose.foundation.FocusableNode;
import androidx.compose.foundation.content.ReceiveContentListener;
import androidx.compose.foundation.content.TransferableContent;
import androidx.compose.foundation.content.TransferableContent_androidKt;
import androidx.compose.foundation.content.internal.DragAndDropRequestPermission_androidKt;
import androidx.compose.foundation.content.internal.ReceiveContentConfiguration;
import androidx.compose.foundation.content.internal.ReceiveContentConfigurationKt;
import androidx.compose.foundation.interaction.HoverInteraction;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.text.AutofillHighlightKt;
import androidx.compose.foundation.text.AutofillHighlight_androidKt;
import androidx.compose.foundation.text.Handle;
import androidx.compose.foundation.text.KeyCommand;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.foundation.text.input.InputTransformation;
import androidx.compose.foundation.text.input.KeyboardActionHandler;
import androidx.compose.foundation.text.input.TextFieldBuffer;
import androidx.compose.foundation.text.input.TextFieldCharSequence;
import androidx.compose.foundation.text.input.TextFieldState;
import androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode;
import androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState;
import androidx.compose.foundation.text.input.internal.selection.TextToolbarState;
import androidx.compose.foundation.text.input.internal.undo.TextFieldEditUndoBehavior;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.autofill.ContentDataType;
import androidx.compose.ui.autofill.ContentType;
import androidx.compose.ui.autofill.FillableData;
import androidx.compose.ui.autofill.FillableData_androidKt;
import androidx.compose.ui.draganddrop.DragAndDropEvent;
import androidx.compose.ui.draganddrop.DragAndDropTargetModifierNode;
import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.focus.FocusManager;
import androidx.compose.ui.focus.FocusProperties;
import androidx.compose.ui.focus.FocusPropertiesModifierNode;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.input.key.KeyInputModifierNode;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNode;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.modifier.ModifierLocalModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.GlobalPositionAwareModifierNode;
import androidx.compose.ui.node.LayoutAwareModifierNode;
import androidx.compose.ui.node.ObserverModifierNode;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import androidx.compose.ui.node.PointerInputModifierNode;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.SemanticsModifierNodeKt;
import androidx.compose.ui.platform.ClipEntry;
import androidx.compose.ui.platform.ClipMetadata;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.PlatformTextInputModifierNode;
import androidx.compose.ui.platform.PlatformTextInputModifierNodeKt;
import androidx.compose.ui.platform.PlatformTextInputSessionScope;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.platform.WindowInfo;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.ImeOptions;
import androidx.compose.ui.text.input.KeyboardType;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.AdaptedFunctionReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u008b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001^\b\u0001\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u00072\u00020\b2\u00020\t2\u00020\n2\u00020\u000b2\u00020\fBs\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\u0006\u0010\u0018\u001a\u00020\u0019\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b\u0012\u0006\u0010\u001c\u001a\u00020\u0016\u0012\u0006\u0010\u001d\u001a\u00020\u001e\u0012\u0006\u0010\u001f\u001a\u00020\u0016\u0012\u000e\u0010 \u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010!¢\u0006\u0004\b#\u0010$J\f\u0010m\u001a\u00020\"*\u00020nH\u0016J\u000e\u0010o\u001a\u00020\"H\u0082@¢\u0006\u0002\u0010pJr\u0010q\u001a\u00020\"2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00162\u000e\u0010 \u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010!J\f\u0010t\u001a\u00020\"*\u00020uH\u0016J\b\u0010v\u001a\u00020\"H\u0002J\b\u0010w\u001a\u00020\"H\u0002J\u0010\u0010x\u001a\u00020\"2\u0006\u0010y\u001a\u00020zH\u0016J\b\u0010{\u001a\u00020\"H\u0016J\b\u0010|\u001a\u00020\"H\u0016J\u0010\u0010}\u001a\u00020\"2\u0006\u0010~\u001a\u00020\u007fH\u0016J0\u0010\u0080\u0001\u001a\u00020\"2\b\u0010\u0081\u0001\u001a\u00030\u0082\u00012\b\u0010\u0083\u0001\u001a\u00030\u0084\u00012\b\u0010\u0085\u0001\u001a\u00030\u0086\u0001H\u0016¢\u0006\u0006\b\u0087\u0001\u0010\u0088\u0001J\t\u0010\u0089\u0001\u001a\u00020\"H\u0016J\u001c\u0010\u008a\u0001\u001a\u00020\u00162\b\u0010\u008b\u0001\u001a\u00030\u008c\u0001H\u0016¢\u0006\u0006\b\u008d\u0001\u0010\u008e\u0001J\u001c\u0010\u008f\u0001\u001a\u00020\u00162\b\u0010\u008b\u0001\u001a\u00030\u008c\u0001H\u0016¢\u0006\u0006\b\u0090\u0001\u0010\u008e\u0001J\t\u0010\u0091\u0001\u001a\u00020\"H\u0016J\t\u0010\u0092\u0001\u001a\u00020\"H\u0002J\u0011\u0010\u0093\u0001\u001a\u00020\"2\u0006\u0010~\u001a\u00020\u007fH\u0016J\u001c\u0010\u0094\u0001\u001a\u00020\"2\b\u0010\u0095\u0001\u001a\u00030\u0086\u0001H\u0016¢\u0006\u0006\b\u0096\u0001\u0010\u0097\u0001J\u0012\u0010\u0098\u0001\u001a\u00020\"2\u0007\u0010\u0099\u0001\u001a\u00020\u0016H\u0002J\t\u0010\u009a\u0001\u001a\u00020\"H\u0002J\n\u0010\u009b\u0001\u001a\u00030\u009c\u0001H\u0002J\t\u0010\u009d\u0001\u001a\u00020\"H\u0002J\u001c\u0010\u009e\u0001\u001a\u00020\u00162\b\u0010\u009f\u0001\u001a\u00030 \u0001H\u0002¢\u0006\u0006\b¡\u0001\u0010¢\u0001J\u001c\u0010£\u0001\u001a\u00020\u00162\b\u0010\u009f\u0001\u001a\u00030 \u0001H\u0002¢\u0006\u0006\b¤\u0001\u0010¢\u0001R\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u001a\u0010\u0017\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u00106\"\u0004\b:\u00108R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\u001a\u0010\u001c\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u00106\"\u0004\bD\u00108R\u001a\u0010\u001d\u001a\u00020\u001eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR\u001a\u0010\u001f\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u00106\"\u0004\bI\u00108R\"\u0010 \u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010K\"\u0004\bL\u0010MR\u000e\u0010N\u001a\u00020OX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010P\u001a\u00020QX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010R\u001a\u0004\u0018\u00010SX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010T\u001a\u00020UX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010V\u001a\u0004\u0018\u00010WX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010X\u001a\u00020\u00168BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bX\u00106R\u0010\u0010Y\u001a\u0004\u0018\u00010ZX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010[\u001a\u00020\\X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010]\u001a\u00020^X\u0082\u0004¢\u0006\u0004\n\u0002\u0010_R\u0010\u0010`\u001a\u00020aX\u0082\u0004¢\u0006\u0004\n\u0002\u0010bR\u0010\u0010c\u001a\u0004\u0018\u00010ZX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010f0eX\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010h\u001a\u00020\u00162\u0006\u0010g\u001a\u00020\u00168B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bk\u0010l\u001a\u0004\bi\u00106\"\u0004\bj\u00108R\u0014\u0010r\u001a\u00020\u00168VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bs\u00106¨\u0006¥\u0001"}, d2 = {"Landroidx/compose/foundation/text/input/internal/TextFieldDecoratorModifierNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/node/DrawModifierNode;", "Landroidx/compose/ui/platform/PlatformTextInputModifierNode;", "Landroidx/compose/ui/node/SemanticsModifierNode;", "Landroidx/compose/ui/node/GlobalPositionAwareModifierNode;", "Landroidx/compose/ui/node/PointerInputModifierNode;", "Landroidx/compose/ui/input/key/KeyInputModifierNode;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "Landroidx/compose/ui/modifier/ModifierLocalModifierNode;", "Landroidx/compose/ui/node/ObserverModifierNode;", "Landroidx/compose/ui/node/LayoutAwareModifierNode;", "Landroidx/compose/ui/focus/FocusPropertiesModifierNode;", "textFieldState", "Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;", "textLayoutState", "Landroidx/compose/foundation/text/input/internal/TextLayoutState;", "textFieldSelectionState", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;", "filter", "Landroidx/compose/foundation/text/input/InputTransformation;", "enabled", "", "readOnly", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardOptions;", "keyboardActionHandler", "Landroidx/compose/foundation/text/input/KeyboardActionHandler;", "singleLine", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "isPassword", "stylusHandwritingTrigger", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "", "<init>", "(Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;Landroidx/compose/foundation/text/input/internal/TextLayoutState;Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;Landroidx/compose/foundation/text/input/InputTransformation;ZZLandroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/input/KeyboardActionHandler;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;ZLkotlinx/coroutines/flow/MutableSharedFlow;)V", "getTextFieldState", "()Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;", "setTextFieldState", "(Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;)V", "getTextLayoutState", "()Landroidx/compose/foundation/text/input/internal/TextLayoutState;", "setTextLayoutState", "(Landroidx/compose/foundation/text/input/internal/TextLayoutState;)V", "getTextFieldSelectionState", "()Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;", "setTextFieldSelectionState", "(Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;)V", "getFilter", "()Landroidx/compose/foundation/text/input/InputTransformation;", "setFilter", "(Landroidx/compose/foundation/text/input/InputTransformation;)V", "getEnabled", "()Z", "setEnabled", "(Z)V", "getReadOnly", "setReadOnly", "getKeyboardOptions", "()Landroidx/compose/foundation/text/KeyboardOptions;", "setKeyboardOptions", "(Landroidx/compose/foundation/text/KeyboardOptions;)V", "getKeyboardActionHandler", "()Landroidx/compose/foundation/text/input/KeyboardActionHandler;", "setKeyboardActionHandler", "(Landroidx/compose/foundation/text/input/KeyboardActionHandler;)V", "getSingleLine", "setSingleLine", "getInteractionSource", "()Landroidx/compose/foundation/interaction/MutableInteractionSource;", "setInteractionSource", "(Landroidx/compose/foundation/interaction/MutableInteractionSource;)V", "setPassword", "getStylusHandwritingTrigger", "()Lkotlinx/coroutines/flow/MutableSharedFlow;", "setStylusHandwritingTrigger", "(Lkotlinx/coroutines/flow/MutableSharedFlow;)V", "focusableNode", "Landroidx/compose/foundation/FocusableNode;", "pointerInputNode", "Landroidx/compose/ui/input/pointer/SuspendingPointerInputModifierNode;", "dragEnterEvent", "Landroidx/compose/foundation/interaction/HoverInteraction$Enter;", "dragAndDropNode", "Landroidx/compose/ui/draganddrop/DragAndDropTargetModifierNode;", "windowInfo", "Landroidx/compose/ui/platform/WindowInfo;", "isFocused", "toolbarAndHandlesVisibilityObserverJob", "Lkotlinx/coroutines/Job;", "textFieldKeyEventHandler", "Landroidx/compose/foundation/text/input/internal/TextFieldKeyEventHandler;", "keyboardActionScope", "androidx/compose/foundation/text/input/internal/TextFieldDecoratorModifierNode$keyboardActionScope$1", "Landroidx/compose/foundation/text/input/internal/TextFieldDecoratorModifierNode$keyboardActionScope$1;", "clipboardKeyCommandsHandler", "Landroidx/compose/foundation/text/input/internal/ClipboardKeyCommandsHandler;", "Lkotlin/jvm/functions/Function1;", "inputSessionJob", "receiveContentConfigurationProvider", "Lkotlin/Function0;", "Landroidx/compose/foundation/content/internal/ReceiveContentConfiguration;", "<set-?>", "autofillHighlightOn", "getAutofillHighlightOn", "setAutofillHighlightOn", "autofillHighlightOn$delegate", "Landroidx/compose/runtime/MutableState;", "draw", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "observeUntransformedTextChanges", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateNode", "shouldMergeDescendantSemantics", "getShouldMergeDescendantSemantics", "applySemantics", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "requestFocus", "onIsFocusedUpdated", "applyFocusProperties", "focusProperties", "Landroidx/compose/ui/focus/FocusProperties;", "onAttach", "onDetach", "onGloballyPositioned", "coordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "onPointerEvent", "pointerEvent", "Landroidx/compose/ui/input/pointer/PointerEvent;", "pass", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "bounds", "Landroidx/compose/ui/unit/IntSize;", "onPointerEvent-H0pRuoY", "(Landroidx/compose/ui/input/pointer/PointerEvent;Landroidx/compose/ui/input/pointer/PointerEventPass;J)V", "onCancelPointerInput", "onPreKeyEvent", "event", "Landroidx/compose/ui/input/key/KeyEvent;", "onPreKeyEvent-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "onKeyEvent", "onKeyEvent-ZmokQxo", "onObservedReadsChanged", "updateWindowFocus", "onPlaced", "onRemeasured", "size", "onRemeasured-ozmzZPI", "(J)V", "startInputSession", "fromTap", "disposeInputSession", "requireKeyboardController", "Landroidx/compose/ui/platform/SoftwareKeyboardController;", "emitDragExitEvent", "onImeActionPerformed", "imeAction", "Landroidx/compose/ui/text/input/ImeAction;", "onImeActionPerformed-KlQnJC8", "(I)Z", "defaultKeyboardActionWithResult", "defaultKeyboardActionWithResult-KlQnJC8", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class TextFieldDecoratorModifierNode extends DelegatingNode implements DrawModifierNode, PlatformTextInputModifierNode, SemanticsModifierNode, GlobalPositionAwareModifierNode, PointerInputModifierNode, KeyInputModifierNode, CompositionLocalConsumerModifierNode, ModifierLocalModifierNode, ObserverModifierNode, LayoutAwareModifierNode, FocusPropertiesModifierNode {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: autofillHighlightOn$delegate, reason: from kotlin metadata */
    private final MutableState autofillHighlightOn;
    private final Function1<? super KeyCommand, ? extends Unit> clipboardKeyCommandsHandler;
    private final DragAndDropTargetModifierNode dragAndDropNode;
    private HoverInteraction.Enter dragEnterEvent;
    private boolean enabled;
    private InputTransformation filter;
    private final FocusableNode focusableNode;
    private Job inputSessionJob;
    private MutableInteractionSource interactionSource;
    private boolean isPassword;
    private KeyboardActionHandler keyboardActionHandler;
    private final TextFieldDecoratorModifierNode$keyboardActionScope$1 keyboardActionScope;
    private KeyboardOptions keyboardOptions;
    private final SuspendingPointerInputModifierNode pointerInputNode;
    private boolean readOnly;
    private final Function0<ReceiveContentConfiguration> receiveContentConfigurationProvider;
    private boolean singleLine;
    private MutableSharedFlow<Unit> stylusHandwritingTrigger;
    private final TextFieldKeyEventHandler textFieldKeyEventHandler;
    private TextFieldSelectionState textFieldSelectionState;
    private TransformedTextFieldState textFieldState;
    private TextLayoutState textLayoutState;
    private Job toolbarAndHandlesVisibilityObserverJob;
    private WindowInfo windowInfo;

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$onIsFocusedUpdated$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$onIsFocusedUpdated$1", f = "TextFieldDecoratorModifier.kt", i = {}, l = {707}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return TextFieldDecoratorModifierNode.this.new AnonymousClass1(continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                TextFieldSelectionState textFieldSelectionState = TextFieldDecoratorModifierNode.this.getTextFieldSelectionState();
                this.label = 1;
                if (textFieldSelectionState.startToolbarAndHandlesVisibilityObserver(this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$startInputSession$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$startInputSession$1", f = "TextFieldDecoratorModifier.kt", i = {}, l = {810}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class C01811 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ ReceiveContentConfiguration $receiveContentConfiguration;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$startInputSession$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0001\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/platform/PlatformTextInputSessionScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$startInputSession$1$1", f = "TextFieldDecoratorModifier.kt", i = {}, l = {811}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        public static final class C00341 extends SuspendLambda implements Function2<PlatformTextInputSessionScope, Continuation<?>, Object> {
            final /* synthetic */ ReceiveContentConfiguration $receiveContentConfiguration;
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ TextFieldDecoratorModifierNode this$0;

            /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$startInputSession$1$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
            public static final /* synthetic */ class C00351 extends AdaptedFunctionReference implements Function1<ImeAction, Unit> {
                public C00351(Object obj) {
                    super(1, obj, TextFieldDecoratorModifierNode.class, "onImeActionPerformed", "onImeActionPerformed-KlQnJC8(I)Z", 8);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    m1582invokeKlQnJC8(((ImeAction) obj).unbox-impl());
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke-KlQnJC8, reason: not valid java name */
                public final void m1582invokeKlQnJC8(int i) {
                    ((TextFieldDecoratorModifierNode) ((AdaptedFunctionReference) this).receiver).m1577onImeActionPerformedKlQnJC8(i);
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00341(TextFieldDecoratorModifierNode textFieldDecoratorModifierNode, ReceiveContentConfiguration receiveContentConfiguration, Continuation<? super C00341> continuation) {
                super(2, continuation);
                this.this$0 = textFieldDecoratorModifierNode;
                this.$receiveContentConfiguration = receiveContentConfiguration;
            }

            public static Unit b(TextFieldDecoratorModifierNode textFieldDecoratorModifierNode, boolean z) {
                textFieldDecoratorModifierNode.getTextFieldSelectionState().setInTouchMode(z);
                return Unit.INSTANCE;
            }

            public static Unit d(TextFieldDecoratorModifierNode textFieldDecoratorModifierNode) {
                textFieldDecoratorModifierNode.getTextFieldSelectionState().updateTextToolbarState(TextToolbarState.Selection);
                return Unit.INSTANCE;
            }

            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00341 c00341 = new C00341(this.this$0, this.$receiveContentConfiguration, continuation);
                c00341.L$0 = obj;
                return c00341;
            }

            public final Object invoke(PlatformTextInputSessionScope platformTextInputSessionScope, Continuation<?> continuation) {
                return create(platformTextInputSessionScope, continuation).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    PlatformTextInputSessionScope platformTextInputSessionScope = (PlatformTextInputSessionScope) this.L$0;
                    TransformedTextFieldState textFieldState = this.this$0.getTextFieldState();
                    TextLayoutState textLayoutState = this.this$0.getTextLayoutState();
                    ImeOptions imeOptions$foundation = this.this$0.getKeyboardOptions().toImeOptions$foundation(this.this$0.getSingleLine());
                    ReceiveContentConfiguration receiveContentConfiguration = this.$receiveContentConfiguration;
                    C00351 c00351 = new C00351(this.this$0);
                    final TextFieldDecoratorModifierNode textFieldDecoratorModifierNode = this.this$0;
                    Function0 function0 = new Function0() { // from class: androidx.compose.foundation.text.input.internal.l
                        public final Object invoke() {
                            return TextFieldDecoratorModifierNode.C01811.C00341.d(textFieldDecoratorModifierNode);
                        }
                    };
                    MutableSharedFlow<Unit> stylusHandwritingTrigger = this.this$0.getStylusHandwritingTrigger();
                    ViewConfiguration viewConfiguration = (ViewConfiguration) CompositionLocalConsumerModifierNodeKt.currentValueOf(this.this$0, CompositionLocalsKt.getLocalViewConfiguration());
                    final TextFieldDecoratorModifierNode textFieldDecoratorModifierNode2 = this.this$0;
                    Function1 function1 = new Function1() { // from class: androidx.compose.foundation.text.input.internal.m
                        public final Object invoke(Object obj2) {
                            return TextFieldDecoratorModifierNode.C01811.C00341.b(textFieldDecoratorModifierNode2, ((Boolean) obj2).booleanValue());
                        }
                    };
                    this.label = 1;
                    if (AndroidTextInputSession_androidKt.platformSpecificTextInputSession(platformTextInputSessionScope, textFieldState, textLayoutState, imeOptions$foundation, receiveContentConfiguration, c00351, function0, stylusHandwritingTrigger, viewConfiguration, function1, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        k2d.a("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                }
                wq6.a();
                return null;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C01811(ReceiveContentConfiguration receiveContentConfiguration, Continuation<? super C01811> continuation) {
            super(2, continuation);
            this.$receiveContentConfiguration = receiveContentConfiguration;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return TextFieldDecoratorModifierNode.this.new C01811(this.$receiveContentConfiguration, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                TextFieldDecoratorModifierNode textFieldDecoratorModifierNode = TextFieldDecoratorModifierNode.this;
                C00341 c00341 = new C00341(textFieldDecoratorModifierNode, this.$receiveContentConfiguration, null);
                this.label = 1;
                if (PlatformTextInputModifierNodeKt.establishTextInputSession(textFieldDecoratorModifierNode, c00341, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            wq6.a();
            return null;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$updateNode$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$updateNode$1", f = "TextFieldDecoratorModifier.kt", i = {}, l = {516}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class C01821 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ TextFieldSelectionState $textFieldSelectionState;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C01821(TextFieldSelectionState textFieldSelectionState, Continuation<? super C01821> continuation) {
            super(2, continuation);
            this.$textFieldSelectionState = textFieldSelectionState;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C01821(this.$textFieldSelectionState, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                TextFieldSelectionState textFieldSelectionState = this.$textFieldSelectionState;
                this.label = 1;
                if (textFieldSelectionState.startToolbarAndHandlesVisibilityObserver(this) == coroutine_suspended) {
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

    public TextFieldDecoratorModifierNode(TransformedTextFieldState transformedTextFieldState, TextLayoutState textLayoutState, TextFieldSelectionState textFieldSelectionState, InputTransformation inputTransformation, boolean z, boolean z2, KeyboardOptions keyboardOptions, KeyboardActionHandler keyboardActionHandler, boolean z3, MutableInteractionSource mutableInteractionSource, boolean z4, MutableSharedFlow<Unit> mutableSharedFlow) {
        this.textFieldState = transformedTextFieldState;
        this.textLayoutState = textLayoutState;
        this.textFieldSelectionState = textFieldSelectionState;
        this.filter = inputTransformation;
        this.enabled = z;
        this.readOnly = z2;
        this.keyboardOptions = keyboardOptions;
        this.keyboardActionHandler = keyboardActionHandler;
        this.singleLine = z3;
        this.interactionSource = mutableInteractionSource;
        this.isPassword = z4;
        this.stylusHandwritingTrigger = mutableSharedFlow;
        textFieldSelectionState.setRequestAutofillAction(new Function0() { // from class: t5e
            public final Object invoke() {
                return TextFieldDecoratorModifierNode.t(this.b);
            }
        });
        this.focusableNode = new FocusableNode(this.interactionSource, 0, new Function1() { // from class: v5e
            public final Object invoke(Object obj) {
                return TextFieldDecoratorModifierNode.u(this.b, ((Boolean) obj).booleanValue());
            }
        }, 2, null);
        this.pointerInputNode = delegate(SuspendingPointerInputFilterKt.SuspendingPointerInputModifierNode(new PointerInputEventHandler() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$pointerInputNode$1

            /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$pointerInputNode$1$1, reason: invalid class name */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$pointerInputNode$1$1", f = "TextFieldDecoratorModifier.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
            public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ PointerInputScope $this_SuspendingPointerInputModifierNode;
                private /* synthetic */ Object L$0;
                int label;
                final /* synthetic */ TextFieldDecoratorModifierNode this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass1(TextFieldDecoratorModifierNode textFieldDecoratorModifierNode, PointerInputScope pointerInputScope, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.this$0 = textFieldDecoratorModifierNode;
                    this.$this_SuspendingPointerInputModifierNode = pointerInputScope;
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final Unit invokeSuspend$lambda$0$0(TextFieldSelectionState textFieldSelectionState, TextFieldDecoratorModifierNode textFieldDecoratorModifierNode) {
                    if (!textFieldSelectionState.getIsFocused()) {
                        textFieldDecoratorModifierNode.requestFocus();
                    }
                    return Unit.INSTANCE;
                }

                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.$this_SuspendingPointerInputModifierNode, continuation);
                    anonymousClass1.L$0 = obj;
                    return anonymousClass1;
                }

                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
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
                    final TextFieldSelectionState textFieldSelectionState = this.this$0.getTextFieldSelectionState();
                    final TextFieldDecoratorModifierNode textFieldDecoratorModifierNode = this.this$0;
                    PointerInputScope pointerInputScope = this.$this_SuspendingPointerInputModifierNode;
                    Function0 function0 = 
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x001b: CONSTRUCTOR (r7v0 'function0' kotlin.jvm.functions.Function0) = 
                          (r11v3 'textFieldSelectionState' androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState A[DONT_INLINE])
                          (r6v0 'textFieldDecoratorModifierNode' androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode A[DONT_INLINE])
                         A[DECLARE_VAR, MD:(androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState, androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode):void (m)] call: androidx.compose.foundation.text.input.internal.j.<init>(androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState, androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode):void type: CONSTRUCTOR in method: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$pointerInputNode$1.1.invokeSuspend(java.lang.Object):java.lang.Object, file: /workspace/dex_all/classes.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                        	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:291)
                        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:270)
                        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:420)
                        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:345)
                        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:299)
                        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:186)
                        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
                        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                        	at java.base/java.util.stream.ReferencePipeline$7$1FlatMap.end(ReferencePipeline.java:284)
                        	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:571)
                        	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:560)
                        	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:153)
                        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:176)
                        	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
                        	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:632)
                        	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:295)
                        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:284)
                        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:268)
                        	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:160)
                        	at jadx.core.codegen.ClassGen.addInnerClass(ClassGen.java:320)
                        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:297)
                        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:186)
                        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
                        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                        	at java.base/java.util.stream.ReferencePipeline$7$1FlatMap.end(ReferencePipeline.java:284)
                        	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:571)
                        	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:560)
                        	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:153)
                        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:176)
                        	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
                        	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:632)
                        	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:295)
                        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:284)
                        	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:845)
                        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                        	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1143)
                        	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:910)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:422)
                        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                        	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1143)
                        	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:910)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:422)
                        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:487)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                        	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:291)
                        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:270)
                        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:420)
                        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:345)
                        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:299)
                        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:186)
                        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
                        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                        	at java.base/java.util.stream.ReferencePipeline$7$1FlatMap.end(ReferencePipeline.java:284)
                        	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:571)
                        	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:560)
                        	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:153)
                        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:176)
                        	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
                        	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:632)
                        	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:295)
                        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:284)
                        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:268)
                        	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:160)
                        	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:104)
                        	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:45)
                        	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:34)
                        	at jadx.core.codegen.CodeGen.generate(CodeGen.java:22)
                        	at jadx.core.ProcessClass.process(ProcessClass.java:89)
                        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:127)
                        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:405)
                        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:393)
                        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:343)
                        Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: androidx.compose.foundation.text.input.internal.j, state: NOT_LOADED
                        	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:306)
                        	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:807)
                        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                        	... 100 more
                        */
                    /*
                        this = this;
                        kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r0 = r10.label
                        if (r0 != 0) goto L4d
                        kotlin.ResultKt.throwOnFailure(r11)
                        java.lang.Object r11 = r10.L$0
                        r0 = r11
                        kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
                        androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode r11 = r10.this$0
                        androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r11 = r11.getTextFieldSelectionState()
                        androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode r6 = r10.this$0
                        androidx.compose.ui.input.pointer.PointerInputScope r10 = r10.$this_SuspendingPointerInputModifierNode
                        androidx.compose.foundation.text.input.internal.j r7 = new androidx.compose.foundation.text.input.internal.j
                        r7.<init>(r11, r6)
                        kotlinx.coroutines.CoroutineStart r2 = kotlinx.coroutines.CoroutineStart.UNDISPATCHED
                        androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$pointerInputNode$1$1$1$1 r3 = new androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$pointerInputNode$1$1$1$1
                        r8 = 0
                        r3.<init>(r11, r10, r8)
                        r4 = 1
                        r5 = 0
                        r1 = 0
                        kotlinx.coroutines.BuildersKt.launch$default(r0, r1, r2, r3, r4, r5)
                        r9 = r2
                        androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$pointerInputNode$1$1$1$2 r1 = new androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$pointerInputNode$1$1$1$2
                        r2 = r6
                        r6 = 0
                        r4 = r10
                        r3 = r11
                        r5 = r7
                        r1.<init>(r2, r3, r4, r5, r6)
                        r10 = r3
                        r11 = r4
                        r6 = r5
                        r4 = 1
                        r5 = 0
                        r3 = r1
                        r1 = 0
                        r2 = r9
                        kotlinx.coroutines.BuildersKt.launch$default(r0, r1, r2, r3, r4, r5)
                        androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$pointerInputNode$1$1$1$3 r3 = new androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$pointerInputNode$1$1$1$3
                        r3.<init>(r10, r11, r6, r8)
                        kotlinx.coroutines.BuildersKt.launch$default(r0, r1, r2, r3, r4, r5)
                        kotlin.Unit r10 = kotlin.Unit.INSTANCE
                        return r10
                    L4d:
                        java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
                        k2d.a(r10)
                        r10 = 0
                        return r10
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$pointerInputNode$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }

            public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
                Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new AnonymousClass1(this.this$0, pointerInputScope, null), continuation);
                return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
            }
        }));
        this.dragAndDropNode = delegate(TextFieldDragAndDropNode_androidKt.textFieldDragAndDropNode$default(new Function0() { // from class: w5e
            public final Object invoke() {
                return TextFieldDecoratorModifierNode.i(this.b);
            }
        }, new Function2() { // from class: x5e
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(TextFieldDecoratorModifierNode.b(this.b, (ClipEntry) obj, (ClipMetadata) obj2));
            }
        }, new Function1() { // from class: y5e
            public final Object invoke(Object obj) {
                return TextFieldDecoratorModifierNode.v(this.b, (DragAndDropEvent) obj);
            }
        }, null, new Function1() { // from class: a6e
            public final Object invoke(Object obj) {
                return TextFieldDecoratorModifierNode.e(this.b, (DragAndDropEvent) obj);
            }
        }, new Function1() { // from class: b6e
            public final Object invoke(Object obj) {
                return TextFieldDecoratorModifierNode.g(this.b, (Offset) obj);
            }
        }, null, new Function1() { // from class: c6e
            public final Object invoke(Object obj) {
                return TextFieldDecoratorModifierNode.n(this.b, (DragAndDropEvent) obj);
            }
        }, new Function1() { // from class: d6e
            public final Object invoke(Object obj) {
                return TextFieldDecoratorModifierNode.d(this.b, (DragAndDropEvent) obj);
            }
        }, 72, null));
        this.textFieldKeyEventHandler = TextFieldKeyEventHandler_androidKt.createTextFieldKeyEventHandler();
        this.keyboardActionScope = new TextFieldDecoratorModifierNode$keyboardActionScope$1(this);
        this.clipboardKeyCommandsHandler = ClipboardKeyCommandsHandler.m1523constructorimpl(new Function1() { // from class: e6e
            public final Object invoke(Object obj) {
                return TextFieldDecoratorModifierNode.A(this.b, (KeyCommand) obj);
            }
        });
        this.receiveContentConfigurationProvider = new Function0() { // from class: u5e
            public final Object invoke() {
                return TextFieldDecoratorModifierNode.m(this.b);
            }
        };
        this.autofillHighlightOn = SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, (SnapshotMutationPolicy) null, 2, (Object) null);
    }

    public static Unit A(TextFieldDecoratorModifierNode textFieldDecoratorModifierNode, KeyCommand keyCommand) {
        BuildersKt.launch$default(textFieldDecoratorModifierNode.getCoroutineScope(), (CoroutineContext) null, CoroutineStart.UNDISPATCHED, new TextFieldDecoratorModifierNode$clipboardKeyCommandsHandler$1$1(keyCommand, textFieldDecoratorModifierNode, null), 1, (Object) null);
        return Unit.INSTANCE;
    }

    public static Unit a(TextFieldDecoratorModifierNode textFieldDecoratorModifierNode) {
        textFieldDecoratorModifierNode.windowInfo = (WindowInfo) CompositionLocalConsumerModifierNodeKt.currentValueOf(textFieldDecoratorModifierNode, CompositionLocalsKt.getLocalWindowInfo());
        textFieldDecoratorModifierNode.onIsFocusedUpdated();
        return Unit.INSTANCE;
    }

    public static boolean b(TextFieldDecoratorModifierNode textFieldDecoratorModifierNode, ClipEntry clipEntry, ClipMetadata clipMetadata) {
        ClipEntry clipEntry2;
        textFieldDecoratorModifierNode.emitDragExitEvent();
        textFieldDecoratorModifierNode.textFieldSelectionState.clearHandleDragging();
        String plainText = TransferableContent_androidKt.readPlainText(clipEntry);
        ReceiveContentConfiguration receiveContentConfiguration = ReceiveContentConfigurationKt.getReceiveContentConfiguration(textFieldDecoratorModifierNode);
        if (receiveContentConfiguration != null) {
            TransferableContent transferableContentOnReceive = receiveContentConfiguration.getReceiveContentListener().onReceive(new TransferableContent(clipEntry, clipMetadata, TransferableContent.Source.INSTANCE.m442getDragAndDropkB6V9T0(), null, 8, null));
            plainText = (transferableContentOnReceive == null || (clipEntry2 = transferableContentOnReceive.getClipEntry()) == null) ? null : TransferableContent_androidKt.readPlainText(clipEntry2);
        }
        String str = plainText;
        if (str == null) {
            return true;
        }
        TransformedTextFieldState.replaceSelectedText$default(textFieldDecoratorModifierNode.textFieldState, str, false, null, false, 14, null);
        return true;
    }

    public static boolean c(boolean z, TextFieldDecoratorModifierNode textFieldDecoratorModifierNode, AnnotatedString annotatedString) {
        if (!z) {
            return false;
        }
        TransformedTextFieldState.replaceSelectedText$default(textFieldDecoratorModifierNode.textFieldState, annotatedString, true, null, false, 12, null);
        return true;
    }

    public static Unit d(TextFieldDecoratorModifierNode textFieldDecoratorModifierNode, DragAndDropEvent dragAndDropEvent) {
        textFieldDecoratorModifierNode.emitDragExitEvent();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: defaultKeyboardActionWithResult-KlQnJC8, reason: not valid java name */
    public final boolean m1576defaultKeyboardActionWithResultKlQnJC8(int imeAction) {
        ImeAction.Companion companion = ImeAction.Companion;
        if (ImeAction.equals-impl0(imeAction, companion.getNext-eUduSuo())) {
            ((FocusManager) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.getLocalFocusManager())).moveFocus-3ESFkO8(FocusDirection.Companion.getNext-dhqQ-8s());
            return true;
        }
        if (ImeAction.equals-impl0(imeAction, companion.getPrevious-eUduSuo())) {
            ((FocusManager) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.getLocalFocusManager())).moveFocus-3ESFkO8(FocusDirection.Companion.getPrevious-dhqQ-8s());
            return true;
        }
        if (!ImeAction.equals-impl0(imeAction, companion.getDone-eUduSuo())) {
            return false;
        }
        requireKeyboardController().hide();
        return true;
    }

    private final void disposeInputSession() {
        Job job = this.inputSessionJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.inputSessionJob = null;
        MutableSharedFlow<Unit> mutableSharedFlow = this.stylusHandwritingTrigger;
        if (mutableSharedFlow != null) {
            mutableSharedFlow.resetReplayCache();
        }
    }

    public static Unit e(TextFieldDecoratorModifierNode textFieldDecoratorModifierNode, DragAndDropEvent dragAndDropEvent) {
        ReceiveContentListener receiveContentListener;
        HoverInteraction.Enter enter = new HoverInteraction.Enter();
        textFieldDecoratorModifierNode.interactionSource.tryEmit(enter);
        textFieldDecoratorModifierNode.dragEnterEvent = enter;
        ReceiveContentConfiguration receiveContentConfiguration = ReceiveContentConfigurationKt.getReceiveContentConfiguration(textFieldDecoratorModifierNode);
        if (receiveContentConfiguration != null && (receiveContentListener = receiveContentConfiguration.getReceiveContentListener()) != null) {
            receiveContentListener.onDragEnter();
        }
        return Unit.INSTANCE;
    }

    private final void emitDragExitEvent() {
        HoverInteraction.Enter enter = this.dragEnterEvent;
        if (enter != null) {
            this.interactionSource.tryEmit(new HoverInteraction.Exit(enter));
            this.dragEnterEvent = null;
        }
    }

    public static boolean f(TextFieldDecoratorModifierNode textFieldDecoratorModifierNode) {
        return textFieldDecoratorModifierNode.m1577onImeActionPerformedKlQnJC8(textFieldDecoratorModifierNode.keyboardOptions.m1370getImeActionOrDefaulteUduSuo$foundation());
    }

    public static Unit g(TextFieldDecoratorModifierNode textFieldDecoratorModifierNode, Offset offset) {
        long jM1606fromWindowToDecorationUv8p0NA = TextLayoutStateKt.m1606fromWindowToDecorationUv8p0NA(textFieldDecoratorModifierNode.textLayoutState, offset.unbox-impl());
        int iM1596getOffsetForPosition3MmeM6k$default = TextLayoutState.m1596getOffsetForPosition3MmeM6k$default(textFieldDecoratorModifierNode.textLayoutState, jM1606fromWindowToDecorationUv8p0NA, false, 2, null);
        if (iM1596getOffsetForPosition3MmeM6k$default >= 0) {
            textFieldDecoratorModifierNode.textFieldState.m1616selectCharsIn5zctL8(TextRangeKt.TextRange(iM1596getOffsetForPosition3MmeM6k$default));
        }
        textFieldDecoratorModifierNode.textFieldSelectionState.m1659updateHandleDraggingUv8p0NA(Handle.Cursor, jM1606fromWindowToDecorationUv8p0NA);
        return Unit.INSTANCE;
    }

    private final boolean getAutofillHighlightOn() {
        return ((Boolean) this.autofillHighlightOn.getValue()).booleanValue();
    }

    public static boolean h(TextFieldDecoratorModifierNode textFieldDecoratorModifierNode, int i, int i2, boolean z) {
        TextFieldCharSequence untransformedText = z ? textFieldDecoratorModifierNode.textFieldState.getUntransformedText() : textFieldDecoratorModifierNode.textFieldState.getVisualText();
        long selection = untransformedText.getSelection();
        if (!textFieldDecoratorModifierNode.enabled || Math.min(i, i2) < 0 || Math.max(i, i2) > untransformedText.length()) {
            return false;
        }
        if (i == TextRange.getStart-impl(selection) && i2 == TextRange.getEnd-impl(selection)) {
            return true;
        }
        long jTextRange = TextRangeKt.TextRange(i, i2);
        if (z || i == i2) {
            textFieldDecoratorModifierNode.textFieldSelectionState.updateTextToolbarState(TextToolbarState.None);
        } else {
            textFieldDecoratorModifierNode.textFieldSelectionState.updateTextToolbarState(TextToolbarState.Selection);
        }
        TransformedTextFieldState transformedTextFieldState = textFieldDecoratorModifierNode.textFieldState;
        if (z) {
            transformedTextFieldState.m1617selectUntransformedCharsIn5zctL8(jTextRange);
            return true;
        }
        transformedTextFieldState.m1616selectCharsIn5zctL8(jTextRange);
        return true;
    }

    public static Set i(TextFieldDecoratorModifierNode textFieldDecoratorModifierNode) {
        return ReceiveContentConfigurationKt.getReceiveContentConfiguration(textFieldDecoratorModifierNode) != null ? TextFieldDecoratorModifierKt.MediaTypesAll : TextFieldDecoratorModifierKt.MediaTypesText;
    }

    private final boolean isFocused() {
        WindowInfo windowInfo;
        return this.focusableNode.getFocusState().isFocused() && (windowInfo = this.windowInfo) != null && windowInfo.isWindowFocused();
    }

    public static String j(TextFieldDecoratorModifierNode textFieldDecoratorModifierNode) {
        return textFieldDecoratorModifierNode.textFieldState.getUntransformedText().toString();
    }

    public static boolean k(TextFieldDecoratorModifierNode textFieldDecoratorModifierNode) {
        if (!textFieldDecoratorModifierNode.isFocused()) {
            textFieldDecoratorModifierNode.requestFocus();
        }
        textFieldDecoratorModifierNode.textFieldSelectionState.updateTextToolbarState(TextToolbarState.Selection);
        return true;
    }

    public static boolean l(boolean z, TextFieldDecoratorModifierNode textFieldDecoratorModifierNode, AnnotatedString annotatedString) {
        if (!z) {
            return false;
        }
        textFieldDecoratorModifierNode.textFieldState.replaceAll(annotatedString);
        return true;
    }

    public static ReceiveContentConfiguration m(TextFieldDecoratorModifierNode textFieldDecoratorModifierNode) {
        return ReceiveContentConfigurationKt.getReceiveContentConfiguration(textFieldDecoratorModifierNode);
    }

    public static Unit n(TextFieldDecoratorModifierNode textFieldDecoratorModifierNode, DragAndDropEvent dragAndDropEvent) {
        ReceiveContentListener receiveContentListener;
        textFieldDecoratorModifierNode.emitDragExitEvent();
        textFieldDecoratorModifierNode.textFieldSelectionState.clearHandleDragging();
        ReceiveContentConfiguration receiveContentConfiguration = ReceiveContentConfigurationKt.getReceiveContentConfiguration(textFieldDecoratorModifierNode);
        if (receiveContentConfiguration != null && (receiveContentListener = receiveContentConfiguration.getReceiveContentListener()) != null) {
            receiveContentListener.onDragExit();
        }
        return Unit.INSTANCE;
    }

    public static boolean o(TextFieldDecoratorModifierNode textFieldDecoratorModifierNode) {
        if (!textFieldDecoratorModifierNode.isFocused()) {
            textFieldDecoratorModifierNode.requestFocus();
            return true;
        }
        if (textFieldDecoratorModifierNode.readOnly) {
            return true;
        }
        textFieldDecoratorModifierNode.requireKeyboardController().show();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object observeUntransformedTextChanges(Continuation<? super Unit> continuation) {
        Object objCollect = FlowKt.take(FlowKt.drop(SnapshotStateKt.snapshotFlow(new Function0() { // from class: o5e
            public final Object invoke() {
                return TextFieldDecoratorModifierNode.j(this.b);
            }
        }), 1), 1).collect(new FlowCollector() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode.observeUntransformedTextChanges.3
            public final Object emit(String str, Continuation<? super Unit> continuation2) {
                TextFieldDecoratorModifierNode.this.setAutofillHighlightOn(false);
                return Unit.INSTANCE;
            }

            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation2) {
                return emit((String) obj, (Continuation<? super Unit>) continuation2);
            }
        }, continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onImeActionPerformed-KlQnJC8, reason: not valid java name */
    public final boolean m1577onImeActionPerformedKlQnJC8(final int imeAction) {
        KeyboardActionHandler keyboardActionHandler;
        ImeAction.Companion companion = ImeAction.Companion;
        if (ImeAction.equals-impl0(imeAction, companion.getNone-eUduSuo()) || ImeAction.equals-impl0(imeAction, companion.getDefault-eUduSuo()) || (keyboardActionHandler = this.keyboardActionHandler) == null) {
            return m1576defaultKeyboardActionWithResultKlQnJC8(imeAction);
        }
        if (keyboardActionHandler == null) {
            return true;
        }
        keyboardActionHandler.onKeyboardAction(new Function0() { // from class: g6e
            public final Object invoke() {
                return TextFieldDecoratorModifierNode.q(this.b, imeAction);
            }
        });
        return true;
    }

    private final void onIsFocusedUpdated() {
        this.textFieldSelectionState.setFocused(isFocused());
        if (isFocused() && this.toolbarAndHandlesVisibilityObserverJob == null) {
            this.toolbarAndHandlesVisibilityObserverJob = BuildersKt.launch$default(getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(null), 3, (Object) null);
        } else {
            if (isFocused()) {
                return;
            }
            Job job = this.toolbarAndHandlesVisibilityObserverJob;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            this.toolbarAndHandlesVisibilityObserverJob = null;
        }
    }

    public static Unit p(TextFieldDecoratorModifierNode textFieldDecoratorModifierNode) {
        DelegatableNodeKt.requestAutofill(textFieldDecoratorModifierNode);
        return Unit.INSTANCE;
    }

    public static Unit q(TextFieldDecoratorModifierNode textFieldDecoratorModifierNode, int i) {
        textFieldDecoratorModifierNode.keyboardActionScope.mo1356defaultKeyboardActionKlQnJC8(i);
        return Unit.INSTANCE;
    }

    public static boolean r(TextFieldDecoratorModifierNode textFieldDecoratorModifierNode) {
        BuildersKt.launch$default(textFieldDecoratorModifierNode.getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new TextFieldDecoratorModifierNode$applySemantics$10$1(textFieldDecoratorModifierNode, null), 3, (Object) null);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void requestFocus() {
        if (this.focusableNode.isAttached()) {
            this.focusableNode.requestFocus();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SoftwareKeyboardController requireKeyboardController() {
        SoftwareKeyboardController softwareKeyboardController = (SoftwareKeyboardController) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.getLocalSoftwareKeyboardController());
        if (softwareKeyboardController != null) {
            return softwareKeyboardController;
        }
        k2d.a("No software keyboard controller");
        return null;
    }

    public static boolean s(TextFieldDecoratorModifierNode textFieldDecoratorModifierNode) {
        BuildersKt.launch$default(textFieldDecoratorModifierNode.getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new TextFieldDecoratorModifierNode$applySemantics$12$1(textFieldDecoratorModifierNode, null), 3, (Object) null);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setAutofillHighlightOn(boolean z) {
        this.autofillHighlightOn.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startInputSession(boolean fromTap) {
        if (fromTap || this.keyboardOptions.getShowKeyboardOnFocusOrDefault$foundation()) {
            this.inputSessionJob = BuildersKt.launch$default(getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new C01811(ReceiveContentConfigurationKt.getReceiveContentConfiguration(this), null), 3, (Object) null);
        }
    }

    public static Unit t(TextFieldDecoratorModifierNode textFieldDecoratorModifierNode) {
        DelegatableNodeKt.requestAutofill(textFieldDecoratorModifierNode);
        return Unit.INSTANCE;
    }

    public static Unit u(TextFieldDecoratorModifierNode textFieldDecoratorModifierNode, boolean z) {
        boolean z2 = textFieldDecoratorModifierNode.enabled && !textFieldDecoratorModifierNode.readOnly;
        if (!z) {
            textFieldDecoratorModifierNode.disposeInputSession();
            TransformedTextFieldState transformedTextFieldState = textFieldDecoratorModifierNode.textFieldState;
            TextFieldState textFieldState = transformedTextFieldState.textFieldState;
            InputTransformation inputTransformation = transformedTextFieldState.inputTransformation;
            TextFieldEditUndoBehavior textFieldEditUndoBehavior = TextFieldEditUndoBehavior.MergeIfPossible;
            textFieldState.getMainBuffer().getChangeTracker$foundation().clearChanges();
            TextFieldBuffer mainBuffer = textFieldState.getMainBuffer();
            mainBuffer.commitComposition$foundation();
            transformedTextFieldState.updateWedgeAffinity(mainBuffer);
            textFieldState.commitEditAsUser(inputTransformation, true, textFieldEditUndoBehavior);
            textFieldDecoratorModifierNode.textFieldState.collapseSelectionToMax();
        } else if (z2) {
            textFieldDecoratorModifierNode.startInputSession(false);
        }
        textFieldDecoratorModifierNode.updateWindowFocus();
        return Unit.INSTANCE;
    }

    private final void updateWindowFocus() {
        ObserverModifierNodeKt.observeReads(this, new Function0() { // from class: r5e
            public final Object invoke() {
                return TextFieldDecoratorModifierNode.a(this.b);
            }
        });
    }

    public static Unit v(TextFieldDecoratorModifierNode textFieldDecoratorModifierNode, DragAndDropEvent dragAndDropEvent) {
        if (ReceiveContentConfigurationKt.getReceiveContentConfiguration(textFieldDecoratorModifierNode) != null) {
            DragAndDropRequestPermission_androidKt.dragAndDropRequestPermission(textFieldDecoratorModifierNode, dragAndDropEvent);
        }
        return Unit.INSTANCE;
    }

    public static boolean w(TextFieldDecoratorModifierNode textFieldDecoratorModifierNode, List list) {
        TextLayoutResult layoutResult = textFieldDecoratorModifierNode.textLayoutState.getLayoutResult();
        if (layoutResult != null) {
            return list.add(layoutResult);
        }
        return false;
    }

    public static boolean x(boolean z, TextFieldDecoratorModifierNode textFieldDecoratorModifierNode, FillableData fillableData) {
        if (!z) {
            return false;
        }
        CharSequence textValue = fillableData.getTextValue();
        if (textValue != null) {
            textFieldDecoratorModifierNode.textFieldState.replaceAll(textValue);
        }
        textFieldDecoratorModifierNode.setAutofillHighlightOn(true);
        BuildersKt.launch$default(textFieldDecoratorModifierNode.getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new TextFieldDecoratorModifierNode$applySemantics$2$2(textFieldDecoratorModifierNode, null), 3, (Object) null);
        return true;
    }

    public static boolean y(TextFieldDecoratorModifierNode textFieldDecoratorModifierNode, int i) {
        textFieldDecoratorModifierNode.m1577onImeActionPerformedKlQnJC8(i);
        return true;
    }

    public static boolean z(TextFieldDecoratorModifierNode textFieldDecoratorModifierNode) {
        BuildersKt.launch$default(textFieldDecoratorModifierNode.getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new TextFieldDecoratorModifierNode$applySemantics$11$1(textFieldDecoratorModifierNode, null), 3, (Object) null);
        return true;
    }

    public void applyFocusProperties(FocusProperties focusProperties) {
        focusProperties.setFocusRect(this.textFieldSelectionState.getFocusRect());
    }

    public void applySemantics(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        TextFieldCharSequence outputText = this.textFieldState.getOutputText();
        long selection = outputText.getSelection();
        SemanticsPropertiesKt.setInputText(semanticsPropertyReceiver, new AnnotatedString(this.textFieldState.getUntransformedText().toString(), (List) null, 2, (DefaultConstructorMarker) null));
        SemanticsPropertiesKt.setEditableText(semanticsPropertyReceiver, new AnnotatedString(outputText.toString(), (List) null, 2, (DefaultConstructorMarker) null));
        SemanticsPropertiesKt.setTextSelectionRange-FDrldGo(semanticsPropertyReceiver, selection);
        if (!this.enabled) {
            SemanticsPropertiesKt.disabled(semanticsPropertyReceiver);
        }
        if (this.isPassword) {
            SemanticsPropertiesKt.password(semanticsPropertyReceiver);
        }
        final boolean z = this.enabled && !this.readOnly;
        SemanticsPropertiesKt.setEditable(semanticsPropertyReceiver, z);
        SemanticsPropertiesKt.setContentDataType(semanticsPropertyReceiver, ContentDataType.Companion.getText());
        FillableData fillableDataCreateFromText = FillableData_androidKt.createFromText(FillableData.Companion, outputText);
        if (fillableDataCreateFromText != null) {
            SemanticsPropertiesKt.setFillableData(semanticsPropertyReceiver, fillableDataCreateFromText);
        }
        SemanticsPropertiesKt.onFillData$default(semanticsPropertyReceiver, (String) null, new Function1() { // from class: z5e
            public final Object invoke(Object obj) {
                return Boolean.valueOf(TextFieldDecoratorModifierNode.x(z, this, (FillableData) obj));
            }
        }, 1, (Object) null);
        int keyboardType = this.keyboardOptions.getKeyboardType();
        KeyboardType.Companion companion = KeyboardType.Companion;
        if (KeyboardType.equals-impl0(keyboardType, companion.getEmail-PjHm6EE())) {
            SemanticsPropertiesKt.setContentType(semanticsPropertyReceiver, ContentType.Companion.getEmailAddress());
        } else if (KeyboardType.equals-impl0(keyboardType, companion.getPassword-PjHm6EE()) || KeyboardType.equals-impl0(keyboardType, companion.getNumberPassword-PjHm6EE())) {
            SemanticsPropertiesKt.setContentType(semanticsPropertyReceiver, ContentType.Companion.getPassword());
        } else if (KeyboardType.equals-impl0(keyboardType, companion.getPhone-PjHm6EE())) {
            SemanticsPropertiesKt.setContentType(semanticsPropertyReceiver, ContentType.Companion.getPhoneNumber());
        }
        SemanticsPropertiesKt.getTextLayoutResult$default(semanticsPropertyReceiver, (String) null, new Function1() { // from class: i6e
            public final Object invoke(Object obj) {
                return Boolean.valueOf(TextFieldDecoratorModifierNode.w(this.b, (List) obj));
            }
        }, 1, (Object) null);
        if (z) {
            SemanticsPropertiesKt.setText$default(semanticsPropertyReceiver, (String) null, new Function1() { // from class: j6e
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(TextFieldDecoratorModifierNode.l(z, this, (AnnotatedString) obj));
                }
            }, 1, (Object) null);
            SemanticsPropertiesKt.insertTextAtCursor$default(semanticsPropertyReceiver, (String) null, new Function1() { // from class: k6e
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(TextFieldDecoratorModifierNode.c(z, this, (AnnotatedString) obj));
                }
            }, 1, (Object) null);
        }
        SemanticsPropertiesKt.setSelection$default(semanticsPropertyReceiver, (String) null, new Function3() { // from class: l6e
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return Boolean.valueOf(TextFieldDecoratorModifierNode.h(this.b, ((Integer) obj).intValue(), ((Integer) obj2).intValue(), ((Boolean) obj3).booleanValue()));
            }
        }, 1, (Object) null);
        final int iM1370getImeActionOrDefaulteUduSuo$foundation = this.keyboardOptions.m1370getImeActionOrDefaulteUduSuo$foundation();
        SemanticsPropertiesKt.onImeAction-9UiTYpY$default(semanticsPropertyReceiver, iM1370getImeActionOrDefaulteUduSuo$foundation, (String) null, new Function0() { // from class: m6e
            public final Object invoke() {
                return Boolean.valueOf(TextFieldDecoratorModifierNode.y(this.b, iM1370getImeActionOrDefaulteUduSuo$foundation));
            }
        }, 2, (Object) null);
        SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, (String) null, new Function0() { // from class: n6e
            public final Object invoke() {
                return Boolean.valueOf(TextFieldDecoratorModifierNode.o(this.b));
            }
        }, 1, (Object) null);
        SemanticsPropertiesKt.onLongClick$default(semanticsPropertyReceiver, (String) null, new Function0() { // from class: o6e
            public final Object invoke() {
                return Boolean.valueOf(TextFieldDecoratorModifierNode.k(this.b));
            }
        }, 1, (Object) null);
        if (!TextRange.getCollapsed-impl(selection) && !this.isPassword) {
            SemanticsPropertiesKt.copyText$default(semanticsPropertyReceiver, (String) null, new Function0() { // from class: p5e
                public final Object invoke() {
                    return Boolean.valueOf(TextFieldDecoratorModifierNode.r(this.b));
                }
            }, 1, (Object) null);
            if (this.enabled && !this.readOnly) {
                SemanticsPropertiesKt.cutText$default(semanticsPropertyReceiver, (String) null, new Function0() { // from class: q5e
                    public final Object invoke() {
                        return Boolean.valueOf(TextFieldDecoratorModifierNode.z(this.b));
                    }
                }, 1, (Object) null);
            }
        }
        if (z) {
            SemanticsPropertiesKt.pasteText$default(semanticsPropertyReceiver, (String) null, new Function0() { // from class: h6e
                public final Object invoke() {
                    return Boolean.valueOf(TextFieldDecoratorModifierNode.s(this.b));
                }
            }, 1, (Object) null);
        }
        InputTransformation inputTransformation = this.filter;
        if (inputTransformation != null) {
            inputTransformation.applySemantics(semanticsPropertyReceiver);
        }
        if (this.enabled) {
            this.focusableNode.applySemantics(semanticsPropertyReceiver);
        }
    }

    public void draw(ContentDrawScope contentDrawScope) {
        contentDrawScope.drawContent();
        if (getAutofillHighlightOn()) {
            DrawScope.drawRect-AsUm42w$default(contentDrawScope, AutofillHighlightKt.m1295resolveAutofillHighlightWkMShQ((Brush) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, AutofillHighlightKt.getLocalAutofillHighlightBrush()), ((Color) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, AutofillHighlightKt.getLocalAutofillHighlightColor())).unbox-impl(), AutofillHighlight_androidKt.autofillHighlightColor()), 0L, 0L, 0.0f, (DrawStyle) null, (ColorFilter) null, 0, 126, (Object) null);
        }
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final InputTransformation getFilter() {
        return this.filter;
    }

    public final MutableInteractionSource getInteractionSource() {
        return this.interactionSource;
    }

    public final KeyboardActionHandler getKeyboardActionHandler() {
        return this.keyboardActionHandler;
    }

    public final KeyboardOptions getKeyboardOptions() {
        return this.keyboardOptions;
    }

    public final boolean getReadOnly() {
        return this.readOnly;
    }

    public boolean getShouldMergeDescendantSemantics() {
        return true;
    }

    public final boolean getSingleLine() {
        return this.singleLine;
    }

    public final MutableSharedFlow<Unit> getStylusHandwritingTrigger() {
        return this.stylusHandwritingTrigger;
    }

    public final TextFieldSelectionState getTextFieldSelectionState() {
        return this.textFieldSelectionState;
    }

    public final TransformedTextFieldState getTextFieldState() {
        return this.textFieldState;
    }

    public final TextLayoutState getTextLayoutState() {
        return this.textLayoutState;
    }

    /* JADX INFO: renamed from: isPassword, reason: from getter */
    public final boolean getIsPassword() {
        return this.isPassword;
    }

    public void onAttach() {
        onObservedReadsChanged();
        this.textFieldSelectionState.setReceiveContentConfiguration(this.receiveContentConfigurationProvider);
        if (this.enabled) {
            delegate(this.focusableNode);
        }
    }

    public void onCancelPointerInput() {
        this.pointerInputNode.onCancelPointerInput();
    }

    public void onDetach() {
        disposeInputSession();
        this.textFieldSelectionState.setReceiveContentConfiguration(null);
    }

    public void onGloballyPositioned(LayoutCoordinates coordinates) {
        this.textLayoutState.setDecoratorNodeCoordinates(coordinates);
        if (this.enabled) {
            this.focusableNode.onGloballyPositioned(coordinates);
        }
    }

    /* JADX INFO: renamed from: onKeyEvent-ZmokQxo, reason: not valid java name */
    public boolean m1578onKeyEventZmokQxo(KeyEvent event) {
        return this.textFieldKeyEventHandler.mo1517onKeyEvent8zsqlwg(event, this.textFieldState, this.textLayoutState, this.textFieldSelectionState, this.clipboardKeyCommandsHandler, requireKeyboardController(), this.enabled && !this.readOnly, this.singleLine, new Function0() { // from class: f6e
            public final Object invoke() {
                return Boolean.valueOf(TextFieldDecoratorModifierNode.f(this.b));
            }
        });
    }

    public void onObservedReadsChanged() {
        updateWindowFocus();
    }

    public void onPlaced(LayoutCoordinates coordinates) {
        this.dragAndDropNode.onPlaced(coordinates);
    }

    /* JADX INFO: renamed from: onPointerEvent-H0pRuoY, reason: not valid java name */
    public void m1579onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pass, long bounds) {
        this.pointerInputNode.onPointerEvent-H0pRuoY(pointerEvent, pass, bounds);
    }

    /* JADX INFO: renamed from: onPreKeyEvent-ZmokQxo, reason: not valid java name */
    public boolean m1580onPreKeyEventZmokQxo(KeyEvent event) {
        return this.textFieldKeyEventHandler.mo1518onPreKeyEventMyFupTE(event, this.textFieldState, this.textFieldSelectionState, (FocusManager) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.getLocalFocusManager()), requireKeyboardController());
    }

    /* JADX INFO: renamed from: onRemeasured-ozmzZPI, reason: not valid java name */
    public void m1581onRemeasuredozmzZPI(long size) {
        this.dragAndDropNode.onRemeasured-ozmzZPI(size);
    }

    public final void setEnabled(boolean z) {
        this.enabled = z;
    }

    public final void setFilter(InputTransformation inputTransformation) {
        this.filter = inputTransformation;
    }

    public final void setInteractionSource(MutableInteractionSource mutableInteractionSource) {
        this.interactionSource = mutableInteractionSource;
    }

    public final void setKeyboardActionHandler(KeyboardActionHandler keyboardActionHandler) {
        this.keyboardActionHandler = keyboardActionHandler;
    }

    public final void setKeyboardOptions(KeyboardOptions keyboardOptions) {
        this.keyboardOptions = keyboardOptions;
    }

    public final void setPassword(boolean z) {
        this.isPassword = z;
    }

    public final void setReadOnly(boolean z) {
        this.readOnly = z;
    }

    public final void setSingleLine(boolean z) {
        this.singleLine = z;
    }

    public final void setStylusHandwritingTrigger(MutableSharedFlow<Unit> mutableSharedFlow) {
        this.stylusHandwritingTrigger = mutableSharedFlow;
    }

    public final void setTextFieldSelectionState(TextFieldSelectionState textFieldSelectionState) {
        this.textFieldSelectionState = textFieldSelectionState;
    }

    public final void setTextFieldState(TransformedTextFieldState transformedTextFieldState) {
        this.textFieldState = transformedTextFieldState;
    }

    public final void setTextLayoutState(TextLayoutState textLayoutState) {
        this.textLayoutState = textLayoutState;
    }

    public final void updateNode(TransformedTextFieldState textFieldState, TextLayoutState textLayoutState, TextFieldSelectionState textFieldSelectionState, InputTransformation filter, boolean enabled, boolean readOnly, KeyboardOptions keyboardOptions, KeyboardActionHandler keyboardActionHandler, boolean singleLine, MutableInteractionSource interactionSource, boolean isPassword, MutableSharedFlow<Unit> stylusHandwritingTrigger) {
        Job job;
        boolean z = this.enabled;
        boolean z2 = z && !this.readOnly;
        TransformedTextFieldState transformedTextFieldState = this.textFieldState;
        KeyboardOptions keyboardOptions2 = this.keyboardOptions;
        TextFieldSelectionState textFieldSelectionState2 = this.textFieldSelectionState;
        MutableInteractionSource mutableInteractionSource = this.interactionSource;
        boolean z3 = this.isPassword;
        MutableSharedFlow<Unit> mutableSharedFlow = this.stylusHandwritingTrigger;
        boolean z4 = enabled && !readOnly;
        this.textFieldState = textFieldState;
        this.textLayoutState = textLayoutState;
        this.textFieldSelectionState = textFieldSelectionState;
        this.filter = filter;
        this.enabled = enabled;
        this.readOnly = readOnly;
        this.keyboardOptions = keyboardOptions;
        this.keyboardActionHandler = keyboardActionHandler;
        this.singleLine = singleLine;
        this.interactionSource = interactionSource;
        this.isPassword = isPassword;
        this.stylusHandwritingTrigger = stylusHandwritingTrigger;
        if (z4 != z2 || !Intrinsics.areEqual(textFieldState, transformedTextFieldState) || !Intrinsics.areEqual(keyboardOptions, keyboardOptions2) || !Intrinsics.areEqual(stylusHandwritingTrigger, mutableSharedFlow)) {
            if (z4 && (isFocused() || this.inputSessionJob != null)) {
                startInputSession(false);
            } else if (!z4) {
                disposeInputSession();
            }
        }
        if (enabled != z || z4 != z2 || !ImeAction.equals-impl0(keyboardOptions.m1370getImeActionOrDefaulteUduSuo$foundation(), keyboardOptions2.m1370getImeActionOrDefaulteUduSuo$foundation()) || isPassword != z3) {
            SemanticsModifierNodeKt.invalidateSemantics(this);
        }
        if (!Intrinsics.areEqual(textFieldSelectionState, textFieldSelectionState2)) {
            this.pointerInputNode.resetPointerInputHandler();
            if (isAttached()) {
                textFieldSelectionState.setReceiveContentConfiguration(this.receiveContentConfigurationProvider);
                if (isFocused() && (job = this.toolbarAndHandlesVisibilityObserverJob) != null) {
                    if (job != null) {
                        Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                    }
                    this.toolbarAndHandlesVisibilityObserverJob = BuildersKt.launch$default(getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new C01821(textFieldSelectionState, null), 3, (Object) null);
                }
            }
            textFieldSelectionState.setRequestAutofillAction(new Function0() { // from class: s5e
                public final Object invoke() {
                    return TextFieldDecoratorModifierNode.p(this.b);
                }
            });
        }
        if (!Intrinsics.areEqual(interactionSource, mutableInteractionSource)) {
            this.pointerInputNode.resetPointerInputHandler();
            if (this.focusableNode.isAttached()) {
                this.focusableNode.update(interactionSource);
            }
        }
        if (enabled != z) {
            FocusableNode focusableNode = this.focusableNode;
            if (!enabled) {
                undelegate(focusableNode);
            } else {
                delegate(focusableNode);
                this.focusableNode.update(interactionSource);
            }
        }
    }
}
