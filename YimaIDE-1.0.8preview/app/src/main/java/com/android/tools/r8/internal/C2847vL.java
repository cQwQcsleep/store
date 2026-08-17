package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C2847vL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.vL, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2847vL {
    public static final /* synthetic */ boolean a = true;

    public static ArrayList a(final List list, C0919Vz c0919Vz) {
        boolean z = a;
        if (!z && c0919Vz.stream().distinct().count() != c0919Vz.c) {
            x1f.a();
            return null;
        }
        if (!z && !c0919Vz.stream().allMatch(new Predicate() { // from class: tli
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return C2847vL.a(list, (Integer) obj);
            }
        })) {
            x1f.a();
            return null;
        }
        if (c0919Vz.c == list.size()) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(list.size() - c0919Vz.c);
        int i = 0;
        InterfaceC2067mA interfaceC2067mAO = c0919Vz.o(0);
        int iQ = interfaceC2067mAO.q();
        while (i < list.size()) {
            boolean z2 = a;
            if (!z2 && i > iQ) {
                x1f.a();
                return null;
            }
            if (i != iQ) {
                arrayList.add(list.get(i));
            } else if (!interfaceC2067mAO.hasNext()) {
                while (true) {
                    i++;
                    if (i >= list.size()) {
                        break;
                    }
                    arrayList.add(list.get(i));
                }
            } else {
                iQ = interfaceC2067mAO.q();
                if (!z2 && iQ <= i) {
                    x1f.a();
                    return null;
                }
            }
            i++;
        }
        return arrayList;
    }

    public static List b(List list, Function function) {
        ArrayList arrayList = null;
        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            Collection collection = (Collection) function.apply(obj);
            if (collection != null) {
                if (arrayList == null) {
                    arrayList = new ArrayList((collection.size() + list.size()) - 1);
                    for (int i2 = 0; i2 < i; i2++) {
                        arrayList.add(list.get(i2));
                    }
                }
                arrayList.addAll(collection);
            } else if (arrayList != null) {
                arrayList.add(obj);
            }
        }
        if (arrayList != null) {
            return arrayList;
        }
        return null;
    }

    public static <T> List<T> c(List<T> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (int size = list.size() - 1; size >= 0; size--) {
            arrayList.add(list.get(size));
        }
        return arrayList;
    }

    public static <T> T b(List<T> list) {
        return list.get(list.size() - 1);
    }

    public static <T> int b(List<T> list, Predicate<T> predicate) {
        for (int size = list.size() - 1; size >= 0; size--) {
            if (predicate.test(list.get(size))) {
                return size;
            }
        }
        return -1;
    }

    public static AbstractC0551Hu b(InterfaceC0806Rq interfaceC0806Rq) {
        final C0473Eu c0473EuG = AbstractC0551Hu.g();
        interfaceC0806Rq.forEach(new Consumer() { // from class: pli
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                c0473EuG.a(obj);
            }
        });
        return c0473EuG.a();
    }

    public static <S, T extends S> List<T> a(Collection<S> collection, final Predicate<? super S> predicate) {
        final ArrayList arrayList = new ArrayList(collection.size());
        collection.forEach(new Consumer() { // from class: rli
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C2847vL.a(predicate, arrayList, obj);
            }
        });
        return arrayList;
    }

    public static /* synthetic */ void a(Predicate predicate, ArrayList arrayList, Object obj) {
        if (predicate.test(obj)) {
            arrayList.add(obj);
        }
    }

    public static <T> T a(List<T> list) {
        return list.get(0);
    }

    public static int a(List list, Predicate predicate) {
        for (int i = 0; i < list.size(); i++) {
            if (predicate.test(list.get(i))) {
                return i;
            }
        }
        return -1;
    }

    public static <S, T> List<T> a(S[] sArr, Function<S, T> function) {
        ArrayList arrayList = new ArrayList();
        for (S s : sArr) {
            arrayList.add(function.apply(s));
        }
        return arrayList;
    }

    public static <S, T> List<T> a(Collection<S> collection, Function<S, T> function) {
        ArrayList arrayList = new ArrayList(collection.size());
        Iterator<S> it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(function.apply(it.next()));
        }
        return arrayList;
    }

    public static List a(List list, final Function function, List list2) {
        return a(list, new InterfaceC2665tA() { // from class: oli
            @Override // com.android.tools.r8.internal.InterfaceC2665tA
            public final Object a(int i, Object obj) {
                return function.apply(obj);
            }
        }, list2);
    }

    public static List a(List list, InterfaceC2665tA interfaceC2665tA, List list2) {
        ArrayList arrayList = null;
        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            Object objA = interfaceC2665tA.a(i, obj);
            if (objA != obj) {
                if (arrayList == null) {
                    arrayList = new ArrayList(list.size());
                    for (int i2 = 0; i2 < i; i2++) {
                        arrayList.add(list.get(i2));
                    }
                }
                if (objA != null) {
                    arrayList.add(objA);
                }
            } else if (arrayList != null) {
                arrayList.add(obj);
            }
        }
        return arrayList != null ? arrayList : list2;
    }

    public static <T> ArrayList<T> a(InterfaceC0806Rq<T> interfaceC0806Rq) {
        final ArrayList<T> arrayList = new ArrayList<>();
        interfaceC0806Rq.forEach(new Consumer() { // from class: qli
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                arrayList.add(obj);
            }
        });
        return arrayList;
    }

    public static <S, T> List<T> a(List<S> list, final Function<S, Collection<T>> function) {
        final ArrayList arrayList = new ArrayList();
        list.forEach(new Consumer() { // from class: sli
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                arrayList.addAll((Collection) function.apply(obj));
            }
        });
        return arrayList;
    }

    public static /* synthetic */ boolean a(List list, Integer num) {
        return num.intValue() < list.size();
    }

    public static Object a(Collection collection, Object obj, BiFunction biFunction) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            obj = biFunction.apply(obj, it.next());
        }
        return obj;
    }

    public static ArrayList a(C2924wC c2924wC, Comparator comparator, int i) {
        ArrayList arrayList = new ArrayList(i);
        AbstractC3179zC.a((Iterable) c2924wC, (Collection) arrayList);
        arrayList.sort(comparator);
        return arrayList;
    }

    public static ArrayList a(Collection collection, Comparator comparator) {
        ArrayList arrayList = new ArrayList(collection);
        arrayList.sort(comparator);
        return arrayList;
    }

    public static void a(List list, Comparator comparator) {
        list.sort(comparator);
    }

    public static int a(ArrayList arrayList, Predicate predicate) {
        int i = -1;
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (predicate.test(arrayList.get(i2))) {
                if (i != -1) {
                    return -1;
                }
                i = i2;
            }
        }
        return i;
    }
}
