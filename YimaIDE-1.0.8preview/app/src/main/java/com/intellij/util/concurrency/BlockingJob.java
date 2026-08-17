package com.intellij.util.concurrency;

import com.intellij.concurrency.IntelliJContextElement;
import kotlin.Metadata;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.Job;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0007\u0018\u0000 \u000e2\u00020\u00012\u00020\u0002:\u0001\u000eB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0018\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000f"}, d2 = {"Lcom/intellij/util/concurrency/BlockingJob;", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "Lcom/intellij/concurrency/IntelliJContextElement;", "blockingJob", "Lkotlinx/coroutines/Job;", "<init>", "(Lkotlinx/coroutines/Job;)V", "getBlockingJob", "()Lkotlinx/coroutines/Job;", "produceChildElement", "parentContext", "Lkotlin/coroutines/CoroutineContext;", "isStructured", "", "Companion", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class BlockingJob extends AbstractCoroutineContextElement implements IntelliJContextElement {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Job blockingJob;

    public final Job getBlockingJob() {
        return this.blockingJob;
    }

    @Override // com.intellij.concurrency.IntelliJContextElement
    public IntelliJContextElement produceChildElement(CoroutineContext parentContext, boolean isStructured) {
        parentContext.getClass();
        return this;
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/intellij/util/concurrency/BlockingJob$Companion;", "Lkotlin/coroutines/CoroutineContext$Key;", "Lcom/intellij/util/concurrency/BlockingJob;", "<init>", "()V", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion implements CoroutineContext.Key<BlockingJob> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
