package net.fabricmc.example.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class ExampleBlock extends Block {

  public ExampleBlock(Settings settings) {
    super(settings);
  }

  @Override
  public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
    // Need to make texture to be opacity for certain block side
    return VoxelShapes.cuboid(0f, 0f, 0f, 1f, 1.0f, 0.5f);
  }

  @Override
  public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
    if (!world.isClient) {
      player.sendMessage(new LiteralText("ExampleBlock::onUse"), false);
    }

    return ActionResult.SUCCESS;
  }

  @Override
  public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
    super.onPlaced(world, pos, state, placer, itemStack);

    if (!world.isClient) {
      if (placer.isPlayer()) {
        PlayerEntity player = (PlayerEntity) placer;
        player.sendMessage(new LiteralText("ExampleBlock::onPlaced"), false);
      }
    }
  }

  @Override
  public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
    super.onBreak(world, pos, state, player);

    if (!world.isClient) {
      player.sendMessage(new LiteralText("ExampleBlock::onBreak"), false);
    }
  }
}
