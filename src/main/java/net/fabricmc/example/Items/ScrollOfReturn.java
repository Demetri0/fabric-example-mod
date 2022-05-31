package net.fabricmc.example.Items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ScrollOfReturn extends Item {
  public ScrollOfReturn(Settings settings) {
    super(settings);
  }

  @Override
  public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
    if (!world.isClient) {
      var srvPlayer = (ServerPlayerEntity) player;
      var srvWorld = (ServerWorld) world;

      var spawn = srvPlayer.getSpawnPointPosition();

      var resPos = PlayerEntity.findRespawnPosition(srvWorld, spawn, 0, false, true);
      if (!resPos.isPresent()) {
        player.sendMessage(new LiteralText("Respawn position is blocked"), false);
        return TypedActionResult.fail(player.getStackInHand(hand));
      }

      var pos = resPos.get();
      player.teleport(pos.getX(), pos.getY(), pos.getZ());
    }

    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.NEUTRAL, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));

    var itemStack = player.getStackInHand(hand);
    if (!player.getAbilities().creativeMode) {
        itemStack.decrement(1);
    }
    return TypedActionResult.success(itemStack, world.isClient());
  }
}
