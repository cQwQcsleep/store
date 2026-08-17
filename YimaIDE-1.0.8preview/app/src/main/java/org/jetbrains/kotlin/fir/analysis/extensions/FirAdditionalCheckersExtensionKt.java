package org.jetbrains.kotlin.fir.analysis.extensions;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.extensions.FirExtensionService;
import org.jetbrains.kotlin.util.ArrayMapAccessor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\"%\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00038FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\u0004\u0010\u0005¨\u0006\b"}, d2 = {"additionalCheckers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/extensions/FirAdditionalCheckersExtension;", "Lorg/jetbrains/kotlin/fir/extensions/FirExtensionService;", "getAdditionalCheckers", "(Lorg/jetbrains/kotlin/fir/extensions/FirExtensionService;)Ljava/util/List;", "additionalCheckers$delegate", "Lorg/jetbrains/kotlin/util/ArrayMapAccessor;", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirAdditionalCheckersExtensionKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(FirAdditionalCheckersExtensionKt.class, "additionalCheckers", "getAdditionalCheckers(Lorg/jetbrains/kotlin/fir/extensions/FirExtensionService;)Ljava/util/List;", 1)};
    private static final ArrayMapAccessor additionalCheckers$delegate = FirExtensionService.INSTANCE.registeredExtensions(Reflection.getOrCreateKotlinClass(FirAdditionalCheckersExtension.class));

    public static final List<FirAdditionalCheckersExtension> getAdditionalCheckers(FirExtensionService firExtensionService) {
        firExtensionService.getClass();
        return (List) additionalCheckers$delegate.getValue(firExtensionService, $$delegatedProperties[0]);
    }
}
