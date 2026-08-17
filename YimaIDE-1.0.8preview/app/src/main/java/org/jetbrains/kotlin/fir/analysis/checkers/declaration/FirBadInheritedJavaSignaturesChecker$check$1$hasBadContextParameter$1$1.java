package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class FirBadInheritedJavaSignaturesChecker$check$1$hasBadContextParameter$1$1 extends FunctionReferenceImpl implements Function1<ConeKotlinType, Boolean> {
    public static final FirBadInheritedJavaSignaturesChecker$check$1$hasBadContextParameter$1$1 INSTANCE = new FirBadInheritedJavaSignaturesChecker$check$1$hasBadContextParameter$1$1();

    public FirBadInheritedJavaSignaturesChecker$check$1$hasBadContextParameter$1$1() {
        super(1, Intrinsics.Kotlin.class, "containsFunctionN", "check$containsFunctionN(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Z", 0);
    }

    public final Boolean invoke(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return Boolean.valueOf(FirBadInheritedJavaSignaturesChecker.check$containsFunctionN(coneKotlinType));
    }
}
