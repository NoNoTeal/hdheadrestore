# HD Head Revival Mod (by xTeal)

## What is this mod?
This mod does exactly what it says. It brings back HD Head usage on Minecraft 1.16.5 and above, but targets 1.18.2. 

### What is an HD Head?
A player head with full transparency (not just the hat layer) where you can make your own textures, like a computer

![computer](https://i.ibb.co/B4hrx2j/jcy43gdyiqn61.webp)

It is obviously higher quality, bigger than 16 pixels per side.

### How do you make HD Head textures?

Get a regular Minecraft skin template, and scale it up to a resolution like 4096x4096 and let your creativity run wild.

### Where do I upload it?

Originally you would abuse `education.minecraft.net` but they have fixed their site to not allow image uploads. This mod comes in, and you use an external image host like `imgbb.com` because it's not forcing ads.

Take the DIRECT image link from `imgbb.com` (Looks like `ibb.co`), and plug it into a tool like my [site](https://nonoteal.github.io/hd-heads). It gives you NBT data to put on a `player_head` item.

## Limitations

* Possible security vulnerabilities if others make heads that link to images with malicious code(?)
* Client-side, not visible to everyone. People must have this mod or something similar to this to see your head
* Lag when entering chunks with heads (Usually brief, but is usual when you download big-ass heads)

## What did Mojang do?

* Around 1.17, they added this line of code:

```java
// Excerpt from com.mojang.authlib.yggdrasil.YggdrasilMinecraftSessionService:
private static final String[] ALLOWED_DOMAINS = {
        ".minecraft.net",
        ".mojang.com",
        };

private static final String[] BLOCKED_DOMAINS = {
        "bugs.mojang.com",
        "education.minecraft.net",
        "feedback.minecraft.net"
        };
```

And from 1.16.5:
```java
// Excerpt from com.mojang.authlib.yggdrasil.YggdrasilMinecraftSessionService:
private static final String[] WHITELISTED_DOMAINS = {
        ".minecraft.net",
        ".mojang.com"
        };
```

They patched the bug to not allow users creating heads by using the Mojira, Feedback, or Education sites. 
* On top of this, 1.17 removed the function to see HD Heads (textures already downloaded) because of this line of code:

```java
// Excerpt from net.minecraft.client.texture.PlayerSkinTexture:
        int i = image.getHeight();
        int j = image.getWidth();
        if (j != 64 || i != 32 && i != 64) {
            image.close();
            LOGGER.warn("Discarding incorrectly sized ({}x{}) skin texture from {}", j, i, this.url);
            return null;
        }
        boolean bl2 = bl = i == 32;
```

...which 1.16.5 did not have. This mod fixes it via mixins.

## FAQ Predictions

**Q:** *It doesn't work!*

**A:** *Use the latest Fabric API (0.51.1) and Fabric loader (0.14.2), or something higher* 
