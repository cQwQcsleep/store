package com.intellij.util;

import androidx.collection.ScatterMapKt;
import com.intellij.util.containers.ContainerUtil;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public class QueryFactory<Result, Parameters> {
    private final List<QueryExecutor<Result, Parameters>> myExecutors = ContainerUtil.createLockFreeCopyOnWriteList();

    /* JADX WARN: Code duplicated, block: B:22:0x002d  */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 3 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 3 ? 3 : 2];
        if (i == 2) {
            objArr[0] = "parameters";
        } else if (i == 3) {
            objArr[0] = "com/intellij/util/QueryFactory";
        } else if (i == 4 || i == 5) {
            objArr[0] = "parameters";
        } else if (i != 6) {
            objArr[0] = "executor";
        } else {
            objArr[0] = "mapper";
        }
        if (i != 3) {
            objArr[1] = "com/intellij/util/QueryFactory";
        } else {
            objArr[1] = "getExecutors";
        }
        switch (i) {
            case 1:
                objArr[2] = "unregisterExecutor";
                break;
            case 2:
                objArr[2] = "createQuery";
                break;
            case 3:
                break;
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[2] = "createUniqueResultsQuery";
                break;
            default:
                objArr[2] = "registerExecutor";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i == 3) {
            throw new IllegalStateException(str2);
        }
    }

    public final Query<Result> createQuery(Parameters parameters) {
        if (parameters == null) {
            $$$reportNull$$$0(2);
        }
        return new ExecutorsQuery(parameters, getExecutors());
    }

    public final <T> Query<Result> createUniqueResultsQuery(Parameters parameters, Function<? super Result, ? extends T> function) {
        if (parameters == null) {
            $$$reportNull$$$0(5);
        }
        if (function == null) {
            $$$reportNull$$$0(6);
        }
        return new UniqueResultsQuery(createQuery(parameters), function);
    }

    public List<QueryExecutor<Result, Parameters>> getExecutors() {
        List<QueryExecutor<Result, Parameters>> list = this.myExecutors;
        if (list == null) {
            $$$reportNull$$$0(3);
        }
        return list;
    }

    public boolean hasAnyExecutors() {
        return !getExecutors().isEmpty();
    }

    public void registerExecutor(QueryExecutor<Result, Parameters> queryExecutor) {
        if (queryExecutor == null) {
            $$$reportNull$$$0(0);
        }
        this.myExecutors.add(queryExecutor);
    }

    public void unregisterExecutor(QueryExecutor<Result, Parameters> queryExecutor) {
        if (queryExecutor == null) {
            $$$reportNull$$$0(1);
        }
        this.myExecutors.remove(queryExecutor);
    }

    public final Query<Result> createUniqueResultsQuery(Parameters parameters) {
        if (parameters == null) {
            $$$reportNull$$$0(4);
        }
        return new UniqueResultsQuery(createQuery(parameters));
    }
}
