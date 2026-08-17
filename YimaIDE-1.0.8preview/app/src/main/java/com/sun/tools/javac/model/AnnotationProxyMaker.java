package com.sun.tools.javac.model;

import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import com.sun.tools.javac.code.Attribute;
import com.sun.tools.javac.code.Kinds;
import com.sun.tools.javac.code.Scope;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Pair;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.annotation.Annotation;
import java.lang.annotation.AnnotationTypeMismatchException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.MirroredTypesException;
import javax.lang.model.type.TypeMirror;
import nbjavac.sun.annotation.AnnotationParser;
import nbjavac.sun.annotation.AnnotationType;
import nbjavac.sun.annotation.EnumConstantNotPresentExceptionProxy;
import nbjavac.sun.annotation.ExceptionProxy;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class AnnotationProxyMaker {
    private final Attribute.Compound anno;
    private final Class<? extends Annotation> annoType;

    public static final class MirroredTypeExceptionProxy extends ExceptionProxy {
        static final long serialVersionUID = 269;
        private transient TypeMirror type;
        private final String typeString;

        public MirroredTypeExceptionProxy(TypeMirror typeMirror) {
            this.type = typeMirror;
            this.typeString = typeMirror.toString();
        }

        private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
            objectInputStream.defaultReadObject();
            this.type = null;
        }

        public boolean equals(Object obj) {
            TypeMirror typeMirror = this.type;
            return typeMirror != null && (obj instanceof MirroredTypeExceptionProxy) && typeMirror.equals(((MirroredTypeExceptionProxy) obj).type);
        }

        public RuntimeException generateException() {
            return new MirroredTypeException(this.type);
        }

        public int hashCode() {
            Object obj = this.type;
            if (obj == null) {
                obj = this.typeString;
            }
            return obj.hashCode();
        }

        public String toString() {
            return this.typeString + JavaClass.EXTENSION;
        }
    }

    public static final class MirroredTypesExceptionProxy extends ExceptionProxy {
        static final long serialVersionUID = 269;
        private final String typeStrings;
        private transient List<TypeMirror> types;

        public MirroredTypesExceptionProxy(List<TypeMirror> list) {
            this.types = list;
            this.typeStrings = list.toString();
        }

        public static /* synthetic */ String b(TypeMirror typeMirror) {
            return typeMirror.toString() + JavaClass.EXTENSION;
        }

        private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
            objectInputStream.defaultReadObject();
            this.types = null;
        }

        public boolean equals(Object obj) {
            List<TypeMirror> list = this.types;
            return list != null && (obj instanceof MirroredTypesExceptionProxy) && list.equals(((MirroredTypesExceptionProxy) obj).types);
        }

        public RuntimeException generateException() {
            return new MirroredTypesException(this.types);
        }

        public int hashCode() {
            Object obj = this.types;
            if (obj == null) {
                obj = this.typeStrings;
            }
            return obj.hashCode();
        }

        public String toString() {
            return (String) this.types.stream().map(new Function() { // from class: com.sun.tools.javac.model.a
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return AnnotationProxyMaker.MirroredTypesExceptionProxy.b((TypeMirror) obj);
                }
            }).collect(Collectors.joining(", ", "{", "}"));
        }
    }

    public class ValueVisitor implements Attribute.Visitor {
        private Symbol.MethodSymbol meth;
        private Class<?> returnClass;
        private Object value;

        public ValueVisitor(Symbol.MethodSymbol methodSymbol) {
            this.meth = methodSymbol;
        }

        private void typeMismatch(Method method, Attribute attribute) {
            this.value = new ExceptionProxy(this, method, attribute) { // from class: com.sun.tools.javac.model.AnnotationProxyMaker.ValueVisitor.1AnnotationTypeMismatchExceptionProxy
                static final long serialVersionUID = 269;
                final transient Method method;
                final /* synthetic */ ValueVisitor this$1;
                final /* synthetic */ Attribute val$attr;

                {
                    this.val$attr = attribute;
                    this.this$1 = this;
                    this.method = method;
                }

                public RuntimeException generateException() {
                    return new AnnotationTypeMismatchException(this.method, this.val$attr.type.toString());
                }

                public String toString() {
                    return "<error>";
                }
            };
        }

        public Object getValue(Attribute attribute) {
            try {
                Method method = AnnotationProxyMaker.this.annoType.getMethod(this.meth.name.toString(), null);
                this.returnClass = method.getReturnType();
                attribute.accept(this);
                if (!(this.value instanceof ExceptionProxy) && !AnnotationType.invocationHandlerReturnType(this.returnClass).isInstance(this.value)) {
                    typeMismatch(method, attribute);
                }
                return this.value;
            } catch (NoSuchMethodException unused) {
                return null;
            }
        }

        @Override // com.sun.tools.javac.code.Attribute.Visitor
        public void visitArray(Attribute.Array array) {
            Name qualifiedName = ((Type.ArrayType) array.type).elemtype.tsym.getQualifiedName();
            int i = 0;
            if (qualifiedName.equals(qualifiedName.table.names.java_lang_Class)) {
                ListBuffer listBuffer = new ListBuffer();
                Attribute[] attributeArr = array.values;
                int length = attributeArr.length;
                while (i < length) {
                    listBuffer.append(((Attribute.Class) attributeArr[i]).classType);
                    i++;
                }
                this.value = new MirroredTypesExceptionProxy(listBuffer.toList());
                return;
            }
            int length2 = array.values.length;
            Class<?> cls = this.returnClass;
            Class<?> componentType = cls.getComponentType();
            this.returnClass = componentType;
            try {
                Object objNewInstance = Array.newInstance(componentType, length2);
                while (i < length2) {
                    array.values[i].accept(this);
                    Object obj = this.value;
                    if (obj == null || (obj instanceof ExceptionProxy)) {
                        this.returnClass = cls;
                        return;
                    }
                    try {
                        Array.set(objNewInstance, i, obj);
                        i++;
                    } catch (IllegalArgumentException unused) {
                        this.value = null;
                        this.returnClass = cls;
                        return;
                    }
                }
                this.value = objNewInstance;
                this.returnClass = cls;
            } catch (Throwable th) {
                this.returnClass = cls;
                throw th;
            }
        }

        @Override // com.sun.tools.javac.code.Attribute.Visitor
        public void visitClass(Attribute.Class r2) {
            this.value = new MirroredTypeExceptionProxy(r2.classType);
        }

        @Override // com.sun.tools.javac.code.Attribute.Visitor
        public void visitCompound(Attribute.Compound compound) {
            try {
                this.value = AnnotationProxyMaker.generateAnnotation(compound, this.returnClass.asSubclass(Annotation.class));
            } catch (ClassCastException unused) {
                this.value = null;
            }
        }

        @Override // com.sun.tools.javac.code.Attribute.Visitor
        public void visitConstant(Attribute.Constant constant) {
            this.value = constant.getValue();
        }

        @Override // com.sun.tools.javac.code.Attribute.Visitor
        public void visitEnum(Attribute.Enum r3) {
            if (!this.returnClass.isEnum()) {
                this.value = null;
                return;
            }
            String string = r3.value.toString();
            try {
                this.value = Enum.valueOf(this.returnClass, string);
            } catch (IllegalArgumentException unused) {
                this.value = new EnumConstantNotPresentExceptionProxy(this.returnClass, string);
            }
        }

        @Override // com.sun.tools.javac.code.Attribute.Visitor
        public void visitError(Attribute.Error error) {
            if (error instanceof Attribute.UnresolvedClass) {
                this.value = new MirroredTypeExceptionProxy(((Attribute.UnresolvedClass) error).classType);
            } else {
                this.value = null;
            }
        }
    }

    private AnnotationProxyMaker(Attribute.Compound compound, Class<? extends Annotation> cls) {
        this.anno = compound;
        this.annoType = cls;
    }

    public static <A extends Annotation> A generateAnnotation(Attribute.Compound compound, Class<A> cls) {
        return cls.cast(new AnnotationProxyMaker(compound, cls).generateAnnotation());
    }

    private Object generateValue(Symbol.MethodSymbol methodSymbol, Attribute attribute) {
        return new ValueVisitor(methodSymbol).getValue(attribute);
    }

    private Map<String, Object> getAllReflectedValues() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<Symbol.MethodSymbol, Attribute> entry : getAllValues().entrySet()) {
            Symbol.MethodSymbol key = entry.getKey();
            Object objGenerateValue = generateValue(key, entry.getValue());
            if (objGenerateValue != null) {
                linkedHashMap.put(key.name.toString(), objGenerateValue);
            }
        }
        return linkedHashMap;
    }

    private Map<Symbol.MethodSymbol, Attribute> getAllValues() {
        Symbol.MethodSymbol methodSymbol;
        Attribute defaultValue;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Symbol symbol : ((Symbol.ClassSymbol) this.anno.type.tsym).members().getSymbols(Scope.LookupKind.NON_RECURSIVE)) {
            if (symbol.kind == Kinds.Kind.MTH && (defaultValue = (methodSymbol = (Symbol.MethodSymbol) symbol).getDefaultValue()) != null) {
                linkedHashMap.put(methodSymbol, defaultValue);
            }
        }
        for (Pair<Symbol.MethodSymbol, Attribute> pair : this.anno.values) {
            linkedHashMap.put(pair.fst, pair.snd);
        }
        return linkedHashMap;
    }

    private Annotation generateAnnotation() {
        return AnnotationParser.annotationForMap(this.annoType, getAllReflectedValues());
    }
}
