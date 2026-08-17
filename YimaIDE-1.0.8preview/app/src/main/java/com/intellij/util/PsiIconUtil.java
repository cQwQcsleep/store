package com.intellij.util;

import com.intellij.ide.IconProvider;
import com.intellij.openapi.progress.ProcessCanceledException;
import com.intellij.openapi.project.IndexNotReadyException;
import com.intellij.psi.PsiElement;
import java.util.Iterator;
import java.util.concurrent.CancellationException;
import javax.swing.Icon;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.JvmStatic;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH\u0007J\u001c\u0010\n\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH\u0007¨\u0006\u000b"}, d2 = {"Lcom/intellij/util/PsiIconUtil;", "", "<init>", "()V", "getIconFromProviders", "Ljavax/swing/Icon;", "element", "Lcom/intellij/psi/PsiElement;", "flags", "", "getProvidersIcon", "intellij.platform.core"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class PsiIconUtil {
    public static final PsiIconUtil INSTANCE = new PsiIconUtil();

    private PsiIconUtil() {
    }

    @JvmStatic
    public static final Icon getIconFromProviders(PsiElement element, int flags) throws Throwable {
        Icon icon;
        Object obj;
        element.getClass();
        Iterator it = IconProvider.EXTENSION_POINT_NAME.getExtensionList().iterator();
        do {
            icon = null;
            if (!it.hasNext()) {
                break;
            }
            IconProvider iconProvider = (IconProvider) it.next();
            try {
                Result.Companion companion = Result.Companion;
                obj = Result.constructor-impl(iconProvider.getIcon(element, flags));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            Throwable th2 = Result.exceptionOrNull-impl(obj);
            if (th2 != null) {
                if ((th2 instanceof ProcessCanceledException) || (th2 instanceof CancellationException)) {
                    throw ExceptionUtilRt.addRethrownStackAsSuppressed(th2);
                }
                if (!(th2 instanceof IndexNotReadyException)) {
                    PsiIconUtilKt.LOG.warn("IconProvider " + iconProvider + " threw an exception", th2);
                }
            }
            icon = (Icon) (Result.isFailure-impl(obj) ? null : obj);
        } while (icon == null);
        return icon;
    }
}
