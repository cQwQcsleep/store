package org.jetbrains.kotlin.fir.types.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.FirQualifierPart;
import org.jetbrains.kotlin.fir.types.FirTypeArgumentList;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B!\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/impl/FirQualifierPartImpl;", "Lorg/jetbrains/kotlin/fir/types/FirQualifierPart;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "typeArgumentList", "Lorg/jetbrains/kotlin/fir/types/FirTypeArgumentList;", "<init>", "(Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/fir/types/FirTypeArgumentList;)V", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "getTypeArgumentList", "()Lorg/jetbrains/kotlin/fir/types/FirTypeArgumentList;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirQualifierPartImpl implements FirQualifierPart {
    private final Name name;
    private final KtSourceElement source;
    private final FirTypeArgumentList typeArgumentList;

    public FirQualifierPartImpl(KtSourceElement ktSourceElement, Name name, FirTypeArgumentList firTypeArgumentList) {
        name.getClass();
        firTypeArgumentList.getClass();
        this.source = ktSourceElement;
        this.name = name;
        this.typeArgumentList = firTypeArgumentList;
    }

    @Override // org.jetbrains.kotlin.fir.types.FirQualifierPart
    public Name getName() {
        return this.name;
    }

    @Override // org.jetbrains.kotlin.fir.types.FirQualifierPart
    public KtSourceElement getSource() {
        return this.source;
    }

    @Override // org.jetbrains.kotlin.fir.types.FirQualifierPart
    public FirTypeArgumentList getTypeArgumentList() {
        return this.typeArgumentList;
    }
}
