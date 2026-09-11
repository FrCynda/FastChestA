# FastChest Animated

A Fabric mod for Minecraft that makes chests fast and keeps their opening animation.

Usually you have to pick one or the other. FastChest makes chests basically free to have around, but the lid stops animating, chests just snap open and closed instantly. Enhanced Block Entities keeps the smooth lid animation, but every closed chest still costs a bit of performance just sitting there, even if nobody ever opens it.

This mod is built on top of both projects and gives you both at once. Chests barely cost anything while closed, and still animate normally when you open or close them. The trick is that a closed chest just isn't doing any work in the background. It wakes up the moment you interact with it, plays the animation, then goes back to sleep once the lid finishes moving.

## How it compares

Tested with 64,000 chests placed in a 40x40x40 cube, all closed, standing where you can see the whole thing (the same spot with no chests placed runs at about 1300 FPS):

| | Vanilla Minecraft | Enhanced Block Entities | FastChest | FastChest Animated (this mod) |
|---|:---:|:---:|:---:|:---:|
| Chests have an opening animation | 🟢 | 🟢 | 🔴 | 🟢 |
| Closed chests are cheap to have around | 🔴 | 🟠 | 🟢 | 🟢 |
| Frame rate with 64,000 chests | ~6 FPS | ~120 FPS | ~300 FPS | ~300 FPS |

This mod matches FastChest's speed while keeping the animation that FastChest gives up.

## Credit

This mod wouldn't exist without two other projects:

- **[Enhanced Block Entities](https://modrinth.com/mod/ebe)** by FoundationGames, and its continuation **Enhanced Block Entities Reloaded** by Mat0u5. This mod is built directly on top of that codebase, and that's where the animation system and the visual improvements (smooth lighting on block entities, resource pack support, seeing chests from far away, etc.) come from.
- **[FastChest](https://modrinth.com/mod/fastchest)** by FakeDomi. The idea of making a closed chest do nothing at all until it's actually opened is theirs. This mod adapts that idea to work alongside the animation instead of replacing it.

See `THIRD_PARTY.txt` for full license details.

## Supported versions

Minecraft 1.21 and newer, Fabric only.

## FAQ and Help

**Q: I need help with the mod / found a bug!**
A: Please open an issue on the Issues tab at the top of this page.

**Q: My chests are invisible!**
A: You might be using a resource pack that conflicts with this mod. Open the Block Entity Settings menu (through this mod's Mod Menu entry, or vanilla Video Settings) and enable "Force Resource Pack Compatibility".

**Q: My chests are still invisible!**
A: You're probably on a Sodium version that doesn't support some Fabric rendering features it needs. Update Sodium, or install [Indium](https://modrinth.com/mod/indium) alongside it.

## For other mod developers: is your mod incompatible with this one?

If your mod changes how block entities render, this mod might break it. There's a small API to disable specific features so your mod can take over instead, and you don't need this mod as a dependency in your dev environment to use it.

### Add the entrypoint
`fabric.mod.json`:
```json
{
  "entrypoints": {
    "main": [...],
    "client": [...],
    "ebe_v1": [
      "my.mod.compat.EBECompatibility"
    ]
  }
}
```

### Need to override config values? Implement `BiConsumer<Properties, Map<String, Text>>`
`my.mod.compat.EBECompatibility`:
```java
public class EBECompatibility implements BiConsumer<Properties, Map<String, Text>>, ... {
@Override
public void accept(Properties overrideConfigValues, Map<String, Text> overrideReasons) {
	overrideConfigValues.setProperty("render_enhanced_chests", "false");

	overrideReasons.put("render_enhanced_chests",
			Text.literal("Enhanced Chests are not compatible with my mod!")
					.formatted(Formatting.YELLOW));
}
    
    ...
			}
```
`accept(Properties, Map<String, Text>)` gets called when this mod loads its config. Set a property on `overrideConfigValues` to force that option, which also grays it out in the config menu.

Add a text component to `overrideReasons` under the same key if you want to explain to the user why your mod changed it.

`Text` here is `net.minecraft.text.Text` when using Yarn mappings.

### Need to reload config manually? Implement `Consumer<Runnable>`
`my.mod.compat.EBECompatibility`:
```java
public class EBECompatibility implements Consumer<Runnable>, ... {
private static Runnable reloader = () -> {};
    
    ...

@Override
public void accept(Runnable configReloader) {
	reloader = configReloader;
}
}
```
If your mod needs to change this mod's config depending on loaded resources, you can hit load order issues. Reloading manually works around that.

`accept(Runnable)` runs once when this mod loads, handing you a `Runnable` that triggers the internal config reload. Stash it in a field and call it whenever you need to.
```java
void onMyModResourceReload() {
	EBECompatibility.someParameter = true;
	EBECompatibility.reloader.run();
	// Your config handler in EBECompatibility can react to
	// EBECompatibility.someParameter here.
}
```
