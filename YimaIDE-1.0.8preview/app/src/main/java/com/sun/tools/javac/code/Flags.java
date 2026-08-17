package com.sun.tools.javac.code;

import com.intellij.psi.PsiKeyword;
import com.sun.tools.javac.util.Assert;
import defpackage.hh5;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import javax.lang.model.element.Modifier;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Flags {

    @Use({FlagTarget.CLASS, FlagTarget.METHOD})
    public static final int ABSTRACT = 1024;

    @Use({FlagTarget.METHOD})
    @NoToStringValue
    public static final int ACC_BRIDGE = 64;

    @Use({FlagTarget.CLASS})
    @NoToStringValue
    public static final int ACC_MODULE = 32768;

    @Use({FlagTarget.CLASS})
    @NoToStringValue
    public static final int ACC_SUPER = 32;

    @Use({FlagTarget.METHOD})
    @NoToStringValue
    public static final int ACC_VARARGS = 128;

    @Use({FlagTarget.CLASS, FlagTarget.METHOD, FlagTarget.TYPE_VAR})
    public static final int ACYCLIC = 1073741824;

    @Use({FlagTarget.CLASS})
    public static final long ACYCLIC_ANN = 34359738368L;

    @Use({FlagTarget.CLASS})
    public static final int ANNOTATION = 8192;

    @Use({FlagTarget.METHOD})
    public static final int ANONCONSTR = 536870912;

    @Use({FlagTarget.METHOD})
    public static final long ANONCONSTR_BASED = 144115188075855872L;

    @Use({FlagTarget.MODULE})
    public static final long AUTOMATIC_MODULE = 4503599627370496L;

    @Use({FlagTarget.CLASS})
    public static final long AUXILIARY = 17592186044416L;

    @NotFlag
    public static final int AccessFlags = 7;

    @NotFlag
    public static final long AnnotationTypeElementMask = 1025;

    @Use({FlagTarget.METHOD})
    public static final long BAD_OVERRIDE = 35184372088832L;

    @Use({FlagTarget.METHOD})
    public static final int BLOCK = 1048576;

    @Use({FlagTarget.BLOCK})
    public static final long BODY_ONLY_FINALIZE = 131072;

    @Use({FlagTarget.METHOD})
    public static final long BRIDGE = 2147483648L;

    @Use({FlagTarget.METHOD, FlagTarget.VARIABLE})
    public static final long CLASH = 4398046511104L;

    @Use({FlagTarget.CLASS})
    public static final int CLASS_SEEN = 33554432;

    @Use({FlagTarget.METHOD})
    public static final long COMPACT_RECORD_CONSTRUCTOR = 2251799813685248L;

    @Use({FlagTarget.CLASS})
    public static final int COMPOUND = 16777216;

    @NotFlag
    public static final int ClassFlags = 32273;

    @NotFlag
    public static final int ConstructorFlags = 7;

    @Use({FlagTarget.CLASS, FlagTarget.METHOD})
    public static final long DEFAULT = 8796093022208L;

    @Use({FlagTarget.CLASS, FlagTarget.METHOD, FlagTarget.MODULE, FlagTarget.PACKAGE, FlagTarget.TYPE_VAR, FlagTarget.VARIABLE})
    public static final int DEPRECATED = 131072;

    @Use({FlagTarget.CLASS, FlagTarget.METHOD, FlagTarget.MODULE, FlagTarget.PACKAGE, FlagTarget.TYPE_VAR, FlagTarget.VARIABLE})
    public static final long DEPRECATED_ANNOTATION = 18014398509481984L;

    @Use({FlagTarget.CLASS, FlagTarget.METHOD, FlagTarget.MODULE, FlagTarget.PACKAGE, FlagTarget.TYPE_VAR, FlagTarget.VARIABLE})
    public static final long DEPRECATED_REMOVAL = 36028797018963968L;

    @Use({FlagTarget.VARIABLE})
    public static final long EFFECTIVELY_FINAL = 2199023255552L;

    @Use({FlagTarget.CLASS, FlagTarget.VARIABLE})
    public static final int ENUM = 16384;

    @Use({FlagTarget.CLASS, FlagTarget.PACKAGE})
    public static final int EXISTS = 8388608;

    @NotFlag
    public static final long ExtendedClassFlags = -9223090561878032879L;

    @NotFlag
    public static final long ExtendedMemberClassFlags = -9223090561878041065L;

    @NotFlag
    public static final long ExtendedMemberStaticClassFlags = -9223090561878041057L;

    @NotFlag
    public static final long ExtendedStandardFlags = -9223081765785038849L;

    @Use({FlagTarget.VARIABLE})
    public static final long FIELD_INIT_TYPE_ANNOTATIONS_QUEUED = 9007199254740992L;

    @Use({FlagTarget.CLASS, FlagTarget.METHOD, FlagTarget.VARIABLE})
    public static final int FINAL = 16;

    @Use({FlagTarget.CLASS})
    public static final int FROM_SOURCE = 2097152;

    @Use({FlagTarget.METHOD})
    public static final long GENERATEDCONSTR = 68719476736L;

    @Use({FlagTarget.METHOD, FlagTarget.VARIABLE})
    public static final int GENERATED_MEMBER = 16777216;

    @Use({FlagTarget.VARIABLE})
    public static final int HASINIT = 262144;

    @Use({FlagTarget.PACKAGE})
    public static final long HAS_RESOURCE = 4503599627370496L;

    @Use({FlagTarget.METHOD})
    public static final long HYPOTHETICAL = 137438953472L;

    @Use({FlagTarget.CLASS})
    public static final int IMPLICIT_CLASS = 524288;

    @Use({FlagTarget.CLASS})
    public static final int INTERFACE = 512;

    @NotFlag
    public static final int InterfaceMethodFlags = 1025;

    @NotFlag
    public static final long InterfaceMethodMask = 8796093025291L;

    @NotFlag
    public static final int InterfaceVarFlags = 25;

    @Use({FlagTarget.METHOD})
    public static final long LAMBDA_METHOD = 562949953421312L;

    @Use({FlagTarget.VARIABLE})
    public static final long LOCAL_CAPTURE_FIELD = 562949953421312L;

    @Use({FlagTarget.CLASS, FlagTarget.METHOD})
    public static final int LOCKED = 134217728;

    @NotFlag
    public static final int LocalClassFlags = 23568;

    @NotFlag
    public static final long LocalVarFlags = 8589934608L;

    @Use({FlagTarget.MODULE, FlagTarget.VARIABLE})
    public static final int MANDATED = 32768;

    @Use({FlagTarget.VARIABLE})
    public static final long MATCH_BINDING = 576460752303423488L;

    @Use({FlagTarget.VARIABLE})
    public static final long MATCH_BINDING_TO_OUTER = 1152921504606846976L;

    @Use({FlagTarget.CLASS})
    public static final long MODULE = 2251799813685248L;

    @NotFlag
    public static final int MemberClassFlags = 24087;

    @NotFlag
    public static final int MemberStaticClassFlags = 24095;

    @NotFlag
    public static final int MethodFlags = 3391;

    @NotFlag
    public static final long ModifierFlags = -9223081765785039361L;

    @Use({FlagTarget.VARIABLE})
    public static final long NAME_FILLED = 4503599627370496L;

    @Use({FlagTarget.METHOD})
    public static final int NATIVE = 256;

    @CustomToStringValue(PsiKeyword.NON_SEALED)
    @Use({FlagTarget.CLASS})
    public static final long NON_SEALED = Long.MIN_VALUE;

    @Use({FlagTarget.CLASS, FlagTarget.VARIABLE})
    public static final int NOOUTERTHIS = 4194304;

    @Use({FlagTarget.CLASS})
    public static final long NOT_IN_PROFILE = 35184372088832L;

    @Use({FlagTarget.VARIABLE})
    public static final long PARAMETER = 8589934592L;

    @Use({FlagTarget.CLASS, FlagTarget.METHOD, FlagTarget.MODULE, FlagTarget.PACKAGE, FlagTarget.TYPE_VAR, FlagTarget.VARIABLE})
    public static final long PREVIEW_API = 72057594037927936L;

    @Use({FlagTarget.CLASS, FlagTarget.METHOD, FlagTarget.MODULE, FlagTarget.PACKAGE, FlagTarget.TYPE_VAR, FlagTarget.VARIABLE})
    public static final long PREVIEW_REFLECTIVE = 288230376151711744L;

    @Use({FlagTarget.CLASS, FlagTarget.METHOD, FlagTarget.VARIABLE})
    public static final int PRIVATE = 2;

    @Use({FlagTarget.CLASS})
    public static final long PROPRIETARY = 274877906944L;

    @Use({FlagTarget.CLASS, FlagTarget.METHOD, FlagTarget.VARIABLE})
    public static final int PROTECTED = 4;

    @Use({FlagTarget.CLASS, FlagTarget.METHOD, FlagTarget.VARIABLE})
    public static final int PUBLIC = 1;

    @Use({FlagTarget.CLASS, FlagTarget.VARIABLE, FlagTarget.METHOD})
    public static final long RECORD = 2305843009213693952L;

    @Use({FlagTarget.CLASS, FlagTarget.TYPE_VAR})
    public static final long RECOVERABLE = 1099511627776L;

    @Use({FlagTarget.VARIABLE})
    public static final long REQUIRES_IDENTITY = 4611686018427387904L;

    @Use({FlagTarget.METHOD})
    public static final long RESTRICTED = 4611686018427387904L;

    @NotFlag
    public static final long ReceiverParamFlags = 8589934592L;

    @NotFlag
    public static final int RecordMethodFlags = 3135;

    @Use({FlagTarget.CLASS})
    public static final long SEALED = 281474976710656L;

    @Use({FlagTarget.METHOD})
    public static final long SIGNATURE_POLYMORPHIC = 70368744177664L;

    @Use({FlagTarget.CLASS})
    public static final int SOURCE_SEEN = 67108864;

    @Use({FlagTarget.BLOCK, FlagTarget.CLASS, FlagTarget.METHOD, FlagTarget.VARIABLE})
    public static final int STATIC = 8;

    @Use({FlagTarget.CLASS, FlagTarget.METHOD})
    public static final int STRICTFP = 2048;

    @Use({FlagTarget.CLASS})
    public static final int SUPER_OWNER_ATTRIBUTED = 536870912;

    @Use({FlagTarget.METHOD})
    public static final int SYNCHRONIZED = 32;

    @Use({FlagTarget.CLASS, FlagTarget.METHOD, FlagTarget.VARIABLE})
    public static final int SYNTHETIC = 4096;

    @Use({FlagTarget.MODULE})
    public static final long SYSTEM_MODULE = 9007199254740992L;

    @NotFlag
    public static final int StandardFlags = 4095;

    @NotFlag
    public static final int StaticLocalFlags = 24088;

    @Use({FlagTarget.TYPE_VAR})
    public static final long THROWS = 140737488355328L;

    @Use({FlagTarget.VARIABLE})
    public static final int TRANSIENT = 128;

    @Use({FlagTarget.CLASS})
    public static final long TYPE_TRANSLATED = 1125899906842624L;

    @Use({FlagTarget.CLASS})
    public static final int UNATTRIBUTED = 268435456;

    @Use({FlagTarget.VARIABLE})
    public static final long UNINITIALIZED_FIELD = 2251799813685248L;

    @Use({FlagTarget.VARIABLE})
    public static final long UNION = 549755813888L;

    @Use({FlagTarget.CLASS})
    public static final long VALUE_BASED = 9007199254740992L;

    @Use({FlagTarget.METHOD, FlagTarget.VARIABLE})
    public static final long VARARGS = 17179869184L;

    @Use({FlagTarget.VARIABLE})
    public static final int VOLATILE = 64;

    @NotFlag
    public static final int VarFlags = 16607;
    private static final Map<Long, Set<Modifier>> modifierSets = new ConcurrentHashMap(64);

    @Retention(RetentionPolicy.RUNTIME)
    public @interface CustomToStringValue {
        String value();
    }

    public enum FlagTarget {
        BLOCK,
        CLASS,
        MODULE,
        PACKAGE,
        TYPE_VAR,
        METHOD,
        VARIABLE
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface NoToStringValue {
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface NotFlag {
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface Use {
        FlagTarget[] value();
    }

    private Flags() {
    }

    public static EnumSet<FlagsEnum> asFlagSet(long j) {
        EnumSet<FlagsEnum> enumSetNoneOf = EnumSet.noneOf(FlagsEnum.class);
        for (FlagsEnum flagsEnum : FlagsEnum.values()) {
            if ((flagsEnum.value() & j) != 0) {
                enumSetNoneOf.add(flagsEnum);
                j &= ~flagsEnum.value();
            }
        }
        Assert.check(j == 0);
        return enumSetNoneOf;
    }

    public static Set<Modifier> asModifierSet(long j) {
        Map<Long, Set<Modifier>> map = modifierSets;
        Set<Modifier> set = map.get(Long.valueOf(j));
        if (set != null) {
            return set;
        }
        EnumSet enumSetNoneOf = EnumSet.noneOf(Modifier.class);
        if (0 != (1 & j)) {
            enumSetNoneOf.add(Modifier.PUBLIC);
        }
        if (0 != (4 & j)) {
            enumSetNoneOf.add(Modifier.PROTECTED);
        }
        if (0 != (2 & j)) {
            enumSetNoneOf.add(Modifier.PRIVATE);
        }
        if (0 != (1024 & j)) {
            enumSetNoneOf.add(Modifier.ABSTRACT);
        }
        if (0 != (8 & j)) {
            enumSetNoneOf.add(Modifier.STATIC);
        }
        if (0 != (SEALED & j)) {
            enumSetNoneOf.add(Modifier.SEALED);
        }
        if (0 != (Long.MIN_VALUE & j)) {
            enumSetNoneOf.add(Modifier.NON_SEALED);
        }
        if (0 != (16 & j)) {
            enumSetNoneOf.add(Modifier.FINAL);
        }
        if (0 != (128 & j)) {
            enumSetNoneOf.add(Modifier.TRANSIENT);
        }
        if (0 != (64 & j)) {
            enumSetNoneOf.add(Modifier.VOLATILE);
        }
        if (0 != (32 & j)) {
            enumSetNoneOf.add(Modifier.SYNCHRONIZED);
        }
        if (0 != (256 & j)) {
            enumSetNoneOf.add(Modifier.NATIVE);
        }
        if (0 != (2048 & j)) {
            enumSetNoneOf.add(Modifier.STRICTFP);
        }
        if (0 != (DEFAULT & j)) {
            enumSetNoneOf.add(Modifier.DEFAULT);
        }
        Set<Modifier> setUnmodifiableSet = Collections.unmodifiableSet(enumSetNoneOf);
        map.put(Long.valueOf(j), setUnmodifiableSet);
        return setUnmodifiableSet;
    }

    public static boolean isConstant(Symbol.VarSymbol varSymbol) {
        return varSymbol.getConstValue() != null;
    }

    public static boolean isEnum(Symbol symbol) {
        return (symbol.flags() & 16384) != 0;
    }

    public static boolean isStatic(Symbol symbol) {
        return (symbol.flags() & 8) != 0;
    }

    public static String toSource(long j) {
        return (String) asModifierSet(j).stream().map(new hh5()).collect(Collectors.joining(" "));
    }

    public static String toString(long j) {
        StringBuilder sb = new StringBuilder();
        String str = "";
        for (FlagsEnum flagsEnum : asFlagSet(j)) {
            sb.append(str);
            sb.append(flagsEnum);
            str = " ";
        }
        return sb.toString();
    }
}
