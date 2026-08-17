package org.jetbrains.kotlin.fir.scopes.impl;

import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.util.ArrayMapAccessor;
import org.jetbrains.kotlin.util.TypeRegistry;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00028BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0007"}, d2 = {"generatedDeclarationsStorage", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirGeneratedMemberDeclarationsStorage;", "Lorg/jetbrains/kotlin/fir/FirSession;", "getGeneratedDeclarationsStorage", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/scopes/impl/FirGeneratedMemberDeclarationsStorage;", "generatedDeclarationsStorage$delegate", "Lorg/jetbrains/kotlin/util/ArrayMapAccessor;", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirGeneratedScopesKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(FirGeneratedScopesKt.class, "generatedDeclarationsStorage", "getGeneratedDeclarationsStorage(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/scopes/impl/FirGeneratedMemberDeclarationsStorage;", 1)};
    private static final ArrayMapAccessor generatedDeclarationsStorage$delegate = TypeRegistry.generateAccessor$default(FirSession.INSTANCE, Reflection.getOrCreateKotlinClass(FirGeneratedMemberDeclarationsStorage.class), (Object) null, 2, (Object) null);

    /* JADX INFO: Access modifiers changed from: private */
    public static final FirGeneratedMemberDeclarationsStorage getGeneratedDeclarationsStorage(FirSession firSession) {
        return (FirGeneratedMemberDeclarationsStorage) generatedDeclarationsStorage$delegate.getValue(firSession, $$delegatedProperties[0]);
    }
}
