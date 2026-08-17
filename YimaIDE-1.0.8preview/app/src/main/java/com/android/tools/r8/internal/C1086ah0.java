package com.android.tools.r8.internal;

import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.D2;
import com.android.tools.r8.internal.C1086ah0;
import com.android.tools.r8.threading.ThreadingModule;
import defpackage.edg;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ah0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C1086ah0 {

    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX INFO: renamed from: com.android.tools.r8.internal.ah0$a */
    public static final class a {
        public static final a c = new a(0, 2, "HEAVY");
        public static final a d = new a(1, 4, "LIGHT");
        public final int b;

        public a(int i, int i2, String str) {
            super(str, i);
            this.b = i2;
        }

        public int a() {
            return this.b;
        }
    }

    public static void a(final Collection collection, final InterfaceC2762uL interfaceC2762uL, ThreadingModule threadingModule, ExecutorService executorService, a aVar) throws ExecutionException {
        if (collection.size() >= aVar.a()) {
            InterfaceC0806Rq interfaceC0806Rq = new InterfaceC0806Rq() { // from class: hdg
                @Override // com.android.tools.r8.internal.InterfaceC0806Rq
                public final void forEach(Consumer consumer) {
                    collection.forEach(consumer);
                }
            };
            Objects.requireNonNull(interfaceC2762uL);
            a(interfaceC0806Rq, new InterfaceC2450qh0() { // from class: idg
                @Override // com.android.tools.r8.internal.InterfaceC2450qh0
                public final void accept(Object obj, int i) {
                    interfaceC2762uL.accept(obj, i);
                }
            }, threadingModule, executorService);
        } else {
            Iterator it = collection.iterator();
            int i = 0;
            while (it.hasNext()) {
                interfaceC2762uL.accept(it.next(), i);
                i++;
            }
        }
    }

    public static ArrayList a(Iterable iterable, final InterfaceC2022lh0 interfaceC2022lh0, Predicate predicate, ThreadingModule threadingModule, ExecutorService executorService) {
        return a(iterable, new InterfaceC2535rh0() { // from class: ddg
            @Override // com.android.tools.r8.internal.InterfaceC2535rh0
            public final Object a(Object obj, int i) {
                return interfaceC2022lh0.apply(obj);
            }
        }, predicate, threadingModule, executorService);
    }

    public static ArrayList a(Iterable iterable, InterfaceC2535rh0 interfaceC2535rh0, ThreadingModule threadingModule, ExecutorService executorService) {
        Objects.requireNonNull(iterable);
        return a(new edg(iterable), interfaceC2535rh0, (Predicate) null, threadingModule, executorService);
    }

    public static ArrayList a(Iterable iterable, InterfaceC2535rh0 interfaceC2535rh0, Predicate predicate, ThreadingModule threadingModule, ExecutorService executorService) {
        Objects.requireNonNull(iterable);
        return a(new edg(iterable), interfaceC2535rh0, predicate, threadingModule, executorService);
    }

    public static ArrayList a(InterfaceC0806Rq interfaceC0806Rq, final InterfaceC2535rh0 interfaceC2535rh0, Predicate predicate, ThreadingModule threadingModule, ExecutorService executorService) throws ExecutionException {
        final Ng0 ng0 = new Ng0(threadingModule, executorService, -1);
        try {
            interfaceC0806Rq.a(new InterfaceC2494rA() { // from class: bdg
                @Override // com.android.tools.r8.internal.InterfaceC2494rA
                public final void a(int i, Object obj) {
                    C1086ah0.a(ng0, interfaceC2535rh0, i, obj);
                }
            });
            return ng0.a(predicate);
        } catch (Xj0 e) {
            throw e.b;
        }
    }

    public static void a(Ng0 ng0, final InterfaceC2535rh0 interfaceC2535rh0, final int i, final Object obj) {
        Callable callable = new Callable() { // from class: cdg
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return interfaceC2535rh0.a(obj, i);
            }
        };
        ng0.getClass();
        try {
            ng0.a(callable);
        } catch (ExecutionException e) {
            throw new Xj0(e);
        }
    }

    public static void a(Collection collection, final Consumer consumer, ThreadingModule threadingModule, ExecutorService executorService) throws ExecutionException {
        a(collection, new InterfaceC2762uL() { // from class: zcg
            @Override // com.android.tools.r8.internal.InterfaceC2762uL
            public final void accept(Object obj, int i) {
                consumer.accept(obj);
            }
        }, threadingModule, executorService, a.d);
    }

    public static ArrayList a(Iterable iterable, final InterfaceC2022lh0 interfaceC2022lh0, ThreadingModule threadingModule, ExecutorService executorService) {
        return a(iterable, new InterfaceC2535rh0() { // from class: adg
            @Override // com.android.tools.r8.internal.InterfaceC2535rh0
            public final Object a(Object obj, int i) {
                return interfaceC2022lh0.apply(obj);
            }
        }, threadingModule, executorService);
    }

    public static void a(InterfaceC0806Rq interfaceC0806Rq, final InterfaceC1936kh0 interfaceC1936kh0, ThreadingModule threadingModule, ExecutorService executorService) throws ExecutionException {
        a(interfaceC0806Rq, new InterfaceC2450qh0() { // from class: jdg
            @Override // com.android.tools.r8.internal.InterfaceC2450qh0
            public final void accept(Object obj, int i) throws Throwable {
                interfaceC1936kh0.accept(obj);
            }
        }, threadingModule, executorService);
    }

    public static void a(InterfaceC0806Rq interfaceC0806Rq, final InterfaceC2450qh0 interfaceC2450qh0, ThreadingModule threadingModule, ExecutorService executorService) throws ExecutionException {
        a(interfaceC0806Rq, new InterfaceC2535rh0() { // from class: fdg
            @Override // com.android.tools.r8.internal.InterfaceC2535rh0
            public final Object a(Object obj, int i) {
                return C1086ah0.a(interfaceC2450qh0, obj, i);
            }
        }, (Predicate) null, threadingModule, executorService);
    }

    public static /* synthetic */ Object a(InterfaceC2450qh0 interfaceC2450qh0, Object obj, int i) {
        interfaceC2450qh0.accept(obj, i);
        return null;
    }

    public static void a(Map map, final InterfaceC1766ih0 interfaceC1766ih0, ThreadingModule threadingModule, ExecutorService executorService) {
        a(map, new InterfaceC1851jh0() { // from class: wcg
            @Override // com.android.tools.r8.internal.InterfaceC1851jh0
            public final Object apply(Object obj, Object obj2) {
                return C1086ah0.a(interfaceC1766ih0, obj, obj2);
            }
        }, threadingModule, executorService);
    }

    public static /* synthetic */ Object a(InterfaceC1766ih0 interfaceC1766ih0, Object obj, Object obj2) throws Throwable {
        interfaceC1766ih0.accept(obj, obj2);
        return null;
    }

    public static void a(Map map, final InterfaceC1851jh0 interfaceC1851jh0, ThreadingModule threadingModule, ExecutorService executorService) {
        a(map.entrySet(), new InterfaceC2022lh0() { // from class: gdg
            @Override // com.android.tools.r8.internal.InterfaceC2022lh0
            public final Object apply(Object obj) {
                Map.Entry entry = (Map.Entry) obj;
                return interfaceC1851jh0.apply(entry.getKey(), entry.getValue());
            }
        }, threadingModule, executorService);
    }

    public static void a(C0333y c0333y, final InterfaceC1936kh0 interfaceC1936kh0, ThreadingModule threadingModule, ExecutorService executorService) throws ExecutionException {
        a(c0333y.g().d(), new Consumer() { // from class: ycg
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C1086ah0.a(interfaceC1936kh0, (D2) obj);
            }
        }, threadingModule, executorService);
    }

    public static /* synthetic */ void a(final InterfaceC1936kh0 interfaceC1936kh0, com.android.tools.r8.graph.D2 d2) {
        Objects.requireNonNull(interfaceC1936kh0);
        d2.n(new Consumer() { // from class: xcg
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                interfaceC1936kh0.a((B5) obj);
            }
        });
    }

    public static ExecutorService a(C2752uB c2752uB) {
        int i = c2752uB.g0;
        ThreadingModule threadingModuleN = c2752uB.N();
        if (i == -1) {
            int iAvailableProcessors = Runtime.getRuntime().availableProcessors();
            if (iAvailableProcessors > 2) {
                iAvailableProcessors = (int) Math.ceil(((double) Integer.min(iAvailableProcessors, 16)) / 2.0d);
            }
            return threadingModuleN.createThreadedExecutorService(iAvailableProcessors);
        }
        return threadingModuleN.createThreadedExecutorService(i);
    }

    public static int a(ExecutorService executorService) {
        if (executorService instanceof ForkJoinPool) {
            return ((ForkJoinPool) executorService).getParallelism();
        }
        return -1;
    }
}
