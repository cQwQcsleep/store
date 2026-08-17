package org.jetbrains.kotlin.fir.deserialization;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.KtBooleanValueParameterReference;
import org.jetbrains.kotlin.contracts.description.KtConstantReference;
import org.jetbrains.kotlin.contracts.description.KtEffectDeclaration;
import org.jetbrains.kotlin.contracts.description.KtValueParameterReference;
import org.jetbrains.kotlin.fir.contracts.ContractUtilsKt;
import org.jetbrains.kotlin.fir.contracts.FirContractDescription;
import org.jetbrains.kotlin.fir.contracts.FirEffectDeclaration;
import org.jetbrains.kotlin.fir.contracts.FirResolvedContractDescription;
import org.jetbrains.kotlin.fir.contracts.builder.FirResolvedContractDescriptionBuilder;
import org.jetbrains.kotlin.fir.contracts.description.ConeContractConstantValues;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirContractDescriptionOwner;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.ProtoTypeTableUtilKt;
import org.jetbrains.kotlin.serialization.deserialization.ProtoBufContractDeserializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001B\u000f\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000b\u001a\u00020\u000fH\u0016J&\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\u0004H\u0016J\u001c\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0014\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0015H\u0016J\u0014\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0015H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/fir/deserialization/FirContractDeserializer;", "Lorg/jetbrains/kotlin/serialization/deserialization/ProtoBufContractDeserializer;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "Lorg/jetbrains/kotlin/fir/declarations/FirContractDescriptionOwner;", "c", "Lorg/jetbrains/kotlin/fir/deserialization/FirDeserializationContext;", "<init>", "(Lorg/jetbrains/kotlin/fir/deserialization/FirDeserializationContext;)V", "loadContract", "Lorg/jetbrains/kotlin/fir/contracts/FirContractDescription;", "proto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Contract;", "owner", "extractType", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Expression;", "extractVariable", "Lorg/jetbrains/kotlin/contracts/description/KtValueParameterReference;", "valueParameterIndex", Argument.Delimiters.none, "loadConstant", "Lorg/jetbrains/kotlin/contracts/description/KtConstantReference;", "value", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Expression$ConstantValue;", "getNotNull", "getWildcard", "org.jetbrains.kotlin:fir-deserialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirContractDeserializer extends ProtoBufContractDeserializer<ConeKotlinType, ConeDiagnostic, FirContractDescriptionOwner> {
    private final FirDeserializationContext c;

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ProtoBuf.Expression.ConstantValue.values().length];
            try {
                iArr[ProtoBuf.Expression.ConstantValue.TRUE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ProtoBuf.Expression.ConstantValue.FALSE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ProtoBuf.Expression.ConstantValue.NULL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public FirContractDeserializer(FirDeserializationContext firDeserializationContext) {
        firDeserializationContext.getClass();
        this.c = firDeserializationContext;
    }

    public ConeKotlinType extractType(ProtoBuf.Expression proto) {
        proto.getClass();
        FirTypeDeserializer typeDeserializer = this.c.getTypeDeserializer();
        ProtoBuf.Type typeIsInstanceType = ProtoTypeTableUtilKt.isInstanceType(proto, this.c.getTypeTable());
        if (typeIsInstanceType == null) {
            return null;
        }
        return typeDeserializer.type(typeIsInstanceType);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public KtValueParameterReference<ConeKotlinType, ConeDiagnostic> extractVariable(int valueParameterIndex, FirContractDescriptionOwner owner) {
        String strAsString;
        FirTypeRef returnTypeRef;
        owner.getClass();
        FirFunction firFunction = (FirFunction) owner;
        FirDeclaration fir = owner instanceof FirPropertyAccessor ? ((FirPropertyAccessor) owner).getPropertySymbol().getFir() : firFunction;
        if (valueParameterIndex == -1) {
            FirReceiverParameter receiverParameter = ((FirCallableDeclaration) fir).getReceiverParameter();
            strAsString = "this";
            returnTypeRef = receiverParameter != null ? receiverParameter.getTypeRef() : null;
        } else if (valueParameterIndex < 0 || valueParameterIndex >= firFunction.getValueParameters().size()) {
            FirValueParameter firValueParameter = (FirValueParameter) CollectionsKt.getOrNull(((FirCallableDeclaration) fir).getContextParameters(), valueParameterIndex - firFunction.getValueParameters().size());
            if (firValueParameter == null) {
                return null;
            }
            strAsString = firValueParameter.getName().asString();
            strAsString.getClass();
            returnTypeRef = firValueParameter.getReturnTypeRef();
        } else {
            FirValueParameter firValueParameter2 = (FirValueParameter) CollectionsKt.getOrNull(firFunction.getValueParameters(), valueParameterIndex);
            if (firValueParameter2 == null) {
                return null;
            }
            strAsString = firValueParameter2.getName().asString();
            strAsString.getClass();
            returnTypeRef = firValueParameter2.getReturnTypeRef();
        }
        if (returnTypeRef == null) {
            return null;
        }
        return !FirTypeUtilsKt.isBoolean(returnTypeRef) ? new KtValueParameterReference<>(valueParameterIndex, strAsString) : new KtBooleanValueParameterReference(valueParameterIndex, strAsString);
    }

    public KtConstantReference<ConeKotlinType, ConeDiagnostic> getNotNull() {
        return ConeContractConstantValues.INSTANCE.getNOT_NULL();
    }

    public KtConstantReference<ConeKotlinType, ConeDiagnostic> getWildcard() {
        return ConeContractConstantValues.INSTANCE.getWILDCARD();
    }

    public KtConstantReference<ConeKotlinType, ConeDiagnostic> loadConstant(ProtoBuf.Expression.ConstantValue value) {
        value.getClass();
        int i = WhenMappings.$EnumSwitchMapping$0[value.ordinal()];
        if (i == 1) {
            return ConeContractConstantValues.INSTANCE.getTRUE();
        }
        if (i == 2) {
            return ConeContractConstantValues.INSTANCE.getFALSE();
        }
        if (i == 3) {
            return ConeContractConstantValues.INSTANCE.getNULL();
        }
        bu8.a();
        return null;
    }

    public final FirContractDescription loadContract(ProtoBuf.Contract proto, FirContractDescriptionOwner owner) {
        proto.getClass();
        owner.getClass();
        List<ProtoBuf.Effect> effectList = proto.getEffectList();
        effectList.getClass();
        ArrayList arrayList = new ArrayList();
        for (ProtoBuf.Effect effect : effectList) {
            effect.getClass();
            KtEffectDeclaration ktEffectDeclarationLoadPossiblyConditionalEffect = loadPossiblyConditionalEffect(effect, owner);
            if (ktEffectDeclarationLoadPossiblyConditionalEffect != null) {
                arrayList.add(ktEffectDeclarationLoadPossiblyConditionalEffect);
            }
        }
        FirResolvedContractDescriptionBuilder firResolvedContractDescriptionBuilder = new FirResolvedContractDescriptionBuilder();
        List<FirEffectDeclaration> effects = firResolvedContractDescriptionBuilder.getEffects();
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(ContractUtilsKt.toFirElement$default((KtEffectDeclaration) it.next(), (KtSourceElement) null, 1, (Object) null));
        }
        CollectionsKt.addAll(effects, arrayList2);
        FirResolvedContractDescription firResolvedContractDescriptionBuild = firResolvedContractDescriptionBuilder.build();
        if (arrayList.isEmpty()) {
            return null;
        }
        return firResolvedContractDescriptionBuild;
    }
}
