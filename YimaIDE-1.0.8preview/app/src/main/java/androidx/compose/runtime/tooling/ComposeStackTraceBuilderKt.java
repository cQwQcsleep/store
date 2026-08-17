package androidx.compose.runtime.tooling;

import androidx.compose.runtime.Anchor;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.RememberObserver;
import androidx.compose.runtime.RememberObserverHolder;
import androidx.compose.runtime.SlotReader;
import androidx.compose.runtime.SlotTable;
import androidx.compose.runtime.SlotWriter;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a9\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0000¢\u0006\u0002\u0010\t\u001a\u0012\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\nH\u0000\u001a$\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\n2\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0000\u001a3\u0010\f\u001a\u0004\u0018\u00010\r*\u00020\u000e2#\u0010\u000f\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0005¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00140\u0010H\u0000\u001a\u001b\u0010\u0015\u001a\u0004\u0018\u00010\u0007*\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0017H\u0000¢\u0006\u0002\u0010\u0018¨\u0006\u0019"}, d2 = {"buildTrace", "", "Landroidx/compose/runtime/tooling/ComposeStackTraceFrame;", "Landroidx/compose/runtime/SlotWriter;", "child", "", "group", "", "parent", "(Landroidx/compose/runtime/SlotWriter;Ljava/lang/Object;ILjava/lang/Integer;)Ljava/util/List;", "Landroidx/compose/runtime/SlotReader;", "traceForGroup", "findLocation", "Landroidx/compose/runtime/tooling/ObjectLocation;", "Landroidx/compose/runtime/SlotTable;", "filter", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "value", "", "findSubcompositionContextGroup", "context", "Landroidx/compose/runtime/CompositionContext;", "(Landroidx/compose/runtime/SlotTable;Landroidx/compose/runtime/CompositionContext;)Ljava/lang/Integer;", "runtime"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ComposeStackTraceBuilderKt {
    public static final List<ComposeStackTraceFrame> buildTrace(SlotWriter slotWriter, Object obj, int i, Integer num) {
        int iParent;
        int iGroupKey;
        if (slotWriter.getClosed() || slotWriter.getSize$runtime() == 0) {
            return CollectionsKt.emptyList();
        }
        WriterTraceBuilder writerTraceBuilder = new WriterTraceBuilder(slotWriter);
        if (num != null) {
            iParent = num.intValue();
        } else {
            iParent = slotWriter.getParent() < 0 ? slotWriter.parent(i) : slotWriter.getParent();
        }
        if (obj == null) {
            obj = Integer.valueOf(slotWriter.groupSlotIndex(i));
        }
        if (slotWriter.isValid(i)) {
            iGroupKey = slotWriter.groupKey(i);
        } else {
            int iParent2 = iParent >= 0 ? slotWriter.parent(iParent) : iParent;
            iGroupKey = slotWriter.groupKey(iParent);
            int i2 = iParent;
            iParent = iParent2;
            i = i2;
        }
        while (i >= 0) {
            writerTraceBuilder.processEdge(iGroupKey, slotWriter.hasObjectKey(i) ? slotWriter.groupObjectKey(i) : Composer.INSTANCE.getEmpty(), slotWriter.sourceInformationOf$runtime(i), obj);
            obj = slotWriter.anchor(i);
            if (iParent >= 0) {
                int iParent3 = slotWriter.parent(iParent);
                iGroupKey = slotWriter.groupKey(iParent);
                int i3 = iParent;
                iParent = iParent3;
                i = i3;
            } else {
                i = iParent;
            }
        }
        return writerTraceBuilder.trace();
    }

    public static /* synthetic */ List buildTrace$default(SlotWriter slotWriter, Object obj, int i, Integer num, int i2, Object obj2) {
        if ((i2 & 1) != 0) {
            obj = null;
        }
        if ((i2 & 2) != 0) {
            i = slotWriter.getCurrentGroup();
        }
        if ((i2 & 4) != 0) {
            num = null;
        }
        return buildTrace(slotWriter, obj, i, num);
    }

    public static final ObjectLocation findLocation(SlotTable slotTable, Function1<Object, Boolean> function1) {
        SlotReader slotReaderOpenReader = slotTable.openReader();
        for (int i = 0; i < slotTable.getGroupsSize(); i++) {
            try {
                if (slotReaderOpenReader.isNode(i) && ((Boolean) function1.invoke(slotReaderOpenReader.node(i))).booleanValue()) {
                    ObjectLocation objectLocation = new ObjectLocation(i, null);
                    slotReaderOpenReader.close();
                    return objectLocation;
                }
                int iSlotSize = slotReaderOpenReader.slotSize(i);
                for (int i2 = 0; i2 < iSlotSize; i2++) {
                    if (((Boolean) function1.invoke(slotReaderOpenReader.groupGet(i, i2))).booleanValue()) {
                        ObjectLocation objectLocation2 = new ObjectLocation(i, Integer.valueOf(i2));
                        slotReaderOpenReader.close();
                        return objectLocation2;
                    }
                }
            } catch (Throwable th) {
                slotReaderOpenReader.close();
                throw th;
            }
        }
        Unit unit = Unit.INSTANCE;
        slotReaderOpenReader.close();
        return null;
    }

    public static final Integer findSubcompositionContextGroup(SlotTable slotTable, CompositionContext compositionContext) {
        SlotReader slotReaderOpenReader = slotTable.openReader();
        try {
            return findSubcompositionContextGroup$lambda$0$scanGroup(slotReaderOpenReader, compositionContext, 0, slotReaderOpenReader.getGroupsSize());
        } finally {
            slotReaderOpenReader.close();
        }
    }

    private static final Integer findSubcompositionContextGroup$lambda$0$scanGroup(SlotReader slotReader, CompositionContext compositionContext, int i, int i2) {
        Integer numFindSubcompositionContextGroup$lambda$0$scanGroup;
        while (true) {
            if (i >= i2) {
                return null;
            }
            int iGroupSize = slotReader.groupSize(i) + i;
            if (slotReader.hasMark(i) && slotReader.groupKey(i) == 206 && Intrinsics.areEqual(slotReader.groupObjectKey(i), ComposerKt.getReference())) {
                Object objGroupGet = slotReader.groupGet(i, 0);
                RememberObserverHolder rememberObserverHolder = objGroupGet instanceof RememberObserverHolder ? (RememberObserverHolder) objGroupGet : null;
                RememberObserver wrapped = rememberObserverHolder != null ? rememberObserverHolder.getWrapped() : null;
                ComposerImpl.CompositionContextHolder compositionContextHolder = wrapped instanceof ComposerImpl.CompositionContextHolder ? (ComposerImpl.CompositionContextHolder) wrapped : null;
                if (compositionContextHolder != null && Intrinsics.areEqual(compositionContextHolder.getRef(), compositionContext)) {
                    return Integer.valueOf(i);
                }
            }
            if (slotReader.containsMark(i) && (numFindSubcompositionContextGroup$lambda$0$scanGroup = findSubcompositionContextGroup$lambda$0$scanGroup(slotReader, compositionContext, i + 1, iGroupSize)) != null) {
                return Integer.valueOf(numFindSubcompositionContextGroup$lambda$0$scanGroup.intValue());
            }
            i = iGroupSize;
        }
    }

    public static final List<ComposeStackTraceFrame> traceForGroup(SlotReader slotReader, int i, Object obj) {
        ReaderTraceBuilder readerTraceBuilder = new ReaderTraceBuilder(slotReader);
        i = slotReader.parent(i);
        Anchor anchor = slotReader.anchor(i);
        while (i >= 0) {
            readerTraceBuilder.processEdge(slotReader.groupKey(i), slotReader.hasObjectKey(i) ? slotReader.groupObjectKey(i) : Composer.INSTANCE.getEmpty(), slotReader.getTable().sourceInformationOf(i), obj);
            if (i >= 0) {
                Anchor anchor2 = anchor;
                anchor = slotReader.anchor(i);
                i = slotReader.parent(i);
                obj = anchor2;
            } else {
                obj = anchor;
            }
        }
        return readerTraceBuilder.trace();
    }

    public static final List<ComposeStackTraceFrame> buildTrace(SlotReader slotReader) {
        Object empty;
        if (!slotReader.getClosed() && slotReader.getGroupsSize() != 0) {
            ReaderTraceBuilder readerTraceBuilder = new ReaderTraceBuilder(slotReader);
            int parent = slotReader.getParent();
            Object objValueOf = Integer.valueOf(slotReader.getSlot());
            while (parent >= 0) {
                if (slotReader.hasObjectKey(parent)) {
                    empty = slotReader.groupObjectKey(parent);
                } else {
                    empty = Composer.INSTANCE.getEmpty();
                }
                readerTraceBuilder.processEdge(slotReader.groupKey(parent), empty, slotReader.getTable().sourceInformationOf(parent), objValueOf);
                objValueOf = slotReader.anchor(parent);
                parent = slotReader.parent(parent);
            }
            return readerTraceBuilder.trace();
        }
        return CollectionsKt.emptyList();
    }
}
