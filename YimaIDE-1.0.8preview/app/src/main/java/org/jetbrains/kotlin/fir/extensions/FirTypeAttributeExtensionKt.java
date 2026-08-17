package org.jetbrains.kotlin.fir.extensions;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.util.ArrayMapAccessor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\"%\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00038FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\u0004\u0010\u0005¨\u0006\b"}, d2 = {"typeAttributeExtensions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/extensions/FirTypeAttributeExtension;", "Lorg/jetbrains/kotlin/fir/extensions/FirExtensionService;", "getTypeAttributeExtensions", "(Lorg/jetbrains/kotlin/fir/extensions/FirExtensionService;)Ljava/util/List;", "typeAttributeExtensions$delegate", "Lorg/jetbrains/kotlin/util/ArrayMapAccessor;", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirTypeAttributeExtensionKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(FirTypeAttributeExtensionKt.class, "typeAttributeExtensions", "getTypeAttributeExtensions(Lorg/jetbrains/kotlin/fir/extensions/FirExtensionService;)Ljava/util/List;", 1)};
    private static final ArrayMapAccessor typeAttributeExtensions$delegate;

    static {
        FirExtensionService.Companion companion = FirExtensionService.INSTANCE;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(FirTypeAttributeExtension.class);
        List listEmptyList = CollectionsKt.emptyList();
        listEmptyList.getClass();
        typeAttributeExtensions$delegate = companion.generateAccessor(orCreateKotlinClass, listEmptyList);
    }

    public static final List<FirTypeAttributeExtension> getTypeAttributeExtensions(FirExtensionService firExtensionService) {
        firExtensionService.getClass();
        return (List) typeAttributeExtensions$delegate.getValue(firExtensionService, $$delegatedProperties[0]);
    }
}
