package technologicalmayhem.magicalstorage.block.blockentity;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import org.apache.logging.log4j.Level;
import technologicalmayhem.magicalstorage.MagicalStorage;

public class StorageBlockEntity extends BlockEntity {

    public StorageBlockEntity() {
        super(MagicalStorage.STORAGE_BLOCK_ENTITY);
    }

    public void SayStuff()
    {
        MagicalStorage.log(Level.INFO, "I exist!");
    }
}
