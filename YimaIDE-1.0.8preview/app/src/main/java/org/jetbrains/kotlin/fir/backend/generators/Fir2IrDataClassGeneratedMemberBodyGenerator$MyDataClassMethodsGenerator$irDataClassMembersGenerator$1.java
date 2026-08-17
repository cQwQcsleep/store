package org.jetbrains.kotlin.fir.backend.generators;

import java.util.Iterator;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.backend.Fir2IrDeclarationStorage;
import org.jetbrains.kotlin.fir.backend.utils.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.scopes.FirScopeKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ArrayUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypesKt;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.ir.builders.IrGeneratorContextBase;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrField;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.declarations.IrProperty;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.expressions.IrMemberAccessExpression;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.util.DataClassMembersGenerator;
import org.jetbrains.kotlin.ir.util.IrBasedDataClassMembersGenerator;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.ir.util.SymbolTable;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001:\u0001\u0019J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0007H\u0016R\u001b\u0010\u000e\u001a\u00020\r*\u00020\u000f8F¢\u0006\f\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001a"}, d2 = {"org/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassGeneratedMemberBodyGenerator$MyDataClassMethodsGenerator$irDataClassMembersGenerator$1", "Lorg/jetbrains/kotlin/ir/util/IrBasedDataClassMembersGenerator;", "generateSyntheticFunctionParameterDeclarations", Argument.Delimiters.none, "irFunction", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "getProperty", "Lorg/jetbrains/kotlin/ir/declarations/IrProperty;", "irValueParameter", "Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", "getHashCodeFunction", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "erasedUpperBound", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "getErasedUpperBound$annotations", "(Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;)V", "getErasedUpperBound", "(Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;)Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "getHashCodeFunctionInfo", "Lorg/jetbrains/kotlin/ir/util/DataClassMembersGenerator$HashCodeFunctionInfo;", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/ir/types/IrType;", "property", "Fir2IrHashCodeFunctionInfo", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrDataClassGeneratedMemberBodyGenerator$MyDataClassMethodsGenerator$irDataClassMembersGenerator$1 extends IrBasedDataClassMembersGenerator {
    final /* synthetic */ Fir2IrDataClassGeneratedMemberBodyGenerator.MyDataClassMethodsGenerator this$0;

    @Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\u008a\u0004\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0014\u0010\f\u001a\u00020\r2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000fH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"org/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassGeneratedMemberBodyGenerator.MyDataClassMethodsGenerator.irDataClassMembersGenerator.1.Fir2IrHashCodeFunctionInfo", "Lorg/jetbrains/kotlin/ir/util/DataClassMembersGenerator$HashCodeFunctionInfo;", "symbol", "Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "hasDispatchReceiver", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassGeneratedMemberBodyGenerator$MyDataClassMethodsGenerator$irDataClassMembersGenerator$1;Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;Z)V", "getSymbol", "()Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "getHasDispatchReceiver", "()Ljava/lang/Boolean;", "commitSubstituted", Argument.Delimiters.none, "irMemberAccessExpression", "Lorg/jetbrains/kotlin/ir/expressions/IrMemberAccessExpression;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public final class Fir2IrHashCodeFunctionInfo implements DataClassMembersGenerator.HashCodeFunctionInfo {
        private final boolean hasDispatchReceiver;
        private final IrSimpleFunctionSymbol symbol;
        final /* synthetic */ Fir2IrDataClassGeneratedMemberBodyGenerator$MyDataClassMethodsGenerator$irDataClassMembersGenerator$1 this$0;

        public Fir2IrHashCodeFunctionInfo(Fir2IrDataClassGeneratedMemberBodyGenerator$MyDataClassMethodsGenerator$irDataClassMembersGenerator$1 fir2IrDataClassGeneratedMemberBodyGenerator$MyDataClassMethodsGenerator$irDataClassMembersGenerator$1, IrSimpleFunctionSymbol irSimpleFunctionSymbol, boolean z) {
            irSimpleFunctionSymbol.getClass();
            this.this$0 = fir2IrDataClassGeneratedMemberBodyGenerator$MyDataClassMethodsGenerator$irDataClassMembersGenerator$1;
            this.symbol = irSimpleFunctionSymbol;
            this.hasDispatchReceiver = z;
        }

        public void commitSubstituted(IrMemberAccessExpression<?> irMemberAccessExpression) {
            irMemberAccessExpression.getClass();
        }

        public Boolean getHasDispatchReceiver() {
            return Boolean.valueOf(this.hasDispatchReceiver);
        }

        public IrSimpleFunctionSymbol getSymbol() {
            return this.symbol;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Fir2IrDataClassGeneratedMemberBodyGenerator$MyDataClassMethodsGenerator$irDataClassMembersGenerator$1(SymbolTable symbolTable, Fir2IrDataClassGeneratedMemberBodyGenerator.MyDataClassMethodsGenerator myDataClassMethodsGenerator, IrGeneratorContextBase irGeneratorContextBase, IrClass irClass, FqName fqName, IrDeclarationOrigin irDeclarationOrigin, boolean z) {
        super(irGeneratorContextBase, symbolTable, irClass, fqName, irDeclarationOrigin, false, z);
        this.this$0 = myDataClassMethodsGenerator;
    }

    public static /* synthetic */ void getErasedUpperBound$annotations(FirTypeParameter firTypeParameter) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final FirNamedFunctionSymbol getHashCodeFunction(FirRegularClass klass) {
        boolean zAreEqual = Intrinsics.areEqual(FirDeclarationUtilKt.getClassId(klass), StandardClassIds.INSTANCE.getNothing());
        Fir2IrDataClassGeneratedMemberBodyGenerator.MyDataClassMethodsGenerator myDataClassMethodsGenerator = this.this$0;
        if (zAreEqual) {
            FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) myDataClassMethodsGenerator, myDataClassMethodsGenerator.getSession().getBuiltinTypes().getAnyType().getConeType());
            regularClassSymbol.getClass();
            return getHashCodeFunction((FirRegularClass) regularClassSymbol.getFir());
        }
        for (FirNamedFunctionSymbol firNamedFunctionSymbol : FirScopeKt.getFunctions(ScopeUtilsKt.unsubstitutedScope(myDataClassMethodsGenerator, klass.getSymbol()), StandardNames.HASHCODE_NAME)) {
            FirNamedFunction firNamedFunction = (FirNamedFunction) firNamedFunctionSymbol.getFir();
            if (firNamedFunction.getValueParameters().isEmpty() && firNamedFunction.getReceiverParameter() == null && firNamedFunction.getContextParameters().isEmpty()) {
                return firNamedFunctionSymbol;
            }
        }
        hb9.a("Collection contains no element matching the predicate.");
        return null;
    }

    public void generateSyntheticFunctionParameterDeclarations(IrFunction irFunction) {
        irFunction.getClass();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9, types: [org.jetbrains.kotlin.fir.FirElement] */
    public final FirRegularClass getErasedUpperBound(FirTypeParameter firTypeParameter) {
        FirRegularClass firRegularClass;
        ClassKind classKind;
        firTypeParameter.getClass();
        for (FirTypeRef firTypeRef : firTypeParameter.getBounds()) {
            Fir2IrDataClassGeneratedMemberBodyGenerator.MyDataClassMethodsGenerator myDataClassMethodsGenerator = this.this$0;
            FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(myDataClassMethodsGenerator, myDataClassMethodsGenerator.coerceToAny(FirTypeUtilsKt.getConeType(firTypeRef)));
            if (regularClassSymbol != null && (firRegularClass = (FirRegularClass) regularClassSymbol.getFir()) != null && (classKind = firRegularClass.getClassKind()) != ClassKind.INTERFACE && classKind != ClassKind.ANNOTATION_CLASS) {
                return firRegularClass;
            }
        }
        Fir2IrDataClassGeneratedMemberBodyGenerator.MyDataClassMethodsGenerator myDataClassMethodsGenerator2 = this.this$0;
        FirClassifierSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(this.this$0, myDataClassMethodsGenerator2.coerceToAny(TypeExpansionUtilsKt.fullyExpandedType(myDataClassMethodsGenerator2, FirTypeUtilsKt.getConeType((FirTypeRef) CollectionsKt.first(firTypeParameter.getBounds())))));
        ?? fir = symbol != null ? symbol.getFir() : 0;
        if (fir instanceof FirRegularClass) {
            return (FirRegularClass) fir;
        }
        if (fir instanceof FirTypeParameter) {
            return getErasedUpperBound((FirTypeParameter) fir);
        }
        StringBuilder sb = new StringBuilder("unknown supertype kind ");
        sb.append(fir != 0 ? UtilsKt.render(fir) : null);
        throw new IllegalStateException(sb.toString().toString());
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11, types: [org.jetbrains.kotlin.fir.FirElement] */
    /* JADX WARN: Type inference failed for: r8v32 */
    public DataClassMembersGenerator.HashCodeFunctionInfo getHashCodeFunctionInfo(IrProperty property) throws KotlinIllegalArgumentExceptionWithAttachments {
        Object next;
        FirVariableSymbol firVariableSymbol;
        FirRegularClass erasedUpperBound;
        Pair pair;
        property.getClass();
        Fir2IrDataClassGeneratedMemberBodyGenerator.MyDataClassMethodsGenerator myDataClassMethodsGenerator = this.this$0;
        Iterator it = FirScopeKt.getProperties(ScopeUtilsKt.declaredScope(myDataClassMethodsGenerator, myDataClassMethodsGenerator.getKlass().getSymbol()), property.getName()).iterator();
        do {
            if (!it.hasNext()) {
                hb9.a("Collection contains no element matching the predicate.");
                return null;
            }
            next = it.next();
            firVariableSymbol = (FirVariableSymbol) next;
            firVariableSymbol.getClass();
        } while (!DeclarationAttributesKt.getFromPrimaryConstructor((FirPropertySymbol) firVariableSymbol));
        next.getClass();
        ConeKotlinType coneKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType(this.this$0, ((FirPropertySymbol) next).getResolvedReturnType());
        if (ArrayUtilsKt.isArrayOrPrimitiveArray(coneKotlinTypeFullyExpandedType, false)) {
            pair = TuplesKt.to(getContext().getIrBuiltIns().getDataClassArrayMemberHashCodeSymbol(), Boolean.FALSE);
        } else {
            FirClassifierSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(this.this$0, this.this$0.coerceToAny(ConeTypesKt.unwrapToSimpleTypeUsingLowerBound(coneKotlinTypeFullyExpandedType)));
            ?? fir = symbol != null ? symbol.getFir() : 0;
            if (fir instanceof FirRegularClass) {
                erasedUpperBound = (FirRegularClass) fir;
            } else {
                if (!(fir instanceof FirTypeParameter)) {
                    StringBuilder sb = new StringBuilder("Unknown classifier kind ");
                    sb.append(fir != 0 ? UtilsKt.render(fir) : null);
                    throw new IllegalStateException(sb.toString().toString());
                }
                erasedUpperBound = getErasedUpperBound((FirTypeParameter) fir);
            }
            FirNamedFunctionSymbol hashCodeFunction = getHashCodeFunction(erasedUpperBound);
            IrSimpleFunctionSymbol irFunctionSymbol$default = Fir2IrDeclarationStorage.getIrFunctionSymbol$default(this.this$0.getDeclarationStorage(), hashCodeFunction, erasedUpperBound.getSymbol().getLookupTag(), false, 4, null);
            irFunctionSymbol$default.getClass();
            pair = TuplesKt.to(irFunctionSymbol$default, Boolean.valueOf(hashCodeFunction.getDispatchReceiverType() != null));
        }
        return new Fir2IrHashCodeFunctionInfo(this, (IrSimpleFunctionSymbol) pair.component1(), ((Boolean) pair.component2()).booleanValue());
    }

    public IrProperty getProperty(IrValueParameter irValueParameter) {
        if (irValueParameter == null) {
            w01.a("Required value was null.");
            return null;
        }
        for (IrProperty irProperty : IrUtilsKt.getProperties(getIrClass())) {
            if (Intrinsics.areEqual(irProperty.getName(), irValueParameter.getName())) {
                IrField backingField = irProperty.getBackingField();
                if (Intrinsics.areEqual(backingField != null ? backingField.getType() : null, irValueParameter.getType())) {
                    return irProperty;
                }
            }
        }
        hb9.a("Sequence contains no element matching the predicate.");
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public DataClassMembersGenerator.HashCodeFunctionInfo getHashCodeFunctionInfo(IrType type) throws KotlinNothingValueException {
        type.getClass();
        AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
        throw new KotlinNothingValueException();
    }
}
