package org.jetbrains.kotlin.asJava.classes;

import kotlin.Metadata;
import org.jetbrains.kotlin.load.java.structure.LightClassOriginKind;
import org.jetbrains.kotlin.psi.KtClassOrObject;
import org.jetbrains.kotlin.psi.KtScript;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000eÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/asJava/classes/KtLightClassForScript;", "Lorg/jetbrains/kotlin/asJava/classes/KtLightClass;", "script", "Lorg/jetbrains/kotlin/psi/KtScript;", "getScript", "()Lorg/jetbrains/kotlin/psi/KtScript;", "kotlinOrigin", "Lorg/jetbrains/kotlin/psi/KtClassOrObject;", "getKotlinOrigin", "()Lorg/jetbrains/kotlin/psi/KtClassOrObject;", "originKind", "Lorg/jetbrains/kotlin/load/java/structure/LightClassOriginKind;", "getOriginKind", "()Lorg/jetbrains/kotlin/load/java/structure/LightClassOriginKind;", "org.jetbrains.kotlin:light-classes-base"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface KtLightClassForScript extends KtLightClass {
    @Override // org.jetbrains.kotlin.asJava.KtLightClassMarker
    default LightClassOriginKind getOriginKind() {
        return LightClassOriginKind.SOURCE;
    }

    KtScript getScript();

    @Override // org.jetbrains.kotlin.asJava.elements.KtLightElement
    default KtClassOrObject getKotlinOrigin() {
        return null;
    }
}
