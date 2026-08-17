package org.jetbrains.kotlin.fir.plugin;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.GeneratedDeclarationKey;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.fir.FirFunctionTarget;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOriginKt;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.builder.FirNamedFunctionBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirReceiverParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.expressions.builder.FirReturnExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.impl.FirSingleExpressionBlock;
import org.jetbrains.kotlin.fir.plugin.SimpleFunctionBuildingContext;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReceiverParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.name.CallableId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BQ\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0002\b\u0003\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0018\u0010\u000b\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0004\u0012\u00020\u000f0\f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u000fJ \u0010\u0017\u001a\u00020\u00182\u0018\u0010\u001a\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0004\u0012\u00020\u000f0\fJ\u0006\u0010\u001b\u001a\u00020\u0018J\b\u0010\u001c\u001a\u00020\u0002H\u0016R \u0010\u000b\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0004\u0012\u00020\u000f0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0014\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0004\u0012\u00020\u000f\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/fir/plugin/SimpleFunctionBuildingContext;", "Lorg/jetbrains/kotlin/fir/plugin/FunctionBuildingContext;", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "key", "Lorg/jetbrains/kotlin/GeneratedDeclarationKey;", "owner", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "callableId", "Lorg/jetbrains/kotlin/name/CallableId;", "returnTypeProvider", "Lkotlin/Function1;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "containingFileName", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/GeneratedDeclarationKey;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;Lorg/jetbrains/kotlin/name/CallableId;Lkotlin/jvm/functions/Function1;Ljava/lang/String;)V", "extensionReceiverTypeProvider", "generateDefaultBody", Argument.Delimiters.none, "extensionReceiverType", Argument.Delimiters.none, ModuleXmlParser.TYPE, "typeProvider", "withGeneratedDefaultBody", "build", "org.jetbrains.kotlin:plugin-utils"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SimpleFunctionBuildingContext extends FunctionBuildingContext<FirNamedFunction> {
    private final String containingFileName;
    private Function1<? super List<? extends FirTypeParameter>, ? extends ConeKotlinType> extensionReceiverTypeProvider;
    private boolean generateDefaultBody;
    private final Function1<List<? extends FirTypeParameter>, ConeKotlinType> returnTypeProvider;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SimpleFunctionBuildingContext(FirSession firSession, GeneratedDeclarationKey generatedDeclarationKey, FirClassSymbol<?> firClassSymbol, CallableId callableId, Function1<? super List<? extends FirTypeParameter>, ? extends ConeKotlinType> function1, String str) {
        super(callableId, firSession, generatedDeclarationKey, firClassSymbol, null);
        firSession.getClass();
        generatedDeclarationKey.getClass();
        callableId.getClass();
        function1.getClass();
        this.returnTypeProvider = function1;
        this.containingFileName = str;
    }

    public static ConeKotlinType d(ConeKotlinType coneKotlinType, List list) {
        list.getClass();
        return coneKotlinType;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x005a  */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.plugin.DeclarationBuildingContext
    public FirNamedFunction build() {
        boolean z;
        FirFunctionTarget firFunctionTarget;
        ConeKotlinType coneKotlinType;
        FirNamedFunctionBuilder firNamedFunctionBuilder = new FirNamedFunctionBuilder();
        firNamedFunctionBuilder.setResolvePhase(FirResolvePhase.BODY_RESOLVE);
        firNamedFunctionBuilder.setModuleData(FirModuleDataKt.getModuleData(getSession()));
        firNamedFunctionBuilder.setOrigin(FirDeclarationOriginKt.getOrigin(getKey()));
        firNamedFunctionBuilder.setSource(getSourceForFirDeclaration());
        firNamedFunctionBuilder.setSymbol(new FirNamedFunctionSymbol(getCallableId()));
        firNamedFunctionBuilder.setName(getCallableId().getCallableName());
        firNamedFunctionBuilder.setStatus(generateStatus());
        FirClassSymbol<?> owner = getOwner();
        if (owner != null) {
            z = ((FirClassLikeDeclaration) owner.getFir()).getIsLocal();
        }
        firNamedFunctionBuilder.setLocal(z);
        FirClassSymbol<?> owner2 = getOwner();
        firNamedFunctionBuilder.setDispatchReceiverType(owner2 != null ? ScopeUtilsKt.defaultType(owner2) : null);
        List<DeclarationBuildingContext.TypeParameterData> typeParameters = getTypeParameters();
        List<FirTypeParameter> typeParameters2 = firNamedFunctionBuilder.getTypeParameters();
        Iterator<T> it = typeParameters.iterator();
        while (it.hasNext()) {
            typeParameters2.add(generateTypeParameter((DeclarationBuildingContext.TypeParameterData) it.next(), firNamedFunctionBuilder.getSymbol()));
        }
        initTypeParameterBounds(firNamedFunctionBuilder.getTypeParameters(), firNamedFunctionBuilder.getTypeParameters());
        produceContextReceiversTo(firNamedFunctionBuilder.getContextParameters(), firNamedFunctionBuilder.getTypeParameters(), firNamedFunctionBuilder.getOrigin(), firNamedFunctionBuilder.getSymbol());
        List<FunctionBuildingContext.ValueParameterData> valueParameters = getValueParameters();
        List<FirValueParameter> valueParameters2 = firNamedFunctionBuilder.getValueParameters();
        Iterator<T> it2 = valueParameters.iterator();
        while (it2.hasNext()) {
            valueParameters2.add(generateValueParameter((FunctionBuildingContext.ValueParameterData) it2.next(), firNamedFunctionBuilder.getSymbol(), firNamedFunctionBuilder.getTypeParameters()));
        }
        firNamedFunctionBuilder.setReturnTypeRef(UtilsKt.toFirResolvedTypeRef$default((ConeKotlinType) this.returnTypeProvider.invoke(firNamedFunctionBuilder.getTypeParameters()), null, null, 3, null));
        Function1<? super List<? extends FirTypeParameter>, ? extends ConeKotlinType> function1 = this.extensionReceiverTypeProvider;
        if (function1 != null && (coneKotlinType = (ConeKotlinType) function1.invoke(firNamedFunctionBuilder.getTypeParameters())) != null) {
            FirReceiverParameterBuilder firReceiverParameterBuilder = new FirReceiverParameterBuilder();
            firReceiverParameterBuilder.setTypeRef(UtilsKt.toFirResolvedTypeRef$default(coneKotlinType, null, null, 3, null));
            firReceiverParameterBuilder.setSymbol(new FirReceiverParameterSymbol());
            firReceiverParameterBuilder.setModuleData(FirModuleDataKt.getModuleData(getSession()));
            firReceiverParameterBuilder.setOrigin(FirDeclarationOriginKt.getOrigin(getKey()));
            firReceiverParameterBuilder.setContainingDeclarationSymbol(firNamedFunctionBuilder.getSymbol());
            firNamedFunctionBuilder.setReceiverParameter(firReceiverParameterBuilder.mo288build());
        }
        if (!this.generateDefaultBody || getModality() == Modality.ABSTRACT) {
            firFunctionTarget = null;
        } else {
            FirReturnExpressionBuilder firReturnExpressionBuilder = new FirReturnExpressionBuilder();
            firReturnExpressionBuilder.setResult(generateExpressionStub());
            firFunctionTarget = new FirFunctionTarget(null, false);
            firReturnExpressionBuilder.setTarget(firFunctionTarget);
            firNamedFunctionBuilder.setBody(new FirSingleExpressionBlock(firReturnExpressionBuilder.mo288build()));
        }
        FirNamedFunction firNamedFunctionMo288build = firNamedFunctionBuilder.mo288build();
        if (this.containingFileName != null && getCallableId().getClassId() != null) {
            wec.a("containingFileName could be set only for top-level declarations, but ", getCallableId(), " is a member");
            return null;
        }
        DeclarationAttributesKt.setFileNameForPluginGeneratedCallable(firNamedFunctionMo288build, this.containingFileName);
        if (firFunctionTarget != null) {
            firFunctionTarget.bind(firNamedFunctionMo288build);
        }
        return firNamedFunctionMo288build;
    }

    public final void extensionReceiverType(Function1<? super List<? extends FirTypeParameter>, ? extends ConeKotlinType> typeProvider) {
        typeProvider.getClass();
        if (this.extensionReceiverTypeProvider == null) {
            this.extensionReceiverTypeProvider = typeProvider;
        } else {
            w01.a("Extension receiver type is already initialized");
        }
    }

    public final void withGeneratedDefaultBody() {
        this.generateDefaultBody = true;
    }

    public final void extensionReceiverType(final ConeKotlinType type) {
        type.getClass();
        extensionReceiverType(new Function1() { // from class: wcd
            public final Object invoke(Object obj) {
                return SimpleFunctionBuildingContext.d(type, (List) obj);
            }
        });
    }
}
