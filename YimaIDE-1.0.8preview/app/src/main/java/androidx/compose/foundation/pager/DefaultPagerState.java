package androidx.compose.foundation.pager;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.saveable.ListSaverKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverScope;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\u0004\b\b\u0010\tR&\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00070\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0014"}, d2 = {"Landroidx/compose/foundation/pager/DefaultPagerState;", "Landroidx/compose/foundation/pager/PagerState;", "currentPage", "", "currentPageOffsetFraction", "", "updatedPageCount", "Lkotlin/Function0;", "<init>", "(IFLkotlin/jvm/functions/Function0;)V", "pageCountState", "Landroidx/compose/runtime/MutableState;", "getPageCountState", "()Landroidx/compose/runtime/MutableState;", "setPageCountState", "(Landroidx/compose/runtime/MutableState;)V", "pageCount", "getPageCount", "()I", "Companion", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class DefaultPagerState extends PagerState {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Saver<DefaultPagerState, ?> Saver = ListSaverKt.listSaver(new Function2() { // from class: androidx.compose.foundation.pager.a
        public final Object invoke(Object obj, Object obj2) {
            return DefaultPagerState.f((SaverScope) obj, (DefaultPagerState) obj2);
        }
    }, new Function1() { // from class: androidx.compose.foundation.pager.b
        public final Object invoke(Object obj) {
            return DefaultPagerState.g((List) obj);
        }
    });
    private MutableState<Function0<Integer>> pageCountState;

    public DefaultPagerState(int i, float f, Function0<Integer> function0) {
        super(i, f);
        this.pageCountState = SnapshotStateKt.mutableStateOf$default(function0, (SnapshotMutationPolicy) null, 2, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int Saver$lambda$1$0(List list) {
        Object obj = list.get(2);
        obj.getClass();
        return ((Integer) obj).intValue();
    }

    public static List f(SaverScope saverScope, DefaultPagerState defaultPagerState) {
        return CollectionsKt.listOf(new Object[]{Integer.valueOf(defaultPagerState.getCurrentPage()), Float.valueOf(RangesKt.coerceIn(defaultPagerState.getCurrentPageOffsetFraction(), -0.5f, 0.5f)), Integer.valueOf(defaultPagerState.getPageCount())});
    }

    public static DefaultPagerState g(final List list) {
        Object obj = list.get(0);
        obj.getClass();
        int iIntValue = ((Integer) obj).intValue();
        Object obj2 = list.get(1);
        obj2.getClass();
        return new DefaultPagerState(iIntValue, ((Float) obj2).floatValue(), new Function0() { // from class: androidx.compose.foundation.pager.c
            public final Object invoke() {
                return Integer.valueOf(DefaultPagerState.Saver$lambda$1$0(list));
            }
        });
    }

    @Override // androidx.compose.foundation.pager.PagerState
    public int getPageCount() {
        return ((Number) ((Function0) this.pageCountState.getValue()).invoke()).intValue();
    }

    public final MutableState<Function0<Integer>> getPageCountState() {
        return this.pageCountState;
    }

    public final void setPageCountState(MutableState<Function0<Integer>> mutableState) {
        this.pageCountState = mutableState;
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\u0004\u001a\f\u0012\u0004\u0012\u00020\u0006\u0012\u0002\b\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Landroidx/compose/foundation/pager/DefaultPagerState$Companion;", "", "<init>", "()V", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/foundation/pager/DefaultPagerState;", "getSaver", "()Landroidx/compose/runtime/saveable/Saver;", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Saver<DefaultPagerState, ?> getSaver() {
            return DefaultPagerState.Saver;
        }

        private Companion() {
        }
    }
}
