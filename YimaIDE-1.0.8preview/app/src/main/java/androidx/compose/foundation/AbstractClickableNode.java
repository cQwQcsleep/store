package androidx.compose.foundation;

import android.view.KeyEvent;
import androidx.collection.LongObjectMapKt;
import androidx.collection.MutableLongObjectMap;
import androidx.compose.foundation.AbstractClickableNode;
import androidx.compose.foundation.gestures.PressGestureScope;
import androidx.compose.foundation.interaction.HoverInteraction;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.ui.focus.Focusability;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.indirect.IndirectPointerEvent;
import androidx.compose.ui.input.indirect.IndirectPointerInputChange;
import androidx.compose.ui.input.indirect.IndirectPointerInputModifierNode;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierNode;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerEventType;
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.ObserverModifierNode;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import androidx.compose.ui.node.PointerInputModifierNode;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.SemanticsModifierNodeKt;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSizeKt;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000Ì\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0005\b!\u0018\u0000 x2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u00072\u00020\b:\u0002wxBM\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015¢\u0006\u0004\b\u0017\u0010\u0018J\b\u00104\u001a\u00020\u000eH\u0002J\n\u00107\u001a\u0004\u0018\u00010$H&J\f\u00108\u001a\u00020\u0016*\u000209H\u0016JS\u0010:\u001a\u00020\u00162\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0004¢\u0006\u0002\b;J\u0018\u0010<\u001a\u00020\u00162\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020@H\u0016J\b\u0010A\u001a\u00020\u0016H\u0016J\u0006\u0010B\u001a\u00020\u0016J\b\u0010C\u001a\u00020\u0016H\u0016J\u0006\u0010D\u001a\u00020\u0016J\b\u0010E\u001a\u00020\u0016H\u0004J\u0010\u0010F\u001a\u00020\u00162\u0006\u0010G\u001a\u00020\u000eH\u0002J\b\u0010H\u001a\u00020\u0016H\u0002J\b\u0010I\u001a\u00020\u0016H\u0002J'\u0010J\u001a\u00020\u00162\u0006\u0010K\u001a\u00020L2\u0006\u0010?\u001a\u00020@2\u0006\u0010M\u001a\u00020NH\u0016¢\u0006\u0004\bO\u0010PJ\b\u0010Q\u001a\u00020\u0016H\u0016J\u0015\u0010R\u001a\u00020\u000e2\u0006\u0010=\u001a\u00020S¢\u0006\u0004\bT\u0010UJ\u0017\u0010V\u001a\u00020\u000e2\u0006\u0010=\u001a\u00020SH$¢\u0006\u0004\bW\u0010UJ\u0017\u0010X\u001a\u00020\u000e2\u0006\u0010=\u001a\u00020SH$¢\u0006\u0004\bY\u0010UJ\b\u0010Z\u001a\u00020\u0016H\u0014J\u0015\u0010[\u001a\u00020\u000e2\u0006\u0010=\u001a\u00020S¢\u0006\u0004\b\\\u0010UJ\n\u0010_\u001a\u00020\u0016*\u000209J\u000f\u0010`\u001a\u0004\u0018\u00010\u0016H\u0004¢\u0006\u0002\u0010aJ\u001f\u0010d\u001a\u00020\u00162\u0006\u0010e\u001a\u00020.2\u0006\u0010f\u001a\u00020\u000eH\u0004¢\u0006\u0004\bg\u0010hJ\u001f\u0010i\u001a\u00020\u00162\u0006\u0010e\u001a\u00020.2\u0006\u0010f\u001a\u00020\u000eH\u0004¢\u0006\u0004\bj\u0010hJ\u0010\u0010k\u001a\u00020\u00162\u0006\u0010f\u001a\u00020\u000eH\u0004J\u001c\u0010l\u001a\u00020\u0016*\u00020m2\u0006\u0010e\u001a\u00020.H\u0084@¢\u0006\u0004\bn\u0010oJ\b\u0010p\u001a\u00020\u000eH\u0002J\b\u0010q\u001a\u00020\u0016H\u0002J\b\u0010r\u001a\u00020\u0016H\u0002R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u000e@BX\u0084\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR*\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015@BX\u0084\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\u000eX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001bR\u000e\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010+\u001a\b\u0012\u0004\u0012\u00020(0,X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010-\u001a\u00020.X\u0082\u000e¢\u0006\u0004\n\u0002\u0010/R\u0010\u00100\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00101\u001a\u0004\u0018\u00010.X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00102\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00105\u001a\u0004\u0018\u000106X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010]\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b^\u0010\u001bR\u0010\u0010b\u001a\u0004\u0018\u00010cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010s\u001a\u00020tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bu\u0010v¨\u0006y"}, d2 = {"Landroidx/compose/foundation/AbstractClickableNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/node/PointerInputModifierNode;", "Landroidx/compose/ui/input/key/KeyInputModifierNode;", "Landroidx/compose/ui/node/SemanticsModifierNode;", "Landroidx/compose/ui/node/TraversableNode;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "Landroidx/compose/ui/node/ObserverModifierNode;", "Landroidx/compose/ui/input/indirect/IndirectPointerInputModifierNode;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "indicationNodeFactory", "Landroidx/compose/foundation/IndicationNodeFactory;", "useLocalIndication", "", "enabled", "onClickLabel", "", "role", "Landroidx/compose/ui/semantics/Role;", "onClick", "Lkotlin/Function0;", "", "<init>", "(Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/IndicationNodeFactory;ZZLjava/lang/String;Landroidx/compose/ui/semantics/Role;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "value", "getEnabled", "()Z", "getOnClick", "()Lkotlin/jvm/functions/Function0;", "shouldAutoInvalidate", "getShouldAutoInvalidate", "focusableNode", "Landroidx/compose/foundation/FocusableNode;", "localIndicationNodeFactory", "pointerInputNode", "Landroidx/compose/ui/input/pointer/SuspendingPointerInputModifierNode;", "indicationNode", "Landroidx/compose/ui/node/DelegatableNode;", "pressInteraction", "Landroidx/compose/foundation/interaction/PressInteraction$Press;", "hoverInteraction", "Landroidx/compose/foundation/interaction/HoverInteraction$Enter;", "currentKeyPressInteractions", "Landroidx/collection/MutableLongObjectMap;", "centerOffset", "Landroidx/compose/ui/geometry/Offset;", "J", "indirectPointerPressInteraction", "indirectPointerEventPressPosition", "userProvidedInteractionSource", "lazilyCreateIndication", "shouldLazilyCreateIndication", "indirectPointerClickDetector", "Landroidx/compose/foundation/AbstractClickableNode$IndirectPointerClickDetector;", "createPointerInputNodeIfNeeded", "applyAdditionalSemantics", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "updateCommon", "updateCommon-O2vRcR0", "onIndirectPointerEvent", "event", "Landroidx/compose/ui/input/indirect/IndirectPointerEvent;", "pass", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "onCancelIndirectPointerInput", "onAttach", "onObservedReadsChanged", "onDetach", "disposeInteractions", "onFocusChange", "isFocused", "recreateIndicationIfNeeded", "initializeIndicationAndInteractionSourceIfNeeded", "onPointerEvent", "pointerEvent", "Landroidx/compose/ui/input/pointer/PointerEvent;", "bounds", "Landroidx/compose/ui/unit/IntSize;", "onPointerEvent-H0pRuoY", "(Landroidx/compose/ui/input/pointer/PointerEvent;Landroidx/compose/ui/input/pointer/PointerEventPass;J)V", "onCancelPointerInput", "onKeyEvent", "Landroidx/compose/ui/input/key/KeyEvent;", "onKeyEvent-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "onClickKeyDownEvent", "onClickKeyDownEvent-ZmokQxo", "onClickKeyUpEvent", "onClickKeyUpEvent-ZmokQxo", "onCancelKeyInput", "onPreKeyEvent", "onPreKeyEvent-ZmokQxo", "shouldMergeDescendantSemantics", "getShouldMergeDescendantSemantics", "applySemantics", "resetPointerInputHandler", "()Lkotlin/Unit;", "delayJob", "Lkotlinx/coroutines/Job;", "handlePressInteractionStart", "offset", "indirectPointer", "handlePressInteractionStart-3MmeM6k", "(JZ)V", "handlePressInteractionRelease", "handlePressInteractionRelease-3MmeM6k", "handlePressInteractionCancel", "handlePressInteraction", "Landroidx/compose/foundation/gestures/PressGestureScope;", "handlePressInteraction-d-4ec7I", "(Landroidx/compose/foundation/gestures/PressGestureScope;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delayPressInteraction", "emitHoverEnter", "emitHoverExit", "traverseKey", "", "getTraverseKey", "()Ljava/lang/Object;", "IndirectPointerClickDetector", "TraverseKey", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class AbstractClickableNode extends DelegatingNode implements PointerInputModifierNode, KeyInputModifierNode, SemanticsModifierNode, TraversableNode, CompositionLocalConsumerModifierNode, ObserverModifierNode, IndirectPointerInputModifierNode {
    private long centerOffset;
    private final MutableLongObjectMap<PressInteraction.Press> currentKeyPressInteractions;
    private Job delayJob;
    private boolean enabled;
    private final FocusableNode focusableNode;
    private HoverInteraction.Enter hoverInteraction;
    private DelegatableNode indicationNode;
    private IndicationNodeFactory indicationNodeFactory;
    private IndirectPointerClickDetector indirectPointerClickDetector;
    private Offset indirectPointerEventPressPosition;
    private PressInteraction.Press indirectPointerPressInteraction;
    private MutableInteractionSource interactionSource;
    private boolean lazilyCreateIndication;
    private IndicationNodeFactory localIndicationNodeFactory;
    private Function0<Unit> onClick;
    private String onClickLabel;
    private SuspendingPointerInputModifierNode pointerInputNode;
    private PressInteraction.Press pressInteraction;
    private Role role;
    private final boolean shouldAutoInvalidate;
    private final Object traverseKey;
    private boolean useLocalIndication;
    private MutableInteractionSource userProvidedInteractionSource;

    /* JADX INFO: renamed from: TraverseKey, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J$\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0011J\u0006\u0010\u0012\u001a\u00020\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Landroidx/compose/foundation/AbstractClickableNode$IndirectPointerClickDetector;", "", "node", "Landroidx/compose/foundation/AbstractClickableNode;", "<init>", "(Landroidx/compose/foundation/AbstractClickableNode;)V", "getNode", "()Landroidx/compose/foundation/AbstractClickableNode;", "downEvent", "Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;", "processRawEvent", "", "pointerEvent", "Landroidx/compose/ui/input/indirect/IndirectPointerEvent;", "pass", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "onClick", "Lkotlin/Function0;", "resetDetector", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class IndirectPointerClickDetector {
        public static final int $stable = 8;
        private IndirectPointerInputChange downEvent;
        private final AbstractClickableNode node;

        public IndirectPointerClickDetector(AbstractClickableNode abstractClickableNode) {
            this.node = abstractClickableNode;
        }

        public final AbstractClickableNode getNode() {
            return this.node;
        }

        public final void processRawEvent(IndirectPointerEvent pointerEvent, PointerEventPass pass, Function0<Unit> onClick) {
            int i = 0;
            if (pass != PointerEventPass.Main) {
                if (pass != PointerEventPass.Final || this.downEvent == null) {
                    return;
                }
                List changes = pointerEvent.getChanges();
                int size = changes.size();
                while (i < size) {
                    IndirectPointerInputChange indirectPointerInputChange = (IndirectPointerInputChange) changes.get(i);
                    if (indirectPointerInputChange.isConsumed() && !Intrinsics.areEqual(indirectPointerInputChange, this.downEvent)) {
                        resetDetector();
                        return;
                    }
                    i++;
                }
                return;
            }
            IndirectPointerInputChange indirectPointerInputChange2 = this.downEvent;
            if (indirectPointerInputChange2 == null) {
                List changes2 = pointerEvent.getChanges();
                int size2 = changes2.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    if (ClickableKt.changedToDownIgnoreConsumed((IndirectPointerInputChange) changes2.get(i2))) {
                        IndirectPointerInputChange indirectPointerInputChange3 = (IndirectPointerInputChange) pointerEvent.getChanges().get(0);
                        this.downEvent = indirectPointerInputChange3;
                        this.node.m288handlePressInteractionStart3MmeM6k(indirectPointerInputChange3.getPosition-F1C5BW0(), true);
                        indirectPointerInputChange3.consume();
                        return;
                    }
                }
                return;
            }
            List changes3 = pointerEvent.getChanges();
            int size3 = changes3.size();
            for (int i3 = 0; i3 < size3; i3++) {
                if (ClickableKt.isMovingIgnoreConsumed((IndirectPointerInputChange) changes3.get(i3))) {
                    if (Math.abs(Offset.getDistance-impl(Offset.minus-MK-Hz9U(((IndirectPointerInputChange) pointerEvent.getChanges().get(0)).getPosition-F1C5BW0(), indirectPointerInputChange2.getPosition-F1C5BW0()))) > ((ViewConfiguration) CompositionLocalConsumerModifierNodeKt.currentValueOf(this.node, CompositionLocalsKt.getLocalViewConfiguration())).getTouchSlop()) {
                        resetDetector();
                        return;
                    }
                    return;
                }
            }
            List changes4 = pointerEvent.getChanges();
            int size4 = changes4.size();
            for (int i4 = 0; i4 < size4; i4++) {
                if (!ClickableKt.changedToUp((IndirectPointerInputChange) changes4.get(i4))) {
                    List changes5 = pointerEvent.getChanges();
                    int size5 = changes5.size();
                    while (i < size5) {
                        if (((IndirectPointerInputChange) changes5.get(i)).isConsumed()) {
                            resetDetector();
                            return;
                        }
                        i++;
                    }
                    return;
                }
            }
            ((IndirectPointerInputChange) pointerEvent.getChanges().get(0)).consume();
            this.node.m287handlePressInteractionRelease3MmeM6k(indirectPointerInputChange2.getPosition-F1C5BW0(), true);
            onClick.invoke();
            this.downEvent = null;
        }

        public final void resetDetector() {
            if (this.downEvent != null) {
                this.downEvent = null;
                this.node.handlePressInteractionCancel(true);
            }
        }
    }

    private AbstractClickableNode(MutableInteractionSource mutableInteractionSource, IndicationNodeFactory indicationNodeFactory, boolean z, boolean z2, String str, Role role, Function0<Unit> function0) {
        this.interactionSource = mutableInteractionSource;
        this.indicationNodeFactory = indicationNodeFactory;
        this.useLocalIndication = z;
        this.onClickLabel = str;
        this.role = role;
        this.enabled = z2;
        this.onClick = function0;
        this.focusableNode = new FocusableNode(this.interactionSource, Focusability.Companion.getSystemDefined-LCbbffg(), new AbstractClickableNode$focusableNode$1(this), null);
        this.currentKeyPressInteractions = LongObjectMapKt.mutableLongObjectMapOf();
        this.centerOffset = Offset.Companion.getZero-F1C5BW0();
        this.userProvidedInteractionSource = this.interactionSource;
        this.lazilyCreateIndication = shouldLazilyCreateIndication();
        this.traverseKey = INSTANCE;
    }

    public static Unit a(AbstractClickableNode abstractClickableNode) {
        Indication indication = (Indication) CompositionLocalConsumerModifierNodeKt.currentValueOf(abstractClickableNode, IndicationKt.getLocalIndication());
        if (!(indication instanceof IndicationNodeFactory)) {
            InlineClassHelperKt.throwIllegalArgumentException(ClickableKt.unsupportedIndicationExceptionMessage(indication));
        }
        IndicationNodeFactory indicationNodeFactory = abstractClickableNode.localIndicationNodeFactory;
        IndicationNodeFactory indicationNodeFactory2 = (IndicationNodeFactory) indication;
        abstractClickableNode.localIndicationNodeFactory = indicationNodeFactory2;
        if (indicationNodeFactory != null && !Intrinsics.areEqual(indicationNodeFactory2, indicationNodeFactory)) {
            abstractClickableNode.recreateIndicationIfNeeded();
        }
        return Unit.INSTANCE;
    }

    public static boolean b(AbstractClickableNode abstractClickableNode) {
        abstractClickableNode.onClick.invoke();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean delayPressInteraction() {
        return ClickableKt.hasScrollableContainer(this) || Clickable_androidKt.isComposeRootInScrollableContainer(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void emitHoverEnter() {
        if (this.hoverInteraction == null) {
            HoverInteraction.Enter enter = new HoverInteraction.Enter();
            MutableInteractionSource mutableInteractionSource = this.interactionSource;
            if (mutableInteractionSource != null) {
                BuildersKt.launch$default(getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new AbstractClickableNode$emitHoverEnter$1$1(mutableInteractionSource, enter, null), 3, (Object) null);
            }
            this.hoverInteraction = enter;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void emitHoverExit() {
        HoverInteraction.Enter enter = this.hoverInteraction;
        if (enter != null) {
            HoverInteraction.Exit exit = new HoverInteraction.Exit(enter);
            MutableInteractionSource mutableInteractionSource = this.interactionSource;
            if (mutableInteractionSource != null) {
                BuildersKt.launch$default(getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new AbstractClickableNode$emitHoverExit$1$1$1(mutableInteractionSource, exit, null), 3, (Object) null);
            }
            this.hoverInteraction = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit handlePressInteractionCancel$lambda$0$0$0(MutableInteractionSource mutableInteractionSource, PressInteraction.Cancel cancel, Throwable th) {
        mutableInteractionSource.tryEmit(cancel);
        return Unit.INSTANCE;
    }

    private final void initializeIndicationAndInteractionSourceIfNeeded() {
        if (this.indicationNode != null) {
            return;
        }
        IndicationNodeFactory indicationNodeFactory = this.useLocalIndication ? this.localIndicationNodeFactory : this.indicationNodeFactory;
        if (indicationNodeFactory != null) {
            if (this.interactionSource == null) {
                this.interactionSource = InteractionSourceKt.MutableInteractionSource();
            }
            this.focusableNode.update(this.interactionSource);
            MutableInteractionSource mutableInteractionSource = this.interactionSource;
            mutableInteractionSource.getClass();
            DelegatableNode delegatableNodeCreate = indicationNodeFactory.create(mutableInteractionSource);
            delegate(delegatableNodeCreate);
            this.indicationNode = delegatableNodeCreate;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:20:0x0060 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:21:0x0062 A[LOOP:0: B:11:0x001a->B:21:0x0062, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:28:0x0065 A[EDGE_INSN: B:28:0x0065->B:22:0x0065 BREAK  A[LOOP:0: B:11:0x001a->B:21:0x0062], SYNTHETIC] */
    public final void onFocusChange(boolean isFocused) {
        if (isFocused) {
            initializeIndicationAndInteractionSourceIfNeeded();
            return;
        }
        if (this.interactionSource != null) {
            MutableLongObjectMap<PressInteraction.Press> mutableLongObjectMap = this.currentKeyPressInteractions;
            Object[] objArr = mutableLongObjectMap.values;
            long[] jArr = mutableLongObjectMap.metadata;
            int length = jArr.length - 2;
            if (length >= 0) {
                int i = 0;
                while (true) {
                    long j = jArr[i];
                    if ((((~j) << 7) & j & (-9187201950435737472L)) == -9187201950435737472L) {
                        if (i != length) {
                            break;
                            break;
                        }
                        i++;
                    } else {
                        int i2 = 8 - ((~(i - length)) >>> 31);
                        for (int i3 = 0; i3 < i2; i3++) {
                            if ((255 & j) < 128) {
                                BuildersKt.launch$default(getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new AbstractClickableNode$onFocusChange$1$1(this, (PressInteraction.Press) objArr[(i << 3) + i3], null), 3, (Object) null);
                            }
                            j >>= 8;
                        }
                        if (i2 != 8) {
                            break;
                        } else if (i != length) {
                            break;
                        } else {
                            i++;
                        }
                    }
                }
            }
            PressInteraction.Press press = this.indirectPointerPressInteraction;
            if (press != null) {
                BuildersKt.launch$default(getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new AbstractClickableNode$onFocusChange$2$1(this, press, null), 3, (Object) null);
            }
        }
        this.currentKeyPressInteractions.clear();
        this.indirectPointerPressInteraction = null;
        onCancelKeyInput();
    }

    private final void recreateIndicationIfNeeded() {
        DelegatableNode delegatableNode = this.indicationNode;
        if (delegatableNode == null && this.lazilyCreateIndication) {
            return;
        }
        if (delegatableNode != null) {
            undelegate(delegatableNode);
        }
        this.indicationNode = null;
        initializeIndicationAndInteractionSourceIfNeeded();
    }

    private final boolean shouldLazilyCreateIndication() {
        return this.userProvidedInteractionSource == null;
    }

    public void applyAdditionalSemantics(SemanticsPropertyReceiver semanticsPropertyReceiver) {
    }

    public final void applySemantics(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        Role role = this.role;
        if (role != null) {
            role.getClass();
            SemanticsPropertiesKt.setRole-kuIjeqM(semanticsPropertyReceiver, role.unbox-impl());
        }
        SemanticsPropertiesKt.onClick(semanticsPropertyReceiver, this.onClickLabel, new Function0() { // from class: ll
            public final Object invoke() {
                return Boolean.valueOf(AbstractClickableNode.b(this.b));
            }
        });
        if (this.enabled) {
            this.focusableNode.applySemantics(semanticsPropertyReceiver);
        } else {
            SemanticsPropertiesKt.disabled(semanticsPropertyReceiver);
        }
        applyAdditionalSemantics(semanticsPropertyReceiver);
    }

    public abstract SuspendingPointerInputModifierNode createPointerInputNodeIfNeeded();

    /* JADX WARN: Code duplicated, block: B:25:0x006f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:26:0x0071 A[LOOP:0: B:16:0x0035->B:26:0x0071, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:30:0x0074 A[EDGE_INSN: B:30:0x0074->B:27:0x0074 BREAK  A[LOOP:0: B:16:0x0035->B:26:0x0071], SYNTHETIC] */
    public final void disposeInteractions() {
        MutableInteractionSource mutableInteractionSource = this.interactionSource;
        if (mutableInteractionSource != null) {
            PressInteraction.Press press = this.pressInteraction;
            if (press != null) {
                mutableInteractionSource.tryEmit(new PressInteraction.Cancel(press));
            }
            PressInteraction.Press press2 = this.indirectPointerPressInteraction;
            if (press2 != null) {
                mutableInteractionSource.tryEmit(new PressInteraction.Cancel(press2));
            }
            HoverInteraction.Enter enter = this.hoverInteraction;
            if (enter != null) {
                mutableInteractionSource.tryEmit(new HoverInteraction.Exit(enter));
            }
            MutableLongObjectMap<PressInteraction.Press> mutableLongObjectMap = this.currentKeyPressInteractions;
            Object[] objArr = mutableLongObjectMap.values;
            long[] jArr = mutableLongObjectMap.metadata;
            int length = jArr.length - 2;
            if (length >= 0) {
                int i = 0;
                while (true) {
                    long j = jArr[i];
                    if ((((~j) << 7) & j & (-9187201950435737472L)) == -9187201950435737472L) {
                        if (i != length) {
                            break;
                            break;
                        }
                        i++;
                    } else {
                        int i2 = 8 - ((~(i - length)) >>> 31);
                        for (int i3 = 0; i3 < i2; i3++) {
                            if ((255 & j) < 128) {
                                mutableInteractionSource.tryEmit(new PressInteraction.Cancel((PressInteraction.Press) objArr[(i << 3) + i3]));
                            }
                            j >>= 8;
                        }
                        if (i2 != 8) {
                            break;
                        } else if (i != length) {
                            break;
                        } else {
                            i++;
                        }
                    }
                }
            }
        }
        this.pressInteraction = null;
        this.indirectPointerPressInteraction = null;
        this.indirectPointerEventPressPosition = null;
        this.hoverInteraction = null;
        this.currentKeyPressInteractions.clear();
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final Function0<Unit> getOnClick() {
        return this.onClick;
    }

    public final boolean getShouldAutoInvalidate() {
        return this.shouldAutoInvalidate;
    }

    public final boolean getShouldMergeDescendantSemantics() {
        return true;
    }

    public Object getTraverseKey() {
        return this.traverseKey;
    }

    /* JADX INFO: renamed from: handlePressInteraction-d-4ec7I, reason: not valid java name */
    public final Object m286handlePressInteractiond4ec7I(PressGestureScope pressGestureScope, long j, Continuation<? super Unit> continuation) {
        Object objCoroutineScope;
        MutableInteractionSource mutableInteractionSource = this.interactionSource;
        return (mutableInteractionSource == null || (objCoroutineScope = CoroutineScopeKt.coroutineScope(new AbstractClickableNode$handlePressInteraction$2$1(pressGestureScope, j, mutableInteractionSource, this, null), continuation)) != IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? Unit.INSTANCE : objCoroutineScope;
    }

    public final void handlePressInteractionCancel(boolean indirectPointer) {
        final MutableInteractionSource mutableInteractionSource = this.interactionSource;
        if (mutableInteractionSource != null) {
            Job job = this.delayJob;
            if (job == null || !job.isActive()) {
                PressInteraction.Press press = indirectPointer ? this.indirectPointerPressInteraction : this.pressInteraction;
                if (press != null) {
                    final PressInteraction.Cancel cancel = new PressInteraction.Cancel(press);
                    Job job2 = getCoroutineScope().getCoroutineContext().get(Job.Key);
                    BuildersKt.launch$default(getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new AbstractClickableNode$handlePressInteractionCancel$1$1$1(mutableInteractionSource, cancel, job2 != null ? job2.invokeOnCompletion(new Function1() { // from class: kl
                        public final Object invoke(Object obj) {
                            return AbstractClickableNode.handlePressInteractionCancel$lambda$0$0$0(mutableInteractionSource, cancel, (Throwable) obj);
                        }
                    }) : null, null), 3, (Object) null);
                }
            } else {
                Job job3 = this.delayJob;
                if (job3 != null) {
                    Job.DefaultImpls.cancel$default(job3, (CancellationException) null, 1, (Object) null);
                }
            }
            if (indirectPointer) {
                this.indirectPointerPressInteraction = null;
            } else {
                this.pressInteraction = null;
            }
        }
    }

    /* JADX INFO: renamed from: handlePressInteractionRelease-3MmeM6k, reason: not valid java name */
    public final void m287handlePressInteractionRelease3MmeM6k(long offset, boolean indirectPointer) {
        MutableInteractionSource mutableInteractionSource = this.interactionSource;
        if (mutableInteractionSource != null) {
            Job job = this.delayJob;
            if (job == null || !job.isActive()) {
                PressInteraction.Press press = indirectPointer ? this.indirectPointerPressInteraction : this.pressInteraction;
                if (press != null) {
                    BuildersKt.launch$default(getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new AbstractClickableNode$handlePressInteractionRelease$1$2$1(press, mutableInteractionSource, null), 3, (Object) null);
                }
            } else {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                BuildersKt.launch$default(getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new AbstractClickableNode$handlePressInteractionRelease$1$1(job, offset, mutableInteractionSource, null), 3, (Object) null);
            }
            if (indirectPointer) {
                this.indirectPointerPressInteraction = null;
            } else {
                this.pressInteraction = null;
            }
        }
    }

    /* JADX INFO: renamed from: handlePressInteractionStart-3MmeM6k, reason: not valid java name */
    public final void m288handlePressInteractionStart3MmeM6k(long offset, boolean indirectPointer) {
        MutableInteractionSource mutableInteractionSource = this.interactionSource;
        if (mutableInteractionSource != null) {
            PressInteraction.Press press = new PressInteraction.Press(offset, null);
            if (delayPressInteraction()) {
                this.delayJob = BuildersKt.launch$default(getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new AbstractClickableNode$handlePressInteractionStart$1$1(mutableInteractionSource, press, indirectPointer, this, null), 3, (Object) null);
                return;
            }
            if (indirectPointer) {
                this.indirectPointerPressInteraction = press;
            } else {
                this.pressInteraction = press;
            }
            BuildersKt.launch$default(getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new AbstractClickableNode$handlePressInteractionStart$1$2(mutableInteractionSource, press, null), 3, (Object) null);
        }
    }

    public final void onAttach() {
        onObservedReadsChanged();
        if (!this.lazilyCreateIndication) {
            initializeIndicationAndInteractionSourceIfNeeded();
        }
        if (this.enabled) {
            delegate(this.focusableNode);
        }
    }

    public void onCancelIndirectPointerInput() {
        IndirectPointerClickDetector indirectPointerClickDetector = this.indirectPointerClickDetector;
        if (indirectPointerClickDetector != null) {
            indirectPointerClickDetector.resetDetector();
        }
    }

    public void onCancelKeyInput() {
    }

    public void onCancelPointerInput() {
        HoverInteraction.Enter enter;
        MutableInteractionSource mutableInteractionSource = this.interactionSource;
        if (mutableInteractionSource != null && (enter = this.hoverInteraction) != null) {
            mutableInteractionSource.tryEmit(new HoverInteraction.Exit(enter));
        }
        this.hoverInteraction = null;
        SuspendingPointerInputModifierNode suspendingPointerInputModifierNode = this.pointerInputNode;
        if (suspendingPointerInputModifierNode != null) {
            suspendingPointerInputModifierNode.onCancelPointerInput();
        }
    }

    /* JADX INFO: renamed from: onClickKeyDownEvent-ZmokQxo, reason: not valid java name */
    public abstract boolean mo289onClickKeyDownEventZmokQxo(KeyEvent event);

    /* JADX INFO: renamed from: onClickKeyUpEvent-ZmokQxo, reason: not valid java name */
    public abstract boolean mo290onClickKeyUpEventZmokQxo(KeyEvent event);

    public final void onDetach() {
        disposeInteractions();
        if (this.userProvidedInteractionSource == null) {
            this.interactionSource = null;
        }
        DelegatableNode delegatableNode = this.indicationNode;
        if (delegatableNode != null) {
            undelegate(delegatableNode);
        }
        this.indicationNode = null;
    }

    public void onIndirectPointerEvent(IndirectPointerEvent event, PointerEventPass pass) {
        initializeIndicationAndInteractionSourceIfNeeded();
        if (this.enabled) {
            if (this.indirectPointerClickDetector == null) {
                this.indirectPointerClickDetector = new IndirectPointerClickDetector(this);
            }
            IndirectPointerClickDetector indirectPointerClickDetector = this.indirectPointerClickDetector;
            if (indirectPointerClickDetector != null) {
                indirectPointerClickDetector.processRawEvent(event, pass, this.onClick);
            }
        }
    }

    /* JADX INFO: renamed from: onKeyEvent-ZmokQxo, reason: not valid java name */
    public final boolean m291onKeyEventZmokQxo(KeyEvent event) {
        boolean z;
        initializeIndicationAndInteractionSourceIfNeeded();
        long j = KeyEvent_androidKt.getKey-ZmokQxo(event);
        if (this.enabled && ClickableKt.m372isPressZmokQxo(event)) {
            if (this.currentKeyPressInteractions.containsKey(j)) {
                z = false;
            } else {
                PressInteraction.Press press = new PressInteraction.Press(this.centerOffset, null);
                this.currentKeyPressInteractions.set(j, press);
                if (this.interactionSource != null) {
                    BuildersKt.launch$default(getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new AbstractClickableNode$onKeyEvent$1(this, press, null), 3, (Object) null);
                }
                z = true;
            }
            return mo289onClickKeyDownEventZmokQxo(event) || z;
        }
        if (this.enabled && ClickableKt.m370isClickZmokQxo(event)) {
            PressInteraction.Press pressRemove = this.currentKeyPressInteractions.remove(j);
            if (pressRemove != null) {
                if (this.interactionSource != null) {
                    BuildersKt.launch$default(getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new AbstractClickableNode$onKeyEvent$2(this, pressRemove, null), 3, (Object) null);
                }
                mo290onClickKeyUpEventZmokQxo(event);
            }
            if (pressRemove != null) {
                return true;
            }
        }
        return false;
    }

    public void onObservedReadsChanged() {
        if (this.useLocalIndication) {
            ObserverModifierNodeKt.observeReads(this, new Function0() { // from class: jl
                public final Object invoke() {
                    return AbstractClickableNode.a(this.b);
                }
            });
        }
    }

    /* JADX INFO: renamed from: onPointerEvent-H0pRuoY, reason: not valid java name */
    public void mo292onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pass, long bounds) {
        SuspendingPointerInputModifierNode suspendingPointerInputModifierNodeCreatePointerInputNodeIfNeeded;
        long j = IntSizeKt.getCenter-ozmzZPI(bounds);
        this.centerOffset = Offset.constructor-impl((((long) Float.floatToRawIntBits(IntOffset.getX-impl(j))) << 32) | (((long) Float.floatToRawIntBits(IntOffset.getY-impl(j))) & 4294967295L));
        initializeIndicationAndInteractionSourceIfNeeded();
        if (this.enabled && pass == PointerEventPass.Main) {
            int i = pointerEvent.getType-7fucELk();
            PointerEventType.Companion companion = PointerEventType.Companion;
            if (PointerEventType.equals-impl0(i, companion.getEnter-7fucELk())) {
                BuildersKt.launch$default(getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new AbstractClickableNode$onPointerEvent$1(this, null), 3, (Object) null);
            } else if (PointerEventType.equals-impl0(i, companion.getExit-7fucELk())) {
                BuildersKt.launch$default(getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new AbstractClickableNode$onPointerEvent$2(this, null), 3, (Object) null);
            }
        }
        if (this.pointerInputNode == null && (suspendingPointerInputModifierNodeCreatePointerInputNodeIfNeeded = createPointerInputNodeIfNeeded()) != null) {
            this.pointerInputNode = delegate(suspendingPointerInputModifierNodeCreatePointerInputNodeIfNeeded);
        }
        SuspendingPointerInputModifierNode suspendingPointerInputModifierNode = this.pointerInputNode;
        if (suspendingPointerInputModifierNode != null) {
            suspendingPointerInputModifierNode.onPointerEvent-H0pRuoY(pointerEvent, pass, bounds);
        }
    }

    /* JADX INFO: renamed from: onPreKeyEvent-ZmokQxo, reason: not valid java name */
    public final boolean m293onPreKeyEventZmokQxo(KeyEvent event) {
        return false;
    }

    public final Unit resetPointerInputHandler() {
        SuspendingPointerInputModifierNode suspendingPointerInputModifierNode = this.pointerInputNode;
        if (suspendingPointerInputModifierNode == null) {
            return null;
        }
        suspendingPointerInputModifierNode.resetPointerInputHandler();
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:35:0x0072  */
    /* JADX INFO: renamed from: updateCommon-O2vRcR0, reason: not valid java name */
    public final void m294updateCommonO2vRcR0(MutableInteractionSource interactionSource, IndicationNodeFactory indicationNodeFactory, boolean useLocalIndication, boolean enabled, String onClickLabel, Role role, Function0<Unit> onClick) {
        boolean z;
        boolean z2;
        if (Intrinsics.areEqual(this.userProvidedInteractionSource, interactionSource)) {
            z = false;
        } else {
            disposeInteractions();
            this.userProvidedInteractionSource = interactionSource;
            this.interactionSource = interactionSource;
            z = true;
        }
        if (!Intrinsics.areEqual(this.indicationNodeFactory, indicationNodeFactory)) {
            this.indicationNodeFactory = indicationNodeFactory;
            z = true;
        }
        if (this.useLocalIndication != useLocalIndication) {
            this.useLocalIndication = useLocalIndication;
            if (useLocalIndication) {
                onObservedReadsChanged();
            }
            z = true;
        }
        if (this.enabled != enabled) {
            FocusableNode focusableNode = this.focusableNode;
            if (enabled) {
                delegate(focusableNode);
            } else {
                undelegate(focusableNode);
                disposeInteractions();
            }
            SemanticsModifierNodeKt.invalidateSemantics(this);
            this.enabled = enabled;
        }
        if (!Intrinsics.areEqual(this.onClickLabel, onClickLabel)) {
            this.onClickLabel = onClickLabel;
            SemanticsModifierNodeKt.invalidateSemantics(this);
        }
        if (!Intrinsics.areEqual(this.role, role)) {
            this.role = role;
            SemanticsModifierNodeKt.invalidateSemantics(this);
        }
        this.onClick = onClick;
        if (this.lazilyCreateIndication != shouldLazilyCreateIndication()) {
            boolean zShouldLazilyCreateIndication = shouldLazilyCreateIndication();
            this.lazilyCreateIndication = zShouldLazilyCreateIndication;
            z2 = (zShouldLazilyCreateIndication || this.indicationNode != null) ? z : true;
        }
        if (z2) {
            recreateIndicationIfNeeded();
        }
        this.focusableNode.update(this.interactionSource);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.AbstractClickableNode$TraverseKey, reason: from kotlin metadata */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/foundation/AbstractClickableNode$TraverseKey;", "", "<init>", "()V", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public /* synthetic */ AbstractClickableNode(MutableInteractionSource mutableInteractionSource, IndicationNodeFactory indicationNodeFactory, boolean z, boolean z2, String str, Role role, Function0 function0, DefaultConstructorMarker defaultConstructorMarker) {
        this(mutableInteractionSource, indicationNodeFactory, z, z2, str, role, function0);
    }
}
