package org.jetbrains.kotlin.fir.types;

import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.util.ArrayMapAccessor;
import org.jetbrains.kotlin.util.TypeRegistry;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00028BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0007\u001a\u00020\b*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\t\u0010\n\"\u0015\u0010\u000b\u001a\u00020\f*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"typeComponents", "Lorg/jetbrains/kotlin/fir/types/TypeComponents;", "Lorg/jetbrains/kotlin/fir/FirSession;", "getTypeComponents", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/types/TypeComponents;", "typeComponents$delegate", "Lorg/jetbrains/kotlin/util/ArrayMapAccessor;", "typeContext", "Lorg/jetbrains/kotlin/fir/types/ConeInferenceContext;", "getTypeContext", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/types/ConeInferenceContext;", "typeApproximator", "Lorg/jetbrains/kotlin/fir/types/ConeTypeApproximator;", "getTypeApproximator", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/types/ConeTypeApproximator;", "org.jetbrains.kotlin:providers"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class TypeComponentsKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(TypeComponentsKt.class, "typeComponents", "getTypeComponents(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/types/TypeComponents;", 1)};
    private static final ArrayMapAccessor typeComponents$delegate = TypeRegistry.generateAccessor$default(FirSession.Companion, Reflection.getOrCreateKotlinClass(TypeComponents.class), (Object) null, 2, (Object) null);

    public static final ConeTypeApproximator getTypeApproximator(FirSession firSession) {
        firSession.getClass();
        return getTypeComponents(firSession).getTypeApproximator();
    }

    private static final TypeComponents getTypeComponents(FirSession firSession) {
        return (TypeComponents) typeComponents$delegate.getValue(firSession, $$delegatedProperties[0]);
    }

    public static final ConeInferenceContext getTypeContext(FirSession firSession) {
        firSession.getClass();
        return getTypeComponents(firSession).getTypeContext();
    }
}
