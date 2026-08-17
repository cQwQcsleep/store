package org.jetbrains.kotlin.fir.declarations;

import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/SealedClassInheritorsProviderImpl;", "Lorg/jetbrains/kotlin/fir/declarations/SealedClassInheritorsProvider;", "<init>", "()V", "getSealedClassInheritors", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/ClassId;", "firClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SealedClassInheritorsProviderImpl extends SealedClassInheritorsProvider {
    public static final SealedClassInheritorsProviderImpl INSTANCE = new SealedClassInheritorsProviderImpl();

    private SealedClassInheritorsProviderImpl() {
    }

    @Override // org.jetbrains.kotlin.fir.declarations.SealedClassInheritorsProvider
    public List<ClassId> getSealedClassInheritors(FirRegularClass firClass) {
        List<ClassId> list;
        firClass.getClass();
        Lazy<List<ClassId>> sealedInheritorsAttr = SealedClassInheritorsKt.getSealedInheritorsAttr(firClass);
        return (sealedInheritorsAttr == null || (list = (List) sealedInheritorsAttr.getValue()) == null) ? CollectionsKt.emptyList() : list;
    }
}
