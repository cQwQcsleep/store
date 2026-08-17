package androidx.compose.foundation.text.selection;

import androidx.collection.LongObjectMap;
import androidx.collection.LongObjectMapKt;
import androidx.collection.MutableLongObjectMap;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.text.selection.Selectable;
import androidx.compose.foundation.text.selection.SelectionRegistrarImpl;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.layout.LayoutCoordinates;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b \b\u0001\u0018\u0000 f2\u00020\u0001:\u0001fB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\t\b\u0016¢\u0006\u0004\b\u0004\u0010\u0006J\u0010\u0010N\u001a\u00020\u000f2\u0006\u0010O\u001a\u00020\u000fH\u0016J\u0010\u0010P\u001a\u00020 2\u0006\u0010O\u001a\u00020\u000fH\u0016J\b\u0010Q\u001a\u00020\u0003H\u0016J\u0014\u0010R\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00112\u0006\u0010S\u001a\u00020'J\u0010\u0010T\u001a\u00020 2\u0006\u0010U\u001a\u00020\u0003H\u0016J/\u0010V\u001a\u00020 2\u0006\u0010W\u001a\u00020'2\u0006\u0010X\u001a\u00020(2\u0006\u0010Y\u001a\u00020)2\u0006\u0010Z\u001a\u00020\bH\u0016¢\u0006\u0004\b[\u0010\\J\u0018\u0010]\u001a\u00020 2\u0006\u0010U\u001a\u00020\u00032\u0006\u0010Z\u001a\u00020\bH\u0016J?\u0010^\u001a\u00020\b2\u0006\u0010W\u001a\u00020'2\u0006\u0010_\u001a\u00020(2\u0006\u0010`\u001a\u00020(2\u0006\u0010a\u001a\u00020\b2\u0006\u0010Y\u001a\u00020)2\u0006\u0010Z\u001a\u00020\bH\u0016¢\u0006\u0004\bb\u0010cJ\b\u0010d\u001a\u00020 H\u0016J\u0010\u0010e\u001a\u00020 2\u0006\u0010U\u001a\u00020\u0003H\u0016R\u001a\u0010\u0007\u001a\u00020\bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00118@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00178@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00060\u001bj\u0002`\u001cX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001dR(\u0010\u001e\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020 \u0018\u00010\u001fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R:\u0010%\u001a\"\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020 \u0018\u00010&X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R.\u0010.\u001a\u0016\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020 \u0018\u00010/X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103RF\u00104\u001a.\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\b\u0018\u000105X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\"\u0010:\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010;X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R(\u0010@\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020 \u0018\u00010\u001fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010\"\"\u0004\bB\u0010$R(\u0010C\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020 \u0018\u00010\u001fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010\"\"\u0004\bE\u0010$R7\u0010H\u001a\b\u0012\u0004\u0012\u00020G0\u00172\f\u0010F\u001a\b\u0012\u0004\u0012\u00020G0\u00178V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\bL\u0010M\u001a\u0004\bI\u0010\u0019\"\u0004\bJ\u0010K¨\u0006g"}, d2 = {"Landroidx/compose/foundation/text/selection/SelectionRegistrarImpl;", "Landroidx/compose/foundation/text/selection/SelectionRegistrar;", "initialIncrementId", "", "<init>", "(J)V", "()V", "sorted", "", "getSorted$foundation", "()Z", "setSorted$foundation", "(Z)V", "_selectables", "", "Landroidx/compose/foundation/text/selection/Selectable;", "selectables", "", "getSelectables$foundation", "()Ljava/util/List;", "_selectableMap", "Landroidx/collection/MutableLongObjectMap;", "selectableMap", "Landroidx/collection/LongObjectMap;", "getSelectableMap$foundation", "()Landroidx/collection/LongObjectMap;", "incrementId", "Ljava/util/concurrent/atomic/AtomicLong;", "Landroidx/compose/foundation/AtomicLong;", "Ljava/util/concurrent/atomic/AtomicLong;", "onPositionChangeCallback", "Lkotlin/Function1;", "", "getOnPositionChangeCallback$foundation", "()Lkotlin/jvm/functions/Function1;", "setOnPositionChangeCallback$foundation", "(Lkotlin/jvm/functions/Function1;)V", "onSelectionUpdateStartCallback", "Lkotlin/Function4;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "Landroidx/compose/ui/geometry/Offset;", "Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "getOnSelectionUpdateStartCallback$foundation", "()Lkotlin/jvm/functions/Function4;", "setOnSelectionUpdateStartCallback$foundation", "(Lkotlin/jvm/functions/Function4;)V", "onSelectionUpdateSelectAll", "Lkotlin/Function2;", "getOnSelectionUpdateSelectAll$foundation", "()Lkotlin/jvm/functions/Function2;", "setOnSelectionUpdateSelectAll$foundation", "(Lkotlin/jvm/functions/Function2;)V", "onSelectionUpdateCallback", "Lkotlin/Function6;", "getOnSelectionUpdateCallback$foundation", "()Lkotlin/jvm/functions/Function6;", "setOnSelectionUpdateCallback$foundation", "(Lkotlin/jvm/functions/Function6;)V", "onSelectionUpdateEndCallback", "Lkotlin/Function0;", "getOnSelectionUpdateEndCallback$foundation", "()Lkotlin/jvm/functions/Function0;", "setOnSelectionUpdateEndCallback$foundation", "(Lkotlin/jvm/functions/Function0;)V", "onSelectableChangeCallback", "getOnSelectableChangeCallback$foundation", "setOnSelectableChangeCallback$foundation", "afterSelectableUnsubscribe", "getAfterSelectableUnsubscribe$foundation", "setAfterSelectableUnsubscribe$foundation", "<set-?>", "Landroidx/compose/foundation/text/selection/Selection;", "subselections", "getSubselections", "setSubselections", "(Landroidx/collection/LongObjectMap;)V", "subselections$delegate", "Landroidx/compose/runtime/MutableState;", "subscribe", "selectable", "unsubscribe", "nextSelectableId", "sort", "containerLayoutCoordinates", "notifyPositionChange", "selectableId", "notifySelectionUpdateStart", "layoutCoordinates", "startPosition", "adjustment", "isInTouchMode", "notifySelectionUpdateStart-ubNVwUQ", "(Landroidx/compose/ui/layout/LayoutCoordinates;JLandroidx/compose/foundation/text/selection/SelectionAdjustment;Z)V", "notifySelectionUpdateSelectAll", "notifySelectionUpdate", "newPosition", "previousPosition", "isStartHandle", "notifySelectionUpdate-njBpvok", "(Landroidx/compose/ui/layout/LayoutCoordinates;JJZLandroidx/compose/foundation/text/selection/SelectionAdjustment;Z)Z", "notifySelectionUpdateEnd", "notifySelectableChange", "Companion", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SelectionRegistrarImpl implements SelectionRegistrar {
    private final MutableLongObjectMap<Selectable> _selectableMap;
    private final List<Selectable> _selectables;
    private Function1<? super Long, Unit> afterSelectableUnsubscribe;
    private AtomicLong incrementId;
    private Function1<? super Long, Unit> onPositionChangeCallback;
    private Function1<? super Long, Unit> onSelectableChangeCallback;
    private Function6<? super Boolean, ? super LayoutCoordinates, ? super Offset, ? super Offset, ? super Boolean, ? super SelectionAdjustment, Boolean> onSelectionUpdateCallback;
    private Function0<Unit> onSelectionUpdateEndCallback;
    private Function2<? super Boolean, ? super Long, Unit> onSelectionUpdateSelectAll;
    private Function4<? super Boolean, ? super LayoutCoordinates, ? super Offset, ? super SelectionAdjustment, Unit> onSelectionUpdateStartCallback;
    private boolean sorted;

    /* JADX INFO: renamed from: subselections$delegate, reason: from kotlin metadata */
    private final MutableState subselections;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final Saver<SelectionRegistrarImpl, Long> Saver = SaverKt.Saver(new Function2() { // from class: c6d
        public final Object invoke(Object obj, Object obj2) {
            return SelectionRegistrarImpl.d((SaverScope) obj, (SelectionRegistrarImpl) obj2);
        }
    }, new Function1() { // from class: d6d
        public final Object invoke(Object obj) {
            return SelectionRegistrarImpl.c(((Long) obj).longValue());
        }
    });

    private SelectionRegistrarImpl(long j) {
        this._selectables = new ArrayList();
        this._selectableMap = LongObjectMapKt.mutableLongObjectMapOf();
        this.incrementId = new AtomicLong(j);
        this.subselections = SnapshotStateKt.mutableStateOf$default(LongObjectMapKt.emptyLongObjectMap(), (SnapshotMutationPolicy) null, 2, (Object) null);
    }

    public static int a(LayoutCoordinates layoutCoordinates, Selectable selectable, Selectable selectable2) {
        long j;
        long j2;
        long j3;
        long j4;
        LayoutCoordinates layoutCoordinates2 = selectable.getLayoutCoordinates();
        LayoutCoordinates layoutCoordinates3 = selectable2.getLayoutCoordinates();
        if (layoutCoordinates2 != null) {
            j = layoutCoordinates.localPositionOf-R5De75A(layoutCoordinates2, Offset.Companion.getZero-F1C5BW0());
            j2 = Offset.constructor-impl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (j & 4294967295L)) + ((int) (layoutCoordinates2.getSize-YbymL2g() & 4294967295L)))) & 4294967295L) | (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (j >> 32)) + ((int) (layoutCoordinates2.getSize-YbymL2g() >> 32)))) << 32));
        } else {
            Offset.Companion companion = Offset.Companion;
            j = companion.getZero-F1C5BW0();
            j2 = companion.getZero-F1C5BW0();
        }
        long j5 = j;
        long j6 = j2;
        if (layoutCoordinates3 != null) {
            long j7 = layoutCoordinates.localPositionOf-R5De75A(layoutCoordinates3, Offset.Companion.getZero-F1C5BW0());
            j3 = Offset.constructor-impl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (j7 & 4294967295L)) + ((int) (layoutCoordinates3.getSize-YbymL2g() & 4294967295L)))) & 4294967295L) | (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (j7 >> 32)) + ((int) (layoutCoordinates3.getSize-YbymL2g() >> 32)))) << 32));
            j4 = j7;
        } else {
            Offset.Companion companion2 = Offset.Companion;
            long j8 = companion2.getZero-F1C5BW0();
            j3 = companion2.getZero-F1C5BW0();
            j4 = j8;
        }
        long j9 = j4;
        return SelectionRegistrarImplKt.m1801inARowzwwh4xc(j5, j6, j4, j3) ? ComparisonsKt.compareValues(Float.valueOf(Float.intBitsToFloat((int) (j5 >> 32))), Float.valueOf(Float.intBitsToFloat((int) (j9 >> 32)))) : ComparisonsKt.compareValues(Float.valueOf(Float.intBitsToFloat((int) (j5 & 4294967295L))), Float.valueOf(Float.intBitsToFloat((int) (j9 & 4294967295L))));
    }

    public static int b(Function2 function2, Object obj, Object obj2) {
        return ((Number) function2.invoke(obj, obj2)).intValue();
    }

    public static SelectionRegistrarImpl c(long j) {
        return new SelectionRegistrarImpl(j);
    }

    public static Long d(SaverScope saverScope, SelectionRegistrarImpl selectionRegistrarImpl) {
        return Long.valueOf(selectionRegistrarImpl.incrementId.get());
    }

    public final Function1<Long, Unit> getAfterSelectableUnsubscribe$foundation() {
        return this.afterSelectableUnsubscribe;
    }

    public final Function1<Long, Unit> getOnPositionChangeCallback$foundation() {
        return this.onPositionChangeCallback;
    }

    public final Function1<Long, Unit> getOnSelectableChangeCallback$foundation() {
        return this.onSelectableChangeCallback;
    }

    public final Function6<Boolean, LayoutCoordinates, Offset, Offset, Boolean, SelectionAdjustment, Boolean> getOnSelectionUpdateCallback$foundation() {
        return this.onSelectionUpdateCallback;
    }

    public final Function0<Unit> getOnSelectionUpdateEndCallback$foundation() {
        return this.onSelectionUpdateEndCallback;
    }

    public final Function2<Boolean, Long, Unit> getOnSelectionUpdateSelectAll$foundation() {
        return this.onSelectionUpdateSelectAll;
    }

    public final Function4<Boolean, LayoutCoordinates, Offset, SelectionAdjustment, Unit> getOnSelectionUpdateStartCallback$foundation() {
        return this.onSelectionUpdateStartCallback;
    }

    public final LongObjectMap<Selectable> getSelectableMap$foundation() {
        return this._selectableMap;
    }

    public final List<Selectable> getSelectables$foundation() {
        return this._selectables;
    }

    /* JADX INFO: renamed from: getSorted$foundation, reason: from getter */
    public final boolean getSorted() {
        return this.sorted;
    }

    @Override // androidx.compose.foundation.text.selection.SelectionRegistrar
    public LongObjectMap<Selection> getSubselections() {
        return (LongObjectMap) this.subselections.getValue();
    }

    @Override // androidx.compose.foundation.text.selection.SelectionRegistrar
    public long nextSelectableId() {
        long andIncrement = this.incrementId.getAndIncrement();
        while (andIncrement == 0) {
            andIncrement = this.incrementId.getAndIncrement();
        }
        return andIncrement;
    }

    @Override // androidx.compose.foundation.text.selection.SelectionRegistrar
    public void notifyPositionChange(long selectableId) {
        this.sorted = false;
        Function1<? super Long, Unit> function1 = this.onPositionChangeCallback;
        if (function1 != null) {
            function1.invoke(Long.valueOf(selectableId));
        }
    }

    @Override // androidx.compose.foundation.text.selection.SelectionRegistrar
    public void notifySelectableChange(long selectableId) {
        Function1<? super Long, Unit> function1 = this.onSelectableChangeCallback;
        if (function1 != null) {
            function1.invoke(Long.valueOf(selectableId));
        }
    }

    @Override // androidx.compose.foundation.text.selection.SelectionRegistrar
    /* JADX INFO: renamed from: notifySelectionUpdate-njBpvok */
    public boolean mo1799notifySelectionUpdatenjBpvok(LayoutCoordinates layoutCoordinates, long newPosition, long previousPosition, boolean isStartHandle, SelectionAdjustment adjustment, boolean isInTouchMode) {
        Function6<? super Boolean, ? super LayoutCoordinates, ? super Offset, ? super Offset, ? super Boolean, ? super SelectionAdjustment, Boolean> function6 = this.onSelectionUpdateCallback;
        if (function6 != null) {
            return ((Boolean) function6.invoke(Boolean.valueOf(isInTouchMode), layoutCoordinates, Offset.box-impl(newPosition), Offset.box-impl(previousPosition), Boolean.valueOf(isStartHandle), adjustment)).booleanValue();
        }
        return true;
    }

    @Override // androidx.compose.foundation.text.selection.SelectionRegistrar
    public void notifySelectionUpdateEnd() {
        Function0<Unit> function0 = this.onSelectionUpdateEndCallback;
        if (function0 != null) {
            function0.invoke();
        }
    }

    @Override // androidx.compose.foundation.text.selection.SelectionRegistrar
    public void notifySelectionUpdateSelectAll(long selectableId, boolean isInTouchMode) {
        Function2<? super Boolean, ? super Long, Unit> function2 = this.onSelectionUpdateSelectAll;
        if (function2 != null) {
            function2.invoke(Boolean.valueOf(isInTouchMode), Long.valueOf(selectableId));
        }
    }

    @Override // androidx.compose.foundation.text.selection.SelectionRegistrar
    /* JADX INFO: renamed from: notifySelectionUpdateStart-ubNVwUQ */
    public void mo1800notifySelectionUpdateStartubNVwUQ(LayoutCoordinates layoutCoordinates, long startPosition, SelectionAdjustment adjustment, boolean isInTouchMode) {
        Function4<? super Boolean, ? super LayoutCoordinates, ? super Offset, ? super SelectionAdjustment, Unit> function4 = this.onSelectionUpdateStartCallback;
        if (function4 != null) {
            function4.invoke(Boolean.valueOf(isInTouchMode), layoutCoordinates, Offset.box-impl(startPosition), adjustment);
        }
    }

    public final void setAfterSelectableUnsubscribe$foundation(Function1<? super Long, Unit> function1) {
        this.afterSelectableUnsubscribe = function1;
    }

    public final void setOnPositionChangeCallback$foundation(Function1<? super Long, Unit> function1) {
        this.onPositionChangeCallback = function1;
    }

    public final void setOnSelectableChangeCallback$foundation(Function1<? super Long, Unit> function1) {
        this.onSelectableChangeCallback = function1;
    }

    public final void setOnSelectionUpdateCallback$foundation(Function6<? super Boolean, ? super LayoutCoordinates, ? super Offset, ? super Offset, ? super Boolean, ? super SelectionAdjustment, Boolean> function6) {
        this.onSelectionUpdateCallback = function6;
    }

    public final void setOnSelectionUpdateEndCallback$foundation(Function0<Unit> function0) {
        this.onSelectionUpdateEndCallback = function0;
    }

    public final void setOnSelectionUpdateSelectAll$foundation(Function2<? super Boolean, ? super Long, Unit> function2) {
        this.onSelectionUpdateSelectAll = function2;
    }

    public final void setOnSelectionUpdateStartCallback$foundation(Function4<? super Boolean, ? super LayoutCoordinates, ? super Offset, ? super SelectionAdjustment, Unit> function4) {
        this.onSelectionUpdateStartCallback = function4;
    }

    public final void setSorted$foundation(boolean z) {
        this.sorted = z;
    }

    public void setSubselections(LongObjectMap<Selection> longObjectMap) {
        this.subselections.setValue(longObjectMap);
    }

    public final List<Selectable> sort(final LayoutCoordinates containerLayoutCoordinates) {
        if (!this.sorted) {
            List<Selectable> list = this._selectables;
            final Function2 function2 = new Function2() { // from class: e6d
                public final Object invoke(Object obj, Object obj2) {
                    return Integer.valueOf(SelectionRegistrarImpl.a(containerLayoutCoordinates, (Selectable) obj, (Selectable) obj2));
                }
            };
            CollectionsKt.sortWith(list, new Comparator() { // from class: f6d
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return SelectionRegistrarImpl.b(function2, obj, obj2);
                }
            });
            this.sorted = true;
        }
        return getSelectables$foundation();
    }

    @Override // androidx.compose.foundation.text.selection.SelectionRegistrar
    public Selectable subscribe(Selectable selectable) {
        if (!(selectable.getSelectableId() != 0)) {
            InlineClassHelperKt.throwIllegalArgumentException("The selectable contains an invalid id: " + selectable.getSelectableId());
        }
        if (this._selectableMap.containsKey(selectable.getSelectableId())) {
            InlineClassHelperKt.throwIllegalArgumentException("Another selectable with the id: " + selectable + ".selectableId has already subscribed.");
        }
        this._selectableMap.set(selectable.getSelectableId(), selectable);
        this._selectables.add(selectable);
        this.sorted = false;
        return selectable;
    }

    @Override // androidx.compose.foundation.text.selection.SelectionRegistrar
    public void unsubscribe(Selectable selectable) {
        if (this._selectableMap.containsKey(selectable.getSelectableId())) {
            this._selectables.remove(selectable);
            this._selectableMap.remove(selectable.getSelectableId());
            Function1<? super Long, Unit> function1 = this.afterSelectableUnsubscribe;
            if (function1 != null) {
                function1.invoke(Long.valueOf(selectable.getSelectableId()));
            }
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Landroidx/compose/foundation/text/selection/SelectionRegistrarImpl$Companion;", "", "<init>", "()V", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/foundation/text/selection/SelectionRegistrarImpl;", "", "getSaver", "()Landroidx/compose/runtime/saveable/Saver;", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Saver<SelectionRegistrarImpl, Long> getSaver() {
            return SelectionRegistrarImpl.Saver;
        }

        private Companion() {
        }
    }

    public SelectionRegistrarImpl() {
        this(1L);
    }
}
