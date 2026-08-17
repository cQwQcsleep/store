package org.jetbrains.kotlin.resolve.jvm;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.JvmTarget;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.jetbrains.kotlin.library.components.KlibMetadataConstants;
import org.jetbrains.kotlin.resolve.BindingTrace;
import org.jetbrains.kotlin.resolve.CodeAnalyzerInitializer;
import org.jetbrains.kotlin.resolve.lazy.KotlinCodeAnalyzer;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J0\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH&¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/resolve/jvm/JvmCodeAnalyzerInitializer;", "Lorg/jetbrains/kotlin/resolve/CodeAnalyzerInitializer;", "<init>", "()V", "initialize", "", "trace", "Lorg/jetbrains/kotlin/resolve/BindingTrace;", KlibMetadataConstants.KLIB_MODULE_METADATA_FILE_NAME, "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "codeAnalyzer", "Lorg/jetbrains/kotlin/resolve/lazy/KotlinCodeAnalyzer;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "jvmTarget", "Lorg/jetbrains/kotlin/config/JvmTarget;", "org.jetbrains.kotlin:frontend.java"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class JvmCodeAnalyzerInitializer implements CodeAnalyzerInitializer {
    public abstract void initialize(BindingTrace trace, ModuleDescriptor module, KotlinCodeAnalyzer codeAnalyzer, LanguageVersionSettings languageVersionSettings, JvmTarget jvmTarget);
}
