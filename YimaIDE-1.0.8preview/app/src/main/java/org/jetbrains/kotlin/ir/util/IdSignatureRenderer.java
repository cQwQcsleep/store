package org.jetbrains.kotlin.ir.util;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.CharsKt;
import org.jetbrains.kotlin.library.abi.AbiQualifiedName;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u001c\u0010\u0006\u001a\u00060\nj\u0002`\u000b*\u00060\nj\u0002`\u000b2\u0006\u0010\b\u001a\u00020\tH\u0002J\u001c\u0010\u0006\u001a\u00060\nj\u0002`\u000b*\u00060\nj\u0002`\u000b2\u0006\u0010\b\u001a\u00020\fH\u0002J\u001c\u0010\u0006\u001a\u00060\nj\u0002`\u000b*\u00060\nj\u0002`\u000b2\u0006\u0010\b\u001a\u00020\rH\u0002J\u001c\u0010\u0006\u001a\u00060\nj\u0002`\u000b*\u00060\nj\u0002`\u000b2\u0006\u0010\b\u001a\u00020\u000eH\u0002J\u001c\u0010\u0006\u001a\u00060\nj\u0002`\u000b*\u00060\nj\u0002`\u000b2\u0006\u0010\b\u001a\u00020\u000fH\u0002J\u001c\u0010\u0006\u001a\u00060\nj\u0002`\u000b*\u00060\nj\u0002`\u000b2\u0006\u0010\b\u001a\u00020\u0010H\u0002J\u001c\u0010\u0006\u001a\u00060\nj\u0002`\u000b*\u00060\nj\u0002`\u000b2\u0006\u0010\b\u001a\u00020\u0011H\u0002J\u001c\u0010\u0006\u001a\u00060\nj\u0002`\u000b*\u00060\nj\u0002`\u000b2\u0006\u0010\b\u001a\u00020\u0012H\u0002J\u001c\u0010\u0006\u001a\u00060\nj\u0002`\u000b*\u00060\nj\u0002`\u000b2\u0006\u0010\b\u001a\u00020\u0013H\u0002J\u001c\u0010\u0006\u001a\u00060\nj\u0002`\u000b*\u00060\nj\u0002`\u000b2\u0006\u0010\b\u001a\u00020\u0014H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/ir/util/IdSignatureRenderer;", "", "showDescriptionForPublicSignatures", "", "<init>", "(Z)V", "render", "", "signature", "Lorg/jetbrains/kotlin/ir/util/IdSignature;", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "Lorg/jetbrains/kotlin/ir/util/IdSignature$CommonSignature;", "Lorg/jetbrains/kotlin/ir/util/IdSignature$AccessorSignature;", "Lorg/jetbrains/kotlin/ir/util/IdSignature$CompositeSignature;", "Lorg/jetbrains/kotlin/ir/util/IdSignature$FileSignature;", "Lorg/jetbrains/kotlin/ir/util/IdSignature$LocalSignature;", "Lorg/jetbrains/kotlin/ir/util/IdSignature$FileLocalSignature;", "Lorg/jetbrains/kotlin/ir/util/IdSignature$ScopeLocalDeclaration;", "Lorg/jetbrains/kotlin/ir/util/IdSignature$SpecialFakeOverrideSignature;", "Lorg/jetbrains/kotlin/ir/util/IdSignature$LoweredDeclarationSignature;", "Companion", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class IdSignatureRenderer {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final IdSignatureRenderer DEFAULT = new IdSignatureRenderer(true);
    private static final IdSignatureRenderer LEGACY = new IdSignatureRenderer(false);
    private final boolean showDescriptionForPublicSignatures;

    private IdSignatureRenderer(boolean z) {
        this.showDescriptionForPublicSignatures = z;
    }

    private final StringBuilder render(StringBuilder sb, IdSignature idSignature) {
        if (idSignature instanceof IdSignature.CommonSignature) {
            return render(sb, (IdSignature.CommonSignature) idSignature);
        }
        if (idSignature instanceof IdSignature.AccessorSignature) {
            return render(sb, (IdSignature.AccessorSignature) idSignature);
        }
        if (idSignature instanceof IdSignature.CompositeSignature) {
            return render(sb, (IdSignature.CompositeSignature) idSignature);
        }
        if (idSignature instanceof IdSignature.FileSignature) {
            return render(sb, (IdSignature.FileSignature) idSignature);
        }
        if (idSignature instanceof IdSignature.LocalSignature) {
            return render(sb, (IdSignature.LocalSignature) idSignature);
        }
        if (idSignature instanceof IdSignature.FileLocalSignature) {
            return render(sb, (IdSignature.FileLocalSignature) idSignature);
        }
        if (idSignature instanceof IdSignature.ScopeLocalDeclaration) {
            return render(sb, (IdSignature.ScopeLocalDeclaration) idSignature);
        }
        if (idSignature instanceof IdSignature.SpecialFakeOverrideSignature) {
            return render(sb, (IdSignature.SpecialFakeOverrideSignature) idSignature);
        }
        if (idSignature instanceof IdSignature.LoweredDeclarationSignature) {
            return render(sb, (IdSignature.LoweredDeclarationSignature) idSignature);
        }
        bu8.a();
        return null;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/ir/util/IdSignatureRenderer$Companion;", "", "<init>", "()V", "DEFAULT", "Lorg/jetbrains/kotlin/ir/util/IdSignatureRenderer;", "getDEFAULT", "()Lorg/jetbrains/kotlin/ir/util/IdSignatureRenderer;", "LEGACY", "getLEGACY", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final IdSignatureRenderer getDEFAULT() {
            return IdSignatureRenderer.DEFAULT;
        }

        public final IdSignatureRenderer getLEGACY() {
            return IdSignatureRenderer.LEGACY;
        }

        private Companion() {
        }
    }

    public final String render(IdSignature signature) {
        signature.getClass();
        StringBuilder sb = new StringBuilder();
        render(sb, signature);
        return sb.toString();
    }

    private final StringBuilder render(StringBuilder sb, IdSignature.CommonSignature commonSignature) {
        Object id;
        sb.append(commonSignature.getPackageFqName());
        sb.append(AbiQualifiedName.SEPARATOR);
        sb.append(commonSignature.getDeclarationFqName());
        sb.append('|');
        if (!this.showDescriptionForPublicSignatures || (id = commonSignature.getDescription()) == null) {
            id = commonSignature.getId();
        }
        sb.append(id);
        sb.append('[');
        String string = Long.toString(commonSignature.getMask(), CharsKt.checkRadix(2));
        string.getClass();
        sb.append(string);
        sb.append(']');
        return sb;
    }

    private final StringBuilder render(StringBuilder sb, IdSignature.AccessorSignature accessorSignature) {
        return render(sb, accessorSignature.getAccessorSignature());
    }

    private final StringBuilder render(StringBuilder sb, IdSignature.CompositeSignature compositeSignature) {
        sb.append("[ ");
        StringBuilder sbRender = render(sb, compositeSignature.getContainer());
        sbRender.append(" <- ");
        StringBuilder sbRender2 = render(sbRender, compositeSignature.getInner());
        sbRender2.append(" ]");
        return sbRender2;
    }

    private final StringBuilder render(StringBuilder sb, IdSignature.FileSignature fileSignature) {
        sb.append("File '");
        sb.append(fileSignature.getFileName());
        sb.append('\'');
        return sb;
    }

    private final StringBuilder render(StringBuilder sb, IdSignature.LocalSignature localSignature) {
        sb.append("Local[");
        sb.append(localSignature.getLocalFqn());
        if (localSignature.getHashSig() != null) {
            sb.append(",");
            sb.append(localSignature.getHashSig().longValue());
        }
        sb.append(']');
        return sb;
    }

    private final StringBuilder render(StringBuilder sb, IdSignature.FileLocalSignature fileLocalSignature) {
        StringBuilder sbRender = render(sb, fileLocalSignature.getContainer());
        sbRender.append(':');
        sbRender.append(fileLocalSignature.getId());
        return sbRender;
    }

    private final StringBuilder render(StringBuilder sb, IdSignature.ScopeLocalDeclaration scopeLocalDeclaration) {
        sb.append('#');
        sb.append(scopeLocalDeclaration.getId());
        return sb;
    }

    private final StringBuilder render(StringBuilder sb, IdSignature.SpecialFakeOverrideSignature specialFakeOverrideSignature) {
        return render(sb, specialFakeOverrideSignature.getMemberSignature());
    }

    private final StringBuilder render(StringBuilder sb, IdSignature.LoweredDeclarationSignature loweredDeclarationSignature) {
        sb.append("ic#");
        sb.append(loweredDeclarationSignature.getStage());
        sb.append(':');
        render(sb, loweredDeclarationSignature.getOriginal());
        sb.append('-');
        sb.append(loweredDeclarationSignature.getIndex());
        return sb;
    }
}
