package androidx.compose.foundation.text.input.internal;

import android.content.ClipDescription;
import android.view.DragEvent;
import androidx.compose.foundation.content.MediaType;
import androidx.compose.foundation.text.input.internal.TextFieldDragAndDropNode_androidKt;
import androidx.compose.ui.draganddrop.DragAndDropEvent;
import androidx.compose.ui.draganddrop.DragAndDropNodeKt;
import androidx.compose.ui.draganddrop.DragAndDropTarget;
import androidx.compose.ui.draganddrop.DragAndDropTargetModifierNode;
import androidx.compose.ui.draganddrop.DragAndDrop_androidKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.platform.AndroidClipboardManager_androidKt;
import androidx.compose.ui.platform.ClipEntry;
import androidx.compose.ui.platform.ClipMetadata;
import java.util.Collection;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aÒ\u0002\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u000326\u0010\u0006\u001a2\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\f¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\u00072\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u00102%\b\u0002\u0010\u0013\u001a\u001f\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00102%\b\u0002\u0010\u0015\u001a\u001f\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00102%\b\u0002\u0010\u0016\u001a\u001f\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00102%\b\u0002\u0010\u0019\u001a\u001f\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00102%\b\u0002\u0010\u001a\u001a\u001f\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00102%\b\u0002\u0010\u001b\u001a\u001f\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0010H\u0000¨\u0006\u001c"}, d2 = {"textFieldDragAndDropNode", "Landroidx/compose/ui/draganddrop/DragAndDropTargetModifierNode;", "hintMediaTypes", "Lkotlin/Function0;", "", "Landroidx/compose/foundation/content/MediaType;", "onDrop", "Lkotlin/Function2;", "Landroidx/compose/ui/platform/ClipEntry;", "Lkotlin/ParameterName;", "name", "clipEntry", "Landroidx/compose/ui/platform/ClipMetadata;", "clipMetadata", "", "dragAndDropRequestPermission", "Lkotlin/Function1;", "Landroidx/compose/ui/draganddrop/DragAndDropEvent;", "", "onStarted", "event", "onEntered", "onMoved", "Landroidx/compose/ui/geometry/Offset;", "position", "onChanged", "onExited", "onEnded", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class TextFieldDragAndDropNode_androidKt {
    public static boolean a(Function0 function0, DragAndDropEvent dragAndDropEvent) {
        ClipDescription clipDescription = DragAndDrop_androidKt.toAndroidDragEvent(dragAndDropEvent).getClipDescription();
        Iterable<MediaType> iterable = (Iterable) function0.invoke();
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return false;
        }
        for (MediaType mediaType : iterable) {
            if (Intrinsics.areEqual(mediaType, MediaType.INSTANCE.getAll()) || clipDescription.hasMimeType(mediaType.getRepresentation())) {
                return true;
            }
        }
        return false;
    }

    public static final DragAndDropTargetModifierNode textFieldDragAndDropNode(final Function0<? extends Set<MediaType>> function0, final Function2<? super ClipEntry, ? super ClipMetadata, Boolean> function2, final Function1<? super DragAndDropEvent, Unit> function1, final Function1<? super DragAndDropEvent, Unit> function3, final Function1<? super DragAndDropEvent, Unit> function4, final Function1<? super Offset, Unit> function5, final Function1<? super DragAndDropEvent, Unit> function6, final Function1<? super DragAndDropEvent, Unit> function7, final Function1<? super DragAndDropEvent, Unit> function8) {
        return DragAndDropNodeKt.DragAndDropTargetModifierNode(new Function1() { // from class: u6e
            public final Object invoke(Object obj) {
                return Boolean.valueOf(TextFieldDragAndDropNode_androidKt.a(function0, (DragAndDropEvent) obj));
            }
        }, new DragAndDropTarget() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDragAndDropNode_androidKt.textFieldDragAndDropNode.2
            public void onChanged(DragAndDropEvent event) {
                Function1<DragAndDropEvent, Unit> function9 = function6;
                if (function9 != null) {
                    function9.invoke(event);
                }
            }

            public boolean onDrop(DragAndDropEvent event) {
                function1.invoke(event);
                return ((Boolean) function2.invoke(AndroidClipboardManager_androidKt.toClipEntry(DragAndDrop_androidKt.toAndroidDragEvent(event).getClipData()), AndroidClipboardManager_androidKt.toClipMetadata(DragAndDrop_androidKt.toAndroidDragEvent(event).getClipDescription()))).booleanValue();
            }

            public void onEnded(DragAndDropEvent event) {
                Function1<DragAndDropEvent, Unit> function9 = function8;
                if (function9 != null) {
                    function9.invoke(event);
                }
            }

            public void onEntered(DragAndDropEvent event) {
                Function1<DragAndDropEvent, Unit> function9 = function4;
                if (function9 != null) {
                    function9.invoke(event);
                }
            }

            public void onExited(DragAndDropEvent event) {
                Function1<DragAndDropEvent, Unit> function9 = function7;
                if (function9 != null) {
                    function9.invoke(event);
                }
            }

            public void onMoved(DragAndDropEvent event) {
                DragEvent androidDragEvent = DragAndDrop_androidKt.toAndroidDragEvent(event);
                Function1<Offset, Unit> function9 = function5;
                if (function9 != null) {
                    float x = androidDragEvent.getX();
                    float y = androidDragEvent.getY();
                    function9.invoke(Offset.box-impl(Offset.constructor-impl((((long) Float.floatToRawIntBits(x)) << 32) | (((long) Float.floatToRawIntBits(y)) & 4294967295L))));
                }
            }

            public void onStarted(DragAndDropEvent event) {
                Function1<DragAndDropEvent, Unit> function9 = function3;
                if (function9 != null) {
                    function9.invoke(event);
                }
            }
        });
    }

    public static /* synthetic */ DragAndDropTargetModifierNode textFieldDragAndDropNode$default(Function0 function0, Function2 function2, Function1 function1, Function1 function3, Function1 function4, Function1 function5, Function1 function6, Function1 function7, Function1 function8, int i, Object obj) {
        if ((i & 8) != 0) {
            function3 = null;
        }
        if ((i & 16) != 0) {
            function4 = null;
        }
        if ((i & 32) != 0) {
            function5 = null;
        }
        if ((i & 64) != 0) {
            function6 = null;
        }
        if ((i & 128) != 0) {
            function7 = null;
        }
        if ((i & 256) != 0) {
            function8 = null;
        }
        return textFieldDragAndDropNode(function0, function2, function1, function3, function4, function5, function6, function7, function8);
    }
}
