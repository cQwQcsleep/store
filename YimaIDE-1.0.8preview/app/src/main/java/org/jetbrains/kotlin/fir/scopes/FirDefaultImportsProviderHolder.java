package org.jetbrains.kotlin.fir.scopes;

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
import org.jetbrains.kotlin.resolve.DefaultImportsProvider;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \r2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0003\r\u000e\u000fB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00000\u000bH\u0017b\u0002\b\fR\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0002\t\u0010¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/FirDefaultImportsProviderHolder;", "Lorg/jetbrains/kotlin/fir/FirComposableSessionComponent;", "<init>", "()V", "provider", "Lorg/jetbrains/kotlin/resolve/DefaultImportsProvider;", "getProvider", "()Lorg/jetbrains/kotlin/resolve/DefaultImportsProvider;", "createComposed", "Lorg/jetbrains/kotlin/fir/scopes/FirDefaultImportsProviderHolder$Composed;", "components", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/SessionConfiguration;", "Companion", "Single", "Composed", "Lorg/jetbrains/kotlin/fir/scopes/FirDefaultImportsProviderHolder$Single;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirDefaultImportsProviderHolder implements FirComposableSessionComponent<FirDefaultImportsProviderHolder> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00010\u0002B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/FirDefaultImportsProviderHolder$Composed;", "Lorg/jetbrains/kotlin/fir/scopes/FirDefaultImportsProviderHolder;", "Lorg/jetbrains/kotlin/fir/FirComposableSessionComponent$Composed;", "components", Argument.Delimiters.none, "<init>", "(Ljava/util/List;)V", "getComponents", "()Ljava/util/List;", "provider", "Lorg/jetbrains/kotlin/resolve/DefaultImportsProvider;", "getProvider", "()Lorg/jetbrains/kotlin/resolve/DefaultImportsProvider;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Composed extends FirDefaultImportsProviderHolder implements FirComposableSessionComponent.Composed<FirDefaultImportsProviderHolder> {
        private final List<FirDefaultImportsProviderHolder> components;
        private final DefaultImportsProvider provider;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public Composed(List<? extends FirDefaultImportsProviderHolder> list) {
            super(null);
            list.getClass();
            this.components = list;
            List<FirDefaultImportsProviderHolder> components = getComponents();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(components, 10));
            Iterator<T> it = components.iterator();
            while (it.hasNext()) {
                arrayList.add(((FirDefaultImportsProviderHolder) it.next()).getProvider());
            }
            this.provider = new DefaultImportsProvider.Composed(arrayList);
        }

        @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
        public List<FirDefaultImportsProviderHolder> getComponents() {
            return this.components;
        }

        @Override // org.jetbrains.kotlin.fir.scopes.FirDefaultImportsProviderHolder
        public DefaultImportsProvider getProvider() {
            return this.provider;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/FirDefaultImportsProviderHolder$Single;", "Lorg/jetbrains/kotlin/fir/scopes/FirDefaultImportsProviderHolder;", "provider", "Lorg/jetbrains/kotlin/resolve/DefaultImportsProvider;", "<init>", "(Lorg/jetbrains/kotlin/resolve/DefaultImportsProvider;)V", "getProvider", "()Lorg/jetbrains/kotlin/resolve/DefaultImportsProvider;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Single extends FirDefaultImportsProviderHolder {
        private final DefaultImportsProvider provider;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Single(DefaultImportsProvider defaultImportsProvider) {
            super(null);
            defaultImportsProvider.getClass();
            this.provider = defaultImportsProvider;
        }

        @Override // org.jetbrains.kotlin.fir.scopes.FirDefaultImportsProviderHolder
        public DefaultImportsProvider getProvider() {
            return this.provider;
        }
    }

    public /* synthetic */ FirDefaultImportsProviderHolder(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
    @SessionConfiguration
    public Composed createComposed(List<? extends FirDefaultImportsProviderHolder> components) {
        components.getClass();
        return new Composed(components);
    }

    public abstract DefaultImportsProvider getProvider();

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/FirDefaultImportsProviderHolder$Companion;", Argument.Delimiters.none, "<init>", "()V", "of", "Lorg/jetbrains/kotlin/fir/scopes/FirDefaultImportsProviderHolder;", "provider", "Lorg/jetbrains/kotlin/resolve/DefaultImportsProvider;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FirDefaultImportsProviderHolder of(DefaultImportsProvider provider) {
            provider.getClass();
            return new Single(provider);
        }

        private Companion() {
        }
    }

    private FirDefaultImportsProviderHolder() {
    }

    @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
    @SessionConfiguration
    public /* bridge */ /* synthetic */ FirComposableSessionComponent.Composed createComposed(List list) {
        return createComposed((List<? extends FirDefaultImportsProviderHolder>) list);
    }
}
