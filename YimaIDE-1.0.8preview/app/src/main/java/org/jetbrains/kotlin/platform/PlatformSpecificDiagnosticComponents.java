package org.jetbrains.kotlin.platform;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.container.DefaultImplementation;
import org.jetbrains.kotlin.container.PlatformSpecificExtension;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationDescriptor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@DefaultImplementation(impl = Default.class)
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\bJ\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/platform/PlatformSpecificDiagnosticComponents;", "Lorg/jetbrains/kotlin/container/PlatformSpecificExtension;", "isNullabilityAnnotation", Argument.Delimiters.none, "annotationDescriptor", "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationDescriptor;", "containingDeclaration", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "Default", "org.jetbrains.kotlin:descriptors"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface PlatformSpecificDiagnosticComponents extends PlatformSpecificExtension<PlatformSpecificDiagnosticComponents> {
    boolean isNullabilityAnnotation(AnnotationDescriptor annotationDescriptor, DeclarationDescriptor containingDeclaration);
}
