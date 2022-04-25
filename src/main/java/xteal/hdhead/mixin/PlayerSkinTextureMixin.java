package xteal.hdhead.mixin;

import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.PlayerSkinTexture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerSkinTexture.class)
public class PlayerSkinTextureMixin {

    /**
     * Overwrite also seems appropriate here but I'm just too stupid on how to modify the variables.
     */
    @Inject(
            method = "remapTexture",
            at = @At("HEAD"),
            cancellable = true
    )
    private void remapTexture(NativeImage image, CallbackInfoReturnable<NativeImage> cir) {
        boolean bl;
        bl = image.getHeight() == 32;
        if (bl) {
            NativeImage nativeImage = new NativeImage(64, 64, true);
            nativeImage.copyFrom(image);
            image.close();
            image = nativeImage;
            image.fillRect(0, 32, 64, 32, 0);
            image.copyRect(4, 16, 16, 32, 4, 4, true, false);
            image.copyRect(8, 16, 16, 32, 4, 4, true, false);
            image.copyRect(0, 20, 24, 32, 4, 12, true, false);
            image.copyRect(4, 20, 16, 32, 4, 12, true, false);
            image.copyRect(8, 20, 8, 32, 4, 12, true, false);
            image.copyRect(12, 20, 16, 32, 4, 12, true, false);
            image.copyRect(44, 16, -8, 32, 4, 4, true, false);
            image.copyRect(48, 16, -8, 32, 4, 4, true, false);
            image.copyRect(40, 20, 0, 32, 4, 12, true, false);
            image.copyRect(44, 20, -8, 32, 4, 12, true, false);
            image.copyRect(48, 20, -16, 32, 4, 12, true, false);
            image.copyRect(52, 20, -8, 32, 4, 12, true, false);
        }
        stripAlpha(image, 0, 0, 32, 16);
        if (bl) {
            stripColor(image, 32, 0, 64, 32);
        }
        stripAlpha(image, 0, 16, 64, 32);
        stripAlpha(image, 16, 48, 48, 64);
        cir.setReturnValue(image);
    }

    @Shadow
    private static void stripColor(NativeImage image, int x1, int y1, int x2, int y2) {
    }

    @Shadow
    private static void stripAlpha(NativeImage image, int x1, int y1, int x2, int y2) {
    }
}
