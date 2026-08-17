package com.intellij.l10n;

import com.intellij.psi.impl.source.tree.ChildRole;
import java.util.List;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\n\b\u0087\u0081\u0002\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\nB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\u000b"}, d2 = {"Lcom/intellij/l10n/LocalizationOrder;", "", "<init>", "(Ljava/lang/String;I)V", "FOLDER_REGION_LEVEL_PLATFORM", "SUFFIX_REGION_LEVEL_PLATFORM", "FOLDER_LANGUAGE_LEVEL_PLATFORM", "SUFFIX_LANGUAGE_LEVEL_PLATFORM", "DEFAULT_PLUGIN", "DEFAULT_PLATFORM", "Companion", "intellij.platform.core"}, k = 1, mv = {2, 0, 0}, xi = ChildRole.TRY_BLOCK)
public enum LocalizationOrder {
    FOLDER_REGION_LEVEL_PLATFORM,
    SUFFIX_REGION_LEVEL_PLATFORM,
    FOLDER_LANGUAGE_LEVEL_PLATFORM,
    SUFFIX_LANGUAGE_LEVEL_PLATFORM,
    DEFAULT_PLUGIN,
    DEFAULT_PLATFORM;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public static EnumEntries<LocalizationOrder> getEntries() {
        return $ENTRIES;
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\b¨\u0006\n"}, d2 = {"Lcom/intellij/l10n/LocalizationOrder$Companion;", "", "<init>", "()V", "getLocalizationOrder", "Lcom/intellij/l10n/LocalizationOrder;", "orderedPaths", "", "", "bundlePath", "intellij.platform.core"}, k = 1, mv = {2, 0, 0}, xi = ChildRole.TRY_BLOCK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final LocalizationOrder getLocalizationOrder(List<String> orderedPaths, String bundlePath) {
            orderedPaths.getClass();
            bundlePath.getClass();
            int size = orderedPaths.size();
            if (size == 1) {
                return LocalizationOrder.DEFAULT_PLATFORM;
            }
            if (size == 3) {
                int iIndexOf = orderedPaths.indexOf(bundlePath);
                if (iIndexOf != 0) {
                    return iIndexOf != 1 ? LocalizationOrder.DEFAULT_PLATFORM : LocalizationOrder.SUFFIX_LANGUAGE_LEVEL_PLATFORM;
                }
                return LocalizationOrder.FOLDER_LANGUAGE_LEVEL_PLATFORM;
            }
            int iIndexOf2 = orderedPaths.indexOf(bundlePath);
            if (iIndexOf2 == LocalizationOrder.DEFAULT_PLUGIN.ordinal()) {
                return LocalizationOrder.DEFAULT_PLATFORM;
            }
            if (iIndexOf2 < 0 || iIndexOf2 >= LocalizationOrder.getEntries().size()) {
                return null;
            }
            return (LocalizationOrder) LocalizationOrder.getEntries().get(iIndexOf2);
        }

        private Companion() {
        }
    }
}
