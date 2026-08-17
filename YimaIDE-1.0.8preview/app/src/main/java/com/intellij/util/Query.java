package com.intellij.util;

import androidx.collection.ScatterMapKt;
import com.intellij.util.Query;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public interface Query<Result> extends Iterable<Result> {
    /* JADX WARN: Code duplicated, block: B:10:0x0017  */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        if (i != 1 && i != 3 && i != 7 && i != 9 && i != 11) {
            switch (i) {
                case 13:
                case 14:
                case 15:
                    str = "@NotNull method %s.%s must not return null";
                    break;
                default:
                    str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                    break;
            }
        } else {
            str = "@NotNull method %s.%s must not return null";
        }
        if (i != 1 && i != 3 && i != 7 && i != 9 && i != 11) {
            switch (i) {
                case 13:
                case 14:
                case 15:
                    i2 = 2;
                    break;
                default:
                    i2 = 3;
                    break;
            }
        } else {
            i2 = 2;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 1:
            case 3:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 9:
            case 11:
            case 13:
            case 14:
            case 15:
                objArr[0] = "com/intellij/util/Query";
                break;
            case 2:
                objArr[0] = "a";
                break;
            case 4:
            case 5:
            case 10:
                objArr[0] = "predicate";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[0] = "transformation";
                break;
            case 8:
            case 12:
                objArr[0] = "mapper";
                break;
            case 16:
                objArr[0] = "interceptor";
                break;
            case 17:
                objArr[0] = "wrapper";
                break;
            default:
                objArr[0] = "consumer";
                break;
        }
        if (i == 1) {
            objArr[1] = "forEachAsync";
        } else if (i == 3) {
            objArr[1] = "toArray";
        } else if (i == 7) {
            objArr[1] = "transforming";
        } else if (i == 9) {
            objArr[1] = "mapping";
        } else if (i != 11) {
            switch (i) {
                case 13:
                    objArr[1] = "flatMapping";
                    break;
                case 14:
                    objArr[1] = "allowParallelProcessing";
                    break;
                case 15:
                    objArr[1] = "iterator";
                    break;
                default:
                    objArr[1] = "com/intellij/util/Query";
                    break;
            }
        } else {
            objArr[1] = "filtering";
        }
        switch (i) {
            case 1:
            case 3:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 9:
            case 11:
            case 13:
            case 14:
            case 15:
                break;
            case 2:
                objArr[2] = "toArray";
                break;
            case 4:
                objArr[2] = "allMatch";
                break;
            case 5:
                objArr[2] = "anyMatch";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[2] = "transforming";
                break;
            case 8:
                objArr[2] = "mapping";
                break;
            case 10:
                objArr[2] = "filtering";
                break;
            case 12:
                objArr[2] = "flatMapping";
                break;
            case 16:
                objArr[2] = "interceptWith";
                break;
            case 17:
                objArr[2] = "wrap";
                break;
            default:
                objArr[2] = "forEachAsync";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 1 && i != 3 && i != 7 && i != 9 && i != 11) {
            switch (i) {
                case 13:
                case 14:
                case 15:
                    break;
                default:
                    throw new IllegalArgumentException(str2);
            }
        }
        throw new IllegalStateException(str2);
    }

    static /* synthetic */ Collection Ya(Predicate predicate, Object obj) {
        return predicate.test(obj) ? Collections.singletonList(obj) : Collections.EMPTY_LIST;
    }

    @Deprecated
    default Iterable<Result> asIterable() {
        return findAll();
    }

    default Query<Result> filtering(final Predicate<? super Result> predicate) {
        if (predicate == null) {
            $$$reportNull$$$0(10);
        }
        Query<Result> query = (Query<Result>) transforming(new java.util.function.Function() { // from class: j1c
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Query.Ya(predicate, obj);
            }
        });
        if (query == null) {
            $$$reportNull$$$0(11);
        }
        return query;
    }

    Collection<Result> findAll();

    Result findFirst();

    @Override // java.lang.Iterable
    @Deprecated
    default void forEach(java.util.function.Consumer<? super Result> consumer) {
        asIterable().forEach(consumer);
    }

    boolean forEach(Processor<? super Result> processor);

    @Override // java.lang.Iterable
    @Deprecated
    default Iterator<Result> iterator() {
        Iterator<Result> it = asIterable().iterator();
        if (it == null) {
            $$$reportNull$$$0(15);
        }
        return it;
    }

    @Override // java.lang.Iterable
    @Deprecated
    default Spliterator<Result> spliterator() {
        return asIterable().spliterator();
    }

    default <R> Query<R> transforming(java.util.function.Function<? super Result, ? extends Collection<? extends R>> function) {
        if (function == null) {
            $$$reportNull$$$0(6);
        }
        Query<R> queryTransforming = Queries.getInstance().transforming(this, function);
        if (queryTransforming == null) {
            $$$reportNull$$$0(7);
        }
        return queryTransforming;
    }
}
