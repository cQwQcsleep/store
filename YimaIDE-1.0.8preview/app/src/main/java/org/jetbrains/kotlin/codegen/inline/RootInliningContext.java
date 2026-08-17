package org.jetbrains.kotlin.codegen.inline;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.state.GenerationState;
import org.jetbrains.kotlin.codegen.state.KotlinTypeMapperBase;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001BQ\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000b\u0012\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0015\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/RootInliningContext;", "Lorg/jetbrains/kotlin/codegen/inline/InliningContext;", "state", "Lorg/jetbrains/kotlin/codegen/state/GenerationState;", "nameGenerator", "Lorg/jetbrains/kotlin/codegen/inline/NameGenerator;", "sourceCompilerForInline", "Lorg/jetbrains/kotlin/codegen/inline/SourceCompilerForInline;", "callSiteInfo", "Lorg/jetbrains/kotlin/codegen/inline/InlineCallSiteInfo;", "inlineMethodReifier", "Lorg/jetbrains/kotlin/codegen/inline/ReifiedTypeInliner;", "typeParameterMappings", "Lorg/jetbrains/kotlin/codegen/inline/TypeParameterMappings;", "inlineScopesGenerator", "Lorg/jetbrains/kotlin/codegen/inline/InlineScopesGenerator;", "typeMapper", "Lorg/jetbrains/kotlin/codegen/state/KotlinTypeMapperBase;", "<init>", "(Lorg/jetbrains/kotlin/codegen/state/GenerationState;Lorg/jetbrains/kotlin/codegen/inline/NameGenerator;Lorg/jetbrains/kotlin/codegen/inline/SourceCompilerForInline;Lorg/jetbrains/kotlin/codegen/inline/InlineCallSiteInfo;Lorg/jetbrains/kotlin/codegen/inline/ReifiedTypeInliner;Lorg/jetbrains/kotlin/codegen/inline/TypeParameterMappings;Lorg/jetbrains/kotlin/codegen/inline/InlineScopesGenerator;Lorg/jetbrains/kotlin/codegen/state/KotlinTypeMapperBase;)V", "getSourceCompilerForInline", "()Lorg/jetbrains/kotlin/codegen/inline/SourceCompilerForInline;", "getCallSiteInfo", "()Lorg/jetbrains/kotlin/codegen/inline/InlineCallSiteInfo;", "getInlineMethodReifier", "()Lorg/jetbrains/kotlin/codegen/inline/ReifiedTypeInliner;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class RootInliningContext extends InliningContext {
    private final InlineCallSiteInfo callSiteInfo;
    private final ReifiedTypeInliner<?> inlineMethodReifier;
    private final SourceCompilerForInline sourceCompilerForInline;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RootInliningContext(GenerationState generationState, NameGenerator nameGenerator, SourceCompilerForInline sourceCompilerForInline, InlineCallSiteInfo inlineCallSiteInfo, ReifiedTypeInliner<?> reifiedTypeInliner, TypeParameterMappings<?> typeParameterMappings, InlineScopesGenerator inlineScopesGenerator, KotlinTypeMapperBase kotlinTypeMapperBase) {
        super(null, generationState, nameGenerator, TypeRemapper.INSTANCE.createRoot(typeParameterMappings), null, false, inlineScopesGenerator, kotlinTypeMapperBase);
        generationState.getClass();
        nameGenerator.getClass();
        sourceCompilerForInline.getClass();
        inlineCallSiteInfo.getClass();
        reifiedTypeInliner.getClass();
        typeParameterMappings.getClass();
        kotlinTypeMapperBase.getClass();
        this.sourceCompilerForInline = sourceCompilerForInline;
        this.callSiteInfo = inlineCallSiteInfo;
        this.inlineMethodReifier = reifiedTypeInliner;
    }

    @Override // org.jetbrains.kotlin.codegen.inline.InliningContext
    public InlineCallSiteInfo getCallSiteInfo() {
        return this.callSiteInfo;
    }

    public final ReifiedTypeInliner<?> getInlineMethodReifier() {
        return this.inlineMethodReifier;
    }

    public final SourceCompilerForInline getSourceCompilerForInline() {
        return this.sourceCompilerForInline;
    }
}
