package top.hendrixshen.magiclib.compat.mixin.minecraft.network.chat;

import net.minecraft.network.chat.Style;
import org.spongepowered.asm.mixin.Mixin;
import top.hendrixshen.magiclib.compat.minecraft.network.chat.StyleCompatApi;

//#if MC <= 11605
//$$ import org.jetbrains.annotations.Nullable;
//$$ import org.spongepowered.asm.mixin.Final;
//$$ import org.spongepowered.asm.mixin.Mutable;
//$$ import org.spongepowered.asm.mixin.Shadow;
//#endif

//#if MC <= 11502
//$$ import net.minecraft.ChatFormatting;
//$$ import org.spongepowered.asm.mixin.injection.At;
//$$ import org.spongepowered.asm.mixin.injection.Inject;
//$$ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//#endif

@Mixin(Style.class)
public abstract class MixinStyle implements StyleCompatApi {

    //#if MC <= 11605
    //$$ @Mutable
    //$$ @Final
    //$$ @Shadow
    //$$ private Boolean strikethrough;

    //$$ @Mutable
    //$$ @Final
    //$$ @Shadow
    //$$ private Boolean obfuscated;


    //$$ @SuppressWarnings("ConstantConditions")
    //$$ @Override
    //$$ public Style withStrikethrough(@Nullable Boolean strikethrough) {
    //#if MC > 11502
    //$$     Style thisStyle = (Style) (Object) this;
    //$$     Boolean oldStrikethrough = this.strikethrough;
    //$$     this.strikethrough = strikethrough;
    //$$     Style ret = thisStyle.applyTo(thisStyle);
    //$$     this.strikethrough = oldStrikethrough;
    //$$     return ret;
    //#else
    //$$     this.copy().setStrikethrough(strikethrough);
    //#endif
    //$$ }

    //$$ @SuppressWarnings("ConstantConditions")
    //$$ @Override
    //$$ public Style withObfuscated(@Nullable Boolean obfuscated) {
    //#if MC > 11502
    //$$     Style thisStyle = (Style) (Object) this;
    //$$     Boolean OldObfuscated = this.obfuscated;
    //$$     this.obfuscated = OldObfuscated;
    //$$     Style ret = thisStyle.applyTo(thisStyle);
    //$$     this.obfuscated = OldObfuscated;
    //$$     return ret;
    //#else
    //$$     this.copy().setObfuscated(strikethrough);
    //#endif
    //$$ }
    //#endif


    //#if MC <= 11502
    //$$ @Override
    //$$ public Style withUnderlined(@Nullable Boolean underlined) {
    //$$     this.copy().setUnderlined(underlined);
    //$$ }

    //$$ @Shadow
    //$$ public abstract Style copy();

    //$$ private final ThreadLocal<Boolean> internalSet = ThreadLocal.withInitial(() -> false);

    //$$ @Inject(method = "setColor", at = @At(value = "HEAD"), cancellable = true)
    //$$ public void preSetColor(ChatFormatting chatFormatting, CallbackInfoReturnable<Style> cir) {
    //$$     if (!internalSet.get()) {
    //$$         internalSet.set(true);
    //$$         Style ret = this.copy().setColor(chatFormatting);
    //$$         internalSet.set(false);
    //$$         cir.setReturnValue(ret);
    //$$     }
    //$$ }

    //$$ @Inject(method = "setBold", at = @At(value = "HEAD"), cancellable = true)
    //$$ public void preSetBold(Boolean boolean_, CallbackInfoReturnable<Style> cir) {
    //$$     if (!internalSet.get()) {
    //$$         internalSet.set(true);
    //$$         Style ret = this.copy().setBold(boolean_);
    //$$         internalSet.set(false);
    //$$         cir.setReturnValue(ret);
    //$$     }
    //$$ }
    //#endif


//    public Style setItalic(Boolean boolean_) {
//        return this.copy().setItalic(boolean_);
//    }
//
//    public Style setStrikethrough(Boolean boolean_) {
//        return this.copy().setStrikethrough(boolean_);
//    }
//
//    public Style setUnderlined(Boolean boolean_) {
//        return this.copy().setUnderlined(boolean_);
//    }
//
//    public Style setObfuscated(Boolean boolean_) {
//        return this.copy().setObfuscated(boolean_);
//    }
//
//    public Style setClickEvent(ClickEvent clickEvent) {
//        return this.copy().setClickEvent(clickEvent);
//    }
//
//    public Style setHoverEvent(HoverEvent hoverEvent) {
//        return this.copy().setHoverEvent(hoverEvent);
//    }

}
