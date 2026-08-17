package org.jetbrains.kotlin.resolve.multiplatform;

import com.intellij.openapi.util.Key;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.psi.KtFile;
import org.jetbrains.kotlin.psi.UserDataProperty;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0007\"3\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\u00018F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0002\u0010\u0004\"\u0004\b\u0005\u0010\u0006\"3\u0010\n\u001a\u0004\u0018\u00010\t*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\t8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u000f\u0010\b\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0010"}, d2 = {"<set-?>", "", "isCommonSource", "Lorg/jetbrains/kotlin/psi/KtFile;", "(Lorg/jetbrains/kotlin/psi/KtFile;)Ljava/lang/Boolean;", "setCommonSource", "(Lorg/jetbrains/kotlin/psi/KtFile;Ljava/lang/Boolean;)V", "isCommonSource$delegate", "Lorg/jetbrains/kotlin/psi/UserDataProperty;", "", "hmppModuleName", "getHmppModuleName", "(Lorg/jetbrains/kotlin/psi/KtFile;)Ljava/lang/String;", "setHmppModuleName", "(Lorg/jetbrains/kotlin/psi/KtFile;Ljava/lang/String;)V", "hmppModuleName$delegate", "org.jetbrains.kotlin:frontend"}, k = 2, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final class IsCommonSourceKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl<>(IsCommonSourceKt.class, "isCommonSource", "isCommonSource(Lorg/jetbrains/kotlin/psi/KtFile;)Ljava/lang/Boolean;", 1), new MutablePropertyReference1Impl<>(IsCommonSourceKt.class, "hmppModuleName", "getHmppModuleName(Lorg/jetbrains/kotlin/psi/KtFile;)Ljava/lang/String;", 1)};
    private static final UserDataProperty hmppModuleName$delegate;
    private static final UserDataProperty isCommonSource$delegate;

    static {
        Key keyCreate = Key.create("IS_COMMON_SOURCE");
        keyCreate.getClass();
        isCommonSource$delegate = new UserDataProperty(keyCreate);
        Key keyCreate2 = Key.create("HMPP_MODULE_NAME");
        keyCreate2.getClass();
        hmppModuleName$delegate = new UserDataProperty(keyCreate2);
    }

    public static final String getHmppModuleName(KtFile ktFile) {
        ktFile.getClass();
        return (String) hmppModuleName$delegate.getValue(ktFile, $$delegatedProperties[1]);
    }

    public static final Boolean isCommonSource(KtFile ktFile) {
        ktFile.getClass();
        return (Boolean) isCommonSource$delegate.getValue(ktFile, $$delegatedProperties[0]);
    }

    public static final void setCommonSource(KtFile ktFile, Boolean bool) {
        ktFile.getClass();
        isCommonSource$delegate.setValue(ktFile, $$delegatedProperties[0], bool);
    }

    public static final void setHmppModuleName(KtFile ktFile, String str) {
        ktFile.getClass();
        hmppModuleName$delegate.setValue(ktFile, $$delegatedProperties[1], str);
    }
}
