package com.intellij.util;

import androidx.collection.ScatterMapKt;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.util.AbstractQuery;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public abstract class AbstractQuery<Result> implements Query<Result> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Comparator<Object> CRAZY_ORDER;
    private static final boolean RANDOMIZE;
    private final ThreadLocal<Boolean> myIsProcessing = new ThreadLocal<>();

    /* JADX WARN: Code duplicated, block: B:28:0x003b  */
    /* JADX WARN: Code duplicated, block: B:29:0x0041  */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 1 || i == 3 || i == 4 || i == 5 || i == 6 || i == 7) ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[(i == 1 || i == 3 || i == 4 || i == 5 || i == 6 || i == 7) ? 3 : 2];
        if (i == 1 || i == 3 || i == 4) {
            objArr[0] = "consumer";
        } else if (i == 5) {
            objArr[0] = "query";
        } else if (i == 6) {
            objArr[0] = "consumer";
        } else if (i != 7) {
            objArr[0] = "com/intellij/util/AbstractQuery";
        } else {
            objArr[0] = "query";
        }
        switch (i) {
            case 1:
            case 3:
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[1] = "com/intellij/util/AbstractQuery";
                break;
            case 2:
                objArr[1] = "threadSafeProcessor";
                break;
            default:
                objArr[1] = "findAll";
                break;
        }
        if (i == 1) {
            objArr[2] = "threadSafeProcessor";
        } else if (i == 3) {
            objArr[2] = "forEach";
        } else if (i == 4) {
            objArr[2] = "doProcessResults";
        } else if (i == 5 || i == 6) {
            objArr[2] = "delegateProcessResults";
        } else if (i == 7) {
            objArr[2] = "wrapInReadAction";
        }
        String str2 = String.format(str, objArr);
        if (i != 1 && i != 3 && i != 4 && i != 5 && i != 6 && i != 7) {
            throw new IllegalStateException(str2);
        }
        throw new IllegalArgumentException(str2);
    }

    static {
        RANDOMIZE = ApplicationManager.getApplication().isUnitTestMode() || ApplicationManager.getApplication().isInternal();
        CRAZY_ORDER = new Comparator() { // from class: up
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return AbstractQuery.b(obj, obj2);
            }
        };
    }

    public static /* synthetic */ boolean a(Object obj, Processor processor, Object obj2) {
        boolean zProcess;
        synchronized (obj) {
            zProcess = processor.process(obj2);
        }
        return zProcess;
    }

    private void assertNotProcessing() {
    }

    public static /* synthetic */ int b(Object obj, Object obj2) {
        return -Integer.compare(System.identityHashCode(obj), System.identityHashCode(obj2));
    }

    public static <T> boolean delegateProcessResults(Query<T> query, Processor<? super T> processor) {
        if (query == null) {
            $$$reportNull$$$0(5);
        }
        if (processor == null) {
            $$$reportNull$$$0(6);
        }
        return query instanceof AbstractQuery ? ((AbstractQuery) query).doProcessResults(processor) : query.forEach(processor);
    }

    private boolean doProcessResults(Processor<? super Result> processor) {
        if (processor == null) {
            $$$reportNull$$$0(4);
        }
        assertNotProcessing();
        this.myIsProcessing.set(Boolean.TRUE);
        try {
            return processResults(processor);
        } finally {
            this.myIsProcessing.remove();
        }
    }

    private Processor<Result> threadSafeProcessor(final Processor<? super Result> processor) {
        if (processor == null) {
            $$$reportNull$$$0(1);
        }
        final Object objSentinel = ObjectUtils.sentinel("AbstractQuery lock");
        return new Processor() { // from class: tp
            @Override // com.intellij.util.Processor
            public final boolean process(Object obj) {
                return AbstractQuery.a(objSentinel, processor, obj);
            }
        };
    }

    @Override // com.intellij.util.Query
    public Collection<Result> findAll() {
        assertNotProcessing();
        ArrayList arrayList = new ArrayList();
        forEach(Processors.cancelableCollectProcessor(arrayList));
        if (RANDOMIZE && arrayList.size() > 1) {
            arrayList.sort(CRAZY_ORDER);
        }
        return arrayList;
    }

    @Override // com.intellij.util.Query
    public Result findFirst() {
        assertNotProcessing();
        CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
        forEach(findFirstProcessor);
        return (Result) findFirstProcessor.getFoundValue();
    }

    @Override // com.intellij.util.Query
    public boolean forEach(Processor<? super Result> processor) {
        if (processor == null) {
            $$$reportNull$$$0(3);
        }
        return doProcessResults(threadSafeProcessor(processor));
    }

    @Override // com.intellij.util.Query, java.lang.Iterable
    public Iterator<Result> iterator() {
        assertNotProcessing();
        return new UnmodifiableIterator(super.iterator());
    }

    public abstract boolean processResults(Processor<? super Result> processor);
}
