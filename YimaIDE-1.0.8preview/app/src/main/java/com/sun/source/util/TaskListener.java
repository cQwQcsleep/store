package com.sun.source.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface TaskListener {
    default void finished(TaskEvent taskEvent) {
    }

    default void started(TaskEvent taskEvent) {
    }
}
