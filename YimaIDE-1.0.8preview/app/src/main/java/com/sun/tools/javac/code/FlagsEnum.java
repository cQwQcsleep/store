package com.sun.tools.javac.code;

import com.intellij.psi.PsiKeyword;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import org.eclipse.jdt.internal.compiler.classfmt.ClassFileConstants;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public enum FlagsEnum {
    PUBLIC(1, PsiKeyword.PUBLIC),
    PRIVATE(2, PsiKeyword.PRIVATE),
    PROTECTED(4, PsiKeyword.PROTECTED),
    STATIC(8, PsiKeyword.STATIC),
    FINAL(16, PsiKeyword.FINAL),
    SYNCHRONIZED_OR_ACC_SUPER(32, PsiKeyword.SYNCHRONIZED),
    VOLATILE_OR_ACC_BRIDGE(64, PsiKeyword.VOLATILE),
    TRANSIENT_OR_ACC_VARARGS(128, PsiKeyword.TRANSIENT),
    NATIVE(256, PsiKeyword.NATIVE),
    INTERFACE(512, PsiKeyword.INTERFACE),
    ABSTRACT(1024, PsiKeyword.ABSTRACT),
    STRICTFP(2048, PsiKeyword.STRICTFP),
    SYNTHETIC(4096, "synthetic"),
    ANNOTATION(8192, "annotation"),
    ENUM(16384, PsiKeyword.ENUM),
    MANDATED_OR_ACC_MODULE(32768, "mandated"),
    DEPRECATED_OR_BODY_ONLY_FINALIZE(Flags.BODY_ONLY_FINALIZE, "deprecated or body_only_finalize"),
    HASINIT(262144, "hasinit"),
    IMPLICIT_CLASS(524288, "implicit_class"),
    BLOCK(1048576, "block"),
    FROM_SOURCE(2097152, "from_source"),
    NOOUTERTHIS(ClassFileConstants.JDK20, "noouterthis"),
    EXISTS(8388608, "exists"),
    COMPOUND_OR_GENERATED_MEMBER(16777216, "compound or generated_member"),
    CLASS_SEEN(33554432, "class_seen"),
    SOURCE_SEEN(67108864, "source_seen"),
    LOCKED(134217728, "locked"),
    UNATTRIBUTED(268435456, "unattributed"),
    ANONCONSTR_OR_SUPER_OWNER_ATTRIBUTED(536870912, "anonconstr or super_owner_attributed"),
    ACYCLIC(1073741824, "acyclic"),
    BRIDGE(Flags.BRIDGE, "bridge"),
    PARAMETER(8589934592L, "parameter"),
    VARARGS(Flags.VARARGS, "varargs"),
    ACYCLIC_ANN(Flags.ACYCLIC_ANN, "acyclic_ann"),
    GENERATEDCONSTR(Flags.GENERATEDCONSTR, "generatedconstr"),
    HYPOTHETICAL(Flags.HYPOTHETICAL, "hypothetical"),
    PROPRIETARY(Flags.PROPRIETARY, "proprietary"),
    UNION(Flags.UNION, SchemaSymbols.ATTVAL_UNION),
    RECOVERABLE(Flags.RECOVERABLE, "recoverable"),
    EFFECTIVELY_FINAL(Flags.EFFECTIVELY_FINAL, "effectively_final"),
    CLASH(Flags.CLASH, "clash"),
    DEFAULT(Flags.DEFAULT, "default"),
    AUXILIARY(Flags.AUXILIARY, "auxiliary"),
    NOT_IN_PROFILE_OR_BAD_OVERRIDE(35184372088832L, "not_in_profile or bad_override"),
    SIGNATURE_POLYMORPHIC(Flags.SIGNATURE_POLYMORPHIC, "signature_polymorphic"),
    THROWS(Flags.THROWS, PsiKeyword.THROWS),
    SEALED(Flags.SEALED, PsiKeyword.SEALED),
    LAMBDA_METHOD_OR_LOCAL_CAPTURE_FIELD(562949953421312L, "lambda_method or local_capture_field"),
    TYPE_TRANSLATED(Flags.TYPE_TRANSLATED, "type_translated"),
    MODULE_OR_COMPACT_RECORD_CONSTRUCTOR_OR_UNINITIALIZED_FIELD(2251799813685248L, "module or compact_record_constructor or uninitialized_field"),
    AUTOMATIC_MODULE_OR_HAS_RESOURCE_OR_NAME_FILLED(4503599627370496L, "automatic_module or has_resource or name_filled"),
    SYSTEM_MODULE_OR_VALUE_BASED_OR_FIELD_INIT_TYPE_ANNOTATIONS_QUEUED(9007199254740992L, "system_module or value_based or field_init_type_annotations_queued"),
    DEPRECATED_ANNOTATION(Flags.DEPRECATED_ANNOTATION, "deprecated_annotation"),
    DEPRECATED_REMOVAL(Flags.DEPRECATED_REMOVAL, "deprecated_removal"),
    PREVIEW_API(Flags.PREVIEW_API, "preview_api"),
    ANONCONSTR_BASED(Flags.ANONCONSTR_BASED, "anonconstr_based"),
    PREVIEW_REFLECTIVE(Flags.PREVIEW_REFLECTIVE, "preview_reflective"),
    MATCH_BINDING(Flags.MATCH_BINDING, "match_binding"),
    MATCH_BINDING_TO_OUTER(Flags.MATCH_BINDING_TO_OUTER, "match_binding_to_outer"),
    RECORD(Flags.RECORD, PsiKeyword.RECORD),
    RESTRICTED_OR_REQUIRES_IDENTITY(4611686018427387904L, "restricted or requires_identity"),
    NON_SEALED(Long.MIN_VALUE, PsiKeyword.NON_SEALED);

    private final String toString;
    private final long value;

    FlagsEnum(long j, String str) {
        this.value = j;
        this.toString = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.toString;
    }

    public long value() {
        return this.value;
    }
}
