package io.github.rosemoe.sora.lang.completion.snippet;

import io.github.rosemoe.sora.lang.styling.inlayHint.TextInlayHint;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B?\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010 \u001a\u00020\u0003HÆ\u0003J\u0011\u0010!\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0003J\u000f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\b0\u0005HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\nHÆ\u0003JA\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nHÆ\u0001J\u0013\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010(\u001a\u00020\u0003HÖ\u0001J\t\u0010)\u001a\u00020\u0006HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\"\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R \u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0012\"\u0004\b\u0016\u0010\u0014R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u0006X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f¨\u0006*"}, d2 = {"Lio/github/rosemoe/sora/lang/completion/snippet/PlaceholderDefinition;", "", "id", "", "choices", "", "", "elements", "Lio/github/rosemoe/sora/lang/completion/snippet/PlaceHolderElement;", "transform", "Lio/github/rosemoe/sora/lang/completion/snippet/Transform;", "<init>", "(ILjava/util/List;Ljava/util/List;Lio/github/rosemoe/sora/lang/completion/snippet/Transform;)V", "getId", "()I", "setId", "(I)V", "getChoices", "()Ljava/util/List;", "setChoices", "(Ljava/util/List;)V", "getElements", "setElements", "getTransform", "()Lio/github/rosemoe/sora/lang/completion/snippet/Transform;", "setTransform", "(Lio/github/rosemoe/sora/lang/completion/snippet/Transform;)V", TextInlayHint.TYPE_NAME, "getText$editor_release", "()Ljava/lang/String;", "setText$editor_release", "(Ljava/lang/String;)V", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final /* data */ class PlaceholderDefinition {
    private List<String> choices;
    private List<? extends PlaceHolderElement> elements;
    private int id;
    private String text;
    private Transform transform;

    public /* synthetic */ PlaceholderDefinition(int i, List list, List list2, Transform transform, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? null : list, (i2 & 4) != 0 ? new ArrayList() : list2, (i2 & 8) != 0 ? null : transform);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ PlaceholderDefinition copy$default(PlaceholderDefinition placeholderDefinition, int i, List list, List list2, Transform transform, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = placeholderDefinition.id;
        }
        if ((i2 & 2) != 0) {
            list = placeholderDefinition.choices;
        }
        if ((i2 & 4) != 0) {
            list2 = placeholderDefinition.elements;
        }
        if ((i2 & 8) != 0) {
            transform = placeholderDefinition.transform;
        }
        return placeholderDefinition.copy(i, list, list2, transform);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    public final List<String> component2() {
        return this.choices;
    }

    public final List<PlaceHolderElement> component3() {
        return this.elements;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Transform getTransform() {
        return this.transform;
    }

    public final PlaceholderDefinition copy(int id, List<String> choices, List<? extends PlaceHolderElement> elements, Transform transform) {
        elements.getClass();
        return new PlaceholderDefinition(id, choices, elements, transform);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlaceholderDefinition)) {
            return false;
        }
        PlaceholderDefinition placeholderDefinition = (PlaceholderDefinition) other;
        return this.id == placeholderDefinition.id && Intrinsics.areEqual(this.choices, placeholderDefinition.choices) && Intrinsics.areEqual(this.elements, placeholderDefinition.elements) && Intrinsics.areEqual(this.transform, placeholderDefinition.transform);
    }

    public final List<String> getChoices() {
        return this.choices;
    }

    public final List<PlaceHolderElement> getElements() {
        return this.elements;
    }

    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: getText$editor_release, reason: from getter */
    public final String getText() {
        return this.text;
    }

    public final Transform getTransform() {
        return this.transform;
    }

    public int hashCode() {
        int iHashCode = Integer.hashCode(this.id) * 31;
        List<String> list = this.choices;
        int iHashCode2 = (((iHashCode + (list == null ? 0 : list.hashCode())) * 31) + this.elements.hashCode()) * 31;
        Transform transform = this.transform;
        return iHashCode2 + (transform != null ? transform.hashCode() : 0);
    }

    public final void setChoices(List<String> list) {
        this.choices = list;
    }

    public final void setElements(List<? extends PlaceHolderElement> list) {
        list.getClass();
        this.elements = list;
    }

    public final void setId(int i) {
        this.id = i;
    }

    public final void setText$editor_release(String str) {
        this.text = str;
    }

    public final void setTransform(Transform transform) {
        this.transform = transform;
    }

    public String toString() {
        return "PlaceholderDefinition(id=" + this.id + ", choices=" + this.choices + ", elements=" + this.elements + ", transform=" + this.transform + ")";
    }

    public PlaceholderDefinition(int i, List<String> list) {
        this(i, list, null, null, 12, null);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public PlaceholderDefinition(int i, List<String> list, List<? extends PlaceHolderElement> list2) {
        this(i, list, list2, null, 8, null);
        list2.getClass();
    }

    public PlaceholderDefinition(int i, List<String> list, List<? extends PlaceHolderElement> list2, Transform transform) {
        list2.getClass();
        this.id = i;
        this.choices = list;
        this.elements = list2;
        this.transform = transform;
    }

    public PlaceholderDefinition(int i) {
        this(i, null, null, null, 14, null);
    }
}
