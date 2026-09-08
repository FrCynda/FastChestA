# FastChest Animated

A Fabric mod for Minecraft that makes chests fast **and** keeps their opening animation.

Normally you have to pick one of two trade-offs. Some mods (like FastChest) make chests almost free to have around, but the lid stops animating — chests just snap open and closed. Other mods (like Enhanced Block Entities) keep the nice smooth lid animation, but every chest still costs a little bit of performance just sitting there closed, even if you never open it.

This mod is built on top of both of those projects and gets you both things at once: chests that sit there costing almost nothing when closed, and still open and close with the normal smooth animation when you actually use them. The trick is that a closed chest simply isn't doing any work in the background — it "wakes up" for the moment you open or close it, and goes back to sleep the instant the lid finishes moving.

## How it compares

Tested with 64,000 chests placed in a 40×40×40 cube, all closed, standing where you can see the whole thing:

| | Vanilla Minecraft | FastChest | FastChest Animated (this mod) |
|---|:---:|:---:|:---:|
| Chests have an opening animation | ✅ | ❌ | ✅ |
| Closed chests are cheap to have around | ❌ | ✅ | ✅ |
| Frame rate with a huge wall of chests | Lowest | ~300 FPS | ~230-290 FPS |

FastChest is still a hair faster, since it does nothing at all for a closed chest, but it also permanently gives up the animation to get there. This mod gets close to that same speed while keeping the part FastChest gives up.

## Credit

This mod wouldn't exist without two other projects:

- **[Enhanced Block Entities](https://modrinth.com/mod/ebe)** by FoundationGames, and its continuation **Enhanced Block Entities Reloaded** by Mat0u5 — this mod is built directly on top of that codebase, and it's where the animation system and the visual improvements (smooth lighting on block entities, resource pack support, seeing chests from far away, etc.) come from.
- **[FastChest](https://modrinth.com/mod/fastchest)** by FakeDomi — the idea of making a closed chest do nothing at all until it's actually opened is theirs. This mod adapts that idea so it works alongside the animation instead of replacing it.

See `THIRD_PARTY.md` for full license details.

## Supported versions

Minecraft 1.21 and newer, Fabric only.

## FAQ and Help

**Q: I need help with the mod / need to report a bug!** <br/>
**A:** Please report it on the Issues tab at the top of this page.

**Q: My chests are invisible!** <br/>
**A:** You may be using a resource pack that conflicts with this mod. Open the Block Entity Settings menu (through this mod's Mod Menu entry, or through vanilla Video Settings) and enable "Force Resource Pack Compatibility".

**Q: My chests are still invisible!** <br/>
**A:** You're likely using a Sodium version that doesn't support certain Fabric rendering features. Try updating Sodium, or install [Indium](https://modrinth.com/mod/indium) alongside it.

## For other mod developers: is your mod incompatible with this one?

If your mod changes how block entities render, this mod may break it. There's an API to force-disable specific features here so your mod can take over instead — and you don't need to add this mod as a dependency in your development environment to use it.

### Add the Entrypoint
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

### Need to modify config values? Implement `BiConsumer<Properties, Map<String, Text>>`
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
The `accept(Properties, Map<String, Text>)` function is called when this mod loads config values. You can override a desired config value by setting the corresponding property of `overrideConfigValues`. This will also gray out the option in the config menu.
<br/>
To explain to users why your mod made that change, you can add a text component to the `overrideReasons` map corresponding to the key of the option you changed.
<br/>
`Text` is `net.minecraft.text.Text` when using Yarn mappings.

### Need to manually reload config? Implement `Consumer<Runnable>`
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
If your mod needs to modify this mod's config values depending on loaded resources, it may encounter load order problems. This can be somewhat fixed by manually reloading.
<br/>
The `accept(Runnable)` function is called when this mod is first loaded. The `Runnable` executes the mod's internal reload. Store this in a field so you can execute it whenever necessary.
```java
void onMyModResourceReload() {
	EBECompatibility.someParameter = true;
	EBECompatibility.reloader.run();
	// Your config modification handler in EBECompatibility can change
	// its behavior based on EBECompatibility.someParameter.
}
```
