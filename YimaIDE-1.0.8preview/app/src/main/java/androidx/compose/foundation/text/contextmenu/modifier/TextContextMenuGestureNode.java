package androidx.compose.foundation.text.contextmenu.modifier;

import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.text.contextmenu.data.TextContextMenuData;
import androidx.compose.foundation.text.contextmenu.gestures.RightClickGesturesKt;
import androidx.compose.foundation.text.contextmenu.provider.TextContextMenuDataProvider;
import androidx.compose.foundation.text.contextmenu.provider.TextContextMenuProvider;
import androidx.compose.foundation.text.contextmenu.provider.TextContextMenuProviderKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.GlobalPositionAwareModifierNode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0002\u0018\u0000  2\u00020\u00012\u00020\u00022\u00020\u0003:\u0002 !B<\u00123\u0010\u0004\u001a/\b\u0001\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f\u0018\u00010\u0005¢\u0006\u0004\b\r\u0010\u000eJ@\u0010\u0019\u001a\u00020\u000b23\u0010\u0004\u001a/\b\u0001\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f\u0018\u00010\u0005¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u0011H\u0016J\u0017\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u001e\u0010\u001fR=\u0010\u0004\u001a/\b\u0001\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000fR/\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u00118B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006\""}, d2 = {"Landroidx/compose/foundation/text/contextmenu/modifier/TextContextMenuGestureNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "Landroidx/compose/ui/node/GlobalPositionAwareModifierNode;", "onPreShowContextMenu", "Lkotlin/Function2;", "Landroidx/compose/ui/geometry/Offset;", "Lkotlin/ParameterName;", "name", "clickLocation", "Lkotlin/coroutines/Continuation;", "", "", "<init>", "(Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "<set-?>", "Landroidx/compose/ui/layout/LayoutCoordinates;", "localCoordinates", "getLocalCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "setLocalCoordinates", "(Landroidx/compose/ui/layout/LayoutCoordinates;)V", "localCoordinates$delegate", "Landroidx/compose/runtime/MutableState;", "update", "onGloballyPositioned", "coordinates", "tryShowContextMenu", "localClickOffset", "tryShowContextMenu-k-4lQ0M", "(J)V", "Companion", "ClickTextContextMenuDataProvider", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class TextContextMenuGestureNode extends DelegatingNode implements CompositionLocalConsumerModifierNode, GlobalPositionAwareModifierNode {
    private static final Companion Companion = new Companion(null);
    private static final String MESSAGE = "Tried to open context menu before the anchor was placed.";

    /* JADX INFO: renamed from: localCoordinates$delegate, reason: from kotlin metadata */
    private final MutableState localCoordinates = SnapshotStateKt.mutableStateOf((Object) null, SnapshotStateKt.neverEqualPolicy());
    private Function2<? super Offset, ? super Continuation<? super Unit>, ? extends Object> onPreShowContextMenu;

    public TextContextMenuGestureNode(Function2<? super Offset, ? super Continuation<? super Unit>, ? extends Object> function2) {
        this.onPreShowContextMenu = function2;
        delegate(SuspendingPointerInputFilterKt.SuspendingPointerInputModifierNode(new PointerInputEventHandler() { // from class: androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuGestureNode.1

            /* JADX INFO: renamed from: androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuGestureNode$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
            public static final /* synthetic */ class C00311 extends FunctionReferenceImpl implements Function1<Offset, Unit> {
                public C00311(Object obj) {
                    super(1, obj, TextContextMenuGestureNode.class, "tryShowContextMenu", "tryShowContextMenu-k-4lQ0M(J)V", 0);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    m1465invokek4lQ0M(((Offset) obj).unbox-impl());
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
                public final void m1465invokek4lQ0M(long j) {
                    ((TextContextMenuGestureNode) ((CallableReference) this).receiver).m1464tryShowContextMenuk4lQ0M(j);
                }
            }

            public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
                Object objOnRightClickDown = RightClickGesturesKt.onRightClickDown(pointerInputScope, new C00311(TextContextMenuGestureNode.this), continuation);
                return objOnRightClickDown == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objOnRightClickDown : Unit.INSTANCE;
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LayoutCoordinates getLocalCoordinates() {
        return (LayoutCoordinates) this.localCoordinates.getValue();
    }

    private final void setLocalCoordinates(LayoutCoordinates layoutCoordinates) {
        this.localCoordinates.setValue(layoutCoordinates);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: tryShowContextMenu-k-4lQ0M, reason: not valid java name */
    public final void m1464tryShowContextMenuk4lQ0M(long localClickOffset) {
        TextContextMenuProvider textContextMenuProvider = (TextContextMenuProvider) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, TextContextMenuProviderKt.getLocalTextContextMenuDropdownProvider());
        if (textContextMenuProvider == null) {
            return;
        }
        BuildersKt.launch$default(getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new TextContextMenuGestureNode$tryShowContextMenu$1(this, localClickOffset, textContextMenuProvider, new ClickTextContextMenuDataProvider(this, localClickOffset, null), null), 3, (Object) null);
    }

    public void onGloballyPositioned(LayoutCoordinates coordinates) {
        setLocalCoordinates(coordinates);
    }

    public final void update(Function2<? super Offset, ? super Continuation<? super Unit>, ? extends Object> onPreShowContextMenu) {
        this.onPreShowContextMenu = onPreShowContextMenu;
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/compose/foundation/text/contextmenu/modifier/TextContextMenuGestureNode$Companion;", "", "<init>", "()V", "MESSAGE", "", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\tH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u0010\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\u0010"}, d2 = {"Landroidx/compose/foundation/text/contextmenu/modifier/TextContextMenuGestureNode$ClickTextContextMenuDataProvider;", "Landroidx/compose/foundation/text/contextmenu/provider/TextContextMenuDataProvider;", "localClickOffset", "Landroidx/compose/ui/geometry/Offset;", "<init>", "(Landroidx/compose/foundation/text/contextmenu/modifier/TextContextMenuGestureNode;JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "J", "position", "destinationCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "position-tuRUvjQ", "(Landroidx/compose/ui/layout/LayoutCoordinates;)J", "contentBounds", "Landroidx/compose/ui/geometry/Rect;", "data", "Landroidx/compose/foundation/text/contextmenu/data/TextContextMenuData;", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public final class ClickTextContextMenuDataProvider implements TextContextMenuDataProvider {
        private final long localClickOffset;

        private ClickTextContextMenuDataProvider(long j) {
            this.localClickOffset = j;
        }

        @Override // androidx.compose.foundation.text.contextmenu.provider.TextContextMenuDataProvider
        public Rect contentBounds(LayoutCoordinates destinationCoordinates) {
            return RectKt.Rect-tz77jQw(mo1466positiontuRUvjQ(destinationCoordinates), Size.Companion.getZero-NH-jbRc());
        }

        @Override // androidx.compose.foundation.text.contextmenu.provider.TextContextMenuDataProvider
        public TextContextMenuData data() {
            return TextContextMenuModifierKt.collectTextContextMenuData(TextContextMenuGestureNode.this);
        }

        @Override // androidx.compose.foundation.text.contextmenu.provider.TextContextMenuDataProvider
        /* JADX INFO: renamed from: position-tuRUvjQ, reason: not valid java name */
        public long mo1466positiontuRUvjQ(LayoutCoordinates destinationCoordinates) {
            LayoutCoordinates localCoordinates = TextContextMenuGestureNode.this.getLocalCoordinates();
            if (localCoordinates != null) {
                return destinationCoordinates.localPositionOf-R5De75A(localCoordinates, this.localClickOffset);
            }
            InlineClassHelperKt.throwIllegalStateExceptionForNullCheck(TextContextMenuGestureNode.MESSAGE);
            wq6.a();
            return 0L;
        }

        public /* synthetic */ ClickTextContextMenuDataProvider(TextContextMenuGestureNode textContextMenuGestureNode, long j, DefaultConstructorMarker defaultConstructorMarker) {
            this(j);
        }
    }
}
