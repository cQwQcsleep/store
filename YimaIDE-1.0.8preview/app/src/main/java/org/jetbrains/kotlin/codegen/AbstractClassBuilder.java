package org.jetbrains.kotlin.codegen;

import com.intellij.psi.PsiElement;
import java.util.List;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.inline.FileMapping;
import org.jetbrains.kotlin.codegen.inline.SMAPBuilder;
import org.jetbrains.kotlin.codegen.inline.SourceMapper;
import org.jetbrains.kotlin.codegen.serialization.JvmSerializationBindings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.jvm.diagnostics.JvmDeclarationOrigin;
import org.jetbrains.org.objectweb.asm.AnnotationVisitor;
import org.jetbrains.org.objectweb.asm.ClassVisitor;
import org.jetbrains.org.objectweb.asm.FieldVisitor;
import org.jetbrains.org.objectweb.asm.MethodVisitor;
import org.jetbrains.org.objectweb.asm.RecordComponentVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public abstract class AbstractClassBuilder implements ClassBuilder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    protected static final FieldVisitor EMPTY_FIELD_VISITOR;
    protected static final MethodVisitor EMPTY_METHOD_VISITOR;
    public static final RecordComponentVisitor EMPTY_RECORD_VISITOR;
    private String debugInfo;
    private final JvmSerializationBindings serializationBindings = new JvmSerializationBindings();
    private String sourceName;
    private String thisName;

    public static class Concrete extends AbstractClassBuilder {
        private final ClassVisitor v;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
            Object[] objArr = new Object[i != 1 ? 3 : 2];
            if (i != 1) {
                objArr[0] = "v";
            } else {
                objArr[0] = "org/jetbrains/kotlin/codegen/AbstractClassBuilder$Concrete";
            }
            if (i != 1) {
                objArr[1] = "org/jetbrains/kotlin/codegen/AbstractClassBuilder$Concrete";
            } else {
                objArr[1] = "getVisitor";
            }
            if (i != 1) {
                objArr[2] = "<init>";
            }
            String str2 = String.format(str, objArr);
            if (i == 1) {
                throw new IllegalStateException(str2);
            }
        }

        public Concrete(ClassVisitor classVisitor) {
            if (classVisitor == null) {
                $$$reportNull$$$0(0);
            }
            this.v = classVisitor;
        }

        @Override // org.jetbrains.kotlin.codegen.ClassBuilder
        public ClassVisitor getVisitor() {
            ClassVisitor classVisitor = this.v;
            if (classVisitor == null) {
                $$$reportNull$$$0(1);
            }
            return classVisitor;
        }
    }

    /* JADX WARN: Code duplicated, block: B:11:0x001c  */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        if (i != 3 && i != 4 && i != 8 && i != 9 && i != 16 && i != 24) {
            switch (i) {
                case 12:
                case 13:
                case 14:
                    str = "@NotNull method %s.%s must not return null";
                    break;
                default:
                    str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                    break;
            }
        } else {
            str = "@NotNull method %s.%s must not return null";
        }
        if (i != 3 && i != 4 && i != 8 && i != 9 && i != 16 && i != 24) {
            switch (i) {
                case 12:
                case 13:
                case 14:
                    i2 = 2;
                    break;
                default:
                    i2 = 3;
                    break;
            }
        } else {
            i2 = 2;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 1:
            case 6:
            case 10:
            case 17:
            case 20:
            case 23:
                objArr[0] = ModuleXmlParser.NAME;
                break;
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
            case 7:
            case 11:
            case 15:
                objArr[0] = "desc";
                break;
            case 3:
            case 4:
            case 8:
            case 9:
            case 12:
            case 13:
            case 14:
            case 16:
            case 24:
                objArr[0] = "org/jetbrains/kotlin/codegen/AbstractClassBuilder";
                break;
            case 5:
            default:
                objArr[0] = "origin";
                break;
            case 18:
                objArr[0] = "superName";
                break;
            case 19:
                objArr[0] = "interfaces";
                break;
            case 21:
                objArr[0] = "smap";
                break;
            case 22:
                objArr[0] = "owner";
                break;
        }
        if (i == 3 || i == 4) {
            objArr[1] = "newField";
        } else if (i == 8 || i == 9) {
            objArr[1] = "newMethod";
        } else if (i == 16) {
            objArr[1] = "newAnnotation";
        } else if (i != 24) {
            switch (i) {
                case 12:
                case 13:
                    objArr[1] = "newRecordComponent";
                    break;
                case 14:
                    objArr[1] = "getSerializationBindings";
                    break;
                default:
                    objArr[1] = "org/jetbrains/kotlin/codegen/AbstractClassBuilder";
                    break;
            }
        } else {
            objArr[1] = "getThisName";
        }
        switch (i) {
            case 3:
            case 4:
            case 8:
            case 9:
            case 12:
            case 13:
            case 14:
            case 16:
            case 24:
                break;
            case 5:
            case 6:
            case 7:
                objArr[2] = "newMethod";
                break;
            case 10:
            case 11:
                objArr[2] = "newRecordComponent";
                break;
            case 15:
                objArr[2] = "newAnnotation";
                break;
            case 17:
            case 18:
            case 19:
                objArr[2] = "defineClass";
                break;
            case 20:
                objArr[2] = "visitSource";
                break;
            case 21:
                objArr[2] = "visitSMAP";
                break;
            case 22:
                objArr[2] = "visitOuterClass";
                break;
            case 23:
                objArr[2] = "visitInnerClass";
                break;
            default:
                objArr[2] = "newField";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 3 && i != 4 && i != 8 && i != 9 && i != 16 && i != 24) {
            switch (i) {
                case 12:
                case 13:
                case 14:
                    break;
                default:
                    throw new IllegalArgumentException(str2);
            }
        }
        throw new IllegalStateException(str2);
    }

    static {
        int i = 589824;
        EMPTY_METHOD_VISITOR = new MethodVisitor(i) { // from class: org.jetbrains.kotlin.codegen.AbstractClassBuilder.1
        };
        EMPTY_RECORD_VISITOR = new RecordComponentVisitor(i) { // from class: org.jetbrains.kotlin.codegen.AbstractClassBuilder.2
        };
        EMPTY_FIELD_VISITOR = new FieldVisitor(i) { // from class: org.jetbrains.kotlin.codegen.AbstractClassBuilder.3
        };
    }

    @Override // org.jetbrains.kotlin.codegen.ClassBuilder
    public void defineClass(PsiElement psiElement, int i, int i2, String str, String str2, String str3, String[] strArr) {
        if (str == null) {
            $$$reportNull$$$0(17);
        }
        if (str3 == null) {
            $$$reportNull$$$0(18);
        }
        if (strArr == null) {
            $$$reportNull$$$0(19);
        }
        this.thisName = str;
        getVisitor().visit(i, i2, str, str2, str3, strArr);
    }

    @Override // org.jetbrains.kotlin.codegen.ClassBuilder
    public void done(boolean z) {
        getVisitor().visitSource(this.sourceName, this.debugInfo);
        if (z && this.debugInfo != null) {
            AnnotationVisitor annotationVisitorVisitAnnotation = getVisitor().visitAnnotation("Lkotlin/jvm/internal/SourceDebugExtension;", false);
            CodegenUtilKt.visitWithSplitting(annotationVisitorVisitAnnotation, "value", this.debugInfo);
            annotationVisitorVisitAnnotation.visitEnd();
        }
        getVisitor().visitEnd();
    }

    @Override // org.jetbrains.kotlin.codegen.ClassBuilder
    public JvmSerializationBindings getSerializationBindings() {
        JvmSerializationBindings jvmSerializationBindings = this.serializationBindings;
        if (jvmSerializationBindings == null) {
            $$$reportNull$$$0(14);
        }
        return jvmSerializationBindings;
    }

    @Override // org.jetbrains.kotlin.codegen.ClassBuilder
    public String getThisName() {
        String str = this.thisName;
        if (str == null) {
            $$$reportNull$$$0(24);
        }
        return str;
    }

    @Override // org.jetbrains.kotlin.codegen.ClassBuilder
    public AnnotationVisitor newAnnotation(String str, boolean z) {
        if (str == null) {
            $$$reportNull$$$0(15);
        }
        AnnotationVisitor annotationVisitorVisitAnnotation = getVisitor().visitAnnotation(str, z);
        if (annotationVisitorVisitAnnotation == null) {
            $$$reportNull$$$0(16);
        }
        return annotationVisitorVisitAnnotation;
    }

    @Override // org.jetbrains.kotlin.codegen.ClassBuilder
    public FieldVisitor newField(JvmDeclarationOrigin jvmDeclarationOrigin, int i, String str, String str2, String str3, Object obj) {
        if (jvmDeclarationOrigin == null) {
            $$$reportNull$$$0(0);
        }
        if (str == null) {
            $$$reportNull$$$0(1);
        }
        if (str2 == null) {
            $$$reportNull$$$0(2);
        }
        FieldVisitor fieldVisitorVisitField = getVisitor().visitField(i, str, str2, str3, obj);
        if (fieldVisitorVisitField == null && (fieldVisitorVisitField = EMPTY_FIELD_VISITOR) == null) {
            $$$reportNull$$$0(3);
        }
        return fieldVisitorVisitField;
    }

    @Override // org.jetbrains.kotlin.codegen.ClassBuilder
    public MethodVisitor newMethod(JvmDeclarationOrigin jvmDeclarationOrigin, int i, String str, String str2, String str3, String[] strArr) {
        if (jvmDeclarationOrigin == null) {
            $$$reportNull$$$0(5);
        }
        if (str == null) {
            $$$reportNull$$$0(6);
        }
        if (str2 == null) {
            $$$reportNull$$$0(7);
        }
        MethodVisitor methodVisitorVisitMethod = getVisitor().visitMethod(i, str, str2, str3, strArr);
        if (methodVisitorVisitMethod == null && (methodVisitorVisitMethod = EMPTY_METHOD_VISITOR) == null) {
            $$$reportNull$$$0(8);
        }
        return methodVisitorVisitMethod;
    }

    @Override // org.jetbrains.kotlin.codegen.ClassBuilder
    public RecordComponentVisitor newRecordComponent(String str, String str2, String str3) {
        if (str == null) {
            $$$reportNull$$$0(10);
        }
        if (str2 == null) {
            $$$reportNull$$$0(11);
        }
        RecordComponentVisitor recordComponentVisitorVisitRecordComponent = getVisitor().visitRecordComponent(str, str2, str3);
        if (recordComponentVisitorVisitRecordComponent == null && (recordComponentVisitorVisitRecordComponent = EMPTY_RECORD_VISITOR) == null) {
            $$$reportNull$$$0(12);
        }
        return recordComponentVisitorVisitRecordComponent;
    }

    @Override // org.jetbrains.kotlin.codegen.ClassBuilder
    public void visitInnerClass(String str, String str2, String str3, int i) {
        if (str == null) {
            $$$reportNull$$$0(23);
        }
        getVisitor().visitInnerClass(str, str2, str3, i);
    }

    @Override // org.jetbrains.kotlin.codegen.ClassBuilder
    public void visitOuterClass(String str, String str2, String str3) {
        if (str == null) {
            $$$reportNull$$$0(22);
        }
        getVisitor().visitOuterClass(str, str2, str3);
    }

    @Override // org.jetbrains.kotlin.codegen.ClassBuilder
    public void visitSMAP(SourceMapper sourceMapper, boolean z, boolean z2) {
        String sourceFileName;
        if (sourceMapper == null) {
            $$$reportNull$$$0(21);
        }
        if ((!sourceMapper.isTrivial() || z2) && !sourceMapper.getResultMappings().isEmpty()) {
            List<FileMapping> resultMappings = sourceMapper.getResultMappings();
            visitSource(resultMappings.get(0).getName(), SMAPBuilder.INSTANCE.build(resultMappings, z));
            return;
        }
        SourceInfo sourceInfo = sourceMapper.getSourceInfo();
        if (sourceInfo == null || (sourceFileName = sourceInfo.getSourceFileName()) == null) {
            return;
        }
        visitSource(sourceFileName, null);
    }

    @Override // org.jetbrains.kotlin.codegen.ClassBuilder
    public void visitSource(String str, String str2) {
        if (str == null) {
            $$$reportNull$$$0(20);
        }
        this.sourceName = str;
        this.debugInfo = str2;
    }
}
