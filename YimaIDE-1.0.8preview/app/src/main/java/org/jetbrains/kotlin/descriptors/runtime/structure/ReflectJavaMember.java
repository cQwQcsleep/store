package org.jetbrains.kotlin.descriptors.runtime.structure;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.load.java.structure.JavaMember;
import org.jetbrains.kotlin.load.java.structure.JavaValueParameter;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b&\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J=\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001f2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"2\u0012\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\"0\"2\u0006\u0010&\u001a\u00020\fH\u0004¢\u0006\u0002\u0010'J\u0014\u0010(\u001a\u00020\f2\b\u0010)\u001a\u0004\u0018\u00010*H\u0096\u0082\u0004J\n\u0010+\u001a\u00020\u0013H\u0096\u0080\u0004J\n\u0010,\u001a\u00020-H\u0096\u0080\u0004R\u0012\u0010\u0007\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001d¨\u0006."}, d2 = {"Lorg/jetbrains/kotlin/descriptors/runtime/structure/ReflectJavaMember;", "Lorg/jetbrains/kotlin/descriptors/runtime/structure/ReflectJavaElement;", "Lorg/jetbrains/kotlin/descriptors/runtime/structure/ReflectJavaAnnotationOwner;", "Lorg/jetbrains/kotlin/descriptors/runtime/structure/ReflectJavaModifierListOwner;", "Lorg/jetbrains/kotlin/load/java/structure/JavaMember;", "<init>", "()V", "member", "Ljava/lang/reflect/Member;", "getMember", "()Ljava/lang/reflect/Member;", "isFromSource", Argument.Delimiters.none, "()Z", "element", "Ljava/lang/reflect/AnnotatedElement;", "getElement", "()Ljava/lang/reflect/AnnotatedElement;", "modifiers", Argument.Delimiters.none, "getModifiers", "()I", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "containingClass", "Lorg/jetbrains/kotlin/descriptors/runtime/structure/ReflectJavaClass;", "getContainingClass", "()Lorg/jetbrains/kotlin/descriptors/runtime/structure/ReflectJavaClass;", "getValueParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/load/java/structure/JavaValueParameter;", "parameterTypes", Argument.Delimiters.none, "Ljava/lang/reflect/Type;", "parameterAnnotations", Argument.Delimiters.none, "isVararg", "([Ljava/lang/reflect/Type;[[Ljava/lang/annotation/Annotation;Z)Ljava/util/List;", "equals", "other", Argument.Delimiters.none, "hashCode", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:descriptors.runtime"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class ReflectJavaMember extends ReflectJavaElement implements ReflectJavaAnnotationOwner, ReflectJavaModifierListOwner, JavaMember {
    public boolean equals(Object other) {
        return (other instanceof ReflectJavaMember) && Intrinsics.areEqual(getMember(), ((ReflectJavaMember) other).getMember());
    }

    /* JADX INFO: renamed from: getContainingClass, reason: merged with bridge method [inline-methods] */
    public ReflectJavaClass m184getContainingClass() {
        Class<?> declaringClass = getMember().getDeclaringClass();
        declaringClass.getClass();
        return new ReflectJavaClass(declaringClass);
    }

    @Override // org.jetbrains.kotlin.descriptors.runtime.structure.ReflectJavaAnnotationOwner
    public AnnotatedElement getElement() {
        Member member = getMember();
        member.getClass();
        return (AnnotatedElement) member;
    }

    public abstract Member getMember();

    @Override // org.jetbrains.kotlin.descriptors.runtime.structure.ReflectJavaModifierListOwner
    public int getModifiers() {
        return getMember().getModifiers();
    }

    public Name getName() {
        Name nameIdentifier;
        String name = getMember().getName();
        return (name == null || (nameIdentifier = Name.identifier(name)) == null) ? SpecialNames.NO_NAME_PROVIDED : nameIdentifier;
    }

    public final List<JavaValueParameter> getValueParameters(Type[] parameterTypes, Annotation[][] parameterAnnotations, boolean isVararg) throws IllegalAccessException, InvocationTargetException {
        parameterTypes.getClass();
        parameterAnnotations.getClass();
        ArrayList arrayList = new ArrayList(parameterTypes.length);
        List<String> listLoadParameterNames = Java8ParameterNamesLoader.INSTANCE.loadParameterNames(getMember());
        int size = listLoadParameterNames != null ? listLoadParameterNames.size() - parameterTypes.length : 0;
        int length = parameterTypes.length;
        int i = 0;
        while (i < length) {
            ReflectJavaType reflectJavaTypeCreate = ReflectJavaType.INSTANCE.create(parameterTypes[i]);
            String str = null;
            if (listLoadParameterNames != null) {
                String str2 = (String) CollectionsKt.getOrNull(listLoadParameterNames, i + size);
                if (str2 == null) {
                    v9c.a(i, size, getName(), reflectJavaTypeCreate, this);
                    return null;
                }
                str = str2;
            }
            arrayList.add(new ReflectJavaValueParameter(reflectJavaTypeCreate, parameterAnnotations[i], str, isVararg && i == ArraysKt.getLastIndex(parameterTypes)));
            i++;
        }
        return arrayList;
    }

    public int hashCode() {
        return getMember().hashCode();
    }

    public final boolean isFromSource() {
        return false;
    }

    public String toString() {
        return getClass().getName() + ": " + getMember();
    }
}
