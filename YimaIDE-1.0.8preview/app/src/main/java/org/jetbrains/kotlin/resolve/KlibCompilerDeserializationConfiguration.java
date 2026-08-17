package org.jetbrains.kotlin.resolve;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.config.LanguageVersion;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.metadata.deserialization.MetadataVersion;
import org.jetbrains.kotlin.util.KlibMetadataHelpersKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/resolve/KlibCompilerDeserializationConfiguration;", "Lorg/jetbrains/kotlin/resolve/CommonCompilerDeserializationConfiguration;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "<init>", "(Lorg/jetbrains/kotlin/config/LanguageVersionSettings;)V", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class KlibCompilerDeserializationConfiguration extends CommonCompilerDeserializationConfiguration {

    /* JADX INFO: renamed from: org.jetbrains.kotlin.resolve.KlibCompilerDeserializationConfiguration$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
    public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<LanguageVersion, MetadataVersion> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1, KlibMetadataHelpersKt.class, "toKlibMetadataVersion", "toKlibMetadataVersion(Lorg/jetbrains/kotlin/config/LanguageVersion;)Lorg/jetbrains/kotlin/metadata/deserialization/MetadataVersion;", 1);
        }

        public final MetadataVersion invoke(LanguageVersion languageVersion) {
            languageVersion.getClass();
            return KlibMetadataHelpersKt.toKlibMetadataVersion(languageVersion);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KlibCompilerDeserializationConfiguration(LanguageVersionSettings languageVersionSettings) {
        super(languageVersionSettings, AnonymousClass1.INSTANCE);
        languageVersionSettings.getClass();
    }
}
