package org.jetbrains.kotlin.fir.serialization;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.extensions.FirExtensionService;
import org.jetbrains.kotlin.util.ArrayMapAccessor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\"+\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00038@X\u0080\u0084\u0002¢\u0006\u0012\n\u0004\b\b\u0010\t\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"metadataSerializerPlugins", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/serialization/FirMetadataSerializerPlugin;", "Lorg/jetbrains/kotlin/fir/extensions/FirExtensionService;", "getMetadataSerializerPlugins$annotations", "(Lorg/jetbrains/kotlin/fir/extensions/FirExtensionService;)V", "getMetadataSerializerPlugins", "(Lorg/jetbrains/kotlin/fir/extensions/FirExtensionService;)Ljava/util/List;", "metadataSerializerPlugins$delegate", "Lorg/jetbrains/kotlin/util/ArrayMapAccessor;", "org.jetbrains.kotlin:fir-serialization"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirMetadataSerializerPluginKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(FirMetadataSerializerPluginKt.class, "metadataSerializerPlugins", "getMetadataSerializerPlugins(Lorg/jetbrains/kotlin/fir/extensions/FirExtensionService;)Ljava/util/List;", 1)};
    private static final ArrayMapAccessor metadataSerializerPlugins$delegate;

    static {
        FirExtensionService.Companion companion = FirExtensionService.INSTANCE;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(FirMetadataSerializerPlugin.class);
        List listEmptyList = CollectionsKt.emptyList();
        listEmptyList.getClass();
        metadataSerializerPlugins$delegate = companion.generateAccessor(orCreateKotlinClass, listEmptyList);
    }

    public static final List<FirMetadataSerializerPlugin> getMetadataSerializerPlugins(FirExtensionService firExtensionService) {
        firExtensionService.getClass();
        return (List) metadataSerializerPlugins$delegate.getValue(firExtensionService, $$delegatedProperties[0]);
    }

    public static /* synthetic */ void getMetadataSerializerPlugins$annotations(FirExtensionService firExtensionService) {
    }
}
