package io.github.rosemoe.sora.lang.completion;

import android.os.Handler;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class CompletionPublisher {
    public static final int DEFAULT_UPDATE_THRESHOLD = 5;
    private final Runnable callback;
    private Comparator<CompletionItem> comparator;
    private final Handler handler;
    private final int languageInterruptionLevel;
    private boolean invalid = false;
    private final List<CompletionItem> items = new ArrayList();
    private final List<CompletionItem> candidates = new ArrayList();
    private final Lock lock = new ReentrantLock(true);
    private int updateThreshold = 5;

    public CompletionPublisher(Handler handler, Runnable runnable, int i) {
        this.handler = handler;
        this.callback = runnable;
        this.languageInterruptionLevel = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setComparator$0(Comparator comparator) {
        if (this.invalid) {
            return;
        }
        Collections.sort(this.items, comparator);
        this.callback.run();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateList$1(boolean z) {
        boolean zTryLock;
        if (this.invalid) {
            this.callback.run();
            return;
        }
        Lock lock = this.lock;
        if (z) {
            lock.lock();
            zTryLock = true;
        } else {
            zTryLock = lock.tryLock();
        }
        if (zTryLock) {
            try {
                if (this.candidates.isEmpty()) {
                    this.callback.run();
                    this.lock.unlock();
                    return;
                }
                Comparator<CompletionItem> comparator = this.comparator;
                if (comparator != null) {
                    while (!this.candidates.isEmpty()) {
                        CompletionItem completionItemRemove = this.candidates.remove(0);
                        int size = this.items.size();
                        int i = 0;
                        int i2 = size;
                        while (i <= i2) {
                            int i3 = (i + i2) / 2;
                            if (i3 >= 0 && i3 < size) {
                                int iCompare = comparator.compare(this.items.get(i3), completionItemRemove);
                                if (iCompare < 0) {
                                    i = i3 + 1;
                                } else if (iCompare > 0) {
                                    i2 = i3 - 1;
                                }
                            }
                            i = i3;
                            break;
                        }
                        this.items.add(Math.max(0, Math.min(size, i)), completionItemRemove);
                    }
                } else {
                    this.items.addAll(this.candidates);
                    this.candidates.clear();
                }
                this.callback.run();
                this.lock.unlock();
            } catch (Throwable th) {
                this.lock.unlock();
                throw th;
            }
        }
    }

    public void addItem(CompletionItem completionItem) {
        checkCancelled();
        if (this.invalid) {
            return;
        }
        this.lock.lock();
        try {
            this.candidates.add(completionItem);
            this.lock.unlock();
            if (this.candidates.size() >= this.updateThreshold) {
                updateList();
            }
        } catch (Throwable th) {
            this.lock.unlock();
            throw th;
        }
    }

    public void addItems(Collection<CompletionItem> collection) {
        checkCancelled();
        if (this.invalid) {
            return;
        }
        this.lock.lock();
        try {
            this.candidates.addAll(collection);
            this.lock.unlock();
            if (this.candidates.size() >= this.updateThreshold) {
                updateList();
            }
        } catch (Throwable th) {
            this.lock.unlock();
            throw th;
        }
    }

    public void cancel() {
        this.invalid = true;
    }

    public void checkCancelled() {
        if (Thread.interrupted() || this.invalid) {
            this.invalid = true;
            if (this.languageInterruptionLevel <= 1) {
                throw new CompletionCancelledException();
            }
        }
    }

    public List<CompletionItem> getItems() {
        return this.items;
    }

    public boolean hasData() {
        return this.items.size() + this.candidates.size() > 0;
    }

    public void setComparator(final Comparator<CompletionItem> comparator) {
        checkCancelled();
        if (this.invalid) {
            return;
        }
        this.comparator = comparator;
        if (this.items.isEmpty() || comparator == null) {
            return;
        }
        this.handler.post(new Runnable() { // from class: xa2
            @Override // java.lang.Runnable
            public final void run() {
                this.b.lambda$setComparator$0(comparator);
            }
        });
    }

    public void setUpdateThreshold(int i) {
        this.updateThreshold = i;
    }

    public void updateList(final boolean z) {
        if (this.invalid) {
            return;
        }
        this.handler.post(new Runnable() { // from class: ya2
            @Override // java.lang.Runnable
            public final void run() {
                this.b.lambda$updateList$1(z);
            }
        });
    }

    public void updateList() {
        updateList(false);
    }
}
