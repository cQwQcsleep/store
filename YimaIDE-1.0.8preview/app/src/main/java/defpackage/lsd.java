package defpackage;

import com.intellij.psi.PsiKeyword;
import com.sun.org.apache.xalan.internal.templates.Constants;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.eclipse.jdt.internal.compiler.env.AccessRestriction;
import org.eclipse.jdt.internal.compiler.env.INameEnvironment;
import org.eclipse.jdt.internal.compiler.env.NameEnvironmentAnswer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class lsd implements INameEnvironment {
    public static final a f = new a(null);
    public static final List g = CollectionsKt.listOf(new String[]{"java.", "javax.", "android.", "kotlin.", "dalvik.", "org.w3c.", "org.xml.", "org.json."});
    public static final Map h = MapsKt.mapOf(TuplesKt.to("java.lang.invoke.StringConcatFactory", "package java.lang.invoke;\npublic class StringConcatFactory {\n    public StringConcatFactory() { }\n    public static java.lang.invoke.CallSite makeConcat(java.lang.invoke.MethodHandles.Lookup arg0, java.lang.String arg1, java.lang.invoke.MethodType arg2) { return null; }\n    public static java.lang.invoke.CallSite makeConcatWithConstants(java.lang.invoke.MethodHandles.Lookup arg0, java.lang.String arg1, java.lang.invoke.MethodType arg2, java.lang.String arg3, java.lang.Object... arg4) { return null; }\n}"));
    public final ClassLoader b;
    public final Map c;
    public final List d;
    public final Map e;

    public lsd(List list, ClassLoader classLoader) {
        list.getClass();
        classLoader.getClass();
        this.b = classLoader;
        this.c = new LinkedHashMap();
        List list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add((File) ((Pair) it.next()).getSecond());
        }
        this.d = CollectionsKt.distinct(arrayList);
        this.e = new LinkedHashMap();
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            Pair pair = (Pair) it2.next();
            File file = (File) pair.component1();
            File file2 = (File) pair.component2();
            String path = FilesKt.relativeTo(file, file2).getPath();
            path.getClass();
            this.c.put(StringsKt.replace$default(StringsKt.removeSuffix(path, ".java"), File.separatorChar, '.', false, 4, (Object) null), TuplesKt.to(file, file2));
            String nameWithoutExtension = FilesKt.getNameWithoutExtension(file);
            if (!this.c.containsKey(nameWithoutExtension)) {
                this.c.put(nameWithoutExtension, TuplesKt.to(file, file2));
            }
        }
    }

    public static CharSequence a(char[] cArr) {
        cArr.getClass();
        return new String(cArr);
    }

    public static CharSequence b(char[] cArr) {
        cArr.getClass();
        return new String(cArr);
    }

    public static CharSequence c(lsd lsdVar, Type type) {
        type.getClass();
        return lsdVar.x(type);
    }

    public static CharSequence d(char[] cArr) {
        cArr.getClass();
        return new String(cArr);
    }

    public static CharSequence e(Class cls) {
        return cls.getName();
    }

    public static CharSequence f(Class cls) {
        return cls.getName();
    }

    public static CharSequence g(lsd lsdVar, Type type) {
        type.getClass();
        return lsdVar.x(type);
    }

    public static CharSequence h(final lsd lsdVar, TypeVariable typeVariable) {
        typeVariable.getClass();
        Type[] bounds = typeVariable.getBounds();
        bounds.getClass();
        ArrayList arrayList = new ArrayList();
        for (Type type : bounds) {
            if (!Intrinsics.areEqual(type, Object.class)) {
                arrayList.add(type);
            }
        }
        if (arrayList.isEmpty()) {
            String name = typeVariable.getName();
            name.getClass();
            return name;
        }
        return typeVariable.getName() + " extends " + CollectionsKt.joinToString$default(arrayList, " & ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: ksd
            public final Object invoke(Object obj) {
                return lsd.i(this.b, (Type) obj);
            }
        }, 30, (Object) null);
    }

    public static CharSequence i(lsd lsdVar, Type type) {
        type.getClass();
        return lsdVar.x(type);
    }

    @Override // org.eclipse.jdt.internal.compiler.env.INameEnvironment
    public void cleanup() {
        this.c.clear();
        this.e.clear();
    }

    @Override // org.eclipse.jdt.internal.compiler.env.INameEnvironment
    public NameEnvironmentAnswer findType(char[] cArr, char[][] cArr2) {
        cArr.getClass();
        cArr2.getClass();
        return q(ArraysKt.joinToString$default(cArr2, Constants.ATTRVAL_THIS, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: dsd
            public final Object invoke(Object obj) {
                return lsd.b((char[]) obj);
            }
        }, 30, (Object) null) + Constants.ATTRVAL_THIS + new String(cArr));
    }

    @Override // org.eclipse.jdt.internal.compiler.env.INameEnvironment
    public boolean isPackage(char[][] cArr, char[] cArr2) {
        if (cArr2 == null) {
            return false;
        }
        String str = new String(cArr2);
        if (str.length() == 0) {
            return false;
        }
        if (cArr != null) {
            if (!(cArr.length == 0)) {
                str = ArraysKt.joinToString$default(cArr, Constants.ATTRVAL_THIS, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: esd
                    public final Object invoke(Object obj) {
                        return lsd.d((char[]) obj);
                    }
                }, 30, (Object) null) + Constants.ATTRVAL_THIS + str;
            }
        }
        String str2 = str;
        String strReplace$default = StringsKt.replace$default(str2, '.', '/', false, 4, (Object) null);
        List list = this.d;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (new File((File) it.next(), strReplace$default).isDirectory()) {
                    return true;
                }
            }
        }
        return (!t(str2) || s(str2) || h.containsKey(str2)) ? false : true;
    }

    public final String j(Class cls) {
        if (!cls.isArray()) {
            return StringsKt.replace$default(cls.getName(), '$', '.', false, 4, (Object) null);
        }
        Class<?> componentType = cls.getComponentType();
        componentType.getClass();
        return j(componentType) + "[]";
    }

    public final String k(Class cls) {
        if (Intrinsics.areEqual(cls, Void.TYPE)) {
            return "";
        }
        if (Intrinsics.areEqual(cls, Boolean.TYPE)) {
            return "return false;";
        }
        if (Intrinsics.areEqual(cls, Byte.TYPE) || Intrinsics.areEqual(cls, Short.TYPE) || Intrinsics.areEqual(cls, Integer.TYPE) || Intrinsics.areEqual(cls, Character.TYPE)) {
            return "return 0;";
        }
        if (Intrinsics.areEqual(cls, Long.TYPE)) {
            return "return 0L;";
        }
        if (Intrinsics.areEqual(cls, Float.TYPE)) {
            return "return 0.0f;";
        }
        return Intrinsics.areEqual(cls, Double.TYPE) ? "return 0.0;" : "return null;";
    }

    public final String l(Class cls) {
        if (Intrinsics.areEqual(cls, Boolean.TYPE)) {
            return "false";
        }
        if (Intrinsics.areEqual(cls, Byte.TYPE) || Intrinsics.areEqual(cls, Short.TYPE) || Intrinsics.areEqual(cls, Integer.TYPE) || Intrinsics.areEqual(cls, Character.TYPE)) {
            return "0";
        }
        if (Intrinsics.areEqual(cls, Long.TYPE)) {
            return "0L";
        }
        if (Intrinsics.areEqual(cls, Float.TYPE)) {
            return "0.0f";
        }
        return Intrinsics.areEqual(cls, Double.TYPE) ? "0.0" : PsiKeyword.NULL;
    }

    public final void m(StringBuilder sb, Class cls, String str, String str2) {
        HashSet hashSet = new HashSet();
        hashSet.add("()");
        sb.append(str2);
        sb.append("public ");
        sb.append(str);
        sb.append("() { }\n");
        try {
            Result.Companion companion = Result.Companion;
            Iterator it = ArrayIteratorKt.iterator(cls.getDeclaredConstructors());
            while (it.hasNext()) {
                Constructor constructor = (Constructor) it.next();
                if (!constructor.isSynthetic()) {
                    int modifiers = constructor.getModifiers();
                    if (Modifier.isPublic(modifiers) || Modifier.isProtected(modifiers)) {
                        Class<?>[] parameterTypes = constructor.getParameterTypes();
                        parameterTypes.getClass();
                        if (hashSet.add("(" + ArraysKt.joinToString$default(parameterTypes, ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: jsd
                            public final Object invoke(Object obj) {
                                return lsd.f((Class) obj);
                            }
                        }, 30, (Object) null) + ")")) {
                            Type[] genericParameterTypes = constructor.getGenericParameterTypes();
                            genericParameterTypes.getClass();
                            String strV = v(genericParameterTypes, constructor.isVarArgs());
                            sb.append(str2);
                            sb.append("public ");
                            sb.append(str);
                            sb.append("(");
                            sb.append(strV);
                            sb.append(") { }\n");
                        }
                    }
                }
            }
            Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            Result.constructor-impl(ResultKt.createFailure(th));
        }
    }

    public final void n(StringBuilder sb, Field field, boolean z, String str) {
        int modifiers = field.getModifiers();
        boolean zIsStatic = Modifier.isStatic(modifiers);
        boolean zIsFinal = Modifier.isFinal(modifiers);
        Type genericType = field.getGenericType();
        genericType.getClass();
        String strX = x(genericType);
        sb.append(str);
        sb.append("public ");
        if (z || (zIsStatic && zIsFinal)) {
            if (!z) {
                sb.append("static final ");
            }
            sb.append(strX);
            sb.append(" ");
            sb.append(field.getName());
            sb.append(" = ");
            Class<?> type = field.getType();
            type.getClass();
            sb.append(l(type));
            sb.append(";\n");
            return;
        }
        if (!zIsStatic) {
            sb.append(strX);
            sb.append(" ");
            sb.append(field.getName());
            sb.append(";\n");
            return;
        }
        sb.append("static ");
        sb.append(strX);
        sb.append(" ");
        sb.append(field.getName());
        sb.append(";\n");
    }

    public final void o(StringBuilder sb, Method method, boolean z, String str) {
        boolean zIsStatic = Modifier.isStatic(method.getModifiers());
        boolean zIsDefault = method.isDefault();
        TypeVariable<Method>[] typeParameters = method.getTypeParameters();
        typeParameters.getClass();
        String strY = y(typeParameters);
        Type genericReturnType = method.getGenericReturnType();
        genericReturnType.getClass();
        String strX = x(genericReturnType);
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        genericParameterTypes.getClass();
        String strV = v(genericParameterTypes, method.isVarArgs());
        sb.append(str);
        sb.append("public ");
        if (zIsStatic) {
            sb.append("static ");
        }
        if (z && zIsDefault) {
            sb.append("default ");
        }
        if (strY.length() > 0) {
            sb.append(strY);
            sb.append(" ");
        }
        sb.append(strX);
        sb.append(" ");
        sb.append(method.getName());
        sb.append("(");
        sb.append(strV);
        sb.append(")");
        if (z && !zIsStatic && !zIsDefault) {
            sb.append(";\n");
            return;
        }
        sb.append(" { ");
        Class<?> returnType = method.getReturnType();
        returnType.getClass();
        sb.append(k(returnType));
        sb.append(" }\n");
    }

    /* JADX WARN: Failed to calculate best type for var: r10v4 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r10v4 ??, new type: int
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:159)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:136)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:241)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:224)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
    	... 6 more
     */
    /* JADX WARN: Failed to calculate best type for var: r10v4 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r10v4 ??, new type: int
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
    	... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r11v1 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r11v1 ??, new type: int
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
    	... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r11v2 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r11v2 ??, new type: int
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
    	... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r11v3 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r11v3 ??, new type: int
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
    	... 7 more
     */
    /*  JADX ERROR: Types fix failed
        jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r10v4 ??, new type: int
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryPossibleTypes(FixTypesVisitor.java:186)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:245)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:224)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
        Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        	... 5 more
        */
    public final void p(java.lang.StringBuilder r30, java.lang.Class r31, java.lang.String r32, java.lang.String r33) {
        /*
            Method dump skipped, instruction units count: 914
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.lsd.p(java.lang.StringBuilder, java.lang.Class, java.lang.String, java.lang.String):void");
    }

    public final NameEnvironmentAnswer q(String str) {
        Pair pair = (Pair) this.c.get(str);
        if (pair != null) {
            return new NameEnvironmentAnswer(new aj7((File) pair.getFirst(), (File) pair.getSecond()), (AccessRestriction) null);
        }
        NameEnvironmentAnswer nameEnvironmentAnswer = (NameEnvironmentAnswer) this.e.get(str);
        if (nameEnvironmentAnswer != null) {
            return nameEnvironmentAnswer;
        }
        NameEnvironmentAnswer nameEnvironmentAnswerW = w(str);
        this.e.put(str, nameEnvironmentAnswerW);
        return nameEnvironmentAnswerW;
    }

    public final String r(Class cls, String str) {
        StringBuilder sb = new StringBuilder();
        String strSubstringBeforeLast = StringsKt.substringBeforeLast(str, '.', "");
        if (strSubstringBeforeLast.length() > 0) {
            sb.append("package ");
            sb.append(strSubstringBeforeLast);
            sb.append(";\n");
        }
        String simpleName = cls.getSimpleName();
        if (simpleName.length() == 0) {
            simpleName = StringsKt.substringAfterLast$default(str, '.', (String) null, 2, (Object) null);
        }
        simpleName.getClass();
        p(sb, cls, simpleName, "");
        return sb.toString();
    }

    public final boolean s(String str) {
        try {
            this.b.loadClass(str);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public final boolean t(String str) {
        List<String> list = g;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            for (String str2 : list) {
                if (StringsKt.startsWith$default(str, str2, false, 2, (Object) null) || Intrinsics.areEqual(str, StringsKt.removeSuffix(str2, Constants.ATTRVAL_THIS))) {
                    return true;
                }
            }
        }
        List list2 = g;
        if ((list2 instanceof Collection) && list2.isEmpty()) {
            return false;
        }
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            if (StringsKt.startsWith$default((String) it.next(), str + Constants.ATTRVAL_THIS, false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    public final boolean u(String str) {
        List list = g;
        if ((list instanceof Collection) && list.isEmpty()) {
            return false;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (StringsKt.startsWith$default(str, (String) it.next(), false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    public final String v(Type[] typeArr, boolean z) {
        ArrayList arrayList = new ArrayList(typeArr.length);
        int length = typeArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = i2 + 1;
            String strX = x(typeArr[i]);
            if (z && i2 == typeArr.length - 1 && StringsKt.endsWith$default(strX, "[]", false, 2, (Object) null)) {
                strX = StringsKt.removeSuffix(strX, "[]") + "...";
            }
            arrayList.add(strX + " arg" + i2);
            i++;
            i2 = i3;
        }
        return CollectionsKt.joinToString$default(arrayList, ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }

    public final NameEnvironmentAnswer w(String str) {
        String str2 = (String) h.get(str);
        if (str2 != null) {
            return new NameEnvironmentAnswer(new yrd(str, str2), (AccessRestriction) null);
        }
        if (!u(str)) {
            return null;
        }
        try {
            Class<?> clsLoadClass = this.b.loadClass(str);
            clsLoadClass.getClass();
            return new NameEnvironmentAnswer(new yrd(str, r(clsLoadClass, str)), (AccessRestriction) null);
        } catch (Throwable unused) {
            return null;
        }
    }

    public final String x(Type type) {
        if (type instanceof Class) {
            return j((Class) type);
        }
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            actualTypeArguments.getClass();
            String strJoinToString$default = ArraysKt.joinToString$default(actualTypeArguments, ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: hsd
                public final Object invoke(Object obj) {
                    return lsd.g(this.b, (Type) obj);
                }
            }, 30, (Object) null);
            Type rawType = parameterizedType.getRawType();
            rawType.getClass();
            return x(rawType) + "<" + strJoinToString$default + ">";
        }
        if (type instanceof GenericArrayType) {
            Type genericComponentType = ((GenericArrayType) type).getGenericComponentType();
            genericComponentType.getClass();
            return x(genericComponentType) + "[]";
        }
        if (!(type instanceof WildcardType)) {
            if (!(type instanceof TypeVariable)) {
                return com.sun.org.apache.xalan.internal.xsltc.compiler.Constants.OBJECT_CLASS;
            }
            String name = ((TypeVariable) type).getName();
            name.getClass();
            return name;
        }
        WildcardType wildcardType = (WildcardType) type;
        Type[] lowerBounds = wildcardType.getLowerBounds();
        Type[] upperBounds = wildcardType.getUpperBounds();
        lowerBounds.getClass();
        if (!(lowerBounds.length == 0)) {
            Type type2 = lowerBounds[0];
            type2.getClass();
            return "? super " + x(type2);
        }
        upperBounds.getClass();
        if ((upperBounds.length == 0) || Intrinsics.areEqual(upperBounds[0], Object.class)) {
            return "?";
        }
        Type type3 = upperBounds[0];
        type3.getClass();
        return "? extends " + x(type3);
    }

    public final String y(TypeVariable[] typeVariableArr) {
        if (typeVariableArr.length == 0) {
            return "";
        }
        return "<" + ArraysKt.joinToString$default(typeVariableArr, ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: isd
            public final Object invoke(Object obj) {
                return lsd.h(this.b, (TypeVariable) obj);
            }
        }, 30, (Object) null) + ">";
    }

    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public a() {
        }
    }

    @Override // org.eclipse.jdt.internal.compiler.env.INameEnvironment
    public NameEnvironmentAnswer findType(char[][] cArr) {
        cArr.getClass();
        return q(ArraysKt.joinToString$default(cArr, Constants.ATTRVAL_THIS, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: csd
            public final Object invoke(Object obj) {
                return lsd.a((char[]) obj);
            }
        }, 30, (Object) null));
    }
}
