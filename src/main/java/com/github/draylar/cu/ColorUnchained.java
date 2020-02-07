package com.github.draylar.cu;

import com.github.draylar.cu.util.Utils;
import net.minecraftforge.fml.common.Mod;

@Mod("colorunchained")
public class ColorUnchained
{
	public ColorUnchained() {
		if(!Utils.isMixinInClasspath()) throw new IllegalStateException(
				"This is an expected crash because MixinBootstrap isn't (properly) installed. " +
						"You can download it from: https://github.com/LXGaming/MixinBootstrap/releases "+
						"Copy the jar into your mods folder."
		);
	}
}
