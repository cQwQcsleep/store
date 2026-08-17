package org.jetbrains.kotlin.fir.resolve;

import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirModuleCapability;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001c\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/ImplicitIntegerCoercionModuleCapability;", "Lorg/jetbrains/kotlin/fir/FirModuleCapability;", "<init>", "()V", "key", "Lkotlin/reflect/KClass;", "getKey", "()Lkotlin/reflect/KClass;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ImplicitIntegerCoercionModuleCapability extends FirModuleCapability {
    public static final ImplicitIntegerCoercionModuleCapability INSTANCE = new ImplicitIntegerCoercionModuleCapability();
    private static final KClass<? extends FirModuleCapability> key = Reflection.getOrCreateKotlinClass(ImplicitIntegerCoercionModuleCapability.class);

    private ImplicitIntegerCoercionModuleCapability() {
    }

    @Override // org.jetbrains.kotlin.fir.FirModuleCapability
    public KClass<? extends FirModuleCapability> getKey() {
        return key;
    }
}
