# Third-party attribution

This mod is a derivative work combining two upstream projects.

## Enhanced Block Entities (base of this fork)

- Original mod: [FoundationGames/EnhancedBlockEntities](https://github.com/FoundationGames/EnhancedBlockEntities)
- Fork this project is built on: [Mat0u5/EnhancedBlockEntitiesReloaded](https://github.com/Mat0u5/EnhancedBlockEntitiesReloaded)
- License: GNU Lesser General Public License v3.0 (LGPL-3.0) — see `LICENSE`.

The dynamic baked-model swap system, the Stonecutter multi-version build, and the
open/close animation plumbing are inherited from this project essentially unchanged.

## FastChest (idle-state optimization technique incorporated)

- Original mod: [FakeDomi/FastChest](https://github.com/FakeDomi/FastChest)
- License: MIT — reproduced below.

The "remove the block-entity renderer and ticker while idle" technique used for
this mod's closed-chest fast path (`ChestBlockMixin`, `EnderChestBlockMixin`,
`BlockEntityRenderManagerMixin`/`BlockEntityRenderDispatcherMixin` short-circuit,
and the static chest model JSONs) is ported and adapted from FastChest.

```
Copyright (c) 2021-2025 Domi

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

As a whole, this project is distributed under LGPL-3.0 (the more restrictive of
the two licenses); MIT-licensed code may be relicensed into an LGPL work.
