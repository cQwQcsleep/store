package org.jetbrains.kotlin.backend.konan;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.sequences.Sequence;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002:\u0002\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0003\u0010\u0004\u0082\u0001\u0002\u0007\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/backend/konan/BinaryType;", "T", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "Primitive", "Reference", "Lorg/jetbrains/kotlin/backend/konan/BinaryType$Primitive;", "Lorg/jetbrains/kotlin/backend/konan/BinaryType$Reference;", "org.jetbrains.kotlin:base"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class BinaryType<T> {

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/backend/konan/BinaryType$Primitive;", "Lorg/jetbrains/kotlin/backend/konan/BinaryType;", "", "type", "Lorg/jetbrains/kotlin/backend/konan/PrimitiveBinaryType;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/backend/konan/PrimitiveBinaryType;)V", "getType", "()Lorg/jetbrains/kotlin/backend/konan/PrimitiveBinaryType;", "org.jetbrains.kotlin:base"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Primitive extends BinaryType {
        private final PrimitiveBinaryType type;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Primitive(PrimitiveBinaryType primitiveBinaryType) {
            super(null);
            primitiveBinaryType.getClass();
            this.type = primitiveBinaryType;
        }

        public final PrimitiveBinaryType getType() {
            return this.type;
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001d\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/backend/konan/BinaryType$Reference;", "T", "Lorg/jetbrains/kotlin/backend/konan/BinaryType;", "types", "Lkotlin/sequences/Sequence;", "nullable", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lkotlin/sequences/Sequence;Z)V", "getTypes", "()Lkotlin/sequences/Sequence;", "getNullable", "()Z", "org.jetbrains.kotlin:base"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Reference<T> extends BinaryType<T> {
        private final boolean nullable;
        private final Sequence<T> types;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public Reference(Sequence<? extends T> sequence, boolean z) {
            super(null);
            sequence.getClass();
            this.types = sequence;
            this.nullable = z;
        }

        public final boolean getNullable() {
            return this.nullable;
        }

        public final Sequence<T> getTypes() {
            return this.types;
        }
    }

    public /* synthetic */ BinaryType(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private BinaryType() {
    }
}
