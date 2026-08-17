package org.jetbrains.kotlin.fir.java.deserialization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.SourceElement;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.java.deserialization.JvmBinaryAnnotationDeserializerKt;
import org.jetbrains.kotlin.load.kotlin.KotlinClassFinder;
import org.jetbrains.kotlin.load.kotlin.KotlinJvmBinaryClass;
import org.jetbrains.kotlin.load.kotlin.MemberSignature;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a&\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002¨\u0006\t"}, d2 = {"loadMemberAnnotations", "Lorg/jetbrains/kotlin/fir/java/deserialization/MemberAnnotations;", "Lorg/jetbrains/kotlin/fir/FirSession;", "kotlinBinaryClass", "Lorg/jetbrains/kotlin/load/kotlin/KotlinJvmBinaryClass;", "byteContent", Argument.Delimiters.none, "kotlinClassFinder", "Lorg/jetbrains/kotlin/load/kotlin/KotlinClassFinder;", "org.jetbrains.kotlin:fir-jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmBinaryAnnotationDeserializerKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final MemberAnnotations loadMemberAnnotations(FirSession firSession, KotlinJvmBinaryClass kotlinJvmBinaryClass, byte[] bArr, KotlinClassFinder kotlinClassFinder) {
        final HashMap map = new HashMap();
        final AnnotationsLoader annotationsLoader = new AnnotationsLoader(firSession, kotlinClassFinder);
        final HashMap map2 = new HashMap();
        kotlinJvmBinaryClass.visitMembers(new KotlinJvmBinaryClass.MemberVisitor() { // from class: org.jetbrains.kotlin.fir.java.deserialization.JvmBinaryAnnotationDeserializerKt.loadMemberAnnotations.1

            /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.java.deserialization.JvmBinaryAnnotationDeserializerKt$loadMemberAnnotations$1$AnnotationVisitorForMethod */
            @Metadata(d1 = {"\u00000\n\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0002\u0000\u0001\b\u008a\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\"\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\n\u0010\u000f\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\u0010"}, d2 = {"org/jetbrains/kotlin/fir/java/deserialization/JvmBinaryAnnotationDeserializerKt$loadMemberAnnotations$1.AnnotationVisitorForMethod", "org/jetbrains/kotlin/fir/java/deserialization/JvmBinaryAnnotationDeserializerKt$loadMemberAnnotations$1.MemberAnnotationVisitor", "Lorg/jetbrains/kotlin/load/kotlin/KotlinJvmBinaryClass$MethodAnnotationVisitor;", "signature", "Lorg/jetbrains/kotlin/load/kotlin/MemberSignature;", "<init>", "(Lorg/jetbrains/kotlin/fir/java/deserialization/JvmBinaryAnnotationDeserializerKt$loadMemberAnnotations$1;Lorg/jetbrains/kotlin/load/kotlin/MemberSignature;)V", "visitParameterAnnotation", "Lorg/jetbrains/kotlin/load/kotlin/KotlinJvmBinaryClass$AnnotationArgumentVisitor;", "index", Argument.Delimiters.none, "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "source", "Lorg/jetbrains/kotlin/descriptors/SourceElement;", "visitAnnotationMemberDefaultValue", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
            public final class AnnotationVisitorForMethod extends MemberAnnotationVisitor implements KotlinJvmBinaryClass.MethodAnnotationVisitor {
                final /* synthetic */ AnonymousClass1 this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnnotationVisitorForMethod(AnonymousClass1 anonymousClass1, MemberSignature memberSignature) {
                    super(anonymousClass1, memberSignature);
                    memberSignature.getClass();
                    this.this$0 = anonymousClass1;
                }

                public static Unit a(HashMap map, AnnotationVisitorForMethod annotationVisitorForMethod, FirExpression firExpression) {
                    firExpression.getClass();
                    map.put(annotationVisitorForMethod.getSignature(), firExpression);
                    return Unit.INSTANCE;
                }

                public KotlinJvmBinaryClass.AnnotationArgumentVisitor visitAnnotationMemberDefaultValue() {
                    AnonymousClass1 anonymousClass1 = this.this$0;
                    AnnotationsLoader annotationsLoader = annotationsLoader;
                    final HashMap<MemberSignature, FirExpression> map = map2;
                    return annotationsLoader.loadAnnotationMethodDefaultValue$org_jetbrains_kotlin_fir_jvm(new Function1() { // from class: lv7
                        public final Object invoke(Object obj) {
                            return JvmBinaryAnnotationDeserializerKt.AnonymousClass1.AnnotationVisitorForMethod.a(map, this, (FirExpression) obj);
                        }
                    });
                }

                public KotlinJvmBinaryClass.AnnotationArgumentVisitor visitParameterAnnotation(int index, ClassId classId, SourceElement source) {
                    classId.getClass();
                    source.getClass();
                    MemberSignature memberSignatureFromMethodSignatureAndParameterIndex = MemberSignature.Companion.fromMethodSignatureAndParameterIndex(getSignature(), index);
                    List<FirAnnotation> arrayList = map.get(memberSignatureFromMethodSignatureAndParameterIndex);
                    if (arrayList == null) {
                        arrayList = new ArrayList<>();
                        map.put(memberSignatureFromMethodSignatureAndParameterIndex, arrayList);
                    }
                    return annotationsLoader.loadAnnotationIfNotSpecial$org_jetbrains_kotlin_fir_jvm(classId, arrayList);
                }
            }

            /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.java.deserialization.JvmBinaryAnnotationDeserializerKt$loadMemberAnnotations$1$MemberAnnotationVisitor */
            @Metadata(d1 = {"\u00009\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\u009a\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001e\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00020\n`\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"org/jetbrains/kotlin/fir/java/deserialization/JvmBinaryAnnotationDeserializerKt$loadMemberAnnotations$1.MemberAnnotationVisitor", "Lorg/jetbrains/kotlin/load/kotlin/KotlinJvmBinaryClass$AnnotationVisitor;", "signature", "Lorg/jetbrains/kotlin/load/kotlin/MemberSignature;", "<init>", "(Lorg/jetbrains/kotlin/fir/java/deserialization/JvmBinaryAnnotationDeserializerKt$loadMemberAnnotations$1;Lorg/jetbrains/kotlin/load/kotlin/MemberSignature;)V", "getSignature", "()Lorg/jetbrains/kotlin/load/kotlin/MemberSignature;", CoroutineCodegenUtilKt.CONTINUATION_RESULT_FIELD_NAME, "Ljava/util/ArrayList;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "Lkotlin/collections/ArrayList;", "visitAnnotation", "Lorg/jetbrains/kotlin/load/kotlin/KotlinJvmBinaryClass$AnnotationArgumentVisitor;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "source", "Lorg/jetbrains/kotlin/descriptors/SourceElement;", "visitEnd", Argument.Delimiters.none, "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
            public class MemberAnnotationVisitor implements KotlinJvmBinaryClass.AnnotationVisitor {
                private final ArrayList<FirAnnotation> result;
                private final MemberSignature signature;
                final /* synthetic */ AnonymousClass1 this$0;

                public MemberAnnotationVisitor(AnonymousClass1 anonymousClass1, MemberSignature memberSignature) {
                    memberSignature.getClass();
                    this.this$0 = anonymousClass1;
                    this.signature = memberSignature;
                    this.result = new ArrayList<>();
                }

                public final MemberSignature getSignature() {
                    return this.signature;
                }

                public KotlinJvmBinaryClass.AnnotationArgumentVisitor visitAnnotation(ClassId classId, SourceElement source) {
                    classId.getClass();
                    source.getClass();
                    return annotationsLoader.loadAnnotationIfNotSpecial$org_jetbrains_kotlin_fir_jvm(classId, this.result);
                }

                public void visitEnd() {
                    if (this.result.isEmpty()) {
                        return;
                    }
                    map.put(this.signature, this.result);
                }
            }

            public KotlinJvmBinaryClass.AnnotationVisitor visitField(Name name, String desc, Object initializer) {
                name.getClass();
                desc.getClass();
                MemberSignature.Companion companion = MemberSignature.Companion;
                String strAsString = name.asString();
                strAsString.getClass();
                return new MemberAnnotationVisitor(this, companion.fromFieldNameAndDesc(strAsString, desc));
            }

            public KotlinJvmBinaryClass.MethodAnnotationVisitor visitMethod(Name name, String desc) {
                name.getClass();
                desc.getClass();
                MemberSignature.Companion companion = MemberSignature.Companion;
                String strAsString = name.asString();
                strAsString.getClass();
                return new AnnotationVisitorForMethod(this, companion.fromMethodNameAndDesc(strAsString, desc));
            }
        }, bArr);
        return new MemberAnnotations(map, map2);
    }
}
