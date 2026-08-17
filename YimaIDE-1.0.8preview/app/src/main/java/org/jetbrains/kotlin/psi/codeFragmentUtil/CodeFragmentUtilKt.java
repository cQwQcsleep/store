package org.jetbrains.kotlin.psi.codeFragmentUtil;

import com.intellij.openapi.util.Key;
import kotlin.Metadata;
import org.jetbrains.kotlin.psi.KtCodeFragment;
import org.jetbrains.kotlin.psi.KtElement;
import org.jetbrains.kotlin.psi.KtFile;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\n\u0010\u0005\u001a\u00020\u0002*\u00020\u0006\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"(\u0010\u0005\u001a\u00020\u0002*\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00028F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f\"\u0014\u0010\r\u001a\u00020\u000eX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"SUPPRESS_DIAGNOSTICS_IN_DEBUG_MODE", "Lcom/intellij/openapi/util/Key;", "", "getSUPPRESS_DIAGNOSTICS_IN_DEBUG_MODE", "()Lcom/intellij/openapi/util/Key;", "suppressDiagnosticsInDebugMode", "Lorg/jetbrains/kotlin/psi/KtElement;", "skip", "Lorg/jetbrains/kotlin/psi/KtFile;", "getSuppressDiagnosticsInDebugMode", "(Lorg/jetbrains/kotlin/psi/KtFile;)Z", "setSuppressDiagnosticsInDebugMode", "(Lorg/jetbrains/kotlin/psi/KtFile;Z)V", "DEBUG_TYPE_REFERENCE_STRING", "", "getDEBUG_TYPE_REFERENCE_STRING", "()Ljava/lang/String;", "org.jetbrains.kotlin:frontend"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class CodeFragmentUtilKt {
    private static final String DEBUG_TYPE_REFERENCE_STRING;
    private static final Key<Boolean> SUPPRESS_DIAGNOSTICS_IN_DEBUG_MODE;

    static {
        Key<Boolean> keyCreate = Key.create("SUPPRESS_DIAGNOSTICS_IN_DEBUG_MODE");
        keyCreate.getClass();
        SUPPRESS_DIAGNOSTICS_IN_DEBUG_MODE = keyCreate;
        DEBUG_TYPE_REFERENCE_STRING = "DebugTypeKotlinRulezzzz";
    }

    public static final String getDEBUG_TYPE_REFERENCE_STRING() {
        return DEBUG_TYPE_REFERENCE_STRING;
    }

    public static final Key<Boolean> getSUPPRESS_DIAGNOSTICS_IN_DEBUG_MODE() {
        return SUPPRESS_DIAGNOSTICS_IN_DEBUG_MODE;
    }

    public static final boolean getSuppressDiagnosticsInDebugMode(KtFile ktFile) {
        ktFile.getClass();
        if (ktFile instanceof KtCodeFragment) {
            return true;
        }
        Boolean bool = (Boolean) ktFile.getUserData(SUPPRESS_DIAGNOSTICS_IN_DEBUG_MODE);
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public static final void setSuppressDiagnosticsInDebugMode(KtFile ktFile, boolean z) {
        ktFile.getClass();
        ktFile.putUserData(SUPPRESS_DIAGNOSTICS_IN_DEBUG_MODE, Boolean.valueOf(z));
    }

    public static final boolean suppressDiagnosticsInDebugMode(KtElement ktElement) {
        ktElement.getClass();
        if (ktElement instanceof KtFile) {
            return getSuppressDiagnosticsInDebugMode((KtFile) ktElement);
        }
        KtFile containingFile = ktElement.getContainingFile();
        return (containingFile instanceof KtFile) && getSuppressDiagnosticsInDebugMode(containingFile);
    }
}
