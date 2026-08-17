package org.jetbrains.kotlin.codegen.inline;

import com.intellij.psi.PsiElement;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.ClassBuilder;
import org.jetbrains.kotlin.codegen.DelegatingClassBuilder;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.jvm.diagnostics.JvmDeclarationOrigin;
import org.jetbrains.org.objectweb.asm.AnnotationVisitor;
import org.jetbrains.org.objectweb.asm.ClassVisitor;
import org.jetbrains.org.objectweb.asm.FieldVisitor;
import org.jetbrains.org.objectweb.asm.MethodVisitor;
import org.jetbrains.org.objectweb.asm.RecordComponentVisitor;
import org.jetbrains.org.objectweb.asm.commons.AnnotationRemapper;
import org.jetbrains.org.objectweb.asm.commons.ClassRemapper;
import org.jetbrains.org.objectweb.asm.commons.LocalVariablesSorter;
import org.jetbrains.org.objectweb.asm.commons.MethodRemapper;
import org.jetbrains.org.objectweb.asm.commons.Remapper;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public class RemappingClassBuilder extends DelegatingClassBuilder {
    private final ClassBuilder builder;
    private final Remapper remapper;
    private final Map<String, FieldVisitor> spilledCoroutineVariables;
    private ClassVisitor visitor;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 2 || i == 9 || i == 10 || i == 19 || i == 20) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 2 || i == 9 || i == 10 || i == 19 || i == 20) ? 2 : 3];
        switch (i) {
            case 1:
                objArr[0] = "remapper";
                break;
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
            case 9:
            case 10:
            case 19:
            case 20:
                objArr[0] = "org/jetbrains/kotlin/codegen/inline/RemappingClassBuilder";
                break;
            case 3:
            case 7:
            case 12:
            case 15:
            case 17:
                objArr[0] = ModuleXmlParser.NAME;
                break;
            case 4:
                objArr[0] = "superName";
                break;
            case 5:
                objArr[0] = "interfaces";
                break;
            case 6:
            case 11:
                objArr[0] = "origin";
                break;
            case 8:
            case 13:
            case 14:
            case 18:
                objArr[0] = "desc";
                break;
            case 16:
                objArr[0] = "owner";
                break;
            default:
                objArr[0] = "builder";
                break;
        }
        if (i == 2) {
            objArr[1] = "getDelegate";
        } else if (i == 9 || i == 10) {
            objArr[1] = "newField";
        } else if (i == 19) {
            objArr[1] = "newRecordComponent";
        } else if (i != 20) {
            objArr[1] = "org/jetbrains/kotlin/codegen/inline/RemappingClassBuilder";
        } else {
            objArr[1] = "getVisitor";
        }
        switch (i) {
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
            case 9:
            case 10:
            case 19:
            case 20:
                break;
            case 3:
            case 4:
            case 5:
                objArr[2] = "defineClass";
                break;
            case 6:
            case 7:
            case 8:
                objArr[2] = "newField";
                break;
            case 11:
            case 12:
            case 13:
                objArr[2] = "newMethod";
                break;
            case 14:
                objArr[2] = "newAnnotation";
                break;
            case 15:
                objArr[2] = "visitInnerClass";
                break;
            case 16:
                objArr[2] = "visitOuterClass";
                break;
            case 17:
            case 18:
                objArr[2] = "newRecordComponent";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 2 && i != 9 && i != 10 && i != 19 && i != 20) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public RemappingClassBuilder(ClassBuilder classBuilder, Remapper remapper) {
        if (classBuilder == null) {
            $$$reportNull$$$0(0);
        }
        if (remapper == null) {
            $$$reportNull$$$0(1);
        }
        this.spilledCoroutineVariables = new HashMap();
        this.visitor = null;
        this.builder = classBuilder;
        this.remapper = remapper;
    }

    private static boolean isSpilledCoroutineVariableName(String str) {
        if (str.length() < 3) {
            return false;
        }
        char cCharAt = str.charAt(0);
        if (cCharAt != 'F' && cCharAt != 'L' && cCharAt != 'S' && cCharAt != 'Z' && cCharAt != 'I' && cCharAt != 'J') {
            switch (cCharAt) {
                case 'B':
                case 'C':
                case 'D':
                    break;
                default:
                    return false;
            }
        }
        if (str.charAt(1) != '$') {
            return false;
        }
        for (int i = 2; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    @Override // org.jetbrains.kotlin.codegen.DelegatingClassBuilder, org.jetbrains.kotlin.codegen.ClassBuilder
    public void defineClass(PsiElement psiElement, int i, int i2, String str, String str2, String str3, String[] strArr) {
        if (str == null) {
            $$$reportNull$$$0(3);
        }
        if (str3 == null) {
            $$$reportNull$$$0(4);
        }
        if (strArr == null) {
            $$$reportNull$$$0(5);
        }
        super.defineClass(psiElement, i, i2, this.remapper.mapType(str), this.remapper.mapSignature(str2, false), this.remapper.mapType(str3), this.remapper.mapTypes(strArr));
    }

    @Override // org.jetbrains.kotlin.codegen.DelegatingClassBuilder
    public ClassBuilder getDelegate() {
        ClassBuilder classBuilder = this.builder;
        if (classBuilder == null) {
            $$$reportNull$$$0(2);
        }
        return classBuilder;
    }

    @Override // org.jetbrains.kotlin.codegen.DelegatingClassBuilder, org.jetbrains.kotlin.codegen.ClassBuilder
    public ClassVisitor getVisitor() {
        if (this.visitor == null) {
            this.visitor = new ClassRemapper(this.builder.getVisitor(), this.remapper);
        }
        ClassVisitor classVisitor = this.visitor;
        if (classVisitor == null) {
            $$$reportNull$$$0(20);
        }
        return classVisitor;
    }

    @Override // org.jetbrains.kotlin.codegen.DelegatingClassBuilder, org.jetbrains.kotlin.codegen.ClassBuilder
    public AnnotationVisitor newAnnotation(String str, boolean z) {
        if (str == null) {
            $$$reportNull$$$0(14);
        }
        return new AnnotationRemapper((String) null, this.builder.newAnnotation(this.remapper.mapDesc(str), z), this.remapper);
    }

    @Override // org.jetbrains.kotlin.codegen.DelegatingClassBuilder, org.jetbrains.kotlin.codegen.ClassBuilder
    public FieldVisitor newField(JvmDeclarationOrigin jvmDeclarationOrigin, int i, String str, String str2, String str3, Object obj) {
        if (jvmDeclarationOrigin == null) {
            $$$reportNull$$$0(6);
        }
        if (str == null) {
            $$$reportNull$$$0(7);
        }
        if (str2 == null) {
            $$$reportNull$$$0(8);
        }
        FieldVisitor fieldVisitor = this.spilledCoroutineVariables.get(str);
        if (fieldVisitor != null) {
            return fieldVisitor;
        }
        FieldVisitor fieldRemapper = new org.jetbrains.org.objectweb.asm.commons.FieldRemapper(this.builder.newField(jvmDeclarationOrigin, i, str, this.remapper.mapDesc(str2), this.remapper.mapSignature(str3, true), obj), this.remapper);
        if (isSpilledCoroutineVariableName(str)) {
            this.spilledCoroutineVariables.put(str, fieldRemapper);
        }
        return fieldRemapper;
    }

    @Override // org.jetbrains.kotlin.codegen.DelegatingClassBuilder, org.jetbrains.kotlin.codegen.ClassBuilder
    public MethodVisitor newMethod(JvmDeclarationOrigin jvmDeclarationOrigin, int i, String str, String str2, String str3, String[] strArr) {
        if (jvmDeclarationOrigin == null) {
            $$$reportNull$$$0(11);
        }
        if (str == null) {
            $$$reportNull$$$0(12);
        }
        if (str2 == null) {
            $$$reportNull$$$0(13);
        }
        String strMapMethodDesc = this.remapper.mapMethodDesc(str2);
        return new MethodRemapper(new LocalVariablesSorter(i, strMapMethodDesc, this.builder.newMethod(jvmDeclarationOrigin, i, str, strMapMethodDesc, this.remapper.mapSignature(str3, false), strArr)), this.remapper);
    }

    @Override // org.jetbrains.kotlin.codegen.DelegatingClassBuilder, org.jetbrains.kotlin.codegen.ClassBuilder
    public RecordComponentVisitor newRecordComponent(String str, String str2, String str3) {
        if (str == null) {
            $$$reportNull$$$0(17);
        }
        if (str2 == null) {
            $$$reportNull$$$0(18);
        }
        RecordComponentVisitor recordComponentVisitorVisitRecordComponent = getVisitor().visitRecordComponent(str, str2, str3);
        if (recordComponentVisitorVisitRecordComponent == null) {
            $$$reportNull$$$0(19);
        }
        return recordComponentVisitorVisitRecordComponent;
    }

    @Override // org.jetbrains.kotlin.codegen.DelegatingClassBuilder, org.jetbrains.kotlin.codegen.ClassBuilder
    public void visitInnerClass(String str, String str2, String str3, int i) {
        if (str == null) {
            $$$reportNull$$$0(15);
        }
        getVisitor().visitInnerClass(str, str2, str3, i);
    }

    @Override // org.jetbrains.kotlin.codegen.DelegatingClassBuilder, org.jetbrains.kotlin.codegen.ClassBuilder
    public void visitOuterClass(String str, String str2, String str3) {
        if (str == null) {
            $$$reportNull$$$0(16);
        }
        getVisitor().visitOuterClass(str, str2, str3);
    }
}
