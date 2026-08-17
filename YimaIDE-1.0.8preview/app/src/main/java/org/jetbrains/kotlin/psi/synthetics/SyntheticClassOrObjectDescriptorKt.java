package org.jetbrains.kotlin.psi.synthetics;

import com.intellij.psi.PsiElement;
import kotlin.Metadata;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.psi.KtPureElement;
import org.jetbrains.kotlin.resolve.BindingContext;
import org.jetbrains.kotlin.resolve.BindingContextUtils;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"findClassDescriptor", "Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "Lorg/jetbrains/kotlin/psi/KtPureElement;", "bindingContext", "Lorg/jetbrains/kotlin/resolve/BindingContext;", "org.jetbrains.kotlin:frontend"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class SyntheticClassOrObjectDescriptorKt {
    public static final ClassDescriptor findClassDescriptor(KtPureElement ktPureElement, BindingContext bindingContext) {
        ktPureElement.getClass();
        bindingContext.getClass();
        if (ktPureElement instanceof PsiElement) {
            Object notNull = BindingContextUtils.getNotNull(bindingContext, BindingContext.CLASS, ktPureElement);
            notNull.getClass();
            return (ClassDescriptor) notNull;
        }
        if (ktPureElement instanceof SyntheticClassOrObjectDescriptor.SyntheticDeclaration) {
            return ((SyntheticClassOrObjectDescriptor.SyntheticDeclaration) ktPureElement).descriptor();
        }
        e4b.a(ktPureElement, " shall be PsiElement or SyntheticClassOrObjectDescriptor.SyntheticDeclaration");
        return null;
    }
}
