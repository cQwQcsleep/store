package org.jetbrains.kotlin.fir.backend;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.backend.common.actualizer.IrMissingActualDeclarationProvider;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassConstructorDescriptor;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities;
import org.jetbrains.kotlin.descriptors.DescriptorVisibility;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.ParameterDescriptor;
import org.jetbrains.kotlin.descriptors.PropertyDescriptor;
import org.jetbrains.kotlin.descriptors.SourceElement;
import org.jetbrains.kotlin.descriptors.TypeParameterDescriptor;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.backend.LenientModeMissingActualDeclarationProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrConstructor;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationParent;
import org.jetbrains.kotlin.ir.declarations.IrFactory;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.declarations.IrProperty;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.IrTypeParameter;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.declarations.impl.IrFactoryImpl;
import org.jetbrains.kotlin.ir.expressions.IrAnnotation;
import org.jetbrains.kotlin.ir.expressions.IrBlockBody;
import org.jetbrains.kotlin.ir.expressions.IrConstKind;
import org.jetbrains.kotlin.ir.expressions.IrMemberAccessExpression;
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin;
import org.jetbrains.kotlin.ir.expressions.impl.BuildersKt;
import org.jetbrains.kotlin.ir.expressions.impl.IrAnnotationImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrConstImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrReturnImpl;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrConstructorSymbol;
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.ir.symbols.IrSymbol;
import org.jetbrains.kotlin.ir.symbols.impl.IrClassSymbolImpl;
import org.jetbrains.kotlin.ir.symbols.impl.IrConstructorSymbolImpl;
import org.jetbrains.kotlin.ir.symbols.impl.IrPropertySymbolImpl;
import org.jetbrains.kotlin.ir.symbols.impl.IrSimpleFunctionSymbolImpl;
import org.jetbrains.kotlin.ir.symbols.impl.IrTypeParameterSymbolImpl;
import org.jetbrains.kotlin.ir.symbols.impl.IrValueParameterSymbolImpl;
import org.jetbrains.kotlin.ir.types.IrSimpleType;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.types.IrTypeArgument;
import org.jetbrains.kotlin.ir.types.IrTypePredicatesKt;
import org.jetbrains.kotlin.ir.types.IrTypesKt;
import org.jetbrains.kotlin.ir.util.DeepCopyIrTreeWithSymbols;
import org.jetbrains.kotlin.ir.util.DeepCopySymbolRemapper;
import org.jetbrains.kotlin.ir.util.DeepCopyTypeRemapper;
import org.jetbrains.kotlin.ir.util.DescriptorsRemapper;
import org.jetbrains.kotlin.ir.util.DumpIrTreeOptions;
import org.jetbrains.kotlin.ir.util.IdSignature;
import org.jetbrains.kotlin.ir.util.IrTypeUtilsKt;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.ir.util.RenderIrElementKt;
import org.jetbrains.kotlin.ir.visitors.IrVisitorsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 :2\u00020\u0001:\u0001:B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ&\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u00192\b\u0010\u001b\u001a\u0004\u0018\u00010\u00142\b\u0010\u001c\u001a\u0004\u0018\u00010\u0014H\u0016J\u001a\u0010\u001d\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u001a\u0010 \u001a\u0004\u0018\u00010\u00142\u0006\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0002J\u0018\u0010%\u001a\u00020&2\u0006\u0010\u001a\u001a\u00020&2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0018\u0010'\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0010\u0010(\u001a\u00020)2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0018\u0010*\u001a\u00020+2\u0006\u0010\u001a\u001a\u00020+2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0010\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0002J\u0010\u00100\u001a\u0002012\u0006\u00102\u001a\u000203H\u0002J \u00104\u001a\u00020-2\u0006\u0010.\u001a\u0002052\u0006\u00106\u001a\u0002052\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0014\u00107\u001a\u000208*\u0002082\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0014\u00107\u001a\u000209*\u0002092\u0006\u0010\u001e\u001a\u00020\u001fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u001d\u0010\u0010\u001a\u0004\u0018\u00010\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0011\u0010\rR\u001b\u0010\u0013\u001a\u00020\u00148BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u000f\u001a\u0004\b\u0015\u0010\u0016¨\u0006;"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/LenientModeMissingActualDeclarationProvider;", "Lorg/jetbrains/kotlin/backend/common/actualizer/IrMissingActualDeclarationProvider;", "builtins", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "classifierStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "symbolProvider", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "<init>", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;)V", "notImplementedErrorConstructorSymbol", "Lorg/jetbrains/kotlin/ir/symbols/IrConstructorSymbol;", "getNotImplementedErrorConstructorSymbol", "()Lorg/jetbrains/kotlin/ir/symbols/IrConstructorSymbol;", "notImplementedErrorConstructorSymbol$delegate", "Lkotlin/Lazy;", "throwsConstructorSymbol", "getThrowsConstructorSymbol", "throwsConstructorSymbol$delegate", "kClassSymbol", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "getKClassSymbol", "()Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "kClassSymbol$delegate", "provideSymbolForMissingActual", "Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "expectSymbol", "containingExpectClassSymbol", "containingActualClassSymbol", "buildStub", "parent", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationParent;", "buildClassStub", "createObjectConstructor", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "clazz", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "buildPropertyStub", "Lorg/jetbrains/kotlin/ir/symbols/IrPropertySymbol;", "buildConstructorStub", "createConstructorBody", "Lorg/jetbrains/kotlin/ir/expressions/IrBlockBody;", "buildFunctionStub", "Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "addThrowsAnnotation", Argument.Delimiters.none, "function", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "generateDefaultReturnValue", "Lorg/jetbrains/kotlin/ir/expressions/impl/IrConstImpl;", "returnType", "Lorg/jetbrains/kotlin/ir/types/IrType;", "fillFunction", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "owner", "copyAsStub", "Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", "Lorg/jetbrains/kotlin/ir/declarations/IrTypeParameter;", "Companion", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class LenientModeMissingActualDeclarationProvider extends IrMissingActualDeclarationProvider {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Fir2IrBuiltinSymbolsContainer builtins;
    private final Fir2IrClassifierStorage classifierStorage;

    /* JADX INFO: renamed from: kClassSymbol$delegate, reason: from kotlin metadata */
    private final Lazy kClassSymbol;

    /* JADX INFO: renamed from: notImplementedErrorConstructorSymbol$delegate, reason: from kotlin metadata */
    private final Lazy notImplementedErrorConstructorSymbol;
    private final FirSymbolProvider symbolProvider;

    /* JADX INFO: renamed from: throwsConstructorSymbol$delegate, reason: from kotlin metadata */
    private final Lazy throwsConstructorSymbol;

    public LenientModeMissingActualDeclarationProvider(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer, Fir2IrClassifierStorage fir2IrClassifierStorage, FirSymbolProvider firSymbolProvider) {
        fir2IrBuiltinSymbolsContainer.getClass();
        fir2IrClassifierStorage.getClass();
        firSymbolProvider.getClass();
        this.builtins = fir2IrBuiltinSymbolsContainer;
        this.classifierStorage = fir2IrClassifierStorage;
        this.symbolProvider = firSymbolProvider;
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.NONE;
        this.notImplementedErrorConstructorSymbol = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: qy8
            public final Object invoke() {
                return LenientModeMissingActualDeclarationProvider.a(this.b);
            }
        });
        this.throwsConstructorSymbol = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: ry8
            public final Object invoke() {
                return LenientModeMissingActualDeclarationProvider.c(this.b);
            }
        });
        this.kClassSymbol = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: sy8
            public final Object invoke() {
                return LenientModeMissingActualDeclarationProvider.b(this.b);
            }
        });
    }

    public static IrConstructorSymbol a(LenientModeMissingActualDeclarationProvider lenientModeMissingActualDeclarationProvider) {
        FirClassLikeSymbol<?> classLikeSymbolByClassId = lenientModeMissingActualDeclarationProvider.symbolProvider.getClassLikeSymbolByClassId(ClassId.Companion.fromString$default(ClassId.Companion, "kotlin/NotImplementedError", false, 2, (Object) null));
        classLikeSymbolByClassId.getClass();
        return (IrConstructorSymbol) SequencesKt.first(IrUtilsKt.getConstructors(lenientModeMissingActualDeclarationProvider.classifierStorage.getIrClassSymbol((FirClassSymbol<?>) classLikeSymbolByClassId)));
    }

    private final void addThrowsAnnotation(IrSimpleFunction function) {
        IrConstructorSymbol throwsConstructorSymbol = getThrowsConstructorSymbol();
        if (throwsConstructorSymbol != null) {
            List annotations = function.getAnnotations();
            IrAnnotationImpl irAnnotationImplIrAnnotationImpl$default = BuildersKt.IrAnnotationImpl$default(-1, -1, throwsConstructorSymbol.getOwner().getReturnType(), throwsConstructorSymbol, 0, 0, (IrStatementOrigin) null, (SourceElement) null, 192, (Object) null);
            IrValueParameter irValueParameter = (IrValueParameter) CollectionsKt.first(throwsConstructorSymbol.getOwner().getParameters());
            IrMemberAccessExpression.ValueArgumentsList arguments = irAnnotationImplIrAnnotationImpl$default.getArguments();
            IrType type = irValueParameter.getType();
            IrSimpleType type2 = irValueParameter.getType();
            type2.getClass();
            IrType typeOrFail = IrTypesKt.getTypeOrFail((IrTypeArgument) CollectionsKt.first(type2.getArguments()));
            IrSimpleType irSimpleTypeTypeWith = IrTypesKt.typeWith(getKClassSymbol(), new IrType[]{getNotImplementedErrorConstructorSymbol().getOwner().getReturnType()});
            IrClass parent = getNotImplementedErrorConstructorSymbol().getOwner().getParent();
            parent.getClass();
            arguments.set(0, BuildersKt.IrVarargImpl(-1, -1, type, typeOrFail, CollectionsKt.listOf(BuildersKt.IrClassReferenceImpl(-1, -1, irSimpleTypeTypeWith, parent.getSymbol(), getNotImplementedErrorConstructorSymbol().getOwner().getReturnType()))));
            Unit unit = Unit.INSTANCE;
            function.setAnnotations(CollectionsKt.plus(annotations, irAnnotationImplIrAnnotationImpl$default));
        }
    }

    public static IrClassSymbol b(LenientModeMissingActualDeclarationProvider lenientModeMissingActualDeclarationProvider) {
        FirClassLikeSymbol<?> classLikeSymbolByClassId = lenientModeMissingActualDeclarationProvider.symbolProvider.getClassLikeSymbolByClassId(ClassId.Companion.fromString$default(ClassId.Companion, "kotlin.reflect/KClass", false, 2, (Object) null));
        classLikeSymbolByClassId.getClass();
        return lenientModeMissingActualDeclarationProvider.classifierStorage.getIrClassSymbol((FirClassSymbol<?>) classLikeSymbolByClassId);
    }

    private final IrClassSymbol buildClassStub(IrClassSymbol expectSymbol, IrDeclarationParent parent) {
        IrClass owner = expectSymbol.getOwner();
        if ((owner.getKind() != ClassKind.CLASS && owner.getKind() != ClassKind.INTERFACE && owner.getKind() != ClassKind.OBJECT) || owner.isValue()) {
            return null;
        }
        IrClass superClass = IrUtilsKt.getSuperClass(owner);
        if (superClass != null && !Intrinsics.areEqual(superClass, this.builtins.getAnyClass())) {
            return null;
        }
        IrClassSymbolImpl irClassSymbolImpl = new IrClassSymbolImpl((ClassDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null);
        IrClass irClassCreateClass$default = IrFactory.createClass$default(IrFactoryImpl.INSTANCE, -1, -1, IrDeclarationOrigin.Companion.getSTUB_FOR_LENIENT(), owner.getName(), owner.getVisibility(), irClassSymbolImpl, owner.getKind(), owner.getModality(), false, owner.isCompanion(), owner.isInner(), owner.isData(), owner.isValue(), false, owner.isFun(), false, (SourceElement) null, 98560, (Object) null);
        irClassCreateClass$default.setSuperTypes(owner.getSuperTypes());
        IrValueParameter thisReceiver = owner.getThisReceiver();
        irClassCreateClass$default.setThisReceiver(thisReceiver != null ? copyAsStub(thisReceiver, (IrDeclarationParent) irClassCreateClass$default) : null);
        List<IrAnnotation> annotations = owner.getAnnotations();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(annotations, 10));
        for (IrAnnotation irAnnotation : annotations) {
            DeepCopySymbolRemapper deepCopySymbolRemapper = new DeepCopySymbolRemapper((DescriptorsRemapper) null, 1, (DefaultConstructorMarker) null);
            IrVisitorsKt.acceptVoid(irAnnotation, deepCopySymbolRemapper);
            IrAnnotation irAnnotationTransform = irAnnotation.transform(new DeepCopyIrTreeWithSymbols(deepCopySymbolRemapper, new DeepCopyTypeRemapper(deepCopySymbolRemapper)), (Object) null);
            if (irAnnotationTransform == null) {
                x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.ir.expressions.IrAnnotation");
                return null;
            }
            arrayList.add(irAnnotationTransform);
        }
        irClassCreateClass$default.setAnnotations(arrayList);
        irClassCreateClass$default.setParent(parent);
        List declarations = irClassCreateClass$default.getDeclarations();
        List declarations2 = owner.getDeclarations();
        ArrayList arrayList2 = new ArrayList();
        Iterator it = declarations2.iterator();
        while (it.hasNext()) {
            IrSymbol irSymbolBuildStub = buildStub(((IrDeclaration) it.next()).getSymbol(), irClassCreateClass$default);
            IrDeclaration irDeclaration = (IrDeclaration) (irSymbolBuildStub != null ? irSymbolBuildStub.getOwner() : null);
            if (irDeclaration != null) {
                arrayList2.add(irDeclaration);
            }
        }
        declarations.addAll(arrayList2);
        if (irClassCreateClass$default.getKind() == ClassKind.OBJECT) {
            irClassCreateClass$default.getDeclarations().add(createObjectConstructor(irClassCreateClass$default));
        }
        return irClassSymbolImpl;
    }

    private final IrConstructorSymbol buildConstructorStub(IrConstructorSymbol expectSymbol, IrDeclarationParent parent) {
        IrConstructorSymbolImpl irConstructorSymbolImpl = new IrConstructorSymbolImpl((ClassConstructorDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null);
        IrConstructor owner = expectSymbol.getOwner();
        IrConstructor irConstructorCreateConstructor$default = IrFactory.createConstructor$default(IrFactoryImpl.INSTANCE, -1, -1, IrDeclarationOrigin.Companion.getSTUB_FOR_LENIENT(), owner.getName(), owner.getVisibility(), owner.isInline(), false, owner.getReturnType(), irConstructorSymbolImpl, false, false, (DeserializedContainerSource) null, 3072, (Object) null);
        fillFunction(irConstructorCreateConstructor$default, owner, parent);
        irConstructorCreateConstructor$default.setBody(createConstructorBody(parent));
        return irConstructorSymbolImpl;
    }

    private final IrSimpleFunctionSymbol buildFunctionStub(IrSimpleFunctionSymbol expectSymbol, IrDeclarationParent parent) {
        IrSimpleFunctionSymbolImpl irSimpleFunctionSymbolImpl = new IrSimpleFunctionSymbolImpl((FunctionDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null);
        IrSimpleFunction owner = expectSymbol.getOwner();
        IrFactoryImpl irFactoryImpl = IrFactoryImpl.INSTANCE;
        IrSimpleFunction irSimpleFunctionCreateSimpleFunction$default = IrFactory.createSimpleFunction$default(irFactoryImpl, -1, -1, IrDeclarationOrigin.Companion.getSTUB_FOR_LENIENT(), owner.getName(), owner.getVisibility(), owner.isInline(), false, owner.getReturnType(), owner.getModality(), irSimpleFunctionSymbolImpl, owner.isTailrec(), owner.isSuspend(), owner.isOperator(), owner.isInfix(), false, (DeserializedContainerSource) null, false, 114688, (Object) null);
        fillFunction(irSimpleFunctionCreateSimpleFunction$default, owner, parent);
        IrBlockBody irBlockBodyCreateBlockBody = irFactoryImpl.createBlockBody(-1, -1);
        IrType returnType = owner.getReturnType();
        if (!Intrinsics.areEqual(returnType, this.builtins.getUnitType())) {
            if (IrTypeUtilsKt.isNullable(returnType) || IrTypePredicatesKt.isPrimitiveType$default(returnType, false, 1, (Object) null) || IrTypePredicatesKt.isString(returnType)) {
                irBlockBodyCreateBlockBody.getStatements().add(new IrReturnImpl(-1, -1, this.builtins.getNothingType(), irSimpleFunctionSymbolImpl, generateDefaultReturnValue(returnType)));
            } else {
                irBlockBodyCreateBlockBody.getStatements().add(BuildersKt.IrThrowImpl(-1, -1, this.builtins.getNothingType(), BuildersKt.IrConstructorCallImpl$default(-1, -1, getNotImplementedErrorConstructorSymbol().getOwner().getReturnType(), getNotImplementedErrorConstructorSymbol(), 0, 0, (IrStatementOrigin) null, (SourceElement) null, 192, (Object) null)));
                addThrowsAnnotation(irSimpleFunctionCreateSimpleFunction$default);
            }
        }
        irSimpleFunctionCreateSimpleFunction$default.setBody(irBlockBodyCreateBlockBody);
        return irSimpleFunctionSymbolImpl;
    }

    private final IrPropertySymbol buildPropertyStub(IrPropertySymbol expectSymbol, IrDeclarationParent parent) {
        IrSimpleFunction irSimpleFunction;
        IrSimpleFunction irSimpleFunction2;
        IrPropertySymbolImpl irPropertySymbolImpl = new IrPropertySymbolImpl((PropertyDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null);
        IrProperty owner = expectSymbol.getOwner();
        IrProperty irPropertyCreateProperty$default = IrFactory.createProperty$default(IrFactoryImpl.INSTANCE, -1, -1, IrDeclarationOrigin.Companion.getSTUB_FOR_LENIENT(), owner.getName(), owner.getVisibility(), owner.getModality(), irPropertySymbolImpl, owner.isVar(), false, false, false, false, (DeserializedContainerSource) null, false, false, 22528, (Object) null);
        IrSimpleFunction getter = owner.getGetter();
        if (getter != null) {
            irSimpleFunction = (IrSimpleFunction) buildFunctionStub(getter.getSymbol(), parent).getOwner();
            irSimpleFunction.setCorrespondingPropertySymbol(irPropertySymbolImpl);
        } else {
            irSimpleFunction = null;
        }
        irPropertyCreateProperty$default.setGetter(irSimpleFunction);
        IrSimpleFunction setter = owner.getSetter();
        if (setter != null) {
            irSimpleFunction2 = (IrSimpleFunction) buildFunctionStub(setter.getSymbol(), parent).getOwner();
            irSimpleFunction2.setCorrespondingPropertySymbol(irPropertySymbolImpl);
        } else {
            irSimpleFunction2 = null;
        }
        irPropertyCreateProperty$default.setSetter(irSimpleFunction2);
        List<IrAnnotation> annotations = owner.getAnnotations();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(annotations, 10));
        for (IrAnnotation irAnnotation : annotations) {
            DeepCopySymbolRemapper deepCopySymbolRemapper = new DeepCopySymbolRemapper((DescriptorsRemapper) null, 1, (DefaultConstructorMarker) null);
            IrVisitorsKt.acceptVoid(irAnnotation, deepCopySymbolRemapper);
            IrAnnotation irAnnotationTransform = irAnnotation.transform(new DeepCopyIrTreeWithSymbols(deepCopySymbolRemapper, new DeepCopyTypeRemapper(deepCopySymbolRemapper)), (Object) null);
            if (irAnnotationTransform == null) {
                x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.ir.expressions.IrAnnotation");
                return null;
            }
            arrayList.add(irAnnotationTransform);
        }
        irPropertyCreateProperty$default.setAnnotations(arrayList);
        irPropertyCreateProperty$default.setParent(parent);
        return irPropertySymbolImpl;
    }

    private final IrSymbol buildStub(IrSymbol expectSymbol, IrDeclarationParent parent) {
        if (expectSymbol instanceof IrClassSymbol) {
            return buildClassStub((IrClassSymbol) expectSymbol, parent);
        }
        if (expectSymbol instanceof IrPropertySymbol) {
            return buildPropertyStub((IrPropertySymbol) expectSymbol, parent);
        }
        if (expectSymbol instanceof IrSimpleFunctionSymbol) {
            return buildFunctionStub((IrSimpleFunctionSymbol) expectSymbol, parent);
        }
        if (expectSymbol instanceof IrConstructorSymbol) {
            return buildConstructorStub((IrConstructorSymbol) expectSymbol, parent);
        }
        return null;
    }

    public static IrConstructorSymbol c(LenientModeMissingActualDeclarationProvider lenientModeMissingActualDeclarationProvider) {
        FirClassSymbol<?> firClassSymbol = (FirClassSymbol) lenientModeMissingActualDeclarationProvider.symbolProvider.getClassLikeSymbolByClassId(ClassId.Companion.fromString$default(ClassId.Companion, "kotlin.jvm/Throws", false, 2, (Object) null));
        if (firClassSymbol == null) {
            return null;
        }
        return (IrConstructorSymbol) SequencesKt.first(IrUtilsKt.getConstructors(lenientModeMissingActualDeclarationProvider.classifierStorage.getIrClassSymbol(firClassSymbol)));
    }

    private final IrValueParameter copyAsStub(IrValueParameter irValueParameter, IrDeclarationParent irDeclarationParent) {
        IrValueParameter irValueParameterCreateValueParameter = IrFactoryImpl.INSTANCE.createValueParameter(-1, -1, IrDeclarationOrigin.Companion.getSTUB_FOR_LENIENT(), irValueParameter.getKind(), irValueParameter.getName(), irValueParameter.getType(), irValueParameter.isAssignable(), new IrValueParameterSymbolImpl((ParameterDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null), irValueParameter.getVarargElementType(), irValueParameter.isCrossinline(), irValueParameter.isNoinline(), irValueParameter.isHidden());
        irValueParameterCreateValueParameter.setParent(irDeclarationParent);
        List<IrAnnotation> annotations = irValueParameter.getAnnotations();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(annotations, 10));
        for (IrAnnotation irAnnotation : annotations) {
            DeepCopySymbolRemapper deepCopySymbolRemapper = new DeepCopySymbolRemapper((DescriptorsRemapper) null, 1, (DefaultConstructorMarker) null);
            IrVisitorsKt.acceptVoid(irAnnotation, deepCopySymbolRemapper);
            IrAnnotation irAnnotationTransform = irAnnotation.transform(new DeepCopyIrTreeWithSymbols(deepCopySymbolRemapper, new DeepCopyTypeRemapper(deepCopySymbolRemapper)), (Object) null);
            if (irAnnotationTransform == null) {
                x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.ir.expressions.IrAnnotation");
                return null;
            }
            arrayList.add(irAnnotationTransform);
        }
        irValueParameterCreateValueParameter.setAnnotations(arrayList);
        return irValueParameterCreateValueParameter;
    }

    private final IrBlockBody createConstructorBody(IrDeclarationParent parent) {
        IrBlockBody irBlockBodyCreateBlockBody = IrFactoryImpl.INSTANCE.createBlockBody(-1, -1);
        irBlockBodyCreateBlockBody.getStatements().add(BuildersKt.IrDelegatingConstructorCallImplWithShape$default(-1, -1, this.builtins.getUnitType(), (IrConstructorSymbol) SequencesKt.first(IrUtilsKt.getConstructors(this.builtins.getAnyClass())), 0, 0, 0, false, false, (IrStatementOrigin) null, 512, (Object) null));
        List statements = irBlockBodyCreateBlockBody.getStatements();
        parent.getClass();
        statements.add(BuildersKt.IrInstanceInitializerCallImpl(-1, -1, ((IrClass) parent).getSymbol(), this.builtins.getUnitType()));
        return irBlockBodyCreateBlockBody;
    }

    private final IrDeclaration createObjectConstructor(IrClass clazz) {
        IrFactoryImpl irFactoryImpl = IrFactoryImpl.INSTANCE;
        IrDeclarationOrigin stub_for_lenient = IrDeclarationOrigin.Companion.getSTUB_FOR_LENIENT();
        IrConstructorSymbolImpl irConstructorSymbolImpl = new IrConstructorSymbolImpl((ClassConstructorDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null);
        Name name = SpecialNames.INIT;
        DescriptorVisibility descriptorVisibility = DescriptorVisibilities.PRIVATE;
        descriptorVisibility.getClass();
        IrConstructor irConstructorCreateConstructor$default = IrFactory.createConstructor$default(irFactoryImpl, -1, -1, stub_for_lenient, name, descriptorVisibility, false, false, IrUtilsKt.getDefaultType(clazz), irConstructorSymbolImpl, true, false, (DeserializedContainerSource) null, 3072, (Object) null);
        irConstructorCreateConstructor$default.setParent(clazz);
        irConstructorCreateConstructor$default.setBody(createConstructorBody(clazz));
        return irConstructorCreateConstructor$default;
    }

    private final void fillFunction(IrFunction function, IrFunction owner, IrDeclarationParent parent) {
        List parameters = owner.getParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(parameters, 10));
        Iterator it = parameters.iterator();
        while (it.hasNext()) {
            arrayList.add(copyAsStub((IrValueParameter) it.next(), (IrDeclarationParent) function));
        }
        function.setParameters(arrayList);
        List typeParameters = owner.getTypeParameters();
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeParameters, 10));
        Iterator it2 = typeParameters.iterator();
        while (it2.hasNext()) {
            arrayList2.add(copyAsStub((IrTypeParameter) it2.next(), (IrDeclarationParent) function));
        }
        function.setTypeParameters(arrayList2);
        List<IrAnnotation> annotations = owner.getAnnotations();
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(annotations, 10));
        for (IrAnnotation irAnnotation : annotations) {
            DeepCopySymbolRemapper deepCopySymbolRemapper = new DeepCopySymbolRemapper((DescriptorsRemapper) null, 1, (DefaultConstructorMarker) null);
            IrVisitorsKt.acceptVoid(irAnnotation, deepCopySymbolRemapper);
            IrAnnotation irAnnotationTransform = irAnnotation.transform(new DeepCopyIrTreeWithSymbols(deepCopySymbolRemapper, new DeepCopyTypeRemapper(deepCopySymbolRemapper)), (Object) null);
            if (irAnnotationTransform == null) {
                x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.ir.expressions.IrAnnotation");
                return;
            }
            arrayList3.add(irAnnotationTransform);
        }
        function.setAnnotations(arrayList3);
        function.setParent(parent);
    }

    private final IrConstImpl generateDefaultReturnValue(IrType returnType) {
        Object objValueOf;
        IrConstKind.String string;
        if (Intrinsics.areEqual(returnType, this.builtins.getStringType())) {
            string = IrConstKind.String.INSTANCE;
            objValueOf = Argument.Delimiters.none;
        } else if (Intrinsics.areEqual(returnType, this.builtins.getBooleanType())) {
            string = IrConstKind.Boolean.INSTANCE;
            objValueOf = Boolean.FALSE;
        } else if (Intrinsics.areEqual(returnType, this.builtins.getByteType())) {
            string = IrConstKind.Byte.INSTANCE;
            objValueOf = (byte) 0;
        } else if (Intrinsics.areEqual(returnType, this.builtins.getShortType())) {
            string = IrConstKind.Short.INSTANCE;
            objValueOf = (short) 0;
        } else if (Intrinsics.areEqual(returnType, this.builtins.getIntType())) {
            string = IrConstKind.Int.INSTANCE;
            objValueOf = 0;
        } else if (Intrinsics.areEqual(returnType, this.builtins.getLongType())) {
            string = IrConstKind.Long.INSTANCE;
            objValueOf = 0L;
        } else if (Intrinsics.areEqual(returnType, this.builtins.getCharType())) {
            string = IrConstKind.Char.INSTANCE;
            objValueOf = (char) 0;
        } else if (Intrinsics.areEqual(returnType, this.builtins.getFloatType())) {
            string = IrConstKind.Float.INSTANCE;
            objValueOf = Float.valueOf(0.0f);
        } else if (Intrinsics.areEqual(returnType, this.builtins.getDoubleType())) {
            string = IrConstKind.Double.INSTANCE;
            objValueOf = Double.valueOf(0.0d);
        } else {
            objValueOf = null;
            if (!IrTypeUtilsKt.isNullable(returnType)) {
                dt1.a("Cannot generate default return value for ", RenderIrElementKt.render$default(returnType, (DumpIrTreeOptions) null, 1, (Object) null));
                return null;
            }
            string = IrConstKind.Null.INSTANCE;
        }
        return BuildersKt.IrConstImpl(-1, -1, IrTypesKt.removeAnnotations(returnType), string, objValueOf);
    }

    private final IrClassSymbol getKClassSymbol() {
        return (IrClassSymbol) this.kClassSymbol.getValue();
    }

    private final IrConstructorSymbol getNotImplementedErrorConstructorSymbol() {
        return (IrConstructorSymbol) this.notImplementedErrorConstructorSymbol.getValue();
    }

    private final IrConstructorSymbol getThrowsConstructorSymbol() {
        return (IrConstructorSymbol) this.throwsConstructorSymbol.getValue();
    }

    public IrSymbol provideSymbolForMissingActual(IrSymbol expectSymbol, IrClassSymbol containingExpectClassSymbol, IrClassSymbol containingActualClassSymbol) {
        expectSymbol.getClass();
        if (containingExpectClassSymbol != null || containingActualClassSymbol != null) {
            return null;
        }
        IrDeclaration owner = expectSymbol.getOwner();
        IrDeclaration irDeclaration = owner instanceof IrDeclaration ? owner : null;
        if (irDeclaration == null) {
            return null;
        }
        return buildStub(expectSymbol, irDeclaration.getParent());
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/LenientModeMissingActualDeclarationProvider$Companion;", Argument.Delimiters.none, "<init>", "()V", "initializeIfNeeded", "Lorg/jetbrains/kotlin/fir/backend/LenientModeMissingActualDeclarationProvider;", "c", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final LenientModeMissingActualDeclarationProvider initializeIfNeeded(Fir2IrComponents c) {
            c.getClass();
            if (((Boolean) FirLanguageSettingsComponentKt.getLanguageVersionSettings(c.getSession()).getFlag(AnalysisFlags.INSTANCE.getLenientMode())).booleanValue()) {
                return new LenientModeMissingActualDeclarationProvider(c.getBuiltins(), c.getClassifierStorage(), FirSymbolProviderKt.getSymbolProvider(c.getSession()));
            }
            return null;
        }

        private Companion() {
        }
    }

    private final IrTypeParameter copyAsStub(IrTypeParameter irTypeParameter, IrDeclarationParent irDeclarationParent) {
        IrTypeParameter irTypeParameterCreateTypeParameter = IrFactoryImpl.INSTANCE.createTypeParameter(-1, -1, IrDeclarationOrigin.Companion.getSTUB_FOR_LENIENT(), irTypeParameter.getName(), new IrTypeParameterSymbolImpl((TypeParameterDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null), irTypeParameter.getVariance(), irTypeParameter.getIndex(), irTypeParameter.isReified());
        irTypeParameterCreateTypeParameter.setParent(irDeclarationParent);
        List<IrAnnotation> annotations = irTypeParameter.getAnnotations();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(annotations, 10));
        for (IrAnnotation irAnnotation : annotations) {
            DeepCopySymbolRemapper deepCopySymbolRemapper = new DeepCopySymbolRemapper((DescriptorsRemapper) null, 1, (DefaultConstructorMarker) null);
            IrVisitorsKt.acceptVoid(irAnnotation, deepCopySymbolRemapper);
            IrAnnotation irAnnotationTransform = irAnnotation.transform(new DeepCopyIrTreeWithSymbols(deepCopySymbolRemapper, new DeepCopyTypeRemapper(deepCopySymbolRemapper)), (Object) null);
            if (irAnnotationTransform != null) {
                arrayList.add(irAnnotationTransform);
            } else {
                x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.ir.expressions.IrAnnotation");
                return null;
            }
        }
        irTypeParameterCreateTypeParameter.setAnnotations(arrayList);
        return irTypeParameterCreateTypeParameter;
    }
}
