package androidx.compose.compiler.plugins.kotlin.analysis;

import androidx.compose.compiler.plugins.kotlin.lower.AbstractComposeLoweringKt;
import com.google.common.annotations.VisibleForTesting;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import org.jetbrains.kotlin.backend.jvm.ir.JvmIrTypeUtilsKt;
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.jetbrains.kotlin.ir.IrStatement;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrField;
import org.jetbrains.kotlin.ir.declarations.IrFile;
import org.jetbrains.kotlin.ir.declarations.IrProperty;
import org.jetbrains.kotlin.ir.declarations.IrScript;
import org.jetbrains.kotlin.ir.declarations.IrSymbolOwner;
import org.jetbrains.kotlin.ir.declarations.IrTypeParameter;
import org.jetbrains.kotlin.ir.declarations.IrVariable;
import org.jetbrains.kotlin.ir.expressions.IrCall;
import org.jetbrains.kotlin.ir.expressions.IrComposite;
import org.jetbrains.kotlin.ir.expressions.IrConst;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrGetValue;
import org.jetbrains.kotlin.ir.expressions.IrLocalDelegatedPropertyReference;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrClassifierSymbol;
import org.jetbrains.kotlin.ir.symbols.IrTypeParameterSymbol;
import org.jetbrains.kotlin.ir.types.IrDynamicType;
import org.jetbrains.kotlin.ir.types.IrErrorType;
import org.jetbrains.kotlin.ir.types.IrSimpleType;
import org.jetbrains.kotlin.ir.types.IrStarProjection;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.types.IrTypeArgument;
import org.jetbrains.kotlin.ir.types.IrTypePredicatesKt;
import org.jetbrains.kotlin.ir.types.IrTypeProjection;
import org.jetbrains.kotlin.ir.types.IrTypesKt;
import org.jetbrains.kotlin.ir.util.AdditionalIrUtilsKt;
import org.jetbrains.kotlin.ir.util.InlineClassesKt;
import org.jetbrains.kotlin.ir.util.IrTypeUtilsKt;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\u0018\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bJ<\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u001d2\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020!0\u001f2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00072\b\u0010#\u001a\u0004\u0018\u00010\u001bH\u0002JU\u0010$\u001a\u00020\u0010*\u00020\u00102\b\u0010%\u001a\u0004\u0018\u00010&2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020)0(2\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020!0\u001f2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00072\b\u0010#\u001a\u0004\u0018\u00010\u001bH\u0002¢\u0006\u0002\u0010*J:\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010+\u001a\u00020\u000f2\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020!0\u001f2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0007H\u0002J\f\u0010,\u001a\u00020\u0003*\u00020-H\u0002J\f\u0010.\u001a\u00020\u0003*\u00020\u001dH\u0002J\f\u0010/\u001a\u00020\u0003*\u00020\u001dH\u0002J<\u0010\u0017\u001a\u00020\u00102\u0006\u00100\u001a\u0002012\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020!0\u001f2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00072\b\u0010#\u001a\u0004\u0018\u00010\u001bH\u0002J<\u0010\u0017\u001a\u00020\u00102\u0006\u00102\u001a\u00020!2\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020!0\u001f2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00072\b\u0010#\u001a\u0004\u0018\u00010\u001bH\u0002J<\u0010\u0017\u001a\u00020\u00102\u0006\u00103\u001a\u00020\u00192\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020!0\u001f2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00072\b\u0010#\u001a\u0004\u0018\u00010\u001bH\u0002J\u0018\u00104\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020!0\u001f*\u000205H\u0002J\"\u0010\u0017\u001a\u00020\u00102\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u00020\u00102\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0002J\u0018\u0010\u0017\u001a\u00020\u00102\u0006\u00106\u001a\u0002092\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000RB\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e8\u0006@BX\u0087\u000er\u0002\b\u0016¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015¨\u0006:"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/analysis/StabilityInferencer;", "", "isTargetJvm", "", "currentModule", "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "externalStableTypeMatchers", "", "Landroidx/compose/compiler/plugins/kotlin/analysis/FqNameMatcher;", "<init>", "(ZLorg/jetbrains/kotlin/descriptors/ModuleDescriptor;Ljava/util/Set;)V", "externalTypeMatcherCollection", "Landroidx/compose/compiler/plugins/kotlin/analysis/FqNameMatcherCollection;", "value", "", "Landroidx/compose/compiler/plugins/kotlin/analysis/SymbolForAnalysis;", "Landroidx/compose/compiler/plugins/kotlin/analysis/Stability;", "cache", "getCache$annotations", "()V", "getCache", "()Ljava/util/Map;", "Lcom/google/common/annotations/VisibleForTesting;", "stabilityOf", "irType", "Lorg/jetbrains/kotlin/ir/types/IrType;", "fileContainingDependent", "Lorg/jetbrains/kotlin/ir/declarations/IrFile;", "declaration", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "substitutions", "", "Lorg/jetbrains/kotlin/ir/symbols/IrTypeParameterSymbol;", "Lorg/jetbrains/kotlin/ir/types/IrTypeArgument;", "currentlyAnalyzing", "analysisEntryFile", "applyTypeParameterMask", "mask", "", "typeParameters", "", "Lorg/jetbrains/kotlin/ir/declarations/IrTypeParameter;", "(Landroidx/compose/compiler/plugins/kotlin/analysis/Stability;Ljava/lang/Integer;Ljava/util/List;Ljava/util/Map;Ljava/util/Set;Lorg/jetbrains/kotlin/ir/declarations/IrFile;)Landroidx/compose/compiler/plugins/kotlin/analysis/Stability;", "symbol", "isInCurrentModule", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "isProtobufType", "isExternalStableType", "classifier", "Lorg/jetbrains/kotlin/ir/symbols/IrClassifierSymbol;", "argument", "type", "substitutionMap", "Lorg/jetbrains/kotlin/ir/types/IrSimpleType;", "expr", "Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "baseStability", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class StabilityInferencer {
    private Map<SymbolForAnalysis, Stability> cache;
    private final ModuleDescriptor currentModule;
    private final FqNameMatcherCollection externalTypeMatcherCollection;
    private final boolean isTargetJvm;

    public StabilityInferencer(boolean z, ModuleDescriptor moduleDescriptor, Set<FqNameMatcher> set) {
        moduleDescriptor.getClass();
        set.getClass();
        this.isTargetJvm = z;
        this.currentModule = moduleDescriptor;
        this.externalTypeMatcherCollection = new FqNameMatcherCollection(set);
        this.cache = new LinkedHashMap();
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0041  */
    /* JADX WARN: Code duplicated, block: B:23:0x004d  */
    /* JADX WARN: Code duplicated, block: B:24:0x0052  */
    private final Stability applyTypeParameterMask(Stability stability, Integer num, List<? extends IrTypeParameter> list, Map<IrTypeParameterSymbol, ? extends IrTypeArgument> map, Set<SymbolForAnalysis> set, IrFile irFile) {
        IrTypeArgument irTypeArgument;
        if ((num != null && num.intValue() == 0) || list.isEmpty()) {
            return stability;
        }
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Object obj : list) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            IrTypeParameter irTypeParameter = (IrTypeParameter) obj;
            Stability parameter = null;
            if (i < 32) {
                if (num != null) {
                    if (((1 << i) & num.intValue()) != 0) {
                        irTypeArgument = map.get(irTypeParameter.getSymbol());
                        if (irTypeArgument != null) {
                            parameter = stabilityOf(irTypeArgument, map, set, irFile);
                        } else {
                            parameter = new Stability.Parameter(irTypeParameter);
                        }
                    }
                } else {
                    irTypeArgument = map.get(irTypeParameter.getSymbol());
                    if (irTypeArgument != null) {
                        parameter = stabilityOf(irTypeArgument, map, set, irFile);
                    } else {
                        parameter = new Stability.Parameter(irTypeParameter);
                    }
                }
            }
            if (parameter != null) {
                arrayList.add(parameter);
            }
            i = i2;
        }
        return stability.plus(new Stability.Combined(arrayList));
    }

    @VisibleForTesting
    public static /* synthetic */ void getCache$annotations() {
    }

    private final boolean isExternalStableType(IrClass irClass) {
        return this.externalTypeMatcherCollection.matches(IrUtilsKt.getFqNameWhenAvailable(irClass), irClass.getSuperTypes());
    }

    private final boolean isInCurrentModule(IrDeclaration irDeclaration) {
        return Intrinsics.areEqual(AdditionalIrUtilsKt.getModule(irDeclaration), this.currentModule);
    }

    private final boolean isProtobufType(IrClass irClass) {
        String string;
        Object objPrevious;
        IrClassSymbol classOrNull;
        IrClass owner;
        FqName fqNameWhenAvailable;
        if (!IrUtilsKt.isFinalClass(irClass)) {
            return false;
        }
        List superTypes = irClass.getSuperTypes();
        ListIterator listIterator = superTypes.listIterator(superTypes.size());
        do {
            string = null;
            if (!listIterator.hasPrevious()) {
                objPrevious = null;
                break;
            }
            objPrevious = listIterator.previous();
        } while (IrTypeUtilsKt.isInterface((IrType) objPrevious));
        IrType irType = (IrType) objPrevious;
        if (irType != null && (classOrNull = IrTypesKt.getClassOrNull(irType)) != null && (owner = classOrNull.getOwner()) != null && (fqNameWhenAvailable = IrUtilsKt.getFqNameWhenAvailable(owner)) != null) {
            string = fqNameWhenAvailable.toString();
        }
        return Intrinsics.areEqual(string, "com.google.protobuf.GeneratedMessageLite") || Intrinsics.areEqual(string, "com.google.protobuf.GeneratedMessage");
    }

    private final Stability stabilityOf(IrClass declaration, SymbolForAnalysis symbol, Map<IrTypeParameterSymbol, ? extends IrTypeArgument> substitutions, Set<SymbolForAnalysis> currentlyAnalyzing) {
        String string;
        if (currentlyAnalyzing.contains(symbol)) {
            return Stability.INSTANCE.getUnstable();
        }
        if (StabilityKt.hasStableMarkedDescendant(declaration)) {
            return Stability.INSTANCE.getStable();
        }
        if (IrUtilsKt.isEnumClass(declaration) || IrUtilsKt.isEnumEntry(declaration)) {
            return Stability.INSTANCE.getStable();
        }
        if (IrUtilsKt.isObject(declaration)) {
            return Stability.INSTANCE.getStable();
        }
        if (!IrTypePredicatesKt.isPrimitiveType$default(IrUtilsKt.getDefaultType(declaration), false, 1, (Object) null) && !isProtobufType(declaration)) {
            IrDeclarationOrigin origin = declaration.getOrigin();
            IrDeclarationOrigin.Companion companion = IrDeclarationOrigin.Companion;
            if (Intrinsics.areEqual(origin, companion.getIR_BUILTINS_STUB())) {
                f2f.a("Builtins Stub: ", declaration.getName());
                return null;
            }
            Set<SymbolForAnalysis> setPlus = SetsKt.plus(currentlyAnalyzing, symbol);
            FqName fqNameWhenAvailable = IrUtilsKt.getFqNameWhenAvailable(declaration);
            if (fqNameWhenAvailable == null || (string = fqNameWhenAvailable.toString()) == null) {
                string = "";
            }
            List<? extends IrTypeParameter> typeParameters = declaration.getTypeParameters();
            IrFile fileOrNull = IrUtilsKt.getFileOrNull(declaration);
            IrFile analysisEntryFile = symbol.getAnalysisEntryFile();
            KnownStableConstructs knownStableConstructs = KnownStableConstructs.INSTANCE;
            if (knownStableConstructs.getStableTypes().containsKey(string)) {
                Stability stable = Stability.INSTANCE.getStable();
                Integer num = knownStableConstructs.getStableTypes().get(string);
                return applyTypeParameterMask(stable, Integer.valueOf(num != null ? num.intValue() : 0), typeParameters, substitutions, setPlus, analysisEntryFile);
            }
            if (isExternalStableType(declaration)) {
                Stability stable2 = Stability.INSTANCE.getStable();
                Integer numMaskForName = this.externalTypeMatcherCollection.maskForName(IrUtilsKt.getFqNameWhenAvailable(declaration));
                return applyTypeParameterMask(stable2, Integer.valueOf(numMaskForName != null ? numMaskForName.intValue() : 0), typeParameters, substitutions, setPlus, analysisEntryFile);
            }
            if (Intrinsics.areEqual(declaration.getOrigin(), companion.getIR_EXTERNAL_JAVA_DECLARATION_STUB())) {
                return Stability.INSTANCE.getUnstable();
            }
            if (IrUtilsKt.isInterface(declaration)) {
                return new Stability.Unknown(declaration);
            }
            if (Intrinsics.areEqual(declaration.getOrigin(), companion.getIR_EXTERNAL_DECLARATION_STUB()) && StabilityKt.stabilityParamBitmask(declaration) == null) {
                return Stability.INSTANCE.getUnstable();
            }
            if (this.isTargetJvm && ((declaration.getVisibility().isPublicAPI() || Intrinsics.areEqual(declaration.getVisibility(), DescriptorVisibilities.INTERNAL)) && (fileOrNull == null || !Intrinsics.areEqual(fileOrNull, analysisEntryFile)))) {
                return typeParameters.isEmpty() ? new Stability.Runtime(declaration) : applyTypeParameterMask(new Stability.Runtime(declaration), null, typeParameters, substitutions, setPlus, analysisEntryFile);
            }
            if (Intrinsics.areEqual(declaration.getOrigin(), companion.getIR_EXTERNAL_DECLARATION_STUB())) {
                Integer numStabilityParamBitmask = StabilityKt.stabilityParamBitmask(declaration);
                return numStabilityParamBitmask != null ? applyTypeParameterMask(new Stability.Runtime(declaration), numStabilityParamBitmask, typeParameters, substitutions, setPlus, analysisEntryFile) : Stability.INSTANCE.getUnstable();
            }
            Stability stable3 = declaration.getModality() == Modality.FINAL ? Stability.INSTANCE.getStable() : new Stability.Unknown(declaration);
            for (IrField irField : declaration.getDeclarations()) {
                if (irField instanceof IrProperty) {
                    IrProperty irProperty = (IrProperty) irField;
                    IrField backingField = irProperty.getBackingField();
                    if (backingField == null) {
                        continue;
                    } else {
                        if (irProperty.isVar() && !irProperty.isDelegated()) {
                            return Stability.INSTANCE.getUnstable();
                        }
                        stable3 = stable3.plus(stabilityOf(backingField.getType(), substitutions, setPlus, analysisEntryFile));
                    }
                } else if (irField instanceof IrField) {
                    stable3 = stable3.plus(stabilityOf(irField.getType(), substitutions, setPlus, analysisEntryFile));
                }
            }
            IrClass superClass = IrUtilsKt.getSuperClass(declaration);
            if (superClass == null) {
                return stable3;
            }
            Stability stabilityStabilityOf = stabilityOf(superClass, substitutions, setPlus, analysisEntryFile);
            return !(stabilityStabilityOf instanceof Stability.Unknown) ? stable3.plus(stabilityStabilityOf) : stable3;
        }
        return Stability.INSTANCE.getStable();
    }

    private final Map<IrTypeParameterSymbol, IrTypeArgument> substitutionMap(IrSimpleType irSimpleType) {
        IrClassSymbol classOrNull = IrTypesKt.getClassOrNull(irSimpleType);
        if (classOrNull == null) {
            return MapsKt.emptyMap();
        }
        List typeParameters = classOrNull.getOwner().getTypeParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeParameters, 10));
        Iterator it = typeParameters.iterator();
        while (it.hasNext()) {
            arrayList.add(((IrTypeParameter) it.next()).getSymbol());
        }
        List listZip = CollectionsKt.zip(arrayList, irSimpleType.getArguments());
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : listZip) {
            Pair pair = (Pair) obj;
            IrTypeParameterSymbol irTypeParameterSymbol = (IrTypeParameterSymbol) pair.component1();
            IrSimpleType irSimpleType2 = (IrTypeArgument) pair.component2();
            IrSimpleType irSimpleType3 = irSimpleType2 instanceof IrSimpleType ? irSimpleType2 : null;
            if (!Intrinsics.areEqual(irTypeParameterSymbol, irSimpleType3 != null ? irSimpleType3.getClassifier() : null)) {
                arrayList2.add(obj);
            }
        }
        return MapsKt.toMap(arrayList2);
    }

    public final Map<SymbolForAnalysis, Stability> getCache() {
        return this.cache;
    }

    private final Stability stabilityOf(IrClass declaration, Map<IrTypeParameterSymbol, ? extends IrTypeArgument> substitutions, Set<SymbolForAnalysis> currentlyAnalyzing, IrFile analysisEntryFile) {
        IrClassSymbol symbol = declaration.getSymbol();
        List typeParameters = declaration.getTypeParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeParameters, 10));
        Iterator it = typeParameters.iterator();
        while (it.hasNext()) {
            arrayList.add(substitutions.get(((IrTypeParameter) it.next()).getSymbol()));
        }
        SymbolForAnalysis symbolForAnalysis = new SymbolForAnalysis(symbol, arrayList, analysisEntryFile);
        if (this.cache.containsKey(symbolForAnalysis)) {
            Stability stability = this.cache.get(symbolForAnalysis);
            stability.getClass();
            return stability;
        }
        Stability stabilityStabilityOf = stabilityOf(declaration, symbolForAnalysis, substitutions, currentlyAnalyzing);
        if (Intrinsics.areEqual(IrUtilsKt.getFileOrNull(declaration), analysisEntryFile)) {
            this.cache.put(symbolForAnalysis, stabilityStabilityOf);
        }
        return stabilityStabilityOf;
    }

    public final Stability stabilityOf(IrType irType, IrFile fileContainingDependent) {
        irType.getClass();
        return stabilityOf(irType, MapsKt.emptyMap(), SetsKt.emptySet(), fileContainingDependent);
    }

    private final Stability stabilityOf(IrClassifierSymbol classifier, Map<IrTypeParameterSymbol, ? extends IrTypeArgument> substitutions, Set<SymbolForAnalysis> currentlyAnalyzing, IrFile analysisEntryFile) {
        IrSymbolOwner owner = classifier.getOwner();
        if (owner instanceof IrClass) {
            return stabilityOf((IrClass) owner, substitutions, currentlyAnalyzing, analysisEntryFile);
        }
        if (owner instanceof IrTypeParameter) {
            return Stability.INSTANCE.getUnstable();
        }
        if (owner instanceof IrScript) {
            return Stability.INSTANCE.getStable();
        }
        w04.a("Unexpected IrClassifier: ", owner);
        return null;
    }

    private final Stability stabilityOf(IrTypeArgument argument, Map<IrTypeParameterSymbol, ? extends IrTypeArgument> substitutions, Set<SymbolForAnalysis> currentlyAnalyzing, IrFile analysisEntryFile) {
        if (argument instanceof IrStarProjection) {
            return Stability.INSTANCE.getUnstable();
        }
        if (argument instanceof IrTypeProjection) {
            return stabilityOf(((IrTypeProjection) argument).getType(), substitutions, currentlyAnalyzing, analysisEntryFile);
        }
        bu8.a();
        return null;
    }

    private final Stability stabilityOf(IrType type, Map<IrTypeParameterSymbol, ? extends IrTypeArgument> substitutions, Set<SymbolForAnalysis> currentlyAnalyzing, IrFile analysisEntryFile) {
        if (!(type instanceof IrErrorType) && !(type instanceof IrDynamicType)) {
            if (!IrTypePredicatesKt.isUnit(type) && !IrTypePredicatesKt.isPrimitiveType$default(type, false, 1, (Object) null) && !IrTypeUtilsKt.isFunctionOrKFunction(type) && !AbstractComposeLoweringKt.isSyntheticComposableFunction(type) && !IrTypePredicatesKt.isString(type)) {
                if (IrTypeUtilsKt.isTypeParameter(type)) {
                    IrClassifierSymbol classifierOrFail = IrTypesKt.getClassifierOrFail(type);
                    IrTypeArgument irTypeArgument = substitutions.get(classifierOrFail);
                    SymbolForAnalysis symbolForAnalysis = new SymbolForAnalysis(classifierOrFail, CollectionsKt.emptyList(), analysisEntryFile);
                    if (irTypeArgument != null && !currentlyAnalyzing.contains(symbolForAnalysis)) {
                        return stabilityOf(irTypeArgument, substitutions, SetsKt.plus(currentlyAnalyzing, symbolForAnalysis), analysisEntryFile);
                    }
                    IrTypeParameter owner = classifierOrFail.getOwner();
                    owner.getClass();
                    return new Stability.Parameter(owner);
                }
                if (IrTypeUtilsKt.isNullable(type)) {
                    return stabilityOf(IrTypesKt.makeNotNull(type), substitutions, currentlyAnalyzing, analysisEntryFile);
                }
                if (JvmIrTypeUtilsKt.isInlineClassType(type)) {
                    IrClass irClass = IrTypesKt.getClass(type);
                    if (irClass != null) {
                        if (StabilityKt.hasStableMarker(irClass)) {
                            return Stability.INSTANCE.getStable();
                        }
                        return stabilityOf((IrType) InlineClassesKt.getInlineClassUnderlyingType(irClass), substitutions, currentlyAnalyzing, analysisEntryFile);
                    }
                    w04.a("Failed to resolve the class definition of inline type ", type);
                    return null;
                }
                if (type instanceof IrSimpleType) {
                    IrSimpleType irSimpleType = (IrSimpleType) type;
                    return stabilityOf(irSimpleType.getClassifier(), MapsKt.plus(substitutions, substitutionMap(irSimpleType)), currentlyAnalyzing, analysisEntryFile);
                }
                w04.a("Unexpected IrType: ", type);
                return null;
            }
            return Stability.INSTANCE.getStable();
        }
        return Stability.INSTANCE.getUnstable();
    }

    private final Stability stabilityOf(IrCall expr, Stability baseStability, IrFile fileContainingDependent) {
        Stability unstable;
        Integer num = KnownStableConstructs.INSTANCE.getStableFunctions().get(AdditionalIrUtilsKt.getKotlinFqName(expr.getSymbol().getOwner()).asString());
        if (num == null) {
            return baseStability;
        }
        if (num.intValue() == 0) {
            return Stability.INSTANCE.getStable();
        }
        IntRange indices = CollectionsKt.getIndices(expr.getTypeArguments());
        ArrayList arrayList = new ArrayList();
        IntIterator it = indices.iterator();
        while (it.hasNext()) {
            int iNextInt = it.nextInt();
            if ((num.intValue() & (1 << iNextInt)) != 0) {
                IrType irType = (IrType) expr.getTypeArguments().get(iNextInt);
                if (irType != null) {
                    unstable = stabilityOf(irType, fileContainingDependent);
                } else {
                    unstable = Stability.INSTANCE.getUnstable();
                }
            } else {
                unstable = null;
            }
            if (unstable != null) {
                arrayList.add(unstable);
            }
        }
        return new Stability.Combined(arrayList);
    }

    public final Stability stabilityOf(IrExpression expr, IrFile fileContainingDependent) {
        IrExpression initializer;
        Stability stabilityStabilityOf;
        expr.getClass();
        Stability stabilityStabilityOf2 = stabilityOf(expr.getType(), fileContainingDependent);
        if (!StabilityKt.knownStable(stabilityStabilityOf2)) {
            if (expr instanceof IrConst) {
                return Stability.INSTANCE.getStable();
            }
            if (expr instanceof IrCall) {
                return stabilityOf((IrCall) expr, stabilityStabilityOf2, fileContainingDependent);
            }
            if (expr instanceof IrGetValue) {
                IrVariable owner = ((IrGetValue) expr).getSymbol().getOwner();
                if (owner instanceof IrVariable) {
                    IrVariable irVariable = owner;
                    if (!irVariable.isVar() && (initializer = irVariable.getInitializer()) != null && (stabilityStabilityOf = stabilityOf(initializer, fileContainingDependent)) != null) {
                        return stabilityStabilityOf;
                    }
                }
            } else {
                if (expr instanceof IrLocalDelegatedPropertyReference) {
                    return Stability.INSTANCE.getStable();
                }
                if (expr instanceof IrComposite) {
                    List<IrStatement> statements = ((IrComposite) expr).getStatements();
                    if (!(statements instanceof Collection) || !statements.isEmpty()) {
                        for (IrStatement irStatement : statements) {
                            if (!(irStatement instanceof IrExpression) || !StabilityKt.knownStable(stabilityOf((IrExpression) irStatement, fileContainingDependent))) {
                                return stabilityStabilityOf2;
                            }
                        }
                    }
                    return Stability.INSTANCE.getStable();
                }
            }
        }
        return stabilityStabilityOf2;
    }
}
