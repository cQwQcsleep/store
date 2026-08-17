package org.jetbrains.kotlin.fir.lazy;

import java.util.ArrayList;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.state.InlineClassManglingUtilsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.backend.Fir2IrComponents;
import org.jetbrains.kotlin.fir.backend.Fir2IrDeclarationStorage;
import org.jetbrains.kotlin.fir.backend.Fir2IrSymbolsMappingForLazyClassesKt;
import org.jetbrains.kotlin.fir.backend.Fir2IrTypeConverterKt;
import org.jetbrains.kotlin.fir.backend.utils.ConversionTypeOrigin;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.synthetic.FirSyntheticPropertyAccessor;
import org.jetbrains.kotlin.fir.lazy.Fir2IrLazyPropertyAccessor;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationParent;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.lazy.LazyUtilKt;
import org.jetbrains.kotlin.ir.expressions.IrAnnotation;
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001Bm\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\r\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018¢\u0006\u0004\b\u0019\u0010\u001aR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u001bR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"R$\u0010$\u001a\u00020\r2\u0006\u0010#\u001a\u00020\r8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b$\u0010\u001b\"\u0004\b%\u0010&R7\u0010*\u001a\b\u0012\u0004\u0012\u00020)0(2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020)0(8V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b/\u00100\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R$\u00102\u001a\u0002012\u0006\u0010#\u001a\u0002018V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b3\u00104\"\u0004\b5\u00106R+\u00108\u001a\u0002072\u0006\u0010'\u001a\u0002078V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b=\u00100\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R7\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00130(2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00130(8V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\bA\u00100\u001a\u0004\b?\u0010,\"\u0004\b@\u0010.R\u001d\u0010B\u001a\u0004\u0018\u00010C8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bF\u0010G\u001a\u0004\bD\u0010ER\u0016\u0010H\u001a\u0004\u0018\u00010I8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bJ\u0010KR\u000e\u0010L\u001a\u00020MX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006N"}, d2 = {"Lorg/jetbrains/kotlin/fir/lazy/Fir2IrLazyPropertyAccessor;", "Lorg/jetbrains/kotlin/fir/lazy/AbstractFir2IrLazyFunction;", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "c", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "startOffset", Argument.Delimiters.none, "endOffset", "origin", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "firAccessor", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "isSetter", Argument.Delimiters.none, "firParentProperty", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "firParentClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "symbol", "Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "parent", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationParent;", "isFakeOverride", "correspondingPropertySymbol", "Lorg/jetbrains/kotlin/ir/symbols/IrPropertySymbol;", "<init>", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;IILorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;ZLorg/jetbrains/kotlin/fir/declarations/FirProperty;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationParent;ZLorg/jetbrains/kotlin/ir/symbols/IrPropertySymbol;)V", "()Z", "getCorrespondingPropertySymbol", "()Lorg/jetbrains/kotlin/ir/symbols/IrPropertySymbol;", "setCorrespondingPropertySymbol", "(Lorg/jetbrains/kotlin/ir/symbols/IrPropertySymbol;)V", "fir", "getFir", "()Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", InlineClassManglingUtilsKt.NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER, "isInline", "setInline", "(Z)V", "<set-?>", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/expressions/IrAnnotation;", "annotations", "getAnnotations", "()Ljava/util/List;", "setAnnotations", "(Ljava/util/List;)V", "annotations$delegate", "Lkotlin/properties/ReadWriteProperty;", "Lorg/jetbrains/kotlin/name/Name;", ModuleXmlParser.NAME, "getName", "()Lorg/jetbrains/kotlin/name/Name;", "setName", "(Lorg/jetbrains/kotlin/name/Name;)V", "Lorg/jetbrains/kotlin/ir/types/IrType;", "returnType", "getReturnType", "()Lorg/jetbrains/kotlin/ir/types/IrType;", "setReturnType", "(Lorg/jetbrains/kotlin/ir/types/IrType;)V", "returnType$delegate", "overriddenSymbols", "getOverriddenSymbols", "setOverriddenSymbols", "overriddenSymbols$delegate", "initialSignatureFunction", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "getInitialSignatureFunction", "()Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "initialSignatureFunction$delegate", "Lkotlin/Lazy;", "containerSource", "Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "getContainerSource", "()Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "conversionTypeContext", "Lorg/jetbrains/kotlin/fir/backend/utils/ConversionTypeOrigin;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrLazyPropertyAccessor extends AbstractFir2IrLazyFunction<FirCallableDeclaration> {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl<>(Fir2IrLazyPropertyAccessor.class, "annotations", "getAnnotations()Ljava/util/List;", 0), new MutablePropertyReference1Impl<>(Fir2IrLazyPropertyAccessor.class, "returnType", "getReturnType()Lorg/jetbrains/kotlin/ir/types/IrType;", 0), new MutablePropertyReference1Impl<>(Fir2IrLazyPropertyAccessor.class, "overriddenSymbols", "getOverriddenSymbols()Ljava/util/List;", 0)};

    /* JADX INFO: renamed from: annotations$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty annotations;
    private final ConversionTypeOrigin conversionTypeContext;
    private IrPropertySymbol correspondingPropertySymbol;
    private final FirPropertyAccessor firAccessor;
    private final FirProperty firParentProperty;

    /* JADX INFO: renamed from: initialSignatureFunction$delegate, reason: from kotlin metadata */
    private final Lazy initialSignatureFunction;
    private final boolean isSetter;

    /* JADX INFO: renamed from: overriddenSymbols$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty overriddenSymbols;

    /* JADX INFO: renamed from: returnType$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty returnType;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Fir2IrLazyPropertyAccessor(Fir2IrComponents fir2IrComponents, int i, int i2, IrDeclarationOrigin irDeclarationOrigin, FirPropertyAccessor firPropertyAccessor, boolean z, FirProperty firProperty, final FirRegularClass firRegularClass, IrSimpleFunctionSymbol irSimpleFunctionSymbol, IrDeclarationParent irDeclarationParent, boolean z2, IrPropertySymbol irPropertySymbol) {
        super(fir2IrComponents, i, i2, irDeclarationOrigin, irSimpleFunctionSymbol, irDeclarationParent, z2);
        fir2IrComponents.getClass();
        irDeclarationOrigin.getClass();
        firProperty.getClass();
        irSimpleFunctionSymbol.getClass();
        irDeclarationParent.getClass();
        this.firAccessor = firPropertyAccessor;
        this.isSetter = z;
        this.firParentProperty = firProperty;
        this.correspondingPropertySymbol = irPropertySymbol;
        irSimpleFunctionSymbol.bind(this);
        this.annotations = firPropertyAccessor != null ? createLazyAnnotations() : LazyUtilKt.lazyVar(getLock(), new Function0() { // from class: fw4
            public final Object invoke() {
                return Fir2IrLazyPropertyAccessor.f();
            }
        });
        this.returnType = LazyUtilKt.lazyVar(getLock(), new Function0() { // from class: gw4
            public final Object invoke() {
                return Fir2IrLazyPropertyAccessor.g(this.b);
            }
        });
        this.overriddenSymbols = Fir2IrSymbolsMappingForLazyClassesKt.lazyMappedFunctionListVar(getSymbolsMappingForLazyClasses(), getLock(), new Function0() { // from class: hw4
            public final Object invoke() {
                return Fir2IrLazyPropertyAccessor.h(firRegularClass, this);
            }
        });
        this.initialSignatureFunction = LazyKt.lazy(new Function0() { // from class: iw4
            public final Object invoke() {
                return Fir2IrLazyPropertyAccessor.i(this.b);
            }
        });
        this.conversionTypeContext = z ? ConversionTypeOrigin.SETTER : ConversionTypeOrigin.DEFAULT;
    }

    public static List f() {
        return CollectionsKt.emptyList();
    }

    public static IrType g(Fir2IrLazyPropertyAccessor fir2IrLazyPropertyAccessor) {
        return fir2IrLazyPropertyAccessor.isSetter ? fir2IrLazyPropertyAccessor.getBuiltins().getUnitType() : Fir2IrTypeConverterKt.toIrType(fir2IrLazyPropertyAccessor, fir2IrLazyPropertyAccessor.firParentProperty.getReturnTypeRef(), fir2IrLazyPropertyAccessor.conversionTypeContext);
    }

    public static List h(FirRegularClass firRegularClass, Fir2IrLazyPropertyAccessor fir2IrLazyPropertyAccessor) {
        if (firRegularClass == null) {
            return CollectionsKt.emptyList();
        }
        IrPropertySymbol correspondingPropertySymbol = fir2IrLazyPropertyAccessor.getCorrespondingPropertySymbol();
        correspondingPropertySymbol.getClass();
        List<IrPropertySymbol> overriddenSymbols = correspondingPropertySymbol.getOwner().getOverriddenSymbols();
        ArrayList arrayList = new ArrayList();
        for (IrPropertySymbol irPropertySymbol : overriddenSymbols) {
            boolean zIsBound = irPropertySymbol.isBound();
            boolean z = fir2IrLazyPropertyAccessor.isSetter;
            IrSimpleFunctionSymbol irSimpleFunctionSymbolFindSetterOfProperty = null;
            if (zIsBound) {
                if (!z) {
                    IrSimpleFunction getter = irPropertySymbol.getOwner().getGetter();
                    if (getter != null) {
                        irSimpleFunctionSymbolFindSetterOfProperty = getter.getSymbol();
                    }
                } else {
                    if (!z) {
                        bu8.a();
                        return null;
                    }
                    IrSimpleFunction setter = irPropertySymbol.getOwner().getSetter();
                    if (setter != null) {
                        irSimpleFunctionSymbolFindSetterOfProperty = setter.getSymbol();
                    }
                }
            } else if (!z) {
                irSimpleFunctionSymbolFindSetterOfProperty = fir2IrLazyPropertyAccessor.getDeclarationStorage().findGetterOfProperty(irPropertySymbol);
            } else {
                if (!z) {
                    bu8.a();
                    return null;
                }
                irSimpleFunctionSymbolFindSetterOfProperty = fir2IrLazyPropertyAccessor.getDeclarationStorage().findSetterOfProperty(irPropertySymbol);
            }
            if (irSimpleFunctionSymbolFindSetterOfProperty != null) {
                arrayList.add(irSimpleFunctionSymbolFindSetterOfProperty);
            }
        }
        return arrayList;
    }

    public static IrFunction i(Fir2IrLazyPropertyAccessor fir2IrLazyPropertyAccessor) {
        FirNamedFunction delegate;
        FirRegularClassSymbol symbol;
        FirCallableDeclaration fir = fir2IrLazyPropertyAccessor.getFir();
        ConeClassLikeLookupTag lookupTag = null;
        FirSyntheticPropertyAccessor firSyntheticPropertyAccessor = fir instanceof FirSyntheticPropertyAccessor ? (FirSyntheticPropertyAccessor) fir : null;
        if (firSyntheticPropertyAccessor == null || (delegate = firSyntheticPropertyAccessor.getDelegate()) == null) {
            return null;
        }
        IrPropertySymbol correspondingPropertySymbol = fir2IrLazyPropertyAccessor.getCorrespondingPropertySymbol();
        correspondingPropertySymbol.getClass();
        Fir2IrLazyProperty owner = correspondingPropertySymbol.getOwner();
        owner.getClass();
        FirRegularClass containingClass = owner.getContainingClass();
        if (containingClass != null && (symbol = containingClass.getSymbol()) != null) {
            lookupTag = symbol.getLookupTag();
        }
        return Fir2IrDeclarationStorage.getIrFunctionSymbol$default(fir2IrLazyPropertyAccessor.getDeclarationStorage(), delegate.getSymbol(), lookupTag, false, 4, null).getOwner();
    }

    public List<IrAnnotation> getAnnotations() {
        return (List) this.annotations.getValue(this, $$delegatedProperties[0]);
    }

    public DeserializedContainerSource getContainerSource() {
        return this.firParentProperty.getContainerSource();
    }

    @Override // org.jetbrains.kotlin.fir.lazy.AbstractFir2IrLazyFunction
    public IrPropertySymbol getCorrespondingPropertySymbol() {
        return this.correspondingPropertySymbol;
    }

    @Override // org.jetbrains.kotlin.fir.lazy.AbstractFir2IrLazyDeclaration
    public FirCallableDeclaration getFir() {
        FirPropertyAccessor firPropertyAccessor = this.firAccessor;
        return firPropertyAccessor != null ? firPropertyAccessor : this.firParentProperty;
    }

    public IrFunction getInitialSignatureFunction() {
        return (IrFunction) this.initialSignatureFunction.getValue();
    }

    public Name getName() {
        StringBuilder sb = new StringBuilder("<");
        sb.append(this.isSetter ? "set" : "get");
        sb.append('-');
        sb.append(this.firParentProperty.getName());
        sb.append('>');
        Name nameSpecial = Name.special(sb.toString());
        nameSpecial.getClass();
        return nameSpecial;
    }

    public List<IrSimpleFunctionSymbol> getOverriddenSymbols() {
        return (List) this.overriddenSymbols.getValue(this, $$delegatedProperties[2]);
    }

    public IrType getReturnType() {
        return (IrType) this.returnType.getValue(this, $$delegatedProperties[1]);
    }

    @Override // org.jetbrains.kotlin.fir.lazy.AbstractFir2IrLazyFunction
    public boolean isInline() {
        FirPropertyAccessor firPropertyAccessor = this.firAccessor;
        return firPropertyAccessor != null && firPropertyAccessor.getStatus().isInline();
    }

    /* JADX INFO: renamed from: isSetter, reason: from getter */
    public final boolean getIsSetter() {
        return this.isSetter;
    }

    public void setAnnotations(List<? extends IrAnnotation> list) {
        list.getClass();
        this.annotations.setValue(this, $$delegatedProperties[0], list);
    }

    @Override // org.jetbrains.kotlin.fir.lazy.AbstractFir2IrLazyFunction
    public void setCorrespondingPropertySymbol(IrPropertySymbol irPropertySymbol) {
        this.correspondingPropertySymbol = irPropertySymbol;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.lazy.AbstractFir2IrLazyFunction
    public void setInline(boolean z) throws KotlinNothingValueException {
        AbstractFir2IrLazyDeclarationKt.mutationNotSupported();
        throw new KotlinNothingValueException();
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
}
