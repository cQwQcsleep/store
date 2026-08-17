package com.intellij.util;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.progress.ProcessCanceledException;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.project.IndexNotReadyException;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class ExecutorsQuery<Result, Parameter> extends AbstractQuery<Result> {
    private static final Logger LOG = Logger.getInstance(ExecutorsQuery.class);
    private final List<? extends QueryExecutor<Result, Parameter>> myExecutors;
    private final Parameter myParameters;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i == 1) {
            objArr[0] = "executors";
        } else if (i == 2) {
            objArr[0] = "consumer";
        } else if (i == 3) {
            objArr[0] = "wrapper";
        } else if (i != 4) {
            objArr[0] = "params";
        } else {
            objArr[0] = "interceptor";
        }
        objArr[1] = "com/intellij/util/ExecutorsQuery";
        if (i == 2) {
            objArr[2] = "processResults";
        } else if (i == 3) {
            objArr[2] = "wrap";
        } else if (i != 4) {
            objArr[2] = "<init>";
        } else {
            objArr[2] = "interceptWith";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public ExecutorsQuery(Parameter parameter, List<? extends QueryExecutor<Result, Parameter>> list) {
        if (parameter == null) {
            $$$reportNull$$$0(0);
        }
        if (list == null) {
            $$$reportNull$$$0(1);
        }
        this.myParameters = parameter;
        this.myExecutors = list;
    }

    @Override // com.intellij.util.AbstractQuery
    public boolean processResults(Processor<? super Result> processor) {
        if (processor == null) {
            $$$reportNull$$$0(2);
        }
        for (QueryExecutor<Result, Parameter> queryExecutor : this.myExecutors) {
            try {
                ProgressManager.checkCanceled();
                if (!queryExecutor.execute(this.myParameters, processor)) {
                    return false;
                }
            } catch (Exception e) {
                LOG.error(e);
            } catch (IndexNotReadyException unused) {
            } catch (ProcessCanceledException e2) {
                throw e2;
            }
        }
        return true;
    }
}
