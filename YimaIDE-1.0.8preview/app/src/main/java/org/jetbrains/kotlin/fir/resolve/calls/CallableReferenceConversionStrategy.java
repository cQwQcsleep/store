package org.jetbrains.kotlin.fir.resolve.calls;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\b\tB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u0004\u0018\u00010\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0002\n\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/CallableReferenceConversionStrategy;", Argument.Delimiters.none, "<init>", "()V", "kind", "Lorg/jetbrains/kotlin/builtins/functions/FunctionTypeKind;", "getKind", "()Lorg/jetbrains/kotlin/builtins/functions/FunctionTypeKind;", "NoConversion", "CustomConversion", "Lorg/jetbrains/kotlin/fir/resolve/calls/CallableReferenceConversionStrategy$CustomConversion;", "Lorg/jetbrains/kotlin/fir/resolve/calls/CallableReferenceConversionStrategy$NoConversion;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class CallableReferenceConversionStrategy {

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/CallableReferenceConversionStrategy$CustomConversion;", "Lorg/jetbrains/kotlin/fir/resolve/calls/CallableReferenceConversionStrategy;", "kind", "Lorg/jetbrains/kotlin/builtins/functions/FunctionTypeKind;", "<init>", "(Lorg/jetbrains/kotlin/builtins/functions/FunctionTypeKind;)V", "getKind", "()Lorg/jetbrains/kotlin/builtins/functions/FunctionTypeKind;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class CustomConversion extends CallableReferenceConversionStrategy {
        private final FunctionTypeKind kind;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CustomConversion(FunctionTypeKind functionTypeKind) {
            super(null);
            functionTypeKind.getClass();
            this.kind = functionTypeKind;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.calls.CallableReferenceConversionStrategy
        public FunctionTypeKind getKind() {
            return this.kind;
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/CallableReferenceConversionStrategy$NoConversion;", "Lorg/jetbrains/kotlin/fir/resolve/calls/CallableReferenceConversionStrategy;", "<init>", "()V", "kind", "Lorg/jetbrains/kotlin/builtins/functions/FunctionTypeKind;", "getKind", "()Lorg/jetbrains/kotlin/builtins/functions/FunctionTypeKind;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class NoConversion extends CallableReferenceConversionStrategy {
        public static final NoConversion INSTANCE = new NoConversion();

        private NoConversion() {
            super(null);
        }

        @Override // org.jetbrains.kotlin.fir.resolve.calls.CallableReferenceConversionStrategy
        public FunctionTypeKind getKind() {
            return null;
        }
    }

    public /* synthetic */ CallableReferenceConversionStrategy(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract FunctionTypeKind getKind();

    private CallableReferenceConversionStrategy() {
    }
}
