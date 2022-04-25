package xteal.hdhead;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HDHeads implements ModInitializer {
    @Override
    public void onInitialize() {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.info("Initializing HD Heads by xTeal. -HEADS ARE ALLOWED FROM IBB.CO-");
    }
}
