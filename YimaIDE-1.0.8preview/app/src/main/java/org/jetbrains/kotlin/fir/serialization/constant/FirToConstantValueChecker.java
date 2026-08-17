package org.jetbrains.kotlin.fir.serialization.constant;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirCollectionLiteral;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirGetClassCall;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.FirNamedArgumentExpression;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirStringConcatenationCall;
import org.jetbrains.kotlin.fir.expressions.FirVarargArgumentsExpression;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.expressions.impl.FirExpressionStub;
import org.jetbrains.kotlin.fir.resolve.ResolveUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.types.ConstantValueKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010\rJ\u001d\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010\u0011J\u001d\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\f\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010\u0015J\u001d\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\f\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010\u0019J\u001d\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\f\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010\u001dJ\u001d\u0010\u001e\u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\f\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010!J\u001d\u0010\"\u001a\u00020\u00022\u0006\u0010#\u001a\u00020$2\u0006\u0010\f\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010%J\u001d\u0010&\u001a\u00020\u00022\u0006\u0010'\u001a\u00020(2\u0006\u0010\f\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010)J\u001d\u0010*\u001a\u00020\u00022\u0006\u0010+\u001a\u00020,2\u0006\u0010\f\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010-J\u001d\u0010.\u001a\u00020\u00022\u0006\u0010/\u001a\u0002002\u0006\u0010\f\u001a\u00020\u0003H\u0016¢\u0006\u0002\u00101J\u001d\u00102\u001a\u00020\u00022\u0006\u00103\u001a\u0002042\u0006\u0010\f\u001a\u00020\u0003H\u0016¢\u0006\u0002\u00105J\u001d\u00106\u001a\u00020\u00022\u0006\u00107\u001a\u0002082\u0006\u0010\f\u001a\u00020\u0003H\u0016¢\u0006\u0002\u00109J\u001d\u0010:\u001a\u00020\u00022\u0006\u0010;\u001a\u00020<2\u0006\u0010\f\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010=R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006>"}, d2 = {"Lorg/jetbrains/kotlin/fir/serialization/constant/FirToConstantValueChecker;", "Lorg/jetbrains/kotlin/fir/visitors/FirDefaultVisitor;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "()V", "supportedConstKinds", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/types/ConstantValueKind;", "visitElement", "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "data", "(Lorg/jetbrains/kotlin/fir/FirElement;Lorg/jetbrains/kotlin/fir/FirSession;)Ljava/lang/Boolean;", "visitLiteralExpression", "literalExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirLiteralExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirLiteralExpression;Lorg/jetbrains/kotlin/fir/FirSession;)Ljava/lang/Boolean;", "visitStringConcatenationCall", "stringConcatenationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirStringConcatenationCall;", "(Lorg/jetbrains/kotlin/fir/expressions/FirStringConcatenationCall;Lorg/jetbrains/kotlin/fir/FirSession;)Ljava/lang/Boolean;", "visitCollectionLiteral", "collectionLiteral", "Lorg/jetbrains/kotlin/fir/expressions/FirCollectionLiteral;", "(Lorg/jetbrains/kotlin/fir/expressions/FirCollectionLiteral;Lorg/jetbrains/kotlin/fir/FirSession;)Ljava/lang/Boolean;", "visitAnnotation", "annotation", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "(Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;Lorg/jetbrains/kotlin/fir/FirSession;)Ljava/lang/Boolean;", "visitAnnotationCall", "annotationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;", "(Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;Lorg/jetbrains/kotlin/fir/FirSession;)Ljava/lang/Boolean;", "visitGetClassCall", "getClassCall", "Lorg/jetbrains/kotlin/fir/expressions/FirGetClassCall;", "(Lorg/jetbrains/kotlin/fir/expressions/FirGetClassCall;Lorg/jetbrains/kotlin/fir/FirSession;)Ljava/lang/Boolean;", "visitQualifiedAccessExpression", "qualifiedAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;Lorg/jetbrains/kotlin/fir/FirSession;)Ljava/lang/Boolean;", "visitPropertyAccessExpression", "propertyAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;Lorg/jetbrains/kotlin/fir/FirSession;)Ljava/lang/Boolean;", "visitFunctionCall", "functionCall", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "(Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;Lorg/jetbrains/kotlin/fir/FirSession;)Ljava/lang/Boolean;", "visitVarargArgumentsExpression", "varargArgumentsExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirVarargArgumentsExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirVarargArgumentsExpression;Lorg/jetbrains/kotlin/fir/FirSession;)Ljava/lang/Boolean;", "visitNamedArgumentExpression", "namedArgumentExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirNamedArgumentExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirNamedArgumentExpression;Lorg/jetbrains/kotlin/fir/FirSession;)Ljava/lang/Boolean;", "visitExpression", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/FirSession;)Ljava/lang/Boolean;", "org.jetbrains.kotlin:fir-serialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class FirToConstantValueChecker extends FirDefaultVisitor<Boolean, FirSession> {
    public static final FirToConstantValueChecker INSTANCE = new FirToConstantValueChecker();
    private static final Set<ConstantValueKind> supportedConstKinds = SetsKt.setOf(new ConstantValueKind[]{ConstantValueKind.Boolean.INSTANCE, ConstantValueKind.Char.INSTANCE, ConstantValueKind.String.INSTANCE, ConstantValueKind.Float.INSTANCE, ConstantValueKind.Double.INSTANCE, ConstantValueKind.Byte.INSTANCE, ConstantValueKind.UnsignedByte.INSTANCE, ConstantValueKind.Short.INSTANCE, ConstantValueKind.UnsignedShort.INSTANCE, ConstantValueKind.Int.INSTANCE, ConstantValueKind.UnsignedInt.INSTANCE, ConstantValueKind.Long.INSTANCE, ConstantValueKind.UnsignedLong.INSTANCE});

    private FirToConstantValueChecker() {
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public Boolean visitAnnotation(FirAnnotation annotation, FirSession data) {
        annotation.getClass();
        data.getClass();
        return Boolean.TRUE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public Boolean visitAnnotationCall(FirAnnotationCall annotationCall, FirSession data) {
        annotationCall.getClass();
        data.getClass();
        return Boolean.TRUE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public Boolean visitCollectionLiteral(FirCollectionLiteral collectionLiteral, FirSession data) {
        collectionLiteral.getClass();
        data.getClass();
        List<FirExpression> arguments = collectionLiteral.getArgumentList().getArguments();
        boolean z = true;
        if (!(arguments instanceof Collection) || !arguments.isEmpty()) {
            Iterator<T> it = arguments.iterator();
            while (it.hasNext()) {
                if (!((Boolean) ((FirExpression) it.next()).accept(INSTANCE, data)).booleanValue()) {
                    z = false;
                    break;
                }
            }
        }
        return Boolean.valueOf(z);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public Boolean visitElement(FirElement element, FirSession data) {
        element.getClass();
        data.getClass();
        return Boolean.FALSE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public Boolean visitExpression(FirExpression expression, FirSession data) {
        expression.getClass();
        data.getClass();
        return Boolean.valueOf(expression instanceof FirExpressionStub);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public Boolean visitFunctionCall(FirFunctionCall functionCall, FirSession data) {
        functionCall.getClass();
        data.getClass();
        if (!ResolveUtilsKt.isArrayOfCall(functionCall, data)) {
            return visitQualifiedAccessExpression((FirQualifiedAccessExpression) functionCall, data);
        }
        List<FirExpression> arguments = functionCall.getArgumentList().getArguments();
        boolean z = true;
        if (!(arguments instanceof Collection) || !arguments.isEmpty()) {
            Iterator<T> it = arguments.iterator();
            while (it.hasNext()) {
                if (!((Boolean) ((FirExpression) it.next()).accept(INSTANCE, data)).booleanValue()) {
                    z = false;
                    break;
                }
            }
        }
        return Boolean.valueOf(z);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public Boolean visitGetClassCall(FirGetClassCall getClassCall, FirSession data) {
        getClassCall.getClass();
        data.getClass();
        return Boolean.valueOf(ConstantValueUtilsKt.create(FirTypeUtilsKt.getResolvedType(getClassCall.getArgument()), data) != null);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public Boolean visitLiteralExpression(FirLiteralExpression literalExpression, FirSession data) {
        literalExpression.getClass();
        data.getClass();
        return Boolean.valueOf(supportedConstKinds.contains(literalExpression.getKind()));
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public Boolean visitNamedArgumentExpression(FirNamedArgumentExpression namedArgumentExpression, FirSession data) {
        namedArgumentExpression.getClass();
        data.getClass();
        return (Boolean) namedArgumentExpression.getExpression().accept(this, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public Boolean visitPropertyAccessExpression(FirPropertyAccessExpression propertyAccessExpression, FirSession data) {
        propertyAccessExpression.getClass();
        data.getClass();
        return visitQualifiedAccessExpression((FirQualifiedAccessExpression) propertyAccessExpression, data);
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0028  */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public Boolean visitQualifiedAccessExpression(FirQualifiedAccessExpression qualifiedAccessExpression, FirSession data) {
        FqName packageName;
        FirRegularClassSymbol regularClassSymbol;
        qualifiedAccessExpression.getClass();
        data.getClass();
        FirCallableSymbol<?> resolvedCallableSymbol = ReferenceUtilsKt.toResolvedCallableSymbol(qualifiedAccessExpression);
        if (resolvedCallableSymbol == null) {
            return Boolean.FALSE;
        }
        boolean zBooleanValue = true;
        ClassId classIdAsString = null;
        if (resolvedCallableSymbol.getFir() instanceof FirEnumEntry) {
            CallableId callableId = resolvedCallableSymbol.getCallableId();
            if ((callableId != null ? callableId.getClassId() : null) == null) {
                zBooleanValue = false;
            }
        } else if (resolvedCallableSymbol instanceof FirPropertySymbol) {
            zBooleanValue = ((FirMemberDeclaration) ((FirPropertySymbol) resolvedCallableSymbol).getFir()).getStatus().isConst();
        } else if (resolvedCallableSymbol instanceof FirFieldSymbol) {
            zBooleanValue = ((FirField) ((FirFieldSymbol) resolvedCallableSymbol).getFir()).getIsVal();
        } else if (resolvedCallableSymbol instanceof FirConstructorSymbol) {
            ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(resolvedCallableSymbol);
            if (coneClassLikeLookupTagContainingClassLookupTag != null && (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(coneClassLikeLookupTagContainingClassLookupTag, data)) != null) {
                classIdAsString = regularClassSymbol.getClassKind();
            }
            if (classIdAsString != ClassKind.ANNOTATION_CLASS) {
                zBooleanValue = false;
            }
        } else {
            CallableId callableId2 = resolvedCallableSymbol.getCallableId();
            if (callableId2 != null && (packageName = callableId2.getPackageName()) != null) {
                classIdAsString = packageName.asString();
            }
            if (Intrinsics.areEqual(classIdAsString, "kotlin")) {
                FirExpression dispatchReceiver = qualifiedAccessExpression.getDispatchReceiver();
                if (FirToConstantValueTransformerKt.constantIntrinsicCalls.contains(resolvedCallableSymbol.getName()) && dispatchReceiver != null) {
                    zBooleanValue = ((Boolean) dispatchReceiver.accept(this, data)).booleanValue();
                } else {
                    zBooleanValue = false;
                }
            } else {
                zBooleanValue = false;
            }
        }
        return Boolean.valueOf(zBooleanValue);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public Boolean visitStringConcatenationCall(FirStringConcatenationCall stringConcatenationCall, FirSession data) {
        stringConcatenationCall.getClass();
        data.getClass();
        List<FirExpression> arguments = stringConcatenationCall.getArgumentList().getArguments();
        boolean z = true;
        if (!(arguments instanceof Collection) || !arguments.isEmpty()) {
            Iterator<T> it = arguments.iterator();
            while (it.hasNext()) {
                if (!((Boolean) ((FirExpression) it.next()).accept(INSTANCE, data)).booleanValue()) {
                    z = false;
                    break;
                }
            }
        }
        return Boolean.valueOf(z);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public Boolean visitVarargArgumentsExpression(FirVarargArgumentsExpression varargArgumentsExpression, FirSession data) {
        varargArgumentsExpression.getClass();
        data.getClass();
        List<FirExpression> arguments = varargArgumentsExpression.getArguments();
        boolean z = true;
        if (!(arguments instanceof Collection) || !arguments.isEmpty()) {
            Iterator<T> it = arguments.iterator();
            while (it.hasNext()) {
                if (!((Boolean) ((FirExpression) it.next()).accept(INSTANCE, data)).booleanValue()) {
                    z = false;
                    break;
                }
            }
        }
        return Boolean.valueOf(z);
    }
}
