package org.jetbrains.kotlin.codegen;

import java.util.function.Consumer;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.StackValue;
import org.jetbrains.kotlin.codegen.state.KotlinTypeMapper;
import org.jetbrains.kotlin.codegen.state.KotlinTypeMapperBase;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.load.kotlin.TypeMappingMode;
import org.jetbrains.kotlin.resolve.jvm.AsmTypes;
import org.jetbrains.kotlin.types.TypeSystemCommonBackendContext;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.org.objectweb.asm.Label;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.commons.InstructionAdapter;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public abstract class StackValue {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public final KotlinTypeMarker kotlinType;
    public final Type type;

    public static class Constant extends StackValue {
        public final Object value;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i == 1) {
                objArr[0] = "v";
            } else if (i != 2) {
                objArr[0] = ModuleXmlParser.TYPE;
            } else {
                objArr[0] = "typeMapper";
            }
            objArr[1] = "org/jetbrains/kotlin/codegen/StackValue$Constant";
            objArr[2] = "put";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        public Constant(Object obj, Type type) {
            super(type, null);
            this.value = obj;
        }

        @Override // org.jetbrains.kotlin.codegen.StackValue
        public void put(Type type, KotlinTypeMarker kotlinTypeMarker, InstructionAdapter instructionAdapter, KotlinTypeMapperBase kotlinTypeMapperBase) {
            if (type == null) {
                $$$reportNull$$$0(0);
            }
            if (instructionAdapter == null) {
                $$$reportNull$$$0(1);
            }
            if (kotlinTypeMapperBase == null) {
                $$$reportNull$$$0(2);
            }
            Object obj = this.value;
            if ((obj instanceof Integer) || (obj instanceof Byte) || (obj instanceof Short)) {
                instructionAdapter.iconst(((Number) obj).intValue());
            } else if (obj instanceof Character) {
                instructionAdapter.iconst(((Character) obj).charValue());
            } else if (obj instanceof Long) {
                instructionAdapter.lconst(((Long) obj).longValue());
            } else if (obj instanceof Float) {
                instructionAdapter.fconst(((Float) obj).floatValue());
            } else if (obj instanceof Double) {
                instructionAdapter.dconst(((Double) obj).doubleValue());
            } else {
                instructionAdapter.aconst(obj);
            }
            if (this.value != null || AsmUtil.isPrimitive(type)) {
                StackValue.coerce(this.type, this.kotlinType, type, kotlinTypeMarker, instructionAdapter, kotlinTypeMapperBase);
            }
        }
    }

    public static class Field extends StackValue {
        public final String name;
        public final Type owner;
        public final StackValue receiver;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            switch (i) {
                case 1:
                    objArr[0] = "owner";
                    break;
                case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
                    objArr[0] = ModuleXmlParser.NAME;
                    break;
                case 3:
                    objArr[0] = "receiver";
                    break;
                case 4:
                default:
                    objArr[0] = ModuleXmlParser.TYPE;
                    break;
                case 5:
                case 8:
                    objArr[0] = "v";
                    break;
                case 6:
                case 9:
                    objArr[0] = "typeMapper";
                    break;
                case 7:
                    objArr[0] = "topOfStackType";
                    break;
            }
            objArr[1] = "org/jetbrains/kotlin/codegen/StackValue$Field";
            switch (i) {
                case 4:
                case 5:
                case 6:
                    objArr[2] = "put";
                    break;
                case 7:
                case 8:
                case 9:
                    objArr[2] = "store";
                    break;
                default:
                    objArr[2] = "<init>";
                    break;
            }
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Field(Type type, Type type2, String str, StackValue stackValue) {
            super(type, null);
            if (type == null) {
                $$$reportNull$$$0(0);
            }
            if (type2 == null) {
                $$$reportNull$$$0(1);
            }
            if (str == null) {
                $$$reportNull$$$0(2);
            }
            if (stackValue == null) {
                $$$reportNull$$$0(3);
            }
            this.owner = type2;
            this.name = str;
            this.receiver = stackValue;
        }

        @Override // org.jetbrains.kotlin.codegen.StackValue
        public void put(Type type, KotlinTypeMarker kotlinTypeMarker, InstructionAdapter instructionAdapter, KotlinTypeMapperBase kotlinTypeMapperBase) {
            if (type == null) {
                $$$reportNull$$$0(4);
            }
            if (instructionAdapter == null) {
                $$$reportNull$$$0(5);
            }
            if (kotlinTypeMapperBase == null) {
                $$$reportNull$$$0(6);
            }
            StackValue stackValue = this.receiver;
            stackValue.put(stackValue.type, stackValue.kotlinType, instructionAdapter, kotlinTypeMapperBase);
            instructionAdapter.visitFieldInsn(180, this.owner.getInternalName(), this.name, this.type.getDescriptor());
            StackValue.coerce(this.type, this.kotlinType, type, kotlinTypeMarker, instructionAdapter, kotlinTypeMapperBase);
        }

        @Override // org.jetbrains.kotlin.codegen.StackValue
        public void store(Type type, KotlinTypeMarker kotlinTypeMarker, InstructionAdapter instructionAdapter, KotlinTypeMapperBase kotlinTypeMapperBase) {
            if (type == null) {
                $$$reportNull$$$0(7);
            }
            if (instructionAdapter == null) {
                $$$reportNull$$$0(8);
            }
            if (kotlinTypeMapperBase == null) {
                $$$reportNull$$$0(9);
            }
            StackValue stackValue = this.receiver;
            stackValue.put(stackValue.type, stackValue.kotlinType, instructionAdapter, kotlinTypeMapperBase);
            StackValue.coerce(type, kotlinTypeMarker, this.type, this.kotlinType, instructionAdapter, kotlinTypeMapperBase);
            instructionAdapter.visitFieldInsn(181, this.owner.getInternalName(), this.name, this.type.getDescriptor());
        }
    }

    public static class Local extends StackValue {
        public final int index;

        /* JADX WARN: Code duplicated, block: B:10:0x001c  */
        /* JADX WARN: Code duplicated, block: B:11:0x0021  */
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i == 1) {
                objArr[0] = "v";
            } else if (i == 2) {
                objArr[0] = "typeMapper";
            } else if (i == 3) {
                objArr[0] = "topOfStackType";
            } else if (i == 4) {
                objArr[0] = "v";
            } else if (i != 5) {
                objArr[0] = ModuleXmlParser.TYPE;
            } else {
                objArr[0] = "typeMapper";
            }
            objArr[1] = "org/jetbrains/kotlin/codegen/StackValue$Local";
            if (i == 3 || i == 4 || i == 5) {
                objArr[2] = "store";
            } else {
                objArr[2] = "put";
            }
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        public Local(int i, Type type, KotlinTypeMarker kotlinTypeMarker) {
            super(type, kotlinTypeMarker);
            if (i >= 0) {
                this.index = i;
            } else {
                k2d.a("local variable index must be non-negative");
                throw null;
            }
        }

        @Override // org.jetbrains.kotlin.codegen.StackValue
        public void put(Type type, KotlinTypeMarker kotlinTypeMarker, InstructionAdapter instructionAdapter, KotlinTypeMapperBase kotlinTypeMapperBase) {
            if (type == null) {
                $$$reportNull$$$0(0);
            }
            if (instructionAdapter == null) {
                $$$reportNull$$$0(1);
            }
            if (kotlinTypeMapperBase == null) {
                $$$reportNull$$$0(2);
            }
            instructionAdapter.load(this.index, this.type);
            StackValue.coerce(this.type, this.kotlinType, type, kotlinTypeMarker, instructionAdapter, kotlinTypeMapperBase);
        }

        @Override // org.jetbrains.kotlin.codegen.StackValue
        public void store(Type type, KotlinTypeMarker kotlinTypeMarker, InstructionAdapter instructionAdapter, KotlinTypeMapperBase kotlinTypeMapperBase) {
            if (type == null) {
                $$$reportNull$$$0(3);
            }
            if (instructionAdapter == null) {
                $$$reportNull$$$0(4);
            }
            if (kotlinTypeMapperBase == null) {
                $$$reportNull$$$0(5);
            }
            StackValue.coerce(type, kotlinTypeMarker, this.type, this.kotlinType, instructionAdapter, kotlinTypeMapperBase);
            instructionAdapter.store(this.index, this.type);
        }
    }

    public static class OnStack extends StackValue {
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i == 1) {
                objArr[0] = "v";
            } else if (i != 2) {
                objArr[0] = ModuleXmlParser.TYPE;
            } else {
                objArr[0] = "typeMapper";
            }
            objArr[1] = "org/jetbrains/kotlin/codegen/StackValue$OnStack";
            objArr[2] = "put";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        public OnStack(Type type, KotlinTypeMarker kotlinTypeMarker) {
            super(type, kotlinTypeMarker);
            if (type != Type.VOID_TYPE) {
                return;
            }
            w01.a("Cannot create OnStack with void type");
            throw null;
        }

        @Override // org.jetbrains.kotlin.codegen.StackValue
        public void put(Type type, KotlinTypeMarker kotlinTypeMarker, InstructionAdapter instructionAdapter, KotlinTypeMapperBase kotlinTypeMapperBase) {
            if (type == null) {
                $$$reportNull$$$0(0);
            }
            if (instructionAdapter == null) {
                $$$reportNull$$$0(1);
            }
            if (kotlinTypeMapperBase == null) {
                $$$reportNull$$$0(2);
            }
            StackValue.coerce(this.type, this.kotlinType, type, kotlinTypeMarker, instructionAdapter, kotlinTypeMapperBase);
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        switch (i) {
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
            case 6:
            case 10:
            case 11:
            case 16:
            case 21:
            case 22:
            case 25:
            case 29:
            case 36:
            case 43:
            case 46:
            case 47:
                objArr[0] = "v";
                break;
            case 3:
            case 7:
            case 17:
            case 30:
            case 33:
            case 37:
            case 40:
                objArr[0] = "typeMapper";
                break;
            case 4:
            case 14:
            case 18:
            default:
                objArr[0] = ModuleXmlParser.TYPE;
                break;
            case 5:
            case 38:
                objArr[0] = "kotlinType";
                break;
            case 8:
            case 20:
                objArr[0] = "unboxed";
                break;
            case 9:
            case 19:
                objArr[0] = "boxed";
                break;
            case 12:
                objArr[0] = "boxedType";
                break;
            case 13:
                objArr[0] = "underlyingType";
                break;
            case 15:
                objArr[0] = "targetInlineClassType";
                break;
            case 23:
                objArr[0] = "owner";
                break;
            case 24:
                objArr[0] = "resultType";
                break;
            case 26:
                objArr[0] = "body";
                break;
            case 27:
            case 31:
            case 34:
            case 41:
            case 44:
                objArr[0] = "fromType";
                break;
            case 28:
            case 32:
            case 35:
            case 42:
            case 45:
                objArr[0] = "toType";
                break;
            case 39:
                objArr[0] = "actualType";
                break;
        }
        objArr[1] = "org/jetbrains/kotlin/codegen/StackValue";
        switch (i) {
            case 1:
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
            case 3:
                objArr[2] = "store";
                break;
            case 4:
                objArr[2] = "createDefaultValue";
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                objArr[2] = "boxInlineClass";
                break;
            case 11:
            case 12:
            case 13:
                objArr[2] = "invokeBoxMethod";
                break;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                objArr[2] = "unboxInlineClass";
                break;
            case 22:
            case 23:
            case 24:
                objArr[2] = "invokeUnboxMethod";
                break;
            case 25:
            case 26:
                objArr[2] = "boxOrUnboxWithNullCheck";
                break;
            case 27:
            case 28:
            case 29:
            case 30:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
                objArr[2] = "coerce";
                break;
            case 31:
            case 32:
            case 33:
                objArr[2] = "requiresInlineClassBoxingOrUnboxing";
                break;
            case 34:
            case 35:
            case 36:
            case 37:
                objArr[2] = "coerceInlineClasses";
                break;
            case 38:
            case 39:
            case 40:
                objArr[2] = "isUnboxedInlineClass";
                break;
            case 47:
                objArr[2] = "putUnitInstance";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public StackValue(Type type, KotlinTypeMarker kotlinTypeMarker) {
        if (type == null) {
            $$$reportNull$$$0(0);
        }
        this.type = type;
        this.kotlinType = kotlinTypeMarker;
    }

    private static void box(Type type, Type type2, InstructionAdapter instructionAdapter) {
        Type type3 = Type.INT_TYPE;
        if (type == type3) {
            if (type2.getInternalName().equals("java/lang/Byte")) {
                type = Type.BYTE_TYPE;
            } else if (type2.getInternalName().equals("java/lang/Short")) {
                type = Type.SHORT_TYPE;
            } else if (type2.getInternalName().equals("java/lang/Long")) {
                type = Type.LONG_TYPE;
            }
            instructionAdapter.cast(type3, type);
        }
        Type typeBoxType = AsmUtil.boxType(type);
        if (typeBoxType == type) {
            return;
        }
        instructionAdapter.invokestatic(typeBoxType.getInternalName(), "valueOf", Type.getMethodDescriptor(typeBoxType, new Type[]{type}), false);
        coerce(typeBoxType, type2, instructionAdapter);
    }

    private static void boxInlineClass(KotlinTypeMarker kotlinTypeMarker, InstructionAdapter instructionAdapter, KotlinTypeMapperBase kotlinTypeMapperBase) {
        if (kotlinTypeMarker == null) {
            $$$reportNull$$$0(5);
        }
        if (instructionAdapter == null) {
            $$$reportNull$$$0(6);
        }
        if (kotlinTypeMapperBase == null) {
            $$$reportNull$$$0(7);
        }
        Type typeMapTypeCommon = kotlinTypeMapperBase.mapTypeCommon(kotlinTypeMarker, TypeMappingMode.CLASS_DECLARATION);
        Type typeMapUnderlyingTypeOfInlineClassType = KotlinTypeMapper.mapUnderlyingTypeOfInlineClassType(kotlinTypeMarker, kotlinTypeMapperBase);
        boxInlineClass(typeMapUnderlyingTypeOfInlineClassType, typeMapTypeCommon, kotlinTypeMapperBase.getTypeSystem().isNullableType(kotlinTypeMarker) && !AsmUtil.isPrimitive(typeMapUnderlyingTypeOfInlineClassType), instructionAdapter);
    }

    private static void boxOrUnboxWithNullCheck(InstructionAdapter instructionAdapter, Consumer<InstructionAdapter> consumer) {
        if (instructionAdapter == null) {
            $$$reportNull$$$0(25);
        }
        if (consumer == null) {
            $$$reportNull$$$0(26);
        }
        Label label = new Label();
        Label label2 = new Label();
        instructionAdapter.dup();
        instructionAdapter.ifnull(label);
        consumer.accept(instructionAdapter);
        instructionAdapter.goTo(label2);
        instructionAdapter.mark(label);
        instructionAdapter.pop();
        instructionAdapter.aconst((Object) null);
        instructionAdapter.mark(label2);
    }

    public static void coerce(Type type, Type type2, InstructionAdapter instructionAdapter, boolean z) {
        if (type == null) {
            $$$reportNull$$$0(44);
        }
        if (type2 == null) {
            $$$reportNull$$$0(45);
        }
        if (instructionAdapter == null) {
            $$$reportNull$$$0(46);
        }
        if (!type2.equals(type) || z) {
            if (type2.getSort() == 0) {
                AsmUtil.pop(instructionAdapter, type);
                return;
            }
            if (type.getSort() == 0) {
                if (type2.equals(AsmTypes.UNIT_TYPE) || type2.equals(AsmTypes.OBJECT_TYPE)) {
                    putUnitInstance(instructionAdapter);
                    return;
                } else {
                    AsmUtil.pushDefaultValueOnStack(type2, instructionAdapter);
                    return;
                }
            }
            Type type3 = AsmTypes.UNIT_TYPE;
            if (type2.equals(type3)) {
                if (type.equals(AsmTypes.getType(Object.class))) {
                    instructionAdapter.checkcast(type3);
                    return;
                } else {
                    if (type.equals(AsmTypes.getType(Void.class))) {
                        return;
                    }
                    AsmUtil.pop(instructionAdapter, type);
                    putUnitInstance(instructionAdapter);
                    return;
                }
            }
            if (type2.getSort() == 9) {
                if (type.getSort() != 9) {
                    instructionAdapter.checkcast(type2);
                    return;
                } else if (type2.getDimensions() != type.getDimensions()) {
                    instructionAdapter.checkcast(type2);
                    return;
                } else {
                    if (type2.getElementType().equals(AsmTypes.OBJECT_TYPE)) {
                        return;
                    }
                    instructionAdapter.checkcast(type2);
                    return;
                }
            }
            if (type2.getSort() == 10) {
                if (type.getSort() != 10 && type.getSort() != 9) {
                    box(type, type2, instructionAdapter);
                    return;
                } else {
                    if (type2.equals(AsmTypes.OBJECT_TYPE)) {
                        return;
                    }
                    instructionAdapter.checkcast(type2);
                    return;
                }
            }
            if (type.getSort() != 10 && type.getSort() != 9) {
                instructionAdapter.cast(type, type2);
                return;
            }
            Type typeUnboxPrimitiveTypeOrNull = AsmUtil.unboxPrimitiveTypeOrNull(type);
            if (typeUnboxPrimitiveTypeOrNull != null) {
                unbox(type, typeUnboxPrimitiveTypeOrNull, instructionAdapter);
                coerce(typeUnboxPrimitiveTypeOrNull, type2, instructionAdapter);
                return;
            }
            if (type2.getSort() == 1) {
                Type type4 = AsmTypes.BOOLEAN_WRAPPER_TYPE;
                coerce(type, type4, instructionAdapter);
                unbox(type4, Type.BOOLEAN_TYPE, instructionAdapter);
            } else {
                if (type2.getSort() != 2) {
                    Type type5 = AsmTypes.NUMBER_TYPE;
                    coerce(type, type5, instructionAdapter);
                    unbox(type5, type2, instructionAdapter);
                    return;
                }
                Type type6 = AsmTypes.NUMBER_TYPE;
                if (type.equals(type6)) {
                    unbox(type6, Type.INT_TYPE, instructionAdapter);
                    instructionAdapter.visitInsn(146);
                } else {
                    Type type7 = AsmTypes.CHARACTER_WRAPPER_TYPE;
                    coerce(type, type7, instructionAdapter);
                    unbox(type7, Type.CHAR_TYPE, instructionAdapter);
                }
            }
        }
    }

    private static boolean coerceInlineClasses(Type type, KotlinTypeMarker kotlinTypeMarker, Type type2, KotlinTypeMarker kotlinTypeMarker2, InstructionAdapter instructionAdapter, KotlinTypeMapperBase kotlinTypeMapperBase) {
        if (type == null) {
            $$$reportNull$$$0(34);
        }
        if (type2 == null) {
            $$$reportNull$$$0(35);
        }
        if (instructionAdapter == null) {
            $$$reportNull$$$0(36);
        }
        if (kotlinTypeMapperBase == null) {
            $$$reportNull$$$0(37);
        }
        if (kotlinTypeMarker != null && kotlinTypeMarker2 != null) {
            TypeSystemCommonBackendContext typeSystem = kotlinTypeMapperBase.getTypeSystem();
            boolean zIsInlineClass = typeSystem.isInlineClass(typeSystem.typeConstructor(kotlinTypeMarker));
            boolean zIsInlineClass2 = typeSystem.isInlineClass(typeSystem.typeConstructor(kotlinTypeMarker2));
            if (!zIsInlineClass && !zIsInlineClass2) {
                return false;
            }
            if (kotlinTypeMarker.equals(kotlinTypeMarker2) && type.equals(type2)) {
                return true;
            }
            if (zIsInlineClass && zIsInlineClass2) {
                boolean zIsUnboxedInlineClass = isUnboxedInlineClass(kotlinTypeMarker, type, kotlinTypeMapperBase);
                boolean zIsUnboxedInlineClass2 = isUnboxedInlineClass(kotlinTypeMarker2, type2, kotlinTypeMapperBase);
                if (zIsUnboxedInlineClass && !zIsUnboxedInlineClass2) {
                    boxInlineClass(kotlinTypeMarker, instructionAdapter, kotlinTypeMapperBase);
                    return true;
                }
                if (!zIsUnboxedInlineClass && zIsUnboxedInlineClass2) {
                    unboxInlineClass(type, kotlinTypeMarker2, instructionAdapter, kotlinTypeMapperBase);
                    return true;
                }
            } else if (zIsInlineClass) {
                if (isUnboxedInlineClass(kotlinTypeMarker, type, kotlinTypeMapperBase)) {
                    boxInlineClass(kotlinTypeMarker, instructionAdapter, kotlinTypeMapperBase);
                    return true;
                }
            } else if (isUnboxedInlineClass(kotlinTypeMarker2, type2, kotlinTypeMapperBase)) {
                unboxInlineClass(type, kotlinTypeMarker2, instructionAdapter, kotlinTypeMapperBase);
                return true;
            }
        }
        return false;
    }

    public static StackValue createDefaultValue(Type type) {
        if (type == null) {
            $$$reportNull$$$0(4);
        }
        switch (type.getSort()) {
            case 1:
                return new Constant(Boolean.FALSE, type);
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
            case 3:
            case 4:
            case 5:
                return new Constant(0, type);
            case 6:
                return new Constant(Float.valueOf(0.0f), type);
            case 7:
                return new Constant(0L, type);
            case 8:
                return new Constant(Double.valueOf(0.0d), type);
            case 9:
            case 10:
                return new Constant(null, type);
            default:
                s22.a("Unsupported type: ", type);
                return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void invokeBoxMethod(InstructionAdapter instructionAdapter, Type type, Type type2) {
        if (instructionAdapter == null) {
            $$$reportNull$$$0(11);
        }
        if (type == null) {
            $$$reportNull$$$0(12);
        }
        if (type2 == null) {
            $$$reportNull$$$0(13);
        }
        instructionAdapter.invokestatic(type.getInternalName(), KotlinTypeMapper.BOX_JVM_METHOD_NAME, Type.getMethodDescriptor(type, new Type[]{type2}), false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void invokeUnboxMethod(InstructionAdapter instructionAdapter, Type type, Type type2) {
        if (instructionAdapter == null) {
            $$$reportNull$$$0(22);
        }
        if (type == null) {
            $$$reportNull$$$0(23);
        }
        if (type2 == null) {
            $$$reportNull$$$0(24);
        }
        instructionAdapter.invokevirtual(type.getInternalName(), KotlinTypeMapper.UNBOX_JVM_METHOD_NAME, "()" + type2.getDescriptor(), false);
    }

    private static boolean isUnboxedInlineClass(KotlinTypeMarker kotlinTypeMarker, Type type, KotlinTypeMapperBase kotlinTypeMapperBase) {
        if (kotlinTypeMarker == null) {
            $$$reportNull$$$0(38);
        }
        if (type == null) {
            $$$reportNull$$$0(39);
        }
        if (kotlinTypeMapperBase == null) {
            $$$reportNull$$$0(40);
        }
        return KotlinTypeMapper.mapUnderlyingTypeOfInlineClassType(kotlinTypeMarker, kotlinTypeMapperBase).equals(type);
    }

    private static void putUnitInstance(InstructionAdapter instructionAdapter) {
        if (instructionAdapter == null) {
            $$$reportNull$$$0(47);
        }
        Type type = AsmTypes.UNIT_TYPE;
        instructionAdapter.visitFieldInsn(178, type.getInternalName(), "INSTANCE", type.getDescriptor());
    }

    public static boolean requiresInlineClassBoxingOrUnboxing(Type type, KotlinTypeMarker kotlinTypeMarker, Type type2, KotlinTypeMarker kotlinTypeMarker2, KotlinTypeMapperBase kotlinTypeMapperBase) {
        if (type == null) {
            $$$reportNull$$$0(31);
        }
        if (type2 == null) {
            $$$reportNull$$$0(32);
        }
        if (kotlinTypeMapperBase == null) {
            $$$reportNull$$$0(33);
        }
        if (kotlinTypeMarker != null && kotlinTypeMarker2 != null) {
            TypeSystemCommonBackendContext typeSystem = kotlinTypeMapperBase.getTypeSystem();
            boolean zIsInlineClass = typeSystem.isInlineClass(typeSystem.typeConstructor(kotlinTypeMarker));
            boolean zIsInlineClass2 = typeSystem.isInlineClass(typeSystem.typeConstructor(kotlinTypeMarker2));
            if (!zIsInlineClass && !zIsInlineClass2) {
                return false;
            }
            boolean z = zIsInlineClass && isUnboxedInlineClass(kotlinTypeMarker, type, kotlinTypeMapperBase);
            boolean z2 = zIsInlineClass2 && isUnboxedInlineClass(kotlinTypeMarker2, type2, kotlinTypeMapperBase);
            if (zIsInlineClass && zIsInlineClass2) {
                return z != z2;
            }
            if ((zIsInlineClass && z) || (zIsInlineClass2 && z2)) {
                return true;
            }
        }
        return false;
    }

    private static void unbox(Type type, Type type2, InstructionAdapter instructionAdapter) {
        instructionAdapter.invokevirtual(type.getInternalName(), type2.getClassName() + "Value", "()" + type2.getDescriptor(), false);
    }

    public static void unboxInlineClass(Type type, KotlinTypeMarker kotlinTypeMarker, InstructionAdapter instructionAdapter, KotlinTypeMapperBase kotlinTypeMapperBase) {
        if (type == null) {
            $$$reportNull$$$0(14);
        }
        if (kotlinTypeMarker == null) {
            $$$reportNull$$$0(15);
        }
        if (instructionAdapter == null) {
            $$$reportNull$$$0(16);
        }
        if (kotlinTypeMapperBase == null) {
            $$$reportNull$$$0(17);
        }
        Type typeMapTypeCommon = kotlinTypeMapperBase.mapTypeCommon(kotlinTypeMarker, TypeMappingMode.CLASS_DECLARATION);
        Type typeMapUnderlyingTypeOfInlineClassType = KotlinTypeMapper.mapUnderlyingTypeOfInlineClassType(kotlinTypeMarker, kotlinTypeMapperBase);
        unboxInlineClass(type, typeMapTypeCommon, typeMapUnderlyingTypeOfInlineClassType, kotlinTypeMapperBase.getTypeSystem().isNullableType(kotlinTypeMarker) && !AsmUtil.isPrimitive(typeMapUnderlyingTypeOfInlineClassType), instructionAdapter);
    }

    public abstract void put(Type type, KotlinTypeMarker kotlinTypeMarker, InstructionAdapter instructionAdapter, KotlinTypeMapperBase kotlinTypeMapperBase);

    public void store(Type type, KotlinTypeMarker kotlinTypeMarker, InstructionAdapter instructionAdapter, KotlinTypeMapperBase kotlinTypeMapperBase) {
        if (type == null) {
            $$$reportNull$$$0(1);
        }
        if (instructionAdapter == null) {
            $$$reportNull$$$0(2);
        }
        if (kotlinTypeMapperBase == null) {
            $$$reportNull$$$0(3);
        }
        throw new UnsupportedOperationException("Cannot store to value " + this);
    }

    public static void boxInlineClass(final Type type, final Type type2, boolean z, InstructionAdapter instructionAdapter) {
        if (type == null) {
            $$$reportNull$$$0(8);
        }
        if (type2 == null) {
            $$$reportNull$$$0(9);
        }
        if (instructionAdapter == null) {
            $$$reportNull$$$0(10);
        }
        if (z) {
            boxOrUnboxWithNullCheck(instructionAdapter, new Consumer() { // from class: nld
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    StackValue.invokeBoxMethod((InstructionAdapter) obj, type2, type);
                }
            });
        } else {
            invokeBoxMethod(instructionAdapter, type2, type);
        }
    }

    public static void unboxInlineClass(Type type, final Type type2, final Type type3, boolean z, InstructionAdapter instructionAdapter) {
        if (type == null) {
            $$$reportNull$$$0(18);
        }
        if (type2 == null) {
            $$$reportNull$$$0(19);
        }
        if (type3 == null) {
            $$$reportNull$$$0(20);
        }
        if (instructionAdapter == null) {
            $$$reportNull$$$0(21);
        }
        coerce(type, type2, instructionAdapter);
        if (z) {
            boxOrUnboxWithNullCheck(instructionAdapter, new Consumer() { // from class: mld
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    StackValue.invokeUnboxMethod((InstructionAdapter) obj, type2, type3);
                }
            });
        } else {
            invokeUnboxMethod(instructionAdapter, type2, type3);
        }
    }

    public static void coerce(Type type, Type type2, InstructionAdapter instructionAdapter) {
        if (type == null) {
            $$$reportNull$$$0(41);
        }
        if (type2 == null) {
            $$$reportNull$$$0(42);
        }
        if (instructionAdapter == null) {
            $$$reportNull$$$0(43);
        }
        coerce(type, type2, instructionAdapter, false);
    }

    public static void coerce(Type type, KotlinTypeMarker kotlinTypeMarker, Type type2, KotlinTypeMarker kotlinTypeMarker2, InstructionAdapter instructionAdapter, KotlinTypeMapperBase kotlinTypeMapperBase) {
        if (type == null) {
            $$$reportNull$$$0(27);
        }
        if (type2 == null) {
            $$$reportNull$$$0(28);
        }
        if (instructionAdapter == null) {
            $$$reportNull$$$0(29);
        }
        if (kotlinTypeMapperBase == null) {
            $$$reportNull$$$0(30);
        }
        if (coerceInlineClasses(type, kotlinTypeMarker, type2, kotlinTypeMarker2, instructionAdapter, kotlinTypeMapperBase)) {
            return;
        }
        coerce(type, type2, instructionAdapter);
    }
}
