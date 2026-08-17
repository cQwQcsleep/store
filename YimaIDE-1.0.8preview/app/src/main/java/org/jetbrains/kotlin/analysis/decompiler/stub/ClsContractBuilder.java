package org.jetbrains.kotlin.analysis.decompiler.stub;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.KtBooleanValueParameterReference;
import org.jetbrains.kotlin.contracts.description.KtConstantReference;
import org.jetbrains.kotlin.contracts.description.KtEffectDeclaration;
import org.jetbrains.kotlin.contracts.description.KtValueParameterReference;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.ProtoTypeTableUtilKt;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.psi.stubs.impl.KotlinContractConstantValues;
import org.jetbrains.kotlin.psi.stubs.impl.KotlinTypeBean;
import org.jetbrains.kotlin.serialization.deserialization.NameResolverUtilKt;
import org.jetbrains.kotlin.serialization.deserialization.ProtoBufContractDeserializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u0016\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00020\u00040\u0001B\u0017\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ$\u0010\u000b\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\r\u0018\u00010\f2\u0006\u0010\u000e\u001a\u00020\u0004J&\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0004H\u0016J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u001e\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0016\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0018H\u0016J\u0016\u0010\u001c\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0018H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/analysis/decompiler/stub/ClsContractBuilder;", "Lorg/jetbrains/kotlin/serialization/deserialization/ProtoBufContractDeserializer;", "Lorg/jetbrains/kotlin/psi/stubs/impl/KotlinTypeBean;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/analysis/decompiler/stub/ClsContractOwner;", "c", "Lorg/jetbrains/kotlin/analysis/decompiler/stub/ClsStubBuilderContext;", "typeStubBuilder", "Lorg/jetbrains/kotlin/analysis/decompiler/stub/TypeClsStubBuilder;", "<init>", "(Lorg/jetbrains/kotlin/analysis/decompiler/stub/ClsStubBuilderContext;Lorg/jetbrains/kotlin/analysis/decompiler/stub/TypeClsStubBuilder;)V", "loadContract", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/contracts/description/KtEffectDeclaration;", "contractOwner", "extractVariable", "Lorg/jetbrains/kotlin/contracts/description/KtValueParameterReference;", "valueParameterIndex", Argument.Delimiters.none, "owner", "extractType", "proto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Expression;", "loadConstant", "Lorg/jetbrains/kotlin/contracts/description/KtConstantReference;", "value", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Expression$ConstantValue;", "getNotNull", "getWildcard", "org.jetbrains.kotlin:decompiler-to-stubs"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ClsContractBuilder extends ProtoBufContractDeserializer {
    private final ClsStubBuilderContext c;
    private final TypeClsStubBuilder typeStubBuilder;

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

    public ClsContractBuilder(ClsStubBuilderContext clsStubBuilderContext, TypeClsStubBuilder typeClsStubBuilder) {
        clsStubBuilderContext.getClass();
        typeClsStubBuilder.getClass();
        this.c = clsStubBuilderContext;
        this.typeStubBuilder = typeClsStubBuilder;
    }

    public KotlinTypeBean extractType(ProtoBuf.Expression proto) {
        proto.getClass();
        return this.typeStubBuilder.createKotlinTypeBean(ProtoTypeTableUtilKt.isInstanceType(proto, this.c.getTypeTable()));
    }

    public KtValueParameterReference extractVariable(int valueParameterIndex, ClsContractOwner owner) {
        ProtoBuf.Type type;
        owner.getClass();
        if (valueParameterIndex == -1) {
            type = owner.receiverType(this.c.getTypeTable());
        } else {
            type = valueParameterIndex < owner.getValueParameterCount() ? ProtoTypeTableUtilKt.type((ProtoBuf.ValueParameter) owner.getValueParameters().get(valueParameterIndex), this.c.getTypeTable()) : ProtoTypeTableUtilKt.type((ProtoBuf.ValueParameter) owner.getContextParameters().get(valueParameterIndex - owner.getValueParameterCount()), this.c.getTypeTable());
        }
        return (type != null && type.hasClassName() && Intrinsics.areEqual(NameResolverUtilKt.getClassId(this.c.getNameResolver(), type.getClassName()), StandardClassIds.INSTANCE.getBoolean())) ? new KtBooleanValueParameterReference(valueParameterIndex, "<ignore>") : new KtValueParameterReference(valueParameterIndex, "<ignore>");
    }

    public KtConstantReference getNotNull() {
        return KotlinContractConstantValues.INSTANCE.getNOT_NULL();
    }

    public KtConstantReference getWildcard() {
        return KotlinContractConstantValues.INSTANCE.getWILDCARD();
    }

    public KtConstantReference loadConstant(ProtoBuf.Expression.ConstantValue value) {
        value.getClass();
        int i = WhenMappings.$EnumSwitchMapping$0[value.ordinal()];
        if (i == 1) {
            return KotlinContractConstantValues.INSTANCE.getTRUE();
        }
        if (i == 2) {
            return KotlinContractConstantValues.INSTANCE.getFALSE();
        }
        if (i == 3) {
            return KotlinContractConstantValues.INSTANCE.getNULL();
        }
        bu8.a();
        return null;
    }

    public final List<KtEffectDeclaration> loadContract(ClsContractOwner contractOwner) {
        contractOwner.getClass();
        List effectList = contractOwner.getContract().getEffectList();
        effectList.getClass();
        List<ProtoBuf.Effect> list = effectList;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (ProtoBuf.Effect effect : list) {
            effect.getClass();
            KtEffectDeclaration ktEffectDeclarationLoadPossiblyConditionalEffect = loadPossiblyConditionalEffect(effect, contractOwner);
            if (ktEffectDeclarationLoadPossiblyConditionalEffect == null) {
                return null;
            }
            arrayList.add(ktEffectDeclarationLoadPossiblyConditionalEffect);
        }
        return arrayList;
    }
}
