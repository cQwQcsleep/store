package org.jetbrains.kotlin.fir.scopes.impl;

import java.util.List;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.DefaultImportsProvider;
import org.jetbrains.kotlin.resolve.ImportPath;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH&j\u0002\b\u0004j\u0002\b\u0005¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/DefaultImportPriority;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "HIGH", "LOW", "getAllDefaultImports", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/resolve/ImportPath;", "defaultImportsProvider", "Lorg/jetbrains/kotlin/resolve/DefaultImportsProvider;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public enum DefaultImportPriority {
    HIGH { // from class: org.jetbrains.kotlin.fir.scopes.impl.DefaultImportPriority.HIGH
        @Override // org.jetbrains.kotlin.fir.scopes.impl.DefaultImportPriority
        public List<ImportPath> getAllDefaultImports(DefaultImportsProvider defaultImportsProvider, LanguageVersionSettings languageVersionSettings) {
            languageVersionSettings.getClass();
            if (defaultImportsProvider != null) {
                return defaultImportsProvider.getDefaultImports(false);
            }
            return null;
        }
    },
    LOW { // from class: org.jetbrains.kotlin.fir.scopes.impl.DefaultImportPriority.LOW
        @Override // org.jetbrains.kotlin.fir.scopes.impl.DefaultImportPriority
        public List<ImportPath> getAllDefaultImports(DefaultImportsProvider defaultImportsProvider, LanguageVersionSettings languageVersionSettings) {
            languageVersionSettings.getClass();
            if (defaultImportsProvider != null) {
                return defaultImportsProvider.getDefaultLowPriorityImports();
            }
            return null;
        }
    };

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* synthetic */ DefaultImportPriority(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public static EnumEntries<DefaultImportPriority> getEntries() {
        return $ENTRIES;
    }

    public abstract List<ImportPath> getAllDefaultImports(DefaultImportsProvider defaultImportsProvider, LanguageVersionSettings languageVersionSettings);
}
