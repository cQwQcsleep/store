package org.jetbrains.kotlin.codegen;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.jetbrains.kotlin.resolve.jvm.diagnostics.JvmDeclarationOrigin;
import org.jetbrains.org.objectweb.asm.ClassWriter;
import org.jetbrains.org.objectweb.asm.util.TraceClassVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public class ClassBuilderFactories {
    public static ClassBuilderFactory TEST = new TestClassBuilderFactory();
    public static ClassBuilderFactory BINARIES = new ClassBuilderFactory() { // from class: org.jetbrains.kotlin.codegen.ClassBuilderFactories.1
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str = i != 1 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
            Object[] objArr = new Object[i != 1 ? 2 : 3];
            if (i != 1) {
                objArr[0] = "org/jetbrains/kotlin/codegen/ClassBuilderFactories$1";
            } else {
                objArr[0] = "origin";
            }
            if (i != 1) {
                objArr[1] = "getClassBuilderMode";
            } else {
                objArr[1] = "org/jetbrains/kotlin/codegen/ClassBuilderFactories$1";
            }
            if (i == 1) {
                objArr[2] = "newClassBuilder";
            }
            String str2 = String.format(str, objArr);
            if (i == 1) {
                throw new IllegalArgumentException(str2);
            }
        }

        @Override // org.jetbrains.kotlin.codegen.ClassBuilderFactory
        public byte[] asBytes(ClassBuilder classBuilder) {
            return classBuilder.getVisitor().toByteArray();
        }

        @Override // org.jetbrains.kotlin.codegen.ClassBuilderFactory
        public String asText(ClassBuilder classBuilder) {
            throw new UnsupportedOperationException("BINARIES generator asked for text");
        }

        @Override // org.jetbrains.kotlin.codegen.ClassBuilderFactory
        public ClassBuilderMode getClassBuilderMode() {
            ClassBuilderMode classBuilderMode = ClassBuilderMode.FULL;
            if (classBuilderMode == null) {
                $$$reportNull$$$0(0);
            }
            return classBuilderMode;
        }

        @Override // org.jetbrains.kotlin.codegen.ClassBuilderFactory
        public ClassBuilder newClassBuilder(JvmDeclarationOrigin jvmDeclarationOrigin) {
            if (jvmDeclarationOrigin == null) {
                $$$reportNull$$$0(1);
            }
            return new AbstractClassBuilder.Concrete(new BinaryClassWriter());
        }
    };

    public static class BinaryClassWriter extends ClassWriter {
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i != 1) {
                objArr[0] = "type1";
            } else {
                objArr[0] = "type2";
            }
            objArr[1] = "org/jetbrains/kotlin/codegen/ClassBuilderFactories$BinaryClassWriter";
            objArr[2] = "getCommonSuperClass";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        public BinaryClassWriter() {
            super(3);
        }

        public String getCommonSuperClass(String str, String str2) {
            if (str == null) {
                $$$reportNull$$$0(0);
            }
            if (str2 != null) {
                return "java/lang/Object";
            }
            $$$reportNull$$$0(1);
            return "java/lang/Object";
        }
    }

    public static class TestClassBuilderFactory implements ClassBuilderFactory {
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str = i != 1 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
            Object[] objArr = new Object[i != 1 ? 2 : 3];
            if (i != 1) {
                objArr[0] = "org/jetbrains/kotlin/codegen/ClassBuilderFactories$TestClassBuilderFactory";
            } else {
                objArr[0] = "origin";
            }
            if (i != 1) {
                objArr[1] = "getClassBuilderMode";
            } else {
                objArr[1] = "org/jetbrains/kotlin/codegen/ClassBuilderFactories$TestClassBuilderFactory";
            }
            if (i == 1) {
                objArr[2] = "newClassBuilder";
            }
            String str2 = String.format(str, objArr);
            if (i == 1) {
                throw new IllegalArgumentException(str2);
            }
        }

        private TestClassBuilderFactory() {
        }

        @Override // org.jetbrains.kotlin.codegen.ClassBuilderFactory
        public byte[] asBytes(ClassBuilder classBuilder) {
            return ((TraceBuilder) classBuilder).binary.toByteArray();
        }

        @Override // org.jetbrains.kotlin.codegen.ClassBuilderFactory
        public String asText(ClassBuilder classBuilder) {
            TraceClassVisitor visitor = classBuilder.getVisitor();
            StringWriter stringWriter = new StringWriter();
            visitor.p.print(new PrintWriter(stringWriter));
            return stringWriter.toString();
        }

        @Override // org.jetbrains.kotlin.codegen.ClassBuilderFactory
        public ClassBuilderMode getClassBuilderMode() {
            ClassBuilderMode classBuilderMode = ClassBuilderMode.FULL;
            if (classBuilderMode == null) {
                $$$reportNull$$$0(0);
            }
            return classBuilderMode;
        }

        @Override // org.jetbrains.kotlin.codegen.ClassBuilderFactory
        public ClassBuilder newClassBuilder(JvmDeclarationOrigin jvmDeclarationOrigin) {
            if (jvmDeclarationOrigin == null) {
                $$$reportNull$$$0(1);
            }
            return new TraceBuilder(new BinaryClassWriter());
        }
    }

    public static class TraceBuilder extends AbstractClassBuilder.Concrete {
        public final BinaryClassWriter binary;

        public TraceBuilder(BinaryClassWriter binaryClassWriter) {
            super(new TraceClassVisitor(binaryClassWriter, new PrintWriter(new StringWriter())));
            this.binary = binaryClassWriter;
        }
    }

    private ClassBuilderFactories() {
    }
}
