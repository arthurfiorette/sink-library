package com.github.arthurfiorette.sinklibrary.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.event.server.*;
import org.bukkit.event.vehicle.*;
import org.bukkit.event.weather.*;
import org.bukkit.event.world.*;

import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseService;

import lombok.*;

/**
 * A abstract Spigot listener implementation with the most methods implements.
 * Override and use {@link org.bukkit.event.EventHandler} to make the method be
 * a listener. This listener will work when register is called.
 *
 * @author https://github.com/ArthurFiorette/sink-library/
 */
@RequiredArgsConstructor
public abstract class SinkListener implements Listener, BaseService {

  @Getter
  @NonNull
  protected final BasePlugin basePlugin;

  @Override
  public void enable() {
    Bukkit.getPluginManager().registerEvents(this, this.getBasePlugin());
  }

  @Override
  public void disable() {
    HandlerList.unregisterAll(this);
  }

  public void onAsyncPlayerChat(final AsyncPlayerChatEvent event) {}

  public void onAsyncPlayerPreLogin(final AsyncPlayerPreLoginEvent event) {}

  public void onBlockBreak(final BlockBreakEvent event) {}

  public void onBlockBurn(final BlockBurnEvent event) {}

  public void onBlockCanBuild(final BlockCanBuildEvent event) {}

  public void onBlockDamage(final BlockDamageEvent event) {}

  public void onBlockDispense(final BlockDispenseEvent event) {}

  public void onBlockFade(final BlockFadeEvent event) {}

  public void onBlockForm(final BlockFormEvent event) {}

  public void onBlockFromTo(final BlockFromToEvent event) {}

  public void onBlockGrow(final BlockGrowEvent event) {}

  public void onBlockIgnite(final BlockIgniteEvent event) {}

  public void onBlockPhysics(final BlockPhysicsEvent event) {}

  public void onBlockPistonExtend(final BlockPistonExtendEvent event) {}

  public void onBlockPistonRetract(final BlockPistonRetractEvent event) {}

  public void onBlockPlace(final BlockPlaceEvent event) {}

  public void onBlockRedstone(final BlockRedstoneEvent event) {}

  public void onBlockSpread(final BlockSpreadEvent event) {}

  public void onBrewEvent(final BrewEvent event) {}

  public void onChunkLoadEvent(final ChunkLoadEvent event) {}

  public void onChunkPopulateEvent(final ChunkPopulateEvent event) {}

  public void onChunkUnload(final ChunkUnloadEvent event) {}

  public void onCraftItem(final CraftItemEvent event) {}

  public void onCreatureSpawn(final CreatureSpawnEvent event) {}

  public void onSinkListener(final CreeperPowerEvent event) {}

  public void onEnchantItem(final EnchantItemEvent event) {}

  public void onEntityBlockForm(final EntityBlockFormEvent event) {}

  public void onEntityBreakDoor(final EntityBreakDoorEvent event) {}

  public void onEntityChangeBlock(final EntityChangeBlockEvent event) {}

  public void onEntityCombustByBlock(final EntityCombustByBlockEvent event) {}

  public void onEntityCombustByEntity(final EntityCombustByEntityEvent event) {}

  public void onEntityCombust(final EntityCombustEvent event) {}

  public void onEntityCreatePortal(final EntityCreatePortalEvent event) {}

  public void onEntityDamageByBlock(final EntityDamageByBlockEvent event) {}

  public void onEntityDamageByEntity(final EntityDamageByEntityEvent event) {}

  public void onEntityDamage(final EntityDamageEvent event) {}

  public void onEntityDeath(final EntityDeathEvent event) {}

  public void onEntityExplode(final EntityExplodeEvent event) {}

  public void onEntityInteract(final EntityInteractEvent event) {}

  public void onEntityPortalEnter(final EntityPortalEnterEvent event) {}

  public void onEntityRegainHealth(final EntityRegainHealthEvent event) {}

  public void onEntityShootBow(final EntityShootBowEvent event) {}

  public void onEntityTame(final EntityTameEvent event) {}

  public void onEntityTarget(final EntityTargetEvent event) {}

  public void onEntityTargetLivingEntity(final EntityTargetLivingEntityEvent event) {}

  public void onEntityTeleport(final EntityTeleportEvent event) {}

  public void onExpBottle(final ExpBottleEvent event) {}

  public void onExplosionPrime(final ExplosionPrimeEvent event) {}

  public void onFoodLevelChange(final FoodLevelChangeEvent event) {}

  public void onFurnaceBurnEvent(final FurnaceBurnEvent event) {}

  public void onFurnaceSmelt(final FurnaceSmeltEvent event) {}

  public void onInventoryClick(final InventoryClickEvent event) {}

  public void onInventoryClose(final InventoryCloseEvent event) {}

  public void onInventoryOpen(final InventoryOpenEvent event) {}

  public void onItemDespawn(final ItemDespawnEvent event) {}

  public void onLeavesDecay(final LeavesDecayEvent event) {}

  public void onLightningStrike(final LightningStrikeEvent event) {}

  public void onMapInitializeEvent(final MapInitializeEvent event) {}

  public void onNotePlay(final NotePlayEvent event) {}

  public void onPigZap(final PigZapEvent event) {}

  public void onPlayerAnimation(final PlayerAnimationEvent event) {}

  public void onPlayerBedEnter(final PlayerBedEnterEvent event) {}

  public void onPlayerBedLeave(final PlayerBedLeaveEvent event) {}

  public void onPlayerBucketEmpty(final PlayerBucketEmptyEvent event) {}

  public void onPlayerBucketFill(final PlayerBucketFillEvent event) {}

  public void onPlayerChatTabComplete(final PlayerChatTabCompleteEvent event) {}

  public void onPlayerCommandPreprocess(final PlayerCommandPreprocessEvent event) {}

  public void onPlayerDeath(final PlayerDeathEvent event) {}

  public void onPlayerDropItem(final PlayerDropItemEvent event) {}

  public void onPlayerEggThrow(final PlayerEggThrowEvent event) {}

  public void onPlayerExpChange(final PlayerExpChangeEvent event) {}

  public void onPlayerFish(final PlayerFishEvent event) {}

  public void onPlayerGameModeChange(final PlayerGameModeChangeEvent event) {}

  public void onPlayerInteractEntity(final PlayerInteractEntityEvent event) {}

  public void onPlayerInteract(final PlayerInteractEvent event) {}

  public void onPlayerItemBreak(final PlayerItemBreakEvent event) {}

  public void onPlayerItemHeld(final PlayerItemHeldEvent event) {}

  public void onPlayerJoin(final PlayerJoinEvent event) {}

  public void onPlayerKick(final PlayerKickEvent event) {}

  public void onPlayerLevelChange(final PlayerLevelChangeEvent event) {}

  public void onPlayerLogin(final PlayerLoginEvent event) {}

  public void onPlayerMove(final PlayerMoveEvent event) {}

  public void onPlayerPickupItem(final PlayerPickupItemEvent event) {}

  public void onPlayerPortal(final PlayerPortalEvent event) {}

  public void onPlayerQuit(final PlayerQuitEvent event) {}

  public void onPlayerRegisterChannel(final PlayerRegisterChannelEvent event) {}

  public void onPlayerRespawn(final PlayerRespawnEvent event) {}

  public void onPlayerShearEntity(final PlayerShearEntityEvent event) {}

  public void onPlayerTeleport(final PlayerTeleportEvent event) {}

  public void onPlayerToggleFlight(final PlayerToggleFlightEvent event) {}

  public void onPlayerToggleSneak(final PlayerToggleSneakEvent event) {}

  public void onPlayerToggleSprint(final PlayerToggleSprintEvent event) {}

  public void onPlayerUnregisterChannel(final PlayerUnregisterChannelEvent event) {}

  public void onPlayerVelocity(final PlayerVelocityEvent event) {}

  public void onPluginDisable(final PluginDisableEvent event) {}

  public void onPluginEnable(final PluginEnableEvent event) {}

  public void onPortalCreate(final PortalCreateEvent event) {}

  public void onPotionSplash(final PotionSplashEvent event) {}

  public void onPrepareItemEnchant(final PrepareItemEnchantEvent event) {}

  public void onRemoteServerCommand(final RemoteServerCommandEvent event) {}

  public void onServerCommand(final ServerCommandEvent event) {}

  public void onServerListPing(final ServerListPingEvent event) {}

  public void onServiceRegister(final ServiceRegisterEvent event) {}

  public void onServiceUnregister(final ServiceUnregisterEvent event) {}

  public void onSheepDyeWoolEvent(final SheepDyeWoolEvent event) {}

  public void onSheepRegrowWoolEvent(final SheepRegrowWoolEvent event) {}

  public void onSignChange(final SignChangeEvent event) {}

  public void onSlimeSplit(final SlimeSplitEvent event) {}

  public void onSpawnChange(final SpawnChangeEvent event) {}

  public void onStructureGrow(final StructureGrowEvent event) {}

  public void onThunderChange(final ThunderChangeEvent event) {}

  public void onVehicleBlockCollision(final VehicleBlockCollisionEvent event) {}

  public void onVehicleCreate(final VehicleCreateEvent event) {}

  public void onVehicleDamage(final VehicleDamageEvent event) {}

  public void onVehicleDestroy(final VehicleDestroyEvent event) {}

  public void onVehicleEnter(final VehicleEnterEvent event) {}

  public void onVehicleEntityCollision(final VehicleEntityCollisionEvent event) {}

  public void onVehicleExit(final VehicleExitEvent event) {}

  public void onVehicleMove(final VehicleMoveEvent event) {}

  public void onVehicleUpdate(final VehicleUpdateEvent event) {}

  public void onWeatherChange(final WeatherChangeEvent event) {}

  public void onWorldInit(final WorldInitEvent event) {}

  public void onWorldLoad(final WorldLoadEvent event) {}

  public void onWorldSave(final WorldSaveEvent event) {}

  public void onWorldUnload(final WorldUnloadEvent event) {}
}
