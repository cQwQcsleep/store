package io.github.rosemoe.sora.graphics;

import io.github.rosemoe.sora.util.MyCharacter;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class GraphicCharacter {
    public static boolean couldBeEmojiPart(int i) {
        return MyCharacter.isVariationSelector(i) || MyCharacter.isFitzpatrick(i) || MyCharacter.isZWJ(i) || MyCharacter.isZWNJ(i) || MyCharacter.couldBeEmoji(i);
    }

    public static boolean isASCIICombiningSymbol(int i) {
        return i == 46 || i == 47 || i == 33 || i == 61 || i == 60 || i == 62 || i == 45;
    }

    public static boolean isCombiningCharacter(int i) {
        return MyCharacter.isVariationSelector(i) || MyCharacter.isFitzpatrick(i) || MyCharacter.isZWJ(i) || MyCharacter.isZWNJ(i) || MyCharacter.couldBeEmoji(i) || (Character.charCount(i) == 1 && Character.isSurrogate((char) i)) || isASCIICombiningSymbol(i);
    }
}
