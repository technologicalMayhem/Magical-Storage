package technologicalmayhem.magicalstorage.block.blockentity;

import com.google.common.eventbus.Subscribe;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DefaultedList;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CoreBlockEntity extends BlockEntity {

    private UUID uuid;
    private List<ItemStack> items = new ArrayList<>();

    public CoreBlockEntity(BlockEntityType<?> type) {
        super(type);
    }

    public void initializeCore()
    {
        uuid = UUID.randomUUID();
    }
}
