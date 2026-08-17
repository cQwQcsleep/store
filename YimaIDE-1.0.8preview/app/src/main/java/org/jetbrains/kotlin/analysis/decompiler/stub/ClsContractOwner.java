package org.jetbrains.kotlin.analysis.decompiler.stub;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.ProtoTypeTableUtilKt;
import org.jetbrains.kotlin.metadata.deserialization.TypeTable;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0017\u0018B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bH&R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\f\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0018\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0014\u0082\u0001\u0002\u0019\u001a¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/analysis/decompiler/stub/ClsContractOwner;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "contract", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Contract;", "getContract", "()Lorg/jetbrains/kotlin/metadata/ProtoBuf$Contract;", "receiverType", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Type;", "typeTable", "Lorg/jetbrains/kotlin/metadata/deserialization/TypeTable;", "valueParameterCount", "", "getValueParameterCount", "()I", "valueParameters", "", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$ValueParameter;", "getValueParameters", "()Ljava/util/List;", "contextParameters", "getContextParameters", "Function", "PropertyAccessor", "Lorg/jetbrains/kotlin/analysis/decompiler/stub/ClsContractOwner$Function;", "Lorg/jetbrains/kotlin/analysis/decompiler/stub/ClsContractOwner$PropertyAccessor;", "org.jetbrains.kotlin:decompiler-to-stubs"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class ClsContractOwner {

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0010\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00160\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0018¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/analysis/decompiler/stub/ClsContractOwner$Function;", "Lorg/jetbrains/kotlin/analysis/decompiler/stub/ClsContractOwner;", "functionProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Function;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/metadata/ProtoBuf$Function;)V", "getFunctionProto", "()Lorg/jetbrains/kotlin/metadata/ProtoBuf$Function;", "contract", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Contract;", "getContract", "()Lorg/jetbrains/kotlin/metadata/ProtoBuf$Contract;", "receiverType", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Type;", "typeTable", "Lorg/jetbrains/kotlin/metadata/deserialization/TypeTable;", "valueParameterCount", "", "getValueParameterCount", "()I", "valueParameters", "", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$ValueParameter;", "getValueParameters", "()Ljava/util/List;", "contextParameters", "getContextParameters", "org.jetbrains.kotlin:decompiler-to-stubs"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Function extends ClsContractOwner {
        private final ProtoBuf.Function functionProto;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Function(ProtoBuf.Function function) {
            super(null);
            function.getClass();
            this.functionProto = function;
        }

        @Override // org.jetbrains.kotlin.analysis.decompiler.stub.ClsContractOwner
        public List<ProtoBuf.ValueParameter> getContextParameters() {
            List<ProtoBuf.ValueParameter> contextParameterList = this.functionProto.getContextParameterList();
            contextParameterList.getClass();
            return contextParameterList;
        }

        @Override // org.jetbrains.kotlin.analysis.decompiler.stub.ClsContractOwner
        public ProtoBuf.Contract getContract() {
            ProtoBuf.Contract contract = this.functionProto.getContract();
            contract.getClass();
            return contract;
        }

        public final ProtoBuf.Function getFunctionProto() {
            return this.functionProto;
        }

        @Override // org.jetbrains.kotlin.analysis.decompiler.stub.ClsContractOwner
        public int getValueParameterCount() {
            return this.functionProto.getValueParameterCount();
        }

        @Override // org.jetbrains.kotlin.analysis.decompiler.stub.ClsContractOwner
        public List<ProtoBuf.ValueParameter> getValueParameters() {
            List<ProtoBuf.ValueParameter> valueParameterList = this.functionProto.getValueParameterList();
            valueParameterList.getClass();
            return valueParameterList;
        }

        @Override // org.jetbrains.kotlin.analysis.decompiler.stub.ClsContractOwner
        public ProtoBuf.Type receiverType(TypeTable typeTable) {
            typeTable.getClass();
            return ProtoTypeTableUtilKt.receiverType(this.functionProto, typeTable);
        }
    }

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0013\u001a\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00190\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001b¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/analysis/decompiler/stub/ClsContractOwner$PropertyAccessor;", "Lorg/jetbrains/kotlin/analysis/decompiler/stub/ClsContractOwner;", "propertyProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property;", "isGetter", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property;Z)V", "getPropertyProto", "()Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property;", "()Z", "contract", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Contract;", "getContract", "()Lorg/jetbrains/kotlin/metadata/ProtoBuf$Contract;", "receiverType", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Type;", "typeTable", "Lorg/jetbrains/kotlin/metadata/deserialization/TypeTable;", "valueParameterCount", "", "getValueParameterCount", "()I", "contextParameters", "", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$ValueParameter;", "getContextParameters", "()Ljava/util/List;", "valueParameters", "getValueParameters", "org.jetbrains.kotlin:decompiler-to-stubs"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class PropertyAccessor extends ClsContractOwner {
        private final boolean isGetter;
        private final ProtoBuf.Property propertyProto;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PropertyAccessor(ProtoBuf.Property property, boolean z) {
            super(null);
            property.getClass();
            this.propertyProto = property;
            this.isGetter = z;
        }

        @Override // org.jetbrains.kotlin.analysis.decompiler.stub.ClsContractOwner
        public List<ProtoBuf.ValueParameter> getContextParameters() {
            List<ProtoBuf.ValueParameter> contextParameterList = this.propertyProto.getContextParameterList();
            contextParameterList.getClass();
            return contextParameterList;
        }

        @Override // org.jetbrains.kotlin.analysis.decompiler.stub.ClsContractOwner
        public ProtoBuf.Contract getContract() {
            boolean z = this.isGetter;
            ProtoBuf.Property property = this.propertyProto;
            ProtoBuf.Contract getterContract = z ? property.getGetterContract() : property.getSetterContract();
            getterContract.getClass();
            return getterContract;
        }

        public final ProtoBuf.Property getPropertyProto() {
            return this.propertyProto;
        }

        @Override // org.jetbrains.kotlin.analysis.decompiler.stub.ClsContractOwner
        public int getValueParameterCount() {
            return !this.isGetter ? 1 : 0;
        }

        @Override // org.jetbrains.kotlin.analysis.decompiler.stub.ClsContractOwner
        public List<ProtoBuf.ValueParameter> getValueParameters() {
            return this.isGetter ? CollectionsKt.emptyList() : CollectionsKt.listOf(this.propertyProto.getSetterValueParameter());
        }

        /* JADX INFO: renamed from: isGetter, reason: from getter */
        public final boolean getIsGetter() {
            return this.isGetter;
        }

        @Override // org.jetbrains.kotlin.analysis.decompiler.stub.ClsContractOwner
        public ProtoBuf.Type receiverType(TypeTable typeTable) {
            typeTable.getClass();
            return ProtoTypeTableUtilKt.receiverType(this.propertyProto, typeTable);
        }
    }

    public /* synthetic */ ClsContractOwner(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract List<ProtoBuf.ValueParameter> getContextParameters();

    public abstract ProtoBuf.Contract getContract();

    public abstract int getValueParameterCount();

    public abstract List<ProtoBuf.ValueParameter> getValueParameters();

    public abstract ProtoBuf.Type receiverType(TypeTable typeTable);

    private ClsContractOwner() {
    }
}
