package org.jetbrains.kotlin.resolve.calls.util;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.psi.KtLambdaExpression;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\b\u001a\u00020\tH\u0096\u0080\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/resolve/calls/util/BuilderLambdaLabelingInfo;", "", "builderLambda", "Lorg/jetbrains/kotlin/psi/KtLambdaExpression;", "<init>", "(Lorg/jetbrains/kotlin/psi/KtLambdaExpression;)V", "getBuilderLambda", "()Lorg/jetbrains/kotlin/psi/KtLambdaExpression;", "toString", "", "Companion", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final class BuilderLambdaLabelingInfo {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final BuilderLambdaLabelingInfo EMPTY = new BuilderLambdaLabelingInfo(null);
    private final KtLambdaExpression builderLambda;

    public BuilderLambdaLabelingInfo(KtLambdaExpression ktLambdaExpression) {
        this.builderLambda = ktLambdaExpression;
    }

    public final KtLambdaExpression getBuilderLambda() {
        return this.builderLambda;
    }

    public String toString() {
        return String.valueOf(this.builderLambda);
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/resolve/calls/util/BuilderLambdaLabelingInfo$Companion;", "", "<init>", "()V", "EMPTY", "Lorg/jetbrains/kotlin/resolve/calls/util/BuilderLambdaLabelingInfo;", "getEMPTY", "()Lorg/jetbrains/kotlin/resolve/calls/util/BuilderLambdaLabelingInfo;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final BuilderLambdaLabelingInfo getEMPTY() {
            return BuilderLambdaLabelingInfo.EMPTY;
        }

        private Companion() {
        }
    }
}
