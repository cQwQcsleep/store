package org.jetbrains.kotlin.js.resolve.diagnostics;

import com.intellij.psi.PsiElement;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.descriptors.CallableMemberDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptorWithSource;
import org.jetbrains.kotlin.descriptors.SourceElement;
import org.jetbrains.kotlin.resolve.source.PsiSourceElementKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"findPsi", "Lcom/intellij/psi/PsiElement;", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "org.jetbrains.kotlin:js.frontend"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class SourceLocationUtilsKt {
    public static final PsiElement findPsi(DeclarationDescriptor declarationDescriptor) {
        SourceElement source;
        declarationDescriptor.getClass();
        PsiElement psi = null;
        DeclarationDescriptorWithSource declarationDescriptorWithSource = declarationDescriptor instanceof DeclarationDescriptorWithSource ? (DeclarationDescriptorWithSource) declarationDescriptor : null;
        if (declarationDescriptorWithSource != null && (source = declarationDescriptorWithSource.getSource()) != null) {
            psi = PsiSourceElementKt.getPsi(source);
        }
        if (psi == null && (declarationDescriptor instanceof CallableMemberDescriptor)) {
            CallableMemberDescriptor callableMemberDescriptor = (CallableMemberDescriptor) declarationDescriptor;
            if (callableMemberDescriptor.getKind() == CallableMemberDescriptor.Kind.FAKE_OVERRIDE) {
                Collection<CallableMemberDescriptor> overriddenDescriptors = callableMemberDescriptor.getOverriddenDescriptors();
                overriddenDescriptors.getClass();
                ArrayList arrayList = new ArrayList();
                for (CallableMemberDescriptor callableMemberDescriptor2 : overriddenDescriptors) {
                    callableMemberDescriptor2.getClass();
                    PsiElement psiElementFindPsi = findPsi(callableMemberDescriptor2);
                    if (psiElementFindPsi != null) {
                        arrayList.add(psiElementFindPsi);
                    }
                }
                return (PsiElement) CollectionsKt.firstOrNull(arrayList);
            }
        }
        return psi;
    }
}
