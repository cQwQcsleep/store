package org.jetbrains.kotlin.fir.resolve.calls;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKind;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.resolve.substitution.AbstractConeSubstitutor;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.CustomAnnotationTypeAttributeKt;
import org.jetbrains.kotlin.fir.types.FirFunctionTypeKindServiceKt;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/FunctionTypeKindSubstitutor;", "Lorg/jetbrains/kotlin/fir/resolve/substitution/AbstractConeSubstitutor;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "substituteType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", ModuleXmlParser.TYPE, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class FunctionTypeKindSubstitutor extends AbstractConeSubstitutor {
    private final FirSession session;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FunctionTypeKindSubstitutor(FirSession firSession) {
        super(TypeComponentsKt.getTypeContext(firSession));
        firSession.getClass();
        this.session = firSession;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ConeTypeProjection[] substituteType$lambda$0$0(FunctionTypeKindSubstitutor functionTypeKindSubstitutor, ConeTypeProjection[] coneTypeProjectionArr) {
        coneTypeProjectionArr.getClass();
        ArrayList arrayList = new ArrayList(coneTypeProjectionArr.length);
        int length = coneTypeProjectionArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            ConeTypeProjection coneTypeProjection = coneTypeProjectionArr[i];
            int i3 = i2 + 1;
            ConeTypeProjection coneTypeProjectionSubstituteArgument = functionTypeKindSubstitutor.substituteArgument(coneTypeProjection, i2);
            if (coneTypeProjectionSubstituteArgument != null) {
                coneTypeProjection = coneTypeProjectionSubstituteArgument;
            }
            arrayList.add(coneTypeProjection);
            i++;
            i2 = i3;
        }
        return (ConeTypeProjection[]) arrayList.toArray(new ConeTypeProjection[0]);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.substitution.AbstractConeSubstitutor
    public ConeKotlinType substituteType(ConeKotlinType type) {
        type.getClass();
        if (!(type instanceof ConeClassLikeType)) {
            return null;
        }
        FunctionTypeKind functionTypeKindExtractSingleExtensionKindForDeserializedConeType = FirFunctionTypeKindServiceKt.getFunctionTypeService(this.session).extractSingleExtensionKindForDeserializedConeType(ConeTypeUtilsKt.getClassId((ConeClassLikeType) type), CustomAnnotationTypeAttributeKt.getCustomAnnotations(type));
        if (functionTypeKindExtractSingleExtensionKindForDeserializedConeType != null) {
            return FunctionalTypeUtilsKt.createFunctionTypeWithNewKind$default(type, this.session, functionTypeKindExtractSingleExtensionKindForDeserializedConeType, null, new Function1() { // from class: org.jetbrains.kotlin.fir.resolve.calls.a
                public final Object invoke(Object obj) {
                    return FunctionTypeKindSubstitutor.substituteType$lambda$0$0(this.b, (ConeTypeProjection[]) obj);
                }
            }, 4, null);
        }
        return null;
    }
}
