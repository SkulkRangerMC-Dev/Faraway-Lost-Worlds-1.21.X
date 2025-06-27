package net.skulkrange.faraway.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PoisonVineBlock extends Block {

    public PoisonVineBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity)
    {
        super.entityInside(state, level, pos, entity);

        if (!level.isClientSide && entity instanceof LivingEntity livingEntity) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 0));

            System.out.println("entity inside: " + entity.getName().getString());
        }
    }

    private static final VoxelShape VINE_SHAPE = Block.box(0, 0, 0, 16, 2.0D, 16); // 2 pixels tall

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return VINE_SHAPE;
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return VINE_SHAPE;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return VINE_SHAPE;
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true; // Enable random tick for spreading
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        for (int i = 0; i < 4; i++) {
            BlockPos targetPos = pos.offset(
                    random.nextInt(3) - 1,
                    random.nextInt(2) - 1,
                    random.nextInt(3) - 1
            );

            if (level.isEmptyBlock(targetPos) && canPlaceAt(level, targetPos)) {
                level.setBlockAndUpdate(targetPos, this.defaultBlockState());
            }
        }
    }

    // Prevent floating and stacking
    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        return canPlaceAt(world, pos);
    }

    private boolean canPlaceAt(LevelReader world, BlockPos pos) {
        BlockState below = world.getBlockState(pos.below());
        BlockState above = world.getBlockState(pos.above());

        return !(above.getBlock() instanceof PoisonVineBlock) && (
                below.isFaceSturdy(world, pos.below(), Direction.UP) ||
                        world.getBlockState(pos.north()).isFaceSturdy(world, pos.north(), Direction.SOUTH) ||
                        world.getBlockState(pos.south()).isFaceSturdy(world, pos.south(), Direction.NORTH) ||
                        world.getBlockState(pos.east()).isFaceSturdy(world, pos.east(), Direction.WEST) ||
                        world.getBlockState(pos.west()).isFaceSturdy(world, pos.west(), Direction.EAST)
        );
    }

    @Override
    public float getShadeBrightness(BlockState state, BlockGetter world, BlockPos pos) {
        return 1.0F;
    }
}
