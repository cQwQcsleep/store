package io.github.rosemoe.sora.lang.styling;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class MappedSpanUpdater {
    private static int findSpanIndexFor(List<Span> list, int i, int i2) {
        while (i < list.size()) {
            if (list.get(i).getColumn() >= i2) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static void shiftSpansOnMultiLineDelete(List<List<Span>> list, int i, int i2, int i3, int i4) {
        for (int i5 = (i3 - i) - 1; i5 > 0; i5--) {
            SpanFactory.recycleAll(list.remove(i + 1));
        }
        List<Span> list2 = list.get(i);
        for (int size = list2.size() - 1; size > 0 && list2.get(size).getColumn() >= i2; size--) {
            list2.remove(size).recycle();
        }
        List<Span> listRemove = list.remove(i + 1);
        for (int i6 = 0; i6 < listRemove.size(); i6++) {
            listRemove.get(i6).shiftColumnBy(i2 - i4);
        }
        while (listRemove.size() > 1 && listRemove.get(0).getColumn() <= i2 && listRemove.get(1).getColumn() <= i2) {
            listRemove.remove(0).recycle();
        }
        if (listRemove.get(0).getColumn() <= i2) {
            listRemove.get(0).setColumn(i2);
        }
        list2.addAll(listRemove);
    }

    public static void shiftSpansOnMultiLineInsert(List<List<Span>> list, int i, int i2, int i3, int i4) {
        List<Span> list2 = list.get(i);
        int iFindSpanIndexFor = findSpanIndexFor(list2, 0, i2);
        if (iFindSpanIndexFor == -1) {
            iFindSpanIndexFor = list2.size() - 1;
        }
        if (list2.get(iFindSpanIndexFor).getColumn() > i2) {
            iFindSpanIndexFor--;
        }
        Span spanObtainNoExt = (iFindSpanIndexFor < 0 || iFindSpanIndexFor >= list2.size()) ? SpanFactory.obtainNoExt(0, 5L) : list2.get(iFindSpanIndexFor);
        for (int i5 = 0; i5 < i3 - i; i5++) {
            ArrayList arrayList = new ArrayList();
            Span spanCopy = spanObtainNoExt.copy();
            spanCopy.setColumn(0);
            arrayList.add(spanCopy);
            list.add(i + 1, arrayList);
        }
        List<Span> list3 = list.get(i3);
        int i6 = iFindSpanIndexFor;
        while (i6 < list2.size()) {
            int i7 = i6 + 1;
            Span span = list2.get(i6);
            Span spanCopy2 = span.copy();
            spanCopy2.setColumn(Math.max(0, (span.getColumn() - i2) + i4));
            list3.add(spanCopy2);
            i6 = i7;
        }
        while (iFindSpanIndexFor + 1 < list2.size()) {
            list2.remove(list2.size() - 1).recycle();
        }
        if (list3.size() > 1 && list3.get(0).getColumn() == 0 && list3.get(1).getColumn() == 0) {
            list3.remove(0).recycle();
        }
    }

    public static void shiftSpansOnSingleLineDelete(List<List<Span>> list, int i, int i2, int i3) {
        if (list == null || list.isEmpty()) {
            return;
        }
        List<Span> list2 = list.get(i);
        int i4 = 0;
        int iFindSpanIndexFor = findSpanIndexFor(list2, 0, i2);
        if (iFindSpanIndexFor == -1) {
            return;
        }
        int iFindSpanIndexFor2 = findSpanIndexFor(list2, iFindSpanIndexFor, i3);
        if (iFindSpanIndexFor2 == -1) {
            iFindSpanIndexFor2 = list2.size();
        }
        int i5 = iFindSpanIndexFor2 - iFindSpanIndexFor;
        for (int i6 = 0; i6 < i5; i6++) {
            list2.remove(iFindSpanIndexFor).recycle();
        }
        int i7 = i3 - i2;
        while (iFindSpanIndexFor < list2.size()) {
            list2.get(iFindSpanIndexFor).shiftColumnBy(-i7);
            iFindSpanIndexFor++;
        }
        if (list2.isEmpty() || list2.get(0).getColumn() != 0) {
            list2.add(0, SpanFactory.obtainNoExt(0, 5L));
        }
        while (true) {
            int i8 = i4 + 1;
            if (i8 >= list2.size()) {
                return;
            }
            if (list2.get(i4).getColumn() >= list2.get(i8).getColumn()) {
                list2.remove(i4).recycle();
                i4--;
            }
            i4++;
        }
    }

    public static void shiftSpansOnSingleLineInsert(List<List<Span>> list, int i, int i2, int i3) {
        List<Span> list2;
        int iFindSpanIndexFor;
        if (list == null || list.isEmpty() || (iFindSpanIndexFor = findSpanIndexFor((list2 = list.get(i)), 0, i2)) == -1) {
            return;
        }
        int i4 = i3 - i2;
        for (int i5 = iFindSpanIndexFor; i5 < list2.size(); i5++) {
            list2.get(i5).shiftColumnBy(i4);
        }
        if (iFindSpanIndexFor == 0) {
            Span span = list2.get(0);
            if (span.getColumn() == 5 && span.hasSpanExt(3)) {
                span.setColumn(0);
            } else {
                list2.add(0, SpanFactory.obtainNoExt(0, 5L));
            }
        }
    }
}
