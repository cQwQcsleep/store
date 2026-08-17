package org.jetbrains.kotlin.backend.konan;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import org.eclipse.jdt.internal.compiler.impl.CompilerOptions;
import org.eclipse.jdt.internal.compiler.util.SuffixConstants;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\"&\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00018\u0006X\u0087\u0004r\u0002\b\u0007¢\u0006\u000e\n\u0000\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"cKeywords", "", "", "getCKeywords$annotations", "()V", "getCKeywords", "()Ljava/util/Set;", "Lorg/jetbrains/kotlin/backend/konan/InternalKotlinNativeApi;", "org.jetbrains.kotlin:base"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class CKeywordsKt {
    private static final Set<String> cKeywords = SetsKt.setOf(new String[]{"auto", "break", "case", "char", "const", "continue", "default", "do", "double", "else", "enum", "extern", "float", "for", "goto", "if", "int", "long", "register", "return", "short", "signed", "sizeof", "static", "struct", "switch", "typedef", "union", "unsigned", "void", "volatile", "while", "_Bool", "_Complex", "_Imaginary", "inline", "restrict", "_Alignas", "_Alignof", "_Atomic", "_Generic", "_Noreturn", "_Static_assert", "_Thread_local", "and", "not", "or", "xor", "bool", "complex", "imaginary", "alignas", "alignof", "and_eq", "asm", "bitand", "bitor", "bool", "catch", "char16_t", "char32_t", SuffixConstants.EXTENSION_class, "compl", "constexpr", "const_cast", "decltype", "delete", "dynamic_cast", "explicit", "export", "false", "friend", "inline", "mutable", "namespace", "new", "noexcept", "not_eq", "nullptr", "operator", "or_eq", CompilerOptions.PRIVATE, CompilerOptions.PROTECTED, CompilerOptions.PUBLIC, "reinterpret_cast", "static_assert", "template", "this", "thread_local", "throw", "true", "try", "typeid", "typename", "using", "virtual", "wchar_t", "xor_eq"});

    public static final Set<String> getCKeywords() {
        return cKeywords;
    }

    @InternalKotlinNativeApi
    public static /* synthetic */ void getCKeywords$annotations() {
    }
}
