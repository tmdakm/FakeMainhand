/*
 * Copyright $today.year Huangyu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package win.huangyu.fakemainhand.mixins;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Arm;
import net.minecraft.network.packet.c2s.common.SyncedClientOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import win.huangyu.fakemainhand.ModConfig;

@Mixin(SyncedClientOptions.class)
public abstract class SyncedClientOptionsMixin {

//    @ModifyArg(
//            method = "<init>(Lnet/minecraft/network/PacketByteBuf;)V",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/network/packet/c2s/common/SyncedClientOptions;<init>(Ljava/lang/String;ILnet/minecraft/network/message/ChatVisibility;ZILnet/minecraft/util/Arm;ZZLnet/minecraft/particle/ParticlesMode;)V"
//            ),
//            index = 5
//    )
//    private static Arm modifyMainArmConstructor(Arm original) {
//        ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
//        if(config.enableMod){
//            return config.armServer;
//        }
//        return original;
//    }

    @Shadow
    public abstract Arm mainArm();


    @Redirect(method = "write",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/network/PacketByteBuf;writeEnumConstant(Ljava/lang/Enum;)Lnet/minecraft/network/PacketByteBuf;",
                    ordinal = 1))
    private PacketByteBuf redirectMainArm(PacketByteBuf buf, Enum<?> e){
        ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
        if(config.enableMod){
            buf.writeEnumConstant(config.armServer);
        }else{
            buf.writeEnumConstant(mainArm());
        }

        return buf;
    }
}