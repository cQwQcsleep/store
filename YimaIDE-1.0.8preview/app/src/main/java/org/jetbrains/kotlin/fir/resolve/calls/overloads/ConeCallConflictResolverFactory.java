package org.jetbrains.kotlin.fir.resolve.calls.overloads;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.SpreadBuilder;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirComposableSessionComponent;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionConfiguration;
import org.jetbrains.kotlin.fir.declarations.FirTypeSpecificityComparatorProvider;
import org.jetbrains.kotlin.fir.declarations.FirTypeSpecificityComparatorProviderKt;
import org.jetbrains.kotlin.fir.resolve.BodyResolveComponents;
import org.jetbrains.kotlin.fir.resolve.inference.InferenceComponents;
import org.jetbrains.kotlin.resolve.calls.results.TypeSpecificityComparator;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0002\u0011\u0012B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b2\u0006\u0010\f\u001a\u00020\rH&J\u001a\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00000\u000bH\u0017b\u0002\b\u0010¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/overloads/ConeCallConflictResolverFactory;", "Lorg/jetbrains/kotlin/fir/FirComposableSessionComponent;", "<init>", "()V", CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME, "Lorg/jetbrains/kotlin/fir/resolve/calls/overloads/ConeCallConflictResolver;", "components", "Lorg/jetbrains/kotlin/fir/resolve/inference/InferenceComponents;", "transformerComponents", "Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;", "createAdditionalResolvers", Argument.Delimiters.none, "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "createComposed", "Lorg/jetbrains/kotlin/fir/resolve/calls/overloads/ConeCallConflictResolverFactory$Composed;", "Lorg/jetbrains/kotlin/fir/SessionConfiguration;", "Default", "Composed", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class ConeCallConflictResolverFactory implements FirComposableSessionComponent<ConeCallConflictResolverFactory> {

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00010\u0002B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00042\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/overloads/ConeCallConflictResolverFactory$Composed;", "Lorg/jetbrains/kotlin/fir/resolve/calls/overloads/ConeCallConflictResolverFactory;", "Lorg/jetbrains/kotlin/fir/FirComposableSessionComponent$Composed;", "components", Argument.Delimiters.none, "<init>", "(Ljava/util/List;)V", "getComponents", "()Ljava/util/List;", "createAdditionalResolvers", "Lorg/jetbrains/kotlin/fir/resolve/calls/overloads/ConeCallConflictResolver;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Composed extends ConeCallConflictResolverFactory implements FirComposableSessionComponent.Composed<ConeCallConflictResolverFactory> {
        private final List<ConeCallConflictResolverFactory> components;

        /* JADX WARN: Multi-variable type inference failed */
        public Composed(List<? extends ConeCallConflictResolverFactory> list) {
            list.getClass();
            this.components = list;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.calls.overloads.ConeCallConflictResolverFactory
        public List<ConeCallConflictResolver> createAdditionalResolvers(FirSession session) {
            session.getClass();
            List<ConeCallConflictResolverFactory> components = getComponents();
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = components.iterator();
            while (it.hasNext()) {
                CollectionsKt.addAll(arrayList, ((ConeCallConflictResolverFactory) it.next()).createAdditionalResolvers(session));
            }
            return arrayList;
        }

        @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
        public List<ConeCallConflictResolverFactory> getComponents() {
            return this.components;
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/overloads/ConeCallConflictResolverFactory$Default;", "Lorg/jetbrains/kotlin/fir/resolve/calls/overloads/ConeCallConflictResolverFactory;", "<init>", "()V", "createAdditionalResolvers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/overloads/ConeCallConflictResolver;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Default extends ConeCallConflictResolverFactory {
        public static final Default INSTANCE = new Default();

        private Default() {
        }

        @Override // org.jetbrains.kotlin.fir.resolve.calls.overloads.ConeCallConflictResolverFactory
        public List<ConeCallConflictResolver> createAdditionalResolvers(FirSession session) {
            session.getClass();
            return CollectionsKt.emptyList();
        }
    }

    public final ConeCallConflictResolver create(InferenceComponents components, BodyResolveComponents transformerComponents) {
        TypeSpecificityComparator typeSpecificityComparator;
        components.getClass();
        transformerComponents.getClass();
        FirSession session = components.getSession();
        FirTypeSpecificityComparatorProvider typeSpecificityComparatorProvider = FirTypeSpecificityComparatorProviderKt.getTypeSpecificityComparatorProvider(session);
        if (typeSpecificityComparatorProvider == null || (typeSpecificityComparator = typeSpecificityComparatorProvider.getTypeSpecificityComparator()) == null) {
            typeSpecificityComparator = TypeSpecificityComparator.NONE.INSTANCE;
        }
        SpreadBuilder spreadBuilder = new SpreadBuilder(4);
        spreadBuilder.add(new ConeEquivalentCallConflictResolver(session));
        spreadBuilder.addSpread(createAdditionalResolvers(session).toArray(new ConeCallConflictResolver[0]));
        spreadBuilder.add(ConeIntegerOperatorConflictResolver.INSTANCE);
        spreadBuilder.add(new ConeOverloadConflictResolver(typeSpecificityComparator, components, transformerComponents));
        return new ConeCompositeConflictResolver((ConeCallConflictResolver[]) spreadBuilder.toArray(new ConeCallConflictResolver[spreadBuilder.size()]));
    }

    public abstract List<ConeCallConflictResolver> createAdditionalResolvers(FirSession session);

    @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
    @SessionConfiguration
    public Composed createComposed(List<? extends ConeCallConflictResolverFactory> components) {
        components.getClass();
        return new Composed(components);
    }

    @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
    @SessionConfiguration
    public /* bridge */ /* synthetic */ FirComposableSessionComponent.Composed createComposed(List list) {
        return createComposed((List<? extends ConeCallConflictResolverFactory>) list);
    }
}
