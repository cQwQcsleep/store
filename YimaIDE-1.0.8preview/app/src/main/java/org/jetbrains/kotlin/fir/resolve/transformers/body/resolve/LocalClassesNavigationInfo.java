package org.jetbrains.kotlin.fir.resolve.transformers.body.resolve;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.LocalClassesNavigationInfo;
import org.jetbrains.kotlin.utils.CollectionsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0006\u0018\u00002\u00020\u0001B1\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\f2\u0006\u0010\u0011\u001a\u00020\u0006H\u0002R\u001f\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R-\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\f0\u00038FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\r\u0010\n¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/LocalClassesNavigationInfo;", Argument.Delimiters.none, "parentForClass", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "parentClassForFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "<init>", "(Ljava/util/Map;Ljava/util/Map;)V", "getParentForClass", "()Ljava/util/Map;", "designationMap", Argument.Delimiters.none, "getDesignationMap", "designationMap$delegate", "Lkotlin/Lazy;", "pathForCallable", "callableMemberDeclaration", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class LocalClassesNavigationInfo {

    /* JADX INFO: renamed from: designationMap$delegate, reason: from kotlin metadata */
    private final Lazy designationMap;
    private final Map<FirCallableDeclaration, FirClassLikeDeclaration> parentClassForFunction;
    private final Map<FirClassLikeDeclaration, FirClassLikeDeclaration> parentForClass;

    /* JADX WARN: Multi-variable type inference failed */
    public LocalClassesNavigationInfo(Map<FirClassLikeDeclaration, ? extends FirClassLikeDeclaration> map, Map<FirCallableDeclaration, ? extends FirClassLikeDeclaration> map2) {
        map.getClass();
        map2.getClass();
        this.parentForClass = map;
        this.parentClassForFunction = map2;
        this.designationMap = LazyKt.lazy(new Function0() { // from class: we9
            public final Object invoke() {
                return LocalClassesNavigationInfo.a(this.b);
            }
        });
    }

    public static Map a(final LocalClassesNavigationInfo localClassesNavigationInfo) {
        return CollectionsKt.keysToMap(localClassesNavigationInfo.parentClassForFunction.keySet(), new Function1() { // from class: xe9
            public final Object invoke(Object obj) {
                return LocalClassesNavigationInfo.designationMap_delegate$lambda$0$0(this.b, (FirCallableDeclaration) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List designationMap_delegate$lambda$0$0(LocalClassesNavigationInfo localClassesNavigationInfo, FirCallableDeclaration firCallableDeclaration) {
        firCallableDeclaration.getClass();
        return localClassesNavigationInfo.pathForCallable(firCallableDeclaration);
    }

    private final List<FirClassLikeDeclaration> pathForCallable(FirCallableDeclaration callableMemberDeclaration) {
        ArrayList arrayList = new ArrayList();
        FirClassLikeDeclaration firClassLikeDeclaration = this.parentClassForFunction.get(callableMemberDeclaration);
        while (firClassLikeDeclaration != null) {
            arrayList.add(firClassLikeDeclaration);
            firClassLikeDeclaration = this.parentForClass.get(firClassLikeDeclaration);
        }
        return kotlin.collections.CollectionsKt.asReversedMutable(arrayList);
    }

    public final Map<FirCallableDeclaration, List<FirClassLikeDeclaration>> getDesignationMap() {
        return (Map) this.designationMap.getValue();
    }

    public final Map<FirClassLikeDeclaration, FirClassLikeDeclaration> getParentForClass() {
        return this.parentForClass;
    }
}
