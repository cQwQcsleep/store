package org.jetbrains.kotlin.ir.overrides;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.ir.declarations.IrOverridableMember;
import org.jetbrains.kotlin.ir.util.DumpIrTreeOptions;
import org.jetbrains.kotlin.ir.util.RenderIrElementKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006B\u0011\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\u0005\u0010\tJ\n\u0010\r\u001a\u00020\u000eH\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/ir/overrides/MemberWithOriginal;", "", "member", "Lorg/jetbrains/kotlin/ir/declarations/IrOverridableMember;", "original", "<init>", "(Lorg/jetbrains/kotlin/ir/declarations/IrOverridableMember;Lorg/jetbrains/kotlin/ir/declarations/IrOverridableMember;)V", "fakeOverride", "Lorg/jetbrains/kotlin/ir/overrides/IrFakeOverrideBuilder$FakeOverride;", "(Lorg/jetbrains/kotlin/ir/overrides/IrFakeOverrideBuilder$FakeOverride;)V", "getMember", "()Lorg/jetbrains/kotlin/ir/declarations/IrOverridableMember;", "getOriginal", "toString", "", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class MemberWithOriginal {
    private final IrOverridableMember member;
    private final IrOverridableMember original;

    public MemberWithOriginal(IrOverridableMember irOverridableMember, IrOverridableMember irOverridableMember2) {
        irOverridableMember.getClass();
        this.member = irOverridableMember;
        this.original = irOverridableMember2 != null ? irOverridableMember2 : irOverridableMember;
    }

    public final IrOverridableMember getMember() {
        return this.member;
    }

    public final IrOverridableMember getOriginal() {
        return this.original;
    }

    public String toString() {
        return RenderIrElementKt.render$default(this.member, (DumpIrTreeOptions) null, 1, (Object) null);
    }

    public /* synthetic */ MemberWithOriginal(IrOverridableMember irOverridableMember, IrOverridableMember irOverridableMember2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(irOverridableMember, (i & 2) != 0 ? null : irOverridableMember2);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MemberWithOriginal(IrFakeOverrideBuilder.FakeOverride fakeOverride) {
        this(fakeOverride.getOverride(), fakeOverride.getOriginal());
        fakeOverride.getClass();
    }
}
