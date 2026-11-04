# <img src="readme_assets/keepitsecreticon.gif" width="40" align="top" alt=""> KeepItSecret

A lightweight client-side mod that helps you "Keep It Secret" by stopping nameplates from showing through blocks. If you or your pet is hiding behind a block, their nameplates will be blocked by the block as well! Works for both player and mob nameplates, and you can turn either one on or off whenever you want.

![Miyabi walking past a tree](https://cdn.modrinth.com/data/G9KoeIJt/images/584ad2384cdeaba8d59576194e8ddfd50dc778c1.gif)

<div align="center">

  <img src="https://img.shields.io/modrinth/v/keepitsecret"/>
  <img src="https://img.shields.io/modrinth/dt/keepitsecret"/>
  <img src="https://img.shields.io/modrinth/game-versions/keepitsecret"/>

</div>

## Install

**[Modrinth](https://modrinth.com/mod/keepitsecret)** - **[CurseForge](https://www.curseforge.com/minecraft/mc-mods/keepitsecret)**

Drop the .jar into your `mods` folder. It's a client side mod but add it to the server side as well if you want to control it for everyone with gamerules.

You can join servers that don't have the mod, and players without the mod can join servers that have it, they just see normal nameplates.

## Gamerules

When the server has KeepItSecret, these decide for everyone who has the mod. Both are on by default. You can turn them off if you want to keep vanilla behaviour:

```
/gamerule keepItSecretPlayers false
/gamerule keepItSecretMobs false
```

## Your own settings

On servers without KeepItSecret, you decide for yourself. Go to **Esc → Mods → KeepItSecret → Config** and switch player or mob nameplates on or off, it applies right away. Both are on by default.

The same switches are saved in `config/keepitsecret-client.toml`.
