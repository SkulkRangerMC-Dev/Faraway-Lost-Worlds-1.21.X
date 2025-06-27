package net.skulkrange.faraway.worldgen.portal;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;

public class FarawayTeleporter {

    /**
     * Teleports the player to a given position in the destination dimension using the new 1.21.1+ TeleportTransition system.
     *
     * @param player       The player to teleport.
     * @param destination  The ServerLevel (dimension) to teleport to.
     * @param targetPos    The position in the destination to teleport to.
     * @param spawnPortal  Whether to place a portal block at destination (optional).
     */
    public static void teleportToDimension(ServerPlayer player, ServerLevel destination, BlockPos targetPos, boolean spawnPortal) {
        Vec3 posVec = Vec3.atCenterOf(findSafePos(destination, targetPos));

        DimensionTransition transition = new DimensionTransition(
                destination,
                posVec,
                player.getDeltaMovement(),
                player.getYRot(),
                player.getXRot(),
                DimensionTransition.DO_NOTHING
        );
    }

    /**
     * Finds a safe block position above the target location that is not obstructed.
     */
    private static BlockPos findSafePos(ServerLevel level, BlockPos basePos) {
        BlockPos pos = basePos;

        for (int i = 0; i < 20; i++) {
            if (isSafePos(level, pos)) {
                return pos;
            }
            pos = pos.above();
        }
        return basePos.above(10); // fallback
    }

    /**
     * Checks if the position is safe to teleport to.
     */
    private static boolean isSafePos(ServerLevel level, BlockPos pos) {
        return level.getBlockState(pos).isAir()
                && level.getBlockState(pos.above()).isAir()
                && !level.getBlockState(pos.below()).isAir();
    }
}
