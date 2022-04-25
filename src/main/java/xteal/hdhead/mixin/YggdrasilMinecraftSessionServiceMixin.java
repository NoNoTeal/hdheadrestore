package xteal.hdhead.mixin;

import com.mojang.authlib.yggdrasil.YggdrasilMinecraftSessionService;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.net.URI;
import java.net.URISyntaxException;

@Mixin(value = YggdrasilMinecraftSessionService.class, remap = false)
public class YggdrasilMinecraftSessionServiceMixin {
    @Shadow @Final private static Logger LOGGER;
    private static final String[] ALLOWED_DOMAINS = {
            ".minecraft.net",
            ".mojang.com",
            ".ibb.co"
    };

    private static final String[] BLOCKED_DOMAINS = {
            ".pornhub.com"
    };

    @Overwrite
    private static boolean isAllowedTextureDomain(final String url) {
        URI uri;

        try {
            uri = new URI(url);
        } catch (final URISyntaxException ignored) {
            throw new IllegalArgumentException("Invalid URL '" + url + "'");
        }

        final String domain = uri.getHost();
        // LOGGER.info("HD Heads Checkout - " + url);
        return isDomainOnList(domain, ALLOWED_DOMAINS) && !isDomainOnList(domain, BLOCKED_DOMAINS);
    }

    @Shadow
    private static boolean isDomainOnList(final String domain, final String[] list) {
        return false;
    }
}
