package org.jetbrains.kotlin.psi.stubs;

import com.intellij.psi.stubs.StubElement;
import kotlin.Metadata;
import org.jetbrains.kotlin.psi.KtElement;
import org.jetbrains.kotlin.psi.KtImplementationDetail;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bg\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003J \u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\f\u0010\u0005\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0003H'b\u0002\b\u0006J\u0018\u0010\u0007\u001a\u00020\b2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\u0000H'b\u0002\b\u0006Ê\u0001\u0002\b\u0006ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/psi/stubs/KotlinStubElement;", "T", "Lorg/jetbrains/kotlin/psi/KtElement;", "Lcom/intellij/psi/stubs/StubElement;", "copyInto", "newParent", "Lorg/jetbrains/kotlin/psi/KtImplementationDetail;", "isEquivalentTo", "", "other", "org.jetbrains.kotlin:psi-api"}, k = 1, mv = {2, 4, 0}, xi = 48)
@KtImplementationDetail
public interface KotlinStubElement<T extends KtElement> extends StubElement<T> {
    @KtImplementationDetail
    KotlinStubElement<T> copyInto(StubElement<?> newParent);

    @KtImplementationDetail
    boolean isEquivalentTo(KotlinStubElement<?> other);
}
