package org.jetbrains.kotlin.backend.common;

import java.util.Map;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.backend.common.serialization.KotlinIrLinker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.PackageFragmentDescriptor;
import org.jetbrains.kotlin.ir.IrBuiltInsOverSymbolFinder;
import org.jetbrains.kotlin.ir.declarations.IrExternalPackageFragment;
import org.jetbrains.kotlin.ir.declarations.IrFactory;
import org.jetbrains.kotlin.ir.declarations.impl.IrExternalPackageFragmentImpl;
import org.jetbrains.kotlin.ir.symbols.IrClassifierSymbol;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.ir.symbols.impl.IrExternalPackageFragmentSymbolImpl;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.StandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010;\u001a\u00020\u000f2\u0006\u0010<\u001a\u00020=H\u0002J\b\u0010>\u001a\u00020?H\u0002R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R \u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\u0015X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u0017X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u00020\u0017X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001cR\u0014\u0010\u001f\u001a\u00020\u0017X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001cR\u0014\u0010!\u001a\u00020\u0017X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001cR\u0014\u0010#\u001a\u00020\u0017X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001cR\u0014\u0010%\u001a\u00020\u0017X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001cR\u0014\u0010'\u001a\u00020\u0017X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001cR\u0014\u0010)\u001a\u00020\u0017X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001cR\u0014\u0010+\u001a\u00020\u0017X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001cR\u0014\u0010-\u001a\u00020\u0017X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001cR\u0014\u0010/\u001a\u00020\u0017X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u001cR\u0014\u00101\u001a\u00020\u0017X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u001cR \u00103\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\u0015X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\u0019R \u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\u0015X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\u0019R \u00107\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\u0015X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\u0019R \u00109\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\u0015X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u0010\u0019¨\u0006@"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/IrBuiltInsForLinker;", "Lorg/jetbrains/kotlin/ir/IrBuiltInsOverSymbolFinder;", "linker", "Lorg/jetbrains/kotlin/backend/common/serialization/KotlinIrLinker;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "<init>", "(Lorg/jetbrains/kotlin/backend/common/serialization/KotlinIrLinker;Lorg/jetbrains/kotlin/config/LanguageVersionSettings;)V", "getLanguageVersionSettings", "()Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "irFactory", "Lorg/jetbrains/kotlin/ir/declarations/IrFactory;", "getIrFactory", "()Lorg/jetbrains/kotlin/ir/declarations/IrFactory;", "operatorsPackageFragment", "Lorg/jetbrains/kotlin/ir/declarations/IrExternalPackageFragment;", "getOperatorsPackageFragment", "()Lorg/jetbrains/kotlin/ir/declarations/IrExternalPackageFragment;", "kotlinInternalPackageFragment", "getKotlinInternalPackageFragment", "ieee754equalsFunByOperandType", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/symbols/IrClassifierSymbol;", "Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "getIeee754equalsFunByOperandType", "()Ljava/util/Map;", "eqeqeqSymbol", "getEqeqeqSymbol", "()Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "eqeqSymbol", "getEqeqSymbol", "throwCceSymbol", "getThrowCceSymbol", "throwIseSymbol", "getThrowIseSymbol", "andandSymbol", "getAndandSymbol", "ororSymbol", "getOrorSymbol", "noWhenBranchMatchedExceptionSymbol", "getNoWhenBranchMatchedExceptionSymbol", "illegalArgumentExceptionSymbol", "getIllegalArgumentExceptionSymbol", "dataClassArrayMemberHashCodeSymbol", "getDataClassArrayMemberHashCodeSymbol", "dataClassArrayMemberToStringSymbol", "getDataClassArrayMemberToStringSymbol", "checkNotNullSymbol", "getCheckNotNullSymbol", "linkageErrorSymbol", "getLinkageErrorSymbol", "lessFunByOperandType", "getLessFunByOperandType", "lessOrEqualFunByOperandType", "getLessOrEqualFunByOperandType", "greaterOrEqualFunByOperandType", "getGreaterOrEqualFunByOperandType", "greaterFunByOperandType", "getGreaterFunByOperandType", "createEmptyExternalPackageFragment", "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "notImplemented", Argument.Delimiters.none, "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class IrBuiltInsForLinker extends IrBuiltInsOverSymbolFinder {
    private final IrSimpleFunctionSymbol andandSymbol;
    private final IrSimpleFunctionSymbol checkNotNullSymbol;
    private final IrSimpleFunctionSymbol dataClassArrayMemberHashCodeSymbol;
    private final IrSimpleFunctionSymbol dataClassArrayMemberToStringSymbol;
    private final IrSimpleFunctionSymbol eqeqSymbol;
    private final IrSimpleFunctionSymbol eqeqeqSymbol;
    private final Map<IrClassifierSymbol, IrSimpleFunctionSymbol> greaterFunByOperandType;
    private final Map<IrClassifierSymbol, IrSimpleFunctionSymbol> greaterOrEqualFunByOperandType;
    private final Map<IrClassifierSymbol, IrSimpleFunctionSymbol> ieee754equalsFunByOperandType;
    private final IrSimpleFunctionSymbol illegalArgumentExceptionSymbol;
    private final IrFactory irFactory;
    private final IrExternalPackageFragment kotlinInternalPackageFragment;
    private final LanguageVersionSettings languageVersionSettings;
    private final Map<IrClassifierSymbol, IrSimpleFunctionSymbol> lessFunByOperandType;
    private final Map<IrClassifierSymbol, IrSimpleFunctionSymbol> lessOrEqualFunByOperandType;
    private final IrSimpleFunctionSymbol linkageErrorSymbol;
    private final IrSimpleFunctionSymbol noWhenBranchMatchedExceptionSymbol;
    private final IrExternalPackageFragment operatorsPackageFragment;
    private final IrSimpleFunctionSymbol ororSymbol;
    private final IrSimpleFunctionSymbol throwCceSymbol;
    private final IrSimpleFunctionSymbol throwIseSymbol;

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IrBuiltInsForLinker(KotlinIrLinker kotlinIrLinker, LanguageVersionSettings languageVersionSettings) throws KotlinNothingValueException {
        super(new SymbolFinderOverLinker(kotlinIrLinker));
        kotlinIrLinker.getClass();
        languageVersionSettings.getClass();
        this.languageVersionSettings = languageVersionSettings;
        this.irFactory = kotlinIrLinker.getSymbolTable().getIrFactory();
        StandardClassIds standardClassIds = StandardClassIds.INSTANCE;
        this.operatorsPackageFragment = createEmptyExternalPackageFragment(standardClassIds.getBASE_INTERNAL_IR_PACKAGE());
        this.kotlinInternalPackageFragment = createEmptyExternalPackageFragment(standardClassIds.getBASE_INTERNAL_PACKAGE());
        notImplemented();
        throw new KotlinNothingValueException();
    }

    private final IrExternalPackageFragment createEmptyExternalPackageFragment(FqName fqName) {
        return new IrExternalPackageFragmentImpl(new IrExternalPackageFragmentSymbolImpl((PackageFragmentDescriptor) null, 1, (DefaultConstructorMarker) null), fqName);
    }

    private final Void notImplemented() {
        throw new IllegalStateException("Should be taken from linker");
    }

    public IrSimpleFunctionSymbol getAndandSymbol() {
        return this.andandSymbol;
    }

    public IrSimpleFunctionSymbol getCheckNotNullSymbol() {
        return this.checkNotNullSymbol;
    }

    public IrSimpleFunctionSymbol getDataClassArrayMemberHashCodeSymbol() {
        return this.dataClassArrayMemberHashCodeSymbol;
    }

    public IrSimpleFunctionSymbol getDataClassArrayMemberToStringSymbol() {
        return this.dataClassArrayMemberToStringSymbol;
    }

    public IrSimpleFunctionSymbol getEqeqSymbol() {
        return this.eqeqSymbol;
    }

    public IrSimpleFunctionSymbol getEqeqeqSymbol() {
        return this.eqeqeqSymbol;
    }

    public Map<IrClassifierSymbol, IrSimpleFunctionSymbol> getGreaterFunByOperandType() {
        return this.greaterFunByOperandType;
    }

    public Map<IrClassifierSymbol, IrSimpleFunctionSymbol> getGreaterOrEqualFunByOperandType() {
        return this.greaterOrEqualFunByOperandType;
    }

    public Map<IrClassifierSymbol, IrSimpleFunctionSymbol> getIeee754equalsFunByOperandType() {
        return this.ieee754equalsFunByOperandType;
    }

    public IrSimpleFunctionSymbol getIllegalArgumentExceptionSymbol() {
        return this.illegalArgumentExceptionSymbol;
    }

    public IrFactory getIrFactory() {
        return this.irFactory;
    }

    public IrExternalPackageFragment getKotlinInternalPackageFragment() {
        return this.kotlinInternalPackageFragment;
    }

    public LanguageVersionSettings getLanguageVersionSettings() {
        return this.languageVersionSettings;
    }

    public Map<IrClassifierSymbol, IrSimpleFunctionSymbol> getLessFunByOperandType() {
        return this.lessFunByOperandType;
    }

    public Map<IrClassifierSymbol, IrSimpleFunctionSymbol> getLessOrEqualFunByOperandType() {
        return this.lessOrEqualFunByOperandType;
    }

    public IrSimpleFunctionSymbol getLinkageErrorSymbol() {
        return this.linkageErrorSymbol;
    }

    public IrSimpleFunctionSymbol getNoWhenBranchMatchedExceptionSymbol() {
        return this.noWhenBranchMatchedExceptionSymbol;
    }

    public IrExternalPackageFragment getOperatorsPackageFragment() {
        return this.operatorsPackageFragment;
    }

    public IrSimpleFunctionSymbol getOrorSymbol() {
        return this.ororSymbol;
    }

    public IrSimpleFunctionSymbol getThrowCceSymbol() {
        return this.throwCceSymbol;
    }

    public IrSimpleFunctionSymbol getThrowIseSymbol() {
        return this.throwIseSymbol;
    }
}
