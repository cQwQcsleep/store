package org.jetbrains.kotlin.fir.resolve.calls.stages;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0096@R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/stages/CheckIncompatibleTypeVariableUpperBounds;", "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ResolutionStage;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;", "sink", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "context", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CheckIncompatibleTypeVariableUpperBounds extends ResolutionStage {
    public static final CheckIncompatibleTypeVariableUpperBounds INSTANCE = new CheckIncompatibleTypeVariableUpperBounds();

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.calls.stages.CheckIncompatibleTypeVariableUpperBounds$check$1, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CheckIncompatibleTypeVariableUpperBounds.this.check(null, null, null, this);
        }
    }

    private CheckIncompatibleTypeVariableUpperBounds() {
    }

    /* JADX WARN: Code duplicated, block: B:17:0x008f  */
    /* JADX WARN: Code duplicated, block: B:21:0x00ad  */
    /* JADX WARN: Code duplicated, block: B:27:0x00c1  */
    /* JADX WARN: Code duplicated, block: B:35:0x00ea  */
    /* JADX WARN: Code duplicated, block: B:38:0x00f4  */
    /* JADX WARN: Code duplicated, block: B:41:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:51:0x0191 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:52:0x0192  */
    /* JADX WARN: Code duplicated, block: B:58:0x00a3 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:59:0x00e0 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:61:0x0154 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:63:0x00d2 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:66:0x0089 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:68:0x0089 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:71:0x010c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:73:0x00f8 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:75:? A[LOOP:2: B:25:0x00bb->B:75:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0015  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:52:0x0192 -> B:53:0x019a). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // org.jetbrains.kotlin.fir.resolve.calls.stages.ResolutionStage
    public java.lang.Object check(org.jetbrains.kotlin.fir.resolve.calls.candidate.CheckerSink r18, org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext r19, org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate r20, kotlin.coroutines.Continuation<? super kotlin.Unit> r21) {
        /*
            Method dump skipped, instruction units count: 428
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.kotlin.fir.resolve.calls.stages.CheckIncompatibleTypeVariableUpperBounds.check(org.jetbrains.kotlin.fir.resolve.calls.candidate.CheckerSink, org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext, org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
