package androidx.compose.ui.platform;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.collection.MutableObjectList;
import androidx.collection.MutableScatterMap;
import androidx.collection.ScatterMapKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.Comparator;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J)\u0010\u001e\u001a\u00020\u001f2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00140!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%¢\u0006\u0002\u0010&R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001d\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00060\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R!\u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00020\u00140\u0018j\b\u0012\u0004\u0012\u00020\u0014`\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR!\u0010\u001c\u001a\u0012\u0012\u0004\u0012\u00020\u00140\u0018j\b\u0012\u0004\u0012\u00020\u0014`\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001b¨\u0006'"}, d2 = {"Landroidx/compose/ui/platform/FocusSorter;", "", "<init>", "()V", "rectPool", "Landroidx/collection/MutableObjectList;", "Landroid/graphics/Rect;", "getRectPool", "()Landroidx/collection/MutableObjectList;", "lastPoolIndex", "", "getLastPoolIndex", "()I", "setLastPoolIndex", "(I)V", "rtlMult", "getRtlMult", "setRtlMult", "rectByView", "Landroidx/collection/MutableScatterMap;", "Landroid/view/View;", "getRectByView", "()Landroidx/collection/MutableScatterMap;", "topsComparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "getTopsComparator", "()Ljava/util/Comparator;", "sidesComparator", "getSidesComparator", "sort", "", "views", "", "root", "Landroid/view/ViewGroup;", "isRtl", "", "([Landroid/view/View;Landroid/view/ViewGroup;Z)V", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
final class FocusSorter {
    private static int lastPoolIndex;
    public static final FocusSorter INSTANCE = new FocusSorter();
    private static final MutableObjectList<Rect> rectPool = new MutableObjectList<>(0, 1, (DefaultConstructorMarker) null);
    private static int rtlMult = 1;
    private static final MutableScatterMap<View, Rect> rectByView = ScatterMapKt.mutableScatterMapOf();
    private static final Comparator<View> topsComparator = new Comparator() { // from class: androidx.compose.ui.platform.b
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return FocusSorter.a((View) obj, (View) obj2);
        }
    };
    private static final Comparator<View> sidesComparator = new Comparator() { // from class: androidx.compose.ui.platform.c
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return FocusSorter.b((View) obj, (View) obj2);
        }
    };

    private FocusSorter() {
    }

    public static int a(View view, View view2) {
        if (view == view2) {
            return 0;
        }
        MutableScatterMap<View, Rect> mutableScatterMap = rectByView;
        Object obj = mutableScatterMap.get(view);
        obj.getClass();
        Rect rect = (Rect) obj;
        Object obj2 = mutableScatterMap.get(view2);
        obj2.getClass();
        Rect rect2 = (Rect) obj2;
        int i = rect.top - rect2.top;
        return i == 0 ? rect.bottom - rect2.bottom : i;
    }

    public static int b(View view, View view2) {
        if (view == view2) {
            return 0;
        }
        MutableScatterMap<View, Rect> mutableScatterMap = rectByView;
        Object obj = mutableScatterMap.get(view);
        obj.getClass();
        Rect rect = (Rect) obj;
        Object obj2 = mutableScatterMap.get(view2);
        obj2.getClass();
        Rect rect2 = (Rect) obj2;
        int i = rect.left - rect2.left;
        return i == 0 ? (rect.right - rect2.right) * rtlMult : i * rtlMult;
    }

    public final int getLastPoolIndex() {
        return lastPoolIndex;
    }

    public final MutableScatterMap<View, Rect> getRectByView() {
        return rectByView;
    }

    public final MutableObjectList<Rect> getRectPool() {
        return rectPool;
    }

    public final int getRtlMult() {
        return rtlMult;
    }

    public final Comparator<View> getSidesComparator() {
        return sidesComparator;
    }

    public final Comparator<View> getTopsComparator() {
        return topsComparator;
    }

    public final void setLastPoolIndex(int i) {
        lastPoolIndex = i;
    }

    public final void setRtlMult(int i) {
        rtlMult = i;
    }

    public final void sort(View[] views, ViewGroup root, boolean isRtl) {
        int length = views.length;
        if (length < 2) {
            return;
        }
        int size = length - rectPool.getSize();
        for (int i = 0; i < size; i++) {
            rectPool.add(new Rect());
        }
        for (View view : views) {
            MutableObjectList<Rect> mutableObjectList = rectPool;
            int i2 = lastPoolIndex;
            lastPoolIndex = i2 + 1;
            Rect rect = (Rect) mutableObjectList.get(i2);
            view.getDrawingRect(rect);
            root.offsetDescendantRectToMyCoords(view, rect);
            rectByView.set(view, rect);
        }
        ArraysKt.sortWith(views, topsComparator);
        Object obj = rectByView.get(views[0]);
        obj.getClass();
        int iMax = ((Rect) obj).bottom;
        rtlMult = isRtl ? -1 : 1;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            Object obj2 = rectByView.get(views[i4]);
            obj2.getClass();
            Rect rect2 = (Rect) obj2;
            if (rect2.top >= iMax) {
                if (i4 - i3 > 1) {
                    ArraysKt.sortWith(views, sidesComparator, i3, i4);
                }
                iMax = rect2.bottom;
                i3 = i4;
            } else {
                iMax = Math.max(iMax, rect2.bottom);
            }
        }
        if (length - i3 > 1) {
            ArraysKt.sortWith(views, sidesComparator, i3, length);
        }
        lastPoolIndex = 0;
        rectByView.clear();
    }
}
