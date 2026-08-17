package org.jetbrains.kotlin.fir.java;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.java.scopes.JavaAnnotationSyntheticPropertiesScope;
import org.jetbrains.kotlin.fir.java.scopes.JavaClassMembersEnhancementScope;
import org.jetbrains.kotlin.fir.java.scopes.JavaClassStaticEnhancementScope;
import org.jetbrains.kotlin.fir.java.scopes.JavaClassUseSiteMemberScope;
import org.jetbrains.kotlin.fir.resolve.ScopeSessionKey;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\"\u001a\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"JAVA_SYNTHETIC_FOR_ANNOTATIONS", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSessionKey;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "Lorg/jetbrains/kotlin/fir/java/scopes/JavaAnnotationSyntheticPropertiesScope;", "JAVA_ENHANCEMENT_FOR_STATIC", "Lorg/jetbrains/kotlin/fir/java/scopes/JavaClassStaticEnhancementScope;", "JAVA_ENHANCEMENT", "Lorg/jetbrains/kotlin/fir/java/scopes/JavaClassMembersEnhancementScope;", "JAVA_USE_SITE", "Lorg/jetbrains/kotlin/fir/java/scopes/JavaClassUseSiteMemberScope;", "org.jetbrains.kotlin:fir-jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JavaScopeProviderKt {
    private static final ScopeSessionKey<FirRegularClassSymbol, JavaAnnotationSyntheticPropertiesScope> JAVA_SYNTHETIC_FOR_ANNOTATIONS = new ScopeSessionKey<FirRegularClassSymbol, JavaAnnotationSyntheticPropertiesScope>() { // from class: org.jetbrains.kotlin.fir.java.JavaScopeProviderKt$special$$inlined$scopeSessionKey$1
    };
    private static final ScopeSessionKey<FirRegularClassSymbol, JavaClassStaticEnhancementScope> JAVA_ENHANCEMENT_FOR_STATIC = new ScopeSessionKey<FirRegularClassSymbol, JavaClassStaticEnhancementScope>() { // from class: org.jetbrains.kotlin.fir.java.JavaScopeProviderKt$special$$inlined$scopeSessionKey$2
    };
    private static final ScopeSessionKey<FirRegularClassSymbol, JavaClassMembersEnhancementScope> JAVA_ENHANCEMENT = new ScopeSessionKey<FirRegularClassSymbol, JavaClassMembersEnhancementScope>() { // from class: org.jetbrains.kotlin.fir.java.JavaScopeProviderKt$special$$inlined$scopeSessionKey$3
    };
    private static final ScopeSessionKey<FirRegularClassSymbol, JavaClassUseSiteMemberScope> JAVA_USE_SITE = new ScopeSessionKey<FirRegularClassSymbol, JavaClassUseSiteMemberScope>() { // from class: org.jetbrains.kotlin.fir.java.JavaScopeProviderKt$special$$inlined$scopeSessionKey$4
    };
}
