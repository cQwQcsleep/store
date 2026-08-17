package org.jetbrains.kotlin.fir.backend.utils;

import defpackage.f2f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ParameterDescriptor;
import org.jetbrains.kotlin.descriptors.ValueClassRepresentation;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.backend.Fir2IrComponents;
import org.jetbrains.kotlin.fir.backend.Fir2IrConversionScope;
import org.jetbrains.kotlin.fir.backend.Fir2IrTypeConverterKt;
import org.jetbrains.kotlin.fir.backend.utils.IrElementsCreationUtilsKt;
import org.jetbrains.kotlin.fir.builder.FirPackageDirectiveBuilder;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirValueClassRepresentationKt;
import org.jetbrains.kotlin.fir.declarations.builder.FirFileBuilder;
import org.jetbrains.kotlin.fir.resolve.providers.impl.FirBuiltinSyntheticFunctionInterfaceProvider;
import org.jetbrains.kotlin.fir.resolve.providers.impl.FirExtensionSyntheticFunctionInterfaceProviderKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.ir.builders.Scope;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationParent;
import org.jetbrains.kotlin.ir.declarations.IrMutableAnnotationContainer;
import org.jetbrains.kotlin.ir.declarations.IrParameterKind;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.declarations.IrVariable;
import org.jetbrains.kotlin.ir.declarations.impl.IrFactoryImpl;
import org.jetbrains.kotlin.ir.expressions.IrConst;
import org.jetbrains.kotlin.ir.expressions.IrElseBranch;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrMemberAccessExpression;
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin;
import org.jetbrains.kotlin.ir.expressions.impl.BuildersKt;
import org.jetbrains.kotlin.ir.expressions.impl.IrBlockImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrCallImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrConstImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrWhenImpl;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrValueSymbol;
import org.jetbrains.kotlin.ir.symbols.impl.IrValueParameterSymbolImpl;
import org.jetbrains.kotlin.ir.types.IrSimpleType;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.types.IrTypesKt;
import org.jetbrains.kotlin.ir.types.impl.IrSimpleTypeImplKt;
import org.jetbrains.kotlin.ir.util.IdSignature;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Ô\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001ai\u0010\u0000\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0013H\u0000R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0014\u001a+\u0010\u0015\u001a\u00020\u0016*\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H\u0000R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u001b\u001a\"\u0010\u001c\u001a\u00020\u001d*\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u001d\u001a\"\u0010#\u001a\u00020$*\u00020\u00022\u0006\u0010%\u001a\u00020\u00062\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u001d\u001a*\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020!0'*\u00020(2\u0006\u0010)\u001a\u00020\u001d2\n\b\u0002\u0010*\u001a\u0004\u0018\u00010+\u001a\u001e\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020!0'*\u00020(2\u0006\u0010)\u001a\u00020\u001d\u001a\u001a\u0010-\u001a\n\u0012\u0004\u0012\u00020/\u0018\u00010.*\u00020\u00022\u0006\u00100\u001a\u000201\u001a\u0010\u00103\u001a\b\u0012\u0004\u0012\u0002040\u0019*\u000205\u001a?\u00106\u001a\u0002042\u0006\u00107\u001a\u00020+2\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020=2\u0017\u0010>\u001a\u0013\u0012\u0004\u0012\u00020@\u0012\u0004\u0012\u00020\u00160?¢\u0006\u0002\bA\u001a\u001a\u0010B\u001a\u00020C*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f\u001a\u001a\u0010D\u001a\u00020C*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f\u001a\u0012\u0010E\u001a\u00020F*\u00020\u00022\u0006\u0010G\u001a\u00020\u001d\"\u000e\u00102\u001a\u00020+X\u0086T¢\u0006\u0002\n\u0000¨\u0006H"}, d2 = {"declareThisReceiverParameter", "Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "c", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationParent;", "thisType", "Lorg/jetbrains/kotlin/ir/types/IrType;", "thisOrigin", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "kind", "Lorg/jetbrains/kotlin/ir/declarations/IrParameterKind;", "startOffset", Argument.Delimiters.none, "endOffset", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "explicitReceiver", "Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;", "isAssignable", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationParent;Lorg/jetbrains/kotlin/ir/types/IrType;Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;Lorg/jetbrains/kotlin/ir/declarations/IrParameterKind;IILorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;Z)Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", "setThisReceiver", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "typeParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/ir/declarations/IrClass;Ljava/util/List;)V", "createSafeCallConstruction", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "receiverVariable", "Lorg/jetbrains/kotlin/ir/declarations/IrVariable;", "receiverVariableSymbol", "Lorg/jetbrains/kotlin/ir/symbols/IrValueSymbol;", "expressionOnNotNull", "createWhenForSafeFall", "Lorg/jetbrains/kotlin/ir/expressions/impl/IrWhenImpl;", "resultType", "createTemporaryVariable", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConversionScope;", "receiverExpression", "nameHint", Argument.Delimiters.none, "createTemporaryVariableForSafeCallConstruction", "computeValueClassRepresentation", "Lorg/jetbrains/kotlin/descriptors/ValueClassRepresentation;", "Lorg/jetbrains/kotlin/ir/types/IrSimpleType;", "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "generatedBuiltinsDeclarationsFileName", "createFilesWithBuiltinsSyntheticDeclarationsIfNeeded", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "Lorg/jetbrains/kotlin/fir/FirSession;", "createSyntheticFirFileForFir2Ir", "fileName", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "fileModuleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "fileOrigin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "init", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirFileBuilder;", "Lkotlin/ExtensionFunctionType;", "constTrue", "Lorg/jetbrains/kotlin/ir/expressions/IrConst;", "constFalse", "elseBranch", "Lorg/jetbrains/kotlin/ir/expressions/IrElseBranch;", "elseExpr", "org.jetbrains.kotlin:fir2ir"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class IrElementsCreationUtilsKt {
    public static final String generatedBuiltinsDeclarationsFileName = "__GENERATED BUILTINS DECLARATIONS__.kt";

    public static IrSimpleType b(Fir2IrComponents fir2IrComponents, FirRegularClass firRegularClass, ConeRigidType coneRigidType) {
        coneRigidType.getClass();
        IrSimpleType irType$default = Fir2IrTypeConverterKt.toIrType$default(fir2IrComponents, coneRigidType, (ConversionTypeOrigin) null, 2, (Object) null);
        IrSimpleType irSimpleType = irType$default instanceof IrSimpleType ? irType$default : null;
        if (irSimpleType != null) {
            return irSimpleType;
        }
        f2f.a("Value class underlying type is not a simple type: ", UtilsKt.render(firRegularClass));
        return null;
    }

    public static final ValueClassRepresentation<IrSimpleType> computeValueClassRepresentation(final Fir2IrComponents fir2IrComponents, final FirRegularClass firRegularClass) {
        fir2IrComponents.getClass();
        firRegularClass.getClass();
        if ((FirValueClassRepresentationKt.getValueClassRepresentation(firRegularClass) != null) != (firRegularClass.getStatus().isInline() || firRegularClass.getStatus().isValue())) {
            dt1.a("Value class has no representation: ", UtilsKt.render(firRegularClass));
            return null;
        }
        ValueClassRepresentation<ConeRigidType> valueClassRepresentation = FirValueClassRepresentationKt.getValueClassRepresentation(firRegularClass);
        if (valueClassRepresentation != null) {
            return valueClassRepresentation.mapUnderlyingType(new Function1() { // from class: g07
                public final Object invoke(Object obj) {
                    return IrElementsCreationUtilsKt.b(fir2IrComponents, firRegularClass, (ConeRigidType) obj);
                }
            });
        }
        return null;
    }

    public static final IrConst constFalse(Fir2IrComponents fir2IrComponents, int i, int i2) {
        fir2IrComponents.getClass();
        return IrConstImpl.Companion.constFalse(i, i2, fir2IrComponents.getBuiltins().getBooleanType());
    }

    public static final IrConst constTrue(Fir2IrComponents fir2IrComponents, int i, int i2) {
        fir2IrComponents.getClass();
        return IrConstImpl.Companion.constTrue(i, i2, fir2IrComponents.getBuiltins().getBooleanType());
    }

    public static final List<FirFile> createFilesWithBuiltinsSyntheticDeclarationsIfNeeded(FirSession firSession) {
        firSession.getClass();
        if (!((Boolean) FirLanguageSettingsComponentKt.getLanguageVersionSettings(firSession).getFlag(AnalysisFlags.getStdlibCompilation())).booleanValue() || !FirModuleDataKt.getModuleData(firSession).getIsCommon() || !FirModuleDataKt.getModuleData(firSession).getDependsOnDependencies().isEmpty()) {
            return CollectionsKt.emptyList();
        }
        final FirBuiltinSyntheticFunctionInterfaceProvider syntheticFunctionInterfacesSymbolProvider = FirExtensionSyntheticFunctionInterfaceProviderKt.getSyntheticFunctionInterfacesSymbolProvider(firSession);
        Set<ClassId> generatedClassIds = syntheticFunctionInterfacesSymbolProvider.getGeneratedClassIds();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : generatedClassIds) {
            FqName packageFqName = ((ClassId) obj).getPackageFqName();
            Object arrayList = linkedHashMap.get(packageFqName);
            if (arrayList == null) {
                arrayList = new ArrayList();
                linkedHashMap.put(packageFqName, arrayList);
            }
            ((List) arrayList).add(obj);
        }
        ArrayList arrayList2 = new ArrayList(linkedHashMap.size());
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            FqName fqName = (FqName) entry.getKey();
            final List list = (List) entry.getValue();
            arrayList2.add(createSyntheticFirFileForFir2Ir(generatedBuiltinsDeclarationsFileName, fqName, FirModuleDataKt.getModuleData(firSession), FirDeclarationOrigin.Synthetic.Builtins.INSTANCE, new Function1() { // from class: h07
                public final Object invoke(Object obj2) {
                    return IrElementsCreationUtilsKt.createFilesWithBuiltinsSyntheticDeclarationsIfNeeded$lambda$1$0(list, syntheticFunctionInterfacesSymbolProvider, (FirFileBuilder) obj2);
                }
            }));
        }
        return arrayList2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit createFilesWithBuiltinsSyntheticDeclarationsIfNeeded$lambda$1$0(List list, FirBuiltinSyntheticFunctionInterfaceProvider firBuiltinSyntheticFunctionInterfaceProvider, FirFileBuilder firFileBuilder) {
        firFileBuilder.getClass();
        List<FirDeclaration> declarations = firFileBuilder.getDeclarations();
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            FirRegularClassSymbol classLikeSymbolByClassId = firBuiltinSyntheticFunctionInterfaceProvider.getClassLikeSymbolByClassId((ClassId) it.next());
            FirRegularClass firRegularClass = classLikeSymbolByClassId != null ? (FirRegularClass) classLikeSymbolByClassId.getFir() : null;
            if (firRegularClass != null) {
                arrayList.add(firRegularClass);
            }
        }
        CollectionsKt.addAll(declarations, arrayList);
        return Unit.INSTANCE;
    }

    public static final IrExpression createSafeCallConstruction(Fir2IrComponents fir2IrComponents, IrVariable irVariable, IrValueSymbol irValueSymbol, IrExpression irExpression) {
        fir2IrComponents.getClass();
        irVariable.getClass();
        irValueSymbol.getClass();
        irExpression.getClass();
        int startOffset = irExpression.getStartOffset();
        int endOffset = irExpression.getEndOffset();
        IrType irTypeMakeNullable = IrTypesKt.makeNullable(irExpression.getType());
        IrBlockImpl IrBlockImpl = BuildersKt.IrBlockImpl(startOffset, endOffset, irTypeMakeNullable, IrStatementOrigin.Companion.getSAFE_CALL());
        IrBlockImpl.getStatements().add(irVariable);
        IrBlockImpl.getStatements().add(createWhenForSafeFall(fir2IrComponents, irTypeMakeNullable, irValueSymbol, irExpression));
        return IrBlockImpl;
    }

    public static final FirFile createSyntheticFirFileForFir2Ir(String str, FqName fqName, FirModuleData firModuleData, FirDeclarationOrigin firDeclarationOrigin, Function1<? super FirFileBuilder, Unit> function1) {
        str.getClass();
        fqName.getClass();
        firModuleData.getClass();
        firDeclarationOrigin.getClass();
        function1.getClass();
        FirFileBuilder firFileBuilder = new FirFileBuilder();
        firFileBuilder.setOrigin(firDeclarationOrigin);
        firFileBuilder.setModuleData(firModuleData);
        FirPackageDirectiveBuilder firPackageDirectiveBuilder = new FirPackageDirectiveBuilder();
        firPackageDirectiveBuilder.setPackageFqName(fqName);
        firFileBuilder.setPackageDirective(firPackageDirectiveBuilder.build());
        firFileBuilder.setName(str);
        function1.invoke(firFileBuilder);
        return firFileBuilder.mo288build();
    }

    public static final Pair<IrVariable, IrValueSymbol> createTemporaryVariable(Fir2IrConversionScope fir2IrConversionScope, IrExpression irExpression, String str) {
        fir2IrConversionScope.getClass();
        irExpression.getClass();
        IrVariable irVariableCreateTemporaryVariable$default = Scope.createTemporaryVariable$default(fir2IrConversionScope.scope(), irExpression, str, false, (IrDeclarationOrigin) null, (IrType) null, 0, 0, false, 252, (Object) null);
        return new Pair<>(irVariableCreateTemporaryVariable$default, irVariableCreateTemporaryVariable$default.getSymbol());
    }

    public static /* synthetic */ Pair createTemporaryVariable$default(Fir2IrConversionScope fir2IrConversionScope, IrExpression irExpression, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        return createTemporaryVariable(fir2IrConversionScope, irExpression, str);
    }

    public static final Pair<IrVariable, IrValueSymbol> createTemporaryVariableForSafeCallConstruction(Fir2IrConversionScope fir2IrConversionScope, IrExpression irExpression) {
        fir2IrConversionScope.getClass();
        irExpression.getClass();
        return createTemporaryVariable(fir2IrConversionScope, irExpression, "safe_receiver");
    }

    public static final IrWhenImpl createWhenForSafeFall(Fir2IrComponents fir2IrComponents, IrType irType, IrValueSymbol irValueSymbol, IrExpression irExpression) {
        fir2IrComponents.getClass();
        irType.getClass();
        irValueSymbol.getClass();
        irExpression.getClass();
        int startOffset = irExpression.getStartOffset();
        int endOffset = irExpression.getEndOffset();
        IrWhenImpl irWhenImplIrWhenImpl$default = BuildersKt.IrWhenImpl$default(startOffset, endOffset, irType, (IrStatementOrigin) null, 8, (Object) null);
        IrCallImpl irCallImplIrCallImplWithShape$default = BuildersKt.IrCallImplWithShape$default(startOffset, endOffset, fir2IrComponents.getBuiltins().getBooleanType(), fir2IrComponents.getBuiltins().getEqeqSymbol(), 0, 2, 0, false, false, IrStatementOrigin.Companion.getEQEQ(), (IrClassSymbol) null, 1024, (Object) null);
        irCallImplIrCallImplWithShape$default.getArguments().set(0, BuildersKt.IrGetValueImpl$default(startOffset, endOffset, irValueSymbol, (IrStatementOrigin) null, 8, (Object) null));
        IrMemberAccessExpression.ValueArgumentsList arguments = irCallImplIrCallImplWithShape$default.getArguments();
        IrConstImpl.Companion companion = IrConstImpl.Companion;
        arguments.set(1, companion.constNull(startOffset, endOffset, fir2IrComponents.getBuiltins().getNothingNType()));
        irWhenImplIrWhenImpl$default.getBranches().add(BuildersKt.IrBranchImpl(irCallImplIrCallImplWithShape$default, companion.constNull(startOffset, endOffset, fir2IrComponents.getBuiltins().getNothingNType())));
        irWhenImplIrWhenImpl$default.getBranches().add(BuildersKt.IrElseBranchImpl(companion.boolean(startOffset, endOffset, fir2IrComponents.getBuiltins().getBooleanType(), true), irExpression));
        return irWhenImplIrWhenImpl$default;
    }

    public static final IrValueParameter declareThisReceiverParameter(Fir2IrComponents fir2IrComponents, IrDeclarationParent irDeclarationParent, IrType irType, IrDeclarationOrigin irDeclarationOrigin, IrParameterKind irParameterKind, int i, int i2, Name name, FirReceiverParameter firReceiverParameter, boolean z) {
        fir2IrComponents.getClass();
        irDeclarationParent.getClass();
        irType.getClass();
        irDeclarationOrigin.getClass();
        irParameterKind.getClass();
        name.getClass();
        IrValueParameter irValueParameterCreateValueParameter = IrFactoryImpl.INSTANCE.createValueParameter(i, i2, irDeclarationOrigin, irParameterKind, name, irType, z, new IrValueParameterSymbolImpl((ParameterDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null), (IrType) null, false, false, false);
        irValueParameterCreateValueParameter.setParent(irDeclarationParent);
        if (firReceiverParameter != null) {
            fir2IrComponents.getAnnotationGenerator().generate((IrMutableAnnotationContainer) irValueParameterCreateValueParameter, (FirAnnotationContainer) firReceiverParameter);
        }
        return irValueParameterCreateValueParameter;
    }

    public static /* synthetic */ IrValueParameter declareThisReceiverParameter$default(Fir2IrComponents fir2IrComponents, IrDeclarationParent irDeclarationParent, IrType irType, IrDeclarationOrigin irDeclarationOrigin, IrParameterKind irParameterKind, int i, int i2, Name name, FirReceiverParameter firReceiverParameter, boolean z, int i3, Object obj) {
        return declareThisReceiverParameter(fir2IrComponents, irDeclarationParent, irType, irDeclarationOrigin, irParameterKind, (i3 & 16) != 0 ? irDeclarationParent.getStartOffset() : i, (i3 & 32) != 0 ? irDeclarationParent.getEndOffset() : i2, (i3 & 64) != 0 ? SpecialNames.THIS : name, (i3 & 128) != 0 ? null : firReceiverParameter, (i3 & 256) != 0 ? false : z);
    }

    public static final IrElseBranch elseBranch(Fir2IrComponents fir2IrComponents, IrExpression irExpression) {
        fir2IrComponents.getClass();
        irExpression.getClass();
        int startOffset = irExpression.getStartOffset();
        int endOffset = irExpression.getEndOffset();
        return BuildersKt.IrElseBranchImpl(startOffset, endOffset, constTrue(fir2IrComponents, startOffset, endOffset), irExpression);
    }

    public static final void setThisReceiver(Fir2IrComponents fir2IrComponents, IrClass irClass, List<? extends FirTypeParameterRef> list) {
        fir2IrComponents.getClass();
        irClass.getClass();
        list.getClass();
        List<? extends FirTypeParameterRef> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(IrSimpleTypeImplKt.IrSimpleTypeImpl(fir2IrComponents.getClassifierStorage().getIrTypeParameterSymbol(((FirTypeParameterRef) it.next()).getSymbol(), ConversionTypeOrigin.DEFAULT), false, CollectionsKt.emptyList(), CollectionsKt.emptyList()));
        }
        irClass.setThisReceiver(declareThisReceiverParameter$default(fir2IrComponents, irClass, IrSimpleTypeImplKt.IrSimpleTypeImpl(irClass.getSymbol(), false, arrayList, CollectionsKt.emptyList()), IrDeclarationOrigin.Companion.getINSTANCE_RECEIVER(), IrParameterKind.DispatchReceiver, 0, 0, null, null, false, 496, null));
    }
}
