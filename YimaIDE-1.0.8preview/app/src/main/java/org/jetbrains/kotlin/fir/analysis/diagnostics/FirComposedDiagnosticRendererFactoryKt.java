package org.jetbrains.kotlin.fir.analysis.diagnostics;

import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.KtRegisteredDiagnosticFactoriesStorage;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.util.ArrayMapAccessor;
import org.jetbrains.kotlin.util.TypeRegistry;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00028BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0007\u001a\u00020\b*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"firRegisteredDiagnosticFactories", "Lorg/jetbrains/kotlin/fir/analysis/diagnostics/FirRegisteredDiagnosticFactoriesStorage;", "Lorg/jetbrains/kotlin/fir/FirSession;", "getFirRegisteredDiagnosticFactories", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/analysis/diagnostics/FirRegisteredDiagnosticFactoriesStorage;", "firRegisteredDiagnosticFactories$delegate", "Lorg/jetbrains/kotlin/util/ArrayMapAccessor;", "registeredDiagnosticFactoriesStorage", "Lorg/jetbrains/kotlin/diagnostics/KtRegisteredDiagnosticFactoriesStorage;", "getRegisteredDiagnosticFactoriesStorage", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/diagnostics/KtRegisteredDiagnosticFactoriesStorage;", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirComposedDiagnosticRendererFactoryKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(FirComposedDiagnosticRendererFactoryKt.class, "firRegisteredDiagnosticFactories", "getFirRegisteredDiagnosticFactories(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/analysis/diagnostics/FirRegisteredDiagnosticFactoriesStorage;", 1)};
    private static final ArrayMapAccessor firRegisteredDiagnosticFactories$delegate = TypeRegistry.generateAccessor$default(FirSession.INSTANCE, Reflection.getOrCreateKotlinClass(FirRegisteredDiagnosticFactoriesStorage.class), (Object) null, 2, (Object) null);

    private static final FirRegisteredDiagnosticFactoriesStorage getFirRegisteredDiagnosticFactories(FirSession firSession) {
        return (FirRegisteredDiagnosticFactoriesStorage) firRegisteredDiagnosticFactories$delegate.getValue(firSession, $$delegatedProperties[0]);
    }

    public static final KtRegisteredDiagnosticFactoriesStorage getRegisteredDiagnosticFactoriesStorage(FirSession firSession) {
        firSession.getClass();
        return getFirRegisteredDiagnosticFactories(firSession).getStorage();
    }
}
