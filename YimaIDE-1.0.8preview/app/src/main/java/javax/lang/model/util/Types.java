package javax.lang.model.util;

import java.util.List;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.NoType;
import javax.lang.model.type.NullType;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.WildcardType;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Types {
    Element asElement(TypeMirror typeMirror);

    TypeMirror asMemberOf(DeclaredType declaredType, Element element);

    TypeElement boxedClass(PrimitiveType primitiveType);

    TypeMirror capture(TypeMirror typeMirror);

    boolean contains(TypeMirror typeMirror, TypeMirror typeMirror2);

    List<? extends TypeMirror> directSupertypes(TypeMirror typeMirror);

    TypeMirror erasure(TypeMirror typeMirror);

    ArrayType getArrayType(TypeMirror typeMirror);

    DeclaredType getDeclaredType(TypeElement typeElement, TypeMirror... typeMirrorArr);

    DeclaredType getDeclaredType(DeclaredType declaredType, TypeElement typeElement, TypeMirror... typeMirrorArr);

    NoType getNoType(TypeKind typeKind);

    NullType getNullType();

    PrimitiveType getPrimitiveType(TypeKind typeKind);

    WildcardType getWildcardType(TypeMirror typeMirror, TypeMirror typeMirror2);

    boolean isAssignable(TypeMirror typeMirror, TypeMirror typeMirror2);

    boolean isSameType(TypeMirror typeMirror, TypeMirror typeMirror2);

    boolean isSubsignature(ExecutableType executableType, ExecutableType executableType2);

    boolean isSubtype(TypeMirror typeMirror, TypeMirror typeMirror2);

    default <T extends TypeMirror> T stripAnnotations(T t) {
        throw new UnsupportedOperationException();
    }

    PrimitiveType unboxedType(TypeMirror typeMirror);
}
