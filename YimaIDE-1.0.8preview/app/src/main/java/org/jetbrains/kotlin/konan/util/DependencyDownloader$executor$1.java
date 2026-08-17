package org.jetbrains.kotlin.konan.util;

import java.util.concurrent.ThreadFactory;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u00012\u000e\u0010\u0003\u001a\n \u0002*\u0004\u0018\u00010\u00040\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Ljava/lang/Thread;", "kotlin.jvm.PlatformType", "r", "Ljava/lang/Runnable;", "newThread"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class DependencyDownloader$executor$1 implements ThreadFactory {
    public static final DependencyDownloader$executor$1 INSTANCE = new DependencyDownloader$executor$1();

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setName("konan-dependency-downloader");
        thread.setDaemon(true);
        return thread;
    }
}
