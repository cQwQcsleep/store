package com.sun.tools.classfile;

import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.tools.classfile.Dependency;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Dependencies {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Dependency.Filter filter;
    private Dependency.Finder finder;

    public static class APIDependencyFinder extends BasicDependencyFinder {
        private int showAccess;

        public APIDependencyFinder(int i) {
            if (i == 0 || i == 1 || i == 2 || i == 4) {
                this.showAccess = i;
            } else {
                z01.a("invalid access 0x", Integer.toHexString(i));
                throw null;
            }
        }

        public boolean checkAccess(AccessFlags accessFlags) {
            boolean zIs = accessFlags.is(1);
            boolean zIs2 = accessFlags.is(4);
            boolean zIs3 = accessFlags.is(2);
            boolean z = (zIs || zIs2 || zIs3) ? false : true;
            int i = this.showAccess;
            if (i == 1 && (zIs2 || zIs3 || z)) {
                return false;
            }
            if (i == 4 && (zIs3 || z)) {
                return false;
            }
            return (i == 0 && zIs3) ? false : true;
        }

        @Override // com.sun.tools.classfile.Dependency.Finder
        public Iterable<? extends Dependency> findDependencies(ClassFile classFile) {
            try {
                BasicDependencyFinder.Visitor visitor = new BasicDependencyFinder.Visitor(classFile);
                visitor.addClass(classFile.super_class);
                visitor.addClasses(classFile.interfaces);
                for (Field field : classFile.fields) {
                    if (checkAccess(field.access_flags)) {
                        visitor.scan(field.descriptor, field.attributes);
                    }
                }
                for (Method method : classFile.methods) {
                    if (checkAccess(method.access_flags)) {
                        visitor.scan(method.descriptor, method.attributes);
                        Exceptions_attribute exceptions_attribute = (Exceptions_attribute) method.attributes.get(Attribute.Exceptions);
                        if (exceptions_attribute != null) {
                            visitor.addClasses(exceptions_attribute.exception_index_table);
                        }
                    }
                }
                return visitor.deps;
            } catch (ConstantPoolException e) {
                throw new ClassFileError(e);
            }
        }
    }

    public static class ClassDependencyFinder extends BasicDependencyFinder {
        @Override // com.sun.tools.classfile.Dependency.Finder
        public Iterable<? extends Dependency> findDependencies(ClassFile classFile) {
            BasicDependencyFinder.Visitor visitor = new BasicDependencyFinder.Visitor(classFile);
            Iterator<ConstantPool.CPInfo> it = classFile.constant_pool.entries().iterator();
            while (it.hasNext()) {
                visitor.scan(it.next());
            }
            try {
                visitor.addClass(classFile.super_class);
                visitor.addClasses(classFile.interfaces);
                visitor.scan(classFile.attributes);
                for (Field field : classFile.fields) {
                    visitor.scan(field.descriptor, field.attributes);
                }
                for (Method method : classFile.methods) {
                    visitor.scan(method.descriptor, method.attributes);
                    Exceptions_attribute exceptions_attribute = (Exceptions_attribute) method.attributes.get(Attribute.Exceptions);
                    if (exceptions_attribute != null) {
                        visitor.addClasses(exceptions_attribute.exception_index_table);
                    }
                }
                return visitor.deps;
            } catch (ConstantPoolException e) {
                throw new ClassFileError(e);
            }
        }
    }

    public static class ClassFileError extends Error {
        private static final long serialVersionUID = 4111110813961313203L;

        public ClassFileError(Throwable th) {
            initCause(th);
        }
    }

    public interface ClassFileReader {
        ClassFile getClassFile(String str) throws ClassFileNotFoundException;
    }

    public static class DefaultFilter implements Dependency.Filter {
        private static DefaultFilter instance;

        public static DefaultFilter instance() {
            if (instance == null) {
                instance = new DefaultFilter();
            }
            return instance;
        }

        @Override // com.sun.tools.classfile.Dependency.Filter
        public boolean accepts(Dependency dependency) {
            return true;
        }
    }

    public interface Recorder {
        void addDependency(Dependency dependency);
    }

    public static class SimpleDependency implements Dependency {
        private Dependency.Location origin;
        private Dependency.Location target;

        public SimpleDependency(Dependency.Location location, Dependency.Location location2) {
            this.origin = location;
            this.target = location2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SimpleDependency)) {
                return false;
            }
            SimpleDependency simpleDependency = (SimpleDependency) obj;
            return this.origin.equals(simpleDependency.origin) && this.target.equals(simpleDependency.target);
        }

        @Override // com.sun.tools.classfile.Dependency
        public Dependency.Location getOrigin() {
            return this.origin;
        }

        @Override // com.sun.tools.classfile.Dependency
        public Dependency.Location getTarget() {
            return this.target;
        }

        public int hashCode() {
            return (this.origin.hashCode() * 31) + this.target.hashCode();
        }

        public String toString() {
            return this.origin + ":" + this.target;
        }
    }

    public static class SimpleLocation implements Dependency.Location {
        private String className;
        private String name;

        public SimpleLocation(String str) {
            this.name = str;
            this.className = str.replace('/', '.');
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof SimpleLocation) {
                return this.name.equals(((SimpleLocation) obj).name);
            }
            return false;
        }

        @Override // com.sun.tools.classfile.Dependency.Location
        public String getClassName() {
            return this.className;
        }

        @Override // com.sun.tools.classfile.Dependency.Location
        public String getName() {
            return this.name;
        }

        @Override // com.sun.tools.classfile.Dependency.Location
        public String getPackageName() {
            int iLastIndexOf = this.name.lastIndexOf(47);
            return iLastIndexOf > 0 ? this.name.substring(0, iLastIndexOf).replace('/', '.') : "";
        }

        public int hashCode() {
            return this.name.hashCode();
        }

        public String toString() {
            return this.name;
        }
    }

    public static class TargetPackageFilter implements Dependency.Filter {
        private final boolean matchSubpackages;
        private final Set<String> packageNames;

        public TargetPackageFilter(Set<String> set, boolean z) {
            Iterator<String> it = set.iterator();
            while (it.hasNext()) {
                if (it.next().length() == 0) {
                    j2d.a();
                    throw null;
                }
            }
            this.packageNames = set;
            this.matchSubpackages = z;
        }

        @Override // com.sun.tools.classfile.Dependency.Filter
        public boolean accepts(Dependency dependency) {
            String packageName = dependency.getTarget().getPackageName();
            if (this.packageNames.contains(packageName)) {
                return true;
            }
            if (!this.matchSubpackages) {
                return false;
            }
            Iterator<String> it = this.packageNames.iterator();
            while (it.hasNext()) {
                if (packageName.startsWith(it.next() + Constants.ATTRVAL_THIS)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static class TargetRegexFilter implements Dependency.Filter {
        private final Pattern pattern;

        public TargetRegexFilter(Pattern pattern) {
            this.pattern = pattern;
        }

        @Override // com.sun.tools.classfile.Dependency.Filter
        public boolean accepts(Dependency dependency) {
            return this.pattern.matcher(dependency.getTarget().getClassName()).matches();
        }
    }

    public static Dependency.Finder getAPIFinder(int i) {
        return new APIDependencyFinder(i);
    }

    public static Dependency.Finder getClassDependencyFinder() {
        return new ClassDependencyFinder();
    }

    public static Dependency.Filter getDefaultFilter() {
        return DefaultFilter.instance();
    }

    public static Dependency.Finder getDefaultFinder() {
        return new APIDependencyFinder(2);
    }

    public static Dependency.Filter getPackageFilter(Set<String> set, boolean z) {
        return new TargetPackageFilter(set, z);
    }

    public static Dependency.Filter getRegexFilter(Pattern pattern) {
        return new TargetRegexFilter(pattern);
    }

    public void findAllDependencies(ClassFileReader classFileReader, Set<String> set, boolean z, Recorder recorder) throws ClassFileNotFoundException {
        HashSet hashSet = new HashSet();
        getFinder();
        getFilter();
        LinkedList linkedList = new LinkedList(set);
        while (true) {
            String str = (String) linkedList.poll();
            if (str == null) {
                return;
            }
            hashSet.add(str);
            for (Dependency dependency : this.finder.findDependencies(classFileReader.getClassFile(str))) {
                recorder.addDependency(dependency);
                if (z && this.filter.accepts(dependency)) {
                    String className = dependency.getTarget().getClassName();
                    if (!hashSet.contains(className)) {
                        linkedList.add(className);
                    }
                }
            }
        }
    }

    public Dependency.Filter getFilter() {
        if (this.filter == null) {
            this.filter = getDefaultFilter();
        }
        return this.filter;
    }

    public Dependency.Finder getFinder() {
        if (this.finder == null) {
            this.finder = getDefaultFinder();
        }
        return this.finder;
    }

    public void setFilter(Dependency.Filter filter) {
        Objects.requireNonNull(filter);
        this.filter = filter;
    }

    public void setFinder(Dependency.Finder finder) {
        Objects.requireNonNull(finder);
        this.finder = finder;
    }

    public static abstract class BasicDependencyFinder implements Dependency.Finder {
        private Map<String, Dependency.Location> locations = new ConcurrentHashMap();

        public Dependency.Location getLocation(String str) {
            return this.locations.computeIfAbsent(str, new Function() { // from class: com.sun.tools.classfile.a
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return new Dependencies.SimpleLocation((String) obj);
                }
            });
        }

        public class Visitor implements ConstantPool.Visitor<Void, Void>, Type.Visitor<Void, Void> {
            private ConstantPool constant_pool;
            Set<Dependency> deps;
            private Dependency.Location origin;

            public Visitor(ClassFile classFile) {
                try {
                    this.constant_pool = classFile.constant_pool;
                    this.origin = BasicDependencyFinder.this.getLocation(classFile.getName());
                    this.deps = new HashSet();
                } catch (ConstantPoolException e) {
                    throw new ClassFileError(e);
                }
            }

            private void addDependency(String str) {
                this.deps.add(new SimpleDependency(this.origin, BasicDependencyFinder.this.getLocation(str)));
            }

            private void findDependencies(List<? extends Type> list) {
                if (list != null) {
                    Iterator<? extends Type> it = list.iterator();
                    while (it.hasNext()) {
                        it.next().accept(this, null);
                    }
                }
            }

            private Void visitRef(ConstantPool.CPRefInfo cPRefInfo, Void r2) {
                try {
                    visitClass(cPRefInfo.getClassInfo(), r2);
                    return null;
                } catch (ConstantPoolException e) {
                    throw new ClassFileError(e);
                }
            }

            public void addClass(int i) throws ConstantPoolException {
                String baseName;
                if (i == 0 || (baseName = this.constant_pool.getClassInfo(i).getBaseName()) == null) {
                    return;
                }
                addDependency(baseName);
            }

            public void addClasses(int[] iArr) throws ConstantPoolException {
                for (int i : iArr) {
                    addClass(i);
                }
            }

            public void scan(Attributes attributes) {
                try {
                    Signature_attribute signature_attribute = (Signature_attribute) attributes.get(Attribute.Signature);
                    if (signature_attribute != null) {
                        scan(signature_attribute.getParsedSignature().getType(this.constant_pool));
                    }
                    scan((RuntimeVisibleAnnotations_attribute) attributes.get(Attribute.RuntimeVisibleAnnotations));
                    scan((RuntimeVisibleParameterAnnotations_attribute) attributes.get(Attribute.RuntimeVisibleParameterAnnotations));
                } catch (ConstantPoolException e) {
                    throw new ClassFileError(e);
                }
            }

            @Override // com.sun.tools.classfile.ConstantPool.Visitor
            public Void visitClass(ConstantPool.CONSTANT_Class_info cONSTANT_Class_info, Void r3) {
                try {
                    if (cONSTANT_Class_info.getName().startsWith("[")) {
                        new Signature(cONSTANT_Class_info.name_index).getType(this.constant_pool).accept(this, null);
                    } else {
                        addDependency(cONSTANT_Class_info.getBaseName());
                    }
                    return null;
                } catch (ConstantPoolException e) {
                    throw new ClassFileError(e);
                }
            }

            @Override // com.sun.tools.classfile.Type.Visitor
            public Void visitClassSigType(Type.ClassSigType classSigType, Void r2) {
                findDependencies(classSigType.superclassType);
                findDependencies(classSigType.superinterfaceTypes);
                return null;
            }

            @Override // com.sun.tools.classfile.Type.Visitor
            public Void visitClassType(Type.ClassType classType, Void r2) {
                findDependencies(classType.outerType);
                addDependency(classType.getBinaryName());
                findDependencies(classType.typeArgs);
                return null;
            }

            @Override // com.sun.tools.classfile.Type.Visitor
            public Void visitMethodType(Type.MethodType methodType, Void r2) {
                findDependencies(methodType.paramTypes);
                findDependencies(methodType.returnType);
                findDependencies(methodType.throwsTypes);
                findDependencies(methodType.typeParamTypes);
                return null;
            }

            @Override // com.sun.tools.classfile.ConstantPool.Visitor
            public Void visitNameAndType(ConstantPool.CONSTANT_NameAndType_info cONSTANT_NameAndType_info, Void r2) {
                try {
                    new Signature(cONSTANT_NameAndType_info.type_index).getType(this.constant_pool).accept(this, null);
                    return null;
                } catch (ConstantPoolException e) {
                    throw new ClassFileError(e);
                }
            }

            @Override // com.sun.tools.classfile.Type.Visitor
            public Void visitTypeParamType(Type.TypeParamType typeParamType, Void r2) {
                findDependencies(typeParamType.classBound);
                findDependencies(typeParamType.interfaceBounds);
                return null;
            }

            @Override // com.sun.tools.classfile.Type.Visitor
            public Void visitArrayType(Type.ArrayType arrayType, Void r2) {
                findDependencies(arrayType.elemType);
                return null;
            }

            @Override // com.sun.tools.classfile.ConstantPool.Visitor
            public Void visitDouble(ConstantPool.CONSTANT_Double_info cONSTANT_Double_info, Void r2) {
                return null;
            }

            @Override // com.sun.tools.classfile.ConstantPool.Visitor
            public Void visitDynamicConstant(ConstantPool.CONSTANT_Dynamic_info cONSTANT_Dynamic_info, Void r2) {
                return null;
            }

            @Override // com.sun.tools.classfile.ConstantPool.Visitor
            public Void visitFieldref(ConstantPool.CONSTANT_Fieldref_info cONSTANT_Fieldref_info, Void r2) {
                return visitRef(cONSTANT_Fieldref_info, r2);
            }

            @Override // com.sun.tools.classfile.ConstantPool.Visitor
            public Void visitFloat(ConstantPool.CONSTANT_Float_info cONSTANT_Float_info, Void r2) {
                return null;
            }

            @Override // com.sun.tools.classfile.ConstantPool.Visitor
            public Void visitInteger(ConstantPool.CONSTANT_Integer_info cONSTANT_Integer_info, Void r2) {
                return null;
            }

            @Override // com.sun.tools.classfile.ConstantPool.Visitor
            public Void visitInterfaceMethodref(ConstantPool.CONSTANT_InterfaceMethodref_info cONSTANT_InterfaceMethodref_info, Void r2) {
                return visitRef(cONSTANT_InterfaceMethodref_info, r2);
            }

            @Override // com.sun.tools.classfile.ConstantPool.Visitor
            public Void visitInvokeDynamic(ConstantPool.CONSTANT_InvokeDynamic_info cONSTANT_InvokeDynamic_info, Void r2) {
                return null;
            }

            @Override // com.sun.tools.classfile.ConstantPool.Visitor
            public Void visitLong(ConstantPool.CONSTANT_Long_info cONSTANT_Long_info, Void r2) {
                return null;
            }

            @Override // com.sun.tools.classfile.ConstantPool.Visitor
            public Void visitMethodHandle(ConstantPool.CONSTANT_MethodHandle_info cONSTANT_MethodHandle_info, Void r2) {
                return null;
            }

            @Override // com.sun.tools.classfile.ConstantPool.Visitor
            public Void visitMethodref(ConstantPool.CONSTANT_Methodref_info cONSTANT_Methodref_info, Void r2) {
                return visitRef(cONSTANT_Methodref_info, r2);
            }

            @Override // com.sun.tools.classfile.ConstantPool.Visitor
            public Void visitModule(ConstantPool.CONSTANT_Module_info cONSTANT_Module_info, Void r2) {
                return null;
            }

            @Override // com.sun.tools.classfile.ConstantPool.Visitor
            public Void visitPackage(ConstantPool.CONSTANT_Package_info cONSTANT_Package_info, Void r2) {
                return null;
            }

            @Override // com.sun.tools.classfile.Type.Visitor
            public Void visitSimpleType(Type.SimpleType simpleType, Void r2) {
                return null;
            }

            @Override // com.sun.tools.classfile.ConstantPool.Visitor
            public Void visitString(ConstantPool.CONSTANT_String_info cONSTANT_String_info, Void r2) {
                return null;
            }

            @Override // com.sun.tools.classfile.ConstantPool.Visitor
            public Void visitUtf8(ConstantPool.CONSTANT_Utf8_info cONSTANT_Utf8_info, Void r2) {
                return null;
            }

            @Override // com.sun.tools.classfile.Type.Visitor
            public Void visitWildcardType(Type.WildcardType wildcardType, Void r2) {
                findDependencies(wildcardType.boundType);
                return null;
            }

            private void findDependencies(Type type) {
                if (type != null) {
                    type.accept(this, null);
                }
            }

            @Override // com.sun.tools.classfile.ConstantPool.Visitor
            public Void visitMethodType(ConstantPool.CONSTANT_MethodType_info cONSTANT_MethodType_info, Void r2) {
                return null;
            }

            public void scan(ConstantPool.CPInfo cPInfo) {
                cPInfo.accept(this, null);
            }

            public void scan(Type type) {
                type.accept(this, null);
            }

            public void scan(Descriptor descriptor, Attributes attributes) {
                try {
                    scan(new Signature(descriptor.index).getType(this.constant_pool));
                    scan(attributes);
                } catch (ConstantPoolException e) {
                    throw new ClassFileError(e);
                }
            }

            private void scan(RuntimeAnnotations_attribute runtimeAnnotations_attribute) throws ConstantPoolException {
                if (runtimeAnnotations_attribute == null) {
                    return;
                }
                int i = 0;
                while (true) {
                    Annotation[] annotationArr = runtimeAnnotations_attribute.annotations;
                    if (i >= annotationArr.length) {
                        return;
                    }
                    scan(new Signature(annotationArr[i].type_index).getType(this.constant_pool));
                    i++;
                }
            }

            private void scan(RuntimeParameterAnnotations_attribute runtimeParameterAnnotations_attribute) throws ConstantPoolException {
                if (runtimeParameterAnnotations_attribute == null) {
                    return;
                }
                for (int i = 0; i < runtimeParameterAnnotations_attribute.parameter_annotations.length; i++) {
                    int i2 = 0;
                    while (true) {
                        Annotation[] annotationArr = runtimeParameterAnnotations_attribute.parameter_annotations[i];
                        if (i2 < annotationArr.length) {
                            scan(new Signature(annotationArr[i2].type_index).getType(this.constant_pool));
                            i2++;
                        }
                    }
                }
            }
        }
    }

    public static class ClassFileNotFoundException extends Exception {
        private static final long serialVersionUID = 3632265927794475048L;
        public final String className;

        public ClassFileNotFoundException(String str, Throwable th) {
            this(str);
            initCause(th);
        }

        public ClassFileNotFoundException(String str) {
            super(str);
            this.className = str;
        }
    }

    public Set<Dependency> findAllDependencies(ClassFileReader classFileReader, Set<String> set, boolean z) throws ClassFileNotFoundException {
        final HashSet hashSet = new HashSet();
        findAllDependencies(classFileReader, set, z, new Recorder() { // from class: pk3
            @Override // com.sun.tools.classfile.Dependencies.Recorder
            public final void addDependency(Dependency dependency) {
                hashSet.add(dependency);
            }
        });
        return hashSet;
    }
}
