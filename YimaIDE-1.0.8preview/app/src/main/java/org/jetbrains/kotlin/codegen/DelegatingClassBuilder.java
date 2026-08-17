package org.jetbrains.kotlin.codegen;

import com.intellij.psi.PsiElement;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
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
public abstract class DelegatingClassBuilder implements ClassBuilder {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 3 || i == 7 || i == 22 || i == 10 || i == 11 || i == 13 || i == 14) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 3 || i == 7 || i == 22 || i == 10 || i == 11 || i == 13 || i == 14) ? 2 : 3];
        switch (i) {
            case 1:
            case 5:
            case 8:
            case 15:
            case 18:
            case 21:
                objArr[0] = ModuleXmlParser.NAME;
                break;
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
            case 6:
            case 9:
            case 12:
                objArr[0] = "desc";
                break;
            case 3:
            case 7:
            case 10:
            case 11:
            case 13:
            case 14:
            case 22:
                objArr[0] = "org/jetbrains/kotlin/codegen/DelegatingClassBuilder";
                break;
            case 4:
            default:
                objArr[0] = "origin";
                break;
            case 16:
                objArr[0] = "superName";
                break;
            case 17:
                objArr[0] = "interfaces";
                break;
            case 19:
                objArr[0] = "smap";
                break;
            case 20:
                objArr[0] = "owner";
                break;
        }
        if (i == 3) {
            objArr[1] = "newField";
        } else if (i == 7) {
            objArr[1] = "newMethod";
        } else if (i == 22) {
            objArr[1] = "getThisName";
        } else if (i == 10) {
            objArr[1] = "newRecordComponent";
        } else if (i == 11) {
            objArr[1] = "getSerializationBindings";
        } else if (i == 13) {
            objArr[1] = "newAnnotation";
        } else if (i != 14) {
            objArr[1] = "org/jetbrains/kotlin/codegen/DelegatingClassBuilder";
        } else {
            objArr[1] = "getVisitor";
        }
        switch (i) {
            case 3:
            case 7:
            case 10:
            case 11:
            case 13:
            case 14:
            case 22:
                break;
            case 4:
            case 5:
            case 6:
                objArr[2] = "newMethod";
                break;
            case 8:
            case 9:
                objArr[2] = "newRecordComponent";
                break;
            case 12:
                objArr[2] = "newAnnotation";
                break;
            case 15:
            case 16:
            case 17:
                objArr[2] = "defineClass";
                break;
            case 18:
                objArr[2] = "visitSource";
                break;
            case 19:
                objArr[2] = "visitSMAP";
                break;
            case 20:
                objArr[2] = "visitOuterClass";
                break;
            case 21:
                objArr[2] = "visitInnerClass";
                break;
            default:
                objArr[2] = "newField";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 3 && i != 7 && i != 22 && i != 10 && i != 11 && i != 13 && i != 14) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    @Override // org.jetbrains.kotlin.codegen.ClassBuilder
    public void defineClass(PsiElement psiElement, int i, int i2, String str, String str2, String str3, String[] strArr) {
        if (str == null) {
            $$$reportNull$$$0(15);
        }
        if (str3 == null) {
            $$$reportNull$$$0(16);
        }
        if (strArr == null) {
            $$$reportNull$$$0(17);
        }
        getDelegate().defineClass(psiElement, i, i2, str, str2, str3, strArr);
    }

    @Override // org.jetbrains.kotlin.codegen.ClassBuilder
    public void done(boolean z) {
        getDelegate().done(z);
    }

    public abstract ClassBuilder getDelegate();

    @Override // org.jetbrains.kotlin.codegen.ClassBuilder
    public JvmSerializationBindings getSerializationBindings() {
        JvmSerializationBindings serializationBindings = getDelegate().getSerializationBindings();
        if (serializationBindings == null) {
            $$$reportNull$$$0(11);
        }
        return serializationBindings;
    }

    @Override // org.jetbrains.kotlin.codegen.ClassBuilder
    public String getThisName() {
        String thisName = getDelegate().getThisName();
        if (thisName == null) {
            $$$reportNull$$$0(22);
        }
        return thisName;
    }

    @Override // org.jetbrains.kotlin.codegen.ClassBuilder
    public ClassVisitor getVisitor() {
        ClassVisitor visitor = getDelegate().getVisitor();
        if (visitor == null) {
            $$$reportNull$$$0(14);
        }
        return visitor;
    }

    @Override // org.jetbrains.kotlin.codegen.ClassBuilder
    public AnnotationVisitor newAnnotation(String str, boolean z) {
        if (str == null) {
            $$$reportNull$$$0(12);
        }
        AnnotationVisitor annotationVisitorNewAnnotation = getDelegate().newAnnotation(str, z);
        if (annotationVisitorNewAnnotation == null) {
            $$$reportNull$$$0(13);
        }
        return annotationVisitorNewAnnotation;
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
        FieldVisitor fieldVisitorNewField = getDelegate().newField(jvmDeclarationOrigin, i, str, str2, str3, obj);
        if (fieldVisitorNewField == null) {
            $$$reportNull$$$0(3);
        }
        return fieldVisitorNewField;
    }

    @Override // org.jetbrains.kotlin.codegen.ClassBuilder
    public MethodVisitor newMethod(JvmDeclarationOrigin jvmDeclarationOrigin, int i, String str, String str2, String str3, String[] strArr) {
        if (jvmDeclarationOrigin == null) {
            $$$reportNull$$$0(4);
        }
        if (str == null) {
            $$$reportNull$$$0(5);
        }
        if (str2 == null) {
            $$$reportNull$$$0(6);
        }
        MethodVisitor methodVisitorNewMethod = getDelegate().newMethod(jvmDeclarationOrigin, i, str, str2, str3, strArr);
        if (methodVisitorNewMethod == null) {
            $$$reportNull$$$0(7);
        }
        return methodVisitorNewMethod;
    }

    @Override // org.jetbrains.kotlin.codegen.ClassBuilder
    public RecordComponentVisitor newRecordComponent(String str, String str2, String str3) {
        if (str == null) {
            $$$reportNull$$$0(8);
        }
        if (str2 == null) {
            $$$reportNull$$$0(9);
        }
        RecordComponentVisitor recordComponentVisitorNewRecordComponent = getDelegate().newRecordComponent(str, str2, str3);
        if (recordComponentVisitorNewRecordComponent == null) {
            $$$reportNull$$$0(10);
        }
        return recordComponentVisitorNewRecordComponent;
    }

    @Override // org.jetbrains.kotlin.codegen.ClassBuilder
    public void visitInnerClass(String str, String str2, String str3, int i) {
        if (str == null) {
            $$$reportNull$$$0(21);
        }
        getDelegate().visitInnerClass(str, str2, str3, i);
    }

    @Override // org.jetbrains.kotlin.codegen.ClassBuilder
    public void visitOuterClass(String str, String str2, String str3) {
        if (str == null) {
            $$$reportNull$$$0(20);
        }
        getDelegate().visitOuterClass(str, str2, str3);
    }

    @Override // org.jetbrains.kotlin.codegen.ClassBuilder
    public void visitSMAP(SourceMapper sourceMapper, boolean z, boolean z2) {
        if (sourceMapper == null) {
            $$$reportNull$$$0(19);
        }
        getDelegate().visitSMAP(sourceMapper, z, z2);
    }

    @Override // org.jetbrains.kotlin.codegen.ClassBuilder
    public void visitSource(String str, String str2) {
        if (str == null) {
            $$$reportNull$$$0(18);
        }
        getDelegate().visitSource(str, str2);
    }
}
