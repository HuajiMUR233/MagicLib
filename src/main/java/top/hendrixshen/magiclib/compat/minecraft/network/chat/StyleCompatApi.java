package top.hendrixshen.magiclib.compat.minecraft.network.chat;

import net.minecraft.network.chat.Style;

//#if MC <= 11605
//$$ import org.jetbrains.annotations.Nullable;
//#endif

public interface StyleCompatApi {
    //#if MC >= 11605
    Style EMPTY = Style.EMPTY;
    //#else
    //$$ Style EMPTY = new Style();
    //#endif

    //#if MC <= 11605
    //$$ default Style withStrikethrough(@Nullable Boolean strikethrough) {
    //$$     throw new UnsupportedOperationException();
    //$$ }

    //$$ default Style withObfuscated(@Nullable Boolean obfuscated) {
    //$$     throw new UnsupportedOperationException();
    //$$ }
    //#endif

    //#if MC <= 11502
    //$$ default Style withUnderlined(@Nullable Boolean underlined) {
    //$$     throw new UnsupportedOperationException();
    //$$ }
    //#endif
}
