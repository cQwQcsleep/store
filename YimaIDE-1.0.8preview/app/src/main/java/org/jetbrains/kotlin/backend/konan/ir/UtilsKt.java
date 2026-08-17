package org.jetbrains.kotlin.backend.konan.ir;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.ir.IrBuiltIns;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrConstructor;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.expressions.IrAnnotation;
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin;
import org.jetbrains.kotlin.ir.expressions.impl.BuildersKt;
import org.jetbrains.kotlin.ir.expressions.impl.IrAnnotationImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrConstImpl;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.ir.types.IdSignatureValues;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.types.IrTypePredicatesKt;
import org.jetbrains.kotlin.ir.types.IrTypesKt;
import org.jetbrains.kotlin.ir.util.IdSignature;
import org.jetbrains.kotlin.ir.util.IrTypeUtilsKt;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.utils.CollectionUtilKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\n\u0010\u0005\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0006\u001a\u00020\u0001*\u00020\u0002\u001a\f\u0010\f\u001a\u0004\u0018\u00010\u0002*\u00020\u0002\u001a\n\u0010\r\u001a\u00020\u0001*\u00020\u0002\u001a\u0010\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\b*\u00020\u0002\u001a\n\u0010\u000f\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0010\u001a\u00020\u0001*\u00020\u0011\u001a?\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u00022\u0012\u0010\u001a\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001c0\u001b\"\u00020\u001c¢\u0006\u0002\u0010\u001d\"\u001b\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b\"\u001b\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001f*\u00020 8F¢\u0006\u0006\u001a\u0004\b!\u0010\"¨\u0006#"}, d2 = {"isClassTypeWithSignature", "", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "signature", "Lorg/jetbrains/kotlin/ir/util/IdSignature$CommonSignature;", "isUnit", "isKotlinArray", "superClasses", "", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "getSuperClasses", "(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)Ljava/util/List;", "getSuperClassNotAny", "isNothing", "getSuperInterfaces", "isSpecialClassWithNoSupertypes", "isInlineParameter", "Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", "buildSimpleAnnotation", "Lorg/jetbrains/kotlin/ir/expressions/IrAnnotation;", "irBuiltIns", "Lorg/jetbrains/kotlin/ir/IrBuiltIns;", "startOffset", "", "endOffset", "annotationClass", "args", "", "", "(Lorg/jetbrains/kotlin/ir/IrBuiltIns;IILorg/jetbrains/kotlin/ir/declarations/IrClass;[Ljava/lang/String;)Lorg/jetbrains/kotlin/ir/expressions/IrAnnotation;", "allOverriddenFunctions", "", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "getAllOverriddenFunctions", "(Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;)Ljava/util/Set;", "org.jetbrains.kotlin:ir.backend.native"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class UtilsKt {
    private static final void _get_allOverriddenFunctions_$traverse(Set<IrSimpleFunction> set, IrSimpleFunction irSimpleFunction) {
        if (set.contains(irSimpleFunction)) {
            return;
        }
        set.add(irSimpleFunction);
        Iterator it = irSimpleFunction.getOverriddenSymbols().iterator();
        while (it.hasNext()) {
            _get_allOverriddenFunctions_$traverse(set, ((IrSimpleFunctionSymbol) it.next()).getOwner());
        }
    }

    public static final IrAnnotation buildSimpleAnnotation(IrBuiltIns irBuiltIns, int i, int i2, IrClass irClass, String... strArr) {
        irBuiltIns.getClass();
        irClass.getClass();
        strArr.getClass();
        Sequence constructors = IrUtilsKt.getConstructors(irClass);
        IrConstructor irConstructor = (IrConstructor) SequencesKt.singleOrNull(constructors);
        int i3 = 0;
        if (irConstructor == null) {
            Object obj = null;
            boolean z = false;
            for (Object obj2 : constructors) {
                if (((IrConstructor) obj2).getParameters().size() == strArr.length) {
                    if (z) {
                        w01.a("Sequence contains more than one matching element.");
                        return null;
                    }
                    z = true;
                    obj = obj2;
                }
            }
            if (!z) {
                hb9.a("Sequence contains no element matching the predicate.");
                return null;
            }
            irConstructor = (IrConstructor) obj;
        }
        IrAnnotationImpl irAnnotationImplFromSymbolOwner$default = BuildersKt.fromSymbolOwner$default(IrAnnotationImpl.Companion, i, i2, irConstructor.getReturnType(), irConstructor.getSymbol(), (IrStatementOrigin) null, 16, (Object) null);
        int length = strArr.length;
        int i4 = 0;
        while (i3 < length) {
            String str = strArr[i3];
            Intrinsics.areEqual(((IrValueParameter) irConstructor.getParameters().get(i4)).getType(), irBuiltIns.getStringType());
            irAnnotationImplFromSymbolOwner$default.getArguments().set(i4, IrConstImpl.Companion.string(i, i2, irBuiltIns.getStringType(), str));
            i3++;
            i4++;
        }
        return irAnnotationImplFromSymbolOwner$default;
    }

    public static final Set<IrSimpleFunction> getAllOverriddenFunctions(IrSimpleFunction irSimpleFunction) {
        irSimpleFunction.getClass();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        _get_allOverriddenFunctions_$traverse(linkedHashSet, irSimpleFunction);
        return linkedHashSet;
    }

    public static final IrClass getSuperClassNotAny(IrClass irClass) {
        irClass.getClass();
        List<IrClassSymbol> superClasses = getSuperClasses(irClass);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(superClasses, 10));
        Iterator<T> it = superClasses.iterator();
        while (it.hasNext()) {
            arrayList.add(((IrClassSymbol) it.next()).getOwner());
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            IrClass irClass2 = (IrClass) obj;
            if (!IrUtilsKt.isInterface(irClass2) && !IrTypePredicatesKt.isAny(irClass2)) {
                arrayList2.add(obj);
            }
        }
        return (IrClass) CollectionUtilKt.atMostOne(arrayList2);
    }

    public static final List<IrClassSymbol> getSuperClasses(IrClass irClass) {
        irClass.getClass();
        List superTypes = irClass.getSuperTypes();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(superTypes, 10));
        Iterator it = superTypes.iterator();
        while (it.hasNext()) {
            IrClassSymbol classifierOrFail = IrTypesKt.getClassifierOrFail((IrType) it.next());
            classifierOrFail.getClass();
            arrayList.add(classifierOrFail);
        }
        return arrayList;
    }

    public static final List<IrClass> getSuperInterfaces(IrClass irClass) {
        irClass.getClass();
        List<IrClassSymbol> superClasses = getSuperClasses(irClass);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(superClasses, 10));
        Iterator<T> it = superClasses.iterator();
        while (it.hasNext()) {
            arrayList.add(((IrClassSymbol) it.next()).getOwner());
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (IrUtilsKt.isInterface((IrClass) obj)) {
                arrayList2.add(obj);
            }
        }
        return arrayList2;
    }

    private static final boolean isClassTypeWithSignature(IrClass irClass, IdSignature.CommonSignature commonSignature) {
        return Intrinsics.areEqual(commonSignature, irClass.getSymbol().getSignature());
    }

    public static final boolean isInlineParameter(IrValueParameter irValueParameter) {
        irValueParameter.getClass();
        if (irValueParameter.isNoinline()) {
            return false;
        }
        return (IrTypeUtilsKt.isFunction(irValueParameter.getType()) || IrTypeUtilsKt.isSuspendFunction(irValueParameter.getType())) && !IrTypePredicatesKt.isMarkedNullable(irValueParameter.getType());
    }

    public static final boolean isKotlinArray(IrClass irClass) {
        irClass.getClass();
        return isClassTypeWithSignature(irClass, IdSignatureValues.array);
    }

    public static final boolean isNothing(IrClass irClass) {
        irClass.getClass();
        return isClassTypeWithSignature(irClass, IdSignatureValues.nothing);
    }

    public static final boolean isSpecialClassWithNoSupertypes(IrClass irClass) {
        irClass.getClass();
        return IrTypePredicatesKt.isAny(irClass) || isNothing(irClass);
    }

    public static final boolean isUnit(IrClass irClass) {
        irClass.getClass();
        return isClassTypeWithSignature(irClass, IdSignatureValues.unit);
    }
}
