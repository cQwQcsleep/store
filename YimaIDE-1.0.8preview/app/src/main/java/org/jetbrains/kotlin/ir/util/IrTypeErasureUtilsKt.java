package org.jetbrains.kotlin.ir.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrScript;
import org.jetbrains.kotlin.ir.declarations.IrSymbolOwner;
import org.jetbrains.kotlin.ir.declarations.IrTypeParameter;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrClassifierSymbol;
import org.jetbrains.kotlin.ir.types.IrErrorType;
import org.jetbrains.kotlin.ir.types.IrSimpleType;
import org.jetbrains.kotlin.ir.types.IrStarProjection;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.types.IrTypeArgument;
import org.jetbrains.kotlin.ir.types.IrTypeProjection;
import org.jetbrains.kotlin.ir.types.IrTypesKt;
import org.jetbrains.kotlin.ir.types.SimpleTypeNullability;
import org.jetbrains.kotlin.ir.types.impl.IrSimpleTypeImplKt;
import org.jetbrains.kotlin.ir.types.impl.IrStarProjectionImpl;
import org.jetbrains.kotlin.types.KotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u0002\u001a\u00020\u0001*\u00020\u0001\u001a\u0014\u0010\u0003\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002\u001a\f\u0010\u0000\u001a\u00020\u0007*\u00020\u0007H\u0002\"\u0015\u0010\b\u001a\u00020\t*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b\"\u0015\u0010\b\u001a\u00020\t*\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\n\u0010\f¨\u0006\r"}, d2 = {"eraseTypeParameters", "Lorg/jetbrains/kotlin/ir/types/IrType;", "eraseIfTypeParameter", "erasedType", "Lorg/jetbrains/kotlin/ir/declarations/IrTypeParameter;", "isNullable", "", "Lorg/jetbrains/kotlin/ir/types/IrTypeArgument;", "erasedUpperBound", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "getErasedUpperBound", "(Lorg/jetbrains/kotlin/ir/declarations/IrTypeParameter;)Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "(Lorg/jetbrains/kotlin/ir/types/IrType;)Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "org.jetbrains.kotlin:ir.tree"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class IrTypeErasureUtilsKt {
    public static final IrType eraseIfTypeParameter(IrType irType) {
        IrClassifierSymbol classifier;
        irType.getClass();
        IrSimpleType irSimpleType = irType instanceof IrSimpleType ? (IrSimpleType) irType : null;
        IrSymbolOwner owner = (irSimpleType == null || (classifier = irSimpleType.getClassifier()) == null) ? null : classifier.getOwner();
        IrTypeParameter irTypeParameter = owner instanceof IrTypeParameter ? (IrTypeParameter) owner : null;
        return irTypeParameter == null ? irType : erasedType(irTypeParameter, IrTypeUtilsKt.isNullable(irType));
    }

    public static final IrType eraseTypeParameters(IrType irType) {
        irType.getClass();
        if (!(irType instanceof IrSimpleType)) {
            if (irType instanceof IrErrorType) {
                return irType;
            }
            w04.a("Unknown IrType kind: ", irType);
            return null;
        }
        IrSimpleType irSimpleType = (IrSimpleType) irType;
        IrTypeParameter owner = irSimpleType.getClassifier().getOwner();
        if (owner instanceof IrScript) {
            irSimpleType.getArguments().isEmpty();
            return IrSimpleTypeImplKt.IrSimpleTypeImpl$default(irSimpleType.getClassifier(), irSimpleType.getNullability(), CollectionsKt.emptyList(), irType.getAnnotations(), (KotlinType) null, 16, (Object) null);
        }
        if (!(owner instanceof IrClass)) {
            if (owner instanceof IrTypeParameter) {
                return erasedType(owner, IrTypeUtilsKt.isNullable(irType));
            }
            w04.a("Unknown IrSimpleType classifier kind: ", owner);
            return null;
        }
        IrClassifierSymbol classifier = irSimpleType.getClassifier();
        SimpleTypeNullability nullability = irSimpleType.getNullability();
        List arguments = irSimpleType.getArguments();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(arguments, 10));
        Iterator it = arguments.iterator();
        while (it.hasNext()) {
            arrayList.add(eraseTypeParameters((IrTypeArgument) it.next()));
        }
        return IrSimpleTypeImplKt.IrSimpleTypeImpl$default(classifier, nullability, arrayList, irType.getAnnotations(), (KotlinType) null, 16, (Object) null);
    }

    private static final IrType erasedType(IrTypeParameter irTypeParameter, boolean z) {
        IrClass erasedUpperBound = getErasedUpperBound(irTypeParameter);
        IrClassSymbol symbol = erasedUpperBound.getSymbol();
        int size = erasedUpperBound.getTypeParameters().size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(IrStarProjectionImpl.INSTANCE);
        }
        return IrSimpleTypeImplKt.IrSimpleTypeImpl(symbol, z, arrayList, irTypeParameter.getAnnotations());
    }

    public static final IrClass getErasedUpperBound(IrType irType) {
        IrClass owner;
        irType.getClass();
        if (!(irType instanceof IrSimpleType)) {
            if (irType instanceof IrErrorType) {
                return ((IrErrorType) irType).getSymbol().getOwner();
            }
            dwe.a(RenderIrElementKt.render$default(irType, (DumpIrTreeOptions) null, 1, (Object) null));
            return null;
        }
        IrSimpleType irSimpleType = (IrSimpleType) irType;
        IrClass owner2 = irSimpleType.getClassifier().getOwner();
        if (owner2 instanceof IrClass) {
            return owner2;
        }
        if (owner2 instanceof IrTypeParameter) {
            return getErasedUpperBound((IrTypeParameter) owner2);
        }
        if (!(owner2 instanceof IrScript)) {
            dwe.a(RenderIrElementKt.render$default(irSimpleType, (DumpIrTreeOptions) null, 1, (Object) null));
            return null;
        }
        IrClassSymbol targetClass = ((IrScript) owner2).getTargetClass();
        if (targetClass != null && (owner = targetClass.getOwner()) != null) {
            return owner;
        }
        dwe.a(RenderIrElementKt.render$default(irSimpleType, (DumpIrTreeOptions) null, 1, (Object) null));
        return null;
    }

    public static final IrClass getErasedUpperBound(IrTypeParameter irTypeParameter) {
        IrClass owner;
        irTypeParameter.getClass();
        Iterator it = irTypeParameter.getSuperTypes().iterator();
        while (it.hasNext()) {
            IrClassSymbol classOrNull = IrTypesKt.getClassOrNull((IrType) it.next());
            if (classOrNull != null && (owner = classOrNull.getOwner()) != null && !IrUtilsKt.isInterface(owner) && !IrUtilsKt.isAnnotationClass(owner)) {
                return owner;
            }
        }
        return getErasedUpperBound((IrType) CollectionsKt.first(irTypeParameter.getSuperTypes()));
    }

    private static final IrTypeArgument eraseTypeParameters(IrTypeArgument irTypeArgument) {
        if (irTypeArgument instanceof IrStarProjection) {
            return irTypeArgument;
        }
        if (irTypeArgument instanceof IrTypeProjection) {
            IrTypeProjection irTypeProjection = (IrTypeProjection) irTypeArgument;
            return IrSimpleTypeImplKt.makeTypeProjection(eraseTypeParameters(irTypeProjection.getType()), irTypeProjection.getVariance());
        }
        bu8.a();
        return null;
    }
}
