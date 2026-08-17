package androidx.core.widget;

import android.widget.ListView;
import androidx.annotation.ReplaceWith;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Deprecated
public final class ListViewCompat {
    private ListViewCompat() {
    }

    @ReplaceWith(expression = "listView.canScrollList(direction)")
    @Deprecated
    public static boolean canScrollList(ListView listView, int i) {
        return listView.canScrollList(i);
    }

    @ReplaceWith(expression = "listView.scrollListBy(y)")
    @Deprecated
    public static void scrollListBy(ListView listView, int i) {
        listView.scrollListBy(i);
    }
}
