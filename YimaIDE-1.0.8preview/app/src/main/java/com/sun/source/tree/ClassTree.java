package com.sun.source.tree;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.lang.model.element.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ClassTree extends StatementTree {
    Tree getExtendsClause();

    List<? extends Tree> getImplementsClause();

    List<? extends Tree> getMembers();

    ModifiersTree getModifiers();

    default List<? extends Tree> getPermitsClause() {
        return Collections.unmodifiableList(Arrays.asList(new Tree[0]));
    }

    Name getSimpleName();

    List<? extends TypeParameterTree> getTypeParameters();
}
