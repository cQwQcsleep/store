package org.jetbrains.kotlin.codegen.inline;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.SpreadBuilder;
import kotlin.reflect.KVariance;
import org.jetbrains.kotlin.builtins.FunctionTypesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.AsmUtil;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.jvm.AsmTypes;
import org.jetbrains.kotlin.types.TypeSystemCommonBackendContext;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.types.model.RigidTypeMarker;
import org.jetbrains.kotlin.types.model.TypeArgumentMarker;
import org.jetbrains.kotlin.types.model.TypeParameterMarker;
import org.jetbrains.kotlin.types.model.TypeVariance;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.commons.InstructionAdapter;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000V\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001aD\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00022\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\n0\tH\u0082\b¢\u0006\u0002\u0010\u000b\u001a7\u0010\f\u001a\u00020\n\"\b\b\u0000\u0010\r*\u00020\u000e*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u0002H\r2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\r0\u0012¢\u0006\u0002\u0010\u0013\u001aA\u0010\f\u001a\u00020\n\"\b\b\u0000\u0010\r*\u00020\u000e*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u0002H\r2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\r0\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0002¢\u0006\u0002\u0010\u0016\u001a4\u0010\u0017\u001a\u00020\n\"\b\b\u0000\u0010\r*\u00020\u000e*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\r0\u0012H\u0002\u001a$\u0010\u001a\u001a\u00020\u0015*\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u000e2\u000e\b\u0002\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00190\u001cH\u0002\u001a<\u0010\u001d\u001a\u00020\n\"\b\b\u0000\u0010\r*\u00020\u000e*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u001f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\r0\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0002¨\u0006 "}, d2 = {"unrollArrayIfFewerThan", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/Type;", "Lorg/jetbrains/org/objectweb/asm/commons/InstructionAdapter;", "n", Argument.Delimiters.none, "limit", ModuleXmlParser.TYPE, "element", "Lkotlin/Function1;", Argument.Delimiters.none, "(Lorg/jetbrains/org/objectweb/asm/commons/InstructionAdapter;IILorg/jetbrains/org/objectweb/asm/Type;Lkotlin/jvm/functions/Function1;)[Lorg/jetbrains/org/objectweb/asm/Type;", "generateTypeOf", "KT", "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "Lorg/jetbrains/kotlin/types/TypeSystemCommonBackendContext;", "v", "intrinsicsSupport", "Lorg/jetbrains/kotlin/codegen/inline/ReifiedTypeInliner$IntrinsicsSupport;", "(Lorg/jetbrains/kotlin/types/TypeSystemCommonBackendContext;Lorg/jetbrains/org/objectweb/asm/commons/InstructionAdapter;Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;Lorg/jetbrains/kotlin/codegen/inline/ReifiedTypeInliner$IntrinsicsSupport;)V", "isTypeParameterBound", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/types/TypeSystemCommonBackendContext;Lorg/jetbrains/org/objectweb/asm/commons/InstructionAdapter;Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;Lorg/jetbrains/kotlin/codegen/inline/ReifiedTypeInliner$IntrinsicsSupport;Z)V", "generateNonReifiedTypeParameter", "typeParameter", "Lorg/jetbrains/kotlin/types/model/TypeParameterMarker;", "typeReferencesParameterWithRecursiveBound", "used", Argument.Delimiters.none, "generateTypeOfArgument", "projection", "Lorg/jetbrains/kotlin/types/model/TypeArgumentMarker;", "org.jetbrains.kotlin:backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TypeOfKt {

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TypeVariance.values().length];
            try {
                iArr[TypeVariance.INV.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TypeVariance.IN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[TypeVariance.OUT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private static final <KT extends KotlinTypeMarker> void generateNonReifiedTypeParameter(TypeSystemCommonBackendContext typeSystemCommonBackendContext, InstructionAdapter instructionAdapter, TypeParameterMarker typeParameterMarker, ReifiedTypeInliner.IntrinsicsSupport<KT> intrinsicsSupport) {
        KVariance kVariance;
        Type[] typeArr;
        intrinsicsSupport.generateTypeParameterContainer(instructionAdapter, typeParameterMarker);
        instructionAdapter.aconst(typeSystemCommonBackendContext.getName(typeParameterMarker).asString());
        int i = WhenMappings.$EnumSwitchMapping$0[typeSystemCommonBackendContext.getVariance(typeParameterMarker).ordinal()];
        if (i == 1) {
            kVariance = KVariance.INVARIANT;
        } else if (i == 2) {
            kVariance = KVariance.IN;
        } else {
            if (i != 3) {
                bu8.a();
                return;
            }
            kVariance = KVariance.OUT;
        }
        Type type = AsmTypes.K_VARIANCE;
        instructionAdapter.getstatic(type.getInternalName(), kVariance.name(), type.getDescriptor());
        instructionAdapter.iconst(typeSystemCommonBackendContext.isReified(typeParameterMarker) ? 1 : 0);
        instructionAdapter.invokestatic("kotlin/jvm/internal/Reflection", "typeParameter", Type.getMethodDescriptor(AsmTypes.K_TYPE_PARAMETER, new Type[]{AsmTypes.OBJECT_TYPE, AsmTypes.JAVA_STRING_TYPE, type, Type.BOOLEAN_TYPE}), false);
        if (typeSystemCommonBackendContext.upperBoundCount(typeParameterMarker) == 0) {
            return;
        }
        instructionAdapter.dup();
        int iUpperBoundCount = typeSystemCommonBackendContext.upperBoundCount(typeParameterMarker);
        Type type2 = AsmTypes.K_TYPE;
        type2.getClass();
        if (iUpperBoundCount < 2) {
            typeArr = new Type[iUpperBoundCount];
            for (int i2 = 0; i2 < iUpperBoundCount; i2++) {
                KotlinTypeMarker upperBound = typeSystemCommonBackendContext.getUpperBound(typeParameterMarker, i2);
                upperBound.getClass();
                generateTypeOf(typeSystemCommonBackendContext, instructionAdapter, upperBound, intrinsicsSupport, true);
                Unit unit = Unit.INSTANCE;
                typeArr[i2] = type2;
            }
        } else {
            instructionAdapter.iconst(iUpperBoundCount);
            instructionAdapter.newarray(type2);
            for (int i3 = 0; i3 < iUpperBoundCount; i3++) {
                instructionAdapter.dup();
                instructionAdapter.iconst(i3);
                KotlinTypeMarker upperBound2 = typeSystemCommonBackendContext.getUpperBound(typeParameterMarker, i3);
                upperBound2.getClass();
                generateTypeOf(typeSystemCommonBackendContext, instructionAdapter, upperBound2, intrinsicsSupport, true);
                instructionAdapter.astore(type2);
            }
            Type arrayType = AsmUtil.getArrayType(type2);
            arrayType.getClass();
            typeArr = new Type[]{arrayType};
        }
        Type type3 = Type.VOID_TYPE;
        SpreadBuilder spreadBuilder = new SpreadBuilder(2);
        spreadBuilder.add(AsmTypes.K_TYPE_PARAMETER);
        spreadBuilder.addSpread(typeArr);
        instructionAdapter.invokestatic("kotlin/jvm/internal/Reflection", "setUpperBounds", Type.getMethodDescriptor(type3, (Type[]) spreadBuilder.toArray(new Type[spreadBuilder.size()])), false);
    }

    private static final <KT extends KotlinTypeMarker> void generateTypeOf(TypeSystemCommonBackendContext typeSystemCommonBackendContext, InstructionAdapter instructionAdapter, KT kt, ReifiedTypeInliner.IntrinsicsSupport<KT> intrinsicsSupport, boolean z) {
        Type[] typeArr;
        Type[] typeArr2;
        TypeParameterMarker typeParameterClassifier = typeSystemCommonBackendContext.getTypeParameterClassifier(typeSystemCommonBackendContext.typeConstructor(kt));
        if (typeParameterClassifier == null) {
            intrinsicsSupport.putClassInstance(instructionAdapter, kt);
            int iArgumentsCount = typeSystemCommonBackendContext.argumentsCount(kt);
            Type type = AsmTypes.K_TYPE_PROJECTION;
            type.getClass();
            if (iArgumentsCount < 3) {
                typeArr2 = new Type[iArgumentsCount];
                for (int i = 0; i < iArgumentsCount; i++) {
                    generateTypeOfArgument(typeSystemCommonBackendContext, instructionAdapter, typeSystemCommonBackendContext.getArgument(kt, i), intrinsicsSupport, z);
                    Unit unit = Unit.INSTANCE;
                    typeArr2[i] = type;
                }
            } else {
                instructionAdapter.iconst(iArgumentsCount);
                instructionAdapter.newarray(type);
                for (int i2 = 0; i2 < iArgumentsCount; i2++) {
                    instructionAdapter.dup();
                    instructionAdapter.iconst(i2);
                    generateTypeOfArgument(typeSystemCommonBackendContext, instructionAdapter, typeSystemCommonBackendContext.getArgument(kt, i2), intrinsicsSupport, z);
                    instructionAdapter.astore(type);
                }
                Type arrayType = AsmUtil.getArrayType(type);
                arrayType.getClass();
                typeArr2 = new Type[]{arrayType};
            }
            SpreadBuilder spreadBuilder = new SpreadBuilder(2);
            spreadBuilder.add(AsmTypes.JAVA_CLASS_TYPE);
            spreadBuilder.addSpread(typeArr2);
            typeArr = (Type[]) spreadBuilder.toArray(new Type[spreadBuilder.size()]);
        } else {
            if (!z && typeSystemCommonBackendContext.isReified(typeParameterClassifier)) {
                String strAsString = typeSystemCommonBackendContext.getName(typeParameterClassifier).asString();
                strAsString.getClass();
                ReifiedTypeInliner.INSTANCE.putReifiedOperationMarker(ReifiedTypeInliner.OperationKind.TYPE_OF, new ReificationArgument(strAsString, typeSystemCommonBackendContext.isMarkedNullable(kt), 0), instructionAdapter);
                instructionAdapter.aconst((Object) null);
                return;
            }
            if (typeReferencesParameterWithRecursiveBound$default(typeSystemCommonBackendContext, kt, null, 2, null)) {
                intrinsicsSupport.reportNonReifiedTypeParameterWithRecursiveBoundUnsupported(typeSystemCommonBackendContext.getName(typeParameterClassifier));
                instructionAdapter.aconst((Object) null);
                return;
            } else {
                generateNonReifiedTypeParameter(typeSystemCommonBackendContext, instructionAdapter, typeParameterClassifier, intrinsicsSupport);
                typeArr = new Type[]{AsmTypes.K_CLASSIFIER_TYPE};
            }
        }
        String str = typeSystemCommonBackendContext.isMarkedNullable(kt) ? "nullableTypeOf" : "typeOf";
        Type type2 = AsmTypes.K_TYPE;
        instructionAdapter.invokestatic("kotlin/jvm/internal/Reflection", str, Type.getMethodDescriptor(type2, (Type[]) Arrays.copyOf(typeArr, typeArr.length)), false);
        if (FunctionTypesKt.isSuspendFunctionType(intrinsicsSupport.toKotlinType(kt))) {
            intrinsicsSupport.reportSuspendTypeUnsupported();
        }
        if (intrinsicsSupport.getConfig().getStableTypeOf()) {
            if (intrinsicsSupport.isMutableCollectionType(kt)) {
                instructionAdapter.invokestatic("kotlin/jvm/internal/Reflection", "mutableCollectionType", Type.getMethodDescriptor(type2, new Type[]{type2}), false);
            } else if (typeSystemCommonBackendContext.isNothingConstructor(typeSystemCommonBackendContext.typeConstructor(kt))) {
                instructionAdapter.invokestatic("kotlin/jvm/internal/Reflection", "nothingType", Type.getMethodDescriptor(type2, new Type[]{type2}), false);
            }
            if (typeSystemCommonBackendContext.isFlexible(kt)) {
                RigidTypeMarker rigidTypeMarkerUpperBoundIfFlexible = typeSystemCommonBackendContext.upperBoundIfFlexible(kt);
                rigidTypeMarkerUpperBoundIfFlexible.getClass();
                generateTypeOf(typeSystemCommonBackendContext, instructionAdapter, rigidTypeMarkerUpperBoundIfFlexible, intrinsicsSupport, z);
                instructionAdapter.invokestatic("kotlin/jvm/internal/Reflection", "platformType", Type.getMethodDescriptor(type2, new Type[]{type2, type2}), false);
            }
        }
    }

    private static final <KT extends KotlinTypeMarker> void generateTypeOfArgument(TypeSystemCommonBackendContext typeSystemCommonBackendContext, InstructionAdapter instructionAdapter, TypeArgumentMarker typeArgumentMarker, ReifiedTypeInliner.IntrinsicsSupport<KT> intrinsicsSupport, boolean z) {
        String str;
        Type type = AsmTypes.K_TYPE_PROJECTION;
        String internalName = type.getInternalName();
        Type type2 = AsmTypes.K_TYPE_PROJECTION_COMPANION;
        instructionAdapter.getstatic(internalName, "Companion", type2.getDescriptor());
        if (typeSystemCommonBackendContext.isStarProjection(typeArgumentMarker)) {
            instructionAdapter.invokevirtual(type2.getInternalName(), "getSTAR", Type.getMethodDescriptor(type, new Type[0]), false);
            return;
        }
        KotlinTypeMarker type3 = typeSystemCommonBackendContext.getType(typeArgumentMarker);
        type3.getClass();
        generateTypeOf(typeSystemCommonBackendContext, instructionAdapter, type3, intrinsicsSupport, z);
        int i = WhenMappings.$EnumSwitchMapping$0[typeSystemCommonBackendContext.getVariance(typeArgumentMarker).ordinal()];
        if (i == 1) {
            str = "invariant";
        } else if (i == 2) {
            str = "contravariant";
        } else {
            if (i != 3) {
                bu8.a();
                return;
            }
            str = "covariant";
        }
        instructionAdapter.invokevirtual(type2.getInternalName(), str, Type.getMethodDescriptor(type, new Type[]{AsmTypes.K_TYPE}), false);
    }

    private static final boolean typeReferencesParameterWithRecursiveBound(TypeSystemCommonBackendContext typeSystemCommonBackendContext, KotlinTypeMarker kotlinTypeMarker, Set<TypeParameterMarker> set) {
        TypeParameterMarker typeParameterClassifier = typeSystemCommonBackendContext.getTypeParameterClassifier(typeSystemCommonBackendContext.typeConstructor(kotlinTypeMarker));
        if (typeParameterClassifier == null) {
            int iArgumentsCount = typeSystemCommonBackendContext.argumentsCount(kotlinTypeMarker);
            for (int i = 0; i < iArgumentsCount; i++) {
                KotlinTypeMarker type = typeSystemCommonBackendContext.getType(typeSystemCommonBackendContext.getArgument(kotlinTypeMarker, i));
                if (type != null && typeReferencesParameterWithRecursiveBound(typeSystemCommonBackendContext, type, set)) {
                    return true;
                }
            }
        } else {
            if (!set.add(typeParameterClassifier)) {
                return true;
            }
            int iUpperBoundCount = typeSystemCommonBackendContext.upperBoundCount(typeParameterClassifier);
            for (int i2 = 0; i2 < iUpperBoundCount; i2++) {
                if (typeReferencesParameterWithRecursiveBound(typeSystemCommonBackendContext, typeSystemCommonBackendContext.getUpperBound(typeParameterClassifier, i2), set)) {
                    return true;
                }
            }
            set.remove(typeParameterClassifier);
        }
        return false;
    }

    public static /* synthetic */ boolean typeReferencesParameterWithRecursiveBound$default(TypeSystemCommonBackendContext typeSystemCommonBackendContext, KotlinTypeMarker kotlinTypeMarker, Set set, int i, Object obj) {
        if ((i & 2) != 0) {
            set = new LinkedHashSet();
        }
        return typeReferencesParameterWithRecursiveBound(typeSystemCommonBackendContext, kotlinTypeMarker, set);
    }

    public static final <KT extends KotlinTypeMarker> void generateTypeOf(TypeSystemCommonBackendContext typeSystemCommonBackendContext, InstructionAdapter instructionAdapter, KT kt, ReifiedTypeInliner.IntrinsicsSupport<KT> intrinsicsSupport) {
        typeSystemCommonBackendContext.getClass();
        instructionAdapter.getClass();
        kt.getClass();
        intrinsicsSupport.getClass();
        generateTypeOf(typeSystemCommonBackendContext, instructionAdapter, kt, intrinsicsSupport, false);
    }
}
