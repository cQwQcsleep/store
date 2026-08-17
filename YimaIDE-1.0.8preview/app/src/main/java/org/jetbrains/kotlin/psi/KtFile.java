package org.jetbrains.kotlin.psi;

import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiClassOwner;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.KtStubBasedElementTypes;
import org.jetbrains.kotlin.psi.stubs.elements.KtTokenSets;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 \u001c2\u00020\u00012\u00020\u0002:\u0001\u001cB\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0013\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016¢\u0006\u0002\u0010\fJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0016\u0010\u0011\u001a\u00020\u0010H\u0017b\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014J5\u0010\u0015\u001a\u0002H\u0016\"\u0004\b\u0000\u0010\u0016\"\u0004\b\u0001\u0010\u00172\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u0002H\u0016\u0012\u0004\u0012\u0002H\u00170\u00192\u0006\u0010\u001a\u001a\u0002H\u0017H\u0016¢\u0006\u0002\u0010\u001b¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/psi/KtFile;", "Lorg/jetbrains/kotlin/psi/KtCommonFile;", "Lcom/intellij/psi/PsiClassOwner;", "viewProvider", "Lcom/intellij/psi/FileViewProvider;", "isCompiled", "", "<init>", "(Lcom/intellij/psi/FileViewProvider;Z)V", "getClasses", "", "Lcom/intellij/psi/PsiClass;", "()[Lcom/intellij/psi/PsiClass;", "setPackageName", "", "packageName", "", "getPackageName", "Lkotlin/Deprecated;", "message", "getPackageFqName should be used instead", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/psi/KtVisitor;", "data", "(Lorg/jetbrains/kotlin/psi/KtVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "Companion", "org.jetbrains.kotlin:psi-api"}, k = 1, mv = {2, 4, 0}, xi = 48)
public class KtFile extends KtCommonFile implements PsiClassOwner {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final TokenSet FILE_DECLARATION_TYPES;

    static {
        TokenSet tokenSetOrSet = TokenSet.orSet(new TokenSet[]{KtTokenSets.DECLARATION_TYPES, TokenSet.create(new IElementType[]{KtStubBasedElementTypes.SCRIPT})});
        tokenSetOrSet.getClass();
        FILE_DECLARATION_TYPES = tokenSetOrSet;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KtFile(FileViewProvider fileViewProvider, boolean z) {
        super(fileViewProvider, z);
        fileViewProvider.getClass();
    }

    @Override // org.jetbrains.kotlin.psi.KtCommonFile, org.jetbrains.kotlin.psi.KtElement, org.jetbrains.kotlin.psi.KtExpression
    public <R, D> R accept(KtVisitor<R, D> visitor, D data) {
        visitor.getClass();
        return visitor.visitKtFile(this, data);
    }

    public PsiClass[] getClasses() {
        PsiClass[] fileClasses;
        KtFileClassProvider ktFileClassProvider = (KtFileClassProvider) getProject().getService(KtFileClassProvider.class);
        if (ktFileClassProvider != null && (fileClasses = ktFileClassProvider.getFileClasses(this)) != null) {
            return fileClasses;
        }
        PsiClass[] psiClassArr = PsiClass.EMPTY_ARRAY;
        psiClassArr.getClass();
        return psiClassArr;
    }

    @Deprecated(message = "getPackageFqName should be used instead")
    public String getPackageName() {
        return getPackageFqName().asString();
    }

    public void setPackageName(String packageName) {
        packageName.getClass();
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0016\u0010\u0004\u001a\u00070\u0005¢\u0006\u0002\b\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/psi/KtFile$Companion;", "", "<init>", "()V", "FILE_DECLARATION_TYPES", "Lcom/intellij/psi/tree/TokenSet;", "Lorg/jetbrains/annotations/NotNull;", "getFILE_DECLARATION_TYPES", "()Lcom/intellij/psi/tree/TokenSet;", "org.jetbrains.kotlin:psi-api"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final TokenSet getFILE_DECLARATION_TYPES() {
            return KtFile.FILE_DECLARATION_TYPES;
        }

        private Companion() {
        }
    }
}
