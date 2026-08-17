package org.jetbrains.kotlin.diagnostics.rendering;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.ClassifierDescriptorWithTypeParameters;
import org.jetbrains.kotlin.renderer.DescriptorRenderer;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0004¨\u0006\u0005"}, d2 = {"renderKindWithName", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/ClassifierDescriptorWithTypeParameters;", "renderKind", "Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "org.jetbrains.kotlin:frontend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DiagnosticRendererUtilKt {
    public static final String renderKind(ClassDescriptor classDescriptor) {
        classDescriptor.getClass();
        return DescriptorRenderer.Companion.getClassifierKindPrefix(classDescriptor);
    }

    public static final String renderKindWithName(ClassifierDescriptorWithTypeParameters classifierDescriptorWithTypeParameters) {
        classifierDescriptorWithTypeParameters.getClass();
        return DescriptorRenderer.Companion.getClassifierKindPrefix(classifierDescriptorWithTypeParameters) + " '" + classifierDescriptorWithTypeParameters.getName() + '\'';
    }
}
