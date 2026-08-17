package androidx.compose.material3;

import androidx.compose.material3.BottomAppBarState;
import androidx.compose.runtime.saveable.ListSaverKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000e\bg\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010R\u0018\u0010\u0002\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\t\u0010\u0005\"\u0004\b\n\u0010\u0007R\u0018\u0010\u000b\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\f\u0010\u0005\"\u0004\b\r\u0010\u0007R\u0012\u0010\u000e\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0011À\u0006\u0001"}, d2 = {"Landroidx/compose/material3/BottomAppBarState;", "", "heightOffsetLimit", "", "getHeightOffsetLimit", "()F", "setHeightOffsetLimit", "(F)V", "heightOffset", "getHeightOffset", "setHeightOffset", "contentOffset", "getContentOffset", "setContentOffset", "collapsedFraction", "getCollapsedFraction", "Companion", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public interface BottomAppBarState {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\u0004\u001a\f\u0012\u0004\u0012\u00020\u0006\u0012\u0002\b\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Landroidx/compose/material3/BottomAppBarState$Companion;", "", "<init>", "()V", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/material3/BottomAppBarState;", "getSaver", "()Landroidx/compose/runtime/saveable/Saver;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final Saver<BottomAppBarState, ?> Saver = ListSaverKt.listSaver(new Function2() { // from class: zy0
            public final Object invoke(Object obj, Object obj2) {
                return BottomAppBarState.Companion.a((SaverScope) obj, (BottomAppBarState) obj2);
            }
        }, new Function1() { // from class: az0
            public final Object invoke(Object obj) {
                return BottomAppBarState.Companion.b((List) obj);
            }
        });

        private Companion() {
        }

        public static List a(SaverScope saverScope, BottomAppBarState bottomAppBarState) {
            return CollectionsKt.listOf(new Float[]{Float.valueOf(bottomAppBarState.getHeightOffsetLimit()), Float.valueOf(bottomAppBarState.getHeightOffset()), Float.valueOf(bottomAppBarState.getContentOffset())});
        }

        public static BottomAppBarState b(List list) {
            return AppBarKt.BottomAppBarState(((Number) list.get(0)).floatValue(), ((Number) list.get(1)).floatValue(), ((Number) list.get(2)).floatValue());
        }

        public final Saver<BottomAppBarState, ?> getSaver() {
            return Saver;
        }
    }

    float getCollapsedFraction();

    float getContentOffset();

    float getHeightOffset();

    float getHeightOffsetLimit();

    void setContentOffset(float f);

    void setHeightOffset(float f);

    void setHeightOffsetLimit(float f);
}
