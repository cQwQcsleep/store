package org.jetbrains.kotlin.fir.backend;

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
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\"%\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00038FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\u0004\u0010\u0005¨\u0006\b"}, d2 = {"fir2IrScriptConfigurators", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/backend/Fir2IrScriptConfiguratorExtension;", "Lorg/jetbrains/kotlin/fir/extensions/FirExtensionService;", "getFir2IrScriptConfigurators", "(Lorg/jetbrains/kotlin/fir/extensions/FirExtensionService;)Ljava/util/List;", "fir2IrScriptConfigurators$delegate", "Lorg/jetbrains/kotlin/util/ArrayMapAccessor;", "org.jetbrains.kotlin:fir2ir"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrScriptConfiguratorExtensionKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(Fir2IrScriptConfiguratorExtensionKt.class, "fir2IrScriptConfigurators", "getFir2IrScriptConfigurators(Lorg/jetbrains/kotlin/fir/extensions/FirExtensionService;)Ljava/util/List;", 1)};
    private static final ArrayMapAccessor fir2IrScriptConfigurators$delegate;

    static {
        FirExtensionService.Companion companion = FirExtensionService.INSTANCE;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Fir2IrScriptConfiguratorExtension.class);
        List listEmptyList = CollectionsKt.emptyList();
        listEmptyList.getClass();
        fir2IrScriptConfigurators$delegate = companion.generateAccessor(orCreateKotlinClass, listEmptyList);
    }

    public static final List<Fir2IrScriptConfiguratorExtension> getFir2IrScriptConfigurators(FirExtensionService firExtensionService) {
        firExtensionService.getClass();
        return (List) fir2IrScriptConfigurators$delegate.getValue(firExtensionService, $$delegatedProperties[0]);
    }
}
