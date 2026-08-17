package org.jetbrains.kotlin.asJava.elements;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.light.LightIdentifier;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.psi.KtClassOrObject;
import org.jetbrains.kotlin.psi.KtDeclaration;
import org.jetbrains.kotlin.psi.KtNamedDeclaration;
import org.jetbrains.kotlin.psi.KtParameterList;
import org.jetbrains.kotlin.psi.KtPrimaryConstructor;
import org.jetbrains.kotlin.psi.KtPropertyAccessor;
import org.jetbrains.kotlin.psi.KtSecondaryConstructor;
import org.jetbrains.kotlin.psi.psiUtil.KtPsiUtilKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B+\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u001a\u0002\b\u000b¢\u0006\u0004\b\t\u0010\nJ\b\u0010\u000f\u001a\u00020\u0003H\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0003H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u0014\u0010\u0019\u001a\u00020\u00112\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0096\u0082\u0004J\n\u0010\u001c\u001a\u00020\u0018H\u0096\u0080\u0004R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\u0004\u0018\u00010\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/asJava/elements/KtLightIdentifier;", "Lcom/intellij/psi/impl/light/LightIdentifier;", "Lorg/jetbrains/kotlin/asJava/elements/PsiElementWithOrigin;", "Lcom/intellij/psi/PsiElement;", "lightOwner", "ktDeclaration", "Lorg/jetbrains/kotlin/psi/KtDeclaration;", "name", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lcom/intellij/psi/PsiElement;Lorg/jetbrains/kotlin/psi/KtDeclaration;Ljava/lang/String;)V", "Lkotlin/jvm/JvmOverloads;", "origin", "getOrigin", "()Lcom/intellij/psi/PsiElement;", "copy", "isPhysical", "", "getParent", "getContainingFile", "Lcom/intellij/psi/PsiFile;", "getTextRange", "Lcom/intellij/openapi/util/TextRange;", "getTextOffset", "", "equals", "other", "", "hashCode", "org.jetbrains.kotlin:light-classes-base"}, k = 1, mv = {2, 4, 0}, xi = 48)
public class KtLightIdentifier extends LightIdentifier implements PsiElementWithOrigin<PsiElement> {
    private final KtDeclaration ktDeclaration;
    private final PsiElement lightOwner;
    private final String name;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KtLightIdentifier(PsiElement psiElement, KtDeclaration ktDeclaration, String str) {
        super(psiElement.getManager(), str);
        psiElement.getClass();
        this.lightOwner = psiElement;
        this.ktDeclaration = ktDeclaration;
        this.name = str;
    }

    public PsiElement copy() {
        return new KtLightIdentifier(getLightOwner(), this.ktDeclaration, this.name);
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof KtLightIdentifier)) {
            return false;
        }
        KtLightIdentifier ktLightIdentifier = (KtLightIdentifier) other;
        return Intrinsics.areEqual(ktLightIdentifier.lightOwner, this.lightOwner) && Intrinsics.areEqual(ktLightIdentifier.ktDeclaration, this.ktDeclaration) && Intrinsics.areEqual(ktLightIdentifier.name, this.name);
    }

    public PsiFile getContainingFile() {
        PsiFile containingFile = this.lightOwner.getContainingFile();
        containingFile.getClass();
        return containingFile;
    }

    @Override // org.jetbrains.kotlin.asJava.elements.PsiElementWithOrigin
    public PsiElement getOrigin() {
        KtSecondaryConstructor ktSecondaryConstructor = this.ktDeclaration;
        if (ktSecondaryConstructor instanceof KtSecondaryConstructor) {
            return ktSecondaryConstructor.getConstructorKeyword();
        }
        if (!(ktSecondaryConstructor instanceof KtPrimaryConstructor)) {
            if (ktSecondaryConstructor instanceof KtPropertyAccessor) {
                return ((KtPropertyAccessor) ktSecondaryConstructor).getNamePlaceholder();
            }
            if (ktSecondaryConstructor instanceof KtNamedDeclaration) {
                return ((KtNamedDeclaration) ktSecondaryConstructor).getNameIdentifier();
            }
            return null;
        }
        PsiElement constructorKeyword = ((KtPrimaryConstructor) ktSecondaryConstructor).getConstructorKeyword();
        if (constructorKeyword != null) {
            return constructorKeyword;
        }
        KtParameterList valueParameterList = this.ktDeclaration.getValueParameterList();
        if (valueParameterList != null) {
            return valueParameterList;
        }
        KtClassOrObject containingClassOrObject = KtPsiUtilKt.getContainingClassOrObject(this.ktDeclaration);
        if (containingClassOrObject != null) {
            return containingClassOrObject.getNameIdentifier();
        }
        return null;
    }

    /* JADX INFO: renamed from: getParent, reason: from getter */
    public PsiElement getLightOwner() {
        return this.lightOwner;
    }

    public int getTextOffset() {
        PsiElement origin = getOrigin();
        if (origin != null) {
            return origin.getTextOffset();
        }
        return -1;
    }

    public TextRange getTextRange() {
        TextRange textRange;
        PsiElement origin = getOrigin();
        if (origin != null && (textRange = origin.getTextRange()) != null) {
            return textRange;
        }
        TextRange textRange2 = TextRange.EMPTY_RANGE;
        textRange2.getClass();
        return textRange2;
    }

    public int hashCode() {
        return this.lightOwner.hashCode();
    }

    public boolean isPhysical() {
        return true;
    }

    public /* synthetic */ KtLightIdentifier(PsiElement psiElement, KtDeclaration ktDeclaration, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(psiElement, ktDeclaration, (i & 4) != 0 ? ktDeclaration != null ? ktDeclaration.getName() : null : str);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public KtLightIdentifier(PsiElement psiElement, KtDeclaration ktDeclaration) {
        this(psiElement, ktDeclaration, null, 4, null);
        psiElement.getClass();
    }
}
