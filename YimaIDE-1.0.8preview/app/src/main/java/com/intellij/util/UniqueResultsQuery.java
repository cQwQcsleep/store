package com.intellij.util;

import androidx.collection.ScatterMapKt;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.util.containers.CollectionFactory;
import com.intellij.util.containers.HashingStrategy;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class UniqueResultsQuery<T, M> extends AbstractQuery<T> {
    private final HashingStrategy<? super M> myHashingStrategy;
    private final Function<? super T, ? extends M> myMapper;
    private final Query<? extends T> myOriginal;

    public final class MyProcessor implements Processor<T> {
        private final Processor<? super T> myConsumer;
        private final Set<? super M> myProcessedElements;
        final /* synthetic */ UniqueResultsQuery this$0;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i != 1) {
                objArr[0] = "processedElements";
            } else {
                objArr[0] = "consumer";
            }
            objArr[1] = "com/intellij/util/UniqueResultsQuery$MyProcessor";
            objArr[2] = "<init>";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        public MyProcessor(UniqueResultsQuery uniqueResultsQuery, Set<? super M> set, Processor<? super T> processor) {
            if (set == null) {
                $$$reportNull$$$0(0);
            }
            if (processor == null) {
                $$$reportNull$$$0(1);
            }
            this.this$0 = uniqueResultsQuery;
            this.myProcessedElements = set;
            this.myConsumer = processor;
        }

        @Override // com.intellij.util.Processor
        public boolean process(T t) {
            ProgressManager.checkCanceled();
            Object objFun = this.this$0.myMapper.fun(t);
            if (this.myProcessedElements.contains(objFun)) {
                return true;
            }
            boolean zProcess = this.myConsumer.process(t);
            this.myProcessedElements.add(objFun);
            return zProcess;
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 6 || i == 7 || i == 9) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 6 || i == 7 || i == 9) ? 2 : 3];
        switch (i) {
            case 2:
                objArr[0] = "hashingStrategy";
                break;
            case 3:
            default:
                objArr[0] = "original";
                break;
            case 4:
                objArr[0] = "mapper";
                break;
            case 5:
            case 8:
                objArr[0] = "consumer";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 9:
                objArr[0] = "com/intellij/util/UniqueResultsQuery";
                break;
        }
        if (i == 6 || i == 7) {
            objArr[1] = "createSet";
        } else if (i != 9) {
            objArr[1] = "com/intellij/util/UniqueResultsQuery";
        } else {
            objArr[1] = "forEachAsync";
        }
        switch (i) {
            case 5:
                objArr[2] = "processResults";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 9:
                break;
            case 8:
                objArr[2] = "forEachAsync";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 6 && i != 7 && i != 9) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public UniqueResultsQuery(Query<? extends T> query, HashingStrategy<? super M> hashingStrategy) {
        if (query == null) {
            $$$reportNull$$$0(1);
        }
        if (hashingStrategy == null) {
            $$$reportNull$$$0(2);
        }
        this.myOriginal = query;
        this.myHashingStrategy = hashingStrategy;
        this.myMapper = Functions.identity();
    }

    private Set<M> createSet() {
        HashingStrategy<? super M> hashingStrategy = this.myHashingStrategy;
        if (hashingStrategy == null) {
            Set<M> setSynchronizedSet = Collections.synchronizedSet(new HashSet());
            if (setSynchronizedSet == null) {
                $$$reportNull$$$0(6);
            }
            return setSynchronizedSet;
        }
        Set<M> setSynchronizedSet2 = Collections.synchronizedSet(CollectionFactory.createCustomHashingStrategySet(hashingStrategy));
        if (setSynchronizedSet2 == null) {
            $$$reportNull$$$0(7);
        }
        return setSynchronizedSet2;
    }

    @Override // com.intellij.util.AbstractQuery
    public boolean processResults(Processor<? super T> processor) {
        if (processor == null) {
            $$$reportNull$$$0(5);
        }
        return AbstractQuery.delegateProcessResults(this.myOriginal, new MyProcessor(this, createSet(), processor));
    }

    public String toString() {
        return "UniqueQuery: " + this.myOriginal;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public UniqueResultsQuery(Query<? extends T> query) {
        this(query, Functions.identity());
        if (query == null) {
            $$$reportNull$$$0(0);
        }
    }

    public UniqueResultsQuery(Query<? extends T> query, Function<? super T, ? extends M> function) {
        if (query == null) {
            $$$reportNull$$$0(3);
        }
        if (function == null) {
            $$$reportNull$$$0(4);
        }
        this.myOriginal = query;
        this.myHashingStrategy = null;
        this.myMapper = function;
    }
}
