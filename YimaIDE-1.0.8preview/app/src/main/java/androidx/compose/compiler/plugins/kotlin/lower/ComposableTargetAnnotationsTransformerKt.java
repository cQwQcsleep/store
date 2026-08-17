package androidx.compose.compiler.plugins.kotlin.lower;

import androidx.compose.compiler.plugins.kotlin.ComposeFqNames;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.declarations.IrParameterKind;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.expressions.IrCall;
import org.jetbrains.kotlin.ir.expressions.IrConst;
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrFunctionExpression;
import org.jetbrains.kotlin.ir.expressions.IrMemberAccessExpression;
import org.jetbrains.kotlin.ir.expressions.IrTypeOperatorCall;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.ir.types.IrSimpleType;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.types.IrTypePredicatesKt;
import org.jetbrains.kotlin.ir.types.IrTypesKt;
import org.jetbrains.kotlin.ir.util.AdditionalIrUtilsKt;
import org.jetbrains.kotlin.ir.util.IrTypeUtilsKt;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000^\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001c\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u001a\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t\u001a\u001c\u0010\n\u001a\u0004\u0018\u0001H\u000b\"\u0006\b\u0000\u0010\u000b\u0018\u0001*\u00020\fH\u0082\b¢\u0006\u0002\u0010\r\u001a\u000e\u0010\u0013\u001a\u0004\u0018\u00010\u0014*\u00020\u0015H\u0002\u001a&\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0017\"\u0004\b\u0000\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\"2\u0006\u0010#\u001a\u00020$H\u0002\u001a\f\u0010%\u001a\u00020\u0001*\u00020\u001eH\u0002\u001a\f\u0010&\u001a\u00020\u0001*\u00020\u001eH\u0002\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003\"\u0018\u0010\u000e\u001a\u00020\u0001*\u00020\f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f\"\u0018\u0010\u0010\u001a\u00020\u0001*\u00020\f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u000f\"\u0018\u0010\u0011\u001a\u00020\u0001*\u00020\f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000f\"\u0018\u0010\u0012\u001a\u00020\u0001*\u00020\f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u000f\" \u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00180\u0017*\u00020\u00198BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b\"\u001e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u0017*\u00020\u001e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 ¨\u0006'"}, d2 = {"isGenericFunction", "", "Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "(Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;)Z", "inferenceNodeOf", "Landroidx/compose/compiler/plugins/kotlin/lower/InferenceNode;", "element", "Lorg/jetbrains/kotlin/ir/IrElement;", "transformer", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableTargetAnnotationsTransformer;", "firstParameterOrNull", "T", "Lorg/jetbrains/kotlin/ir/expressions/IrConstructorCall;", "(Lorg/jetbrains/kotlin/ir/expressions/IrConstructorCall;)Ljava/lang/Object;", "isComposableTarget", "(Lorg/jetbrains/kotlin/ir/expressions/IrConstructorCall;)Z", "isComposableTargetMarked", "isComposableInferredTarget", "isComposableOpenTarget", "samOwnerOrNull", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "Lorg/jetbrains/kotlin/ir/types/IrType;", "targetArguments", "", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "getTargetArguments", "(Lorg/jetbrains/kotlin/ir/expressions/IrCall;)Ljava/util/List;", "targetParameters", "Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "getTargetParameters", "(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;)Ljava/util/List;", "takeUpTo", "", "n", "", "hasOverlyWideParameters", "hasOpenTypeParameters", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class ComposableTargetAnnotationsTransformerKt {
    private static final /* synthetic */ <T> T firstParameterOrNull(IrConstructorCall irConstructorCall) {
        Object objFirstOrNull = CollectionsKt.firstOrNull(irConstructorCall.getArguments());
        T t = null;
        IrConst irConst = objFirstOrNull instanceof IrConst ? (IrConst) objFirstOrNull : null;
        if (irConst != null) {
            t = (T) irConst.getValue();
        }
        Intrinsics.reifiedOperationMarker(2, "T");
        return t;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<IrExpression> getTargetArguments(IrCall irCall) {
        IrMemberAccessExpression.ValueArgumentsList arguments = irCall.getArguments();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Object obj : arguments) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            if (((IrValueParameter) irCall.getSymbol().getOwner().getParameters().get(i)).getKind() != IrParameterKind.DispatchReceiver) {
                arrayList.add(obj);
            }
            i = i2;
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<IrValueParameter> getTargetParameters(IrFunction irFunction) {
        List parameters = irFunction.getParameters();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Object obj : parameters) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            if (((IrValueParameter) irFunction.getSymbol().getOwner().getParameters().get(i)).getKind() != IrParameterKind.DispatchReceiver) {
                arrayList.add(obj);
            }
            i = i2;
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean hasOpenTypeParameters(IrFunction irFunction) {
        List parameters = irFunction.getParameters();
        if ((parameters instanceof Collection) && parameters.isEmpty()) {
            return false;
        }
        Iterator it = parameters.iterator();
        while (it.hasNext()) {
            if (IrTypeUtilsKt.isTypeParameter(((IrValueParameter) it.next()).getType())) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean hasOverlyWideParameters(IrFunction irFunction) {
        List<IrValueParameter> parameters = irFunction.getParameters();
        if ((parameters instanceof Collection) && parameters.isEmpty()) {
            return false;
        }
        for (IrValueParameter irValueParameter : parameters) {
            if (IrTypePredicatesKt.isAny(irValueParameter.getType()) || IrTypePredicatesKt.isNullableAny(irValueParameter.getType())) {
                return true;
            }
        }
        return false;
    }

    public static final InferenceNode inferenceNodeOf(IrElement irElement, ComposableTargetAnnotationsTransformer composableTargetAnnotationsTransformer) {
        irElement.getClass();
        composableTargetAnnotationsTransformer.getClass();
        if (irElement instanceof IrFunction) {
            return new InferenceFunctionDeclarationNode(composableTargetAnnotationsTransformer, (IrFunction) irElement);
        }
        if (irElement instanceof IrFunctionExpression) {
            return new InferenceFunctionExpressionNode(composableTargetAnnotationsTransformer, (IrFunctionExpression) irElement);
        }
        if (irElement instanceof IrTypeOperatorCall) {
            return inferenceNodeOf(((IrTypeOperatorCall) irElement).getArgument(), composableTargetAnnotationsTransformer);
        }
        if (irElement instanceof IrCall) {
            return new InferenceCallExpression(composableTargetAnnotationsTransformer, (IrCall) irElement);
        }
        return irElement instanceof IrExpression ? new InferenceElementExpression(composableTargetAnnotationsTransformer, (IrExpression) irElement) : new InferenceUnknownElement(irElement);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isComposableInferredTarget(IrConstructorCall irConstructorCall) {
        IrClassSymbol annotationClass = AbstractComposeLoweringKt.getAnnotationClass(irConstructorCall);
        return annotationClass != null && IrTypePredicatesKt.isClassWithFqName(annotationClass, ComposeFqNames.INSTANCE.getComposableInferredTarget().toUnsafe());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isComposableOpenTarget(IrConstructorCall irConstructorCall) {
        IrClassSymbol annotationClass = AbstractComposeLoweringKt.getAnnotationClass(irConstructorCall);
        return annotationClass != null && IrTypePredicatesKt.isClassWithFqName(annotationClass, ComposeFqNames.INSTANCE.getComposableOpenTarget().toUnsafe());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isComposableTarget(IrConstructorCall irConstructorCall) {
        IrClassSymbol annotationClass = AbstractComposeLoweringKt.getAnnotationClass(irConstructorCall);
        return annotationClass != null && IrTypePredicatesKt.isClassWithFqName(annotationClass, ComposeFqNames.INSTANCE.getComposableTarget().toUnsafe());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isComposableTargetMarked(IrConstructorCall irConstructorCall) {
        IrClass owner;
        List annotations;
        IrClassSymbol annotationClass = AbstractComposeLoweringKt.getAnnotationClass(irConstructorCall);
        return (annotationClass == null || (owner = annotationClass.getOwner()) == null || (annotations = owner.getAnnotations()) == null || !AdditionalIrUtilsKt.hasAnnotation(annotations, ComposeFqNames.INSTANCE.getComposableTargetMarker())) ? false : true;
    }

    public static final boolean isGenericFunction(IrSimpleFunctionSymbol irSimpleFunctionSymbol) {
        IrSimpleType type;
        irSimpleFunctionSymbol.getClass();
        if (!irSimpleFunctionSymbol.getOwner().getTypeParameters().isEmpty()) {
            return true;
        }
        IrValueParameter dispatchReceiverParameter = irSimpleFunctionSymbol.getOwner().getDispatchReceiverParameter();
        return (dispatchReceiverParameter == null || (type = dispatchReceiverParameter.getType()) == null || !(type instanceof IrSimpleType) || type.getArguments().isEmpty()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IrSimpleFunction samOwnerOrNull(IrType irType) {
        IrClassSymbol classOrNull = IrTypesKt.getClassOrNull(irType);
        if (classOrNull != null && IrUtilsKt.isInterface(classOrNull.getOwner()) && classOrNull.getOwner().isFun()) {
            Iterator it = IrUtilsKt.getFunctions(classOrNull).iterator();
            boolean z = false;
            Object obj = null;
            while (true) {
                if (!it.hasNext()) {
                    if (!z) {
                        break;
                    }
                    break;
                }
                Object next = it.next();
                if (((IrSimpleFunctionSymbol) next).getOwner().getModality() == Modality.ABSTRACT) {
                    if (!z) {
                        z = true;
                        obj = next;
                    }
                }
                obj = null;
                break;
            }
            IrSimpleFunctionSymbol irSimpleFunctionSymbol = (IrSimpleFunctionSymbol) obj;
            if (irSimpleFunctionSymbol != null) {
                return irSimpleFunctionSymbol.getOwner();
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> List<T> takeUpTo(Iterable<? extends T> iterable, int i) {
        return i <= 0 ? CollectionsKt.emptyList() : CollectionsKt.take(iterable, i);
    }
}
