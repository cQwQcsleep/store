package org.jetbrains.kotlin.psi.stubs;

import kotlin.Metadata;
import org.jetbrains.kotlin.psi.KtDeclarationWithBody;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\bg\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0012\u0010\n\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0007Ê\u0001\u0010\b\r\u0012\f\b\u000e\u0012\b\b\fJ\u0004\b\t0\u000fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/psi/stubs/KotlinDeclarationWithBodyStub;", "T", "Lorg/jetbrains/kotlin/psi/KtDeclarationWithBody;", "Lorg/jetbrains/kotlin/psi/stubs/KotlinStubElement;", "mayHaveContract", "", "getMayHaveContract", "()Z", "hasNoExpressionBody", "getHasNoExpressionBody", "hasBody", "getHasBody", "org.jetbrains.kotlin:psi-api", "Lkotlin/SubclassOptInRequired;", "markerClass", "Lorg/jetbrains/kotlin/psi/KtImplementationDetail;"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface KotlinDeclarationWithBodyStub<T extends KtDeclarationWithBody> extends KotlinStubElement<T> {
    boolean getHasBody();

    boolean getHasNoExpressionBody();

    boolean getMayHaveContract();
}
