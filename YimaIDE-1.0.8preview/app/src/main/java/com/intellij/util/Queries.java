package com.intellij.util;

import com.intellij.openapi.application.ApplicationManager;
import java.util.Collection;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public abstract class Queries {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/intellij/util/Queries", "getInstance"));
    }

    public static Queries getInstance() {
        Queries queries = (Queries) ApplicationManager.getApplication().getService(Queries.class);
        if (queries == null) {
            $$$reportNull$$$0(0);
        }
        return queries;
    }

    public abstract <I, O> Query<O> transforming(Query<? extends I> query, java.util.function.Function<? super I, ? extends Collection<? extends O>> function);
}
