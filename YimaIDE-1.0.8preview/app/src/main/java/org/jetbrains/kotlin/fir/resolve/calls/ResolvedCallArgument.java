package org.jetbrains.kotlin.fir.resolve.calls;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.inline.ReifiedTypeInliner;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002:\u0003\t\n\u000bB\t\b\u0004¢\u0006\u0004\b\u0003\u0010\u0004R\u0018\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\u0082\u0001\u0003\f\r\u000e¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/ResolvedCallArgument;", "T", Argument.Delimiters.none, "<init>", "()V", "arguments", Argument.Delimiters.none, "getArguments", "()Ljava/util/List;", "DefaultArgument", "SimpleArgument", "VarargArgument", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolvedCallArgument$DefaultArgument;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolvedCallArgument$SimpleArgument;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolvedCallArgument$VarargArgument;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class ResolvedCallArgument<T> {

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/ResolvedCallArgument$DefaultArgument;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolvedCallArgument;", Argument.Delimiters.none, "<init>", "()V", "arguments", Argument.Delimiters.none, "getArguments", "()Ljava/util/List;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class DefaultArgument extends ResolvedCallArgument {
        public static final DefaultArgument INSTANCE = new DefaultArgument();

        private DefaultArgument() {
            super(null);
        }

        @Override // org.jetbrains.kotlin.fir.resolve.calls.ResolvedCallArgument
        public List getArguments() {
            return CollectionsKt.emptyList();
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0003\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00028\u0001¢\u0006\u0004\b\u0004\u0010\u0005R\u0013\u0010\u0003\u001a\u00028\u0001¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00010\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/ResolvedCallArgument$SimpleArgument;", "T", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolvedCallArgument;", "callArgument", "<init>", ReifiedTypeInliner.pluginIntrinsicsMarkerSignature, "getCallArgument", "()Ljava/lang/Object;", "Ljava/lang/Object;", "arguments", Argument.Delimiters.none, "getArguments", "()Ljava/util/List;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class SimpleArgument<T> extends ResolvedCallArgument<T> {
        private final T callArgument;

        public SimpleArgument(T t) {
            super(null);
            this.callArgument = t;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.calls.ResolvedCallArgument
        public List<T> getArguments() {
            return CollectionsKt.listOf(this.callArgument);
        }

        public final T getCallArgument() {
            return this.callArgument;
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/ResolvedCallArgument$VarargArgument;", "T", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolvedCallArgument;", "arguments", Argument.Delimiters.none, "<init>", "(Ljava/util/List;)V", "getArguments", "()Ljava/util/List;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class VarargArgument<T> extends ResolvedCallArgument<T> {
        private final List<T> arguments;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public VarargArgument(List<? extends T> list) {
            super(null);
            list.getClass();
            this.arguments = list;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.calls.ResolvedCallArgument
        public List<T> getArguments() {
            return this.arguments;
        }
    }

    public /* synthetic */ ResolvedCallArgument(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract List<T> getArguments();

    private ResolvedCallArgument() {
    }
}
