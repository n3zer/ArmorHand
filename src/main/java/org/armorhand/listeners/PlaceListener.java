package org.armorhand.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.util.EulerAngle;

import java.util.Random;

public class PlaceListener implements Listener {
    private FileConfiguration config;

    public PlaceListener(FileConfiguration config) {
        this.config = config;
    }

    @EventHandler
    public void onPlaceArmorStand(EntitySpawnEvent event){

        if (event.getEntity().getType() == EntityType.ARMOR_STAND){
            ArmorStand spawnedEntity = (ArmorStand) event.getEntity();
            spawnedEntity.setArms(true);
        }

    }
    @EventHandler
    public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent event){
        Player player = event.getPlayer();
        if (event.getRightClicked().getType().equals(EntityType.ARMOR_STAND) &&
            player.isSneaking())
        {

            ArmorStand as = (ArmorStand) event.getRightClicked();

            Random rand = new Random();

            int n = rand.nextInt(config.getInt("count"));


            as.setRightArmPose(getRotation(String.format("%s.rarm", n)));
            as.setLeftArmPose(getRotation(String.format("%s.larm", n)));
            as.setRightLegPose(getRotation(String.format("%s.rleg", n)));
            as.setLeftLegPose(getRotation(String.format("%s.lleg", n)));
            as.setHeadPose(getRotation(String.format("%s.head", n)));
            as.setBodyPose(getRotation(String.format("%s.body", n)));


        }
    }

    public  EulerAngle getRotation(String path){
        return new EulerAngle(this.config.getDouble(String.format("%s.x", path)),
                this.config.getDouble(String.format("%s.y", path)),
                this.config.getDouble(String.format("%s.z", path)));
    }
}
