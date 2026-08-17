package org.jetbrains.kotlin.resolve.jvm;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.text.StringsKt;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\b\tJ\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/resolve/jvm/NotFoundPackagesCachingStrategy;", "", "chooseStrategy", "Lorg/jetbrains/kotlin/resolve/jvm/NotFoundPackagesCachingStrategy$CacheType;", "isLibrarySearchScope", "", "qualifiedName", "", "CacheType", "Default", "org.jetbrains.kotlin:frontend.common.jvm"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public interface NotFoundPackagesCachingStrategy {

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/resolve/jvm/NotFoundPackagesCachingStrategy$CacheType;", "", "<init>", "(Ljava/lang/String;I)V", "LIB_SCOPE", "SCOPE", "NO_CACHING", "org.jetbrains.kotlin:frontend.common.jvm"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
    public enum CacheType {
        LIB_SCOPE,
        SCOPE,
        NO_CACHING;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<CacheType> getEntries() {
            return $ENTRIES;
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/resolve/jvm/NotFoundPackagesCachingStrategy$Default;", "Lorg/jetbrains/kotlin/resolve/jvm/NotFoundPackagesCachingStrategy;", "<init>", "()V", "chooseStrategy", "Lorg/jetbrains/kotlin/resolve/jvm/NotFoundPackagesCachingStrategy$CacheType;", "isLibrarySearchScope", "", "qualifiedName", "", "org.jetbrains.kotlin:frontend.common.jvm"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
    public static final class Default implements NotFoundPackagesCachingStrategy {
        public static final Default INSTANCE = new Default();

        private Default() {
        }

        @Override // org.jetbrains.kotlin.resolve.jvm.NotFoundPackagesCachingStrategy
        public CacheType chooseStrategy(boolean isLibrarySearchScope, String qualifiedName) {
            qualifiedName.getClass();
            return (isLibrarySearchScope && (StringsKt.indexOf$default(qualifiedName, '.', 0, false, 6, (Object) null) > 0)) ? CacheType.LIB_SCOPE : CacheType.SCOPE;
        }
    }

    CacheType chooseStrategy(boolean isLibrarySearchScope, String qualifiedName);
}
