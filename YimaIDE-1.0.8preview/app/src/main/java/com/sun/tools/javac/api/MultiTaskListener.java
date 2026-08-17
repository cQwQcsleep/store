package com.sun.tools.javac.api;

import com.sun.source.util.TaskEvent;
import com.sun.source.util.TaskListener;
import com.sun.tools.javac.code.DeferredCompletionFailureHandler;
import com.sun.tools.javac.util.Context;
import java.util.Arrays;
import java.util.Collection;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class MultiTaskListener implements TaskListener {
    ClientCodeWrapper ccw;
    private final DeferredCompletionFailureHandler dcfh;
    TaskListener[] listeners = EMPTY_LISTENERS;
    public static final Context.Key<MultiTaskListener> taskListenerKey = new Context.Key<>();
    private static final TaskListener[] EMPTY_LISTENERS = new TaskListener[0];

    public MultiTaskListener(Context context) {
        context.put(taskListenerKey, this);
        this.ccw = ClientCodeWrapper.instance(context);
        this.dcfh = DeferredCompletionFailureHandler.instance(context);
    }

    public static MultiTaskListener instance(Context context) {
        MultiTaskListener multiTaskListener = (MultiTaskListener) context.get(taskListenerKey);
        return multiTaskListener == null ? new MultiTaskListener(context) : multiTaskListener;
    }

    public void add(TaskListener taskListener) {
        for (TaskListener taskListener2 : this.listeners) {
            if (this.ccw.unwrap(taskListener2) == taskListener) {
                g33.a();
                return;
            }
        }
        TaskListener[] taskListenerArr = this.listeners;
        TaskListener[] taskListenerArr2 = (TaskListener[]) Arrays.copyOf(taskListenerArr, taskListenerArr.length + 1);
        this.listeners = taskListenerArr2;
        taskListenerArr2[taskListenerArr2.length - 1] = this.ccw.wrap(taskListener);
    }

    public void clear() {
        this.listeners = EMPTY_LISTENERS;
    }

    @Override // com.sun.source.util.TaskListener
    public void finished(TaskEvent taskEvent) {
        DeferredCompletionFailureHandler deferredCompletionFailureHandler = this.dcfh;
        DeferredCompletionFailureHandler.Handler handler = deferredCompletionFailureHandler.setHandler(deferredCompletionFailureHandler.userCodeHandler);
        try {
            for (TaskListener taskListener : this.listeners) {
                taskListener.finished(taskEvent);
            }
            this.dcfh.setHandler(handler);
        } catch (Throwable th) {
            this.dcfh.setHandler(handler);
            throw th;
        }
    }

    public Collection<TaskListener> getTaskListeners() {
        return Arrays.asList(this.listeners);
    }

    public boolean isEmpty() {
        return this.listeners == EMPTY_LISTENERS;
    }

    public void remove(TaskListener taskListener) {
        int i = 0;
        while (true) {
            TaskListener[] taskListenerArr = this.listeners;
            if (i >= taskListenerArr.length) {
                return;
            }
            if (this.ccw.unwrap(taskListenerArr[i]) == taskListener) {
                TaskListener[] taskListenerArr2 = this.listeners;
                if (taskListenerArr2.length == 1) {
                    this.listeners = EMPTY_LISTENERS;
                    return;
                }
                int length = taskListenerArr2.length - 1;
                TaskListener[] taskListenerArr3 = new TaskListener[length];
                System.arraycopy(taskListenerArr2, 0, taskListenerArr3, 0, i);
                System.arraycopy(this.listeners, i + 1, taskListenerArr3, i, length - i);
                this.listeners = taskListenerArr3;
                return;
            }
            i++;
        }
    }

    @Override // com.sun.source.util.TaskListener
    public void started(TaskEvent taskEvent) {
        DeferredCompletionFailureHandler deferredCompletionFailureHandler = this.dcfh;
        DeferredCompletionFailureHandler.Handler handler = deferredCompletionFailureHandler.setHandler(deferredCompletionFailureHandler.userCodeHandler);
        try {
            for (TaskListener taskListener : this.listeners) {
                taskListener.started(taskEvent);
            }
            this.dcfh.setHandler(handler);
        } catch (Throwable th) {
            this.dcfh.setHandler(handler);
            throw th;
        }
    }

    public String toString() {
        return Arrays.toString(this.listeners);
    }
}
