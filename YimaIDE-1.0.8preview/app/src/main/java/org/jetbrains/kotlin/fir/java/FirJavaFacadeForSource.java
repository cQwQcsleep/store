package org.jetbrains.kotlin.fir.java;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.load.java.JavaClassFinder;
import org.jetbrains.kotlin.load.java.structure.JavaClass;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/FirJavaFacadeForSource;", "Lorg/jetbrains/kotlin/fir/java/FirJavaFacade;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "sourceModuleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "classFinder", "Lorg/jetbrains/kotlin/load/java/JavaClassFinder;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/FirModuleData;Lorg/jetbrains/kotlin/load/java/JavaClassFinder;)V", "getModuleDataForClass", "javaClass", "Lorg/jetbrains/kotlin/load/java/structure/JavaClass;", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJavaFacadeForSource extends FirJavaFacade {
    private final FirModuleData sourceModuleData;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirJavaFacadeForSource(FirSession firSession, FirModuleData firModuleData, JavaClassFinder javaClassFinder) {
        super(firSession, javaClassFinder);
        firSession.getClass();
        firModuleData.getClass();
        javaClassFinder.getClass();
        this.sourceModuleData = firModuleData;
    }

    @Override // org.jetbrains.kotlin.fir.java.FirJavaFacade
    public FirModuleData getModuleDataForClass(JavaClass javaClass) {
        javaClass.getClass();
        return this.sourceModuleData;
    }
}
