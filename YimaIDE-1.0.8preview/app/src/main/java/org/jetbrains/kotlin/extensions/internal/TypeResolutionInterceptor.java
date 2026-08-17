package org.jetbrains.kotlin.extensions.internal;

import com.intellij.openapi.project.Project;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.impl.AnonymousFunctionDescriptor;
import org.jetbrains.kotlin.extensions.ProjectExtensionDescriptor;
import org.jetbrains.kotlin.psi.KtElement;
import org.jetbrains.kotlin.psi.KtLambdaExpression;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.types.expressions.ExpressionTypingContext;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\nJ\"\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0011J\u0006\u0010\u0015\u001a\u00020\u0016R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/extensions/internal/TypeResolutionInterceptor;", Argument.Delimiters.none, "project", "Lcom/intellij/openapi/project/Project;", "<init>", "(Lcom/intellij/openapi/project/Project;)V", "extensions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/extensions/internal/TypeResolutionInterceptorExtension;", "interceptFunctionLiteralDescriptor", "Lorg/jetbrains/kotlin/descriptors/impl/AnonymousFunctionDescriptor;", "expression", "Lorg/jetbrains/kotlin/psi/KtLambdaExpression;", "context", "Lorg/jetbrains/kotlin/types/expressions/ExpressionTypingContext;", "descriptor", "interceptType", "Lorg/jetbrains/kotlin/types/KotlinType;", "element", "Lorg/jetbrains/kotlin/psi/KtElement;", "resultType", "isEmpty", Argument.Delimiters.none, "Companion", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TypeResolutionInterceptor {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final List<TypeResolutionInterceptorExtension> extensions;

    public TypeResolutionInterceptor(Project project) {
        project.getClass();
        this.extensions = INSTANCE.getInstances(project);
    }

    public final AnonymousFunctionDescriptor interceptFunctionLiteralDescriptor(KtLambdaExpression expression, ExpressionTypingContext context, AnonymousFunctionDescriptor descriptor) {
        expression.getClass();
        context.getClass();
        descriptor.getClass();
        Iterator<T> it = this.extensions.iterator();
        while (it.hasNext()) {
            descriptor = ((TypeResolutionInterceptorExtension) it.next()).interceptFunctionLiteralDescriptor(expression, context, descriptor);
        }
        return descriptor;
    }

    public final KotlinType interceptType(KtElement element, ExpressionTypingContext context, KotlinType resultType) {
        element.getClass();
        context.getClass();
        if (resultType == null) {
            return null;
        }
        Iterator<T> it = this.extensions.iterator();
        while (it.hasNext()) {
            resultType = ((TypeResolutionInterceptorExtension) it.next()).interceptType(element, context, resultType);
        }
        return resultType;
    }

    public final boolean isEmpty() {
        return this.extensions.isEmpty();
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/extensions/internal/TypeResolutionInterceptor$Companion;", "Lorg/jetbrains/kotlin/extensions/ProjectExtensionDescriptor;", "Lorg/jetbrains/kotlin/extensions/internal/TypeResolutionInterceptorExtension;", "<init>", "()V", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion extends ProjectExtensionDescriptor<TypeResolutionInterceptorExtension> {
        private Companion() {
            super("org.jetbrains.kotlin.extensions.internal.typeResolutionInterceptorExtension", TypeResolutionInterceptorExtension.class);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
