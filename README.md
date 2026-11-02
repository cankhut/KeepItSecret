# <img src="readme_assets/keepitsecreticon.gif" width="40" align="top" alt=""> KeepItSecret

A lightweight server-side mod that helps you "Keep It Secret" by stopping nameplates from showing through blocks. If you or your pet is hiding behind a block, their nameplates will be blocked by the block as well! You can easily configure this by using the gamerule commands if you want this feature enabled for players or mobs. Gamerules are set to false by default so you should turn it on when you get in game.

![Miyabi walking past a tree](https://cdn.modrinth.com/data/G9KoeIJt/images/584ad2384cdeaba8d59576194e8ddfd50dc778c1.gif)

<div align="center">

  <img src="https://img.shields.io/modrinth/v/keepitsecret"/>
  <img src="https://img.shields.io/modrinth/dt/keepitsecret"/>
  <img src="https://img.shields.io/modrinth/game-versions/keepitsecret"/>

</div>

## Install

**[Modrinth](https://modrinth.com/mod/keepitsecret)** - **[CurseForge](https://www.curseforge.com/minecraft/mc-mods/keepitsecret)**

Drop the .jar into your `mods` folder. It's a client side mod but add it to the server side as well if you want to control it for everyone with gamerules.

Joining a vanilla server should be fine, you just keep whatever your own config says. 

## Gamerules

Both are on by default. You can turn it off if you want to keep vanilla behaviour:

```
/gamerule keepItSecretPlayers false
/gamerule keepItSecretMobs false
```

## Client config

`config/keepitsecret-client.toml` has the same two switches for your own game:

```toml
hidePlayerNames = true
hideMobNames = true
```
