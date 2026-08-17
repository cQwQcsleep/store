package org.jetbrains.kotlin.resolve.jvm.checkers;

import com.intellij.psi.PsiElement;
import kotlin.Metadata;
import org.jetbrains.kotlin.config.JvmTarget;
import org.jetbrains.kotlin.lexer.KtModifierKeywordToken;
import org.jetbrains.kotlin.psi.KtModifierList;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a'\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0004\"\u00020\u0005H\u0002¢\u0006\u0002\u0010\u0006\u001a\u0014\u0010\u0007\u001a\u00020\b*\u00020\t2\u0006\u0010\n\u001a\u00020\bH\u0002¨\u0006\u000b"}, d2 = {"findOneOfModifiers", "Lcom/intellij/psi/PsiElement;", "Lorg/jetbrains/kotlin/psi/KtModifierList;", "modifierTokens", "", "Lorg/jetbrains/kotlin/lexer/KtModifierKeywordToken;", "(Lorg/jetbrains/kotlin/psi/KtModifierList;[Lorg/jetbrains/kotlin/lexer/KtModifierKeywordToken;)Lcom/intellij/psi/PsiElement;", "areRecordsAllowed", "", "Lorg/jetbrains/kotlin/config/JvmTarget;", "enableJvmPreview", "org.jetbrains.kotlin:frontend.java"}, k = 2, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final class JvmRecordApplicabilityCheckerKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean areRecordsAllowed(JvmTarget jvmTarget, boolean z) {
        int majorVersion = jvmTarget.getMajorVersion();
        JvmTarget jvmTarget2 = JvmTarget.JVM_15;
        if (majorVersion < jvmTarget2.getMajorVersion()) {
            return false;
        }
        return z || jvmTarget.getMajorVersion() > jvmTarget2.getMajorVersion();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PsiElement findOneOfModifiers(KtModifierList ktModifierList, KtModifierKeywordToken... ktModifierKeywordTokenArr) {
        for (KtModifierKeywordToken ktModifierKeywordToken : ktModifierKeywordTokenArr) {
            PsiElement modifier = ktModifierList.getModifier(ktModifierKeywordToken);
            if (modifier != null) {
                return modifier;
            }
        }
        return null;
    }
}
