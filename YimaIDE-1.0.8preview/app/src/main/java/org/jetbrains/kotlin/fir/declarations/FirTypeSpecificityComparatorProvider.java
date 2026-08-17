package org.jetbrains.kotlin.fir.declarations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirComposableSessionComponent;
import org.jetbrains.kotlin.fir.SessionConfiguration;
import org.jetbrains.kotlin.resolve.calls.results.TypeSpecificityComparator;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u000f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0003\r\u000e\u000fB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00000\u000bH\u0017b\u0002\b\fR\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0002\t\u0010¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirTypeSpecificityComparatorProvider;", "Lorg/jetbrains/kotlin/fir/FirComposableSessionComponent;", "<init>", "()V", "typeSpecificityComparator", "Lorg/jetbrains/kotlin/resolve/calls/results/TypeSpecificityComparator;", "getTypeSpecificityComparator", "()Lorg/jetbrains/kotlin/resolve/calls/results/TypeSpecificityComparator;", "createComposed", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeSpecificityComparatorProvider$Composed;", "components", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/SessionConfiguration;", "Simple", "Composed", "Companion", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeSpecificityComparatorProvider$Simple;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirTypeSpecificityComparatorProvider implements FirComposableSessionComponent<FirTypeSpecificityComparatorProvider> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00010\u0002B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirTypeSpecificityComparatorProvider$Composed;", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeSpecificityComparatorProvider;", "Lorg/jetbrains/kotlin/fir/FirComposableSessionComponent$Composed;", "components", Argument.Delimiters.none, "<init>", "(Ljava/util/List;)V", "getComponents", "()Ljava/util/List;", "typeSpecificityComparator", "Lorg/jetbrains/kotlin/resolve/calls/results/TypeSpecificityComparator;", "getTypeSpecificityComparator", "()Lorg/jetbrains/kotlin/resolve/calls/results/TypeSpecificityComparator;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Composed extends FirTypeSpecificityComparatorProvider implements FirComposableSessionComponent.Composed<FirTypeSpecificityComparatorProvider> {
        private final List<FirTypeSpecificityComparatorProvider> components;
        private final TypeSpecificityComparator typeSpecificityComparator;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public Composed(List<? extends FirTypeSpecificityComparatorProvider> list) {
            super(null);
            list.getClass();
            this.components = list;
            List<FirTypeSpecificityComparatorProvider> components = getComponents();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(components, 10));
            Iterator<T> it = components.iterator();
            while (it.hasNext()) {
                arrayList.add(((FirTypeSpecificityComparatorProvider) it.next()).getTypeSpecificityComparator());
            }
            this.typeSpecificityComparator = new TypeSpecificityComparator.Composed(arrayList);
        }

        @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
        public List<FirTypeSpecificityComparatorProvider> getComponents() {
            return this.components;
        }

        @Override // org.jetbrains.kotlin.fir.declarations.FirTypeSpecificityComparatorProvider
        public TypeSpecificityComparator getTypeSpecificityComparator() {
            return this.typeSpecificityComparator;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirTypeSpecificityComparatorProvider$Simple;", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeSpecificityComparatorProvider;", "typeSpecificityComparator", "Lorg/jetbrains/kotlin/resolve/calls/results/TypeSpecificityComparator;", "<init>", "(Lorg/jetbrains/kotlin/resolve/calls/results/TypeSpecificityComparator;)V", "getTypeSpecificityComparator", "()Lorg/jetbrains/kotlin/resolve/calls/results/TypeSpecificityComparator;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Simple extends FirTypeSpecificityComparatorProvider {
        private final TypeSpecificityComparator typeSpecificityComparator;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Simple(TypeSpecificityComparator typeSpecificityComparator) {
            super(null);
            typeSpecificityComparator.getClass();
            this.typeSpecificityComparator = typeSpecificityComparator;
        }

        @Override // org.jetbrains.kotlin.fir.declarations.FirTypeSpecificityComparatorProvider
        public TypeSpecificityComparator getTypeSpecificityComparator() {
            return this.typeSpecificityComparator;
        }
    }

    public /* synthetic */ FirTypeSpecificityComparatorProvider(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
    @SessionConfiguration
    public Composed createComposed(List<? extends FirTypeSpecificityComparatorProvider> components) {
        components.getClass();
        return new Composed(components);
    }

    public abstract TypeSpecificityComparator getTypeSpecificityComparator();

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirTypeSpecificityComparatorProvider$Companion;", Argument.Delimiters.none, "<init>", "()V", "of", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeSpecificityComparatorProvider;", "typeSpecificityComparator", "Lorg/jetbrains/kotlin/resolve/calls/results/TypeSpecificityComparator;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FirTypeSpecificityComparatorProvider of(TypeSpecificityComparator typeSpecificityComparator) {
            typeSpecificityComparator.getClass();
            return new Simple(typeSpecificityComparator);
        }

        private Companion() {
        }
    }

    private FirTypeSpecificityComparatorProvider() {
    }

    @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
    @SessionConfiguration
    public /* bridge */ /* synthetic */ FirComposableSessionComponent.Composed createComposed(List list) {
        return createComposed((List<? extends FirTypeSpecificityComparatorProvider>) list);
    }
}
