package org.jetbrains.kotlin.codegen;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.state.JvmBackendConfig;
import org.jetbrains.kotlin.config.JvmAnalysisFlags;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.load.java.JvmAnnotationNames;
import org.jetbrains.kotlin.load.kotlin.header.KotlinClassHeader;
import org.jetbrains.org.objectweb.asm.AnnotationVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001aB\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\r¨\u0006\u000f"}, d2 = {"writeKotlinMetadata", Argument.Delimiters.none, "cb", "Lorg/jetbrains/kotlin/codegen/ClassBuilder;", "config", "Lorg/jetbrains/kotlin/codegen/state/JvmBackendConfig;", "kind", "Lorg/jetbrains/kotlin/load/kotlin/header/KotlinClassHeader$Kind;", "publicAbi", Argument.Delimiters.none, "extraFlags", Argument.Delimiters.none, "action", "Lkotlin/Function1;", "Lorg/jetbrains/org/objectweb/asm/AnnotationVisitor;", "org.jetbrains.kotlin:backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class WriteKotlinMetadataKt {
    public static final void writeKotlinMetadata(ClassBuilder classBuilder, JvmBackendConfig jvmBackendConfig, KotlinClassHeader.Kind kind, boolean z, int i, Function1<? super AnnotationVisitor, Unit> function1) {
        classBuilder.getClass();
        jvmBackendConfig.getClass();
        kind.getClass();
        function1.getClass();
        AnnotationVisitor annotationVisitorNewAnnotation = classBuilder.newAnnotation(JvmAnnotationNames.METADATA_DESC, true);
        annotationVisitorNewAnnotation.getClass();
        annotationVisitorNewAnnotation.visit("mv", jvmBackendConfig.getMetadataVersion().toArray());
        annotationVisitorNewAnnotation.visit("k", Integer.valueOf(kind.getId()));
        if (jvmBackendConfig.getLanguageVersionSettings().isPreRelease()) {
            i |= 2;
        }
        if (((Boolean) jvmBackendConfig.getLanguageVersionSettings().getFlag(JvmAnalysisFlags.getStrictMetadataVersionSemantics())).booleanValue()) {
            i |= 8;
        }
        if (z) {
            i |= 128;
        }
        if (i != 0) {
            annotationVisitorNewAnnotation.visit("xi", Integer.valueOf(i));
        }
        function1.invoke(annotationVisitorNewAnnotation);
        annotationVisitorNewAnnotation.visitEnd();
    }
}
