package org.jetbrains.kotlin.fir.lazy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.state.InlineClassManglingUtilsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.backend.Fir2IrComponents;
import org.jetbrains.kotlin.fir.backend.Fir2IrDeclarationStorage;
import org.jetbrains.kotlin.fir.backend.Fir2IrSymbolsMappingForLazyClassesKt;
import org.jetbrains.kotlin.fir.backend.Fir2IrTypeConverterKt;
import org.jetbrains.kotlin.fir.backend.utils.ConversionTypeOrigin;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.lazy.Fir2IrLazySimpleFunction;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationParent;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.declarations.lazy.LazyUtilKt;
import org.jetbrains.kotlin.ir.expressions.IrAnnotation;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BQ\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012¢\u0006\u0004\b\u0013\u0010\u0014R\u0014\u0010\n\u001a\u00020\u0002X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u0004¢\u0006\u0002\n\u0000R7\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00188V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b\u001f\u0010 \u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR$\u0010#\u001a\u00020\"2\u0006\u0010!\u001a\u00020\"8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R+\u0010)\u001a\u00020(2\u0006\u0010\u0017\u001a\u00020(8V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b.\u0010 \u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R7\u0010/\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00182\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00188V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b2\u0010 \u001a\u0004\b0\u0010\u001c\"\u0004\b1\u0010\u001eR\u001d\u00103\u001a\u0004\u0018\u0001048VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b7\u00108\u001a\u0004\b5\u00106R\u0016\u00109\u001a\u0004\u0018\u00010:8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b;\u0010<¨\u0006="}, d2 = {"Lorg/jetbrains/kotlin/fir/lazy/Fir2IrLazySimpleFunction;", "Lorg/jetbrains/kotlin/fir/lazy/AbstractFir2IrLazyFunction;", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "c", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "startOffset", Argument.Delimiters.none, "endOffset", "origin", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "fir", "firParent", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "symbol", "Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "parent", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationParent;", "isFakeOverride", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;IILorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationParent;Z)V", "getFir", "()Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "<set-?>", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/expressions/IrAnnotation;", "annotations", "getAnnotations", "()Ljava/util/List;", "setAnnotations", "(Ljava/util/List;)V", "annotations$delegate", "Lkotlin/properties/ReadWriteProperty;", InlineClassManglingUtilsKt.NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER, "Lorg/jetbrains/kotlin/name/Name;", ModuleXmlParser.NAME, "getName", "()Lorg/jetbrains/kotlin/name/Name;", "setName", "(Lorg/jetbrains/kotlin/name/Name;)V", "Lorg/jetbrains/kotlin/ir/types/IrType;", "returnType", "getReturnType", "()Lorg/jetbrains/kotlin/ir/types/IrType;", "setReturnType", "(Lorg/jetbrains/kotlin/ir/types/IrType;)V", "returnType$delegate", "overriddenSymbols", "getOverriddenSymbols", "setOverriddenSymbols", "overriddenSymbols$delegate", "initialSignatureFunction", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "getInitialSignatureFunction", "()Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "initialSignatureFunction$delegate", "Lkotlin/Lazy;", "containerSource", "Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "getContainerSource", "()Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrLazySimpleFunction extends AbstractFir2IrLazyFunction<FirNamedFunction> {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl<>(Fir2IrLazySimpleFunction.class, "annotations", "getAnnotations()Ljava/util/List;", 0), new MutablePropertyReference1Impl<>(Fir2IrLazySimpleFunction.class, "returnType", "getReturnType()Lorg/jetbrains/kotlin/ir/types/IrType;", 0), new MutablePropertyReference1Impl<>(Fir2IrLazySimpleFunction.class, "overriddenSymbols", "getOverriddenSymbols()Ljava/util/List;", 0)};

    /* JADX INFO: renamed from: annotations$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty annotations;
    private final FirNamedFunction fir;
    private final FirRegularClass firParent;

    /* JADX INFO: renamed from: initialSignatureFunction$delegate, reason: from kotlin metadata */
    private final Lazy initialSignatureFunction;

    /* JADX INFO: renamed from: overriddenSymbols$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty overriddenSymbols;

    /* JADX INFO: renamed from: returnType$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty returnType;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Fir2IrLazySimpleFunction(Fir2IrComponents fir2IrComponents, int i, int i2, IrDeclarationOrigin irDeclarationOrigin, FirNamedFunction firNamedFunction, FirRegularClass firRegularClass, IrSimpleFunctionSymbol irSimpleFunctionSymbol, final IrDeclarationParent irDeclarationParent, boolean z) {
        super(fir2IrComponents, i, i2, irDeclarationOrigin, irSimpleFunctionSymbol, irDeclarationParent, z);
        fir2IrComponents.getClass();
        irDeclarationOrigin.getClass();
        firNamedFunction.getClass();
        irSimpleFunctionSymbol.getClass();
        irDeclarationParent.getClass();
        this.fir = firNamedFunction;
        this.firParent = firRegularClass;
        irSimpleFunctionSymbol.bind(this);
        getClassifierStorage().preCacheTypeParameters$org_jetbrains_kotlin_fir2ir(getFir());
        this.annotations = createLazyAnnotations();
        this.returnType = LazyUtilKt.lazyVar(getLock(), new Function0() { // from class: kw4
            public final Object invoke() {
                return Fir2IrLazySimpleFunction.f(this.b);
            }
        });
        this.overriddenSymbols = Fir2IrSymbolsMappingForLazyClassesKt.lazyMappedFunctionListVar(getSymbolsMappingForLazyClasses(), getLock(), new Function0() { // from class: lw4
            public final Object invoke() {
                return Fir2IrLazySimpleFunction.g(this.b, irDeclarationParent);
            }
        });
        this.initialSignatureFunction = LazyKt.lazy(new Function0() { // from class: mw4
            public final Object invoke() {
                return Fir2IrLazySimpleFunction.h(this.b);
            }
        });
    }

    public static IrType f(Fir2IrLazySimpleFunction fir2IrLazySimpleFunction) {
        return Fir2IrTypeConverterKt.toIrType$default((Fir2IrComponents) fir2IrLazySimpleFunction, (FirTypeRef) fir2IrLazySimpleFunction.getFir().getSymbol().getResolvedReturnTypeRef(), (ConversionTypeOrigin) null, 2, (Object) null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static List g(Fir2IrLazySimpleFunction fir2IrLazySimpleFunction, IrDeclarationParent irDeclarationParent) throws KotlinIllegalArgumentExceptionWithAttachments {
        if (fir2IrLazySimpleFunction.firParent == null || !(irDeclarationParent instanceof Fir2IrLazyClass)) {
            return CollectionsKt.emptyList();
        }
        List<Pair<FirNamedFunctionSymbol, ConeClassLikeLookupTag>> listComputeFakeOverrideKeys$org_jetbrains_kotlin_fir2ir = fir2IrLazySimpleFunction.getLazyFakeOverrideGenerator().computeFakeOverrideKeys$org_jetbrains_kotlin_fir2ir(fir2IrLazySimpleFunction.firParent, fir2IrLazySimpleFunction.getFir().getSymbol());
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listComputeFakeOverrideKeys$org_jetbrains_kotlin_fir2ir, 10));
        Iterator<T> it = listComputeFakeOverrideKeys$org_jetbrains_kotlin_fir2ir.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            IrSimpleFunctionSymbol irFunctionSymbol$default = Fir2IrDeclarationStorage.getIrFunctionSymbol$default(fir2IrLazySimpleFunction.getDeclarationStorage(), (FirNamedFunctionSymbol) pair.component1(), (ConeClassLikeLookupTag) pair.component2(), false, 4, null);
            irFunctionSymbol$default.getClass();
            arrayList.add(irFunctionSymbol$default);
        }
        return arrayList;
    }

    public static IrFunction h(Fir2IrLazySimpleFunction fir2IrLazySimpleFunction) {
        FirRegularClassSymbol symbol;
        FirNamedFunctionSymbol initialSignatureAttr = ClassMembersKt.getInitialSignatureAttr(fir2IrLazySimpleFunction.getFir());
        if (initialSignatureAttr == null) {
            return null;
        }
        FirRegularClass firRegularClass = fir2IrLazySimpleFunction.firParent;
        IrFunction owner = Fir2IrDeclarationStorage.getIrFunctionSymbol$default(fir2IrLazySimpleFunction.getDeclarationStorage(), initialSignatureAttr, (firRegularClass == null || (symbol = firRegularClass.getSymbol()) == null) ? null : symbol.getLookupTag(), false, 4, null).getOwner();
        if (owner != fir2IrLazySimpleFunction) {
            return owner;
        }
        k2d.a("Initial function can not be the same as remapped function");
        return null;
    }

    public List<IrAnnotation> getAnnotations() {
        return (List) this.annotations.getValue(this, $$delegatedProperties[0]);
    }

    public DeserializedContainerSource getContainerSource() {
        return getFir().getContainerSource();
    }

    public IrFunction getInitialSignatureFunction() {
        return (IrFunction) this.initialSignatureFunction.getValue();
    }

    public Name getName() {
        return getFir().getName();
    }

    public List<IrSimpleFunctionSymbol> getOverriddenSymbols() {
        return (List) this.overriddenSymbols.getValue(this, $$delegatedProperties[2]);
    }

    public IrType getReturnType() {
        return (IrType) this.returnType.getValue(this, $$delegatedProperties[1]);
    }

    public void setAnnotations(List<? extends IrAnnotation> list) {
        list.getClass();
        this.annotations.setValue(this, $$delegatedProperties[0], list);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public void setName(Name name) throws KotlinNothingValueException {
        name.getClass();
        AbstractFir2IrLazyDeclarationKt.mutationNotSupported();
        throw new KotlinNothingValueException();
    }

    public void setOverriddenSymbols(List<? extends IrSimpleFunctionSymbol> list) {
        list.getClass();
        this.overriddenSymbols.setValue(this, $$delegatedProperties[2], list);
    }

    public void setReturnType(IrType irType) {
        irType.getClass();
        this.returnType.setValue(this, $$delegatedProperties[1], irType);
    }

    @Override // org.jetbrains.kotlin.fir.lazy.AbstractFir2IrLazyDeclaration
    public FirNamedFunction getFir() {
        return this.fir;
    }
}
