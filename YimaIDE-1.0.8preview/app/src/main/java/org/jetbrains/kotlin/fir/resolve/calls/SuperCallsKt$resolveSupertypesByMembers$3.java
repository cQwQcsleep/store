package org.jetbrains.kotlin.fir.resolve.calls;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.BodyResolveComponents;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.types.AbstractTypeChecker;
import org.jetbrains.kotlin.utils.SmartList;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SuperCallsKt$resolveSupertypesByMembers$3 implements Function1<ConeKotlinType, Boolean> {
    final /* synthetic */ BodyResolveComponents $this_resolveSupertypesByMembers;
    final /* synthetic */ SmartList<ConeKotlinType> $typesWithNonConcreteMembers;

    public SuperCallsKt$resolveSupertypesByMembers$3(SmartList<ConeKotlinType> smartList, BodyResolveComponents bodyResolveComponents) {
        this.$typesWithNonConcreteMembers = smartList;
        this.$this_resolveSupertypesByMembers = bodyResolveComponents;
    }

    public final Boolean invoke(ConeKotlinType coneKotlinType) {
        SmartList<ConeKotlinType> smartList = this.$typesWithNonConcreteMembers;
        BodyResolveComponents bodyResolveComponents = this.$this_resolveSupertypesByMembers;
        boolean z = false;
        if (smartList == null || !smartList.isEmpty()) {
            for (ConeKotlinType coneKotlinType2 : smartList) {
                AbstractTypeChecker abstractTypeChecker = AbstractTypeChecker.INSTANCE;
                ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(bodyResolveComponents.getSession());
                coneKotlinType2.getClass();
                coneKotlinType.getClass();
                ConeKotlinType coneKotlinType3 = coneKotlinType;
                if (AbstractTypeChecker.isSubtypeOf$default(abstractTypeChecker, typeContext, coneKotlinType2, coneKotlinType3, false, 8, (Object) null)) {
                    z = true;
                    break;
                }
                coneKotlinType = coneKotlinType3;
            }
        }
        return Boolean.valueOf(z);
    }
}
