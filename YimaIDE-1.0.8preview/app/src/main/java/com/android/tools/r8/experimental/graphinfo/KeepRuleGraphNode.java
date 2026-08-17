package com.android.tools.r8.experimental.graphinfo;

import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;
import com.android.tools.r8.position.TextPosition;
import com.android.tools.r8.position.TextRange;
import com.android.tools.r8.shaking.C3427m3;
import com.android.tools.r8.shaking.C3432n3;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class KeepRuleGraphNode extends GraphNode {
    static final /* synthetic */ boolean g = true;
    private final Origin c;
    private final Position d;
    private final String e;
    private final Set f;

    public KeepRuleGraphNode(C3432n3 c3432n3, Set<GraphNode> set) {
        super(false);
        boolean z = g;
        if (!z && c3432n3 == null) {
            x1f.a();
            throw null;
        }
        if (!z && set == null) {
            x1f.a();
            throw null;
        }
        this.c = c3432n3.i();
        this.d = c3432n3.b;
        this.e = c3432n3.j();
        this.f = set;
    }

    private static String a(Position position) {
        if (position instanceof TextRange) {
            TextPosition start = ((TextRange) position).getStart();
            return start.getLine() + ":" + start.getColumn();
        }
        if (!(position instanceof TextPosition)) {
            return position.getDescription();
        }
        TextPosition textPosition = (TextPosition) position;
        return textPosition.getLine() + ":" + textPosition.getColumn();
    }

    @Override // com.android.tools.r8.experimental.graphinfo.GraphNode
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof KeepRuleGraphNode)) {
            return false;
        }
        KeepRuleGraphNode keepRuleGraphNode = (KeepRuleGraphNode) obj;
        return this.c.equals(keepRuleGraphNode.getOrigin()) && this.d.equals(keepRuleGraphNode.getPosition()) && Objects.equals(this.e, keepRuleGraphNode.getContent()) && this.f.equals(keepRuleGraphNode.getPreconditions());
    }

    public String getContent() {
        return this.e;
    }

    public Origin getOrigin() {
        return this.c;
    }

    public Position getPosition() {
        return this.d;
    }

    public Set<GraphNode> getPreconditions() {
        return this.f;
    }

    @Override // com.android.tools.r8.experimental.graphinfo.GraphNode
    public int hashCode() {
        return Objects.hash(this.c, this.d, this.e, this.f);
    }

    @Override // com.android.tools.r8.experimental.graphinfo.GraphNode
    public String toString() {
        return (getOrigin() == Origin.unknown() ? getContent() : getOrigin()) + ":" + a(getPosition());
    }

    public KeepRuleGraphNode(C3427m3 c3427m3) {
        this(c3427m3, Collections.EMPTY_SET);
    }
}
