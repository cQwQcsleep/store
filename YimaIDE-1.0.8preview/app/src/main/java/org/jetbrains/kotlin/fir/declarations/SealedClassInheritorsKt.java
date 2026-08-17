package org.jetbrains.kotlin.fir.declarations;

import java.util.Comparator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.SealedClassInheritorsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.util.ArrayMapAccessor;
import org.jetbrains.kotlin.util.TypeRegistry;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b*\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002\u001a\u0018\u0010\f\u001a\u00020\r*\u00020\n2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\b\u001a\u001e\u0010\f\u001a\u00020\r*\u00020\n2\u0012\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0010\"\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00028BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0004\"U\u0010\u0013\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0018\u00010\u0012*\u00020\n2\u0014\u0010\u0011\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0018\u00010\u00128F@BX\u0087\u008e\u0002r\u0002\b\u001c¢\u0006\u0018\n\u0004\b\u001a\u0010\u001b\u0012\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006\u001d"}, d2 = {"sealedClassInheritorsProvider", "Lorg/jetbrains/kotlin/fir/declarations/SealedClassInheritorsProvider;", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSealedClassInheritorsProvider", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/declarations/SealedClassInheritorsProvider;", "sealedClassInheritorsProvider$delegate", "Lorg/jetbrains/kotlin/util/ArrayMapAccessor;", "getSealedClassInheritors", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/ClassId;", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "session", "setSealedClassInheritors", Argument.Delimiters.none, "inheritors", "inheritorComputer", "Lkotlin/Function0;", "<set-?>", "Lkotlin/Lazy;", "sealedInheritorsAttr", "getSealedInheritorsAttr$annotations", "(Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)V", "getSealedInheritorsAttr", "(Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)Lkotlin/Lazy;", "setSealedInheritorsAttr", "(Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;Lkotlin/Lazy;)V", "sealedInheritorsAttr$delegate", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationDataRegistry$DeclarationDataAccessor;", "Lorg/jetbrains/kotlin/fir/declarations/SealedClassInheritorsProviderInternals;", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SealedClassInheritorsKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(SealedClassInheritorsKt.class, "sealedClassInheritorsProvider", "getSealedClassInheritorsProvider(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/declarations/SealedClassInheritorsProvider;", 1), new MutablePropertyReference1Impl<>(SealedClassInheritorsKt.class, "sealedInheritorsAttr", "getSealedInheritorsAttr(Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)Lkotlin/Lazy;", 1)};
    private static final ArrayMapAccessor sealedClassInheritorsProvider$delegate = TypeRegistry.generateAccessor$default(FirSession.INSTANCE, Reflection.getOrCreateKotlinClass(SealedClassInheritorsProvider.class), (Object) null, 2, (Object) null);
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor sealedInheritorsAttr$delegate = FirDeclarationDataRegistry.INSTANCE.data(SealedClassInheritorsKey.INSTANCE);

    public static List a(Function0 function0) {
        return CollectionsKt.sortedWith((Iterable) function0.invoke(), new Comparator() { // from class: org.jetbrains.kotlin.fir.declarations.SealedClassInheritorsKt$setSealedClassInheritors$lambda$1$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(((ClassId) t).asFqNameString(), ((ClassId) t2).asFqNameString());
            }
        });
    }

    public static final List<ClassId> getSealedClassInheritors(FirRegularClass firRegularClass, FirSession firSession) {
        firRegularClass.getClass();
        firSession.getClass();
        if (firRegularClass.getStatus().getModality() == Modality.SEALED) {
            return getSealedClassInheritorsProvider(firSession).getSealedClassInheritors(firRegularClass);
        }
        w01.a("Failed requirement.");
        return null;
    }

    private static final SealedClassInheritorsProvider getSealedClassInheritorsProvider(FirSession firSession) {
        return (SealedClassInheritorsProvider) sealedClassInheritorsProvider$delegate.getValue(firSession, $$delegatedProperties[0]);
    }

    public static final Lazy<List<ClassId>> getSealedInheritorsAttr(FirRegularClass firRegularClass) {
        firRegularClass.getClass();
        return (Lazy) sealedInheritorsAttr$delegate.getValue(firRegularClass, $$delegatedProperties[1]);
    }

    @SealedClassInheritorsProviderInternals
    public static /* synthetic */ void getSealedInheritorsAttr$annotations(FirRegularClass firRegularClass) {
    }

    public static final void setSealedClassInheritors(FirRegularClass firRegularClass, List<ClassId> list) {
        firRegularClass.getClass();
        list.getClass();
        if (firRegularClass.getStatus().getModality() == Modality.SEALED) {
            setSealedInheritorsAttr(firRegularClass, LazyKt.lazyOf(CollectionsKt.sortedWith(list, new Comparator() { // from class: org.jetbrains.kotlin.fir.declarations.SealedClassInheritorsKt$setSealedClassInheritors$$inlined$sortedBy$1
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(((ClassId) t).asFqNameString(), ((ClassId) t2).asFqNameString());
                }
            })));
        } else {
            w01.a("Failed requirement.");
        }
    }

    private static final void setSealedInheritorsAttr(FirRegularClass firRegularClass, Lazy<? extends List<ClassId>> lazy) {
        sealedInheritorsAttr$delegate.setValue(firRegularClass, $$delegatedProperties[1], lazy);
    }

    public static final void setSealedClassInheritors(FirRegularClass firRegularClass, final Function0<? extends List<ClassId>> function0) {
        firRegularClass.getClass();
        function0.getClass();
        if (firRegularClass.getStatus().getModality() == Modality.SEALED) {
            setSealedInheritorsAttr(firRegularClass, LazyKt.lazy(new Function0() { // from class: xyc
                public final Object invoke() {
                    return SealedClassInheritorsKt.a(function0);
                }
            }));
        } else {
            w01.a("Failed requirement.");
        }
    }
}
