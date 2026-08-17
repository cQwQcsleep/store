package org.jetbrains.kotlin.fir.extensions;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.extensions.QualifierPartBuilder;
import org.jetbrains.kotlin.fir.types.FirQualifierPart;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.impl.FirQualifierPartImpl;
import org.jetbrains.kotlin.fir.types.impl.FirTypeArgumentListImpl;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0011B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J)\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0019\b\u0002\u0010\r\u001a\u0013\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\n0\u000e¢\u0006\u0002\b\u0010R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/QualifierPartBuilder;", Argument.Delimiters.none, "destination", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/FirQualifierPart;", "<init>", "(Ljava/util/List;)V", "getDestination$org_jetbrains_kotlin_resolve", "()Ljava/util/List;", "part", Argument.Delimiters.none, ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "argumentsBuilder", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/extensions/QualifierPartBuilder$TypeArgumentsBuilder;", "Lkotlin/ExtensionFunctionType;", "TypeArgumentsBuilder", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class QualifierPartBuilder {
    private final List<FirQualifierPart> destination;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/QualifierPartBuilder$TypeArgumentsBuilder;", Argument.Delimiters.none, "typeArgumentList", "Lorg/jetbrains/kotlin/fir/types/impl/FirTypeArgumentListImpl;", "<init>", "(Lorg/jetbrains/kotlin/fir/types/impl/FirTypeArgumentListImpl;)V", "getTypeArgumentList$org_jetbrains_kotlin_resolve", "()Lorg/jetbrains/kotlin/fir/types/impl/FirTypeArgumentListImpl;", "argument", Argument.Delimiters.none, "typeArgument", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class TypeArgumentsBuilder {
        private final FirTypeArgumentListImpl typeArgumentList;

        public TypeArgumentsBuilder(FirTypeArgumentListImpl firTypeArgumentListImpl) {
            firTypeArgumentListImpl.getClass();
            this.typeArgumentList = firTypeArgumentListImpl;
        }

        public final void argument(FirTypeProjection typeArgument) {
            typeArgument.getClass();
            this.typeArgumentList.getTypeArguments().add(typeArgument);
        }

        /* JADX INFO: renamed from: getTypeArgumentList$org_jetbrains_kotlin_resolve, reason: from getter */
        public final FirTypeArgumentListImpl getTypeArgumentList() {
            return this.typeArgumentList;
        }
    }

    public QualifierPartBuilder(List<FirQualifierPart> list) {
        list.getClass();
        this.destination = list;
    }

    public static Unit a(TypeArgumentsBuilder typeArgumentsBuilder) {
        typeArgumentsBuilder.getClass();
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void part$default(QualifierPartBuilder qualifierPartBuilder, Name name, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1() { // from class: i1c
                public final Object invoke(Object obj2) {
                    return QualifierPartBuilder.a((QualifierPartBuilder.TypeArgumentsBuilder) obj2);
                }
            };
        }
        qualifierPartBuilder.part(name, function1);
    }

    public final List<FirQualifierPart> getDestination$org_jetbrains_kotlin_resolve() {
        return this.destination;
    }

    public final void part(Name name, Function1<? super TypeArgumentsBuilder, Unit> argumentsBuilder) {
        name.getClass();
        argumentsBuilder.getClass();
        FirTypeArgumentListImpl firTypeArgumentListImpl = new FirTypeArgumentListImpl(null);
        argumentsBuilder.invoke(new TypeArgumentsBuilder(firTypeArgumentListImpl));
        this.destination.add(new FirQualifierPartImpl(null, name, firTypeArgumentListImpl));
    }
}
