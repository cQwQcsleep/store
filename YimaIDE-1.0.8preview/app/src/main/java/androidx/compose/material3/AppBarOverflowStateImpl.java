package androidx.compose.material3;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\r\b\u0002\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R+\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR+\u0010\r\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b\u0010\u0010\f\u001a\u0004\b\u000e\u0010\b\"\u0004\b\u000f\u0010\n¨\u0006\u0012"}, d2 = {"Landroidx/compose/material3/AppBarOverflowStateImpl;", "Landroidx/compose/material3/AppBarOverflowState;", "<init>", "()V", "<set-?>", "", "totalItemCount", "getTotalItemCount", "()I", "setTotalItemCount", "(I)V", "totalItemCount$delegate", "Landroidx/compose/runtime/MutableIntState;", "visibleItemCount", "getVisibleItemCount", "setVisibleItemCount", "visibleItemCount$delegate", "Companion", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class AppBarOverflowStateImpl implements AppBarOverflowState {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Saver<AppBarOverflowStateImpl, ?> Saver = SaverKt.Saver(new Function2() { // from class: androidx.compose.material3.c
        public final Object invoke(Object obj, Object obj2) {
            return AppBarOverflowStateImpl.a((SaverScope) obj, (AppBarOverflowStateImpl) obj2);
        }
    }, new Function1() { // from class: androidx.compose.material3.d
        public final Object invoke(Object obj) {
            return AppBarOverflowStateImpl.b((List) obj);
        }
    });

    /* JADX INFO: renamed from: totalItemCount$delegate, reason: from kotlin metadata */
    private final MutableIntState totalItemCount = SnapshotIntStateKt.mutableIntStateOf(0);

    /* JADX INFO: renamed from: visibleItemCount$delegate, reason: from kotlin metadata */
    private final MutableIntState visibleItemCount = SnapshotIntStateKt.mutableIntStateOf(0);

    public static List a(SaverScope saverScope, AppBarOverflowStateImpl appBarOverflowStateImpl) {
        return CollectionsKt.listOf(new Integer[]{Integer.valueOf(appBarOverflowStateImpl.getTotalItemCount()), Integer.valueOf(appBarOverflowStateImpl.getVisibleItemCount())});
    }

    public static AppBarOverflowStateImpl b(List list) {
        AppBarOverflowStateImpl appBarOverflowStateImpl = new AppBarOverflowStateImpl();
        appBarOverflowStateImpl.setTotalItemCount(((Number) list.get(0)).intValue());
        appBarOverflowStateImpl.setVisibleItemCount(((Number) list.get(1)).intValue());
        return appBarOverflowStateImpl;
    }

    @Override // androidx.compose.material3.AppBarOverflowState
    public int getTotalItemCount() {
        return this.totalItemCount.getIntValue();
    }

    @Override // androidx.compose.material3.AppBarOverflowState
    public int getVisibleItemCount() {
        return this.visibleItemCount.getIntValue();
    }

    @Override // androidx.compose.material3.AppBarOverflowState
    public void setTotalItemCount(int i) {
        this.totalItemCount.setIntValue(i);
    }

    @Override // androidx.compose.material3.AppBarOverflowState
    public void setVisibleItemCount(int i) {
        this.visibleItemCount.setIntValue(i);
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\u0004\u001a\f\u0012\u0004\u0012\u00020\u0006\u0012\u0002\b\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Landroidx/compose/material3/AppBarOverflowStateImpl$Companion;", "", "<init>", "()V", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/material3/AppBarOverflowStateImpl;", "getSaver", "()Landroidx/compose/runtime/saveable/Saver;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Saver<AppBarOverflowStateImpl, ?> getSaver() {
            return AppBarOverflowStateImpl.Saver;
        }

        private Companion() {
        }
    }
}
