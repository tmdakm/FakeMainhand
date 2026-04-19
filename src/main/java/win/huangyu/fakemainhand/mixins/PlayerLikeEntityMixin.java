package win.huangyu.fakemainhand.mixins;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.PlayerLikeEntity;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.util.Arm;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import win.huangyu.fakemainhand.ModConfig;

@Mixin(PlayerLikeEntity.class)
public abstract class PlayerLikeEntityMixin {

    @Shadow
    @Final
    protected static TrackedData<Arm> MAIN_ARM_ID;

    @Inject(method = "getMainArm()Lnet/minecraft/util/Arm;", at = @At("RETURN"), cancellable = true)
    private void injected(CallbackInfoReturnable<Arm> cir) {
        if((PlayerLikeEntity)(Object)this instanceof ClientPlayerEntity cpe){
            ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
            if(config.enableMod){
                cir.setReturnValue(config.armClient);
            }else{
                cir.setReturnValue(cpe.getDataTracker().get(MAIN_ARM_ID));
            }

        }
    }
}
