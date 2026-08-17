package org.jetbrains.kotlin.fir.java.deserialization;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.StandardTypes;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirArgumentUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirClassReferenceExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.builder.FirArgumentListBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirCollectionLiteralBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirGetClassCallBuilder;
import org.jetbrains.kotlin.fir.types.ArrayUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.load.kotlin.KotlinJvmBinaryClass;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.constants.ClassLiteralValue;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000=\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u0018\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000bH\u0016J\b\u0010\u0013\u001a\u00020\u0006H\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"org/jetbrains/kotlin/fir/java/deserialization/AnnotationsLoader$AnnotationsLoaderVisitorImpl$visitArray$1", "Lorg/jetbrains/kotlin/load/kotlin/KotlinJvmBinaryClass$AnnotationArrayArgumentVisitor;", "elements", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "visit", Argument.Delimiters.none, "value", Argument.Delimiters.none, "visitEnum", "enumClassId", "Lorg/jetbrains/kotlin/name/ClassId;", "enumEntryName", "Lorg/jetbrains/kotlin/name/Name;", "visitClassLiteral", "Lorg/jetbrains/kotlin/resolve/constants/ClassLiteralValue;", "visitAnnotation", "Lorg/jetbrains/kotlin/load/kotlin/KotlinJvmBinaryClass$AnnotationArgumentVisitor;", "classId", "visitEnd", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class AnnotationsLoader$AnnotationsLoaderVisitorImpl$visitArray$1 implements KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor {
    final /* synthetic */ Name $name;
    private final List<FirExpression> elements = new ArrayList();
    final /* synthetic */ AnnotationsLoader.AnnotationsLoaderVisitorImpl this$0;
    final /* synthetic */ AnnotationsLoader this$1;

    public AnnotationsLoader$AnnotationsLoaderVisitorImpl$visitArray$1(AnnotationsLoader.AnnotationsLoaderVisitorImpl annotationsLoaderVisitorImpl, AnnotationsLoader annotationsLoader, Name name) {
        this.this$0 = annotationsLoaderVisitorImpl;
        this.this$1 = annotationsLoader;
        this.$name = name;
    }

    public void visit(Object value) {
        this.elements.add(this.this$0.createConstant(value));
    }

    public KotlinJvmBinaryClass.AnnotationArgumentVisitor visitAnnotation(ClassId classId) {
        classId.getClass();
        final ArrayList arrayList = new ArrayList();
        final KotlinJvmBinaryClass.AnnotationArgumentVisitor annotationArgumentVisitorLoadAnnotation = this.this$1.loadAnnotation(classId, arrayList);
        return new KotlinJvmBinaryClass.AnnotationArgumentVisitor(annotationArgumentVisitorLoadAnnotation, this, arrayList) { // from class: org.jetbrains.kotlin.fir.java.deserialization.AnnotationsLoader$AnnotationsLoaderVisitorImpl$visitArray$1$visitAnnotation$1
            private final /* synthetic */ KotlinJvmBinaryClass.AnnotationArgumentVisitor $$delegate_0;
            final /* synthetic */ List<FirAnnotation> $list;
            final /* synthetic */ KotlinJvmBinaryClass.AnnotationArgumentVisitor $visitor;
            final /* synthetic */ AnnotationsLoader$AnnotationsLoaderVisitorImpl$visitArray$1 this$0;

            {
                this.$visitor = annotationArgumentVisitorLoadAnnotation;
                this.this$0 = this;
                this.$list = arrayList;
                this.$$delegate_0 = annotationArgumentVisitorLoadAnnotation;
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
                this.$visitor.visitEnd();
                this.this$0.elements.add(CollectionsKt.single(this.$list));
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
        FirClassReferenceExpression firClassReferenceExpression = this.this$0.toFirClassReferenceExpression(value);
        if (firClassReferenceExpression == null) {
            return;
        }
        List<FirExpression> list = this.elements;
        FirGetClassCallBuilder firGetClassCallBuilder = new FirGetClassCallBuilder();
        firGetClassCallBuilder.setArgumentList(FirArgumentUtilKt.buildUnaryArgumentList(firClassReferenceExpression));
        firGetClassCallBuilder.setConeTypeOrNull(FirTypeUtilsKt.getResolvedType(firClassReferenceExpression));
        list.add(firGetClassCallBuilder.mo289build());
    }

    public void visitEnd() {
        AnnotationsLoader.AnnotationsLoaderVisitorImpl annotationsLoaderVisitorImpl = this.this$0;
        Name name = this.$name;
        FirCollectionLiteralBuilder firCollectionLiteralBuilder = new FirCollectionLiteralBuilder();
        firCollectionLiteralBuilder.setConeTypeOrNull(ArrayUtilsKt.createOutArrayType$default(StandardTypes.INSTANCE.getAny(), false, false, 3, null));
        FirArgumentListBuilder firArgumentListBuilder = new FirArgumentListBuilder();
        CollectionsKt.addAll(firArgumentListBuilder.getArguments(), this.elements);
        firCollectionLiteralBuilder.setArgumentList(firArgumentListBuilder.build());
        Unit unit = Unit.INSTANCE;
        annotationsLoaderVisitorImpl.visitExpression(name, firCollectionLiteralBuilder.mo289build());
    }

    public void visitEnum(ClassId enumClassId, Name enumEntryName) {
        enumClassId.getClass();
        enumEntryName.getClass();
        this.elements.add(this.this$1.createEnumEntryAccess(enumClassId, enumEntryName));
    }
}
