package org.jetbrains.kotlin.fir.resolve.calls.stages;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.builder.FirPlaceholderProjectionBuilder;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\b\tB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H¦\u0002\u0082\u0001\u0002\n\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/stages/TypeArgumentMapping;", Argument.Delimiters.none, "<init>", "()V", "get", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "typeParameterIndex", Argument.Delimiters.none, "NoExplicitArguments", "Mapped", "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/TypeArgumentMapping$Mapped;", "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/TypeArgumentMapping$NoExplicitArguments;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class TypeArgumentMapping {

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0011\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0096\u0002R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/stages/TypeArgumentMapping$Mapped;", "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/TypeArgumentMapping;", "ordered", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "<init>", "(Ljava/util/List;)V", "get", "typeParameterIndex", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Mapped extends TypeArgumentMapping {
        private final List<FirTypeProjection> ordered;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public Mapped(List<? extends FirTypeProjection> list) {
            super(null);
            list.getClass();
            this.ordered = list;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.calls.stages.TypeArgumentMapping
        public FirTypeProjection get(int typeParameterIndex) {
            List<FirTypeProjection> list = this.ordered;
            return (typeParameterIndex < 0 || typeParameterIndex >= list.size()) ? new FirPlaceholderProjectionBuilder().build() : list.get(typeParameterIndex);
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0096\u0002¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/stages/TypeArgumentMapping$NoExplicitArguments;", "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/TypeArgumentMapping;", "<init>", "()V", "get", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "typeParameterIndex", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class NoExplicitArguments extends TypeArgumentMapping {
        public static final NoExplicitArguments INSTANCE = new NoExplicitArguments();

        private NoExplicitArguments() {
            super(null);
        }

        @Override // org.jetbrains.kotlin.fir.resolve.calls.stages.TypeArgumentMapping
        public FirTypeProjection get(int typeParameterIndex) {
            return new FirPlaceholderProjectionBuilder().build();
        }
    }

    public /* synthetic */ TypeArgumentMapping(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract FirTypeProjection get(int typeParameterIndex);

    private TypeArgumentMapping() {
    }
}
