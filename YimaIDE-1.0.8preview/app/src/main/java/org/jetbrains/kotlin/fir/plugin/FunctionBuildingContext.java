package org.jetbrains.kotlin.fir.plugin;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.GeneratedDeclarationKey;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOriginKt;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.builder.FirValueParameterBuilder;
import org.jetbrains.kotlin.fir.plugin.FunctionBuildingContext;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001)B/\b\u0004\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\f\u0010\n\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000b¢\u0006\u0004\b\f\u0010\rJH\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001c2\b\b\u0002\u0010\u001e\u001a\u00020\u001c2\b\b\u0002\u0010\u001f\u001a\u00020\u001c2\b\b\u0002\u0010\b\u001a\u00020\tJZ\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0018\u0010 \u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\"\u0012\u0004\u0012\u00020\u001a0!2\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001c2\b\b\u0002\u0010\u001e\u001a\u00020\u001c2\b\b\u0002\u0010\u001f\u001a\u00020\u001c2\b\b\u0002\u0010\b\u001a\u00020\tJ*\u0010$\u001a\u00020%2\u0006\u0010\u0015\u001a\u00020\u00122\n\u0010&\u001a\u0006\u0012\u0002\b\u00030'2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020#0\"H\u0004R\u0014\u0010\u0004\u001a\u00020\u0005X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014\u0082\u0001\u0002*+¨\u0006,"}, d2 = {"Lorg/jetbrains/kotlin/fir/plugin/FunctionBuildingContext;", "T", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "Lorg/jetbrains/kotlin/fir/plugin/DeclarationBuildingContext;", "callableId", "Lorg/jetbrains/kotlin/name/CallableId;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "key", "Lorg/jetbrains/kotlin/GeneratedDeclarationKey;", "owner", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "<init>", "(Lorg/jetbrains/kotlin/name/CallableId;Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/GeneratedDeclarationKey;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;)V", "getCallableId", "()Lorg/jetbrains/kotlin/name/CallableId;", "valueParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/plugin/FunctionBuildingContext$ValueParameterData;", "getValueParameters", "()Ljava/util/List;", "valueParameter", Argument.Delimiters.none, ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "isCrossinline", Argument.Delimiters.none, "isNoinline", "isVararg", "hasDefaultValue", "typeProvider", "Lkotlin/Function1;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;", "generateValueParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "containingFunctionSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "functionTypeParameters", "ValueParameterData", "Lorg/jetbrains/kotlin/fir/plugin/ConstructorBuildingContext;", "Lorg/jetbrains/kotlin/fir/plugin/SimpleFunctionBuildingContext;", "org.jetbrains.kotlin:plugin-utils"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FunctionBuildingContext<T extends FirFunction> extends DeclarationBuildingContext<T> {
    private final CallableId callableId;
    private final List<ValueParameterData> valueParameters;

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0084\b\u0018\u00002\u00020\u0001BQ\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0018\u0010\u0004\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0004\u0012\u00020\b0\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\n\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\u001b\u0010\u001b\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0004\u0012\u00020\b0\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\nHÆ\u0003J\t\u0010\u001d\u001a\u00020\nHÆ\u0003J\t\u0010\u001e\u001a\u00020\nHÆ\u0003J\t\u0010\u001f\u001a\u00020\nHÆ\u0003J\t\u0010 \u001a\u00020\u000fHÆ\u0003Ja\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u001a\b\u0002\u0010\u0004\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0004\u0012\u00020\b0\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\n2\b\b\u0002\u0010\u000e\u001a\u00020\u000fHÆ\u0001J\u0014\u0010\"\u001a\u00020\n2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010$\u001a\u00020%HÖ\u0081\u0004J\n\u0010&\u001a\u00020'HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R#\u0010\u0004\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0016R\u0011\u0010\f\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0016R\u0011\u0010\r\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006("}, d2 = {"Lorg/jetbrains/kotlin/fir/plugin/FunctionBuildingContext$ValueParameterData;", Argument.Delimiters.none, ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "typeProvider", "Lkotlin/Function1;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "isCrossinline", Argument.Delimiters.none, "isNoinline", "isVararg", "hasDefaultValue", "key", "Lorg/jetbrains/kotlin/GeneratedDeclarationKey;", "<init>", "(Lorg/jetbrains/kotlin/name/Name;Lkotlin/jvm/functions/Function1;ZZZZLorg/jetbrains/kotlin/GeneratedDeclarationKey;)V", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "getTypeProvider", "()Lkotlin/jvm/functions/Function1;", "()Z", "getHasDefaultValue", "getKey", "()Lorg/jetbrains/kotlin/GeneratedDeclarationKey;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:plugin-utils"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class ValueParameterData {
        private final boolean hasDefaultValue;
        private final boolean isCrossinline;
        private final boolean isNoinline;
        private final boolean isVararg;
        private final GeneratedDeclarationKey key;
        private final Name name;
        private final Function1<List<? extends FirTypeParameterRef>, ConeKotlinType> typeProvider;

        /* JADX WARN: Multi-variable type inference failed */
        public ValueParameterData(Name name, Function1<? super List<? extends FirTypeParameterRef>, ? extends ConeKotlinType> function1, boolean z, boolean z2, boolean z3, boolean z4, GeneratedDeclarationKey generatedDeclarationKey) {
            name.getClass();
            function1.getClass();
            generatedDeclarationKey.getClass();
            this.name = name;
            this.typeProvider = function1;
            this.isCrossinline = z;
            this.isNoinline = z2;
            this.isVararg = z3;
            this.hasDefaultValue = z4;
            this.key = generatedDeclarationKey;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ValueParameterData copy$default(ValueParameterData valueParameterData, Name name, Function1 function1, boolean z, boolean z2, boolean z3, boolean z4, GeneratedDeclarationKey generatedDeclarationKey, int i, Object obj) {
            if ((i & 1) != 0) {
                name = valueParameterData.name;
            }
            if ((i & 2) != 0) {
                function1 = valueParameterData.typeProvider;
            }
            if ((i & 4) != 0) {
                z = valueParameterData.isCrossinline;
            }
            if ((i & 8) != 0) {
                z2 = valueParameterData.isNoinline;
            }
            if ((i & 16) != 0) {
                z3 = valueParameterData.isVararg;
            }
            if ((i & 32) != 0) {
                z4 = valueParameterData.hasDefaultValue;
            }
            if ((i & 64) != 0) {
                generatedDeclarationKey = valueParameterData.key;
            }
            boolean z5 = z4;
            GeneratedDeclarationKey generatedDeclarationKey2 = generatedDeclarationKey;
            boolean z6 = z3;
            boolean z7 = z;
            return valueParameterData.copy(name, function1, z7, z2, z6, z5, generatedDeclarationKey2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Name getName() {
            return this.name;
        }

        public final Function1<List<? extends FirTypeParameterRef>, ConeKotlinType> component2() {
            return this.typeProvider;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getIsCrossinline() {
            return this.isCrossinline;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getIsNoinline() {
            return this.isNoinline;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final boolean getIsVararg() {
            return this.isVararg;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final boolean getHasDefaultValue() {
            return this.hasDefaultValue;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final GeneratedDeclarationKey getKey() {
            return this.key;
        }

        public final ValueParameterData copy(Name name, Function1<? super List<? extends FirTypeParameterRef>, ? extends ConeKotlinType> typeProvider, boolean isCrossinline, boolean isNoinline, boolean isVararg, boolean hasDefaultValue, GeneratedDeclarationKey key) {
            name.getClass();
            typeProvider.getClass();
            key.getClass();
            return new ValueParameterData(name, typeProvider, isCrossinline, isNoinline, isVararg, hasDefaultValue, key);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ValueParameterData)) {
                return false;
            }
            ValueParameterData valueParameterData = (ValueParameterData) other;
            return Intrinsics.areEqual(this.name, valueParameterData.name) && Intrinsics.areEqual(this.typeProvider, valueParameterData.typeProvider) && this.isCrossinline == valueParameterData.isCrossinline && this.isNoinline == valueParameterData.isNoinline && this.isVararg == valueParameterData.isVararg && this.hasDefaultValue == valueParameterData.hasDefaultValue && Intrinsics.areEqual(this.key, valueParameterData.key);
        }

        public final boolean getHasDefaultValue() {
            return this.hasDefaultValue;
        }

        public final GeneratedDeclarationKey getKey() {
            return this.key;
        }

        public final Name getName() {
            return this.name;
        }

        public final Function1<List<? extends FirTypeParameterRef>, ConeKotlinType> getTypeProvider() {
            return this.typeProvider;
        }

        public int hashCode() {
            return (((((((((((this.name.hashCode() * 31) + this.typeProvider.hashCode()) * 31) + Boolean.hashCode(this.isCrossinline)) * 31) + Boolean.hashCode(this.isNoinline)) * 31) + Boolean.hashCode(this.isVararg)) * 31) + Boolean.hashCode(this.hasDefaultValue)) * 31) + this.key.hashCode();
        }

        public final boolean isCrossinline() {
            return this.isCrossinline;
        }

        public final boolean isNoinline() {
            return this.isNoinline;
        }

        public final boolean isVararg() {
            return this.isVararg;
        }

        public String toString() {
            return "ValueParameterData(name=" + this.name + ", typeProvider=" + this.typeProvider + ", isCrossinline=" + this.isCrossinline + ", isNoinline=" + this.isNoinline + ", isVararg=" + this.isVararg + ", hasDefaultValue=" + this.hasDefaultValue + ", key=" + this.key + ')';
        }
    }

    private FunctionBuildingContext(CallableId callableId, FirSession firSession, GeneratedDeclarationKey generatedDeclarationKey, FirClassSymbol<?> firClassSymbol) {
        super(firSession, generatedDeclarationKey, firClassSymbol, null);
        this.callableId = callableId;
        this.valueParameters = new ArrayList();
    }

    public static ConeKotlinType c(ConeKotlinType coneKotlinType, List list) {
        list.getClass();
        return coneKotlinType;
    }

    public static /* synthetic */ void valueParameter$default(FunctionBuildingContext functionBuildingContext, Name name, ConeKotlinType coneKotlinType, boolean z, boolean z2, boolean z3, boolean z4, GeneratedDeclarationKey generatedDeclarationKey, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: valueParameter");
            return;
        }
        if ((i & 4) != 0) {
            z = false;
        }
        if ((i & 8) != 0) {
            z2 = false;
        }
        if ((i & 16) != 0) {
            z3 = false;
        }
        if ((i & 32) != 0) {
            z4 = false;
        }
        if ((i & 64) != 0) {
            generatedDeclarationKey = functionBuildingContext.getKey();
        }
        functionBuildingContext.valueParameter(name, coneKotlinType, z, z2, z3, z4, generatedDeclarationKey);
    }

    public final FirValueParameter generateValueParameter(ValueParameterData valueParameter, FirFunctionSymbol<?> containingFunctionSymbol, List<? extends FirTypeParameterRef> functionTypeParameters) {
        valueParameter.getClass();
        containingFunctionSymbol.getClass();
        functionTypeParameters.getClass();
        FirValueParameterBuilder firValueParameterBuilder = new FirValueParameterBuilder();
        firValueParameterBuilder.setResolvePhase(FirResolvePhase.BODY_RESOLVE);
        firValueParameterBuilder.setModuleData(FirModuleDataKt.getModuleData(getSession()));
        firValueParameterBuilder.setOrigin(FirDeclarationOriginKt.getOrigin(valueParameter.getKey()));
        firValueParameterBuilder.setReturnTypeRef(UtilsKt.toFirResolvedTypeRef$default((ConeKotlinType) valueParameter.getTypeProvider().invoke(functionTypeParameters), null, null, 3, null));
        firValueParameterBuilder.setName(valueParameter.getName());
        firValueParameterBuilder.setSymbol(new FirValueParameterSymbol());
        if (valueParameter.getHasDefaultValue()) {
            firValueParameterBuilder.setDefaultValue(generateExpressionStub());
        }
        firValueParameterBuilder.setContainingDeclarationSymbol(containingFunctionSymbol);
        firValueParameterBuilder.setCrossinline(valueParameter.isCrossinline());
        firValueParameterBuilder.setNoinline(valueParameter.isNoinline());
        firValueParameterBuilder.setVararg(valueParameter.isVararg());
        return firValueParameterBuilder.mo288build();
    }

    public final CallableId getCallableId() {
        return this.callableId;
    }

    public final List<ValueParameterData> getValueParameters() {
        return this.valueParameters;
    }

    public final void valueParameter(Name name, Function1<? super List<? extends FirTypeParameterRef>, ? extends ConeKotlinType> typeProvider, boolean isCrossinline, boolean isNoinline, boolean isVararg, boolean hasDefaultValue, GeneratedDeclarationKey key) {
        name.getClass();
        typeProvider.getClass();
        key.getClass();
        this.valueParameters.add(new ValueParameterData(name, typeProvider, isCrossinline, isNoinline, isVararg, hasDefaultValue, key));
    }

    public /* synthetic */ FunctionBuildingContext(CallableId callableId, FirSession firSession, GeneratedDeclarationKey generatedDeclarationKey, FirClassSymbol firClassSymbol, DefaultConstructorMarker defaultConstructorMarker) {
        this(callableId, firSession, generatedDeclarationKey, firClassSymbol);
    }

    public final void valueParameter(Name name, final ConeKotlinType type, boolean isCrossinline, boolean isNoinline, boolean isVararg, boolean hasDefaultValue, GeneratedDeclarationKey key) {
        name.getClass();
        type.getClass();
        key.getClass();
        valueParameter(name, new Function1() { // from class: fr5
            public final Object invoke(Object obj) {
                return FunctionBuildingContext.c(type, (List) obj);
            }
        }, isCrossinline, isNoinline, isVararg, hasDefaultValue, key);
    }

    public static /* synthetic */ void valueParameter$default(FunctionBuildingContext functionBuildingContext, Name name, Function1 function1, boolean z, boolean z2, boolean z3, boolean z4, GeneratedDeclarationKey generatedDeclarationKey, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: valueParameter");
            return;
        }
        if ((i & 4) != 0) {
            z = false;
        }
        if ((i & 8) != 0) {
            z2 = false;
        }
        if ((i & 16) != 0) {
            z3 = false;
        }
        if ((i & 32) != 0) {
            z4 = false;
        }
        if ((i & 64) != 0) {
            generatedDeclarationKey = functionBuildingContext.getKey();
        }
        functionBuildingContext.valueParameter(name, (Function1<? super List<? extends FirTypeParameterRef>, ? extends ConeKotlinType>) function1, z, z2, z3, z4, generatedDeclarationKey);
    }
}
