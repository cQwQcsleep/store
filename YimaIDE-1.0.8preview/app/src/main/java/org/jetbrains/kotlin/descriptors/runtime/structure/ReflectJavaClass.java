package org.jetbrains.kotlin.descriptors.runtime.structure;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.runtime.structure.ReflectJavaClass;
import org.jetbrains.kotlin.load.java.structure.JavaClass;
import org.jetbrains.kotlin.load.java.structure.JavaClassifierType;
import org.jetbrains.kotlin.load.java.structure.JavaRecordComponent;
import org.jetbrains.kotlin.load.java.structure.LightClassOriginKind;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u0013\u0012\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u0019\u001a\u00020\u0015H\u0016J\u0010\u0010)\u001a\u00020\u00112\u0006\u0010*\u001a\u00020+H\u0002J\b\u00102\u001a\u00020\u0011H\u0016J\u0014\u0010H\u001a\u00020\u00112\b\u0010I\u001a\u0004\u0018\u00010JH\u0096\u0082\u0004J\n\u0010K\u001a\u00020\rH\u0096\u0080\u0004J\n\u0010L\u001a\u00020MH\u0096\u0080\u0004R\u0012\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\t\u001a\u0006\u0012\u0002\b\u00030\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0012R\u001a\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u001a\u001a\u00020\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0016\u0010\u001e\u001a\u0004\u0018\u00010\u00008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R\u001a\u0010&\u001a\b\u0012\u0004\u0012\u00020'0\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b(\u0010\u0017R\u001a\u0010,\u001a\b\u0012\u0004\u0012\u00020-0\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b.\u0010\u0017R\u001a\u0010/\u001a\b\u0012\u0004\u0012\u0002000\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b1\u0010\u0017R\u0016\u00103\u001a\u0004\u0018\u0001048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b5\u00106R\u0014\u0010\u0019\u001a\u00020\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b7\u00108R\u001a\u00109\u001a\b\u0012\u0004\u0012\u00020:0\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b;\u0010\u0017R\u0014\u0010<\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b<\u0010\u0012R\u0014\u0010=\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b=\u0010\u0012R\u0014\u0010>\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b>\u0010\u0012R\u0014\u0010?\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b?\u0010\u0012R\u001a\u0010@\u001a\b\u0012\u0004\u0012\u00020A0\"8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bB\u0010%R\u0014\u0010C\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bC\u0010\u0012R\u001a\u0010D\u001a\b\u0012\u0004\u0012\u00020#0E8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bF\u0010G¨\u0006N"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/runtime/structure/ReflectJavaClass;", "Lorg/jetbrains/kotlin/descriptors/runtime/structure/ReflectJavaElement;", "Lorg/jetbrains/kotlin/descriptors/runtime/structure/ReflectJavaAnnotationOwner;", "Lorg/jetbrains/kotlin/descriptors/runtime/structure/ReflectJavaModifierListOwner;", "Lorg/jetbrains/kotlin/load/java/structure/JavaClass;", "klass", "Ljava/lang/Class;", "<init>", "(Ljava/lang/Class;)V", "element", "getElement", "()Ljava/lang/Class;", "modifiers", Argument.Delimiters.none, "getModifiers", "()I", "isFromSource", Argument.Delimiters.none, "()Z", "innerClassNames", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", "getInnerClassNames", "()Ljava/util/List;", "findInnerClass", ModuleXmlParser.NAME, "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "getFqName", "()Lorg/jetbrains/kotlin/name/FqName;", "outerClass", "getOuterClass", "()Lorg/jetbrains/kotlin/descriptors/runtime/structure/ReflectJavaClass;", "supertypes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/load/java/structure/JavaClassifierType;", "getSupertypes", "()Ljava/util/Collection;", "methods", "Lorg/jetbrains/kotlin/descriptors/runtime/structure/ReflectJavaMethod;", "getMethods", "isEnumValuesOrValueOf", "method", "Ljava/lang/reflect/Method;", "fields", "Lorg/jetbrains/kotlin/descriptors/runtime/structure/ReflectJavaField;", "getFields", "constructors", "Lorg/jetbrains/kotlin/descriptors/runtime/structure/ReflectJavaConstructor;", "getConstructors", "hasDefaultConstructor", "lightClassOriginKind", "Lorg/jetbrains/kotlin/load/java/structure/LightClassOriginKind;", "getLightClassOriginKind", "()Lorg/jetbrains/kotlin/load/java/structure/LightClassOriginKind;", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "typeParameters", "Lorg/jetbrains/kotlin/descriptors/runtime/structure/ReflectJavaTypeParameter;", "getTypeParameters", "isInterface", "isAnnotationType", "isEnum", "isRecord", "recordComponents", "Lorg/jetbrains/kotlin/load/java/structure/JavaRecordComponent;", "getRecordComponents", "isSealed", "permittedTypes", "Lkotlin/sequences/Sequence;", "getPermittedTypes", "()Lkotlin/sequences/Sequence;", "equals", "other", Argument.Delimiters.none, "hashCode", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:descriptors.runtime"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ReflectJavaClass extends ReflectJavaElement implements ReflectJavaAnnotationOwner, ReflectJavaModifierListOwner, JavaClass {
    private final Class<?> klass;

    public ReflectJavaClass(Class<?> cls) {
        cls.getClass();
        this.klass = cls;
    }

    public static boolean a(Class cls) {
        return cls.getSimpleName().length() == 0;
    }

    public static Name b(Class cls) {
        String simpleName = cls.getSimpleName();
        if (!Name.isValidIdentifier(simpleName)) {
            simpleName = null;
        }
        if (simpleName != null) {
            return Name.identifier(simpleName);
        }
        return null;
    }

    public static boolean c(ReflectJavaClass reflectJavaClass, Method method) {
        if (method.isSynthetic()) {
            return false;
        }
        return (reflectJavaClass.isEnum() && reflectJavaClass.isEnumValuesOrValueOf(method)) ? false : true;
    }

    private final boolean isEnumValuesOrValueOf(Method method) {
        String name = method.getName();
        if (Intrinsics.areEqual(name, "values")) {
            Class<?>[] parameterTypes = method.getParameterTypes();
            parameterTypes.getClass();
            return parameterTypes.length == 0;
        }
        if (Intrinsics.areEqual(name, "valueOf")) {
            return Arrays.equals(method.getParameterTypes(), new Class[]{String.class});
        }
        return false;
    }

    public boolean equals(Object other) {
        return (other instanceof ReflectJavaClass) && Intrinsics.areEqual(this.klass, ((ReflectJavaClass) other).klass);
    }

    /* JADX INFO: renamed from: findInnerClass, reason: merged with bridge method [inline-methods] */
    public ReflectJavaClass m177findInnerClass(Name name) {
        Object next;
        name.getClass();
        Class<?>[] declaredClasses = this.klass.getDeclaredClasses();
        declaredClasses.getClass();
        Iterator it = ArraysKt.asSequence(declaredClasses).iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(((Class) next).getSimpleName(), name.asString()));
        Class cls = (Class) next;
        if (cls != null) {
            return new ReflectJavaClass(cls);
        }
        return null;
    }

    public List<ReflectJavaConstructor> getConstructors() {
        Constructor<?>[] declaredConstructors = this.klass.getDeclaredConstructors();
        declaredConstructors.getClass();
        return SequencesKt.toList(SequencesKt.map(SequencesKt.filterNot(ArraysKt.asSequence(declaredConstructors), ReflectJavaClass$constructors$1.INSTANCE), ReflectJavaClass$constructors$2.INSTANCE));
    }

    public List<ReflectJavaField> getFields() {
        Field[] declaredFields = this.klass.getDeclaredFields();
        declaredFields.getClass();
        return SequencesKt.toList(SequencesKt.map(SequencesKt.filterNot(ArraysKt.asSequence(declaredFields), ReflectJavaClass$fields$1.INSTANCE), ReflectJavaClass$fields$2.INSTANCE));
    }

    public FqName getFqName() {
        return ReflectClassUtilKt.getClassId(this.klass).asSingleFqName();
    }

    public List<Name> getInnerClassNames() {
        Class<?>[] declaredClasses = this.klass.getDeclaredClasses();
        declaredClasses.getClass();
        return SequencesKt.toList(SequencesKt.mapNotNull(SequencesKt.filterNot(ArraysKt.asSequence(declaredClasses), new Function1() { // from class: t9c
            public final Object invoke(Object obj) {
                return Boolean.valueOf(ReflectJavaClass.a((Class) obj));
            }
        }), new Function1() { // from class: u9c
            public final Object invoke(Object obj) {
                return ReflectJavaClass.b((Class) obj);
            }
        }));
    }

    public LightClassOriginKind getLightClassOriginKind() {
        return null;
    }

    public List<ReflectJavaMethod> getMethods() {
        Method[] declaredMethods = this.klass.getDeclaredMethods();
        declaredMethods.getClass();
        return SequencesKt.toList(SequencesKt.map(SequencesKt.filter(ArraysKt.asSequence(declaredMethods), new Function1() { // from class: s9c
            public final Object invoke(Object obj) {
                return Boolean.valueOf(ReflectJavaClass.c(this.b, (Method) obj));
            }
        }), ReflectJavaClass$methods$2.INSTANCE));
    }

    @Override // org.jetbrains.kotlin.descriptors.runtime.structure.ReflectJavaModifierListOwner
    public int getModifiers() {
        return this.klass.getModifiers();
    }

    public Name getName() {
        boolean zIsAnonymousClass = this.klass.isAnonymousClass();
        Class<?> cls = this.klass;
        if (zIsAnonymousClass) {
            Name nameIdentifier = Name.identifier(StringsKt.substringAfterLast$default(cls.getName(), ".", (String) null, 2, (Object) null));
            nameIdentifier.getClass();
            return nameIdentifier;
        }
        Name nameIdentifier2 = Name.identifier(cls.getSimpleName());
        nameIdentifier2.getClass();
        return nameIdentifier2;
    }

    /* JADX INFO: renamed from: getOuterClass, reason: merged with bridge method [inline-methods] */
    public ReflectJavaClass m182getOuterClass() {
        Class<?> declaringClass = this.klass.getDeclaringClass();
        if (declaringClass != null) {
            return new ReflectJavaClass(declaringClass);
        }
        return null;
    }

    public Sequence<JavaClassifierType> getPermittedTypes() throws IllegalAccessException, InvocationTargetException {
        Class<?>[] clsArrLoadGetPermittedSubclasses = Java16SealedRecordLoader.INSTANCE.loadGetPermittedSubclasses(this.klass);
        if (clsArrLoadGetPermittedSubclasses != null) {
            ArrayList arrayList = new ArrayList(clsArrLoadGetPermittedSubclasses.length);
            for (Class<?> cls : clsArrLoadGetPermittedSubclasses) {
                arrayList.add(new ReflectJavaClassifierType(cls));
            }
            Sequence<JavaClassifierType> sequenceAsSequence = CollectionsKt.asSequence(arrayList);
            if (sequenceAsSequence != null) {
                return sequenceAsSequence;
            }
        }
        return SequencesKt.emptySequence();
    }

    public Collection<JavaRecordComponent> getRecordComponents() {
        Object[] objArrLoadGetRecordComponents = Java16SealedRecordLoader.INSTANCE.loadGetRecordComponents(this.klass);
        if (objArrLoadGetRecordComponents == null) {
            objArrLoadGetRecordComponents = new Object[0];
        }
        ArrayList arrayList = new ArrayList(objArrLoadGetRecordComponents.length);
        for (Object obj : objArrLoadGetRecordComponents) {
            arrayList.add(new ReflectJavaRecordComponent(obj));
        }
        return arrayList;
    }

    public Collection<JavaClassifierType> getSupertypes() {
        if (Intrinsics.areEqual(this.klass, Object.class)) {
            return CollectionsKt.emptyList();
        }
        SpreadBuilder spreadBuilder = new SpreadBuilder(2);
        Type genericSuperclass = this.klass.getGenericSuperclass();
        spreadBuilder.add(genericSuperclass != null ? genericSuperclass : Object.class);
        spreadBuilder.addSpread(this.klass.getGenericInterfaces());
        List listListOf = CollectionsKt.listOf(spreadBuilder.toArray(new Type[spreadBuilder.size()]));
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listListOf, 10));
        Iterator it = listListOf.iterator();
        while (it.hasNext()) {
            arrayList.add(new ReflectJavaClassifierType((Type) it.next()));
        }
        return arrayList;
    }

    public List<ReflectJavaTypeParameter> getTypeParameters() {
        TypeVariable<Class<?>>[] typeParameters = this.klass.getTypeParameters();
        typeParameters.getClass();
        ArrayList arrayList = new ArrayList(typeParameters.length);
        for (TypeVariable<Class<?>> typeVariable : typeParameters) {
            arrayList.add(new ReflectJavaTypeParameter(typeVariable));
        }
        return arrayList;
    }

    public boolean hasDefaultConstructor() {
        return false;
    }

    public int hashCode() {
        return this.klass.hashCode();
    }

    public boolean isAnnotationType() {
        return this.klass.isAnnotation();
    }

    public boolean isEnum() {
        return this.klass.isEnum();
    }

    public boolean isFromSource() {
        return false;
    }

    public boolean isInterface() {
        return this.klass.isInterface();
    }

    public boolean isRecord() throws IllegalAccessException, InvocationTargetException {
        Boolean boolLoadIsRecord = Java16SealedRecordLoader.INSTANCE.loadIsRecord(this.klass);
        if (boolLoadIsRecord != null) {
            return boolLoadIsRecord.booleanValue();
        }
        return false;
    }

    public boolean isSealed() throws IllegalAccessException, InvocationTargetException {
        Boolean boolLoadIsSealed = Java16SealedRecordLoader.INSTANCE.loadIsSealed(this.klass);
        if (boolLoadIsSealed != null) {
            return boolLoadIsSealed.booleanValue();
        }
        return false;
    }

    public String toString() {
        return ReflectJavaClass.class.getName() + ": " + this.klass;
    }

    @Override // org.jetbrains.kotlin.descriptors.runtime.structure.ReflectJavaAnnotationOwner
    public Class<?> getElement() {
        return this.klass;
    }
}
