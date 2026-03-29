<div align="center">

<img src="https://cdn.modrinth.com/data/LTaa2o7Y/2f622ecc73f0a1e5e3304e7f6f9b5bd5d3ab82a0.png" width="200">

# BuildCraft - NeoForge 1.21.1

A port of BuildCraft to Minecraft 1.21.1 (NeoForge), based on [Buildcraft-Legacy](https://github.com/Thepigcat76/Buildcraft-Legacy).

[![](https://img.shields.io/github/license/Thepigcat76/Buildcraft-Legacy?style=flat&color=900c3f)](https://github.com/Thepigcat76/Buildcraft-Legacy/blob/main/LICENSE)

</div>

## Features

### Pipes (无需额外动力驱动！)

All pipes work without any power source. Items flow automatically through the pipe network.

| Pipe | Type | Description |
|------|------|-------------|
| 🪵 Wooden | Extracting | Pulls items from adjacent containers |
| 🪨 Cobblestone | Basic | Basic item transport |
| 🪨 Stone | Basic | Basic item transport |
| ✨ Quartz | Basic | Basic item transport |
| 🏜️ Sandstone | Basic | Basic item transport |
| 🥇 Gold | Fast | Fast item transport (2x speed) |
| 🔩 Iron | Directional | One-way item transport |
| 🧱 Clay | Color Priority | Prioritizes same-color containers |
| 💎 Diamond | Extracting | Extracting + filtering |
| ⬛ Void | Destruction | Destroys items that enter it |

### Other Features
- 🛢️ Tanks (fluid storage)
- 📦 Crates (item storage)
- ⚙️ Engines (Redstone, Stirling, Combustion)
- 🛢️ Oil world generation
- 🚫 No robots

## Based On

This project is based on [Buildcraft-Legacy](https://github.com/Thepigcat76/Buildcraft-Legacy) by Thepigcat76, which is an unofficial port of the original [BuildCraft](https://www.curseforge.com/minecraft/mc-mods/buildcraft) mod.

Original BuildCraft is licensed under MPL 2.0. Buildcraft-Legacy uses MIT license.

## Building

```bash
./gradlew build
```

The compiled JAR will be in `build/libs/`.

## Requirements

- Minecraft 1.21.1
- NeoForge 21.1.209+
- Java 21
