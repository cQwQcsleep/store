package com.reandroid.utils.collection;

import java.util.Comparator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ArraySort {

    public static class IntSort extends Sorter {
        private final int[] elementData;
        private final int length;
        private int mid;
        private final int start;

        public IntSort(int[] iArr, int i, int i2) {
            this.elementData = iArr;
            this.start = i;
            this.length = i2;
        }

        @Override // com.reandroid.utils.collection.Sorter
        public int compareToMid(int i) {
            int i2 = this.elementData[i];
            int i3 = this.mid;
            if (i2 == i3) {
                return 0;
            }
            return i2 < i3 ? -1 : 1;
        }

        @Override // com.reandroid.utils.collection.Sorter
        public void onSwap(int i, int i2) {
            int[] iArr = this.elementData;
            int i3 = iArr[i];
            iArr[i] = iArr[i2];
            iArr[i2] = i3;
        }

        @Override // com.reandroid.utils.collection.Sorter
        public void setMid(int i) {
            this.mid = this.elementData[i];
        }

        public boolean sort() {
            return sort(this.start, this.length);
        }
    }

    public static <T> boolean sort(Object[] objArr, Comparator<T> comparator) {
        return new ObjectSort(objArr, 0, objArr.length, comparator).sort();
    }

    public static class ObjectSort extends Sorter {
        private final Comparator<Object> comparator;
        private final Object[] elementData;
        private final int length;
        private Object mid;
        private final int start;

        public ObjectSort(Object[] objArr, int i, int i2, Comparator<?> comparator) {
            this.elementData = objArr;
            this.comparator = comparator;
            this.start = i;
            this.length = i2;
        }

        @Override // com.reandroid.utils.collection.Sorter
        public int compareToMid(int i) {
            Object obj = this.elementData[i];
            Object obj2 = this.mid;
            if (obj2 == obj) {
                return 0;
            }
            return this.comparator.compare(obj, obj2);
        }

        @Override // com.reandroid.utils.collection.Sorter
        public void onSwap(int i, int i2) {
            Object[] objArr = this.elementData;
            Object obj = objArr[i];
            objArr[i] = objArr[i2];
            objArr[i2] = obj;
        }

        @Override // com.reandroid.utils.collection.Sorter
        public void setMid(int i) {
            this.mid = this.elementData[i];
        }

        public boolean sort() {
            return sort(this.start, this.length);
        }

        public ObjectSort(Object[] objArr, Comparator<?> comparator) {
            this(objArr, 0, objArr.length, comparator);
        }
    }

    public static boolean sort(int[] iArr, int i, int i2) {
        return new IntSort(iArr, i, i2).sort();
    }

    public static boolean sort(Object[] objArr, Comparator<?> comparator, int i) {
        return new ObjectSort(objArr, 0, i, comparator).sort();
    }

    public static boolean sort(Object[] objArr, int i, int i2, Comparator<?> comparator) {
        return new ObjectSort(objArr, i, i2, comparator).sort();
    }

    public static boolean sort(int[] iArr) {
        return new IntSort(iArr, 0, iArr.length).sort();
    }
}
