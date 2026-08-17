package org.jetbrains.kotlin.analysis.decompiler.stub;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.constant.ConstantValue;
import org.jetbrains.kotlin.load.kotlin.KotlinJvmBinaryClass;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.psi.stubs.impl.AnnotationData;
import org.jetbrains.kotlin.psi.stubs.impl.EnumData;
import org.jetbrains.kotlin.psi.stubs.impl.KClassData;
import org.jetbrains.kotlin.psi.stubs.impl.KotlinConstantValueKt;
import org.jetbrains.kotlin.resolve.constants.ClassLiteralValue;
import org.jetbrains.kotlin.utils.CollectionsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0010\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\n\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006H\u0002J\u001c\u0010\f\u001a\u00020\r2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u001a\u0010\u0010\u001a\u00020\r2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000e\u001a\u00020\u0011H\u0016J\"\u0010\u0012\u001a\u00020\r2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0006H\u0016J\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u00012\b\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0017\u001a\u00020\u0014H\u0016J\u0014\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006H\u0016J\b\u0010\u001a\u001a\u00020\rH\u0016R$\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0005X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/analysis/decompiler/stub/KotlinJvmAnnotationArgumentsCollector;", "Lorg/jetbrains/kotlin/load/kotlin/KotlinJvmBinaryClass$AnnotationArgumentVisitor;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "args", "", "Lorg/jetbrains/kotlin/name/Name;", "Lorg/jetbrains/kotlin/constant/ConstantValue;", "getArgs", "()Ljava/util/Map;", "nameOrSpecial", "name", "visit", "", "value", "", "visitClassLiteral", "Lorg/jetbrains/kotlin/resolve/constants/ClassLiteralValue;", "visitEnum", "enumClassId", "Lorg/jetbrains/kotlin/name/ClassId;", "enumEntryName", "visitAnnotation", "classId", "visitArray", "Lorg/jetbrains/kotlin/load/kotlin/KotlinJvmBinaryClass$AnnotationArrayArgumentVisitor;", "visitEnd", "org.jetbrains.kotlin:decompiler-to-file-stubs"}, k = 1, mv = {2, 4, 0}, xi = 48)
public class KotlinJvmAnnotationArgumentsCollector implements KotlinJvmBinaryClass.AnnotationArgumentVisitor {
    private final Map<Name, ConstantValue<?>> args = new LinkedHashMap();

    /* JADX INFO: renamed from: org.jetbrains.kotlin.analysis.decompiler.stub.KotlinJvmAnnotationArgumentsCollector$visitArray$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u00007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\u0016J\u0018\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\nH\u0016J\b\u0010\u0012\u001a\u00020\u0006H\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"org/jetbrains/kotlin/analysis/decompiler/stub/KotlinJvmAnnotationArgumentsCollector$visitArray$1", "Lorg/jetbrains/kotlin/load/kotlin/KotlinJvmBinaryClass$AnnotationArrayArgumentVisitor;", "elements", "", "", "visit", "", "value", "visitEnum", "enumClassId", "Lorg/jetbrains/kotlin/name/ClassId;", "enumEntryName", "Lorg/jetbrains/kotlin/name/Name;", "visitClassLiteral", "Lorg/jetbrains/kotlin/resolve/constants/ClassLiteralValue;", "visitAnnotation", "Lorg/jetbrains/kotlin/load/kotlin/KotlinJvmBinaryClass$AnnotationArgumentVisitor;", "classId", "visitEnd", "org.jetbrains.kotlin:decompiler-to-file-stubs"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class C00001 implements KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor {
        final /* synthetic */ Name $name;
        private final List<Object> elements = new ArrayList();

        public C00001(Name name) {
            this.$name = name;
        }

        public void visit(Object value) {
            CollectionsKt.addIfNotNull(this.elements, value);
        }

        public KotlinJvmBinaryClass.AnnotationArgumentVisitor visitAnnotation(final ClassId classId) {
            classId.getClass();
            final KotlinJvmAnnotationArgumentsCollector kotlinJvmAnnotationArgumentsCollector = new KotlinJvmAnnotationArgumentsCollector();
            return new KotlinJvmBinaryClass.AnnotationArgumentVisitor(this, classId) { // from class: org.jetbrains.kotlin.analysis.decompiler.stub.KotlinJvmAnnotationArgumentsCollector$visitArray$1$visitAnnotation$1
                private final /* synthetic */ KotlinJvmAnnotationArgumentsCollector $$delegate_0;
                final /* synthetic */ ClassId $classId;
                final /* synthetic */ KotlinJvmAnnotationArgumentsCollector.C00001 this$0;

                {
                    this.this$0 = this;
                    this.$classId = classId;
                    this.$$delegate_0 = this.$visitor;
                }

                public void visit(Name name, Object value) {
                    this.$$delegate_0.visit(name, value);
                }

                public KotlinJvmBinaryClass.AnnotationArgumentVisitor visitAnnotation(Name name, ClassId classId2) {
                    classId2.getClass();
                    return this.$$delegate_0.visitAnnotation(name, classId2);
                }

                public KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor visitArray(Name name) {
                    return this.$$delegate_0.visitArray(name);
                }

                public void visitClassLiteral(Name name, ClassLiteralValue value) {
                    value.getClass();
                    this.$$delegate_0.visitClassLiteral(name, value);
                }

                public void visitEnd() {
                    CollectionsKt.addIfNotNull(this.this$0.elements, new AnnotationData(this.$classId, this.$visitor.getArgs()));
                }

                public void visitEnum(Name name, ClassId enumClassId, Name enumEntryName) {
                    enumClassId.getClass();
                    enumEntryName.getClass();
                    this.$$delegate_0.visitEnum(name, enumClassId, enumEntryName);
                }
            };
        }

        public void visitClassLiteral(ClassLiteralValue value) {
            value.getClass();
            this.elements.add(new KClassData(value.getClassId(), value.getArrayNestedness()));
        }

        public void visitEnd() {
            KotlinJvmAnnotationArgumentsCollector.this.getArgs().put(KotlinJvmAnnotationArgumentsCollector.this.nameOrSpecial(this.$name), KotlinConstantValueKt.createConstantValue(this.elements.toArray(new Object[0])));
        }

        public void visitEnum(ClassId enumClassId, Name enumEntryName) {
            enumClassId.getClass();
            enumEntryName.getClass();
            this.elements.add(new EnumData(enumClassId, enumEntryName));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Name nameOrSpecial(Name name) {
        if (name != null) {
            return name;
        }
        Name nameSpecial = Name.special("<no_name>");
        nameSpecial.getClass();
        return nameSpecial;
    }

    public final Map<Name, ConstantValue<?>> getArgs() {
        return this.args;
    }

    public void visit(Name name, Object value) {
        this.args.put(nameOrSpecial(name), KotlinConstantValueKt.createConstantValue(value));
    }

    public KotlinJvmBinaryClass.AnnotationArgumentVisitor visitAnnotation(final Name name, final ClassId classId) {
        classId.getClass();
        return new KotlinJvmBinaryClass.AnnotationArgumentVisitor() { // from class: org.jetbrains.kotlin.analysis.decompiler.stub.KotlinJvmAnnotationArgumentsCollector.visitAnnotation.1
            private final /* synthetic */ KotlinJvmAnnotationArgumentsCollector $$delegate_0;

            {
                this.$$delegate_0 = KotlinJvmAnnotationArgumentsCollector.this;
            }

            public void visit(Name name2, Object value) {
                this.$$delegate_0.visit(name2, value);
            }

            public KotlinJvmBinaryClass.AnnotationArgumentVisitor visitAnnotation(Name name2, ClassId classId2) {
                classId2.getClass();
                return this.$$delegate_0.visitAnnotation(name2, classId2);
            }

            public KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor visitArray(Name name2) {
                return this.$$delegate_0.visitArray(name2);
            }

            public void visitClassLiteral(Name name2, ClassLiteralValue value) {
                value.getClass();
                this.$$delegate_0.visitClassLiteral(name2, value);
            }

            public void visitEnd() {
                this.getArgs().put(this.nameOrSpecial(name), KotlinConstantValueKt.createConstantValue(new AnnotationData(classId, KotlinJvmAnnotationArgumentsCollector.this.getArgs())));
            }

            public void visitEnum(Name name2, ClassId enumClassId, Name enumEntryName) {
                enumClassId.getClass();
                enumEntryName.getClass();
                this.$$delegate_0.visitEnum(name2, enumClassId, enumEntryName);
            }
        };
    }

    public KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor visitArray(Name name) {
        return new C00001(name);
    }

    public void visitClassLiteral(Name name, ClassLiteralValue value) {
        value.getClass();
        this.args.put(nameOrSpecial(name), KotlinConstantValueKt.createConstantValue(new KClassData(value.getClassId(), value.getArrayNestedness())));
    }

    public void visitEnd() {
    }

    public void visitEnum(Name name, ClassId enumClassId, Name enumEntryName) {
        enumClassId.getClass();
        enumEntryName.getClass();
        this.args.put(nameOrSpecial(name), KotlinConstantValueKt.createConstantValue(new EnumData(enumClassId, enumEntryName)));
    }
}
