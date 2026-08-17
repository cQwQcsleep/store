package org.jetbrains.kotlin.asJava.elements;

import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.ItemPresentationProviders;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.light.LightElement;
import com.intellij.psi.search.SearchScope;
import javax.swing.Icon;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.idea.KotlinLanguage;
import org.jetbrains.kotlin.psi.KtElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\u0006\u001a\u00020\u0007H\u0096\u0080\u0004J\b\u0010\b\u001a\u00020\u0003H\u0016J*\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0003H\u0007b\u0018\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\n\b\u0013\u0012\u0006\b\n0\u00148\u0015J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0014J\r\u0010\u001a\u001a\u00070\u0007¢\u0006\u0002\b\u001bH\u0016J\n\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u0019H\u0016J\b\u0010\u001f\u001a\u00020\u0019H\u0016J\b\u0010 \u001a\u00020!H\u0016J\b\u0010\"\u001a\u00020\u0003H\u0016J\r\u0010#\u001a\u00070$¢\u0006\u0002\b%H\u0016J\u0010\u0010&\u001a\n (*\u0004\u0018\u00010'0'H\u0016J\u000f\u0010)\u001a\t\u0018\u00010*¢\u0006\u0002\b+H\u0016J\b\u0010,\u001a\u00020!H\u0016J\u0017\u0010-\u001a\t\u0018\u00010\u0003¢\u0006\u0002\b+2\u0006\u0010.\u001a\u00020\u0019H\u0016J\u0012\u0010/\u001a\u00020!2\b\u00100\u001a\u0004\u0018\u00010\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u0004\u0018\u00010\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u00061"}, d2 = {"Lorg/jetbrains/kotlin/asJava/elements/KtLightElementBase;", "Lcom/intellij/psi/impl/light/LightElement;", "parent", "Lcom/intellij/psi/PsiElement;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lcom/intellij/psi/PsiElement;)V", "toString", "", "getParent", "kotlinOrigin", "Lorg/jetbrains/kotlin/psi/KtElement;", "getKotlinOrigin", "()Lorg/jetbrains/kotlin/psi/KtElement;", "setParent", "", "newParent", "Lkotlin/Deprecated;", "message", "Hack for ULC", "level", "Lkotlin/DeprecationLevel;", "ERROR", "getElementIcon", "Ljavax/swing/Icon;", "flags", "", "getText", "Lcom/intellij/openapi/util/NlsSafe;", "getTextRange", "Lcom/intellij/openapi/util/TextRange;", "getTextOffset", "getStartOffsetInParent", "isWritable", "", "getNavigationElement", "getUseScope", "Lcom/intellij/psi/search/SearchScope;", "Lorg/jetbrains/annotations/NotNull;", "getContainingFile", "Lcom/intellij/psi/PsiFile;", "kotlin.jvm.PlatformType", "getPresentation", "Lcom/intellij/navigation/ItemPresentation;", "Lorg/jetbrains/annotations/Nullable;", "isValid", "findElementAt", "offset", "isEquivalentTo", "another", "org.jetbrains.kotlin:light-classes-base"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class KtLightElementBase extends LightElement {
    private PsiElement parent;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KtLightElementBase(PsiElement psiElement) {
        super(psiElement.getManager(), KotlinLanguage.INSTANCE);
        psiElement.getClass();
        this.parent = psiElement;
    }

    public PsiElement findElementAt(int offset) {
        KtElement ktElementMo25getKotlinOrigin = mo25getKotlinOrigin();
        if (ktElementMo25getKotlinOrigin != null) {
            return ktElementMo25getKotlinOrigin.findElementAt(offset);
        }
        return null;
    }

    public PsiFile getContainingFile() {
        return this.parent.getContainingFile();
    }

    public Icon getElementIcon(int flags) {
        return null;
    }

    /* JADX INFO: renamed from: getKotlinOrigin */
    public abstract KtElement mo25getKotlinOrigin();

    public PsiElement getNavigationElement() {
        PsiElement navigationElement;
        KtElement ktElementMo25getKotlinOrigin = mo25getKotlinOrigin();
        return (ktElementMo25getKotlinOrigin == null || (navigationElement = ktElementMo25getKotlinOrigin.getNavigationElement()) == null) ? this : navigationElement;
    }

    public PsiElement getParent() {
        return this.parent;
    }

    public ItemPresentation getPresentation() {
        KtElement ktElementMo25getKotlinOrigin = mo25getKotlinOrigin();
        if (ktElementMo25getKotlinOrigin != null) {
            this = ktElementMo25getKotlinOrigin;
        }
        return ItemPresentationProviders.getItemPresentation((NavigationItem) this);
    }

    public int getStartOffsetInParent() {
        KtElement ktElementMo25getKotlinOrigin = mo25getKotlinOrigin();
        if (ktElementMo25getKotlinOrigin != null) {
            return ktElementMo25getKotlinOrigin.getStartOffsetInParent();
        }
        return -1;
    }

    public String getText() {
        String text;
        KtElement ktElementMo25getKotlinOrigin = mo25getKotlinOrigin();
        return (ktElementMo25getKotlinOrigin == null || (text = ktElementMo25getKotlinOrigin.getText()) == null) ? "" : text;
    }

    public int getTextOffset() {
        KtElement ktElementMo25getKotlinOrigin = mo25getKotlinOrigin();
        if (ktElementMo25getKotlinOrigin != null) {
            return ktElementMo25getKotlinOrigin.getTextOffset();
        }
        return -1;
    }

    public TextRange getTextRange() {
        KtElement ktElementMo25getKotlinOrigin = mo25getKotlinOrigin();
        if (ktElementMo25getKotlinOrigin != null) {
            return ktElementMo25getKotlinOrigin.getTextRange();
        }
        return null;
    }

    public SearchScope getUseScope() {
        SearchScope useScope;
        KtElement ktElementMo25getKotlinOrigin = mo25getKotlinOrigin();
        if (ktElementMo25getKotlinOrigin != null && (useScope = ktElementMo25getKotlinOrigin.getUseScope()) != null) {
            return useScope;
        }
        SearchScope useScope2 = super/*com.intellij.psi.impl.PsiElementBase*/.getUseScope();
        useScope2.getClass();
        return useScope2;
    }

    public boolean isEquivalentTo(PsiElement another) {
        if (super/*com.intellij.psi.impl.PsiElementBase*/.isEquivalentTo(another)) {
            return true;
        }
        KtElement ktElementMo25getKotlinOrigin = mo25getKotlinOrigin();
        if (ktElementMo25getKotlinOrigin == null) {
            return false;
        }
        return ktElementMo25getKotlinOrigin.isEquivalentTo(another) || ((another instanceof KtLightElementBase) && ktElementMo25getKotlinOrigin.isEquivalentTo(((KtLightElementBase) another).mo25getKotlinOrigin()));
    }

    public boolean isValid() {
        if (!this.parent.isValid()) {
            return false;
        }
        KtElement ktElementMo25getKotlinOrigin = mo25getKotlinOrigin();
        return ktElementMo25getKotlinOrigin == null || ktElementMo25getKotlinOrigin.isValid();
    }

    public boolean isWritable() {
        KtElement ktElementMo25getKotlinOrigin = mo25getKotlinOrigin();
        if (ktElementMo25getKotlinOrigin != null) {
            return ktElementMo25getKotlinOrigin.isWritable();
        }
        return false;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Hack for ULC")
    public final void setParent(PsiElement newParent) {
        newParent.getClass();
        this.parent = newParent;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String simpleName = Reflection.getOrCreateKotlinClass(getClass()).getSimpleName();
        if (simpleName == null) {
            simpleName = "";
        }
        sb.append(simpleName);
        sb.append(" of ");
        sb.append(this.parent);
        return sb.toString();
    }
}
