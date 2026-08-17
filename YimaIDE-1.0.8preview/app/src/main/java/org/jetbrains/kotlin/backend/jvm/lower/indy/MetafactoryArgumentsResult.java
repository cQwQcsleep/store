package org.jetbrains.kotlin.backend.jvm.lower.indy;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0002\u0007\bB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0006\u0082\u0001\u0002\t\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/lower/indy/MetafactoryArgumentsResult;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "isSuccess", "", "()Z", "Success", "Failure", "Lorg/jetbrains/kotlin/backend/jvm/lower/indy/MetafactoryArgumentsResult$Failure;", "Lorg/jetbrains/kotlin/backend/jvm/lower/indy/MetafactoryArgumentsResult$Success;", "org.jetbrains.kotlin:backend.jvm.lower"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class MetafactoryArgumentsResult {

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\b&\u0018\u00002\u00020\u0001:\u0004\u0007\b\t\nB\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0006¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/lower/indy/MetafactoryArgumentsResult$Failure;", "Lorg/jetbrains/kotlin/backend/jvm/lower/indy/MetafactoryArgumentsResult;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "isSuccess", "", "()Z", "LambdaMetafactorySemanticsHazard", "LambdaMetafactoryAbiHazard", "InliningHazard", "FunctionHazard", "org.jetbrains.kotlin:backend.jvm.lower"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static abstract class Failure extends MetafactoryArgumentsResult {

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/lower/indy/MetafactoryArgumentsResult$Failure$FunctionHazard;", "Lorg/jetbrains/kotlin/backend/jvm/lower/indy/MetafactoryArgumentsResult$Failure;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:backend.jvm.lower"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class FunctionHazard extends Failure {
            public static final FunctionHazard INSTANCE = new FunctionHazard();

            private FunctionHazard() {
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/lower/indy/MetafactoryArgumentsResult$Failure$InliningHazard;", "Lorg/jetbrains/kotlin/backend/jvm/lower/indy/MetafactoryArgumentsResult$Failure;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:backend.jvm.lower"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class InliningHazard extends Failure {
            public static final InliningHazard INSTANCE = new InliningHazard();

            private InliningHazard() {
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/lower/indy/MetafactoryArgumentsResult$Failure$LambdaMetafactoryAbiHazard;", "Lorg/jetbrains/kotlin/backend/jvm/lower/indy/MetafactoryArgumentsResult$Failure;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:backend.jvm.lower"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class LambdaMetafactoryAbiHazard extends Failure {
            public static final LambdaMetafactoryAbiHazard INSTANCE = new LambdaMetafactoryAbiHazard();

            private LambdaMetafactoryAbiHazard() {
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/lower/indy/MetafactoryArgumentsResult$Failure$LambdaMetafactorySemanticsHazard;", "Lorg/jetbrains/kotlin/backend/jvm/lower/indy/MetafactoryArgumentsResult$Failure;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:backend.jvm.lower"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class LambdaMetafactorySemanticsHazard extends Failure {
            public static final LambdaMetafactorySemanticsHazard INSTANCE = new LambdaMetafactorySemanticsHazard();

            private LambdaMetafactorySemanticsHazard() {
            }
        }

        public Failure() {
            super(null);
        }

        @Override // org.jetbrains.kotlin.backend.jvm.lower.indy.MetafactoryArgumentsResult
        public boolean isSuccess() {
            return false;
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/lower/indy/MetafactoryArgumentsResult$Success;", "Lorg/jetbrains/kotlin/backend/jvm/lower/indy/MetafactoryArgumentsResult;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "isSuccess", "", "()Z", "org.jetbrains.kotlin:backend.jvm.lower"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static abstract class Success extends MetafactoryArgumentsResult {
        public Success() {
            super(null);
        }

        @Override // org.jetbrains.kotlin.backend.jvm.lower.indy.MetafactoryArgumentsResult
        public boolean isSuccess() {
            return true;
        }
    }

    public /* synthetic */ MetafactoryArgumentsResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract boolean isSuccess();

    private MetafactoryArgumentsResult() {
    }
}
