package org.jetbrains.kotlin.fir.extensions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.util.ConeTypeRegistry;
import org.jetbrains.kotlin.util.ArrayMapAccessor;
import org.jetbrains.kotlin.util.ComponentArrayOwner;
import org.jetbrains.kotlin.util.TypeRegistry;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001c2\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030\u00012\u00020\u0004:\u0001\u001cB\u000f\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ.\u0010\u0014\u001a\u00020\u00152\u000e\u0010\u0016\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u00172\u0010\u0010\u0018\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00190\u0003H\u0007b\u0002\b\u001aJ\u0012\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00020\u0003H\u0007b\u0002\b\u001aR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR&\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030\f8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u001e\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0010@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/FirExtensionService;", "Lorg/jetbrains/kotlin/util/ComponentArrayOwner;", "Lorg/jetbrains/kotlin/fir/extensions/FirExtension;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "typeRegistry", "Lorg/jetbrains/kotlin/util/TypeRegistry;", "getTypeRegistry", "()Lorg/jetbrains/kotlin/util/TypeRegistry;", "value", Argument.Delimiters.none, "registeredExtensionsSize", "getRegisteredExtensionsSize", "()I", "registerExtensions", Argument.Delimiters.none, "extensionClass", "Lkotlin/reflect/KClass;", "extensionFactories", "Lorg/jetbrains/kotlin/fir/extensions/FirExtension$Factory;", "Lorg/jetbrains/kotlin/fir/extensions/PluginServicesInitialization;", "getAllExtensions", "Companion", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirExtensionService extends ComponentArrayOwner<FirExtension, List<? extends FirExtension>> implements FirSessionComponent {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private int registeredExtensionsSize;
    private final FirSession session;

    public FirExtensionService(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
    }

    @PluginServicesInitialization
    public final List<FirExtension> getAllExtensions() {
        return CollectionsKt.flatten(getArrayMap());
    }

    public final int getRegisteredExtensionsSize() {
        return this.registeredExtensionsSize;
    }

    public final FirSession getSession() {
        return this.session;
    }

    public TypeRegistry<FirExtension, List<FirExtension>> getTypeRegistry() {
        return INSTANCE;
    }

    @PluginServicesInitialization
    public final void registerExtensions(KClass<? extends FirExtension> extensionClass, List<? extends FirExtension.Factory<?>> extensionFactories) {
        extensionClass.getClass();
        extensionFactories.getClass();
        this.registeredExtensionsSize += extensionFactories.size();
        List<? extends FirExtension.Factory<?>> list = extensionFactories;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((FirExtension.Factory) it.next()).create(this.session));
        }
        registerComponent(extensionClass, arrayList);
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J=\u0010\u0006\u001a\u001a\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0003\u0012\u0004\u0012\u0002H\b0\u0007\"\n\b\u0000\u0010\t\u0018\u0001*\u00020\u0002\"\u000e\b\u0001\u0010\b*\b\u0012\u0004\u0012\u0002H\t0\u0003H\u0086\bJF\u0010\u0006\u001a\u001a\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0003\u0012\u0004\u0012\u0002H\b0\u0007\"\b\b\u0000\u0010\t*\u00020\u0002\"\u000e\b\u0001\u0010\b*\b\u0012\u0004\u0012\u0002H\t0\u00032\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/FirExtensionService$Companion;", "Lorg/jetbrains/kotlin/fir/util/ConeTypeRegistry;", "Lorg/jetbrains/kotlin/fir/extensions/FirExtension;", Argument.Delimiters.none, "<init>", "()V", "registeredExtensions", "Lorg/jetbrains/kotlin/util/ArrayMapAccessor;", "V", "P", "kClass", "Lkotlin/reflect/KClass;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion extends ConeTypeRegistry<FirExtension, List<? extends FirExtension>> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final /* synthetic */ <P extends FirExtension, V extends List<? extends P>> ArrayMapAccessor<FirExtension, List<FirExtension>, V> registeredExtensions() {
            Intrinsics.reifiedOperationMarker(4, "P");
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(FirExtension.class);
            List listEmptyList = CollectionsKt.emptyList();
            listEmptyList.getClass();
            return generateAccessor(orCreateKotlinClass, listEmptyList);
        }

        private Companion() {
        }

        public final <P extends FirExtension, V extends List<? extends P>> ArrayMapAccessor<FirExtension, List<FirExtension>, V> registeredExtensions(KClass<P> kClass) {
            kClass.getClass();
            List listEmptyList = CollectionsKt.emptyList();
            listEmptyList.getClass();
            return generateAccessor(kClass, listEmptyList);
        }
    }
}
