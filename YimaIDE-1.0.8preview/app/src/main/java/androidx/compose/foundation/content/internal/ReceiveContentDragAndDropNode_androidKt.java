package androidx.compose.foundation.content.internal;

import android.view.DragEvent;
import androidx.compose.foundation.content.TransferableContent;
import androidx.compose.foundation.content.internal.ReceiveContentDragAndDropNode_androidKt;
import androidx.compose.ui.draganddrop.DragAndDropEvent;
import androidx.compose.ui.draganddrop.DragAndDropNodeKt;
import androidx.compose.ui.draganddrop.DragAndDropTarget;
import androidx.compose.ui.draganddrop.DragAndDropTargetModifierNode;
import androidx.compose.ui.draganddrop.DragAndDrop_androidKt;
import androidx.compose.ui.platform.AndroidClipboardManager_androidKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a$\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005H\u0000\u001a\f\u0010\b\u001a\u00020\t*\u00020\u0006H\u0000¨\u0006\n"}, d2 = {"ReceiveContentDragAndDropNode", "Landroidx/compose/ui/draganddrop/DragAndDropTargetModifierNode;", "receiveContentConfiguration", "Landroidx/compose/foundation/content/internal/ReceiveContentConfiguration;", "dragAndDropRequestPermission", "Lkotlin/Function1;", "Landroidx/compose/ui/draganddrop/DragAndDropEvent;", "", "toTransferableContent", "Landroidx/compose/foundation/content/TransferableContent;", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ReceiveContentDragAndDropNode_androidKt {
    public static final DragAndDropTargetModifierNode ReceiveContentDragAndDropNode(final ReceiveContentConfiguration receiveContentConfiguration, final Function1<? super DragAndDropEvent, Unit> function1) {
        return DragAndDropNodeKt.DragAndDropTargetModifierNode(new Function1() { // from class: l7c
            public final Object invoke(Object obj) {
                return Boolean.valueOf(ReceiveContentDragAndDropNode_androidKt.a((DragAndDropEvent) obj));
            }
        }, new DragAndDropTarget() { // from class: androidx.compose.foundation.content.internal.ReceiveContentDragAndDropNode_androidKt.ReceiveContentDragAndDropNode.2
            public boolean onDrop(DragAndDropEvent event) {
                function1.invoke(event);
                TransferableContent transferableContent = ReceiveContentDragAndDropNode_androidKt.toTransferableContent(event);
                return !Intrinsics.areEqual(transferableContent, receiveContentConfiguration.getReceiveContentListener().onReceive(transferableContent));
            }

            public void onEnded(DragAndDropEvent event) {
                receiveContentConfiguration.getReceiveContentListener().onDragEnd();
            }

            public void onEntered(DragAndDropEvent event) {
                receiveContentConfiguration.getReceiveContentListener().onDragEnter();
            }

            public void onExited(DragAndDropEvent event) {
                receiveContentConfiguration.getReceiveContentListener().onDragExit();
            }

            public void onStarted(DragAndDropEvent event) {
                receiveContentConfiguration.getReceiveContentListener().onDragStart();
            }
        });
    }

    public static boolean a(DragAndDropEvent dragAndDropEvent) {
        return true;
    }

    public static final TransferableContent toTransferableContent(DragAndDropEvent dragAndDropEvent) {
        DragEvent androidDragEvent = DragAndDrop_androidKt.toAndroidDragEvent(dragAndDropEvent);
        return new TransferableContent(AndroidClipboardManager_androidKt.toClipEntry(androidDragEvent.getClipData()), AndroidClipboardManager_androidKt.toClipMetadata(androidDragEvent.getClipDescription()), TransferableContent.Source.INSTANCE.m442getDragAndDropkB6V9T0(), null, 8, null);
    }
}
