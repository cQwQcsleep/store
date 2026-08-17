package com.sun.org.apache.bcel.internal;

import defpackage.s22;
import jdk.xml.internal.Utils;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ExceptionConst {
    public static final Class<Throwable> THROWABLE = Throwable.class;
    public static final Class<RuntimeException> RUNTIME_EXCEPTION = RuntimeException.class;
    public static final Class<LinkageError> LINKING_EXCEPTION = LinkageError.class;
    public static final Class<ClassCircularityError> CLASS_CIRCULARITY_ERROR = ClassCircularityError.class;
    public static final Class<ClassFormatError> CLASS_FORMAT_ERROR = ClassFormatError.class;
    public static final Class<ExceptionInInitializerError> EXCEPTION_IN_INITIALIZER_ERROR = ExceptionInInitializerError.class;
    public static final Class<IncompatibleClassChangeError> INCOMPATIBLE_CLASS_CHANGE_ERROR = IncompatibleClassChangeError.class;
    public static final Class<AbstractMethodError> ABSTRACT_METHOD_ERROR = AbstractMethodError.class;
    public static final Class<IllegalAccessError> ILLEGAL_ACCESS_ERROR = IllegalAccessError.class;
    public static final Class<InstantiationError> INSTANTIATION_ERROR = InstantiationError.class;
    public static final Class<NoSuchFieldError> NO_SUCH_FIELD_ERROR = NoSuchFieldError.class;
    public static final Class<NoSuchMethodError> NO_SUCH_METHOD_ERROR = NoSuchMethodError.class;
    public static final Class<NoClassDefFoundError> NO_CLASS_DEF_FOUND_ERROR = NoClassDefFoundError.class;
    public static final Class<UnsatisfiedLinkError> UNSATISFIED_LINK_ERROR = UnsatisfiedLinkError.class;
    public static final Class<VerifyError> VERIFY_ERROR = VerifyError.class;
    public static final Class<NullPointerException> NULL_POINTER_EXCEPTION = NullPointerException.class;
    public static final Class<ArrayIndexOutOfBoundsException> ARRAY_INDEX_OUT_OF_BOUNDS_EXCEPTION = ArrayIndexOutOfBoundsException.class;
    public static final Class<ArithmeticException> ARITHMETIC_EXCEPTION = ArithmeticException.class;
    public static final Class<NegativeArraySizeException> NEGATIVE_ARRAY_SIZE_EXCEPTION = NegativeArraySizeException.class;
    public static final Class<ClassCastException> CLASS_CAST_EXCEPTION = ClassCastException.class;
    public static final Class<IllegalMonitorStateException> ILLEGAL_MONITOR_STATE = IllegalMonitorStateException.class;
    private static final Class<?>[] EXCS_CLASS_AND_INTERFACE_RESOLUTION = {NoClassDefFoundError.class, ClassFormatError.class, VerifyError.class, AbstractMethodError.class, ExceptionInInitializerError.class, IllegalAccessError.class};
    private static final Class<?>[] EXCS_FIELD_AND_METHOD_RESOLUTION = {NoSuchFieldError.class, IllegalAccessError.class, NoSuchMethodError.class};
    private static final Class<?>[] EXCS_INTERFACE_METHOD_RESOLUTION = new Class[0];
    private static final Class<?>[] EXCS_STRING_RESOLUTION = new Class[0];
    private static final Class<?>[] EXCS_ARRAY_EXCEPTION = {NullPointerException.class, ArrayIndexOutOfBoundsException.class};

    /* JADX INFO: renamed from: com.sun.org.apache.bcel.internal.ExceptionConst$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$org$apache$bcel$internal$ExceptionConst$EXCS;

        static {
            int[] iArr = new int[EXCS.values().length];
            $SwitchMap$com$sun$org$apache$bcel$internal$ExceptionConst$EXCS = iArr;
            try {
                iArr[EXCS.EXCS_CLASS_AND_INTERFACE_RESOLUTION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$org$apache$bcel$internal$ExceptionConst$EXCS[EXCS.EXCS_ARRAY_EXCEPTION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$org$apache$bcel$internal$ExceptionConst$EXCS[EXCS.EXCS_FIELD_AND_METHOD_RESOLUTION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sun$org$apache$bcel$internal$ExceptionConst$EXCS[EXCS.EXCS_INTERFACE_METHOD_RESOLUTION.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$sun$org$apache$bcel$internal$ExceptionConst$EXCS[EXCS.EXCS_STRING_RESOLUTION.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public enum EXCS {
        EXCS_CLASS_AND_INTERFACE_RESOLUTION,
        EXCS_FIELD_AND_METHOD_RESOLUTION,
        EXCS_INTERFACE_METHOD_RESOLUTION,
        EXCS_STRING_RESOLUTION,
        EXCS_ARRAY_EXCEPTION
    }

    public static Class<?>[] createExceptions(EXCS excs, Class<?>... clsArr) {
        int i = AnonymousClass1.$SwitchMap$com$sun$org$apache$bcel$internal$ExceptionConst$EXCS[excs.ordinal()];
        if (i == 1) {
            return mergeExceptions(EXCS_CLASS_AND_INTERFACE_RESOLUTION, clsArr);
        }
        if (i == 2) {
            return mergeExceptions(EXCS_ARRAY_EXCEPTION, clsArr);
        }
        if (i == 3) {
            return mergeExceptions(EXCS_FIELD_AND_METHOD_RESOLUTION, clsArr);
        }
        if (i == 4) {
            return mergeExceptions(EXCS_INTERFACE_METHOD_RESOLUTION, clsArr);
        }
        if (i == 5) {
            return mergeExceptions(EXCS_STRING_RESOLUTION, clsArr);
        }
        s22.a("Cannot happen; unexpected enum value: ", excs);
        return null;
    }

    private static Class<?>[] mergeExceptions(Class<?>[] clsArr, Class<?>... clsArr2) {
        return Utils.arraysAppend(clsArr, clsArr2);
    }
}
